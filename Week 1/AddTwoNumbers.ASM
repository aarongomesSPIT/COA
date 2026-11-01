.MODEL SMALL
.STACK 100H

.DATA
    NUM1 DB 25H
    NUM2 DB 15H
    SUM  DB ?

.CODE
MAIN PROC
    MOV AX, @DATA
    MOV DS, AX

    MOV AL, NUM1      ; Load first 8-bit number
    ADD AL, NUM2      ; Add second 8-bit number
    MOV SUM, AL       ; Store the result

    MOV AH, 4CH       ; Exit to DOS
    INT 21H

MAIN ENDP
END MAIN
