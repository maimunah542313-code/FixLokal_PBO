package fixlokal.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public final class AlertHelper {
    private AlertHelper() { }

    private static void tampil(AlertType tipe, String judul, String pesan) {
        Alert alert = new Alert(tipe);
        alert.setTitle(judul);
        alert.setHeaderText(null);
        alert.setContentText(pesan);
        alert.showAndWait();
    }

    public static void info(String judul, String pesan) { tampil(AlertType.INFORMATION, judul, pesan); }
    public static void peringatan(String judul, String pesan) { tampil(AlertType.WARNING, judul, pesan); }
    public static void error(String judul, String pesan) { tampil(AlertType.ERROR, judul, pesan); }
}
