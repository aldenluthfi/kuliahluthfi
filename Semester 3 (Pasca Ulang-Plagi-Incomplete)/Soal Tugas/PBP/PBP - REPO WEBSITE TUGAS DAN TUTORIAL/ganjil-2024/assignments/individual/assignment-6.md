---
sidebar_label: Tugas 6
sidebar_position: 6
Path: assignment/individual/assignment-6
---

# Tugas 6: JavaScript dan Asynchronous JavaScript

Pemrograman Berbasis Platform (CSGE602022) — diselenggarakan oleh Fakultas Ilmu Komputer Universitas Indonesia, Semester Ganjil 2023/2024

---

## Deskripsi Tugas

Pada tugas ini, kamu akan mengimplementasikan AJAX pada aplikasi yang telah kamu buat pada tugas sebelumnya.

*Checklist* untuk tugas ini adalah sebagai berikut:

- [ ] Mengubah tugas 5 yang telah dibuat sebelumnya menjadi menggunakan AJAX.
  - [ ] AJAX GET
    - [ ] Ubahlah kode _cards_ data item agar dapat mendukung AJAX GET.
    - [ ] Lakukan pengambilan item menggunakan AJAX GET.
  - [ ] AJAX POST
    - [ ] Buatlah sebuah tombol yang membuka sebuah modal dengan form untuk menambahkan item.

        > Modal di-*trigger* dengan menekan suatu tombol pada halaman utama. Saat penambahan item berhasil, modal harus ditutup dan input form harus dibersihkan dari data yang sudah dimasukkan ke dalam form sebelumnya.

    - [ ] Buatlah fungsi *view* baru untuk menambahkan item baru ke dalam basis data.
    - [ ] Buatlah *path* `/create-ajax/` yang mengarah ke fungsi *view* yang baru kamu buat.
    - [ ] Hubungkan form yang telah kamu buat di dalam modal kamu ke *path* `/create-ajax/`.
    - [ ] Lakukan *refresh* pada halaman utama secara asinkronus untuk menampilkan daftar item terbaru tanpa *reload* halaman utama secara keseluruhan.
  - [ ] Melakukan perintah `collectstatic`.
    - Perintah ini bertujuan untuk mengumpulkan *file static* dari setiap aplikasi kamu ke dalam suatu *folder* yang dapat dengan mudah disajikan pada produksi.

- [ ] Menjawab beberapa pertanyaan berikut pada `README.md` pada *root folder* (silakan modifikasi `README.md` yang telah kamu buat sebelumnya; tambahkan subjudul untuk setiap tugas).
    - Jelaskan perbedaan antara *asynchronous programming* dengan *synchronous programming*.
    - Dalam penerapan JavaScript dan AJAX, terdapat penerapan paradigma *event-driven programming*. Jelaskan maksud dari paradigma tersebut dan sebutkan salah satu contoh penerapannya pada tugas ini.
    - Jelaskan penerapan *asynchronous programming* pada AJAX.
    - Pada PBP kali ini, penerapan AJAX dilakukan dengan menggunakan Fetch API daripada *library* jQuery. Bandingkanlah kedua teknologi tersebut dan tuliskan pendapat kamu teknologi manakah yang lebih baik untuk digunakan.
    - Jelaskan bagaimana cara kamu mengimplementasikan *checklist* di atas secara *step-by-step* (bukan hanya sekadar mengikuti tutorial).

- [ ] Melakukan `add`-`commit`-`push` ke GitHub.

- [ ] Melakukan *deployment* ke PaaS PBP Fasilkom UI dan sertakan tautan aplikasi pada file `README.md`.
    - DOKKU_APP_NAME = `UsernameSSO-tugas`

      > Ubah juga tanda titik menjadi tanda strip. Contoh: `muhammad-iqbal111-tugas`.

## Tenggat Waktu Pengerjaan

Tenggat waktu pengerjaan Tugas 6 adalah **Jumat, 13 Oktober 2023, pukul 12.00 siang**.

Asisten dosen akan mengecek *last commit* dari repositori tugas lab, sehingga kamu tidak perlu mengumpulkan tautan repositori ke dalam slot submisi.

## Bonus

Kamu akan mendapatkan nilai bonus pada penilaian tugas ini apabila kamu membuat fitur berikut.

- [ ] Menambahkan fungsionalitas hapus dengan menggunakan AJAX DELETE
