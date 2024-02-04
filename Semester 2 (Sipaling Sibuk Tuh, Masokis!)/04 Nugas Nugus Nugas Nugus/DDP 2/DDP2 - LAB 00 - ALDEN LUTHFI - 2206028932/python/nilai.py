nama = input("Masukkan Nama Mahasiswa: ")
nilai_asli = int(input("Masukkan Nilai Asli: "))
durasi = int(input("Masukkan Durasi: "))

if durasi < 60:
    nilai_akhir = 1.2 * nilai_asli
elif 60 <= durasi <= 70:
    nilai_akhir = nilai_asli
elif 70 < durasi < 90:
    nilai_akhir = 0.75 * nilai_asli
elif 90 <= durasi <= 100:
    nilai_akhir = 0.5 * nilai_asli
else:
    nilai_akhir = 0.2 * nilai_asli

print(nama + " mendapatkan nilai akhir " + str(nilai_akhir))
