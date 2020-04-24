package com.is.covid_19_tracker.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.is.covid_19_tracker.R;
import com.is.covid_19_tracker.ui.local.LocalFragment;
import com.is.covid_19_tracker.ui.worldList.WorldListFragment;
import com.is.covid_19_tracker.ui.base.BaseActivity;

import java.util.Objects;


/**
 * @author Islam Elshnawey
 * @date 2020-03-25
 * <p>
 * is.elshnawey@gmail.com
 **/
public class MainActivity extends BaseActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private InVoiceSectionPager inVoiceSectionPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.inVoiceToolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(null);

        /*AppBarLayout appBarLayout = findViewById(R.id.inVoiceAppBar);
        TextView textView = findViewById(R.id.inVoiceNameToolbar);
        TextView textView1 = findViewById(R.id.inVoiceName);
        CircleImageView imageView = findViewById(R.id.inVoiceProfileImg);
        appBarLayout.addOnOffsetChangedListener(new CustomAppBarOffset(this, textView, textView1, toolbar, USER_NAME, imageView));
*/
        mViewPager = findViewById(R.id.container13);
        mTabLayout = findViewById(R.id.tabLayout3);

        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        inVoiceSectionPager = new InVoiceSectionPager(getSupportFragmentManager());

        mViewPager.setAdapter(inVoiceSectionPager);

    }

    private class InVoiceSectionPager extends FragmentPagerAdapter {

        public InVoiceSectionPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new WorldListFragment();
                case 1:
                    return new LocalFragment();
                default:
                    return new WorldListFragment();
            }
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                //navigationController.navigateToUploadDeposit(MainActivity.this);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
