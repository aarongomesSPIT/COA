.MODEL SMALL
.STACK 100H

.DATA
    ; Two 8-bit operands and one byte for the sum.
    NUM1 DB 25H
    NUM2 DB 15H
    SUM  DB ?

.CODE
MAIN PROC
    ; Initialize DS so data labels can be addressed.
    MOV AX, @DATA
    MOV DS, AX

    MOV AL, NUM1      ; Load the first operand into AL.
    ADD AL, NUM2      ; Add the second operand to AL.
    MOV SUM, AL       ; Store the 8-bit result.

    MOV AH, 4CH       ; DOS terminate-program function.
    INT 21H

MAIN ENDP
END MAIN
