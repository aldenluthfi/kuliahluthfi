---
sidebar_label: Tutorial 2
sidebar_position: 4
Path: docs/tutorial-2
---

# Tutorial 2: Form dan Data Delivery

Pemrograman Berbasis Platform (CSGE602022) — diselenggarakan oleh Fakultas Ilmu Komputer Universitas Indonesia, Semester Ganjil 2023/2024

---

## Tujuan Pembelajaran​

Setelah menyelesaikan tutorial ini, mahasiswa diharapkan untuk dapat:

- Mengetahui `XML` dan `JSON` sebagai salah satu metode _data delivery_
- Memahami cara kerja submisi data yang diberikan oleh pengguna menggunakan elemen `form`
- Memahami cara mengirimkan data menggunakan format `XML` dan `JSON`
- Memahami cara mengambil data spesifik berdasarkan `ID`

## Pengenalan Data Delivery

Dalam mengembangkan suatu _platform_, ada kalanya kita perlu mengirimkan data dari satu _stack_ ke _stack_ lainnya. Data yang dikirimkan bisa bermacam-macam bentuknya. Beberapa contoh format data yang umum digunakan antara lain HTML, XML, dan JSON. Implementasi _data delivery_ dalam bentuk HTML sudah kamu pelajari pada tutorial sebelumnya. Pada tutorial ini akan diajarkan terkait XML dan JSON.

## XML (Extensible Markup Language)

XML adalah singkatan dari _eXtensible Markup Language_. XML didesain menjadi _self-descriptive_, jadi dengan membaca XML tersebut kita bisa mengerti informasi apa yang ingin disampaikan dari data yang tertulis. XML digunakan pada banyak aplikasi web maupun _mobile_, yaitu untuk menyimpan dan mengirimkan data. XML hanya berisi informasi yang dibungkus di dalam _tag_. Kita perlu menulis program untuk mengirim, menerima, menyimpan, atau menampilkan informasi tersebut.

Contoh Format XML:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<person>
    <name>Alice Johnson</name>
    <age>25</age>
    <address>
        <street>123 Main St</street>
        <city>Los Angeles</city>
        <zip>90001</zip>
    </address>
