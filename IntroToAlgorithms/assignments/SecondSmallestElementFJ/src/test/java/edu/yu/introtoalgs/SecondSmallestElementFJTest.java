package edu.yu.introtoalgs;

import org.junit.Test;
import static org.junit.Assert.*;

public class SecondSmallestElementFJTest {
    @Test
    public void normalInputUniqueElemsRandomOrderSeqTest(){
        int[] arr = {5,9,7,6,4,8,0,1,67,54,22,90,34};
        SecondSmallestElementFJ trial1 = new SecondSmallestElementFJ(arr, 1.0);
        assertEquals(1,trial1.secondSmallest());
    }
    @Test
    public void secondSmallestAtBegUniqueElemsRandomOrderSeqTest(){
        int[] arr = {2,9,7,6,4,8,0,5,67,54,22,90,34};
        SecondSmallestElementFJ trial1 = new SecondSmallestElementFJ(arr, 1.0);
        assertEquals(2,trial1.secondSmallest());
    }
    @Test
    public void secondSmallestAtEndUniqueElemsRandomOrderSeqTest(){
        int[] arr = {34,9,7,6,4,8,0,5,67,54,22,90,3};
        SecondSmallestElementFJ trial1 = new SecondSmallestElementFJ(arr, 1.0);
        assertEquals(3,trial1.secondSmallest());
    }
    @Test
    public void smallestAtEndUniqueElemsRandomOrderSeqTest(){
        int[] arr = {34,9,7,6,4,8,3,5,67,54,22,90,0};
        SecondSmallestElementFJ trial1 = new SecondSmallestElementFJ(arr, 1.0);
        assertEquals(3,trial1.secondSmallest());
    }
    @Test
    public void smallestAtBegUniqueElemsRandomOrderSeqTest(){
        int[] arr = {0,9,7,6,4,8,3,5,67,54,22,90,34};
        SecondSmallestElementFJ trial1 = new SecondSmallestElementFJ(arr, 1.0);
        assertEquals(3,trial1.secondSmallest());
    }
    @Test
    public void dupElemsRandomOrderSeqTest(){
        int[] arr = {34,34,9,9,7,7,6,6,4,4,8,8,3,3,5,5,67,67,54,54,22,22,90,90,27,27};
        SecondSmallestElementFJ trial1 = new SecondSmallestElementFJ(arr, 1.0);
        assertEquals(4,trial1.secondSmallest());
    }
    @Test
    public void smallestDupAtBegUniqueElemsRandomOrderSeqTest(){
        int[] arr = {0,0,9,7,6,4,8,3,5,67,54,22,90,34};
        SecondSmallestElementFJ trial1 = new SecondSmallestElementFJ(arr, 1.0);
        assertEquals(3,trial1.secondSmallest());
    }
    @Test
    public void secondSmallestDupAtBegUniqueElemsRandomOrderSeqTest(){
        int[] arr = {3,3,9,7,6,4,8,0,5,67,54,22,90,34};
        SecondSmallestElementFJ trial1 = new SecondSmallestElementFJ(arr, 1.0);
        assertEquals(3,trial1.secondSmallest());
    }
    @Test(expected = IllegalArgumentException.class)
    public void noTwoUniqueSeqTest(){
        int[] arr = {5,5,5,5,5,5,5,5,5,5,5,5,5};
        SecondSmallestElementFJ trial1 = new SecondSmallestElementFJ(arr, 1.0);
        trial1.secondSmallest();
    }
    @Test
    public void incrOrderSeqTest(){
        int[] arr = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
        SecondSmallestElementFJ trial1 = new SecondSmallestElementFJ(arr, 1.0);
        assertEquals(2,trial1.secondSmallest());
    }
    @Test
    public void decrOrderSeqTest(){
        int[] arr = {16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
        SecondSmallestElementFJ trial1 = new SecondSmallestElementFJ(arr, 1.0);
        assertEquals(2,trial1.secondSmallest());
    }
    @Test
    public void largeNRandomSeqTest(){
        int n = 10000000;//ten mil
        int[] arr = new int[n];
        for(int i = 0; i < n; i++){
            if(i == n/2){
                arr[i] = -1;//2nd lowest val
            }
            else if(i == n/10){
                arr[i] = -2;
            }
            else {
                arr[i] = (int) (Math.random() * n);//can flip for efficiency
            }
        }
        SecondSmallestElementFJ largeTrial = new SecondSmallestElementFJ(arr,1.0);
        assertEquals(-1, largeTrial.secondSmallest());
    }
    @Test
    public void workBigONRandomSeqTest(){
        int n = 100000;//ten mil
        int halfN = n/2;
        //int[] arr1 = new int[halfN];
        long firstStartTime = 0;
        long firstEndTime = 0;
        long firstTime = 0;
        long secondStartTime = 0;
        long secondEndTime = 0;
        long secondTime = 0;
        double sumRatio = 0.0;
        double ratio = 0.0;
        int iterations = 10;
        for(int i = 0; i < iterations; i++) {
            int[] arr1 = new int[halfN];
            for (int j = 0; j < halfN; j++) {
                if (j == halfN / 2) {
                    arr1[j] = -1;//2nd lowest val
                } else if (j == n / 10) {
                    arr1[j] = -2;
                } else {
                    arr1[j] = (int) (Math.random() * halfN);//can flip for efficiency
                }
            }
            int[] arr2 = new int[n];
            for (int j = 0; j < n; j++) {
                if (j == n / 2) {
                    arr2[j] = -1;//2nd lowest val
                } else if (j == n / 10) {
                    arr2[j] = -2;
                } else {
                    arr2[j] = (int) (Math.random() * n);//can flip for efficiency
                }

            }
            SecondSmallestElementFJ largeTrial1 = new SecondSmallestElementFJ(arr1, 1.0);
            firstStartTime = System.nanoTime();
            largeTrial1.secondSmallest();
            firstEndTime = System.nanoTime();
            firstTime = firstEndTime - firstStartTime;
            SecondSmallestElementFJ largeTrial2 = new SecondSmallestElementFJ(arr2, 1.0);
            secondStartTime = System.nanoTime();
            largeTrial2.secondSmallest();
            secondEndTime = System.nanoTime();
            secondTime = secondEndTime - secondStartTime;
            sumRatio += ((double) secondTime) / (double) firstTime;
            ratio = (((double) secondTime) / (double) firstTime);
            //System.out.println("doubled array size: " + n +"\noriginal array size: " + halfN + "\nTheir performance ratio: " + ratio);
            n*= 2;
            halfN = n/2;
        }
        ratio = sumRatio/iterations;
        //System.out.println("Average ratio over " + iterations + " iterations: " + ratio);
        assertTrue(ratio < 3.5);
    }
    @Test
    public void workBigONdecrMaxWorkTest(){//reason why large is faster might be predictability
        int n = 100000000;//ten mil
        int halfN = n/2;
        int[] arr1 = new int[halfN];
        for(int i = halfN - 1; i > 0; i--){
                arr1[i] = i;//can flip for efficiency
        }
        arr1[halfN-2] = 2;
        arr1[halfN-1] = 1;
        int[] arr2 = new int[n];
        for(int i = n - 1; i > 0; i--){
            arr2[i] = i;//can flip for efficiency
        }
        arr2[n-2] = 2;
        arr2[n-1] = 1;
        SecondSmallestElementFJ largeTrial1 = new SecondSmallestElementFJ(arr1,1.0);
        long firstStartTime = System.currentTimeMillis();
        largeTrial1.secondSmallest();
        long firstEndTime = System.currentTimeMillis();
        long firstTime = firstEndTime - firstStartTime;
        SecondSmallestElementFJ largeTrial2 = new SecondSmallestElementFJ(arr2,1.0);
        long secondStartTime = System.currentTimeMillis();
        largeTrial2.secondSmallest();
        long secondEndTime = System.currentTimeMillis();
        long secondTime = secondEndTime - secondStartTime;
        double ratio = ((double)secondTime)/(double)firstTime;
        System.out.println(ratio);
        assertTrue(ratio < 3.5);
    }

    /*@Test
    public void parallelSpeedUpTest(){
        int n = 100000000;//ten mil
        int[] arr1 = new int[n];
        for(int i = 0; i < n; i++){
            if(i == n/2){
                arr1[i] = -1;//2nd lowest val
            }
            else if(i == n/10){
                arr1[i] = -2;
            }
            else {
                arr1[i] = (int) (Math.random() * n);//can flip for efficiency
            }
        }
        long firstStartTime = 0;
        long firstEndTime = 0;
        long firstTime = 0;
        long secondStartTime = 0;
        long secondEndTime = 0;
        long secondTime = 0;
        double ratio = 0.0;
        int i = 1;
        long[] seqTimes = new long[50];
        long[] parTimes = new long[50];
        int[] fracThreshold = new int[50];
        int maxNumOfParallelization = 50;
        int index = 0;
        while (i <= maxNumOfParallelization){
            SecondSmallestElementFJ largeTrial1 = new SecondSmallestElementFJ(arr1, 1.0);
            firstStartTime = System.currentTimeMillis();
            assertEquals(-1, largeTrial1.secondSmallest());
            firstEndTime = System.currentTimeMillis();
            firstTime = firstEndTime - firstStartTime;
            SecondSmallestElementFJ largeTrial2 = new SecondSmallestElementFJ(arr1, 1.0/i);
            secondStartTime = System.currentTimeMillis();
            assertEquals(-1,largeTrial2.secondSmallest());
            secondEndTime = System.currentTimeMillis();
            secondTime = secondEndTime - secondStartTime;
            ratio = ((double) firstTime) / (double) secondTime;//seq/par time
            System.out.println("frac/Parallelization: " + i + "\nParralleization is " + ratio + "X the speed of Sequential processing");
            seqTimes[index] = firstTime;
            parTimes[index] = secondTime;
            fracThreshold[index]= i;
            index++;
            i += 1;
        }
        for(int j = 0; j < 50; j++){
            System.out.println(seqTimes[j]);
        }
        System.out.println("Paralllel Times");
        for(int j = 0; j < 50; j++){//y
            //System.out.println("Paralllel Times");
            System.out.println(parTimes[j]);
        }
        System.out.println("fractions");
        for(int j = 0; j < 50; j++){//x
            //System.out.println("fractions");
            System.out.println(fracThreshold[j]);
        }
        assertTrue(true);
    }*/
}
