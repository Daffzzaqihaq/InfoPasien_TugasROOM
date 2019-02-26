package com.daffzzaqihaq.infopasien.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daffzzaqihaq.infopasien.R;
import com.daffzzaqihaq.infopasien.db.PasienDatabase;
import com.daffzzaqihaq.infopasien.model.PasienModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TambahPasienActivity extends AppCompatActivity {

    @BindView(R.id.edtNamaPasien)
    EditText edtNamaPasien;
    @BindView(R.id.edtNamaPenyakit)
    EditText edtNamaPenyakit;
    @BindView(R.id.btnSimpan)
    Button btnSimpan;

    private PasienDatabase pasienDatabase;
    private String namaPasien, namaPenyakit;
    private int idPasien;
    private boolean empty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_pasien);
        ButterKnife.bind(this);

        setTitle("Add New Patient");
        pasienDatabase = PasienDatabase.createDatabase(this);
    }

    @OnClick(R.id.btnSimpan)
    public void onViewClicked() {
        checkData();

        if (!empty) {
            saveData();
            clearData();
            Toast.makeText(this, "New Patient Added", Toast.LENGTH_SHORT).show();
            finish();

        }else {
            Toast.makeText(this, "Please fill all the data", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkData() {
        namaPasien = edtNamaPasien.getText().toString();
        namaPenyakit = edtNamaPenyakit.getText().toString();

        empty = namaPasien.isEmpty() || namaPenyakit.isEmpty();

    }

    private void clearData() {
        edtNamaPasien.setText(" ");
        edtNamaPenyakit.setText(" ");
    }

    private void saveData() {
        PasienModel pasienModel = new PasienModel();
        pasienModel.setNama_pasien(namaPasien);
        pasienModel.setNama_penyakit(namaPenyakit);
        pasienDatabase.kelasDao().insert(pasienModel);
    }

}
