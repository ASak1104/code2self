func maxScore(a []int, b []int) int64 {
	mem := [5]int64{0, -1 << 62, -1 << 62, -1 << 62, -1 << 62}
	for _, v := range b {
		for j := 4; j > 0; j-- {
			mem[j] = max(mem[j], mem[j-1]+int64(a[j-1])*int64(v))
		}
	}
	return mem[len(a)]
}