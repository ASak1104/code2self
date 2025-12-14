func solution(t string, p string) int {
    res := 0
    
    for i := 0; i <= len(t) - len(p); i++ {
        if compare(t, p, i, 0) {
            res++
        }
    }
    
    return res
}

func compare(t string, p string, s int, offset int) bool {
    if offset >= len(p) {
        return true
    }
    
    if t[s + offset] == p[offset] {
        return compare(t, p, s, offset + 1)
    }
    
    
    return t[s + offset] < p[offset]
}