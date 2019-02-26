package com.daffzzaqihaq.infopasien.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.daffzzaqihaq.infopasien.db.Constant;


@Entity(tableName = Constant.nama_table)
    public class PasienModel {



        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = Constant.id_pasien)
        private int id_pasien;

        @ColumnInfo(name = Constant.nama_pasien)
        private String nama_pasien;

        @ColumnInfo(name = Constant.nama_penyakit)
        private String nama_penyakit;

        public int getId_pasien() {
            return id_pasien;
        }

        public void setId_pasien(int id_pasien) {
            this.id_pasien = id_pasien;
        }

        public String getNama_pasien() {
            return nama_pasien;
        }

        public void setNama_pasien(String nama_pasien) {
            this.nama_pasien = nama_pasien;
        }

        public String getNama_penyakit() {
            return nama_penyakit;
        }

        public void setNama_penyakit(String nama_penyakit) {
            this.nama_penyakit = nama_penyakit;
        }
    }

