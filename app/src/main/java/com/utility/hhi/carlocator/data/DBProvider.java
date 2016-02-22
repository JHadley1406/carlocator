package com.utility.hhi.carlocator.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import java.util.HashMap;

/**
 * Created by Josiah Hadley on 2/21/2016.
 */
public class DBProvider extends ContentProvider {

    private static DBHelper mOpenHelper;
    private SQLiteDatabase mDb;
    private static final int CARS = 100;
    private static final int CAR_ID = 101;
    private static final int CARS_WITH_OWNER = 102;
    private static HashMap<String, String> CARS_PROJECTION_MAP;

    private UriMatcher mUriMatcher = buildUriMatcher();

    private static final SQLiteQueryBuilder mCarQuery = new SQLiteQueryBuilder();

    static UriMatcher buildUriMatcher(){
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = DBContract.BASE_CONTENT_URI.toString();
        matcher.addURI(authority, "cars", CARS);
        matcher.addURI(authority, "cars/#", CAR_ID);
        return matcher;
    }

    private int match_uri(Uri uri){
        String link = uri.toString();

        if(link.contentEquals(DBContract.BASE_CONTENT_URI.toString())){
            return CARS;
        }
        return -1;
    }

    @Override
    public boolean onCreate(){
        mOpenHelper = new DBHelper(getContext());
        mDb = mOpenHelper.getWritableDatabase();
        return (mDb == null)? false:true;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs){
        int count = 0;
        switch(match_uri(uri)){
            case CARS:
                count = mDb.update(DBContract.CAR_TABLE, values, selection, selectionArgs);
                break;
            case CAR_ID:
                count = mDb.update(DBContract.CAR_TABLE
                        , values
                        , DBContract.car_table._ID
                            + " = " + uri.getPathSegments().get(1)
                            + (!TextUtils.isEmpty(selection) ? " AND("+selection+")" : "")
                        , selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown Uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public String getType(Uri uri){
        final int car = mUriMatcher.match(uri);

        switch(car){
            case CARS:
                return DBContract.car_table.CONTENT_TYPE;
            case CAR_ID:
                return DBContract.car_table.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown Uri: " + uri);
        }
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){
        Cursor retCursor;
        mCarQuery.setTables(DBContract.CAR_TABLE);
        switch(match_uri(uri)){
            case CARS:
                mCarQuery.setProjectionMap(CARS_PROJECTION_MAP);
                break;
            case CAR_ID:
                mCarQuery.appendWhere(DBContract.car_table._ID + "=" + uri.getPathSegments().get(1));
            default:
                throw new UnsupportedOperationException("Unknown Uri" + uri);
        }

        if (sortOrder == null || sortOrder == ""){
            sortOrder = DBContract.car_table.NAME;
        }
        retCursor = mCarQuery.query(mDb, projection, selection, selectionArgs, null, null, sortOrder);
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values){
        long rowId = mDb.insert(DBContract.CAR_TABLE, "", values);

        if(rowId > 0){
            Uri newUri = ContentUris.withAppendedId(DBContract.BASE_CONTENT_URI, rowId);
            getContext().getContentResolver().notifyChange(newUri, null);
            return newUri;
        }
        throw new SQLException("Failed to add a record into " + uri);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs){
        int count = 0;
        switch (match_uri(uri)){
            case CARS:
                count = mDb.delete(DBContract.CAR_TABLE, selection, selectionArgs);
                break;
            case CAR_ID:
                String id = uri.getPathSegments().get(1);
                count = mDb.delete(DBContract.CAR_TABLE, DBContract.car_table._ID + " = " + id
                + (!TextUtils.isEmpty(selection) ? " AND (" + selection + ")" : ""), selectionArgs);
                break;
            default:
                throw new UnsupportedOperationException("Unknown Uri" + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }


}
