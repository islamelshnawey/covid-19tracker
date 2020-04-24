package com.is.covid_19_tracker.data.binding;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;

import androidx.fragment.app.Fragment;

import javax.inject.Inject;

/**
 * Binding adapters that work with a fragment instance.
 */
public class FragmentBindingAdapters {
    private final Fragment fragment;

    @Inject
    public FragmentBindingAdapters(Fragment fragment) {
        this.fragment = fragment;
    }

    private Boolean isValid(ImageView imageView, String url) {
        return !(url == null
                || imageView == null
                || fragment == null
                || url.equals(""));
    }

    private Boolean isButtonValid(Button button, String url) {
        return !(url == null
                || button == null
                || fragment == null
                || url.equals(""));
    }

    private Boolean isValidRatingBar(RatingBar ratingBar) {
        return !(ratingBar == null
                || fragment == null);
    }

}

