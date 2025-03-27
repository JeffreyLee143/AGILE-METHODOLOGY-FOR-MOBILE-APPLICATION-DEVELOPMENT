package com.example.projectimplementationii

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val tvGender = findViewById<TextView>(R.id.tvGender)
        val tvBlood = findViewById<TextView>(R.id.tvBlood)
        val tvJob = findViewById<TextView>(R.id.tvJob)
        val tvCollege = findViewById<TextView>(R.id.tvCollege)
        tvGender.text = "性別"+intent.getStringExtra("Gender")
        tvBlood.text = "血型"+intent.getStringExtra("Blood")
        tvJob.text = "職業"+intent.getStringExtra("Job")
        tvCollege.text = "大學"+intent.getStringExtra("College")
    }
}