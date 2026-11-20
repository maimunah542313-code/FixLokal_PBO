package fixlokal.service;

import fixlokal.model.Pelanggan;
import fixlokal.model.Pengguna;
import fixlokal.model.PenyediaJasa;
import java.util.ArrayList;
import java.util.List;

public class AuthService {
    private final List<Pengguna> daftarPengguna = new ArrayList<>();
    private int idBerikutnya = 1;

    public AuthService() {
        // Data dummy
        daftarPengguna.add(new Pelanggan(idBerikutnya++, "Andi", "andi@gmail.com",
                "andi123", "081234567890", "Bandar Lampung"));
        daftarPengguna.add(new PenyediaJasa(idBerikutnya++, "Budi Santoso", "budi@fixlokal.id",
                "teknisi123", "081200000101", "Bandar Lampung", "Elektronik"));
        daftarPengguna.add(new PenyediaJasa(idBerikutnya++, "Siti Aminah", "siti@fixlokal.id",
                "plumbing123", "081200000102", "Bandar Lampung", "Plumbing"));
    }

    public boolean registrasiPelanggan(String nama, String email, String password,
                                       String telepon, String alamat) {
        if (cariByEmail(email) != null) return false;
        daftarPengguna.add(new Pelanggan(idBerikutnya++, nama, email, password, telepon, alamat));
        return true;
    }

    public boolean registrasiTukang(String nama, String email, String password,
                                    String telepon, String alamat, String keahlian) {
        if (cariByEmail(email) != null) return false;
        daftarPengguna.add(new PenyediaJasa(idBerikutnya++, nama, email, password, telepon, alamat, keahlian));
        return true;
    }

    /** @return pengguna jika email, password, dan peran cocok; null jika gagal. */
    public Pengguna login(String email, String password, String peran) {
        for (Pengguna p : daftarPengguna) {
            if (p.getEmail().equalsIgnoreCase(email) && p.getPassword().equals(password)
                    && p.getPeran().equals(peran)) {
                return p;
            }
        }
        return null;
    }

    public Pengguna cariByEmail(String email) {
        for (Pengguna p : daftarPengguna) {
            if (p.getEmail().equalsIgnoreCase(email)) return p;
        }
        return null;
    }
}
