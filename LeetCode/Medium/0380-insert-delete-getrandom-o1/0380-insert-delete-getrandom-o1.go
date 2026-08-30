import (
	"maps"
	"math/rand/v2"
)

type RandomizedSet struct {
	set map[int]struct{}
}

func Constructor() RandomizedSet {
	return RandomizedSet{set: make(map[int]struct{})}
}

func (s *RandomizedSet) Insert(val int) bool {
	if s.has(val) {
		return false
	}
	s.set[val] = struct{}{}
	return true
}

func (s *RandomizedSet) Remove(val int) bool {
	if !s.has(val) {
		return false
	}
	delete(s.set, val)
	return true
}

func (s *RandomizedSet) GetRandom() int {
	idx := 0
	target := rand.IntN(len(s.set))
	for k := range maps.Keys(s.set) {
		if idx == target {
			return k
		}
		idx++
	}
	return 0
}

func (s *RandomizedSet) has(val int) bool {
	_, ok := s.set[val]
	return ok
}