.MODEL SMALL
.STACK 100H

.DATA
    ; The string ends with '$', the DOS string terminator.
    STR1 DB "Hello World$"       ; The target string terminated with '$'
    LEN  DB 0                   ; Memory variable to store the final length

.CODE
MAIN PROC
    ; Initialize the data segment.
    MOV AX, @DATA
    MOV DS, AX

    ; Point SI to the first character and clear the counter.
    LEA SI, STR1                ; SI points to the start of the string
    XOR CX, CX                  ; Clear CX register (Counter = 0)

COUNT_LOOP:
    MOV AL, [SI]                ; Move the current character into AL
    CMP AL, '$'                 ; Check if it is the terminator character
    JE DONE_LOOP                ; If '$' is found, jump out of the loop
    
    INC CX                      ; Increment the character count
    INC SI                      ; Move to the next memory address
    JMP COUNT_LOOP              ; Repeat for the next character

DONE_LOOP:
    MOV LEN, CL                 ; Save the lower byte of the count into LEN

    ; Return control to DOS.
    MOV AH, 4CH
    INT 21H
MAIN ENDP
END MAIN
