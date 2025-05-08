package com.example.imageslider2;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {
    ViewPager2 viewPager;
    ImageSliderAdapter adapter;
    ArrayList<Integer> imageList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager);
        imageList = new ArrayList<>();
        imageList.add(R.drawable.image1);
        imageList.add(R.drawable.image2);
        imageList.add(R.drawable.image3);
        adapter = new ImageSliderAdapter(imageList);
        viewPager.setAdapter(adapter);
    }
}