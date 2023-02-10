import os, typing, re                                                           # Import modul-modul yang dipakai

i = lambda x: input(x)                                                          # Shortcut untuk input
o = lambda x, e='\n', f=None: print(x, end=e, file=f)                           # Shortcut untuk print

slit = lambda x, y: x.strip().split(y)                                          # Shortcut unutuk strip dan split
total = lambda x, y: f'{sum(x[i] * y[i] for i in x.keys()):,}'.replace(',', '.')# Shortcut untuk menghitung total

def main() -> None:

    data_menu: dict[str, dict[tuple[str, str], int]] | None = pindai_menu()     # Pembacaan menu
    
    if data_menu == None:                                                       # Validasi menu
        o("Daftar menu tidak valid, cek kembali menu.txt!")
        return
    
    isi: list[dict[tuple[str, str], int]] = list(data_menu.values())            # Dictionary isi dari menu
    harga: dict[str, int] = {k: v for m in isi for t, v in m.items() for k in t}# Dictionary menu: harga
    kode: dict[str, str] = {k: v for m in isi for k, v in m.keys()}             # Dictionary kode: nama
    nama: dict[str, str] = {v: k for m in isi for k, v in m.keys()}             # Dictionary nama: kode
    pengguna: dict[int, str | None] = {n: None for n in range(1, 11)}           # Dictionary no. meja: nama pengguna
    pesanan: dict[int, dict[str, int] | None] = {n: None for n in range(1, 11)} # Dictionary no. meja: dict pesanan

    while True:                                                                 # Loop Utama

        o('Selamat datang di Kafe Daun Daun Pacilkom')

        match i('Apa yang ingin Anda lakukan? '):
            case 'BUAT PESANAN':
                buat(kode, pengguna, pesanan, data_menu, harga)
            case 'UBAH PESANAN':
                ubah(kode, pesanan, data_menu, harga)
            case 'SELESAI MENGGUNAKAN MEJA':
                selesai(nama, pengguna, pesanan, harga)
            case _:
                o('Perintah tidak benar, silakan coba lagi\n\n---')
                continue

def pindai_menu() -> dict[str, dict[tuple[str, str], int]] | None:              # Function untuk pindai menu

    hasil: dict[str, dict[tuple[str, str], int]] = {}                           # Hasil yang akan di return
    list_nama: list[str] = []                                                   # Untuk mengecek duplikat

    with open('menu.txt', 'r') as berkas:                                       # Membuka file

        isi: str = berkas.read()

        for baris in (f' {x.replace(" ", "_")} ' for x in slit(isi, '\n')):
            if not re.search(' ===.+ | [^;_]+;[^;]+;[0-9]+ ', baris):           # Mengecek format menu per baris
                return

        entri: list[list[str]] = [slit(x, '\n') for x in slit(isi, '===') if x]
        
        for judul, *menu in entri:                                              # Membuat Dictionary menu
            if judul in hasil.keys(): 
                return
            
            hasil[judul] = {}

            for menu_item in menu:
                kode, nama, harga = slit(menu_item, ';')
                
                if kode in list_nama or nama in list_nama: 
                    return

                list_nama.extend([kode, nama])                                  # Menambah biar tidak ada duplikat
                hasil[judul][(kode, nama)] = int(harga)                         # Memberikan value pada key
    
    return hasil

def cetak_menu(data_menu: dict[str, dict[tuple[str, str], int]]) -> str:        # Mencetak menu

    hasil: str = '\nBerikut ini adalah menu yang kami sediakan:'

    for judul, menu in data_menu.items():                                       # Mengambil nilai kategori dan isi menu
        hasil += f'\n{judul}:'

        for (kode, nama), harga in menu.items():
            hasil += f'\n{kode} {nama}, Rp{f"{harga:,}".replace(",", ".")}'
    
    return hasil                                                                # Mereturnnya sebagai string

def cetak_pesanan(pesanan: dict[str, int], harga: dict[str, int]) -> str:       # Mencetak pesanan sesuai meja

    hasil: str = '\nBerikut adalah pesanan Anda:'

    for nama, jumlah in pesanan.items():                                        # Mengambil nilai nama dan jumlah
                                                                                
        subtotal: str = f"{jumlah * harga[nama]:,}".replace(',', '.')           # dari dictionary
        hasil += f'\n{nama} {jumlah} buah, total Rp{subtotal}'

    return hasil

def validasi_item(item: str, key: typing.Any) -> str | None:                    # Validasi makanan ada pada menu

    list_valid: list[str] = [x for m in key.items() for x in m]                 # Membuat list nama item yang valid                

    if item not in list_valid and item not in list_valid:
        o(f'Menu {item} tidak ditemukan. ', e='') 
        return       
    
    if item in key.keys():                                                      # Jika item berbentuk kode maka ubah ke
        item = key[item]                                                        # bentuk nama
    
    return item

