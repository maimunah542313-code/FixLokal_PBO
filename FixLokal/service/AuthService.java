package service;

import java.util.ArrayList;
import java.util.List;
import model.Pelanggan;

public class AuthService {
    private final List<Pelanggan> daftarPelanggan = new ArrayList<>();
    private int idBerikutnya = 1;

    public AuthService() {
        // Akun contoh agar program bisa langsung diuji.
        daftarPelanggan.add(new Pelanggan(idBerikutnya++, "Andi", "andi@gmail.com",
                "andi123", "081234567890", "Bandar Lampung"));
    }

    public boolean registrasi(String nama, String email, String password,
                              String telepon, String alamat) {
        if (cariByEmail(email) != null) return false;
        daftarPelanggan.add(new Pelanggan(idBerikutnya++, nama, email, password, telepon, alamat));
        return true;
    }

    public Pelanggan login(String email, String password) {
        for (Pelanggan pelanggan : daftarPelanggan) {
            if (pelanggan.getEmail().equalsIgnoreCase(email)
                    && pelanggan.getPassword().equals(password)) {
                return pelanggan;
            }
        }
        return null;
    }

    private Pelanggan cariByEmail(String email) {
        for (Pelanggan pelanggan : daftarPelanggan) {
            if (pelanggan.getEmail().equalsIgnoreCase(email)) return pelanggan;
        }
        return null;
    }
}
