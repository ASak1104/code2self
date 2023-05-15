class Solution:
    def longestPalindrome(self, s: str) -> str:
        if len(s) < 2 or s == s[::-1]:
            return s
        for len_pal in range(len(s), 0, -1):
            for i in range(len(s) - len_pal + 1):
                pal = s[i: i + len_pal]
                if pal == pal[::-1]:
                    return pal