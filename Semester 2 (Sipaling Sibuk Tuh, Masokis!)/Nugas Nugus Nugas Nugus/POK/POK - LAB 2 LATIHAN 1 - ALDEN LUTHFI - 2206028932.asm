.data

input1: .asciiz "Masukkan input 10 digit: "
output1: .asciiz "Total penjualan mobil : "
output2: .asciiz "Total penjualan motor : "

newline: .asciiz "\n"

arrayKosong1: .word 0, 0, 0, 0, 0
arrayKosong2: .word 0, 0, 0, 0, 0

.text
.globl main

# $s0 -> arrayKosong1
# $s1 -> arrayKosong2
# $s3 -> hasil mobil
# $s4 -> hasil motor

# $t0 -> loop counter dua digit
# $t1 -> input raw
# $t2 -> sisa pembagian dua digit (puluhan)
# $t3 -> sisa pembagian 1 digit (satuan)
# $t4 -> word yang akan ditambahkan

main:

        la $s0, arrayKosong1                                                    # Memeasukkan address dari array
        la $s1, arrayKosong2                                                    # Memeasukkan address dari array

        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, input1                                                          # Load argument string
        syscall                                                                 # Execute

        li $v0, 5                                                               # Mengeload perintah input integer
        syscall                                                                 # Execute
        addi $t1, $v0, 0                                                        # Memasukkan input raw

        addi $t0, $zero, 0                                                      # Inisialisasi counter loop

        inputLoop:

                div $t1, $t1, 100                                               # Mengisolasikan 2 digit terakhir
                mflo $t1                                                        # Hasilnya untuk iterasi berikutnya
                mfhi $t2                                                        # Sisanya mau dipecah lagi

                div $t2, $t2, 10                                                # Memecah 2 digit menjadi 1 digit
                mflo $t2                                                        # Pecahan digit puluhan (hasil)
                mfhi $t3                                                        # Pecahan digit satuan (sisa)

                sw $t2, 0($s0)                                                  # Memasukkan kedalam array
                sw $t3, 0($s1)                                                  # Memasukkan kedalam array

                addi $s0, $s0, 4                                                # Increment pointer array
                addi $s1, $s1, 4                                                # Increment pointer array

                addi $t0, $t0, 1                                                # persiapan iterasi berikutnya
                bne $t0, 5, inputLoop                                           # Condition loop

        sumLoop:

                addi $s0, $s0, -4                                               # Sekarang ada di arrayKosong1[-1]
                addi $s1, $s1, -4                                               # Sekarang ada di arrayKosong2[-1]

                lw $t4, 0($s0)                                                  # Mengeload word yang ada di array
                add $s3, $s3, $t4                                               # Menambahkan ke total mobil

                lw $t4, 0($s1)                                                  # Mengeload word yang ada di array
                add $s4, $s4, $t4                                               # Menambahkan ke total motor

                addi $t0, $t0, -1                                               # Loop counter downwards
                bne $t0, 0, sumLoop                                             # Condition loop

        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, output1                                                         # Load argument string
        syscall                                                                 # Execute

        li $v0, 1                                                               # Load perintah print integer
        addi $a0, $s3, 0                                                        # Load argumen integer
        syscall                                                                 # Execute

        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, newline                                                         # Load argument string
        syscall                                                                 # Execute

        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, output2                                                         # Load argument string
        syscall                                                                 # Execute

        li $v0, 1                                                               # Load perintah print integer
        addi $a0, $s4, 0                                                        # Load argumen integer
        syscall                                                                 # Execute

        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, newline                                                         # Load argument string
        syscall                                                                 # Execute

        j exit

exit:                                                                           # Akhir dari program
        li $v0, 10                                                              # Exit Program
        syscall                                                                 # Execute