import math

def main() -> None:
    print(f"Selamat datang di Depot Minuman Dek Depe!\n{'='*42}")

    input_valid: list = ["BALOK", "KERUCUT"]
    total_volume_air: int = 0
    ada_input: bool = False

    # Jika user belum memberi input STOP
    bentuk_galon: str = input("\nMasukkan bentuk galon yang diinginkan (STOP untuk berhenti): ")
    while bentuk_galon != "STOP": 
        
        # Jika user memberi input tidak valid
        while bentuk_galon not in input_valid:
            bentuk_galon = input("Input tidak benar, masukkan kembali\n\nMasukkan bentuk galon yang diinginkan (STOP untuk berhenti): ")

            # Jika user menghentikan programnya secara prematur
            if bentuk_galon == "STOP":
                break
        
        else: #Jika sudah valid maka ada input
            ada_input = True

        if bentuk_galon in input_valid: # Jika user memasukkan bentuk valid
            total_volume_air += eval(f"{bentuk_galon.lower()}()") # Menghitung total volume air

            # Meminta input lagi
            bentuk_galon = input("\n\nMasukkan bentuk galon yang diinginkan (STOP untuk berhenti): ")

    output(total_volume_air, ada_input)
        
# Function untuk menhitung volume balok
def balok() -> int:

    panjang_balok: float = float(input("Masukkan panjang balok : "))
    lebar_balok: float = float(input("Masukkan lebar balok : "))
    tinggi_balok: float = float(input("Masukkan tinggi balok : "))

    return panjang_balok * lebar_balok * tinggi_balok

# Function untuk menghitung volume kerucut
def kerucut() -> int:
    
    jari_jari_balok: float = float(input("Masukkan panjang balok : "))
    tinggi_balok: float = float(input("Masukkan lebar balok : "))

    return (math.pi * pow(jari_jari_balok, 2) * tinggi_balok) / 3

# Function untuk output
def output(total_volume_air: int, ada_input: bool) -> None:

    if ada_input:
        print(f"\n{'='*  50}\nTotal volume air yang dikeluarkan adalah : {total_volume_air:.2f}")
        print(f"Total harga yang harus dibayar adalah : Rp{total_volume_air * 700:.2f}\n{'=' * 50}\n")
        print("Terima kasih telah menggunakan Depot Air Minum Dek Depe")
    
    else:
        print(f"\n{'='*  50}\nAnda tidak memasukkan input satupun :(")
        print(f"Terima kasih telah menggunakan Depot Air Minum Dek Depe\n{'=' * 50}")


if __name__ == "__main__":
    main()