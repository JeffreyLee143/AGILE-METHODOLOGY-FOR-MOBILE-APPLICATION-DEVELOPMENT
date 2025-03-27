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


class FirstFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("FirstFragment","onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("FirstFragment","onCreate")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        Log.e("FirstFragment","onCreateView")
        // 填充 Layout 佈局，返回 View 對象


        return inflater.inflate(R.layout.fragment_first, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("FirstFragment","onViewCreated")
        // 初始化 ViewModel
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        val rgGender = view.findViewById<RadioGroup>(R.id.rgGender)
        val btnNext = view.findViewById<Button>(R.id.btnNext)
        var selectedText: String? = null  // 用來儲存當前選擇的文字

        // 設置 RadioGroup 監聽器
        rgGender.setOnCheckedChangeListener { _, checkedId ->
            viewModel.selectedRadioId.value = checkedId
            // 找到被選中的 RadioButton
            val selectedRadioButton = view.findViewById<RadioButton>(checkedId)
            selectedText = selectedRadioButton?.text.toString()
            viewModel.selectedGender.value = selectedText
        }


        // 設置 Button 點擊事件
        btnNext.setOnClickListener {
            // 檢查是否選擇了 "Female" (而不是 "Male")
            if (selectedText != "男性") {
                AlertDialog.Builder(requireContext())
                    .setTitle("失敗")
                    .setMessage("投胎失敗")
                    .setPositiveButton("重新選擇") { _, _ ->
                        showToast("這一世我重生了")
                    }
                    .show()
            } else {
                // 如果選擇的是 "Male"，直接跳頁
                viewModel.buttonClickEvent.value = Event(Unit)
                viewModel.navigateToNextPage.value = Event(Unit)
            }
        }
    }
    // 自訂 showToast 方法
    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onStart() {
        super.onStart()
        Log.e("FirstFragment","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("FirstFragment","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("FirstFragment","onPause")

    }

    override fun onStop() {
        super.onStop()
        Log.e("FirstFragment","onStop")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("FirstFragment","onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("FirstFragment","onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("FirstFragment","onDetach")
    }


}