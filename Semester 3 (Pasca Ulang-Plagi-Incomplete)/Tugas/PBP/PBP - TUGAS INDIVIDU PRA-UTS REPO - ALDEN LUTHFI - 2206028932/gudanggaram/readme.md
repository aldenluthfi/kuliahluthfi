# Gudang Garam

Sebuah proyek Django sederhana sebagai Tugas Mata Kuliah Pemrograman Berbasis Platform oleh Alden Luthfi 2206028932. Proyek ini di buat dengan sistem operasi MacOS.

## Tugas 2

### Proses Pembuatan Projek Django
1. Membuat sebuah _repository_ Github baru bernama ```gudanggaram```
2. Meng-_clone repostiory_ kosong tersebut ke komputer
3. Di direktori asal Membuat _virtual environment_ Python baru dengan command:

    ```bash
    python -m venv env
    ```
4. Menyalakan _virtual environment_ Python baru dengan command:
    ```bash
    source env/bin/activate
    ```
5. Mempersiapkan _requirements_ dengan _neovim_
    ```bash
    nvim requirements.txt
    ```
    isi dari requirements.txt
    ```
    django
    gunicorn
    whitenoise
    psycopg2-binary
    requests
    urllib3
    ```
6. Meng-_install requirements_ dengan pip
    ```bash
    Python -m pip install -r requirements.txt
    ```
7. Membuat proyek Django baru dengan command:
    ```bash
    django-admin startproject gudanggaram .
    ```
8. Mengubah ```ALLOWED_HOSTS``` di file ```settings.py``` dengan menambahkan ```"*"``` agar proyek ini bisa dijalankan di host/domain apapun.

9. Membuat aplikasi ```main``` dengan command:
    ```bash
    python manage.py startapp main
    ```
10. Menambahkan nama aplikasi ke ```INSTALLED_APPS``` pada file ```settings.py``` di direktori ```gudanggaram```

11. Me-_routing_ url pada file ```urls.py``` di direktori ```guadnggaram``` sehingga isi file ```urls.py``` menjadi:
    ```python
    from django.contrib import admin
    from django.urls import path, include

    urlpatterns = [
        path('admin/', admin.site.urls),
        path('', include('main.urls')),
    ]
    ```
12. Mengubah ```models.py``` menjadi:
    ```python
    from django.db import models

    class Product(models.Model):
        name = models.CharField(max_length=255)
        amount = models.IntegerField()
        description = models.TextField()
    ```
13. Melakukan migrasi dengan command:
    ```
    python manage.py makemigrations
    python manage.py migrate
    ```
14. Membuat direktori template dan template ```html``` untuk laman ```main```:

    ```html
    <h1>{{ app_name }} Page</h1>

    <h5>Name: </h5>
    <p>{{ name }}<p>
    <h5>Class: </h5>
    <p>{{ class }}<p>
    ```
15. Menambahkan fungsi untuk me-_render_ laman main pada file ```views.py```:
    ```python
    from django.shortcuts import render

    def show_main(request):
        context = {
            'app name': 'Gudang Garam',
            'name': 'Alden Luthfi',
            'class': 'PBP B'
        }

        return render(request, "main.html", context)
    ```

16. Melakukan routing pada aplikasi ```main``` pada file ```urls.py``` di direktori main:
    ```python
    from django.urls import path
    from main.views import show_main

    app_name = 'main'

    urlpatterns = [
        path('', show_main, name='show_main'),
    ]
    ```

17. Mengetest aplikasi pada localhost dengan command:
    ```
    python manage.py runserver
    ```
    kemudian membuka ```http://localhost:8000/``` di _browser_

18. Melakukan deploy app ke situs _Adaptable_

### Jawaban dari Pertanyaan

1. **Bagan Arsitektur Django**
![](static/images/raster/MVT.png)
Terlihat bahwa _request_  dari user akan diproses terlebih dahulu sehingga dapat diteruskan ke View yang sesuai. kemudian View tersebut akan membaca/menulis data di Model dan menggunakan Template untuk menampilkan dan mengembalikan _response_ ke _user_

