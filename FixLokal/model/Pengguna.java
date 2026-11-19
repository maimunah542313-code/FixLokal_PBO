package model;

public abstract class Pengguna {
    private int id;
    private String nama;
    private String email;
    private String password;

    public Pengguna(int id, String nama, String email, String password) {
        this.id = id;
        this.nama = nama;
        this.email = email;
        this.password = password;
    }

    public int getId() { return id; }
    public void setId(int id) { if (id > 0) this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) {
        if (nama != null && !nama.trim().isEmpty()) this.nama = nama;
    }

    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (email != null && email.contains("@")) this.email = email;
    }

    public String getPassword() { return password; }
    public void setPassword(String password) {
        if (password != null && !password.isEmpty()) this.password = password;
    }

    public abstract String getPeran();
}
