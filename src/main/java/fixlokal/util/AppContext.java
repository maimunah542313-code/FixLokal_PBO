package fixlokal.util;

import fixlokal.model.Pengguna;
import fixlokal.service.AuthService;
import fixlokal.service.JasaService;
import fixlokal.service.PembayaranService;
import fixlokal.service.PemesananService;

/** Menyimpan object service yang dipakai bersama dan pengguna yang sedang login. */
public final class AppContext {
    public static final AuthService auth = new AuthService();
    public static final JasaService jasa = new JasaService(auth);
    public static final PemesananService pemesanan = new PemesananService();
    public static final PembayaranService pembayaran = new PembayaranService();

    public static Pengguna penggunaAktif;

    private AppContext() { }
}
