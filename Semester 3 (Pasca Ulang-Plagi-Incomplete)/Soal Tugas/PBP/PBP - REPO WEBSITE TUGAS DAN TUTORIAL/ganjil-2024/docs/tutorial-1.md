---
sidebar_label: Tutorial 1
sidebar_position: 3
Path: docs/tutorial-1
---

# Tutorial 1: Pengenalan Aplikasi Django dan *Model-View-Template* (MVT) pada Django

Pemrograman Berbasis Platform (CSGE602022) — diselenggarakan oleh Fakultas Ilmu Komputer Universitas Indonesia, Semester Ganjil 2023/2024

---

## Tujuan Pembelajaran

Setelah menyelesaikan tutorial ini, mahasiswa diharapkan untuk dapat:

- Mengerti konsep MVT pada aplikasi Django
- Mengerti bagaimana alur Django menampilkan sebuah halaman HTML
- Mengerti konfigurasi *routing* yang ada pada `urls.py`
- Memahami kaitan *models*, *views* dan *template* pada Django
- Memahami pembuatan *unit test* pada *framework* Django

## Ringkasan Hasil Tutorial 0

Untuk membantumu mengerjakan tutorial 1 dengan baik, kami mengharapkan hasil pengerjaan tutorial 0 sebagai berikut.

