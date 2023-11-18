package com.example.onboardingscreenrecyclerviewkotlin

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.onboardingscreenrecyclerviewkotlin.databinding.SliderScreenBinding

class MyAdapter(): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var sliderAllAnimation = intArrayOf(R.raw.one, R.raw.two, R.raw.three)
    var sliderAllTitle = intArrayOf(R.string.screen1, R.string.screen2, R.string.screen3)
    var sliderAllDesc = intArrayOf(R.string.screen1desc, R.string.screen2desc, R.string.screen3desc)


    class MyViewHolder(val binding: SliderScreenBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return MyViewHolder(SliderScreenBinding.inflate(android.view.LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount(): Int {
        return sliderAllTitle.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as MyViewHolder).binding

        binding.sliderAnimation.setAnimation(sliderAllAnimation[position])
        binding.sliderTitle.setText(sliderAllTitle[position])
        binding.sliderDesc.setText(sliderAllDesc[position])
    }
}