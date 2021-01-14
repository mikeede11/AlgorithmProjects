package edu.yu.introtoalgs;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import edu.yu.introtoalgs.MergeAnInterval.*;

import static org.junit.Assert.*;

public class MergeAnIntervalTest {
    @Test
    public void newIntervalNoOverlap() {
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0, 2));
        actualSet.add(new Interval(3, 4));
        actualSet.add(new Interval(5, 6));
        actualSet.add(new Interval(7, 8));
        actualSet.add(new Interval(9, 10));
        actualSet.add(new Interval(11, 12));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0, 2));
        expectedSet.add(new Interval(3, 4));
        expectedSet.add(new Interval(5, 6));
        expectedSet.add(new Interval(7, 8));
        expectedSet.add(new Interval(9, 10));
        expectedSet.add(new Interval(11, 12));
        expectedSet.add(new Interval(13, 18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(13, 18));
        assertEquals("NONO NO", expectedSet, actualSet);
    }

    @Test
    public void oNLogNPerformance() {
        int left = 0;
        int right = 1;
        Set<Interval> actualSet = new HashSet<>();
        for (int i = 0; i < 100000; i++) {
            actualSet.add(new Interval(left, right));
            left += 2;
            right += 2;
        }
        long startTime = System.currentTimeMillis();
        MergeAnInterval.merge(actualSet, new Interval(10008, 10010));
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        Set<Interval> actualSet2 = new HashSet<>();
        left = 900000;
        right = 900003;
        for (int i = 0; i < 200000; i++) {
            actualSet2.add(new Interval(left, right));
            left -= 4;//NEED TO DO A JUMP BIGGER THAN THE GAP IN THE FIRST PLACE OR ELSE NOT DISJOINT
            right -= 4;
        }
        long startTime1 = System.currentTimeMillis();
        MergeAnInterval.merge(actualSet2, new Interval(64, 19881));
        long endTime1 = System.currentTimeMillis();
        double totalTime1 = (double) (endTime1 - startTime1);
        double ratio = totalTime1 / totalTime;
        assertTrue(ratio < 2.2);
    }

    @Test
    public void testOverlapOnLeftSideOfNewIntervalMiddleOfIntervals() {
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0, 2));
        actualSet.add(new Interval(3, 4));
        actualSet.add(new Interval(5, 8));
        actualSet.add(new Interval(12, 15));
        actualSet.add(new Interval(20, 21));
        actualSet.add(new Interval(25, 31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0, 2));
        expectedSet.add(new Interval(3, 4));
        expectedSet.add(new Interval(5, 10));
        expectedSet.add(new Interval(12, 15));
        expectedSet.add(new Interval(20, 21));
        expectedSet.add(new Interval(25, 31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(7, 10));
        assertEquals("NONO NO", expectedSet, actualSet);
    }

    @Test
    public void testOverlapOnRightSideOfNewIntervalMiddleOfIntervals() {
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0, 2));
        actualSet.add(new Interval(3, 4));
        actualSet.add(new Interval(5, 8));
        actualSet.add(new Interval(12, 15));
        actualSet.add(new Interval(20, 21));
        actualSet.add(new Interval(25, 31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0, 2));
        expectedSet.add(new Interval(3, 4));
        expectedSet.add(new Interval(5, 8));
        expectedSet.add(new Interval(10, 15));
        expectedSet.add(new Interval(20, 21));
        expectedSet.add(new Interval(25, 31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(10, 14));
        assertEquals("NONO NO", expectedSet, actualSet);
    }

    @Test
    public void testOverlapOnLeftSideOfNewIntervalLeftEndOfOldInterval() {
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0, 2));
        actualSet.add(new Interval(3, 4));
        actualSet.add(new Interval(5, 8));
        actualSet.add(new Interval(9, 10));
        actualSet.add(new Interval(12, 15));
        actualSet.add(new Interval(20, 21));
        actualSet.add(new Interval(25, 31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0, 2));
        expectedSet.add(new Interval(3, 4));
        expectedSet.add(new Interval(5, 11));
        expectedSet.add(new Interval(12, 15));
        expectedSet.add(new Interval(20, 21));
        expectedSet.add(new Interval(25, 31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(5, 11));
        assertEquals("NONO NO", expectedSet, actualSet);
    }

    @Test
    public void testOverlapOnLeftSideOfNewIntervalRightEndOfOldInterval() {
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0, 2));
        actualSet.add(new Interval(3, 4));
        actualSet.add(new Interval(5, 8));
        actualSet.add(new Interval(12, 15));
        actualSet.add(new Interval(20, 21));
        actualSet.add(new Interval(25, 31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0, 2));
        expectedSet.add(new Interval(3, 4));
        expectedSet.add(new Interval(5, 10));
        expectedSet.add(new Interval(12, 15));
        expectedSet.add(new Interval(20, 21));
        expectedSet.add(new Interval(25, 31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(8, 10));
        assertEquals("NONO NO", expectedSet, actualSet);
    }

    @Test
    public void testOverlapOnRightSideOfNewIntervalRightEndOfOldInterval() {
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0, 2));
        actualSet.add(new Interval(3, 4));
        actualSet.add(new Interval(5, 8));
        actualSet.add(new Interval(12, 15));
        actualSet.add(new Interval(20, 21));
        actualSet.add(new Interval(25, 31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0, 2));
        expectedSet.add(new Interval(3, 4));
        expectedSet.add(new Interval(5, 8));
        expectedSet.add(new Interval(10, 15));
        expectedSet.add(new Interval(20, 21));
        expectedSet.add(new Interval(25, 31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(10, 15));
        assertEquals("NONO NO", expectedSet, actualSet);
    }

    @Test
    public void testOverlapOnRightSideOfNewIntervalLeftEndOfOldInterval() {
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0, 2));
        actualSet.add(new Interval(3, 4));
        actualSet.add(new Interval(5, 8));
        actualSet.add(new Interval(12, 15));
        actualSet.add(new Interval(20, 21));
        actualSet.add(new Interval(25, 31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0, 2));
        expectedSet.add(new Interval(3, 4));
        expectedSet.add(new Interval(5, 8));
        expectedSet.add(new Interval(10, 15));
        expectedSet.add(new Interval(20, 21));
        expectedSet.add(new Interval(25, 31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(10, 12));
        assertEquals("NONO NO", expectedSet, actualSet);
    }

    @Test
    public void testOverlapOnBothSidesMiddle() {
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0, 2));
        actualSet.add(new Interval(3, 4));
        actualSet.add(new Interval(5, 8));
        actualSet.add(new Interval(12, 15));
        actualSet.add(new Interval(20, 21));
        actualSet.add(new Interval(25, 31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0, 2));
        expectedSet.add(new Interval(3, 4));
        expectedSet.add(new Interval(5, 15));
        expectedSet.add(new Interval(20, 21));
        expectedSet.add(new Interval(25, 31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(6, 14));
        assertEquals("NONO NO", expectedSet, actualSet);
    }

    @Test
    public void testOverlapOnBothSidesLeftSideLeftEndRightMiddle() {
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0, 2));
        actualSet.add(new Interval(3, 4));
        actualSet.add(new Interval(5, 8));
        actualSet.add(new Interval(12, 15));
        actualSet.add(new Interval(20, 21));
        actualSet.add(new Interval(25, 31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0, 2));
        expectedSet.add(new Interval(3, 4));
        expectedSet.add(new Interval(5, 15));
        expectedSet.add(new Interval(20, 21));
        expectedSet.add(new Interval(25, 31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(5, 13));
        assertEquals("NONO NO", expectedSet, actualSet);
    }

    @Test
    public void testOverlapOnBothSidesLeftSideLeftEndRightLeftEnd() {
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0, 2));
        actualSet.add(new Interval(3, 4));
        actualSet.add(new Interval(5, 8));
        actualSet.add(new Interval(12, 15));
        actualSet.add(new Interval(20, 21));
        actualSet.add(new Interval(25, 31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0, 2));
        expectedSet.add(new Interval(3, 4));
        expectedSet.add(new Interval(5, 15));
        expectedSet.add(new Interval(20, 21));
        expectedSet.add(new Interval(25, 31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(5, 12));
        assertEquals("NONO NO", expectedSet, actualSet);
    }

    @Test
    public void testOverlapOnBothSidesLeftSideLeftEndRightRightEnd() {
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0, 2));
        actualSet.add(new Interval(3, 4));
        actualSet.add(new Interval(5, 8));
        actualSet.add(new Interval(12, 15));
        actualSet.add(new Interval(20, 21));
        actualSet.add(new Interval(25, 31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0, 2));
        expectedSet.add(new Interval(3, 4));
        expectedSet.add(new Interval(5, 15));
        expectedSet.add(new Interval(20, 21));
        expectedSet.add(new Interval(25, 31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(5, 15));
        assertEquals("NONO NO", expectedSet, actualSet);
    }

    @Test
    public void testOverlapOnBothSidesLeftSideMiddleRightLeftEnd() {
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0, 2));
        actualSet.add(new Interval(3, 4));
        actualSet.add(new Interval(5, 8));
        actualSet.add(new Interval(12, 15));
        actualSet.add(new Interval(20, 21));
        actualSet.add(new Interval(25, 31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0, 2));
        expectedSet.add(new Interval(3, 4));
        expectedSet.add(new Interval(5, 15));
        expectedSet.add(new Interval(20, 21));
        expectedSet.add(new Interval(25, 31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(6, 12));
        assertEquals("NONO NO", expectedSet, actualSet);
    }

    @Test
    public void testOverlapOnBothSidesLeftSideMiddleRightRightEnd() {
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0, 2));
        actualSet.add(new Interval(3, 4));
        actualSet.add(new Interval(5, 8));
        actualSet.add(new Interval(12, 15));
        actualSet.add(new Interval(20, 21));
        actualSet.add(new Interval(25, 31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0, 2));
        expectedSet.add(new Interval(3, 4));
        expectedSet.add(new Interval(5, 15));
        expectedSet.add(new Interval(20, 21));
        expectedSet.add(new Interval(25, 31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(6, 15));
        assertEquals("NONO NO", expectedSet, actualSet);
    }

    @Test
    public void testOverlapOnBothSidesLeftSideRightEndRightRightEnd() {
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0, 2));
        actualSet.add(new Interval(3, 4));
        actualSet.add(new Interval(5, 8));
        actualSet.add(new Interval(12, 15));
        actualSet.add(new Interval(20, 21));
        actualSet.add(new Interval(25, 31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0, 2));
        expectedSet.add(new Interval(3, 4));
        expectedSet.add(new Interval(5, 15));
        expectedSet.add(new Interval(20, 21));
        expectedSet.add(new Interval(25, 31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(8, 15));
        assertEquals("NONO NO", expectedSet, actualSet);
    }

    @Test
    public void testOverlapOnBothSidesLeftSideRightEndRightLeftEnd() {
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0, 2));
        actualSet.add(new Interval(3, 4));
        actualSet.add(new Interval(5, 8));
        actualSet.add(new Interval(12, 15));
        actualSet.add(new Interval(20, 21));
        actualSet.add(new Interval(25, 31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0, 2));
        expectedSet.add(new Interval(3, 4));
        expectedSet.add(new Interval(5, 15));
        expectedSet.add(new Interval(20, 21));
        expectedSet.add(new Interval(25, 31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(8, 12));
        assertEquals("NONO NO", expectedSet, actualSet);
    }

    @Test
    public void testOverlapOnLeftSideOfNewIntervalMiddleOfIntervalsWIntervalInBetween() {
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0, 2));
        actualSet.add(new Interval(3, 4));
        actualSet.add(new Interval(5, 8));
        actualSet.add(new Interval(9, 10));
        actualSet.add(new Interval(12, 15));
        actualSet.add(new Interval(20, 21));
        actualSet.add(new Interval(25, 31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0, 2));
        expectedSet.add(new Interval(3, 4));
        expectedSet.add(new Interval(5, 11));
        expectedSet.add(new Interval(12, 15));
        expectedSet.add(new Interval(20, 21));
        expectedSet.add(new Interval(25, 31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(7, 11));
        assertEquals("NONO NO", expectedSet, actualSet);
    }


    @Test
    public void testOverlapOnRightSideOfNewIntervalMiddleOfIntervalsWIntervalInBetween(){
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0,2));
        actualSet.add(new Interval(3,4));
        actualSet.add(new Interval(5,8));
        actualSet.add(new Interval(10, 11));
        actualSet.add(new Interval(12,15));
        actualSet.add(new Interval(20,21));
        actualSet.add(new Interval(25,31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0,2));
        expectedSet.add(new Interval(3,4));
        expectedSet.add(new Interval(5,8));
        expectedSet.add(new Interval(9,15));
        expectedSet.add(new Interval(20,21));
        expectedSet.add(new Interval(25,31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(9,14));
        assertEquals("NONO NO",expectedSet, actualSet);
    }

    @Test
    public void testOverlapOnLeftSideOfNewIntervalLeftEndOfOldIntervalWIntervalInBetween(){
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0,2));
        actualSet.add(new Interval(3,4));
        actualSet.add(new Interval(5,8));
        actualSet.add(new Interval(9,10));
        actualSet.add(new Interval(12,15));
        actualSet.add(new Interval(20,21));
        actualSet.add(new Interval(25,31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0,2));
        expectedSet.add(new Interval(3,4));
        expectedSet.add(new Interval(5,11));
        expectedSet.add(new Interval(12,15));
        expectedSet.add(new Interval(20,21));
        expectedSet.add(new Interval(25,31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(5, 11));
        assertEquals("NONO NO",expectedSet, actualSet);
    }

    @Test
    public void testOverlapOnLeftSideOfNewIntervalRightEndOfOldIntervalInBetween(){
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0,2));
        actualSet.add(new Interval(3,4));
        actualSet.add(new Interval(5,8));
        actualSet.add(new Interval(9,10));
        actualSet.add(new Interval(12,15));
        actualSet.add(new Interval(20,21));
        actualSet.add(new Interval(25,31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0,2));
        expectedSet.add(new Interval(3,4));
        expectedSet.add(new Interval(5,11));
        expectedSet.add(new Interval(12,15));
        expectedSet.add(new Interval(20,21));
        expectedSet.add(new Interval(25,31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(8, 11));
        assertEquals("NONO NO",expectedSet, actualSet);
    }

    @Test
    public void testOverlapOnRightSideOfNewIntervalRightEndOfOldIntervalWIntervalInBetween(){
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0,2));
        actualSet.add(new Interval(3,4));
        actualSet.add(new Interval(5,8));
        actualSet.add(new Interval(10,11));
        actualSet.add(new Interval(12,15));
        actualSet.add(new Interval(20,21));
        actualSet.add(new Interval(25,31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0,2));
        expectedSet.add(new Interval(3,4));
        expectedSet.add(new Interval(5,8));
        expectedSet.add(new Interval(9,15));
        expectedSet.add(new Interval(20,21));
        expectedSet.add(new Interval(25,31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(9, 15));
        assertEquals("NONO NO",expectedSet, actualSet);
    }
    /*
    @Test
    public void testOverlapOnRightSideOfNewIntervalLeftEndOfOldInterval(){
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0,2));
        actualSet.add(new Interval(3,4));
        actualSet.add(new Interval(5,8));
        actualSet.add(new Interval(12,15));
        actualSet.add(new Interval(20,21));
        actualSet.add(new Interval(25,31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0,2));
        expectedSet.add(new Interval(3,4));
        expectedSet.add(new Interval(5,8));
        expectedSet.add(new Interval(10,15));
        expectedSet.add(new Interval(20,21));
        expectedSet.add(new Interval(25,31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(10, 12));
        assertEquals("NONO NO",expectedSet, actualSet);
    }*/

    @Test
    public void testOverlapOnBothSidesMiddleWIntervalinBetween(){
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0,2));
        actualSet.add(new Interval(3,4));
        actualSet.add(new Interval(5,8));
        actualSet.add(new Interval(9,10));
        actualSet.add(new Interval(12,15));
        actualSet.add(new Interval(20,21));
        actualSet.add(new Interval(25,31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0,2));
        expectedSet.add(new Interval(3,4));
        expectedSet.add(new Interval(5,15));
        expectedSet.add(new Interval(20,21));
        expectedSet.add(new Interval(25,31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(6, 14));
        assertEquals("NONO NO",expectedSet, actualSet);
    }
    /*
    @Test
    public void testOverlapOnBothSidesLeftSideLeftEndRightMiddle(){
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0,2));
        actualSet.add(new Interval(3,4));
        actualSet.add(new Interval(5,8));
        actualSet.add(new Interval(12,15));
        actualSet.add(new Interval(20,21));
        actualSet.add(new Interval(25,31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0,2));
        expectedSet.add(new Interval(3,4));
        expectedSet.add(new Interval(5,15));
        expectedSet.add(new Interval(20,21));
        expectedSet.add(new Interval(25,31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(5, 13));
        assertEquals("NONO NO",expectedSet, actualSet);
    }

    @Test
    public void testOverlapOnBothSidesLeftSideLeftEndRightLeftEnd(){
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0,2));
        actualSet.add(new Interval(3,4));
        actualSet.add(new Interval(5,8));
        actualSet.add(new Interval(12,15));
        actualSet.add(new Interval(20,21));
        actualSet.add(new Interval(25,31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0,2));
        expectedSet.add(new Interval(3,4));
        expectedSet.add(new Interval(5,15));
        expectedSet.add(new Interval(20,21));
        expectedSet.add(new Interval(25,31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(5, 12));
        assertEquals("NONO NO",expectedSet, actualSet);
    }*/

    @Test
    public void testOverlapOnBothSidesLeftSideLeftEndRightRightEndWIntervalInBetween(){
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0,2));
        actualSet.add(new Interval(3,4));
        actualSet.add(new Interval(5,8));
        actualSet.add(new Interval(9,10));
        actualSet.add(new Interval(12,15));
        actualSet.add(new Interval(20,21));
        actualSet.add(new Interval(25,31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0,2));
        expectedSet.add(new Interval(3,4));
        expectedSet.add(new Interval(5,15));
        expectedSet.add(new Interval(20,21));
        expectedSet.add(new Interval(25,31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(5, 15));
        assertEquals("NONO NO",expectedSet, actualSet);
    }

    /*@Test
    public void testOverlapOnBothSidesLeftSideMiddleRightLeftEnd(){
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0,2));
        actualSet.add(new Interval(3,4));
        actualSet.add(new Interval(5,8));
        actualSet.add(new Interval(12,15));
        actualSet.add(new Interval(20,21));
        actualSet.add(new Interval(25,31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0,2));
        expectedSet.add(new Interval(3,4));
        expectedSet.add(new Interval(5,15));
        expectedSet.add(new Interval(20,21));
        expectedSet.add(new Interval(25,31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(6, 12));
        assertEquals("NONO NO",expectedSet, actualSet);
    }

    @Test
    public void testOverlapOnBothSidesLeftSideMiddleRightRightEnd(){
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0,2));
        actualSet.add(new Interval(3,4));
        actualSet.add(new Interval(5,8));
        actualSet.add(new Interval(12,15));
        actualSet.add(new Interval(20,21));
        actualSet.add(new Interval(25,31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0,2));
        expectedSet.add(new Interval(3,4));
        expectedSet.add(new Interval(5,15));
        expectedSet.add(new Interval(20,21));
        expectedSet.add(new Interval(25,31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(6, 15));
        assertEquals("NONO NO",expectedSet, actualSet);
    }*/

    @Test
    public void testOverlapOnBothSidesLeftSideRightEndRightRightEndWINtervalInBetween(){
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0,2));
        actualSet.add(new Interval(3,4));
        actualSet.add(new Interval(5,8));
        actualSet.add(new Interval(9,10));
        actualSet.add(new Interval(12,15));
        actualSet.add(new Interval(20,21));
        actualSet.add(new Interval(25,31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0,2));
        expectedSet.add(new Interval(3,4));
        expectedSet.add(new Interval(5,15));
        expectedSet.add(new Interval(20,21));
        expectedSet.add(new Interval(25,31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(8, 15));
        assertEquals("NONO NO",expectedSet, actualSet);
    }

    @Test
    public void testOverlapOnBothSidesLeftSideRightEndRightLeftEndWIntervalInBetween(){
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(0,2));
        actualSet.add(new Interval(3,4));
        actualSet.add(new Interval(5,8));
        actualSet.add(new Interval(12,15));
        actualSet.add(new Interval(20,21));
        actualSet.add(new Interval(25,31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0,2));
        expectedSet.add(new Interval(3,21));
        expectedSet.add(new Interval(25,31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(3, 21));
        assertEquals("NONO NO",expectedSet, actualSet);
    }

    @Test
    public void testAddOneElemToAnEmptySet(){
        Set<Interval> actualSet = new HashSet<>();
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(1,10));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(1,10));
        assertEquals("NONO NO",expectedSet, actualSet);


    }

    @Test
    public void testOverlapEntireSet(){
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(1,2));
        actualSet.add(new Interval(3,4));
        actualSet.add(new Interval(5,8));
        actualSet.add(new Interval(12,15));
        actualSet.add(new Interval(20,21));
        actualSet.add(new Interval(25,31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(0,32));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(0, 32));
        assertEquals("NONO NO",expectedSet, actualSet);
    }

    @Test
    public void testOverlapEntireSetBorder(){
        Set<Interval> actualSet = new HashSet<>();
        actualSet.add(new Interval(1,2));
        actualSet.add(new Interval(3,4));
        actualSet.add(new Interval(5,8));
        actualSet.add(new Interval(12,15));
        actualSet.add(new Interval(20,21));
        actualSet.add(new Interval(25,31));
        Set<Interval> expectedSet = new HashSet<>();
        expectedSet.add(new Interval(1,31));
        //expectedSet.add(new Interval(13,18));
        actualSet = MergeAnInterval.merge(actualSet, new Interval(1, 31));
        assertEquals("NONO NO",expectedSet, actualSet);
    }




}