2. **Mengapa perlu menggunakan virtual environment untuk membuat suatu proyek**

    Sebuah _virtual environment_ memberikan kebebasan seorang _developer_ yang memiliki banyak proyek untuk menggunakan _dependency_ yang mungkin saling bertabrakan jika disatukan tanpa menggunakan _virtual environment_. Secara singkat, sebuah _virtual environment_ menghindari terjadinya konflik pada proyek, baik itu konflik versi, konflik _dependency_, dsb.

3. **Penjelasan Arsitektur MVC, MVT dan MVVM**

    **MVC**

    MVC adalah singkatan dari Model, View, dan Controller. Pada arsitektur proyek ini, tiga komponen aplikasi ini mempunyai tugas yang berbeda-beda. komponen Model bertugas untuk meng-_handle_ logic dan segala fungsionalitas yang dibutuhkan oleh _user_. komponen View
    bertugas untuk mengelola tampilan yang dilihat oleh _user_. komponen Controller
    memroses _request_  dari _user_ dengan berkomunikasi dengan Model untuk mengolah data kemudian
    berkomunikasi dengan View untuk menampilkan laman kepada _user_

    **MVT**

    MVT adalah singkatan dari Model, View, Template. Pola arsitektur ini mirip dengan pola MVC. hanya saja, yang bertugas untuk mengelolas tampilan adalah komponen Template sedangkan komponen View-lah yang bertugas untuk berkomunikasi antara
    Model dan Template.

    **MVVM**

    MVVM adalah singkatan dari Model, View, ViewModel. pada arsitektur ini, komponen ViewModel menjadi penengah antara View dan Model. Interaksi dari _user_ akan diterima oleh View dan diteruskan ke ViewModel yang akan memperbaharui Model dan meneruskan perubahan yang ada kepada masing masing View dan Model.

    **Perbedaan Antara Ketiganya**

    Perbedaan antara MVVM dan MVC/MVT mungkin jelas terlihat. Sehingga ada baiknya kita membedakan antara MVT dan MVC. Pada MVC, komponen Controllerlah yang menerima _request_  dan memberikan respons kepada _user_, Controler memanipulasi Model untuk mengambil Data kemudian meneruskannya ke View agar View bisa memproses Data dari Model dan menampilkan tampilan yang sesuai, kemudian Controller akan mengembalikan respons kepada _user_. Sedangkan pada MVT, _request_  dari _user_ akan diteruskan ke View yang bersangkutan, kemudian View akan membaca/menulis data kepada Model dan menggunakan Template untuk mengembalikan _response_ kepada _user_. Perbedaan utama pada kedua ini adalah pada MVT, seorang _developer_ tidak lagi berperan sebagai penulis logika dari Controller pada aplikasinya.

    Untuk MVVM, berbeda dari yang lain, komponen View dan komponen Model pada arsitektur ini tidak berkomunikasi sama sekali.

## Tugas 3

### Proses Implementasi Form

1. Membuat ```forms.py``` di direktori ```main``` dengan isi

    ```python
    from main.models import Salts
    from django import forms

    class SaltsForm(forms.ModelForm):
        class Meta:
            model = Salts
            fields = '__all__'

    ```

2. Menambahkan Method ```create_page``` untuk menambah entri database di file ```views.py``` di direktori ```main```

    ```python
    def create_page(request):
        form = SaltsForm(request.POST or None)

        if request.method == "POST" and form.is_valid():
            form.save()
            return HttpResponseRedirect(reverse('main:main_page'))

        context = {'form': form}

        return render(request, "create.html", context)

    ```

    BONUS: Saya juga menambahkan fungsionalitas delete untuk menghapus entri pada database

3. Mengimplementasikan form yang tadi sudah dibuat ke dalam laman baru dengan template html yang baru ```create.html```.

