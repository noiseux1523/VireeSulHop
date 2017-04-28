package com.example.noiseux1523.vireesulhop;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import static com.example.noiseux1523.vireesulhop.R.id.ingredients;

public class Biere extends FragmentActivity {

    // Variables
//    private TextView nom;
//    private TextView version;
//    private TextView version_value;
//    private TextView abv;
//    private TextView abv_value;
//    private TextView ibu;
//    private TextView ibu_value;
//    private TextView description;
//    private TextView ingredients;
//    private TextView ingredients_value;

    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 5;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private ViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biere);

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        // Set Phenomena font to buttons
        Typeface custom_font = Typeface.createFromAsset(getAssets(), "fonts/Phenomena-Bold.otf");

//        // Nom
//        nom = (TextView)findViewById(R.id.nom);
//        nom.setTypeface(custom_font);
//
//        // Version
//        version = (TextView)findViewById(R.id.version);
//        version.setTypeface(custom_font);
//
//        // ABV
//        abv = (TextView)findViewById(R.id.abv);
//        abv.setTypeface(custom_font);
//
//        // IBU
//        ibu = (TextView)findViewById(R.id.ibu);
//        ibu.setTypeface(custom_font);
//
//        // Description
//        description = (TextView)findViewById(R.id.description);
//        description.setTypeface(custom_font);
//
//        // Ingredients
//        ingredients = (TextView)findViewById(ingredients);
//        ingredients.setTypeface(custom_font);


    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    /**
     * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
     * sequence.
     */
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return BiereSlide.newInstance(position);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
