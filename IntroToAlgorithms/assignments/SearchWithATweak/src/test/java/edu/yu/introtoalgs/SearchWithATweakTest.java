package edu.yu.introtoalgs;

import org.junit.Test;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SearchWithATweakTest{

    @Test
    public void testFindFirstInstanceIfKeyDoesntExistInIncrNoDupsPos(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < 101; i++){
            list.add(i);
        }
        assertEquals("Error", -1, SearchWithATweak.findFirstInstance(list,150));
    }
    @Test
    public void testFindFirstInstanceIfKeyDoesntExistInDecrNoDupsPos() {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 100; i > 0; i--){
            list.add(i);
        }
        assertEquals("Error", -1, SearchWithATweak.findFirstInstance(list, 150));
    }
    @Test
    public void testFindFirstInstanceIfKeyDoesntExistInIncrDupsPos(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < 101; i++){
            list.add(i);
            list.add(i);
        }
        assertEquals("Error", -1, SearchWithATweak.findFirstInstance(list,150));
    }
    @Test
    public void testFindFirstInstanceIfKeyDoesntExistInDecrDupsPos() {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 100; i > 0; i--){
            list.add(i);
            list.add(i);
        }
        assertEquals("Error", -1, SearchWithATweak.findFirstInstance(list, 150));
    }
    @Test
    public void testFindFirstInstanceIfKeyDoesntExistInIncrDupsNeg(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = -50; i < 50; i++){
            list.add(i);
            list.add(i);
        }
        assertEquals("Error", -1, SearchWithATweak.findFirstInstance(list,-100));
    }
    @Test
    public void testFindFirstInstanceIfKeyDoesntExistInDecrDupsNeg() {
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = -100; i > -200; i--){
            list.add(i);
            list.add(i);
        }
        assertEquals("Error", -1, SearchWithATweak.findFirstInstance(list, 150));
    }
    @Test
    public void testFindFirstInstanceIfKeyDoesntExistInIncrDupsNegSmallList(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = -1; i < 2; i++){
            list.add(i);
            list.add(i);
        }
        assertEquals("Error", -1, SearchWithATweak.findFirstInstance(list,-100));
    }
    @Test
    public void testFindFirstInstanceIfKeyDoesntExistInAllDupsList(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < 100; i++){
            list.add(1);
        }
        assertEquals("Error", -1, SearchWithATweak.findFirstInstance(list,-1));
    }
    @Test public void testFindFirstInstanceInEmptyList(){//Empty List?
        ArrayList<Integer> list = new ArrayList<>();
        assertEquals("Error", -1, SearchWithATweak.findFirstInstance(list,1));
    }
    @Test
    public void testFindFirstInstanceIfKeyDoesntExistInOneElemList(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < 1; i++){
            list.add(1);
        }
        assertEquals("Error", -1, SearchWithATweak.findFirstInstance(list,-1));
    }
    @Test
    public void testFindFirstInstanceWithOneElemList(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < 1; i++){
            list.add(1);
        }
        assertEquals("Error", 0, SearchWithATweak.findFirstInstance(list,1));
    }
    @Test
    public void testFindFirstInstanceWithAllDups(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < 1; i++){
            list.add(1);
        }
        assertEquals("Error", 0, SearchWithATweak.findFirstInstance(list,1));
    }
    @Test
    public void testFindFirstInstanceInIncrDupsNegSmallList(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = -1; i < 2; i++){
            list.add(i);
            list.add(i);
        }
        assertEquals("Error", 2, SearchWithATweak.findFirstInstance(list,0));
    }

    @Test
    public void testFindFirstInstanceWithIncrArrayNegPosNumInMiddle(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = -10; i < 10; i+=2){
            list.add(i);
        }
        assertEquals("Error", 5 , SearchWithATweak.findFirstInstance(list,0));
    }
    @Test
    public void testFindFirstInstanceWithDecrArrayNegPosNumInMiddle(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 10; i > -10; i-=2){
            list.add(i);
        }
        assertEquals("Error", 1 , SearchWithATweak.findFirstInstance(list,8));
    }
    @Test
    public void testFindFirstInstanceWithDecrArrayNegPosNumInMiddledups(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 10; i > -10; i-=2){
            list.add(i);
            list.add(i);
            list.add(i);
        }
        assertEquals("Error", 3 , SearchWithATweak.findFirstInstance(list,8));
    }
    @Test
    public void testFindFirstInstanceWithIncrArrayNegPosNumInMiddleDups(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = -2; i < 2; i+=2){
            list.add(i);
            list.add(i);
            list.add(i);
            list.add(i);
            list.add(i);
        }
        assertEquals("Error", 5, SearchWithATweak.findFirstInstance(list,0));
    }
    @Test
    public void testFindFirstInstanceWithIncrArrayNegPosNumInBegDups(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = -2; i < 4; i+=2){
            list.add(i);
            list.add(i);
            list.add(i);
            list.add(i);
            list.add(i);
        }
        assertEquals("Error", 0, SearchWithATweak.findFirstInstance(list,-2));
    }
    @Test
    public void testFindFirstInstanceWithDecrArrayNegPosNumInBegDups(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = -2; i < 4; i+=2){
            list.add(i);
            list.add(i);
            list.add(i);
            list.add(i);
            list.add(i);
        }
        assertEquals("Error", 0, SearchWithATweak.findFirstInstance(list,-2));
    }
    @Test
    public void testFindFirstInstanceWithIncrArrayNegPosNumInEnd(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = -2; i < 4; i+=2){
            list.add(i);
            list.add(i);
            list.add(i);
            list.add(i);
            list.add(i);
        }
        list.add(105);
        assertEquals("Error", list.size() - 1, SearchWithATweak.findFirstInstance(list,105));
    }

    @Test
    public void testFindFirstInstanceWithDecrArrayNegPosNumInEnd(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 4; i > -2; i-=2){
            list.add(i);
            list.add(i);
            list.add(i);
            list.add(i);
            list.add(i);
        }
        list.add(-100);
        assertEquals("Error", list.size() - 1, SearchWithATweak.findFirstInstance(list,-100));
    }


    @Test
    public void testFindFirstInstanceWithDecreasingArrayAndDuplicateElems(){
        ArrayList<Integer> pracList = new ArrayList<Integer>();
        for(int i = 10; i > 0; i--){
            pracList.add(i);
            pracList.add(i);
        }
        assertEquals("not good",10, SearchWithATweak.findFirstInstance(pracList, 5));
    }
    //No dups
    @Test
    public void testElemEqToItsIndexIncrPosNegExistsMiddle(){
        ArrayList<Integer> pracList = new ArrayList<Integer>();
        for(int i = -1; i < 6; i++) {
            pracList.add(i);
        }
        pracList.add(7,7);
        for(int i = 10; i < 14; i +=2){
            pracList.add(i);
        }
        assertEquals("not good",7, SearchWithATweak.elementEqualToItsIndex(pracList));
    }
    @Test
    public void testElemEqToItsIndexDecrPosExistsMiddle(){
        ArrayList<Integer> pracList = new ArrayList<Integer>();
        for(int i = 20; i > 15; i--) {
            pracList.add(i);
        }
        pracList.add(5,5);
        for(int i = 3; i > -66; i -=2){
            pracList.add(i);
        }
        assertEquals("not good",5, SearchWithATweak.elementEqualToItsIndex(pracList));
    }
    @Test
    public void testElemEqToItsIndexIncrPosNegExistsBeg(){
        ArrayList<Integer> pracList = new ArrayList<Integer>();
        pracList.add(0);
        for(int i = 15; i < 600; i+=15){
            pracList.add(i);
        }
        assertEquals("not good",0, SearchWithATweak.elementEqualToItsIndex(pracList));
    }
    @Test
    public void testElemEqToItsIndexDecrPosNegExistsBeg(){
        ArrayList<Integer> pracList = new ArrayList<Integer>();
        pracList.add(0);
        for(int i = -15; i > -600; i-=15){
            pracList.add(i);
        }
        assertEquals("not good",0, SearchWithATweak.elementEqualToItsIndex(pracList));
    }
    @Test
    public void testElemEqToItsIndexIncrPosNegExistsEnd(){
        ArrayList<Integer> pracList = new ArrayList<Integer>();
        for(int i = -600; i < 15; i+=15){
            pracList.add(i);
        }
        pracList.add(pracList.size(), pracList.size());
        assertEquals("not good",pracList.size() - 1, SearchWithATweak.elementEqualToItsIndex(pracList));
    }
    @Test
    public void testElemEqToItsIndexDecrPosNegExistsEnd(){
        ArrayList<Integer> pracList = new ArrayList<Integer>();
        for(int i = 600; i > 225; i-=15){
            pracList.add(i);
        }
        pracList.add(pracList.size(), pracList.size());
        assertEquals("not good",pracList.size() - 1, SearchWithATweak.elementEqualToItsIndex(pracList));
    }
    @Test
    public void testElemEqToIndexWithMultipleAnswers(){
        ArrayList<Integer> pracList = new ArrayList<Integer>();
        for(int i = 0; i < 21; i++){
            pracList.add(i);
        }
        assertEquals("Error", 0, SearchWithATweak.elementEqualToItsIndex(pracList));//b/c 1st result
    }
    @Test
    public void testElemEqToIndexWithMultipleAnswers2(){
        ArrayList<Integer> pracList = new ArrayList<Integer>();
        pracList.add(-3);
        for(int i = 1; i < 21; i++){
            pracList.add(i);
        }
        assertEquals("Error", 10, SearchWithATweak.elementEqualToItsIndex(pracList));//b/c 1st result
    }
    @Test
    public void testElemEqToIndexWithOneElemListExist(){
        ArrayList<Integer> pracList = new ArrayList<Integer>();
        pracList.add(0);
        assertEquals("not good",0, SearchWithATweak.elementEqualToItsIndex(pracList));
    }
    @Test
    public void testEmptyList(){
        ArrayList<Integer> list = new ArrayList<>();
        assertEquals("Error", -1, SearchWithATweak.elementEqualToItsIndex(list));
    }
    @Test
    public void testDoesntExisteIncrWouldBeInMid(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = -6; i < 6; i+=2) {
            list.add(i);
        }
        for(int i = 13; i< 57; i+=3){
            list.add(i);
        }
        assertEquals("Error", -1, SearchWithATweak.elementEqualToItsIndex(list));
    }
    @Test
    public void testDoesntExistDecrWouldBeInMid(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 50; i > 38; i-=2) {
            list.add(i);
        }
        for(int i = -5; i < -40; i-=3){
            list.add(i);
        }
        assertEquals("Error", -1, SearchWithATweak.elementEqualToItsIndex(list));
    }
    @Test
    public void testDoestExistWouldBeAtEndIncr(){//all nums before point of no return
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = -5; i < 5; i++) {
            list.add(i);
        }
        assertEquals("Error", -1, SearchWithATweak.elementEqualToItsIndex(list));
    }
    @Test
    public void testDoestExistWouldBeAtEndDecr(){
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 35; i > 25; i--) {
            list.add(i);
        }
        assertEquals("Error", -1, SearchWithATweak.elementEqualToItsIndex(list));
    }
    @Test
    public void testDoesntExistWouldBeAtBeg(){
        //point of no return is first elem
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 1; i < 10; i++){
            list.add(i);
        }
        assertEquals("Error", -1, SearchWithATweak.elementEqualToItsIndex(list));
    }
    @Test
    public void testStackOverflow(){
        ArrayList<Integer> bigList = new ArrayList<>();
        for(int i = -10000000; i < 10000000; i++){
            bigList.add(i);
        }
        assertEquals("Error", 1, SearchWithATweak.findFirstInstance(bigList, -9999999));
    }
    @Test
    public void testStackOverflowWithDupsTestGetFirst(){
        ArrayList<Integer> bigList = new ArrayList<>();
        bigList.add(0);
        for(int i = -10000000; i < 10000000; i++){
            bigList.add(1);
        }
        assertEquals("Error", 1, SearchWithATweak.findFirstInstance(bigList, 1));
    }
    @Test
    public void testStackOverflowElemEqToIndex(){
        ArrayList<Integer> bigList = new ArrayList<>();
        bigList.add(0);
        for(int i = -10000000; i < 10000000; i++){
            bigList.add(-5);
        }
        assertEquals("Error", 0, SearchWithATweak.elementEqualToItsIndex(bigList));
    }
    //doesntexisttests

}


