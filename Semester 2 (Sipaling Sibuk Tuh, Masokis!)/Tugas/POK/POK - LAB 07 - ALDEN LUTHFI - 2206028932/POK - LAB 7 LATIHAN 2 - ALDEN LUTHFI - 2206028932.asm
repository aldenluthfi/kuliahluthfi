;====================================================================
; Processor		: ATmega8515
; Compiler		: AVRASM
;====================================================================

;====================================================================
; DEFINITIONS
;====================================================================

.include "m8515def.inc"
.def temp = r16 ; temporary register
.def EW = r23 ; for PORTA
.def PB = r24 ; for PORTB
.def A  = r25
.def count = r21

;====================================================================
; RESET and INTERRUPT VECTORS
;====================================================================

.org $00
rjmp MAIN
.org $01
rjmp ext_int0
.org $02
rjmp ext_int1

;====================================================================
; CODE SEGMENT
;====================================================================

ext_int0: ; Call from button interrupt
	rcall INIT_LCD_MAIN
	reti

ext_int1: ; Call from button interrupt
	rcall INIT_LCD_MAIN_2
	reti

MAIN: ; First call

INIT_INTERRUPT: ; Initialize Interrupt on INT0
	ldi temp,0b00001010
	out MCUCR,temp
	ldi temp,0b11000000
	out GICR,temp
	sei

INIT_STACK:
	ldi temp, low(RAMEND)
	ldi temp, high(RAMEND)
	out SPH, temp

rcall INIT_LCD_MAIN

EXIT:
	rjmp EXIT

INPUT_TEXT:
	ldi ZH,high(2*message) ; Load high part of byte address into ZH
	ldi ZL,low(2*message) ; Load low part of byte address into ZL
	ret

INPUT_TEXT_2:
	ldi ZH,high(2*message2) ; Load high part of byte address into ZH
	ldi ZL,low(2*message2) ; Load low part of byte address into ZL
	ret

INIT_LCD_MAIN:
	ser temp
	out DDRA,temp ; Set port A as output
	out DDRB,temp ; Set port B as output

	rcall INIT_LCD

	rcall INPUT_TEXT

WRITE_NPM: ; untuk printing "NPM"
	lpm ; Load byte from program memory into r0

	cpi count, 3 ; Check if we've reached the end for the first line
	breq PAUSE ; If so, change line

	mov A, r0 ; Put the character onto Port B
	rcall WRITE_TEXT
	inc count
	adiw ZL,1 ; Increase Z registers
	rjmp WRITE_NPM

PAUSE:
	rcall DELAY_02
	rcall NEW_LINE ; Untuk new line
	ldi count, 0

NPM_NUM: ; Print nomor npm
	lpm ; Load byte from program memory into r0

	tst r0 ; Check if we've reached the end of the message
	breq LOOP_LCD ; If so, quit

	mov A, r0 ; Put the character onto Port B
	rcall WRITE_TEXT
	adiw ZL,1 ; Increase Z registers
	rjmp NPM_NUM

LOOP_LCD: ; Finish npm printing
	ret

INIT_LCD_MAIN_2:
	ser temp
	out DDRA,temp ; Set port A as output
	out DDRB,temp ; Set port B as output

	rcall INIT_LCD

	rcall INPUT_TEXT_2

WRITE_NAMA: ; untuk printing "NPM"
	lpm ; Load byte from program memory into r0

	cpi count, 4 ; Check if we've reached the end for the first line
	breq PAUSE_2 ; If so, change line

	mov A, r0 ; Put the character onto Port B
	rcall WRITE_TEXT
	inc count
	adiw ZL,1 ; Increase Z registers
	rjmp WRITE_NAMA

PAUSE_2:
	rcall DELAY_02
	rcall NEW_LINE ; Untuk new line
	ldi count, 0

NAMA_STR: ; Print nomor npm
	lpm ; Load byte from program memory into r0

	tst r0 ; Check if we've reached the end of the message
	breq LOOP_LCD_2 ; If so, quit

	mov A, r0 ; Put the character onto Port B
	rcall WRITE_TEXT
	adiw ZL,1 ; Increase Z registers
	rjmp NAMA_STR

LOOP_LCD_2: ; Finish npm printing
	ret

INIT_LCD:
	cbi PORTA,1 ; CLR RS
	ldi PB,0x38 ; MOV DATA,0x38 --> 8bit, 2line, 5x7
	out PORTB,PB
	sbi PORTA,0 ; SETB EN
	cbi PORTA,0 ; CLR EN
	rcall DELAY_01

	cbi PORTA,1 ; CLR RS
	ldi PB,$0E ; MOV DATA,0x0E --> disp ON, cursor ON, blink OFF
	out PORTB,PB
	sbi PORTA,0 ; SETB EN
	cbi PORTA,0 ; CLR EN
	rcall DELAY_01

	rcall CLEAR_LCD ; CLEAR LCD

	cbi PORTA,1 ; CLR RS
	ldi PB,$06 ; MOV DATA,0x06 --> increase cursor, display sroll OFF
	out PORTB,PB
	sbi PORTA,0 ; SETB EN
	cbi PORTA,0 ; CLR EN
	rcall DELAY_01
	ret

CLEAR_LCD:
	cbi PORTA,1 ; CLR RS
	ldi PB,$01 ; MOV DATA,0x01
	out PORTB,PB
	sbi PORTA,0 ; SETB EN
	cbi PORTA,0 ; CLR EN
	rcall DELAY_01
	ret

NEW_LINE:
	cbi PORTA,1 ; Instruction
	ldi PB, $C0 ; set DDRAM address to 64 (second line start point)
	out PORTB,PB
	sbi PORTA,0 ; SETB EN
	cbi PORTA,0 ; CLR EN
	ret

WRITE_TEXT:
	sbi PORTA,1 ; SETB RS
	out PORTB, A
	sbi PORTA,0 ; SETB EN
	cbi PORTA,0 ; CLR EN
	rcall DELAY_01
	ret

;====================================================================
; DELAYS	[ Generated by delay loop calculator at	  ]
; 		[ http://www.bretmulvey.com/avrdelay.html ]
;====================================================================

DELAY_00:				; Delay 4 000 cycles
						; 500us at 8.0 MHz	
	    ldi  r18, 6
	    ldi  r19, 49
	L0: dec  r19
	    brne L0
	    dec  r18
	    brne L0
	ret

DELAY_01:				; DELAY_CONTROL 40 000 cycles
						; 5ms at 8.0 MHz
	    ldi  r18, 52
	    ldi  r19, 242
	L1: dec  r19
	    brne L1
	    dec  r18
	    brne L1
	    nop
	ret

DELAY_02:				; Delay 160 000 cycles
						; 20ms at 8.0 MHz
	    ldi  r18, 208
	    ldi  r19, 202
	L2: dec  r19
	    brne L2
	    dec  r18
	    brne L2
	    nop
	ret

;====================================================================
; DATA
;====================================================================

message:
.db "NPM2206028932", 0

message2:
.db "NAMAUPI", 0