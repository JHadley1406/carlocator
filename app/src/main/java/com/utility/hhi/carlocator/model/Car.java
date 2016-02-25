package com.utility.hhi.carlocator.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.utility.hhi.carlocator.data.DBContract;

/**
 * Created by Josiah Hadley on 2/21/2016.
 */
public class Car{


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getKeyLoc() {
        return keyLoc;
    }

    public void setKeyLoc(String keyLoc) {
        this.keyLoc = keyLoc;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public int getParkPass() {
        return parkPass;
    }

    public void setParkPass(int parkPass) {
        this.parkPass = parkPass;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public static Car fromCursor(Cursor cursor){
        Car car = new Car();
        car.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.car_table._ID)));
        car.setName(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.car_table.NAME)));
        car.setVin(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.car_table.VIN)));
        car.setKeyLoc(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.car_table.KEY_LOC)));
        car.setOwner(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.car_table.OWNER)));
        car.setParkPass(cursor.getInt(cursor.getColumnIndexOrThrow(DBContract.car_table.HAS_PASS)));
        car.setLon(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.car_table.LON)));
        car.setLat(cursor.getString(cursor.getColumnIndexOrThrow(DBContract.car_table.LAT)));
        return car;
    }

    private int id;
    private String name;
    private String vin;
    private String owner;
    private String keyLoc;
    private String lat;
    private String lon;
    private int parkPass;



}
