package model;

public class Pelanggan extends Pengguna {
    private String nomorTelepon;
    private String alamat;

    public Pelanggan(int id, String nama, String email, String password,
                     String nomorTelepon, String alamat) {
        super(id, nama, email, password);
        this.nomorTelepon = nomorTelepon;
        this.alamat = alamat;
    }

    public String getNomorTelepon() { return nomorTelepon; }
    public void setNomorTelepon(String nomorTelepon) {
        if (nomorTelepon != null && !nomorTelepon.trim().isEmpty()) this.nomorTelepon = nomorTelepon;
    }

    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) {
        if (alamat != null && !alamat.trim().isEmpty()) this.alamat = alamat;
    }

    @Override
    public String getPeran() { return "Pelanggan"; }
}
