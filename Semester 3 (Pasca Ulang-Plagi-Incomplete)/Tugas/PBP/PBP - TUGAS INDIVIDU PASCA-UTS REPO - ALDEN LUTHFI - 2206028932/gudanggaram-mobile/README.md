# GudangGaram

## Tugas 7

### Proses Implementasi
1.  Membuat sebuah program Flutter baru dengan tema inventory seperti tugas-tugas sebelumnya.
    - Melanjutkan GudangGaram dari tugas sebelumnya.

2. Membuat tiga tombol sederhana dengan ikon dan teks untuk:
 Melihat daftar item (Lihat Item),
 Menambah item (Tambah Item),
 Logout (Logout)

``` dart
final List<ShopItem> items = [
    ShopItem("Lihat Item", Icons.view_in_ar, const Color(0xFF78D6C6)),
    ShopItem("Tambah Item", Icons.add_box, const Color(0xFF419197)),
    ShopItem("Logout", Icons.logout_outlined, const Color(0xFF12486B)),
];

Column(
    children: items.map((item) {
    return SizedBox(
        width: 300,
        height: 200,
        child: Padding(
        padding: const EdgeInsets.all(8.0),
        child: ShopCard(item),
        ),
    );
    }).toList(),
),

class ShopItem {
  final String name;
  final IconData iconFeat;
  final Color cardColor;

  ShopItem(this.name, this.iconFeat, this.cardColor);
}

class ShopCard extends StatelessWidget {
  final ShopItem feat;

  const ShopCard(this.feat, {Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Material(
      color: feat.cardColor,
      borderRadius: BorderRadius.circular(12),
      child: InkWell(
        onTap: () => ScaffoldMessenger.of(context)
        ..hideCurrentSnackBar()
        ..showSnackBar(
          SnackBar(
            content: Text("Kamu telah menekan tombol ${feat.name}"),
          ),
        ),
        child: Container(
          width: 240,
          padding: const EdgeInsets.all(8),
          child: Center(
            child: Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                Icon(
                  feat.iconFeat,
                  color: Colors.white,
                  size: 50.0,
                ),
                const Padding(padding: EdgeInsets.all(3)),
                Text(
                  feat.name,
                  textAlign: TextAlign.center,
                  style: const TextStyle(
                    color: Colors.white,
                    fontSize: 25
                  ),
                ),
              ],
            ),
          ),
        ),
      ),
    );
  }
}
```

3. Memunculkan Snackbar dengan tulisan:

    "Kamu telah menekan tombol Lihat Item" ketika tombol Lihat Item ditekan.

    "Kamu telah menekan tombol Tambah Item" ketika tombol Tambah Item ditekan.

    "Kamu telah menekan tombol Logout" ketika tombol Logout ditekan.

```dart
 child: InkWell(
    onTap: () => ScaffoldMessenger.of(context)
    ..hideCurrentSnackBar()
    ..showSnackBar(
        SnackBar(
        content: Text("Kamu telah menekan tombol ${feat.name}"),
        ),
    ),
```

### Jawaban dan Pertanyaan
1.Apa perbedaan utama antara stateless dan stateful widget dalam konteks pengembangan aplikasi Flutter?

- Stateless Widget:
    -  Stateless widget adalah widget yang tidak menyimpan keadaan internal atau data yang dapat berubah. Itu berarti sekali dibuat, tampilan dari widget tersebut tidak akan berubah sepanjang siklus hidupnya.
- Stateful Widget:
    - Stateful widget adalah widget yang menyimpan keadaan internal atau data yang dapat berubah. Itu berarti sekali dibuat, tampilan dari widget tersebut dapat berubah sepanjang siklus hidupnya.