</person>
```

XML di atas sangatlah _self-descriptive_:

- Ada informasi nama (`name`)
- Ada informasi umur (`age`)
- Ada informasi alamat (`address`)
  - Ada informasi jalan (`street`)
  - Ada informasi kota (`city`)
  - Ada informasi kode pos (`zip`)

Dokumen XML membentuk struktur seperti _tree_ yang dimulai dari _root_, lalu _branch_, hingga berakhir pada _leaves_. Dokumen XML **harus mengandung sebuah _root element_** yang merupakan _parent_ dari elemen lainnya. Pada contoh di atas, `<person>` adalah _root element_.

Untuk baris `<?xml version="1.0" encoding="UTF-8"?>` biasa disebut sebagai **XML Prolog**. XML Prolog bersifat opsional, akan tetapi jika ada maka posisinya harus berada di awal dokumen XML. Pada dokumen XML **semua elemen wajib memiliki** **closing tag**. **Tag** pada XML sifatnya **case sensitive**, sehingga tag `<person>` **berbeda** dengan tag `<Person>`.

## JSON (JavaScript Object Notation)

JSON adalah singkatan dari _JavaScript Object Notation_. JSON didesain menjadi _self-describing_, sehingga JSON sangat mudah untuk dimengerti. JSON digunakan pada banyak aplikasi web maupun _mobile_, yaitu untuk menyimpan dan mengirimkan data. Sintaks JSON merupakan turunan dari _Object_ JavaScript. Akan tetapi format JSON berbentuk _text_, sehingga kode untuk membaca dan membuat JSON banyak terdapat dibanyak bahasa pemrograman.

Contoh format JSON:

```json
{
    "name": "Alice Johnson",
    "age": 25,
    "address": {
        "street": "123 Main St",
        "city": "Los Angeles",
        "zip": "90001"
    }
}
```

Data pada JSON disimpan dalam bentuk _key_ dan _value_. Pada contoh di atas yang menjadi _key_ adalah `name`, `age`, dan `address`. _Value_ dapat berupa tipe data primitif (_string, number, boolean_) ataupun berupa objek.

## Tutorial: Panduan Mengatur _Routing_ dari `main/` ke `/`

Sebelum kita masuk ke pembahasan mengenai _form_, kita akan mengubah _routing_ `main/` menjadi `/` agar lebih sesuai dengan konvensi yang ada dan kami harap dapat menjawab pertanyaan kalian mengenai hal ini.

> Catatan: Pada tutorial ini, kamu akan menggunakan proyek yang sudah kamu buat pada tutorial sebelumnya.

1. Jalankan _virtual environment_ terlebih dahulu.

    - Windows:

        ```batch
        env\Scripts\activate.bat
        ```

    - Unix (Mac/Linux):

        ```bash
        source env/bin/activate
        ```

2. Buka `urls.py` yang ada pada folder `shopping_list` dan ubahlah _path_ `main/` menjadi `''` pada `urlpatterns` seperti berikut.

    ```python
    urlpatterns = [
        path('', include('main.urls')),
        path('admin/', admin.site.urls),
    ]
    ```

3. Jalankan server dengan perintah `python manage.py runserver` dan bukalah <http://localhost:8000/> di browser favoritmu untuk melihat hasilnya.

## Tutorial: Implementasi _Skeleton_ sebagai Kerangka _Views_

Sebelum kita membuat form registrasi, kita perlu membuat suatu _skeleton_ yang berfungsi sebagai kerangka views dari situs web kita. Dengan kerangka views ini, kita dapat memastikan adanya konsistensi dalam desain situs web kita serta memperkecil kemungkinan terjadinya redundansi kode. Pada tutorial ini, kita akan membuat _skeleton_ untuk situs web yang akan kita buat pada tutorial selanjutnya.

1. Buat _folder_ `templates` pada _root folder_ dan buatlah sebuah berkas HTML baru bernama `base.html`. Berkas `base.html` berfungsi sebagai _template_ dasar yang dapat digunakan sebagai kerangka umum untuk halaman web lainnya di dalam proyek. Isilah berkas `base.html` tersebut dengan kode berikut:

    ```html
    {% load static %}
    <!DOCTYPE html>
    <html lang="en">
        <head>
            <meta charset="UTF-8" />
            <meta
                name="viewport"
                content="width=device-width, initial-scale=1.0"
            />
            {% block meta %}
            {% endblock meta %}
        </head>

        <body>
            {% block content %}
            {% endblock content %}
        </body>
    </html>
    ```

2. Buka `settings.py` yang ada pada subdirektori `shopping_list` dan carilah baris yang mengandung `TEMPLATES`. Sesuaikan kode yang ada dengan potongan kode berikut agar berkas `base.html` terdeteksi sebagai berkas _template_.

    ```python
    ...
    TEMPLATES = [
        {
            'BACKEND': 'django.template.backends.django.DjangoTemplates',
            'DIRS': [BASE_DIR / 'templates'], # Tambahkan kode ini
            'APP_DIRS': True,
            ...
        }
    ]
    ...
    ```

3. Pada subdirektori `templates` yang ada pada direktori `main`, ubahlah kode berkas `main.html` yang telah dibuat di tutorial sebelumnya menjadi sebagai berikut.

    ```html
    {% extends 'base.html' %}

    {% block content %}
        <h1>Shopping List Page</h1>

        <h5>Name:</h5>
        <p>{{name}}</p>

        <h5>Class:</h5>
        <p>{{class}}</p>
    {% endblock content %}
    ```

    > Perhatikan bahwa kode diatas merupakan kode yang sama dengan kode pada `main.html` pada tutorial sebelumnya. Perbedaannya adalah pada kode diatas, kita menggunakan `base.html` sebagai _template_ utama.

## Tutorial: Membuat Form Input Data dan Menampilkan Data Produk Pada HTML

Sampai saat ini, belum ada data yang ditambahkan ke dalam aplikasi. Sekarang, kita akan membuat sebuah _form_ sederhana untuk menginput data barang pada aplikasi sehingga nantinya kamu dapat menambahkan data baru untuk ditampilkan pada halaman utama.

1. Buat berkas baru pada direktori `main` dengan nama `forms.py` untuk membuat struktur _form_ yang dapat menerima data produk baru. Tambahkan kode berikut ke dalam berkas `forms.py`.

    ```python
    from django.forms import ModelForm
    from main.models import Product

    class ProductForm(ModelForm):
        class Meta:
            model = Product
            fields = ["name", "price", "description"]
    ```

    **Penjelasan Kode:**

    - `model = Product` untuk menunjukkan model yang digunakan untuk _form_. Ketika data dari _form_ disimpan, isi dari _form_ akan disimpan menjadi sebuah objek `Product`.
    - `fields = ["name", "price", "description"]` untuk menunjukkan _field_ dari model Product yang digunakan untuk _form_. _Field_ `date_added` tidak dimasukkan ke _list_ `fields` karena tanggal ditambahkan secara otomatis.

2. Buka berkas `views.py` yang ada pada folder `main` dan tambahkan beberapa _import_ berikut pada bagian paling atas.

    ```python
    from django.http import HttpResponseRedirect
    from main.forms import ProductForm
    from django.urls import reverse
    ```

3. Buat fungsi baru dengan nama `create_product` pada berkas tersebut yang menerima parameter `request` dan tambahkan potongan kode di bawah ini untuk menghasilkan formulir yang dapat menambahkan data produk secara otomatis ketika data di-_submit_ dari _form_.

    ```python
    def create_product(request):
        form = ProductForm(request.POST or None)

        if form.is_valid() and request.method == "POST":
            form.save()
            return HttpResponseRedirect(reverse('main:show_main'))

        context = {'form': form}
        return render(request, "create_product.html", context)
    ```

    **Penjelasan Kode:**

    - `form = ProductForm(request.POST or None)` digunakan untuk membuat `ProductForm` baru dengan memasukkan QueryDict berdasarkan input dari _user_ pada `request.POST`.
    - `form.is_valid()` digunakan untuk memvalidasi isi input dari _form_ tersebut.
    - `form.save()` digunakan untuk membuat dan menyimpan data dari _form_ tersebut.
    - `return HttpResponseRedirect(reverse('main:show_main'))` digunakan untuk melakukan _redirect_ setelah data _form_ berhasil disimpan.

4. Ubahlah fungsi `show_main` yang sudah ada pada berkas `views.py` menjadi seperti berikut.

    ```python
    def show_main(request):
        products = Product.objects.all()

        context = {
            'name': 'Pak Bepe', # Nama kamu
            'class': 'PBP A', # Kelas PBP kamu
            'products': products
        }

        return render(request, "main.html", context)
    ```

    **Penjelasan Kode:**

    Fungsi `Product.objects.all()` digunakan untuk mengambil seluruh object Product yang tersimpan pada _database_.

5. Buka `urls.py` yang ada pada folder `main` dan _import_ fungsi `create_product` yang sudah kamu buat tadi.

    ```python
    from main.views import show_main, create_product
    ```

6. Tambahkan _path url_ ke dalam `urlpatterns` pada `urls.py` di `main` untuk mengakses fungsi yang sudah di-_import_ pada poin sebelumnya.

    ```python
    path('create-product', create_product, name='create_product'),
    ```

7. Buat berkas HTML baru dengan nama `create_product.html` pada direktori `main/templates`. Isi `create_product.html` dengan kode berikut.

    ```html
    {% extends 'base.html' %} 

    {% block content %}
    <h1>Add New Product</h1>

    <form method="POST">
        {% csrf_token %}
        <table>
            {{ form.as_table }}
            <tr>
                <td></td>
                <td>
                    <input type="submit" value="Add Product"/>
                </td>
            </tr>
        </table>
    </form>

    {% endblock %}
    ```

    **Penjelasan Kode:**

    - `<form method="POST">` digunakan untuk menandakan _block_ untuk _form_ dengan metode POST.
    - `{% csrf_token %}` adalah token yang berfungsi sebagai _security_. Token ini di-_generate_ secara otomatis oleh Django untuk mencegah serangan berbahaya.
    - `{{ form.as_table }}` digunakan untuk menampilkan _fields_ form yang sudah dibuat pada `forms.py` sebagai _table_.
    - `<input type="submit" value="Add Product"/>` digunakan sebagai tombol _submit_ untuk mengirimkan _request_ ke _view_ `create_product(request)`.

8. Buka `main.html` dan tambahkan kode berikut di dalam `{% block content %}` untuk menampilkan data produk dalam bentuk _table_ serta tombol "Add New Product" yang akan _redirect_ ke halaman _form_.

    ```html
    ...
    <table>
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Description</th>
            <th>Date Added</th>
        </tr>

        {% comment %} Berikut cara memperlihatkan data produk di bawah baris ini {% endcomment %}

        {% for product in products %}
            <tr>
                <td>{{product.name}}</td>
                <td>{{product.price}}</td>
                <td>{{product.description}}</td>
                <td>{{product.date_added}}</td>
            </tr>
        {% endfor %}
    </table>

    <br />

    <a href="{% url 'main:create_product' %}">
        <button>
            Add New Product
        </button>
    </a>
    
    {% endblock content %}
    ```

9. Jalankan proyek Django-mu dengan perintah `python manage.py runserver` dan bukalah <http://localhost:8000> di browser favoritmu. Coba tambahkan beberapa data produk baru dan seharusnya kamu dapat melihat data yang ditambahkan pada halaman utama aplikasi.

## Tutorial Mengembalikan Data dalam Bentuk XML

1. Buka `views.py` yang ada pada folder `main` dan tambahkan _import_ `HttpResponse` dan `Serializer` pada bagian paling atas.

    ```python
    from django.http import HttpResponse
    from django.core import serializers
    ```

2. Buatlah sebuah fungsi yang menerima parameter _request_ dengan nama `show_xml` dan buatlah sebuah variabel di dalam fungsi tersebut yang menyimpan hasil _query_ dari seluruh data yang ada pada `Product`.

    ```python
    def show_xml(request):
        data = Product.objects.all()
    ```

3. Tambahkan _return function_ berupa `HttpResponse` yang berisi parameter data hasil _query_ yang sudah diserialisasi menjadi XML dan parameter `content_type="application/xml"`.

    ```python
    def show_xml(request):
        data = Product.objects.all()
        return HttpResponse(serializers.serialize("xml", data), content_type="application/xml")
    ```

    **Penjelasan Kode:**

    `serializers` digunakan untuk _translate_ objek model menjadi format lain seperti dalam fungsi ini adalah XML.

4. Buka `urls.py` yang ada pada folder `main` dan _import_ fungsi yang sudah kamu buat tadi.

    ```python
    from main.views import show_main, create_product, show_xml 
    ```

5. Tambahkan _path url_ ke dalam `urlpatterns` untuk mengakses fungsi yang sudah diimpor tadi.

    ```python
    ...
    path('xml/', show_xml, name='show_xml'), 
    ...
    ```

6. Jalankan proyek Django-mu dengan perintah `python manage.py runserver` dan bukalah <http://localhost:8000/xml> di browser favoritmu untuk melihat hasilnya.

## Tutorial: Mengembalikan Data dalam Bentuk JSON

1. Buka `views.py` yang ada pada folder `main` dan buatlah sebuah fungsi baru yang menerima parameter _request_ dengan nama `show_json` dengan sebuah variabel di dalamnya yang menyimpan hasil _query_ dari seluruh data yang ada pada `Product`.

    ```python
    def show_json(request):
        data = Product.objects.all()
    ```

2. Tambahkan _return function_ berupa `HttpResponse` yang berisi parameter data hasil _query_ yang sudah diserialisasi menjadi JSON dan parameter `content_type="application/json"`.

    ```python
    def show_json(request):
        data = Product.objects.all()
        return HttpResponse(serializers.serialize("json", data), content_type="application/json")
    ```

3. Buka `urls.py` yang ada pada folder `main` dan _import_ fungsi yang sudah kamu buat tadi.

    ```python
    from main.views import show_main, create_product, show_xml, show_json
    ```

4. Tambahkan _path url_ ke dalam `urlpatterns` untuk mengakses fungsi yang sudah diimpor tadi.

    ```python
    ...
    path('json/', show_json, name='show_json'), 
    ...
    ```

5. Jalankan proyek Django-mu dengan perintah `python manage.py runserver` dan bukalah <http://localhost:8000/json> (sesuaikan dengan _path url_ yang dibuat) di browser favoritmu untuk melihat hasilnya.

## Tutorial: Mengembalikan Data Berdasarkan ID dalam Bentuk XML dan JSON

1. Buka `views.py` yang ada pada folder `main` dan buatlah sebuah fungsi baru yang menerima parameter _request_ dan id dengan nama `show_xml_by_id` dan `show_json_by_id`.

2. Buatlah sebuah variabel di dalam fungsi tersebut yang menyimpan hasil _query_ dari data dengan id tertentu yang ada pada `Product`.

    ```python
    data = Product.objects.filter(pk=id)
    ```

3. Tambahkan _return function_ berupa `HttpResponse` yang berisi parameter data hasil _query_ yang sudah diserialisasi menjadi JSON atau XML dan parameter `content_type` dengan _value_ `"application/xml"` (untuk format XML) atau `"application/json"` (untuk format JSON).

    - XML

        ```python
        def show_xml_by_id(request, id):
            data = Product.objects.filter(pk=id)
            return HttpResponse(serializers.serialize("xml", data), content_type="application/xml")
        ```

    - JSON

        ```python
        def show_json_by_id(request, id):
            data = Product.objects.filter(pk=id)
            return HttpResponse(serializers.serialize("json", data), content_type="application/json")
        ```

4. Buka `urls.py` yang ada pada folder `main` dan impor fungsi yang sudah kamu buat tadi.

    ```python
    from main.views import show_main, create_product, show_xml, show_json, show_xml_by_id, show_json_by_id 
    ```

5. Tambahkan _path url_ ke dalam `urlpatterns` untuk mengakses fungsi yang sudah diimpor tadi.

    ```python
    ...
    path('xml/<int:id>/', show_xml_by_id, name='show_xml_by_id'),
    path('json/<int:id>/', show_json_by_id, name='show_json_by_id'), 
    ...
    ```

6. Jalankan proyek Django-mu dengan perintah `python manage.py runserver` dan bukalah <http://localhost:8000/xml/[id]> atau <http://localhost:8000/json/[id]> di browser favoritmu untuk melihat hasilnya.

## Tutorial: Penggunaan Postman Sebagai _Data Viewer_

1. Pastikan server kamu sudah berjalan dengan perintah `python manage.py runserver`.

2. Buka Postman dan buatlah sebuah _request_ baru dengan _method_ `GET` dan _url_ <http://localhost:8000/xml> atau <http://localhost:8000/json> untuk mengetes apakah data terkirimkan dengan baik.

    > Panduan Instalasi Postman dapat dilihat pada [Laman Resmi Postman](#referensi-tambahan).

    Contoh:
    ![Tampilan Halaman Postman](https://cdn.discordapp.com/attachments/923523971226435584/1150063777648214168/image.png)

3. Klik tombol `Send` untuk mengirimkan _request_ tersebut.

4. Kamu akan melihat hasil _response_ dari _request_ tersebut pada bagian bawah Postman.

    ![Sent](https://cdn.discordapp.com/attachments/923523971226435584/1150063647599624333/image.png)

5. Kamu juga dapat mengubah _url_ menjadi <http://localhost:8000/xml/[id]> atau <http://localhost:8000/json/[id]> untuk mengetes fungsi pengambilan data produk berdasarkan ID.

    ![Json By ID](https://cdn.discordapp.com/attachments/923523971226435584/1150064359658238033/image.png)

## Tutorial: Menambahkan Konfigurasi _Deployment_ ke PaaS PBP Fasilkom UI

Pada tutorial ini, kita akan mencoba untuk mengonfirgurasi aplikasi Django yang sudah dibuat untuk _deployment_ ke PaaS (_Platform-as-a-Service_) yang sudah disediakan khusus untuk keperluan PBP Fasilkom UI. Namun migrasi ke PaaS tersebut dilakukan secara bertahap, sehingga kita juga tetap melakukan _deployment_ ke Adaptable sambil melakukan _deployment_ ke PaaS PBP Fasilkom UI.

Pastikan kamu sudah mengisi form pendataan data _deployment_  yang telah disediakan sebelumnya, karena PaaS PBP Fasilkom UI masih membutuhkan konfigurasi di sisi administrator.

1. Buka berkas `requirements.txt` pada _root folder_ dan tambahkan `django-environ` di baris terakhir berkas. Jangan lupa untuk menyimpan berkas setelah kamu memodifikasinya.

2. Jalankan perintah `pip install -r requirements.txt` untuk menginstal perubahan pada berkas `requirements.txt`.

3. Buat berkas baru bernama `Procfile` (**tanpa format ekstensi file**) pada _root folder_ dan isi berkas tersebut dengan kode berikut.

    ```yaml
    release: django-admin migrate --noinput
    web: gunicorn project_name.wsgi
    ```

    > Sesuaikan `project_name` dengan nama proyek Django (`shopping_list`).

4. Buat folder baru bernama `.github` (dengan tanda titik di awal nama folder) pada _root folder_ dan buat folder baru di dalam folder `.github` dengan nama `workflows`.

5. Buat berkas baru bernama `pbp-deploy.yml` di dalam folder `workflows` dan isi berkas tersebut dengan kode berikut.

    ```yaml
    name: Deploy

    on:
      push:
        branches:
          - main
          - master

    jobs:
      Deployment:
        if: github.ref == 'refs/heads/main'
        runs-on: ubuntu-latest
        steps:
        - name: Cloning repo
          uses: actions/checkout@v4
          with:
            fetch-depth: 0

        - name: Push to Dokku server
          uses: dokku/github-action@master
          with:
            branch: 'main'
            git_remote_url: ssh://dokku@${{ secrets.DOKKU_SERVER_IP }}/${{ secrets.DOKKU_APP_NAME }}
            ssh_private_key: ${{ secrets.DOKKU_SSH_PRIVATE_KEY }}
    ```

6. Buat berkas baru bernama `.dockerignore` (**tanpa format ekstensi file dan tanda titik di awal**) pada _root folder_ dan isi berkas tersebut dengan kode berikut.

    ```yaml
    **/*.pyc
    **/*.pyo
    **/*.mo
    **/*.db
    **/*.css.map
    **/*.egg-info
    **/*.sql.gz
    **/__pycache__/
    .cache
    .project
    .idea
    .pydevproject
    .idea/workspace.xml
    .DS_Store
    .git/
    .sass-cache
    .vagrant/
    dist
    docs
    env
    logs
    src/{{ project_name }}/settings/local.py
    src/node_modules
    web/media
    web/static/CACHE
    stats
    Dockerfile
    .gitignore
    Dockerfile
    db.sqlite3
    **/*.md
    logs/
    ```

7. Buat berkas baru bernama `Dockerfile` (**tanpa format ekstensi file**) pada _root folder_ dan isi berkas tersebut dengan kode berikut.

    ```dockerfile
    FROM python:3.10-slim-buster

    WORKDIR /app

    ENV PYTHONUNBUFFERED=1 \
        PYTHONPATH=/app \
        DJANGO_SETTINGS_MODULE=shopping_list.settings \
        PORT=8000 \
        WEB_CONCURRENCY=2

    # Install system packages required Django.
    RUN apt-get update --yes --quiet && apt-get install --yes --quiet --no-install-recommends \
    && rm -rf /var/lib/apt/lists/*

    RUN addgroup --system django \
        && adduser --system --ingroup django django

    # Requirements are installed here to ensure they will be cached.
    COPY ./requirements.txt /requirements.txt
    RUN pip install -r /requirements.txt

    # Copy project code
    COPY . .

    RUN python manage.py collectstatic --noinput --clear

    # Run as non-root user
    RUN chown -R django:django /app
    USER django

    # Run application
    # CMD gunicorn shopping_list.wsgi:application
    ```

8. Buka berkas `settings.py` yang ada di dalam folder `shopping_list`.

9. Tambahkan kode `import environ` dan `import os` setelah baris kode `from pathlib import Path`.

    ```python
    from pathlib import Path
    import environ # Tambahkan kode berikut
    import os # Tambahkan kode berikut
    ```

10. Tambahkan kode `env = environ.Env()` setelah baris kode `BASE_DIR`.

    ```python
    BASE_DIR = Path(__file__).resolve().parent.parent

    env = environ.Env() # Tambahkan kode berikut
    ```

11. Tambahkan kode berikut setelah baris kode `SECRET_KEY`.

    ```python
    # Automatically determine environment by detecting if DATABASE_URL variable.
    # DATABASE_URL is provided by Heroku if a database add-on is added (e.g. Heroku Postgres).
    PRODUCTION = env.bool('PRODUCTION', False)
    ```

12. Tambahkan kode berikut setelah bagian kode `DATABASES`.

    ```python
    # Set database settings automatically using DATABASE_URL.
    if PRODUCTION:
        DATABASES = {
            'default': env.db('DATABASE_URL')
        }
        DATABASES["default"]["ATOMIC_REQUESTS"] = True
    ```

13. Tambahkan kode berikut setelah bagian kode `STATIC_URL`.

    ```python
    STATIC_URL = 'static/'

    STATIC_ROOT = os.path.join(BASE_DIR, 'static')
    ```

Saat ini, kita sudah mengonfigurasi aplikasi Django kita untuk _deployment_ ke PaaS PBP Fasilkom UI. Selanjutnya, kita akan mengonfigurasi repositori GitHub aplikasi kita agar _deployment_ dapat dilakukan secara otomatis.

1. Buka repositori GitHub dari aplikasi kamu dan buka menu `Settings` yang ada di bagian paling kanan.

2. Buka menu `Secrets and variables` dan pilih submenu `Actions`.

    ![Action secrets and variables](https://media.discordapp.net/attachments/923523971226435584/1150303039882801234/image.png)

3. Klik tombol `New repository secret` yang ada di bagian kanan untuk menambahkan variabel rahasia baru.

4. Buat tiga variabel rahasia baru dengan spesifikasi sebagai berikut.

    | **Name**              | **Secret**                 |
    |-----------------------|----------------------------|
    | DOKKU_SERVER_IP       | pbp.cs.ui.ac.id            |
    | DOKKU_APP_NAME        | UsernameSIAK-tutorial      |
    | DOKKU_SSH_PRIVATE_KEY | [Isi dari SSH private key] |

    > Sesuaikan `UsernameSIAK-tutorial` dengan data kalian, ya. Ubah juga tanda titik menjadi tanda strip. Contoh: `muhammad-iqbal111-tutorial`.

    > (13 September 2023) Untuk sekarang, **jangan isi `DOKKU_SERVER_IP` dengan _value_ yang diberikan**. Isi variabel `DOKKU_APP_NAME` dan `DOKKU_SSH_PRIVATE_KEY` saja karena masih ada isu di server untuk saat ini.

    > Buka [tutorial berikut](https://docs.google.com/document/d/1ZVsfUBaEGvuioYMmP-gboKLFd_2c_EPTYyC-urev5UQ/edit?usp=sharing) untuk menyalin _private key_ kamu.

    Contoh hasil akan tampak seperti berikut.

    ![Example of secrets](https://media.discordapp.net/attachments/923523971226435584/1150303593874849813/image.png)

Kita telah mengonfigurasi repositori GitHub untuk _deployment_ secara otomatis. Selamat, kamu telah mengonfigurasi proyek aplikasimu untuk _deployment_ ke PaaS PBP Fasilkom UI!

Untuk mengakses _deployment_ proyek aplikasimu di PaaS PBP Fasilkom UI, **gunakan protokol HTTP** dan `UsernameSIAK-tutorial` sebagai URL. Contohnya adalah <http://muhammad-athallah01-tutorial.pbp.cs.ui.ac.id>.

## Penutup

1. Setelah menyelesaikan tutorial ini, tampilan halaman web kamu seharusnya terlihat seperti ini.

    ![Tampilan Halaman Web](https://cdn.discordapp.com/attachments/923523971226435584/1149953373177651251/image.png)

2. Pada akhir tutorial ini, struktur direktori lokalmu akan terlihat seperti ini.

    ![Struktur Direktori Lokal](https://cdn.discordapp.com/attachments/923523971226435584/1151130212784943144/image.png)

3. Sebelum melakukan langkah ini, **pastikan struktur direktori lokal sudah benar**. Selanjunya, lakukan `add`, `commit` dan `push` untuk memperbarui repositori GitHub. Ketika repositori GitHub diperbarui, Adaptable secara otomatis akan melakukan _deploy_ kembali. Bila berhasil, fitur yang kamu buat pada tutorial ini sudah bisa diakses secara umum.

4. Jalankan perintah berikut untuk melakukan `add`, `commit` dan `push`.

    ```shell
    git add .
    git commit -m "<pesan_commit>"
    git push -u origin <branch_utama>
    ```

    - Ubah `<pesan_commit>` sesuai dengan keinginan. Contoh: `git commit -m "tutorial 2 selesai"`.
    - Ubah `<branch_utama>` sesuai dengan nama branch utamamu. Contoh: `git push -u origin main` atau `git push -u origin master`.

## Referensi Tambahan

- [How to install Postman](https://learning.postman.com/docs/getting-started/installation/installation-and-updates/#:~:text=Postman%20is%20available%20on%20the,select%20Download%20for%20your%20platform.)

## Kontributor

- Rayhan Putra Randi
- Anindya Lokeswara
- Kade Satrya Noto Sadharma
- Alfredo Austin
- Alya Azhar Agharid

## Credits

Tutorial ini dikembangkan berdasarkan [PBP Ganjil 2023](https://github.com/pbp-fasilkom-ui/ganjil-2023) dan [PBP Genap 2023](https://github.com/pbp-fasilkom-ui/genap-2023) yang ditulis oleh Tim Pengajar Pemrograman Berbasis Platform 2023. Segala tutorial serta instruksi yang dicantumkan pada repositori ini dirancang sedemikian rupa sehingga mahasiswa yang sedang mengambil mata kuliah Pemrograman Berbasis Platform dapat menyelesaikan tutorial saat sesi lab berlangsung.
