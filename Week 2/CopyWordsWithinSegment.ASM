.MODEL SMALL
.STACK 100H           

.CODE                 
MAIN PROC             

    ; Use segment 1000H for both source and destination.
    MOV AX, 1000H
    MOV DS, AX
    MOV ES, AX

    ; Copy ten words from offset 0000H to offset 2000H.
    MOV SI, 0000H
    MOV DI, 2000H

    MOV CX, 000AH     ; Ten 16-bit words.
    CLD                ; Advance SI and DI after each word.
    REP MOVSW          ; Copy CX words from DS:SI to ES:DI.
                      

    MOV AH, 4CH       ; DOS terminate-program function.
    INT 21H           

MAIN ENDP             
END MAIN
