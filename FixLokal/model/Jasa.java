package model;

public class Jasa {
    private int id;
    private String namaJasa;
    private String kategori;
    private String deskripsi;
    private double harga;
    private PenyediaJasa penyedia;

    public Jasa(int id, String namaJasa, String kategori, String deskripsi,
                double harga, PenyediaJasa penyedia) {
        this.id = id;
        this.namaJasa = namaJasa;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
        this.harga = harga;
        this.penyedia = penyedia;
    }

    public int getId() { return id; }
    public void setId(int id) { if (id > 0) this.id = id; }
    public String getNamaJasa() { return namaJasa; }
    public void setNamaJasa(String namaJasa) {
        if (namaJasa != null && !namaJasa.trim().isEmpty()) this.namaJasa = namaJasa;
    }
    public String getKategori() { return kategori; }
    public void setKategori(String kategori) {
        if (kategori != null && !kategori.trim().isEmpty()) this.kategori = kategori;
    }
    public String getDeskripsi() { return deskripsi; }
    public void setDeskripsi(String deskripsi) { this.deskripsi = deskripsi; }
    public double getHarga() { return harga; }
    public void setHarga(double harga) { if (harga >= 0) this.harga = harga; }
    public PenyediaJasa getPenyedia() { return penyedia; }
    public void setPenyedia(PenyediaJasa penyedia) { if (penyedia != null) this.penyedia = penyedia; }

    public void tampilkanDetail() {
        System.out.println("\n---------- DETAIL JASA ----------");
        System.out.println("ID          : " + id);
        System.out.println("Nama jasa   : " + namaJasa);
        System.out.println("Kategori    : " + kategori);
        System.out.println("Deskripsi   : " + deskripsi);
        System.out.printf("Harga       : Rp%,.0f%n", harga);
        System.out.println("Penyedia    : " + penyedia.getNama());
        System.out.println("Keahlian    : " + penyedia.getKeahlian());
        System.out.println("---------------------------------");
    }
}
