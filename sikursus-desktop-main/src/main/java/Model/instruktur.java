package Model;

public class instruktur extends orang {
    private String keahlian;

    public instruktur(int id, String nama, String noHp, String keahlian) {
        // Inheritance: data umum diteruskan ke constructor parent Orang.
        super(id, nama, noHp);
        this.keahlian = keahlian;
    }

    public String getKeahlian() {
        return keahlian;
    }

    public void setKeahlian(String keahlian) {
        this.keahlian = keahlian;
    }

    @Override
    public String getInfo() {
        return "[Instruktur] " + super.getInfo()
                + " | Keahlian: " + keahlian;
    }
}