2. Sebutkan seluruh widget yang kamu gunakan untuk menyelesaikan tugas ini dan jelaskan fungsinya masing-masing.

    1. Scaffold:
        - Widget Scaffold merupakan struktur dasar untuk sebuah halaman. Hal ini memungkinkan untuk menentukan AppBar, body, dan berbagai bagian dari tata letak halaman.

    2. AppBar:

        - AppBar adalah bagian atas halaman yang biasanya berisi judul dan beberapa fungsi lainnya seperti tombol kembali, aksi, dll.

    3. SingleChildScrollView:

        - SingleChildScrollView digunakan untuk mengizinkan konten di dalamnya dapat di-gulir (scrollable) ketika kontennya melebihi ruang yang tersedia.

    4. Column:

        - Column digunakan untuk menata widget secara vertikal, sehingga elemen-elemen yang diletakkan di dalamnya akan ditampilkan dari atas ke bawah.

    5. Padding:

        - Padding digunakan untuk menambahkan jarak (padding) di sekitar widget tertentu.

    6. Text:

        - Text menampilkan teks tertentu di layar dengan pengaturan tertentu seperti ukuran font, warna, dll.

    7. SizedBox:

        - SizedBox digunakan untuk menentukan dimensi (lebar dan tinggi) dari widget di dalamnya.

    8. ShopCard:

        - ShopCard adalah custom widget yang digunakan untuk menampilkan kartu toko dengan icon, teks, dan aksi saat di-tap.

    9. InkWell:

        - InkWell memberikan efek splash saat widget di-tap, dan di dalamnya terdapat aksi yang terhubung dengan interaksi tap.

    10. Material:

        - Material adalah sebuah widget yang memberikan efek material design dengan properti seperti warna dan border radius pada bagian tertentu dari UI.

    11. Icon:

        - Icon digunakan untuk menampilkan ikon tertentu di layar.
    12. SnackBar:

        - SnackBar digunakan untuk menampilkan pesan singkat pada bagian bawah layar sebagai respon terhadap interaksi pengguna.


## Tugas 8

### Proses Implementasi
1. Membuat halaman form tambah item
    - 
    ```dart
    class AddForm extends StatefulWidget {
    const AddForm({super.key});
    @override
    State<AddForm> createState() => _AddFormState();
    }

    class _AddFormState extends State<AddForm> {
    final _formKey = GlobalKey<FormState>();
    String _name = "";
    int _amount = 0;
    String _description = "";

    @override
    Widget build(BuildContext context) {
        return Scaffold(
        appBar: AppBar(
            title: const Center(
            child: Text(
                'Form Simpan',
            ),
            ),
            backgroundColor: const Color(0xFF419197),
            foregroundColor: Colors.white,
        ),
        body: Form(
            key: _formKey,
            child: SingleChildScrollView(
            child: Column(
                children: [
                Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: TextFormField(
                    decoration: InputDecoration(
                        hintText: "Nama",
                        labelText: "Nama",
                        border: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(5.0),
                        ),
                    ),
                    onChanged: (String? value) => setState(() {
                        _name = value!;
                    }),
                    validator: (String? value) {
                        return (value == null || value.isEmpty)
                            ? "Nama tidak boleh kosong!"
                            : (int.tryParse(value) != null)
                                ? "Nama harus berupa kata!"
                                : null;
                    },
                    ),
                ),
                Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: TextFormField(
                    decoration: InputDecoration(
                        hintText: "Amount",
                        labelText: "Amount",
                        border: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(5.0),
                        ),
                    ),
                    onChanged: (String? value) => setState(() {
                        _amount = int.parse(value!);
                    }),
                    validator: (String? value) {
                        return (value == null || value.isEmpty)
                            ? "Amount tidak boleh kosong!"
                            : (int.tryParse(value) == null)
                                ? "Amount harus berupa angka!"
                                : null;
                    },
                    ),
                ),
                Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: TextFormField(
                    decoration: InputDecoration(
                        hintText: "Description",
                        labelText: "Description",
                        border: OutlineInputBorder(
                        borderRadius: BorderRadius.circular(5.0),
                        ),
                    ),
                    onChanged: (String? value) {
                        setState(() {
                        _description = value!;
                        });
                    },
                    validator: (String? value) {
                        return (value == null || value.isEmpty)
                            ? "Description tidak boleh kosong!"
                            : (int.tryParse(value) != null)
                                ? "Description harus berupa kata!"
                                : null;
                    },
                    ),
                ),
                Align(
                    alignment: Alignment.bottomCenter,
                    child: Padding(
                    padding: const EdgeInsets.all(8.0),
                    child: ElevatedButton(
                        style: ButtonStyle(
                        backgroundColor: MaterialStateProperty.all(const Color(0xFF12486B)),
                        ),
                        onPressed: () {
                        if (_formKey.currentState!.validate()) {
                            data.add(Salts(
                            _name,
                            _amount,
                            _description,
                            
                            ));
                            showDialog(
                                context: context,
                                builder: (context) {
                                return AlertDialog(
                                    title: const Text(
                                    'Salt berhasil tersimpan',
                                    style: TextStyle(
                                        color: Colors.green,
                                    ),
                                    ),
                                    content: SingleChildScrollView(
                                    child: Column(
                                        crossAxisAlignment:
                                            CrossAxisAlignment.start,
                                        children: [
                                        Text('Name: $_name', style: const TextStyle(color: Colors.black),),
                                        Text('Amount: $_amount', style: const TextStyle(color: Colors.black),),
                                        Text('Description: $_description', style: const TextStyle(color: Colors.black),),
                                        ],
                                    ),
                                    ),
                                    actions: [
                                    TextButton(
                                        onPressed: () =>
                                            Navigator.pushReplacement(
                                                context,
                                                MaterialPageRoute(
                                                    builder: (context) =>
                                                        MyHomePage(),
                                                )),
                                        child: const Text('SIP'))
                                    ],
                                );
                                });
                            _formKey.currentState!.reset();
                        }
                        },
                        child: const Text(
                        "Save",
                        style: TextStyle(color: Colors.white),
                        ),
                    ),
                    ),
                ),
                ],
            ),
            ),
        ),
        );
    }
    }

    ```

