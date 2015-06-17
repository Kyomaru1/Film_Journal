package com.kyostudios.filmjournal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Kyoma_000 on 5/12/2015.
 */
public class ActivityFilmRollEntry extends AppCompatActivity{

    TextView title;
    EditText filmNumber, filmBrand, filmISO, filmExposures, filmExpireDate;
    Spinner filmType;

    String[] filmTypes = new String[]{"Color", "B&W", "X-Ray", "Infrared"};
    @Override
    public void onCreate(Bundle SIS){
        super.onCreate(SIS);
        Intent intent = getIntent();
        if(intent.getBooleanExtra("Start_EDIT", true)){
            setContentView(R.layout.activity_cameras_entry);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_entry);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        }
        else{
            setContentView(R.layout.activity_cameras_entry);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_entry);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_entry, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return false;
    }
}
