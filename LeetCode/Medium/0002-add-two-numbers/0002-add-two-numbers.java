import static java.util.Objects.nonNull;

class Solution {

    ListNode root = new ListNode();
    ListNode node = root;
    int offset = 0;

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        while (nonNull(l1) && nonNull(l2)) {
            offset += l1.val + l2.val;
            node.next = new ListNode(offset % 10);
            offset /= 10;

            node = node.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        addSingleNode(l1);
        addSingleNode(l2);

        if (offset > 0) {
            node.next = new ListNode(offset);
        }

        return root.next;
    }

    void addSingleNode(ListNode target) {
        while (nonNull(target)) {
            offset += target.val;
            node.next = new ListNode(offset % 10);
            offset /= 10;

            node = node.next;
            target = target.next;
        }
    }

}