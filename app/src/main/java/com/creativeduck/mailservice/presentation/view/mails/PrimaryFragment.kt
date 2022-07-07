package com.creativeduck.mailservice.presentation.view.mails

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.creativeduck.mailservice.R
import com.creativeduck.mailservice.adapter.MailListAdapter
import com.creativeduck.mailservice.adapter.MailType
import com.creativeduck.mailservice.adapter.WooWaMail
import com.creativeduck.mailservice.config.BaseFragment
import com.creativeduck.mailservice.databinding.FragmentPrimaryBinding
import com.creativeduck.mailservice.util.VerticalSpaceDecoration
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class PrimaryFragment : BaseFragment<FragmentPrimaryBinding>(FragmentPrimaryBinding::bind, R.layout.fragment_primary) {

    private lateinit var mailListAdapter: MailListAdapter
    @Inject
    lateinit var dummy : List<WooWaMail>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mailListAdapter = MailListAdapter()
        binding.recyclerPrimaryMail.apply {
            adapter = mailListAdapter
            layoutManager = LinearLayoutManager(activity)
            itemAnimator = null
            addItemDecoration(VerticalSpaceDecoration(50))
        }
        mailListAdapter.submitList(dummy.filter { it.type == MailType.Primary.ordinal }.toList())
    }


}