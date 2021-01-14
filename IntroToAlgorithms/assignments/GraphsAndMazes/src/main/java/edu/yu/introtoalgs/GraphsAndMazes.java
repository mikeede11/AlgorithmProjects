package edu.yu.introtoalgs;
import java.util.*;

public class GraphsAndMazes {
    //private static boolean endWasReached = false;
    /** A immutable coordinate in 2D space.
     *
     * Students must NOT modify the constructor (or its semantics) in any way,
     * but can ADD whatever they choose.
     */
    public static class Coordinate {
        public final int x, y;

        /** Constructor, defines an immutable coordinate in 2D space.
         *
         * @param x specifies x coordinate
         * @param y specifies x coordinate
         */
        public Coordinate(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }
        public int getY() {
            return y;
        }
        public List<Coordinate> getAdjCoords(){
            List<Coordinate> ls = new ArrayList<>();
            //N = x -1 y
            ls.add(new Coordinate(this.x - 1,this.y));
            //E = x, y + 1
            ls.add(new Coordinate(this.x, this.y + 1));
            //s = x +1 , y
            ls.add(new Coordinate(this.x + 1, this.y));
            //W = x, y - 1
            ls.add(new Coordinate(this.x,this.y - 1));
            return ls;
        }

        /** Add any methods, instance variables, static variables that you choose
         */

        @Override
        public boolean equals(Object that){
            if(that instanceof Coordinate) {
                if (this.x == ((Coordinate)that).x && this.y == ((Coordinate)that).y) {
                    return true;
                }
            }
            return false;
        }
        @Override
        public int hashCode(){
            return this.x * 31 + this.y * 31;
        }
        @Override
        public String toString(){
            return "(" + this.x + "," + this.y + ")";
        }
        //maybe we should have NSEW coordinates calculated from original coordinates
    } // Coordinate class

    /** Given a maze (specified by a 2D integer array, and start and end
     * Coordinate instances), return a path (beginning with the start
     * coordinate, and terminating wih the end coordinate), that legally
     * traverses the maze from the start to end coordinates.  If no such
     * path exists, returns an empty list.  The path need need not be a
     * "shortest path".
     *
     * @param maze 2D int array whose "0" entries are interpreted as
     * "coordinates that can be navigated to in a maze traversal (can be
     * part of a maze path)" and "1" entries are interpreted as
     * "coordinates that cannot be navigated to (part of a maze wall)".
     * @param start maze navigation must begin here, must have a value
     * of "0"
     * @param end maze navigation must terminate here, must have a value
     * of "0"
     * @return a path, beginning with the start coordinate, terminating
     * with the end coordinate, and intervening elements represent a
     * legal navigation from maze start to maze end.  If no such path
     * exists, returns an empty list.  A legal navigation may only
     * traverse maze coordinates, may not contain coordinates whose
     * value is "1", may only traverse from a coordinate to one of its
     * immediate neighbors using one of the standard four compass
     * directions (no diagonal movement allowed).  A legal path may not
     * contain a cycle.  It is legal for a path to contain only the
     * start coordinate, if the start coordinate is equal to the end
     * coordinate.
     */
    public static List<Coordinate> searchMaze(final int[][] maze, final Coordinate start, final Coordinate end)
    {
        if(!isValid(maze, start) ||!isValid(maze, end)){throw  new IllegalArgumentException();}
        Coordinate ls[] = new Coordinate[0];
        HashMap<Coordinate,Boolean> marked = new HashMap<>();
        HashMap<Coordinate, Coordinate> edgeTo = new HashMap<>();
        HashMap<Coordinate,Integer> distTo = new HashMap<>();
        Queue<Coordinate> q = new LinkedList<Coordinate>();
        q.add(start);
        marked.put(start, true);
        distTo.put(start, 0);

        while(!q.isEmpty()){
            Coordinate c = q.remove();
            if(c.equals(end)){
                break;//we have path;
            }

            for(Coordinate d: getValidCoords(maze, c.getAdjCoords())){
                if(marked.get(d) == null || !marked.get(d)){
                    q.add(d);
                    marked.put(d,true);
                    edgeTo.put(d,c);
                    distTo.put(d,distTo.get(c) + 1);
                }
            }
        }
        if(distTo.get(end) != null) {
            int distToStart = distTo.get(end);
            ls = new Coordinate[distToStart + 1];
            Coordinate c = end;
            while (distToStart >= 0) {
                ls[distToStart] = c;
                c = edgeTo.get(c);
                distToStart--;
            }
        }

        /*for(Coordinate c = end; !c.equals(start);c = edgeTo.get(c)){
            path.add(c);
        }
        path.add(start);
        Collections.reverse(path);*/
        return  Arrays.asList(ls);
    }

    private static List<Coordinate> getValidCoords(int[][] maze, List<Coordinate> coords) {
        List<Coordinate> validList = new ArrayList<>();
        for (Coordinate c : coords) {
            if (c.getX() >= 0 && c.getY() >= 0 && c.getX() < maze.length && c.getY() < maze[c.getX()].length && maze[c.getX()][c.getY()] == 0) {
                validList.add(c);
            }
        }
        return  validList;
    }
    private static boolean isValid(int[][] maze, Coordinate c){
        return c.getX() >= 0 && c.getY() >= 0 && c.getX() < maze.length && c.getY() < maze[c.getX()].length && maze[c.getX()][c.getY()] == 0;
    }

    /** minimal main() demonstrates use of APIs
     */
    public static void main (final String[] args) {
        final int[][] exampleMaze = {
                {0, 0, 0, 0, 0},
                {0, 1, 1},
                {0, 0, 0,0,0}
        };
        //int[][] maze2 = new int[750][750];

        final Coordinate start = new Coordinate(0, 4);
        final Coordinate end = new Coordinate(2, 4);
        final List<Coordinate> path = searchMaze(exampleMaze, start, end);
        System.out.println("path="+path);
    }

}