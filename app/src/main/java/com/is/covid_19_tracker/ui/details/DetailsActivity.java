package com.is.covid_19_tracker.ui.details;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.is.covid_19_tracker.R;
import com.is.covid_19_tracker.databinding.ActivityDetailsBinding;
import com.is.covid_19_tracker.ui.base.BaseActivity;

/**
 * @author Islam Elshnawey
 * @date 2020-03-25
 * <p>
 * is.elshnawey@gmail.com
 **/
public class DetailsActivity extends BaseActivity {

    //region Variables
    //endregion

    //region Override Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityDetailsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_details);

        // Init all UI
        initUI(binding);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    //endregion

    //region Private Methods

    private void initUI(ActivityDetailsBinding binding) {

        // Toolbar
        initToolbar(binding.toolbar,"Details");

        // setup Fragment
        setupFragment(new DetailsFragment());
        // Or you can call like this
        //setupFragment(new NewsListFragment(), R.id.content_frame);

    }

    //endregion



}

