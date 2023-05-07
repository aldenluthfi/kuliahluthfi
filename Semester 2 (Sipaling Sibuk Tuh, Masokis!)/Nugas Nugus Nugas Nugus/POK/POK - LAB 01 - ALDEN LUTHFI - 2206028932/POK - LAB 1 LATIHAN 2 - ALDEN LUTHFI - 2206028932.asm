.data
pesan1: .asciiz "Selamat Datang di Restoran Peokra!\n"

input1: .asciiz "Masukkan banyak kategori pesanan: "
input2: .asciiz "Total menu yang dipessan pada kategori "
input2_1: .asciiz ": "
input3: .asciiz "\nMasukan nominal pembayaran: "

output1: .asciiz "Minimal pesan satu makanan!\n"
output2: .asciiz "Total Harga Pesanan : "
output3: .asciiz "Maaf, uang anda kurang sebesar "
output4: .asciiz "\nTotal Harga yang perlu dibayar: "
output5: .asciiz "\nTotal ppn: "
output6: .asciiz "\nTotal service: "
output7: .asciiz "Terima kasih, berikut kembalian sebesar "

# $s0 -> jumlah kategori
# $s1 -> Akumulasi total harga
# $s2 -> ppn
# $s3 -> service

# $t0 -> loop counter kategori
# $t1 -> harga makanan saat ini
# $t2 -> menyimpan subtotal
# $t3 -> pembayaran user dan kembalian/kekurangan

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

        ble $v0, $zero, salahInput                                              # Jika input <= 0
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

                ble $v0, $zero, salahInput                                      # Jika input <= 0
                mul $t2, $t1, $v0                                               # Menghitung subtotal

                add $s1, $s1, $t2                                               # menambah ke total

                blt $t0, $s0, loop                                              # jika kategori belum habis

        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, output2                                                         # Load argument string
        syscall                                                                 # Execute

        li $v0, 1                                                               # Load perintah print integer
        addi $a0, $s1, 0                                                        # Load argumen integer
        syscall                                                                 # Execute

        mul $s2, $s1, 10                                                        # Menhitung ppn
        div $s2, $s2, 100                                                       # Menghitung ppn

        mul $s3, $s1, 5                                                         # Menhitung service
        div $s3, $s3, 100                                                       # Menghitung service

        add $s1, $s1, $s2                                                       # Menambah ppn ke total
        add $s1, $s1, $s3                                                       # Menambah service ke total

        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, output5                                                         # Load argument string
        syscall                                                                 # Execute

        li $v0, 1                                                               # Load perintah print integer
        addi $a0, $s2, 0                                                        # Load argumen integer
        syscall                                                                 # Execute

        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, output6                                                         # Load argument string
        syscall                                                                 # Execute

        li $v0, 1                                                               # Load perintah print integer
        addi $a0, $s3, 0                                                        # Load argumen integer
        syscall                                                                 # Execute

        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, output4                                                         # Load argument string
        syscall                                                                 # Execute

        li $v0, 1                                                               # Load perintah print integer
        addi $a0, $s1, 0                                                        # Load argumen integer
        syscall                                                                 # Execute

        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, input3                                                          # Load argument string
        syscall                                                                 # Execute

        li $v0, 5                                                               # Mengeload perintah input integer
        syscall                                                                 # Execute

        addi $t3, $v0, 0                                                        # penyimpan nominal pembayaran

        blt $t3, $s1, uangKurang                                                # kalau uangnya kurang

        j benar                                                                 # lanjut ke output

salahInput:
        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, output1                                                         # Load argument string
        syscall                                                                 # Execute

        j exit                                                                  # Keluar dari program

uangKurang:
        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, output3                                                         # Load argument string
        syscall                                                                 # Execute

        sub $t3, $s1, $t3                                                       # menghitung kekurangan uang

        li $v0, 1                                                               # Load perintah print integer
        addi $a0, $t3, 0                                                        # Load argumen integer
        syscall                                                                 # Execute

        j exit                                                                  # Keluar dari program

benar:
        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, output7                                                         # Load argument string
        syscall                                                                 # Execute

        sub $t3, $t3, $s1                                                       # menghitung kekurangan uang

        li $v0, 1                                                               # Load perintah print integer
        addi $a0, $t3, 0                                                        # Load argumen integer
        syscall                                                                 # Execute


exit:                                                                           # Akhir dari program
        li $v0, 10                                                              # Exit Program
        syscall                                                                 # Execute
