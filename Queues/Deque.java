import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
  private int size;

  private Node head;

  private Node tail;

  private class Node {
    private Item value;
    private Node next;
    private Node prev;
  }

  public Deque() {
    size = 0;
    head = tail = null;
  }

  public  boolean isEmpty() {
    return size == 0;
  }

  public int size() {
    return size;
  }

  public void addFirst(Item item) {
    if (item == null) {
      throw new java.lang.NullPointerException();
    }
    Node currentNode = new Node();
    currentNode.value = item;
    if (size == 0) {
      currentNode.prev = null;
      currentNode.next = null;
      head = currentNode;
      tail = currentNode;
    } else {
      currentNode.next = head;
      currentNode.prev = null;
      head.prev = currentNode;
      head = currentNode;
    }
    size++;
  }

  public void addLast(Item item) {
    if (item == null) {
      throw new java.lang.NullPointerException();
    }
    Node currentNode = new Node();
    currentNode.value = item;
    if (size == 0) {
      currentNode.prev = null;
      currentNode.next = null;
      head = currentNode;
      tail = currentNode;
    } else {
      currentNode.prev = tail;
      currentNode.next = null;
      tail.next = currentNode;
      tail = currentNode;
    }
    size++;
  }

  public Item removeFirst() {
    if (this.size == 0) {
      throw new java.util.NoSuchElementException();
    }
    Item result = null;
    if (size == 1) {
      result = head.value;
      head = tail = null;
    } else {
      Node oldhead = head;
      result = oldhead.value;
      head = oldhead.next;
      head.prev = null;
      oldhead.prev = null;
      oldhead.next = null;
    }
    size--;
    return result;
  }

  public Item removeLast() {
    if (this.size == 0) {
      throw new java.util.NoSuchElementException();
    }
    Item result = null;
    if (size == 1) {
      result = head.value;
      head = tail = null;
    } else {
      Node oldtail = tail;
      result = oldtail.value;
      tail = oldtail.prev;
      tail.next = null;
      oldtail.prev = null;
      oldtail.next = null;
    }
    size--;
    return result;
  }

  public Iterator<Item> iterator() {
    return new DeqIterator();
  }

  private class DeqIterator implements Iterator<Item> {

    private  Node currentNode = head;

    @Override

    public boolean hasNext() {
      return currentNode != null;
    }

    public Item next() {
      if (!hasNext()) {
        throw new java.util.NoSuchElementException("there are no more items");
      }
      Item item = currentNode.value;
      currentNode = currentNode.next;
      return item;
    }

    public void remove() {
      throw new UnsupportedOperationException();
    }
  }

}
