package com.example.myapplication

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPageAdapter(supportFragmentManager: FragmentManager) : FragmentPagerAdapter(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT){

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {return frag1()}
            1 -> {return frag2()}
            2 -> {return frag3()}
            else -> {return frag1()}
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {return "학생누리관"}
            1 -> {return "50주년 기념관"}
            2 -> {return "샬롬하우스"}
        }
        return super.getPageTitle(position)
    }
}