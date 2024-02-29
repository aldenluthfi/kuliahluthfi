---
sidebar_label: Tugas 8
sidebar_position: 8
Path: assignment/individual/assignment-8
---

# Tugas 8: Flutter Navigation, Layouts, Forms, and Input Elements

Pemrograman Berbasis Platform (CSGE602022) — diselenggarakan oleh Fakultas Ilmu Komputer Universitas Indonesia, Semester Ganjil 2023/2024

---

## Deskripsi Tugas

Pada tugas ini, kamu akan mengimplementasikan *navigation*, *layout*, *form*, dan *form input elements* pada aplikasi Flutter yang kamu buat pada tugas sebelumnya.

*Checklist* untuk tugas ini adalah sebagai berikut:

- [ ] Membuat minimal satu halaman baru pada aplikasi, yaitu halaman formulir tambah item baru dengan ketentuan sebagai berikut:
    - [ ] Memakai minimal tiga elemen input, yaitu `name`, `amount`, `description`. Tambahkan elemen input sesuai dengan model pada aplikasi tugas Django yang telah kamu buat.
    - [ ] Memiliki sebuah tombol `Save`.
    - [ ] Setiap elemen input di formulir juga harus divalidasi dengan ketentuan sebagai berikut:
        - [ ] Setiap elemen input tidak boleh kosong.
        - [ ] Setiap elemen input harus berisi data dengan tipe data atribut modelnya.
- [ ] Mengarahkan pengguna ke halaman form tambah item baru ketika menekan tombol `Tambah Item` pada halaman utama.
- [ ] Memunculkan data sesuai isi dari formulir yang diisi dalam sebuah `pop-up` setelah menekan tombol `Save` pada halaman formulir tambah item baru.
- [ ] Membuat sebuah drawer pada aplikasi dengan ketentuan sebagai berikut:
    - [ ] Drawer minimal memiliki dua buah opsi, yaitu `Halaman Utama` dan `Tambah Item`.
    - [ ] Ketika memiih opsi `Halaman Utama`, maka aplikasi akan mengarahkan pengguna ke halaman utama.
    - [ ] Ketika memiih opsi (`Tambah Item`), maka aplikasi akan mengarahkan pengguna ke halaman form tambah item baru.
- [ ] Menjawab beberapa pertanyaan berikut pada `README.md` pada *root folder* (silakan modifikasi `README.md` yang telah kamu buat sebelumnya; tambahkan subjudul untuk setiap tugas).
    - [ ] Jelaskan perbedaan antara `Navigator.push()` dan `Navigator.pushReplacement()`, disertai dengan contoh mengenai penggunaan kedua metode tersebut yang tepat!
    - [ ] Jelaskan masing-masing *layout* widget pada Flutter dan konteks penggunaannya masing-masing!
    - [ ] Sebutkan apa saja elemen input pada form yang kamu pakai pada tugas kali ini dan jelaskan mengapa kamu menggunakan elemen input tersebut!
    - [ ] Bagaimana penerapan *clean architecture* pada aplikasi Flutter?
    - [ ] Jelaskan bagaimana cara kamu mengimplementasikan *checklist* di atas secara *step-by-step*! (bukan hanya sekadar mengikuti tutorial)
- [ ] Melakukan `add`-`commit`-`push` ke GitHub.

## Tenggat Waktu Pengerjaan

Tenggat waktu pengerjaan Tugas 8 adalah **Rabu, 15 November 2023, pukul 12.00 siang**.

Asisten dosen akan mengecek *last commit* dari repositori tugas lab, sehingga kamu tidak perlu mengumpulkan tautan repositori ke dalam slot submisi.

## Bonus

Kamu akan mendapatkan nilai bonus pada penilaian tugas ini apabila kamu membuat fitur-fitur berikut.

- [ ] Membuat sebuah halaman baru, yaitu halaman daftar item yang sudah dibuat dengan isi halamannya adalah setiap data produk yang sudah pernah dibuat.

    > Kamu dapat memanfaatkan objek model untuk mengerjakan fitur ini.

- [ ] Mengarahkan pengguna ke halaman tersebut jika menekan tombol `Lihat Produk` pada halaman utama atau drawer.
