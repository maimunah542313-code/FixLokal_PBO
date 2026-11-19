FIXLOKAL - SISTEM INFORMASI PENCARI JASA TUKANG DAN REPARASI HARIAN
===================================================================

KEBUTUHAN
- Java JDK 8 atau lebih baru.
- IDE (misalnya VS Code, IntelliJ IDEA, atau NetBeans) atau terminal.

STRUKTUR
FixLokal/
  Main.java
  model/       -> class data dan objek
  service/     -> logika autentikasi, jasa, dan pemesanan
  util/        -> bantuan input Scanner

CARA MENJALANKAN DARI TERMINAL IDE
1. Buka folder FixLokal di IDE.
2. Buka terminal pada folder utama FixLokal (folder yang berisi Main.java).
3. Kompilasi:
   javac -encoding UTF-8 -d out Main.java model/*.java service/*.java util/*.java
4. Jalankan:
   java -cp out Main

AKUN DEMO
Email    : andi@gmail.com
Password : andi123

FITUR
- Registrasi pelanggan
- Login pelanggan
- Menampilkan semua jasa
- Pencarian jasa berdasarkan kategori
- Melihat detail jasa
- Membuat pemesanan
- Melihat riwayat pemesanan
- Membatalkan pemesanan
- Logout dan keluar

MATERI YANG DIIMPLEMENTASIKAN
1. Scanner dan Control Flow:
   Scanner digunakan melalui InputHelper untuk membaca input.
   if/else, switch, for, dan while digunakan di berbagai bagian program.
2. Class dan Object:
   Main membuat object AuthService, JasaService, dan PemesananService.
   Program juga membuat object Pelanggan, PenyediaJasa, Jasa, dan Pemesanan.
3. Encapsulation:
   Atribut pada class model menggunakan private serta getter/setter.
4. Inheritance:
   Pengguna adalah superclass; Pelanggan dan PenyediaJasa adalah subclass.
   Keduanya menggunakan extends Pengguna dan mengimplementasikan getPeran().

CATATAN
- Data disimpan sementara di memori (ArrayList). Data akan kembali ke data awal
  ketika program ditutup. Program ini belum menggunakan database.
- Akun demo disediakan agar alur login dapat langsung dicoba.
- Ini adalah simulasi pemesanan; belum ada pembayaran online atau koneksi
  dengan penyedia jasa sungguhan.
