package id.web.hn.multiviewrecyclerview.activity;

import android.app.Dialog;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import id.web.hn.multiviewrecyclerview.R;
import id.web.hn.multiviewrecyclerview.app.fragment.FragmentInilah;
import id.web.hn.multiviewrecyclerview.app.fragment.FragmentMain;

public class MainActivity extends AppCompatActivity
                        implements NavigationView.OnNavigationItemSelectedListener {
    NavigationView mNavigationView = null;
    Toolbar toolbar = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("InilahKoran");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

//        //fragment isi dummy
//        FragmentMain mFragmentMain = new FragmentMain();
//        FragmentTransaction mFragmentTransaction =
//                getSupportFragmentManager().beginTransaction();
//        mFragmentTransaction.replace(R.id.fragment_container, mFragmentMain);
//        mFragmentTransaction.commit();

        Bundle args = new Bundle();
        args.putString("url", "ht");
        //fragment isi
        FragmentInilah mFragmentMain = new FragmentInilah();
        FragmentTransaction mFragmentTransaction =
                getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.fragment_container, mFragmentMain);
        mFragmentTransaction.commit();

    }



    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //TODO: di sini ganti fragment per kategori
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
////        int id = item.getItemId();
////        if(id == R.id.action_refresh){
//////            Snackbar.make(toolbar, "sample Multivew RecyclerView", Snackbar.LENGTH_LONG).show();
////        }
//        return true;
//    }
}
