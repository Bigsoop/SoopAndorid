package com.example.sangh.soop;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.sangh.soop.Fragment.MainFragment;
import com.example.sangh.soop.view.DrawerItem;
import com.example.sangh.soop.view.GreenToast;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginManager;

import org.json.JSONObject;

import java.util.ArrayList;

import bolts.AppLinks;
import jp.wasabeef.glide.transformations.CropCircleTransformation;


public class MainActivity extends AppCompatActivity {
    private final String TAG="MainActivity";

    Toolbar toolbar;
    DrawerLayout drawer;
    NavigationView navigationView;
    LinearLayout layout_drawer;
    ArrayList<DrawerItem> drawableMenu;

    ImageView img_Profile;
    TextView txt_Profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //new GreenToast(this).showToast("hello");

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        img_Profile = (ImageView)navigationView.getHeaderView(0).findViewById(R.id.img_profile);
        txt_Profile = (TextView) navigationView.getHeaderView(0).findViewById(R.id.txt_profile);

        makeDrawerMenu();

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.content_fragment_layout);
        if(fragment==null) {
            fragment = new MainFragment();
            fm.beginTransaction()
                    .add(R.id.content_fragment_layout, fragment)
                    .commit();
        }
        if(User.getIsLogin(this)){
            setProfileView();
        }
    }

    private void setProfileView(){
        if(Profile.getCurrentProfile()==null){
            GraphRequest request =  GraphRequest.newMeRequest(
                    AccessToken.getCurrentAccessToken(),
                    new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject userInfo, GraphResponse response) {
                            String id = userInfo.optString("id");
                            if (id == null) {
                                return;
                            }
                            String link = userInfo.optString("link");
                            Profile profile = new Profile(
                                    id,
                                    userInfo.optString("first_name"),
                                    userInfo.optString("middle_name"),
                                    userInfo.optString("last_name"),
                                    userInfo.optString("name"),
                                    link != null ? Uri.parse(link) : null
                            );
                            Profile.setCurrentProfile(profile);
                            setProfileUpdate();
                        }
                    });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "id,name,link");
            request.setParameters(parameters);
            request.executeAsync();
        }
        else{
            setProfileUpdate();
        }
    }

    private void setProfileUpdate(){
        if(Profile.getCurrentProfile().getProfilePictureUri(500,500)!=null)
            Common.setCircleImage(this,Profile.getCurrentProfile().getProfilePictureUri(500,500),img_Profile);

        img_Profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =Common.getFacebookIntent(getApplicationContext() ,Profile.getCurrentProfile().getLinkUri());
                startActivity(i);
            }
        });
        txt_Profile.setText(Profile.getCurrentProfile().getName());
    }

    private void makeDrawerMenu(){

        layout_drawer = (LinearLayout)navigationView.getHeaderView(0);
        drawableMenu = new ArrayList<>();
        for(int i=0; i<Constant.menuNameList.length;i++){
            if(Constant.menuNameList[i].equals("로그아웃") && !User.getIsLogin(this)) continue;
            drawableMenu.add(new DrawerItem(this,Constant.menuIconList[i],Constant.menuNameList[i]));
            layout_drawer.addView(drawableMenu.get(i));
        }

        drawableMenu.get(Constant.MENU_MAIN).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.closeDrawer(GravityCompat.START);
            }
        });

        drawableMenu.get(Constant.MENU_BEST).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,BestActivity.class));
            }
        });

        drawableMenu.get(Constant.MENU_MAIL).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("mailto:"+Constant.ADMIN_EMAIL);
                startActivity(new Intent(Intent.ACTION_SENDTO,uri));
            }
        });

        drawableMenu.get(Constant.MENU_SETTING).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingActivity.class));
            }
        });
        drawableMenu.get(Constant.MENU_INFO).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AppInfoActivity.class));
            }
        });
        if(drawableMenu.size() >=6){
            drawableMenu.get(Constant.MENU_LOGOUT).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new GreenToast(MainActivity.this).showToast("로그아웃!");
                    logout();
                }
            });
        }

    }

    private void logout() {
        new GreenToast(this).showToast("로그아웃 되었습니다 ^0^");

        LoginManager.getInstance().logOut();
        User.setIsLogin(this,false);

        Intent intent = new Intent(MainActivity.this, IntroLoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }


    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
