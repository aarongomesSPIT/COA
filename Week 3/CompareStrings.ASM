.MODEL SMALL
.STACK 100H

.DATA
    ; '$' terminates each DOS display string.
    STRING1 DB 'HELLO$'
    LEN1    EQU $ - STRING1 - 1     ; Moved here! Now LEN1 correctly equals 5
    STRING2 DB 'HELLO$'
    
    MSG_EQUAL    DB 'Strings are equal.$'
    MSG_NOTEQUAL DB 'Strings are NOT equal.$'

.CODE
MAIN PROC
    ; Initialize data and extra segments.
    MOV AX, @DATA
    MOV DS, AX          ; Load Data Segment
    MOV ES, AX          ; Load Extra Segment

    LEA SI, STRING1     ; SI points to the first string.
    LEA DI, STRING2     ; DI points to the second string.
    MOV CX, LEN1        ; Compare all five characters.

    CLD                 ; Clear direction flag
    
    REPE CMPSB          ; Compare bytes while they remain equal.
    JNE NOT_EQUAL       ; A mismatch means the strings differ.

EQUAL:
    LEA DX, MSG_EQUAL
    JMP DISPLAY

NOT_EQUAL:
    LEA DX, MSG_NOTEQUAL

DISPLAY:
    MOV AH, 09H         ; DOS display-string function.
    INT 21H

    MOV AH, 4CH         ; DOS terminate-program function.
    INT 21H
MAIN ENDP
END MAIN