2. Mengarahkan pengguna ke halaman form tambah item baru ketika menekan tombol Tambah Item pada halaman utama.

    - 
    ```dart
    onTap: () {
            if (feat.name == "Lihat Item") {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => const SaltCard()),
              );
            } else if (feat.name == "Tambah Item") {
              Navigator.push(
                context,
                MaterialPageRoute(builder: (context) => const AddForm()),              );
            } else {
              ScaffoldMessenger.of(context)
                ..hideCurrentSnackBar()
                ..showSnackBar(SnackBar(
                    content: Text("Kamu telah menekan tombol ${feat.name}!")));
            }
          },
    ```

3. Memunculkan data sesuai isi dari formulir yang diisi dalam sebuah pop-up setelah menekan tombol Save pada halaman formulir tambah item baru.

    - 
    ```dart
    onPressed: () {
        if (_formKey.currentState!.validate()) {
        data.add(Salts(
            _name,
            _amount,
            _description,
            
        ));
        showDialog(
            context: context,
            builder: (context) {
                return AlertDialog(
                title: const Text(
                    'Salt berhasil tersimpan',
                    style: TextStyle(
                    color: Colors.green,
                    ),
                ),
                content: SingleChildScrollView(
                    child: Column(
                    crossAxisAlignment:
                        CrossAxisAlignment.start,
                    children: [
                        Text('Name: $_name', style: const TextStyle(color: Colors.black),),
                        Text('Amount: $_amount', style: const TextStyle(color: Colors.black),),
                        Text('Description: $_description', style: const TextStyle(color: Colors.black),),
                    ],
                    ),
                ),
                actions: [
                    TextButton(
                        onPressed: () =>
                            Navigator.pushReplacement(
                                context,
                                MaterialPageRoute(
                                builder: (context) =>
                                    MyHomePage(),
                                )),
                        child: const Text('SIP'))
                ],
                );
            });
        _formKey.currentState!.reset();
        }
    },
    ```

