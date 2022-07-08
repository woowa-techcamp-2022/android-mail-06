package com.creativeduck.mailservice.presentation.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.activityViewModels
import com.creativeduck.mailservice.R
import com.creativeduck.mailservice.config.BaseFragment
import com.creativeduck.mailservice.databinding.FragmentMailBinding
import com.creativeduck.mailservice.presentation.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MailFragment : BaseFragment<FragmentMailBinding>(FragmentMailBinding::bind, R.layout.fragment_mail) {

    private val mViewModel : MainViewModel by activityViewModels()
    private lateinit var primaryFragment : MailItemFragment
    private lateinit var socialFragment : MailItemFragment
    private lateinit var promotionsFragment : MailItemFragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        // 아예 뷰모델로 해서 바로 파라티머 전달받도록 했음
        mViewModel.mailType.observe(viewLifecycleOwner) {
            changeType(it)
        }
    }

    private fun init() {
        primaryFragment = MailItemFragment.newInstance(0)
        socialFragment = MailItemFragment.newInstance(1)
        promotionsFragment = MailItemFragment.newInstance(2)
    }

    private fun changeType(mailType: Int) {
        // 선택된 메일 타입에 따라 프래그먼트 변경
        when (mailType) {
            0 -> {
                childFragmentManager.apply {
                    if (backStackEntryCount > 0) {
                        popBackStackImmediate(
                            null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
                    }
                    beginTransaction()
                        .replace(R.id.frame_mail, primaryFragment)
                        .setReorderingAllowed(true)
                        .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                        .commit()
                }
            }
            1 -> {
                childFragmentManager.beginTransaction()
                    .replace(R.id.frame_mail, socialFragment)
                    .setReorderingAllowed(true)
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .addToBackStack(null)
                    .commit()
            }
            2 -> {
                childFragmentManager.beginTransaction()
                    .replace(R.id.frame_mail, promotionsFragment)
                    .setReorderingAllowed(true)
                    .setCustomAnimations(R.anim.fade_in, R.anim.fade_out)
                    .addToBackStack(null)
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