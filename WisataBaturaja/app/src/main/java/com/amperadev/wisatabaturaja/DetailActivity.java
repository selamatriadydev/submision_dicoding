package com.amperadev.wisatabaturaja;

//import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class DetailActivity extends AppCompatActivity {
    public static final String EXTRA_REMARKS = "extra_remarks";
    public static final String EXTRA_TENTANG = "extra_tentang";
    public static final String EXTRA_FASILITAS = "extra_fasilitas";
    public static final String EXTRA_KATEGORI = "extra_kategori";
    public static final String EXTRA_NAME = "extra_name";
    public static final String EXTRA_PHOTO = "extra_photo";

    TextView tvItemName,tvItemRemarks,tvItemKat,tvItemKat2,tvItemTentang,tvItemAlamat,tvItemFasilitas;

    ImageView detailImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvItemName = findViewById(R.id.tv_item_name);
        tvItemRemarks = findViewById(R.id.tv_item_remarks);
        tvItemKat = findViewById(R.id.tv_item_kat);
        tvItemKat2 = findViewById(R.id.tv_item_kat2);
        tvItemTentang = findViewById(R.id.tv_item_tentang);
        tvItemAlamat = findViewById(R.id.tv_item_alamat);
        tvItemFasilitas = findViewById(R.id.tv_item_fasilitas);

        String name = getIntent().getStringExtra(EXTRA_NAME);
        String remarks = getIntent().getStringExtra(EXTRA_REMARKS);
        String tentang = getIntent().getStringExtra(EXTRA_TENTANG);
        String fasilitas = getIntent().getStringExtra(EXTRA_FASILITAS);
        String kategori = getIntent().getStringExtra(EXTRA_KATEGORI);
        String photo = getIntent().getStringExtra(EXTRA_PHOTO);
        //int age = getIntent().getIntExtra(EXTRA_REMARKS, 0);
        //String text = "Name : " + name + ",\nalamat : " + remarks +",\nphoto : " + photo;
        //tvDataReceived.setText(text);

        tvItemName.setText(name);
        tvItemAlamat.setText(remarks);
        tvItemTentang.setText(tentang);
        tvItemFasilitas.setText(fasilitas);
        tvItemKat.setText(kategori);
        tvItemKat2.setText(kategori);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(name);
        }




        detailImg = (ImageView) findViewById(R.id.img_item_photo);

        Glide.with(this)
                .load(photo)
                .apply(RequestOptions.centerCropTransform())
                .into(detailImg);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}
