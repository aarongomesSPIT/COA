.MODEL SMALL
.STACK 100H

.DATA
    ; DOS display strings end with '$'.
    STRING1  DB 'HELLO$'
    LEN1     EQU $ - STRING1 - 1
    STRING2  DB 'HELLO$'
    EQUALMSG DB 'Strings are equal.$'
    NEQMSG   DB 'Strings are NOT equal.$'

.CODE
MAIN PROC
    ; Initialize both segments for CMPSB.
    MOV AX,@DATA
    MOV DS,AX
    MOV ES,AX
    LEA SI,STRING1     ; SI points to the first string.
    LEA DI,STRING2     ; DI points to the second string.
    MOV CX,LEN1        ; Compare five characters.
    CLD                ; Compare from left to right.
    REPE CMPSB         ; Stop when a character differs.
    JNE NEQUAL         ; Jump if the strings are different.
    LEA DX,EQUALMSG    ; Select the equal message.
    JMP SHOW
NEQUAL:
    LEA DX,NEQMSG      ; Select the different message.
SHOW:
    MOV AH,09H         ; DOS display-string function.
    INT 21H
    MOV AH,4CH         ; Exit to DOS.
    INT 21H
MAIN ENDP
END MAIN
