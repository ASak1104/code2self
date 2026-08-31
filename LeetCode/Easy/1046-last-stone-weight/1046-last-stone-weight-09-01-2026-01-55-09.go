import "container/heap"

type intHeap []int

func (h *intHeap) Len() int {
	return len(*h)
}

func (h *intHeap) Less(i, j int) bool {
	return (*h)[i] > (*h)[j]
}

func (h *intHeap) Swap(i, j int) {
	(*h)[i], (*h)[j] = (*h)[j], (*h)[i]
}

func (h *intHeap) Push(x any) {
	*h = append(*h, x.(int))
}

func (h *intHeap) Pop() any {
	ldx := h.Len() - 1
	x := (*h)[ldx]
	*h = (*h)[:ldx]
	return x
}

func lastStoneWeight(stones []int) int {
	h := intHeap(stones)
	heap.Init(&h)

	for len(h) > 1 {
		x, y := heap.Pop(&h).(int), heap.Pop(&h).(int)
		if x > y {
			heap.Push(&h, x-y)
		}
	}
	if len(h) == 0 {
		return 0
	}
	return heap.Pop(&h).(int)
}