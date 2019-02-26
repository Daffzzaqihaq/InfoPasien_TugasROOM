package com.daffzzaqihaq.infopasien.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.daffzzaqihaq.infopasien.model.PasienModel;

@Database(entities = {PasienModel.class}, version = 1)
public abstract class PasienDatabase extends RoomDatabase {

    public abstract KelasDao kelasDao();

    private static PasienDatabase INSTANCE;

    public static PasienDatabase createDatabase(Context context) {
        synchronized (PasienDatabase.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context, PasienDatabase.class, "db_pasien").allowMainThreadQueries().build();
            }
        }return INSTANCE;
    }

}
