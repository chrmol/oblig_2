package com.example.chris.oblig_2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by chris on 04-Mar-18.
 */

public class fragmentCurrency extends Fragment {
    SharedPreferences sharedPreferences;
    Spinner valutaspin;
    String valuta, valutakurs,valutaNok, valutaArray;
    int valutaindeks;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_currency, null);
        valutaspin = view.findViewById(R.id.valutaSpinner);

        //For å lagre byttet valuta


        sharedPreferences = this.getActivity().getSharedPreferences("myData", Context.MODE_PRIVATE);
        valutaindeks = sharedPreferences.getInt("valutaindeks",0);
        valutaspin.setSelection(valutaindeks);

        valutaspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                SharedPreferences.Editor myEditor = sharedPreferences.edit();
                valutaindeks = i;
                String valutaArray[] = getResources().getStringArray(R.array.valuta);
                valutakurs = valutaArray[i];
                String valutatilnokArray[] = getResources().getStringArray(R.array.valutatilnok);
                valutaNok = valutatilnokArray[i];
                valuta = valutaspin.getSelectedItem().toString();
                myEditor.putInt("valutaindeks",valutaindeks);
                myEditor.putString("valutakurs",valutakurs);
                myEditor.putString("valutaNok",valutaNok);
                myEditor.putString("valuta",valuta);
                myEditor.commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        /*
        Button button = view.findViewById(R.id.btnTestern);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String test = sharedPreferences.getString("valuta","nei");
                Toast.makeText(getContext(),test,Toast.LENGTH_SHORT);
            }
        }); */

        //EditText tekst = (EditText)view.findViewById(R.id.tekst);
        return view;
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
        view.findViewById(R.id.knapp).setOnClickListener(new View.OnClickListener() {
            // TODO slett denne funksjonen
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(),"You are inside import fragment count",Toast.LENGTH_SHORT).show();
            }
        }); */
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
