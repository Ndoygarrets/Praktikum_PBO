import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

// Interface
interface Diskon {
    int hitungDiskon(); // menghitung potongan harga
    String infoDiskon(); // deskripsi diskon
}

// Abstract Parent class
abstract class Barang {
    private String nama;
    private int harga;
    private int stok;

    public Barang(String nama, int harga, int stok) {
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

    public abstract String getInfoTambahan(); // Abstract method

    @Override
    public String toString() {
        return "Nama: " + nama + ", Harga: " + harga + ", Stok: " + stok + ", " + getInfoTambahan();
    }
}

// Subclass Makanan
class Makanan extends Barang implements Diskon {
    private String tanggalKadaluarsa;

    public Makanan(String nama, int harga, int stok, String tanggalKadaluarsa) {
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
    public String getInfoTambahan() {
        return "Tanggal Kadaluarsa: " + tanggalKadaluarsa + ", " + infoDiskon();
    }

    @Override
    public int hitungDiskon() {
        return (int)(getHarga() * 0.1); // diskon 10%
    }

    @Override
    public String infoDiskon() {
        return "Diskon: " + hitungDiskon();
    }
}

// Subclass Minuman
class Minuman extends Barang implements Diskon {
    private boolean isDingin;

    public Minuman(String nama, int harga, int stok, boolean isDingin) {
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
    public String getInfoTambahan() {
        return "Dingin: " + (isDingin ? "Ya" : "Tidak") + ", " + infoDiskon();
    }

    @Override
    public int hitungDiskon() {
        return (int)(getHarga() * 0.05); // diskon 5%
    }

    @Override
    public String infoDiskon() {
        return "Diskon: " + hitungDiskon();
    }
}

// Final class utama
public final class POSTTEST6 {
    private static final ArrayList<Barang> barangList = new ArrayList<>();
    private static final Scanner scanner = new Scanner(System.in);
    private static int totalBarang = 0; // static variable

    public static void main(String[] args) {
        int pilihan = 0;
        do {
            try {
                System.out.println("\n=== Indomaret Bengkuring ===");
                System.out.println("1. Tambah Barang");
                System.out.println("2. Lihat Barang");
                System.out.println("3. Update Barang");
                System.out.println("4. Hapus Barang");
                System.out.println("5. Total Barang");
                System.out.println("6. Keluar");
                System.out.print("Pilih menu: ");
                pilihan = scanner.nextInt();
                scanner.nextLine();

                switch (pilihan) {
                    case 1 -> tambahBarang();
                    case 2 -> lihatBarang();
                    case 3 -> updateBarang();
                    case 4 -> hapusBarang();
                    case 5 -> tampilkanTotalBarang();
                    case 6 -> System.out.println("Terima kasih!");
                    default -> System.out.println("Pilihan tidak valid.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input harus berupa angka!");
                scanner.nextLine(); // clear buffer
            }
        } while (pilihan != 6);
    }

    private static void tambahBarang() {
        try {
            System.out.println("1. Makanan\n2. Minuman");
            System.out.print("Pilih jenis barang: ");
            int jenis = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Nama: ");
            String nama = scanner.nextLine();
            System.out.print("Harga: ");
            int harga = scanner.nextInt();
            System.out.print("Stok: ");
            int stok = scanner.nextInt();
            scanner.nextLine();

            if (jenis == 1) {
                System.out.print("Tanggal Kadaluarsa (YYYY-MM-DD): ");
                String tgl = scanner.nextLine();
                barangList.add(new Makanan(nama, harga, stok, tgl));
            } else if (jenis == 2) {
                System.out.print("Apakah dingin? (true/false): ");
                boolean dingin = scanner.nextBoolean();
                barangList.add(new Minuman(nama, harga, stok, dingin));
            } else {
                System.out.println("Jenis tidak valid.");
                return;
            }

            totalBarang++; // tambah barang
            System.out.println("Barang berhasil ditambahkan.");
        } catch (InputMismatchException e) {
            System.out.println("Input tidak sesuai. Tambah barang dibatalkan.");
            scanner.nextLine();
        }
    }

    private static final void lihatBarang() {
        if (barangList.isEmpty()) {
            System.out.println("Belum ada barang.");
        } else {
            for (int i = 0; i < barangList.size(); i++) {
                System.out.println((i + 1) + ". " + barangList.get(i));
            }
        }
    }

    private static void updateBarang() {
        lihatBarang();
        if (!barangList.isEmpty()) {
            try {
                System.out.print("Pilih nomor barang: ");
                int index = scanner.nextInt() - 1;
                scanner.nextLine();
                if (index >= 0 && index < barangList.size()) {
                    Barang barang = barangList.get(index);

                    System.out.print("Nama baru: ");
                    String nama = scanner.nextLine();
                    System.out.print("Harga baru: ");
                    int harga = scanner.nextInt();
                    System.out.print("Stok baru: ");
                    int stok = scanner.nextInt();
                    scanner.nextLine();

                    barang.setNama(nama);
                    barang.setHarga(harga);
                    barang.setStok(stok);

                    if (barang instanceof Makanan makanan) {
                        System.out.print("Tanggal kadaluarsa baru: ");
                        makanan.setTanggalKadaluarsa(scanner.nextLine());
                    } else if (barang instanceof Minuman minuman) {
                        System.out.print("Apakah dingin? (true/false): ");
                        minuman.setDingin(scanner.nextBoolean());
                        scanner.nextLine();
                    }

                    System.out.println("Barang berhasil diperbarui.");
                } else {
                    System.out.println("Nomor barang tidak valid.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input tidak valid.");
                scanner.nextLine();
            }
        }
    }

    private static void hapusBarang() {
        lihatBarang();
        if (!barangList.isEmpty()) {
            try {
                System.out.print("Pilih nomor barang: ");
                int index = scanner.nextInt() - 1;
                scanner.nextLine();

                if (index >= 0 && index < barangList.size()) {
                    barangList.remove(index);
                    totalBarang--; // kurangi total barang
                    System.out.println("Barang berhasil dihapus.");
                } else {
                    System.out.println("Nomor tidak valid.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Input salah.");
                scanner.nextLine();
            }
        }
    }

    public static void tampilkanTotalBarang() {
        System.out.println("Total barang yang tersedia: " + totalBarang);
    }
}
