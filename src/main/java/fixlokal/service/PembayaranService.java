package fixlokal.service;

import fixlokal.model.Pembayaran;

/** Simulasi pembayaran (tanpa payment gateway). */
public class PembayaranService {

    /** Tunai -> "Menunggu Pembayaran"; Transfer/QRIS -> "Sudah Dibayar". */
    public void konfirmasi(Pembayaran pembayaran, String metode) {
        pembayaran.setMetode(metode);
        if (metode.equals(Pembayaran.TUNAI)) {
            pembayaran.setStatus(Pembayaran.MENUNGGU);
        } else {
            pembayaran.setStatus(Pembayaran.LUNAS);
        }
    }
}
