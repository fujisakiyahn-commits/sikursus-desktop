package app;

import java.util.ArrayList;
import Model.instruktur;
import Model.orang;
import Model.peserta;

public class DemoInheritance {
    public static void main(String[] args) {
        ArrayList<orang> daftarorang = new ArrayList<>();

        daftarorang.add(new peserta(
                1, "Fuji sakiyah nst", "081234567890",
                "252001", "Informatika"));

        daftarorang.add(new peserta(
                2, "Annisa miftahul jannah", "081298765432",
                "252002", "Informatika"));

        peserta pesertaUji = new peserta(
                3, "Cinta ola hernanda", "080000000000",
                "252003", "Informatika");
        // Setter milik parent tetap dapat digunakan oleh object Peserta.
        pesertaUji.setNama("Awa aima sugira");
        daftarorang.add(pesertaUji);

        daftarorang.add(new instruktur(
                101, "Santri wani", "081211110001",
                "Java Desktop"));

        daftarorang.add(new instruktur(
                102, "Mifta huljanah", "081211110002",
                "Data Science"));

        System.out.println("=== DATA SIKURSUS ===");
        for (orang orang : daftarorang) {
            System.out.println(orang.getInfo());
        }

        System.out.println("Jumlah object: " + daftarorang.size());
    }
}