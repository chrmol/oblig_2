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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by chris on 04-Mar-18.
 */

public class fragmentTravel extends Fragment {

    DatabaseHelper mydb;
    //EditText etLand, etBy, etHotell, etHotellPris, etFlyPris;
    String land, by, hotell, hotellPris, flyPris, strhotellPris, strflyPris;
    double dblhotellPris, dblflyPris;
    SharedPreferences sharedPreferences;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_travel, null);
        mydb = new DatabaseHelper(getContext());


        sharedPreferences = this.getActivity().getSharedPreferences("myData", Context.MODE_PRIVATE);
        String test = sharedPreferences.getString("valuta","nei");
        final Double valutaNok = Double.parseDouble(sharedPreferences.getString("valutaNok","1.00"));
        Toast.makeText(getContext(),test,Toast.LENGTH_SHORT);

        final EditText etLand = (EditText)view.findViewById(R.id.etLand);
        final EditText etBy = (EditText)view.findViewById(R.id.etBy);
        final EditText etHotell = (EditText)view.findViewById(R.id.etHotell);
        final EditText etHotellPris = (EditText)view.findViewById(R.id.etHotellPris);
        final EditText etFlyPris = (EditText)view.findViewById(R.id.etFlyPris);

        Button button = view.findViewById(R.id.btnLagre);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                land = etLand.getText().toString();
                by = etBy.getText().toString();
                hotell = etHotell.getText().toString();
                hotellPris = etHotellPris.getText().toString();
                flyPris = etFlyPris.getText().toString();
                if (land.length() > 0 && by.length() > 0 && hotell.length() > 0 && hotellPris.length() > 0 && flyPris.length() > 0) {

                    dblhotellPris = valutaNok * (Double.parseDouble(hotellPris));
                    dblflyPris = valutaNok * (Double.parseDouble(flyPris));
                    strhotellPris = String.valueOf(dblhotellPris);
                    strflyPris = String.valueOf(dblflyPris);

                    AddData(land, by, hotell, strhotellPris, strflyPris);
                } else {
                    Toast.makeText(getContext(),"All fields need a value",Toast.LENGTH_SHORT);
                }
            }
        });

        return view;
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /*
        view.findViewById(R.id.btnLagre).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"You are inside import fragment count",Toast.LENGTH_SHORT).show();
            }
        });*/
    }

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


}
