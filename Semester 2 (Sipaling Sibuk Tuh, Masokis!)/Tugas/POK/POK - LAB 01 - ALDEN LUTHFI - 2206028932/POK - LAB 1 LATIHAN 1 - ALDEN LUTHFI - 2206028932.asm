.data
pesan1: .asciiz "Selamat Datang di Restoran Peokra!\n"

input1: .asciiz "Masukkan banyak kategori pesanan: "
input2: .asciiz "Total menu yang dipessan pada kategori "
input2_1: .asciiz ": "

output1: .asciiz "Minimal pesan satu makanan!\n"
output2: .asciiz "Total Harga Pesanan : "

# $s0 -> jumlah kategori
# $s1 -> Akumulasi total harga

# $t0 -> loop counter kategori
# $t1 -> harga makanan saat ini
# $t2 -> menyimpan subtotal


.text
main:

        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, pesan1                                                          # Load argument string
        syscall                                                                 # Execute

        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, input1                                                          # Load argument string
        syscall                                                                 # Execute

        li $v0, 5                                                               # Mengeload perintah input integer
        syscall                                                                 # Execute

        ble $v0, $zero, salah                                                   # Jika input <= 0
        addi $s0, $v0, 0                                                         # menyimpan variabel ke $s0

        loop:
                addi $t0, $t0, 1                                                # Increment loop counter
                mul $t1, $t0, 5000                                              # Menghitung harga makanan

                li $v0, 4                                                       # Mengeload perintah print string
                la $a0, input2                                                  # Load argument string
                syscall                                                         # Execute

                li $v0, 1                                                       # Load perintah print integer
                addi $a0, $t0, 0                                                # Load argumen integer
                syscall                                                         # Execute

                li $v0, 4                                                       # Mengeload perintah print string
                la $a0, input2_1                                                # Load argument string
                syscall                                                         # Execute

                li $v0, 5                                                       # Mengeload perintah input integer
                syscall                                                         # Execute

                ble $v0, $zero, salah                                           # Jika input <= 0
                mul $t2, $t1, $v0                                               # Menghitung subtotal

                add $s1, $s1, $t2                                               # menambah ke total

                blt $t0, $s0, loop                                              # jika kategori belum habis

        j benar                                                                 # lanjut ke output

salah:
        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, output1                                                         # Load argument string
        syscall                                                                 # Execute

        j exit                                                                  # Keluar dari program

benar:
        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, output2                                                         # Load argument string
        syscall                                                                 # Execute

        li $v0, 1                                                               # Load perintah print integer
        addi $a0, $s1, 0                                                        # Load argumen integer
        syscall                                                                 # Execute


exit:                                                                           # Akhir dari program
        li $v0, 10                                                              # Exit Program
        syscall                                                                 # Execute
