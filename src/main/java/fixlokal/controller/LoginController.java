package fixlokal.controller;

import fixlokal.model.Pengguna;
import fixlokal.util.AlertHelper;
import fixlokal.util.AppContext;
import fixlokal.util.UiHelper;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/** Halaman Login. */
public class LoginController {
    private final TextField txtEmail = new TextField();
    private final PasswordField txtPassword = new PasswordField();
    private final ComboBox<String> cmbRole = new ComboBox<>();

    public Parent getView() {
        cmbRole.getItems().addAll("Pelanggan", "Tukang");
        cmbRole.setValue("Pelanggan");
        cmbRole.setMaxWidth(Double.MAX_VALUE);

        Button btnLogin = UiHelper.tombol("Login", "btn-primary");
        btnLogin.setMaxWidth(Double.MAX_VALUE);
        btnLogin.setOnAction(e -> prosesLogin());

        Button btnDaftar = UiHelper.tombol("Belum punya akun? Daftar", "btn-secondary");
        btnDaftar.setMaxWidth(Double.MAX_VALUE);
        btnDaftar.setOnAction(e -> Navigator.tampilkanRegistrasi());

        VBox kartu = new VBox(10,
                UiHelper.label("FixLokal", "title-app"),
                UiHelper.label("Cari tukang dan reparasi harian", "subtitle"),
                UiHelper.label("Email", "field-label"), txtEmail,
                UiHelper.label("Password", "field-label"), txtPassword,
                UiHelper.label("Masuk sebagai", "field-label"), cmbRole,
                btnLogin, btnDaftar);
        kartu.getStyleClass().add("card");
        kartu.setPadding(new Insets(30));
        kartu.setMaxSize(360, VBox.USE_PREF_SIZE);

        StackPane root = new StackPane(kartu);
        root.getStyleClass().add("bg-login");
        return root;
    }

    private void prosesLogin() {
        String email = txtEmail.getText().trim();
        String password = txtPassword.getText();
        if (email.isEmpty() || password.isEmpty()) {
            AlertHelper.peringatan("Input Kosong", "Email dan password wajib diisi.");
            return;
        }

        String role = cmbRole.getValue();
        Pengguna pengguna = AppContext.auth.login(email, password, role);
        if (pengguna == null) {
            AlertHelper.error("Login Gagal", "Email, password, atau peran salah.");
            return;
        }

        AppContext.penggunaAktif = pengguna;
        AlertHelper.info("Login Berhasil", "Selamat datang, " + pengguna.getNama() + "!");
        if (role.equals("Pelanggan")) {
            Navigator.tampilkanDashboardPelanggan();
        } else {
            Navigator.tampilkanDashboardTukang();
        }
    }
}
