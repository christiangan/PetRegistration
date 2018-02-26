package com.christian.reporting;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PersonalInformation extends AppCompatActivity {

    Button btnNext, btnChangeDate;
    EditText editBirthYear, editBirthMonth, editBirthDate, editLName, editFName, editMName, editContact, editEmail;
    TextInputLayout labelLName, labelFName, labelMName, labelContact, labelEmail;
    RadioGroup gender, petOrigin;
    RadioButton genderButton;
    DatePickerDialog.OnDateSetListener dateSetListener;
    public static String SQL_QUERY = "";
    public static String currentClientID = "";
    public String regID = "", provID = "", munID = "", brgyID = "";
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        btnChangeDate = (Button) findViewById(R.id.btnChangeDate);
        editBirthMonth = (EditText) findViewById(R.id.textMM);
        editBirthDate = (EditText) findViewById(R.id.textDD);
        editBirthYear = (EditText) findViewById(R.id.textYYYY);
        btnNext = (Button) findViewById(R.id.btnNext1);
        gender = (RadioGroup) findViewById(R.id.radioGroupGender);
        editLName = (EditText) findViewById(R.id.editLName);
        editFName = (EditText) findViewById(R.id.editFName);
        editMName = (EditText) findViewById(R.id.editMName);
        editContact = (EditText) findViewById(R.id.editNumber);
        editEmail = (EditText) findViewById(R.id.editEmail);
        labelLName = (TextInputLayout) findViewById(R.id.editLabel1);
        labelFName = (TextInputLayout) findViewById(R.id.editLabel2);
        labelMName = (TextInputLayout) findViewById(R.id.editLabel3);
        labelContact = (TextInputLayout) findViewById(R.id.editLabel4);
        labelEmail = (TextInputLayout) findViewById(R.id.editLabel5);
        bundle = getIntent().getExtras();


        btnChangeDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validateForm()) {
                    ClientDatabaseAccess clientDatabaseAccess = ClientDatabaseAccess.getInstance(getApplicationContext());
                    clientDatabaseAccess.open();
                    Date date = new Date();
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                    DateFormat dateFormat2 = new SimpleDateFormat("yyyymmdd");
                    String strDate = dateFormat.format(date);
                    String strDateId = dateFormat2.format(date);
                    String strNameId = (editLName.getText().toString().charAt(0) + editFName.getText().toString()).toUpperCase();
                    currentClientID = strDateId + strNameId;

                    regID = bundle.getString("RCodeNum");
                    provID = bundle.getString("PCodeNum");
                    munID = bundle.getString("MCodeNum");
                    brgyID = bundle.getString("BCodeNum");

                    clientDatabaseAccess.addClientInfo(strDateId + strNameId, editLName.getText().toString(), editFName.getText().toString(), editMName.getText().toString(), editContact.getText().toString(),
                            editEmail.getText().toString(),strDate, getCheckedButton(), editBirthYear.getText().toString() + "-" + editBirthMonth.getText().toString() +"-" +
                    editBirthDate.getText().toString(), regID, provID, munID, brgyID);

                    Log.e("insert Value", "successful");
                    clientDatabaseAccess.close();
                    Toast.makeText(getApplicationContext(), "Insert successful.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(PersonalInformation.this, MainMenu.class);
                    startActivity(intent);
                }

//                Intent intent = new Intent(PersonalInformation.this, PetInformation.class);
//                startActivity(intent);
    }
});

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                editBirthDate.setText(String.valueOf(day));
                editBirthMonth.setText(String.valueOf(month+1));
                editBirthYear.setText(String.valueOf(year));
            }
        };
    }

    public void showDatePickerDialog() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(PersonalInformation.this, dateSetListener, year, month, day);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        dialog.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public String getCheckedButton() {
        int selectedId = gender.getCheckedRadioButtonId();

        // find the radiobutton by returned id
        genderButton = (RadioButton) findViewById(selectedId);
        return genderButton.getText().toString();
    }

    private boolean validateForm() {
//        if (editLName.getText().toString().trim().isEmpty()) {
//            String lName = editLName.getText().toString().trim();
//
//            if (lName.isEmpty()) {
//                labelLName.setError("Field is required.");
//                requestFocus(editLName);
//            } else {
//                labelLName.setErrorEnabled(false);
//
//            }
//        }
//
//        if (editFName.getText().toString().trim().isEmpty()) {
//            String lName = editFName.getText().toString().trim();
//
//            if (lName.isEmpty()) {
//                labelFName.setError("Field is required.");
//                requestFocus(editFName);
//            } else {
//                labelFName.setErrorEnabled(false);
//            }
//        }
//
//        if (editMName.getText().toString().trim().isEmpty()) {
//            String lName = editMName.getText().toString().trim();
//
//            if (lName.isEmpty()) {
//                labelMName.setError("Field is required.");
//                requestFocus(editMName);
//            } else {
//                labelMName.setErrorEnabled(false);
//            }
//        }
//
//        if (editContact.getText().toString().trim().isEmpty()) {
//            String lName = editContact.getText().toString().trim();
//
//            if (lName.isEmpty()) {
//                labelContact.setError("Field is required.");
//                requestFocus(editContact);
//            } else {
//                labelContact.setErrorEnabled(false);
//            }
//        }
//
//        if (editEmail.getText().toString().trim().isEmpty()) {
//            String lName = editEmail.getText().toString().trim();
//
//            if (lName.isEmpty()) {
//                labelEmail.setError("Field is required.");
//                requestFocus(editEmail);
//            } else {
//                labelEmail.setErrorEnabled(false);
//            }
//        }
        if (editFName.getText().toString().trim().equals("") || editLName.getText().toString().trim().equals("") || editMName.getText().toString().trim().equals("") ||
                editContact.getText().toString().trim().equals("") || editEmail.getText().toString().trim().equals("") || editBirthMonth.getText().toString().equals("") ||
                editBirthDate.getText().toString().equals("") || editBirthYear.getText().toString().equals("") || gender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(getApplicationContext(), "Please fill in all the fields.", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

}