4. Routing URL ke laman yang bersangkutan di file ```urls.py``` di direktori ```main```

    ```python
    urlpatterns = [
        ...
        path('create/', create_page, name='create'),
        path('delete/<int:id>/', delete_salt, name='delete'),
        ...
    ]

    ```

5. Menambahkan folder ```templates``` di direktori utama dan ```base.html``` sebagai basis dari laman-laman lain

6. Menambahkan lokasi folder ```templates``` tersebut ke ```settings.py``` di direktori ```gudanggaram```

    ```python
    ...
    'DIRS': [BASE_DIR / 'templates'],
    ...
    ```

7. Mengimplementasikan database ke dalam laman utama ```main.html``` dan juga menjadi perpanjangan dari ```base.html``` di direktori utama

    ```html
    ...
    <table>
        <tr>
            <th>Name</th>
            <th>Amount</th>
            <th>Description</th>
            <th></th>
        </tr>

        {% for salt in salts %}
            <tr>
                <td>{{salt.name}}</td>
                <td>{{salt.amount}}</td>
                <td>{{salt.description}}</td>
                <td id="delete">
                    <a href="{% url 'main:delete' salt.id %}">
                        <button class="tablebutton">
                            Delete
                        </button>
                    </a>
                </td>
            </tr>
        {% endfor %}
    </table>
    ...
    ```

8. Menggunakan folder ```static``` untuk mengorganisir aset-aset yang digunakan seperti gambar dan *stylesheet* CSS untuk mempercantik laman

9. Membuat app baru bernama ```debug``` untuk melihat tampilan JSON dan XML dari app ```main```

10. Menambahkan fungsi-fungsi yang diperlukan untuk menampilkan JSON dan XML baik secara keseluruhan maupun per entri database

    ```python
    def show_xml(request):
        data = Salts.objects.all()
        return HttpResponse(serializers.serialize("xml", data), content_type="application/xml")

    def show_json(request):
        data = Salts.objects.all()
        return HttpResponse(serializers.serialize("json", data), content_type="application/json")

    def show_xml_by_id(request, id):
        data = Salts.objects.filter(pk=id)
        return HttpResponse(serializers.serialize("xml", data), content_type="application/xml")

    def show_json_by_id(request, id):
        data = Salts.objects.filter(pk=id)
        return HttpResponse(serializers.serialize("json", data), content_type="application/json")

    ```

11. Merouting kembali URL yang bersangkutan di file ```urls.py``` di direktori ```debug```

    ```python
    urlpatterns = [
        path('json/', show_json, name='json'),
        path('xml/', show_xml, name='xml'),
        path('json/<int:id>/', show_json_by_id, name='json_by_id'),
        path('xml/<int:id>/', show_xml_by_id, name='xml_by_id'),
    ]
    ```

12. Mengetest aplikasi pada localhost dengan command:
    ```
    python manage.py runserver
    ```
    kemudian membuka ```http://localhost:8000/``` di _browser_

13. BONUS: terkena ban di *platform* Adaptable

### Jawaban Dari Pertanyaan

1. **Perbedaan antara POST dan GET**

    Perbedaan antara keduanya terletak pada tujuan masing masing metode digunakan. Metode GET biasa digunakan ketika *user* ingin mendapatkan data dari server. Sedangkan, metode POST digunakan ketika *user* ingin mengirim data ke server. Penggunaan metode GET
    untuk mengirimkan data mengharuskan *user* untuk menuliskan data yang mau disampaikan ke server di dalam url, sehingga data-data pribadi sangat tidak aman jika dikirimkan dengan data ini ketimbang metode POST.

2. **Perbedaan antara HTML vs JSON vs XML**

    Pada kasus ini HTML berada pada cakupan yang berbeda dengan JSON dan XML. HTML digunakan sebagai kerangka dasar sebuah website, HTML menentukan apa saja yang akan tampil di website tersebut dan penggayaan yang sederhana. sedangkan JSON dan XML adalah sarana transfer data, XML cenderung lebih sulit dibaca daripada JSON namun dianggap sebagai struktur data yang lebih *robust* dari JSON. Meskipun demikian, JSON memiliki *support* terhadap *array* sedangkan XML tidak. Diantara perbedaan yang sudah disebutkan masih banyak perbedaan lain, pada hakikatnya JSON dan XML berperan sama yaitu mentransfer data namun format penulisan dan kemampuannya lah yang membedakannya.

