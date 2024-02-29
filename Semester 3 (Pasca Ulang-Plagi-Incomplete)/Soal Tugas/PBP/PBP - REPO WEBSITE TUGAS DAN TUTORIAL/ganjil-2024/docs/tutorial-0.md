---
sidebar_label: Tutorial 0
sidebar_position: 2
Path: docs/tutorial-0
---

# Tutorial 0: Konfigurasi dan Instalasi Git dan Django

Pemrograman Berbasis Platform (CSGE602022) — diselenggarakan oleh Fakultas Ilmu Komputer Universitas Indonesia, Semester Ganjil 2023/2024

---

## Tujuan Pembelajaran

Setelah menyelesaikan tutorial ini, mahasiswa diharapkan untuk dapat:

- Mengerti perintah-perintah dasar Git yang perlu diketahui untuk mengerjakan proyek aplikasi.
- Menggunakan perintah-perintah dasar Git yang perlu diketahui untuk mengerjakan proyek aplikasi.
- Membuat repositori Git lokal dan daring (GitHub).
- Menambahkan *remote* antara repositori Git lokal dan repositori daring pada GitHub.
- Memahami *branching* pada Git dan mampu melakukan *merge request*/*pull request*.
- Membuat aplikasi Django secara lokal.
- Melakukan *deployment* aplikasi Django pada Adaptable.io

## Tutorial: Pembuatan Akun GitHub

### Pengenalan Tentang Git dan GitHub

Pengenalan awal ini akan membantumu memahami dasar-dasar mengenai Git dan platform berbasis web yang dikenal sebagai GitHub.

#### Git: Sistem Kontrol Versi yang Kuat

- **Git** adalah sistem kontrol versi yang membantumu melacak perubahan pada kode sumber proyek.
- Dengan Git, kamu dapat memantau semua revisi yang telah dilakukan pada proyekmu seiring waktu.

#### GitHub: Platform Kolaborasi Menggunakan Git

- **GitHub** adalah platform berbasis web yang memungkinkanmu untuk menyimpan, mengelola, dan berkolaborasi pada proyek-proyek menggunakan Git.
- Ini memberikan wadah yang aman untuk meng-*host* proyekmu dan berinteraksi dengan rekan tim melalui Git.

#### Mengapa Penting?

- Git dan GitHub memainkan peran penting dalam pengembangan perangkat lunak modern dan kolaborasi tim.
- Keduanya memungkinkan tim untuk melacak perubahan kode, menyimpan versi, dan bekerja bersama dalam proyek secara efisien.

Dengan pemahaman dasar mengenai Git dan GitHub, kamu siap untuk melangkah lebih jauh dalam dunia pengembangan perangkat lunak yang kolaboratif dan terstruktur.

### Tutorial: Membuat Akun di GitHub

Langkah selanjutnya adalah membuat akun di GitHub, yang akan memungkinkanmu untuk mulai berkolaborasi pada proyek-proyek menggunakan Git.

1. Buka Situs Web GitHub

   - Buka peramban web dan akses [GitHub].

2. Membuat Akun

   - Di halaman beranda GitHub, cari tombol **`Sign up`** di pojok kanan atas halaman.
   - Klik tombol tersebut untuk memulai proses pendaftaran akun.

3. Isi Formulir Pendaftaran

   - Isi formulir pendaftaran dengan informasi yang diperlukan, seperti nama pengguna yang ingin digunakan, alamat surel yang valid, dan kata sandi yang aman.
   - Pastikan kamu menyimpan informasi ini dengan aman untuk masuk ke akunmu di masa mendatang.

4. Verifikasi Akun Melalui Surel

   - Setelah mengisi formulir, GitHub akan mengirimkan surel verifikasi ke alamat surel yang kamu berikan.
   - Buka surel tersebut dan ikuti instruksi untuk verifikasi akunmu.

5. Akun GitHub Siap Digunakan

   - Setelah verifikasi selesai, kamu akan memiliki akun GitHub yang siap digunakan untuk berkolaborasi dalam proyek dan melacak perubahan menggunakan Git.

**Catatan:**

- Akun GitHub adalah pintu masuk untuk terlibat dalam kolaborasi proyek dan menyimpan proyekmu di platform ini.
- Pastikan informasi pendaftaran yang kamu berikan akurat dan aman.

#### Selamat, Kamu Telah Membuat Akun GitHub

Kamu sekarang telah memiliki akun GitHub yang dapat digunakan untuk menyimpan proyek, berkolaborasi dengan orang lain, dan masih banyak lagi.

## Tutorial: Instalasi IDE

IDE (*Integrated Development Environment*) adalah perangkat lunak yang membantu para pengembang dalam menulis, mengedit, dan mengelola kode. Berikut adalah langkah-langkah untuk memasang IDE.

### Langkah 1: Pemilihan *Text Editor* atau IDE

Pilihlah *text editor* atau IDE yang sesuai dengan preferensimu. Beberapa pilihan populer yang dapat kamu pertimbangkan meliputi:

- [Vim](http://www.vim.org/download.php)
- [Visual Studio Code](https://code.visualstudio.com/)
- [Sublime Text](https://www.sublimetext.com/)
- [PyCharm](https://www.jetbrains.com/pycharm/)

### Langkah 2: Proses Instalasi

1. Pergi ke situs web resmi IDE yang kamu pilih.
2. Ikuti petunjuk yang diberikan untuk mengunduh *installer* IDE.
3. Jalankan *installer* dan ikuti instruksi di layar untuk menyelesaikan proses instalasi.

### Langkah 3: Memulai Menggunakan IDE

1. Setelah proses instalasi selesai, buka IDE yang telah terinstal.
2. Eksplorasi antarmuka dan fitur yang disediakan oleh IDE untuk membantumu dalam pengembangan proyek.

**Catatan:**

- Pastikan kamu memilih IDE yang sesuai dengan jenis proyek yang akan dikerjakan.
- Jangan ragu untuk mengeksplorasi fitur-fitur IDE (contoh: *extensions* atau *plugin*) dan memanfaatkan sumber daya pendukung, seperti dokumentasi dan tutorial, untuk meningkatkan produktivitas dalam pengembangan perangkat lunak.

## Tutorial: Instalasi dan Konfigurasi Git

### Langkah 1: Instalasi Git

Jika Git belum terpasang pada sistem, kamu dapat mengikuti langkah-langkah berikut untuk menginstalnya.

1. Buka situs web resmi Git di [git](https://git-scm.com/downloads).
2. Pilih sistem operasi yang sesuai (Windows, macOS, atau Linux) dan unduh *installer* yang sesuai.
3. Jalankan *installer* yang telah diunduh dan ikuti petunjuk di layar untuk menyelesaikan proses instalasi.

### Langkah 2: Konfigurasi Awal Git

Setelah Git terpasang, langkah-langkah berikut akan membantumu mengatur konfigurasi awal sebelum mulai menggunakan Git.

1. Buka terminal atau *command prompt* pada sistem.
2. Pindah ke direktori tempat kamu ingin menyimpan proyek Git dengan menggunakan perintah `cd <nama_direktori>`.
3. Inisiasi repositori baru dengan perintah `git init`. Perintah ini akan membuat repositori Git kosong di dalam direktori yang kamu tentukan.

### Langkah 3: Konfigurasi Nama Pengguna dan Surel

Sebelum mulai berkontribusi ke repositori, konfigurasikan nama pengguna dan alamat surel agar terhubung dengan *commit*-mu.

Atur *username* dan *email* yang akan diasosiasikan dengan pekerjaanmu ke repositori Git ini. Sesuaikan dengan *username* dan *email* yang kamu gunakan pada [GitHub].

```bash
git config user.name "<NAME>"
git config user.email "<EMAIL>"
```

Contoh:

```bash
git config user.name "pakbepe"
git config user.email "pak.bepe@cs.ui.ac.id"
```

### Langkah 4: Konfigurasi Global (Opsional)

Jika kamu ingin menerapkan konfigurasi ke semua repositori lokal, gunakan *flag* `--global` pada perintah `git config`. Namun, perlu diingat bahwa ini akan mengubah konfigurasi global untuk seluruh sistem.

Untuk konfigurasi nama pengguna secara global, jalankan perintah berikut.

```bash
git config --global user.name "<NAME>"
git config --global user.email "<EMAIL>"
```

Contoh:

```bash
git config --global user.name "pakbepe"
git config --global user.email "pak.bepe@cs.ui.ac.id"
```

### Langkah 5: Verifikasi Konfigurasi

Untuk memastikan konfigurasi lokal telah diatur dengan benar pada repositori lokal, kamu dapat menggunakan perintah berikut.

```bash
git config --list --local
```

**Catatan:**

- Pastikan untuk mengganti `<NAME>` dan `<EMAIL>` dengan informasi pribadimu
- Gunakan langkah-langkah ini sebagai panduan untuk mengkonfigurasi Git sesuai kebutuhanmu.

## Tutorial: Penggunaan Dasar Git

**Repositori** adalah tempat penyimpanan untuk proyek perangkat lunak, yang mencakup semua revisi dan perubahan yang telah dilakukan pada kode. Untuk mengeksekusi perintah-perintah Git, kamu dapat melakukannya pada repositori di GitHub, platform kolaboratif untuk mengelola proyek menggunakan Git.

### Langkah 1: Melakukan Inisiasi Repositori di GitHub

Langkah pertama dalam penggunaan Git adalah melakukan inisiasi repositori di GitHub untuk memulai pelacakan perubahan pada proyekmu.

1. Buka [GitHub] melalui peramban web.

2. Buat Repositori Baru

   - Pada halaman beranda GitHub, buat repositori baru dengan nama `my-first-repo`.
   - Buka halaman repositori yang baru kamu buat. Pastikan untuk mengatur visibilitas proyek sebagai "Public" dan biarkan pengaturan lainnya pada nilai *default*.

3. Tentukan Direktori Lokal

   - Pilih direktori lokal di komputermu yang telah diinisiasi dengan Git. Inilah tempat kamu akan menyimpan versi lokal dari proyek.

4. Tambahkan Berkas `README.md`

   - Buat berkas baru dengan nama `README.md` dalam direktori lokal proyekmu.
   - Isi berkas `README.md` dengan informasi seperti nama, NPM, dan kelas. Contoh:

        ```md
        Nama    : Kevin Ivan Eda Radhiyya

        NPM     : 2201233210

        Kelas   : PBP B
        ```

5. Cek Status dan Lakukan *Tracking*

   - Buka *command prompt* atau terminal, lalu jalankan `git status`. Perintah ini akan menampilkan berkas-berkas yang belum di-*track* (*untracked*).
   - Gunakan perintah `git add README.md` untuk menandai berkas README.md sebagai yang akan di-*commit* (*tracked*).

6. *Commit* Perubahan

   - Jalankan kembali `git status` dan pastikan berkas README.md sudah ditandai sebagai yang akan di-*commit*.
   - Lanjutkan dengan `git commit -m "<KOMENTAR KAMU>"` untuk membuat *commit* dengan pesan komentar yang sesuai dengan perubahan yang kamu lakukan.

**Catatan:**

- Langkah ini akan membuat kamu siap untuk mulai melacak perubahan pada proyek menggunakan Git.
- ***Good practice*** dalam memberikan komentar *commit* adalah menjelaskan dengan singkat apa yang kamu lakukan.
- Komentar *commit* yang baik membantumu dan rekan-rekan tim dalam memahami tujuan perubahan tersebut.
- **Hindari** komentar yang terlalu umum atau ambigu, seperti `Perbaikan bug` atau `Update file`.

### Langkah 2: Menghubungkan Repositori Lokal dengan Repositori di GitHub

Setelah melakukan inisiasi repositori lokal, langkah selanjutnya adalah menghubungkannya dengan repositori di GitHub agar kamu dapat berkolaborasi dan menyimpan perubahan di platform daring tersebut.

1. Buat *Branch* Utama Baru

   - Di terminal atau *command prompt*, jalankan perintah `git branch -M main` untuk membuat *branch* utama baru dengan nama "main".
   - Pastikan huruf "M" pada perintah `-M` ditulis dengan **huruf kapital**.

2. Hubungkan dengan Repositori di GitHub

   - Gunakan perintah `git remote add origin <URL_REPO>` untuk menghubungkan repositori lokal dengan repositori di GitHub.
   - Gantilah `<URL_REPO>` dengan URL HTTPS repositori yang telah kamu buat di GitHub. Contoh:

       ```bash
       git remote add origin https://github.com/pakbepe/test.git
       ```

3. Lakukan Penyimpanan Pertama ke GitHub

   - Terakhir, lakukan penyimpanan pertama ke GitHub dengan menjalankan perintah `git push -u origin main`.
   - Perintah ini akan mengirimkan semua perubahan yang ada pada *branch* saat ini (dalam hal ini adalah *branch* utama) di repositori lokal ke *branch* main di repositori GitHub.

4. Lakukan Pengecekan Kembali

   - Lakukan *refresh* halaman kamu, apakah berkas README.md kamu terlihat?

**Catatan:**

- Langkah ini penting untuk menjaga konsistensi antara repositori lokal dan repositori di GitHub.
- Proses ini memungkinkanmu untuk mulai berkolaborasi dan menyimpan perubahan proyek secara terstruktur di platform GitHub.

### Langkah 3: Melakukan *Cloning* terhadap Suatu Repositori

***Cloning* repositori** adalah proses menduplikasi seluruh konten dari repositori yang ada di platform GitHub ke komputer lokal. Langkah-langkahnya adalah sebagai berikut.

1. Buka halaman repositori di [GitHub] yang telah kamu buat sebelumnya.

2. Salin URL *Clone*

   - Klik tombol **`Code`** di pojok kanan atas halaman repositori di GitHub.
   - Pilih opsi HTTPS untuk salin URL *clone*.

3. *Clone* Repositori ke Komputer Lokal

   - Buka terminal atau *command prompt* di direktori yang **berbeda** dari tempat repositori lokalmu.
   - Jalankan perintah `git clone <URL_CLONE>` (gantilah URL_CLONE dengan URL yang telah kamu salin).
   - Perintah ini akan menduplikasi seluruh repositori ke komputer lokalmu.

Saat ini, kamu memiliki tiga repositori:

1. **Repositori asli** di komputer lokal.
2. **Repositori daring** di GitHub yang terhubung dengan repositori lokal.
3. **Repositori baru hasil dari proses *cloning*** yang terhubung dengan repositori GitHub.

**Catatan:**

- Langkah ini memungkinkanmu untuk bekerja dengan repositori di berbagai tempat dengan mudah.

### Langkah 4: Melakukan *Push* kepada Suatu Repositori

Seperti yang sudah disinggung sebelumnya (Langkah 2), ***push*** adalah proses mengirimkan perubahan yang kamu lakukan di repositori lokal ke repositori di GitHub. Langkah-langkahnya adalah sebagai berikut.

1. Buka kembali **repositori lokal** yang pertama kali kamu buat.

2. Ubah isi berkas `README.md` dengan menambahkan atribut Jurusan. Contohnya adalah sebagai berikut.

   ```md
   Nama    : Kevin Ivan Eda Radhiyya

   NPM     : 2201233210
        
   Kelas   : PBP B

   Hobi    : Tidur
   ```

3. Lakukan *Push* ke Repositori GitHub

   - Jalankan perintah `git status` untuk melihat status perubahan yang dilakukan.
   - Gunakan `git add README.md` untuk menambahkan perubahan ke dalam tahap yang akan di-*commit*.
   - Lakukan *commit* dengan perintah `git commit -m "<KOMENTAR KAMU>"` untuk memberikan deskripsi singkat tentang perubahan yang kamu lakukan.
   - Terakhir, jalankan `git push -u origin <NAMA_BRANCH>` untuk mengirim perubahan ke *branch* yang dipilih pada repositori GitHub (gantilah "Nama Branch" dengan *target branch*, misalnya `main`).

4. Lakukan Pengecekan Kembali

   - Lakukan *refresh* halaman kamu, apakah berkas README.md kamu berubah?

**Catatan**: Jika kamu ingin mengambil **semua** perubahan yang belum di-*stage* (ditandai untuk dimasukkan dalam commit) **dari seluruh direktori proyek kamu**, jalankan `git add .`

### Langkah 5: Melakukan *Pull* dari Suatu Repositori

***Pull*** pada suatu repositori adalah proses mengambil perubahan terbaru dari repositori di GitHub dan menggabungkannya dengan repositori lokal.

1. Buka kembali **repositori yang telah kamu *clone*** sebelumnya.

2. Jalankan Perintah *Pull*

   - Di terminal atau *command prompt*, jalankan perintah `git pull origin main` untuk mengambil perubahan terbaru yang ada di repositori GitHub dan menggabungkannya dengan repositori lokalmu.

3. Lakukan Pengecekan Kembali

   - Cek kembali berkas `README.md` di repositori tersebut. Apakah tertulis hobi kamu?

**Catatan:**

- Langkah ini memastikan bahwa repositori lokalmu selalu diperbarui dengan perubahan terbaru yang ada di repositori GitHub.
- Melakukan *pull* secara berkala penting untuk menghindari konflik dan memastikan kamu bekerja dengan versi terbaru dari proyek.

### Langkah 6: Melakukan *Branching* pada Suatu Repositori

Seperti yang sudah disinggung sebelumnya, dalam tahap ini kamu akan mempelajari tentang penggunaan *branch* dalam Git, yang memungkinkanmu untuk mengembangkan fitur atau memperbaiki *bug* di lingkungan terpisah sebelum menggabungkannya kembali ke *branch* utama.

Sebelum memulai, pastikan kamu **telah** melakukan perintah `git branch -M main` untuk membuat *branch* utama baru dengan nama "main".

**Apa Itu *Branch* di Git?**

- *Branch* di Git adalah cabang terpisah dari kode sumber yang memungkinkan pengembangan independen dari fitur atau perubahan.
- Hal ini memungkinkan tim untuk bekerja pada fitur atau perbaikan *bug* tanpa mengganggu kode yang ada di *branch* utama.

1. Membuat dan Mengganti *Branch* Baru

    - Pada direktori repositori lokal asli (bukan *clone*), gunakan perintah `git checkout -b <NAMA_BRANCH>` di terminal atau *command prompt* repositori pertama untuk membuat dan beralih ke *branch* baru. Contoh: `git checkout -b jurusan_branch`
    - Tambahkan atribut jurusan pada berkas `README.md`. Contoh:

        ```md
        Nama    : Kevin Ivan Eda Radhiyya

        NPM     : 2201233210

        Kelas   : PBP B

        Hobi    : Tidur

        Jurusan : Ilmu Sistem Informasi Komputer
        ```

2. Menyimpan Perubahan dan *Push* ke GitHub

    - Setelah menambahkan atribut jurusan, simpan berkas tersebut.
    - Lakukan `add`, `commit`, dan `push` ke GitHub dengan menjalankan perintah yang sudah kamu kuasai sebelumnya.
    - Ketika `push`, jangan lupa untuk mengganti `<NAMA_BRANCH>` sesuai dengan nama branch baru yang telah dibuat.

3. Menggabungkan *Branch* Menggunakan *Pull Request*

    - Buka kembali halaman repositori GitHub.
    - Secara otomatis, pop-up dengan tombol **`Compare & pull request`** akan muncul. Jika tidak, alternatifnya adalah dengan menekan tombol **`Pull Request`** dan kemudian memilih opsi **`New pull request`**.
    - Setelah itu, GitHub akan membandingkan perubahan yang ada di kedua *branch* yang ingin digabungkan.
    - Apabila tidak terdapat konflik, Tekan tombol **`Merge pull request`** yang akan menggabungkan perubahan dari *branch* yang ingin digabungkan ke dalam *branch* utama (`main`).
    - Dengan melakukan langkah di atas, semua perubahan dari kedua *branch* akan diintegrasikan ke dalam *branch* utama, menciptakan kesatuan antara perubahan tersebut.

**Catatan:**

- Jika kamu ingin mengganti antar *branch* yang sudah ada, jalankan `git checkout <NAMA_BRANCH>` pada terminal. (Flag `-b` digunakan untuk membuat cabang baru dan beralih ke cabang tersebut dalam satu langkah)
- **Konflik** terjadi ketika perubahan yang dilakukan pada satu *branch* bertabrakan dengan perubahan yang dilakukan pada *branch* lain. Misalnya, jika dua pengembang mengubah bagian yang sama dari berkas yang sama dalam waktu bersamaan, Git tidak dapat dengan otomatis memutuskan perubahan mana yang harus diterapkan.
- Jika terdapat konflik atau perubahan yang saling bertabrakan, platform ini akan meminta kamu untuk menentukan perubahan mana yang sebaiknya diambil.
- **Penting** untuk memahami konsep *branching* dalam Git, karena ini memungkinkan **pengembangan yang terorganisir dan terpisah**, sebelum semua perubahan dikombinasikan kembali ke dalam kode utama.

## Tutorial: Instalasi Django dan Inisiasi Proyek Django

**Django** adalah kerangka kerja (*framework*) yang populer untuk pengembangan aplikasi web dengan bahasa pemrograman Python. Dalam tutorial ini, kamu akan mempelajari langkah-langkah instalasi Django dan inisiasi proyek demo sebagai *starter*.

### Langkah 1: Membuat Direktori dan Mengaktifkan *Virtual Environment*

1. Buat direktori baru dengan nama `shopping_list` dan masuk ke dalamnya.
2. Di dalam direktori tersebut, buka *command prompt* (Windows) atau *terminal shell* (Unix).
3. Buat *virtual environment* dengan menjalankan perintah berikut.

    ```bash
    python -m venv env
    ```

4. ***Virtual environment*** ini berguna untuk mengisolasi *package* serta *dependencies* dari aplikasi sehingga tidak bertabrakan dengan versi lain yang ada pada komputermu. Kamu dapat mengaktifkan *virtual environment* dengan perintah berikut.

    - Windows:

        ```bash
        env\Scripts\activate.bat
        ```

    - Unix (Mac/Linux):

        ```bash
        source env/bin/activate
        ```

5. *Virtual environment* akan aktif dan ditandai dengan `(env)` di baris *input* terminal.

### Langkah 2: Menyiapkan *Dependencies* dan Membuat Proyek Django

***Dependencies*** adalah komponen atau modul yang diperlukan oleh suatu perangkat lunak untuk berfungsi, termasuk *library*, *framework*, atau *package*. Hal tersebut memungkinkan pengembang memanfaatkan kode yang telah ada, mempercepat pengembangan, tetapi juga memerlukan manajemen yang hati-hati untuk memastikan kompatibilitas versi yang tepat. Penggunaan *virtual environment* membantu mengisolasi *dependencies* antara proyek-proyek yang berbeda.

1. Di dalam direktori yang sama, buat berkas `requirements.txt` dan tambahkan beberapa *dependencies*.

    ```text
    django
    gunicorn
    whitenoise
    psycopg2-binary
    requests
    urllib3
    ```

2. Pasang *dependencies* dengan perintah berikut. Jangan lupa jalankan *virtual environment* terlebih dahulu sebelum menjalankan perintah.

    ```bash
    pip install -r requirements.txt
    ```

3. Buat proyek Django bernama `shopping_list` dengan perintah berikut.

    ```bash
    django-admin startproject shopping_list .
    ```

    > Pastikan karakter `.` tertulis di akhir perintah

### Langkah 3: Konfigurasi Proyek dan Menjalankan Server

1. Tambahkan `*` pada `ALLOWED_HOSTS` di `settings.py` untuk keperluan *deployment*:

    ```python
    ...
    ALLOWED_HOSTS = ["*"]
    ...
    ```

    Dalam konteks *deployment*, `ALLOWED_HOSTS` berfungsi sebagai daftar *host* yang diizinkan untuk mengakses aplikasi web. Dengan menetapkan nilai `["*"]`, kamu mengizinkan akses dari **semua** *host*, yang akan memungkinkan aplikasi diakses secara luas. Namun, perlu diingat bahwa pengaturan ini harus digunakan dengan bijak dan hanya dalam situasi tertentu, seperti saat melakukan uji coba atau tahap awal pengembangan.

2. Pastikan bahwa berkas `manage.py` ada pada direktori yang aktif pada *shell* kamu saat ini. Jalankan *server* Django dengan perintah:

   - Windows:

        ```bash
        python manage.py runserver
        ```

   - Unix:

        ```bash
       ./manage.py runserver
        ```

3. Buka <http://localhost:8000> pada peramban web untuk melihat animasi roket sebagai tanda aplikasi Django kamu berhasil dibuat.

### Langkah 4: Menghentikan *Server* dan Menonaktifkan *Virtual Environment*

1. Untuk menghentikan *server*, tekan `Ctrl+C` (Windows/Linux) atau `Control+C` (Mac) pada *shell*.
2. Nonaktifkan *virtual environment* dengan perintah:

    ```bash
    deactivate
    ```

    Selamat! Kamu telah berhasil membuat aplikasi Django dari awal.

## Tutorial: Unggah Proyek ke Repositori GitHub

1. Buatlah repositori GitHub baru bernama `shopping-list` dengan visibilitas *public*.

2. Inisiasi direktori `shopping_list` sebagai repositori Git.

    > *Hint: Ingat kembali tutorial sebelumnya*

3. Tambahkan Berkas `.gitignore`

    - Tambahkan berkas `.gitignore` dan isilah berkas tersebut dengan teks berikut.

        ```yaml
        # Django
        *.log
        *.pot
        *.pyc
        __pycache__
        db.sqlite3
        media

        # Backup files
        *.bak 

        # If you are using PyCharm
        # User-specific stuff
        .idea/**/workspace.xml
        .idea/**/tasks.xml
        .idea/**/usage.statistics.xml
        .idea/**/dictionaries
        .idea/**/shelf

        # AWS User-specific
        .idea/**/aws.xml

        # Generated files
        .idea/**/contentModel.xml

        # Sensitive or high-churn files
        .idea/**/dataSources/
        .idea/**/dataSources.ids
        .idea/**/dataSources.local.xml
        .idea/**/sqlDataSources.xml
        .idea/**/dynamic.xml
        .idea/**/uiDesigner.xml
        .idea/**/dbnavigator.xml

        # Gradle
        .idea/**/gradle.xml
        .idea/**/libraries

        # File-based project format
        *.iws

        # IntelliJ
        out/

        # JIRA plugin
        atlassian-ide-plugin.xml

        # Python
        *.py[cod] 
        *$py.class 

        # Distribution / packaging 
        .Python build/ 
        develop-eggs/ 
        dist/ 
        downloads/ 
        eggs/ 
        .eggs/ 
        lib/ 
        lib64/ 
        parts/ 
        sdist/ 
        var/ 
        wheels/ 
        *.egg-info/ 
        .installed.cfg 
        *.egg 
        *.manifest 
        *.spec 

        # Installer logs 
        pip-log.txt 
        pip-delete-this-directory.txt 

        # Unit test / coverage reports 
        htmlcov/ 
        .tox/ 
        .coverage 
        .coverage.* 
        .cache 
        .pytest_cache/ 
        nosetests.xml 
        coverage.xml 
        *.cover 
        .hypothesis/ 

        # Jupyter Notebook 
        .ipynb_checkpoints 

        # pyenv 
        .python-version 

        # celery 
        celerybeat-schedule.* 

        # SageMath parsed files 
        *.sage.py 

        # Environments 
        .env 
        .venv 
        env/ 
        venv/ 
        ENV/ 
        env.bak/ 
        venv.bak/ 

        # mkdocs documentation 
        /site 

        # mypy 
        .mypy_cache/ 

        # Sublime Text
        *.tmlanguage.cache 
        *.tmPreferences.cache 
        *.stTheme.cache 
        *.sublime-workspace 
        *.sublime-project 

        # sftp configuration file 
        sftp-config.json 

        # Package control specific files Package 
        Control.last-run 
        Control.ca-list 
        Control.ca-bundle 
        Control.system-ca-bundle 
        GitHub.sublime-settings 

        # Visual Studio Code
        .vscode/* 
        !.vscode/settings.json 
        !.vscode/tasks.json 
        !.vscode/launch.json 
        !.vscode/extensions.json 
        .history
        ```

    - Berkas `.gitignore` adalah sebuah berkas konfigurasi yang digunakan dalam repositori Git untuk menentukan berkas-berkas dan direktori-direktori yang harus diabaikan oleh Git.
    - Berkas-berkas yang tercantum di dalam `.gitignore` **tidak** akan dimasukkan ke dalam versi kontrol Git.
    - Berkas ini berfungsi karena ada berkas-berkas yang tidak perlu dilacak oleh Git, seperti berkas-berkas yang dihasilkan oleh proses kompilasi, berkas sementara, atau berkas konfigurasi pribadi.

4. Lakukan `add`, `commit`, dan `push` dari direktori repositori lokal.

**Catatan:**

- Repositori **`shopping-list`** yang baru saja kamu buat akan menjadi landasan untuk tutorial-tutorial berikutnya. **Repositori ini akan terus digunakan** dan berkembang seiring tutorial yang kamu ikuti.
- Pada akhir semester, kamu akan melihat bahwa repositori tutorial ini telah berkembang menjadi aplikasi yang utuh, buatan kamu sendiri. Sehingga, kamu bisa saja memasukkan ini ke dalam portofiolio kamu!
- Oleh karena itu, pastikan kamu mengelola repositori ini dengan baik dan mengikuti tutorial-tutorial selanjutnya untuk mengembangkan kemampuan kamu dalam pemrograman berbasis platform.

## Tutorial: Pembuatan Akun dan *Deployment* Adaptable.io

1. Buatlah akun [Adaptable.io] menggunakan akun GitHub yang digunakan untuk membuat proyek `shopping_list`.
2. Jika sudah *login*, silakan tekan tombol `New App`. Pilih `Connect an Existing Repository`.
3. Hubungkan [Adaptable.io] dengan GitHub dan pilih `All Repositories` pada proses instalasi.
4. Pilihlah repositori proyek `shopping_list` sebagai basis aplikasi yang akan di-*deploy*. Pilih *branch* yang ingin dijadikan sebagai *deployment branch*.
5. Pilihlah `Python App Template` sebagai *template* *deployment*.
6. Pilih `PostgreSQL` sebagai tipe basis data yang akan digunakan.
7. Sesuaikan versi Python dengan spesifikasi aplikasimu. Untuk mengeceknya, nyalakan *virtual environment* dan jalankan perintah `python --version`.
8. Pada bagian `Start Command` masukkan perintah `python manage.py migrate && gunicorn shopping_list.wsgi`.
9. Masukkan nama aplikasi yang juga akan menjadi nama *domain* situs web aplikasimu.
10. Centang bagian `HTTP Listener on PORT` dan klik `Deploy App` untuk memulai proses *deployment* aplikasi.

## Akhir Kata

Selamat, ya! Kamu sudah menyelesaikan tutorial tentang penggunaan Git, GitHub, instalasi IDE,  pengembangan proyek dengan Django, dan *deployment* ke Adaptable.io.

Ke depannya, kalau sedang mengerjakan lab, jangan grogi sama banyaknya materi, ya. Santai saja, ini bukan lomba *sprint* kok; pelan-pelan saja, pasti bisa. Kode-kode itu tidak harus langsung masuk ke otak, tapi yang penting dimengerti, kan? Jadi, **jangan sampai asal *copy-paste* tanpa mengerti** ya, nanti jadi bingung sendiri. Kalau memang buntu, jangan malu untuk bertanya ke teman atau asisten dosen. Asisten dosen sudah pasti siap bantuin, kok. Jadi, semangat terus dan nikmati prosesnya. *Good luck!*

## Referensi Tambahan

- [Bahas Hosting (Lagi)](https://determinedguy.github.io/cecoret/hosting-alternatives/)
- [About pull request merges](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/incorporating-changes-from-a-pull-request/about-pull-request-merges)
- [Resolving a merge conflict on GitHub](https://docs.github.com/en/pull-requests/collaborating-with-pull-requests/addressing-merge-conflicts/resolving-a-merge-conflict-on-github)

## Kontributor

- Kevin Alexander
- Ivan Rabbani Cezeliano
- Fariz Eda
- Dafi Nafidz Radhiyya

## Credits

Tutorial ini dikembangkan berdasarkan [PBP Ganjil 2023](https://github.com/pbp-fasilkom-ui/ganjil-2023) dan [PBP Genap 2023](https://github.com/pbp-fasilkom-ui/genap-2023) yang ditulis oleh Tim Pengajar Pemrograman Berbasis Platform 2023. Segala tutorial serta instruksi yang dicantumkan pada repositori ini dirancang sedemikian rupa sehingga mahasiswa yang sedang mengambil mata kuliah Pemrograman Berbasis Platform dapat menyelesaikan tutorial saat sesi lab berlangsung.

[GitHub]: https://github.com/
[Adaptable.io]: https://adaptable.io/
