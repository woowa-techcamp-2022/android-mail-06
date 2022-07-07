package com.creativeduck.mailservice.presentation.view.mails

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.creativeduck.mailservice.R
import com.creativeduck.mailservice.adapter.MailListAdapter
import com.creativeduck.mailservice.adapter.MailType
import com.creativeduck.mailservice.adapter.WooWaMail
import com.creativeduck.mailservice.config.BaseFragment
import com.creativeduck.mailservice.databinding.FragmentPromotionsBinding
import com.creativeduck.mailservice.util.VerticalSpaceDecoration
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class PromotionsFragment : BaseFragment<FragmentPromotionsBinding>(FragmentPromotionsBinding::bind, R.layout.fragment_promotions) {

    private lateinit var mailListAdapter: MailListAdapter
    @Inject
    lateinit var dummy : List<WooWaMail>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mailListAdapter = MailListAdapter()
        binding.recyclerPromotionsMail.apply {
            adapter = mailListAdapter
            layoutManager = LinearLayoutManager(activity)
            itemAnimator = null
            addItemDecoration(VerticalSpaceDecoration(50))
        }
        mailListAdapter.submitList(dummy.filter { it.type == MailType.Promotions.ordinal }.toList())
    }
}