package fixlokal.controller;

import fixlokal.model.Pembayaran;
import fixlokal.model.Pemesanan;
import fixlokal.util.AlertHelper;
import fixlokal.util.AppContext;
import fixlokal.util.UiHelper;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/** Halaman Pembayaran (simulasi). */
public class PembayaranController {
    private final Pemesanan pemesanan;
    private final ComboBox<String> cmbMetode = new ComboBox<>();
    private final Label lblInfo = new Label("Pilih metode pembayaran.");

    public PembayaranController(Pemesanan pemesanan) {
        this.pemesanan = pemesanan;
    }

    public Parent getView() {
        cmbMetode.getItems().addAll(Pembayaran.TUNAI, Pembayaran.TRANSFER, Pembayaran.QRIS);
        cmbMetode.setPromptText("Pilih metode");
        cmbMetode.setOnAction(e -> tampilkanInfo());
        lblInfo.setWrapText(true);

        Button btnKonfirmasi = UiHelper.tombol("Konfirmasi Pembayaran", "btn-success");
        btnKonfirmasi.setOnAction(e -> konfirmasi());
        Button btnNanti = UiHelper.tombol("Bayar Nanti", "btn-secondary");
        btnNanti.setOnAction(e -> Navigator.tampilkanRiwayat());

        VBox kartu = new VBox(10,
                UiHelper.label("Pembayaran", "page-title"),
                new Label("Pesanan  : #" + pemesanan.getId()),
                new Label("Jasa     : " + pemesanan.getJasa().getNamaJasa()),
                new Label("Tukang   : " + pemesanan.getJasa().getPenyedia().getNama()),
                new Label("Tanggal  : " + pemesanan.getTanggal()),
                UiHelper.label("Total: " + UiHelper.rupiah(pemesanan.getTotal()), "page-title"),
                UiHelper.label("Metode pembayaran", "field-label"), cmbMetode,
                lblInfo,
                new HBox(10, btnKonfirmasi, btnNanti));
        kartu.getStyleClass().add("card");
        kartu.setMaxWidth(520);

        VBox halaman = new VBox(kartu);
        halaman.setPadding(new Insets(24));
        halaman.getStyleClass().add("page");
        return halaman;
    }

    private void tampilkanInfo() {
        String metode = cmbMetode.getValue();
        if (metode == null) return;
        switch (metode) {
            case Pembayaran.TUNAI:
                lblInfo.setText("Pembayaran dilakukan langsung kepada tukang.");
                break;
            case Pembayaran.TRANSFER:
                lblInfo.setText("Bank FixLokal (SIMULASI)\nNo. Rek: 1234-5678-9000\n*** SIMULASI PEMBAYARAN ***");
                break;
            case Pembayaran.QRIS:
                lblInfo.setText("[ QRIS SIMULASI ]\n*** SIMULASI PEMBAYARAN - bukan QRIS sungguhan ***");
                break;
            default:
                break;
        }
    }

    private void konfirmasi() {
        String metode = cmbMetode.getValue();
        if (metode == null) {
            AlertHelper.peringatan("Belum Memilih", "Pilih metode pembayaran terlebih dahulu.");
            return;
        }
        AppContext.pembayaran.konfirmasi(pemesanan.getPembayaran(), metode);
        AlertHelper.info("Pembayaran Berhasil",
                "Metode : " + metode + "\nStatus : " + pemesanan.getPembayaran().getStatus());
        Navigator.tampilkanRiwayat();
    }
}