3. **Mengapa JSON lebih banyak dipakai di web modern**

    JSON lebih banyak dipakai karena formatnya yang lebih mudah dibaca manusia. Selain itu, format JSON yang cenderung memiliki karakter lebih sedikit dari format lain seperti XML memungkinkan JSON untuk diproses lebih cepat daripada format lain.

### Screenshot Postman

1. **HTML Source**
![](static/images/raster/HTML.png)

2. **JSON**
![](static/images/raster/JSON.png)

3. **JSON by ID**
![](static/images/raster/JSONID.png)

4. **XML**
![](static/images/raster/XML.png)

5. **XML by ID**
![](static/images/raster/XMLID.png)

## Tugas 4

### Proses Implementasi Login Page

1. Membuat app baru bernama ```login``` serta laman-laman yang dimilikinya seperti register, login dan logout (walaupun logout tidak mempunyai tampilan hanya mengarahkan ke laman login lagi)

2. Melakukan routing url seperti biasa untuk mengatur alur tampilan laman

3. Menerapkan decorator keharusan login terutama pada laman utama sehingga perlu login untuk mengaksesnya

4. Mengimplementasikan cookie sehingga user masih berstatus login ketika menjelajahi laman utama

5. Menambahkan field baru pada Model utama yaitu ```user``` yang akan diasosiasikan dengan barang-barang khusus untuk user tersebut, sehingga user yang berbeda akan memiliki akses ke barang yang berbeda

6. Melakukan migrasi database untuk mensinkronkan database pada keseluruhan proyek

### Jawaban dari Pertanyaan

1. **Kelebihan dan Kekurangan Django UserCreationForm**

Django UserCreationForm adalah sebuah bentuk form bawaan yang disediakan oleh Django untuk memungkinkan pengembang web dengan mudah membuat *user registration form* dalam aplikasi Django. Form ini memungkinkan pengguna untuk membuat akun dengan mengisi informasi seperti *username* dan *password*. Kelebihan dari fitur bawaan ini antara lain kemudahan penggunaannya, validasi yang telah disematkan, dan integrasi dengan sistem Django. Namun, fitur bawaan ini tidak memberikan banyak ruang dalam segi desain tampilan dan *customization*.

2. **Autentikasi vs Otorisasi**

Autentikasi adalah proses verifikasi identitas pengguna. Dalam konteks Django, ini berarti memeriksa apakah pengguna yang mencoba mengakses aplikasi adalah pengguna yang sah. Sedangkan, Otorisasi adalah proses mengontrol apa yang diizinkan atau tidak diizinkan pengguna lakukan setelah mereka berhasil diotentikasi. Ini melibatkan penentuan hak akses dan izin apa yang dimiliki pengguna.

3. **Cookies dan Penggunaannya**

Cookies adalah data kecil yang disimpan pada perangkat pengguna oleh server web. Mereka digunakan untuk menyimpan informasi terkait sesi atau preferensi pengguna di antara kunjungan ke situs web. Django menggunakan cookies untuk mengelola data sesi pengguna. Ketika pengguna masuk atau berinteraksi dengan aplikasi, Django dapat menyimpan informasi tentang sesi pengguna (seperti ID sesi) dalam cookie.

4. **Keamanan Cookie**

