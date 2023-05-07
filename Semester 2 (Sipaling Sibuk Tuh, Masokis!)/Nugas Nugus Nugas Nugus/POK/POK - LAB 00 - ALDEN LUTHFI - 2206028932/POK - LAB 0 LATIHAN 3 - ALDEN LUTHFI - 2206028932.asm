.data
pesan1: .asciiz "\nSelamat datang pada awal perjalananmu, petualang!\n"         # Pesan selamat datang
pesan2: .asciiz "Sebelum memulai petualanganmu, silahkan perkenalkan dirimu terlebih dahulu\n" # Pesan selamat datang

input1: .asciiz "Masukkan NPM kamu: "                                           # Prompt input NPM
input2: .asciiz "Masukkan jumlah mata kuliah yang kamu ambil: "                 # Prompt input Mata kuliah
input3: .asciiz "Masukkan sks mata pelajaran "                                  # Prompt input SKS

output1: .asciiz "Halo petualang dengan NPM "                                   # String output
output2: .asciiz ". Hebat! Anda mengambil "                                     # String output
output3: .asciiz " mata kuliah dengan total "                                   # String output
output4: .asciiz " SKS."                                                        # String output

.text

# NPM -> $s0, Mata Kuliah -> $s1, KumulatifSKS -> $t0, Countdown Matkul -> $t1
main:

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

        ori $s1, $v0, 0                                                         # Menyimpan variabel mata kuliah ke $s1
        addi $t1, $s1, 0                                                        # Menyimpan variabel untuk loop

        j loop                                                                  # Mulai loop

loop:                                                                           # Loop setiap matkul

        subi $t1, $t1, 1                                                        # Decrement loop

        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, input3                                                          # Load argument string
        syscall                                                                 # Execute

        li $v0, 1                                                               # Load perintah print integer
        sub $a0, $s1, $t1                                                       # Load argumen integer
        syscall                                                                 # Execute

        li $v0, 11                                                              # Mengeload perintah print character
        li $a0, 58                                                              # Load argument character
        syscall                                                                 # Execute

        li $v0, 11                                                              # Mengeload perintah print character
        li $a0, 32                                                              # Load argument character
        syscall                                                                 # Execute

        li $v0, 5                                                               # Mengeload perintah input integer
        syscall                                                                 # Execute

        add $t0, $t0, $v0                                                       # Menambah SKS

        beq $t1, 0, output                                                      # kalo sudah selesai loopnya (countdown = 0)
        j loop                                                                  # selain itu loop lagi

output:                                                                         # Output dari program
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

        li $v0, 1                                                               # Load perintah print integer
        ori $a0, $t0, 0                                                         # Load argumen integer
        syscall                                                                 # Execute

        li $v0, 4                                                               # Mengeload perintah print string
        la $a0, output4                                                         # Load argument string
        syscall                                                                 # Execute

        j exit                                                                  # Keluar dari program

exit:                                                                           # Akhir dari program
        li $v0, 10                                                              # Exit Program
        syscall                                                                 # Execute