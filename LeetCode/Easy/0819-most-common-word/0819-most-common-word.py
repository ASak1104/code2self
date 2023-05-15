class Solution:
    def mostCommonWord(self, paragraph: str, banned: List[str]) -> str:
        word_dict = Counter(word for word in re.sub('[^\w]', ' ', paragraph.lower()).split() if word not in banned)
        return word_dict.most_common(1)[0][0]