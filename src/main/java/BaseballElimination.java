import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.util.HashMap;

public class BaseballElimination {
    private HashMap<String, int[]> division;
    private final int TEAM_NUMBER_POSITION = 0;
    private final int WINS_POSITION = 1;
    private final int LOSS_POSITION = 2;
    private final int GAMES_LEFT = 3;


    // create a baseball division from given filename in format specified below
    public BaseballElimination(String filename) {
        // read in the file and set up the division
        // utilize split("\\s+") and Integer.parseInt
        In file = new In(new File(filename));
        division = new HashMap<>();
        int numofteams = file.readInt();
        file.readLine();
        for (int i = 0; i < numofteams; i++) {
            String[] next = file.readLine().split("\\s+");
            String teamname = next[TEAM_NUMBER_POSITION];
            int[] putarr = new int[numofteams + 3];
            for (int x = WINS_POSITION; x < putarr.length; x++) {
                putarr[x] = Integer.parseInt(next[x]);
            }
            division.put(teamname, putarr);
        }

    }

    // number of teams
    public int numberOfTeams() {
        return 0;
    }

    // all teams
    public Iterable<String> teams() {
        return division.keySet();
    }

    // number of wins for given team
    public int wins(String team) {
        return 0;
    }

    // number of losses for given team
    public int losses(String team) {
        return 0;
    }

    // number of remaining games for given team
    public int remaining(String team) {
        return 0;
    }

    // number of remaining games between team1 and team2
    public int against(String team1, String team2) {
        return 0;
    }

    // is given team eliminated?
    public boolean isEliminated(String team) {
        return false;
    }

    private FlowNetwork getFlowNetwork(String team) {
        // # of vertices in your FlowNetwork is the numberOfMatchups + numberofTeams + 2

        // keeps track of the game number, starts at 1 because 0 is the start node
        int game = 1;

        // calculate the maximum wins possible for the team passed to this method

        //iterate over all of the team combinations
//      for (int x = 0; x < numberOfTeams; x++) {
//        team1 = teams[x];
//        for (int y = 0; y < x; y++) {
//          team2 = teams[y];
        // add the edge from 'start' to 'game x-y' with the capacity of the games left between them
        // add an edge from 'game x-y' to 'x' and to 'y'
        // increment game for the next iteration
//          game++;
//        }
        // add an edge from team1 to the 'end' and give it the capacity of the wins that the
        // query team could afford  winning without overtaking our query team,
        // i.e. the query teams max possible wins minus team1's current wins

//      }
//      return flowNetwork;
    }

    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        return null;
    }

    /**
     * Reads in a sports division from an input file and
     * prints whether each team is mathematically eliminated
     * and a certificate of elimination for each team that is eliminated.
     *
     * @param args
     */
    public static void main(String[] args) {
        BaseballElimination division = new BaseballElimination("baseball-testing-files/teams4.txt");
        for (String team : division.teams()) {
            if (division.isEliminated(team)) {
                StdOut.print(team + " is eliminated by the subset R = { ");
                for (String t : division.certificateOfElimination(team)) {
                    StdOut.print(t + " ");
                }
                StdOut.println("}");
            } else {
                StdOut.println(team + " is not eliminated");
            }
        }
    }
}
