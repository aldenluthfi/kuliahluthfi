---
sidebar_label: Proyek Tengah Semester
sidebar_position: 1
Path: assignment/group/midterm
---

# Proyek Tengah Semester

**Membuat Situs Web menggunakan Framework Django (Berkelompok)**

---

## Tujuan Pembelajaran Khusus

1. Merancang halaman web
2. Mengimplementasikan situs web dengan *framework* Django dengan memenuhi *models*, *views*, dan *template*
3. Memanfaatkan *framework* CSS untuk mewujudkan *responsive web design*
4. Mengimplementasikan *unit test* dan *deployment* (bonus)

### Catatan

Perlu diperhatikan selain tujuan pembelajaran khusus seperti tertulis di atas, peserta kuliah juga perlu mempelajari dan dilatih beberapa aspek kecendekiaan sebagai calon sarjana. Di antaranya yang relevan dalam kuliah ini adalah keteguhan (*grit*), kemandirian, ketelitian, termasuk juga metakognitif (secara sederhana bisa diartikan kemampuan mengatur strategi belajar yang sesuai dengan dirinya meliputi perencanaan, *monitoring* dan evaluasi proses belajar mandiri), termasuk di dalamnya kemampuan untuk memahami, mengkomunikasikan masalah, diskusi dan bertanya, sehingga peserta kuliah juga perlu siap bersikap positif dengan kondisi-kondisi yang secara tidak langsung atau tidak pasti akan dihadapi dan mungkin dapat menghabiskan banyak waktu. Kondisi tersebut bisa dianggap kendala, seperti keterbatasan sumber daya, *bug tools*, kesulitan teknis atau lainnya. Walaupun dirasakan menyulitkan, perlu diupayakan untuk disikapi dengan positif agar dapat menjadi manfaat terkait aspek kecendekiaan yang perlu dilatih peserta kuliah. Sikap negatif hanya akan memperburuk keadaan dan menghilangkan manfaat tugas ini untuk pembelajaran yang akan dapat dirasakan di kemudian hari. Tim asisten dan dosen melalui sarana yang ada, akan berusaha semampunya melayani pertanyaan, keluhan, dan membantu proses pembelajaran peserta agar peserta bisa menjalani perkuliahan dan belajar semaksimal mungkin.

Sebagai selingan, bila rekan-rekan lelah dan bingung menghadapi *error* yang belum kunjung terselesaikan, berikut ini ada video yang cukup populer dan mudah-mudahan bisa menambah semangat untuk tetap teguh mengerjakan dan berlatih demi kesuksesan di kemudian hari.

<iframe width="560" height="315" src="https://www.youtube-nocookie.com/embed/42-hh-iMJJI" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe><br /><br />

Selamat mengerjakan. 😄

## Aturan Umum Tugas Kelompok

