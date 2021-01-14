package edu.yu.introtoalgs;

import org.junit.Test;
import edu.yu.introtoalgs.*;
import edu.yu.introtoalgs.GraphsAndMazes.*;

import java.util.List;

import static edu.yu.introtoalgs.GraphsAndMazes.searchMaze;
import static org.junit.Assert.assertTrue;

public class GraphsAndMazesTest {
    @Test
    public void testRegular4by4(){
        final int[][] testMaze = {
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0}
        };
        Coordinate start = new Coordinate(2,1);
        Coordinate end = new Coordinate(0,2);
        List<Coordinate> ls = searchMaze(testMaze,start,end);
        assertTrue(followPath(ls,testMaze,start,end));

    }
    private boolean followPath(List<Coordinate> path, int[][] maze, Coordinate start, Coordinate end){
        Coordinate src = path.get(0);
        Coordinate dest = path.get(1);
        if(src.equals(start)){
            for(int i = 0;i < path.size() - 1; i++){
                //if(!mazeMove(maze,src,dest)){return false;}
                src = path.get(i);
                dest = path.get(i + 1);
                if(!mazeMove(maze,src,dest)){return false;}
            }
        }
        return dest.equals(end);
    }
    private boolean mazeMove(int[][] maze,Coordinate src, Coordinate dest) {//not for when sytart and end is same
        if (src.getX() >= 0 && src.getY() >= 0 && src.getX() < maze.length && src.getY() < maze[src.getX()].length && maze[src.getX()][src.getY()] == 0 & dest.getX() >= 0 && dest.getY() >= 0 && dest.getX() < maze.length && dest.getY() < maze[dest.getX()].length && maze[dest.getX()][dest.getY()] == 0) {
            if((Math.abs(dest.getX() - src.getX()) == 1 && dest.getY() == src.getY()) ||  (Math.abs(dest.getY() - src.getY()) == 1 && dest.getX() == src.getX())){
                return true;
            }
        }
        return false;
    }
    @Test
    public void testOVEPerformance600by600to1200by600SamePath(){
        int[][] bigMaze = new int[600][600];
        for(int i = 0; i < bigMaze.length; i++){
            for(int j = 0; j < bigMaze[i].length;j++){
                if(i % 4 == 0 && j % 4 == 0 && i >= 4 && j >= 4){
                    bigMaze[i][j] = 1;
                }
                else {
                    bigMaze[i][j] = 0;
                }
            }
        }
        Coordinate start = new Coordinate(0,0);
        Coordinate end = new Coordinate(599,599);
        long startTime1 = System.nanoTime();
        List<Coordinate> ls = searchMaze(bigMaze,start,end);
        long endTime1 = System.nanoTime();
        long time1 = endTime1 - startTime1;
        assertTrue(followPath(ls,bigMaze,start,end));
        int[][] bigMaze2 = new int[600][1200];
        for(int i = 0; i < bigMaze2.length; i++){
            for(int j = 0; j < bigMaze2[i].length;j++){
                if(i % 4 == 0 && j % 4 == 0 && i >= 4 && j >= 4){
                    bigMaze2[i][j] = 1;
                }
                else {
                    bigMaze2[i][j] = 0;
                }
            }
        }
        for(int i = 0; i < bigMaze2.length; i++) {
            for (int j = 0; j < bigMaze2[i].length; j++) {
                System.out.print(bigMaze2[i][j]);
            }
            System.out.println("");
        }
        System.out.println();
        Coordinate start2 = new Coordinate(0,0);
        Coordinate end2 = new Coordinate(599,1199);
        long startTime2 = System.nanoTime();
        List<Coordinate> ls2 = searchMaze(bigMaze2,start2,end2);
        System.out.println(ls2);
        long endTime2 = System.nanoTime();
        long time2 = endTime2 - startTime2;
        assertTrue(followPath(ls2,bigMaze2,start2,end2));
        assertTrue(time2/time1 < 3);
        System.out.println(time2 + "   " + time1 + "    " + time2/time1);
    }
    @Test
    public void testOnMassiveMaze(){}
}
