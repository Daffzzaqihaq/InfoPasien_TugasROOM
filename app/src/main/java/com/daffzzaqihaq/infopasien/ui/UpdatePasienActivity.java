package com.daffzzaqihaq.infopasien.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daffzzaqihaq.infopasien.R;
import com.daffzzaqihaq.infopasien.db.Constant;
import com.daffzzaqihaq.infopasien.db.PasienDatabase;
import com.daffzzaqihaq.infopasien.model.PasienModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdatePasienActivity extends AppCompatActivity {

    @BindView(R.id.edtNamaPasien)
    EditText edtNamaPasien;
    @BindView(R.id.edtNamaPenyakit)
    EditText edtNamaPenyakit;
    @BindView(R.id.btnUpdate)
    Button btnUpdate;

    private Bundle bundle;
    private List<PasienModel> pasienModelList;
    private PasienDatabase pasienDatabase;
    private int idPasien;
    private String nama_pasien, nama_penyakit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pasien);
        ButterKnife.bind(this);

        setTitle("Edit Patient Data");
        bundle = getIntent().getExtras();
        pasienModelList = new ArrayList<>();
        pasienDatabase = PasienDatabase.createDatabase(this);
        showData();
    }

    private void showData() {
        idPasien = bundle.getInt(Constant.KEY_ID_PASIEN);
        nama_pasien = bundle.getString(Constant.KEY_NAMA_PASIEN);
        nama_penyakit = bundle.getString(Constant.KEY_NAMA_PENYAKIT);

        edtNamaPasien.setText(nama_pasien);
        edtNamaPenyakit.setText(nama_penyakit);
    }

    @OnClick(R.id.btnUpdate)
    public void onViewClicked() {
        getData();
        saveData();
        Toast.makeText(this, "Successfully Update", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void saveData() {
        PasienModel pasienModel = new PasienModel();
        pasienModel.setId_pasien(idPasien);
        pasienModel.setNama_pasien(nama_pasien);
        pasienModel.setNama_penyakit(nama_penyakit);

        pasienDatabase.kelasDao().update(pasienModel);
    }

    private void getData() {
        nama_pasien = edtNamaPasien.getText().toString();
        nama_penyakit = edtNamaPenyakit.getText().toString();
    }
}
