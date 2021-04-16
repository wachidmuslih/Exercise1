package com.example.exercise1;

public class Kontak {
    public String Nama;
    public String Nomor;

    public Kontak(String nama, String nomor)
    {
        nama = Nama;
        nomor = nomor;
    }

    public String getNama() {
        return Nama;
    }

    public String getNomor() {
        return Nomor;
    }

    public void setNomor(String nomor) {
        Nomor = nomor;
    }

    public void setNama(String nama) {
        Nama = nama;
    }
}
