package com.example.chris.oblig_2;

import java.util.ArrayList;

/**
 * Created by chris on 05-Mar-18.
 */

public class countryList extends countries {
    ArrayList<countries> liste;

    public countryList(String land, String by, String hotell, Double dagPris, Double flyPris, ArrayList<countries> liste) {
        super(land, by, hotell, dagPris, flyPris);
        this.liste = liste;
    }
}
