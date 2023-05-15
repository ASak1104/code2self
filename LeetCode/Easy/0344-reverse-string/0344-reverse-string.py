class Solution:
    # solution 1
    # def reverseString(self, s: List[str]) -> None:
    #     """
    #     Do not return anything, modify s in-place instead.
    #     """
    #     s.reverse()
    
    # solution 2
    def reverseString(self, s: List[str]) -> None:
        for i in range(len(s) >> 1):
            s[i], s[-(i + 1)] = s[-(i + 1)], s[i]