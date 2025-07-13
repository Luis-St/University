#include <pthread.h>
#include <stdio.h>
#include <stdbool.h>
#include <unistd.h>

/**
 * Producer-Consumer Pattern Implementation
 *
 * This program demonstrates a classic producer-consumer pattern using POSIX threads.
 * Multiple producer threads generate sequential numbers and place them in a shared buffer.
 * Multiple consumer threads retrieve and process these numbers.
 *
 * The implementation uses a single-slot buffer with mutex and condition variable
 * for thread synchronization.
 */

/* Function prototypes */
void put(int x);
int get();
void* produce(void* arg);
void* consume(void* arg);

/* Shared data between producers and consumers */
bool available = false;  /* Flag indicating if data is available in buffer */
int data;               /* Single-slot buffer for produced data */

/* Synchronization primitives */
pthread_mutex_t buffer_mutex = PTHREAD_MUTEX_INITIALIZER;  /* Mutex for buffer access */
pthread_cond_t buffer_cond = PTHREAD_COND_INITIALIZER;     /* Condition variable for signaling */

/**
 * Producer thread function
 *
 * Continuously produces incrementing numbers and places them in the shared buffer.
 * Blocks when buffer is full (available == true).
 *
 * @param arg Thread argument (unused, but contains thread ID)
 * @return NULL (required by pthread interface)
 */
void* produce(void* arg) {
    int thread_id = *(int*)arg;
    int number = 0;

    while(1) {
        number++;
        printf("[Producer %d]: Produced number %d\n", thread_id, number);
        put(number);

        /* Small delay to make output more readable */
        usleep(100000); /* 100ms */
    }

    return NULL;
}

/**
 * Consumer thread function
 *
 * Continuously retrieves numbers from the shared buffer and processes them.
 * Blocks when buffer is empty (available == false).
 *
 * @param arg Thread argument (unused, but contains thread ID)
 * @return NULL (required by pthread interface)
 */
void* consume(void* arg) {
    int thread_id = *(int*)arg;

    while(1) {
        int number = get();
        printf("[Consumer %d]: Consumed number %d\n", thread_id, number);

        /* Small delay to make output more readable */
        usleep(150000); /* 150ms */
    }

    return NULL;
}

/**
 * Put data into the shared buffer
 *
 * Blocks if buffer is full (available == true).
 * Once buffer is available, stores the data and signals waiting consumers.
 *
 * @param x The integer value to store in the buffer
 */
void put(int x) {
    pthread_mutex_lock(&buffer_mutex);

    /* Wait while buffer is full */
    while(available) {
        printf("[Producer]: Waiting - buffer full\n");
        pthread_cond_wait(&buffer_cond, &buffer_mutex);
    }

    /* Store data in buffer */
    printf("[Producer]: Buffer available, storing data\n");
    data = x;
    available = true;

    /* Signal all waiting threads (consumers) that data is available */
    pthread_cond_broadcast(&buffer_cond);

    pthread_mutex_unlock(&buffer_mutex);
}

/**
 * Get data from the shared buffer
 *
 * Blocks if buffer is empty (available == false).
 * Once data is available, retrieves it and signals waiting producers.
 *
 * @return The integer value retrieved from the buffer
 */
int get() {
    int result;

    pthread_mutex_lock(&buffer_mutex);

    /* Wait while buffer is empty */
    while(!available) {
        printf("[Consumer]: Waiting - buffer empty\n");
        pthread_cond_wait(&buffer_cond, &buffer_mutex);
    }

    /* Retrieve data from buffer (copy before releasing lock) */
    printf("[Consumer]: Data available, retrieving\n");
    result = data;
    available = false;

    /* Signal all waiting threads (producers) that buffer is available */
    pthread_cond_broadcast(&buffer_cond);

    pthread_mutex_unlock(&buffer_mutex);

    return result;
}

/**
 * Main function
 *
 * Creates multiple producer and consumer threads and waits for them to complete.
 * Since threads run infinite loops, this program will run until terminated externally.
 */
int main() {
    /* Thread handles */
    pthread_t producer1, producer2, producer3;
    pthread_t consumer1, consumer2;

    /* Thread IDs for identification in output */
    int prod_id1 = 1, prod_id2 = 2, prod_id3 = 3;
    int cons_id1 = 1, cons_id2 = 2;

    printf("Starting Producer-Consumer Demo\n");
    printf("===============================\n");
    printf("Creating 3 producers and 2 consumers\n\n");

    /* Create producer threads */
    if (pthread_create(&producer1, NULL, produce, &prod_id1) != 0) {
        perror("Failed to create producer1");
        return 1;
    }
    if (pthread_create(&producer2, NULL, produce, &prod_id2) != 0) {
        perror("Failed to create producer2");
        return 1;
    }
    if (pthread_create(&producer3, NULL, produce, &prod_id3) != 0) {
        perror("Failed to create producer3");
        return 1;
    }

    /* Create consumer threads */
    if (pthread_create(&consumer1, NULL, consume, &cons_id1) != 0) {
        perror("Failed to create consumer1");
        return 1;
    }
    if (pthread_create(&consumer2, NULL, consume, &cons_id2) != 0) {
        perror("Failed to create consumer2");
        return 1;
    }

    printf("All threads created successfully\n");
    printf("Press Ctrl+C to terminate\n\n");

    /* Wait for threads to complete (they won't, due to infinite loops) */
    pthread_join(producer1, NULL);
    pthread_join(producer2, NULL);
    pthread_join(producer3, NULL);
    pthread_join(consumer1, NULL);
    pthread_join(consumer2, NULL);

    /* Cleanup (won't be reached in this example) */
    pthread_mutex_destroy(&buffer_mutex);
    pthread_cond_destroy(&buffer_cond);

    return 0;
}
