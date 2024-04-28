//Java implementation of Hashtable chaining using doubly linked list.
public class HashTableChainingDoublyLinkedList {
    DoublyLinkedListNode[] hashTable; // Declaration of Hash Table
    int size; // keep track of the number of elements in the hash table

    HashTableChainingDoublyLinkedList(int hashTableSize) // Constructor
    // Creating an empty Hash Table
    {
        hashTable = new DoublyLinkedListNode[hashTableSize];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    } // Function to check if hash table is empty

    public void clear() // Function to clear/delete all elements from Hash table
    {
        int len = hashTable.length; // Capacity of Hash Table
        // Creating new empty Hash Table of same initial capacity
        hashTable = new DoublyLinkedListNode[len];
        size = 0;
    }

    public int getSize() {
        return size;
    } // Function that returns size of Hash Table

    public void insert(int value) { // Function to insert a value/element
        size++;
        int position = hash(value); // gets the position/index where the value should be stored
        // creates a node for storing value
        DoublyLinkedListNode node = new DoublyLinkedListNode(value);
        DoublyLinkedListNode start = hashTable[position];
        if (hashTable[position] == null)
            hashTable[position] = node;
        else {
            node.next = start;
            start.prev = node;
            hashTable[position] = node;
            // If there is already a node at hashTable[position],
            // the new node is inserted at the beginning of the existing linked list.
            // The next and prev pointers are updated accordingly.
        }
    }

    public void printHashTable() // Function to print hash table
    {
        System.out.println();
        for (int i = 0; i < hashTable.length; i++) {
            System.out.print("At " + i + ": ");
            DoublyLinkedListNode start = hashTable[i];
            while (start != null) {
                System.out.print(start.data + " ");
                start = start.next;
            }
            System.out.println();
        }
    }

    public void remove(int value) // Function to remove an element
    {
        try {
            int position = hash(value); // gets the position where the value to be deleted exists
            DoublyLinkedListNode start = hashTable[position];
            DoublyLinkedListNode end = start;
            if (start.data == value) { // if value is found at start
                size--;
                if (start.next == null) {
                    // removing the value …
                    hashTable[position] = null;
                    return;
                } // Inner if
                start = start.next;
                start.prev = null;
                hashTable[position] = start;
                return;
            } // Outter if
            while (end.next != null && end.next.data != value) // traversing the list until the value is found
                end = end.next;
            if (end.next == null) { // if reached at the end without finding the value
                System.out.println("\nElement not found\n");
                return;
            } // if
            size--;
            if (end.next.next == null) {
                end.next = null;
                return;
            } // if
            end.next.next.prev = end;
            end.next = end.next.next;
            hashTable[position] = start;
        } // try
        catch (Exception e) {
            System.out.println("\nElement not found\n");
        } // catch
    } // remove

    private int hash(Integer x) // Definition of Hash function
    {
        int hashValue = x.hashCode();
        hashValue %= hashTable.length;
        if (hashValue < 0)
            hashValue += hashTable.length;
        return hashValue;
    } // hash
      // *******************************************************************

    public static void main(String[] args) {
        HashTableChainingDoublyLinkedList hashTab = new HashTableChainingDoublyLinkedList(5);
        hashTab.insert(99);
        hashTab.insert(23);
        hashTab.insert(36);
        hashTab.insert(47);
        hashTab.insert(80);
        hashTab.printHashTable();
        hashTab.insert(92);
        hashTab.insert(49);
        hashTab.printHashTable();
        hashTab.remove(99);
        hashTab.printHashTable();
        hashTab.clear();
        hashTab.printHashTable();
        System.out.println(hashTab.isEmpty());
    }
} // main