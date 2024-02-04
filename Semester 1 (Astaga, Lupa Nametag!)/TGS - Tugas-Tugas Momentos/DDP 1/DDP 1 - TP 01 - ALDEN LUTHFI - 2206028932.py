import turtle
import random

#mode rahasia
mode_rahasia = False

def main():

    #deklarasi layar turtle utama dan mengawali beberapa pengaturannya
    turtle.getscreen()
    turtle.setworldcoordinates(-500, -500, 500, 500)
    turtle.hideturtle()
    turtle.speed(0)
    turtle.bgcolor("green" if not mode_rahasia else "white")

    #pesan pesan (pake lambda dikit boleh yaa) ini agar code tidak >80 karakter
    bawah = "Jumlah Bata Bawah"
    atas = "Jumlah Bata Atas"
    panjang = "Panjang Bata"
    lebar = "Lebar Bata"

    #template pesan
    normal = lambda pesan: f"Masukkan {pesan.lower()}: "
    cancel = lambda pesan: f"Kami butuh input valid, masukkan {pesan.lower()}: "

    #input dan validasi jumlah bata bawah
    bata_bawah = turtle.numinput(bawah, normal(bawah), None, 1, 25)
    while bata_bawah == None or not bata_bawah.is_integer():
        bata_bawah = turtle.numinput(bawah, cancel(bawah), None, 1, 25)
    bata_bawah = int(bata_bawah)

    #input dan validasi jumlah bata atas
    bata_atas = turtle.numinput(atas, normal(atas), None, 1, bata_bawah)
    while bata_atas == None or not bata_atas.is_integer():
        bata_atas = turtle.numinput(atas, cancel(atas), None, 1, bata_bawah)
    bata_atas = int(bata_atas)

    #input dan validasi panjang bata
    panjang_bata = turtle.numinput(panjang, normal(panjang), None, 1, 35)
    while panjang_bata == None or not panjang_bata.is_integer():
        panjang_bata = turtle.numinput(panjang, cancel(panjang), None, 1, 35)
    panjang_bata = int(panjang_bata)

    #input dan validasi lebar bata
    lebar_bata = turtle.numinput(lebar, normal(lebar), None, 1, 25)
    while lebar_bata == None or not lebar_bata.is_integer():
        lebar_bata = turtle.numinput(lebar, cancel(lebar), None, 1, 25)
    lebar_bata = int(lebar_bata)

    #variabel yang bakal penting, rumus Sn deret aritmatika Sn = n/2(a+Un)
    jumlah_lapisan = bata_bawah - bata_atas + 1
    jumlah_diperlukan = int((jumlah_lapisan / 2) * (bata_bawah + bata_atas))

    #koordinat y: turun sejauh setengah tinggi candi dari titik (0, 0)
    koordinat_y = 0 - ((jumlah_lapisan / 2) * lebar_bata)
    #koordinat x: ke kiri sejauh setengah lebar bata bawah dari titik (0, 0)
    koordinat_x = 0 - ((bata_bawah / 2) * panjang_bata)

    #looping untuk setiap lapisan
    for lapisan in range(jumlah_lapisan):

        #persiapan menggambar lapisan dengan menaruh turtle di titik (x, y)
        turtle.penup()
        turtle.goto(koordinat_x, koordinat_y)
        turtle.pendown()

        #penentuan jumlah bata pada lapisan (berkurang 1 setiap lapisan)
        bata_lapisan = bata_bawah - lapisan

        #looping untuk setiap bata
        for bata in range(bata_lapisan):

            #warna random yang beda dari bata normal
            warna = f"#{random.randint(0x000000, 0xFFFFFF):06x}"
            if warna == "#bc4a3c":
                warna = "#bc4d3d"

            #warna mode rahasia
            if mode_rahasia:
                warna = pewarna(jumlah_lapisan, bata_lapisan, lapisan, bata)

            #penentuan jenis bata
            if lapisan == 0 or lapisan == jumlah_lapisan - 1:
                warna = "#bc4a3c"
            elif bata == 0 or bata == bata_lapisan - 1:
                warna = "#bc4a3c"

            #menggambar 1 bata
            turtle.fillcolor(warna)
            turtle.begin_fill()

            for gambar_belok in range(2):
                turtle.forward(panjang_bata)
                turtle.left(90)
                turtle.forward(lebar_bata)
                turtle.left(90)

            turtle.end_fill()

            #pesiapan untuk bata selanjutnya, turtle ke kiri bawah bata
            turtle.penup()
            turtle.forward(panjang_bata)
            turtle.pendown()

        #next lapisan, posisi awal naik 1 lebar bata, ke kanan 1/2 panjang bata
        koordinat_y += lebar_bata
        koordinat_x += panjang_bata / 2

    #menulis pesan jumlah bata
    pesan_jumlah = f"Jadilah candi dari {jumlah_diperlukan} bata"

    turtle.penup()
    turtle.goto(0, 450)
    turtle.pendown()

    turtle.write(pesan_jumlah, align="center", font=("Courier New", 30))

    #mengakhiri program saat diklik (tidak langsung mati)
    turtle.exitonclick()

'''
################################################################################
Metode Rahasia, ubah variabel mode_rahasia menjadi True (just for fun)
################################################################################
'''

def pewarna(jumlah_lapisan, bata_lapisan, lapisan, bata):

    if bata_lapisan > 2:

        partisi_hijau = 255 // (jumlah_lapisan - 2)
        partisi_merah_biru= 255 // (bata_lapisan - 2)

        start_range_hijau = partisi_hijau * (lapisan - 1)
        end_range_hijau = partisi_hijau * lapisan
        nilai_hijau = random.randint(start_range_hijau, end_range_hijau)


        start_range_merah = partisi_merah_biru * (bata - 1)
        end_range_merah = partisi_merah_biru * bata
        nilai_merah = random.randint(start_range_merah, end_range_merah)

        start_range_biru = 255 - end_range_merah
        end_range_biru = 255 - start_range_merah
        nilai_biru = random.randint(start_range_biru, end_range_biru)

        return f"#{nilai_merah:02x}{nilai_hijau:02x}{nilai_biru:02x}"

'''
################################################################################
Akhir dari Metode Rahasia
################################################################################
'''

#good practice menurut beberapa buku, penanda bahwa ini script bukan modul
if __name__ == "__main__":
    main()

