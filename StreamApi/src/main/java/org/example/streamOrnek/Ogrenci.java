package org.example.streamOrnek;

import java.util.Comparator;

public class Ogrenci implements Comparable<Ogrenci> {

    private Long id;
    private String ad;
    private String soyad;
    private String sehir;
    private String bolum;
    private Double not;

    public Ogrenci(Long id, String ad, String soyad, String sehir, String bolum, Double not) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
        this.sehir = sehir;
        this.bolum = bolum;
        this.not = not;
    }



    public Long getId() {
        return id;
    }

    public String getAd() {
        return ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public String getSehir() {
        return sehir;
    }
    public String getBolum() {
        return bolum;
    }

    public Double getNot() {
        return not;
    }

    @Override
    public String toString() {
        return "Ogrenci{" +
                "id=" + getId() +
                ", ad='" + getAd() + '\'' +
                ", soyad='" + getSoyad() + '\'' +
                ", sehir='" + getSehir() + '\'' +
                ", bolum='" + getBolum() + '\'' +
                ", not=" + getNot() +
                '}';
    }

    @Override
    public int compareTo(Ogrenci o) {
        //return (int) (this.not-o.getNot()); // bu şekilde küçükten büyüğe sıralar.
        return o.getNot().compareTo(this.not); //bu yöntemi ben denedim

    }
}
