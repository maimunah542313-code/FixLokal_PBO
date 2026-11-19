package service;

import java.util.ArrayList;
import java.util.List;
import model.Jasa;
import model.Pelanggan;
import model.Pemesanan;

public class PemesananService {
    private final List<Pemesanan> daftarPemesanan = new ArrayList<>();
    private int idBerikutnya = 1001;

    public Pemesanan buatPemesanan(Pelanggan pelanggan, Jasa jasa, String tanggal,
                                   String alamat, String catatan) {
        Pemesanan pemesanan = new Pemesanan(idBerikutnya++, pelanggan, jasa,
                tanggal, alamat, catatan);
        daftarPemesanan.add(pemesanan);
        return pemesanan;
    }

    public void tampilkanRiwayat(Pelanggan pelanggan) {
        System.out.println("\n========== RIWAYAT PEMESANAN ==========");
        boolean ditemukan = false;
        for (Pemesanan p : daftarPemesanan) {
            if (p.getPelanggan().getId() == pelanggan.getId()) {
                p.tampilkanDetail();
                ditemukan = true;
            }
        }
        if (!ditemukan) System.out.println("Belum ada riwayat pemesanan.");
    }

    public boolean batalkanPemesanan(Pelanggan pelanggan, int idPemesanan) {
        for (Pemesanan p : daftarPemesanan) {
            if (p.getId() == idPemesanan
                    && p.getPelanggan().getId() == pelanggan.getId()
                    && !p.getStatus().equalsIgnoreCase("Dibatalkan")) {
                p.setStatus("Dibatalkan");
                return true;
            }
        }
        return false;
    }
}
