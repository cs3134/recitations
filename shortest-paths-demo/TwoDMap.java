import java.util.*;

/**
 * Demonstrates path finding algorithms on a square grid. This class represents
 * a square grid map and implements DFS, BFS, greedy search, and A* search with
 * straight line distance as its heuristic.
 */
public class TwoDMap {

    public int width;
    public int height;
    public int[][] map; /* The map is represented as a 2D array of integers.
                         * 0 means empty field, rendered as white
                         * 1 means obstacle (wall), rendered as black
                         * 2, 3, 4 represent colors used to demonstrate
                         * the algorithms: red, blue, and gray */

    private DisplayMap display;
    /**
     * Set the DisplayMap object used to visualize the map.
     * @param display
     */
    public void setDisplay(DisplayMap display) {
        this.display = display;
    }

    /**
     * Represent a coordinate on the map. This class is used to store (x,y) coordinates
     * in data structures used during search.
     */
    private static class Cell {
        public int x;
        public int y;

        public Cell(int x, int y) {
            this.x=x;
            this.y=y;
        }

        @Override
        public int hashCode() {
            return 251*x + 131*y;
        }

        public boolean equals(Object other) {
            if ( other == this ) return true; // same object
            if ( !(other instanceof Cell) ) return false;
            Cell otherCell  = (Cell)other;
            return (otherCell.x==x && otherCell.y==y);
        }
    }

    /**
     * Construct a new map. The map is initially empty.
     * @param w width of the new map.
     * @param h height of the new map.
     */
    public TwoDMap(int w, int h) {
        width = w;
        height = h;
        map = new int[width][height];
        for (int i = 0; i < width; i++) // set all cells to 0 = empty
            for (int j = 0; j < height; j++)
                map[i][j] = 0;
    }

    /**
     * Clear colored overlays, leaving obstacles intact.
     * This method is called before the path finding algorithms
     * are run.
     */
    private void clear() {
        for (int i = 0; i < width; i++)
            for (int j = 0; j < height; j++)
                if (map[i][j] != 1)
                    map[i][j] = 0;
    }

    /**
     * Return all free cells (cells without obstacles) that can be reached
     * from a given cell.
     * Takes the boundaries of the map into account.
     * This method defines the adjacency relation of the graph, instead of
     * storing the graph explicitly.
     * @param u The cell for which adjacent cells should be returned.
     * @return A collection of cells reachable from u in a single step.
     */
    public Collection<Cell> getAdjacent(Cell u) {
        LinkedList<Cell> result = new LinkedList<>();
        // Left
        if (u.x > 0 && (map[u.x-1][u.y]!=1)) result.add(new Cell(u.x-1 ,u.y));
        // Up
        if (u.y > 0 && (map[u.x][u.y-1]!=1)) result.add(new Cell(u.x ,u.y-1));
        // Right
        if (u.x < width-1 && (map[u.x+1][u.y]!=1)) result.add(new Cell(u.x+1 ,u.y));
        // Down
        if (u.y < height-1 && (map[u.x][u.y+1]!=1)) result.add(new Cell(u.x ,u.y+1));
        return result;
    }


    /****************** Path-Finding Methods Follow ***********/


    /**
     * Find a path using depth-first search and visualize the algorithm.
     * DFS turns out to be a bad strategy in this case. Essentially it just
     * systematically explores the graph, hoping it will hit the target at
     * some point. The algorithm will produce a path from the source to the target,
     * but it certainly will not be the shortest path.
     */
    public void dfs(Cell source, Cell target){
        LinkedList<Cell> stack = new LinkedList<>();
        stack.push(source);

        // initialize display
        clear();
        setPos(target.x, target.y, 3); // Mark the target blue
        setPos(source.x, source.y, 2); // Mark the source red

        HashSet<Cell> visited = new HashSet<>();

        Cell u;
        int steps=0;
        while(!stack.isEmpty()) {
            u = stack.pop();
            steps +=1;
            if (!visited.contains(u)) {
                    setPos(u.x, u.y, 2); // Display this cell as visited
                    if (display != null) display.refresh();

                    if (u.equals(target)) {
                        System.out.println("Success. Search took "+steps+" steps.");
                        return;
                    }
                    visited.add(u);
                    for (Cell v : getAdjacent(u)) {
                        if (!visited.contains(v)) {
                            stack.push(v);
                            // Gray cells: This cell is on the stack.
                            if (!(v.equals(target))) setPos(v.x, v.y, 4);
                        }
                    }
                }
        }
    }

