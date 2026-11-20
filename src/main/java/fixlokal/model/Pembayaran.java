package fixlokal.model;

public class Pembayaran {
    public static final String TUNAI = "Tunai (Cash)";
    public static final String TRANSFER = "Transfer Bank (Simulasi)";
    public static final String QRIS = "QRIS (Simulasi)";

    public static final String BELUM = "Belum Dibayar";
    public static final String MENUNGGU = "Menunggu Pembayaran";
    public static final String LUNAS = "Sudah Dibayar";

    private String metode = "-";
    private String status = BELUM;

    public String getMetode() { return metode; }
    public void setMetode(String metode) { this.metode = metode; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
