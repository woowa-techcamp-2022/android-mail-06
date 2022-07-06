package com.creativeduck.mailservice.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.creativeduck.mailservice.R
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    private val _nickname = MutableLiveData<String>()
    private val _email = MutableLiveData<String>()
    private val _canNext = MutableLiveData<Boolean>()
    private val _correctNickname = MutableLiveData<Boolean>()
    private val _correctEmail = MutableLiveData<Boolean>()

    val nickname : LiveData<String> = _nickname
    val email : LiveData<String> = _email
    val canNext : LiveData<Boolean> = _canNext
    val correctNickname : LiveData<Boolean> = _correctNickname
    val correctEmail : LiveData<Boolean> = _correctEmail

    init {
        _nickname.value = ""
        _email.value = ""
        _canNext.value = false
        _correctNickname.value = false
        _correctEmail.value = false
    }

    fun onNicknameTextChanged(s : CharSequence, start : Int, before : Int, count : Int) {
        _nickname.postValue(s.toString())
        s.let {
            var foundDigit = false
            var foundLetter = false
            var foundOther = false
            for(i in it.indices) {
                if (it[i].isDigit()) { foundDigit = true }
                else if ((it[i] in 'a'..'z') || (it[i] in 'A'..'Z') ) { foundLetter = true }
                else { foundOther = true }
            }
            _correctNickname.value = it.length in 4..12 && foundDigit && foundLetter && !foundOther
            _canNext.value = _correctNickname.value!! && _correctEmail.value!!
        }
    }

    fun onEmailTextChanged(s : CharSequence, start : Int, before : Int, count : Int) {
        _email.postValue(s.toString())
        s.let {
            val pattern : Pattern = Pattern.compile("^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$")
            val matcher = pattern.matcher(s.toString())
            _correctEmail.value = matcher.matches()
            _canNext.value = _correctNickname.value!! && _correctEmail.value!!
        }
    }

}