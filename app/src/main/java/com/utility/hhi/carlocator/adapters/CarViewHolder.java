package com.utility.hhi.carlocator.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.utility.hhi.carlocator.R;


import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Josiah Hadley on 2/21/2016.
 */
public class CarViewHolder extends RecyclerView.ViewHolder {
    @Bind(R.id.car_name)
    TextView mCarName;
    @Bind(R.id.vin)
    TextView mVin;

    public CarViewHolder(View v){
        super(v);
        ButterKnife.bind(this, v);
    }
}
