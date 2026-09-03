import (
	"math/rand/v2"
	"sync"
)

const threshold = 10_000

func sortArray(nums []int) []int {
	quickSort(nums, 0, len(nums)-1)
	return nums
}

func quickSort(a []int, s, e int) {
	if s >= e {
		return
	}
	i, lt, gt := s, s, e
	piv := a[s+rand.IntN(e-s+1)]
	for i <= gt {
		switch {
		case a[i] < piv:
			a[i], a[lt] = a[lt], a[i]
			lt++
			i++
		case a[i] > piv:
			a[i], a[gt] = a[gt], a[i]
			gt--
		default:
			i++
		}
	}

	if e-s+1 < threshold {
		quickSort(a, s, lt-1)
		quickSort(a, gt+1, e)
		return
	}

	var wg sync.WaitGroup
	wg.Add(2)
	go func() {
		defer wg.Done()
		quickSort(a, s, lt-1)
	}()
	go func() {
		defer wg.Done()
		quickSort(a, gt+1, e)
	}()
	wg.Wait()
}
