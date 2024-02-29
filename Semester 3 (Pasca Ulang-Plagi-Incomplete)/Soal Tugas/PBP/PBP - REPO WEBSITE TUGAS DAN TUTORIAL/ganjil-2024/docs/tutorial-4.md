---
sidebar_label: Tutorial 4
sidebar_position: 6
Path: docs/tutorial-4
---

# Tutorial 4: Desain Web Menggunakan HTML dan CSS3 & Metode Update dan Delete pada Data

Pemrograman Berbasis Platform (CSGE602022) — diselenggarakan oleh Fakultas Ilmu Komputer Universitas Indonesia, Semester Ganjil 2023/2024

---

## Tujuan Pembelajaran

Setelah menyelesaikan tutorial ini, mahasiswa diharapkan untuk dapat:

- Memahami konsep _update_ dan _delete_
- Memahami cara _update_ dan _delete_ pada Django
- Memahami susunan tag pada HTML5
- Mengetahui berbagai jenis tag HTML5
- Memahami sintaks penulisan CSS
- Memahami konsep _static files_ pada Django
- Memahami penggunaan _selector_ pada CSS

## Pengenalan HTML

HyperText Markup Language (HTML) adalah bahasa _markup_ standar untuk dokumen agar dapat ditampilkan dalam _browser_ web. HTML mendefinisikan struktur dari konten suatu _website_.

