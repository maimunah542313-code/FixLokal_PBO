package fixlokal.controller;

import fixlokal.model.Jasa;
import fixlokal.model.Pemesanan;
import fixlokal.model.PenyediaJasa;
import fixlokal.service.JasaService;
import fixlokal.util.AlertHelper;
import fixlokal.util.AppContext;
import fixlokal.util.UiHelper;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/** Dashboard Tukang: kelola jasa dan proses pesanan masuk. */
public class TukangController {
    private final PenyediaJasa tukang = (PenyediaJasa) AppContext.penggunaAktif;

    private final TableView<Jasa> tabelJasa = new TableView<>();
    private final TableView<Pemesanan> tabelPesanan = new TableView<>();
    private final TextField txtNama = new TextField();
    private final ComboBox<String> cmbKategori = new ComboBox<>();
    private final TextField txtHarga = new TextField();

    public Parent getView() {
        // ----- header -----
        Button btnLogout = UiHelper.tombol("Logout", "btn-secondary");
        btnLogout.setOnAction(e -> Navigator.logout());
        Region pengisi = new Region();
        HBox.setHgrow(pengisi, Priority.ALWAYS);
        HBox header = new HBox(10,
                UiHelper.label("Dashboard Tukang - " + tukang.getNama(), "page-title"),
                pengisi,
                UiHelper.label("Keahlian: " + tukang.getKeahlian(), "subtitle"),
                btnLogout);

        // ----- bagian jasa -----
        tabelJasa.getColumns().add(UiHelper.kolom("Nama Jasa", (Jasa j) -> j.getNamaJasa()));
        tabelJasa.getColumns().add(UiHelper.kolom("Kategori", (Jasa j) -> j.getKategori()));
        tabelJasa.getColumns().add(UiHelper.kolom("Harga", (Jasa j) -> UiHelper.rupiah(j.getHarga())));
        tabelJasa.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tabelJasa.setPrefHeight(170);

        txtNama.setPromptText("Nama jasa");
        txtHarga.setPromptText("Harga (angka)");
        cmbKategori.getItems().addAll(JasaService.KATEGORI);
        cmbKategori.setValue(tukang.getKeahlian());
        Button btnTambah = UiHelper.tombol("Tambah Jasa", "btn-primary");
        btnTambah.setOnAction(e -> tambahJasa());
        Button btnHapus = UiHelper.tombol("Hapus Jasa", "btn-danger");
        btnHapus.setOnAction(e -> hapusJasa());

        VBox kartuJasa = new VBox(10,
                UiHelper.label("Jasa Saya", "page-title"),
                tabelJasa,
                new HBox(10, txtNama, cmbKategori, txtHarga, btnTambah, btnHapus));
        kartuJasa.getStyleClass().add("card");

        // ----- bagian pesanan -----
        tabelPesanan.getColumns().add(UiHelper.kolom("ID", (Pemesanan p) -> String.valueOf(p.getId())));
        tabelPesanan.getColumns().add(UiHelper.kolom("Pelanggan", (Pemesanan p) -> p.getPelanggan().getNama()));
        tabelPesanan.getColumns().add(UiHelper.kolom("Jasa", (Pemesanan p) -> p.getJasa().getNamaJasa()));
        tabelPesanan.getColumns().add(UiHelper.kolom("Tanggal", (Pemesanan p) -> p.getTanggal().toString()));
        tabelPesanan.getColumns().add(UiHelper.kolom("Status", (Pemesanan p) -> p.getStatus()));
        tabelPesanan.getColumns().add(UiHelper.kolom("Pembayaran", (Pemesanan p) -> p.getPembayaran().getStatus()));
        tabelPesanan.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        VBox.setVgrow(tabelPesanan, Priority.ALWAYS);

        Button btnTerima = UiHelper.tombol("Terima", "btn-success");
        btnTerima.setOnAction(e -> ubahStatus(Pemesanan.DITERIMA));
        Button btnTolak = UiHelper.tombol("Tolak", "btn-danger");
        btnTolak.setOnAction(e -> ubahStatus(Pemesanan.DITOLAK));
        Button btnKerja = UiHelper.tombol("Sedang Dikerjakan", "btn-primary");
        btnKerja.setOnAction(e -> ubahStatus(Pemesanan.DIKERJAKAN));
        Button btnSelesai = UiHelper.tombol("Selesai", "btn-primary");
        btnSelesai.setOnAction(e -> ubahStatus(Pemesanan.SELESAI));
        Button btnRefresh = UiHelper.tombol("Refresh", "btn-secondary");
        btnRefresh.setOnAction(e -> muatData());

        VBox kartuPesanan = new VBox(10,
                UiHelper.label("Pesanan Masuk", "page-title"),
                tabelPesanan,
                new HBox(10, btnTerima, btnTolak, btnKerja, btnSelesai, btnRefresh));
        kartuPesanan.getStyleClass().add("card");
        VBox.setVgrow(kartuPesanan, Priority.ALWAYS);

        VBox root = new VBox(14, header, kartuJasa, kartuPesanan);
        root.setPadding(new Insets(20));
        root.getStyleClass().add("page");
        muatData();
        return root;
    }

    private void muatData() {
        tabelJasa.setItems(FXCollections.observableArrayList(AppContext.jasa.getByPenyedia(tukang)));
        tabelPesanan.setItems(FXCollections.observableArrayList(AppContext.pemesanan.getByPenyedia(tukang)));
    }

    private void tambahJasa() {
        String nama = txtNama.getText().trim();
        if (nama.isEmpty() || txtHarga.getText().trim().isEmpty()) {
            AlertHelper.peringatan("Input Kosong", "Nama jasa dan harga wajib diisi.");
            return;
        }
        try {
            double harga = Double.parseDouble(txtHarga.getText().trim());
            AppContext.jasa.tambah(nama, cmbKategori.getValue(),
                    "Jasa " + nama + " oleh " + tukang.getNama(), harga, tukang);
            txtNama.clear();
            txtHarga.clear();
            muatData();
        } catch (NumberFormatException ex) {
            AlertHelper.error("Harga Tidak Valid", "Harga harus berupa angka.");
        }
    }

    private void hapusJasa() {
        Jasa jasa = tabelJasa.getSelectionModel().getSelectedItem();
        if (jasa == null) {
            AlertHelper.peringatan("Belum Memilih", "Pilih jasa yang akan dihapus.");
            return;
        }
        AppContext.jasa.hapus(jasa);
        muatData();
    }

    private void ubahStatus(String statusBaru) {
        Pemesanan p = tabelPesanan.getSelectionModel().getSelectedItem();
        if (p == null) {
            AlertHelper.peringatan("Belum Memilih", "Pilih salah satu pesanan terlebih dahulu.");
            return;
        }
        if (AppContext.pemesanan.ubahStatus(p, statusBaru)) {
            muatData();
        } else {
            AlertHelper.peringatan("Tidak Dapat Diubah",
                    "Status \"" + p.getStatus() + "\" tidak bisa langsung menjadi \"" + statusBaru + "\".");
        }
    }
}
