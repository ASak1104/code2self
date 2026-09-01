import "container/heap"

type maxHeap struct {
	a       []int
	reverse bool
}

func (h *maxHeap) Len() int {
	return len(h.a)
}

func (h *maxHeap) Less(i, j int) bool {
	if h.reverse {
		return h.a[i] < h.a[j]
	}
	return h.a[i] > h.a[j]
}

func (h *maxHeap) Swap(i, j int) {
	h.a[i], h.a[j] = h.a[j], h.a[i]
}

func (h *maxHeap) Push(x any) {
	h.a = append(h.a, x.(int))
}

func (h *maxHeap) Pop() any {
	ldx := len(h.a) - 1
	lv := h.a[ldx]
	h.a = h.a[:ldx]
	return lv
}

func findKthLargest(nums []int, k int) int {
	h := maxHeap{a: nums}
	if k > len(nums)/2 {
		k = len(nums) - k + 1
		h.reverse = true
	}
	heap.Init(&h)
	for range k - 1 {
		heap.Pop(&h)
	}
	return heap.Pop(&h).(int)
}