def buat(*info_kafe: typing.Any) -> None:

    kode, pengguna, pesanan, data_menu, harga = info_kafe

    if all(x != None for x in pesanan.values()):                                # Mengecek jika meja sudah penuh
        o('Mohon maaf meja sudah penuh, silakan kembali nanti.\n\n---')
        return

    meja: int = list(pesanan.values()).index(None) + 1                          # Mengambil meja yang kosong
    pengguna[meja] = i('Siapa nama Anda? ')
    pesanan[meja] = {}                                                          # Inisialisasi pesanan

    o(f'{cetak_menu(data_menu)}\n')

    while True:
        
        pesan: str = i('Masukkan menu yang ingin Anda pesan: ')                 # Input pesanan

        if pesan == 'SELESAI':
            break

        if (valid := validasi_item(pesan, kode)) == None:                       # Validasi input                     
            continue

        pesan = valid
        pesanan[meja][pesan] = pesanan[meja].get(pesan, 0) + 1                  # Menambahkan pesanan

        o(f'Berhasil memesan {pesan}. ', e='')
    
    o(cetak_pesanan(pesanan[meja], harga))                                      # Output
    o(f'\nTotal pesanan: Rp{total(pesanan[meja], harga)}')
    o('Pesanan akan kami proses, Anda bisa menggunakan meja ', e='')
    o(f'nomor {meja}. Terima kasih.\n\n---')

def ubah(*info_kafe: typing.Any) -> None:

    kode, pesanan, data_menu, harga = info_kafe                                 # Unpacking info kafe yang diperlukan
    meja: str = i('Nomor meja berapa? ')                                        # Input nomor meja

    if not meja.isnumeric() or not int(meja) or pesanan[int(meja)] == None:     # Validasi input
        o('Nomor meja kosong atau tidak sesuai!\n\n---')
        return
    
    pesan_meja: dict[str, int] = pesanan[int(meja)]

    o(cetak_menu(data_menu))
    o(f'{cetak_pesanan(pesan_meja, harga)}\n')

    while True:
        match i('Apakah Anda ingin GANTI JUMLAH, HAPUS, atau TAMBAH PESANAN? '):# Query input
            case 'GANTI JUMLAH':
                ganti: str = i('Menu apa yang ingin Anda ganti jumlahnya: ')    # Input

                if (valid := validasi_item(ganti, kode)) == None:               # Validasi input
                    continue

                if not pesan_meja.get(valid):
                    o(f'Menu {ganti} tidak anda pesan sebelumnya. ', e='')
                    continue
                
                ganti = valid
                jumlah: int = int(i('Masukkan jumlah pesanan yang baru: '))

                if jumlah <= 0:                                                 # Validasi jumlah
                    o('Jumlah harus bilangan positif! ', e='')
                    continue

                pesan_meja[ganti] = jumlah                                      # Memperbaharui pesanan
                o(f'Berhasil memesan {ganti} {jumlah} buah. ', e='')
            case 'HAPUS':
                hapus: str = i('Menu apa yang ingin Anda hapus dari pesanan: ') # Input

                if (valid := validasi_item(hapus, kode)) == None:               # Validasi input
                    continue

                if not pesan_meja.get(valid):                                   # Validasi input
                    o(f'Menu {hapus} tidak anda pesan sebelumnya. ', e='')
                    continue

                hapus = valid
                jumlah: int = pesan_meja[hapus]                                 

                del pesan_meja[hapus]                                           # Menghapus pesanan
                
                o(f'{jumlah} buah {hapus} dihapus dari pesanan. ', e='')                
            case 'TAMBAH PESANAN':
                tambah: str = i('Menu apa yang ingin Anda pesan: ')             # Input

                if (valid := validasi_item(tambah, kode)) == None:              # Validasi input
                    continue

                tambah = valid

                pesan_meja[tambah] = pesan_meja.get(tambah, 0) + 1              # Memperbaharui pesanan
                o(f'Berhasil memesan {tambah}. ', e='')
            case 'SELESAI':
                break
            case _:
                o('Perintah tidak benar, silakan coba lagi. ' , e='')
                continue

    o(cetak_pesanan(pesan_meja, harga))                                         # Output akhir
    o(f'\nTotal pesanan: Rp{total(pesan_meja, harga)}\n\n---')

def selesai(*info_kafe: typing.Any) -> None:
    
    nama, pengguna, pesanan, harga = info_kafe
    meja: str = i('Nomor meja berapa? ')                                        # Input nomor meja

    if not meja.isnumeric() or not int(meja) or pesanan[int(meja)] == None:     # Validasi nomor meja
        o('Nomor meja kosong atau tidak valid!\n\n---')
        return
    
    pesan_meja: dict[str, int] = pesanan[int(meja)]
    customer: str = pengguna[int(meja)]

    o(f'Pelanggan atas nama {customer} selesai menggunakan meja {meja}.\n\n---')

    nama_file : str = f'receipt_{customer}.txt'
    counter: int = 1

    while os.path.exists(nama_file):                                            # Membuat nama file unik
        nama_file = f'receipt_{customer}({counter}).txt'
        counter += 1

    with open(nama_file, 'w') as berkas:                                        # Menulis file

        total: int = 0

        for key, value in pesan_meja.items():
            subtotal: int = value * harga[key]
            entri: str = f'{nama[key]};{key};{value};{harga[key]};{subtotal}'
            total += subtotal
            
            o(entri, f=berkas)
        
        o(f'\nTotal {total}', f=berkas)
    
    pesanan[int(meja)] = None                                                   # Mengosongkan pesanan

if __name__ == "__main__":
    main()