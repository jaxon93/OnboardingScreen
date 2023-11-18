package com.example.onboardingscreenrecyclerviewjava;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onboardingscreenrecyclerviewjava.databinding.ActivityNavigationBinding;


public class NavigationActivity extends AppCompatActivity {
    private ActivityNavigationBinding binding;
    private LinearLayoutManager layoutManager;
    private MyAdapter myAdapter;
    private TextView[] dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.slideRecyclerView.setLayoutManager(layoutManager);
        myAdapter = new MyAdapter();
        binding.slideRecyclerView.setAdapter(myAdapter);

        // Use PagerSnapHelper for snapping to the nearest item
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(binding.slideRecyclerView);

        binding.skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NavigationActivity.this, GetStarted.class);
                startActivity(intent);
                finish();
            }
        });

        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = layoutManager.findFirstVisibleItemPosition();
                if (currentPosition > 0) {
                    layoutManager.scrollToPosition(currentPosition - 1);
                    setDotIndicator(currentPosition - 1);
                }
            }
        });

        binding.nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = layoutManager.findFirstVisibleItemPosition();
                if (currentPosition < myAdapter.getItemCount() - 1) {
                    layoutManager.scrollToPosition(currentPosition + 1);
                    setDotIndicator(currentPosition + 1);
                    binding.backButton.setVisibility(View.VISIBLE);
                } else {
                    Intent intent = new Intent(NavigationActivity.this, GetStarted.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        binding.slideRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int currentPosition = layoutManager.findFirstVisibleItemPosition();
                setDotIndicator(currentPosition);
                if (currentPosition == 2) {
                    binding.nextButton.setText("Finish");
                } else {
                    binding.nextButton.setText("Next");
                }
                binding.backButton.setVisibility(currentPosition > 0 ? View.VISIBLE : View.INVISIBLE);
            }
        });

        // Initial dot setup
        setDotIndicator(0);
        binding.backButton.setVisibility(View.INVISIBLE);
    }

    private void setDotIndicator(int position) {
        if (dots != null) {
            for (int i = 0; i < dots.length; i++) {
                if (dots[i] != null) {
                    dots[i].setTextColor(getResources().getColor(
                            i == position ? R.color.lavender : R.color.grey,
                            getTheme()
                    ));
                }
            }
        } else {
            dots = new TextView[myAdapter.getItemCount()];
            binding.dotIndicator.removeAllViews();

            for (int i = 0; i < dots.length; i++) {
                dots[i] = new TextView(this);
                dots[i].setText(Html.fromHtml("&#8226;", Html.FROM_HTML_MODE_LEGACY));
                dots[i].setTextSize(35f);
                dots[i].setTextColor(getResources().getColor(
                        i == position ? R.color.lavender : R.color.grey,
                        getTheme()
                ));
                binding.dotIndicator.addView(dots[i]);
            }
        }
    }
}
