# Petri Nets 😕

- A Petri net is a formalism for modeling concurrent systems.
  - A petri net is a directed bipartite graph with places and transitions.
  - A place is a set of tokens.
  - A transition is a set of arcs.
  - A token is a tuple of the form (place, transition).
- A input place is a place with an incoming arc.
- A output place is a place with an outgoing arc.
- Its enabled when all input places have at least one token.

# OS Threads 🔒

- A thread is a sequence of instructions that can be executed independently of other code.
- Why use threads?

1. Parallelization
2. Overlap wait for IO with computation

- A multithreaded program has multiple instruction pointers
- can be visualized by multiple tokens in a petri net
- process becomes the main thread
- threads share the same address space but have different stacks

#### **In c defining means giving address space to a variable and declaring means telling the compiler that a variable exists.**

```c
#include <pthread.h>
#include <stdio.h>

#def NUM_ITERATIONS 1000000

volatile int data = 0;

void increment(int *p_data, int amount) {
    *p_data += amount;
}

void * thread_main(void *id) {
    for (int i = 0; i < NUM_ITERATIONS; i++) {
        increment((int *)id, 1);
    }
    return NULL;
}

int main() {
    pthread_t thread1, thread2;
    pthread_create(&thread1, NULL, thread_main, &data);
    pthread_create(&thread2, NULL, thread_main, &data);
  
    pthread_join(thread1, NULL);
    pthread_join(thread2, NULL);
  
    printf("data = %d\n", data);
    return 0;
}
```

- This code could print any number between 0 and 2,000,000
- The problem is that the increment function is not atomic
- The solution is to use a mutex
- A mutex is a lock that can be locked and unlocked
- A mutex can be used to make a function atomic
- A (void * ) only cares about the address not the type

```c
#include <pthread.h>
#include <stdio.h>
#include <assert.h>

# define CHECK(F) = assert(F == 0)

# define NUM_ITERATIONS 1000000

volatile int data = 0;
pthread_mutex_t lock;

void * thread_main(void *id)
{
    int *p_data = (int *)id;
    for (int i = 0; i < NUM_ITERATIONS; i++) {
        CHECK(pthread_mutex_lock(&lock));
        *p_data += 1;
        CHECK(pthread_mutex_unlock(&lock));
    }
    return NULL
}

int main() 
{
    pthread_t thread1, thread2;
    CHECK(pthread_mutex_init(&lock, NULL));
    CHECK(pthread_create(&thread1, NULL, thread_main, &data));
    CHECK(pthread_create(&thread2, NULL, thread_main, &data));
  
    CHECK(pthread_join(thread1, NULL));
    CHECK(pthread_join(thread2, NULL));
  
    printf("data = %d\n", data);
    return 0;

}
```

## Atomic Primitives

- An atomic primitive is a function that is atomic
- Test and Set

````c
#include <pthread.h>
#include <stdio.h>
#include <assert.h>
#include <stdatomic.h>

#define NUM_ITERATIONS 1000000

volatile int data = 0;
vioatile atomic_flag flag = ATOMIC_FLAG_INIT;

void spin_lock(atomic_flag *lock) {
    while (atomic_flag_test_and_set(lock));
}

void spin_unlock(atomic_flag *lock) {
    atomic_flag_clear(lock);
}

void * thread_main(void *id) {
    int *p_data = (int *)id;
    for (int i = 0; i < NUM_ITERATIONS; i++) {
        spin_lock(&flag); // if thread one comes in and thread 2 comes in at the same one of the threads will atomically set the flag to true and the other will spin until the flag is set to false
        *p_data += 1;
        spin_unlock(&flag);
    }
    return NULL;
}

int main() {
    pthread_t thread1, thread2;
    CHECK(pthread_create(&thread1, NULL, thread_main, &data));
    CHECK(pthread_create(&thread2, NULL, thread_main, &data));
  
    CHECK(pthread_join(thread1, NULL));
    CHECK(pthread_join(thread2, NULL));
  
    printf("data = %d\n", data);
    return 0;
}
````

- **Compare and swap**

```c
int CompareandSwap(int *ptr, int old, int new) {
    int temp = *ptr;
    if (temp == old) {
        *ptr = new;
    }
    return temp;
}
```

- **Fetch and add**

```c
int FetchandAdd(int *ptr, int value) {
    int temp = *ptr;
    *ptr += value;
    return temp;
}
```

- **Load Linked (LL)**

```c
int LoadLinked(int *ptr) {
    return *ptr;
}
```

- **Store Conditional (SC)**
  - Often tracked in cache lines
  - Store may spuriously fail
  - Store may fail if another thread has modified the cache line
- A *cache line* is a block of memory that is loaded into the cache
- This is used to implement a lock
  - LoadLinked will set the flag
  - StoreConditional will check the flag
  - If the flag is set then the lock is acquired
  - If the flag is not set then the lock is not acquired
- Load a value into a register
- Do some computation
- Attempt to store the value back into memory
- If the value has changed then the store will fail

```c
int StoreConditional(int *ptr, int value) {

    if (/* no other thread has changed the value of ptr */) 
    {
        *ptr = value;
        return 1;
    } 
    else 
    {
        return 0;
    }
}
```

- **Spin Lock with LL and SC**

```c
int StoreConditional(int *ptr, int value) {

    if (/* no other thread has changed the value of ptr */) 
    {
        *ptr = value;
        return 1;
    } 
    else 
    {
        return 0;
    }
}

int LoadLinked(int *ptr) {
    return *ptr;
}

void lock(lock_t *lock)
{
  // 1 == locked
  // 0 == unlocked
  while (1) 
  {
        // will spin until the lock is acquired
        while (LoadLinked(&lock->flag) == 1);
      
        // will try to acquire the lock but it could fail if another thread has changed the value of the lock
        // if the lock is acquired it will break out because the acquired
        if (StoreConditional(&lock->flag, 1) == 1) break;
  }
}


void unlock(lock_t *lock) {
  lock->flag = 0;
}

void lock_ss(lock_t *lock) {
  while (LoadLinked(&lock->flag) == 1 || !StoreConditional(&lock->flag, 1) == 0);
  // spin until the lock is acquired
  // uses short circut
}

void thread_main(void *id) {
    int *p_data = (int *)id;
    for (int i = 0; i < NUM_ITERATIONS; i++) {
        lock(&lock);
        *p_data += 1;
        unlock(&lock);
    }
    return NULL;
}

int main() {
    pthread_t thread1, thread2;
    CHECK(pthread_create(&thread1, NULL, thread_main, &data));
    CHECK(pthread_create(&thread2, NULL, thread_main, &data));
  
    CHECK(pthread_join(thread1, NULL));
    CHECK(pthread_join(thread2, NULL));
  
    printf("data = %d\n", data);
    return 0;
}

```