Pada dasarnya, penggunaan cookies tidak aman karena data yang disimpan di dalamnya dapat diakses dan dimanipulasi oleh pihak yang tidak sah jika tidak diimplementasikan dengan benar. Risiko potensial termasuk pencurian informasi pengguna, peretasan sesi, dan penyadapan data sensitif. Untuk mengurangi risiko ini, pengembang harus mengikuti praktik terbaik keamanan, seperti mengenkripsi data yang disimpan dalam cookies, menggunakan atribut keamanan seperti HttpOnly dan Secure (untuk menghindari JavaScript akses dan memastikan penggunaan HTTPS), dan menyimpan sesi pengguna yang sensitif di sisi server bukan di cookies.

## Tugas 5

### Proses Implementasi CSS

1. Membuat folder ```static``` pada semua project dan project app

2. Menambahkan ```base.css``` sebagai basis desain dari semua laman pada projek

3. Menambahkan file-file seperti ```main.css``` sebagai desain laman masing-masing fitur

4. Menambahkan line berikut di file ```main.html``` untuk menghubungkan css dengan html

```html
<link rel="stylesheet" href="{% static 'css/main.css' %}">
```

5. Menambahkan line berikut di file ```base.html``` untuk menghubungkan css dengan html

```html
<link rel="stylesheet" href="{% static 'css/base.css' %}">
```
6. Mengetest aplikasi pada localhost dengan command:
    ```
    python manage.py runserver
    ```
    kemudian membuka ```http://localhost:8000/``` di _browser_

### Jawaban dari Pertanyaan

1. **Element Selector**

 - Type Selector: memilih semua elemen dengan tipe yang ditentukan. Contoh, jika Anda ingin mengganti gaya semua tag ```<p>```, Anda dapat menggunakan ```p { ... }```
 - Class Selector: memilih semua elemen dengan atribut ```class``` tertentu.
 - ID Selector: Selector ini memilih elemen dengan atribut ```id``` tertentu. Berguna untuk mengidentifikasi dan mengatur gaya elemen tertentu.

2. **Tag-Tag pada HTML**

Kebanyakan Tag-tag pada html sebenarnya memberikan konteks pada isi sebuah web, misalnya tag ```<h1>``` adalah judul utama web, ```<h2>``` adalah judul yang pentingnya kurang dari ```<h1>```, dst. tag ```<table>``` adalah untuk membuat table, tag ```<p>``` adalah untuk membuat paragraf, dsb.

3. **Margin vs Padding**

Perbedaan antara Margin dan Padding adalah pada tempatnya, Margin adalah ruang di luar batas elemen. Ini adalah jarak antara elemen tersebut dan elemen lainnya di sekitarnya. Margin digunakan untuk mengontrol jarak antara elemen dengan elemen lain di luar elemen tersebut. Margin tidak memiliki latar belakang atau warna, dan tidak akan memengaruhi tampilan elemen itu sendiri. Padding adalah ruang di dalam batas elemen. Ini adalah jarak antara konten elemen dan batasnya. Padding digunakan untuk mengatur jarak antara konten dalam elemen dan batas elemen tersebut. Padding dapat memiliki latar belakang atau warna, dan akan memengaruhi tampilan elemen dan kontennya.

4. **Bootstrap vs Tailwind**

Tailwind mempromosikan pendekatan utility-first, di mana Anda membangun antarmuka dengan menggabungkan kelas-kelas utilitas ke dalam elemen HTML. Ini memberikan fleksibilitas yang besar dalam merancang tampilan yang sesuai dengan kebutuhan. Sedangkan, Bootstrap menggunakan pendekatan komponen, yang berarti Anda menggunakan komponen-komponen yang sudah dibangun (seperti navbar, card, form) dengan gaya yang sudah ditentukan. Tailwind cenderung memiliki kebebasan desain lebih daripada bootstrap. Tailwind cenderung lebih ringan dan tidak ramah pemula dibanding Bootstrap.

# Tugas 6

## Proses Implementasi AJAX

1. Menambahkan banyak file ```.js``` sehingga bisa mengimplementasi kode asinkronus

2. Menambahkan kode GET secara AJAX, dengan menambahkan function
```javascript
async function getProducts() {
    return fetch("/debug/json").then((res) => res.json())
}
```
ke dalam file ```main.js```

