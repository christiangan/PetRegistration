package com.christian.reporting;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Christian on 1/18/2018.
 */
public class ClientDatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static ClientDatabaseAccess instance;

    private ClientDatabaseAccess(Context context) {
        this.openHelper = new ClientDatabase(context);
    }

    /**
     * Return a singleton instance of ClientDatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static ClientDatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new ClientDatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public List<String> getClients() {
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT lastName, firstName, middleName, emailAddress, contactNumber, dateRegistered, sexDesc, birthday, rcodenum, pcodenum, mcodenum, bcodenum FROM clients", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            list.add(cursor.getString(1));
            list.add(cursor.getString(2));
            list.add(cursor.getString(3));
            list.add(cursor.getString(4));
            list.add(cursor.getString(5));
            list.add(cursor.getString(6));
            list.add(cursor.getString(7));
            list.add(cursor.getString(8));
            list.add(cursor.getString(9));
            list.add(cursor.getString(10));
            list.add(cursor.getString(11));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<DatabaseModel> getClientListView() {
        List<DatabaseModel> modelList = new ArrayList<DatabaseModel>();
        Cursor cursor = database.rawQuery("SELECT lastName, firstName, middleName, emailAddress, contactNumber, dateRegistered, sexDesc, birthday, rcodenum, pcodenum, mcodenum, bcodenum FROM clients", null);

        cursor.moveToFirst();
        do {
            DatabaseModel model = new DatabaseModel();
            model.setLastName(cursor.getString(0));
            model.setFirstName(cursor.getString(1));
            model.setMiddleName(cursor.getString(2));
            model.setEmail(cursor.getString(3));
            model.setContact(cursor.getString(4));
            model.setDateRegistered(cursor.getString(5));
            model.setSexDesc(cursor.getString(6));
            model.setBday(cursor.getString(7));

            modelList.add(model);
        } while (cursor.moveToNext());
        return modelList;
    }



    public void addClientInfo(String id, String lastName, String firstName, String middleName, String contactNum, String email, String dateRegistered, String gender, String birthday, String regID, String provID, String munID, String brgyID) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("clientId", id);
        contentValues.put("lastName", lastName);
        contentValues.put("firstName", firstName);
        contentValues.put("middleName", middleName);
        contentValues.put("emailAddress", email);
        contentValues.put("contactNumber", contactNum);
        contentValues.put("dateRegistered", dateRegistered);
        contentValues.put("sexDesc", gender);
        contentValues.put("birthday", birthday);
        contentValues.put("RCodeNum", regID);
        contentValues.put("PCodeNum", provID);
        contentValues.put("MCodeNum", munID);
        contentValues.put("BCodeNum", brgyID);

        //birthdayFirstLetterFirstNameWholeLastName

        database.insert("clients", null, contentValues);
    }

    public void addPetInfo(String dateRegistered, String petHabitat, String species, String petName, String petColor, String breed, double weight, String dob, String petSex, String femaleClass, String tagClass, String tagNum, String contactOtherAnimals, String clientId) {
        ContentValues contentValues = new ContentValues();

        contentValues.put("dateRegistered", dateRegistered);
        contentValues.put("petHabitat", petHabitat);
        contentValues.put("species", species);
        contentValues.put("petName", petName);
        contentValues.put("petColor", petColor);
        contentValues.put("breed", breed);
        contentValues.put("weight", weight);
        contentValues.put("dob", dob);
        contentValues.put("petSex", petSex);
        contentValues.put("femaleClass", femaleClass);
        contentValues.put("tagClass", tagClass);
        contentValues.put("tagNum", tagNum);
        contentValues.put("contactOtherAnimals", contactOtherAnimals);
        contentValues.put("clientId", clientId);

        database.insert("petRegistration", null, contentValues);
    }



}

