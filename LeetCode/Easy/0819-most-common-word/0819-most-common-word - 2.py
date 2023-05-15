class Solution:
    def mostCommonWord(self, paragraph: str, banned: List[str]) -> str:
        ban_set = set(banned) | {""}
        str_counter = Counter(re.split('\W+', paragraph.lower()))
        for s, _ in str_counter.most_common():
            if s not in ban_set:
                return s