1. Satu kelompok terdiri atas 4-6 orang. Pembagian kelompok dapat dilihat di SCELE.
2. Satu kelompok membuat satu repositori Git di dalam satu [organisasi](https://docs.github.com/en/organizations/collaborating-with-groups-in-organizations/about-organizations) yang digunakan oleh seluruh anggota kelompok untuk bekerja sama. Kumpulkan tautan repositori ke SCELE.
3. Setiap kelompok dipersilakan untuk mencari ide sendiri mengenai aplikasi yang akan dibuat. Tema aplikasi adalah **literasi dan buku**. Tema ini dipilih karena tiga alasan: 
    - Untuk meningkatkan kesadaran kita tentang pentingnya literasi.
    - Tema ini sesuai dengan tema Kongres Bahasa Indonesia XII yang akan diselenggarakan pada tanggal 25—28 Oktober 2023, yaitu "Literasi dalam Kebinekaan untuk Kemajuan Bangsa." Kongres Bahasa Indonesia adalah forum tertinggi yang membahas masalah kebahasaan dan kesastraan di Indonesia. Kongres Bahasa Indonesia diselenggarakan setiap lima tahun sekali oleh Badan Pengembangan dan Pembinaan Bahasa, Kementerian Pendidikan, Kebudayaan, Riset, dan Teknologi. <https://kbi.kemdikbud.go.id/>.
    - Tema ini sesuai dengan peringatan Bulan Bahasa dan Sastra yang dilakukan setiap bulan Oktober di Indonesia untuk mengingat sejarah kelahiran bahasa Indonesia.
4. Setiap kelompok mengimplementasikan katalog buku dalam bentuk *class models* dan menyimpan data dari dataset ke dalam *basis data* Django. Dataset katalog buku harus berisi **minimal 100 buku**. Sumber dataset katalog buku boleh berasal dari mana saja, misalnya dari Project Gutenberg, Google Books API, Kaggle, dan Data Skripsi LONTAR.
    Berikut adalah contoh URL dataset yang dapat digunakan:
    - Project Gutenberg: <https://www.gutenberg.org/ebooks/offline_catalogs.html>, <https://www.gutenberg.org/cache/epub/feeds/pg_catalog.csv>, dan <https://drive.google.com/file/d/17jiAwHx_68zUrolbTl75IoLRFK_JLYrx/view>
    - [Google Books API](https://developers.google.com/books/)
    - [Kaggle](https://www.kaggle.com/datasets?search=book)
    - [Data Skripsi Lontar](https://univindonesia-my.sharepoint.com/:x:/g/personal/iisafriyanti_office_ui_ac_id/EY1Lmwm40rJLhtbWtKerNOYBI3BxiLSlDbLuL3mFIsw8wA?e=DL43jL)
5. Setiap anggota kelompok mengerjakan modul yang berbeda. Modul ditentukan oleh kelompok yang disesuaikan dengan ide aplikasi yang sudah didiskusikan dalam kelompok.
6. Tugas kelompok dikumpulkan sebagai kesatuan aplikasi web.

## Aturan Khusus per Anggota Kelompok

1. Menerapkan *models* dengan membuat atau memanfaatkan model yang sudah disediakan oleh Django maupun yang sudah dibuat oleh anggota kelompok (pada modul lain).
2. Menerapkan *views* untuk memproses *request* dan mengolah data untuk menghasilkan respons menggunakan templat HTML maupun mengembalikan respons JSON.
3. Menerapkan templat HTML dengan kerangka yang sistematis dan efisien, seperti `base.html`, `header.html`, dan `footer.html`.
4. Menerapkan *responsive framework* pada templat HTML (seperti [Bootstrap](https://getbootstrap.com/) atau [Tailwind](https://tailwindcss.com/)).
5. Memiliki halaman form yang dapat menerima masukan dari pengguna kemudian diproses oleh *views*. Contoh pemrosesan oleh *views* adalah *insert* data ke dalam model, *query* data dari model, dan *update* data pada model.
6. Menerapkan JavaScript dengan pemanggilan AJAX.
7. Menerapkan filter informasi bagi pengguna yang sudah *login* saja. Contohnya adalah data alamat, umur, dan nomor ponsel hanya dapat dilihat oleh pengguna yang sudah *login* saja.
8. Menerapkan filter pada dataset katalog buku yang ditampilkan. Contohnya adalah menampilkan daftar buku berdasarkan nama penulisnya.

## Tahapan Tugas Kelompok

<table>
    <tr>
        <th>Tahapan dan <em>deliverables</em></th>
        <th>Tenggat Waktu dan Keterangan</th>
    </tr>
    <tr>
        <td>
            <b>Tahap I (40%)</b>
            <ul>
                <li>Pembuatan GitHub kelompok</li>
                <li>README.md pada GitHub yang berisi:</li>
                    <ol>
                        <li>Nama-nama anggota kelompok</li>
                        <li>Cerita aplikasi yang diajukan serta manfaatnya</li>
                        <li>Daftar modul yang akan diimplementasikan</li>
                        <li>Sumber dataset katalog buku</li>
                    </ol>
                <li><em>Role</em> atau peran pengguna beserta deskripsinya (karena bisa saja lebih dari satu jenis pengguna yang mengakses aplikasi)</li>
            </ul>
        </td>
        <td>
            <b>Tenggat Waktu: Rabu, 11 Oktober 2023, pukul 23:55 WIB</b>
            <b>Kumpulkan tautan GitHub</b> dengan <em>code base</em> proyek Django yang sudah disiapkan di GitHub ke slot submisi yang tersedia di SCELE.
        </td>
    </tr>
    <tr>
        <td>
            <b>Tahap II (60%)</b>
            <p>(Modul sudah terimplementasi dengan baik)</p>
            <ul>
                <li>Modul aplikasi dari tiap anggota kelompok</li>
                <li><em>URL Mapping</em> untuk modul</li>
                <li><em>Models</em> untuk modul</li>
                <li><em>Views</em> untuk modul</li>
                <li>Terintegrasi sebagai satu kesatuan aplikasi</li>
                <li>Fungsionalitas sesuai dengan rancangan desain</li>
            </ul>
        </td>
        <td>
            <b>Tenggat Waktu: Minggu, 29 Oktober 2023, pukul 23.55 WIB</b>
            <p><b>Kriteria Submisi:</b> Seluruh modul yang dikerjakan oleh setiap anggota kelompok sudah muncul dan dapat diakses pada proyek Django</p>
        </td>
    </tr>
    <tr>
        <td>
            <b>Bonus (5%)</b>
            <ul>
                <li>Unit Test (<em>passed</em>) untuk semua aspek, diharapkan <em>code coverage</em> bisa mencapai minimal 80%</li>
                <li>GitHub Actions (CI/CD) sudah terkonfigurasi hingga <em>deployment</em></li>
                <li><em>Pipeline status</em> dan tautan aplikasi yang sudah di-<em>deploy</em> tersedia di berkas README.md pada GitHub</li>
            </ul>
        </td>
        <td></td>
    </tr>
</table>
