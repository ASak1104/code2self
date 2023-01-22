import re
from typing import *


class Solution:
    def mostCommonWord(self, paragraph: str, banned: List[str]) -> str:
        word_dict = Counter(word for word in re.sub('[^\w]', ' ', paragraph.lower()).split() if word not in banned)
        return word_dict.most_common(1)[0][0]


s = Solution()
print(s.mostCommonWord("Bob hit a ball, the hit BALL flew far after it was hit.", ["hit"]))
print(s.mostCommonWord("a.", []))