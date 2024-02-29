---
sidebar_label: Tutorial 5
sidebar_position: 7
Path: docs/tutorial-5
---

# Tutorial 5: JavaScript dan AJAX

Pemrograman Berbasis Platform (CSGE602022) — diselenggarakan oleh Fakultas Ilmu Komputer Universitas Indonesia, Semester Ganjil 2023/2024

---

## Tujuan Pembelajaran

Setelah menyelesaikan tutorial ini, mahasiswa diharapkan untuk dapat:

- Memahami fungsi JavaScript pada *front-end development*
- Menggunakan JavaScript secara dasar
- Menerapkan AJAX dan Fetch API

## JavaScript

### Pengenalan JavaScript

JavaScript merupakan bahasa pemrograman multi-paradigma tingkat tinggi lintas platform (*cross-platform high-level multi-paradigm programming language*). Sifat multi-paradigma membuat JavaScript mendukung konsep pemrograman berbasis obyek, pemrograman imperatif, dan pemrograman fungsional. JavaScript sendiri merupakan implementasi dari ECMAScript, yang merupakan inti dari bahasa JavaScript. Beberapa implementasi lain dari ECMAScript yang mirip dengan JavaScript antara lain JScript (Microsoft) dan ActionScript (Adobe).

JavaScript, bersama dengan HTML dan CSS, menjadi tiga teknologi utama yang dipakai pada pengembangan web. Pada dasarnya, keuntungan menggunakan JavaScript dalam pengembangan web adalah manipulasi halaman web dapat dilakukan secara dinamis dan interaksi antara halaman web dengan pengguna dapat meningkat. Oleh karena itu, hampir semua situs web modern saat ini menggunakan JavaScript dalam halaman web mereka untuk memberikan pengalaman terbaik kepada pengguna. Beberapa contoh yang dapat kita lakukan dengan menggunakan JavaScript antara lain menampilkan informasi berdasarkan waktu, mengenali jenis peramban pengguna, melakukan validasi form atau data, membuat *cookies* (bukan kue, namun [HTTP *cookies*](https://en.wikipedia.org/wiki/HTTP_cookie)), mengganti *styling* dan CSS suatu *element* secara dinamis, dan lain sebagainya.

Pada pengembangan web umumnya kode JavaScript digunakan pada *client-side* suatu web (*client-side JavaScript*), namun beberapa jenis kode JavaScript saat ini digunakan pada *server-side* suatu web (*server-side JavaScript*) seperti **node.js**. Istilah *client-side* menunjukkan bahwa kode JavaScript akan dieksekusi atau dijalankan pada peramban pengguna, bukan pada server situs web. Hal ini berarti kompleksitas kode JavaScript tidak akan memengaruhi performa server situs web tersebut namun memengaruhi performa peramban web dan komputer; semakin kompleks kode JavaScript, maka semakin banyak memori komputer yang dikonsumsi oleh peramban web.

Pada mata kuliah PBP, kita hanya akan fokus kepada kode *client-side JavaScript*.

### Tahapan Eksekusi JavaScript oleh Peramban

Perhatikan diagram berikut untuk mengamati tahapan eksekusi JavaScript oleh peramban web.

![javascript-works](https://preview.ibb.co/e258TG/Screenshot_from_2017_10_31_14_29_13.png)

Setelah peramban mengunduh halaman HTML web maka tepat dimana tag `<script></script>` berada, peramban akan melihat *tag script* tersebut, apakah tag tersebut berisi kode *embedded* JavaScript atau merujuk berkas eksternal JavaScript. Jika merujuk pada berkas eksternal JavaScript, maka peramban akan mengunduh berkas tersebut terlebih dahulu.

### Penulisan JavaScript

Penulisan JavaScript dapat dilakukan dengan ***embedded JavaScript*** atau ***external JavaScript***. Kode JavaScript dapat didefinisikan atau dituliskan secara *embedded* pada berkas HTML maupun secara terpisah pada berkas tersendiri. Jika ditulis dalam berkas terpisah dari HTML, ekstensi berkas yang digunakan untuk berkas JavaScript adalah `.js`. Berikut contoh beberapa pendefinisian dari JavaScript.

JavaScript dapat diletakkan pada *head* atau *body* dari halaman HTML. Selain itu, kode JavaScript **harus** dimasukkan di antara tag `<script>` dan `</script>`. Kamu dapat meletakkan lebih dari satu *tag script* yang berisi JavaScript pada suatu berkas HTML.

#### Embedded JavaScript pada HTML

`index.html`

```html
<script type="text/JavaScript">
  alert("Hello World!");
</script>
```

#### External JavaScript pada HTML

`index.html`

```html
<script type="text/JavaScript" src="js/script.js"></script>
```

`js/script.js`

```javascript
alert("Hello World!");
```

Pada berkas eksternal JavaScript, tag `<script>` tidak perlu lagi ditambahkan.

Memisahkan JavaScript pada berkas tersendiri dapat memberikan beberapa keuntungan seperti kode dapat digunakan di berkas HTML lain, kode JavaScript dan HTML tidak bercampur sehingga lebih fokus saat mengembangkan aplikasi, serta mempercepat proses pemuatan halaman. berkas `.js` biasanya akan di-*cache* oleh peramban sehingga jika kita membuka halaman yang sama dan tidak ada perubahan pada berkas `.js`, maka peramban tidak akan meminta berkas `.js` tersebut kepada server lagi, namun akan menggunakan berkas dari *cache* yang sudah disimpan sebelumnya.

### Eksekusi JavaScript

Setelah JavaScript sudah dimuat dengan sempurna, maka peramban akan langsung mulai mengeksekusi kode JavaScript. Jika kode tersebut BUKAN merupakan *event-triggered*, maka kode langsung dieksekusi. Jika kode tersebut merupakan *event-triggered*, maka kode tersebut hanya akan dieksekusi jika *event* yang didefinisikan terpicu (*triggered*).

```javascript
// langsung dieksekusi
alert("Hello World");

// langsung dieksekusi
var obj = document.getElementById("object");
// langsung dieksekusi, menambahkan event handler onclick untuk element object
obj.onclick = function () {
  // hanya dieksekusi jika element 'object' di klik
  alert("You just clicked the object!");
};
```

### Sintaks JavaScript

#### Variabel

Mendefinisikan variabel pada JavaScript cukup mudah. Contohnya seperti berikut.

```javascript
var example = 0; // var example merupakan sebuah bilangan
var example = "example"; // var example merupakan sebuah string
var example = true; // var example merupakan sebuah boolean
```

JavaScript dapat menampung banyak tipe data; mulai dari string, integer, hingga *object* sekalipun. Berbeda dengan Java yang penandaan tipe datanya dibedakan dengan *head variable* (contohnya kamu ingin membuat variabel dengan tipe data `int`, maka sintaknya seperti `int x = 9`), JavaScript mempunyai ciri khas *loosely typed* atau *dynamic language*, yakni kamu tidak perlu menuliskan tipe data pada *head variable* dan JavaScript nantinya akan secara otomatis membaca tipe data kamu berdasarkan standar yang ada (seperti pada contoh di atas).

Ada beberapa aturan dalam pemilihan *indentifiers* atau nama variabel dalam JavaScript. Karakter pertama **harus** merupakan alfabet, *underscore* (`_`), atau karakter dolar (`$`). Selain itu, JavaScript *identifiers* bersifat *case sensitive*.

#### Penggabungan String

Dalam JavaScript, kita juga dapat menyambungkan `string` dengan `string` lainnya seperti pada Java.

```javascript
var str1 = "PBP" + " " + "Fun";
var str2 = "PBP";
var str3 = "Fun";
var str4 = str2 + " " + str3;
var str5 = "Fun";
var str6 = `PBP ${str5}`;  // Memiliki hasil yang sama seperti "PBP" + " " + str5
```

### Ruang Lingkup JavaScript

#### Variabel Lokal

Variabel yang didefinisikan **di dalam** fungsi bersifat lokal, sehingga hanya dapat diakses oleh kode didalam fungsi tersebut.

```javascript
// kode diluar fungsi thisFunction() tidak dapat mengakses variabel courseName
function thisFunction() {
  var courseName = "PBP";
  // kode di dalam fungsi ini dapat mengakses variabel courseName
}
```

#### Variabel Global

Variabel yang didefinisikan **di luar** fungsi bersifat global dan dapat diakses oleh kode lain dalam berkas JavaScript tersebut.

```javascript
var courseName = "PBP";
function thisFunction() {
  // kode di dalam fungsi ini dapat mengakses variabel courseName
}
```

#### Variabel Auto Global

Value yang di-*assign* pada variabel yang belum dideklarasikan otomatis menjadi variabel global, walaupun variabel tersebut berada di dalam suatu fungsi.

```javascript
thisFunction(); // fungsi thisFunction() perlu dipanggil terlebih dahulu
console.log(courseName); // cetak "PBP" pada JavaScript console
function thisFunction() {
  courseName = "PBP";
}
```

#### Mengakses Variabel Global dari HTML

Kamu dapat mengakses variabel yang berada dalam berkas JavaScript pada berkas HTML yang memuat berkas JavaScript tersebut.

```html
...
<input type="text" onclick="this.value=courseName" />
...
```

```javascript
...
var courseName = "PBP";
...
```

### Function dan Event

*Function* adalah sekumpulan grup dari kode-kode yang bisa dipanggil di mana pun pada bagian kode program (mirip dengan `method` pada Java). Hal ini mengurangi redundansi kode yang ada (mengurangi kode-kode yang dapat sama berulang-ulang). Selain itu, *function* pada JavaScript sangat berguna untuk memudahkan elemen pemanggilan secara dinamis. *Function* dapat dipanggil sesama *function* dan dapat juga dipanggil karena *event* (akan dijelaskan di bawah). Sebagai contoh, berikut kode yang terdapat pada `index.html`.

```html
...
<input type="button" value="magicButton" id="magicButton" onclick="hooray();" />
...
```

Kemudian berikut adalah kode pada `javascript.js`.

```javascript
...
function hooray(){
    alert("Yahoo!");
}
...
```

Apabila `magicButton` ditekan, maka fungsi `onclick` akan menjalankan *function* `hooray()` pada `javascript.js`, lalu muncul *alert* sesuai yang sudah di-*assign* sebelumnya.

Kode `onclick` sebenarnya adalah salah satu contoh kemampuan JavaScript yang disebut *event*. *Event* adalah kemampuan JavaScript untuk membuat sebuah situs web dinamis. Maksud dari `onclick` adalah penanda apa yang akan dilakukan JavaScript jika elemen tersebut ditekan. Selain itu, *event* biasanya diberikan sebuah fungsi yang berguna sebagai perintah-perintah untuk JavaScript. Selain itu, banyak contoh-contoh *event* lainnya seperti `onchange`, `onmouseover`, `onmouseout`, dan lain sebagainya yang bisa kamu baca pada tautan [ini](https://www.w3schools.com/js/js_events.asp).

### JavaScript DOM

#### HTML DOM

HTML DOM (*Document Object Model*) adalah standar bagaimana mengubah, mengambil, dan menghapus HTML *elements*. HTML DOM dapat diakses melalui JavaScript atau dengan bahasa pemrograman lainnya. Detail lengkapnya dapat dilihat [di sini](https://www.w3schools.com/js/js_htmldom.asp).

Berikut adalah contoh implementasinya.

```html
...     
<div>
  <p onclick="myFunction()" id="demo">Example of HTML DOM</p>
      
</div>
...
```

```javascript
...
    function myFunction() {
document.getElementById("demo").innerHTML = "YOU CLICKED ME!";
    }
...
```

#### CSS DOM

Sama dengan HTML DOM, CSS DOM dapat mengubah CSS secara dinamis melalui JavaScript. Detail lengkapnya dapat dilihat [di sini](https://www.w3schools.com/js/js_htmldom_css.asp).

Berikut adalah contoh implementasinya.

`index.html`

```html
...
<p id="blueText" onclick="changeColor()">Click me v2</p>
...
```

`javascript.js`

```javascript
...
function changeColor(){
    document.getElementById("blueText").style.color="blue";
}
...
```

## AJAX

### Pengenalan AJAX

AJAX merupakan singkatan dari ***A**synchronous **J**avaScript **A**nd **X**ML*.

AJAX bukanlah merupakan sebuah bahasa pemrograman, melainkan sebuah teknologi yang memadukan peramban web (untuk meminta data dari *web server*) dengan JavaScript dan HTML DOM (untuk menampilkan data). AJAX dapat menggunakan XML untuk mengirim data, tetapi AJAX juga dapat menggunakan teks ataupun JSON untuk mengirim data. AJAX memungkinkan halaman web untuk memperbarui data secara asinkronus dengan mengirimkan data ke peladen di balik layar. Hal tersebut berarti bahwa kita dapat memperbarui sebagian elemen data pada halaman tanpa harus me-*reload* halaman secara keseluruhan.

Berikut ini adalah diagram cara kerja AJAX.

![ajax-works](https://www.w3schools.com/js/pic_ajax.gif)

1. Sebuah *event* terjadi pada halaman web (contohnya tombol *submit data* ditekan)
2. Sebuah `XMLHttpRequest` *object* dibuat oleh JavaScript
3. `XMLHttpRequest` *object* mengirimkan *request* ke server
4. Server memproses *request* tersebut
5. Server mengembalikan *response* kembali kepada halaman web
6. *Response* dibaca oleh JavaScript
7. Aksi berikutnya akan dipicu oleh JavaScript sesuai dengan langkah yang dibuat (contohnya memperbarui data di halaman tersebut)

Pada PBP kali ini, kamu akan melakukan AJAX pada peramban web dengan menggunakan fungsi `fetch()` yang diberikan oleh JavaScript. Secara gambaran besar, penggunaan `fetch()` untuk melakukan pemanggilan AJAX dapat dilihat di tautan [ini](https://www.w3schools.com/jsref/api_fetch.asp).

### Fetch API

Fetch API merupakan API baru yang diperkenalkan pada ECMAScript 2020 sebagai standar baru untuk membuat *request* dengan `Promise`. Fetch API menyediakan antarmuka untuk mengambil sumber daya (termasuk di seluruh jaringan). API ini merupakan pengganti yang lebih kuat dan fleksibel untuk [`XMLHttpRequest`](https://developer.mozilla.org/en-US/docs/Web/API/XMLHttpRequest). Fetch API secara umum digunakan untuk mengimplementasikan AJAX secara lebih mudah daripada AJAX dengan `XMLHttpRequest`. Fetch API juga mendukung lebih banyak metode HTTP dan header HTTP daripada AJAX biasa.

Fungsi `fetch()` memiliki beberapa parameter, yaitu:

- `url`: URL dari sumber daya yang akan diminta
- `method`: Metode HTTP yang akan digunakan
- `headers`: Header HTTP yang akan dikirim
- `body`: Isi dari permintaan HTTP

Fungsi `fetch()` mengembalikan objek `Response`. Objek `Response` memiliki beberapa properti, yaitu:

- `status`: Kode `status` HTTP dari respons
- `headers`: Header HTTP dari respons
- `body`: Isi dari respons

Kamu dapat mempelajari Fetch API lebih lanjut pada tautan [ini](https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API).

### Fungsi Async dan Await

Sebelum mempelajari penggunaan fungsi `fetch()`, ada baiknya kita mempelajari fungsi `async` dan `await` yang memungkinkan pengimplementasian AJAX tanpa perlu menggunakan *library* eksternal, seperti [jQuery](https://jquery.com/).

Fungsi `async` dan `await` merupakan fungsi baru yang diperkenalkan di ECMAScript 2017. Fungsi `async` digunakan untuk menandai fungsi sebagai fungsi yang dapat mengembalikan nilai secara asinkronus, sedangkan fungsi `await` digunakan untuk menunggu hasil dari fungsi `async`.

Kamu dapat mempelajari fungsi `async` dan `await` lebih lanjut pada tautan [ini](https://www.w3schools.com/js/js_async.asp).

### Penggunaan Fetch API

Fetch API menyediakan antarmuka JavaScript untuk mengakses dan memanipulasi bagian-bagian protokol, seperti *requests* dan *responses*. API ini juga menyediakan metode `fetch()` global yang menyediakan cara yang mudah dan logis untuk mengambil sumber daya secara asinkronus pada seluruh jaringan.

Tidak seperti `XMLHttpRequest` yang merupakan API berbasis *callback*, Fetch API berbasis `Promise` dan menyediakan alternatif yang lebih baik dan dapat dengan mudah digunakan pada *service worker*. Fetch API juga mengintegrasikan konsep HTTP tingkat lanjut seperti CORS dan ekstensi lain ke HTTP.

Berikut adalah contoh penggunaan Fetch API dengan fungsi `async` dan `await` untuk melakukan AJAX.

```javascript
async function fetchData() {
  const response = await fetch("https://jokes-bapack2-api.yuana.id/v1/text/random");
  const data = await response.json();
  return data;
}

const joke = await fetchData();
console.log(joke);
```

Kode di atas akan melakukan AJAX untuk meminta data dari API lelucon bapak-bapak masa kini secara asinkronus. Hasil dari AJAX akan disimpan dalam variabel `joke`.

Kamu dapat mempelajari penggunaan Fetch API lebih lanjut pada tautan [ini](https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API/Using_Fetch).

## Tutorial: Membuat Fungsi untuk Mengembalikan Data JSON

Pada bagian ini, kamu akan membuat fungsi pada *views* untuk mengembalikan data JSON. Fungsi ini akan digunakan untuk menampilkan data produk pada HTML dengan menggunakan `fetch`.

1. Buatlah fungsi baru pada `views.py` dengan nama `get_product_json` yang menerima parameter `request`.

2. Isilah fungsi tersebut dengan kode berikut.

    ```python
    def get_product_json(request):
        product_item = Product.objects.all()
        return HttpResponse(serializers.serialize('json', product_item))
    ```

## Tutorial: Membuat Fungsi untuk Menambahkan Produk dengan AJAX

Pada bagian ini, kamu akan membuat fungsi pada *views* untuk menambahkan produk baru ke basis data dengan AJAX.

1. Buatlah fungsi baru pada `views.py` dengan nama `add_product_ajax` yang menerima parameter `request`.

2. Impor `from django.views.decorators.csrf import csrf_exempt` pada berkas `views.py`.

3. Tambahkan dekorator `@csrf_exempt` di atas fungsi `add_product_ajax` yang sudah kamu buat.

4. Isilah fungsi tersebut dengan kode berikut.

    ```python
    ...
    @csrf_exempt
    def add_product_ajax(request):
    if request.method == 'POST':
        name = request.POST.get("name")
        price = request.POST.get("price")
        description = request.POST.get("description")
        user = request.user

        new_product = Product(name=name, price=price, description=description, user=user)
        new_product.save()

        return HttpResponse(b"CREATED", status=201)

    return HttpResponseNotFound()
    ```

    **Penjelasan Kode:**
    - `name = request.POST.get("name")` berfungsi untuk mengambil *value* `name` pada `request`.
    - `new_product` membuat objek Product baru dengan parameter sesuai *values* dari *request*.

## Tutorial: Menambahkan *Routing* Untuk Fungsi `get_product_json` dan `add_product_ajax`

1. Buka berkas `urls.py` pada folder `main` dan impor fungsi `get_product_json` serta `add_product_ajax`.

2. Tambahkan *path url* kedua fungsi tersebut ke dalam urlpatterns.

    ```python
    ...
    path('get-product/', get_product_json, name='get_product_json'),
    path('create-product-ajax/', add_product_ajax, name='add_product_ajax')
    ```

## Tutorial: Menampilkan Data *Product* dengan `Fetch()` API

1. Bukalah berkas `main.html` pada `main/templates` dan hapuslah bagian kode *table* yang sudah kamu buat pada tutorial sebelumnya.

2. Tambahkanlah kode berikut sebagai struktur *table*.

    ```html
    <table id="product_table"></table>
    ```

3. Buatlah *block* `<Script>` di bagian bawah berkas kamu dan buatlah fungsi baru pada *block* `<Script>` tersebut dengan nama `getProducts().`

    ```JavaScript
    <script>
        async function getProducts() {
            return fetch("{% url 'main:get_product_json' %}").then((res) => res.json())
        }
    </script>
    ```

    **Penjelasan Kode:**
    - Fungsi ini menggunakan `fetch()` API ke data JSON secara *asynchronous*.
    - Setelah data di-*fetch*, fungsi `then()` digunakan untuk melakukan *parse* pada data JSON menjadi objek JavaScript.

4. Buatlah fungsi baru pada *block* `<Script>` dengan nama `refreshProducts()` yang digunakan untuk me-*refresh* data produk secara *asynchronous*.

    ```JavaScript
    <script>
        ...
        async function refreshProducts() {
            document.getElementById("product_table").innerHTML = ""
            const products = await getProducts()
            let htmlString = `<tr>
                <th>Name</th>
                <th>Price</th>
                <th>Description</th>
                <th>Date Added</th>
            </tr>`
            products.forEach((item) => {
                htmlString += `\n<tr>
                <td>${item.fields.name}</td>
                <td>${item.fields.price}</td>
                <td>${item.fields.description}</td>
                <td>${item.fields.date_added}</td>
            </tr>` 
            })
            
            document.getElementById("product_table").innerHTML = htmlString
        }

        refreshProducts()
    </script>
    ```

    **Penjelasan Kode:**
    - `document.getElementById("product_table")` digunakan untuk mendapatkan elemen berdasarkan ID nya. Pada baris kode ini, elemen yang dituju adalah tag `<table>` dengan ID `product_table` yang sudah kamu buat pada tahapan sebelumnya.
    - `innerHTML` digunakan untuk mengisi *child element* dari elemen yang dituju. Jika `innerHTML = ""`, maka akan mengosongkan isi *child element* dari elemen yang dituju.
    - `products.forEach((item))` digunakan untuk melakukan *for each loop* pada data *products* yang diambil menggunakan fungsi `getProducts()`. Kemudian, `htmlString` kita konkatenasi dengan data produk untuk mengisi tabel.
    - `refreshProducts()` digunakan untuk memanggil fungsi tersebut pada setiap kali membuka halaman web.

## Tutorial: Membuat Modal Sebagai *Form* untuk Menambahkan Produk

1. Tambahkan kode berikut untuk mengimplementasikan modal ([Bootstrap](https://getbootstrap.com/docs/5.3/components/modal/)) pada aplikasi kamu.

    ```html
    ...
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Add New Product</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    <form id="form" onsubmit="return false;">
                        {% csrf_token %}
                        <div class="mb-3">
                            <label for="name" class="col-form-label">Name:</label>
                            <input type="text" class="form-control" id="name" name="name"></input>
                        </div>
                        <div class="mb-3">
                            <label for="price" class="col-form-label">Price:</label>
                            <input type="number" class="form-control" id="price" name="price"></input>
                        </div>
                        <div class="mb-3">
                            <label for="description" class="col-form-label">Description:</label>
                            <textarea class="form-control" id="description" name="description"></textarea>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-primary" id="button_add" data-bs-dismiss="modal">Add Product</button>
                </div>
            </div>
        </div>
    </div>
    ...
    ```

    > Form pada modal tersebut sudah disesuaikan untuk model pada aplikasi `shopping_list`.

    > Kamu dapat membaca dokumentasi lebih lanjut mengenai modal pada Bootstrap di [sini](https://getbootstrap.com/docs/5.3/components/modal/).

2. Menambahkan `button` yang berfungsi untuk menampilkan modal.

    ```html
    ...
    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">Add Product by AJAX</button>
    ...
    ```

## Tutorial: Menambahkan Data *Product* dengan AJAX

Modal dengan form yang telah kamu buat sebelumnya belum bisa digunakan untuk menambahkan data produk. Oleh karena itu, kamu perlu membuat fungsi JavaScript baru untuk menambahkan data berdasarkan input ke basis data secara AJAX.

1. Buatlah fungsi baru pada *block* `<Script>` dengan nama `addProduct()`. Isilah fungsi tersebut dengan kode berikut.

    ```JavaScript
    <script>
        ...
        function addProduct() {
            fetch("{% url 'main:add_product_ajax' %}", {
                method: "POST",
                body: new FormData(document.querySelector('#form'))
            }).then(refreshProducts)

            document.getElementById("form").reset()
            return false
        }
    </script>
    ```

    **Penjelasan Kode:**
    - `new FormData(document.querySelector('#form'))` digunakan untuk membuat sebuah FormData baru yang datanya diambil dari form pada modal. Objek FormData dapat digunakan untuk mengirimkan data form tersebut ke server.
    - `document.getElementById("form").reset()` digunakan untuk mengosongkan isi *field* form modal setelah di-*submit*.

2. Tambahkan fungsi `onclick` pada *button* "Add Product" pada modal untuk menjalankan fungsi `addProduct()` dengan menambahkan kode berikut.

    ```JavaScript
    <script>
    ...
    document.getElementById("button_add").onclick = addProduct
    </script>
    ```

Selamat! Kamu telah berhasil membuat aplikasi yang dapat menambahkan data dengan menggunakan AJAX. Bukalah <http://localhost:8000/> dan cobalah untuk menambahkan data produk baru pada aplikasi. Seharusnya, sekarang aplikasi tidak perlu melakukan *reload* setiap kali data produk baru ditambahkan.

## Penutup

Lakukan `add`, `commit` dan `push` untuk memperbarui repositori GitHub. Jalankan perintah berikut untuk melakukan `add`, `commit` dan `push`.

```shell
git add .
git commit -m "<pesan_commit>"
git push -u origin <branch_utama>
```

- Ubah `<pesan_commit>` sesuai dengan keinginan. Contoh: `git commit -m "tutorial 1 selesai"`.
- Ubah `<branch_utama>` sesuai dengan nama branch utamamu. Contoh: `git push -u origin main` atau `git push -u origin master`.

## Referensi Tambahan

- [Async/Await Function in JavaScript](https://www.geeksforgeeks.org/async-await-function-in-javascript/)
- [How to Use Fetch to Make AJAX Calls in JavaScript](https://www.freecodecamp.org/news/how-to-use-fetch-api/)
- [Modal Bootstrap 5.3](https://getbootstrap.com/docs/5.3/components/modal/)

## Kontributor

- Muhammad Athallah
- Muhammad Iqbal Dwitama

## Credits

Tutorial ini dikembangkan berdasarkan [PBP Ganjil 2023](https://github.com/pbp-fasilkom-ui/ganjil-2023) dan [PBP Genap 2023](https://github.com/pbp-fasilkom-ui/genap-2023) yang ditulis oleh Tim Pengajar Pemrograman Berbasis Platform 2023. Segala tutorial serta instruksi yang dicantumkan pada repositori ini dirancang sedemikian rupa sehingga mahasiswa yang sedang mengambil mata kuliah Pemrograman Berbasis Platform dapat menyelesaikan tutorial saat sesi lab berlangsung.
