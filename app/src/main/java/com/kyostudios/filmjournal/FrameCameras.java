package com.kyostudios.filmjournal;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.support.design.widget.FloatingActionButton;

import java.util.ArrayList;

/**
 * Created by Kyoma_000 on 5/12/2015.
 */
public class FrameCameras extends Fragment {
    ArrayList<String> results = new ArrayList<>();
    String[] dialogChoicesLongClick = new String[]{"Delete"};
    String[] columns = new String[]{"_id", "Make", "Model"};
    int recordPosition;
    boolean startEdit;
    View rootView;

    public FrameCameras(){

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle SIS){
        startEdit = false;
        rootView = inflater.inflate(R.layout.content_cameras, container, false);
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab_cameras);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityCameraEntry.class);
                intent.putExtra("Start_EDIT", false);
                startActivity(intent);
            }
        });

        final SQLiteDatabase db = new DatabaseHelper(getActivity().getApplicationContext()).getReadableDatabase();
        DatabaseHelper db2 = new DatabaseHelper(getActivity().getApplicationContext());
        String emptyCheck = "SELECT count(*) FROM Cameras";
        Cursor countC = db.rawQuery(emptyCheck, null);
        countC.moveToFirst();
        int tableCount = countC.getInt(0);
        if(tableCount>0) {
            results = db2.getCameras();
        }
        else{

        }
        final ListView content_cameras = (ListView) rootView.findViewById(R.id.listView_cameras);
        final ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, results);
        content_cameras.setAdapter(listAdapter);
        content_cameras.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Selected Item")
                        .setItems(dialogChoicesLongClick, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {

                                    case 0: //Delete
                                        String nickname = content_cameras.getItemAtPosition(position).toString();

                                        String deleteNicknameStatement = "nickname = \"" + nickname +"\"";
                                        db.delete("Cameras", deleteNicknameStatement, null);
                                        listAdapter.notifyDataSetChanged();
                                        break;
                                }
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }
        });
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle SIS){
        super.onViewCreated(view, SIS);
    }

    @Override
    public void onResume(){
        startEdit = false;
        Log.d("TESTING", "FrameCameras resumed");
        DatabaseHelper db = new DatabaseHelper(getActivity().getApplicationContext());
        db.startDatabase();

        String emptyCheck = "SELECT count(*) FROM Cameras";
        SQLiteDatabase db2 = new DatabaseHelper(getActivity().getApplicationContext()).getReadableDatabase();
        Cursor countC = db2.rawQuery(emptyCheck, null);
        countC.moveToFirst();
        int tableCount = countC.getInt(0);
        if(tableCount>0) {
            results = db.getCameras();
        }
        else{

        }
        ListView content_cameras = (ListView) rootView.findViewById(R.id.listView_cameras);
        content_cameras.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.custom_listview_item, results));
        super.onResume();
    }
}
