.MODEL SMALL
.STACK 100H

.DATA
    STRING1 DB 'HELLO$'
    LEN1    EQU $ - STRING1 - 1     ; Moved here! Now LEN1 correctly equals 5
    STRING2 DB 'HELLO$'
    
    MSG_EQUAL    DB 'Strings are equal.$'
    MSG_NOTEQUAL DB 'Strings are NOT equal.$'

.CODE
MAIN PROC
    MOV AX, @DATA
    MOV DS, AX          ; Load Data Segment
    MOV ES, AX          ; Load Extra Segment

    LEA SI, STRING1     ; SI points to source string
    LEA DI, STRING2     ; DI points to destination string
    MOV CX, LEN1        ; CX is now correctly set to 5

    CLD                 ; Clear direction flag
    
    REPE CMPSB          ; Compares 5 bytes ('H','E','L','L','O')
    JNE NOT_EQUAL       ; Jumps only if bytes differ

EQUAL:
    LEA DX, MSG_EQUAL
    JMP DISPLAY

NOT_EQUAL:
    LEA DX, MSG_NOTEQUAL

DISPLAY:
    MOV AH, 09H
    INT 21H

    MOV AH, 4CH
    INT 21H
MAIN ENDP
END MAIN
