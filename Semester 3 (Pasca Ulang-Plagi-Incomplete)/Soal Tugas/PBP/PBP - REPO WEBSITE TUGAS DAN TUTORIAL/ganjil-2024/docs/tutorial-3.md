---
sidebar_label: Tutorial 3
sidebar_position: 5
Path: docs/tutorial-3
---

# Tutorial 3: Autentikasi, Session, dan Cookies

Pemrograman Berbasis Platform (CSGE602022) — diselenggarakan oleh Fakultas Ilmu Komputer Universitas Indonesia, Semester Ganjil 2023/2024  

---

## Tujuan Pembelajaran

Setelah menyelesaikan tutorial ini, kamu diharapkan untuk dapat:

- Memahami konsep dasar autentikasi dalam pengembangan web.
- Memahami peran dan fungsi _cookie_ dan _session_ dalam pengembangan web.
- Memahami cara kerja _cookie_ dan _session_ dalam pengembangan web.
- Mengimplementasikan _cookie_ dan _session_ dalam proyek web.

## Pengenalan HTTP

HTTP (_HyperText Transfer Protocol_) adalah protokol yang digunakan untuk berkomunikasi antara _client_ dan _server_. HTTP bersifat _stateless_ yang berarti setiap transaksi/aktivitas yang dilakukan dianggap sebagai transaksi/aktivitas yang benar-benar **baru**, sehingga tidak ada data sebelumnya yang **disimpan** untuk transaksi/aktivitas saat ini.

Beberapa konsep dasar mengenai HTTP:

1. **_Client/Server_**: Interaksi dilakukan antar _client/server_. Klien adalah pihak yang melakukan _request_ dan server adalah pihak yang memberikan _response_.

2. **_Stateless_**: Setiap aktivitas (_request/response_) bersifat independen, tidak tersimpan pada aktivitas terdahulu.

3. **_OSI Layer/Model_**: Model _Open Systems Interconnection (OSI)_ menjelaskan 7 lapisan yang digunakan sistem komputer untuk berkomunikasi melalui jaringan. Model 7-layer OSI terdiri dari _Application Layer_, _Presentation Layer_, _Session Layer_, _Transport Layer_, _Network Layer_, _Data Link Layer_, dan _Physical Layer_.

4. **_Application Layer_**: Pada OSI Model yang disinggung di atas, website berjalan pada _application layer_. Sedangkan, proses _request/response_ terjadi pada _transport Layer_ yang umumnya menggunakan protokol TCP yang menentukan bagaimana data akan dikirim. _Application Layer_ tidak peduli apa yang dilakukan oleh _transport Layer_ (bagaimana data dikirim, diolah, dsb) karena _application layer_ hanya berfokus kepada _request_ dan _response_.

    > Lapisan OSI lainnya akan diajarkan pada mata kuliah Jaringan Komputer/Jaringan Komunikasi Data. Kamu dapat mencarinya sendiri jika kamu penasaran. 😉

