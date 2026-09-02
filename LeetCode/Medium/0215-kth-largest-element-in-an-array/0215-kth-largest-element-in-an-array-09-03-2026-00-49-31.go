import "math/rand/v2"

func findKthLargest(nums []int, k int) int {
	target := len(nums) - k
	s, e := 0, len(nums)-1
	for {
		r := s + rand.IntN(e-s+1)
		nums[r], nums[e] = nums[e], nums[r]
		p := s
		for i := s; i < e; i++ {
			if nums[i] < nums[e] {
				nums[i], nums[p] = nums[p], nums[i]
				p++
			}
		}
		nums[p], nums[e] = nums[e], nums[p]
		switch {
		case p < target:
			s = p + 1
		case p > target:
			e = p - 1
		default:
			return nums[p]
		}
	}
}