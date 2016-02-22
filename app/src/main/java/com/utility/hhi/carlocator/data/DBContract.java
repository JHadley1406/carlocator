package com.utility.hhi.carlocator.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Josiah Hadley on 2/21/2016.
 */
public class DBContract {


    public static final String CAR_TABLE = "car_table";

    public static final String CONTENT_AUTHORITY = "hhi.cars";
    public static final String PATH = "cars";
    public static Uri BASE_CONTENT_URI = Uri.parse("content://"+CONTENT_AUTHORITY);

    public static final class car_table implements BaseColumns{

        public static final String NAME = "name";
        public static final String VIN = "vin";
        public static final String OWNER = "owner";
        public static final String KEY_LOC = "keys";
        public static final String HAS_PASS = "park_pass";
        public static final String LAT = "lat";
        public static final String LON = "lon";

        public static Uri CAR_CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH;

        public static Uri buildCarWithOwner()
        {
            return BASE_CONTENT_URI.buildUpon().appendPath("owner").build();
        }
    }
}
