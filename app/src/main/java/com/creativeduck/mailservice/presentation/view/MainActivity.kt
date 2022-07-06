package com.creativeduck.mailservice.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.creativeduck.mailservice.R
import com.creativeduck.mailservice.databinding.ActivityMainBinding
import com.creativeduck.mailservice.presentation.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigationrail.NavigationRailView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var settingFragment: SettingFragment
    private lateinit var mailFragment: MailFragment

    private val mViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val nickname = intent.getStringExtra("nickname") ?: "null"
        val email = intent.getStringExtra("email") ?: "null"
        settingFragment = SettingFragment.newInstance(nickname, email)
        mailFragment = MailFragment()

        setListener()

        if (savedInstanceState != null) {
            with(savedInstanceState) {
                when (getInt(BOTTOM_MENU)) {
                    R.id.menu_home_mail -> { setSelect(R.id.menu_home_mail) }
                    R.id.menu_home_setting -> { setSelect(R.id.menu_home_setting) }
                }
            }
        } else {
            setSelect(R.id.menu_home_mail)
            mViewModel.changeMailType(0)
            binding.drawerHomeMenus.setCheckedItem(R.id.menu_home_primary)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState?.run {
            val itemId = if (binding.bottomNavHome is NavigationRailView) {
                (binding.bottomNavHome as NavigationRailView).selectedItemId
            }
            else {
                (binding.bottomNavHome as BottomNavigationView).selectedItemId
            }
            putInt(BOTTOM_MENU, itemId)
        }
        super.onSaveInstanceState(outState)
    }

    private fun setSelect(resId : Int) {
        if (binding.bottomNavHome is NavigationRailView) {
            (binding.bottomNavHome as NavigationRailView).selectedItemId = resId
        }
        else if (binding.bottomNavHome is BottomNavigationView) {
            (binding.bottomNavHome as BottomNavigationView).selectedItemId = resId
        }
    }

    private fun setListener() {
        // drawer 펼치기
        binding.toolbarHome.setNavigationOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        // drawer 메뉴 이벤트
        binding.drawerHomeMenus.setNavigationItemSelectedListener {
            val mailType = when (it.title) {
                "Primary" -> 0
                "Social" -> 1
                "Promotions" -> 2
                else -> 0
            }
            binding.drawerLayout.closeDrawer(GravityCompat.START)
            mViewModel.changeMailType(mailType)
//            val f = supportFragmentManager.findFragmentById(R.id.frame_home)
//            if (f is MailFragment) {
//                f.changeType(mailType)
//            }
            true
        }
        // bottomNavigationView 일 경우
        if (binding.bottomNavHome is BottomNavigationView) {
            (binding.bottomNavHome as BottomNavigationView).run {
                setOnItemSelectedListener {
                    when (it.itemId) {
                        R.id.menu_home_mail -> {
                            supportFragmentManager.popBackStack("mail", POP_BACK_STACK_INCLUSIVE)
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.frame_home, mailFragment)
                                .commit()
                        }
                        R.id.menu_home_setting -> {
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.frame_home, settingFragment)
                                .addToBackStack("mail")
                                .commit()
                        }
                        else -> {

                        }
                    }
                    true
                }
            }
        } // navigationRailView 일 경우
        else if (binding.bottomNavHome is NavigationRailView) {
            (binding.bottomNavHome as NavigationRailView).run {
                setOnItemSelectedListener {
                    when (it.title) {
                        "Mail" -> {
//                            supportFragmentManager.popBackStack("mail", POP_BACK_STACK_INCLUSIVE)
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.frame_home, mailFragment)
                                .commit()
                        }
                        "Setting" -> {
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.frame_home, settingFragment)
//                                .addToBackStack("mail")
                                .commit()
                        }
                        else -> {
//                            supportFragmentManager.popBackStack("mail", POP_BACK_STACK_INCLUSIVE)
                            supportFragmentManager.beginTransaction()
                                .replace(R.id.frame_home, mailFragment)
                                .commit()
                        }
                    }
                    true
                }
            }
        }
    }

    override fun onBackPressed() {
        val f = supportFragmentManager.findFragmentById(R.id.frame_home)
        if (f is SettingFragment) {
            if (binding.bottomNavHome is NavigationRailView) {
                (binding.bottomNavHome as NavigationRailView).selectedItemId = R.id.menu_home_mail
            }
            else if (binding.bottomNavHome is BottomNavigationView) {
                (binding.bottomNavHome as BottomNavigationView).selectedItemId = R.id.menu_home_mail
            }
        } else {
            super.onBackPressed()
        }
    }
    companion object {
        const val BOTTOM_MENU = "bottomMenu"
    }
}