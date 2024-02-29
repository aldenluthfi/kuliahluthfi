---
sidebar_label: Tutorial 8
sidebar_position: 10
Path: docs/tutorial-8
---

# Tutorial 8: Flutter Networking, Authentication, and Integration

Pemrograman Berbasis Platform (CSGE602022) — diselenggarakan oleh Fakultas Ilmu Komputer Universitas Indonesia, Semester Ganjil 2023/2024

---

## Tujuan Pembelajaran

Setelah menyelesaikan tutorial ini, mahasiswa diharapkan untuk dapat:

- Memahami struktur dan pembuatan model pada Flutter.
- Memahami cara mengambil, mengolah, dan menampilkan data dari web service.
- Memahami *state management* dasar menggunakan Provider pada Flutter.
- Dapat melakukan autentikasi dengan web service Django dengan aplikasi Flutter.

## Model pada Flutter

Pada tutorial kali ini, kita akan memanggil *web service* dan menampilkan hasil yang didapat ke halaman Flutter yang kita buat. Akan tetapi sebelum melakukan pemanggilan *web service*, kita perlu mendefinisikan model yang kita gunakan ketika melakukan pemanggilan *web service*. Model pada Flutter menggunakan prinsip *class* seperti layaknya yang sudah dipelajari pada DDP2 bagian OOP.

> Kode di bawah ini adalah contoh, tidak wajib diikuti, tetapi sangat disarankan dibaca karena konsepnya akan digunakan pada bagian-bagian selanjutnya.

Berikut merupakan contoh *class* pada Flutter.

```dart
class Mobil {
    Mobil({
        this.id,
        this.brand,
        this.model
        this.color
    });

    int id;
    String brand;
    String model;
    String color;
}
```

Catatan: Jika kamu mengalami *error* saat membuat *class*, tambahkan *keyword* `required` pada setiap parameter *class* pada bagian *constructor*.

Sampai saat ini, kita telah berhasil membuat *class*. Selanjutnya, kita akan menambahkan beberapa kode sehingga terbentuk sebuah model `Mobil`. `Mobil` ini merupakan suatu model yang merepresentasikan respons dari pemanggilan *web service*.

Import `dart:convert` pada bagian paling atas file.

```dart
import 'dart:convert';
...
```

Pada *class* `Mobil`, tambahkan kode berikut.

```dart
factory Mobil.fromJson(Map<String, dynamic> json) => Mobil(
    id: json["id"],
    brand: json["brand"],
    model: json["model"],
    color: json["color"],
);

Map<String, dynamic> toJson() => {
    "id": id,
    "brand": brand,
    "model": model,
    "color": color,
};
```

Tambahkan kode berikut di luar *class* `Mobil`.

```dart
Mobil mobilFromJson(String str) => Mobil.fromJson(json.decode(str));
String mobilToJson(Mobil data) => json.encode(data.toJson());
```

Pada akhirnya, kode akan terbentuk seperti berikut untuk menampilkan satu objek `Mobil` dari *web service*.

```dart
import 'dart:convert';

Mobil mobilFromJson(String str) => Mobil.fromJson(json.decode(str));
String mobilToJson(Mobil data) => json.encode(data.toJson());

class Mobil {
    Mobil({
        this.id,
        this.brand,
        this.model,
        this.color,
    });

    int id;
    String brand;
    String model;
    String color;

    factory Mobil.fromJson(Map<String, dynamic> json) => Mobil(
        id: json["id"],
        brand: json["brand"],
        model: json["model"],
        color: json["color"],
    );

    Map<String, dynamic> toJson() => {
        "id": id,
        "brand": brand,
        "model": model,
        "color": color,
    };
}
```

Berikut adalah penjelasan dari kode di atas.

Terdapat beberapa kode-kode tambahan seperti *method* `toJson` dan `fromJson` di dalam *class* `Mobil`. Hal tersebut disebabkan ketika kita me-*request* suatu *web service* dengan *method* **GET**, umumnya kita mendapatkan hasil pemanggilan berupa JSON. Oleh karena itu, kita perlu melakukan konversi data dengan *method* `fromJson` agar Flutter mengenali JSON tersebut sebagai objek *class* `Mobil`. Selain itu, terdapat juga *method* `toJson` yang akan digunakan ketika kita melakukan pengiriman data ke *web service* (seperti **POST** atau **PUT**).