4. Membuat sebuah drawer

    - 
    ```dart
    class DrawerNavi extends StatelessWidget {
    const DrawerNavi({super.key});

    @override
    Widget build(BuildContext context) {
        return Drawer(
        backgroundColor: const Color.fromARGB(255, 172, 196, 11),
        child: ListView(
            children: [
            const SizedBox(
                height: 200,
                child: DrawerHeader(
                decoration: BoxDecoration(
                color: Color.fromARGB(255, 172, 196, 11),
                ),
                child: Column(
                    children: [
                    Text(
                        'Gudang\nGaram',
                        textAlign: TextAlign.center,
                        style: TextStyle(
                        fontSize: 30,
                        fontWeight: FontWeight.bold,
                        color: Colors.white,
                        ),
                    ),
                    Padding(padding: EdgeInsets.all(10)),
                    Text("Nyimpen apa-apa disini",
                    textAlign: TextAlign.center,
                        style: TextStyle(
                        fontSize: 15,
                        color: Colors.white,
                        fontWeight: FontWeight.normal,
                        ),
                    ),
                    ],
                ),
                ),
            ),
            ListTile(
                tileColor: const Color.fromARGB(255, 172, 196, 11),
                leading: const Icon(
                Icons.home_outlined,
                color: Colors.white,
                ),
                title: const Text(
                'Halaman Utama',
                style: TextStyle(
                    color: Colors.white
                ),
                ),
                onTap: () => Navigator.pushReplacement(
                context,
                MaterialPageRoute(
                    builder: (context) => MyHomePage(),
                )
                )
            ),
            ListTile(
                tileColor: const Color.fromARGB(255, 172, 196, 11),
                leading: const Icon(
                Icons.add_box,
                color: Colors.white,
                ),
                title: const Text(
                'Tambah Item',
                style: TextStyle(
                    color: Colors.white
                ),
                ),
                onTap: () => Navigator.push(context, MaterialPageRoute(builder: (context) => const AddForm()))
            ),
            ListTile(
                tileColor: const Color.fromARGB(255, 172, 196, 11),
                leading: const Icon(
                Icons.view_in_ar,
                color: Colors.white,
                ),
                title: const Text(
                'Lihat Item',
                style: TextStyle(
                    color: Colors.white
                ),
                ),
                onTap: () => Navigator.push(context, MaterialPageRoute(builder: (context) => const SaltCard()))
            ),
            ],
        ),
        );
    }
    }
    ```

### Jawaban dan Pertanyaan
1. Perbedaan antara Navigator.push() dan Navigator.pushReplacement() dalam Flutter:

- Navigator.push():
Fungsi ini digunakan untuk menavigasi ke halaman baru di atas halaman saat ini.
Halaman baru ditumpuk di atas tumpukan navigasi, jadi ketika pengguna kembali, mereka akan kembali ke halaman sebelumnya.
Contoh Penggunaan: Anda menggunakan Navigator.push() ketika ingin membawa pengguna ke halaman detail dari suatu item dalam daftar. Pengguna dapat kembali ke daftar dengan menekan tombol kembali.

- Navigator.pushReplacement():
Fungsi ini menggantikan halaman saat ini di tumpukan navigasi dengan halaman baru.
Ini tidak menyimpan halaman sebelumnya di tumpukan. Jadi, ketika pengguna kembali, mereka tidak akan kembali ke halaman sebelumnya.
Contoh Penggunaan: Anda menggunakan Navigator.pushReplacement() untuk alur seperti login ke dashboard. Setelah berhasil login, halaman login digantikan dengan dashboard, dan pengguna tidak dapat kembali ke halaman login dengan tombol kembali.

2. Layout Widgets dalam Flutter dan Konteks Penggunaannya:

- Column dan Row:

    Digunakan untuk mengatur child widgets dalam bentuk vertikal (Column) atau horizontal (Row).
    Cocok untuk membuat tata letak yang linier dan responsif.

- Container:

    Widget serba guna untuk dekorasi, padding, dan penyesuaian ukuran.
    Baik untuk membuat widget dengan background, border, atau margin tertentu.

- Stack:

    Mengatur widgets secara bertumpuk.
    Ideal untuk tata letak overlay, seperti teks di atas gambar.

- GridView:

    Menampilkan data dalam format grid.
    Berguna untuk galeri gambar atau tampilan grid produk.

- ListView:

    Menampilkan daftar scrollable secara vertikal.
    Cocok untuk daftar item yang panjang seperti feed berita atau email.

- Flex dan Expanded:

    Mengatur space allocation antar widgets dalam Row atau Column.
    Penting untuk tata letak yang fleksibel dan adaptif terhadap ukuran layar.

- Padding:

    Memberi jarak di dalam widget.
    Penting untuk UI yang lega dan nyaman dilihat.

- Align dan Center:

    Mengontrol posisi widget di dalam container.
    Berguna untuk penempatan widget secara spesifik atau sentralisasi widget.

