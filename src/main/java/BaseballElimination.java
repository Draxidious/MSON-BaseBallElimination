import edu.princeton.cs.algs4.FlowEdge;
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
    String[] teams;
    private int NUM_OF_TEAMS;


    // create a baseball division from given filename in format specified below
    public BaseballElimination(String filename) {
        // read in the file and set up the division
        // utilize split("\\s+") and Integer.parseInt
        In file = new In(new File(filename));
        division = new HashMap<>();
        int numofteams = file.readInt();
        NUM_OF_TEAMS = numofteams;
        teams = new String[NUM_OF_TEAMS];
        file.readLine();
        for (int i = 0; i < numofteams; i++) {
            String[] next = file.readLine().split("\\s+");
            String teamname = next[0];
            int[] putarr = new int[numofteams + 4];
            putarr[TEAM_NUMBER_POSITION] = i;
            for (int x = WINS_POSITION; x < putarr.length; x++) {
                putarr[x] = Integer.parseInt(next[x]);
            }
            division.put(teamname, putarr);
            teams[i] = teamname;
        }

    }

    // number of teams
    public int numberOfTeams() {
        return NUM_OF_TEAMS;
    }

    // all teams
    public Iterable<String> teams() {
        return division.keySet();
    }

    // number of wins for given team
    public int wins(String team) {
        return division.get(team)[WINS_POSITION];
    }

    // number of losses for given team
    public int losses(String team) {
        return division.get(team)[LOSS_POSITION];
    }

    // number of remaining games for given team
    public int remaining(String team) {
        return division.get(team)[GAMES_LEFT];
    }

    // number of remaining games between team1 and team2
    public int against(String team1, String team2) {
        return division.get(team1)[GAMES_LEFT + division.get(team2)[TEAM_NUMBER_POSITION]];
    }

    // is given team eliminated?
    public boolean isEliminated(String team) {
        return false;
    }

    private FlowNetwork getFlowNetwork(String qteam) {
        // # of vertices in your FlowNetwork is the numberOfMatchups + numberofTeams + 2
        int game = 1;
        int numOfMatchups = getNumofMatchups(qteam);
        int[] qarr = division.get(qteam);
        int vertices = qarr[GAMES_LEFT] + NUM_OF_TEAMS + 2;
        FlowNetwork flowNetwork = new FlowNetwork(vertices);
        // calculate the maximum wins possible for the team passed to this method
        int maxwins = qarr[WINS_POSITION] + qarr[GAMES_LEFT];
        int start = 0; // start num is unique, separate from teamnumbers

        // iterate over all of the team combinations
        for (int x = 0; x < NUM_OF_TEAMS; x++) {

            String team1 = teams[x];
            for (int y = 0; y < x; y++) {
                String team2 = teams[y];
                FlowEdge edge = new FlowEdge(start, game,
                        division.get(team2)[GAMES_LEFT + division.get(team1)[TEAM_NUMBER_POSITION]]);
                flowNetwork.addEdge(edge);
                FlowEdge edgex = new FlowEdge(game, numOfMatchups + 1 + division.get(team1)[TEAM_NUMBER_POSITION],
                        Integer.MAX_VALUE);
                flowNetwork.addEdge(edgex);
                FlowEdge edgey = new FlowEdge(game, numOfMatchups + 1 + division.get(team2)[TEAM_NUMBER_POSITION],
                        Integer.MAX_VALUE);
                flowNetwork.addEdge(edgey);

                game++;
            }
            // add an edge from team1 to the 'end' and give it the capacity of the wins that the
            // query team could afford  winning without overtaking our query team,
            // i.e. the query teams max possible wins minus team1's current wins

        }
        return flowNetwork;
    }

    private int getNumofMatchups(String qteam) {
        int ret = 0;
        for (int i = 0; i < NUM_OF_TEAMS; i++) {
            if (teams[i].equals(qteam)) continue;
            for (int x = i + 1; x < NUM_OF_TEAMS; x++) {
                if (teams[x].equals(qteam)) continue;
                ret++;
            }
        }
        return ret;
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
