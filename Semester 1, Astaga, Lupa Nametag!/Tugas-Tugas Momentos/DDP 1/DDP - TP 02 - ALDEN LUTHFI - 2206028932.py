import os
import sys

cetak_hasil = lambda x, y, z: print(f"{x:<40s} line {y:<3d} {z[:40]:<40s}")     # Mencetak hasil kesamaan program
cetak_error_berkas = lambda x: print(f"Path {x} tidak ditemukan")               # Mencetak jika berkas tidak ditemukan
cetak_error_argumen = lambda: print("Argumen program tidak benar.")             # Mencetak jika argumen tidak valid

def main(argv: list) -> None:                                                   # Fungsi utama program

    *argumen, pola, direktori = argv                                            # Unpacking argumen yang dimasukkan user

    if not os.path.exists(direktori):
        cetak_error_berkas(direktori)
        sys.exit()                                                              # Pengecekan argumen terakhir user

    syarat_error = [pola.count('*') > 1, '-w' in argumen and '-i' in argumen]   # Pengecekan pola string user
    syarat_error.append(len(argumen) == 2 and argumen[-1] not in ['-w', '-i'])  # harus -w atau -i atau tidak keduanya

    if any(syarat_error) or len(argumen) > 2:
        cetak_error_argumen()
        sys.exit()                                                              # Pencetakan pesan error argumen

    if os.path.isfile(direktori): pindai_berkas(direktori, argumen, pola)       # langsung mengecek kalau direktori file

    for direktori_akar, subdirektori, berkas_berkas in os.walk(direktori):      # kita tdk memakai subdirektori
        if berkas_berkas:                                                       # Jika ada berkas di direktori ini
            for berkas in berkas_berkas:                                        # Untuk setiap file di direktori ini
                pindai_berkas(f"{direktori_akar}/{berkas}", argumen, pola)      # Memanggil fungsi scan utk setiap file

def pindai_berkas(jalur_berkas: str, argumen: list, pola: str) -> None:         # Fungsi scan

    with open(jalur_berkas, "r") as berkas:                                     # Agar langsung close jika out of scope
        baris_baris: list = berkas.readlines()                                  # Membaca per baris

        for nomor, isi in enumerate(baris_baris, 1):                            # Mengambil nomor dan isi baris
            if cek(argumen, pola, isi):                                         # Jika pola ada di isi baris tertentu
                cetak_hasil(jalur_berkas, nomor, isi.strip())                   # Mencetak output yang verbose

def cek(argumen: list, pola: str, isi: str) -> bool:                            # Fungsi validasi pola ke baris

    if '-i' in argumen:                                                         # Mengubah semua jadi huruf kecil
        pola = pola.lower()                                                     # Untuk case insensitive
        isi = isi.lower()

    if '-w' in argumen:                                                         # Semua kata diawali dan diakhiri
        pola = f" {pola.strip()} "                                              # dengan " " untuk whole word
        isi = f" {isi.strip()} "

    if "*" in pola:
        awal, akhir = pola.split("*")                                           # Memisahkan dua buah bagian wildcard
        syarat = [awal in isi, akhir in isi]                                    # Jika kedua bagian ada di isi baris

        if all(syarat):                                                         # Syarat bagian pertama harus sebelum
            syarat.append(isi.index(awal) + len(awal) - 1 < isi.rindex(akhir))  # bagian kedua

        return all(syarat)                                                      # True jika semua syarat terpenuhi

    if "*" not in pola:                                                         # Jika bukan wildcard
        return pola in isi                                                      # True jika pola ada di baris

if __name__ == "__main__":                                                      # Memanggil fungsi main
    main(sys.argv)