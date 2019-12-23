package SYMBOL_TABLE;

import TYPES.*;

//file complete - don't touch

public class Entry {
    int index;                          // number of elements that inserted before the current element.
    public String name;
    public Type type;
    public Entry prevtop;  // this is the last element that we inserted before current element
    public Entry next;     // this is the next element in the list of specific hash value.
    public int prevtop_index;           // The prevtop index is just for debug purposes

    //simple constructor - don't touch
    public Entry(
            String name,
            Type type,
            int index,
            Entry next,
            Entry prevtop,
            int prevtop_index) {

        this.index = index;
        this.name = name;
        this.type = type;
        this.next = next;
        this.prevtop = prevtop;
        this.prevtop_index = prevtop_index;
    }
}
