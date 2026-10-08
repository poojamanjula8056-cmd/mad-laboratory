package com.example.wallpaper;

import android.app.WallpaperManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.IOException;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button start, stop;
    Handler handler = new Handler();
    WallpaperManager wallpaperManager;
    Random random = new Random();
    int[] wallpapers = {
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image4
    };

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                int randomImage = wallpapers[random.nextInt(wallpapers.length)];
                Bitmap bitmap = BitmapFactory.decodeResource(getResources(), randomImage);
                wallpaperManager.setBitmap(bitmap);
                Toast.makeText(MainActivity.this,
                        "Wallpaper Changed",
                        Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            handler.postDelayed(this, 6000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wallpaperManager = WallpaperManager.getInstance(this);
        start = findViewById(R.id.btnStart);
        stop = findViewById(R.id.btnStop);

        start.setOnClickListener(v -> {
            handler.post(runnable);
            Toast.makeText(this,
                    "Wallpaper Changing Started",
                    Toast.LENGTH_SHORT).show();
        });

        stop.setOnClickListener(v -> {
            handler.removeCallbacks(runnable);
            Toast.makeText(this,
                    "Wallpaper Changing Stopped",
                    Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }
}
