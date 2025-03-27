package de.bfw.kartenapp;

import java.io.Serializable;

// Klasse die DB Eintröge als objekte wiederspiegelt
public class Karte implements Serializable {
    private int id;
    private String qPath;
    private String aPath;
    private int box;
    private int kat;
    private long nextReviewDate;
    private int totalReviews;
    private int correctReviews;
    private long lastReviewDate;

    public Karte() {
    }

    public Karte(int id, String qPath, String aPath, int box, int kat, long nextReviewDate, int totalReviews, int correctReviews, long lastReviewDate) {
        this.id = id;
        this.qPath = qPath;
        this.aPath = aPath;
        this.box = box;
        this.kat = kat;
        this.nextReviewDate = nextReviewDate;
        this.totalReviews = totalReviews;
        this.correctReviews = correctReviews;
        this.lastReviewDate = lastReviewDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKat() {
        return kat;
    }

    public void setKat(int kat) {
        this.kat = kat;
    }

    public String getQPath() {
        return qPath;
    }

    public void setQPath(String qPath) {
        this.qPath = qPath;
    }

    public String getAPath() {
        return aPath;
    }

    public void setAPath(String aPath) {
        this.aPath = aPath;
    }

    public int getBox() {
        return box;
    }

    public void setBox(int box) {
        this.box = box;
    }

    public long getNextReviewDate() {
        return nextReviewDate;
    }

    public void setNextReviewDate(long nextReviewDate) {
        this.nextReviewDate = nextReviewDate;
    }

    public int getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(int totalReviews) {
        this.totalReviews = totalReviews;
    }

    public int getCorrectReviews() {
        return correctReviews;
    }

    public void setCorrectReviews(int correctReviews) {
        this.correctReviews = correctReviews;
    }

    public long getLastReviewDate() {
        return lastReviewDate;
    }

    public void setLastReviewDate(long lastReviewDate) {
        this.lastReviewDate = lastReviewDate;
    }
}
