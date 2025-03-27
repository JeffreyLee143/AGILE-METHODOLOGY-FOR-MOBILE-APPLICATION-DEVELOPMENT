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


class FourthFragment : Fragment() {
    private lateinit var viewModel: SharedViewModel

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.e("FourthFragment","onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("FourthFragment","onCreate")
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_fourth, container, false)
        Log.e("FourthFragment","onCreateView")
        // 填充 Layout 佈局，返回 View 對象
        return inflater.inflate(R.layout.fragment_fourth, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("FourthFragment","onViewCreated")
        // 初始化 ViewModel
    }

    override fun onStart() {
        super.onStart()
        Log.e("FourthFragment","onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e("FourthFragment","onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.e("FourthFragment","onPause")

    }

    override fun onStop() {
        super.onStop()
        Log.e("FourthFragment","onStop")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("FourthFragment","onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("FourthFragment","onDestroy")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("FourthFragment","onDetach")
    }


}