Berikut adalah contoh respons dari *web service* dengan *method* **GET** yang dapat dikonversi ke *class* model `Mobil`.

```json
{
   "id": 1,
   "brand": "Honda",
   "model": "Civic",
   "color": "Yellow"
}
```

Lalu, bagaimana jika respons dari *web service* berupa kumpulan objek JSON? Sebenarnya sama saja dengan kode di atas, hanya saja terdapat pengubahan pada *method* `mobilFromJson` dan `mobilToJson`.

Kodenya adalah sebagai berikut.

```dart
List<Mobil> mobilFromJson(String str) => List<Mobil>.from(json.decode(str).map((mobil) => Mobil.fromJson(mobil)));

String mobilToJson(List<Mobil> data) => json.encode(List<dynamic>.from(data.map((mobil) => mobil.toJson())));
```

Berikut adalah contoh respons dari *web service* dengan *method* **GET** yang dapat dikonversi ke model `Mobil`.

```json
[
  {
    "id": 1,
    "brand": "Honda",
    "model": "Civic",
    "color": "Yellow"
  },
  {
    "id": 2,
    "brand": "Toyota",
    "model": "Supra",
    "color": "Red"
  }
]
```

## Fetch Data dari Web Service pada Flutter

Pada saat pengembangan aplikasi, ada kalanya kita perlu mengambil data eksternal dari luar aplikasi kita (Internet) untuk ditampilkan di aplikasi kita. Tutorial ini bertujuan untuk memahami cara melakukan *fetching data* dari sebuah *web service* pada Flutter.

Secara umum terdapat beberapa langkah ketika ingin menampilkan data dari *web service* lain ke aplikasi Flutter, yaitu:

1. Menambahkan dependensi `http` ke proyek; dependensi ini digunakan untuk bertukar HTTP *request*.

2. Membuat model sesuai dengan respons dari data yang berasal dari *web service* tersebut.

3. Membuat *http request* ke *web service* menggunakan dependensi `http`.

4. Mengkonversikan objek yang didapatkan dari *web service* ke model yang telah kita buat di langkah kedua.

5. Menampilkan data yang telah dikonversi ke aplikasi dengan `FutureBuilder`.

Penjelasan lebih lanjut dapat dibaca pada tautan berikut: <http://docs.flutter.dev/cookbook/networking/fetch-data#5-display-the-data>.

## State Management Dasar menggunakan Provider

`Provider` adalah sebuah pembungkus di sekitar `InheritedWidget` agar `InheritedWidget` lebih mudah digunakan dan lebih dapat digunakan kembali. `InheritedWidget` sendiri adalah kelas dasar untuk widget Flutter yang secara efisien menyebarkan informasi ke widget lainnya yang berada pada satu *tree*.

Manfaat menggunakan `provider` adalah sebagai berikut.

- Mengalokasikan *resource* menjadi lebih sederhana.
- *Lazy-loading*.
- Mengurangi *boilerplate* tiap kali membuat *class* baru.
- Didukung oleh Flutter Devtool sehingga `provider` dapat dilacak dari Devtool.
- Peningkatan skalabilitas untuk *class* yang memanfaatkan mekanisme *listen* yang dibangun secara kompleks.

Untuk mengetahui `provider` secara lebih lanjut, silakan buka halaman *package* Provider: <http://pub.dev/packages/provider>

## Tutorial: Integrasi Autentikasi Django-Flutter

### Setup Autentikasi pada Django untuk Flutter

Ikuti langkah-langkah berikut untuk melakukan integrasi sistem autentikasi pada **Django**.

1. Buatlah `django-app` bernama `authentication` pada project Django yang telah kamu buat sebelumnya.

2. Tambahkan `authentication` ke `INSTALLED_APPS` pada *main project* `settings.py` aplikasi Django kamu.

3. Jalankan perintah `pip install django-cors-headers` untuk menginstal _library_ yang dibutuhkan.

4. Tambahkan `corsheaders` ke `INSTALLED_APPS` pada *main project* `settings.py` aplikasi Django kamu.

5. Tambahkan `corsheaders.middleware.CorsMiddleware` pada *main project* `settings.py` aplikasi Django kamu.

