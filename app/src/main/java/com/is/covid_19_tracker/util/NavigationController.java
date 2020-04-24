package com.is.covid_19_tracker.util;


import android.content.Intent;
import android.net.Uri;

import androidx.fragment.app.FragmentActivity;

import com.is.covid_19_tracker.Constants;
import com.is.covid_19_tracker.ui.details.DetailsActivity;
import com.is.covid_19_tracker.ui.main.MainActivity;

import javax.inject.Inject;

/**
 * Created by Panacea-Soft on 11/17/17.
 * Contact Email : teamps.is.cool@gmail.com
 */

public class NavigationController {

    //region Variables

    //private final int containerId;
    public Uri photoURI;

    //endregion


    //region Constructor
    @Inject
    public NavigationController() {

        // This setup is for MainActivity
        //this.containerId = R.id.content_frame;
    }

    //endregion

    public void navigateToDetailsActivity(FragmentActivity activity, String country) {
        Intent intent = new Intent(activity, DetailsActivity.class);
        intent.putExtra(Constants.COUNTRY, country);
        activity.startActivity(intent);
    }

    public void navigateToInfo(FragmentActivity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.sendBroadcast(intent);

    }
}
