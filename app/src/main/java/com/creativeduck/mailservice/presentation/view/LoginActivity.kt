package com.creativeduck.mailservice.presentation.view

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.creativeduck.mailservice.R
import com.creativeduck.mailservice.databinding.ActivityLoginBinding
import com.creativeduck.mailservice.presentation.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.regex.Pattern

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding
    private val mViewModel : LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityLoginBinding?>(this, R.layout.activity_login)
            .apply {
                vm = mViewModel
                lifecycleOwner = this@LoginActivity
            }
        setContentView(binding.root)

        val clickListener = View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("nickname", binding.editLoginNickname.text.toString())
            intent.putExtra("email", binding.editLoginEmail.text.toString())
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }

        mViewModel.canNext.observe(this) {
            binding.btnLoginNext.apply {
                backgroundTintList = if (it) {
                    setOnClickListener(clickListener)
                    ColorStateList.valueOf(
                        ContextCompat.getColor(this@LoginActivity, R.color.black))
                } else {
                    setOnClickListener(null)
                    ColorStateList.valueOf(
                        ContextCompat.getColor(this@LoginActivity, R.color.gray))
                }
            }
        }
        mViewModel.correctNickname.observe(this) {
            binding.editLoginNicknameLayout.run {
                error = if (it) null else getString(R.string.warn_nickname)
            }
        }
        mViewModel.correctEmail.observe(this) {
            binding.editLoginEmailLayout.run {
                error = if (it) null else getString(R.string.warn_email)
            }
        }

    }

}