Silakan pelajari dan mencoba sendiri HTML pada referensi [ini](https://www.w3schools.com/html/default.asp).

Perbedaan antara HTML dan HTML5 bisa kamu baca pada referensi [ini](https://www.geeksforgeeks.org/difference-between-html-and-html5/).

## Pengenalan CSS

### Apa itu CSS?

Cascading Style Sheets (CSS) adalah bahasa yang digunakan untuk mendeskripsikan tampilan dan format dari sebuah situs web yang ditulis pada _markup language_ (seperti HTML). CSS berguna untuk membuat tampilan situs web menjadi lebih menarik.

Untuk mempelajari perbedaan antara CSS dan CSS3 bisa kamu baca pada referensi [ini](https://www.geeksforgeeks.org/difference-between-css-and-css3/).

### Cara Penulisan CSS

Secara umum, CSS dapat dituliskan dalam bentuk sebagai berikut.

```css
selector {
  properties: value;
}
```

Silakan pelajari dan mencoba sendiri CSS pada referensi [ini](https://www.w3schools.com/css/).

Terdapat tiga jenis cara penulisan CSS:

1. **_Inline Styles_**
2. **_Internal Style Sheet_**
3. **_External Style Sheet_**

Silakan pelajari tentang ketiga jenis CSS tersebut pada referensi [ini](https://www.geeksforgeeks.org/types-of-css-cascading-style-sheet/).

Perlu diperhatikan, jika kamu membuat jenis _External Style Sheet_, kamu perlu menambahkan _tag_ `{% load staticfiles %}` pada halaman HTML kamu. Contohnya seperti potongan kode di bawah ini.

```html
{% load staticfiles %}
<html>
  <head>
    <title>Tutorial CSS Yay</title>
    <link rel="stylesheet" href="{% static 'css/tutorial.css' %}" />
  </head>
  <body>
    <div>
      <h1>Tutorial CSS Yay</h1>
    </div>
    <div id="main">
      <div>
        <p>published: 27 September 2023</p>
        <h1><a href="">Tutorial CSS ku</a></h1>
        <p>Yay ini tutorial yang gampang!</p>
      </div>
    </div>
  </body>
</html>
```

Hal ini dapat terjadi karena CSS merupakan _static files_ di Django. _Static files_ akan dijelaskan pada bagian selanjutnya.

### Catatan Tambahan

Jika terdapat lebih dari satu _style_ yang didefinisikan untuk suatu elemen, maka _style_ yang akan diterapkan adalah yang memiliki prioritas yang lebih tinggi. Berikut ini urutan prioritasnya, nomor 1 yang memiliki prioritas paling tinggi.

1. _Inline style_
2. _External_ dan internal _style sheets_
3. _Browser default_

## *Static files* pada Django

Pada _framework_ Django, terdapat _file-file_ yang disebut dengan _static files_. _Static files_ merupakan _file-file_ pendukung HTML pada suatu situs web. Contoh _static files_ antara lain seperti CSS, JavaScript dan gambar. 

Pengaturan untuk _static files_ terletak pada _file_ **`settings.py`**:

```html 
...
# Static files (CSS, JavaScript, Images)
# httpsdocs.djangoproject.comen1.9howtostatic-files
STATIC_ROOT = os.path.join(PROJECT_ROOT, 'static')
STATIC_URL = 'static'
...
```
Pada `settings.py`, terdapat: 
- `STATIC_ROOT` yang menentukan _absolute path_ ke direktori _static files_ ketika menjalankan perintah `collectstatic` pada proyek dan terdapat `STATIC_URL` yang merupakan URL yang dapat diakses publik untuk memperoleh _static files_ tersebut.
- `STATIC_URL` yang merupakan URL yang dapat diakses publik untuk memperoleh _static files_ tersebut.

Perintah `collectstatic` adalah perintah untuk mengumpulkan _static files_ dari semua _app_ sehingga mempermudah akses untuk semua _app_.

Penjelasan lebih lengkap mengenai static files dapat kamu baca pada referensi [ini](https://docs.djangoproject.com/en/4.1/ref/contrib/staticfiles/).

## _Selector_ pada CSS

Pada tutorial ini, kita akan mengenak tiga jenis _selector_: **_Element Selector_**, **ID _Selector_**, dan **_Class Selector_**.

1. **_ELement Selector_**

    _Element Selector_ memungkinkan kita mengubah properti untuk semua elemen yang memiliki tag HTML yang sama.
    
    Contoh penggunaan _Element Selector_:
    ```html 
    <body>
      <div>
        <h1>Tutorial CSS Yay :D</h1>
        <h2>Tutorial CSS Yay kedua :D</h2>
      </div>
      ...
    </body>
    ```
    
    Kita dapat menggunakan _element_ sebagai _selector_ dalam **_file_ CSS**. _Element selector_ menggunakan format *[id_name]* (tanpa diawali oleh sebuah simbol)
    
    ```html 
    h1 {
      color: #fca205;
      font-family: "Monospace";
      font-style: italic;
    }
    ```
2. **ID _Selector_**

    ID _selector_ menggunakan ID pada _tag_ sebagai _selector_-nya. ID bersifat unik dalam satu halaman web. ID dapat ditambahkan pada halaman template HTML.
    
    Contoh penggunaan ID _Selector_ pada **_template_ HTML**:
    
    ```html 
    <body>
      <div id="header">
        <h1>Tutorial CSS Yay :D</h1>
      </div>
      ...
    </body>
    ```
    
    Kemudian, kita dapat menggunakan ID tersebut sebagai _selector_ dalam **_file_ CSS**. ID _selector_ menggunakan format *#[id_name]* (selalu diawali #)
    ```html 
    #header {
      background-color: #f0f0f0;
      margin-top: 0;
      padding: 20px 20px 20px 40px;
    }
    ```
    
3. **_Class Selector_**
    
    _Class Selector_ memungkinkan kita untuk mengelompokkan elemen dengan karakteristik yang sama.
    
    Contoh penggunaan _Class Selector_ pada **_template_ HTML**:
    
    ```html
    ...
    <div id="main">
        <div class="content_section">
            <p class="date">published: 28 September 2022</p>
            <h2><a href="">Tutorial CSS ku</a></h2>
            <p id="content_1">Yay ini tutorial yang gampang!</p>
        </div>
        <div class="content_section">
            <p class="date ">published: 29 September 2022</p>
            <h2><a href="">Tutorial CSS mu</a></h2>
            <p id="content_2">Yay ini tutorial yang mudah!</p>
        </div>
        <div class="content_section">
            <p>published: 30 September 2022</p>
            <h2><a href="">Tutorial CSS semua</a></h2>
            <p id="content_3">Yay ini tutorial yang tidak sulit!</p>
        </div>
    </div>
    ...
    ```
    
    Kemudian, kita dapat menggunakan _Class_ tersebut sebagai _selector_ dalam **_file_ CSS**. _Class selector_ menggunakan format *.[class_name]* (diawali .)
    
    ```html
    .content_section {
      background-color: #3696e1;
      margin-bottom: 30px;
      color: #000000;
      font-family: cursive;
      padding: 20px 20px 20px 40px;
    }
    ```
    
Untuk memperdalam pengetahuan mengenai CSS _Selector Reference_, kamu dapat membaca referensi [ini](https://www.w3schools.com/cssref/css_selectors.php).

## Tips & trik CSS

### Mengenal _Combinator_ pada CSS
_Combinator_ dalam CSS menghubungkan dua atau lebih _selector_ untuk merincikan lebih lanjut elemen-elemen yang di-*select*.

Terdapat empat *combinators* pada CSS. Berikut ringkasan pemakaiannya: 
    
| *Combinator* | Contoh pemakaian | Penjelasan contoh |
| -------- | -------- | -------- |
| Descendant selector (space) | `div p`     | Menyeleksi semua elemen `p` yang merupakan keturunan dari elemen `div` |
| Child selector (>) | `div > p`     | Menyeleksi semua elemen `p` yang merupakan anak dari elemen `div` |
| Adjacent sibling selector (+) | `div + p` | Menyeleksi elemen `p` pertama yang berada tepat setelah elemen `div` (harus memiliki elemen induk yang sama) |
| General sibling selector (~) | `div ~ p` | Menyeleksi semua elemen `p` yang sejajar dan berada setelah elemen `div` |

Silakan pelajari lebih lanjut mengenai *combinator*  melalui [referensi ini](https://www.w3schools.com/css/css_combinators.asp).

### Mengenal *Pseudo-class* pada CSS
*Pseudo-class* digunakan untuk mendefinisikan *state* khusus dari suatu elemen. Sintaks pemakaian *pseudo-class* adalah sebagai berikut:

```css
selector:pseudo-class {
  property: value;
}
```


Beberapa contoh *pseudo-class*:
| Pseudo-class | Mengaplikasikan *style* pada .. |
| -------- | -------- |
| `:link` | tautan yang belum pernah dikunjungi |
| `:visited` | tautan yang sudah pernah dikunjungi |
| `:hover` | saat pengguna mengarahkan kursor di atas elemen tersebut |
| `:active` | saat elemen diaktifkan (biasanya saat pengguna mengklik elemen tersebut) |
| `:focus` | saat elemen fokus (seperti saat pengguna mengklik *input field*) |
| `:checked` | elemen yang telah dicentang |
| `:disabled` | elemen yang telah dibuat tidak responsif (misalnya tombol yang tidak bisa diklik) |

Silakan pelajari lebih lanjut mengenai *pseudo-class*  melalui [referensi ini](https://www.w3schools.com/css/css_pseudo_classes.asp).

### Mengenal Box Model pada CSS

![css box model](https://hackmd.io/_uploads/B1QiTx9ya.png)

*Box model* pada CSS pada dasarnya merupakan suatu *box* yang membungkus setiap elemen HTML dan terdiri atas:

* *Content*: isi dari *box* (tempat terlihatnya teks dan gambar)
* *Padding*: mengosongkan area di sekitar konten (transparan)
* *Border*: garis tepian yang membungkus konten dan *padding*-nya
* *Margin*: mengosongkan area di sekitar *border* (transparan)

Silakan pelajari lebih lanjut mengenai *margin*, *border*, dan *padding* melalui [referensi ini](https://w3schools.com/css/css_boxmodel.asp).

## Pengenalan Bootstrap & Tailwind

Pada bidang pengembangan web, terdapat banyak *framework* CSS yang sering digunakkan. Fungsi sebuah *framework* adalah untuk mempermudah pekerjaan *programmer* pada saat mengerjakan pekerjaan mereka. *Framework* CSS yang populer saat ini adalah **Bootstrap** dan juga **Tailwind**. Kedua *framework* ini memberikan banyak kelebihan dibandingkan CSS yang biasa kita gunakan. Berikut adalah kelebihan-kelebihan penggunaan *framework* yang diperoleh dibandingkan CSS biasa:

1. Proses Pengembangan Lebih Cepat: *Framework* seperti Bootstrap menyediakan komponen siap pakai sehingga tanpa harus menulis kode CSS dari awal. 
2. Responsif secara Bawaan: *Framework* seperti Bootstrap dan Tailwind telah dirancang dengan responsif.
3. Skalabilitas Besar: *Framework* CSS memberikan struktur yang baik untuk proyek yang berkembang seiring waktu. 

Bootstrap dan Tailwind tentu saja sebagai *framework* memiliki perbedaan yang signifikan antara satu sama lain, yaitu:


| Tailwind | Bootstrap |
| -------- | --------- |
| Tailwind CSS membangun tampilan dengan menggabungkan kelas-kelas utilitas yang telah didefinisikan sebelumnya.     | Bootstrap menggunakan gaya dan komponen yang telah didefinisikan, yang memiliki tampilan yang sudah jadi dan dapat digunakan secara langsung.      |
| Tailwind CSS memiliki *file* CSS yang lebih kecil sedikit dibandingkan Bootstrap dan hanya akan memuat kelas-kelas utilitas yang ada | Bootstrap memiliki *file* CSS yang lebih besar dibandingkan dengan Tailwind CSS karena termasuk banyak komponen yang telah didefinisikan. |
| Tailwind CSS memiliki memberikan fleksibilitas dan adaptabilitas tinggi terhadap proyek | Bootstrap sering kali menghasilkan tampilan yang lebih konsisten di seluruh proyek karena menggunakan komponen yang telah didefinisikan. |
| Tailwind CSS memiliki pembelajaran yang lebih curam karena memerlukan pemahaman terhadap kelas-kelas utilitas yang tersedia dan bagaimana menggabungkannya untuk mencapai tampilan yang diinginkan. | Bootstrap memiliki pembelajaran yang lebih cepat untuk pemula karena dapat mulai dengan komponen yang telah didefinisikan. |


## Responsive Web Design

*Responsive web design* merupakan metode sistem desain web yang memiliki tujuan untuk menghasilkan tampilan web yang terlihat baik pada seluruh perangkat seperti *desktop*, *tablet*, *mobile*, dan sebagainya. *Responsive web design* tidak mengubah isi dari situs web, tetapi hanya mengubah tampilan dan penataan pada setiap perangkat agar sesuai dengan lebar layar dan kemampuan perangkat tersebut. Dalam *responsive web design* tampilan-tampilan tertentu membutuhkan bantuan CSS (seperti mengecilkan atau membesarkan) suatu elemen. 

Salah satu cara untuk menguji apakah suatu situs web menggunakan *responsive web design* adalah dengan mengakses situs web tersebut dan mengaktifkan fitur `Toggle Device Mode` pada *browser*. Fitur ini memungkinkan kamu untuk melihat bagaimana situs web tersebut ditampilkan pada berbagai perangkat, seperti komputer, tablet, atau *smartphone*, tanpa harus mengubah ukuran jendela *browser*. 

Berikut adalah cara untuk mengakses fitur tersebut pada *browser* **Google Chrome**.
* Windows/Linux : `CTRL + SHIFT + M`
* Mac : `Command + Shift + M`
 
Cara lain yang lebih praktis adalah dengan melakukan klik kanan pada *browser* dan memilih *Inspect Element/Inspect* untuk membuka *Dev Tools* yang berguna.

Untuk mempelajari lebih lengkap mengenai *Reponsive Web Design*, kamu dapat membuka referensi [ini](https://web.dev/responsive-web-design-basics/).

## Tutorial: Menambahkan Bootstrap ke Aplikasi

Pada mata kuliah PBP ini, kita akan memfokuskan pembelajaran CSS menggunakan framework bootstrap. Berikut adalah langkah yang perlu kamu lakukan untuk menyelesaikan bagian tutorial ini. 

1. Buka project django kalian **(shopping_list)**, lalu buka file `base.html` yang telah dibuat sebelumnya pada templates folder yang berada di root project kalian. 

2. Didalam `templates/base.html`, tambahkan tag `<meta name="viewport">` agar halaman web kamu dapat menyesuaikan ukuran dan perilaku perangkat mobile **(apabila belum)**.

    ```html
    <head>
        {% block meta %}
            <meta charset="UTF-8" />
            <meta name="viewport" content="width=device-width, initial-scale=1">
        {% endblock meta %}
    </head>
    ```

3. Tambahkan Bootstrap CSS dan juga JS. 

    **CSS:**
    ```html
    <head>
        {% block meta %}
            ...
        {% endblock meta %}
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    </head>
    ```

    **JS:**
    ```html
    <head>
        ...
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha384-KyZXEAg3QhqLMpG8r+J4jsl5c9zdLKaUk5Ae5f5b1bw6AUn5f5v8FZJoMxm6f5cH1" crossorigin="anonymous"></script>
    </head>
    ```

4. **(Opsional)** Apabila kalian ingin menggunakan dropdowns, popover, tooltips yang disediakan framework Bootstrap, maka kalian perlu menambahkan 2 baris *script* JS ini dibawah *script* JS yang sudah kalian buat sebelumnya.

    ```html
    <head>
        ...
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>
    </head>
    ```

Referensi: [Get Started with Bootstrap 5.3](https://getbootstrap.com/docs/5.3/getting-started/introduction/)

## Tutorial: Menambahkan *navbar* pada Aplikasi

Tambahkan *navigation bar* (boleh menggunakan Bootstrap) pada halaman `main.html` kamu. Tampilkan nama kamu dan tombol *logout* pada *navigation bar* yang kamu buat. Kamu dapat referensi cara membuat *navigation bar* pada Bootstrap dengan [dokumentasi berikut](https://getbootstrap.com/docs/5.3/components/navbar/) dan kreasikan *navigation bar* sesukamu.

## Tutorial: Menambahkan Fitur *Edit* pada Aplikasi

1. Buka `views.py` yang ada pada subdirektori `main`, dan buatlah fungsi baru bernama `edit_product` yang menerima parameter `request` dan `id`.

2. Tambahkan potongan kode berikut pada fungsi `edit_product`

    ```python
    def edit_product(request, id):
        # Get product berdasarkan ID
        product = Product.objects.get(pk = id)

        # Set product sebagai instance dari form
        form = ProductForm(request.POST or None, instance=product)

        if form.is_valid() and request.method == "POST":
            # Simpan form dan kembali ke halaman awal
            form.save()
            return HttpResponseRedirect(reverse('main:show_main'))

        context = {'form': form}
        return render(request, "edit_product.html", context)
    ```

3. Buatlah berkas HTML baru dengan nama `edit_product.html` pada subdirektori `main/templates`. Isi berkas tersebut dengan template berikut.

    ```html 
    {% extends 'base.html' %}

    {% load static %}

    {% block content %}

    <h1>Edit Product</h1>

    <form method="POST">
        {% csrf_token %}
        <table>
            {{ form.as_table }}
            <tr>
                <td></td>
                <td>
                    <input type="submit" value="Edit Product"/>
                </td>
            </tr>
        </table>
    </form>

    {% endblock %}
    ```

4. Buka `urls.py` yang berada pada direktori `main` dan *import* fungsi `edit_product` yang sudah dibuat.

    ```python
    from main.views import edit_product
    ```

5. Tambahkan *path* url ke dalam `urlpatterns` untuk mengakses fungsi yang sudah diimpor tadi.

    ```python 
    ...
    path('edit-product/<int:id>', edit_product, name='edit_product'),
    ...
    ```

6. Buka `main.html` yang berada pada subdirektori `main/templates`. Tambahkan potongan kode berikut sejajar dengan elemen `<td>` terakhir agar terlihat tombol *edit* pada setiap baris tabel.

    ```html 
    ...
    <tr>
        ...
        <td>
            <a href="{% url 'main:edit_product' product.pk %}">
                <button>
                    Edit
                </button>
            </a>
        </td>
    </tr>
    ...
    ```

7. Jalankan proyek Django-mu dengan perintah `python manage.py runserver` dan bukalah http://localhost:8000 di browser favoritmu. Setelah login, coba untuk meng-*edit* data suatu produk. Apabila setelah di-*submit* perubahan tersimpan dan tercermin pada halaman utama aplikasi tanpa *error*, maka selamat, kamu berhasil menambahkan fitur *edit*!

## Tutorial: Membuat Fungsi untuk Menghapus Data Produk

Berikut adalah yang perlu kamu lakukan untuk membuat fungsi penghapusan data produk.

1. Buat fungsi baru dengan nama `delete_product` yang menerima parameter `request` dan `id` pada `views.py` di folder `main` untuk menghapus data produk. Kamu dapat menggunakan templat kode berikut untuk memuat fungsinya.

    > Jangan lupa untuk memahami isi kodenya, ya. 😉

    ```python
    def delete_product(request, id):
        # Get data berdasarkan ID
        product = Product.objects.get(pk = id)
        # Hapus data
        product.delete()
        # Kembali ke halaman awal
        return HttpResponseRedirect(reverse('main:show_main'))
    ```

2. Buka `urls.py` yang ada pada *folder* `main` dan impor fungsi yang sudah kamu buat tadi.

    ```python
    from main.views import delete_product
    ```

3. Tambahkan _path url_ ke dalam `urlpatterns` untuk mengakses fungsi yang sudah diimpor.

    ```python
    ...
    path('delete/<int:id>', delete_product, name='delete_product'), # sesuaikan dengan nama fungsi yang dibuat
    ...
    ```

4. Bukalah berkas `main.html` yang ada pada folder `main/templates` dan ubahlah kode yang sudah ada menjadi seperti berikut agar terdapat tombol hapus untuk setiap produk.

    ```html 
    ...
    <tr>
        ...
        <td>
            <a href="{% url 'main:edit_product' product.pk %}">
                <button>
                    Edit
                </button>
            </a>
            <a href="{% url 'main:delete_product' product.pk %}">
                  <button>
                      Delete
                  </button>
              </a>
        </td>
    </tr>
    ...
    ```

Jalankan proyek Django-mu dan cobalah untuk menghapus data produk yang sudah ada pada browser favoritmu.

## Referensi Tambahan

Kamu dapat membuka beberapa tautan dibawah untuk melihat beberapa opsi kode yang dapat kamu gunakan untuk menambahkan navigation bar
- Dengan menggunakan Bootstrap, dapat diakses melalui [link ini](https://getbootstrap.com/docs/5.2/components/navbar/)
- Dengan menggunakan CSS secara manual, dapat diakses melalui [link ini](https://www.w3schools.com/css/css_navbar.asp)

## Kontributor

- Alanna
- Alvaro Austin
- Naila Shafirni Hidayat
- Shayna Putri Fitria

## Credits

Tutorial ini dikembangkan berdasarkan [PBP Ganjil 2023](https://github.com/pbp-fasilkom-ui/ganjil-2023) dan [PBP Genap 2023](https://github.com/pbp-fasilkom-ui/genap-2023) yang ditulis oleh Tim Pengajar Pemrograman Berbasis Platform 2023. Segala tutorial serta instruksi yang dicantumkan pada repositori ini dirancang sedemikian rupa sehingga mahasiswa yang sedang mengambil mata kuliah Pemrograman Berbasis Platform dapat menyelesaikan tutorial saat sesi lab berlangsung.
