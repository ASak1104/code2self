const solution = (scoville, K) => {
    let count = 0;

    heapify(scoville);

    while (scoville.length > 1 && scoville[0] < K) {
        const first = heappop(scoville);
        const second = heappop(scoville);

        heappush(scoville, first + second * 2);
        count++;
    }

    if (scoville[0] < K) {
        return -1;
    }

    return count;
}

const heapify = (array) => {
    for (let i = ~~(array.length / 2); i >= 0; i--) {
        swapdown(array, i)
    }
}

const heappush = (heap, value) => {
    heap.push(value);

    swapup(heap, heap.length - 1);
}

const heappop = (heap) => {
    if (heap.length === 1) {
        return heap.pop();
    }

    const pop = heap[0];

    heap[0] = heap.pop();
    swapdown(heap, 0);

    return pop;
}

const swapdown = (heap, index) => {
    const left = index * 2 + 1;
    const right = index * 2 + 2;
    let target = index;

    if (left < heap.length && heap[left] < heap[target]) {
        target = left;
    }

    if (right < heap.length && heap[right] < heap[target]) {
        target = right;
    }

    if (index !== target) {
        const temp = heap[index];

        heap[index] = heap[target];
        heap[target] = temp;

        swapdown(heap, target);
    }
}

const swapup = (heap, index) => {
    let parent = ~~((index - 1) / 2);

    while (index > 0 && heap[index] < heap[parent]) {
        const temp = heap[index];

        heap[index] = heap[parent];
        heap[parent] = temp;

        index = parent;
        parent = ~~((index - 1) / 2);
    }
}
