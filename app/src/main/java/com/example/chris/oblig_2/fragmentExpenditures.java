package com.example.chris.oblig_2;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by chris on 04-Mar-18.
 */


public class fragmentExpenditures extends Fragment {
    DatabaseHelper mydb;
    SharedPreferences sharedPreferences;
    Spinner spinnerLand;
    TextView txtHotellPris, txtFlyPris;
    EditText etHotellPris, etFlyPris;
    String land, nyhotellPris, nyflyPris, nyHPris, nyFPris, valuta, test;
    Double valutaKurs, valutaNok, hotellPris, flyPris, dblnyhotellPris, dblnyflyPris;
    //ArrayList listen;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_expenditures, null);
        mydb = new DatabaseHelper(getContext());
        spinnerLand = view.findViewById(R.id.spinnerLand);
        populateSpinner();
        txtHotellPris = view.findViewById(R.id.txtHotellPris);
        txtFlyPris = view.findViewById(R.id.txtFlyPris);
        etHotellPris = view.findViewById(R.id.etHotellPris);
        etFlyPris = view.findViewById(R.id.etFlyPris);

        sharedPreferences = this.getActivity().getSharedPreferences("myData", Context.MODE_PRIVATE);
        valutaKurs = Double.parseDouble(sharedPreferences.getString("valutakurs","1.00"));
        valutaNok = Double.parseDouble(sharedPreferences.getString("valutaNok","1.00"));
        valuta = sharedPreferences.getString("valuta","Nok");

        hentListe();

        //EditText tekst = (EditText)view.findViewById(R.id.tekst);
         // TODO Slette alt som er kommentert ut
        final Button button = (Button) view.findViewById(R.id.btnSave);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //land = spinnerLand.getSelectedItem().toString();
                nyHPris = etHotellPris.getText().toString();
                nyFPris = etFlyPris.getText().toString();
                if (nyFPris.length() > 0 && nyHPris.length() > 0) {
                    dblnyhotellPris = valutaNok * (Double.parseDouble(nyFPris));
                    dblnyflyPris = valutaNok * (Double.parseDouble(nyHPris));
                    nyhotellPris = dblnyhotellPris.toString();
                    nyflyPris = dblnyflyPris.toString();

                    updateData(land, nyhotellPris, nyflyPris);
                    //AddData(land, by, hotell, dagpris, flypris);
                } else {
                    Toast.makeText(getContext(),"No fields can be empty",Toast.LENGTH_SHORT);
                }
            }
        });

        spinnerLand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //SharedPreferences.Editor myEditor = sharedPreferences.edit();
                land = spinnerLand.getSelectedItem().toString();
                Cursor data = mydb.getPris(land);
                data.moveToFirst();
                //valutaKurs*Double.parseDouble
                hotellPris = valutaKurs*Double.parseDouble(data.getString(0));
                flyPris = valutaKurs*Double.parseDouble(data.getString(1));
                Toast.makeText(getContext(),hotellPris.toString(),Toast.LENGTH_SHORT);
                //test = data.getString(0).toString();
                //test = data.getString(1).toString();

                txtHotellPris.setText(hotellPris.toString() + ",- " + valuta);
                txtFlyPris.setText(flyPris.toString() + ",- " + valuta);
                //txtHotellPris.setText(data.getString(1));
                //txtFlyPris.setText(data.getString(2));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }

    private void hentListe() {
        Cursor date = mydb.getData();
        ArrayList<String> listen = new ArrayList<>();
    }

    private void populateSpinner() {
        Cursor data = mydb.getData();
        ArrayList<String> landListe = new ArrayList<>();
        while(data.moveToNext()){
            landListe.add(data.getString(1));
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,landListe);
        //ListAdapter adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,landListe);
        spinnerLand.setAdapter(adapter);
    }

    private void updateData(String land, String nyHotellPris, String nyFlyPris){
        mydb.updateData(land, nyHotellPris, nyFlyPris);
    }

    private void AddData(String LAND, String BY, String HOTELL, String DAGPRIS, String FLYPRIS) {
        boolean insertData = mydb.addData(LAND, BY, HOTELL, DAGPRIS, FLYPRIS);
        if(insertData == true){
            Toast.makeText(getContext(),"Data inserted",Toast.LENGTH_SHORT);
        }
        Cursor data = mydb.getData();
        ArrayList<String> listData = new ArrayList<>();
        while (data.moveToNext()){
            listData.add(data.getString(1));
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);

        //EditText tekst = (EditText) View.findViewById(R.id.tekst);

        //final EditText flightPrice = (EditText)view.findViewById(R.id.ETFlightPrice);
    }

    /*
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.knapp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"You are inside import fragment count",Toast.LENGTH_SHORT).show();
            }
        });
    } */

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onPause() {

        super.onPause();
    }

    public void endre(View view) {

    }
}
