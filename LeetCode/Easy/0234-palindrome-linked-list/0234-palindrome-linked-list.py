# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def isPalindrome(self, head: ListNode) -> bool:
        node = head
        vals = []
        while node is not None:
            vals.append(node.val)
            node = node.next
        return vals == vals[::-1]