package fixlokal.model;

public class PenyediaJasa extends Pengguna {
    private String keahlian;

    public PenyediaJasa(int id, String nama, String email, String password,
                        String nomorTelepon, String alamat, String keahlian) {
        super(id, nama, email, password, nomorTelepon, alamat);
        this.keahlian = keahlian;
    }

    public String getKeahlian() { return keahlian; }

    @Override
    public String getPeran() { return "Tukang"; }
}
