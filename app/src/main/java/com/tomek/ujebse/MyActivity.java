package com.tomek.ujebse;

/**
 * Created by tomek on 26.07.15.
 */


import android.content.res.Configuration;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;


public class MyActivity extends ActionBarActivity {

    private static final String DELETE_LINKS_SUCCES = "Sukces !  Usunięto linków : " ;
    private static final String TOOLBAR_TITLE = "Skróć linka";
    private static final String BOSS_NAME = "Tomasz Wiśniewski";
    private static final String BOSS_EMAIL = "tomek97@gmail.com";
    private static final String EMPTY_HISTORY_MESSAGE = "Historia linków jest już wyczyszczona !";
    private static final String FRAGMENT_TAG = "History Fragment";

    private Bundle bundle;
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private RecyclerView leftDrawerList;
    private NavigationDrawerAdapter drawerAdapter;
    private String[] leftSliderData = {"Skracanie", "Historia", "Wyczyść historię"};
    private int[] icons = {R.drawable.nozyce ,R.drawable.list ,R.drawable.miotla};
    private HistoryFragment historyFragment;
    private ShortcuttingFragment shortcuttingFragment;
    private LinearLayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bundle = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        nitView();
        if (toolbar != null) {
            toolbar.setTitle(TOOLBAR_TITLE);
            setSupportActionBar(toolbar);
        }
        initDrawer();
        shortcuttingFragment = new ShortcuttingFragment();
        historyFragment = new HistoryFragment();
        onNavDrawerItemClickHandler(1);
    }

    private void nitView() {
        leftDrawerList = (RecyclerView) findViewById(R.id.left_drawer);
        leftDrawerList.setHasFixedSize(true);
        drawerAdapter = new NavigationDrawerAdapter(leftSliderData,icons , BOSS_NAME, BOSS_EMAIL, R.drawable.unnamed, this);

        leftDrawerList.setAdapter(drawerAdapter);
        mLayoutManager = new LinearLayoutManager(this);
    }
    private void initDrawer() {
        final GestureDetector mDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
        leftDrawerList = (RecyclerView) findViewById(R.id.left_drawer);
        leftDrawerList.setLayoutManager(mLayoutManager);
        leftDrawerList.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent motionEvent) {
                View child = leftDrawerList.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if (child != null && mDetector.onTouchEvent(motionEvent)) {
                        onNavDrawerItemClickHandler(leftDrawerList.getChildAdapterPosition(child));
                        return true;
                }
                else return false;
            }


            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


    public void onNavDrawerItemClickHandler (int position) {
        if (position == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, shortcuttingFragment).commit();
        } else if (position == 2)
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, historyFragment).commit();
        else if (position == 3) {
            String ToastMessage;
            int deletedItems = new RecyclerViewAdapter().deleteAllLinks();
            if (deletedItems == 0) {
                ToastMessage = EMPTY_HISTORY_MESSAGE;
            }
            else {
                new RecyclerViewAdapter().deleteAllLinks();
                ToastMessage = DELETE_LINKS_SUCCES + deletedItems;
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, shortcuttingFragment).commit();
            }


            Utils.makeShortToast(MyActivity.this, ToastMessage);
        }
        drawerLayout.closeDrawers();
    }
}

