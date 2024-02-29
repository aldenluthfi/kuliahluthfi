.data

charArray: .byte 0, 0, 0, 0, 0, 0, 0, 0, 0, 0

input: .asciiz "Masukkan string berukuran 10: "
output: .asciiz "Hasil: "

.text
.globl main

# $s0 -> charArray
# $t0 -> current char viewed
# $t1 -> loop counter
# $t2 -> Condition 1
# $t3 -> Condition 2

main:
        la $s0, charArray                                                       # Menyimpan address char

        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, input                                                           # Load argument string
        syscall                                                                 # Execute

        li $v0, 8                                                               # Mengeload perintah read string
        la $a0, charArray                                                       # Load argument address array
        li $a1, 12                                                              # Panjang String yang dibaca + 2 buffer
        syscall                                                                 # Execute

        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, output                                                          # Load argument string
        syscall                                                                 # Execute

        firstFive:

                lb $t0, 0($s0)                                                  # Load character berikutnya

                slti $t2, $t0, 91                                               # 90 itu ascii dari Z
                slti $t3, $t0, 65                                               # 65 itu ascii dari A

                beq $t2, $t3, nextIteration1                                    # kalo bukan 65 <= $t0 <= 90

                printChar1:
                        li $v0, 11                                              # Mengeload perintah print string
                        add $a0, $t0, $zero                                     # Load argument string
                        syscall                                                 # Execute

                nextIteration1:
                        addi $s0, $s0, 1                                        # Mempersiapkan address char berikutnya
                        addi $t1, $t1, 1                                        # Increment loop counter
                        blt $t1, 5, firstFive                                   # Branch loop

        lastFive:

                lb $t0, 0($s0)

                slti $t2, $t0, 123                                              # 90 itu ascii dari z
                slti $t3, $t0, 97                                               # 65 itu ascii dari a

                beq $t2, $t3, nextIteration2                                    # kalo bukan 97 <= $t0 <= 122

                printChar2:
                        li $v0, 11                                              # Mengeload perintah print string
                        add $a0, $t0, $zero                                     # Load argument string
                        syscall                                                 # Execute

                nextIteration2:
                        addi $s0, $s0, 1                                        # Mempersiapkan address char berikutnya
                        addi $t1, $t1, 1                                        # Increment loop counter
                        blt $t1, 10, lastFive                                   # Branch loop

exit:                                                                           # Akhir dari program
        li $v0, 10                                                              # Exit Program
        syscall                                                                 # Execute