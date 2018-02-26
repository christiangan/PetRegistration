package com.christian.reporting;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian on 2/22/2018.
 */

public class BarangayDBAccess {
    private SQLiteDatabase database;
    private SQLiteOpenHelper openHelper;
    private static BarangayDBAccess instance;

    private BarangayDBAccess(Context context) {
        this.openHelper = new BarangayDatabase(context);
    }

    public static BarangayDBAccess getInstance(Context context) {
        if (instance == null) {
            instance = new BarangayDBAccess(context);
        }
        return instance;
    }



    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();


        }
    }

    public List<List> getRegions() {
        List<List> outerList = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT RCodeNum, Region from regions", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            List<String> innerList = new ArrayList<>();
            innerList.add(cursor.getString(0));
            innerList.add(cursor.getString(1));
            outerList.add(innerList);
            Log.e("list size: ",  outerList.size() +"");
            cursor.moveToNext();
        }
        Log.e("dbAcc list", String.valueOf(outerList));

        cursor.close();
//        database.close();
        return outerList;
    }


    public List<List> getProvinces(String id) {
        Log.e("regionID passed", id);
        List<List> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select PcodeNum, ProvinceName from provinces where RcodeNum = '" + id + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            List<String> innerList = new ArrayList<>();
            innerList.add(cursor.getString(0));
            innerList.add(cursor.getString(1));
            list.add(innerList);
            Log.e("list size: ",  list.size() +"");
            cursor.moveToNext();
        }
        Log.e("dbAcc list", String.valueOf(list));
        cursor.close();
//        database.close();
        return list;
    }

    public List<List> getMunicipalities(String id) {
        List<List> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select McodeNum, MunCity from municipalities where PcodeNum = '" + id + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            List<String> innerList = new ArrayList<>();
            innerList.add(cursor.getString(0));
            innerList.add(cursor.getString(1));
            list.add(innerList);
            Log.e("list size: ",  list.size() +"");
            cursor.moveToNext();
        }
        Log.e("dbAcc list", String.valueOf(list));
        cursor.close();
//        database.close();
        return list;
    }

    public List<List> getBarangays(String id) {
        List<List> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select BCode, BRGY from barangay where McodeNum = '" + id + "'", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            List<String> innerList = new ArrayList<>();
            innerList.add(cursor.getString(0));
            innerList.add(cursor.getString(1));
            list.add(innerList);
            Log.e("list size: ",  list.size() +"");
            cursor.moveToNext();
        }
        Log.e("dbAcc list", String.valueOf(list));
        cursor.close();
//        database.close();
        return list;
    }
    /*

    IMPORTANT: BRGYDB IS A READ-ONLY DATABASE
    list list array liSt
    cursor cursot database rawquery
    cursor movetofirst
    while cursor not after last


     */
}
