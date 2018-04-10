package com.example.chris.oblig_2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 04-Mar-18.
 */

public class fragmentCountry extends Fragment {
    // TODO Fjerne unødvendig og ubkrukt kode
    DatabaseHelper mydb;
    ListView land;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_country, null);
        //EditText tekst = (EditText)view.findViewById(R.id.tekst);



        mydb = new DatabaseHelper(getContext());
        land = (ListView) view.findViewById(R.id.landListe);

        populateList();

  //      Bundle bundle = getArguments();
   //     String streng = getArguments().getString("countries");
    //    tekst.setText(streng.toString());
        return view;
   //     ArrayList<countries> = ((MainActivity)getActivity()).get


    }

    private void populateList() {
        Cursor data = mydb.getData();
        ArrayList<String> landListe = new ArrayList<>();
        while(data.moveToNext()){
            landListe.add(data.getString(1));
        }
        ListAdapter adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,landListe);
        land.setAdapter(adapter);
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
