.MODEL SMALL
.STACK 100H

.DATA
    MSG DB 'Hello World$'       ; '$' terminates a DOS display string.

.CODE
MAIN PROC
    MOV AX,@DATA
    MOV DS,AX                   ; Initialize the data segment.

    LEA DX,MSG                  ; Load the message address.
    MOV AH,09H                  ; DOS function: display a string.
    INT 21H

    MOV AH,4CH                  ; DOS function: exit program.
    INT 21H

MAIN ENDP
END MAIN