    /**
     * Find a path using breadth first search and visualize the algorithm.
     * This algorithm will produce the shortest path because all transitions
     * between cells have the same cost. Unfortunately, it is somewhat slow.
     * @param source
     * @param target
     */
     public void bfs(Cell source, Cell target){
        LinkedList<Cell> queue = new LinkedList<>();
        queue.offer(source);

         // initialize display
         clear();
        setPos(target.x, target.y, 3); // Mark the target blue
        setPos(source.x, source.y, 2); // Mark the source red

        Map<Cell, Double> cost = new HashMap<>();
        Map<Cell, Cell> prev = new HashMap<>();

        for (int i=0;i<width;i++)
            for (int j=0;j<height;j++)
                cost.put(new Cell(i,j),Double.POSITIVE_INFINITY);
        cost.put(source, 0.0);

        Cell u;
        int steps = 0;
        while(!queue.isEmpty()) {
            u = queue.poll();
            setPos(u.x,u.y,2);
            if (display != null) display.refresh();
            steps++;
            if (u.equals(target)) {
                System.out.println("Success in "+steps+" steps.");

                    // print out the shortest path
                    steps = 0;
                    while(prev.containsKey(u)) {
                        setPos(u.x, u.y, 3);
                        u = prev.get(u);
                        steps++;
                    }
                if (display != null) display.refresh();
                System.out.println("Shortest path: "+steps+"steps.");
                return;
            }
            for (Cell v : getAdjacent(u)) {
                if (cost.get(v).equals(Double.POSITIVE_INFINITY)) {
                    cost.put(v, cost.get(u)+1);
                    prev.put(v,u);
                    queue.offer(v);
                    if (!(v.equals(target))) setPos(v.x, v.y, 4); // Mark this cell as seen
                }
            }
        }
    }

    /**
     * This class represents an Agenda entry together with a cost.
     * When implementing greedy search and A*, cells are stored on a
     * priority queue, together with their lowest cost (similar to
     * Dijkstra's algorithm).
     */
    private static class AgendaEntry implements Comparable<AgendaEntry>{
        public Cell cell;
        public double cost;

        public AgendaEntry(Cell cell, double cost) {
            this.cell = cell;
            this.cost = cost;
        }

        @Override
        public int compareTo(AgendaEntry o) {
            if (o.cost == cost)
                return 0;
            else return o.cost > cost ? -1 : 1;
        }
    }

    /**
     * Greedy search using a priority queue and keeps cells sorted by their
     * estimated distance to the goal. This estimate is called a heuristic and
     * we will simply use the euclidean (shortest path) distance to the goal.
     * Obviously, if there are any obstacles on the map, this heuristic is
     * overly optimistic. In the absence of obstacles greedy search will only
     * take the steps on the shortest path and never explore any other cells.
     * One downside of this algorithm is that it can get stuck behind an obstacle
     * for a long time. A more serious problem is that greedy search is not
     * guaranteed to find the shortest path. In many situations, this algorithm
     * is both fast and finds a reasonably short path.
     *
     * @param source
     * @param target
     */
    public void greedy(Cell source, Cell target){
        PriorityQueue<AgendaEntry> heap = new PriorityQueue<>();

        clear();
        setPos(target.x, target.y, 3); // Mark the target blue
        setPos(source.x, source.y, 2); // Mark the target blue

        Map<Cell, Double> cost = new HashMap<>();
        Map<Cell, Cell> prev = new HashMap<>();
        HashSet<Cell> visited = new HashSet<>();

        for (int i=0;i<width;i++)
            for (int j=0;j<height;j++)
                cost.put(new Cell(i,j),Double.POSITIVE_INFINITY);
        cost.put(source, 0.0 );
        heap.add(new AgendaEntry(source, Heuristics.euclidean(source.x, source.y, target.x, target.y)));

        AgendaEntry item;
        int steps = 0;
        while(!heap.isEmpty()) {
            item = heap.poll();
            Cell u = item.cell;
            if (!visited.contains(u)) {
                visited.add(u);
                Double fu = item.cost;
                System.out.println(cost.get(u) + " "+fu);
                setPos(u.x, u.y, 2);
                if (display != null) display.refresh();
                steps++;

                if (u.equals(target)) {
                    System.out.println("Success in " + steps + " steps.");

                    // print out the shortest path
                    steps = 0;
                    while (prev.containsKey(u)) {
                        setPos(u.x, u.y, 3);
                        u = prev.get(u);
                        steps++;
                    }
                    if (display != null) display.refresh();
                    System.out.println(" path: " + steps + "steps.");
                    return;
                }

                for (Cell v : getAdjacent(u)) {
                    if (!visited.contains(v) && cost.get(u)+1 < cost.get(v)) {

                        double newcost = cost.get(u)+1.0;
                        cost.put(v, newcost);

                        prev.put(v, u);
                        Double newfu = Heuristics.euclidean(v.x, v.y, target.x, target.y);
                        heap.offer(new AgendaEntry(v,newfu));
                        if (!(v.equals(target))) setPos(v.x, v.y, 4); // Mark this cell as seen
                    }
                }
            }
        }
    }

