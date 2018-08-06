package com.poppin.movies.animation;


import android.animation.AnimatorListenerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewAnimationUtils;

import com.poppin.movies.R;

public class Animator {

    public static void circleReveal(AppCompatActivity context, int view, int posFromRight, boolean containsOverflow, final boolean isShow) {
        final View myView = context.findViewById(view);

        int width=myView.getWidth();
        if(posFromRight>0) width -= (posFromRight * context.getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_material)) - (context.getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_material)/ 2);
        if(containsOverflow) width -= context.getResources().getDimensionPixelSize(R.dimen.abc_action_button_min_width_overflow_material);

        int cx=width;
        int cy=myView.getHeight()/2;

        android.animation.Animator anim;
        if(isShow) anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0,(float)width);
        else anim = ViewAnimationUtils.createCircularReveal(myView, cx, cy, (float)width, 0);

        anim.setDuration((long)220);

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(android.animation.Animator animation) {
                if(!isShow) {
                    super.onAnimationEnd(animation);
                    myView.setVisibility(View.INVISIBLE);
                }
            }
        });

        if(isShow) myView.setVisibility(View.VISIBLE);

        anim.start();
    }
}
