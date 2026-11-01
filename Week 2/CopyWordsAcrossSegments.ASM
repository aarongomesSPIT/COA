.model small
.stack 100h

.code
main proc

    ; Source segment is 1000H.
    mov ax,1000h
    mov ds,ax

    ; Destination segment is 2000H.
    mov ax,2000h
    mov es,ax

    mov si,0000h
    mov di,0000h
    mov cx,10          ; Copy ten words.

L1:
    mov ax,[si]        ; Read one word from 1000:SI.
    mov es:[di],ax     ; Write the word to 2000:DI.
    add si,2           ; Move to the next source word.
    add di,2           ; Move to the next destination word.
    loop L1            ; Repeat until all ten words are processed.

    mov ah,4ch         ; DOS terminate-program function.
    int 21h

main endp
end main
