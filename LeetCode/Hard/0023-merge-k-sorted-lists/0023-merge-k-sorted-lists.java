class Solution {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> pq = new PriorityQueue<>(lists.length, Comparator.comparingInt(node -> node.val));
        ListNode root = new ListNode();
        ListNode prev = root;

        for (ListNode node : lists) {
            if (Objects.nonNull(node)) {
                pq.add(node);
            }
        }

        while (!pq.isEmpty()) {
            ListNode node = pq.poll();

            prev.next = node;
            prev = node;

            if (Objects.nonNull(node.next)) {
                pq.add(node.next);
            }
        }

        return root.next;
    }

}