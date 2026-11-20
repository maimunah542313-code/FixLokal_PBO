package fixlokal.service;

import fixlokal.model.Jasa;
import fixlokal.model.Pelanggan;
import fixlokal.model.Pemesanan;
import fixlokal.model.PenyediaJasa;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PemesananService {
    private final List<Pemesanan> daftarPemesanan = new ArrayList<>();
    private int idBerikutnya = 1001;

    public Pemesanan buat(Pelanggan pelanggan, Jasa jasa, LocalDate tanggal, String alamat, String catatan) {
        Pemesanan p = new Pemesanan(idBerikutnya++, pelanggan, jasa, tanggal, alamat, catatan);
        daftarPemesanan.add(p);
        return p;
    }

    public List<Pemesanan> getByPelanggan(Pelanggan pelanggan) {
        List<Pemesanan> hasil = new ArrayList<>();
        for (Pemesanan p : daftarPemesanan) {
            if (p.getPelanggan().getId() == pelanggan.getId()) hasil.add(p);
        }
        return hasil;
    }

    public List<Pemesanan> getByPenyedia(PenyediaJasa penyedia) {
        List<Pemesanan> hasil = new ArrayList<>();
        for (Pemesanan p : daftarPemesanan) {
            if (p.getJasa().getPenyedia().getId() == penyedia.getId()) hasil.add(p);
        }
        return hasil;
    }

    /**
     * Mengubah status dengan urutan yang benar:
     * Menunggu -> Diterima/Ditolak ; Diterima -> Sedang Dikerjakan ; Sedang Dikerjakan -> Selesai.
     * @return false jika perubahan tidak sesuai urutan.
     */
    public boolean ubahStatus(Pemesanan p, String statusBaru) {
        String sekarang = p.getStatus();
        boolean boleh = false;
        switch (statusBaru) {
            case Pemesanan.DITERIMA:
            case Pemesanan.DITOLAK:
                boleh = sekarang.equals(Pemesanan.MENUNGGU);
                break;
            case Pemesanan.DIKERJAKAN:
                boleh = sekarang.equals(Pemesanan.DITERIMA);
                break;
            case Pemesanan.SELESAI:
                boleh = sekarang.equals(Pemesanan.DIKERJAKAN);
                break;
            default:
                break;
        }
        if (boleh) p.setStatus(statusBaru);
        return boleh;
    }
}
