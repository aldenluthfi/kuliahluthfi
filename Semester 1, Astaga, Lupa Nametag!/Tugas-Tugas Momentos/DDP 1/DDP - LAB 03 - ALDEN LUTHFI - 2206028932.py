print("Selamat Datang di Bunker Hacker!\n")

jumlah_input = int(input("Masukkan berapa kali konversi yang ingin dilakukan: "))

for iterasi in range(1, jumlah_input + 1):

    desimal = int(input(f"\nMasukkan angka ke-{iterasi} yang ingin dikonversikan (dalam desimal): "))
    
    # deklarasi variable untuk menyimpan hasil konversi
    hasil = ""
    
    # menggunakan walrus operator yang baru di python 3.8 https://docs.python.org/3/whatsnew/3.8.html
    while hasil_bagi := desimal // 8:
        
        # mengupdate digit (sisa pembagian) dan ditambahkan ke hasil
        digit = f"{desimal % 8}"
        hasil = digit + hasil

        # menyiapkan untuk iterasi berikutnya
        desimal = hasil_bagi
    
    else:

        # menambahkan digit pertama
        hasil = f"{desimal}" + hasil
        
    print(f"Hasil konversi desimal ke basis 8 : {hasil}")

print("\nTerima kasih telah menggunakan Bunker Hacker!")