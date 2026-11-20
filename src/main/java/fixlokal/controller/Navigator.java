package fixlokal.controller;

import fixlokal.model.Jasa;
import fixlokal.model.Pemesanan;
import fixlokal.util.AppContext;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/** Mengatur perpindahan halaman dengan mengganti root Scene. */
public final class Navigator {
    private static Scene scene;

    private Navigator() { }

    public static void init(Stage stage) {
        scene = new Scene(new StackPane(), 1000, 650);
        scene.getStylesheets().add(Navigator.class.getResource("/css/style.css").toExternalForm());
        stage.setTitle("FixLokal");
        stage.setScene(scene);
    }

    private static void tampil(Parent halaman) { scene.setRoot(halaman); }

    public static void tampilkanLogin() { tampil(new LoginController().getView()); }
    public static void tampilkanRegistrasi() { tampil(new RegistrasiController().getView()); }
    public static void tampilkanDashboardPelanggan() { tampil(new PelangganController(false).getView()); }
    public static void tampilkanRiwayat() { tampil(new PelangganController(true).getView()); }
    public static void tampilkanDashboardTukang() { tampil(new TukangController().getView()); }
    public static void tampilkanPemesanan(Jasa jasa) { tampil(new PemesananController(jasa).getView()); }
    public static void tampilkanPembayaran(Pemesanan p) { tampil(new PembayaranController(p).getView()); }

    public static void logout() {
        AppContext.penggunaAktif = null;
        tampilkanLogin();
    }
}
