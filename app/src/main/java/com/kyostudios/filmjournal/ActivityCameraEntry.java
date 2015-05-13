package com.kyostudios.filmjournal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

/**
 * Created by Kyoma_000 on 5/12/2015.
 */
public class ActivityCameraEntry extends AppCompatActivity{

    TextView title;
    EditText make;
    EditText model;

    String makeText, modelText;

    @Override
    public void onCreate(Bundle SIS){
        super.onCreate(SIS);

        setContentView(R.layout.activity_cameras_entry);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_entry);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        title = (TextView) findViewById(R.id.entryTitleText);
        make = (EditText) findViewById(R.id.cameraEntry_make);
        model = (EditText)findViewById(R.id.cameraEntry_model);

        title.setText("Camera Entry");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_entry, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_item) {
            SQLiteDatabase db = new DatabaseHelper(this).getWritableDatabase();
            ContentValues cv = new ContentValues();
            makeText = make.getText().toString().trim();
            modelText= model.getText().toString().trim();
            cv.put("Make", makeText);
            cv.put("Model", modelText);
            db.insert("Cameras", null, cv);
            db.close();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
