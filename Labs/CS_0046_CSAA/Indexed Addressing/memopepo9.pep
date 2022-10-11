BR      bigL, i

letters:   .BLOCK  42
fib:       .BLOCK  069
br:        .ASCII  "\n\x00"  
br2:  .ASCII  "\n\x00"

    

bigL:    LDBA    'A',i
         LDWX    0, i

smallL:  STBA    letters,x
         ;STRO    br, d
         ;STBA    0xFC16, d
         ;STRO    br, d
         
         LDBA    letters, x
         ADDA    32, i
         ADDX    1,  i
         STBA    letters, x
         ;STBA    0xFC16, d
         LDBA    br, d
         LDBA    letters, x
         CPBA    'z', i ; stops at lower case letter i
         BRGE    reverse, i 
         SUBA    31, i       ; 
         ADDX    1, i    ; this will make it go back to next captial
         BR      smallL, i ; will goto top to do small and big letters
         

reverse:    LDBA letters, x ; starting from the back and counting down like counting down in an array
            STBA 0xFC16, d ; printing values
            STRO br, d
            CPBX 0,i
            BRLE fib1, i
            SUBX 1, i
            br reverse, i     

fib1:       LDWA    0, i     
            LDWX    0, i
            STWA    fib, x
            LDWA    1, i
            ADDX    2, i
            STWA    fib, x
            ADDX    2, i

fibloop:    ADDX    -4, i
            LDWA    fib, x
            ADDX    2, i
            ADDA    fib, x
            ADDX    2, i
            STWA    fib, x 
            CPWX    46, i ; used to be 46
            BREQ    fibout 
            ADDX    2, i
            BR      fibloop

         
fibout: STRO    br2, d
         DECO    fib, x
         SUBX    2, i
         CPWX    -2, i
         BREQ    stop, i
         BR      fibout
     
stop:  .END 
        
