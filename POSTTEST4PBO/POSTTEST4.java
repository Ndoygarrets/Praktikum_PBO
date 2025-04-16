import java.util.ArrayList;
import java.util.Scanner;

// Parent class
class Barang {
    private String nama;
    private int harga;
    private int stok;

    Barang(String nama, int harga, int stok) {
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public int getStok() {
        return stok;
    }

    public void setStok(int stok) {
        this.stok = stok;
    }

    @Override
    public String toString() {
        return "Nama: " + nama + ", Harga: " + harga + ", Stok: " + stok;
    }
}

// Subclass 1
class Makanan extends Barang {
    private String tanggalKadaluarsa;

    Makanan(String nama, int harga, int stok, String tanggalKadaluarsa) {
        super(nama, harga, stok);
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }

    public String getTanggalKadaluarsa() {
        return tanggalKadaluarsa;
    }

    public void setTanggalKadaluarsa(String tanggalKadaluarsa) {
        this.tanggalKadaluarsa = tanggalKadaluarsa;
    }

    @Override
    public String toString() {
        return super.toString() + ", Tanggal Kadaluarsa: " + tanggalKadaluarsa;
    }
}

// Subclass 2
class Minuman extends Barang {
    private boolean isDingin;

    Minuman(String nama, int harga, int stok, boolean isDingin) {
        super(nama, harga, stok);
        this.isDingin = isDingin;
    }

    public boolean isDingin() {
        return isDingin;
    }

    public void setDingin(boolean dingin) {
        isDingin = dingin;
    }

    @Override
    public String toString() {
        return super.toString() + ", Dingin: " + (isDingin ? "Ya" : "Tidak");
    }
}

public class POSTTEST4 {
    private static ArrayList<Barang> barangList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int pilihan;
        do {
            System.out.println("Sistem Penjualan Sembilan Bahan Pokok Indomaret Bengkuring");
            System.out.println("1. Tambah Barang");
            System.out.println("2. Lihat Barang");
            System.out.println("3. Update Barang");
            System.out.println("4. Hapus Barang");
            System.out.println("5. Keluar");
            System.out.print("Pilih menu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (pilihan) {
                case 1:
                    tambahBarang();
                    break;
                case 2:
                    lihatBarang();
                    break;
                case 3:
                    updateBarang();
                    break;
                case 4:
                    hapusBarang();
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan program ini.");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 5);
    }

    private static void tambahBarang() {
        System.out.println("Pilih jenis barang:");
        System.out.println("1. Makanan");
        System.out.println("2. Minuman");
        System.out.print("Pilihan: ");
        int jenis = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Masukkan nama barang: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan harga barang: ");
        int harga = scanner.nextInt();
        System.out.print("Masukkan stok barang: ");
        int stok = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (jenis == 1) {
            System.out.print("Masukkan tanggal kadaluarsa (YYYY-MM-DD): ");
            String tanggalKadaluarsa = scanner.nextLine();
            barangList.add(new Makanan(nama, harga, stok, tanggalKadaluarsa));
        } else if (jenis == 2) {
            System.out.print("Apakah minuman dingin? (true/false): ");
            boolean isDingin = scanner.nextBoolean();
            scanner.nextLine(); // Consume newline
            barangList.add(new Minuman(nama, harga, stok, isDingin));
        } else {
            System.out.println("Jenis barang tidak valid.");
        }

        System.out.println("Barang berhasil ditambahkan.");
    }

    private static void lihatBarang() {
        if (barangList.isEmpty()) {
            System.out.println("Tidak ada barang.");
        } else {
            for (int i = 0; i < barangList.size(); i++) {
                System.out.println((i + 1) + ". " + barangList.get(i));
            }
        }
    }

    private static void updateBarang() {
        lihatBarang();
        if (!barangList.isEmpty()) {
            System.out.print("Pilih nomor barang yang akan diupdate: ");
            int index = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume newline

            if (index >= 0 && index < barangList.size()) {
                Barang barang = barangList.get(index);

                System.out.print("Masukkan nama barang baru: ");
                String nama = scanner.nextLine();
                System.out.print("Masukkan harga barang baru: ");
                int harga = scanner.nextInt();
                System.out.print("Masukkan stok barang baru: ");
                int stok = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                barang.setNama(nama);
                barang.setHarga(harga);
                barang.setStok(stok);

                if (barang instanceof Makanan) {
                    System.out.print("Masukkan tanggal kadaluarsa baru (YYYY-MM-DD): ");
                    String tanggalKadaluarsa = scanner.nextLine();
                    ((Makanan) barang).setTanggalKadaluarsa(tanggalKadaluarsa);
                } else if (barang instanceof Minuman) {
                    System.out.print("Apakah minuman dingin? (true/false): ");
                }
            } else {
                System.out.println("Nomor barang tidak valid.");
            }
        }
    }

    private static void hapusBarang() {
        lihatBarang();
        if (!barangList.isEmpty()) {
            System.out.print("Pilih nomor barang yang akan dihapus: ");
            int index = scanner.nextInt() - 1;
            scanner.nextLine(); // Consume newline

            if (index >= 0 && index < barangList.size()) {
                barangList.remove(index);
                System.out.println("Barang berhasil dihapus.");
            } else {
                System.out.println("Nomor barang tidak valid.");
            }
        }
    }
}               
