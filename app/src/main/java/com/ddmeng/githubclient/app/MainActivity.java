package com.ddmeng.githubclient.app;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ddmeng.githubclient.R;
import com.ddmeng.githubclient.account.AccountUtil;
import com.ddmeng.githubclient.app.home.HomeContract;
import com.ddmeng.githubclient.app.home.HomePresenter;
import com.ddmeng.githubclient.app.me.MeFragment;
import com.ddmeng.githubclient.app.news.NewsFragment;
import com.ddmeng.githubclient.data.models.User;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements HomeContract.View {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;
    @BindView(R.id.content_container)
    FrameLayout contentContainer;

    @Inject
    AccountUtil accountUtil;

    private HomeContract.Presenter presenter;
    private TextView userInformation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((GitHubApplication) getApplication()).getComponent().inject(this);
        new HomePresenter(this, accountUtil);
        presenter.start();
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void initActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void initDrawerMenu() {
        View headerView = navigationView.getHeaderView(0);
        userInformation = (TextView) headerView.findViewById(R.id.user_information);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                item.setChecked(true);
                drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.nav_sign_in:
                        accountUtil.signIn(MainActivity.this);
                        break;
                    case R.id.nav_me:
                        showFragment(new MeFragment());
                        break;
                    case R.id.nav_news:
                        showFragment(new NewsFragment());
                        break;
                }
                return true;
            }
        });
    }

    private void showFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.content_container, fragment).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.updateSignInState();
    }

    @Override
    public void showUserInformation(User currentUser) {
        userInformation.setText(currentUser.getName());
    }

    @Override
    public void showSignInButton() {
        userInformation.setText(R.string.sign_in);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
