package app;

import java.util.ArrayList;
import model.Instruktur; // Perhatikan huruf 'I' besar
import model.Orang;      // Perhatikan huruf 'O' besar
import model.Peserta;    // Perhatikan huruf 'P' besar

public class DemoInheritance {
    public static void main(String[] args) {
        ArrayList<Orang> daftarorang = new ArrayList<>();

        daftarorang.add(new Peserta(
                1, "Fuji sakiyah nst", "081234567890",
                "252001", "Informatika"));

        daftarorang.add(new Peserta(
                2, "Annisa miftahul jannah", "081298765432",
                "252002", "Informatika"));

        Peserta pesertaUji = new Peserta(
                3, "Cinta ola hernanda", "080000000000",
                "252003", "Informatika");
        
        // Setter milik parent tetap dapat digunakan oleh object Peserta.
        pesertaUji.setNama("Awa aima sugira");
        daftarorang.add(pesertaUji);

        daftarorang.add(new Instruktur(
                101, "Santri wani", "081211110001",
                "Java Desktop"));

        daftarorang.add(new Instruktur(
                102, "Mifta huljanah", "081211110002",
                "Data Science"));

        System.out.println("=== DATA SIKURSUS ===");
        for (Orang orang : daftarorang) {
            System.out.println(orang.getInfo());
        }

        System.out.println("Jumlah object: " + daftarorang.size());
    }
}