6. Tambahkan beberapa variabel berikut ini pada *main project* `settings.py` aplikasi Django kamu.

    ```python
    CORS_ALLOW_ALL_ORIGINS = True
    CORS_ALLOW_CREDENTIALS = True
    CSRF_COOKIE_SECURE = True
    SESSION_COOKIE_SECURE = True
    CSRF_COOKIE_SAMESITE = 'None'
    SESSION_COOKIE_SAMESITE = 'None'
    ```
    
7. Buatlah sebuah metode _view_ untuk login pada `authentication/views.py`.

	```python
	from django.shortcuts import render
	from django.contrib.auth import authenticate, login as auth_login
	from django.http import JsonResponse
	from django.views.decorators.csrf import csrf_exempt
	
	@csrf_exempt
	def login(request):
	    username = request.POST['username']
	    password = request.POST['password']
	    user = authenticate(username=username, password=password)
	    if user is not None:
	        if user.is_active:
	            auth_login(request, user)
	            # Status login sukses.
	            return JsonResponse({
	                "username": user.username,
	                "status": True,
	                "message": "Login sukses!"
	                # Tambahkan data lainnya jika ingin mengirim data ke Flutter.
	            }, status=200)
	        else:
	            return JsonResponse({
	                "status": False,
	                "message": "Login gagal, akun dinonaktifkan."
	            }, status=401)
	
	    else:
	        return JsonResponse({
	            "status": False,
	            "message": "Login gagal, periksa kembali email atau kata sandi."
	        }, status=401)
	```

8. Buat _file_ `urls.py` pada folder `authentication` dan tambahkan URL _routing_ terhadap fungsi yang sudah dibuat dengan _endpoint_ `login/`.

    ```python
	from django.urls import path
	from authentication.views import login
	
	app_name = 'authentication'
	
	urlpatterns = [
	    path('login/', login, name='login'),
	]
    ```

9. Terakhir, tambahkan `path('auth/', include('authentication.urls')),` pada file `shopping_list/urls.py`.

### Integrasi Sistem Autentikasi pada Flutter

Untuk memudahkan pembuatan sistem autentikasi, tim asisten dosen telah membuatkan *package* Flutter yang dapat dipakai untuk melakukan kontak dengan *web service* Django (termasuk operasi `GET` dan `POST`).

