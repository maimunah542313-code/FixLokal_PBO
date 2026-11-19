import java.util.Scanner;
import service.AuthService;
import service.JasaService;
import service.PemesananService;
import model.Pelanggan;
import model.PenyediaJasa;
import model.Jasa;
import model.Pemesanan;
import util.InputHelper;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        AuthService authService = new AuthService();
        JasaService jasaService = new JasaService();
        PemesananService pemesananService = new PemesananService();

        boolean berjalan = true;
        System.out.println("==============================================");
        System.out.println("                 FIXLOKAL");
        System.out.println("      SISTEM INFORMASI JASA REPARASI");
        System.out.println("==============================================");

        while (berjalan) {
            System.out.println("\n========== MENU UTAMA ==========");
            System.out.println("1. Registrasi pelanggan");
            System.out.println("2. Login pelanggan");
            System.out.println("3. Lihat daftar jasa");
            System.out.println("0. Keluar");
            int pilihan = InputHelper.readInt(scanner, "Pilih menu: ");

            switch (pilihan) {
                case 1:
                    registrasi(authService);
                    break;
                case 2: {
                    Pelanggan pelanggan = login(authService);
                    if (pelanggan != null) {
                        menuPelanggan(pelanggan, jasaService, pemesananService);
                    }
                    break;
                }
                case 3:
                    jasaService.tampilkanSemuaJasa();
                    break;
                case 0:
                    berjalan = false;
                    System.out.println("Terima kasih telah menggunakan FixLokal!");
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia. Silakan coba lagi.");
            }
        }
        scanner.close();
    }

    private static void registrasi(AuthService authService) {
        System.out.println("\n========== REGISTRASI ==========");
        String nama = InputHelper.readNonEmpty(scanner, "Nama lengkap: ");
        String email = InputHelper.readNonEmpty(scanner, "Email: ");
        String password = InputHelper.readNonEmpty(scanner, "Password: ");
        String telepon = InputHelper.readNonEmpty(scanner, "Nomor telepon: ");
        String alamat = InputHelper.readNonEmpty(scanner, "Alamat: ");

        boolean berhasil = authService.registrasi(nama, email, password, telepon, alamat);
        if (berhasil) {
            System.out.println("Registrasi berhasil. Silakan login.");
        } else {
            System.out.println("Email sudah terdaftar. Gunakan email lain.");
        }
    }

    private static Pelanggan login(AuthService authService) {
        System.out.println("\n========== LOGIN PELANGGAN ==========");
        String email = InputHelper.readNonEmpty(scanner, "Email: ");
        String password = InputHelper.readNonEmpty(scanner, "Password: ");
        Pelanggan pelanggan = authService.login(email, password);
        if (pelanggan == null) {
            System.out.println("Login gagal. Email atau password salah.");
        } else {
            System.out.println("Login berhasil. Selamat datang, " + pelanggan.getNama() + "!");
        }
        return pelanggan;
    }

    private static void menuPelanggan(Pelanggan pelanggan, JasaService jasaService,
                                      PemesananService pemesananService) {
        boolean aktif = true;
        while (aktif) {
            System.out.println("\n========== MENU PELANGGAN ==========");
            System.out.println("1. Lihat semua jasa");
            System.out.println("2. Cari jasa berdasarkan kategori");
            System.out.println("3. Lihat detail jasa");
            System.out.println("4. Pesan jasa");
            System.out.println("5. Riwayat pemesanan");
            System.out.println("6. Batalkan pemesanan");
            System.out.println("0. Logout");
            int pilihan = InputHelper.readInt(scanner, "Pilih menu: ");

            switch (pilihan) {
                case 1:
                    jasaService.tampilkanSemuaJasa();
                    break;
                case 2: {
                    String kategori = InputHelper.readNonEmpty(scanner, "Masukkan kategori (Elektronik/Plumbing/AC/Cat): ");
                    jasaService.cariBerdasarkanKategori(kategori);
                    break;
                }
                case 3: {
                    jasaService.tampilkanSemuaJasa();
                    int id = InputHelper.readInt(scanner, "Masukkan ID jasa: ");
                    Jasa jasa = jasaService.cariById(id);
                    if (jasa != null) jasa.tampilkanDetail();
                    else System.out.println("Jasa dengan ID tersebut tidak ditemukan.");
                    break;
                }
                case 4: {
                    jasaService.tampilkanSemuaJasa();
                    int idJasa = InputHelper.readInt(scanner, "Masukkan ID jasa yang ingin dipesan: ");
                    Jasa jasa = jasaService.cariById(idJasa);
                    if (jasa == null) {
                        System.out.println("Jasa tidak ditemukan.");
                        break;
                    }
                    String tanggal = InputHelper.readNonEmpty(scanner, "Tanggal layanan (contoh 20-11-2026): ");
                    String alamat = InputHelper.readNonEmpty(scanner, "Alamat pengerjaan: ");
                    String catatan = InputHelper.readLine(scanner, "Catatan tambahan (boleh kosong): ");
                    Pemesanan pesanan = pemesananService.buatPemesanan(pelanggan, jasa, tanggal, alamat, catatan);
                    System.out.println("\nPemesanan berhasil dibuat!");
                    pesanan.tampilkanDetail();
                    break;
                }
                case 5:
                    pemesananService.tampilkanRiwayat(pelanggan);
                    break;
                case 6: {
                    pemesananService.tampilkanRiwayat(pelanggan);
                    int idPesanan = InputHelper.readInt(scanner, "Masukkan ID pemesanan yang dibatalkan: ");
                    if (pemesananService.batalkanPemesanan(pelanggan, idPesanan)) {
                        System.out.println("Pemesanan berhasil dibatalkan.");
                    } else {
                        System.out.println("Pemesanan tidak ditemukan atau sudah tidak dapat dibatalkan.");
                    }
                    break;
                }
                case 0:
                    aktif = false;
                    System.out.println("Anda telah logout.");
                    break;
                default:
                    System.out.println("Pilihan tidak tersedia.");
            }
        }
    }
}
