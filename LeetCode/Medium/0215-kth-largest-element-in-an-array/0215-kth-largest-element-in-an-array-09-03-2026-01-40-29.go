import "math/rand/v2"

func findKthLargest(nums []int, k int) int {
	return quickSelect(nums, 0, len(nums)-1, len(nums)-k)
}

func quickSelect(nums []int, s, e, target int) int {
	i, lt, gt := s, s, e
	piv := nums[s + rand.IntN(e-s+1)]
	for i <= gt {
		switch {
		case nums[i] < piv:
			nums[i], nums[lt] = nums[lt], nums[i]
			lt++
			i++
		case nums[i] > piv:
			nums[i], nums[gt] = nums[gt], nums[i]
			gt--
		default:
			i++
		}
	}
	switch {
	case target < lt:
		return quickSelect(nums, s, lt-1, target)
	case target > gt:
		return quickSelect(nums, gt+1, e, target)
	default:
		return piv
	}
}