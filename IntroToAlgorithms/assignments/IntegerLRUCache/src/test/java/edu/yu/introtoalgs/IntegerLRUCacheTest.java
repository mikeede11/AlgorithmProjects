package edu.yu.introtoalgs;

import org.junit.Test;

import static org.junit.Assert.*;

public class IntegerLRUCacheTest {
    @Test
    public void testNewPut(){
        IntegerLRUCache cache = new IntegerLRUCache(100);
        assertNull(cache.put(5,10));
        assertEquals("OhNo",  10, (int)cache.get(5));

    }
    @Test
    public void testPutModify(){
        IntegerLRUCache cache = new IntegerLRUCache(5);
        cache.put(1,32);
        assertEquals(32, (int)cache.get(1));
        cache.put(2,45);
        cache.put(1,333);
        assertEquals(333, (int)cache.get(1));
    }
    @Test
    public void testPutNewWhenCapIsReached(){
        IntegerLRUCache cache = new IntegerLRUCache(1);
        cache.put(1,32);
        cache.put(2,44);
        assertEquals(44, (int) cache.get(2));
    }
    @Test
    public void testPutModifyWhenCapIsReached(){
        IntegerLRUCache cache = new IntegerLRUCache(1);
        cache.put(1,32);
        cache.put(1,44);
        assertEquals(44, (int) cache.get(1));
    }
    @Test
    public void testputWhenAllPreviousElemsRemoved(){
        IntegerLRUCache cache = new IntegerLRUCache(6);
        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,3);
        cache.put(4,4);
        cache.put(5,5);
        for(int i = 1; i <= 5; i++){
            cache.remove(1);
        }
        cache.put(6,6);//should be LRU and MRU = 0
        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,3);
        cache.put(4,4);
        cache.put(5,5);
        cache.put(7,7);
        assertNull(cache.get(6));//should've been kicked out

    }
    @Test
    public void testGetNotThere(){
        IntegerLRUCache cache = new IntegerLRUCache(6);
        assertNull(cache.get(1));
        cache.put(100,200);
        assertNull(cache.get(99));
    }
    @Test
    public void testGetThere(){
        IntegerLRUCache cache = new IntegerLRUCache(6);
        cache.put(100,200);
        assertEquals(200,(int)cache.get(100));
    }
    @Test
    public void testRemoveThere(){
        IntegerLRUCache cache = new IntegerLRUCache(6);
        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,3);
        cache.put(4,4);
        cache.put(5,5);
        assertEquals(3,(int)cache.remove(3));
    }
    @Test
    public void testRemoveNotThere(){
        IntegerLRUCache cache = new IntegerLRUCache(5);
        assertNull(cache.remove(4));
    }
    @Test
    public void testPutNewGetRemoveTogether(){

    }
    @Test
    public void testAmortizedPerformancePut(){
        IntegerLRUCache cache = new IntegerLRUCache(100);
        long time1 = 0;
        long time2= 0;
        long start1 = 0;
        long start2 = 0;
        long end1 = 0;
        long end2 = 0;
        int h = 0;
        double ratio = 0;
        double sumOfRatios= 0;
        double averageRatio = 0;

        for(int i = 0; i < 20;i++){
            start1 = System.nanoTime();
            cache.put(h,h);
            end1 = System.nanoTime();
            time1 = end1-start1;
            for(int j = 0; j < Math.pow(2,i); j++){
                cache.put(h,h);
                h++;
            }
            start2 = System.nanoTime();
            cache.put(h,h);
            end2 = System.nanoTime();
            time2 = end2-start2;
            ratio = (double)time2/(double)time1;
            sumOfRatios += ratio;

        }
        averageRatio = sumOfRatios/20;
        System.out.println(averageRatio);
        assertTrue(averageRatio < 2);
    }

    @Test
    public void testAmortizedPerformanceGet(){
        IntegerLRUCache cache = new IntegerLRUCache(100);
        long time1 = 0;
        long time2= 0;
        long start1 = 0;
        long start2 = 0;
        long end1 = 0;
        long end2 = 0;
        int h = 0;
        double ratio = 0;
        double sumOfRatios= 0;
        double averageRatio = 0;
        for(int g = 0; g <  1100000; g++){
            cache.put(h,h);
            h++;
        }
        h = 0;
        for(int i = 0; i < 1000;i++){
            start1 = System.nanoTime();
            cache.get(h);
            end1 = System.nanoTime();
            time1 = end1-start1;
            start2 = System.nanoTime();
            cache.get(h + 1);
            end2 = System.nanoTime();
            time2 = end2-start2;
            ratio = (double)time2/(double)time1;
            sumOfRatios += ratio;
            h++;

        }
        averageRatio = sumOfRatios/1000;
        System.out.println(averageRatio);
        assertTrue(averageRatio < 2);
    }
    @Test
    public void testAmortizedPerformanceRemove(){
        IntegerLRUCache cache = new IntegerLRUCache(100);
        long time1 = 0;
        long time2= 0;
        long start1 = 0;
        long start2 = 0;
        long end1 = 0;
        long end2 = 0;
        int h = 0;
        double ratio = 0;
        double sumOfRatios= 0;
        double averageRatio = 0;
        for(int g = 0; g <  1100000; g++){
            cache.put(h,h);
            h++;
        }
        h = 0;
        for(int i = 0; i < 1000;i++){
            start1 = System.nanoTime();
            cache.remove(h);
            end1 = System.nanoTime();
            time1 = end1-start1;
            start2 = System.nanoTime();
            cache.remove(h + 1);
            end2 = System.nanoTime();
            time2 = end2-start2;
            ratio = (double)time2/(double)time1;
            sumOfRatios += ratio;
            h++;

        }
        averageRatio = sumOfRatios/1000;
        System.out.println(averageRatio);
        assertTrue(averageRatio < 2);
    }




}
