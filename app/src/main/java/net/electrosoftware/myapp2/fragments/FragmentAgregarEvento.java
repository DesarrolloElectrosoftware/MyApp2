package net.electrosoftware.myapp2.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.electrosoftware.myapp2.R;

public class FragmentAgregarEvento extends Fragment {

    private View view;

    public FragmentAgregarEvento() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = getActivity().getLayoutInflater().inflate(R.layout.fragment_agregar_evento, container, false);
        // Inflate the layout for this fragment
        return view;
    }

}
