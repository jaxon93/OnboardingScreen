package com.example.onboardingscreenrecyclerviewjava;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onboardingscreenrecyclerviewjava.databinding.SliderScreenBinding;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int[] sliderAllAnimation = {R.raw.one, R.raw.two, R.raw.three};
    private int[] sliderAllTitle = {R.string.screen1, R.string.screen2, R.string.screen3};
    private int[] sliderAllDesc = {R.string.screen1desc, R.string.screen2desc, R.string.screen3desc};

    static class MyViewHolder extends RecyclerView.ViewHolder {
        final SliderScreenBinding binding;

        MyViewHolder(SliderScreenBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(SliderScreenBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolder myViewHolder = (MyViewHolder) holder;
        SliderScreenBinding binding = myViewHolder.binding;

        binding.sliderAnimation.setAnimation(sliderAllAnimation[position]);
        binding.sliderTitle.setText(sliderAllTitle[position]);
        binding.sliderDesc.setText(sliderAllDesc[position]);
    }

    @Override
    public int getItemCount() {
        return sliderAllTitle.length;
    }
}
