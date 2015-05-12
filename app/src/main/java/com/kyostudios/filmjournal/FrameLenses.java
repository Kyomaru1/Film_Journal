package com.kyostudios.filmjournal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melnykov.fab.FloatingActionButton;

/**
 * Created by Kyoma_000 on 5/12/2015.
 */
public class FrameLenses extends Fragment {

    public FrameLenses(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SIS){
        View rootview = inflater.inflate(R.layout.content_lenses, container, false);
        FloatingActionButton fab = (FloatingActionButton) rootview.findViewById(R.id.fab_lenses);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return rootview;
    }

    @Override
    public void onViewCreated(View view, Bundle SIS){
        super.onViewCreated(view, SIS);
    }
}
