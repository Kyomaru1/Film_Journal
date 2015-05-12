package com.kyostudios.filmjournal;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;

/**
 * Created by Kyoma_000 on 5/12/2015.
 */
public class FramePhotos extends Fragment {

    public FramePhotos(){

    }

    public static FramePhotos newInstance(){
        FramePhotos ps = new FramePhotos();
        return ps;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle SIS ){
        View rootview = inflater.inflate(R.layout.content_photos, container, false);
        FloatingActionButton fab = (FloatingActionButton) rootview.findViewById(R.id.fab_photos);
        ArrayList<String> listContentsPhotos_name = new ArrayList<>();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(getActivity().getApplicationContext(), "FAB Photos clicked", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        return rootview;
    }

    @Override
    public void onViewCreated(final View view, Bundle SIS){
        super.onViewCreated(view, SIS);

    }
}
