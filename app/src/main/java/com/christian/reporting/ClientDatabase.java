package com.christian.reporting;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Christian on 1/18/2018.
 */

public class ClientDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "MockClientDB.db";
    private static final int DATABASE_VERSION = 1;


    public ClientDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
