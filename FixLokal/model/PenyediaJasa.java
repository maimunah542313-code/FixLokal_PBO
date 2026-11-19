package model;

public class PenyediaJasa extends Pengguna {
    private String keahlian;
    private String nomorTelepon;

    public PenyediaJasa(int id, String nama, String email, String password,
                        String keahlian, String nomorTelepon) {
        super(id, nama, email, password);
        this.keahlian = keahlian;
        this.nomorTelepon = nomorTelepon;
    }

    public String getKeahlian() { return keahlian; }
    public void setKeahlian(String keahlian) {
        if (keahlian != null && !keahlian.trim().isEmpty()) this.keahlian = keahlian;
    }

    public String getNomorTelepon() { return nomorTelepon; }
    public void setNomorTelepon(String nomorTelepon) {
        if (nomorTelepon != null && !nomorTelepon.trim().isEmpty()) this.nomorTelepon = nomorTelepon;
    }

    @Override
    public String getPeran() { return "Penyedia Jasa"; }
}
