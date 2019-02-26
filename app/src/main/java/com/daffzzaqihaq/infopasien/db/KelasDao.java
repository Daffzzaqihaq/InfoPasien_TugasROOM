package com.daffzzaqihaq.infopasien.db;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.daffzzaqihaq.infopasien.model.PasienModel;

import java.util.List;

@Dao
public interface KelasDao {

    @Query("SELECT * FROM PASIEN ORDER BY nama_pasien DESC")
    List<PasienModel> select();

    @Insert
    void insert(PasienModel pasienModel);

    @Update
    void update(PasienModel pasienModel);

    @Delete
    void delete(PasienModel pasienModel);
}
