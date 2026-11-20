package fixlokal.model;

public class Pelanggan extends Pengguna {
    public Pelanggan(int id, String nama, String email, String password,
                     String nomorTelepon, String alamat) {
        super(id, nama, email, password, nomorTelepon, alamat);
    }

    @Override
    public String getPeran() { return "Pelanggan"; }
}
