package com.example.quizanda;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    private Context context;
    private LayoutInflater layoutInflater;

    private int[] slide_images = { android.R.color.transparent, R.drawable.ic_q, R.drawable.ic__left,
            R.drawable.ic__right, R.drawable.ic_a, android.R.color.transparent,
            android.R.color.transparent, R.drawable.ic_q_a_white, android.R.color.transparent};

    private String[] slide_headings = {"Quiz", "Answer", "Quiz & A"};

    private String[] slide_text = {"Quiz yourself! Test your knowledge by taking countless quizzes on the go!",
            "Answer and get a score, compete with friends and other users around the world for the #1 spot!",
            "Join Quiz & A today!"};

    public SliderAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_page, container, false);

        ImageView slidePageImageLeft = view.findViewById(R.id.slide_page_ic_left);
        ImageView slidePageImageCenter = view.findViewById(R.id.slide_page_ic);
        ImageView slidePageImageRight = view.findViewById(R.id.slide_page_ic_right);
        TextView slidePageHeading = view.findViewById(R.id.slide_page_heading);
        TextView slidePageText = view.findViewById(R.id.slide_page_text);
        Button joinBtn = view.findViewById(R.id.join_btn);

        slidePageImageLeft.setImageResource(slide_images[3*position]);
        slidePageImageCenter.setImageResource(slide_images[3*position+1]);
        slidePageImageRight.setImageResource(slide_images[3*position+2]);
        slidePageHeading.setText(slide_headings[position]);
        slidePageText.setText(slide_text[position]);

        if (position != (getCount()-1)) {
            joinBtn.setEnabled(false);
            joinBtn.setVisibility(View.INVISIBLE);
        }

        joinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, MainActivity.class));
                ((Activity)context).finish();
            }
        });

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
