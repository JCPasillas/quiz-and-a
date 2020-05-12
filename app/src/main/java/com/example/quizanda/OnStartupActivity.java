package com.example.quizanda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OnStartupActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private LinearLayout linearLayout;

    private TextView[] dots;

    private Button backBtn;
    private Button nextBtn;
    private int currentPage;

    private SliderAdapter sliderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_startup);

        viewPager = findViewById(R.id.slide);
        linearLayout = findViewById(R.id.dots);

        backBtn = findViewById(R.id.back_btn);
        nextBtn = findViewById(R.id.next_btn);

        sliderAdapter = new SliderAdapter(this);

        viewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        viewPager.addOnPageChangeListener(viewListener);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(currentPage + 1);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(currentPage - 1);
            }
        });
    }

    public void addDotsIndicator(int position) {
        dots = new TextView[3];
        linearLayout.removeAllViews();

        for (int i=0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.colorText));

            linearLayout.addView(dots[i]);
        }

        if(dots.length > 0) {
            dots[position].setTextColor(getResources().getColor(R.color.pureWhite));
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            currentPage = position;
            if (currentPage == 0) {
                nextBtn.setEnabled(true);
                backBtn.setVisibility(View.VISIBLE);
                backBtn.setEnabled(false);
                backBtn.setVisibility(View.INVISIBLE);
                nextBtn.setText("NEXT");
                backBtn.setText("");
            } else if (currentPage == (dots.length-1)) {
                nextBtn.setEnabled(false);
                backBtn.setVisibility(View.INVISIBLE);
                backBtn.setEnabled(true);
                backBtn.setVisibility(View.VISIBLE);
                nextBtn.setText("");
                backBtn.setText("BACK");
            } else {
                nextBtn.setEnabled(true);
                backBtn.setVisibility(View.VISIBLE);
                backBtn.setEnabled(true);
                backBtn.setVisibility(View.VISIBLE);
                nextBtn.setText("NEXT");
                backBtn.setText("BACK");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
