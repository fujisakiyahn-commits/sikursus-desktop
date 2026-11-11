package model;

public class Kelas {
    private final int idKelas;
    private final String namaKelas;
    private final String keterangan;

    public Kelas(int idKelas, String namaKelas, String keterangan) {
        this.idKelas = idKelas;
        this.namaKelas = namaKelas;
        this.keterangan = keterangan;
    }

    public int getIdKelas() { return idKelas; }
    public String getNamaKelas() { return namaKelas; }
    public String getKeterangan() { return keterangan; }
}