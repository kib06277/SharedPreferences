package com.order.mostgreen.sharedpreferences

import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var settings: SharedPreferences
    lateinit var nameField: EditText
    lateinit var phoneField: EditText
    lateinit var sexField: EditText
    lateinit var saveButton: Button
    lateinit var readButton: Button
    lateinit var clearButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        setEventListener()
    }

    private  fun init(){
        nameField = this.findViewById(R.id.nameField)
        phoneField = this.findViewById(R.id.phoneField)
        sexField = this.findViewById(R.id.sexField)
        saveButton = this.findViewById(R.id.saveButton)
        readButton = this.findViewById(R.id.readButton)
        clearButton = this.findViewById(R.id.clearButton)
    }

    private fun readData() {
        settings = getSharedPreferences(DATA, 0)
        nameField.setText(settings.getString(NAME, ""))
        phoneField.setText(settings.getString(PHONE, ""))
        sexField.setText(settings.getString(SEX, ""))
    }

    private fun saveData() {
        settings = getSharedPreferences(DATA, 0)
        settings.edit()
            .putString(NAME, nameField.text.toString())
            .putString(PHONE, phoneField.text.toString())
            .putString(SEX, sexField.text.toString())
            .apply()
    }

    private fun setEventListener() {
        saveButton.setOnClickListener {
            saveData()
            Toast.makeText(this@MainActivity, R.string.save_success, Toast.LENGTH_SHORT).show()
        }
        readButton.setOnClickListener {
            readData()
            Toast.makeText(this@MainActivity, R.string.read_success, Toast.LENGTH_SHORT).show()
        }
        clearButton.setOnClickListener {
            nameField.setText("")
            phoneField.setText("")
            sexField.setText("")
        }
    }

    companion object {
        private const val DATA = "DATA"
        private const val NAME = "NAME"
        private const val PHONE = "PHONE"
        private const val SEX = "SEX"
    }
}