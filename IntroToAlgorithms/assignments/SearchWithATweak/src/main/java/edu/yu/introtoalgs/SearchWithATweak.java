package edu.yu.introtoalgs;

import java.util.ArrayList;
import java.util.List;

public class SearchWithATweak {
    public static int findFirstInstance(final List<Integer> list, final int key) {
        //determine if list is increasing or decreasing order
        if(list.size() == 0){return -1;}
        if(isIncreasing(list)) {
            int keyValIndex = findKeyValueIncr(list, 0, list.size() - 1, key);
            if(keyValIndex == -1){return -1;}
            if(keyValIndex == 0){return  keyValIndex;}
            if (list.get(keyValIndex) > list.get(keyValIndex - 1)) {
                return keyValIndex;//findKeyValueIncr(list, 0, list.size() - 1, key);
            } else {
                return getFirst(list, 0, keyValIndex, key);
            }
        }
        else{
            int keyValIndex = findKeyValueDecr(list, 0 , list.size()-1, key);
            if(keyValIndex == -1){return -1;}
            if(list.get(keyValIndex) < list.get(keyValIndex - 1)){
                return keyValIndex;
            }
            else{
                return getFirst(list, 0, keyValIndex,key);
            }
        }
    }

    //use a binary  search algorithm to find desired number
    //once the number is found if the number to its left is equal then [do binary search algorithm again] until number to the left is less than desired number(first instance
    public static int elementEqualToItsIndex(final List<Integer> list) {
        //determine if list is increasing or decreasing
        if(list.size() == 0){return -1;}
        if(isIncreasing(list)){
            int keyValIndex = 0;
            if(list.get(keyValIndex) > keyValIndex){
                return -1;
            }
            if(list.get(keyValIndex) == keyValIndex){
                return keyValIndex;
            }
            return binSearchElemEqualToIndexIncr(list, 1, list.size() - 1);
        }
        else{
            int keyValIndex = list.size() - 1;
            if(list.get(keyValIndex) > keyValIndex){
                return -1;
            }
            if(list.get(keyValIndex) == keyValIndex){
                return keyValIndex;
            }
            return binSearchElemEqualToIndexDecr(list, 0, list.size() - 1);

        }
        //first look at most extreme value which would be the first in incr. and the last in decr. if its greater than(incr) or if its lower than(decr.) the index return -1
        //then find that point with a binary search alg. if mid val is greater in incr. go to the left split if less go to right. if mid val is less in decr. goto right, if greater go to left.
        //do this until you find the index
    }

    private static boolean isIncreasing(List<Integer> list) {
        return list.get(0) <= list.get(list.size() - 1);
    }

    private static int findKeyValueIncr(List<Integer> list, int first, int last, int key) {
        if (last >= first) {
            int mid = first + (last - first) / 2;
            if (list.get(mid) == key) {
                return mid;
            }
            if (list.get(mid) > key) {
                return findKeyValueIncr(list, first, mid - 1, key);//search in left subarray
            } else {
                return findKeyValueIncr(list, mid + 1, last, key);//search in right subarray
            }
        }
        return -1;
    }
    private static int findKeyValueDecr(List<Integer> list, int first, int last, int key) {
        if (last >= first) {
            int mid = first + (last - first) / 2;
            if (list.get(mid) == key) {
                return mid;
            }
            if (list.get(mid) > key) {
                return findKeyValueDecr(list, mid + 1, last, key);//search in left subarray
            } else {
                return findKeyValueDecr(list, first, mid - 1, key);//search in right subarray
            }
        }
        return -1;
    }

    private static int getFirst(List<Integer> list, int first, int last, int key) {
        int mid = first + (last - first) / 2;
        if (list.get(mid) == key && (mid == 0 || list.get(mid) != list.get(mid - 1))) {
            return mid;
        }
        if (list.get(mid) == key && list.get(mid) == list.get(mid - 1)) {
            return getFirst(list, first, mid - 1, key);
        } else {
            return getFirst(list, mid + 1, last, key);
        }
    }
    private static int binSearchElemEqualToIndexIncr(List<Integer> list, int first, int last){
        if (last >= first) {
            int mid = first + (last - first) / 2;
            if (list.get(mid) == mid) {
                return mid;
            }
            if (list.get(mid) > mid) {
                return binSearchElemEqualToIndexIncr(list, first, mid - 1);//search in left subarray
            } else {
                return binSearchElemEqualToIndexIncr(list, mid + 1, last);//search in right subarray
            }
        }
        return -1;
    }
    private static int binSearchElemEqualToIndexDecr(List<Integer> list, int first, int last){
        if (last >= first) {
            int mid = first + (last - first) / 2;
            if (list.get(mid) == mid) {
                return mid;
            }
            if (list.get(mid) < mid) {
                return binSearchElemEqualToIndexDecr(list, first, mid - 1);//search in left subarray
            } else {
                return binSearchElemEqualToIndexDecr(list, mid + 1, last);//search in right subarray
            }
        }
        return -1;
    }
    /*public static void main(String[] args){
        ArrayList<Integer> pracList = new ArrayList<Integer>();
        for(int i = 10; i > 0; i--){
            pracList.add(i);
            pracList.add(i);
        }
        System.out.println(findFirstInstance(pracList, 5));
        ArrayList<Integer> valEqIndexList = new ArrayList<Integer>();
        for(int i = -1; i < 15; i++){
            valEqIndexList.add(i);
        }
        valEqIndexList.add(16, 16);
        for(int i = 56 ; i < 69; i++){
            valEqIndexList.add(i);
        }
        System.out.println(elementEqualToItsIndex(valEqIndexList));//should be 16
    }*/
}
