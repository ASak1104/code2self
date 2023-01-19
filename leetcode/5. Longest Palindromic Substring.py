class Solution:
    def longestPalindrome(self, s: str) -> str:
        if len(s) < 2 or s == s[::-1]:
            return s

        def expand(s: str, left: int, right: int) -> str:
            while left > -1 and right < len(s) and s[left] == s[right]:
                left -= 1
                right += 1
            return s[left + 1: right]

        answer = ''
        for left in range(len(s) - 1):
            answer = max(answer,
                         expand(s, left, left + 1),
                         expand(s, left, left + 2),
                         key=len)
        return answer


s = Solution()
print(s.longestPalindrome('babad'))
print(s.longestPalindrome('cbbd'))
