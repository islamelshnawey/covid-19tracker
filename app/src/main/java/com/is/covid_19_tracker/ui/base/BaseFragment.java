package com.is.covid_19_tracker.ui.base;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.is.covid_19_tracker.R;
import com.is.covid_19_tracker.di.Injectable;
import com.is.covid_19_tracker.util.Connectivity;
import com.is.covid_19_tracker.util.NavigationController;
import com.is.covid_19_tracker.util.Utils;

import javax.inject.Inject;

/**
 * @author Islam
 */

public abstract class BaseFragment extends Fragment implements Injectable {

    @Inject
    protected Connectivity connectivity;

    @Inject
    protected SharedPreferences pref;

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    @Inject
    protected NavigationController navigationController;

    private BaseActivity mActivity;
    private Dialog mProgressDialog;

    private boolean isFadeIn = false;
    protected String cameraType;


    //region Override Methods

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (mActivity != null) {
            //Tools.setSystemBarColorPrimaryDark(mActivity);
        }

        loadLoginUserId();

        initViewModels();

        initUIAndActions();

        initAdapters();

        initData();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) getActivity();
            this.mActivity = activity;
        }
    }

    //endregion


    //region Methods

    protected void loadLoginUserId() {
        try {

            if (getActivity() != null && getActivity().getBaseContext() != null) {

               // loginUserId = pref.getString(Constants.USER_ID, Constants.EMPTY_STRING);
               // facebookId = pref.getString(Constants.FACEBOOK_ID, Constants.EMPTY_STRING);
               // phoneId = pref.getString(Constants.PHONE_ID, Constants.EMPTY_STRING);
               // googleId = pref.getString(Constants.GOOGLE_ID, Constants.EMPTY_STRING);
               // loginUserEmail = pref.getString(Constants.USER_EMAIL, Constants.EMPTY_STRING);
               // loginUserPwd = pref.getString(Constants.USER_PASSWORD, Constants.EMPTY_STRING);
               // if (!facebookId.isEmpty()) {
               //     loginUserPwd = facebookId;
               //     loginUserEmail = facebookId;
               // }if (!phoneId.isEmpty()) {
               //     loginUserPwd = phoneId;
               //     loginUserEmail = phoneId;
               // }if (!googleId.isEmpty()) {
               //     loginUserPwd = googleId;
               //     loginUserEmail = googleId;
               // }
               // loginUserName = pref.getString(Constants.USER_NAME, Constants.EMPTY_STRING);
               // selectedCityId = pref.getString(Constants.CITY_ID, Constants.EMPTY_STRING);
               // selectedCityName = pref.getString(Constants.CITY_NAME, Constants.EMPTY_STRING);
               // selectedCityLat = pref.getString(Constants.CITY_LAT, Constants.EMPTY_STRING);
               // selectedCityLng = pref.getString(Constants.CITY_LNG, Constants.EMPTY_STRING);
               // versionNo = pref.getString(Constants.APPINFO_PREF_VERSION_NO, Constants.EMPTY_STRING);
               // force_update = pref.getBoolean(Constants.APPINFO_PREF_FORCE_UPDATE, false);
               // force_update_msg = pref.getString(Constants.APPINFO_FORCE_UPDATE_MSG, Constants.EMPTY_STRING);
               // selectedLat = pref.getString(Constants.LAT, Constants.EMPTY_STRING);
               // selectedLng = pref.getString(Constants.LNG, Constants.EMPTY_STRING);
                // cameraType = pref.getString(Constants.CAMERA_TYPE, Config.CAMERA_CONFIG);
               // force_update_title = pref.getString(Constants.APPINFO_FORCE_UPDATE_TITLE, Constants.EMPTY_STRING);
               // selected_location_id = pref.getString(Constants.SELECTED_LOCATION_ID, Constants.EMPTY_STRING);
               // selected_location_name = pref.getString(Constants.SELECTED_LOCATION_NAME, Constants.EMPTY_STRING);
               // userEmailToVerify = pref.getString(Constants.USER_EMAIL_TO_VERIFY, Constants.EMPTY_STRING);
               // userPasswordToVerify = pref.getString(Constants.USER_PASSWORD_TO_VERIFY, Constants.EMPTY_STRING);
               // userNameToVerify = pref.getString(Constants.USER_NAME_TO_VERIFY, Constants.EMPTY_STRING);
               // userIdToVerify = pref.getString(Constants.USER_ID_TO_VERIFY, Constants.EMPTY_STRING);
               // consent_status = pref.getString(Config.CONSENTSTATUS_CURRENT_STATUS, Config.CONSENTSTATUS_CURRENT_STATUS);

            }

        } catch (NullPointerException ne) {
            Utils.errorLog("Null Pointer Exception.", ne);
        } catch (Exception e) {
            Utils.errorLog("Error in getting notification flag data.", e);
        }
    }

    protected abstract void initUIAndActions();

    protected abstract void initViewModels();

    protected abstract void initAdapters();

    protected abstract void initData();

    protected void fadeIn(View view) {

        if (!isFadeIn) {
            view.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
            isFadeIn = true; // Fade in will do only one time.
        }
    }

    //endregion


    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }



}
