import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author <insert your name hre>
 */
public class BaseballEliminationTest {

    BaseballElimination baseballEliminationTeams4;
    BaseballElimination baseballEliminationTeams4a;
    BaseballElimination baseballEliminationTeams4b;
    BaseballElimination baseballEliminationTeams5;
    BaseballElimination baseballEliminationTeams5c;
    BaseballElimination baseballEliminationTeams30;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        baseballEliminationTeams4 = new BaseballElimination("baseball-testing-files/teams4.txt");
        baseballEliminationTeams4a = new BaseballElimination("baseball-testing-files/teams4a.txt");
        baseballEliminationTeams4b = new BaseballElimination("baseball-testing-files/teams4b.txt");
        baseballEliminationTeams5 = new BaseballElimination("baseball-testing-files/teams5.txt");
        baseballEliminationTeams5c = new BaseballElimination("baseball-testing-files/teams5c.txt");
        baseballEliminationTeams30 = new BaseballElimination("baseball-testing-files/teams30.txt");
    }


    /**
     * Test method for {@link BaseballElimination#numberOfTeams()}.
     */
    @Test
    public void testNumberOfTeams() {
        assertEquals(4, baseballEliminationTeams4.numberOfTeams());
    }

    @Test
    public void testNumberOfTeams30() {
        assertEquals(30, baseballEliminationTeams30.numberOfTeams());
    }

    @Test
    public void testNumberOfTeams5() {
        assertEquals(5, baseballEliminationTeams5.numberOfTeams());
    }

    /**
     * Test method for {@link BaseballElimination#teams()}.
     */
    @Test
    public void testTeams() {
        String[] teams4Expected = {"Atlanta", "Philadelphia", "New_York", "Montreal"};
        ArrayList<String> teams4ExpectedList = new ArrayList<String>();
        Collections.addAll(teams4ExpectedList, teams4Expected);
        for (String actualTeam : baseballEliminationTeams4.teams()) {
            assertTrue(actualTeam + " is not in the expected list", teams4ExpectedList.contains(actualTeam));
        }
    }

    /**
     * Test method for {@link BaseballElimination#wins(java.lang.String)}.
     */
    @Test
    public void testWinsTeams4() {
        String[] teams4Expected = {"Atlanta", "Philadelphia", "New_York", "Montreal"};
        int[] teams4ExpectedWins = {83, 80, 78, 77};
        for (int i = 0; i < teams4ExpectedWins.length; i++) {
            assertEquals(teams4ExpectedWins[i], baseballEliminationTeams4.wins(teams4Expected[i]));
        }
    }

    @Test
    public void testWinsTeams5() {
        String[] teams5Expected = {"New_York", "Baltimore", "Boston", "Toronto", "Detroit"};
        int[] teams5ExpectedWins = {75, 71, 69, 63, 49};
        for (int i = 0; i < teams5ExpectedWins.length; i++) {
            assertEquals(teams5ExpectedWins[i], baseballEliminationTeams5.wins(teams5Expected[i]));
        }
    }

    @Test
    public void testWinsTeams4a() {
        String[] teams4aExpected = {"CIA", "Ghaddafi", "Bin_Ladin", "Obama"};
        int[] teams4aExpectedWins = {3, 2, 3, 4};
        for (int i = 0; i < teams4aExpectedWins.length; i++) {
            assertEquals(teams4aExpectedWins[i], baseballEliminationTeams4a.wins(teams4aExpected[i]));
        }
    }

    /**
     * Test method for {@link BaseballElimination#losses(java.lang.String)}.
     */
    @Test
    public void testLosses() {
        String[] teams4Expected = {"Atlanta", "Philadelphia", "New_York", "Montreal"};
        int[] teams4ExpectedLosses = {71, 79, 78, 82};
        for (int i = 0; i < teams4Expected.length; i++) {
            assertEquals(teams4ExpectedLosses[i], baseballEliminationTeams4.losses(teams4Expected[i]));
        }
    }

    @Test
    public void testLossesTeams5() {
        String[] teams5Expected = {"New_York", "Baltimore", "Boston", "Toronto", "Detroit"};
        int[] teams5ExpectedLosses = {59, 63, 66, 72, 86};
        for (int i = 0; i < teams5ExpectedLosses.length; i++) {
            assertEquals(teams5ExpectedLosses[i], baseballEliminationTeams5.losses(teams5Expected[i]));
        }
    }

    @Test
    public void testLossesTeams4a() {
        String[] teams4aExpected = {"CIA", "Ghaddafi", "Bin_Ladin", "Obama"};
        int[] teams4aExpectedLosses = {3, 5, 6, 2};
        for (int i = 0; i < teams4aExpected.length; i++) {
            assertEquals(teams4aExpectedLosses[i], baseballEliminationTeams4a.losses(teams4aExpected[i]));
        }
    }

    /**
     * Test method for {@link BaseballElimination#remaining(java.lang.String)}.
     */
    @Test
    public void testRemaining() {
        String[] teams4Expected = {"Atlanta", "Philadelphia", "New_York", "Montreal"};
        int[] teams4ExpectedRemaining = {8, 3, 6, 3};
        for (int i = 0; i < teams4Expected.length; i++) {
            assertEquals(teams4ExpectedRemaining[i], baseballEliminationTeams4.remaining(teams4Expected[i]));
        }
    }

    @Test
    public void testRemainingTeams5() {
        String[] teams5Expected = {"New_York", "Baltimore", "Boston", "Toronto", "Detroit"};
        int[] teams5ExpectedRemaining = {28, 28, 27, 27, 27};
        for (int i = 0; i < teams5ExpectedRemaining.length; i++) {
            assertEquals(teams5ExpectedRemaining[i], baseballEliminationTeams5.remaining(teams5Expected[i]));
        }
    }

    @Test
    public void testRemainingTeams4a() {
        String[] teams4aExpected = {"CIA", "Ghaddafi", "Bin_Ladin", "Obama"};
        int[] teams4aExpectedRemaining = {3, 2, 0, 3};
        for (int i = 0; i < teams4aExpected.length; i++) {
            assertEquals(teams4aExpectedRemaining[i], baseballEliminationTeams4a.remaining(teams4aExpected[i]));
        }
    }


    /**
     * Test method for {@link BaseballElimination#against(java.lang.String, java.lang.String)}.
     */
    @Test
    public void testAgainstTeams4() {
        String[] teams4Expected = {"Atlanta", "Philadelphia", "New_York", "Montreal"};
        int[] teams4ExpectedAgainstAtlanta = {0, 1, 6, 1};
        int[] teams4ExpectedAgainstMontreal = {1, 2, 0, 0};
        for (int i = 0; i < teams4Expected.length; i++) {
            assertEquals(teams4ExpectedAgainstAtlanta[i], baseballEliminationTeams4.against("Atlanta", teams4Expected[i]));
            assertEquals(teams4ExpectedAgainstMontreal[i], baseballEliminationTeams4.against("Montreal", teams4Expected[i]));
        }
    }

    @Test
    public void testAgainstTeams5() {
        String[] teams5Expected = {"New_York", "Baltimore", "Boston", "Toronto", "Detroit"};
        int[] teams5ExpectedAgainstNewYork = {0, 3, 8, 7, 3};
        int[] teams5ExpectedAgainstBoston = {8, 2, 0, 0, 3};
        for (int i = 0; i < teams5Expected.length; i++) {
            assertEquals(teams5ExpectedAgainstNewYork[i], baseballEliminationTeams5.against("New_York", teams5Expected[i]));
            assertEquals(teams5ExpectedAgainstBoston[i], baseballEliminationTeams5.against("Boston", teams5Expected[i]));
        }
    }

    @Test
    public void testAgainstTeams4a() {
        String[] teams4aExpected = {"CIA", "Ghaddafi", "Bin_Ladin", "Obama"};
        int[] teams4aExpectedAgainstCIA = {0, 1, 0, 2};
        int[] teams4aExpectedAgainstObama = {2, 1, 0, 0};
        for (int i = 0; i < teams4aExpected.length; i++) {
            assertEquals(teams4aExpectedAgainstCIA[i], baseballEliminationTeams4a.against("CIA", teams4aExpected[i]));
            assertEquals(teams4aExpectedAgainstObama[i], baseballEliminationTeams4a.against("Obama", teams4aExpected[i]));
        }
    }


    /**
     * Test method for {@link BaseballElimination#isEliminated(java.lang.String)}.
     */
    @Test
    public void testIsEliminatedTeams4() {
        assertFalse(baseballEliminationTeams4.isEliminated("Atlanta"));
        assertTrue(baseballEliminationTeams4.isEliminated("Philadelphia"));
        assertFalse(baseballEliminationTeams4.isEliminated("New_York"));
        assertTrue(baseballEliminationTeams4.isEliminated("Montreal"));
    }

    //TODO Write testIsEliminated for teams5.txt and one other input file
    @Test
    public void testIsEliminatedTeams5() {
        assertFalse(baseballEliminationTeams5.isEliminated("New_York"));
        assertFalse(baseballEliminationTeams5.isEliminated("Baltimore"));
        assertFalse(baseballEliminationTeams5.isEliminated("Boston"));
        assertFalse(baseballEliminationTeams5.isEliminated("Toronto"));
        assertTrue(baseballEliminationTeams5.isEliminated("Detroit"));

    }

    @Test
    public void testIsEliminatedTeams4a() {
        assertFalse(baseballEliminationTeams4a.isEliminated("CIA"));
        assertTrue(baseballEliminationTeams4a.isEliminated("Ghaddafi"));
        assertTrue(baseballEliminationTeams4a.isEliminated("Bin_Ladin"));
        assertFalse(baseballEliminationTeams4a.isEliminated("Obama"));
    }

    /**
     * Test method for {@link BaseballElimination#certificateOfElimination(java.lang.String)}.
     */
    @Test
    public void testCertificateOfEliminationTriviallyEliminatedTeams4() {
        String[] expectedEliminationCertificate = {"Atlanta"};
        int index = 0;
        for (String actualEliminationTeam :
                baseballEliminationTeams4.certificateOfElimination("Montreal")) {
            assertEquals(expectedEliminationCertificate[index], actualEliminationTeam);
        }

    }

    @Test
    public void testCertificateOfEliminationTriviallyEliminatedTeams4b() {
        String[] expectedEliminationCertificate = {"Gryffindor"};
        int index = 0;
        for (String actualEliminationTeam :
                baseballEliminationTeams4b.certificateOfElimination("Slytherin")) {
            assertEquals(expectedEliminationCertificate[index], actualEliminationTeam);
        }

    }

    @Test
    public void testCertificateOfEliminationTriviallyEliminatedTeams5c() {
        String[] expectedEliminationCertificate = {"New_York"};
        int index = 0;
        for (String actualEliminationTeam :
                baseballEliminationTeams5c.certificateOfElimination("Washington")) {
            assertEquals(expectedEliminationCertificate[index], actualEliminationTeam);
        }

    }

    /**
     * Test method for {@link BaseballElimination#certificateOfElimination(java.lang.String)}.
     */
    @Test
    public void testCertificateOfEliminationNonTriviallyEliminatedTeams4() {
        List<String> teams4ExpectedList = Arrays.asList("Atlanta", "New_York");

        for (String actualEliminationTeam :
                baseballEliminationTeams4.certificateOfElimination("Philadelphia")) {
            assertTrue(actualEliminationTeam + " is not in the expected list", teams4ExpectedList.contains(actualEliminationTeam));

        }

    }

  @Test
  public void testCertificateOfEliminationNonTriviallyEliminatedTeams4a() {
    List<String> teams4ExpectedList = Arrays.asList("CIA", "Obama");

    for (String actualEliminationTeam :
            baseballEliminationTeams4a.certificateOfElimination("Ghaddafi")) {
      assertTrue(actualEliminationTeam + " is not in the expected list", teams4ExpectedList.contains(actualEliminationTeam));

    }

  }

  @Test
  public void testCertificateOfEliminationNonTriviallyEliminatedTeams5() {
    List<String> teams4ExpectedList = Arrays.asList("New_York", "Baltimore", "Boston", "Toronto");
    for (String actualEliminationTeam :
            baseballEliminationTeams5.certificateOfElimination("Detroit")) {
      assertTrue(actualEliminationTeam + " is not in the expected list", teams4ExpectedList.contains(actualEliminationTeam));

    }

  }

}
