---
sidebar_label: Tutorial 9
sidebar_position: 11
Path: docs/tutorial-9
---

# Tutorial 9: Flutter Deployment dengan GitHub Actions dan Microsoft App Center

Pemrograman Berbasis Platform (CSGE602022) — diselenggarakan oleh Fakultas Ilmu Komputer Universitas Indonesia, Semester Ganjil 2023/2024

---

## Tujuan Pembelajaran

Setelah menyelesaikan tutorial ini, mahasiswa diharapkan untuk dapat:

- Memahami konsep *continuous integration* dan *continuous deployment*.
- Memahami konsep dan penggunaan *GitHub Actions*.
- Memahami konsep dan penggunaan *Microsoft App Center*.
- Menerapkan *continuous integration* dan *continuous deployment* pada aplikasi Flutter menggunakan *GitHub Actions* dan *Microsoft App Center*.
- Menerapkan *GitHub Actions* untuk melakukan *build* dan *release* aplikasi Flutter secara otomatis.

## Pengenalan CI/CD

CI/CD, singkatan dari Continuous Integration dan Continuous Deployment, merupakan konsep penting dalam pengembangan perangkat lunak yang terkait dengan GitHub Actions. Konsep ini memberikan cara untuk mengotomatisasi dan meningkatkan kualitas serta kecepatan dalam pengembangan perangkat lunak.

Continuous Integration (CI) berfokus pada penggabungan (integrasi) perubahan kode secara terus-menerus ke dalam repositori bersama oleh anggota tim. Saat seorang pengembang melakukan perubahan pada kode dan mengirimkannya ke repositori (seperti yang kita lakukan dengan GitHub), sistem CI akan secara otomatis menjalankan serangkaian tes dan verifikasi untuk memastikan bahwa perubahan tersebut tidak merusak atau mengganggu fungsionalitas yang sudah ada. Dengan kata lain, CI membantu mengidentifikasi masalah lebih awal dalam siklus pengembangan.

Continuous Deployment (CD), pada gilirannya, melibatkan otomatisasi untuk merilis (deployment) perubahan kode yang telah melewati proses CI ke lingkungan produksi atau pengujian. Ketika perubahan kode dinyatakan aman setelah melalui serangkaian pengujian CI, CD memungkinkan untuk menyebarkan perubahan tersebut secara otomatis ke server atau lingkungan lainnya tanpa perlu campur tangan manual. Ini membantu mempercepat proses pengembangan dan meningkatkan kecepatan dalam merespons perubahan kebutuhan bisnis.

Ketika kita menggunakan GitHub Actions dalam CI/CD, setiap kali ada perubahan pada repositori, GitHub Actions dapat memicu alur kerja CI untuk menjalankan pengujian dan verifikasi. Jika semuanya berhasil, alur kerja CD dapat diaktifkan untuk merilis perubahan tersebut ke *environment* produksi atau pengujian.

Dengan menggunakan CI/CD, tim pengembang dapat memastikan bahwa perubahan-perubahan yang dilakukan tidak merusak kualitas atau kinerja aplikasi. Ini juga mempercepat waktu rilis produk dan meningkatkan efisiensi dalam pengelolaan siklus hidup perangkat lunak secara keseluruhan.

Berikut adalah visualisasi diagram alur CI/CD:

