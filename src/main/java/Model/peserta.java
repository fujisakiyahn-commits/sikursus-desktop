package Model;

public class peserta extends orang {
    private String nim;
    private String prodi;
    private String jenisKelamin; // BARU: "L" atau "P"
    private String email;        // BARU

    // Constructor lama (dipertahankan agar kode lain tidak error)
    public peserta(int id, String nama, String noHp, String nim, String prodi) {
        super(id, nama, noHp);
        this.nim = nim;
        this.prodi = prodi;
    }

    // BARU: constructor lengkap untuk disimpan ke database
    public peserta(int id, String nama, String noHp, String nim, String prodi,
                   String jenisKelamin, String email) {
        this(id, nama, noHp, nim, prodi);
        this.jenisKelamin = jenisKelamin;
        this.email = email;
    }

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

    @Override
    public String getInfo() {
        return "[Peserta] " + super.getInfo()
                + " | NIM: " + nim
                + " | Prodi: " + prodi;
    }
}