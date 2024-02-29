---
sidebar_label: Tugas 2
sidebar_position: 2
Path: assignment/individual/assignment-2
---

# Tugas 2: Implementasi *Model-View-Template* (MVT) pada Django

Pemrograman Berbasis Platform (CSGE602022) — diselenggarakan oleh Fakultas Ilmu Komputer Universitas Indonesia, Semester Ganjil 2023/2024

---

## Deskripsi Tugas

Pada tugas ini, kamu akan mengimplementasikan konsep *Model-View-Template* serta beberapa hal yang sudah kamu pelajari di kelas dan tutorial. Perlu diperhatikan bahwa proyek yang dibuat pada tugas **berbeda** dengan proyek yang digunakan pada tutorial.

## Tema Aplikasi

Tema besar aplikasi untuk tugas PBP adalah aplikasi pengelolaan (inventori). Pada semester ini, kamu diberikan kebebasan dalam memilih nama dan tema kecil aplikasi. Namun, aplikasi dari tugas kamu harus memiliki atribut-atribut berikut pada model aplikasimu.

- `name` sebagai nama *item* dengan tipe `CharField`.
- `amount` sebagai jumlah *item* dengan tipe `IntegerField`.
- `description` sebagai deskripsi *item* dengan tipe `TextField`.

Kamu dipersilakan untuk menambahkan atribut lainnya jika diinginkan, seperti `price`, `power`, `category`. Namun, model aplikasi kamu wajib memiliki ketiga atribut wajib di atas (`name`, `amount`, `description`).

Beberapa contoh ide aplikasi pengelolaan yang dapat kamu buat adalah sebagai berikut.

- Pengelolaan stok produk pada toko.
- Inventori karakter dan/atau *item* pada sebuah *game*.
- Pengelolaan koleksi kartu *trading game*.
- Pengelolaan koleksi perpustakaan.

## Checklist Tugas

*Checklist* untuk tugas ini adalah sebagai berikut.

- [ ] Membuat sebuah proyek Django baru.
- [ ] Membuat aplikasi dengan nama `main` pada proyek tersebut.
- [ ] Melakukan *routing* pada proyek agar dapat menjalankan aplikasi `main`.
- [ ] Membuat model pada aplikasi `main` dengan nama `Item` dan memiliki atribut wajib sebagai berikut.
    - `name` sebagai nama *item* dengan tipe `CharField`.
    - `amount` sebagai jumlah *item* dengan tipe `IntegerField`.
    - `description` sebagai deskripsi *item* dengan tipe `TextField`.
- [ ] Membuat sebuah fungsi pada `views.py` untuk dikembalikan ke dalam sebuah *template* HTML yang menampilkan nama aplikasi serta nama dan kelas kamu.
- [ ] Membuat sebuah *routing* pada `urls.py` aplikasi `main` untuk memetakan fungsi yang telah dibuat pada `views.py`.
- [ ] Melakukan *deployment* ke Adaptable terhadap aplikasi yang sudah dibuat sehingga nantinya dapat diakses oleh teman-temanmu melalui Internet.
- [ ] Membuat sebuah `README.md` yang berisi tautan menuju aplikasi Adaptable yang sudah di-*deploy*, serta jawaban dari beberapa pertanyaan berikut.
    - Jelaskan bagaimana cara kamu mengimplementasikan *checklist* di atas secara *step-by-step* (bukan hanya sekadar mengikuti tutorial).
    - Buatlah bagan yang berisi *request client* ke web aplikasi berbasis Django beserta responnya dan jelaskan pada bagan tersebut kaitan antara `urls.py`, `views.py`, `models.py`, dan berkas `html`.
    - Jelaskan mengapa kita menggunakan ***virtual environment***? Apakah kita tetap dapat membuat aplikasi web berbasis Django tanpa menggunakan ***virtual environment***?
    - Jelaskan apakah itu MVC, MVT, MVVM dan perbedaan dari ketiganya.

Perhatikan bahwa kamu harus mengerjakan tugas ini menggunakan repositori yang **berbeda** dengan tutorial, sehingga pastikan kamu **membuat repositori baru** untuk tugas ini.

## Tenggat Waktu Pengerjaan

Tenggat waktu pengerjaan Tugas 2 adalah hari **Rabu, 13 September 2023, pukul 12.00 siang**.

Harap mengumpulkan link repositori yang kamu gunakan ke dalam slot submisi yang telah disediakan di SCELE.

## Bonus

Kamu akan mendapatkan nilai bonus pada penilaian tugas ini apabila kamu berhasil mengimplementasikan dan mendemonstrasikan *testing* dasar **selain *testing* yang diajarkan di tutorial**. Dengan demikian, kamu tidak mendapatkan nilai bonus apabila kamu mengimplementasikan *testing* pengecekan berjalannya URL di aplikasi dan penggunaan *template* yang sesuai.

> Hint: Silakan membaca dokumentasi [ini](https://docs.djangoproject.com/en/4.2/topics/testing/) atau sumber lainnya untuk melihat cara membuat testing di Django.
