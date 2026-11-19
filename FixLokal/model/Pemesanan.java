package model;

public class Pemesanan {
    private int id;
    private Pelanggan pelanggan;
    private Jasa jasa;
    private String tanggal;
    private String alamatPengerjaan;
    private String catatan;
    private String status;

    public Pemesanan(int id, Pelanggan pelanggan, Jasa jasa, String tanggal,
                     String alamatPengerjaan, String catatan) {
        this.id = id;
        this.pelanggan = pelanggan;
        this.jasa = jasa;
        this.tanggal = tanggal;
        this.alamatPengerjaan = alamatPengerjaan;
        this.catatan = catatan;
        this.status = "Menunggu konfirmasi";
    }

    public int getId() { return id; }
    public void setId(int id) { if (id > 0) this.id = id; }
    public Pelanggan getPelanggan() { return pelanggan; }
    public Jasa getJasa() { return jasa; }
    public String getTanggal() { return tanggal; }
    public String getAlamatPengerjaan() { return alamatPengerjaan; }
    public String getCatatan() { return catatan; }
    public String getStatus() { return status; }
    public void setStatus(String status) {
        if (status != null && !status.trim().isEmpty()) this.status = status;
    }

    public void tampilkanDetail() {
        System.out.println("\n========== DETAIL PEMESANAN ==========");
        System.out.println("ID pemesanan : " + id);
        System.out.println("Pelanggan    : " + pelanggan.getNama());
        System.out.println("Jasa         : " + jasa.getNamaJasa());
        System.out.println("Penyedia     : " + jasa.getPenyedia().getNama());
        System.out.println("Tanggal      : " + tanggal);
        System.out.println("Alamat       : " + alamatPengerjaan);
        System.out.println("Catatan      : " + (catatan.isEmpty() ? "-" : catatan));
        System.out.printf("Total biaya  : Rp%,.0f%n", jasa.getHarga());
        System.out.println("Status       : " + status);
        System.out.println("======================================");
    }
}