3. Sebutkan apa saja elemen input pada form yang kamu pakai pada tugas kali ini dan jelaskan mengapa kamu menggunakan elemen input tersebut!

- TextFormField untuk Nama:
    Tujuan Penggunaan: Untuk mengumpulkan nama item yang disimpan. Nama merupakan identifikasi dasar untuk setiap item.
    Validator: Memastikan bahwa field nama tidak kosong dan mengandung kata, bukan angka, yang penting untuk memastikan input yang valid dan menghindari kebingungan dalam identifikasi item.

- TextFormField untuk Jumlah (Amount):
    Tujuan Penggunaan: Untuk mengumpulkan jumlah item. Ini penting untuk manajemen inventaris dan pemantauan stok.
    Validator: Memastikan bahwa field jumlah tidak kosong dan hanya berisi angka. Ini vital untuk keakuratan data numerik, seperti stok atau jumlah item.

- TextFormField untuk Deskripsi (Description):
    Tujuan Penggunaan: Untuk memberikan deskripsi tambahan tentang item. Deskripsi ini dapat berisi informasi seperti spesifikasi, kegunaan, atau detail lain yang relevan.
    Validator: Memastikan bahwa deskripsi tidak kosong dan harus berupa kata. Ini penting untuk menyediakan informasi yang jelas dan lengkap tentang item.

4. Bagaimana penerapan clean architecture pada aplikasi Flutter?

Penerapan Clean Architecture pada Aplikasi Flutter:
- Pemisahan Layer:

    - Membagi aplikasi menjadi beberapa layer seperti Presentation, Business Logic, dan Data.
    - Ini memudahkan manajemen kode dan testing.

- Modularisasi:
    - Setiap fitur dibuat modular. Hal ini meningkatkan keterbacaan dan kemudahan dalam pemeliharaan kode.

- Dependency Inversion:
    - Komponen high-level tidak bergantung pada komponen low-level, melainkan bergantung pada abstraksi.
    - Ini meningkatkan fleksibilitas dan memudahkan penggantian komponen.

- Use Cases / Interactors:
    - Membuat use cases untuk menangani bisnis logik.
    - Ini memudahkan pengujian logika bisnis secara terpisah dari UI.

- Repository Pattern:
    - Menggunakan pattern ini untuk mengatur akses data.
    - Memudahkan pergantian sumber data (misalnya dari lokal ke remote) tanpa mengubah logika bisnis.

- Testing:
    - Memastikan setiap layer dapat diuji secara terpisah.
    - Meningkatkan kualitas dan keandalan kode.


## Tugas 9

### Proses Implementasi

1. Memastikan deployment proyek tugas Django kamu telah berjalan dengan baik.

- Proyek tugas django telah berjalan dengan baik. Proyek dapat diakses dari https://alden-luthfi-tugas.pbp.cs.ui.ac.id/

2. Membuat halaman login pada proyek tugas Flutter.

```dart
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
        backgroundColor: const Color(0xFF419197),
        foregroundColor: Colors.white,
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

                final response =
                    await request.login("http://localhost:8000/auth/login/", {
                  'username': username,
                  'password': password,
                });

                if (request.loggedIn) {
                  String message = response['message'];
                  loggedInUser = User(username: username, password: password);
                  userList.add(loggedInUser);

                  Navigator.pushReplacement(
                    context,
                    MaterialPageRoute(
                        builder: (context) => MyHomePage()),
                  );
                  ScaffoldMessenger.of(context)
                    ..hideCurrentSnackBar()
                    ..showSnackBar(SnackBar(
                        content: Text(
                            "$message Selamat datang, ${loggedInUser.username}.")));
                } else {
                  showDialog(
                    context: context,
                    builder: (context) => AlertDialog(
                      title: const Text('Login Gagal'),
                      content: Text(response['message']),
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
            const SizedBox(height: 15,),
            GestureDetector (
              onTap: () => Navigator.pushReplacement(
              context,
              MaterialPageRoute (
                builder: (context) => const RegisterPage(),
              )
              ),
              child: const Text(
                'Don\'t have an account yet?',
                style: TextStyle(
                  color: Colors.blue
                ),
              ),
            )
          ],
        ),
      ),
    );
  }
}
```

