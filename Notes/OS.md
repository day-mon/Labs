# Operating System Notes

# Paging
## Geting Page Offset
- `0x00000FFF` is the mask for the offset
- `0x00000FFF & 0x12345678` will give you the offset
- `0x12345678 & 0xFFFFF000` will give you the page number

- Given this example with paging
  - 64 kB pages
    - Page size is ( 2^16 ) / 1024
    - Formula is (2 ^ X) / 1024 where X is the number of bits in the page size
    - Just use powers of 2 for page sizes



[comment]: <> (Write a table in markdown 2 x 2)

| Page Size                     | Number of Bits |
|-------------------------------|----------------|
| 4 kB                          | 12             |
| 8 kB                          | 13             |
| 16 kB                         | 14             |
| 32 kB                         | 15             |
| 64 kB                         | 16             |
| 128 kB                        | 17             |
| 256 kB                        | 18             |
| 512 kB                        | 19             |
| 1 MB                          | 20             |
| 2 MB                          | 21             |
| 4 MB                          | 22             |
| 8 MB                          | 23             |


| Virtual Address | Physical Address |
|-----------------|------------------|
| 0x0000          | **DISK**         |
| 0x001           | 0x003            |
| 0x002           | 0x004            |
| 0x003           | 0x005            |
| 0x004           | 0x006            |
| 0xffff          | 0x0f6            |



## Page faults
##### What happens when the data is not in RAM

( This process takes a lot of time ) 
- Page Table Entry says the page in on **disk**  (**Takes about 1 CPU cycle**)
  - CPU will generate a page fault exception (**Takes about 100 CPU cycles**)
  - The hardware jumps to the page fault handler to clean up the mess (**Takes about 10,000 CPU cycles**)
    - OS will choose a page to evict from RAM and write to disk (basically a swap) (**Takes about 40,000,000 CPU cycles**)
    - If the page is dirty, it will be written to disk (**Takes about 40,000,000 CPU cycles**)
      - Dirty means the page has been modified since it was loaded into RAM 
    - The OS then changes the PTE to point to the new location on disk and will map the other page into RAM (**Takes about 1,000 CPU cycles**)
  - The OS jumps back to the instruction the page fault  (**Takes about 10,000 CPU cycles**)

### **In the time it takes to do all of this you can execute ~80,0000,000 cycles on a modern CPU**
### **It is actually faster to load the data over a network than to do all of this**
### **Because this is really slow the OS usually does another job while this is happening**

- Paging is one of those thing one of those things that you don't ever want to use but it's good to have around:
  - Very, very slow when generate a page fault aka when the data is not in RAM
  - Very, very good if you dont crash because you run out of memory
- If you have enough RAM you will **NEVER** generate a page fault


# Memory Protection
- **Memory Protection** is a way to protect the memory of a process from other processes

## Program address space in Linux
- Each program has its on 32-bit address space
- Linux defines how that space is used
- The address space has 7 regions
  - **Text** - The code of the program
  - **Data** - The initialized data of the program
  - **Heap** - The dynamic memory of the program
    - The heap is where the program can request memory at run time and grows up 
  - **Stack** - The stack of the program
    - The stack is where the function calls are stored and grows down and also has a maximum fixed size
  - **Kernel Space** - 1GB reserved for the kernel (not used by the program)
  - **Shared Libraries** - The shared libraries of the program
  - **Ground Space** - The rest of the address space (not used by the program)
- Random offset is used to make it harder to guess the address of the program
  - Between:
    - Kernel Space and stack
    - Stack and Libraries
    - Heap and Data


# Translation Lookaside Buffer (TLB)
- TLB is a cache for the page table
- TLB is a small cache that is used to speed up the translation of virtual addresses to physical addresses
- TLB is a cache that is used to speed up the translation of virtual addresses to physical addresses
- 
