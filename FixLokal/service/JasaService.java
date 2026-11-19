package service;

import java.util.ArrayList;
import java.util.List;
import model.Jasa;
import model.PenyediaJasa;

public class JasaService {
    private final List<Jasa> daftarJasa = new ArrayList<>();

    public JasaService() {
        PenyediaJasa budi = new PenyediaJasa(101, "Budi Santoso", "budi@fixlokal.id",
                "teknisi123", "Elektronik", "081200000101");
        PenyediaJasa siti = new PenyediaJasa(102, "Siti Aminah", "siti@fixlokal.id",
                "plumbing123", "Plumbing", "081200000102");
        PenyediaJasa rudi = new PenyediaJasa(103, "Rudi Hartono", "rudi@fixlokal.id",
                "ac123", "Pendingin/AC", "081200000103");
        PenyediaJasa joko = new PenyediaJasa(104, "Joko Pratama", "joko@fixlokal.id",
                "cat123", "Pengecatan", "081200000104");

        daftarJasa.add(new Jasa(1, "Perbaikan Peralatan Elektronik", "Elektronik",
                "Perbaikan TV, kipas angin, dan peralatan rumah tangga.", 150000, budi));
        daftarJasa.add(new Jasa(2, "Perbaikan Pipa dan Keran", "Plumbing",
                "Mengatasi pipa bocor, saluran mampet, dan keran rusak.", 100000, siti));
        daftarJasa.add(new Jasa(3, "Servis dan Cuci AC", "AC",
                "Pembersihan, perawatan, dan pemeriksaan AC rumah.", 175000, rudi));
        daftarJasa.add(new Jasa(4, "Pengecatan Dinding", "Cat",
                "Pengecatan ulang dinding rumah atau ruangan.", 200000, joko));
    }

    public void tampilkanSemuaJasa() {
        System.out.println("\n================ DAFTAR JASA ================");
        for (Jasa jasa : daftarJasa) {
            System.out.printf("ID: %d | %s | Kategori: %s | Harga: Rp%,.0f | Penyedia: %s%n",
                    jasa.getId(), jasa.getNamaJasa(), jasa.getKategori(),
                    jasa.getHarga(), jasa.getPenyedia().getNama());
        }
        System.out.println("=============================================");
    }

    public void cariBerdasarkanKategori(String kategori) {
        boolean ditemukan = false;
        System.out.println("\nHasil pencarian kategori: " + kategori);
        for (Jasa jasa : daftarJasa) {
            if (jasa.getKategori().equalsIgnoreCase(kategori)
                    || jasa.getNamaJasa().toLowerCase().contains(kategori.toLowerCase())) {
                System.out.printf("ID: %d | %s | Rp%,.0f | Penyedia: %s%n",
                        jasa.getId(), jasa.getNamaJasa(), jasa.getHarga(),
                        jasa.getPenyedia().getNama());
                ditemukan = true;
            }
        }
        if (!ditemukan) System.out.println("Tidak ada jasa pada kategori tersebut.");
    }

    public Jasa cariById(int id) {
        for (Jasa jasa : daftarJasa) {
            if (jasa.getId() == id) return jasa;
        }
        return null;
    }
}
