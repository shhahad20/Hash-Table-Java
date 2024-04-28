class DoublyLinkedListNode {
    // Declaration of Nodes
    DoublyLinkedListNode next, prev;
    int data;

    DoublyLinkedListNode(int data) // constructor
    {
        this.data = data;
        next = prev = null;
    }
} // DoublyLinkedListNode