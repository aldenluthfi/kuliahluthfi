# Tugas Pemrograman DDP2 2022/2023

CSGE601021 Dasar-Dasar Pemrograman 2 @ Fakultas Ilmu Komputer Universitas Indonesia,
Semester Genap 2022/2023

## Friendly Notes

* Kalau kamu bingung, jangan sungkan untuk bertanya pada asdosmu ya ✌
* Jangan deadliner teman-teman!

## Daftar Isi

1. Soal Tugas Pemrograman
   * [Tugas Pemrograman 1][tp1]
   * [Tugas Pemrograman 2][tp2]
   * [Tugas Pemrograman 3][tp3]
   * [Tugas Pemrograman 4][tp4]
2. [Latihan Tugas Pemrograman](#latihan-tugas-pemrograman)
3. [Memulai](#memulai)
4. [Mengerjakan Tugas Pemrograman](#mengerjakan-tugas-pemrograman)
5. [Mendapatkan pembaruan dari *upstream*](#mendapatkan-pembaruan-dari-upstream)
6. [Menyelesaikan konflik](#menyelesaikan-konflik)
7. [Atribusi](#atribusi)
8. [Lisensi](#lisensi)

## Latihan Tugas Pemrograman

Sebelum memulai mengerjakan tugas pemrograman, harap mengerjakan [Latihan Tugas Pemrograman][latihan-tp] terlebih dahulu untuk memahami alur pengerjaan tugas pemrograman.

## Memulai

1. **Duplikat** repositori ini ke akun GitHub kamu. Cara untuk menduplikat repositori Tugas Pemrograman, hampir sama dengan cara menduplikat repositori Latihan Tugas Pemrograman yang dapat dilihat [disini](https://docs.google.com/document/d/1LFRe4iRxjLmcOZrZ4q1qQPt6m-C7PIVTgpRYrKxDJkM/edit#heading=h.fy60kcxym2pi)

2. Jika visibilitas repositori masih belum private, ubah visibilitas repositori hasil duplikat tersebut menjadi ***private*** melalui halaman *Settings* > *General* > *Danger Zone* > *Change repository visibility* > *Change Visibility* > *Change to private*.

3. Tambahkan akun GitHub `asdos kamu` dan `@dekdepe` sebagai *collaborator* di repositori kamu melalui *Settings* > *Collaborators* > *Add People*.

4. Lakukan **clone** repository. Caranya seperti yang sudah dijelaskan pada dokumen Latihan Tugas Pemrograman yang dapat dilihat [disini](https://docs.google.com/document/d/1LFRe4iRxjLmcOZrZ4q1qQPt6m-C7PIVTgpRYrKxDJkM/edit#heading=h.t8q6xnd73ohy)

5. Tambahkan repositori ***remote*** bernama **`upstream`** yang mengarah ke [repositori pusat][repositori-pusat] (milik tim pengajar). Caranya seperti yang sudah dijelaskan pada dokumen Latihan Tugas Pemrograman yang dapat dilihat [disini](https://docs.google.com/document/d/1LFRe4iRxjLmcOZrZ4q1qQPt6m-C7PIVTgpRYrKxDJkM/edit#heading=h.swwsqobz0z9s)

    > Catatan: Sebuah git *remote* adalah sepasang alias dan pranala ke suatu repositori. Secara *default*, repositori yang baru saja kamu *clone* sudah punya sebuah *remote* bernama `origin` yang mengarah ke repositori kamu di GitHub. Contoh di atas memberitahu repositori git lokal kamu untuk melacak sebuah repositori git *remote* lainnya yang berada di `https://github.com/dekdepe/assignments-22-23` dan menamainya `upstream`.

6. Nantinya, akan ada pembaruan pada repositori pusat (misalnya ada soal tugas pemrograman baru atau revisi soal). Kamu bisa mendapatkan *commit* terbaru dari `upstream` dan menggabungkannya ke repositori lokal kamu menggunakan perintah **`git pull upstream master`**.

7. Apabila kamu ingin menyimpan pekerjaan ke repositori GitHub-mu, gunakan perintah **`git push origin master`**. Perintah ini akan mengirimkan semua *commit* yang ada pada repositori lokal ke repositori di GitHub (jika *commit* tersebut belum ada di repositori GitHub-mu).

    > Catatan: Pastikan kamu melakukan `push` ke *remote* **`origin`**, **bukan** `upstream`!

8. Kamu bisa memeriksa bahwa *commit* yang ada di repositori lokal kamu juga ada di repositori GitHub-mu.

9. Apabila kamu melakukan `push` dari komputer lain (atau repositori lokal lain), misalnya komputer lab, dan kamu ingin lanjut mengerjakan tugas di komputer kamu, kamu dapat menggunakan perintah `git pull origin master` untuk mendapatkan semua *commit* yang ada di GitHub dan belum ada di komputermu.

## Mengerjakan Tugas Pemrograman

1. Misalnya kamu ingin mengerjakan Tugas Pemrograman 1. Masuk ke direktori yang berisi soal tugas tersebut (contoh: `assignment1`).

2. **Baca** berkas `README.md` di dalam direktori `assignment1` dengan teliti.

3. Kerjakan tugas tersebut.

4. Gunakan `git add` atau `git reset` untuk *stage*/*unstage* berkas yang ingin di-*commit* ke repositori git kamu.

5. Ketika kamu ingin menyimpan progres, *commit* pekerjaan kamu ke repositori dengan command `git commit -m "<sebuah pesan commit>"`. Usahakan untuk mengikuti [panduan ini][panduan-commit] untuk menuliskan pesan *commit* yang baik.

6. Ulangi langkah 3-5 sampai kamu menyelesaikan tugas tersebut.

7. Ketika kamu sudah siap untuk mengumpulkan pekerjaan kamu (atau ingin menyimpan progres ke GitHub), lakukan `git push origin master`.

## Mendapatkan pembaruan dari *upstream*

Jika ada pembaruan dari `upstream` (repositori milik tim pengajar), kamu bisa mendapatkan semua *commit* terbaru dan menggabungkannya ke repositori git lokal kamu menggunakan perintah `git pull upstream master` seperti yang dicontohkan di [Latihan Tugas Pemrograman](https://docs.google.com/document/d/1LFRe4iRxjLmcOZrZ4q1qQPt6m-C7PIVTgpRYrKxDJkM/edit#heading=h.swwsqobz0z9s).

Jika kamu belum mengubah teks editor *default* yang digunakan oleh git, yakni `vim`, akan muncul antarmuka `vim` yang memungkinkan kamu untuk mengubah pesan *commit*. Jangan khawatir! Kamu bisa keluar dari `vim` dengan mengetik `:q` lalu menekan <kbd>Enter</kbd>.

Jika kondisi `vim` kamu sudah dalam mode `INSERT`, kamu mungkin harus menekan <kbd>Esc</kbd> terlebih dahulu. Jika kamu sudah mengubah isi pesan *commit* dan ingin menyimpannya, kamu bisa mengetik `:wq` untuk menyimpan dan keluar dari `vim`. Jika kamu tidak ingin menyimpan perubahan tersebut, kamu bisa mengetik `:q!` untuk memaksa keluar dari `vim`.

## Menyelesaikan konflik

Jika terjadi *merge conflict*, silakan selesaikan konflik yang ada dan lanjutkan proses *merging*. Kamu bisa cari [panduan][panduan-konflik] atau meminta bantuan asdos jika mengalami kesulitan.

## Atribusi

Struktur Gradle multi-proyek dan isi berkas README utama diadaptasi dari
repositori [Tugas Pemrograman DDP2 2017/2018 Genap][repositori-2018], [Tugas Pemrograman DDP2 2019/2020 Genap][repositori-2019-2020-genap], [Tugas Pemrograman DDP2 2020/2021 Genap][repositori-2020-2021-genap], dan [Tugas Pemrograman DDP2 2021/2022 Genap][repositori-2021-2022-genap].

## Lisensi

Hak cipta (c) 2023, Fakultas Ilmu Komputer Universitas Indonesia

Izin legal untuk menyalin, mengubah, dan membagikan pekerjaan dalam proyek ini
diatur dalam dua lisensi: [BSD 3-Clause][lisensi-bsd] dan
[Creative Commons Attribution-ShareAlike 4.0 (CC BY-SA 4.0)][lisensi-cc].
Kecuali disebutkan sebaliknya, lisensi BSD 3-Clause berlaku untuk kode sumber
(contoh: Java, YML, berkas konfigurasi), sedangkan CC BY-SA 4.0 berlaku untuk
dokumen teks dalam proyek ini.

Peraturan akademis, terutama terkait **plagiarisme**, tetap berlaku sebagaimana
yang telah dijelaskan dalam perkuliahan.

<!-- Kumpulan Link -->
[latihan-tp]: https://github.com/dekdepe/exercise-assignments
[tp1]: assignment1/README.md
[tp2]: assignment2/README.md
[tp3]: assignment3/README.md
[tp4]: assignment4/README.md
[repositori-pusat]: https://github.com/dekdepe/assignments-22-23

[repositori-2018]: https://gitlab.com/DDP2-CSUI/assignment
[repositori-2019-2020-genap]: https://gitlab.com/DDP2-CSUI/2020/assignments
[repositori-2020-2021-genap]: https://gitlab.com/DDP2-CSUI/2020-2021-genap/assignments
[repositori-2021-2022-genap]: https://gitlab.com/DDP2-CSUI/2021-2022-genap/assignments
[lisensi-bsd]: LICENSE
[lisensi-cc]: https://creativecommons.org/licenses/by-sa/4.0

[panduan-commit]: https://chris.beams.io/posts/git-commit
[panduan-konflik]: https://githowto.com/resolving_conflicts