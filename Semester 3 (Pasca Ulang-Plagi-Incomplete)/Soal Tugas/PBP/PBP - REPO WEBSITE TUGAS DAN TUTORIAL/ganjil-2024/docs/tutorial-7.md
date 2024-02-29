---
sidebar_label: Tutorial 7
sidebar_position: 9
Path: docs/tutorial-7
---

# Tutorial 7: Flutter Navigation, Layouts, Forms, and Input Elements

Pemrograman Berbasis Platform (CSGE602022) — diselenggarakan oleh Fakultas Ilmu Komputer Universitas Indonesia, Semester Ganjil 2023/2024

---

## Tujuan Pembelajaran

Setelah menyelesaikan tutorial ini, mahasiswa diharapkan untuk dapat:

- Memahami navigasi dan *routing* dasar pada Flutter.
- Memahami elemen *input* dan *form* pada Flutter.
- Memahami alur pembuatan *form* dan data pada Flutter.
- Memahami dan menerapkan *clean architecture* sederhana

## Navigasi Halaman pada Flutter

Pada saat belajar pengembangan _web_, kalian pasti sudah belajar bahwa dalam sebuah _website_ kita dapat berpindah-pindah halaman sesuai dengan *URL* yang diakses. Hal yang sama juga berlaku pada pengembangan aplikasi, dimana kita juga dapat melakukan perpindahan dari satu 'halaman' ke 'halaman' yang lainnya. Bedanya, pada sebuah aplikasi, yang kita gunakan untuk berpindah bukanlah dengan mengakses *URL* yang berbeda.

Untuk mengimplementasikan navigasi pada Flutter, sebenarnya sudah disediakan sistem yang cukup lengkap untuk melakukan navigasi antar halaman. Salah satu cara yang dapat kita gunakan untuk berpindah-pindah halaman adalah dengan menggunakan _widget_ `Navigator`. _Widget_ `Navigator` menampilkan halaman-halaman yang ada kepada layar seakan sebagai sebuah tumpukan (_stack_). Untuk menavigasi sebuah halaman baru, kita dapat mengakses `Navigator` melalui `BuildContext` dan memanggil fungsi yang ada, seperti misalnya `push()`, `pop()`, serta `pushReplacement()`.

> Note: Di dalam Flutter, layar dan halaman seringkali disebut dengan terminologi *route*.

Berikut adalah penjelasan mengenai beberapa penggunaan `Navigator` yang paling sering dijumpai dalam pengembangan aplikasi.

### Push (`push()`)

```dart
...
    if (item.name == "Tambah Produk") {
        Navigator.push(context,
            MaterialPageRoute(builder: (context) => const ShopFormPage()));
    }
...
```

Method `push()` menambahkan suatu *route* ke dalam *stack* *route* yang dikelola oleh `Navigator`. Method ini menyebabkan *route* yang ditambahkan berada pada paling atas stack, sehingga *route* yang baru saja ditambahkan tersebut akan muncul dan ditampilkan kepada pengguna.

### Pop (`pop()`)

```dart
...
    onPressed: () {
        Navigator.pop(context);
    },
...
```

Method `pop()` menghapus *route* yang sedang ditampilkan kepada pengguna (atau dalam kata lain, *route* yang berada pada paling atas *stack*) dari *stack* *route* yang dikelola oleh Navigator. Method ini menyebabkan aplikasi untuk berpindah dari *route* yang sedang ditampilkan kepada pengguna ke *route* yang berada di bawahnya pada *stack* yang dikelola `Navigator`.

### Push Replacement (`pushReplacement()`)

```dart
...
    onTap: () {
        Navigator.pushReplacement(
        context,
        MaterialPageRoute(
            builder: (context) => MyHomePage(),
        ));
    },
...
```

Method `pushReplacement()` menghapus *route* yang sedang ditampilkan kepada pengguna dan menggantinya dengan suatu *route*. Method ini menyebabkan aplikasi untuk berpindah dari *route* yang sedang ditampilkan kepada pengguna ke suatu *route* yang diberikan. Pada *stack* *route* yang dikelola `Navigator`, *route* lama pada atas *stack* akan digantikan secara langsung oleh *route* baru yang diberikan tanpa mengubah kondisi elemen *stack* yang berada di bawahnya.

