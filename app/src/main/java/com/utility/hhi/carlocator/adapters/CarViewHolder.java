package com.utility.hhi.carlocator.adapters;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.support.v7.widget.RecyclerView;

import android.view.View;
import android.widget.TextView;

import com.utility.hhi.carlocator.R;


import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Josiah Hadley on 2/21/2016.
 */
public class CarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    @Bind(R.id.car_name)
    TextView mCarName;
    @Bind(R.id.vin)
    TextView mVin;

    public CarViewHolder(View v){
        super(v);
        ButterKnife.bind(this, v);
    }

    @Override
    public void onClick(View v){
        ClipboardManager cp = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("vin", mVin.getText().toString());
        cp.setPrimaryClip(clip);
    }
}
