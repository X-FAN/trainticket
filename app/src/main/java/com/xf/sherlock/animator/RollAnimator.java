package com.xf.sherlock.animator;

import android.view.View;

import com.daimajia.androidanimations.library.BaseViewAnimator;
import com.nineoldandroids.animation.ObjectAnimator;

/**
 * Created by TC on 2015/12/1.
 */
public class RollAnimator extends BaseViewAnimator {
    public RollAnimator() {
    }

    @Override
    protected void prepare(View view) {
        this.getAnimatorAgent().playTogether(ObjectAnimator.ofFloat(view, "rotation", new float[]{0.0F, 360.0F}));
    }

}
