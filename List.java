/** A linked list of character data objects.
 *  (Actually, a list of Node objects, each holding a reference to a character data object.
 *  However, users of this class are not aware of the Node objects. As far as they are concerned,
 *  the class represents a list of CharData objects. Likwise, the API of the class does not
 *  mention the existence of the Node objects). */
public class List {

    // Reference to the first node
    private Node head;

    // Number of elements in the list
    private int length;

    /** Constructs an empty list. */
    public List() {
        head = null;
        length = 0;
    }

    /** Returns the number of elements in this list. */
    public int getSize() {
        return length;
    }

    /** Returns the CharData of the first element in this list. */
    public CharData getFirst() {
        if (head == null) {
            return null;
        }
        return head.cp;
    }

    /** GIVE Adds a CharData object with the given character to the beginning of this list. */
    public void addFirst(char chr) {
        CharData payload = new CharData(chr);
        Node newHead = new Node(payload, head);
        head = newHead;
        length++;
    }

    /** GIVE Textual representation of this list. */
    public String toString() {
        if (length == 0) {
            return "()";
        }

        String out = "(";
        Node runner = head;

        while (runner != null) {
            out += runner.toString();
            if (runner.next != null) {
                out += " ";
            }
            runner = runner.next;
        }

        out += ")";
        return out;
    }

    /** Returns the index of the first CharData object in this list
     *  that has the same chr value as the given char,
     *  or -1 if there is no such object in this list. */
    public int indexOf(char chr) {
        Node runner = head;
        int pos = 0;

        while (runner != null) {
            if (runner.cp.chr == chr) {
                return pos;
            }
            runner = runner.next;
            pos++;
        }

        return -1;
    }

    /** If the given character exists in one of the CharData objects in this list,
     *  increments its counter. Otherwise, adds a new CharData object with the
     *  given chr to the beginning of this list. */
    public void update(char chr) {
        int where = indexOf(chr);

        if (where != -1) {
            get(where).count++;
        } else {
            addFirst(chr);
        }
    }

    /** GIVE If the given character exists in one of the CharData objects
     *  in this list, removes this CharData object from the list and returns
     *  true. Otherwise, returns false. */
    public boolean remove(char chr) {
        Node prev = null;
        Node curr = head;

        while (curr != null) {

            if (curr.cp.chr == chr) {

                if (prev == null) {
                    head = head.next;
                } else {
                    prev.next = curr.next;
                }

                length--;
                return true;
            }

            prev = curr;
            curr = curr.next;
        }

        return false;
    }

    /** Returns the CharData object at the specified index in this list.
     *  If the index is negative or is greater than the size of this list,
     *  throws an IndexOutOfBoundsException. */
    public CharData get(int index) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException();
        }

        Node runner = head;
        for (int i = 0; i < index; i++) {
            runner = runner.next;
        }

        return runner.cp;
    }

    /** Returns an array of CharData objects, containing all the CharData objects in this list. */
    public CharData[] toArray() {
        CharData[] arr = new CharData[length];

        Node runner = head;
        int i = 0;

        while (runner != null) {
            arr[i++] = runner.cp;
            runner = runner.next;
        }

        return arr;
    }

    /** Returns an iterator over the elements in this list, starting at the given index. */
    public ListIterator listIterator(int index) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException();
        }

        Node runner = head;
        int i = 0;

        while (i < index && runner != null) {
            runner = runner.next;
            i++;
        }

        return new ListIterator(runner);
    }
}