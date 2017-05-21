package net.electrosoftware.myapp2.activityes;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import net.electrosoftware.myapp2.R;
import net.electrosoftware.myapp2.clasesbases.GridViewAdapter;
import net.electrosoftware.myapp2.clasesbases.ImageItem;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Galeria extends AppCompatActivity {
    private GridView grid_galeria;
    private GridViewAdapter gridviewadapter;
    Toolbar mToolbar;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);

        mToolbar = (Toolbar) findViewById(R.id.toolbarGaleria);
        mToolbar.setTitle("Galeria");

        grid_galeria = (GridView) findViewById(R.id.grid_galeria);
        gridviewadapter = new GridViewAdapter(this, R.layout.item_grid_galeria, getData());
        grid_galeria.setAdapter(gridviewadapter);

        grid_galeria.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                ImageItem item = (ImageItem) parent.getItemAtPosition(position);
                //Create intent
                Intent intent = new Intent(Galeria.this, GaleriaDetalle.class);
                intent.putExtra("title", item.getTitle());
                //intent.putExtra("image", item.getImage());
                //Start details activity
                startActivity(intent);
            }
        });
    }

    /**
     * Prepare some dummy data for grid_galeria
     */
    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            imageItems.add(new ImageItem(bitmap, "Dann Carlton " + i));
        }
        return imageItems;
    }
}
