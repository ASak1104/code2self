func maxScore(a []int, b []int) int64 {
	mem := make([][5]int64, 0, len(b))
	for range len(b) + 1 {
		mem = append(mem, [5]int64{0, -1 << 62, -1 << 62, -1 << 62, -1 << 62})
	}
	for i := 1; i <= len(a); i++ {
		for j := 1; j <= len(b); j++ {
			mem[j][i] = max(mem[j-1][i], mem[j-1][i-1]+int64(a[i-1])*int64(b[j-1]))
		}
	}

	return mem[len(b)][len(a)]
}