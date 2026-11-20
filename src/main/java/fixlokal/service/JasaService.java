package fixlokal.service;

import fixlokal.model.Jasa;
import fixlokal.model.PenyediaJasa;
import java.util.ArrayList;
import java.util.List;

public class JasaService {
    public static final String SEMUA = "Semua";
    public static final String[] KATEGORI = {"Elektronik", "Plumbing", "AC", "Pengecatan", "Perabot"};

    private final List<Jasa> daftarJasa = new ArrayList<>();
    private int idBerikutnya = 1;

    public JasaService(AuthService auth) {
        // Data dummy
        PenyediaJasa budi = (PenyediaJasa) auth.cariByEmail("budi@fixlokal.id");
        PenyediaJasa siti = (PenyediaJasa) auth.cariByEmail("siti@fixlokal.id");
        tambah("Perbaikan Peralatan Elektronik", "Elektronik",
                "Perbaikan TV, kipas angin, dan peralatan rumah tangga.", 150000, budi);
        tambah("Perbaikan Pipa dan Keran", "Plumbing",
                "Mengatasi pipa bocor, saluran mampet, dan keran rusak.", 100000, siti);
    }

    /** Cari berdasarkan kata kunci (nama jasa/tukang) dan kategori ("Semua" = tanpa filter). */
    public List<Jasa> cari(String kataKunci, String kategori) {
        String kunci = kataKunci.trim().toLowerCase();
        List<Jasa> hasil = new ArrayList<>();
        for (Jasa j : daftarJasa) {
            boolean cocokKategori = kategori.equals(SEMUA) || j.getKategori().equals(kategori);
            boolean cocokKunci = j.getNamaJasa().toLowerCase().contains(kunci)
                    || j.getPenyedia().getNama().toLowerCase().contains(kunci);
            if (cocokKategori && cocokKunci) hasil.add(j);
        }
        return hasil;
    }

    public List<Jasa> getByPenyedia(PenyediaJasa penyedia) {
        List<Jasa> hasil = new ArrayList<>();
        for (Jasa j : daftarJasa) {
            if (j.getPenyedia().getId() == penyedia.getId()) hasil.add(j);
        }
        return hasil;
    }

    public Jasa tambah(String nama, String kategori, String deskripsi, double harga, PenyediaJasa penyedia) {
        Jasa jasa = new Jasa(idBerikutnya++, nama, kategori, deskripsi, harga, penyedia);
        daftarJasa.add(jasa);
        return jasa;
    }

    public void hapus(Jasa jasa) {
        daftarJasa.remove(jasa);
    }
}
