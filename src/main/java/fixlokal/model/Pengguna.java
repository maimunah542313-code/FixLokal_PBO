package fixlokal.model;

/** Superclass pengguna (INHERITANCE): Pelanggan dan PenyediaJasa extends Pengguna. */
public abstract class Pengguna {
    private int id;
    private String nama;
    private String email;
    private String password;
    private String nomorTelepon;
    private String alamat;

    public Pengguna(int id, String nama, String email, String password,
                    String nomorTelepon, String alamat) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.password = password;
        this.nomorTelepon = nomorTelepon;
        this.alamat = alamat;
    }

    public int getId() { return id; }
    public String getNama() { return nama; }
    public void setNama(String nama) { if (nama != null && !nama.trim().isEmpty()) this.nama = nama; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getNomorTelepon() { return nomorTelepon; }
    public String getAlamat() { return alamat; }

    public abstract String getPeran();
}
