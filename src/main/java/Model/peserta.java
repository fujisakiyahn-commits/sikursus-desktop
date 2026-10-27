package Model;

public class peserta extends orang {
    private String nim;
    private String prodi;
<<<<<<< HEAD
    private String jenisKelamin; // BARU: "L" atau "P"
    private String email;        // BARU

    // Constructor lama (dipertahankan agar kode lain tidak error)
    public peserta(int id, String nama, String noHp, String nim, String prodi) {
=======

    public peserta(int id, String nama, String noHp, String nim, String prodi) {
        // Inheritance: data umum diteruskan ke constructor parent Orang.
>>>>>>> 4f759feb040f8e3ff69ae50d37edb463e5073700
        super(id, nama, noHp);
        this.nim = nim;
        this.prodi = prodi;
    }

<<<<<<< HEAD
    // BARU: constructor lengkap untuk disimpan ke database
    public peserta(int id, String nama, String noHp, String nim, String prodi,
                   String jenisKelamin, String email) {
        this(id, nama, noHp, nim, prodi);
        this.jenisKelamin = jenisKelamin;
        this.email = email;
    }

=======
>>>>>>> 4f759feb040f8e3ff69ae50d37edb463e5073700
    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

<<<<<<< HEAD
    // BARU
    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

=======
>>>>>>> 4f759feb040f8e3ff69ae50d37edb463e5073700
    @Override
    public String getInfo() {
        return "[Peserta] " + super.getInfo()
                + " | NIM: " + nim
                + " | Prodi: " + prodi;
    }
}