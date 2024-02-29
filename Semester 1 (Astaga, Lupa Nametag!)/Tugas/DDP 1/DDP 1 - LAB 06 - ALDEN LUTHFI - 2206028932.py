print("Selamat mencoba Program Pemeriksa Nilai Dek Depe!")
print("="*49, "\n\nMasukkan kunci jawaban:")

def cek(kunci: list[str], jawaban: list[str]) -> int:                           # Function yang menghitung jumlah benar
    return sum(int(a == b) for a, b in zip(kunci, jawaban))

kunci = []                                                                      # List berisi kunci jawaban

kunci_nomor = input()
while kunci_nomor != 'STOP':                                                    # Input kunci jawaban
    kunci.append(kunci_nomor)
    kunci_nomor = input()

print("Masukkan jawaban kamu:")

jawaban = []                                                                    # List berisi jawaban user

for _ in kunci:                                                                 # Input jawaban user
    jawaban.append(input())

poin = cek(kunci, jawaban)                                                      # Menyimpan Jumlah Benar
nilai = poin * 100 // len(kunci)                                                # Menghitung nilai

if nilai < 55:                                                                  # Penentuan pesan berdasarkan nilai
    print("nt")
elif nilai < 85:
    print("Semangat :)")
else:
    print("Selamat :D")

print(f"Total jawaban benar adalah {poin} dari {len(kunci)} soal")
print(f"Nilai yang kamu peroleh adalah {nilai}")