    /**
     * A* modifies greedy search to combine the heuristic with the actual cost
     * of the shortest path to a vertex. More specifically, A* keeps cells on
     * a priority queue organized by a function f(u), where u is a cell.
     * f(u) = cost(u) + h(u) where cost(u) is the length of the shortest path
     * from the source to u and h(u) is a heuristic function. In our case h(u)
     * is just the euclidean distance of u to the goal.
     * A* give a more realistic estimate of the real cost to the goal. It will
     * sometimes take longer than greedy search to find a path (but usually not
     * as long as plain BFS), and it is guaranteed to find the shortest path if
     * the heuristic h(u) is "admissible", which means that h(u) never
     * overestimates the real cost of reaching the goal from u. The euclidean
     * distance is admissible.
     *
     * @param source
     * @param target
     */
    public void aStar(Cell source, Cell target){
        PriorityQueue<AgendaEntry> heap = new PriorityQueue<>();


        clear();
        setPos(target.x, target.y, 3); // Mark the target blue
        setPos(source.x, source.y, 2); // Mark the target blue

        Map<Cell, Double> cost = new HashMap<>();
        Map<Cell, Cell> prev = new HashMap<>();
        HashSet<Cell> visited = new HashSet<>();

        for (int i=0;i<width;i++)
            for (int j=0;j<height;j++)
                cost.put(new Cell(i,j),Double.POSITIVE_INFINITY);
        cost.put(source, 0.0 );
        heap.add(new AgendaEntry(source, Heuristics.euclidean(source.x, source.y, target.x, target.y)));

        AgendaEntry item;
        int steps = 0;
        while(!heap.isEmpty()) {
            item = heap.poll();
            Cell u = item.cell;
            if (!visited.contains(u)) {
                visited.add(u);
                Double fu = item.cost;
                System.out.println(cost.get(u) + " "+ (fu-cost.get(u)) + " "+fu);
                setPos(u.x, u.y, 2);
                if (display != null) display.refresh();
                steps++;

                if (u.equals(target)) {
                    System.out.println("Success in " + steps + " steps.");

                    // print out the shortest path
                    steps = 0;
                    while (prev.containsKey(u)) {
                        setPos(u.x, u.y, 3);
                        u = prev.get(u);
                        steps++;
                    }
                    if (display != null) display.refresh();
                    System.out.println("Shortest path: " + steps + "steps.");
                    return;
                }

                for (Cell v : getAdjacent(u)) {
                    if (!visited.contains(v))
                        if (cost.get(u)+1.0 < cost.get(v)) {
                            double newcost = cost.get(u)+1.0;
                            cost.put(v, newcost);
                            prev.put(v, u);
                            Double newfu = newcost + Heuristics.euclidean(v.x, u.y, target.x, target.y);
                            heap.offer(new AgendaEntry(v,newfu));
                            if (!(v.equals(target))) setPos(v.x, v.y, 4); // Mark this cell as seen
                        }
                }
            }
        }
    }


    /*************************************************************************/

    public int getPos(int x, int y) {
        return map[x][y];
    }

    public void setPos(int x, int y, int value) {
        map[x][y] = value;
    }


    public static void main(String[] args){
        TwoDMap map = new TwoDMap(20,20);

        map.setPos(10,10,1);
        map.setPos(11,10,1);
        map.setPos(12,10,1);
        map.setPos(10,13,1);
        map.setPos(12,11,1);
        map.setPos(13,11,1);
        map.setPos(14,11,1);
        map.setPos(15,11,1);
        map.setPos(16,11,1);
        map.setPos(17,11,1);
        map.setPos(18,11,1);
        map.setPos(19,11,1);

        DisplayMap display = new DisplayMap(map);
        map.setDisplay(display);
        map.setPos(12,15,2);
        map.setPos(15,3,3);
        display.setVisible(true);
        //map.dfs(new Cell(12,15), new Cell(15,3));
        //map.bfs(new Cell(12,15), new Cell(15,3));
        //map.greedy(new Cell(12,15), new Cell(15,3));
        map.aStar(new Cell(12,15), new Cell(15,3));
    }
}
