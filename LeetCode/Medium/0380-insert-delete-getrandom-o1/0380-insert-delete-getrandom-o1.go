import (
	"math/rand/v2"
)

type RandomizedSet struct {
	idxByVal map[int]int
	vals     []int
}

func Constructor() RandomizedSet {
	return RandomizedSet{idxByVal: make(map[int]int, 200_000), vals: make([]int, 0, 200_000)}
}

func (s *RandomizedSet) Insert(val int) bool {
	if _, ok := s.idxByVal[val]; ok {
		return false
	}
	s.idxByVal[val] = len(s.vals)
	s.vals = append(s.vals, val)
	return true
}

func (s *RandomizedSet) Remove(val int) bool {
	tdx, ok := s.idxByVal[val]
	if !ok {
		return false
	}
	ldx := len(s.vals) - 1
	lv := s.vals[ldx]
	s.idxByVal[lv] = tdx
	s.vals[tdx] = lv

	delete(s.idxByVal, val)
	s.vals = s.vals[:ldx]

	return true
}

func (s *RandomizedSet) GetRandom() int {
	tdx := rand.IntN(len(s.vals))
	return s.vals[tdx]
}