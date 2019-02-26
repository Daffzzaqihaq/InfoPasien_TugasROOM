package com.daffzzaqihaq.infopasien.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.daffzzaqihaq.infopasien.R;
import com.daffzzaqihaq.infopasien.adapter.PasienAdapter;
import com.daffzzaqihaq.infopasien.db.Constant;
import com.daffzzaqihaq.infopasien.db.PasienDatabase;
import com.daffzzaqihaq.infopasien.model.PasienModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rvPasien)
    RecyclerView rvPasien;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private PasienAdapter pasienAdapter;
    private PasienDatabase pasienDatabase;
    private List<PasienModel> pasienModelList;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pasienDatabase = PasienDatabase.createDatabase(this);
        pasienModelList = new ArrayList<>();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TambahPasienActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        pasienModelList.clear();
        getData();

        rvPasien.setLayoutManager(new LinearLayoutManager(this));
        rvPasien.setAdapter(new PasienAdapter(this, pasienModelList));

    }

    private void getData() {
        pasienModelList = pasienDatabase.kelasDao().select();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.rvPasien)
    public void onViewClicked() {
    }
}
