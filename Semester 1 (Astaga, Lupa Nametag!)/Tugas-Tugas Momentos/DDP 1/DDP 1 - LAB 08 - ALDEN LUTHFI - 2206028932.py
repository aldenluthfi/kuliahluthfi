def search(awal, tujuan, kenal, jarak):                                         # mencari hubungan 2 orang mulai dari orang tujuan ke orang awal

    if awal not in kenal.keys():                                                # Kalau tidak ada yang kenal sama orang tujuan
        return False

    if kenal[awal] == tujuan:                                                   # kalau orang tujuan telah ditemukan
        return jarak[awal]

    result = search(kenal[awal], tujuan, kenal, jarak)

    return jarak[awal] + result if result != False else False                   # Rekursi

def main():
    print('Masukkan data hubungan:')

    dict_kenalan = {}                                                           # Inisialisasi dictionary kenalan
    dict_jarak = {}                                                             # Inisialisasi dictionary jarak

    while True:
        data = input()

        if data == 'SELESAI':
            break

        nama_x, kenalan_x, jarak = data.split()

        dict_kenalan[nama_x] = kenalan_x                                        # Input kenalan ke dictionary
        dict_jarak[nama_x] = float(jarak)                                       # Input jarak ke dictionary

    awal = input('\nMasukkan nama awal: ')
    tujuan = input('Masukkan nama tujuan: ')

    jarak_total = search(awal, tujuan, dict_kenalan, dict_jarak)                # menentukan hubungan antara 2 orang

    if jarak_total == False:                                                    # proses output
        print(f'Tidak ada hubungan antara {awal} dan {tujuan}')
    else:
        print(f'Jarak total: {int(jarak_total * 10)}')
        if jarak_total * 10 <= 100:
            print(f'{awal} dan {tujuan} kenal dekat.')
        elif jarak_total * 10 <= 1000:
            print(f'{awal} dan {tujuan} mungkin saling kenal.')
        else:
            print(f'{awal} dan {tujuan} tidak saling kenal.')

if __name__ == "__main__":
    main()