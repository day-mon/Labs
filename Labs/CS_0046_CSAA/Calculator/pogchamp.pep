br main

a:       .ascii "+\x00"
s:       .ascii "-\x00"
m:       .ascii "*\x00"
d:       .ascii "/\x00"
space:   .ascii " \x00" 
equal:   .ascii " = \x00"
error:   .ascii "error\x00"
bar:     .ascii "-----------\x00"
lf:      .ascii "\n\x00"

main:    ldba 0xfc15,d
         cpba ' ',i
         breq main,i
         cpba 'q',i
         breq stopp,i
         cpba 'Q',i
         breq stopp,i
         cpba '+',i
         breq adding,i
         cpba '-',i
         breq subing,i
         cpba '*',i
         breq muling,i
         cpba '/',i
         breq diving,i
         br main,i

adding:  stro a,d
         stro space,d
         call add,i
         stro lf,d
         stro bar,d
         stro lf,d
         br main,i

subing:  stro s,d
         stro space,d
         call subtr,i
         stro lf,d
         stro bar,d
         stro lf,d
         br main,i 

muling:  stro m,d
         stro space,d
         ldba 0,i
         stba 0x6969,d
         stba 0x6970,d
         call multi,i
         stro lf,d
         stro bar,d
         stro lf,d
         br main,i 

diving:  stro d,d
         stro space,d
         ldba 0,i
         stba 0x6969,d
         stba 0x6970,d
         call divi,i
         stro lf,d
         stro bar,d
         stro lf,d
         br main,i 

add:     deci 2,s
         deco 2,s
         stro space,d
         ldwa 2,s
         deci 4,s
         deco 4,s
         stro equal,d
         adda 4,s
         stwa 6,s
         deco 6,s
         ret

subtr:   deci 2,s
         deco 2,s
         stro space,d
         ldwa 2,s
         deci 4,s
         deco 4,s
         stro equal,d
         suba 4,s
         stwa 6,s
         deco 6,s
         ret 

multi:   deci 2,s
         deco 2,s
         stro space,d
         ldwa 2,s
         deci 4,s
         deco 4,s
         stro equal,d
         ldwx 4,s
         cpwa 0,i
         breq print0,i
         brgt flipxm
         nega
         stwa 2,s
         ldba 1,i
         stba 0x6969,d
         ldwa 2,s
flipxm:  cpwx 0,i
         breq print0,i
         brgt calcm,i
         negx
         stwx 4,s
         ldbx 1,i
         stbx 0x6970,d
         ldwx 4,s
calcm:   subx 1,i
         cpwx 0,i
         brle printm,i
         adda 2,s
         brv printer,i
         br calcm,i
printm:  stwa 6,s
         ldba 0x6969,d
         cpba 1,i
         brlt mx,i
         ldwa 6,s
         nega
         stwa 6,s
mx:      ldba 0x6970,d
         cpba 1,i
         brlt my,i
         ldwa 6,s
         nega
         stwa 6,s
my:      deco 6,s
         ret

divi:    deci 2,s
         deco 2,s
         stro space,d
         ldwa 2,s
         deci 4,s
         deco 4,s
         stro equal,d
         ldwx 4,s
         cpwa 0,i
         breq print0,i
         brgt flipxd,i
         nega
         stwa 2,s
         ldba 1,i
         stba 0x6969,d
         ldwa 2,s
flipxd:  cpwa 4,s
         brlt print0,i
         cpwx 0,i
         breq print0,i
         brgt calcds0,i
         negx
         stwx 4,s
         ldbx 1,i
         stbx 0x6970,d
calcds0: ldwx 0,i 
calcd:   suba 4,s
         addx 1,i
         cpwa 4,s
         brlt printd,i
         br calcd,i
printd:  stwx 6,s
         ldba 0x6969,d
         cpba 1,i
         brlt dx,i
         ldwx 6,s
         negx
         stwx 6,s
dx:      ldba 0x6970,d
         cpba 1,i
         brlt dy,i
         ldwx 6,s
         negx
         stwx 6,s
dy:      deco 6,s
         ret

print0:  ldwa 0,i
         stwa 6,s
         deco 6,s
         ret

printer: stro error,d
         ret

stopp:   stba 0xfc16,d
         stop
         .end
         