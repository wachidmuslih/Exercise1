package com.example.exercise1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, ListViewAdapter.ContactsAdapterListener {

    private SearchView searchView;
    public final Kontak[] DaftarKontak = new Kontak[]{
            new Kontak("Inayah", "0980998982374"),
            new Kontak("Ilham R", "082131214243"),
            new Kontak("Eris J", "081231342355"),
            new Kontak("Adam", "08909890889789"),
            new Kontak("Mikas", "081125789123"),
            new Kontak("Eren", "081289172318"),
            new Kontak("Fauzan", "081612190123"),
            new Kontak("Wachid", "081221234112"),
            new Kontak("Andy", "0815378681276"),
            new Kontak("nina", null),
    };
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private ListViewAdapter mAdapter;
    private ArrayList<Kontak> listNama;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listNama = new ArrayList<>();
       this.listNama.addAll(Arrays.asList(DaftarKontak));

        mRecyclerView = findViewById(R.id.recyclerView);


        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new ListViewAdapter(getApplicationContext(), this.listNama , this);
        mRecyclerView.setAdapter(mAdapter);

        searchView = findViewById(R.id.searchView);
        searchView.setOnQueryTextListener(this);

    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        mAdapter.getFilter().filter(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mAdapter.getFilter().filter(newText);
        return false;
    }

    @Override
    public void onContactSelected(Kontak contact) {

    }
}