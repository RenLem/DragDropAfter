package omy.boston.dragdropafter.fragments;

import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import omy.boston.dragdropafter.R;

/**
 * Created by LosFrancisco on 19-Apr-17.
 */

public class Palette extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.palette, container, false);
    }
}
