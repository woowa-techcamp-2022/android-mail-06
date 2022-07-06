package com.creativeduck.mailservice.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.creativeduck.mailservice.adapter.MailListAdapter
import com.creativeduck.mailservice.adapter.MailType
import com.creativeduck.mailservice.adapter.WooWaMail
import com.creativeduck.mailservice.databinding.FragmentMailBinding
import com.creativeduck.mailservice.presentation.viewmodel.MainViewModel
import com.creativeduck.mailservice.util.VerticalSpaceDecoration
import java.util.*

private const val ARG_MAIL_TYPE = "mail_type"

class MailFragment : Fragment() {

    private var _binding : FragmentMailBinding? = null
    private val binding get() = _binding!!

    private val mViewModel : MainViewModel by activityViewModels()

    private lateinit var mailListAdapter: MailListAdapter
    private lateinit var dummy : List<WooWaMail>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mailListAdapter = MailListAdapter()
        binding.recyclerMail.apply {
            adapter = mailListAdapter
            layoutManager = LinearLayoutManager(activity)
            itemAnimator = null
            addItemDecoration(VerticalSpaceDecoration(50))
        }
        mViewModel.mailType.observe(viewLifecycleOwner) {
            changeType(it)
        }

        var now = System.currentTimeMillis()

        dummy = listOf<WooWaMail>(
            WooWaMail(
                MailType.Primary.ordinal, "Google", "보안 알림", "Android 에서 로그인하셨습니다.",
            Date(now)),
            WooWaMail(MailType.Social.ordinal, "김영희", "보안 알림", "Android 에서 로그인하셨습니다.", Date(now+1)),
            WooWaMail(MailType.Promotions.ordinal, "Facebook", "보안 알림", "Android 에서 로그인하셨습니다.", Date(now+2)),
            WooWaMail(MailType.Primary.ordinal, "심플", "보안 알림", "Android 에서 로그인하셨습니다.", Date(now+3)),
            WooWaMail(MailType.Primary.ordinal, "Google", "보안 알림", "Android 에서 로그인하셨습니다.", Date(now+4))
,                    WooWaMail(
                    MailType.Primary.ordinal, "Google", "보안 알림", "Android 에서 로그인하셨습니다.",
            Date(now)),
        WooWaMail(MailType.Social.ordinal, "김영희", "보안 알림", "Android 에서 로그인하셨습니다.", Date(now+31)),
        WooWaMail(MailType.Promotions.ordinal, "Facebook", "보안 알림", "Android 에서 로그인하셨습니다.", Date(now+32)),
        WooWaMail(MailType.Primary.ordinal, "심플", "보안 알림", "Android 에서 로그인하셨습니다.", Date(now+43)),
        WooWaMail(MailType.Primary.ordinal, "Google", "보안 알림", "Android 에서 로그인하셨습니다.", Date(now+54))
,            WooWaMail(
                MailType.Primary.ordinal, "Google", "보안 알림", "Android 에서 로그인하셨습니다.",
                Date(now)),
            WooWaMail(MailType.Social.ordinal, "김영희", "보안 알림", "Android 에서 로그인하셨습니다.", Date(now+134)),
            WooWaMail(MailType.Promotions.ordinal, "Facebook", "보안 알림", "Android 에서 로그인하셨습니다.", Date(now+23)),
            WooWaMail(MailType.Primary.ordinal, "심플", "보안 알림", "Android 에서 로그인하셨습니다.", Date(now+33)),
            WooWaMail(MailType.Primary.ordinal, "Google", "보안 알림", "Android 에서 로그인하셨습니다.", Date(now+423))

        )
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }


    private fun changeType(mailType: Int) {
        mailListAdapter.submitList(dummy.filter { it.type == mailType }.toList())
        binding.textMailType.text = when (mailType) {
            0 -> "Primary"
            1 -> "Social"
            2 -> "Promotions"
            else -> "Primary"
        }
    }
}