3. Mengintegrasikan sistem autentikasi Django dengan proyek tugas Flutter.

```python
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

@csrf_exempt
def register(request):
    if request.method == "POST":
        user = UserCreationForm(request.POST)
        if user.is_valid():
            user.save()
            return JsonResponse({
                "status": True,
                "message": "Successfully Signed Up!",
                "user_type": "pasien"
            }, status=200)
        else:
            return JsonResponse({
                "status": False,
                "message": "Failed to Sign Up, Account Disabled."
            }, status=401)
    else:
        return JsonResponse({
            "status": False,
            "message": "Failed to Sign Up, check your username/password."
        }, status=401) 
```

```dart
final response =
    await request.login("https://alden-luthfi-tugas.pbp.cs.ui.ac.id/auth/login/", {
    'username': username,
    'password': password,
});
```

4. Membuat model kustom sesuai dengan proyek aplikasi Django.

```dart
List<Salts> saltsFromJson(String str) => List<Salts>.from(json.decode(str).map((x) => Salts.fromJson(x)));

String saltsToJson(List<Salts> data) => json.encode(List<dynamic>.from(data.map((x) => x.toJson())));

class Salts {
    String model;
    int pk;
    Fields fields;

    Salts({
        required this.model,
        required this.pk,
        required this.fields,
    });

    factory Salts.fromJson(Map<String, dynamic> json) => Salts(
        model: json["model"],
        pk: json["pk"],
        fields: Fields.fromJson(json["fields"]),
    );

    Map<String, dynamic> toJson() => {
        "model": model,
        "pk": pk,
        "fields": fields.toJson(),
    };
}

class Fields {
    String name;
    int user;
    int amount;
    String description;
    String sha256Sum;

    Fields({
        required this.name,
        required this.user,
        required this.amount,
        required this.description,
        required this.sha256Sum,
    });

    factory Fields.fromJson(Map<String, dynamic> json) => Fields(
        name: json["name"],
        user: json["user"],
        amount: json["amount"],
        description: json["description"],
        sha256Sum: json["sha256sum"],
    );

    Map<String, dynamic> toJson() => {
        "name": name,
        "user": user,
        "amount": amount,
        "description": description,
        "sha256sum": sha256Sum,
    };
}
```

5. Membuat halaman yang berisi daftar semua item yang terdapat pada endpoint JSON di Django yang telah kamu deploy.

```python
def show_json_by_user(request, username):
     user = User.objects.get(username=username)
     data = Salts.objects.filter(user=user)

     response = HttpResponse(serializers.serialize("json", data), content_type="application/json")
     return response
    
urlpatterns = [
    path('json/', show_json, name='json'),
    path('xml/', show_xml, name='xml'),
    path('json/<str:hash>/', show_json_by_hash, name='json_by_id'),
    path('xml/<str:hash>/', show_xml_by_hash, name='xml_by_id'),
    path('jsonx/<str:username>/', show_json_by_user, name='json_by_user'),
]
```

