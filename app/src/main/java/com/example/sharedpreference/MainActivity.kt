package com.example.sharedpreference

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    
    // line 17 used to set the name of our file where we will store our data.
    
    private val sharedPrefFile = "kotlinsharedpreference"
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val inputId = findViewById<EditText>(R.id.editId)
        val inputName = findViewById<EditText>(R.id.editName)
        val outputId = findViewById<TextView>(R.id.textViewShowId)
        val outputName = findViewById<TextView>(R.id.textViewShowName)

        val btnSave = findViewById<Button>(R.id.save)
        val btnView = findViewById<Button>(R.id.view)
        val btnClear = findViewById<Button>(R.id.clear)
      // below line me k zarye hum ne usko file btai hai k isky ander store krna hai  and mode set private.
        // so that another App does not used our data.
        val sharedPreferences: SharedPreferences = this.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)
    
    // for save the data save button used.
        btnSave.setOnClickListener(View.OnClickListener {
            // first we have stored the values of our input data.
            
            val id:Int = inputId.text.toString ().toInt()
            val name:String = inputName.text.toString()
            
            // editor will store our data.
            val editor: SharedPreferences.Editor =  sharedPreferences.edit()
            editor.putInt("id_key",id)
            editor.putString("name_key",name)
            editor.apply()
            editor.commit()
            Toast.makeText(this,"Data Saved",Toast.LENGTH_LONG).show()
        })

        // for display our data on the screen
        // keys ky zarye se hum data read krty hain and show krwaty hain.
        // val sharedIdValue me hum ne key likh di hai or 0 uski default value hai.
        // ta ka agr data na mily to phe hum wo display krwa dain

        btnView.setOnClickListener {
            val sharedIdValue = sharedPreferences.getInt("id_key",0)
            val sharedNameValue = sharedPreferences.getString("name_key","defaultname")
            if(sharedIdValue.equals(0) && sharedNameValue.equals("defaultname")){
                outputName.setText("default name: ${sharedNameValue}").toString()
                outputId.setText("default id: ${sharedIdValue.toString()}")
            }else{
                outputName.text = sharedNameValue.toString()
//                outputName.setText(sharedNameValue).toString()
  //              outputId.setText(sharedIdValue.toString())
                outputId.text= sharedIdValue.toString()
            }

        }
        // data clear krny k lye

        btnClear.setOnClickListener(View.OnClickListener {
            val editor = sharedPreferences.edit()

            editor.clear()
            editor.apply()
            outputName.setText("").toString()
            outputId.setText("".toString())
          Toast.makeText(this,"DATA CLEARED",Toast.LENGTH_LONG).show()

        })



    }
}
