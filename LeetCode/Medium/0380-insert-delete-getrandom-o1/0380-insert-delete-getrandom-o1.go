import (
	"math/rand/v2"
)

type RandomizedSet struct {
	idxByVal map[int]int
	vals     []int
}

func Constructor() RandomizedSet {
	return RandomizedSet{idxByVal: make(map[int]int), vals: make([]int, 0)}
}

func (s *RandomizedSet) Insert(val int) bool {
	if s.has(val) {
		return false
	}
	s.idxByVal[val] = len(s.vals)
	s.vals = append(s.vals, val)
	return true
}

func (s *RandomizedSet) Remove(val int) bool {
	if !s.has(val) {
		return false
	}
	tdx := s.idxByVal[val]
	ldx := len(s.vals) - 1

	s.idxByVal[s.vals[ldx]] = tdx
	s.vals[tdx], s.vals[ldx] = s.vals[ldx], s.vals[tdx]

	delete(s.idxByVal, val)
	s.vals = s.vals[:ldx]

	return true
}

func (s *RandomizedSet) GetRandom() int {
	tdx := rand.IntN(len(s.vals))
	return s.vals[tdx]
}

func (s *RandomizedSet) has(val int) bool {
	_, ok := s.idxByVal[val]
	return ok
}