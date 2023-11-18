package com.example.onboardingscreenkotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.airbnb.lottie.LottieAnimationView

class ViewPagerAdapter(var context: Context) : PagerAdapter() {
    var sliderAllAnimation = intArrayOf(R.raw.one, R.raw.two, R.raw.three)
    var sliderAllTitle = intArrayOf(R.string.screen1, R.string.screen2, R.string.screen3)
    var sliderAllDesc = intArrayOf(R.string.screen1desc, R.string.screen2desc, R.string.screen3desc)
    override fun getCount(): Int {
        return sliderAllTitle.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater =
            context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.slider_screen, container, false)
        val sliderAnimation = view.findViewById<View>(R.id.sliderAnimation) as LottieAnimationView
        val sliderTitle = view.findViewById<View>(R.id.sliderTitle) as TextView
        val sliderDesc = view.findViewById<View>(R.id.sliderDesc) as TextView
        sliderAnimation.setAnimation(sliderAllAnimation[position])
        sliderTitle.setText(sliderAllTitle[position])
        sliderDesc.setText(sliderAllDesc[position])
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as LinearLayout)
    }
}