package com.creativeduck.mailservice.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.creativeduck.mailservice.R
import com.creativeduck.mailservice.databinding.ActivityMainBinding
import com.creativeduck.mailservice.presentation.view.mails.PrimaryFragment
import com.creativeduck.mailservice.presentation.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView
import com.google.android.material.navigation.NavigationView
import com.google.android.material.navigationrail.NavigationRailView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var settingFragment: SettingFragment
    private val mailFragment: MailFragment by lazy { MailFragment() }
    private val mViewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val nickname = intent.getStringExtra("nickname") ?: "null"
        val email = intent.getStringExtra("email") ?: "null"
        settingFragment = SettingFragment.newInstance(nickname, email)

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
            binding.drawerHomeMenus.setCheckedItem(R.id.menu_home_primary)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState?.run {
            val itemId = if (binding.bottomNavHome is NavigationRailView) { (binding.bottomNavHome as NavigationRailView).selectedItemId }
            else { (binding.bottomNavHome as BottomNavigationView).selectedItemId }
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
            true
        }
        val navItemSelectedListener = NavigationBarView.OnItemSelectedListener {
            when (it.title) {
                "Mail" -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_home, mailFragment)
                        .commit()
                }
                "Setting" -> {
                    val transaction = supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_home, settingFragment)
                    // 한 번 이 트랜젝션을 백스택에 추가하면 더 추가하지 않도록 함
                    if (supportFragmentManager.backStackEntryCount < 1) {
                        transaction.addToBackStack(null)
                    }
                    transaction.commit()
                }
            }
            true
        }
        // bottomNavigationView 일 경우
        if (binding.bottomNavHome is BottomNavigationView) {
            (binding.bottomNavHome as BottomNavigationView).setOnItemSelectedListener(navItemSelectedListener)
        } // navigationRailView 일 경우
        else if (binding.bottomNavHome is NavigationRailView) {
            (binding.bottomNavHome as NavigationRailView).setOnItemSelectedListener(navItemSelectedListener)
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            supportFragmentManager.popBackStack()
            setSelect(R.id.menu_home_mail)
            return
        }
        val f = supportFragmentManager.findFragmentById(R.id.frame_home)
        f?.let {
            val childF = f.childFragmentManager.findFragmentById(R.id.frame_mail)
            if (childF is PrimaryFragment) {
                super.onBackPressed()
                return
            } else {
                binding.drawerHomeMenus.setCheckedItem(R.id.menu_home_primary)
                mViewModel.changeMailType(0)
                return
            }
        }
        super.onBackPressed()
    }
    companion object {
        const val BOTTOM_MENU = "bottomMenu"
    }
}