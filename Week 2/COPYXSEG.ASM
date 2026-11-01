.MODEL SMALL
.STACK 100H
.CODE
MAIN PROC
    ; Set the source segment to 1000H.
    MOV AX,1000H
    MOV DS,AX
    ; Set the destination segment to 2000H.
    MOV AX,2000H
    MOV ES,AX
    XOR SI,SI          ; Source:      1000:0000
    XOR DI,DI          ; Destination: 2000:0000
    MOV CX,10          ; Copy 10 words (16-bit values).
    CLD                ; Move forward after each word.
    REP MOVSW          ; Copy CX words from DS:SI to ES:DI.
    MOV AH,4CH         ; Exit to DOS.
    INT 21H
MAIN ENDP
END MAIN
