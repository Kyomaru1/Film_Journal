package com.kyostudios.filmjournal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import android.support.design.widget.FloatingActionButton;

import java.util.ArrayList;

/**
 * Created by Kyoma_000 on 5/12/2015.
 */
public class FrameFilmRolls extends Fragment {

    public FrameFilmRolls(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup view, Bundle SIS){
        View rootview = inflater.inflate(R.layout.content_filmrolls, view, false);
        FloatingActionButton fab = (FloatingActionButton) rootview.findViewById(R.id.fab_filmRolls);
        ArrayList<Integer> listContentsFilmRoll_number = new ArrayList<>();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ActivityFilmRollEntry.class);
                intent.putExtra("Start_EDIT", false);
                startActivity(intent);
            }
        });
        return rootview;
    }

    @Override
    public void onViewCreated(final View view, Bundle SIS){
        super.onCreate(SIS);

    }
}