5. **_Client Actions Method_**: Terdapat metode-metode yang digunakan oleh _client_ saat melakukan _request_. Contoh: GET, POST, PUT, DELETE, dll. Penjelasan lebih detail dapat dibaca [di sini](https://www.restapitutorial.com/lessons/httpmethods.html).

6. **_Server Status Code_**: Merupakan status kode yang diberikan oleh server terhadap suatu _request_ pada sebuah halaman web. Contoh: 200 (OK), 404 (_Page Not Found_), 500 (_Internal Server Error_), dsb. Penjelasan lebih detail dapat dibaca [di sini](https://developer.mozilla.org/en-US/docs/Web/HTTP/Status).

7. **_Headers_**: Merupakan informasi kecil yang dikirim bersamaan dengan _request_ dan _response_. Informasi-informasi tersebut berguna sebagai data tambahan yang digunakan untuk memproses _request/response_. Contoh: Pada _headers_, terdapat `content-type:json`. Artinya, tipe konten yang diminta/dikirim adalah `json`. _Headers_ juga menyimpan data _cookies_.

## Pengenalan Cookies & Session

Semua komunikasi antara klien dan server dilakukan melalui protokol HTTP, di mana HTTP merupakan _stateless protocol_. Artinya _state_ yang satu dengan yang lain tidak berhubungan (independen). Hal ini mengharuskan komputer klien yang menjalankan browser untuk membuat koneksi TCP ke server **setiap kali melakukan _request_**.
  
Tanpa adanya koneksi persisten antara klien dan server, _software_ pada setiap sisi (_endpoint_) tidak dapat bergantung hanya pada koneksi TCP untuk melakukan _holding state_ atau _holding session state_.

### Apa yang dimaksud dengan _holding state_?

Sebagai contoh, kamu ingin mengakses suatu halaman A pada suatu web yang mensyaratkan pengaksesnya sudah _login_ ke dalam web. Kemudian kamu _login_ ke web tersebut dan berhasil membuka halaman A. Saat ingin pindah ke halaman B pada web yang sama, tanpa adanya suatu proses _holding state_ **maka kamu akan diminta untuk _login_ kembali**. Begitu yang akan terjadi setiap kali kamu mengakses halaman yang berbeda padahal masih pada web yang sama.

Proses memberitahu "siapa" yang sedang _login_ dan menyimpan data ini dikenal sebagai bentuk dialog antara klien-server dan merupakan dasar _session_ - _a semi-permanent exchange of information_. Merupakan hal yang sulit untuk membuat HTTP melakukan _holding state_ (karena HTTP merupakan _stateless protocol_). Oleh karena itu, dibutuhkan teknik untuk mengatasi masalah tersebut, yaitu _Cookie_ dan _Session_.
  
### Cara melakukan _holding state_?  

Salah satu cara yang paling banyak digunakan untuk melakukan _holding state_ adalah dengan menggunakan _session ID_ yang disimpan sebagai _cookie_ pada komputer klien. _Session ID_ dapat dianggap sebagai suatu _token_ (barisan karakter) untuk mengenali _session_ yang unik pada aplikasi web tertentu. Daripada menyimpan semua jenis informasi sebagai _cookies_ pada klien seperti _username_, nama, dan password, hanya _Session ID_ yang disimpan.

_Session ID_ ini kemudian dapat dipetakan ke suatu struktur data pada sisi web server. Pada struktur data tersebut, kamu dapat menyimpan semua informasi yang kamu butuhkan. Pendekatan ini jauh lebih aman untuk menyimpan informasi mengenai pengguna, daripada menyimpannya pada _cookie_. Dengan cara ini, informasi tidak dapat disalahgunakan oleh klien atau koneksi yang mencurigakan.

Selain itu, pendekatan ini lebih "tepat" jika data yang akan disimpan ada banyak. Hal itu karena cookie hanya dapat menyimpan maksimal 4 KB data. Bayangkan kamu sudah _login_ ke suatu web/aplikasi dan mendapat _session ID_ (_session identifier_). Untuk dapat melakukan _holding state_ pada HTTP yang _stateless_, browser biasanya mengirimkan suatu _session ID_ ke server pada setiap _request_. Dengan begitu, setiap kali datang suatu _request_, maka server akan bereaksi (kurang lebih) "Oh, ini orang yang tepat!". Kemudian server akan mencari informasi _state_ di memori server atau di _database_ berdasarkan _session ID_ yang didapat, lalu mengembalikan data yang diminta.

Perbedaan penting yang perlu diingat adalah data _cookie_ disimpan pada sisi klien, sedangkan data _session_ biasanya disimpan pada sisi server. Untuk pembahasan lebih detail mengenai _stateless, stateful, cookie_, dan _session_ dapat dibaca [di sini](https://sethuramanmurali.wordpress.com/2013/07/07/stateful-stateless-cookie-and-session/).

Berikut tabel singkat yang menjelaskan perbedaan antara _cookies_, _session_, dan _local storage_ secara singkat.

|                       | **_Cookies_**  | **_Local Storage_** | **_Sessions_**     |
|-----------------------|----------------|---------------------|--------------------|
| **Kapasitas**         | 4 KB           | 5 MB                | 5 MB               |
| **Teknologi Browser** | HTML4/HTML5    | HTML5               | HTML5              |
| **Aksesibilitas**     | Semua _window_ | Semua _window_      | _Tab_ yang sama    |
| **Kedaluwarsa**       | Diatur manual  | Selamanya           | Saat _tab_ ditutup |

Beberapa tautan video yang dapat memperkaya pengetahuan terkait materi ini:

- [Session & Cookies](https://www.youtube.com/watch?v=64veb6tKTm0&t=10s)
- [Cookies History](https://www.youtube.com/watch?v=I01XMRo2ESg)
- [Cookies vs. Local Storage vs. Session](https://www.youtube.com/watch?v=AwicscsvGLg)

## Tutorial: Membuat Fungsi dan Form Registrasi

> Catatan: Pada tutorial ini, kamu akan menggunakan proyek yang sudah kamu buat pada tutorial sebelumnya.  

Pada tutorial sebelumnya, kita sudah mencoba membuat sebuah form untuk penambahan produk. Bagaimana? Mudah kan? Pada tutorial ini kita akan membuat halaman utama _main_ menjadi _restricted_ dengan cara membuat akun untuk pengguna. Sehingga, pengguna yang ingin mengakses halaman utama _main_ harus melakukan _login_ terlebih dahulu agar mendapatkan akses.

1. Jalankan _virtual environment_ terlebih dahulu.

2. Buka `views.py` yang ada pada subdirektori `main` dan buatlah fungsi dengan nama `register` yang menerima parameter `request`.

3. Tambahkan _import_ `redirect`, `UserCreationForm`, dan `messages` pada bagian paling atas.

    ```python
    from django.shortcuts import redirect
    from django.contrib.auth.forms import UserCreationForm
    from django.contrib import messages  
    ```

    **Penjelasan Kode:**  

    _UserCreationForm_ adalah impor formulir bawaan yang memudahkan pembuatan formulir pendaftaran pengguna dalam aplikasi web. Dengan formulir ini, pengguna baru dapat mendaftar dengan mudah di situs web Anda tanpa harus menulis kode dari awal.

4. Tambahkan potongan kode di bawah ini ke dalam fungsi `register` yang sudah kamu buat sebelumnya. Potongan kode ini berfungsi untuk menghasilkan formulir registrasi secara otomatis dan menghasilkan akun pengguna ketika data di-_submit_ dari form.

    ```python
    def register(request):
        form = UserCreationForm()

        if request.method == "POST":
            form = UserCreationForm(request.POST)
            if form.is_valid():
                form.save()
                messages.success(request, 'Your account has been successfully created!')
                return redirect('main:login')
        context = {'form':form}
        return render(request, 'register.html', context)
    ```

    **Penjelasan Kode:**  

    - `form = UserCreationForm(request.POST)` digunakan untuk membuat `UserCreationForm` baru  dari yang sudah di-impor sebelumnya dengan memasukkan QueryDict berdasarkan input dari _user_ pada `request.POST`.
    - `form.is_valid()` digunakan untuk memvalidasi isi input dari _form_ tersebut.
    - `form.save()` digunakan untuk membuat dan menyimpan data dari _form_ tersebut.  
    - `messages.success(request, 'Your account has been successfully created!')` digunakan untuk menampilkan pesan kepada pengguna setelah melakukan suatu aksi.
    - `return redirect('main:show_main')` digunakan untuk melakukan _redirect_ setelah data _form_ berhasil disimpan.

5. Buatlah berkas HTML baru dengan nama `register.html` pada folder `main/templates`. Isi dari `register.html` dapat kamu isi dengan _template_ berikut.

    ```html
    {% extends 'base.html' %}

    {% block meta %}
        <title>Register</title>
    {% endblock meta %}

    {% block content %}  

    <div class = "login">
        
        <h1>Register</h1>  

            <form method="POST" >  
                {% csrf_token %}  
                <table>  
                    {{ form.as_table }}  
                    <tr>  
                        <td></td>
                        <td><input type="submit" name="submit" value="Daftar"/></td>  
                    </tr>  
                </table>  
            </form>

        {% if messages %}  
            <ul>   
                {% for message in messages %}  
                    <li>{{ message }}</li>  
                    {% endfor %}  
            </ul>   
        {% endif %}

    </div>  

    {% endblock content %}
    ```

6. Buka `urls.py` yang ada pada subdirektori `main` dan impor fungsi yang sudah kamu buat tadi.

    ```python
    from main.views import register #sesuaikan dengan nama fungsi yang dibuat
    ```

7. Tambahkan _path url_ ke dalam `urlpatterns` untuk mengakses fungsi yang sudah diimpor tadi.

    ```python
    ...
    path('register/', register, name='register'), #sesuaikan dengan nama fungsi yang dibuat
    ...
    ```

Kita sudah menambahkan formulir registrasi akun dan membuat mekanisme `register`. Selanjutnya, kita akan membuat form _login_ agar pengguna dapat melakukan autentikasi akun.

## Tutorial: Membuat Fungsi Login

1. Buka `views.py` yang ada pada subdirektori `main` dan buatlah fungsi dengan nama `login_user` yang menerima parameter `request`.

2. Tambahkan _import_ `authenticate` dan `login` pada bagian paling atas.

    ```python
    from django.contrib.auth import authenticate, login
    ```

    **Penjelasan Kode:**

    Singkatnya, function ``authenticate``, ``login`` yang diimport diatas akan digunakan untuk melakukan autentikasi dan login jika autentikasi berhasil. Selengkapnya dapat dibaca [di sini](https://docs.djangoproject.com/en/4.2/topics/auth/default/).

3. Tambahkan potongan kode di bawah ini ke dalam fungsi `login` yang sudah kamu buat sebelumnya. Potongan kode ini berfungsi untuk mengautentikasi pengguna yang ingin _login_.

    ```python
    def login_user(request):
        if request.method == 'POST':
            username = request.POST.get('username')
            password = request.POST.get('password')
            user = authenticate(request, username=username, password=password)
            if user is not None:
                login(request, user)
                return redirect('main:show_main')
            else:
                messages.info(request, 'Sorry, incorrect username or password. Please try again.')
        context = {}
        return render(request, 'login.html', context)
    ```

    **Penjelasan Kode:**
  
    `authenticate(request, username=username, password=password` digunakan untuk melakukan autentikasi pengguna berdasarkan username dan password yang diterima dari permintaan (request) yang dikirim oleh pengguna saat login.  

4. Buatlah berkas HTML baru dengan nama `login.html` pada folder `main/templates`. Isi dari `login.html` dapat kamu isi dengan _template_ berikut.

    ```html
    {% extends 'base.html' %}

    {% block meta %}
        <title>Login</title>
    {% endblock meta %}

    {% block content %}

    <div class = "login">

        <h1>Login</h1>

        <form method="POST" action="">
            {% csrf_token %}
            <table>
                <tr>
                    <td>Username: </td>
                    <td><input type="text" name="username" placeholder="Username" class="form-control"></td>
                </tr>
                        
                <tr>
                    <td>Password: </td>
                    <td><input type="password" name="password" placeholder="Password" class="form-control"></td>
                </tr>

                <tr>
                    <td></td>
                    <td><input class="btn login_btn" type="submit" value="Login"></td>
                </tr>
            </table>
        </form>

        {% if messages %}
            <ul>
                {% for message in messages %}
                    <li>{{ message }}</li>
                {% endfor %}
            </ul>
        {% endif %}     
            
        Don't have an account yet? <a href="{% url 'main:register' %}">Register Now</a>

    </div>

    {% endblock content %}
    ```

5. Buka `urls.py` yang ada pada subdirektori `main` dan impor fungsi yang sudah kamu buat tadi.

    ```python
    from main.views import login_user #sesuaikan dengan nama fungsi yang dibuat
    ```

6. Tambahkan _path url_ ke dalam `urlpatterns` untuk mengakses fungsi yang sudah diimpor tadi.

    ```python
    ...
    path('login/', login_user, name='login'), #sesuaikan dengan nama fungsi yang dibuat
    ...
    ```

Kita sudah menambahkan form _login_ akun dan membuat mekanisme `login`. Selanjutnya, kita akan membuat mekanisme _logout_ dan menambahkan tombol _logout_ pada halaman _main_.

## Tutorial: Membuat Fungsi Logout

1. Buka `views.py` yang ada pada subdirektori `main` dan buatlah fungsi dengan nama `logout_user` yang menerima parameter `request`.

2. Tambahkan _import_ `logout` pada bagian paling atas.

    ```python
    from django.contrib.auth import logout
    ```

3. Tambahkan potongan kode di bawah ini ke dalam fungsi `logout` yang sudah kamu buat sebelumnya. Potongan kode ini berfungsi untuk melakukan mekanisme _logout_.

    ```python
    def logout_user(request):
        logout(request)
        return redirect('main:login')
    ```

    **Penjelasan Kode:**
  
    - `logout(request)` digunakan untuk menghapus sesi pengguna yang saat ini masuk.
    - `return redirect('main:login')` mengarahkan pengguna ke halaman login dalam aplikasi Django.

4. Bukalah berkas `main.html` yang ada pada folder `main/templates`.

5. Tambahkan potongan kode di bawah ini setelah _hyperlink tag_ untuk _Add New Product_ pada berkas `main.html`.

    ```html
    ...
    <a href="{% url 'main:logout' %}">
        <button>
            Logout
        </button>
    </a>
    ...
    ```

6. Buka `urls.py` yang ada pada subdirektori `main` dan impor fungsi yang sudah kamu buat tadi.

    ```python
    from main.views import logout_user
    ```

7. Tambahkan _path url_ ke dalam `urlpatterns` untuk mengakses fungsi yang sudah diimpor tadi.

    ```python
    ...
    path('logout/', logout_user, name='logout'),
    ...
    ```

Kita sudah membuat mekanisme `logout` dan menyelesaikan sistem autentikasi dalam proyek ini.

## Tutorial: Merestriksi Akses Halaman Main

1. Buka `views.py` yang ada pada subdirektori `main` dan tambahkan _import_ `login_required` pada bagian paling atas.

    ```python
    from django.contrib.auth.decorators import login_required
    ```

    **Penjelasan Kode:**  
    Kode ``from django.contrib.auth.decorators import login_required`` digunakan untuk mengharuskan pengguna masuk (login) sebelum dapat mengakses suatu halaman web.  

2. Tambahkan kode `@login_required(login_url='/login')` di atas fungsi `show_main` agar halaman _main_ hanya dapat diakses oleh pengguna yang sudah login (terautentikasi).

    ```python
    ...
    @login_required(login_url='/login')
    def show_main(request):
    ...
    ```

Setelah merestriksi akses halaman main, jalankan proyek Django-mu dengan perintah `python manage.py runserver` dan bukalah <http://localhost:8000/> di browser favoritmu untuk melihat hasilnya, seharusnya halaman yang muncul bukanlah daftar produk di halaman main, tetapi akan di-_redirect_ ke halaman login.

## Tutorial: Menggunakan Data Dari _Cookies_

Sekarang, kita akan melihat penggunaan _cookies_ dengan menambahkan data _last login_ dan menampilkannya ke halaman _main_.

1. Lakukan _logout_ terlebih dahulu apabila kamu sedang menjalankan aplikasi Django-mu.

2. Buka `views.py` yang ada pada subdirektori `main` dan tambahkan _import_ `HttpResponseRedirect`, `reverse`, dan `datetime` pada bagian paling atas.

    ```python
    import datetime
    from django.http import HttpResponseRedirect
    from django.urls import reverse
    ```

3. Pada fungsi `login_user`, kita akan menambahkan fungsi untuk menambahkan _cookie_ yang bernama `last_login` untuk melihat kapan terakhir kali pengguna melakukan _login_. Caranya adalah dengan **mengganti kode** yang ada pada **blok** `if user is not None` menjadi potongan kode berikut.

    ```python
    ...
    if user is not None:
        login(request, user)
        response = HttpResponseRedirect(reverse("main:show_main")) 
        response.set_cookie('last_login', str(datetime.datetime.now()))
        return response
    ...
    ```

    **Penjelasan Kode:**  
    - `login(request, user)` berfungsi untuk melakukan login terlebih dahulu  
    - `response = HttpResponseRedirect(reverse("main:show_main"))` untuk membuat response  
    - `response.set_cookie('last_login', str(datetime.datetime.now())) berfungsi untuk membuat _cookie last_login_ dan menambahkannya ke dalam response

4. Pada fungsi `show_main`, tambahkan potongan kode `'last_login': request.COOKIES['last_login']` ke dalam variabel `context`. Berikut adalah contoh kode yang sudah diubah.

    ```python
    context = {
        'name': 'Pak Bepe',
        'class': 'PBP A',
        'products': products,
        'last_login': request.COOKIES['last_login'],
    }
    ```

    **Penjelasan Kode:**

    `'last_login': request.COOKIES['last_login']` berfungsi menambahkan informasi _cookie last_login_ pada response yang akan ditampilkan di halaman web.

5. Ubah fungsi `logout_user` menjadi seperti potongan kode berikut.

    ```python
    def logout_user(request):
        logout(request)
        response = HttpResponseRedirect(reverse('main:login'))
        response.delete_cookie('last_login')
        return response
    ```  

    **Penjelasan Kode:**  

    `response.delete_cookie('last_login')` berfungsi untuk menghapus _cookie_ `last_login` saat pengguna melakukan `logout`.

6. Buka berkas `main.html` dan tambahkan potongan kode berikut di antara tabel dan tombol _logout_ untuk menampilkan data _last login_.

    ```html
    ...
    <h5>Sesi terakhir login: {{ last_login }}</h5>
    ...
    ```

7. Silakan _refresh_ halaman _login_ (atau jalankan proyek Django-mu dengan perintah `python manage.py runserver` jika kamu belum menjalankan proyekmu) dan cobalah untuk _login_. Data _last login_ kamu akan muncul di halaman _main_.

8. Untuk melihat data _cookie_ `last_login`, kamu dapat mengakses fitur _inspect element_ dan membuka bagian _Application/Storage_. Klik bagian _Cookies_ dan kamu dapat melihat data _cookies_ yang tersedia. Selain `last_login`, kamu juga dapat melihat data `sessionid` dan `csrftoken`. Berikut adalah contoh tampilannya.

    ![Gambar Halaman Main beserta Data Cookies](https://cdn.discordapp.com/attachments/923523971226435584/1152456209278976011/Tutorial-3.png)

9. Jika kamu melakukan _logout_ dan membuka bagian riwayat _cookie_, _cookie_ yang dibuat sebelumnya akan hilang dan dibuat ulang ketika kamu _login_ kembali.

Sebelum melanjutkan ke tutorial berikutnya, cobalah untuk **membuat satu akun** pada halaman web kamu.

## Tutorial: Menghubungkan Model `Product` dengan `User`

> **Kamu perlu mengikuti tutorial secara berurut dari awal sebelum menjalankan bagian berikut. Jika kamu tidak mengikuti tutorial secara berurut, maka kami tidak bertanggung jawab atas _error_ di luar pembahasan tutorial yang dapat muncul dari bagian tutorial berikut.**

Terakhir, kita akan menghubungkan setiap objek `Product` yang akan dibuat dengan pengguna yang membuatnya, sehingga pengguna yang sedang terotorisasi hanya melihat produk-produk yang telah dibuat sendiri. Untuk itu, ikuti langkah-langkah berikut:

1. Buka `models.py` yang ada pada subdirektori `main` dan tambahkan kode berikut pada dibawah kode untuk mengimpor model:

    ```python
    ...
    from django.contrib.auth.models import User
    ...
    ```

2. Pada model `Product` yang sudah dibuat, tambahkan potongan kode berikut:

    ```python
    class Product(models.Model):
        user = models.ForeignKey(User, on_delete=models.CASCADE)
        ...
    ```

    **Penjelasan Kode:**

    Potongan kode diatas berfungsi untuk menghubungkan satu produk dengan satu user melalui sebuah _relationship_, dimana sebuah produk pasti terasosiasikan dengan seorang user. Lebih lanjutnya terkait `ForeignKey` akan dipelajari pada mata kuliah Basis Data. Penjelasan lebih lanjut terkait `ForeignKey` pada Django dapat dilihat [di sini](https://docs.djangoproject.com/en/4.2/topics/db/examples/many_to_one/).

3. Buka `views.py` yang ada pada subdirektori `main`, dan ubah potongan kode pada fungsi `create_product` menjadi sebagai berikut:

   ```python
   def create_product(request):
    form = ProductForm(request.POST or None)

    if form.is_valid() and request.method == "POST":
        product = form.save(commit=False)
        product.user = request.user
        product.save()
        return HttpResponseRedirect(reverse('main:show_main'))
    ...
   ```

   **Penjelasan Kode:**

   Parameter `commit=False` yang digunakan pada potongan kode diatas berguna untuk mencegah Django agar tidak langsung menyimpan objek yang telah dibuat dari form langsung ke database. Hal tersebut memungkinkan kita untuk memodifikasi terlebih dahulu objek tersebut sebelum disimpan ke database. Pada kasus ini, kita akan mengisi field `user` dengan objek `User` dari return value `request.user` yang sedang terotorisasi untuk menandakan bahwa objek tersebut dimiliki oleh pengguna yang sedang login.

4. Ubah fungsi `show_main` menjadi sebagai berikut.

    ```python
    def show_main(request):
        products = Product.objects.filter(user=request.user)

        context = {
            'name': request.user.username,
        ...
    ...
    ```

    **Penjelasan Kode:**

    - Potongan kode diatas berfungsi untuk menampilkan objek `Product` yang terasosiasikan dengan pengguna yang sedang login. Hal tersebut dilakukan dengan menyaring seluruh objek dengan hanya mengambil `Product` yang dimana field `user` terisi dengan objek `User` yang sama dengan pengguna yang sedang login.
    - Kode `request.user.username` berfungsi untuk menampilkan _username_ pengguna yang login pada halaman _main_.

5. Simpan semua perubahan, dan lakukan migrasi model dengan `python manage.py makemigrations`.

6. Seharusnya, akan muncul *error* saat melakukan migrasi model. Pilih `1` untuk menetapkan _default value_ untuk _field user_ pada semua _row_ yang telah dibuat pada basis data.

    ![Migrations-1](https://cdn.discordapp.com/attachments/923523971226435584/1152471335080046712/image.png)

7. Ketik angka 1 lagi untuk menetapkan _user_ dengan ID 1 (yang sudah kita buat sebelumnya) pada model yang sudah ada.

    ![Migrations-2](https://cdn.discordapp.com/attachments/923523971226435584/1152471372988170310/image.png)

8. Lakukan `python manage.py migrate` untuk mengaplikasikan migrasi yang dilakukan pada poin sebelumnya.

Jalankan proyek Django-mu dengan perintah `python manage.py runserver` dan bukalah <http://localhost:8000/> di browser favoritmu untuk melihat hasilnya. Cobalah untuk membuat akun baru dan login dengan akun yang baru dibuat. Amatilah halaman utama, produk yang tadi telah dibuat dengan akun sebelumnya tidak akan ditampilkan di halaman pengguna akun yang baru saja kamu buat. Hal tersebut berarti kamu sudah berhasil menghubungkan objek `Product` dengan `User` yang membuatnya.

## Akhir Kata

Selamat! Kamu telah menyelesaikan Tutorial 3 dengan baik. 😄

Setelah kamu menyelesaikan seluruh tutorial di atas, harapannya kamu sekarang lebih paham tentang penggunaan _form_, autentikasi, _session_, dan _cookie_ pada _framework_ Django.

1. Setelah menyelesaikan tutorial ini, tampilan halaman web kamu seharusnya terlihat seperti ini.

    ![Tampilan Halaman Web](https://cdn.discordapp.com/attachments/923523971226435584/1152473904342900776/image.png)

2. Pada akhir tutorial ini, struktur direktori `templates` pada subdirektori `main` lokalmu akan terlihat seperti ini.

    ![Struktur Direktori Lokal](https://cdn.discordapp.com/attachments/923523971226435584/1152473607373602846/image.png)

3. Sebelum melakukan langkah ini, **pastikan struktur direktori lokal sudah benar**. Selanjunya, lakukan `add`, `commit` dan `push` untuk memperbarui repositori GitHub.

4. Jalankan perintah berikut untuk melakukan `add`, `commit` dan `push`.

    ```shell
    git add .
    git commit -m "<pesan_commit>"
    git push -u origin <branch_utama>
    ```

    - Ubah `<pesan_commit>` sesuai dengan keinginan. Contoh: `git commit -m "tutorial 3 selesai"`.
    - Ubah `<branch_utama>` sesuai dengan nama branch utamamu. Contoh: `git push -u origin main` atau `git push -u origin master`.

## Kontributor

- Rayhan Putra Randi
- Anindya Lokeswara
- Kade Satrya Noto Sadharma
- Alfredo Austin
- Alya Azhar Agharid  

## Credits

Tutorial ini dikembangkan berdasarkan [PBP Ganjil 2023](https://github.com/pbp-fasilkom-ui/ganjil-2023) dan [PBP Genap 2023](https://github.com/pbp-fasilkom-ui/genap-2023) yang ditulis oleh Tim Pengajar Pemrograman Berbasis Platform 2023/2024. Segala tutorial serta instruksi yang dicantumkan pada repositori ini dirancang sedemikian rupa sehingga mahasiswa yang sedang mengambil mata kuliah Pemrograman Berbasis Platform dapat menyelesaikan tutorial saat sesi lab berlangsung.
