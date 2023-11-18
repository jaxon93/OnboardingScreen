package com.example.onboardingscreenkotlin

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener

class NavigationActivity : AppCompatActivity() {
    lateinit var slideViewPager: ViewPager
    lateinit var dotIndicator: LinearLayout
    lateinit var backButton: Button
    lateinit var nextButton: Button
    lateinit var skipButton: Button
    lateinit var dots: Array<TextView?>
    lateinit var viewPagerAdapter: ViewPagerAdapter
    var viewPagerListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            setDotIndicator(position)
            if (position > 0) {
                backButton!!.visibility = View.VISIBLE
            } else {
                backButton!!.visibility = View.INVISIBLE
            }
            if (position == 2) {
                nextButton!!.text = "Finish"
            } else {
                nextButton!!.text = "Next"
            }
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)
        backButton = findViewById(R.id.backButton)
        nextButton = findViewById(R.id.nextButton)
        skipButton = findViewById(R.id.skipButton)
        backButton.setOnClickListener(View.OnClickListener {
            if (getItem(0) > 0) {
                slideViewPager!!.setCurrentItem(getItem(-1), true)
            }
        })
        nextButton.setOnClickListener(View.OnClickListener {
            if (getItem(0) < 2) slideViewPager!!.setCurrentItem(getItem(1), true) else {
                val i = Intent(
                    this@NavigationActivity,
                    com.example.onboardingscreenkotlin.GetStarted::class.java
                )
                startActivity(i)
                finish()
            }
        })
        skipButton.setOnClickListener(View.OnClickListener {
            val i = Intent(
                this@NavigationActivity,
                com.example.onboardingscreenkotlin.MainActivity::class.java
            )
            startActivity(i)
            finish()
        })
        slideViewPager = findViewById<View>(R.id.slideViewPager) as ViewPager
        dotIndicator = findViewById<View>(R.id.dotIndicator) as LinearLayout
        viewPagerAdapter = ViewPagerAdapter(this)
        slideViewPager!!.adapter = viewPagerAdapter
        setDotIndicator(0)
        slideViewPager!!.addOnPageChangeListener(viewPagerListener)
    }

    fun setDotIndicator(position: Int) {
        dots = arrayOfNulls(3)
        dotIndicator!!.removeAllViews()
        for (i in dots.indices) {
            dots[i] = TextView(this)
            dots[i]!!.text = Html.fromHtml("&#8226", Html.FROM_HTML_MODE_LEGACY)
            dots[i]!!.textSize = 35f
            dots[i]!!.setTextColor(resources.getColor(R.color.grey, applicationContext.theme))
            dotIndicator!!.addView(dots[i])
        }
        dots[position]!!
            .setTextColor(resources.getColor(R.color.lavender, applicationContext.theme))
    }

    private fun getItem(i: Int): Int {
        return slideViewPager!!.currentItem + i
    }
}