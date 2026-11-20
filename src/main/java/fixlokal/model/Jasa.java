package fixlokal.model;

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
    public String getNamaJasa() { return namaJasa; }
    public String getKategori() { return kategori; }
    public String getDeskripsi() { return deskripsi; }
    public double getHarga() { return harga; }
    public PenyediaJasa getPenyedia() { return penyedia; }
}
