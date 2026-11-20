package fixlokal.controller;

import fixlokal.service.JasaService;
import fixlokal.util.AlertHelper;
import fixlokal.util.AppContext;
import fixlokal.util.UiHelper;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/** Halaman Registrasi (Pelanggan atau Tukang). */
public class RegistrasiController {
    private final TextField txtNama = new TextField();
    private final TextField txtEmail = new TextField();
    private final PasswordField txtPassword = new PasswordField();
    private final TextField txtTelepon = new TextField();
    private final TextField txtAlamat = new TextField();
    private final ComboBox<String> cmbRole = new ComboBox<>();
    private final ComboBox<String> cmbKeahlian = new ComboBox<>();

    public Parent getView() {
        cmbRole.getItems().addAll("Pelanggan", "Tukang");
        cmbRole.setValue("Pelanggan");
        cmbKeahlian.getItems().addAll(JasaService.KATEGORI);
        cmbKeahlian.setValue(JasaService.KATEGORI[0]);

        GridPane form = new GridPane();
        form.setHgap(12);
        form.setVgap(10);
        form.addRow(0, UiHelper.label("Nama lengkap", "field-label"), txtNama);
        form.addRow(1, UiHelper.label("Email", "field-label"), txtEmail);
        form.addRow(2, UiHelper.label("Password", "field-label"), txtPassword);
        form.addRow(3, UiHelper.label("Nomor telepon", "field-label"), txtTelepon);
        form.addRow(4, UiHelper.label("Alamat", "field-label"), txtAlamat);
        form.addRow(5, UiHelper.label("Daftar sebagai", "field-label"), cmbRole);
        form.addRow(6, UiHelper.label("Keahlian (khusus tukang)", "field-label"), cmbKeahlian);

        Button btnDaftar = UiHelper.tombol("Daftar", "btn-primary");
        btnDaftar.setOnAction(e -> prosesRegistrasi());
        Button btnKembali = UiHelper.tombol("Kembali ke Login", "btn-secondary");
        btnKembali.setOnAction(e -> Navigator.tampilkanLogin());

        VBox kartu = new VBox(14, UiHelper.label("Registrasi Akun", "page-title"),
                form, new HBox(10, btnDaftar, btnKembali));
        kartu.getStyleClass().add("card");
        kartu.setPadding(new Insets(30));
        kartu.setMaxSize(520, VBox.USE_PREF_SIZE);

        StackPane root = new StackPane(kartu);
        root.getStyleClass().add("bg-login");
        return root;
    }

    private void prosesRegistrasi() {
        String nama = txtNama.getText().trim();
        String email = txtEmail.getText().trim();
        String password = txtPassword.getText();
        String telepon = txtTelepon.getText().trim();
        String alamat = txtAlamat.getText().trim();

        if (nama.isEmpty() || email.isEmpty() || password.isEmpty() || telepon.isEmpty() || alamat.isEmpty()) {
            AlertHelper.peringatan("Input Kosong", "Semua kolom wajib diisi.");
            return;
        }

        boolean berhasil;
        if (cmbRole.getValue().equals("Tukang")) {
            berhasil = AppContext.auth.registrasiTukang(nama, email, password, telepon, alamat, cmbKeahlian.getValue());
        } else {
            berhasil = AppContext.auth.registrasiPelanggan(nama, email, password, telepon, alamat);
        }

        if (berhasil) {
            AlertHelper.info("Registrasi Berhasil", "Akun berhasil dibuat. Silakan login.");
            Navigator.tampilkanLogin();
        } else {
            AlertHelper.error("Registrasi Gagal", "Email sudah terdaftar.");
        }
    }
}
