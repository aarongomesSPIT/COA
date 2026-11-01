.MODEL SMALL
.STACK 100H

.DATA
    STR1 DB 'HELLO$'              ; String to reverse.
    LEN  EQU $ - STR1 - 1         ; Length without '$'.
    REV  DB LEN DUP (?)            ; Space for the reversed string.
    DB '$'                        ; DOS string terminator.

.CODE
MAIN PROC
    MOV AX,@DATA
    MOV DS,AX

    LEA SI,STR1+LEN-1             ; Read the source from the end.
    LEA DI,REV                     ; Write from the beginning.
    MOV CX,LEN                     ; Number of characters.
    CLD

COPY:
    MOV AL,[SI]                    ; Read one character.
    MOV [DI],AL                    ; Store it in the reversed string.
    DEC SI
    INC DI
    LOOP COPY

    LEA DX,REV                     ; Display the reversed string.
    MOV AH,09H
    INT 21H
    MOV AH,4CH
    INT 21H

MAIN ENDP
END MAIN
