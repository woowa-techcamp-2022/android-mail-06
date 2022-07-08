package com.creativeduck.mailservice.presentation.view

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.creativeduck.mailservice.R
import com.creativeduck.mailservice.adapter.MailListAdapter
import com.creativeduck.mailservice.adapter.WooWaMail
import com.creativeduck.mailservice.config.BaseFragment
import com.creativeduck.mailservice.databinding.FragmentMailItemBinding
import com.creativeduck.mailservice.util.VerticalSpaceDecoration
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val ARG_TYPE = "type"

@AndroidEntryPoint
class MailItemFragment : BaseFragment<FragmentMailItemBinding>(FragmentMailItemBinding::bind, R.layout.fragment_mail_item) {

    private var type : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            type = it.getInt(ARG_TYPE)
        }
    }

    private lateinit var mailListAdapter: MailListAdapter
    @Inject
    lateinit var dummy : List<WooWaMail>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mailListAdapter = MailListAdapter()
        binding.recyclerMail.apply {
            adapter = mailListAdapter
            layoutManager = LinearLayoutManager(activity)
            itemAnimator = null
            addItemDecoration(VerticalSpaceDecoration(50))
        }
        mailListAdapter.submitList(dummy.filter { it.type == type }.toList())
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: Int) =
            MailItemFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_TYPE, param1)
                }
            }
    }

}