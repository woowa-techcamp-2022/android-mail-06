package com.creativeduck.mailservice.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.creativeduck.mailservice.R
import com.creativeduck.mailservice.adapter.MailListAdapter
import com.creativeduck.mailservice.adapter.MailType
import com.creativeduck.mailservice.adapter.WooWaMail
import com.creativeduck.mailservice.config.BaseFragment
import com.creativeduck.mailservice.databinding.FragmentMailBinding
import com.creativeduck.mailservice.presentation.view.mails.PrimaryFragment
import com.creativeduck.mailservice.presentation.view.mails.PromotionsFragment
import com.creativeduck.mailservice.presentation.view.mails.SocialFragment
import com.creativeduck.mailservice.presentation.viewmodel.MainViewModel
import com.creativeduck.mailservice.util.VerticalSpaceDecoration
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

private const val ARG_MAIL_TYPE = "mail_type"

@AndroidEntryPoint
class MailFragment : BaseFragment<FragmentMailBinding>(FragmentMailBinding::bind, R.layout.fragment_mail) {

    private val mViewModel : MainViewModel by activityViewModels()
    private val primaryFragment : PrimaryFragment by lazy { PrimaryFragment() }
    private val socialFragment : SocialFragment by lazy { SocialFragment() }
    private val promotionsFragment : PromotionsFragment by lazy { PromotionsFragment() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 아예 뷰모델로 해서 바로 파라티머 전달받도록 했음
        mViewModel.mailType.observe(viewLifecycleOwner) {
            changeType(it)
        }
    }

    private fun changeType(mailType: Int) {
        // 선택된 메일 타입에 따라 프래그먼트 변경
        when (mailType) {
            0 -> {
                childFragmentManager.beginTransaction()
                    .replace(R.id.frame_mail, primaryFragment)
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
//                    .addToBackStack("mails")
                    .commit()
            }
            1 -> {
                childFragmentManager.beginTransaction()
                    .replace(R.id.frame_mail, socialFragment)
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
//                    .addToBackStack("mails")
                    .commit()
            }
            2 -> {
                childFragmentManager.beginTransaction()
                    .replace(R.id.frame_mail, promotionsFragment)
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
//                    .addToBackStack("mails")
                    .commit()
            }
            else -> {
                childFragmentManager.beginTransaction()
                    .replace(R.id.frame_mail, primaryFragment)
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
//                    .addToBackStack("mails")
                    .commit()
            }
        }
        binding.textMailType.text = when (mailType) {
            0 -> "Primary"
            1 -> "Social"
            2 -> "Promotions"
            else -> "Primary"
        }
    }
}