3. Menambahkan kode POST secara AJAX, dengan menambahkan function
```javascript
async function refreshSalts() {
    document.getElementById("datatable").innerHTML = ""
    const products = await getProducts();
    let counter = 0;
    let htmlString = `
        <tr>
    `

    products.forEach((item) => {
        if (counter < 10) {
            if (counter % 5 == 0) {
                htmlString += `
                </tr>
                <tr>
                `
            }
            htmlString += `
            <td>
                <img src="../static/images/vector/${(parseInt(item.fields.sha256sum, 16) % 22) + 1}.svg"/>
                <button class="clickable button" id="button_delete" onClick="deleteSalt('${item.fields.sha256sum}')">
                    Delete
                </button>
            </td>`
        }
        counter++
    })

    htmlString += "</tr>"
    document.getElementById("datatable").innerHTML = htmlString
    document.getElementById("salt_count").innerHTML = `You have ${products.length} salts in your database...`
}
```
ke dalam file ```main.js```

4. Menjalankan command ```python manage.py collectstatic``` untuk merapikan semua static files

## Jawaban dari Pertanyaan

1. Dalam pemrograman sinkron, tugas-tugas dieksekusi satu per satu dalam urutan yang telah ditentukan. Artinya, program menunggu satu tugas selesai sebelum melanjutkan ke tugas berikutnya. Ini berarti jika ada tugas yang memakan waktu, seluruh program akan terhenti. Dalam pemrograman asinkron, tugas-tugas dieksekusi secara non-blok, yang berarti program dapat melanjutkan menjalankan tugas-tugas lainnya tanpa harus menunggu tugas yang sedang berjalan selesai. Ini memungkinkan program untuk tetap responsif, terutama saat ada operasi yang memakan waktu seperti mengambil data dari server atau melakukan operasi I/O. Intinya, web harus direload untuk melihat perubahan

2. Paradigma ini fokus pada penggunaan peristiwa (events) sebagai pemicu untuk menjalankan tindakan tertentu. Dalam konteks JavaScript dan AJAX, ini berarti program menunggu peristiwa (seperti klik tombol, pengiriman data, atau menerima respons dari server) untuk menjalankan fungsi tertentu. Ini memungkinkan program untuk merespons interaksi pengguna atau data yang diterima dari luar dengan cepat dan efisien.

3. AJAX (Asynchronous JavaScript and XML) memanfaatkan pemrograman asinkron untuk mengirim atau menerima data dari server tanpa harus memuat ulang seluruh halaman web. Dalam penerapan ini, Anda menggunakan callback functions atau Promise untuk menangani respons dari server saat data diterima atau dikirim. Ini memungkinkan halaman web untuk tetap responsif tanpa menghalangi interaksi pengguna.

4. Fetch API adalah bagian dari JavaScript yang dibangun ke dalam browser modern. Ini adalah API yang sangat kuat dan lebih modern untuk melakukan permintaan HTTP asinkron. Kelebihan dari Fetch API adalah lebih ringan, lebih sederhana, dan lebih fleksibel. Anda dapat menggunakan Promise dengan Fetch API, yang membuat penanganan asinkron lebih mudah dibandingkan dengan callback hell. Fetch API mendukung Promises dan kemampuan memparsing berbagai jenis respon, termasuk JSON. jQuery adalah library JavaScript yang memiliki fungsi untuk melakukan AJAX requests dan banyak fitur lainnya untuk manipulasi DOM dan animasi. Sementara jQuery sangat kuat dan mudah digunakan, penggunaannya cenderung lebih berat daripada Fetch API karena jQuery memiliki berbagai fitur lain yang mungkin tidak Anda butuhkan. Ini bisa mempengaruhi performa jika Anda hanya perlu melakukan permintaan AJAX tanpa fitur-fitur tambahan yang ditawarkan jQuery. Mana yang lebih baik adalah selera pribadi, jika tidak ingin mengimport suatu library external maka fetch API lah yang digunakan.
