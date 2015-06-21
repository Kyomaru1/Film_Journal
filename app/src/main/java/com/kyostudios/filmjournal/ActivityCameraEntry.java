package com.kyostudios.filmjournal;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
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

    String makeText, modelText, nicknameText;

    @Override
    public void onCreate(Bundle SIS){
        super.onCreate(SIS);
        Intent intent = getIntent();
        if (!intent.getBooleanExtra("Start_EDIT", false)) {
            setContentView(R.layout.activity_cameras_entry);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_entry);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            title = (TextView) findViewById(R.id.entryTitleText);
            make = (EditText) findViewById(R.id.cameraEntry_make);
            model = (EditText)findViewById(R.id.cameraEntry_model);

            title.setText("Camera Entry");
        } else {
            setContentView(R.layout.activity_cameras_entry);
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_entry);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            title = (TextView) findViewById(R.id.entryTitleText);
            make = (EditText) findViewById(R.id.cameraEntry_make);
            model = (EditText)findViewById(R.id.cameraEntry_model);

            title.setText("Camera Edit");

            make.setText(intent.getStringExtra("Make"));
            model.setText(intent.getStringExtra("Model"));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_entry, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        Intent intent = getIntent();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_item) {
            if(intent.getBooleanExtra("Start_EDIT", true)){
                SQLiteDatabase db = new DatabaseHelper(this).getWritableDatabase();
                Bundle bundle = intent.getExtras();
                Object _id = bundle.get("ID_Value");
                makeText = make.getText().toString().trim();
                modelText = model.getText().toString().trim();

                Cursor countC = db.rawQuery("Select count(*) where Make = ? and Model = ?", new String[]{makeText, modelText});
                countC.moveToFirst();

                int countTotal = countC.getInt(0);

                countC.close();

                id = (int) intent.getLongExtra("ID_Value", Integer.parseInt(_id.toString()));
                ContentValues cv = new ContentValues();
                cv.put("Make", makeText);
                cv.put("Model", modelText);
                db.update("Cameras", cv, "_id = " + id, null);
                finish();
            }
            else {
                SQLiteDatabase db = new DatabaseHelper(this).getWritableDatabase();
                ContentValues cv = new ContentValues();
                makeText = make.getText().toString().trim();
                modelText = model.getText().toString().trim();
                Cursor countC = db.rawQuery("Select count(*) where Make = ? and Model = ?", new String[]{makeText, modelText});
                countC.moveToFirst();

                int countTotal = countC.getInt(0);

                countC.close();
                nicknameText = makeText + " " + modelText + " #" + Integer.toString(countTotal + 1);
                cv.put("Make", makeText);
                cv.put("Model", modelText);
                cv.put("Nickname", nicknameText);
                db.insert("Cameras", null, cv);
                db.close();
                finish();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }
}
