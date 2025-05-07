package com.example.imageslider;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private LinearLayout dotsLayout;
    private ImageView[] dots;
    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        dotsLayout = findViewById(R.id.dotsLayout);

        // Image list
        List<Integer> images = Arrays.asList(
                R.drawable.image1,
                R.drawable.image2,
                R.drawable.image3
        );

        // Set adapter
        ImageSliderAdapter adapter = new ImageSliderAdapter(this, images);
        viewPager.setAdapter(adapter);

        // Set dots
        setupDots(images.size());
        updateDots(0);

        // Auto-slide
        Runnable slide = new Runnable() {
            @Override
            public void run() {
                int next = (viewPager.getCurrentItem() + 1) % images.size();
                viewPager.setCurrentItem(next);
                handler.postDelayed(this, 2000);
            }
        };
        handler.postDelayed(slide, 3000);

        // Update dots on page change
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                updateDots(position);
            }
        });
    }

    private void setupDots(int count) {
        dots = new ImageView[count];
        for (int i = 0; i < count; i++) {
            dots[i] = new ImageView(this);
            dots[i].setImageResource(R.drawable.dot_inactive);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20);
            params.setMargins(8, 0, 8, 0);
            dotsLayout.addView(dots[i], params);
        }
    }

    private void updateDots(int position) {
        for (int i = 0; i < dots.length; i++) {
            dots[i].setImageResource(i == position ? R.drawable.dot_active : R.drawable.dot_inactive);
        }
    }
}