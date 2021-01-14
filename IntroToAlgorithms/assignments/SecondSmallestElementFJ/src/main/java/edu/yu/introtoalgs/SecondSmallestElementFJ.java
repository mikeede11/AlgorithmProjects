package edu.yu.introtoalgs;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class SecondSmallestElementFJ {
    private int[] array;
    private  double frac;
    private int threshold;
    /** Constructor .
     *
     * @param array input that we ’ll search
     * for the second smallest element ,
     * cannot be null .
     * @param fractionToApplySequentialCutoff
     * a fraction of the original number of
     * array elements : when the remaining
     * elements dip below this fraction , the
     * fork - join algorithm will process using
     * a sequential algorithm . Cannot be
     * less than 0.0 (fork - join processing
     * for all but arrays of size 1) and
     * cannot exceed 1.0 (no fork - join
     * processing will take place at all).
     */
    public SecondSmallestElementFJ( final int [] array , final double fractionToApplySequentialCutoff ) {//O(1)
        if(array == null || fractionToApplySequentialCutoff < 0.0 || fractionToApplySequentialCutoff > 1.0){throw new IllegalArgumentException();}
        this.array = array;
        this.frac = fractionToApplySequentialCutoff;
        if(fractionToApplySequentialCutoff >= 1){
            this.threshold = array.length;//-1?
        }
        else if(fractionToApplySequentialCutoff * array.length <= 1){
            this.threshold = 1;
        }
        else {
            this.threshold = (int) (array.length * fractionToApplySequentialCutoff);
        }
        //what if more cutoff then elements - no prob b/c it creates threads accordingly -PROBLEM
    }

    class SecondSmallestElemFinder extends RecursiveTask<Integer[]>{
        private final int low;
        private final int high;
        private final int[] array;
        private final int threshold;

        SecondSmallestElemFinder(int threshold, int[] array, int low, int high){ //O(1)
            this.low = low;
            this.high = high;
            this.array = array;
            this.threshold = threshold;
        }
        protected Integer[] compute() {
            if(high - low <= threshold){
                return getSmallestTwoElemsSeq(this.array, low, high);
            }
            else{
                SecondSmallestElemFinder left = new SecondSmallestElemFinder(threshold, array, low, (high + low)/2);
                SecondSmallestElemFinder right = new SecondSmallestElemFinder(threshold, array, (high + low)/2, high);
                left.fork();
                final Integer[] rightArr = right.compute();
                final Integer[] leftArr = left.join();
                return resolveTwoLowest(leftArr, rightArr);//more parallelized more work, but can only parrelize it so much 8 and since this method is constant it shouldnt be such a big deal
            }
        }
        protected Integer[] getSmallestTwoElemsSeq(int[] array, int low, int high){
            Integer[] smallestTwoElems = new Integer[2];
            smallestTwoElems[0] = Math.min(array[low], array[low+1]);
            //loop through until 0 elem doesnt equal 1?
            smallestTwoElems[1] = Math.max(array[low],array[low +1]);
            int secondSmallestNumInCaseDuplSmallestElem = Integer.MAX_VALUE;
            for(int i = low + 2; i < high; i++){//Worst Case 0(4n)
                if(array[i] != smallestTwoElems[0]) {
                    secondSmallestNumInCaseDuplSmallestElem = Math.min(secondSmallestNumInCaseDuplSmallestElem, array[i]);
                }
                if(array[i] < smallestTwoElems[1] && array[i] != smallestTwoElems[0]){
                    smallestTwoElems[1] = array[i];
                    if(smallestTwoElems[1] < smallestTwoElems[0]){
                        int temp = smallestTwoElems[0];
                        smallestTwoElems[0] = smallestTwoElems[1];
                        smallestTwoElems[1] = temp;
                    }
                }
            }
            if(smallestTwoElems[0].equals(smallestTwoElems[1])){
                smallestTwoElems[1] = secondSmallestNumInCaseDuplSmallestElem;
            }
            return smallestTwoElems;
        }
        protected Integer[] resolveTwoLowest(Integer[] arr1, Integer[] arr2){
            Integer[] combinedArr = {arr1[0], arr1[1], arr2[0], arr2[1]};
            Integer[] resolvedArr = new Integer[2];
            for(int i = 1; i < combinedArr.length; i++){//O(16) (constant) 4*4
                int j = i;
                while(j > 0 && combinedArr[j-1] > combinedArr[j]) {
                    Integer temp = combinedArr[j - 1];
                    combinedArr[j - 1] = combinedArr[j];
                    combinedArr[j] = temp;
                    j = j - 1;
                }
            }
            if(combinedArr[0] == combinedArr[1]) {
                for (int i = 2; i < combinedArr.length; i++) {//O(4)
                    if (combinedArr[i] != combinedArr[0]) {
                        combinedArr[1] = combinedArr[i];
                        break;
                    }
                }
            }
            resolvedArr[0] = combinedArr[0];
            resolvedArr[1] = combinedArr[1];
            return resolvedArr;
        }
    }

    /** Returns the second smallest
     * unique element of the input array .
     *
     * Example : if array is [1 , 7 , 4 , 3 , 6] ,
     * then secondSmallest ( array ) == 3.
     *
     * Example : if array is [6 , 1 , 4 , 3 , 5 ,
     * 2 , 1] , secondSmallest ( array ) == 2.
     2
     *
     * @return second smallest unique element
     * of the input
     * @throws IllegalArgumentException if
     * the input doesn ’t contain a minimum of
     * two unique elements .
     */
    public int secondSmallest () {
        //array size or elements filled in - if not filled in than default value is zero which is valid so i dont know
        int parallelism = Runtime.getRuntime().availableProcessors();
        ForkJoinTask<Integer[]> task = new SecondSmallestElemFinder(this.threshold, this.array, 0, array.length);
        final ForkJoinPool fjpool = new ForkJoinPool(parallelism);
        Integer[] twoSmallestValues = fjpool.invoke(task);
        fjpool.shutdown();
        int secondSmallestVal = twoSmallestValues[1];
        if(secondSmallestVal == Integer.MAX_VALUE){throw new IllegalArgumentException();}
        return secondSmallestVal;
    }
}
