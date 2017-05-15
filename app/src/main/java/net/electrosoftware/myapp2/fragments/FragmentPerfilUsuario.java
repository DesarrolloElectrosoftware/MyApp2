package net.electrosoftware.myapp2.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.electrosoftware.myapp2.R;


public class FragmentPerfilUsuario extends Fragment {

    View view;

    public FragmentPerfilUsuario() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = getActivity().getLayoutInflater().inflate(R.layout.fragment_perfil_usuario, null);

        return view;
    }

}
