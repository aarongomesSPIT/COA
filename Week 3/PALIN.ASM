.MODEL SMALL
.STACK 100H

.DATA
    STR1 DB 'MADAM$'             ; String to check.
    LEN  EQU $ - STR1 - 1        ; Length without '$'.
    YES  DB 'Palindrome.$'
    NO   DB 'Not a palindrome.$'

.CODE
MAIN PROC
    MOV AX,@DATA
    MOV DS,AX
    MOV ES,AX

    LEA SI,STR1                   ; SI points to the first character.
    LEA DI,STR1+LEN-1             ; DI points to the last character.
    MOV CX,LEN/2                  ; Only half the string must be checked.
    CLD

CHECK:
    MOV AL,[SI]                   ; Read from the beginning.
    CMP AL,ES:[DI]                ; Compare with the end.
    JNE DIFFERENT
    INC SI
    DEC DI
    LOOP CHECK

    LEA DX,YES                     ; All pairs matched.
    JMP SHOW

DIFFERENT:
    LEA DX,NO                      ; A pair did not match.

SHOW:
    MOV AH,09H
    INT 21H
    MOV AH,4CH
    INT 21H

MAIN ENDP
END MAIN
