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
- A (void *m) only cares about the address not the type

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
        // will spin until the lock is unlocked
        while (LoadLinked(&lock->flag) == 1);
  
        // will try to acquire the lock but it could fail due to a number for reasons.
        // 1. a system interupt happens
        // 2. another thread has changed the value of the lock  
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

---

Date: **November 2nd, 2022**

**Lock with Queues**

```c
type def struct __lock_t {
    // the 
    int flag;
    // the guard is for 
    int guard;
    // the queue is used for threads that are waiting to acquire the lock
    queue_t queue
} lock_t;

void lock_init(lock_t *m) {
    m->flag = 0;
    m->tail = 0;
    queue_init(&m->q);
}

// uses active and passive waiting
// have to get the guard first to do anything
// the guard is used to make sure that only one thread is in the critical section at a time
void lock(lock_t *m) {
    // acquired guard by spinning
    while (TestandSet(&m->guard, 1) == 1);
  
    if (m->flag == 0) {
        m->flag = 1;
        m->guard = 0;
    } else {
        // gettid is a thread id
        queue_enqueue(&m->q, gettid());
        m->guard = 0;
  
        // gives up the time slice
        park();
    }
}

void unlock(lock_t *m) {
    while (TestandSet(&m->guard, 1) == 1);
  
    if (queue_empty(&m->q)) {
        m->flag = 0; // if the queue is empty then the lock is unlocked
    } else {
      unpark(queue_dequeue(&m->q));
    }
    m->guard = 0;
}
```

- Fairness
  - A lock is fair if it guarantees that threads acquire the lock in the order in which they request it
  - A lock is unfair if it does not guarantee that threads acquire the lock in the order in which they request it

---

Date: **November 4th, 2022**

**Lock with Futex**

```c
void mutex_lock(int *mutex) {
    int v;
    // sets msb to 1, then it returns the old value
    // if the old value was 0 that means the lock was unlocked
    if (atomic_bit_test_and_set(mutex, 3) == 0) {
        return;
    }
  
    // sets LSB to 1 by adding 1
    atomic_increment(mutex);
    while (1) {
  
      // checks again to see if the bit is set
      if (atomic_bit_test_and_set(mutex, 3) == 0) {
        // if the state here would have to be 0001 so you would want to decrement it
            atomic_decrement(mutex);
            return;
      }
  
        // in c ints are signed by deafult
        // so if we have 4 bits for exmaple
        // 1  0  0  1
        // First bit is the sign bit
        // would be -1
  
        // this is going to deref the mutex 
        v = *mutex;
  
        // checks to see if the value is negative. If the lock is aquired the bit should look like 1001 == -2
        if (v >= 0)
            continue;
  
         // a futex wait is a system call that will put the thread to sleep
        futex_wait(mutex, v);
    }
}

void mutex_unlock(int *mutex) {
    /* Adding 0x80000000 to the counter results in 0 if and only if
    there are not other interested threads. */
    if (atomic_add_zero(mutex, 0x8000)) {
        // overflow occurs 
        return;
    }
  
    futex_wake(mutex, 1);
}
```

---

Date: **November 7th, 2022**

### Producer Consumer Problem with mermaid

````mermaid
graph LR
A(O) --> B[Producer] B[Producer] --> C[O]
C[O] --> D[Consumer]1
B[Producer] -->A[O]

````

### Producer Consumer Problem with C (*Unbounded*)

```c
int buffer[CAP]
int tail_idx = 0;
int head_idx = 0;
int len = 0;

void put(int value) {
    buffer[tail_idx] = value;
    tail_idx = (tail_idx + 1);
    len++;
}

int get() {
    int value = buffer[head_idx];
    head_idx = (head_idx + 1);
    len--;
    return value;
}

cond_t not_empty;
mutex_t mutex;

void *producer(void *arg) {
    int i;
    for (i =0; i < loops; i++) {
        Pthread_mutex_lock(&mutex);
        put(i);
        Pthread_cond_signal(&not_empty);
        Pthread_mutex_unlock(&mutex);
    }
}

void *consumer(void *arg) {
    int i;
    for (i = 0; i < loops; i++) {
        Pthread_mutex_lock(&mutex);
      
        // you use a while loop instead of an if statement because you want to make sure that the condition is true and it was not a spurious wakeup
        while (len == 0) {
            // puts thread into blocked state and unlocks the mutex
            Pthread_cond_wait(&not_empty, &mutex);
        }
        int value = get();
        Pthread_mutex_unlock(&mutex);
    }
}
```

