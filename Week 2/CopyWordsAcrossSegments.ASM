.model small
.stack 100h

.code
main proc

    mov ax,1000h
    mov ds,ax

    mov ax,2000h
    mov es,ax

    mov si,0000h
    mov di,0000h
    mov cx,10

L1:
    mov ax,[si]
    inc ax
    mov es:[di],ax
    add si,2
    add di,2
    loop L1

    mov ah,4ch
    int 21h

main endp
end main