Walaupun `push()` dan `pushReplacement()` sekilas terlihat mirip, namun perbedaan kedua *method* tersebut terletak pada apa yang dilakukan kepada *route* yang berada pada atas *stack*. `push()` akan menambahkan *route* baru diatas *route* yang sudah ada pada atas *stack*, sedangkan `pushReplacement()` menggantikan *route* yang sudah ada pada atas *stack* dengan *route* baru tersebut. Penting juga untuk memperhatikan kemungkinan urutan dan isi dari *stack*, karena jika kondisi *stack* kosong serta kita menekan tombol **Back** pada gawai, maka sistem akan keluar dari aplikasi tersebut.

Di samping ketiga method `Navigator` di atas, terdapat juga beberapa method lain yang dapat memudahkan routing dalam pengembangan aplikasi, seperti `popUntil()`, `canPop()`, dan `maybePop()`. Silakan mengeksplorasi method-method tersebut secara mandiri. Untuk mengetahui lebih dalam terkait `Navigator`, kalian dapat membaca dokumentasi yang ada pada tautan berikut: <https://api.flutter.dev/flutter/widgets/Navigator-class.html>

## Input dan Form Pada Flutter

Sama halnya dengan sebuah web, sebuah aplikasi juga dapat berinteraksi dengan pengguna melalui input dan form. Flutter memiliki widget Form yang dapat kita manfaatkan untuk menjadi wadah bagi beberapa *input field widget* yang kita buat. Sama halnya dengan *input field* pada web, Flutter juga memiliki banyak tipe *input field*, salah satunya widget `TextField`.

Untuk mencoba sampel dari widget Form, jalankan perintah berikut:

```bash
flutter create --sample=widgets.Form.1 form_sample
```

