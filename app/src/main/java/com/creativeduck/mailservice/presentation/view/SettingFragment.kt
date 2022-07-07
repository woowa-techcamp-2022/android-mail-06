package com.creativeduck.mailservice.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.creativeduck.mailservice.R
import com.creativeduck.mailservice.config.BaseFragment
import com.creativeduck.mailservice.databinding.FragmentSettingBinding

private const val ARG_NICKNAME = "nickname"
private const val ARG_EMAIL = "email"

class SettingFragment : BaseFragment<FragmentSettingBinding>(FragmentSettingBinding::bind, R.layout.fragment_setting) {

    private var nickname: String? = null
    private var email: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            nickname = it.getString(ARG_NICKNAME)
            email = it.getString(ARG_EMAIL)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textSettingNickname.text = "Nickname = $nickname"
        binding.textSettingEmail.text = "Email = $email"
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SettingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_NICKNAME, param1)
                    putString(ARG_EMAIL, param2)
                }
            }
    }
}