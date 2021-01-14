package edu.yu.introtoalgs;

import java.util.*;

public class MergeAnInterval {

    /** An immutable class, holds a left and right integer-valued pair that
     * defines a closed interval
     */
    public static class Interval implements Comparable<Interval>{
        public final int left;
        public final int right;

        /** Constructor
         *
         * @param l the left endpoint of the interval
         * @param r the right endpoint of the interval
         */
        public Interval(int l, int r) {
            if(l >= r){throw  new IllegalArgumentException();}
            this.left = l;
            this.right = r;
        }

        @Override
        public int compareTo(Interval that) {
            if(this.left < that.left && this.right < that.left){return -1;}//this time is earlier than that time
            else if(this.left > that.right && this.right > that.right){return 1;}//this time is later than that time
            else{return 0;}//this time overlaps that time
        }
        @Override
        public String toString(){
            return "[" + this.left + "," + this.right + "]";
        }
        @Override
        public boolean equals(Object that){
            if(that instanceof Interval) {
                Interval thatInterval = (Interval) that;
                return this.left == thatInterval.left & this.right == thatInterval.right;
            }
            else{
                return false;//not even an interval
            }
        }
        @Override
        public int hashCode(){
            return this.left * 31 + this.right * 31;
        }
        // fill in the rest yourself!
    } // Interval class

    /** Merges the new interval into an existing set of disjoint intervals.
     *
     * @param intervals the existing set of intervals
     * @param newInterval the interval to be added
     * @return a new set of disjoint intervals containing the original intervals
     * and the new interval, merging the new interval if necessary into existing
     * interval(s), to preseve the "disjointedness" property.
     */
    public static Set<Interval> merge(final Set<Interval> intervals, Interval newInterval)
    {   //assume disjoint set
        if(intervals == null || newInterval == null){throw new IllegalArgumentException();}
        List<Interval> intervalsList = new ArrayList<>(intervals);
        Collections.sort(intervalsList);//TEST IF SORTED PROPERLY
        boolean overlap = false;
        ///TASK 1 merge
        for(int i = 0; i < intervalsList.size(); i++){
            if(newInterval.left < intervalsList.get(i).left && newInterval.right > intervalsList.get(i).right){
                intervalsList.set(i, new Interval(newInterval.left, newInterval.right));
                overlap = true;
            }
            else if(newInterval.left <= intervalsList.get(i).left && newInterval.right <= intervalsList.get(i).right && newInterval.right >= intervalsList.get(i).left){//be wary of the equals
                intervalsList.set(i, new Interval(newInterval.left, intervalsList.get(i).right));
                overlap = true;
            }
            else if(newInterval.left >= intervalsList.get(i).left && newInterval.left <= intervalsList.get(i).right && newInterval.right >= intervalsList.get(i).right){
                intervalsList.set(i, new Interval(intervalsList.get(i).left, newInterval.right));
                overlap = true;
            }
        }
        ///Task 2 overlap
        if(!overlap){
            intervalsList.add(newInterval);
        }
        else {
            ///Task 3 reconcile overlapping merges
            for(int i = intervalsList.size() - 1; i > 0; i--){
                int r1 = intervalsList.get(i).right;
                int r2 = intervalsList.get(i - 1).right;
                int l1 = intervalsList.get(i).left;
                int l2 = intervalsList.get(i - 1).left;
                if(l1 == l2 && r1 > r2){
                    intervalsList.set(i - 1, new Interval(l2, r1));
                }
            }
            for(int i = 0; i < intervalsList.size() - 1; i++){
                int r1 = intervalsList.get(i).right;
                int r2 = intervalsList.get(i + 1).right;
                int l1 = intervalsList.get(i).left;
                int l2 = intervalsList.get(i + 1).left;
                if(r1 == r2 && l1 < l2){
                    intervalsList.set(i + 1, new Interval(l1,r2));
                }
            }
            for(int i = 0; i < intervalsList.size() - 1; i++){
                int r1 = intervalsList.get(i).right;
                int r2 = intervalsList.get(i + 1).right;
                int l1 = intervalsList.get(i).left;
                int l2 = intervalsList.get(i + 1).left;
                if(r1 >= l2){
                    intervalsList.set(i, new Interval(l1,r2));
                    intervalsList.set(i + 1, new Interval(l1, r2));
                }
            }
        }

        return new HashSet<>(intervalsList);
    }
    public static void main(String[] args){
        Set<Interval> test = new HashSet<>();
        test.add(new Interval(1,2));
        test.add(new Interval(3,4));
        test.add(new Interval(5,6));
        test.add(new Interval(7,9));
        Set<Interval> res = merge(test, new Interval(3,8));
        for(Interval i: res){
            System.out.println(i);
        }
        test.add(new Interval(15,17));
        System.out.println(test.equals(res));
        Interval a = new Interval(0,2);
        System.out.println(a.equals(new Interval(0,2)));

    }
}
