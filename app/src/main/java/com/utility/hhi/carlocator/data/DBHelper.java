package com.utility.hhi.carlocator.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Josiah Hadley on 2/21/2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "cars.db";
    private static final int DB_VER = 2;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }


    @Override
    public void onCreate(SQLiteDatabase database){
        final String CreateTable = "CREATE TABLE" + DBContract.CAR_TABLE + " ("
                + DBContract.car_table._ID + " INTEGER PRIMARY KEY,"
                + DBContract.car_table.NAME + " TEXT NOT NULL,"
                + DBContract.car_table.VIN + " TEXT NOT NULL,"
                + DBContract.car_table.OWNER + " TEXT NOT NULL,"
                + DBContract.car_table.KEY_LOC + " TEXT NOT NULL,"
                + DBContract.car_table.HAS_PASS + " INTEGER,"
                + DBContract.car_table.LAT + " TEXT,"
                + DBContract.car_table.LON + " TEXT);";
        database.execSQL(CreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS " + DBContract.CAR_TABLE);
    }

}
