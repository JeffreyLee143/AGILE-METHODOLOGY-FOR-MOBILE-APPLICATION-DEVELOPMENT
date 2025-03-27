package com.example.projectimplementationii

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: SharedViewModel
    private lateinit var viewPager2: ViewPager2
    private var isProgrammaticScroll = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        Log.e("MainActivity", "onCreate")
        val viewPager2 = findViewById<ViewPager2>(R.id.viewPager2)
        val adapter = ViewPageAdapter(supportFragmentManager, this.lifecycle)

        viewPager2.adapter = adapter
        viewPager2.offscreenPageLimit = 1

        // 初始化 ViewModel
        viewModel = ViewModelProvider(this)[SharedViewModel::class.java]

        // 觀察 RadioGroup 選擇變化
        viewModel.selectedRadioId.observe(this) { radioId ->
            Log.d("MainActivity", "Selected radio ID: $radioId")
            // 在這裡處理 RadioGroup 選擇變化
        }

        // 觀察 Button 點擊事件
        viewModel.buttonClickEvent.observe(this) { event ->
            event.getContentIfNotHandled()?.let {
                Log.d("MainActivity", "Button clicked from FirstFragment")
                // 在這裡處理按鈕點擊
            }
        }

        // 處理跳頁事件
        viewModel.navigateToNextPage.observe(this) { event ->
            event.getContentIfNotHandled()?.let {
                val currentItem = viewPager2.currentItem
                if (currentItem < adapter.itemCount - 1) {  // 確保不會超出範圍
                    viewPager2.setCurrentItem(currentItem + 1, true)  // true 表示使用平滑動畫
                }
            }
        }

        // 監聽頁面切換事件
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
                if (state == ViewPager2.SCROLL_STATE_IDLE && !isProgrammaticScroll) {
                    // 用戶手動滑動結束
                    val currentPage = viewPager2.currentItem
                    if (currentPage == 1) { // 切換到 SecondFragment
                        val selectedGender = viewModel.selectedGender.value
                        if (selectedGender != "男性") {
                            // 如果不是男性，顯示對話框並回到 FirstFragment
                            AlertDialog.Builder(this@MainActivity)
                                .setTitle("失敗")
                                .setMessage("投胎失敗")
                                .setPositiveButton("重新選擇") { _, _ ->
                                    Toast.makeText(this@MainActivity, "這一世我重生了", Toast.LENGTH_SHORT).show()
                                }
                                .show()
                            viewPager2.setCurrentItem(0, true) // 回到 FirstFragment
                        }
                    }
                    if (currentPage == 2) { // 切換到 ThirdFragment
                        val selectedBlood = viewModel.selectedBlood.value
                        if (selectedBlood != "A++") {
                            // 如果不是A++，顯示對話框並回到 SecondFragment
                            AlertDialog.Builder(this@MainActivity)
                                .setTitle("失敗")
                                .setMessage("變成流浪漢")
                                .setPositiveButton("重新選擇") { _, _ ->
                                    Toast.makeText(this@MainActivity, "又回到最初的起點", Toast.LENGTH_SHORT).show()
                                }
                                .show()
                            viewPager2.setCurrentItem(1, true) // 回到 FirstFragment
                        }
                    }
                    if (currentPage == 3) { // 切換到 SecondActivity
                        val bundle = Bundle()
                        bundle.putString("Gender",viewModel.selectedGender.value)
                        bundle.putString("Job",viewModel.selectedJobs.value)
                        bundle.putString("Blood",viewModel.selectedBlood.value)
                        bundle.putString("College",viewModel.selectedCollege.value)
                        val intent = Intent(this@MainActivity,SecondActivity::class.java)
                        intent.putExtras(bundle)
                        startActivity(intent)
                    }
                }
                if (state == ViewPager2.SCROLL_STATE_IDLE) {
                    isProgrammaticScroll = false // 重置標記
                }
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.d("MainActivity", "Current page: $position")
            }
        })
    }
}