```dart
class ItemPage extends StatefulWidget {
  const ItemPage({Key? key}) : super(key: key);

  @override
  _ItemPageState createState() => _ItemPageState();
}

class _ItemPageState extends State<ItemPage> {


  Future<List<Salts>> fetchItem() async {
    var url = Uri.parse('http://localhost:8000/debug/json/');
    var response = await http.get(
      url,
      headers: {"Content-Type": "application/json"},
    );

    var data = jsonDecode(utf8.decode(response.bodyBytes));

    List<Salts> listItem = [];
    for (var d in data) {
      if (d != null) {
        listItem.add(Salts.fromJson(d));
      }
    }
    return listItem;
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: const Text('List Item'),
          backgroundColor: const Color(0xFF419197),
        foregroundColor: Colors.white,
        ),
        drawer: const DrawerNavi(),
        body: FutureBuilder(
          future: fetchItem(),
          builder: (context, AsyncSnapshot snapshot) {
            if (snapshot.data == null) {
              return const Center(child: CircularProgressIndicator());
            } else {
              if (!snapshot.hasData) {
                return const Column(
                  children: [
                    Text(
                      "Tidak ada data item.",
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
                    child: ClipRRect(
                      borderRadius: BorderRadius.circular(20.0),
                      child: Material(
                        color: const Color(0xFF78D6C6),
                        child: InkWell(
                          onTap: () => Navigator.push(context, MaterialPageRoute(builder: (context)=> Detail(snapshot.data![index]))),
                          child: Column(
                            children: [
                              
                              Padding(
                                padding: const EdgeInsets.all(15.0),
                                child: Row(
                                  mainAxisAlignment:
                                      MainAxisAlignment.spaceAround,
                                  crossAxisAlignment:
                                      CrossAxisAlignment.start,
                                  children: [
                                    SizedBox(
                                      width: 200.0,
                                      child: Column(
                                        crossAxisAlignment:
                                            CrossAxisAlignment.start,
                                        children: [
                                          Text(
                                            "${snapshot.data![index].fields.name}",
                                          ),
                                        ],
                                      ),
                                    ),
                                    Column(
                                      mainAxisAlignment:
                                          MainAxisAlignment.start,
                                      children: [
                                        Text(
                                          "Amount: ${snapshot.data![index].fields.amount}",
                                        ),
                                      ],
                                    )
                                  ],
                                ),
                              ),
                              SizedBox(
                                width: 300,
                                child: Text("${snapshot.data![index].fields.description}")
                              ),
                              const SizedBox(height: 20,)
                            ],
                          ),
                        ),
                      ),
                    ),
                  )
                );
            }
          }
        }
      )
    );
  }
}
```

6. Membuat halaman detail untuk setiap item yang terdapat pada halaman daftar Item.

```dart
class Detail extends StatelessWidget {
  final Salts item;

  const Detail(this.item, {Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          "Detail Item",
          style: TextStyle(color: Colors.white),
        ),
        backgroundColor: const Color(0xFF419197),
        foregroundColor: Colors.white,
      ),
      body: SingleChildScrollView(
        child: Padding(
          padding: const EdgeInsets.all(50.0),
          child: ItemCard(item.fields),
        ),
      ),
    );
  }
}

class ItemCard extends StatelessWidget {
  final Fields item;

  const ItemCard(this.item, {super.key});

  @override
  Widget build(BuildContext context) {
    return ClipRRect(
      borderRadius: BorderRadius.circular(20.0),
      child: Material(
        color: const Color(0xFF78D6C6),
        child: InkWell(
          onTap: () {},
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Padding(
                padding: const EdgeInsets.all(15.0),
                child: Row(
                  mainAxisAlignment: MainAxisAlignment.spaceAround,
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    SizedBox(
                      width: 200.0,
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          Text(
                            item.name,
                            style: const TextStyle(color: Colors.white),
                          ),
                          const SizedBox(height: 5,),
                        
                          Column(
                            crossAxisAlignment: CrossAxisAlignment.center,
                            children: [
                              const Text("HASH:", style: TextStyle(fontSize: 20),),
                              Text(
                                item.sha256Sum,
                                textAlign: TextAlign.center,
                                style: const TextStyle(color: Colors.white, fontSize: 20),
                              ),
                            ],
                          ),
                        ],
                      ),
                    ),
                    Column(
                      mainAxisAlignment: MainAxisAlignment.start,
                      children: [
                        Text(
                          "Amount: ${item.amount.toString()}",
                          style: const TextStyle(color: Colors.white),
                        ),
                      ],
                    )
                  ],
                ),
              ),
              Padding(
                padding: const EdgeInsets.all(20.0),
                child: Column(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    const Text("Description:"),
                    const SizedBox(height: 5,),
                    SizedBox(width: 300, child: Text(item.description)),
                    const SizedBox(
                      height: 20,
                    ),
                  ],
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
```

### Jawaban dan Pertanyaan

1. Apakah bisa kita melakukan pengambilan data JSON tanpa membuat model terlebih dahulu? Jika iya, apakah hal tersebut lebih baik daripada membuat model sebelum melakukan pengambilan data JSON?

