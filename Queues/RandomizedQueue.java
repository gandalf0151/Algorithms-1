import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
  private Item[] rqArrays;
  private int size;

  public RandomizedQueue() { // construct an empty randomized queue
    rqArrays = (Item[]) new Object[1];
    size = 0;
  }

  private void valivate(Item item) {
    if (item == null) {
      throw new IllegalArgumentException("the item is null!");
    }
  }

  public boolean isEmpty() { // is the randomized queue empty?
    return size == 0;
  }

  public int size() { // return the number of items on the randomized queue
    return size;
  }

  private void resize(int cap) {
    Item[] temp = (Item[]) new Object[cap];
    for (int i = 0; i < size; i++) {
      temp[i] = rqArrays[i];
    }
    rqArrays = temp;
  }

  public void enqueue(Item item) {   // add the item
    valivate(item);
    rqArrays[size++] = item;
    if (size == rqArrays.length) {
      resize(2 * rqArrays.length);
    }
  }

  public Item dequeue() { // remove and return a random item
    if (this.size == 0) {
      throw new java.util.NoSuchElementException("the RandomizeQueue is empty");
    }
    int r = StdRandom.uniform(0,size);
    size--;
    rqArrays[r] = rqArrays[size];
    rqArrays[size] = null;
    if (size > 0 && size == rqArrays.length / 4) {
      resize(rqArrays.length / 2);
    }
    Item result = rqArrays[r];
    return result;
  }

  public Item sample() { // return a random item (but do not remove it)
    if (this.size == 0) {
      throw new java.util.NoSuchElementException("the RandomizeQueue is empty");
    }
    int r = StdRandom.uniform(0,size);
    Item findItem = rqArrays[r];
    return findItem;
  }

  public Iterator<Item> iterator() {  // return an independent iterator over items in random order
    return new RandomizedQueueIterator();
  }

  private class RandomizedQueueIterator implements Iterator<Item> {
    private int rank;

    private Item[] iterArrays;

    public RandomizedQueueIterator() {
      rank = size;
      iterArrays = (Item[]) new Object[rank];
      for (int i = 0;i < size; i++) {
        iterArrays[i] = rqArrays[i];
      }
    }

    @Override
    public Item next() {
      if (!hasNext()) {
        throw new java.util.NoSuchElementException("there are no more items");
      }
      int r = StdRandom.uniform(0,rank);
      rank--;
      Item item = iterArrays[r];
      iterArrays[r] = iterArrays[rank];
      iterArrays[rank] = item;
      return item;
    }

    @Override
    public boolean hasNext() {
      return rank > 0;
    }

    @Override
    public void remove() {
      throw new java.lang.UnsupportedOperationException();
    }
  }


}
