.MODEL SMALL
.STACK 100H
.CODE
MAIN PROC
    ; Source and destination use segment 1000H.
    MOV AX,1000H
    MOV DS,AX
    MOV ES,AX
    MOV SI,0000H       ; Source:      1000:0000
    MOV DI,2000H       ; Destination: 1000:2000
    MOV CX,10          ; Copy 10 words (16-bit values).
    CLD                ; Move forward after each word.
    REP MOVSW          ; Copy CX words from DS:SI to ES:DI.
    MOV AH,4CH         ; Exit to DOS.
    INT 21H
MAIN ENDP
END MAIN
