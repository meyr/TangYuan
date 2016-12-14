package net.mshome.fyon_linux.tangyuan;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity
        extends AppCompatActivity
        implements ActionBar.TabListener ,
        OrderFragment.OrderListener{

    AppSectionsPagerAdapter mAppSectionsPagerAdapter;
    CustomViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAppSectionsPagerAdapter = new AppSectionsPagerAdapter(getSupportFragmentManager());


        // Set up the action bar.
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();

        // Specify that the Home/Up button should not be enabled, since there is no hierarchical
        // parent.
        actionBar.setHomeButtonEnabled(false);

        // Specify that we will be displaying tabs in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        mViewPager = (CustomViewPager) findViewById(R.id.pager);
        mViewPager.setPagingEnabled(false);
        mViewPager.setAdapter(mAppSectionsPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // When swiping between different app sections, select the corresponding tab.
                // We can also use ActionBar.Tab#select() to do this if we have a reference to the
                // Tab.
                actionBar.setSelectedNavigationItem(position);
            }
        });
        // For each of the sections in the app, add a tab to the action bar.
        for (int i = 0; i < mAppSectionsPagerAdapter.getCount(); i++) {
            // Create a tab with text corresponding to the page title defined by the adapter.
            // Also specify this Activity object, which implements the TabListener interface, as the
            // listener for when this tab is selected.
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(mAppSectionsPagerAdapter.getPageTitle(i))
                            .setTabListener(this));
        }
        // setup title name
        setTitle("湯圓");
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, switch to the corresponding page in the ViewPager.
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void addOrder(Actor actor) {
        mAppSectionsPagerAdapter.addOrder(actor);
    }

    public static class AppSectionsPagerAdapter
            extends FragmentPagerAdapter
            implements OrderFragment.OrderListener{

        String pageTitle[]={"清單","點菜","統計"};
        public AppSectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        public static Fragment tab1,tab2,tab3;
        Fragment[] fragments = {new Fragment(),new Fragment(),new Fragment()};


        @Override
        public Fragment getItem(int i) {
            switch (i) {
                case 0:
                    tab1 = new ToDoFragment();
                    return tab1;
                case 1:
                    tab2 = new OrderFragment();
                    return tab2;
                case 2:
                    tab3 = new StaticFragment();
                    return tab3;
                default:
                    return new DummyFragment();
            }
        }


        @Override
        public int getCount() {
            return pageTitle.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return pageTitle[position];
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            Fragment fragment = (Fragment) super.instantiateItem(container, position);
            fragments[position] = fragment;
            return fragment;
        }
        @Override
        public void addOrder(Actor actor) {
            if(fragments[0] != null){
                ((ToDoFragment)fragments[0]).addOrder(actor);
            }

        }
    }

}
