package fixlokal.controller;

import fixlokal.model.Jasa;
import fixlokal.model.Pelanggan;
import fixlokal.model.Pemesanan;
import fixlokal.service.JasaService;
import fixlokal.util.AlertHelper;
import fixlokal.util.AppContext;
import fixlokal.util.UiHelper;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/** Dashboard Pelanggan: sidebar + halaman Cari Jasa / Riwayat Pesanan. */
public class PelangganController {
    private final Pelanggan pelanggan = (Pelanggan) AppContext.penggunaAktif;
    private final BorderPane root = new BorderPane();
    private final boolean langsungRiwayat;

    private final TextField txtCari = new TextField();
    private final ComboBox<String> cmbKategori = new ComboBox<>();
    private final TableView<Jasa> tabelJasa = new TableView<>();

    public PelangganController(boolean langsungRiwayat) {
        this.langsungRiwayat = langsungRiwayat;
    }

    public Parent getView() {
        root.getStyleClass().add("page");
        root.setLeft(buatSidebar());
        if (langsungRiwayat) {
            tampilkanRiwayat();
        } else {
            tampilkanCariJasa();
        }
        return root;
    }

    private VBox buatSidebar() {
        Button btnCari = UiHelper.tombol("Cari Jasa", "sidebar-button");
        Button btnRiwayat = UiHelper.tombol("Riwayat Pesanan", "sidebar-button");
        Button btnLogout = UiHelper.tombol("Logout", "sidebar-button");
        btnCari.setMaxWidth(Double.MAX_VALUE);
        btnRiwayat.setMaxWidth(Double.MAX_VALUE);
        btnLogout.setMaxWidth(Double.MAX_VALUE);
        btnCari.setOnAction(e -> tampilkanCariJasa());
        btnRiwayat.setOnAction(e -> tampilkanRiwayat());
        btnLogout.setOnAction(e -> Navigator.logout());

        Region pengisi = new Region();
        VBox.setVgrow(pengisi, Priority.ALWAYS);

        VBox sidebar = new VBox(8,
                UiHelper.label("FixLokal", "sidebar-title"),
                UiHelper.label(pelanggan.getNama(), "sidebar-title"),
                UiHelper.label(pelanggan.getEmail(), "sidebar-info"),
                UiHelper.label("Akun " + pelanggan.getPeran(), "sidebar-info"),
                btnCari, btnRiwayat, pengisi, btnLogout);
        sidebar.getStyleClass().add("sidebar");
        sidebar.setPrefWidth(210);
        return sidebar;
    }

    // ===== Halaman Cari Jasa =====
    private void tampilkanCariJasa() {
        txtCari.setPromptText("Cari nama jasa atau nama tukang...");
        HBox.setHgrow(txtCari, Priority.ALWAYS);

        cmbKategori.getItems().clear();
        cmbKategori.getItems().add(JasaService.SEMUA);
        cmbKategori.getItems().addAll(JasaService.KATEGORI);
        cmbKategori.setValue(JasaService.SEMUA);

        Button btnCari = UiHelper.tombol("Cari", "btn-primary");
        btnCari.setOnAction(e -> muatJasa());

        tabelJasa.getColumns().clear();
        tabelJasa.getColumns().add(UiHelper.kolom("Nama Jasa", (Jasa j) -> j.getNamaJasa()));
        tabelJasa.getColumns().add(UiHelper.kolom("Kategori", (Jasa j) -> j.getKategori()));
        tabelJasa.getColumns().add(UiHelper.kolom("Tukang", (Jasa j) -> j.getPenyedia().getNama()));
        tabelJasa.getColumns().add(UiHelper.kolom("Harga", (Jasa j) -> UiHelper.rupiah(j.getHarga())));
        tabelJasa.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        VBox.setVgrow(tabelJasa, Priority.ALWAYS);

        Button btnDetail = UiHelper.tombol("Lihat Detail", "btn-secondary");
        btnDetail.setOnAction(e -> lihatDetail());
        Button btnPesan = UiHelper.tombol("Pesan Jasa", "btn-primary");
        btnPesan.setOnAction(e -> pesanJasa());

        VBox isi = new VBox(12,
                UiHelper.label("Cari Jasa Tukang", "page-title"),
                new HBox(10, txtCari, cmbKategori, btnCari),
                tabelJasa,
                new HBox(10, btnDetail, btnPesan));
        isi.setPadding(new Insets(24));
        root.setCenter(isi);
        muatJasa();
    }

    private void muatJasa() {
        tabelJasa.setItems(FXCollections.observableArrayList(
                AppContext.jasa.cari(txtCari.getText(), cmbKategori.getValue())));
    }

    private Jasa jasaTerpilih() {
        Jasa jasa = tabelJasa.getSelectionModel().getSelectedItem();
        if (jasa == null) {
            AlertHelper.peringatan("Belum Memilih", "Pilih salah satu jasa terlebih dahulu.");
        }
        return jasa;
    }

    private void lihatDetail() {
        Jasa j = jasaTerpilih();
        if (j == null) return;
        AlertHelper.info("Detail Jasa",
                j.getNamaJasa() + "\nKategori : " + j.getKategori()
                + "\nTukang   : " + j.getPenyedia().getNama()
                + "\nHarga    : " + UiHelper.rupiah(j.getHarga())
                + "\n\n" + j.getDeskripsi());
    }

    private void pesanJasa() {
        Jasa j = jasaTerpilih();
        if (j != null) Navigator.tampilkanPemesanan(j);
    }

    // ===== Halaman Riwayat =====
    private void tampilkanRiwayat() {
        TableView<Pemesanan> tabel = new TableView<>();
        tabel.getColumns().add(UiHelper.kolom("ID", (Pemesanan p) -> String.valueOf(p.getId())));
        tabel.getColumns().add(UiHelper.kolom("Jasa", (Pemesanan p) -> p.getJasa().getNamaJasa()));
        tabel.getColumns().add(UiHelper.kolom("Tanggal", (Pemesanan p) -> p.getTanggal().toString()));
        tabel.getColumns().add(UiHelper.kolom("Total", (Pemesanan p) -> UiHelper.rupiah(p.getTotal())));
        tabel.getColumns().add(UiHelper.kolom("Status Pesanan", (Pemesanan p) -> p.getStatus()));
        tabel.getColumns().add(UiHelper.kolom("Status Pembayaran", (Pemesanan p) -> p.getPembayaran().getStatus()));
        tabel.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabel.setPlaceholder(new Label("Belum ada riwayat pemesanan."));
        tabel.setItems(FXCollections.observableArrayList(AppContext.pemesanan.getByPelanggan(pelanggan)));
        VBox.setVgrow(tabel, Priority.ALWAYS);

        VBox isi = new VBox(12, UiHelper.label("Riwayat Pesanan", "page-title"), tabel);
        isi.setPadding(new Insets(24));
        root.setCenter(isi);
    }
}
