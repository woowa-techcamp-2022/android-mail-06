package com.creativeduck.mailservice.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor()  : ViewModel() {

    private val _mailType = MutableLiveData<Int>()

    val mailType : LiveData<Int> = _mailType

    init {
        _mailType.postValue(0)
    }
    fun changeMailType(type: Int) {
        _mailType.postValue(type)
    }

}