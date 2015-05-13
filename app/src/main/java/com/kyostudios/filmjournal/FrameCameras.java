package com.kyostudios.filmjournal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

/**
 * Created by Kyoma_000 on 5/12/2015.
 */
public class FrameCameras extends Fragment {
    ArrayList<String> results = new ArrayList<>();
    View rootview;

    public FrameCameras(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SIS){
        rootview = inflater.inflate(R.layout.content_cameras, container, false);
        FloatingActionButton fab = (FloatingActionButton) rootview.findViewById(R.id.fab_cameras);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityCameraEntry.class);
                startActivity(intent);
            }
        });

        DatabaseHelper db = new DatabaseHelper(getActivity().getApplicationContext());
        db.startDatabase();
        results = db.getCameras();

        ListView content_cameras = (ListView) rootview.findViewById(R.id.listView_cameras);
        content_cameras.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(),R.layout.custom_listview_item, results ));
        content_cameras.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Item long clicked", Toast.LENGTH_SHORT);
                toast.show();
                return false;
            }
        });
        return rootview;
    }

    @Override
    public void onViewCreated(View view, Bundle SIS){
        super.onViewCreated(view, SIS);
    }

    @Override
    public void onResume(){
        Log.d("TESTING", "FrameCameras resumed");
        DatabaseHelper db = new DatabaseHelper(getActivity().getApplicationContext());
        db.startDatabase();
        results = db.getCameras();

        ListView content_cameras = (ListView) rootview.findViewById(R.id.listView_cameras);
        content_cameras.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.custom_listview_item, results));
        super.onResume();
    }
}
