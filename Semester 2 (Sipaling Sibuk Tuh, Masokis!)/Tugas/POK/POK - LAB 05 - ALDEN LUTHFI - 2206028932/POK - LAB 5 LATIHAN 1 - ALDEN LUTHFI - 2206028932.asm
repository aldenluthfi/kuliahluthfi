.include "m8515def.inc"
.def result = r2
main:
ldi ZH, HIGH(2*DATA)
ldi ZL, LOW(2*DATA)

loop:
	lpm				;Menyalin isi Z ke r0
	tst r0
	breq stop			;branch jika flag zero = 1 
	mov r16, r0

funct1:
	cpi r16, 3			;mengecek apakah r16 < 3
	brlt funct2			;kalau lebih kecil maka ke funct 2
	subi r16, 3			;selain itu kurangin 3 (kayak basically operasi modulo)
	rjmp funct1

funct2:
	add r1, r16			;menambahkan isi r16 ke r1
	adiw ZL, 1			;increment pointer array
	rjmp loop

stop:
	mov result, R1			;menyalin apapun isi r1 ke result

forever:
	rjmp forever

DATA:
	.db 2, 11, 7, 8			;array data
	.db 0, 0

;intinya sumasi dari x mod 3 untuk semua x di array
