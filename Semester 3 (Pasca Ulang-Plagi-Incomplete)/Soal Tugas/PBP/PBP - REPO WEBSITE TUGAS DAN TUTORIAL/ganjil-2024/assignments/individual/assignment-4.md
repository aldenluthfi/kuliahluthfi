---
sidebar_label: Tugas 4
sidebar_position: 4
Path: assignment/individual/assignment-4
---

# Tugas 4: Implementasi Autentikasi, Session, dan Cookies pada Django

Pemrograman Berbasis Platform (CSGE602022) — diselenggarakan oleh Fakultas Ilmu Komputer Universitas Indonesia, Semester Ganjil 2023/2024

---

## Deskripsi Tugas

Pada tugas ini, kamu akan mengimplementasikan konsep *authentication*, *session*, *cookies*, serta menerapkan beberapa konsep yang telah dipelajari selama sesi tutorial.

*Checklist* untuk tugas ini adalah sebagai berikut:

- [ ] Mengimplementasikan fungsi registrasi, login, dan logout untuk memungkinkan pengguna untuk mengakses aplikasi sebelumnya dengan lancar.
- [ ] Membuat **dua** akun pengguna dengan masing-masing **tiga** *dummy data* menggunakan model yang telah dibuat pada aplikasi sebelumnya untuk setiap akun **di lokal**.
- [ ] Menghubungkan model `Item` dengan `User`.
- [ ] Menampilkan detail informasi pengguna yang sedang *logged in* seperti *username* dan menerapkan `cookies` seperti `last login` pada halaman utama aplikasi.
- [ ] Menjawab beberapa pertanyaan berikut pada `README.md` pada *root folder* (silakan modifikasi `README.md` yang telah kamu buat sebelumnya; tambahkan subjudul untuk setiap tugas).

    - [ ] Apa itu Django `UserCreationForm`, dan jelaskan apa kelebihan dan kekurangannya?
    - [ ] Apa perbedaan antara autentikasi dan otorisasi dalam konteks Django, dan mengapa keduanya penting?
    - [ ] Apa itu *cookies* dalam konteks aplikasi web, dan bagaimana Django menggunakan *cookies* untuk mengelola data sesi pengguna?
    - [ ] Apakah penggunaan *cookies* aman secara *default* dalam pengembangan web, atau apakah ada risiko potensial yang harus diwaspadai?
    - [ ] Jelaskan bagaimana cara kamu mengimplementasikan *checklist* di atas secara *step-by-step* (bukan hanya sekadar mengikuti tutorial).
- [ ] Melakukan `add`-`commit`-`push` ke GitHub.

## Tenggat Waktu Pengerjaan

Tenggat waktu pengerjaan Tugas 4 adalah **Rabu, 27 September 2023, pukul 12.00 siang**.

Asisten dosen akan mengecek *last commit* dari repositori tugas lab, sehingga kamu tidak perlu mengumpulkan tautan repositori ke dalam slot submisi.

## Bonus

Kamu akan mendapatkan nilai bonus pada penilaian tugas ini apabila kamu membuat fitur berikut.

- [ ] Tambahkan tombol dan fungsi untuk menambahkan `amount` suatu objek sebanyak satu dan tombol untuk mengurangi jumlah stok suatu objek sebanyak satu.
- [ ] Tambahkan tombol dan fungsi untuk menghapus suatu objek dari inventori.

Kedua fitur di atas wajib diimplementaskan (bukan sekedar tombol, melainkan harus dapat melakukan fungsi yang diinginkan) jika kamu ingin mendapatkan nilai bonus.