![Diagram CI/CD](https://cdn.discordapp.com/attachments/923523971226435584/1175755032008872006/CICD20V4.png?ex=656c6234&is=6559ed34&hm=26b3b31e2835db4a2a893ee5cae3b956ccf94018e4052874f36c4bcaa6071d7b&)

## Pengenalan Github Actions

GitHub Actions adalah fitur yang disediakan oleh GitHub untuk memungkinkan otomatisasi dalam siklus pengembangan perangkat lunak. Dengan kata lain, GitHub Actions memungkinkan kita untuk membuat dan menyesuaikan alur kerja otomatis (*workflows*) untuk menjalankan tugas-tugas tertentu setiap kali ada perubahan pada repositori GitHub.

*Workflows* ini bisa diatur untuk menjalankan berbagai tindakan atau skrip otomatis, seperti melakukan pengujian (*testing*), membangun (*building*) aplikasi, atau merilis (*releasing*) versi baru. Tujuannya adalah untuk membantu tim pengembang mengotomatiskan proses-proses ini, sehingga mereka dapat fokus pada penulisan kode dan pengembangan fitur tanpa harus terlalu khawatir dengan langkah-langkah administratif.

Misalnya, ketika ada perubahan kode di repositori GitHub, GitHub Actions dapat secara otomatis menjalankan alur kerja yang telah Anda tentukan. Alur kerja tersebut dapat mencakup langkah-langkah seperti menguji apakah perubahan tersebut tidak merusak fungsionalitas yang ada, membangun aplikasi yang baru, dan bahkan merilis versi baru jika diperlukan.

Penting untuk dicatat bahwa GitHub Actions memanfaatkan file konfigurasi khusus (biasanya dengan nama `.github/workflows/nama-file.yml`) pada repositori. File ini berisi deskripsi langkah-langkah yang harus dijalankan oleh GitHub Actions.

Dengan GitHub Actions, kolaborasi dalam pengembangan perangkat lunak dapat menjadi lebih efisien karena banyak tugas dapat diotomatisasi. Hal ini memberikan fleksibilitas tambahan kepada pengembang dan tim untuk menyesuaikan alur kerja mereka sesuai kebutuhan proyek.

Berikut adalah contoh *workflow* sederhana yang dapat kita gunakan untuk membangun dan menguji kode aplikasi berbasis JavaScript dan menggunakan Yarn *package manager*.
  
  ```yaml
  name: Build and test

  on:
    push:
      branches:
        - main

  jobs:
    build:
      runs-on: ubuntu-latest
      steps:
        - uses: actions/checkout@v4
        - name: Install dependencies
          run: yarn install
        - name: Build
          run: yarn build

    test:
      runs-on: ubuntu-latest
      steps:
        - uses: actions/checkout@v4
        - uses: actions/setup-node@v4
          with:
            node-version: 16
        - name: Install dependencies
          run: yarn install
        - name: Run tests
          run: yarn test
  ```

## Pengenalan Microsoft App Center

Microsoft App Center merupakan layanan *cloud* yang menyediakan berbagai macam fitur untuk memudahkan proses *build*, *test*, *release*, dan *monitoring* aplikasi. Layanan ini dapat digunakan untuk berbagai macam platform, seperti Android, iOS, Windows, macOS, dan lain-lain. Pada tutorial ini, kita akan menggunakan layanan ini untuk melakukan *build* dan *release* aplikasi Flutter secara otomatis.

Microsoft App Center menyediakan banyak sekali fitur keren secara gratis seperti *continuous integration*, *UI testing*, *continuous delivery*, laporan *crash* dan error dari aplikasi yang ditampilkan secara detail bagaimana log errornya, dan fitur Analytics. Kali ini, kita tidak akan memakai seluruh fitur yang tersedia karena Flutter belum didukung secara resmi oleh App Center. Saat ini, bahasa dan *framework* yang baru didukung resmi adalah Kotlin, Java, React Native, Xamarin, dan Unity. Pada tutorial kali ini, kita akan fokus ke pembuatan dan perilisan aplikasi pada App Center saja.Untuk lebih jelasnya, kamu dapat membaca [dokumentasi App Center](https://docs.microsoft.com/en-us/appcenter/) secara lebih lanjut.

## Tutorial: Pengaturan Dasar pada App Center

Pada tutorial ini, kamu akan men-*deploy* aplikasi Flutter yang telah kalian buat pada tutorial-tutorial sebelumnya ke App Center. Berikut ini merupakan tahap awal dalam proses *deployment* aplikasi.

1. Buatlah akun [App Center](https://appcenter.ms/) menggunakan akun GitHub kamu.

  ![Halaman Registrasi Visual Studio App Center](https://cdn.discordapp.com/attachments/987024899296088125/1174745256567263282/halaman_registrasi_app_center.png?ex=6568b5c6&is=655640c6&hm=352398be2a5f97a12f3d6a2f559d65e7797abaffc7c466a36c5435a46012f0a9&)

2. Buatlah organisasi baru dengan mengakses menu *Add new* -> *Add new organization*. Isi nama organisasi yang kamu inginkan.

  ![Petunjuk Add Organization](https://cdn.discordapp.com/attachments/987024899296088125/1174745257078964234/petunjuk_add_organization.png?ex=6568b5c6&is=655640c6&hm=0b329d563fde78dcb3aacfd4800048dc3f8c3883cf69e9873788440fa4784fb6&)

3. Buatlah *slot* aplikasi baru dengan menekan tombol `Add app`.

  ![Petunjuk Add App](https://cdn.discordapp.com/attachments/987024899296088125/1174745257494204446/petunjuk_add_app.png?ex=6568b5c7&is=655640c7&hm=c2be80e2a5e3af64fb5e19991d64c115c1113b6a65c08addd3d02b0457681588&)

4. Isi nama aplikasi dengan `Shopping List`. Kamu tidak perlu untuk memilih tipe rilis. Pilih `Android` sebagai OS dan `Java / Kotlin` sebagai platform.

  ![Pendaftaran App](https://cdn.discordapp.com/attachments/987024899296088125/1174745257884266597/pendaftaran_app.png?ex=6568b5c7&is=655640c7&hm=fecefbf76dcca3f1becba71801f3e2e62ecf5c269965ccec65f294319ef19ab5&)

5. Buka menu *Distribute* dan buka menu *Groups*.

  ![Petunjuk Add Group](https://cdn.discordapp.com/attachments/987024899296088125/1174775000784453783/petunjuk_add_group.png?ex=6568d17a&is=65565c7a&hm=a6dc96491190ee10685b41148a12d1b4f242d1650f674b08e15b3de30b378c8f&)

6. Buatlah grup baru dengan menekan tombol tambah. Berikan nama `Public` dan berikan akses publik dengan mengubah toggle pada `Allow public access`. Tekan tombol `Create Group` untuk membuat grup baru. Hal ini kita lakukan agar APK yang nantinya dibuat oleh App Center dapat diakses secara publik.

  ![Pembuatan Public Group](https://cdn.discordapp.com/attachments/987024899296088125/1174774965669740716/pembuatan_public_group.png?ex=6568d172&is=65565c72&hm=e4a286bc9472a991b4868500c1f51eb9d7a96dd210c48a8004f050b6ba72681f&)

Jika kamu menggunakan organisasi untuk mengorganisasi kode proyekmu, maka ikuti langkah tambahan berikut. Langkah-langkah ini berfungsi untuk memberikan akses repositori kepada App Center pada GitHub.

1. Buka situs [Authorized OAuth Apps](https://github.com/settings/applications) dengan akun yang terdaftar pada organisasi yang memiliki kode proyek.

2. Klik `App Center`.

3. Cari nama organisasi yang memiliki kode proyekmu, lalu klik tombol `Grant` untuk memberikan akses organisasi kepada App Center.

Sampai sini, kamu telah melakukan pengaturan dasar pada App Center. Selanjutnya, kamu akan melakukan pengaturan skrip dan pengesahan (*sign*) pada proyek aplikasi Flutter.

## Tutorial: Pengaturan Dasar Pengesahan Aplikasi Flutter

Untuk publikasi aplikasi pada App Center, aplikasi Flutter harus ditandatangani atau disahkan menggunakan *key* agar aplikasi yang dirilis dijamin keabsahannya. Oleh karena itu, kita akan membuat *key* untuk aplikasi dan mengatur automasi agar skrip CI/CD (baik yang ada pada GitHub Actions maupun App Center) dapat berjalan dengan baik.

1. Buatlah sebuah *keystore*.

    Untuk pengguna Mac OS atau Linux, jalankan perintah berikut pada Terminal.

    ```bash
    keytool -genkey -v -keystore ~/release-keystore.jks -keyalg RSA -keysize 2048 -validity 10000 -alias release
    ```

    Untuk pengguna Windows, jalankan perintah berikut pada Command Prompt.

    ```bash
    keytool -genkey -v -keystore %userprofile%\release-keystore.jks -storetype JKS -keyalg RSA -keysize 2048 -validity 10000 -alias release
    ```

    Perintah ini berguna untuk menyimpan *keystore file* dengan nama `release-keystore.jks` di direktori *home* kamu dengan alias `release`. Pindahkan berkas tersebut ke dalam *root folder* proyek aplikasi. Apabila Terminal atau Command Prompt tidak mengenali perintah `keytool`, silakan mengikuti panduan tambahan pada bagian *Note* di [laman web resmi flutter bagian *Create an upload keystore*](https://docs.flutter.dev/deployment/android#create-an-upload-keystore) untuk memasukkan perintah `keytool` ke dalam *environment path*.

3. Tambahkan sintaks berikut pada file `.gitignore` yang ada pada *root folder* proyek aplikasi agar *keystore* tidak dihitung masuk sebagai berkas yang ada pada repositori Git. Hal ini dilakukan karena *keystore* merupakan berkas yang rahasia dan perlu dijaga selayaknya kata sandi sebuah akun.

    ```yaml
    # Remember to never publicly share your keystore.
    # See https://flutter.dev/docs/deployment/android#reference-the-keystore-from-the-app
    *.keystore
    *.jks
    ```

4. Buka berkas `/android/app/build.gradle` dan cari bagian `buildTypes`.

    ```gradle
    buildTypes {
         release {
             // TODO: Add your own signing config for the release build.
             // Signing with the debug keys for now,
             // so `flutter run --release` works.
             signingConfig signingConfigs.debug
         }
    }
    ```

    Ubahlah bagian tersebut menjadi seperti berikut.

    ```gradle
    signingConfigs {
         release {
                 storeFile file("../../release-keystore.jks")
                 storePassword = "$System.env.KEY_PASSWORD"
                 keyAlias = "release"
                 keyPassword = "$System.env.KEY_PASSWORD"
         }
    }
     buildTypes {
         release {
             signingConfig signingConfigs.release
         }
    }
    ```

Sampai sini, kamu sudah melakukan pengaturan dasar untuk pengesahan aplikasi. Selanjutnya, kamu akan melakukan modifikasi skrip GitHub Actions dan pembuatan skrip baru untuk membangun berkas aplikasi pada App Center.

## Tutorial: Pembuatan Skrip GitHub Actions

1. Hasilkan sebuah *base64 string* sebagai representasi dari *keystore file* yang akan kita simpan sebagai *environment variable* nantinya.

    Jalankan perintah `openssl base64 -in release-keystore.jks` pada *root folder* untuk menghasilkan *base64 string*. Simpan *string* yang dihasilkan untuk sementara waktu.
    
    Berikut adalah contoh hasil yang didapatkan setelah menjalankan perintahnya.

    ![Example Openssl](https://i.ibb.co/3kmxky1/Screenshot-2023-11-16-213743.jpg)

2. Buatlah *repository secrets* pada repositori GitHub dengan spesifikasi sebagai berikut.

    i. `GH_TOKEN` berisi GitHub *(Personal Access) Token* dari admin repositori untuk kepentingan *automated release*.

    ii. `KEY_JKS` berisi `base64` *string* dari *keystore file* yang telah kamu buat sebelumnya.

    iii. `KEY_PASSWORD` berisi kata sandi yang kamu gunakan saat kamu membuat *keystore file*.

3. Jika belum ada, buatlah folder `.github/workflows` pada root aplikasi.

4. Buatlah tiga berkas baru pada folder `.github/workflows` dengan spesifikasi berikut:
    
    > **Diasumsikan** branch `staging` digunakan untuk menampung kode aplikasi sebelum rilis dan branch `main` digunakan untuk perilisan kode aplikasi.
   
    i. `staging.yml`; berfungsi untuk mengecek apakah *codebase* yang ada pada *branch* `staging` bebas dari *error* saat melakukan `flutter analyze`. Skrip ini hanya di-*trigger* saat ada *commit* pada *branch* `staging`.

    ```yaml
    name: Staging

    # Controls when the workflow will run
    on:
      # Triggers the workflow on push or pull request events but only for the develop branch
      push:
        branches: [staging]
      pull_request:
        branches: [staging]
    
    # A workflow run is made up of one or more jobs that can run sequentially or in parallel
    jobs:
      # This workflow contains a single job called "build"
      build:
        name: Analyze
        # The type of runner that the job will run on
        runs-on: ubuntu-latest
        steps:
          - name: Checkout the code
            uses: actions/checkout@v4
    
          - name: Setup Java
            uses: actions/setup-java@v3
            with:
              distribution: 'zulu'
              java-version: '11'
    
          - name: Setup Flutter
            uses: subosito/flutter-action@v2
            with:
              channel: 'stable'
          
          - name: Get packages
            run: flutter pub get
            
          - name: Analyze
            run: flutter analyze
    ```
    
    ii. `pre-release.yml`; berfungsi untuk mengecek apakah proses *build* aplikasi dapat berjalan tanpa *error*. Jika tidak ada *error*, file APK dapat diakses sebagai *artifact*. Skrip ini hanya di-*trigger* saat ada *pull request* dari *branch* `staging` ke *branch* `main`.

    ```yaml
    name: Pre-Release

    # Controls when the workflow will run
    on: 
      # Triggers the workflow on pull request events but only for the main branch
      pull_request:
        branches: [main]
      
    # A workflow run is made up of one or more jobs that can run sequentially or in parallel
    jobs:
      # This workflow contains a single job called "Build and Pre-Release APK"
      releases:
        name: Build and Pre-Release APK
        # The type of runner that the job will run on
        runs-on: ubuntu-latest
        steps:
          - name: Checkout the code
            uses: actions/checkout@v4
    
          - name: Setup Java
            uses: actions/setup-java@v3
            with:
              distribution: 'zulu'
              java-version: '11'
    
          - name: Setup Flutter
            uses: subosito/flutter-action@v2
            with:
              channel: 'stable'
          
          - name: Get packages
            run: flutter pub get
    
          - name: Generate Java keystore
            env: 
              KEY_JKS: ${{ secrets.KEY_JKS }}
            run: echo "$KEY_JKS" | base64 --decode > release-keystore.jks 
                  
          - name: Build APK
            env:
              KEY_PASSWORD: ${{ secrets.KEY_PASSWORD }}
            run: flutter build apk --split-per-abi
          
          - name: Pre-release APK by uploading it to Artifacts
            uses: actions/upload-artifact@v3
            with:
              name: APKS
              path: build/app/outputs/flutter-apk/*.apk
    ```

    iii. `release.yml`; berfungsi untuk melakukan proses *build* aplikasi dan perilisan aplikasi sebagai *Releases* pada repositori GitHub. Skrip ini hanya di-*trigger* saat ada *commit* pada *branch* `main`.

    ```yaml
    # This is a basic workflow to help you get started with Actions
    name: Release

    # Controls when the workflow will run
    on: 
      # Triggers the workflow on push events but only for the main branch
      push:
        branches: [main]
      
    # A workflow run is made up of one or more jobs that can run sequentially or in parallel
    jobs:
      # This workflow contains a single job called "Build and Release APK"
      releases:
        name: Build and Release APK
        # The type of runner that the job will run on
        runs-on: ubuntu-latest
        steps:
          - name: Checkout the code
            uses: actions/checkout@v4
          
          - name: Get version from pubspec.yaml
            id: version
            run: echo "::set-output name=version::$(grep "version:" pubspec.yaml | cut -c10-)"
    
          - name: Setup Java
            uses: actions/setup-java@v3
            with:
              distribution: 'zulu'
              java-version: '11'
    
          - name: Setup Flutter
            uses: subosito/flutter-action@v2
            with:
              channel: 'stable'
          
          - name: Get packages
            run: flutter pub get
                  
          - name: Generate Java keystore
            env: 
              KEY_JKS: ${{ secrets.KEY_JKS }}
            run: echo "$KEY_JKS" | base64 --decode > release-keystore.jks 
                  
          - name: Build APK
            env:
              KEY_PASSWORD: ${{ secrets.KEY_PASSWORD }}
            run: flutter build apk --split-per-abi
    
          - name: Get current date
            id: date
            run: echo "::set-output name=date::$(TZ='Asia/Jakarta' date +'%A %d-%m-%Y %T WIB')"
          
          - name: Release APK
            uses: ncipollo/release-action@v1
            with:
              allowUpdates: true
              artifacts: "build/app/outputs/flutter-apk/*.apk"
              body: "Published at ${{ steps.date.outputs.date }}"
              name: "v${{ steps.version.outputs.version }}"
              token: ${{ secrets.GH_TOKEN }}
              tag: ${{ steps.version.outputs.version }}
    ```

5. Simpan file tersebut dan *push* ke repositori. Cek apakah aplikasi berhasil dibuat dan dirilis oleh GitHub Actions secara otomatis.

Apabila aplikasi kamu berhasil dibuat dan dirilis otomatis, maka selamat! Sampai sini, kita sudah mengamankan *workflow* pada GitHub. Selanjutnya, kita akan membuat skrip baru untuk *build* pada App Center dan mengonfigurasi aplikasi secara lebih lanjut pada situs web App Center.

Berikut adalah struktur aplikasi Flutter kamu setelah melakukan tutorial ini.

  ![Struktur file](https://i.ibb.co/4pD8Jbw/Screenshot-2023-11-16-205934.png)

## Tutorial: Penambahan Skrip CI/CD untuk App Center

Pada bagian ini, kita akan menambahkan skrip *continuous integration* dan *continuous delivery* pada repositori aplikasi Flutter agar App Center dapat membangun dan menghasilkan berkas APK aplikasi secara otomatis.

1. Buka folder `/android/app`.

2. Buatlah file baru dengan nama `appcenter-post-clone.sh` dan isi file tersebut dengan kode berikut.

    ```bash
    #!/usr/bin/env bash
    # Place this script in project/android/app/

    cd ..

    # fail if any command fails
    set -e
    # debug log
    set -x

    cd ..
    git clone -b beta https://github.com/flutter/flutter.git
    export PATH=`pwd`/flutter/bin:$PATH

    flutter channel stable
    flutter doctor

    echo "Installed flutter to `pwd`/flutter"

    # export keystore for release
    echo "$KEY_JKS" | base64 --decode > release-keystore.jks

    # build APK
    # if you get "Execution failed for task ':app:lintVitalRelease'." error, uncomment next two lines
    # flutter build apk --debug
    # flutter build apk --profile
    flutter build apk --release

    # copy the APK where AppCenter will find it
    mkdir -p android/app/build/outputs/apk/; mv build/app/outputs/apk/release/app-release.apk $_
    ```

3. Buka file `/android/.gitignore` dan ubahlah file tersebut menjadi berikut. Hal ini dilakukan agar App Center dapat mendeteksi aplikasi sebagai aplikasi Android.

    ```yaml
    # add comment for app center
    # gradle-wrapper.jar
    # /gradlew
    # /gradlew.bat
    /.gradle
    /captures/
    /local.properties
    GeneratedPluginRegistrant.java

    # Remember to never publicly share your keystore.
    # See https://flutter.dev/docs/deployment/android#reference-the-keystore-from-the-app
    key.properties
    **/*.keystore
    **/*.jks
    ```

4. Simpan file tersebut dan *push* ke repositori. Pastikan skrip ini dan `.gitignore` yang baru telah ter-*push* sampai ke *branch* `main`.

Setelah selesai membuat skrip, kita akan mengonfigurasi aplikasi pada App Center agar dapat dibuat dan dirilis secara otomatis.

## Tutorial: Konfigurasi Lanjutan pada App Center

1. Buka situs web App Center dan buka proyek aplikasi.

    ![lanjutan_langkah_1](https://i.ibb.co/58WpRNT/langkah-1.png)
   
2. Buka menu `Build` dan pilih GitHub sebagai servis penyedia repositori aplikasi. Pilihlah repositori aplikasi proyek kelompok kamu.

    ![lanjutan_langkah_2](https://i.ibb.co/Rv3rFJV/langkah-2.png)
   
3. Buka branch utama kamu (`main` atau `master`, silakan disesuaikan) dan klik tombol `Configure`.

    ![lanjutan_langkah_3](https://i.ibb.co/25SGyMv/langkah-3.png)
   
4. Ikuti pengaturan berikut.

   ![lanjutan_langkah_4_1](https://i.ibb.co/mCQ8dR2/langkah-4-1.png)
   ![lanjutan_langkah_4_2](https://i.ibb.co/wYfJG19/langkah-4-2.png)
   ![lanjutan_langkah_4_3](https://i.ibb.co/L1fPRM6/langkah-4-3.png)
   
   Catatan:
     - Jangan lupa untuk mengganti `KEY_JKS` dan `KEY_PASSWORD` dengan _value_ yang sebenarnya.
     - Jangan lupa untuk membuat grup `Public` untuk distribusi aplikasi secara publik.
     - Salinlah tautan _build badge_ dengan format `Markdown` dan tempelkan ke `README.md`.

       ![lanjutan_langkah_4_4](https://i.ibb.co/LvpM03f/advance.png)

       ![lanjutan_langkah_4_5](https://i.ibb.co/xfpKLDh/develop.png)
       
5. Klik tombol `Save & Build` untuk menyimpan konfigurasi dan memulai proses _build_ perdana.

    Kamu dapat mengecek tautan publik dari publikasi aplikasi pada App Center melalui menu *Distribute* -> *Groups* -> *Public*.

    ![tautan_publikasi_aplikasi](https://i.ibb.co/5Y2K3NB/distribute.png)

    Berikut adalah contoh tampilan App Center ketika tautan publik dari publikasi apilkasi diakses oleh pengguna.

    ![window_instalasi_aplikasi](https://cdn.discordapp.com/attachments/987024899296088125/1175025198546829382/windows_install.png?ex=6569ba7e&is=6557457e&hm=05cfb7004b48f30f2bf19a5b2d3cb10ddd3cc8d30111fb399ce76dbb462ce7f3&)

6. Salinlah tautan publik dari publikasi aplikasi dan tempelkan ke `README.md`

## Akhir Kata

Selamat, kamu telah berhasil untuk men-*deploy* aplikasi Flutter kamu ke App Center. Kamu dapat mengecek aplikasi yang telah kamu *deploy* dengan mengunduh berkas APK dari App Center dan menginstalnya pada ponsel pintar kamu.

Dan kita, sudah resmi, menamatkan tutorial PBP semester ganjil 2023-2024! Terima kasih telah mengikuti dan mengerjakan semua tutorial PBP semester ganjil 2023-2024. Tim pengajar ingin mengucapkan apresiasi kepada semua mahasiswa yang telah berpartisipasi dan berkontribusi dalam mata kuliah ini. Tim pengajar melihat usaha dan dedikasi yang telah kalian tunjukkan dalam menghadapi tantangan pengembangan aplikasi multiplatform pada mata kuliah ini.

Selama proses lab dan tugas, kita telah menggali konsep dan prinsip dasar yang mendasari pengembangan aplikasi web dan *mobile* menggunakan Django dan Flutter. Kalian telah mempelajari tentang arsitektur, fitur, dan alat yang dapat membantu dalam membangun aplikasi yang tangguh dan responsif di kedua platform ini.

Tim pengajar berharap lab dan tugas yang diberikan dapat memberikan pemahaman yang lebih mendalam tentang potensi dan tantangan dalam pengembangan aplikasi multiplatform serta memberikan kalian keterampilan yang berguna dan dapat diterapkan dalam karir kalian sebagai pengembang perangkat lunak.

Namun, pembelajaran tidak berhenti di sini. Dunia pengembangan terus berkembang dengan cepat, dan penting untuk tetap mengikuti perkembangan terbaru dalam industri ini. Tim pengajar mendorong kalian untuk terus belajar dan menjaga keterampilan kalian tetap relevan dengan membaca referensi lainnya, mengikuti kursus lanjutan, dan mengambil bagian dalam proyek-proyek nyata.

Akhir kata, ingatlah bahwa pengembangan aplikasi multiplatform adalah bidang yang menarik dan penuh potensi. Teruslah eksplorasi dan berinovasi, dan tim pengajar yakin kalian memiliki masa depan yang cerah sebagai pengembang perangkat lunak. Terima kasih dan semoga sukses dalam perjalanan kalian!

Do zobaczenia i powodzenia!

## Kontributor

- Fadhlan Hasyim
- Muhammad Vicky Maulana
- Stenly Yosua Saputra
- Steven Yosua Saputra

## Credits

Tutorial ini dikembangkan berdasarkan [entri blog](https://determinedguy.github.io/cecoret/flutter-at-app-center/) yang ditulis oleh [Muhammad Athallah](https://github.com/determinedguy/). Segala tutorial serta instruksi yang dicantumkan pada repositori ini dirancang sedemikian rupa sehingga mahasiswa yang sedang mengambil mata kuliah Pemrograman Berbasis Platform dapat menyelesaikan tutorial saat sesi lab berlangsung.