- Pengambilan Data JSON Tanpa Membuat Model:

    - Kemungkinan
        Ya, Anda bisa mengambil data JSON tanpa membuat model terlebih dahulu. Ini biasanya dilakukan dengan menguraikan JSON menjadi struktur data seperti Map atau List di Dart.

    - Perbandingan dengan Membuat Model

        Kelebihan Tanpa Model: Lebih cepat dan mudah untuk implementasi sederhana; fleksibel untuk JSON yang sering berubah.

    - Kelebihan Dengan Model
        
        Mempermudah pemeliharaan kode, meningkatkan keterbacaan, dan mengurangi kesalahan saat runtime. Model memberikan struktur yang lebih jelas dan memudahkan validasi data.

    - Kesimpulan: Pilihan antara keduanya tergantung pada kebutuhan proyek. Untuk proyek besar dengan struktur data yang kompleks dan stabil, menggunakan model lebih disarankan.

2. Jelaskan fungsi dari CookieRequest dan jelaskan mengapa instance CookieRequest perlu untuk dibagikan ke semua komponen di aplikasi Flutter.

    - Fungsi CookieRequest di Flutter

        - Definisi

            CookieRequest adalah mekanisme untuk mengelola cookie dalam permintaan HTTP di aplikasi Flutter.

    - Fungsi Utama

        Memungkinkan aplikasi untuk menyimpan, mengambil, dan mengelola cookie, yang penting untuk otentikasi, pengaturan sesi, dan menyimpan preferensi pengguna.

    - Pembagian Instance ke Semua Komponen

        - Keamanan

            Memastikan bahwa cookie yang digunakan konsisten di seluruh aplikasi.

        - Pemeliharaan Sesi

            Membantu dalam pemeliharaan sesi pengguna saat berpindah antar komponen atau halaman.

        - Kemudahan Penggunaan
        
            Mengurangi redundansi kode dan mempermudah pengelolaan cookie secara terpusat.

3. Jelaskan mekanisme pengambilan data dari JSON hingga dapat ditampilkan pada Flutter.

    - Mekanisme Pengambilan Data dari JSON di Flutter

        - Pengambilan Data

            Pertama, data JSON diambil dari sumber eksternal (seperti API) menggunakan permintaan HTTP.

        - Penguraian JSON
        
            Data JSON yang diterima diurai menjadi struktur Dart (misalnya Map atau List).


        - Penggunaan Model
            
            Jika menggunakan model, data yang diurai diubah menjadi objek model.

        - Tampilan pada Flutter
            
            Data tersebut kemudian dapat digunakan untuk membangun widget dan ditampilkan dalam UI Flutter.


4. Jelaskan mekanisme autentikasi dari input data akun pada Flutter ke Django hingga selesainya proses autentikasi oleh Django dan tampilnya menu pada Flutter.

    - Mekanisme Autentikasi dari Flutter ke Django

        - Input Data Akun
        
            Pengguna memasukkan data akun (seperti username dan password) di aplikasi Flutter.


        - Pengiriman Data
        
            Data ini dikirim ke backend Django melalui permintaan HTTP (biasanya POST).


        - Pengolahan di Django

            Django menerima data, memverifikasi terhadap database, dan melakukan proses autentikasi.

        - Respons dari Django
        
            Setelah autentikasi berhasil, Django mengirimkan respons (biasanya berupa token atau konfirmasi status autentikasi).


        - Tampilan Menu di Flutter
        
            Aplikasi Flutter menerima respons dan, jika autentikasi berhasil, menampilkan menu atau halaman yang relevan.

        - Manajemen Sesi
        
            Flutter mungkin menggunakan cookie atau token untuk mengelola sesi pengguna setelah autentikasi.

5. Sebutkan seluruh widget yang kamu pakai pada tugas ini dan jelaskan fungsinya masing-masing.

Pada tugas ini, hanya dua widget baru jika dibandingkan dengan tugas-tugas sebelumnya:

- FutureBuilder

    Berfungsi untuk membangun widget berdasarkan hasil terbaru dari Future. Dalam konteks fetching data, digunakan untuk membangun UI setelah data diterima dari permintaan HTTP.

- CircularProgressIndicator

    Berfungsi untuk menampilkan indikator loading berputar, digunakan untuk memberi tahu pengguna bahwa aplikasi sedang menunggu proses (misalnya, memuat data).





