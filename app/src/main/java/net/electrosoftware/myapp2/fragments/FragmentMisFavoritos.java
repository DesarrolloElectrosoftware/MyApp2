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
import android.widget.Button;
import android.widget.TextView;

import net.electrosoftware.myapp2.R;
import net.electrosoftware.myapp2.activityes.AgregarEvento;
import net.electrosoftware.myapp2.clasesbases.MisEventosAdapter;
import net.electrosoftware.myapp2.clasesbases.MisEventosData;

import java.util.ArrayList;
import java.util.List;

public class FragmentMisFavoritos extends Fragment {
    View view;
    TextView txt_mis_favoritos_crear;
    RecyclerView rv_mis_favoritos;

    List<MisEventosData> LugaresDataModels;
    List<MisEventosData> EventosDataModels;
    List<MisEventosData> PromocionesDataModels;
    MisEventosAdapter Lugaresadapter;
    MisEventosAdapter Eventosadapter;
    MisEventosAdapter Promocionesadapter;
    Toolbar mToolbar;

    Button btn_favoritos_lugares, btn_favoritos_eventos, btn_favoritos_promociones;

    public FragmentMisFavoritos() {
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
        view = getActivity().getLayoutInflater().inflate(R.layout.fragment_mis_favoritos, container, false);

        mToolbar = (Toolbar) view.findViewById(R.id.toolbarMisfavoritos);
        mToolbar.setTitle("Mis Favoritos");

        rv_mis_favoritos = (RecyclerView) view.findViewById(R.id.rv_mis_favoritos);
        LinearLayoutManager linearlayoutmanager = new LinearLayoutManager(getActivity());
        rv_mis_favoritos.setLayoutManager(linearlayoutmanager);
        rv_mis_favoritos.setHasFixedSize(true);
        initializeData();
        initializeAdapter(1);

        btn_favoritos_lugares = (Button) view.findViewById(R.id.btn_favoritos_lugares);
        btn_favoritos_eventos = (Button) view.findViewById(R.id.btn_favoritos_eventos);
        btn_favoritos_promociones = (Button) view.findViewById(R.id.btn_favoritos_promociones);

        btn_favoritos_lugares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeAdapter(1);
            }
        });

        btn_favoritos_eventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeAdapter(2);
            }
        });

        btn_favoritos_promociones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializeAdapter(3);
            }
        });
        return view;
    }

    private void initializeData() {
        LugaresDataModels = new ArrayList<>();
        EventosDataModels = new ArrayList<>();
        PromocionesDataModels = new ArrayList<>();

        Bitmap icon = BitmapFactory.decodeResource(getActivity().getResources(),
                R.drawable.kamran);

        LugaresDataModels.add(new MisEventosData(icon, "Paraiso", "8 David Alley"));
        LugaresDataModels.add(new MisEventosData(icon, "Dois Vizinhos", "3599 Morning Hill"));
        LugaresDataModels.add(new MisEventosData(icon, "Manaoag", "94 Orin Plaza"));
        LugaresDataModels.add(new MisEventosData(icon, "Chengzhong", "57873 Sachs Drive"));
        LugaresDataModels.add(new MisEventosData(icon, "Paldit", "5 Loomis Way"));
        LugaresDataModels.add(new MisEventosData(icon, "Asker", "2 Veith Street"));
        LugaresDataModels.add(new MisEventosData(icon, "Jinshan", "6 Longview Parkway"));
        LugaresDataModels.add(new MisEventosData(icon, "Aourir", "699 Pawling Junction"));
        LugaresDataModels.add(new MisEventosData(icon, "Dajin", "38467 Lerdahl Trail"));

        EventosDataModels.add(new MisEventosData(icon, "Twitterwire", "8 Arrowood Drive"));
        EventosDataModels.add(new MisEventosData(icon, "Oyoloo", "34 Jay Hill"));
        EventosDataModels.add(new MisEventosData(icon, "Avavee", "9 Forest Lane"));
        EventosDataModels.add(new MisEventosData(icon, "Twinder", "511 Sauthoff Pass"));
        EventosDataModels.add(new MisEventosData(icon, "Thoughtstorm", "06444 Columbus Pass"));
        EventosDataModels.add(new MisEventosData(icon, "Skajo", "3699 Sunfield Crossing"));
        EventosDataModels.add(new MisEventosData(icon, "Twitternation", "840 Parkside Terrace"));
        EventosDataModels.add(new MisEventosData(icon, "Youspan", "74 Doe Crossing Hill"));
        EventosDataModels.add(new MisEventosData(icon, "Quire", "8 Nevada Plaza"));

        PromocionesDataModels.add(new MisEventosData(icon, "Jewelery", "9 Thackeray Terrace"));
        PromocionesDataModels.add(new MisEventosData(icon, "Automotive", "7 Hanson Drive"));
        PromocionesDataModels.add(new MisEventosData(icon, "Jewelery", "08 Debs Drive"));
        PromocionesDataModels.add(new MisEventosData(icon, "Sports", "86 Sauthoff Center"));
        PromocionesDataModels.add(new MisEventosData(icon, "Kids", "88864 Lyons Hill"));
        PromocionesDataModels.add(new MisEventosData(icon, "Industrial", "619 Dryden Point"));
        PromocionesDataModels.add(new MisEventosData(icon, "Automotive", "1 Almo Hill"));
        PromocionesDataModels.add(new MisEventosData(icon, "Toys", "54362 Montana Avenue"));
        PromocionesDataModels.add(new MisEventosData(icon, "Electronics", "2497 Garrison Junction"));
    }

    private void initializeAdapter(int adapter) {
        Lugaresadapter = new MisEventosAdapter(LugaresDataModels);
        Eventosadapter = new MisEventosAdapter(EventosDataModels);
        Promocionesadapter = new MisEventosAdapter(PromocionesDataModels);

        switch (adapter) {
            case 1:
                rv_mis_favoritos.setAdapter(Lugaresadapter);
                break;
            case 2:
                rv_mis_favoritos.setAdapter(Eventosadapter);
                break;
            case 3:
                rv_mis_favoritos.setAdapter(Promocionesadapter);
                break;
            default:
                rv_mis_favoritos.setAdapter(Lugaresadapter);
                break;
        }

    }
}
