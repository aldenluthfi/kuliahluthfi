print("Selamat datang di Pacil Mart!\n")
input_file_str = str(input("Masukkan nama file input: "))

try:

    #syntax agar file di close automatis, best practice untuk file handling
    with open(input_file_str, "r") as input_file:
        
        #membaca isi file dan membuat setiap baris menjadi sebuah list
        isi_file = input_file.readlines()

        # jika file kosong
        if not isi_file:
            print("File input ada tapi kosong")
        
        else:
            print("Berikut adalah daftar belanjaanmu:")
            print(f"\n{'Nama Barang':<12s}|{'Jumlah':>8s}|{'Kembalian':>10s}\n{'-'*32}")
            # untuk setiap baris di isi file
            for baris in isi_file:
                
                # memisahkan baris file ke data-data yang dapat diolah
                data = baris.split()

                # menghitung jumlah barang yang dapat dibeli dan kembalian
                jumlah = eval(f"{data[1]} // {data[2]}")
                kembalian = eval(f"{data[1]} % {data[2]}") if int(data[1]) >= int(data[2]) else 0
                
                print(f"{data[0]:<12s}|{jumlah:>8d}|{kembalian:>10d}")
            
            print("\nTerima kasih sudah belanja di Pacil Mart!")

# jika file tidak ada
except FileNotFoundError:
    print("File tidak tersedia")