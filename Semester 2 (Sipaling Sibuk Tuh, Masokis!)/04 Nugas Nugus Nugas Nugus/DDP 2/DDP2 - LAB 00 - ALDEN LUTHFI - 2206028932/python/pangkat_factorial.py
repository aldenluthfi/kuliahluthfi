from turtle import st


n = int(input("masukkan bilangan utama (n): "))
m = int(input("masukkan pemangkatan (m): "))

# Factorial
hasil_fact = 1
temp_n = n
while temp_n >= 1:
    hasil_fact = hasil_fact * temp_n
    temp_n = temp_n - 1

# Pangkat
hasil_pangkat = 1
for i in range(m):
    hasil_pangkat = hasil_pangkat * n

print("n factorial = " + str(hasil_fact))
print("n pangkat m = " + str(hasil_pangkat))
