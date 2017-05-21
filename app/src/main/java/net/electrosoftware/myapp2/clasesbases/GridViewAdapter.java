package net.electrosoftware.myapp2.clasesbases;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import net.electrosoftware.myapp2.R;

public class GridViewAdapter extends ArrayAdapter<ImageItem> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<ImageItem> data = new ArrayList<ImageItem>();

    public GridViewAdapter(Context context, int layoutResourceId, ArrayList<ImageItem> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.txt_item_galeria_titulo = (TextView) row.findViewById(R.id.txt_item_galeria_titulo);
            holder.imv_item_galeria_imagen = (ImageView) row.findViewById(R.id.imv_item_galeria_imagen);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        ImageItem item = data.get(position);
        holder.txt_item_galeria_titulo.setText(item.getTitle());
        holder.imv_item_galeria_imagen.setImageBitmap(item.getImage());
        return row;
    }

    static class ViewHolder {
        TextView txt_item_galeria_titulo;
        ImageView imv_item_galeria_imagen;
    }
}