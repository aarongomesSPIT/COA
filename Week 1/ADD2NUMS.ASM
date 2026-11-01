.MODEL SMALL
.STACK 100H

.DATA
    ; Two 8-bit numbers and the byte used to store their sum.
    NUM1 DB 25H
    NUM2 DB 15H
    SUM  DB ?

.CODE
MAIN PROC
    ; Initialize the data segment.
    MOV AX,@DATA
    MOV DS,AX

    MOV AL,NUM1       ; Load the first number.
    ADD AL,NUM2       ; Add the second number.
    MOV SUM,AL        ; Store the result.

    MOV AH,4CH        ; Exit to DOS.
    INT 21H

MAIN ENDP
END MAIN
