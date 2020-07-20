package com.agrilinks.mytodos

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_item.*
import java.util.*

class AddItem : AppCompatActivity() {

    var databaseRepo : ToDoRepo ? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_item)
        databaseRepo = ToDoRepo(AddItem@this)
        add.setOnClickListener {
            if(validateInput()) {
                databaseRepo!!.insertTask(
                    ToDo(
                        createTransactionID()!!,
                        et_title.text.toString(),
                        et_description.text.toString(),
                        false
                    )
                )
                et_title.setText("")
                et_description.setText("")
                setResult(Activity.RESULT_OK)
                finish()
            }
        }
    }

    @Throws(Exception::class)
    fun createTransactionID(): String? {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase()
    }

    private fun validateInput(): Boolean {
        if(et_title.text.toString().isEmpty()) {
            et_title.error = getString(R.string.error_title)
            et_title.requestFocus()
            return false
        }

        if(et_description.text.toString().isEmpty()) {
            et_description.error = getString(R.string.error_description)
            et_description.requestFocus()
            return false
        }

        return true
    }
}