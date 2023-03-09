.data
pesan1: .asciiz "\nSelamat datang pada awal perjalananmu, petualang!\n"         # Pesan selamat datang
pesan2: .asciiz "Sebelum memulai petualanganmu, silahkan perkenalkan dirimu terlebih dahulu\n" # Pesan selamat datang

input1: .asciiz "Masukkan NPM kamu: "                                           # Prompt input NPM
input2: .asciiz "Masukkan SKS yang kamu ambil: "                                # Prompt input SKS

output1: .asciiz "Halo petualang dengan NPM "                                   # String output
output2: .asciiz ". Semoga dengan mengambil "                                   # String output
output3: .asciiz " SKS anda bisa menyelesaikan petualangan ini dengan baik!"    # String output
output4: .asciiz "Berbohong itu tidak baik!"

.text                                                                           # NPM->$s0, SKS->$s1

main:                                                                           # program utama

        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, pesan1                                                          # Load argument string
        syscall                                                                 # Execute

        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, pesan2                                                          # Load argument string
        syscall                                                                 # Execute

        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, input1                                                          # Load argument string
        syscall                                                                 # Execute

        li $v0, 5                                                               # Mengeload perintah input integer
        syscall                                                                 # Execute

        ori $s0, $v0, 0                                                         # menyimpan variabel NPM ke $s0

        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, input2                                                          # Load argument string
        syscall                                                                 # Execute

        li $v0, 5                                                               # Mengeload perintah input integer
        syscall                                                                 # Execute

        ori $s1, $v0, 0                                                         # menyimpan variabel SKS ke $s1
        ori $t1, $zero, 24

        bgt $s1, $t1, salah                                                     # Kalau variabel sks > 24 jump ke salah
        j benar                                                                 # Selain itu jump ke benar

salah:                                                                          # Masih salah jadi petualangan belum selesai

        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, output4                                                         # Load argument string
        syscall                                                                 # Execute

        j main                                                                  # Keluar dari program

benar:                                                                          #kalau sudah benar maka petualangan selesai
        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, output1                                                         # Load argument string
        syscall                                                                 # Execute

        li $v0, 1                                                               # Load perintah print integer
        ori $a0, $s0, 0                                                         # Load argumen integer
        syscall                                                                 # Execute

        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, output2                                                         # Load argument string
        syscall                                                                 # Execute

        li $v0, 1                                                               # Load perintah print integer
        ori $a0, $s1, 0                                                         # Load argumen integer
        syscall                                                                 # Execute

        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, output3                                                         # Load argument string
        syscall                                                                 # Execute

        j exit                                                                  # Keluar dari program

exit:                                                                           # Akhir dari program
        li $v0, 10                                                              # Exit Program
        syscall                                                                 # Execute