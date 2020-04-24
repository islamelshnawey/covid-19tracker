package com.is.covid_19_tracker.ui.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.is.covid_19_tracker.R;
import com.is.covid_19_tracker.util.NavigationController;
import com.is.covid_19_tracker.util.Utils;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasAndroidInjector;

/**
 * @author Islam
 * @date 10/12/2018
 */

public abstract class BaseActivity extends AppCompatActivity implements HasAndroidInjector {

    //region Variables

    @Inject
    DispatchingAndroidInjector<Object> dispatchingAndroidInjector;

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    @Inject
    protected NavigationController navigationController;

    private Dialog progressDialog;

    //endregion

    //region Override Methods

    @Override
    public AndroidInjector<Object> androidInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


//endregion

    //region Toolbar Init

    protected Toolbar initToolbar(Toolbar toolbar, String title, int color) {

        if(toolbar != null) {

            toolbar.setTitle(title);

            if(color != 0) {
                try {
                    toolbar.setTitleTextColor(getResources().getColor(color));
                }catch (Exception e){
                    Utils.errorLog("Can't set color.", e);
                }
            }else {
                try {
                    toolbar.setTitleTextColor(getResources().getColor(R.color.white));
                }catch (Exception e){
                    Utils.errorLog("Can't set color.", e);
                }
            }

            try {
                setSupportActionBar(toolbar);
            }catch (Exception e) {
                Utils.errorLog("Error in set support action bar.", e);
            }

            try {
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
            }catch (Exception e) {
                Utils.errorLog("Error in set display home as up enabled.", e);
            }

            /*
               Warning :

               Set Spannable text must call after set Support Action Bar

               The problem is actually that you have a non-String title set on the Toolbar
               at the time you're calling setSupportActionBar. It will end up in ToolbarWidgetWrapper,
               where it will be used when you click the navigation menu. You can use any CharSequence (e.g. Spannable)
               after calling setSuppportActionBar.

               https://stackoverflow.com/questions/27023802/clicking-toolbar-navigation-icon-crashes-the-app/27044868

             */
            if(!title.equals("")) {
                setToolbarText(toolbar, title);
            }

        }else {
            Utils.log("Toolbar is null");
        }

        return toolbar;
    }

    protected Toolbar initToolbar(Toolbar toolbar, String title) {

        if(toolbar != null) {

            toolbar.setTitle(title);

            try {
                toolbar.setTitleTextColor(getResources().getColor(R.color.white));
            }catch (Exception e){
                Utils.errorLog("Can't set color.", e);
            }

            try {
                setSupportActionBar(toolbar);
            }catch (Exception e) {
                Utils.errorLog("Error in set support action bar.", e);
            }

            try {
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                }
            }catch (Exception e) {
                Utils.errorLog("Error in set display home as up enabled.", e);
            }

            /*
               Warning :

               Set Spannable text must call after set Support Action Bar

               The problem is actually that you have a non-String title set on the Toolbar
               at the time you're calling setSupportActionBar. It will end up in ToolbarWidgetWrapper,
               where it will be used when you click the navigation menu. You can use any CharSequence (e.g. Spannable)
               after calling setSuppportActionBar.

               https://stackoverflow.com/questions/27023802/clicking-toolbar-navigation-icon-crashes-the-app/27044868

             */

            if(!title.equals("")) {
                setToolbarText(toolbar, title);
            }

        }else {
            Utils.log("Toolbar is null");
        }
        return toolbar;
    }

    public void setToolbarText(Toolbar toolbar, String text) {
        Utils.log("Set Toolbar Text : " + text);
        toolbar.setTitle( text );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item != null) {
            Utils.log("Clicked " + item.getItemId());
            switch (item.getItemId()) {
                case android.R.id.home:
                    onBackPressed();
                    return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
    //endregion

    //region Setup Fragment

    protected void setupFragment(Fragment fragment) {
        try {
            this.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commitAllowingStateLoss();
        }catch (Exception e){
            Utils.errorLog("Error! Can't replace fragment.", e);
        }
    }

    protected void setupFragment(Fragment fragment, int frameId) {
        try {
            this.getSupportFragmentManager().beginTransaction()
                    .replace(frameId, fragment)
                    .commitAllowingStateLoss();
        }catch (Exception e){
            Utils.errorLog("Error! Can't replace fragment.", e);
        }
    }

    //endregion

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


}
