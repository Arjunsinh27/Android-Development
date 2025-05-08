package com.example.imageslider3d;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Make sure your layout contains the image_slider view

        ImageSlider imageSlider = findViewById(R.id.image_slider);

        List<SlideModel> imageList = new ArrayList<>();

        // Add images with titles
        imageList.add(new SlideModel(R.drawable.image1, "", ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.image2, "",ScaleTypes.CENTER_CROP));
        imageList.add(new SlideModel(R.drawable.image3, "",ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(imageList);
    }
}
