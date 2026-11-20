package fixlokal.controller;

import fixlokal.model.Jasa;
import fixlokal.model.Pelanggan;
import fixlokal.model.Pemesanan;
import fixlokal.util.AlertHelper;
import fixlokal.util.AppContext;
import fixlokal.util.UiHelper;
import java.time.LocalDate;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/** Halaman Pemesanan: detail jasa, form, dan ringkasan harga. */
public class PemesananController {
    private final Pelanggan pelanggan = (Pelanggan) AppContext.penggunaAktif;
    private final Jasa jasa;

    private final DatePicker dpTanggal = new DatePicker(LocalDate.now().plusDays(1));
    private final TextField txtAlamat = new TextField();
    private final TextArea txtCatatan = new TextArea();

    public PemesananController(Jasa jasa) {
        this.jasa = jasa;
    }

    public Parent getView() {
        txtAlamat.setText(pelanggan.getAlamat());
        txtCatatan.setPrefRowCount(3);

        GridPane form = new GridPane();
        form.setHgap(12);
        form.setVgap(10);
        form.addRow(0, UiHelper.label("Tanggal pengerjaan", "field-label"), dpTanggal);
        form.addRow(1, UiHelper.label("Alamat", "field-label"), txtAlamat);
        form.addRow(2, UiHelper.label("Catatan tambahan", "field-label"), txtCatatan);

        Label lblDeskripsi = new Label(jasa.getDeskripsi());
        lblDeskripsi.setWrapText(true);

        Button btnPesan = UiHelper.tombol("Konfirmasi Pesanan", "btn-primary");
        btnPesan.setOnAction(e -> buatPesanan());
        Button btnKembali = UiHelper.tombol("Kembali", "btn-secondary");
        btnKembali.setOnAction(e -> Navigator.tampilkanDashboardPelanggan());

        VBox kartu = new VBox(10,
                UiHelper.label(jasa.getNamaJasa(), "page-title"),
                new Label("Kategori : " + jasa.getKategori()),
                new Label("Tukang   : " + jasa.getPenyedia().getNama()),
                lblDeskripsi,
                form,
                UiHelper.label("Total biaya: " + UiHelper.rupiah(jasa.getHarga()), "page-title"),
                new HBox(10, btnPesan, btnKembali));
        kartu.getStyleClass().add("card");
        kartu.setMaxWidth(560);

        VBox halaman = new VBox(kartu);
        halaman.setPadding(new Insets(24));
        halaman.getStyleClass().add("page");
        return halaman;
    }

    private void buatPesanan() {
        LocalDate tanggal = dpTanggal.getValue();
        String alamat = txtAlamat.getText().trim();
        if (tanggal == null || alamat.isEmpty()) {
            AlertHelper.peringatan("Input Kosong", "Tanggal dan alamat wajib diisi.");
            return;
        }
        Pemesanan p = AppContext.pemesanan.buat(pelanggan, jasa, tanggal, alamat, txtCatatan.getText().trim());
        AlertHelper.info("Pemesanan Berhasil", "Pesanan #" + p.getId() + " dibuat. Silakan pilih pembayaran.");
        Navigator.tampilkanPembayaran(p);
    }
}
