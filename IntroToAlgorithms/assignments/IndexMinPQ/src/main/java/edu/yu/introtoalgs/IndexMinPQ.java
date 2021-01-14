package edu.yu.introtoalgs;

import java.util.*;

/**our implementation is iterable whereas Sedgewick's was not*/
public class IndexMinPQ <Key extends Comparable <Key> > implements Iterable <Integer> {
    private int[] pq;//binary heap maintains an order to know which is minindex - pq[1] index supplied is an ID for the key, then we can get key based off that index
    private int[] qp;//inverse- given the the indexVal(id) as index it returns the position of the indexVal in pq. so pq[n] -> indexVal ^ qp[indexVal] -> n
    private Key[] keys;//an array of the keys indexed by their indexValues(id) that represent them in the pq. enables us to maintain the indexVal - key association.
    //so key[pq[1]] == lowest priority key
    private int n;//size, helps us put a new index at bottom of heap to swim up
    private int maxN;
    private HashMap<Key, Integer> key2Index;

    public IndexMinPQ(int maxN) {
        if (maxN < 0) {
            throw new IllegalArgumentException();
        }
        keys = (Key[]) new Comparable[maxN + 1];
        pq = new int[maxN + 1];
        qp = new int[maxN + 1];
        n = 0;
        key2Index = new HashMap<>();
        this.maxN = maxN;
        for (int i = 0; i <= maxN; i++) qp[i] = -1;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    /*public boolean contains(int i){
        if(i > this.maxN || i < 0){throw new IllegalArgumentException("invalid index supplied must be  0..(maxN−1)");}
        return qp[i] != -1; }*/
    public void insert(int i, Key key) {
        if (i > this.maxN || i < 0) {
            throw new IllegalArgumentException("invalid index supplied must be  0..(maxN−1)");
        }//a valid index?
        if (qp[i] != -1) {
            throw new IllegalArgumentException("index already inserted");
        }//index exist?
        key2Index.put(key, i);
        n++;//incr size
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;//associate id-key
        swim(n);
    }

    public Key minKey() {
        // take id at root of heap which is representing the minkey and use it to get the key
        if (isEmpty()) {
            throw new IllegalStateException("pqueue is empty");
        }//the pq is empty therefore it is in an illegal state to perform such an op.
        return keys[pq[1]];
    }

    public int delMin() {
        if (isEmpty()) {
            throw new IllegalStateException("pqueue is empty");
        }//the pq is empty therefore it is in an illegal state to perform such an op.
        //where do we delete min from pq? maybe in sink? but we use it after?
        int indexOfMin = pq[1];
        key2Index.remove(keyOf(indexOfMin));
        exch(1, n--);//is this correct exchange?
        sink(1);
        keys[pq[n + 1]] = null;//take the minIndex which has been put at the bottom of pq and use it to null out its corresponding key
        qp[pq[n + 1]] = -1;//the IndexVal no longer has a position in pq so set the indexVal-pos array to -1
        pq[n + 1] = -1;//remove from pq?
        return indexOfMin;
    }

    public Key keyOf(int i) {
        if (i > this.maxN || i < 0) {
            throw new IllegalArgumentException("invalid index supplied must be  0..(maxN−1)");
        }
        if (isEmpty()) {
            throw new IllegalStateException();
        }//the pq is empty therefore it is in an illegal state to perform such an op.
        return keys[i];
    }

    //what about changeKey or delete? maybe insert does it all?
    public int minIndex() {
        if (isEmpty()) {
            throw new IllegalStateException("pqueue is empty");
        }//the pq is empty therefore it is in an illegal state to perform such an op.
        return pq[1];
    }

    public int size() {
        return n;
    }

    private boolean less(int i, int j) {//less IN PRIORITY
        return keyOf(pq[i]).compareTo(keyOf(pq[j])) > 0;// if +1 is returned the i has greater relative value than j and therefore has LESS priority
    }

    private void exch(int i, int j) {//parent , child
        int temp = qp[pq[i]];//save parents old location to become location of child in pq
        qp[pq[i]] = qp[j];//set parent location to childs
        qp[pq[j]] = temp;//location of child index becomes parent in pq
        temp = pq[i];
        pq[i] = pq[j];//pq[qp[j]];//orig pos of i in pq, put j there
        pq[j] = temp;//pq[qp[i]];//orig pos of j in pq, put i there;
    }

    //we did have to change swim and sink b/c max -> min
    //i guess all the chnages could have been done in less but i felt it wasnt as intuitive linguistically
    //less(a,b) could mean a has less priority than b. in max that was literally a had less priority than b when a is less than b
    //but in min would be a has less priority than b when a is more than b or b is less than a. if problems then just do this
    private void swim(int k) {//if k == 1 its trivially sorted
        while (k > 1 && less(k / 2, k)) {//while child k is less than parent k/2 swim up -minPQ
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {//understand this and how it would woork in minPQ
        while (2 * k <= n) {//while were not at the bottom of the heap
            int j = 2 * k;
            if (j < n && less(j, j + 1)) {//if not bottom of heap and left child is more or eq to right child
                j++;
            }
            if (!less(k, j)) break;//if child is greater or equal to parent - leave be
            exch(k, j);//else sink
            k = j;//register index change
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new PQIterator();
    }

    private class PQIterator implements Iterator {
        Integer[] orderedKeys;
        int currentIndex;

        PQIterator() {
            orderedKeys = new Integer[n];
            for (int i = 0; i < keys.length; i++) {
                orderedKeys = key2Index.values().toArray(new Integer[n]);//why need to cast to key
            }
            currentIndex = 0;
            Arrays.sort(orderedKeys, new KeyComparator());
        }

        private class KeyComparator implements Comparator<Integer> {
            //default constructor - can just be a lambda
            @Override
            public int compare(Integer o1, Integer o2) {
                return (keyOf(o1)).compareTo(keyOf(o2));
            }
        }

        //order keySet by their comparable order and implement an iterator
        //of that
        @Override
        public boolean hasNext() {
            return currentIndex < orderedKeys.length;//if its equal to last index than no more next
        }

        @Override
        public Integer next() {// 0...1...2
            if (hasNext()) {
                return orderedKeys[currentIndex++];
            } else {
                throw new NoSuchElementException();
            }
        }
    }

}

