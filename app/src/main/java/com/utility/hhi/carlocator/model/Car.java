package com.utility.hhi.carlocator.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Josiah Hadley on 2/21/2016.
 */
public class Car implements Parcelable{

    public Car(){}

    public Car(Parcel in){
        setName(in.readString());
        setVin(in.readString());
        setOwner(in.readString());
        setKeyLoc(in.readString());
        setParkPass(in.readInt());
        setLat(in.readString());
        setLon(in.readString());
    }


    @Override
    public int describeContents(){return 0;}

    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(this.getName());
        dest.writeString(this.getVin());
        dest.writeString(this.getOwner());
        dest.writeString(this.getKeyLoc());
        dest.writeInt(this.getParkPass());
        dest.writeString(this.getLat());
        dest.writeString(this.getLon());
    }

    public static final Parcelable.Creator<Car> CREATOR = new Parcelable.Creator<Car>(){
        public Car createFromParcel(Parcel in){return new Car(in);}

        public Car[] newArray(int size){return new Car[size];}
    };

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

    private String name;
    private String vin;
    private String owner;
    private String keyLoc;
    private String lat;
    private String lon;
    private int parkPass;



}
