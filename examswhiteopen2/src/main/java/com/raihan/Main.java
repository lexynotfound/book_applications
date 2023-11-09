package com.raihan;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Buku[] daftarBuku = new Buku[5]; // Maksimum 5 buku
    static int jumlahBuku = 0;

    public static void main(String[] args) {
        char pilihan;
        do {
            tampilkanMenu();
            pilihan = Character.toLowerCase(scanner.next().charAt(0));
            scanner.nextLine(); // Membersihkan karakter newline dari buffer

            switch (pilihan) {
                case 's':
                    tampilkanBuku();
                    break;
                case 'a':
                    tambahBuku();
                    break;
                case 'e':
                    ubahBuku();
                    break;
                case 'd':
                    hapusBuku();
                    break;
                case 'x':
                    System.out.println("Terima kasih!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        } while (pilihan != 'X');
    }

    public static void tampilkanMenu() {
        System.out.println("\nMenu Aplikasi");
        System.out.println("======================");
        System.out.println("[S] Tampilkan Buku");
        System.out.println("[A] Tambah Buku");
        System.out.println("[E] Ubah Buku");
        System.out.println("[D] Hapus Buku");
        System.out.println("[X] Keluar");
        System.out.print("\nPilih menu [S / A / E / D / X]: ");
    }

    public static void tampilkanBuku() {
        if (jumlahBuku == 0) {
            System.out.println("\n==========================================================================");
            System.out.println("Belum ada buku yang ditambahkan. Silakan tambahkan buku terlebih dahulu.");
            System.out.println("==========================================================================");
            tungguEnter();
            return;
        }

        System.out.println("\n=====================================================================");
        System.out.println("No    Judul           ISBN           Penerbit      Harga    Halaman");
        System.out.println("\n=====================================================================");
        for (int i = 0; i < jumlahBuku; i++) {
            System.out.printf("%-6d%-16s%-14s%-14s%-9.2f%-10d%n",
                    (i + 1),
                    daftarBuku[i].judul,
                    daftarBuku[i].isbn,
                    daftarBuku[i].penerbit,
                    daftarBuku[i].harga,
                    daftarBuku[i].halaman);
        }
        tungguEnter();
    }

    public static void tambahBuku() {
        if (jumlahBuku >= daftarBuku.length) {
            System.out.println("\nTidak dapat menambahkan lebih banyak buku. Batas maksimum mencapai 5.");
            tungguEnter();
            return;
        }

        Buku buku = new Buku();
        System.out.println("\nMenambahkan Buku:");

        do {
            System.out.print("ISBN: ");
            buku.isbn = scanner.nextLine();
        } while (buku.isbn.trim().isEmpty());

        do {
            System.out.print("Judul: ");
            buku.judul = scanner.nextLine();
        } while (buku.judul.trim().isEmpty());

        do {
            System.out.print("Penerbit: ");
            buku.penerbit = scanner.nextLine();
        } while (buku.penerbit.trim().isEmpty());

        do {
            try {
                System.out.print("Harga: ");
                buku.harga = Double.parseDouble(scanner.nextLine());
                if (buku.harga <= 0) {
                    throw new Exception("Harga harus lebih dari 0.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (buku.harga <= 0);

        do {
            try {
                System.out.print("Jumlah Halaman: ");
                buku.halaman = Integer.parseInt(scanner.nextLine());
                if (buku.halaman <= 0) {
                    throw new Exception("Jumlah halaman harus lebih dari 0.");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (buku.halaman <= 0);

        daftarBuku[jumlahBuku] = buku;
        jumlahBuku++;

        System.out.println("\nBuku berhasil ditambahkan!");
        tungguEnter();
    }

    public static void ubahBuku() {
        if (jumlahBuku == 0) {
            System.out.println("\nBelum ada buku yang ditambahkan. Tidak ada data untuk diubah.");
            tungguEnter();
            return;
        }

        System.out.print("\nMasukkan ISBN buku yang ingin diubah: ");
        String isbnCari = scanner.nextLine();
        int indexDitemukan = -1;

        for (int i = 0; i < jumlahBuku; i++) {
            if (daftarBuku[i].isbn.equals(isbnCari)) {
                indexDitemukan = i;
                break;
            }
        }

        if (indexDitemukan == -1) {
            System.out.println("\nBuku dengan ISBN " + isbnCari + " tidak ditemukan.");
            tungguEnter();
            return;
        }

        Buku buku = daftarBuku[indexDitemukan];

        System.out.println("\nData Buku yang Akan Diubah:");
        System.out.println("Judul: " + buku.judul );
        System.out.println("Penerbit: " + buku.penerbit );
        System.out.println("Harga: " + buku.harga );
        System.out.println("Jumlah Halaman: " + buku.halaman );

        System.out.println("\nPilih data yang ingin diubah:");
        System.out.println("[J] Judul");
        System.out.println("[P] Penerbit");
        System.out.println("[H] Harga");
        System.out.println("[A] Jumlah Halaman");
        System.out.println("[B] Batal");
        System.out.print("\nMasukkan Kode Data [J / P / H / A / B]: ");

        char pilihan = Character.toLowerCase(scanner.next().charAt(0));
        scanner.nextLine(); // Membersihkan karakter newline dari buffer

        switch (pilihan) {
            case 'j':
                System.out.print("Masukkan judul baru: ");
                buku.judul = scanner.nextLine();
                break;
            case 'p':
                System.out.print("Masukkan penerbit baru: ");
                buku.penerbit = scanner.nextLine();
                break;
            case 'h':
                do {
                    try {
                        System.out.print("Masukkan harga baru: ");
                        buku.harga = Double.parseDouble(scanner.nextLine());
                        if (buku.harga <= 0) {
                            throw new Exception("Harga harus lebih dari 0.");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } while (buku.harga <= 0);
                break;
            case 'a':
                do {
                    try {
                        System.out.print("Masukkan jumlah halaman baru: ");
                        buku.halaman = Integer.parseInt(scanner.nextLine());
                        if (buku.halaman <= 0) {
                            throw new Exception("Jumlah halaman harus lebih dari 0.");
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } while (buku.halaman <= 0);
                break;
            case 'b':
                System.out.println("Pengubahan dibatalkan.");
                break;
            default:
                System.out.println("Pilihan tidak valid. Tidak ada data yang diubah.");
        }
        tungguEnter();
    }

    public static void hapusBuku() {
        if (jumlahBuku == 0) {
            System.out.println("\nBelum ada buku yang ditambahkan. Tidak ada data untuk dihapus.");
            tungguEnter();
            return;
        }

        System.out.print("\nMasukkan ISBN buku yang ingin dihapus: ");
        String isbnCari = scanner.nextLine();
        int indexDitemukan = -1;

        for (int i = 0; i < jumlahBuku; i++) {
            if (daftarBuku[i].isbn.equals(isbnCari)) {
                indexDitemukan = i;
                break;
            }
        }

        if (indexDitemukan == -1) {
            System.out.println("\nBuku dengan ISBN " + isbnCari + " tidak ditemukan.");
            tungguEnter();
            return;
        }

        Buku bukuDihapus = daftarBuku[indexDitemukan];

        for (int i = indexDitemukan; i < jumlahBuku - 1; i++) {
            daftarBuku[i] = daftarBuku[i + 1];
        }

        jumlahBuku--;

        System.out.println("\nBuku dengan ISBN " + bukuDihapus.isbn + " berhasil dihapus.");
        tungguEnter();
    }

    public static void tungguEnter() {
        System.out.print("\nTekan Enter untuk kembali ke menu...");
        scanner.nextLine(); // Menunggu Enter
    }
}
