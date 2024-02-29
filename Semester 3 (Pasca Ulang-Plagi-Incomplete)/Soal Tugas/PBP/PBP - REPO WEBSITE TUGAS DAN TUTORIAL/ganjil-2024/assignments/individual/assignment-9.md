---
sidebar_label: Tugas 9
sidebar_position: 9
Path: assignment/individual/assignment-9
---

# Tugas 9: Integrasi Layanan Web Django dengan Aplikasi Flutter

Pemrograman Berbasis Platform (CSGE602022) — diselenggarakan oleh Fakultas Ilmu Komputer Universitas Indonesia, Semester Ganjil 2023/2024

---

## Deskripsi Tugas

Pada tugas ini, kamu akan mengintegrasikan layanan Django yang sudah kamu buat pada tugas-tugas sebelumnya dengan aplikasi Flutter yang sudah kamu buat sebelumnya.

*Checklist* untuk tugas ini adalah sebagai berikut:

- [ ] Memastikan *deployment* proyek tugas Django kamu telah berjalan dengan baik.
- [ ] Membuat halaman login pada proyek tugas Flutter.
- [ ] Mengintegrasikan sistem autentikasi Django dengan proyek tugas Flutter.
- [ ] Membuat model kustom sesuai dengan proyek aplikasi Django.
- [ ] Membuat halaman yang berisi daftar semua item yang terdapat pada *endpoint* `JSON` di Django yang telah kamu *deploy*.
    - [ ] Tampilkan *name*, *amount*, dan *description* dari masing-masing item pada halaman ini.
- [ ] Membuat halaman detail untuk setiap item yang terdapat pada halaman daftar Item.
    - [ ] Halaman ini dapat diakses dengan menekan salah satu item pada halaman daftar Item.
    - [ ] Tampilkan seluruh atribut pada model item kamu pada halaman ini.
    - [ ] Tambahkan tombol untuk kembali ke halaman daftar item.
- [ ] Menjawab beberapa pertanyaan berikut pada `README.md` pada *root folder* (silakan modifikasi `README.md` yang telah kamu buat sebelumnya; tambahkan subjudul untuk setiap tugas).
    - [ ] Apakah bisa kita melakukan pengambilan data JSON tanpa membuat model terlebih dahulu? Jika iya, apakah hal tersebut lebih baik daripada membuat model sebelum melakukan pengambilan data JSON?
    - [ ] Jelaskan fungsi dari CookieRequest dan jelaskan mengapa *instance* CookieRequest perlu untuk dibagikan ke semua komponen di aplikasi Flutter.
    - [ ] Jelaskan mekanisme pengambilan data dari JSON hingga dapat ditampilkan pada Flutter.
    - [ ] Jelaskan mekanisme autentikasi dari input data akun pada Flutter ke Django hingga selesainya proses autentikasi oleh Django dan tampilnya menu pada Flutter.
    - [ ] Sebutkan seluruh *widget* yang kamu pakai pada tugas ini dan jelaskan fungsinya masing-masing.
    - [ ] Jelaskan bagaimana cara kamu mengimplementasikan *checklist* di atas secara *step-by-step*! (bukan hanya sekadar mengikuti tutorial).
- [ ] Melakukan `add`-`commit`-`push` ke GitHub.

## Tenggat Waktu Pengerjaan

Tenggat waktu pengerjaan Tugas 9 adalah **Rabu, 22 November 2023, pukul 12.00 siang**.

Asisten dosen akan mengecek *last commit* dari repositori tugas lab, sehingga kamu tidak perlu mengumpulkan tautan repositori ke dalam slot submisi.

## Bonus

Kamu akan mendapatkan nilai bonus pada penilaian tugas ini apabila kamu membuat fitur-fitur berikut.

- [ ] Mengimplementasikan fitur registrasi akun pada aplikasi Flutter.
- [ ] Melakukan filter pada halaman daftar item dengan hanya menampilkan item yang terasosiasi dengan pengguna yang login.
