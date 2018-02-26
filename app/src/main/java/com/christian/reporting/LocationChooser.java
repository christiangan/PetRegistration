package com.christian.reporting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class LocationChooser extends AppCompatActivity {

    Spinner spinnerRegion, spinnerProvince, spinnerMunicipality, spinnerBarangay;
    TextView tv1, tv2, tv3, tv4;
    Button buttonDispToast;
    public String regID = "", provID = "", munID = "", brgyID = "";

    List<List> listRegion, listProvince, listMun, listBrgy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_chooser);

        listRegion = new ArrayList<>();
        listProvince = new ArrayList<>();
        listMun = new ArrayList<>();
        listBrgy = new ArrayList<>();

        spinnerRegion = (Spinner) findViewById(R.id.spinner1);
        spinnerProvince =  (Spinner) findViewById(R.id.spinner2);
        spinnerMunicipality = (Spinner) findViewById(R.id.spinner3);
        spinnerBarangay = (Spinner) findViewById(R.id.spinner4);

        tv1 = (TextView) findViewById(R.id.textView);
        tv2 = (TextView) findViewById(R.id.textView2);
        tv3 = (TextView) findViewById(R.id.textView3);
        tv4 = (TextView) findViewById(R.id.textView4);

        buttonDispToast = (Button) findViewById(R.id.btnDispToast);

        BarangayDBAccess brgyDbAccess = BarangayDBAccess.getInstance(getApplicationContext());
        brgyDbAccess.open();
//        brgyDbAccess.getRegions();
//        listRegion.clear();
//        listRegion.add(brgyDbAccess.getRegions());
        listRegion = brgyDbAccess.getRegions();
        Log.e("region list", String.valueOf(listRegion));
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, getDatabaseContents(listRegion));
        Log.e("spinnerRegion list", String.valueOf(getDatabaseContents(listRegion)));
        spinnerRegion.setAdapter(dataAdapter);
//        getDatabaseContents(listRegion);

        spinnerRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Log.e("spnReg listener", "entered onItemSelected");
                int index = spinnerRegion.getSelectedItemPosition();
                Log.e("spnReg listener", "index selected: " + index);
                String selected = getSelectedCodeNum(index, listRegion);
                regID = selected;
                Log.e("spnReg listener", selected);
                BarangayDBAccess brgyDbAccess = BarangayDBAccess.getInstance(getApplicationContext());
                brgyDbAccess.open();
                listProvince = brgyDbAccess.getProvinces(selected);
                ArrayAdapter<String> provinceAdapter = new ArrayAdapter<>(getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item, getDatabaseContents(listProvince));
                spinnerProvince.setAdapter(provinceAdapter);
                tv2.setVisibility(View.VISIBLE);
                spinnerProvince.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerProvince.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("spnProv listener", "entered onItemSelected");
                int index = spinnerProvince.getSelectedItemPosition();
                Log.e("spnProv listener", "index selected: " + index);
                String selected = getSelectedCodeNum(index, listProvince);
                provID = selected;
                Log.e("spnProv listener", selected);
                BarangayDBAccess brgyDbAccess = BarangayDBAccess.getInstance(getApplicationContext());
                brgyDbAccess.open();
                listMun = brgyDbAccess.getMunicipalities(selected);
                ArrayAdapter<String> municipalityAdapter = new ArrayAdapter<>(getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item, getDatabaseContents(listMun));
                spinnerMunicipality.setAdapter(municipalityAdapter);
                tv3.setVisibility(View.VISIBLE);
                spinnerMunicipality.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerMunicipality.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("spnMun listener", "entered onItemSelected");
                int index = spinnerMunicipality.getSelectedItemPosition();
                Log.e("spnMun listener", "index selected: " + index);
                String selected = getSelectedCodeNum(index, listMun);
                munID = selected;
                Log.e("spnMun listener", selected);
                BarangayDBAccess brgyDbAccess = BarangayDBAccess.getInstance(getApplicationContext());
                brgyDbAccess.open();
                listBrgy = brgyDbAccess.getBarangays(selected);
                ArrayAdapter<String> brgyAdapter = new ArrayAdapter<>(getApplicationContext(),
                        android.R.layout.simple_spinner_dropdown_item, getDatabaseContents(listBrgy));
                spinnerBarangay.setAdapter(brgyAdapter);
                tv4.setVisibility(View.VISIBLE);
                spinnerBarangay.setVisibility(View.VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerBarangay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int index = spinnerBarangay.getSelectedItemPosition();
                String selected = getSelectedCodeNum(index, listMun);
                brgyID = selected;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        buttonDispToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Region: " + spinnerRegion.getSelectedItem() + ", " +
                        spinnerProvince.getSelectedItem() + " Province, Municipality of " + spinnerMunicipality.getSelectedItem() +
                        ", Barangay " + spinnerBarangay.getSelectedItem(), Toast.LENGTH_SHORT).show();

                Bundle bundle = new Bundle();
                bundle.putString("region", spinnerRegion.getSelectedItem().toString());
                bundle.putString("province", spinnerProvince.getSelectedItem().toString());
                bundle.putString("municipality", spinnerMunicipality.getSelectedItem().toString());
                bundle.putString("barangay", spinnerBarangay.getSelectedItem().toString());
                bundle.putString("RCodeNum", regID);
                bundle.putString("PCodeNum", provID);
                bundle.putString("MCodeNum", munID);
                bundle.putString("BCodeNum", brgyID);

                Intent intent = new Intent(LocationChooser.this, PersonalInformation.class);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }

    public List<String> getDatabaseContents(List<List> outerList) {
        List<String> innerList = new ArrayList<>();
        Log.e("getDBCont", "entered outer list: " + String.valueOf(outerList));

        for (int a = 0; a < (outerList.size()); a++) {

            Log.e("getDBCont forLoop", a + "");
            Log.e("getDBCont loopSize", outerList.size() + "");
//            if (a%2 == 1) {
            Log.e("getStringList", String.valueOf(outerList.get(a)));
            innerList.add(outerList.get(a).get(1).toString());
            Log.e("getDBCont", "entered inner list");
//            }
        }
        return innerList;
    }

    public String getSelectedCodeNum(int index, List<List> outerList) {
        String codeNum = "";
        Log.e("selectedID", outerList.get(index).get(0).toString());
        codeNum = outerList.get(index).get(0).toString();
        return codeNum;
    }


}
