.MODEL SMALL
.STACK 100H

.DATA
    STR1 DB "Hello World$" ; '$' marks the end of the string.
    LEN  DB 0              ; Stores the final length.

.CODE
MAIN PROC
    ; Initialize the data segment and counter.
    MOV AX,@DATA
    MOV DS,AX
    LEA SI,STR1      ; SI points to the current character.
    XOR CX,CX        ; CX counts the characters.
L1:
    LODSB            ; Read one character and advance SI.
    CMP AL,'$'       ; Is it the string terminator?
    JE DONE
    INC CX            ; Count ordinary characters.
    JMP L1
DONE:
    MOV LEN,CL        ; Save the length.
    MOV AH,4CH        ; Exit to DOS.
    INT 21H
MAIN ENDP
END MAIN
