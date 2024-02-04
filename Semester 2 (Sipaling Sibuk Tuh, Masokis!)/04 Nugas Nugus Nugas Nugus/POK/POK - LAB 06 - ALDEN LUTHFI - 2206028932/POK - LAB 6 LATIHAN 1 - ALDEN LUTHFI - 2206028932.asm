.include m8515def.inc
.def tempi = r2									;register definitions																; throwaway variable
.def aN = r3
.def aNN = r4
.def result = r19
.def tempN = r6
.def n = r16
.def rtwo = r17
.def counter = r18

ldi YL, low(RAMEND)
out SPL, YL
ldi YL, high(RAMEND)
out SPH, YL

ldi XH, $00									; sets X to the beginning of the array
ldi XL, $60

ldi n, 5									; loads the initial values
ldi rtwo, 2
ldi counter, 1

loop
	mov tempN, n
 	mov n, counter   							; pindahkan counter ke n
 	rcall pekSec    							; cari P(n)
	mov n, tempN

savemem
 	st X+, result								; menyimpan ke memory
 	cp counter, n
 	breq forever
 	inc counter
 	rjmp loop

forever
	rjmp forever

pekSec
	push n									; push ke stack
	push aN
	push aNN
	push tempi

baseCase
	cpi n, 1
	breq one
	cpi n, 2
	breq two

recCase

	dec n
	mov aN, n
	dec n
	mov aNN, n

	mov n, aN								; cari P(n-1)
	rcall pekSec
	mov tempi, result
	inc tempi									; tambah 1

	mov n, aNN								; cari P(n-2)
	rcall pekSec
	mul result, rtwo							; kali 2
	mov result, r0
	add result, tempi								; tambahkan keduanya

	rjmp done

one										; base case pertama
	ldi result, 1
	rjmp done

two										; base case kedua
	ldi result, 2

done
	pop tempi									; pop dari stack
	pop aNN
	pop aN
	pop n
	ret



