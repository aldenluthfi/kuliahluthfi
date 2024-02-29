import math

#deklarasi awal
harga_kertas = 4/10

#input
nama = str(input("Nama: "))
panjang_persegi = float(input("Panjang persegi nametag (cm): "))
panjang_trapesium = float(input("Panjang trapesium nametag (cm): "))
jumlah_nametag = int(input("Banyak nametag: "))

#perhitungan luas
luas_tembereng = math.pi * (1/8) * pow(panjang_persegi, 2)
luas_persegi = pow(panjang_persegi, 2)
luas_segitiga = pow(panjang_persegi, 2) / 2
luas_trapesium = (panjang_persegi + panjang_trapesium) * panjang_persegi / 2

#perhitungan luas total
luas_total = luas_tembereng + luas_persegi + luas_segitiga + luas_trapesium

#perhitungan harga total
harga_total = harga_kertas*luas_total*jumlah_nametag

#output
print(f"Halo, {nama}! Berikut informasi terkait nametag kamu: \n")
print(f"Luas 1 nametag: {round(luas_total, 2)} cm^2")
print(f"Luas total nametag: {round(jumlah_nametag * luas_total, 2)} cm^2")
print(f"Uang yang diperlukan: Rp{math.ceil(harga_total/1000) * 1000}")
