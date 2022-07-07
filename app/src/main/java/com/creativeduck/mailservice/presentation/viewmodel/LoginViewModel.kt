package com.creativeduck.mailservice.presentation.viewmodel

import android.util.Patterns
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _correctNickname = MutableLiveData<Boolean>()
    private val _correctEmail = MutableLiveData<Boolean>()

    val correctNickname : LiveData<Boolean> = _correctNickname
    val correctEmail : LiveData<Boolean> = _correctEmail

    fun onNicknameTextChanged(s : CharSequence, start : Int, before : Int, count : Int) {
        s.let {
            _correctNickname.value = Pattern.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{4,12}\$", s.toString())
        }
    }
    fun onEmailTextChanged(s : CharSequence, start : Int, before : Int, count : Int) {
        s.let {
            _correctEmail.value = Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()
        }
    }

}