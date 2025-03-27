package com.example.projectimplementationii

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val selectedRadioId = MutableLiveData<Int>()
    val buttonClickEvent = MutableLiveData<Event<Unit>>()
    val navigateToNextPage = MutableLiveData<Event<Unit>>()
    val selectedGender = MutableLiveData<String?>()
    val selectedJobs = MutableLiveData<String?>()
    val selectedBlood = MutableLiveData<String?>()
    val selectedCollege = MutableLiveData<String?>()
}

// Event 類用於處理單次事件
open class Event<out T>(private val content: T) {
    private var hasBeenHandled = false
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) null
        else {
            hasBeenHandled = true
            content
        }
    }
}