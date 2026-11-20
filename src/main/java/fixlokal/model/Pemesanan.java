package fixlokal.model;

import java.time.LocalDate;

public class Pemesanan {
    public static final String MENUNGGU = "Menunggu Konfirmasi";
    public static final String DITERIMA = "Diterima";
    public static final String DITOLAK = "Ditolak";
    public static final String DIKERJAKAN = "Sedang Dikerjakan";
    public static final String SELESAI = "Selesai";
    public static final String DIBATALKAN = "Dibatalkan";

    private int id;
    private Pelanggan pelanggan;
    private Jasa jasa;
    private LocalDate tanggal;
    private String alamat;
    private String catatan;
    private String status = MENUNGGU;
    private Pembayaran pembayaran = new Pembayaran();

    public Pemesanan(int id, Pelanggan pelanggan, Jasa jasa, LocalDate tanggal,
                     String alamat, String catatan) {
        this.id = id;
        this.pelanggan = pelanggan;
        this.jasa = jasa;
        this.tanggal = tanggal;
        this.alamat = alamat;
        this.catatan = catatan;
    }

    public int getId() { return id; }
    public Pelanggan getPelanggan() { return pelanggan; }
    public Jasa getJasa() { return jasa; }
    public LocalDate getTanggal() { return tanggal; }
    public String getAlamat() { return alamat; }
    public String getCatatan() { return catatan; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Pembayaran getPembayaran() { return pembayaran; }
    public double getTotal() { return jasa.getHarga(); }
}
