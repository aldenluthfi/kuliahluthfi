print("Selamat datang ke Dek Depe Holiday Tracker!\n")

#Input jumlah tempat
jumlah_tempat = int(input("Masukkan banyak tempat wisata yang kamu kunjungi: "))

#Akan selalu bertanya selama jumlah_tempat bernilai <= 0
while jumlah_tempat <= 0:
    print("Input tidak valid. Silahkan masukkan input kembali!")
    jumlah_tempat = int(input("Masukkan banyak tempat wisata yang kamu kunjungi: "))

#deklarasi variabel rating akumulatif untuk menghitung skala kebahagiaan
rating_akumulatif = 0

#deklarasi variabel rating_tertinggi untuk men-track wisata paling berkesan
rating_tertinggi = 0

#deklarasi variabel tempat_favorit untuk menyimpan tempat favorit
tempat_favorit = ""

for counter in range(jumlah_tempat):

    #Input perjalanan dan ratingnya
    tempat = str(input(f"\nPerjalanan {counter + 1}: "))
    rating = int(input(f"Rating perjalanan kamu ke {tempat} (indeks 1-10): "))

    #Terpenuhi jika rating favorit saat iterasi ini lebih tinggi atau sama dengan rating favorit sebelumnya
    if rating >= rating_tertinggi:

        #Meng-update tempat favorit dan rating tertinggi saat ini
        tempat_favorit = tempat
        rating_tertinggi = rating
    
    #Menambah rating saat ini ke rating akumulatif
    rating_akumulatif += rating

#Perhitungan skala kebahagiaan
skala_kebahagiaan = rating_akumulatif / jumlah_tempat

#Deklarasi variabel pesan kepuasan Dek Depe
satisfaction = ""

#Penentuan pesan kepuasan Dek Depe
if skala_kebahagiaan < 5:
    satisfaction = "Dek Depe sedih karena pengalamannya buruk."
elif skala_kebahagiaan < 8:
    satisfaction = "Dek Depe senang karena pengalamannya cukup baik."
else:
    satisfaction = "Dek Depe bahagia karena pengalamannya menyenangkan."

#Output
print("\n---Summary---")
print(f"Perjalanan paling mengesankan adalah ketika pergi ke {tempat_favorit}.")
print(f"Skala kebahagiaan Dek Depe adalah {skala_kebahagiaan:.2f}")
print(satisfaction + "\n\n")
print("Terimakasih telah menggunakan Dek Depe Holiday Tracker!")
