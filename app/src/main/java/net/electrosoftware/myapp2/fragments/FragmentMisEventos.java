package net.electrosoftware.myapp2.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.electrosoftware.myapp2.R;
import net.electrosoftware.myapp2.activityes.AgregarEvento;
import net.electrosoftware.myapp2.clasesbases.MisEventosAdapter;
import net.electrosoftware.myapp2.clasesbases.MisEventosData;

import java.util.ArrayList;
import java.util.List;

public class FragmentMisEventos extends Fragment {
    View view;
    TextView txt_mis_eventos_crear;
    RecyclerView rv_mis_eventos;

    List<MisEventosData> dataModels;
    MisEventosAdapter adapter;
    Toolbar mToolbar;

    public FragmentMisEventos() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = getActivity().getLayoutInflater().inflate(R.layout.fragment_mis_eventos, container, false);

        mToolbar = (Toolbar) view.findViewById(R.id.toolbarMisEventos);
        mToolbar.setTitle("Mis Eventos");

        txt_mis_eventos_crear = (TextView) view.findViewById(R.id.txt_mis_eventos_crear);
        txt_mis_eventos_crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), AgregarEvento.class));
            }
        });

        rv_mis_eventos = (RecyclerView) view.findViewById(R.id.rv_mis_eventos);
        LinearLayoutManager linearlayoutmanager = new LinearLayoutManager(getActivity());
        rv_mis_eventos.setLayoutManager(linearlayoutmanager);
        rv_mis_eventos.setHasFixedSize(true);
        initializeData();
        initializeAdapter();

        return view;
    }

    private void initializeData() {
        dataModels = new ArrayList<>();

        Bitmap icon = BitmapFactory.decodeResource(getActivity().getResources(),
                R.drawable.kamran);

        dataModels.add(new MisEventosData(icon, "Twitterwire", "8 Arrowood Drive"));
        dataModels.add(new MisEventosData(icon, "Oyoloo", "34 Jay Hill"));
        dataModels.add(new MisEventosData(icon, "Avavee", "9 Forest Lane"));
        dataModels.add(new MisEventosData(icon, "Twinder", "511 Sauthoff Pass"));
        dataModels.add(new MisEventosData(icon, "Thoughtstorm", "06444 Columbus Pass"));
        dataModels.add(new MisEventosData(icon, "Skajo", "3699 Sunfield Crossing"));
        dataModels.add(new MisEventosData(icon, "Twitternation", "840 Parkside Terrace"));
        dataModels.add(new MisEventosData(icon, "Youspan", "74 Doe Crossing Hill"));
        dataModels.add(new MisEventosData(icon, "Quire", "8 Nevada Plaza"));
    }

    private void initializeAdapter() {
        MisEventosAdapter adapter = new MisEventosAdapter(dataModels);
        rv_mis_eventos.setAdapter(adapter);
    }


}
