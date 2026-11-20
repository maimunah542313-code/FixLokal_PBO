package fixlokal.util;

import java.util.function.Function;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

/** Pembantu membuat komponen JavaFX agar kode controller lebih singkat. */
public final class UiHelper {
    private UiHelper() { }

    public static Button tombol(String teks, String styleClass) {
        Button b = new Button(teks);
        b.getStyleClass().add(styleClass);
        return b;
    }

    public static Label label(String teks, String styleClass) {
        Label l = new Label(teks);
        l.getStyleClass().add(styleClass);
        return l;
    }

    public static String rupiah(double nilai) {
        return String.format("Rp%,.0f", nilai);
    }

    /** Membuat kolom TableView berisi teks hasil fungsi 'isi'. */
    public static <T> TableColumn<T, String> kolom(String judul, Function<T, String> isi) {
        TableColumn<T, String> k = new TableColumn<>(judul);
        k.setCellValueFactory(data -> new SimpleStringProperty(isi.apply(data.getValue())));
        return k;
    }
}
