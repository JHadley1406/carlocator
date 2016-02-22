package com.utility.hhi.carlocator.adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

import com.utility.hhi.carlocator.R;
import com.utility.hhi.carlocator.model.Car;

import java.util.List;

/**
 * Created by Josiah Hadley on 2/21/2016.
 */

public class CarAdapter extends RecyclerView.Adapter<CarViewHolder> {

    private List<Car> mCars;

    public CarAdapter(List<Car> cars){
        this.mCars = cars;
    }

    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View itemView = LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_car, viewGroup, false);
        return new CarViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CarViewHolder viewHolder, int position){
        Car car = mCars.get(position);
        viewHolder.mCarName.setText(car.getName());
        viewHolder.mVin.setText(car.getVin());
    }

    @Override
    public int getItemCount(){
        return mCars.size();
    }
}
