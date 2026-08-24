type LRUCache struct {
	cap        int
	age        int
	leastAge   int
	keyByAge   map[int]int
	ageByKey   map[int]int
	valueByKey map[int]int
}

func Constructor(capacity int) LRUCache {
	return LRUCache{
		cap:        capacity,
		keyByAge:   make(map[int]int, 200_000),
		ageByKey:   make(map[int]int, 10_001),
		valueByKey: make(map[int]int, capacity),
	}
}

func (c *LRUCache) Get(key int) int {
	if v, ok := c.valueByKey[key]; ok {
		c.hit(key)
		return v
	}
	return -1
}

func (c *LRUCache) Put(key int, value int) {
	if _, ok := c.valueByKey[key]; ok {
		c.valueByKey[key] = value
		c.hit(key)
		return
	}

	if len(c.valueByKey) == c.cap {
		c.compact()
		k := c.keyByAge[c.leastAge]
		delete(c.ageByKey, k)
		delete(c.valueByKey, k)
		delete(c.keyByAge, c.leastAge)
		c.leastAge++
	}

	c.valueByKey[key] = value
	c.hit(key)
}

func (c *LRUCache) hit(k int) {
	c.compact()

	if oldAge, ok := c.ageByKey[k]; ok {
		delete(c.keyByAge, oldAge)
	}

	c.ageByKey[k] = c.age
	c.keyByAge[c.age] = k
	c.age++
}

func (c *LRUCache) compact() {
	for {
		if _, ok := c.keyByAge[c.leastAge]; ok || c.leastAge == c.age {
			break
		}
		c.leastAge++
	}
}