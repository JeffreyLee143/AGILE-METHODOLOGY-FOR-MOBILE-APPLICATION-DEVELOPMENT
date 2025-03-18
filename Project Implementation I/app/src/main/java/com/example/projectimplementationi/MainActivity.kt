package com.example.projectimplementationi

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.pow


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
        val radioGroupGender = findViewById<RadioGroup>(R.id.rdgrpGender) // 性別Radio Group
        val btnCalculate = findViewById<Button>(R.id.btnCalculate)
        val tvText = findViewById<TextView>(R.id.tvText)
        val imgResult = findViewById<ImageView>(R.id.img_result)
        /*
        val imgResource = listOf{
            R.drawable.bad_to_health_1
            R.drawable.bad_to_health_2
            R.drawable.bad_to_health_3
        }
        */

        btnCalculate.setOnClickListener{
            // 初始化
            imgResult.setImageResource(0)
            // 確認所有資訊已被填寫
            if(edName.text.isEmpty()||edHeight.text.isEmpty()||edWeight.text.isEmpty()){
                tvText.text ="填入完整訊息"
                return@setOnClickListener
            }
            // 限縮資料範圍，防呆
            if(edHeight.text.toString().toDouble()<0 || edHeight.text.toString().toDouble()>=3){
                tvText.text ="填入正確的身高(公尺)"
                return@setOnClickListener
            }
            if(edWeight.text.toString().toDouble()<0 || edWeight.text.toString().toDouble()>300){
                tvText.text ="填入正確的體重(公斤)"
                return@setOnClickListener
            }
            // 讀取資料、計算BMI
            val name = edName.text.toString()
            val gender = when(radioGroupGender.checkedRadioButtonId){
                R.id.radioButton -> "男"
                else -> "女"
            }
            val weight = edWeight.text.toString().toDouble()
            val height = edHeight.text.toString().toDouble() // 次方要用double
            val bmi = weight/(height.pow(2))
            val condition = calculateBMI(bmi)
            tvText.text = "姓名 : ${name}\n" +
                          "性別 : ${gender}\n" +
                          "BMI : ${String.format("%.1f", bmi)}\n"+
                          "指標 : ${condition}"
            if(condition!="正常"){
                //val img = imgResource.random()
                imgResult.setImageResource(R.drawable.bad_to_health)
            }else{
                imgResult.setImageResource(R.drawable.good_to_health)
            }

        }
    }
    private fun calculateBMI(bmi : Double):String {
        return when{
            bmi < 18.5 -> "過輕"
            bmi >= 18.5 && bmi < 24.0 -> "正常"
            bmi >= 24.0 && bmi < 27.0 -> "過重"
            bmi >= 27.0 && bmi < 30.0 -> "輕度肥胖"
            bmi >= 30 && bmi < 35.0 -> "中度肥胖"
            else -> "重度肥胖"
        }
    }
}