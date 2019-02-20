package algorithms;

/**
 * @Auther: Jesper
 * @Date: 2019/2/13 15:42
 * @Description:  单链表的快排
 *
 * 我们只需要两个指针p1和p2，这两个指针均往next方向移动，移动的过程中保持p1之前的key都小于选定的key，p1和p2之间的key都大于选定的key，那么当p2走到末尾时交换p1与key值便完成了一次切分。
 */
public class QuickSortList {
    class ListNode {
        ListNode next;
        int val;
    }

    public ListNode sortList(ListNode head){
        //采用快速排序
        quickSort(head, null);
        return head;
    }

    public static void quickSort(ListNode head, ListNode end){
        if (head != end){
            ListNode node = partion(head, end);
            quickSort(head, node);
            quickSort(node.next, end);
        }
    }

    public static ListNode partion(ListNode head, ListNode end){
        ListNode p1 = head, p2 = head.next;
        //走到末尾才停
        while (p2 != end){
            //大于key值时，p1向前走一步，交换p1与p2的值
            if (p2.val < head.val){
                p1 = p1.next;

                int temp = p1.val;
                p1.val = p2.val;
                p2.val = temp;
            }
            p2 = p2.next;
        }
        //当有序时，不交换p1和key值
        if (p1 != head){
            int temp = p1.val;
            p1.val = head.val;
            head.val = temp;
        }
        return p1;
    }

}
