         BR      main,i

main:           STRO    sep, d
                STRO    sep, d
                STRO    sep, d

         
                
                STRO    br, d
                LDBA    0xFC15,d 
                STBA    0x800D,d   ;store input at 0x800D
                STBA    0xFC16,d
                 STRO    br, d
                
                


                CPBA    'q',i      ;see if they quit
                BREQ    quit,i
                CPBA    'Q',i
                BREQ    quit,i
                LDBA    0x800D,d
                
                CPBA    'A',i
                BRLT    ns_case,i
                CPBA    'Z',i
                BRLE    c_case,i
                BR      l_case,i
		 











;CHECKS
ns_case:        CPBA    '0',i	;check for num or symbol
                BRLT    s_out,i
                CPBA    '9',i
                BRLE    n_out,i
                BR      s_out,i
                
v_case:         CPBA    'a',i    ; check if vowel
                BREQ    vowel,i
                CPBA    'A',i
                BREQ    vowel,i
                CPBA    'e',i
                BREQ    vowel,i
                CPBA    'E',i
                BREQ    vowel,i
                CPBA    'i',i
                BREQ    vowel,i
                CPBA    'I',i
                BREQ    vowel,i
                CPBA    'o',i
                BREQ    vowel,i
                CPBA    'O',i
                BREQ    vowel,i
                CPBA    'u',i
                BREQ    vowel,i
                CPBA    'U',i
                BREQ    vowel,i
                BR      main,i

l_case:         CPBA     'a',i     ; check lowercase
                BRLT    ns_case,i
                CPBA    'z',i
                BRLE    l_out,i
                BR      ns_case,i

c_case:         STRO    c, d
                STRO    br, d 
                LDBA    0x800D,d
                BR      v_case,i


;OUTPUTS
s_out:          STRO q, d
                STRO    br, d
                 BR   main,i
            
e_out:          STRO e, d
                STRO    br, d

                 BR main,i

o_out:          STRO o, d
                STRO    br, d
                BR main,i

n_out:          STRO n, d
                STRO br, d
                LDBA    0x800D,d
                
                CPBA '0',i
                BREQ main,i
                CPBA '1',i
                BREQ o_out,i
                CPBA '2',i
                BREQ e_out,i
                CPBA '3',i
                BREQ o_out,i
                CPBA '4',i
                BREQ e_out,i
                CPBA '5',i
                BREQ o_out,i
                CPBA '6',i
                BREQ e_out,i
                CPBA '7',i
                BREQ o_out,i
                CPBA '8',i
                BREQ e_out,i
                CPBA '9',i

l_out:          STRO l, d
                STRO    br, d
                BRLE      v_case, i 
                LDBA    0x800D,d
                 BR      main,i


vowel:          STRO    v, d
                STRO    br, d
                BR      main, i
               
       
		 
quit:    STOP
                l: .ascii "L" 
                   STOP 
                q: .ascii "?" 
STOP
                c: .ascii "C" 
STOP
                v: .ascii "V" 
STOP
                sep: .ascii "==" 
STOP
                br: .ascii "\n" 
STOP
                e: .ascii "E" 
STOP 
                o: .ascii "O" 
STOP
                n: .ascii "N" 
STOP
                .end  