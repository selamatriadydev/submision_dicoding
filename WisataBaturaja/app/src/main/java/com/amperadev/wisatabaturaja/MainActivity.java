package com.amperadev.wisatabaturaja;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rvCategory;
    private ArrayList<Wisata> list = new ArrayList<>();
    final String STATE_TITLE = "state_string";
    final String STATE_LIST = "state_list";
    final String STATE_MODE = "state_mode";
    int mode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCategory = findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);


        list = new ArrayList<>();

        if (savedInstanceState == null) {
            setActionBarTitle("Mode List");
            list.addAll(WisataData.getListData());
            showRecyclerList();
            mode = R.id.action_list;
        } else {
            String stateTitle = savedInstanceState.getString(STATE_TITLE);
            ArrayList<Wisata> stateList = savedInstanceState.getParcelableArrayList(STATE_LIST);
            int stateMode = savedInstanceState.getInt(STATE_MODE);
            setActionBarTitle(stateTitle);
            list.addAll(stateList);
            setMode(stateMode);
        }
    }

    private void showSelectedWisata(Wisata wisata){
        Toast.makeText(this, "Kamu memilih "+wisata.getName(), Toast.LENGTH_SHORT).show();

        Intent moveintent = new Intent(MainActivity.this, DetailActivity.class);
        moveintent.putExtra(DetailActivity.EXTRA_NAME , wisata.getName());
        moveintent.putExtra(DetailActivity.EXTRA_REMARKS , wisata.getRemarks());
        moveintent.putExtra(DetailActivity.EXTRA_TENTANG , wisata.getTentang());
        moveintent.putExtra(DetailActivity.EXTRA_FASILITAS , wisata.getFasilitas());
        moveintent.putExtra(DetailActivity.EXTRA_KATEGORI , wisata.getKategori());
        moveintent.putExtra(DetailActivity.EXTRA_PHOTO , wisata.getPhoto());
        startActivity(moveintent);
    }

    private void showRecyclerList(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        ListWisataAdapter listWisataAdapter = new ListWisataAdapter(this);
        listWisataAdapter.setListWisata(list);
        rvCategory.setAdapter(listWisataAdapter);

        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedWisata(list.get(position));
            }
        });
    }

    private void showRecyclerGrid(){
        rvCategory.setLayoutManager(new GridLayoutManager(this, 2));
        GridWisataAdapter gridWisataAdapter = new GridWisataAdapter(this);
        gridWisataAdapter.setListWisata(list);
        rvCategory.setAdapter(gridWisataAdapter);

        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {

                showSelectedWisata(list.get(position));
            }
        });
    }

    private void showRecyclerCardView(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        CardViewWisataAdapter cardViewWisataAdapter = new CardViewWisataAdapter(this);
        cardViewWisataAdapter.setListWisata(list);
        rvCategory.setAdapter(cardViewWisataAdapter);
    }


    private void setActionBarTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    public void setMode(int selectedMode) {
        String title = null;
        switch (selectedMode) {
            case R.id.action_list:
                title = "Mode List";
                showRecyclerList();
                break;
            case R.id.action_grid:
                title = "Mode Grid";
                showRecyclerGrid();
                break;
            case R.id.action_cardview:
                title = "Mode CardView";
                showRecyclerCardView();
                break;
        }
        mode = selectedMode;
        setActionBarTitle(title);
    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_TITLE, getSupportActionBar().getTitle().toString());
        outState.putParcelableArrayList(STATE_LIST, list);
        outState.putInt(STATE_MODE, mode);
    }
}
