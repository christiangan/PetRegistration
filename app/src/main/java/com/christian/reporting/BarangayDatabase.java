package com.christian.reporting;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Christian on 2/5/2018.
 */

public class BarangayDatabase extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "BrgyDB.db";
    private static final int DATABASE_VERSION = 1;

    public BarangayDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}
