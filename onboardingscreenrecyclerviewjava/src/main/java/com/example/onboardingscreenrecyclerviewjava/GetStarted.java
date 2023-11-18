package com.example.onboardingscreenrecyclerviewjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.example.onboardingscreenrecyclerviewjava.databinding.ActivityGetStartedBinding;

public class GetStarted extends AppCompatActivity {
    private Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityGetStartedBinding binding = ActivityGetStartedBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        startButton = binding.startButton;
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(GetStarted.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
