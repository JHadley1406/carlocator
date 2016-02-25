package com.utility.hhi.carlocator.views;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.utility.hhi.carlocator.R;
import com.utility.hhi.carlocator.data.DBContract;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AddVehicle extends AppCompatActivity {

    @Bind(R.id.add_vehicle_name)
    EditText mVehicleName;
    @Bind(R.id.add_vehicle_vin)
    EditText mVin;
    @Bind(R.id.add_fab)
    FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mVehicleName.getText().length() > 0 && mVin.getText().length() > 0) {
                    ContentValues vals = new ContentValues();

                    vals.put(DBContract.car_table.NAME, mVehicleName.getText().toString());

                    vals.put(DBContract.car_table.VIN, mVin.getText().toString());
                    vals.put(DBContract.car_table.KEY_LOC, "");
                    vals.put(DBContract.car_table.HAS_PASS, 0);
                    vals.put(DBContract.car_table.OWNER, "Automatic");
                    vals.put(DBContract.car_table.LON, "");
                    vals.put(DBContract.car_table.LAT, "");
                    getContentResolver().insert(DBContract.BASE_CONTENT_URI, vals);
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