### Producer Consumer Problem with C (*Bounded*)

````c
int buffer[CAP]
int tail_idx = 0;
int head_idx = 0;
int len = 0;

void put(int value) {
    buffer[tail_idx] = value;
    tail_idx = (tail_idx + 1) % CAP;
    len++;
}

int get() {
    int value = buffer[head_idx];
    head_idx = (head_idx + 1) % CAP;
    len--;
    return value;
}

cond_t not_empty, not_full;
mutex_t mutex;

void *producer(void *arg) {
    int i;
    for (i =0; i < loops; i++) {
        Pthread_mutex_lock(&mutex);
        while (len == CAP) {
            Pthread_cond_wait(&not_full, &mutex);
        }
        put(i);
        Pthread_cond_signal(&not_empty);
        Pthread_mutex_unlock(&mutex);
    }
}

void *consumer(void *arg) {
    int i;
    for (i = 0; i < loops; i++) {
        Pthread_mutex_lock(&mutex);
      
        // you use a while loop instead of an if statement because you want to make sure that the condition is true and it was not a spurious wakeup
        while (len == 0) {
            // puts thread into blocked state and unlocks the mutex
            Pthread_cond_wait(&not_full, &mutex);
        }
        int value = get();
        Pthread_cond_signal(&not_empty);
        Pthread_mutex_unlock(&mutex);
    }
}
````

- Unbounded vs Bounded
  - Unbounded
    - Unbounded means that you can put as many items in the buffer as you want
  - Bounded
    - Bounded means that you can only put a certain amount of items in the buffer before you have to wait for the consumer to consume some of the items

---

Date: November 9th, 2022

### Semaphores

- Introduced by Dijkstra in 1965
- A semaphore is a variable that is used to control access to a common resource by multiple processes in a concurrent system such as a multitasking operating system
- Conceptually, combine counter with wait and signal operations

#### Semaphore operations (All atomic)

- Keeps track of how many threads are waiting essentially

````c
void down(semaphore_t *s) {
    s->value =- 1;
    if (s->value < 0) {
        block(); // puts this thread that is trying to access in a blocked state
    }
}

void up(semaphore_t *s) {
    s->value =+ 1;
    if (s->value <= 0) {
        wakeup(); // sends a wakeup signal to the threads that are waiting
    }
}
````

#### Up-to-K Inclusion problem

- The up-to-k inclusion problem is a problem in concurrency theory that asks whether it is possible to implement a concurrent algorithm that guarantees that at most k threads are executing a critical section at any given time
- The up-to-k inclusion problem is a special case of the mutual exclusion problem
- K is how many threads are allowed to be in the critical section at a time

````c
semaphore_t sem;
sem.value = 2;
sem.down()
// some critical section
sem.up()
````

#### Semaphore with Producer Consumer

````psedo
sem = semaphore(0)
mutex semaphore(1)

 
t1                     t2
mutex.down()   |   mutex.down()
sem.down()     |   produce()
consume()      |   mutex.up()
mutex.up()     |   sem.up()

````

---

Date: November 11th, 2022

## Event-based concurrency
- basis is *single-threaded* event loop
```psuedo
State state; // only loop should change this data
loop {
    event = receive_event();
    if event == EVENT_DONE { break; }
    handle_event(event);
}
```
- handle_event function processes the event and updates the state
  - access associated state of event loop 
  - send events to:
    - the same loop
    - other system parts
- events can represent different action form different actors
    - user input
    - network
    - timers
    - file system
    - etc
- all actions can operate on the same *shared associative state* which simplifies concurrent programing because only loop can access shared state 
- in simplier terms only one thread handles the data at a time

### Asynchronous I/O
- event handlers are not supposed to block
  - operations that block or take long time to compute should be done outside event loop
- OS provides interfaces for AIO 
  - send request ; receive acknowledgement
- technically there are different means to implement AIO
  - threads
  - processes
  - signals
  - callbacks

### Disadvantages
- multi-core machines would require multiple event loops
````mermaid
graph LR
A[Event Loop] --> B[Event Loop]
B --> C[Event Loop]
C --> D[Event Loop]
D --> A
````
- to share data between event loops you need a pointer to the shared data

#### Implicit Blocking
- event loop is blocked when it is waiting for an event
- can happen with page faults, disk I/O, network I/O, etc
- have to associate state with each event