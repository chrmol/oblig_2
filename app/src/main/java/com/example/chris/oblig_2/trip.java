package com.example.chris.oblig_2;

/**
 * Created by chris on 05-Mar-18.
 */

public class trip extends countries {

    Integer dager;

    public trip(String land, String by, String hotell, Double dagPris, Double flyPris, Integer dager) {
        super(land, by, hotell, dagPris, flyPris);
        this.dager = dager;
    }

    @Override
    public String toString() {
        return "trip{" +
                "land='" + land + '\'' +
                ", by='" + by + '\'' +
                ", dager=" + dager +
                ", hotell='" + hotell + '\'' +
                ", dagPris=" + dagPris +
                ", flyPris=" + flyPris +
                '}';
    }

    public Integer getDager() {
        return dager;
    }

    public void setDager(Integer dager) {
        this.dager = dager;
    }
}
