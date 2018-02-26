package com.christian.reporting;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainMenu extends AppCompatActivity {

    Button btnClient, button3;

    List<String> clientList;
    ClientDatabaseAccess dbAccess;
    public String clientID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        btnClient = (Button) findViewById(R.id.buttonClient);
        button3 = (Button) findViewById(R.id.button3);

        btnClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, LocationChooser.class);
//                finish();
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatabase();
                listDBContents(openDatabase());
            }
        });
    }

    public List<String> openDatabase() {

        ClientDatabaseAccess clientDatabaseAccess = ClientDatabaseAccess.getInstance(this);
        clientDatabaseAccess.open();
        clientList = clientDatabaseAccess.getClients();
        clientDatabaseAccess.close();
        Log.e("list values", String.valueOf(clientList));
        return clientList;
    }

    int rowCursorIndex;
    public void listDBContents(List list) {
        String lName, fName, mName, email, gender, birthday, dateRegistered, listContent = "";
        for (int a = 0; a < (list.size()); a++) {
            rowCursorIndex = a % 12;

            Log.e("mod", String.valueOf(rowCursorIndex));

//            if (!list.get(a).toString().) {
                listContent = listContent.concat(list.get(a).toString() + ",");
//            } else {
//                listContent = listContent.concat("null, ");
//            }
            if (rowCursorIndex == 7) {
                listContent.concat("\n");
            }
            startActivity(new Intent(MainMenu.this, ClientListView.class));

        }

        AlertDialog.Builder builder = new AlertDialog.Builder(MainMenu.this, R.style.Theme_AppCompat_Dialog);
        builder.setTitle("List contents")
                .setMessage(listContent)
                .setCancelable(true).show();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit application")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}
