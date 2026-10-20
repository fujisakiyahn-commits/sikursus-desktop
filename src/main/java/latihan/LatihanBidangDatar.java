<<<<<<< HEAD
package latihan;

import java.util.Scanner;

public class LatihanBidangDatar {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Menampilkan Pilihan Menu
        System.out.println("=== MENU HITUNG BIDANG DATAR ===");
        System.out.println("1. Persegi Panjang");
        System.out.println("2. Lingkaran");
        System.out.print("Pilih jenis bidang datar (1/2): ");
        int pilihan = input.nextInt();

        System.out.println("--------------------------------");

        // Proses Logika Berdasarkan Pilihan
        if (pilihan == 1) {
            // Input Persegi Panjang
            System.out.print("Masukkan Panjang (cm): ");
            double panjang = input.nextDouble();
            System.out.print("Masukkan Lebar (cm)  : ");
            double lebar = input.nextDouble();

            // Rumus Persegi Panjang
            double luas = panjang * lebar;
            double keliling = 2 * (panjang + lebar);

            // Output Hasil
            System.out.println("\n========= HASIL REKAP =========");
            System.out.printf("Luas Persegi Panjang    : %.2f cm²%n", luas);
            System.out.printf("Keliling Persegi Panjang: %.2f cm%n", keliling);

        } else if (pilihan == 2) {
            // Input Lingkaran
            System.out.print("Masukkan Jari-jari (cm): ");
            double r = input.nextDouble();

            // Rumus Lingkaran
            double luas = Math.PI * r * r;
            double keliling = 2 * Math.PI * r;

            // Output Hasil
            System.out.println("\n========= HASIL REKAP =========");
            System.out.printf("Luas Lingkaran    : %.2f cm²%n", luas);
            System.out.printf("Keliling Lingkaran: %.2f cm%n", keliling);

        } else {
            System.out.println("Error: Pilihan menu tidak valid!");
        }
        System.out.println("===============================");
    }
}
=======

package latihan;
public class LatihanBidangDatar {
    public static void main(String[] args) {

        //variabel persegi panjang
        double panjang =12 ;
        double lebar = 8;
        double luasPersegiPanjang = panjang * lebar;
        double kelilingPersegiPanjang = 2 * (panjang + lebar);

        //variabel lingkaran
        double jariJari = 7;
        double phi = 3.14;
        double luasLingkaran = phi * jariJari * jariJari;
        double kelilingLingkaran = 2 * phi * jariJari;

        //variabel segitiga (alas & tinggi, plus 3 sisi buat keliling)
        double alas = 10;
        double tinggi = 6;
        double sisiA = 10, sisiB = 8, sisiC = 7;
        double luasSegitiga = 0.5 * alas * tinggi;
        double kelilingSegitiga = sisiA + sisiB + sisiC;

        //output
        System.out.println("=== Persegi Panjang ===");
        System.out.println("Luas     : " + luasPersegiPanjang);
        System.out.println("Keliling : " + kelilingPersegiPanjang);

        System.out.println("=== Lingkaran ===");
        System.out.println("Luas     : " + luasLingkaran);
        System.out.println("Keliling : " + kelilingLingkaran);

        System.out.println("=== Segitiga ===");
        System.out.println("Luas     : " + luasSegitiga);
        System.out.println("Keliling : " + kelilingSegitiga);
    }
}   

>>>>>>> a221d1d4881411a60257830f8d5b424d08ac3f08
