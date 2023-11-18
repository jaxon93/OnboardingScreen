package com.example.onboardingscreenrecyclerviewkotlin

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.onboardingscreenrecyclerviewkotlin.databinding.ActivityNavigationBinding

class NavigationActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNavigationBinding
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var myAdapter: MyAdapter
    private lateinit var dots: Array<TextView?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.slideRecyclerView.layoutManager = layoutManager
        myAdapter = MyAdapter()
        binding.slideRecyclerView.adapter = myAdapter

        // Use PagerSnapHelper for snapping to the nearest item
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.slideRecyclerView)

        binding.skipButton.setOnClickListener {
            val intent = Intent(this, GetStarted::class.java)
            startActivity(intent)
            finish()
        }

        binding.backButton.setOnClickListener {
            val currentPosition = layoutManager.findFirstVisibleItemPosition()
            if (currentPosition > 0) {
                layoutManager.scrollToPosition(currentPosition - 1)
                setDotIndicator(currentPosition - 1)
            }
        }

        binding.nextButton.setOnClickListener {
            val currentPosition = layoutManager.findFirstVisibleItemPosition()
            if (currentPosition < myAdapter.itemCount - 1) {
                layoutManager.scrollToPosition(currentPosition + 1)
                setDotIndicator(currentPosition + 1)
                binding.backButton.visibility = View.VISIBLE
            } else {
                val intent = Intent(this, GetStarted::class.java)
                startActivity(intent)
                finish()
            }
        }

        binding.slideRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val currentPosition = layoutManager.findFirstVisibleItemPosition()
                setDotIndicator(currentPosition)
                if (currentPosition == 2) {
                    binding.nextButton!!.text = "Finish"
                } else {
                    binding.nextButton!!.text = "Next"
                }
                binding.backButton.visibility = if (currentPosition > 0) View.VISIBLE else View.INVISIBLE
            }
        })

        // Initial dot setup
        setDotIndicator(0)

        binding.backButton.visibility = View.INVISIBLE
    }

    private fun setDotIndicator(position: Int) {
        if (::dots.isInitialized) {
            for (i in dots.indices) {
                dots[i]?.setTextColor(
                    resources.getColor(
                        if (i == position) R.color.lavender else R.color.grey,
                        applicationContext.theme
                    )
                )
            }
        } else {
            dots = arrayOfNulls(myAdapter.itemCount)
            binding.dotIndicator.removeAllViews()

            for (i in dots.indices) {
                dots[i] = TextView(this)
                dots[i]!!.text = Html.fromHtml("&#8226;", Html.FROM_HTML_MODE_LEGACY)
                dots[i]!!.textSize = 35f
                dots[i]!!.setTextColor(
                    resources.getColor(
                        if (i == position) R.color.lavender else R.color.grey,
                        applicationContext.theme
                    )
                )
                binding.dotIndicator.addView(dots[i])
            }
        }
    }
}
