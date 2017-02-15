package com.example.sangh.soop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

/**
 * Created by sangh on 2017-02-15.
 */

public class ContentActivity extends AppCompatActivity{
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedSavedInstance){
        super.onCreate(savedSavedInstance);
        setContentView(R.layout.activity_content);

        mToolbar=(Toolbar)findViewById(R.id.toolbar_content);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
