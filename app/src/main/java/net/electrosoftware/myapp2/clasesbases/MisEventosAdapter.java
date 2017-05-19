package net.electrosoftware.myapp2.clasesbases;

import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import net.electrosoftware.myapp2.R;

import java.util.List;

/**
 * Created by Jonathan on 18/05/2017.
 */

public class MisEventosAdapter extends RecyclerView.Adapter<MisEventosAdapter.MisEventosViewHolder> {

    public static class MisEventosViewHolder extends RecyclerView.ViewHolder {

        TextView txt_dial_mis_eventos_nombre, txt_dial_mis_eventos_direccion;
        ImageView imv_dial_mis_eventos_foto;
        CardView cv_mis_eventos;

        MisEventosViewHolder(View itemView) {
            super(itemView);
            txt_dial_mis_eventos_nombre = (TextView) itemView.findViewById(R.id.txt_dial_mis_eventos_nombre);
            txt_dial_mis_eventos_direccion = (TextView) itemView.findViewById(R.id.txt_dial_mis_eventos_direccion);
            imv_dial_mis_eventos_foto = (ImageView) itemView.findViewById(R.id.imv_dial_mis_eventos_foto);
            cv_mis_eventos = (CardView) itemView.findViewById(R.id.cv_mis_eventos);
        }
    }

    List<MisEventosData> misEventosDataList;

    public MisEventosAdapter(List<MisEventosData> eventosList) {
        this.misEventosDataList = eventosList;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public MisEventosAdapter.MisEventosViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_mis_eventos, viewGroup, false);
        MisEventosAdapter.MisEventosViewHolder pvh = new MisEventosAdapter.MisEventosViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(MisEventosAdapter.MisEventosViewHolder misEventosViewHolder, final int i) {
        misEventosViewHolder.txt_dial_mis_eventos_nombre.setText(misEventosDataList.get(i).getNombreEvento());
        misEventosViewHolder.txt_dial_mis_eventos_direccion.setText(misEventosDataList.get(i).getDireccionEvento());
        misEventosViewHolder.imv_dial_mis_eventos_foto.setImageBitmap(misEventosDataList.get(i).getFotoEvento());

        misEventosViewHolder.cv_mis_eventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Foto del evento " + misEventosDataList.get(i).getNombreEvento(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return misEventosDataList.size();
    }
}
