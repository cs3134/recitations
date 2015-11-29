/**
 * Created by daniel on 11/25/15.
 */
public class Heuristics {
    public static Double euclidean(Integer ux, Integer uy, Integer vx, Integer vy) {
        return Math.sqrt(Math.pow((ux-vx),2) + Math.pow(uy-vy,2));
    }

}
