# TP 3: Inheritance, Abstract Classes, and Interfaces

CSGE601021 Dasar-Dasar Pemrograman 2 @ Fakultas Ilmu Komputer Universitas Indonesia, Semester Genap 2022/2023

Tugas Pemrograman ini mencakup materi berikut:

- Inheritance
- Interface
- Abstract class
- Composition
- Polymorphism

## Daftar Isi

- [Dokumen Soal](#dokumen-soal)
- [Persiapan](#persiapan)
- [Menjalankan dan menguji program](#menjalankan-dan-menguji-program)
- [Pengumpulan](#pengumpulan)
- [Daftar pekerjaan](#daftar-pekerjaan)
    - [Daftar pekerjaan wajib](#daftar-pekerjaan-wajib)
- [Penilaian](#penilaian)

## Dokumen Soal

Dokumen soal dapat diakses melalui [link ini](https://docs.google.com/document/d/12SF_i5OQBMZCaxzQgpRpMDGc8riQQzeDFFaFIuVIuOk/edit?usp=sharing).

## Persiapan

Silakan ikuti persiapan yang ada pada berkas [`README.md`](../README.md) di
*root* repositori ini.

> Template pengerjaan tugas ini disediakan di *path*
`src\main\java\assignments\assignment3`
## Menjalankan dan menguji program

> Catatan:<br>
> - Ubah `gradlew.bat` dengan `./gradlew` dan `\` dengan `/` jika kamu
    menggunakan Linux/Mac.
> - Tanda <kbd>></kbd> pada awal *command* tidak perlu ditulis.
    Digunakan sebagai pembeda antara *command* dan *output*-nya
    Kamu bisa menjalankan beberapa *task* dengan Gradle melalui IDE atau
    terminal/Command Prompt.
    Jalankan *task* tersebut di **root folder** dari repository ini.

Untuk menjalankan program:

```bash
> gradlew.bat :assignment3:run --console plain
```

Jika kamu tidak ingin menggunakan Gradle, kamu juga bisa melakukan kompilasi
dan menjalankan program dengan `javac` dan `java` seperti biasa. Masuk ke
direktori `src\main\java`, *compile* semua berkas `.java` kamu, dan jalankan
*main class*-nya.

```bash
> cd src\main\java
src\main\java> javac assignments\assignment3\*.java
src\main\java> java assignments.assignment3.MainMenu
```

Untuk menguji program dengan *test case* yang disediakan:

```bash
> gradlew.bat :assignment3:test
```

> Catatan: <kbd>src\main\java></kbd> tidak perlu ditulis saat menjalankan *command*-nya (hanya menandakan bahwa anda sudah berada di folder src\main\java)
## Pengumpulan

Simpan pekerjaan kamu, lalu lakukan `git add`, `git commit`, dan `git push`
sebagaimana dijelaskan pada dokumen
Getting Started with a Java Project (Latihan Tugas Pemrograman).
*Push* solusi kamu **sebelum 4 Mei 2023 pukul 23.55**. Disarankan untuk
*push* **sebelum pukul 22.00** untuk mengantisipasi antrean pada GitHub
Pipelines.

Jangan lupa untuk menambahkan asdos kamu dan dekdepe sebagai *member* di repositori kamu.
Tim pengajar hanya akan menilai pekerjaan kamu berdasarkan ***commit* terbaru**
yang **sudah di-*push* sebelum waktu dan tanggal yang ditentukan**. *Commit*
tersebut diidentifikasi dengan memeriksa GitHub Pipelines.
> Catatan: *timestamp* pada GitHub Pipelines tidak pernah bohong.
> *So, no funny business, please :)*
## Daftar pekerjaan
### Daftar pekerjaan wajib
- [ ] Buat sekurang-kurangnya 1 *commit* yang mengandung progres kamu dalam
  mengerjakan Tugas Pemrograman 3.
- [ ] *Push* *commit* kamu ke repositori Git *online* yang ada pada proyek
  GitHub kamu.
- [ ] Implementasikan class `Member` dengan benar.
- [ ] Implementasikan class `Nota` dengan benar.
- [ ] Implementasikan concrete class untuk interface `LaundryService`
- [ ] Implementasikan menu spesifik untuk setiap role dengan _inherit_ abstract class `SystemCLI`
- [ ] Implementasikan class `Employee` dan `Member`, dengan `Employee` _inherit_ `Member`
- [ ] Implementasikan class `LoginManager`
- [ ] Melengkapi fungsionalitas class `MainMenu` dengan benar.
- [ ] Silakan menambah class atau method yang diperlukan.
- [ ] Demonstrasikan program kamu ke asdos kamu.
## Penilaian
- 30% Fungsionalitas Program 
- 10% Implementasi Package Service
- 10% Implementasi Package User
- 10% Implementasi Package Menu
- 5% Dokumentasi (kejelasan kode)
- 5% Standar penulisan kode* (kerapian kode)
- 30% Demo program dengan asisten dosen
