package com.kyostudios.filmjournal;

import android.support.v4.app.FragmentTransaction;
import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class MainContentHandler extends AppCompatActivity {

    static int[] currentFragmentPosition = {0};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maincontent_handler);
        String[] spinner_choices = getResources().getStringArray(R.array.spinner_choices);
        if (savedInstanceState == null) {
            for (int i = 0; i < spinner_choices.length; i++) {
                Log.d("TESTING", "Choice " + i + " is " + spinner_choices[i]);
            }
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            Log.d("TESTING", "Toolbar set as supportActionBar");

            getSupportActionBar().setDisplayShowTitleEnabled(false);
            Log.d("TESTING", "Toolbar set to NOT display title");

            Spinner spinner = (Spinner) findViewById(R.id.spinner_nav);
            ArrayAdapter<CharSequence> toolbarSpinnerAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                    R.array.spinner_choices, R.layout.custom_spinner_item);
            toolbarSpinnerAdapter.setDropDownViewResource(R.layout.custom_spinner_item_dropdown);
            spinner.setAdapter(toolbarSpinnerAdapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    long pos = parent.getSelectedItemId();

                    switch ((int) pos) {
                        case 0:
                            if (currentFragmentPosition[0] != 0) {

                                FramePhotos pFrame = new FramePhotos();
                                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.content_landing, pFrame)
                                        .addToBackStack("PhotosFrame")
                                        .commit();

                                currentFragmentPosition[0] = 0;
                            } else {

                            }
                            break;
                        case 1:
                            if (currentFragmentPosition[0] != 1) {
                                FrameFilmRolls frFrame = new FrameFilmRolls();
                                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.content_landing, frFrame)
                                        .addToBackStack("FilmRollFrame")
                                        .commit();
                                currentFragmentPosition[0] = 1;
                            } else {

                            }
                            break;
                        case 2:
                            if (currentFragmentPosition[0] != 2) {
                                FrameLenses lFrame = new FrameLenses();
                                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.content_landing, lFrame)
                                        .addToBackStack("LensesFrame")
                                        .commit();
                                currentFragmentPosition[0] = 2;
                            } else {

                            }
                            break;
                        case 3:
                            if (currentFragmentPosition[0] != 3) {
                                FrameCameras cFrame = new FrameCameras();
                                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                                ft.replace(R.id.content_landing, cFrame)
                                        .addToBackStack("CamerasFrame");
                                ft.commit();
                                currentFragmentPosition[0] = 3;
                            } else {

                            }
                            break;
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            Log.d("TESTING", "Toolbar spinner set up complete");
            Log.d("TESTING", "Toolbar setup complete");

            FramePhotos ps = new FramePhotos();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_landing, ps)
                    .addToBackStack("PhotosFrame")
                    .commit();

            currentFragmentPosition[0] = 0;

            DatabaseHelper db = new DatabaseHelper(this);
            Log.d("TESTING", "DatabaseHelper object db setup complete");
            ContentValues cv = new ContentValues();

            db.startDatabase();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
