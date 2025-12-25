func solution(a int, b int) int64 {
    if a > b {
        return solution(b, a)
    }
    
    sumB := int64(b) * int64(b + 1) / 2
    sumA := int64(a - 1) * int64(a) / 2
    
    return sumB - sumA
}