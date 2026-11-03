package model; // Diubah menjadi huruf kecil agar sesuai dengan import di UjiValidasi.java

public class Kursus {
    String kode;
    String nama;
    String level;
    double biaya;

    public Kursus() {
    }

    public Kursus(String kode, String nama, String level, double biaya) {
        this.kode = kode;
        this.nama = nama;
        this.level = level;
        this.biaya = biaya;
    }

    // Tambahkan getter dan setter untuk biaya beserta validasinya
    public double getBiaya() {
        return biaya;
    }

    public void setBiaya(double biaya) {
        if (biaya < 0) {
            throw new IllegalArgumentException("Biaya tidak boleh negatif");
        }
        this.biaya = biaya;
    }

    public double hitungBiayaSetelahDiskon(double persenDiskon) {
        return biaya - (biaya * persenDiskon / 100.0);
    }
}