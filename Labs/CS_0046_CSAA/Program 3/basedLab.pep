; end my suffering damon @ 12:38 am
; went insane after day 6
BR fortntie

errorfl: .ascii "Invalid character, try again {P, J, G, L}\n\x00"
errorll: .ascii "Invalid character, try again {G, B, S, T}\n\x00"
errorn: .ascii "Number must be positive\n\x00"
errorad: .ascii "Invalid character, try again {A, D}\n\x00"

fp: .ascii " Peter\x00"
fj: .ascii " Joe\x00" 
fg: .ascii " Glen\x00" 
fl: .ascii " Lois\x00" 

lg: .ascii " Griffin\n\x00" 
lb: .ascii " Brown\n\x00" 
ls: .ascii " Swanson\n\x00" 
lt: .ascii " Tucker\n\x00" 

num:     .block 2
num2:    .block 2

fortntie:    LDBA 0xFC15,d
         CPBA '\n',i
         BREQ fortntie,i
         CPBA 'P',i
         BREQ savef,i
         CPBA 'J',i
         BREQ savef,i 
         CPBA 'G',i
         BREQ savef,i 
         CPBA 'L',i
         BREQ savef,i
         STRO errorfl,d
         BR fortntie,i

; end my suffering damon @ 11:38 pm
savef:   STBA 0xAAAA,d

last:    LDBA 0xFC15,d
         CPBA '\n',i
         BREQ last,i
         CPBA 'G',i
         BREQ savel,i
         CPBA 'B',i
         BREQ savel,i
         CPBA 'S',i
         BREQ savel,i
         CPBA 'T',i
         BREQ savel,i
         STRO errorll,d
         BR last,i

savel:   STBA 0xBBBB,d 

getnum:  DECI num,d
         LDWA num,d
         CPWA 0,i
         BRGT ad,i
         STRO errorn,d 
         BR getnum,i

ad:      LDBA 0xFC15,d
         CPBA '\n',i
         BREQ ad,i
         CPBA 'A',i
         BREQ savea,i
         CPBA 'D',i
         BREQ saved,i
         STRO errorad,d
         BR ad,i

;i farted and it smelled @ 12:51 am

saved:   STBA 0xCCCC,d
         BR d,i

savea:   STBA 0xCCCC,d
         LDWA 1,i
         STWA num2,d

; roblox > fortnite


a:       LDWA num2,d
         DECO num2,d
         ADDA 1,i
         STWA num2,d
         LDBA 0xAAAA,d
         CPBA 'P',i
         BREQ outfp,i
         CPBA 'J',i
         BREQ outfj,i
         CPBA 'G',i
         BREQ outfg,i
         BR outfl,i

d:       LDWA num,d
         DECO num,d
         SUBA 1,i
         STWA num,d
         LDBA 0xAAAA,d
         CPBA 'P',i
         BREQ outfp,i
         CPBA 'J',i
         BREQ outfj,i
         CPBA 'G',i
         BREQ outfg,i
         BR outfl,i

outfp:   STRO fp,d
         BR cout,i
outfj:   STRO fj,d
         BR cout,i
outfg:   STRO fg,d
         BR cout,i
outfl:   STRO fl,d

cout:    LDBA 0xBBBB,d
         CPBA 'G',i
         BREQ outlg,i
         CPBA 'B',i
         BREQ outlb,i
         CPBA 'S',i
         BREQ outls,i
         BR outlt,i

outlg:   STRO lg,d
         BR cend,i
outlb:   STRO lb,d
         BR cend,i
outls:   STRO ls,d
         BR cend,i
outlt:   STRO lt,d

cend:    LDBA 0xCCCC,d
         CPBA 'A',i
         BREQ endofa,i

endofd:  LDWA num,d
         CPWA 0,i
         BREQ roblox,i
         BR d,i

endofa:  LDWA num2,d
         CPWA num,d
         BRLE a,i
         BR roblox,i
; i love pep9!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

roblox:  STOP
         .end


         