*Package* dapat diakses melalui tautan berikut: [pbp_django_auth](http://pub.dev/packages/pbp_django_auth)

Ikuti langkah-langkah berikut untuk melakukan integrasi sistem autentikasi pada **Flutter**.

1. Instal *package* yang telah disediakan oleh tim asisten dosen dengan menjalankan perintah berikut di Terminal.

	```bash
	flutter pub add provider
	flutter pub add pbp_django_auth
	```

2. Untuk menggunakan _package_ tersebut, kamu perlu memodifikasi _root widget_ untuk menyediakan `CookieRequest` _library_ ke semua _child widgets_ dengan menggunakan `Provider`.

    Sebagai contoh, jika aplikasimu sebelumnya seperti ini:

    ```dart
    class MyApp extends StatelessWidget {
        const MyApp({Key? key}) : super(key: key);
        
        @override
        Widget build(BuildContext context) {
            return MaterialApp(
                title: 'Flutter App',
                theme: ThemeData(
                        colorScheme: ColorScheme.fromSeed(seedColor: Colors.indigo),
          				useMaterial3: true,
    			),
                home: MyHomePage()),
            );
        }
    }
    ```

    Ubahlah menjadi:

    ```dart
    class MyApp extends StatelessWidget {
        const MyApp({Key? key}) : super(key: key);

        @override
        Widget build(BuildContext context) {
            return Provider(
                create: (_) {
                    CookieRequest request = CookieRequest();
                    return request;
                },
                child: MaterialApp(
                    title: 'Flutter App',
                    theme: ThemeData(
                        colorScheme: ColorScheme.fromSeed(seedColor: Colors.indigo),
          				useMaterial3: true,
                    ),
                    home: MyHomePage()),
                ),
            );
        }
    }
    ```

    Hal ini akan membuat objek `Provider` baru yang akan membagikan _instance_ `CookieRequest` dengan semua komponen yang ada di aplikasi.

2. Buatlah _file_ baru pada folder `screens` dengan nama `login.dart`.

3. Isilah _file_ `login.dart` dengan kode berikut.

    ```dart
    import 'package:shopping_list/screens/menu.dart';
    import 'package:flutter/material.dart';
    import 'package:pbp_django_auth/pbp_django_auth.dart';
    import 'package:provider/provider.dart';

    void main() {
        runApp(const LoginApp());
    }

    class LoginApp extends StatelessWidget {
    const LoginApp({super.key});

    @override
    Widget build(BuildContext context) {
        return MaterialApp(
            title: 'Login',
            theme: ThemeData(
                primarySwatch: Colors.blue,
        ),
        home: const LoginPage(),
        );
        }
    }

    class LoginPage extends StatefulWidget {
        const LoginPage({super.key});

        @override
        _LoginPageState createState() => _LoginPageState();
    }

    class _LoginPageState extends State<LoginPage> {
        final TextEditingController _usernameController = TextEditingController();
        final TextEditingController _passwordController = TextEditingController();

        @override
        Widget build(BuildContext context) {
            final request = context.watch<CookieRequest>();
            return Scaffold(
                appBar: AppBar(
                    title: const Text('Login'),
                ),
                body: Container(
                    padding: const EdgeInsets.all(16.0),
                    child: Column(
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                            TextField(
                                controller: _usernameController,
                                decoration: const InputDecoration(
                                    labelText: 'Username',
                                ),
                            ),
                            const SizedBox(height: 12.0),
                            TextField(
                                controller: _passwordController,
                                decoration: const InputDecoration(
                                    labelText: 'Password',
                                ),
                                obscureText: true,
                            ),
                            const SizedBox(height: 24.0),
                            ElevatedButton(
                                onPressed: () async {
                                    String username = _usernameController.text;
                                    String password = _passwordController.text;

                                    // Cek kredensial
                                    // TODO: Ganti URL dan jangan lupa tambahkan trailing slash (/) di akhir URL!
    								// Untuk menyambungkan Android emulator dengan Django pada localhost,
                                    // gunakan URL http://10.0.2.2/
                                    final response = await request.login("http://<APP_URL_KAMU>/auth/login/", {
                                    'username': username,
                                    'password': password,
                                    });
                        
                                    if (request.loggedIn) {
                                        String message = response['message'];
                                        String uname = response['username'];
                                        Navigator.pushReplacement(
                                            context,
                                            MaterialPageRoute(builder: (context) => MyHomePage()),
                                        );
                                        ScaffoldMessenger.of(context)
                                            ..hideCurrentSnackBar()
                                            ..showSnackBar(
                                                SnackBar(content: Text("$message Selamat datang, $uname.")));
                                        } else {
                                        showDialog(
                                            context: context,
                                            builder: (context) => AlertDialog(
                                                title: const Text('Login Gagal'),
                                                content:
                                                    Text(response['message']),
                                                actions: [
                                                    TextButton(
                                                        child: const Text('OK'),
                                                        onPressed: () {
                                                            Navigator.pop(context);
                                                        },
                                                    ),
                                                ],
                                            ),
                                        );
                                    }
                                },
                                child: const Text('Login'),
                            ),
                        ],
                    ),
                ),
            );
        }
    }
    ```

6. Pada _file_ `main.dart`, pada Widget `MaterialApp(...)`, ubah `home: MyHomePage()` menjadi `home: LoginPage()`

7. Jalankan aplikasi Flutter kamu dan cobalah untuk login.

## Pembuatan Model Kustom

Dalam membuat model yang menyesuaikan dengan data JSON, kita dapat memanfaatkan website [Quicktype](http://app.quicktype.io/) dengan tahapan sebagai berikut.

1. Bukalah _endpoint_ `JSON` yang sudah kamu buat sebelumnya pada tutorial 2.

2. Salinlah data `JSON` dan buka situs web [Quicktype](http://app.quicktype.io/).

3. Pada situs web Quicktype, ubahlah _setup name_ menjadi `Product`, _source type_ menjadi `JSON`, dan _language_ menjadi `Dart`.

4. Tempel data JSON yang telah disalin sebelumnya ke dalam _textbox_ yang tersedia pada Quicktype.

5. Klik pilihan `Copy Code` pada Quicktype.

    Berikut adalah contoh hasilnya.

    ![Quicktype Example](http://i.ibb.co/09pzyBp/product.png)

Setelah mendapatkan kode model melalui Quicktype, buka kembali proyek Flutter, buatlah file baru pada folder `lib/models` dengan nama `product.dart`, dan tempel kode yang sudah disalin dari Quicktype.

## Penerapan Fetch Data dari Django Untuk Ditampilkan ke Flutter

### Menambahkan Dependensi HTTP

Untuk melakukan perintah _HTTP request_, kita membutuhkan _package_ tambahan yakni _package_ [http](http://pub.dev/packages/http).

1. Lakukan `flutter pub add http` pada terminal proyek Flutter untuk menambahkan _package_ `http`.

2. Pada file `android/app/src/main/AndroidManifest.xml`, tambahkan kode berikut untuk memperbolehkan akses Internet pada aplikasi Flutter yang sedang dibuat.

    ```xml
    ...
        <application>
        ...
        </application>
        <!-- Required to fetch data from the Internet. -->
        <uses-permission android:name="android.permission.INTERNET" />
    ...
    ```

### Melakukan Fetch Data dari Django

1. Buatlah file baru pada folder `lib/screens` dengan nama `list_product.dart`.

2. Pada file `list_product.dart`, impor *library* yang dibutuhkan. Ubahlah <APP_NAME> sesuai dengan nama proyek Flutter yang kalian buat.

    ```dart
    import 'package:flutter/material.dart';
    import 'package:http/http.dart' as http;
    import 'dart:convert';
    import 'package:<APP_NAME>/models/product.dart';
    ...
    ```
3. Salinlah potongan kode berikut pada `pages/list_product.dart`. Jangan lupa untuk mengimpor file yang diperlukan.

    ```dart
    ...
    import 'package:<APP_NAME>/widgets/left_drawer.dart';

    class ProductPage extends StatefulWidget {
        const ProductPage({Key? key}) : super(key: key);

        @override
        _ProductPageState createState() => _ProductPageState();
    }

    class _ProductPageState extends State<ProductPage> {
    Future<List<Product>> fetchProduct() async {
        // TODO: Ganti URL dan jangan lupa tambahkan trailing slash (/) di akhir URL!
        var url = Uri.parse(
            'http://<URL_APP_KAMU>/json/');
        var response = await http.get(
            url,
            headers: {"Content-Type": "application/json"},
        );

        // melakukan decode response menjadi bentuk json
        var data = jsonDecode(utf8.decode(response.bodyBytes));

        // melakukan konversi data json menjadi object Product
        List<Product> list_product = [];
        for (var d in data) {
            if (d != null) {
                list_product.add(Product.fromJson(d));
            }
        }
        return list_product;
    }

    @override
    Widget build(BuildContext context) {
        return Scaffold(
            appBar: AppBar(
            title: const Text('Product'),
            ),
            drawer: const LeftDrawer(),
            body: FutureBuilder(
                future: fetchProduct(),
                builder: (context, AsyncSnapshot snapshot) {
                    if (snapshot.data == null) {
                        return const Center(child: CircularProgressIndicator());
                    } else {
                        if (!snapshot.hasData) {
                        return const Column(
                            children: [
                            Text(
                                "Tidak ada data produk.",
                                style:
                                    TextStyle(color: Color(0xff59A5D8), fontSize: 20),
                            ),
                            SizedBox(height: 8),
                            ],
                        );
                    } else {
                        return ListView.builder(
                            itemCount: snapshot.data!.length,
                            itemBuilder: (_, index) => Container(
                                    margin: const EdgeInsets.symmetric(
                                        horizontal: 16, vertical: 12),
                                    padding: const EdgeInsets.all(20.0),
                                    child: Column(
                                    mainAxisAlignment: MainAxisAlignment.start,
                                    crossAxisAlignment: CrossAxisAlignment.start,
                                    children: [
                                        Text(
                                        "${snapshot.data![index].fields.name}",
                                        style: const TextStyle(
                                            fontSize: 18.0,
                                            fontWeight: FontWeight.bold,
                                        ),
                                        ),
                                        const SizedBox(height: 10),
                                        Text("${snapshot.data![index].fields.price}"),
                                        const SizedBox(height: 10),
                                        Text(
                                            "${snapshot.data![index].fields.description}")
                                    ],
                                    ),
                                ));
                        }
                    }
                }));
        }
    }
    ```

4. Tambahkan halaman `list_product.dart` ke `widgets/left_drawer.dart` dengan menambahkan kode berikut.

    ```dart
    // Kode ListTile Menu
    ...
    ListTile(
        leading: const Icon(Icons.shopping_basket),
        title: const Text('Daftar Produk'),
        onTap: () {
            // Route menu ke halaman produk
            Navigator.push(
            context,
            MaterialPageRoute(builder: (context) => const ProductPage()),
            );
        },
    ),
    ...
    ```
    
5. Ubah fungsi tombol `Lihat Produk` pada halaman utama agar mengarahkan ke halaman `ProductPage`. Kamu dapat melakukan _redirection_ dengan menambahkan `else if` setelah kode `if(...){...}` di bagian akhir `onTap: () { }` yang ada pada file `widgets/shop_card.dart`

    ```dart
    ...
    else if (item.name == "Lihat Produk") {
            Navigator.push(context,
                MaterialPageRoute(builder: (context) => const ProductPage()));
          }
    ...
    ```
    
6.  Impor _file_ yang dibutuhkan saat menambahkan `ProductPage` ke `left_drawer.dart` dan `shop_card.dart`.

7. Jalankan aplikasi dan cobalah untuk menambahkan beberapa `Product` di situs web kamu. Kemudian, coba lihat hasilnya melalui halaman `Daftar Produk` yang baru saja kamu buat di aplikasi Flutter

## Integrasi Form Flutter Dengan Layanan Django

Langkah-langkah berikut akan dilakukan pada kode proyek **Django**.

1. Buatlah sebuah fungsi _view_ baru pada `main/views.py` aplikasi Django kamu dengan potongan kode berikut.

    ```python
    @csrf_exempt
    def create_product_flutter(request):
        if request.method == 'POST':
            
            data = json.loads(request.body)

            new_product = Product.objects.create(
				user = request.user,
                name = data["name"],
                price = int(data["price"]),
                description = data["description"]
            )

            new_product.save()

            return JsonResponse({"status": "success"}, status=200)
        else:
            return JsonResponse({"status": "error"}, status=401)
    ```

2. Tambahkan _path_ baru pada `main/urls.py` dengan kode berikut.

    ```python
    path('create-flutter/', create_product_flutter, name='create_product_flutter'),
    ```

3. Jalankan ulang (dan _deploy_ ulang) aplikasi kamu. Apabila kamu telah men-_deploy_ aplikasimu, maka data akun dan transaksi akan hilang setelah di-_redeploy_.

Langkah-langkah berikut akan dilakukan pada kode proyek **Flutter**.

1. Hubungkan halaman `shoplist_form.dart` dengan `CookieRequest` dengan menambahkan baris kode berikut.

    ```dart
    ...
    @override
    Widget build(BuildContext context) {
        final request = context.watch<CookieRequest>();

        return Scaffold(
    ...
    ```

2. Ubahlah perintah pada `onPressed: ()` _button_ tambah menjadi kode berikut.

    ```dart
    ...
    onPressed: () async {
        if (_formKey.currentState!.validate()) {
            // Kirim ke Django dan tunggu respons
            // TODO: Ganti URL dan jangan lupa tambahkan trailing slash (/) di akhir URL!
            final response = await request.postJson(
            "http://<URL_APP_KAMU>/create-flutter/",
            jsonEncode(<String, String>{
                'name': _name,
                'price': _price.toString(),
                'description': _description,
                // TODO: Sesuaikan field data sesuai dengan aplikasimu
            }));
            if (response['status'] == 'success') {
                ScaffoldMessenger.of(context)
                    .showSnackBar(const SnackBar(
                content: Text("Produk baru berhasil disimpan!"),
                ));
                Navigator.pushReplacement(
                    context,
                    MaterialPageRoute(builder: (context) => MyHomePage()),
                );
            } else {
                ScaffoldMessenger.of(context)
                    .showSnackBar(const SnackBar(
                    content:
                        Text("Terdapat kesalahan, silakan coba lagi."),
                ));
            }
        }
    },
    ...
    ```

4. Lakukan _quick fix_ pada baris-baris yang bermasalah untuk mengimpor _file_ yang dibutuhkan.

5. Jalankan ulang aplikasi dan coba untuk menambahkan transaksi baru dari aplikasi Flutter kamu.

## Implementasi Fitur Logout

Langkah-langkah berikut akan dilakukan pada kode proyek Django.

1. Buatlah sebuah metode _view_ untuk logout pada `authentication/views.py`.

    ```python
    from django.contrib.auth import logout as auth_logout
    ...
    @csrf_exempt
    def logout(request):
        username = request.user.username

        try:
            auth_logout(request)
            return JsonResponse({
                "username": username,
                "status": True,
                "message": "Logout berhasil!"
            }, status=200)
        except:
            return JsonResponse({
            "status": False,
            "message": "Logout gagal."
            }, status=401)
    ```

2. Tambahkan _path_ baru pada `authentication/urls.py` dengan kode berikut.

    ```python
    path('logout/', logout, name='logout'),
    ```

Langkah-langkah berikut akan dilakukan pada kode proyek Flutter.

1. Buka _file_ `lib/widgets/shop_card.dart` dan tambahkan potongan kode berikut. Selesaikan masalah impor *library* setelah menambahkan potongan kode ke dalam _file_ tersebut.

    ```dart
    ...
    @override
    Widget build(BuildContext context) {
        final request = context.watch<CookieRequest>();
        return Material(
    ...
    ```

2. Ubahlah perintah `onTap: () {...}` pada widget `Inkwell` menjadi `onTap: () async {...}` agar widget `Inkwell` dapat melakukan proses logout secara asinkronus.

3. Tambahkan kode berikut ke dalam `async {...}` di bagian akhir:

    ```dart
    ...
	// statement if sebelumnya
    // tambahkan else if baru seperti di bawah ini
    else if (item.name == "Logout") {
            final response = await request.logout(
                // TODO: Ganti URL dan jangan lupa tambahkan trailing slash (/) di akhir URL!
                "http://<APP_URL_KAMU>/auth/logout/");
            String message = response["message"];
            if (response['status']) {
              String uname = response["username"];
              ScaffoldMessenger.of(context).showSnackBar(SnackBar(
                content: Text("$message Sampai jumpa, $uname."),
              ));
              Navigator.pushReplacement(
                context,
                MaterialPageRoute(builder: (context) => const LoginPage()),
              );
            } else {
              ScaffoldMessenger.of(context).showSnackBar(SnackBar(
                content: Text("$message"),
              ));
            }
          }
    ...
    ```

4. Jalankan ulang aplikasi dan coba untuk lakukan logout.

## Akhir Kata

Selamat! Kamu telah menyelesaikan Tutorial 8! Semoga dengan tutorial ini, kalian dapat memahami mengenai *model*, *fetch* data, *state management* dasar, dan integrasi Django-Flutter dengan baik. 😄

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

- [Fetch Data From the Internet](http://docs.flutter.dev/cookbook/networking/fetch-data)
- [How to create models in Flutter Dart](http://thegrowingdeveloper.org/coding-blog/how-to-create-models-in-flutter-dart)
- [Simple app state management | Flutter](http://docs.flutter.dev/development/data-and-backend/state-mgmt/simple)
- [Flutter State Management with Provider](http://blog.devgenius.io/flutter-state-management-with-provider-5a57eca108f1)
- [Pengenalan State Management Flutter dan Jenis-jenisnya](http://caraguna.com/pengenalan-state-management-flutter/)

## Kontributor

- Yudi Putra Sabri
- Muhammad Falensi Azmi
- James Smith Wigglesworth
- Adjie Djaka Permana

## Credits

Tutorial ini dikembangkan berdasarkan [PBP Ganjil 2023](http://github.com/pbp-fasilkom-ui/ganjil-2023) dan [PBP Genap 2023](http://github.com/pbp-fasilkom-ui/genap-2023) yang ditulis oleh Tim Pengajar Pemrograman Berbasis Platform 2023. Segala tutorial serta instruksi yang dicantumkan pada repositori ini dirancang sedemikian rupa sehingga mahasiswa yang sedang mengambil mata kuliah Pemrograman Berbasis Platform dapat menyelesaikan tutorial saat sesi lab berlangsung.
