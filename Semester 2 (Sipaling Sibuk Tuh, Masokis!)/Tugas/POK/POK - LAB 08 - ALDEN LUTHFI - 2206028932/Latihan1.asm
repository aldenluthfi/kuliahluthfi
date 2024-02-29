;====================================================================
; Processor		: ATmega8515
; Compiler		: AVRASM
;====================================================================

;====================================================================
; DEFINITIONS
;====================================================================

.include "m8515def.inc"

;====================================================================
; RESET and INTERRUPT VECTORS
;====================================================================

.org $00 	; JUMP to MAIN to initialze
rjmp MAIN
.org $01	; When Button0 pressed, jump to BUTTON_PRESS
rjmp BUTTON_PRESS1
.org $02	; When Button0 pressed, jump to BUTTON_PRESS
rjmp BUTTON_PRESS2
.org $06	; When Timer0 overflows, jump to OVERFLOW
rjmp OVERFLOW

; For determining LED cycle
.def CYCLE = r17

; For toggling speed and modes
.def TOGGLE_S = r18
.def TOGGLE_M = r19
.def TOGGLE = r20

;====================================================================
; CODE SEGMENT
;====================================================================

; Initialize stack pointer
MAIN:
	ldi r16, low(RAMEND)
	out SPL, r16
	ldi r16, high(RAMEND)
	out SPH, r16

	ldi TOGGLE_S, (1<<CS00)
	ldi TOGGLE_M, 1

; Initialize Interrupt on INT0
INIT_INTERRUPT:
	;Falling edge trigger
	ldi r16, 0b00001010
	out MCUCR, r16
	
	;INT0 enable
	ldi r16, 0b11000000
	out GICR, r16

; Setup LED PORT
INIT_LED:
	ser r16			; Load $FF to temp		
	out DDRB, r16	; Set PORTB to output	

; Setup Overflow Timer0
INIT_TIMER:
	; Timer speed = clock/64 (set CS01 and CS00 in TCCR1B) overflow about once every 2-3 seconds on 4MHz
	ldi r16, (1<<CS01) | (1<<CS00)
	out TCCR1B, r16

	; Execute an internal interrupt when Timer1 overflows
	ldi r16, (1<<TOV1)
	out TIFR, r16

	; Set Timer1 overflow as the timer
	ldi r16, (1<<TOIE1)
	out TIMSK, r16

	; Set global interrupt flag
	sei

; While waiting for interrupt, loop infinitely
FOREVER:

	rjmp FOREVER

; Program executed on button press
BUTTON_PRESS1:
	push r16
	in r16, SREG
	push r16

	clr r16
	in r16, TCCR1B
	eor r16, TOGGLE_S
	out TCCR1B, r16

	pop r16
	out SREG, r16
	pop r16

	reti

; Program executed on button press
BUTTON_PRESS2:
	push r16
	in r16, SREG
	push r16

	ser r16
	out PORTB, r16
	eor TOGGLE, TOGGLE_M

	pop r16
	out SREG, r16
	pop r16

	reti

; Program executed on timer overflow
OVERFLOW:
	push r16
	in r16, SREG
	push r16

	rcall CHANGE_STATE
	out PORTB, r16
	
	pop r16
	out SREG, r16
	pop r16

	reti

;Merubah lampu
CHANGE_STATE:
	in r16, PORTB

	cpi TOGGLE, 1
	breq COMPLEMENT

	cpi CYCLE, 1
	breq ONING

;Fase Mematikan LED
OFFING:
	cpi r16, 0b11111111
	breq OFF_BLUE

	cpi r16, 0b11100111
	breq OFF_YELLOW

	cpi r16, 0b11000011
	breq OFF_GREEN

; Fase Menyalakan LED
ONING:
	cpi r16, 0b10000001
	breq ON_GREEN

	cpi r16, 0b11000011
	breq ON_YELLOW

	cpi r16, 0b11100111
	breq ON_BLUE

; Mematikan LED Biru
OFF_BLUE:
	ldi r16, 0b11100111
	ret

; Menyalakan LED Biru
ON_BLUE:
	ldi r16, 0b11111111
	clr CYCLE
	ret

; Mematikan LED Kuning
OFF_YELLOW:
	ldi r16, 0b11000011
	ret

; Menyalakan LED Kuning
ON_YELLOW:
	ldi r16, 0b11100111
	ret

; Mematikan LED Hijau
OFF_GREEN:
	ldi r16, 0b10000001
	ldi CYCLE, 1
	ret

; Menyalakan LED Hijau
ON_GREEN:
	ldi r16, 0b11000011
	ret

COMPLEMENT:
	cpi r16, 0b11111111
	breq SET_MODE

	com r16
	
	ret

SET_MODE:
	clr r16
	ldi r16, 0b10101010

	ret
	
