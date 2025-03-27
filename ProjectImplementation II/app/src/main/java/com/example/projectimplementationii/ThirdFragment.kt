package com.example.projectimplementationii

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

class ThirdFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("ThirdFragment","onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("ThirdFragment","onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e("ThirdFragment","onCreateView")
        // 填充 Layout 佈局，返回 View 對象
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("ThirdFragment","onViewCreated")
        // 初始化 ViewModel
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        val rgJobs = view.findViewById<RadioGroup>(R.id.rgJobs)
        val btnNext3 = view.findViewById<Button>(R.id.btnNext3)
        var selectedText: String? = null  // 用來儲存當前選擇的文字

        // 設置 RadioGroup 監聽器
        rgJobs.setOnCheckedChangeListener { _, checkedId ->
            viewModel.selectedRadioId.value = checkedId
            // 找到被選中的 RadioButton
            val selectedRadioButton = view.findViewById<RadioButton>(checkedId)
            selectedText = selectedRadioButton?.text.toString()
            viewModel.selectedJobs.value = selectedText
        }


        // 設置 Button 點擊事件
        btnNext3.setOnClickListener {
            // 檢查是否選擇了 "工程師" (而不是其他職業)
            if (selectedText != "工程師") {
                AlertDialog.Builder(requireContext())
                    .setTitle("失敗")
                    .setMessage("變成流浪漢")
                    .setPositiveButton("重新選擇") { _, _ ->
                        showToast("又回到最初的起點")
                    }
                    .show()
            } else {
                val item = arrayOf("臺灣大學","野雞大學","野狗大學")
                var position = 0
                AlertDialog.Builder(requireContext())
                    .setTitle("選擇學校")
                    .setSingleChoiceItems(item,0){dialogInterface, i->
                        position = i
                    }.setPositiveButton("確定"){dialog, which ->
                        if(item[position]=="臺灣大學"){
                            // 如果選擇的是 "臺灣大學"，直接跳頁
                            viewModel.selectedCollege.value = "臺灣大學"
                            viewModel.buttonClickEvent.value = Event(Unit)
                            viewModel.navigateToNextPage.value = Event(Unit)
                        }else{
                            AlertDialog.Builder(requireContext())
                                .setTitle("失敗")
                                .setMessage("變成流浪漢")
                                .setPositiveButton("重新選擇") { _, _ ->
                                    showToast("又回到最初的起點")
                                }
                                .show()
                        }
                    }.show()
            }
        }
    }
    // 自訂 showToast 方法
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        Log.e("ThirdFragment","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("ThirdFragment","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("ThirdFragment","onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.e("ThirdFragment","onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("ThirdFragment","onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("ThirdFragment","onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("ThirdFragment","onDetach")
    }
}