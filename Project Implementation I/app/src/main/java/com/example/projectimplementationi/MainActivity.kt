package com.example.projectimplementationi

import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.LocalDate


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val edName = findViewById<EditText>(R.id.edName)
        val edHeight = findViewById<EditText>(R.id.edHeight)
        val edWeight = findViewById<EditText>(R.id.edWeight)
        val rdgrpGender = findViewById<RadioGroup>(R.id.rdgrpGender)
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val tvWarning = findViewById<TextView>(R.id.tvWarning)
        btnCalculate.setOnClickListener{
            if(edName.text.isEmpty()||edHeight.text.isEmpty()||edWeight.text.isEmpty()){
                tvWarning.text ="填入完整訊息"
                return@setOnClickListener
            }
            if(edHeight.toString().toDouble()<0 || edHeight.toString().toDouble()>3){
                tvWarning.text ="填入正確的身高(公尺)"
                return@setOnClickListener
            }
            if(edWeight.toString().toDouble()<0 || edWeight.toString().toDouble()>300){
                tvWarning.text ="填入正確的體重(公斤)"
                return@setOnClickListener
            }
            val name = edName.text.toString()
            val weight = edWeight.text.toString()
        }

    }
}