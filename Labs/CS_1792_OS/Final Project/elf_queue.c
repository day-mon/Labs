#include "elf_queue.h"
#include <stdlib.h>
#include <pthread.h>

typedef struct elf_queue_s * elf_queue_t;

elf_status_t elf_queue_new(elf_queue_t *ref_queue);
elf_status_t elf_queue_delete(elf_queue_t *ref_queue);
elf_status_t elf_queue_enqueue(elf_queue_t queue, elf_event_t event);
elf_status_t elf_queue_dequeue(elf_queue_t queue, elf_event_t * ref_event);

struct elf_queue_s {
    size_t cap;
    size_t front;
    size_t back;
    // could calculate this but idk if thow this is implemented could break this
    size_t size;
    elf_event_t events[ELF_QUEUE_CAP]; // simple implementation
    pthread_mutex_t mutex;
    pthread_cond_t cond;
};

/*
 * Creates a new multi-threaded unbounded queue. Capacity is fixed for simplicity.
 */
elf_status_t elf_queue_new(elf_queue_t *ref_queue) {
    elf_queue_t queue = malloc(sizeof(struct elf_queue_s));
    if (queue == NULL) {
        return ELF_ERROR;
    }

    queue->cap = ELF_QUEUE_CAP;
    queue->front = 0;
    queue->back = 0;

    if (pthread_mutex_init(&queue->mutex, NULL) != 0) {
        free(queue);
        return ELF_ERROR;
    }

    if (pthread_cond_init(&queue->cond, NULL) != 0) {
        free(queue);
        return ELF_ERROR;
    }

    *ref_queue = queue;
    return ELF_OK;
}

/*
 * Delete queue.
 */
elf_status_t elf_queue_delete(elf_queue_t *ref_queue) {
    if (ref_queue == NULL || *ref_queue == NULL) {
        return ELF_ERROR;
    }

    // free mutex and condition variable
    pthread_mutex_destroy(&(*ref_queue)->mutex);
    pthread_cond_destroy(&(*ref_queue)->cond);

    free(*ref_queue);
    *ref_queue = NULL; // invalidate caller pointer
    return ELF_OK;
}

/*
 * Enqueues a void pointer. If the queue has reached maximal capacity, ELF_FULL is returned;
 * else, ELF_OK is returns. DOES NOT BLOCK.
 */
elf_status_t elf_queue_enqueue(elf_queue_t queue, elf_event_t event) {
    if (queue == NULL) { return ELF_ERROR; }

    pthread_mutex_lock(&queue->mutex);

    if (queue->size == queue->cap) {
        pthread_mutex_unlock(&queue->mutex);
        return ELF_FULL;
    }

    queue->events[queue->back] = event;
    queue->back = (queue->back + 1) % queue->cap;
    queue->size++;

    // signal incase something is waiting
    pthread_cond_signal(&queue->cond);
    // unlock mutex
    pthread_mutex_unlock(&queue->mutex);

    return ELF_OK;
}

/*
 * Dequeues a void pointer. If the queue is empty, the function BLOCKS. Return ELF_OK.
 */
elf_status_t elf_queue_dequeue(elf_queue_t queue, elf_event_t * ref_event) {
    pthread_mutex_lock(&queue->mutex);
    while (queue->size == 0) {
        pthread_cond_wait(&queue->cond, &queue->mutex);
    }
    *ref_event = queue->events[queue->front];
    queue->front = (queue->front + 1) % queue->cap;
    queue->size--;
    pthread_mutex_unlock(&queue->mutex);
    return ELF_OK;
}
