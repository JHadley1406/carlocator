package com.utility.hhi.carlocator.views;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.utility.hhi.carlocator.R;
import com.utility.hhi.carlocator.adapters.CarAdapter;
import com.utility.hhi.carlocator.data.DBContract;
import com.utility.hhi.carlocator.data.DBProvider;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.car_list)
    RecyclerView mRecyclerView;
    @Bind(R.id.main_fab)
    FloatingActionButton mFab;

    CarAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        adapter = new CarAdapter(getApplicationContext(), getContentResolver().query(DBContract.BASE_CONTENT_URI, null, null, null, null));
        mRecyclerView.setAdapter(adapter);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddVehicle.class);
                startActivityForResult(intent, 666);
            }
        });
/*
        final GestureDetector mGestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {

            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

        });
        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = mRecyclerView.findChildViewUnder(e.getX(),e.getY());



                if(child!=null && mGestureDetector.onTouchEvent(e)){
                    ClipboardManager cp = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("vin", child.);
                    cp.setPrimaryClip(clip);
                    return true;

                }


                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        getContentResolver().notifyChange(DBContract.BASE_CONTENT_URI, null);
    }

}