1. Pada komputer lokal, terdapat direktori utama `shopping_list` yang telah diinisiasi sebagai repositori lokal.
2. Pada direktori utama `shopping_list` tersebut, terdapat beberapa berkas dan subdirektori berikut.

    - Subdirektori `env`.
    - Subdirektori proyek `shopping_list`. Berbeda dengan direktori utama, subdirektori ini terbentuk setelah menjalankan perintah

        ```bash
        django-admin startproject shopping_list .
        ```

    - Berkas `.gitignore`.
    - Berkas `manage.py`.
    - Berkas `requirements.txt`.
    - (Opsional) Berkas `db.sqlite3`.

    Struktur proyek `shopping_list` pada direktori lokal adalah sebagai berikut.

    ![Struktur Direktori Lokal](https://cdn.discordapp.com/attachments/881129085403811840/1146994121974755448/image.png)

3. Pada repositori GitHub, pastikan repositori `shopping-list` memiliki berkas dan direktori berikut.

    - Direktori proyek `shopping_list`. Direktori ini hasil menjalankan perintah

        ```bash
        django-admin startproject shopping_list .
        ```

    - Berkas `.gitignore`.
    - Berkas `manage.py`.
    - Berkas `requirements.txt`.

    Struktur proyek `shopping_list` pada repositori GitHub adalah sebagai berikut.

    ![Struktur Repositori Github](https://cdn.discordapp.com/attachments/881129085403811840/1147077783017758720/image.png)

## Pengenalan Mengenai Konsep MVT

Dalam dunia pengembangan web, terdapat berbagai konsep dan arsitektur yang membantu dalam merancang dan mengembangkan aplikasi. Salah satu konsep yang umum digunakan adalah MVT (*Model-View-Template*).

### Apa Itu Konsep MVT?

![MVT Diagram](https://cdn.discordapp.com/attachments/1142468662461214771/1146996248268775484/3._Python_Django_-_Modul_2_Page2_Image5.jpg)

**MVT** adalah singkatan dari ***Model-View-Template***. MVT adalah sebuah konsep arsitektur yang digunakan dalam pengembangan web untuk memisahkan komponen-komponen utama dari sebuah aplikasi. Konsep ini memungkinkan pengembang web untuk mengorganisasi dan mengelola kode dengan lebih terstruktur.

### Apa Itu Model?

***Model*** merupakan komponen dalam konsep MVT yang bertanggung jawab untuk mengatur dan mengelola data dari aplikasi. Model mewakili struktur data dan logika aplikasi yang berada di belakang tampilan. Model menghubungkan aplikasi dengan basis data dan mengatur interaksi dengan data tersebut.

### Apa Itu *View*?

***View*** merupakan komponen yang menangani logika presentasi dalam konsep MVT. *View* mengontrol bagaimana data yang dikelola oleh model akan ditampilkan kepada pengguna. Dalam konteks MVT, *view* berperan sebagai pengatur tampilan dan mengambil data dari model untuk disajikan kepada pengguna.

### Apa Itu *Template*?

***Template*** adalah komponen yang berfungsi untuk mengatur tampilan atau antarmuka pengguna. *Template* memisahkan kode HTML dari logika aplikasi. Dalam MVT, *template* digunakan untuk merancang tampilan yang akhirnya akan diisi dengan data dari model melalui *view*.

### Hubungan Antara Komponen-komponen MVT

Secara ringkas, konsep MVT berjalan dalam kerangka berikut:

- ***Model***: Menyimpan data dan logika aplikasi.
- ***View***: Menampilkan data dari model dan menghubungkannya dengan *template*.
- ***Template***: Menentukan tampilan antarmuka pengguna.

### Manfaat MVT

- **Pemisahan Tugas**

    MVT memisahkan tugas antara logika aplikasi, tampilan, dan data, sehingga memungkinkan pengembang untuk bekerja pada setiap komponen secara terpisah.

- **Kode yang Mudah Dikelola**

    Dengan pemisahan tugas yang jelas, kode menjadi lebih terorganisir dan mudah dikelola.

- **Penggunaan Kembali**

    Kode dapat digunakan kembali dalam berbagai bagian aplikasi yang berbeda.

- **Skalabilitas**

    Struktur MVT mendukung skalabilitas dengan memungkinkan pengembangan paralel pada setiap komponen.

**Catatan:**

- Konsep MVT sangat terkait dengan *framework* Django dalam pengembangan web dengan bahasa pemrograman Python.
- Dalam praktiknya, pemahaman yang baik mengenai konsep MVT akan membantu kamu dalam merancang aplikasi web yang lebih terstruktur dan mudah dikelola.

## Tutorial: Membuat Aplikasi Django beserta Konfigurasi Model

Dalam tutorial ini, akan dijelaskan mengenai konsep aplikasi dan proyek dalam Django.

**Apa Itu Proyek dan Aplikasi dalam Django?**

- **Proyek (*Project*)** adalah keseluruhan proyek web yang kamu bangun dengan menggunakan Django. **Proyek berisi berbagai aplikasi** yang berfungsi secara bersama untuk menciptakan situs web atau aplikasi web yang lengkap.

- **Aplikasi (*Apps*)** adalah unit modular yang melakukan tugas-tugas spesifik dalam suatu proyek Django. Setiap aplikasi dapat memiliki model, tampilan, *template*, dan URL yang terkait dengannya. Aplikasi memungkinkanmu untuk membagi fungsionalitas proyek menjadi bagian-bagian terpisah yang dapat dikelola secara independen.

Sebelum dimulai, kamu perlu mengingat kembali bahwa direktori utama adalah direktori **terluar**, sedangkan direktori proyek adalah direktori **di dalam** direktori utama. Perlu diingat bahwa keduanya memiliki nama yang sama, yaitu **`shopping_list`**.

### Langkah 1: Persiapan Awal

1. Buka Direktori Proyek **`shopping_list`**.

    - Sebelum memulai, pastikan kamu berada di direktori utama **`shopping_list`** yang telah dibuat pada tutorial sebelumnya.
    - Di dalam direktori ini, kamu akan melanjutkan pengembangan proyek Django.

2. Buka terminal atau *command prompt* dan pastikan direktori kerja kamu adalah direktori utama **`shopping_list`**.
  
3. Aktifkan *virtual environment* yang telah dibuat sebelumnya. Jalankan perintah berikut sesuai dengan sistem operasi perangkat kamu.

   - **Windows:**

     ```bash
     env\Scripts\activate.bat
     ```

   - **Unix (Linux & Mac OS):**

     ```bash
     source env/bin/activate
     ```

### Langkah 2: Membuat Aplikasi `main` dalam Proyek *Shopping List*

Kamu akan membuat aplikasi baru bernama `main` dalam proyek *shopping list*.

1. Jalankan perintah berikut untuk membuat aplikasi baru.

     ```shell
     python manage.py startapp main
     ```

    Setelah perintah di atas dijalankan, akan terbentuk direktori baru dengan nama `main` yang akan berisi struktur awal untuk **aplikasimu**.

    > Mungkin kamu cukup bingung dengan istilah direktori **utama**, direktori **proyek**, atau direktori **aplikasi**. Akan tetapi, seiring berjalannya waktu, kamu pasti bisa!

2. Mendaftarkan aplikasi `main` ke dalam proyek.

   - Buka berkas `settings.py` di dalam direktori proyek `shopping_list`.
   - Temukan variabel `INSTALLED_APPS`.
   - Tambahkan `'main'` ke dalam daftar aplikasi yang ada.

     ```python
     INSTALLED_APPS = [
         ...,
         'main',
         ...
     ]
     ```

Dengan melakukan langkah-langkah tersebut, kamu telah mendaftarkan aplikasi `main` ke dalam proyek *shopping list* kamu.

## Tutorial: Implementasi *Template* Dasar

Kamu akan membuat *template* `main.html` di dalam direktori `templates` pada aplikasi `main`. *Template* ini akan digunakan untuk menampilkan data *shopping list* kamu.

> Saat ini, aplikasi *shopping list* belum menampilkan data apapun. Data akan ditampilkan pada tutorial 2.

### Langkah 1: Membuat dan Mengisi Berkas `main.html`

Sebelum mulai, mari pahami secara singkat mengenai HTML. HTML (*Hypertext Markup Language*) adalah penanda bahasa yang digunakan untuk membuat struktur dan tampilan konten pada halaman web.

> Hint: Kamu akan mempelajari HTML lebih lanjut di tutorial 4.

1. **Buat direktori baru** bernama `templates` di dalam direktori aplikasi `main`.

2. Di dalam direktori `templates`, **buat berkas baru** bernama `main.html` dengan isi sebagai berikut. Gantilah nilai yang sesuai dengan data kamu.

     ```html
     <h1>Shopping List Page</h1>
     
     <h5>Name: </h5>
     <p>Pak Bepe</p> <!-- Ubahlah sesuai dengan nama kamu -->
     <h5>Class: </h5>
     <p>PBP A</p> <!-- Ubahlah sesuai dengan kelas kamu -->
     ```

3. Buka berkas HTML di peramban web.

   - Sebelum dihubungkan dengan aplikasi, cobalah membuka berkas `main.html` di peramban web-mu.
   - Perlu dicatat bahwa pada tahap ini **hanya untuk memeriksa** tampilan dasar HTML dan **belum terhubung dengan Django.**
   - Berikut merupakan contoh tampilan HTML yang diharapkan.

        ![main.html](https://cdn.discordapp.com/attachments/923523971226435584/1147448582295793705/image.png)

## Tutorial: Implementasi Model Dasar

### Langkah 1: Mengubah Berkas `models.py` dalam Aplikasi `main`

Pada langkah ini, kamu akan mengubah berkas `models.py` yang terdapat di dalam direktori aplikasi `main` untuk mendefinisikan model baru.

1. Buka berkas `models.py` pada direktori aplikasi `main`.

2. Isi berkas `models.py` dengan kode berikut.

    ```python
    from django.db import models

    class Product(models.Model):
        name = models.CharField(max_length=255)
        date_added = models.DateField(auto_now_add=True)
        price = models.IntegerField()
        description = models.TextField()
    ```

    **Penjelasan Kode:**

    - `models.Model` adalah kelas dasar yang digunakan untuk mendefinisikan model dalam Django.
    - `Product` adalah nama model yang kamu definisikan.
    - `nama`, `tanggal_tambah`, `harga`, dan `deskripsi` adalah atribut atau *field* pada model. Setiap *field* memiliki tipe data yang sesuai seperti `CharField`, `DateField`, `IntegerField`, dan `TextField`.

### Langkah 2: Membuat dan Mengaplikasikan Migrasi Model

**Apa itu migrasi model?**

- Migrasi model adalah cara Django melacak perubahan pada model basis data kamu.
- Migrasi ini adalah instruksi untuk mengubah struktur tabel basis data sesuai dengan perubahan model yang didefinisikan dalam kodemu.

**Bagaimana cara melakukan migrasi model?**

1. Jalankan perintah berikut untuk membuat migrasi model.

    ```shell
    python manage.py makemigrations
    ```

    > `makemigrations` menciptakan berkas migrasi yang berisi perubahan model yang belum diaplikasikan ke dalam basis data.

2. Jalankan perintah berikut untuk menerapkan migrasi ke dalam basis data lokal.

    ```shell
    python manage.py migrate
    ```

   > `migrate` mengaplikasikan perubahan model yang tercantum dalam berkas migrasi ke basis data.

**Setiap kali kamu melakukan perubahan pada *model***, seperti menambahkan atau mengubah atribut, **kamu perlu melakukan migrasi** untuk merefleksikan perubahan tersebut.

## Tutorial: Menghubungkan *View* dengan  *Template*

Dalam tutorial ini, kamu akan menghubungkan *view* dengan *template* menggunakan Django. Langkah-langkah ini akan menjelaskan bagaimana membuat fungsi *view* `show_main` dalam aplikasi `main` dan me-*render* *template* dengan data yang telah diambil dari model.

### Langkah 1: Mengintegrasikan Komponen MVT

Kamu akan mengimpor modul yang diperlukan dan membuat fungsi *view* `show_main`.

1. Buka berkas `views.py` yang terletak di dalam berkas aplikasi `main`.

2. Tambahkan baris-baris impor berikut di bagian paling atas berkas.

    ```python
    from django.shortcuts import render
    ```

    **Penjelasan Kode:**

    `from django.shortcuts import render` berguna untuk mengimpor fungsi *render* dari modul `django.shortcuts`. Fungsi *render* digunakan untuk me-*render* tampilan HTML dengan menggunakan data yang diberikan.

3. Tambahkan fungsi `show_main` di bawah impor:

    ```python
    def show_main(request):
        context = {
            'name': 'Pak Bepe',
            'class': 'PBP A'
        }

        return render(request, "main.html", context)
    ```

    **Penjelasan Kode:**

    - `def show_main(request)` merupakan deklarasi fungsi `show_main`, yang menerima parameter `request`. Fungsi ini akan mengatur permintaan HTTP dan mengembalikan tampilan yang sesuai.
    - `context` adalah *dictionary* yang berisi data yang akan dikirimkan ke tampilan. Pada konteks ini, dua data disertakan, yaitu:

       - `name`: Data nama.
       - `class`: Data kelas.

    - `return render(request, "main.html", context)` berguna untuk me-*render* tampilan `main.html` dengan menggunakan fungsi `render`. Fungsi `render` mengambil tiga argumen:

       - `request`: Ini adalah objek permintaan HTTP yang dikirim oleh pengguna.
       - `main.html`: Ini adalah nama berkas *template* yang akan digunakan untuk me-*render* tampilan.
       - `context`: Ini adalah *dictionary* yang berisi data yang akan diteruskan ke tampilan untuk digunakan dalam penampilan dinamis.

## Langkah 2: Modifikasi *Template*

Sekarang, kamu akan mengubah *template* `main.html` agar dapat menampilkan data yang telah diambil dari *model*.

1. Buka berkas `main.html` yang telah dibuat sebelumnya dalam direktori `templates` pada direktori `main`.

2. Ubah nama dan kelas yang sebelumnya dibuat secara statis menjadi kode Django yang sesuai untuk menampilkan data.

    ```html
    ...
    <h5>Name: </h5>
    <p>{{ name }}<p>
    <h5>Class: </h5>
    <p>{{ class }}<p>
    ...
    ```

    **Penjelasan Kode:**

    Sintaks Django `{{ name }}` dan `{{ class }}` digunakan untuk menampilkan nilai dari variabel yang telah didefinisikan dalam `context`.

## Tutorial: Mengonfigurasi *Routing* URL

Kamu akan mengatur *routing* URL agar aplikasi `main` dapat diakses melalui peramban web.

### Langkah 1: Mengonfigurasi *Routing* URL Aplikasi `main`

1. Buatlah berkas `urls.py` **di dalam** direktori `main`.
2. Isi `urls.py` dengan kode berikut.

    ```python
    from django.urls import path
    from main.views import show_main

    app_name = 'main'

    urlpatterns = [
        path('', show_main, name='show_main'),
    ]
    ```

   **Penjelasan Kode dalam `urls.py` pada Aplikasi `main`:**

   - Berkas `urls.py` pada aplikasi `main` bertanggung jawab untuk mengatur rute URL yang terkait dengan aplikasi `main`.
   - Impor `path` dari `django.urls` untuk mendefinisikan pola URL.
   - Gunakan fungsi `show_main` dari modul `main.views` sebagai tampilan yang akan ditampilkan ketika URL terkait diakses.
   - Nama `app_name` diberikan untuk memberikan nama unik pada pola URL dalam aplikasi.

### Langkah 2: Mengonfigurasi *Routing* URL Proyek

Kamu akan menambahkan rute URL dalam `urls.py` proyek untuk menghubungkannya ke tampilan `main`.

1. Buka berkas `urls.py` **di dalam direktori proyek `shopping_list`, bukan yang ada di dalam direktori aplikasi `main`**.
2. Impor fungsi `include` dari `django.urls`.

    ```python
    ...
    from django.urls import path, include
    ...
    ```

3. Tambahkan rute URL seperti berikut untuk mengarahkan ke tampilan `main` di dalam variabel `urlpatterns`.

    ```python
    urlpatterns = [
        ...
        path('main/', include('main.urls')),
        ...
    ]
    ```

   **Penjelasan:**

   - Berkas `urls.py` pada proyek bertanggung jawab untuk mengatur rute URL tingkat proyek.
   - Fungsi `include` digunakan untuk mengimpor rute URL dari aplikasi lain (dalam hal ini, dari aplikasi `main`) ke dalam berkas `urls.py` proyek.
   - *Path* URL `'main/'` akan diarahkan ke rute yang didefinisikan dalam berkas `urls.py` aplikasi `main`.

4. Jalankan proyek Django dengan perintah `python manage.py runserver`

5. Bukalah <http://localhost:8000/main/> di peramban web favoritmu untuk melihat halaman yang sudah kamu buat.

Dengan langkah-langkah di atas, kamu telah berhasil mengimplementasikan tampilan dasar dalam aplikasi `main` dan menghubungkannya dengan rute URL proyek. Pastikan kamu memahami setiap langkah dan informasi yang diberikan untuk mengaktifkan tampilan dalam proyek Django-mu

### Apa bedanya `urls.py` pada aplikasi dan `urls.py` pada proyek?

- Berkas `urls.py` pada aplikasi mengatur rute URL spesifik untuk fitur-fitur dalam aplikasi tersebut
- `urls.py` pada proyek mengarahkan rute URL tingkat proyek dan dapat mengimpor rute URL dari berkas `urls.py` aplikasi-aplikasi, memungkinkan aplikasi dalam proyek Django untuk bersifat modular dan terpisah.

## Tutorial: Pengenalan Django *Unit Testing*

*Unit testing* dapat digunakan untuk mengecek apakah kode yang dibuat bekerja sesuai dengan keinginan. Hal ini juga berguna ketika kamu melakukan perubahan kode. Dengan menggunakan tes, kamu bisa mengecek apakah perubahan yang dilakukan dapat menyebabkan perilaku yang tidak diinginkan pada aplikasi.

### Langkah 1: Membuat Unit *Test*

1. Bukalah berkas `tests.py` pada direktori aplikasi `main`.
2. Isi `tests.py` dengan kode berikut.

    ```python
    from django.test import TestCase, Client

    class mainTest(TestCase):
        def test_main_url_is_exist(self):
            response = Client().get('/main/')
            self.assertEqual(response.status_code, 200)

        def test_main_using_main_template(self):
            response = Client().get('/main/')
            self.assertTemplateUsed(response, 'main.html')
    ```

    **Penjelasan:**
    - `test_main_url_is_exist` adalah tes untuk mengecek apakah *path* URL `/main/` dapat diakses.
    - `test_main_using_main_template` adalah tes untuk mengecek apakah halaman `/main/` di-*render* menggunakan *template* `main.html`.

### Langkah 2: Menjalankan *Test*

1. Jalankan tes dengan menggunakan perintah berikut.

    ```shell
    python manage.py test
    ```

2. Jika tes berhasil, akan mengeluarkan informasi berikut.

    ```text
    Found 2 test(s).
    Creating test database for alias 'default'...
    System check identified no issues (0 silenced).
    ..
    ----------------------------------------------------------------------
    Ran 2 tests in 0.016s

    OK
    Destroying test database for alias 'default'...
    ```

**Selamat!** Kamu telah berhasil menulis Django *Test* dan menjalankannya.

## Penutup

1. Pada akhir tutorial ini, struktur direktori lokalmu akan terlihat seperti ini.

    ![Struktur Direktori Lokal](https://cdn.discordapp.com/attachments/881129085403811840/1147078488667475978/image.png)

2. Sebelum melakukan langkah ini, **pastikan struktur direktori lokal sudah benar**. Selanjunya, lakukan `add`, `commit` dan `push` untuk memperbarui repositori GitHub. Ketika repositori GitHub diperbarui, Adaptable secara otomatis akan melakukan *deploy* kembali. Bila berhasil, fitur yang kamu buat pada tutorial ini sudah bisa diakses secara umum.
3. Jalankan perintah berikut untuk melakukan `add`, `commit` dan `push`.

    ```shell
    git add .
    git commit -m "<pesan_commit>"
    git push -u origin <branch_utama>
    ```

    - Ubah `<pesan_commit>` sesuai dengan keinginan. Contoh: `git commit -m "tutorial 1 selesai"`.
    - Ubah `<branch_utama>` sesuai dengan nama branch utamamu. Contoh: `git push -u origin main` atau `git push -u origin master`.

4. Berikut struktur direktori GitHub setelah kamu menyelesaikan tutorial ini.

    ![Struktur Repositori Github](https://cdn.discordapp.com/attachments/881129085403811840/1147081165031882815/image.png)

## Referensi Tambahan

- [Django Unit Testing](https://docs.djangoproject.com/en/4.2/topics/testing/)

## Kontributor

- Kevin Alexander
- Ivan Rabbani Cezeliano
- Fariz Eda
- Dafi Nafidz Radhiyya

## Credits

Tutorial ini dikembangkan berdasarkan [PBP Ganjil 2023](https://github.com/pbp-fasilkom-ui/ganjil-2023) dan [PBP Genap 2023](https://github.com/pbp-fasilkom-ui/genap-2023) yang ditulis oleh Tim Pengajar Pemrograman Berbasis Platform 2023. Segala tutorial serta instruksi yang dicantumkan pada repositori ini dirancang sedemikian rupa sehingga mahasiswa yang sedang mengambil mata kuliah Pemrograman Berbasis Platform dapat menyelesaikan tutorial saat sesi lab berlangsung.
