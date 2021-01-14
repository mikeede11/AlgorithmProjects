package edu.yu.introtoalgs;

import java.util.HashMap;

/** A class that provides a cache associating Integer-valued keys and values.
 * The implementation is constrained to a given capacity such that if the
 * number of entries exceeds the capacity, entries are removed to to maintain
 * the "does not exceed capacity" constraint.  When removing elements to
 * maintain the capacity constraint, the implementation follows the
 * "least-recently-used" policy.
 *
 */

public class IntegerLRUCache {
    private HashMap<Integer, Integer> cache;
    private HashMap<Integer, Integer> keyToArrayIndex;
    private Integer[] keyOrdering;
    private int lruIndex;
    private int mruIndex;
    private int capacity;
    //if(count/cap >10){do sometihng}
    /** Constructs an empty cache with the specified capacity.  The cache
     * implementation is forbidden from exceeding this capacity, but may choose
     * to use less than this capacity.
     *
     * @param capacity the initial capacity
     */
    public IntegerLRUCache(final int capacity) {
        if(capacity < 0){throw new IllegalArgumentException();}
        keyOrdering = new Integer[capacity*10];
        this.capacity = capacity;
        cache = new HashMap<>();
        keyToArrayIndex = new HashMap<>();
        mruIndex = -1;
        lruIndex = -1;//no elems put in yet when it adds it will add1 and subsequent index will be 0 - which is correct
    }

    /** Returns the cached value associated with the specified key, null if not
     * cached previously
     *
     * @param key the key whose associated value is to be returned
     * @return the previously cached value, or null if not previously cached
     * @throws IllegalArgumentException if the key is null
     */
    public Integer get(final Integer key) {
        if(key == null){throw new IllegalArgumentException();}
        Integer result = cache.get(key);
        if(result != null){//if get is called repeatedly on item maybe do something to prevent increment
            Integer index = keyToArrayIndex.get(key);
            keyOrdering[index] = null;
            keyOrdering[++mruIndex] = key;
            keyToArrayIndex.put(key,mruIndex);//i think update key index
            if(index == lruIndex){findNewLRUIndex();}
            if(mruIndex == 9*capacity){realignData();}
        }
        return result;
    }

    private void findNewLRUIndex(){//def think of border cases
         for(int i = lruIndex + 1; i < keyOrdering.length;i++){
             if(keyOrdering[i] != null){//0 or null hmmm
                 lruIndex = i;
                 break;
             }
         }
    }
    private void realignData(){
        int index = 0;
        for(int i = 0; i < keyOrdering.length;i++){//can be mruindex
            if(keyOrdering[i] != null){
                keyOrdering[index] = keyOrdering[i];
                keyToArrayIndex.put(keyOrdering[index], index);
                index++;
            }
        }
        lruIndex = 0;
        mruIndex = index - 1;
    }

    /** Associates the specified value with the specified key. If the cache
     * previously contained an association for this key, the old value is
     * replaced, and the key is now associated with the specified value.  If
     * inserting this item will cause the cache to exceed its capacity, the
     * method will evict some other item to maintain the capacity constraint.
     * The item selected is the least-recently-used ("accessed") item.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be cached
     * @return the value associated with this key if previously cached, otherwise
     * null
     * @throws IllegalArgumentException if either the key or value is null
     */
    public Integer put (final Integer key, final Integer value) {
        if(key == null || value == null){throw new IllegalArgumentException();}
        if(capacity == 0){return null;}
        Integer index = keyToArrayIndex.get(key);
        boolean oldIndexIsLRU = false;
        boolean keyWasAlreadyInCache = cache.get(key) != null;
        if(cache.size() == capacity && !keyWasAlreadyInCache){//will remove lru elem if size = cap && if its a new elem(if just modification no need to remove b/c wont go over cap)
            remove(keyOrdering[lruIndex]);//removes the LRU element from cache
        }
        if (keyWasAlreadyInCache){
            oldIndexIsLRU = lruIndex == index;
            keyOrdering[index] = null;//null out old index val. this is onlt step needed for modification. b/c map puts automatically modify.
        }
        //if its a modification - deal with
        Integer result = cache.put(key,value);
        keyOrdering[++mruIndex] = key;
        keyToArrayIndex.put(key, mruIndex);
        if(oldIndexIsLRU || lruIndex == -1){//if modification happened to be on lru
            findNewLRUIndex();
        }
        if(mruIndex == 9*capacity){realignData();}
        return result;
    }

    /** Removes the specified key-value mapping if present (returning the value
     * previously associated with the key), no-op returns null if no previous
     * association.
     *
     * @param key key whose mapping is to be removed
     * @return previous value associated with key, otherwise null
     * @throws IllegalArgumentException if the key is null
     */
    public Integer remove(Object key) {
        //what if LRU or MRU
        if(key == null){throw  new IllegalArgumentException();}
        Integer result = cache.remove((Integer)key);
        if(result != null) {
            Integer index = keyToArrayIndex.get(key);
            keyOrdering[index] = null;//MAYBE ANOTHER VAL
            keyToArrayIndex.remove(key);
            if(index == lruIndex){findNewLRUIndex();}
            //if(index == mruIndex){findNewMRUIndex();}//?
        }
        if(cache.size() == 0){
            lruIndex = mruIndex = -1;//can start fresh
        }
        return result;

    }


}