Untuk mengetahui lebih lanjut terkait widget Form, dapat dibaca pada tautan berikut: [Flutter Form Class](https://api.flutter.dev/flutter/widgets/Form-class.html.)

## Tutorial: Menambahkan Drawer Menu Untuk Navigasi

Untuk mempermudah navigasi di aplikasi Flutter kita, kita dapat menambahkan *drawer menu*. *Drawer menu* adalah sebuah menu yang muncul dari sisi kiri atau kanan layar. *Drawer menu* biasanya berisi navigasi ke halaman-halaman lain pada aplikasi.

1. Buka proyek yang sebelumnya telah dibuat pada tutorial 6 dengan menggunakan IDE favoritmu.

2. Buatlah berkas baru di dalam direktori baru `widgets` dengan nama `left_drawer.dart`. Tambahkan kode berikut ke dalam berkas `left_drawer.dart`.

    ```dart
    import 'package:flutter/material.dart';

    class LeftDrawer extends StatelessWidget {
      const LeftDrawer({super.key});

      @override
      Widget build(BuildContext context) {
        return Drawer(
          child: ListView(
            children: [
              const DrawerHeader(
                // TODO: Bagian drawer header
              ),
              // TODO: Bagian routing
            ],
          ),
        );
      }
    }
    ```

3. Berikutnya, tambahkan impor untuk halaman-halaman yang kita ingin masukkan navigasinya ke dalam Drawer Menu. Pada contoh ini, kita akan menambahkan navigasi ke halaman `MyHomePage` dan `ShopFormPage`.

    ```dart
    import 'package:flutter/material.dart';
    import 'package:shopping_list/menu.dart';
    // TODO: Impor halaman ShopFormPage jika sudah dibuat
    ```

4. Setelah berhasil impor, kita akan memasukkan routing untuk halaman-halaman yang kita impor ke bagian `TODO: Bagian routing`.

    ```dart
    ...
    ListTile(
      leading: const Icon(Icons.home_outlined),
      title: const Text('Halaman Utama'),
      // Bagian redirection ke MyHomePage
      onTap: () {
        Navigator.pushReplacement(
            context,
            MaterialPageRoute(
              builder: (context) => MyHomePage(),
            ));
      },
    ),
    ListTile(
      leading: const Icon(Icons.add_shopping_cart),
      title: const Text('Tambah Produk'),
      // Bagian redirection ke ShopFormPage
      onTap: () {
        /*
        TODO: Buatlah routing ke ShopFormPage di sini,
        setelah halaman ShopFormPage sudah dibuat.
        */
      },
    ),
    ...
    ```

5. Selanjutnya, kita akan menghias drawer kita dengan memasukkan drawer header di `TODO: Bagian drawer header`.

    ```dart
    ...
    const DrawerHeader(
      decoration: BoxDecoration(
        color: Colors.indigo,
      ),
      child: Column(
        children: [
          Text(
            'Shopping List',
            textAlign: TextAlign.center,
            style: TextStyle(
              fontSize: 30,
              fontWeight: FontWeight.bold,
              color: Colors.white,
            ),
          ),
          Padding(padding: EdgeInsets.all(10)),
          Text("Catat seluruh keperluan belanjamu di sini!",
              // TODO: Tambahkan gaya teks dengan center alignment, font ukuran 15, warna putih, dan weight biasa
              ),
        ],
      ),
    ),
    ...
    ```

6. Selamat, kamu telah berhasil membuat *drawer menu*. Sekarang, masukkan drawer tersebut ke halaman yang ingin kamu tambahkan drawer. Untuk poin ini, kami akan berikan contoh untuk memasukkan ke halaman `menu.dart`.

    ```dart
    ...
    // Impor drawer widget
    import 'package:shopping_list/widgets/left_drawer.dart';
    ...
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          'Shopping List',
        ),
        backgroundColor: Colors.indigo,
        foregroundColor: Colors.white,
      ),
      // Masukkan drawer sebagai parameter nilai drawer dari widget Scaffold
      drawer: const LeftDrawer(),
    ...
    ```

7. Selamat, drawer dan navigasi sudah dibuat secara sempurna. Silakan jalankan program untuk melihat hasilnya. Jangan lupa kerjakan `TODO` yang masih ada **sebelum mengumpulkan tutorial** (tutorial yang dikumpulkan sudah **tidak memiliki** `TODO`). **Jangan lupa** juga tambahkan drawer ke halaman `ShopFormPage` jika halaman tersebut sudah dibuat.

## Tutorial: Menambahkan Form dan Elemen Input

Sekarang, kita akan membuat sebuah form sederhana untuk memasukkan data barang pada aplikasi sehingga nantinya kamu dapat menambahkan data baru untuk ditampilkan.

1. Buat berkas baru pada direktori `lib` dengan nama `shoplist_form.dart`. Tambahkan kode berikut ke dalam berkas `shoplist_form.dart`.

    ```dart
    import 'package:flutter/material.dart';
    // TODO: Impor drawer yang sudah dibuat sebelumnya

    class ShopFormPage extends StatefulWidget {
        const ShopFormPage({super.key});

        @override
        State<ShopFormPage> createState() => _ShopFormPageState();
    }

    class _ShopFormPageState extends State<ShopFormPage> {
        @override
        Widget build(BuildContext context) {
            return Placeholder();
        }
    }
    ```

2. Ubah widget `Placeholder` dengan potongan kode berikut.

    ```dart
    Scaffold(
      appBar: AppBar(
        title: const Center(
          child: Text(
            'Form Tambah Produk',
          ),
        ),
        backgroundColor: Colors.indigo,
        foregroundColor: Colors.white,
      ),
      // TODO: Tambahkan drawer yang sudah dibuat di sini
      body: Form(
        child: SingleChildScrollView(),
      ),
    );
    ```

    Penjelasan Kode:

    1. Widget `Form` berfungsi sebagai wadah bagi beberapa *input field widget* yang nanti akan kita buat.

    2. Widget `SingleChildScrollView` berfungsi untuk membuat *child widget* di dalamnya menjadi *scrollable*.

3. Buat variabel baru bernama `_formKey` lalu tambahkan `_formKey` tersebut ke dalam atribut `key` milik widget `Form`. Atribut `key` akan berfungsi sebagai handler dari form state, validasi form, dan penyimpanan form.

   ```dart
   ...
   class _ShopFormPageState extends State<ShopFormPage> {
       final _formKey = GlobalKey<FormState>();
   ...
   ```
  
   ```dart
   ...
   body: Form(
        key: _formKey,
        child: SingleChildScrollView(),
   ),
   ...
   ```

4. Selanjutnya, kita akan mulai mengisi widget `Form` dengan *field*. Buatlah beberapa variabel untuk menyimpan input dari masing-masing *field* yang akan dibuat.

    ```dart
      ...
      class _ShopFormPageState extends State<ShopFormPage> {
        final _formKey = GlobalKey<FormState>();
        String _name = "";
        int _price = 0;
        String _description = "";
      ...
    ```

5. Buatlah *widget* `Column` sebagai *child* dari `SingleChildScrollView`.

    ```dart
    ...
    body: Form(
          key: _formKey,
          child: SingleChildScrollView(
            child: Column()
          ),
    ...
    ```

6. Buatlah widget `TextFormField` yang dibungkus oleh `Padding` sebagai salah satu *children* dari widget `Column`. Setelah itu, tambahkan atribut `crossAxisAlignment` untuk mengatur alignment *children* dari `Column`.

      ```dart
      ...
      child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Padding(
                padding: const EdgeInsets.all(8.0),
                child: TextFormField(
                  decoration: InputDecoration(
                    hintText: "Nama Produk",
                    labelText: "Nama Produk",
                    border: OutlineInputBorder(
                      borderRadius: BorderRadius.circular(5.0),
                    ),
                  ),
                  onChanged: (String? value) {
                    setState(() {
                      _name = value!;
                    });
                  },
                  validator: (String? value) {
                    if (value == null || value.isEmpty) {
                      return "Nama tidak boleh kosong!";
                    }
                    return null;
                  },
                ),
              ),
      ...
      ```

      Penjelasan Kode:

      1. `onChanged` akan dijalankan setiap ada perubahan isi `TextFormField`.
      2. `validator` akan melakukan validasi isi `TextFormField` dan mengembalikan `String` jika terdapat error.
      3. Terdapat implementasi null-safety pada bagian `String?` dan `value!`. Operator `?` berfungsi untuk menandakan bahwa variabel tersebut boleh berisi `String` atau `null`. Sedangkan operator `!` berfungsi untuk menandakan bahwa variabel tersebut dijamin akan tidak akan berisi `null`.

      Untuk mempelajari lebih dalam mengenai `null-safety`, kalian dapat membaca dokumentasi yang ada pada tautan berikut: [Dart Null Safety](https://dart.dev/null-safety/understanding-null-safety)

7. Buatlah dua `TextFormField` yang dibungkus dengan `Padding` sebagai *child* selanjutnya dari `Column` seperti sebelumnya untuk field `price` dan `description`.

    ```dart
    ...
    Padding(
      padding: const EdgeInsets.all(8.0),
      child: TextFormField(
        decoration: InputDecoration(
          hintText: "Harga",
          labelText: "Harga",
          border: OutlineInputBorder(
            borderRadius: BorderRadius.circular(5.0),
          ),
        ),
        // TODO: Tambahkan variabel yang sesuai
        onChanged: (String? value) {
          setState(() {
            ... = int.parse(value!);
          });
        },
        validator: (String? value) {
          if (value == null || value.isEmpty) {
            return "Harga tidak boleh kosong!";
          }
          if (int.tryParse(value) == null) {
            return "Harga harus berupa angka!";
          }
          return null;
        },
      ),
    ),
    Padding(
      padding: const EdgeInsets.all(8.0),
      child: TextFormField(
        decoration: InputDecoration(
          hintText: "Deskripsi",
          labelText: "Deskripsi",
          border: OutlineInputBorder(
            borderRadius: BorderRadius.circular(5.0),
          ),
        ),
        onChanged: (String? value) {
          setState(() {
            // TODO: Tambahkan variabel yang sesuai
            ... = value!;
          });
        },
        validator: (String? value) {
          if (value == null || value.isEmpty) {
            return "Deskripsi tidak boleh kosong!";
          }
          return null;
        },
      ),
    ),
    ...
    ```

11. Buatlah tombol sebagai *child* selanjutnya dari `Column`. Bungkus tombol ke dalam widget `Padding` dan `Align`. Kali ini kita tidak akan menyimpan data ke dalam *database*, namun kita akan memunculkannya pada *pop-up* yang akan muncul setelah tombol ditekan.

      ```dart
      ...
      Align(
        alignment: Alignment.bottomCenter,
        child: Padding(
          padding: const EdgeInsets.all(8.0),
          child: ElevatedButton(
            style: ButtonStyle(
              backgroundColor:
                  MaterialStateProperty.all(Colors.indigo),
            ),
            onPressed: () {
              if (_formKey.currentState!.validate()) {}
            },
            child: const Text(
              "Save",
              style: TextStyle(color: Colors.white),
            ),
          ),
        ),
      ),
      ...
      ```

## Tutorial: Memunculkan Data

1. Tambahkan fungsi `showDialog()` pada bagian `onPressed()` dan munculkan widget `AlertDialog` pada fungsi tersebut. Kemudian, tambahkan juga fungsi untuk *reset* form.

    ```dart
    ...
    child: ElevatedButton(
              style: ButtonStyle(
                backgroundColor:
                    MaterialStateProperty.all(Colors.indigo),
              ),
              onPressed: () {
                if (_formKey.currentState!.validate()) {
                  showDialog(
                    context: context,
                    builder: (context) {
                      return AlertDialog(
                        title: const Text('Produk berhasil tersimpan'),
                        content: SingleChildScrollView(
                          child: Column(
                            crossAxisAlignment:
                                CrossAxisAlignment.start,
                            children: [
                              Text('Nama: $_name'),
                              // TODO: Munculkan value-value lainnya
                            ],
                          ),
                        ),
                        actions: [
                          TextButton(
                            child: const Text('OK'),
                            onPressed: () {
                              Navigator.pop(context);
                            },
                          ),
                        ],
                      );
                    },
                  );
                _formKey.currentState!.reset();
                }
              },
              child: const Text(
                "Save",
                style: TextStyle(color: Colors.white),
              ),
            ),
    ...
    ```

2. Coba jalankan program kamu, gunakan form yang telah dibuat, dan lihat hasilnya.

## Tutorial: Menambahkan Fitur Navigasi pada Tombol

Sampai sini, kita sudah berhasil membuat suatu *drawer* yang dapat menjalankan fitur navigasi ke halaman lain pada aplikasi, serta suatu halaman *form*. Pada tutorial sebelumnya, kita juga sudah berhasil membuat tiga *button widget* yang dapat melakukan *action* tertentu saat ia ditekan. Sekarang, kita akan menambahkan fitur navigasi pada tombol tersebut sehingga saat ditekan, pengguna akan ditampilkan halaman lain.

1. Pada _widget_ `ShopItem` pada berkas `menu.dart` yang sudah dibuat pada tutorial sebelumnya, akan dibuat agar agar kode yang terletak pada atribut `onTap` dari `InkWell` dapat melakukan navigasi ke *route* lain (tambahkan kode tambahan di bawah kode `ScaffoldMessenger` yang menampilkan *snackbar*).

    ```dart
    ...
      // Area responsif terhadap sentuhan
      onTap: () {
        // Memunculkan SnackBar ketika diklik
        ScaffoldMessenger.of(context)
          ..hideCurrentSnackBar()
          ..showSnackBar(SnackBar(
              content: Text("Kamu telah menekan tombol ${item.name}!")));

        // Navigate ke route yang sesuai (tergantung jenis tombol)
        if (item.name == "Tambah Produk") {
          // TODO: Gunakan Navigator.push untuk melakukan navigasi ke MaterialPageRoute yang mencakup ShopFormPage.
        }

      },
    ...
    ```

    Perhatikan bahwa pada tombol ini, kita menggunakan `Navigator.push()` sehingga pengguna dapat menekan tombol **Back** untuk kembali ke halaman menu. Selain itu, jika kita menggunakan `Navigator.pop()`, maka kita dapat membuat kode dalam program untuk kembali ke halaman menu.

2. Coba jalankan program kamu, gunakan tombol yang telah dibuat fungsionalitas, dan lihatlah apa yang terjadi. Bandingkan dengan apa yang terjadi jika kita melakukan navigasi melalui drawer (tentu saja setelah menyelesaikan seluruh TODO pada drawer).

## Tutorial: *Refactoring File*

Setelah membuat halaman `shoplist_form.dart`, halaman kita sudah semakin banyak. Dengan demikian, mari kita pindahkan halaman-halaman yang sudah dibuat sebelumnya ke dalam satu folder `screens` untuk mempermudah kita ke depannya.

1. Sebelum mulai, pastikan kamu sudah memiliki **ekstensi Flutter terinstal** di IDE atau *text editor* yang kamu gunakan.

2. Buatlah berkas baru dengan nama `shop_card.dart` pada direktori `widgets`.

3. Pindahkan isi widget `ShopItem` pada `menu.dart` ke berkas `widgets/shop_card.dart`.

4. Pastikan untuk mengimpor halaman `shoplist_form.dart` pada berkas `shop_card.dart` dan import halaman `shop_card.dart` pada berkas `menu.dart`.

5. Buatlah folder baru bernama `screens` pada direktori `lib`.

    ![buat folder screens](https://cdn.discordapp.com/attachments/923523971226435584/1170530937885442108/image.png?ex=655960e3&is=6546ebe3&hm=812a7ea28f6f7aec80b0c75222e0f45824ffa2ee1c850480ad78adb13334eb20&)

6. Pindahkan file `menu.dart` dan `shoplist_form.dart` ke dalam folder `screens`. Pastikan pemindahan file dilakukan **melalui IDE atau *text editor* yang memiliki ekstensi atau *plugin* Flutter**, bukan melalui *file manager* biasa (seperti File Explorer atau Finder). Hal ini dilakukan agar IDE atau *text editor* yang kamu gunakan dapat melakukan *refactoring* secara otomatis.

    Tampilan pada Visual Studio Code
    ![pindah file vscode](https://cdn.discordapp.com/attachments/923523971226435584/1170529781599719464/image.png?ex=65595fcf&is=6546eacf&hm=5019998eac2e4b0b8b3d0cb49ab0e14a9829e51fa20ef5860255b57fc56485d6&)

    Tampilan pada Android Studio
    ![pindahkan file](https://cdn.discordapp.com/attachments/923523971226435584/1170530165433061426/image.png?ex=6559602a&is=6546eb2a&hm=27b1aeb2b988193fd2e3bfb034491e6b0a36835bcfb95c7a917016ead5b53a9b&)

Setelah *refactoring file* dilakukan, seharusnya struktur dari direktori `lib` adalah seperti berikut.

![Struktur file di akhir](https://cdn.discordapp.com/attachments/923523971226435584/1170531163769667625/image.png?ex=65596118&is=6546ec18&hm=c6b0bf529735ac9d891f38d88b1e9be3b1e2db741e1f53f443525a4d4162b601&)

## Akhir Kata

Selamat! Kamu telah menyelesaikan Tutorial 7! Semoga dengan tutorial ini, kalian dapat memahami mengenai *navigation*, *forms*, *input*, dan *layouts* dengan baik. 😄

1. Pelajari dan pahami kembali kode yang sudah kamu tuliskan di atas dengan baik. **Jangan lupa untuk menyelesaikan semua TODO yang ada!**

2. Lakukan `add`, `commit` dan `push` untuk memperbarui repositori GitHub.

    ```shell
    git add .
    git commit -m "<pesan_commit>"
    git push -u origin <branch_utama>
    ```

    - Ubah `<pesan_commit>` sesuai dengan keinginan. Contoh: `git commit -m "tutorial 7 selesai"`.
    - Ubah `<branch_utama>` sesuai dengan nama branch utamamu. Contoh: `git push -u origin main` atau `git push -u origin master`.

## Referensi Tambahan

- [Flutter Navigation Basics Cookbook](https://docs.flutter.dev/cookbook/navigation/navigation-basics)
- [Add Drawer to a Screen in Flutter](https://docs.flutter.dev/cookbook/design/drawer)

## Kontributor

- Muhammad Raditya Hanif
- Hugo Sulaiman Setiawan
- Andi Muhamad Dzaky Raihan
- Alek Yoanda Partogi Tampubolon

## Credits

Tutorial ini dikembangkan berdasarkan [PBP Ganjil 2023](https://github.com/pbp-fasilkom-ui/ganjil-2023) dan [PBP Genap 2023](https://github.com/pbp-fasilkom-ui/genap-2023) yang ditulis oleh Tim Pengajar Pemrograman Berbasis Platform 2023. Segala tutorial serta instruksi yang dicantumkan pada repositori ini dirancang sedemikian rupa sehingga mahasiswa yang sedang mengambil mata kuliah Pemrograman Berbasis Platform dapat menyelesaikan tutorial saat sesi lab berlangsung.
