
# Koreksinya pake Python 3.10+ ya kaa <3 thank uu

def input_data(kamus: dict[str, str], data: str, key: str) -> None:
    try:
        kamus[key].add(data)                                                    # Menambahkan data

    except KeyError:
        kamus[key] = set()                                                      # Inisialisasi set
        kamus[key].add(data)                                                    # Menambahkan data pertama

def search_result(kamus: dict[str, str], bulan: str, tipe: str) -> bool:
    try:

        jumlah_entry: int = len(kamus[bulan])                                   # Jumlah data yang akan diprint

        print(f"Terdapat {jumlah_entry} {tipe} yang lahir di bulan {bulan}:")

        for entry in kamus[bulan]:                                              # Pencetakan semua isi set
            print(f"- {entry}")
        
        return True

    except KeyError:
        print(f"Tidak ditemukan mahasiswa dan NPM yang lahir di bulan {bulan}.")# Jika set kosong
        
        return False


def main() -> None:
    print("Selamat datang di program Mengenal Angkatan!")
    print("============================================")
    print("Masukkan identitas mahasiswa: ")

    kamus_nama: dict[str, str] = {}                                             # Dictionary nama
    kamus_npm: dict[str, str] = {}                                              # Dictionary npm

    while True:

        data: str = input()                                                     # Input data mahasiswa

        if data == "STOP":
            break

        nama, npm, *_, bulan, tahun = data.split()                              # List yang langsung di unpack
        
        input_data(kamus_nama, nama, bulan)
        input_data(kamus_npm, npm, bulan)
    
    while True:
        search: str = input("\nCari mahasiswa berdasarkan bulan: ")             # Input bulan yang mau di-search

        if search == "STOP":
            break

        print("================= Hasil ================")

        if syarat := search_result(kamus_nama, search, "nama"):                 # Pencetakan hasil search
            search_result(kamus_npm, search, "NPM")
    
    print("\nTerima kasih telah menggunakan program ini, semangat PMB-nya!")

if __name__ == "__main__":
    main()