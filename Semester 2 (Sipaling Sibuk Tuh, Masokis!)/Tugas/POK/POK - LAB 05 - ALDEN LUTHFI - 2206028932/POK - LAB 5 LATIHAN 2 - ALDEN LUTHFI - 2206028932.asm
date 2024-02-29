.include "m8515def.inc"
.def ab = r0
.def a = r4
.def b = r5
.def temp = r6
.def gcd = r7
.def result = r16

main:
ldi ZH, HIGH(DATA*2)
ldi ZL, LOW(DATA*2)

lpm a, Z+			; mengeload nilai a
lpm b, Z			; mengeload nilai b

mul a, b			; menyimpan hasil perkalian ke ab (r0), sebenernay r1 juga tapi ab < 127

swaps:
	cp a, b			; swap jika a < b
	brlt swapping
	rjmp euclidean		; langsung menghitung gcd
	
	swapping:
		mov temp, a	; menukar a denan b jika a < b
		mov a, b
		mov b, temp

euclidean:
	tst b
	breq gcdAssign		; if b == 0 return a

	sub a, b		; gcd(a, b) == gcd(a, a-b) pake mod muter kepala lagi

	rjmp swaps

gcdAssign:
	mov gcd, a		; memindahkan gcd

division:			; MASA GAADA DIVISION SIH PAYAH BGT
	tst ab
	breq forever

	sub ab, gcd
	inc result

	rjmp division
	
forever:
	rjmp forever


DATA:
.db 8, 12			; array data
.db 0, 0
