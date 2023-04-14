from typing import *


class Solution:
    right_bracket_map: Dict = {
        ')': '(',
        '}': '{',
        ']': '[',
    }

    def isValid(self, s: str) -> bool:
        stack = []
        for bracket in s:
            if bracket in self.right_bracket_map:
                if not stack or stack.pop() != self.right_bracket_map[bracket]:
                    return False
            else:
                stack.append(bracket)
        return len(stack) == 0


s = Solution()
a = s.isValid(
    "()[]{}"
)
print(a)
