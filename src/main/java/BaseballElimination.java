import edu.princeton.cs.algs4.*;

import java.io.File;
import java.util.HashMap;

public class BaseballElimination {
    private HashMap<String, int[]> division;
    private final int TEAM_NUMBER_POSITION = 0;
    private final int WINS_POSITION = 1;
    private final int LOSS_POSITION = 2;
    private final int GAMES_LEFT = 3;
    private String[] teams;
    private int NUM_OF_TEAMS;
    private int numOfMatchups;


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
        return division.get(team1)[GAMES_LEFT + 1 + division.get(team2)[TEAM_NUMBER_POSITION]];
    }

    // is given team eliminated?
    public boolean isEliminated(String team) {
        if (isTriviallyEliminated(team)) return true;
        FlowNetwork network = getFlowNetwork(team);

        new FordFulkerson(network, 0, network.V() - 1);
        System.out.println(network.toString());
        for (FlowEdge edge : network.adj(0)) {
            if (edge.flow() != edge.capacity()) return true;
        }
        return false;
    }

    private FlowNetwork getFlowNetwork(String qteam) {
        // # of vertices in your FlowNetwork is the numberOfMatchups + numberofTeams + 2
        int game = 1;
        numOfMatchups = getNumofMatchups(qteam);
        int vertices = numOfMatchups + NUM_OF_TEAMS + 2;
        FlowNetwork flowNetwork = new FlowNetwork(vertices);

        // calculate the maximum wins possible for the team passed to this method
        int maxwins = division.get(qteam)[WINS_POSITION] + division.get(qteam)[GAMES_LEFT];
        int start = 0;

        // iterate over all of the team combinations
        for (int x = 0; x < NUM_OF_TEAMS; x++) {
            String team1 = teams[x];
            for (int y = 0; y < x; y++) {
                String team2 = teams[y];
                FlowEdge edge = new FlowEdge(start, game,
                        division.get(team1)[GAMES_LEFT + 1 + division.get(team2)[TEAM_NUMBER_POSITION]]);
                flowNetwork.addEdge(edge);
                FlowEdge edgex = new FlowEdge(game, numOfMatchups + 1 + x,
                        Integer.MAX_VALUE);
                flowNetwork.addEdge(edgex);
                FlowEdge edgey = new FlowEdge(game, numOfMatchups + 1 + division.get(team2)[TEAM_NUMBER_POSITION],
                        Integer.MAX_VALUE);
                flowNetwork.addEdge(edgey);
                game++;
            }
            FlowEdge edgefin = new FlowEdge(numOfMatchups + 1 + division.get(team1)[TEAM_NUMBER_POSITION],
                    vertices - 1, Math.max(0, maxwins - division.get(team1)[WINS_POSITION]));
            flowNetwork.addEdge(edgefin);
            // add an edge from team1 to the 'end' and give it the capacity of the wins that the
            // query team could afford  winning without overtaking our query team,
            // i.e. the query teams max possible wins minus team1's current wins

        }
        return flowNetwork;
    }

    private boolean isTriviallyEliminated(String team) {
        // is the team trivially eliminated
        // if number of games left played is possible for them to become division leader
        // if so return true
        // The division leader would be the team returned by certificate of elimination

        return false;

    }

    private int getNumofMatchups(String qteam) {
        return ((NUM_OF_TEAMS - 1) * NUM_OF_TEAMS) / 2;
    }

    // subset R of teams that eliminates given team; null if not eliminated
    public Iterable<String> certificateOfElimination(String team) {
        // if trivially eliminated
        // add the division leader
        FlowNetwork flowNetwork = getFlowNetwork(team);
        FordFulkerson fordFulkerson = new FordFulkerson(flowNetwork, 0, flowNetwork.V() - 1);
        // loop through all the teams
        // if it's in the incut of the fordFulkerson
        // add to the list
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
