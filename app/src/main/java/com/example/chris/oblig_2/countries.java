package com.example.chris.oblig_2;

/**
 * Created by chris on 05-Mar-18.
 */

public class countries {
    String land;
    String by;
    String hotell;
    Double dagPris;
    Double flyPris;

    public countries(String land, String by, String hotell, Double dagPris, Double flyPris) {
        this.land = land;
        this.by = by;
        this.hotell = hotell;
        this.dagPris = dagPris;
        this.flyPris = flyPris;
    }

    @Override
    public String toString() {
        return "countries{" +
                "land='" + land + '\'' +
                ", by='" + by + '\'' +
                ", hotell='" + hotell + '\'' +
                ", dagPris=" + dagPris +
                ", flyPris=" + flyPris +
                '}';
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getBy() {
        return by;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public String getHotell() {
        return hotell;
    }

    public void setHotell(String hotell) {
        this.hotell = hotell;
    }

    public Double getDagPris() {
        return dagPris;
    }

    public void setDagPris(Double dagPris) {
        this.dagPris = dagPris;
    }

    public Double getFlyPris() {
        return flyPris;
    }

    public void setFlyPris(Double flyPris) {
        this.flyPris = flyPris;
    }
}
