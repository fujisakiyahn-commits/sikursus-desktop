
public class Latihanbiayakursus {
    public static void main(String[] args) {

        String kode = "JAVA-BSC";
        String nama = "Java Desktop Fundamental";
        double biaya = 2_120_000;
        double registrasi = 500_000;
        boolean aktif = true;

        double totalSebelumDiskon = biaya + registrasi;

        double diskon;

        if (totalSebelumDiskon >= 3_500_000) {
            diskon = 0.10;
        } else {
            diskon = 0.05;
        }

        double potongan = totalSebelumDiskon * diskon;
        double total = totalSebelumDiskon - potongan;

        String status;

        if (total >= 3_500_000) {
            status = "MAHAL";
        } else {
            status = "TERJANGKAU";
        }

        System.out.println("Kode              : " + kode);
        System.out.println("Kursus            : " + nama);
        System.out.println("Aktif             : " + aktif);
        System.out.printf("Biaya Kursus      : Rp%,.0f%n", biaya);
        System.out.printf("Biaya Registrasi   : Rp%,.0f%n", registrasi);
        System.out.printf("Sebelum Diskon    : Rp%,.0f%n", totalSebelumDiskon);
        System.out.printf("Diskon            : %.0f%%%n", diskon * 100);
        System.out.printf("Potongan          : Rp%,.0f%n", potongan);
        System.out.printf("Total             : Rp%,.0f%n", total);
        System.out.println("Status            : " + status);
    }
}

