import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

/**
 * @author <insert your name hre>
 *
 */
public class BaseballEliminationTest {

  BaseballElimination baseballEliminationTeams4;
  BaseballElimination baseballEliminationTeams5;

  /**
   * @throws java.lang.Exception
   */
  @Before
  public void setUp() throws Exception {
    baseballEliminationTeams4 = new BaseballElimination("baseball-testing-files/teams4.txt");
    baseballEliminationTeams5 = new BaseballElimination("baseball-testing-files/teams5.txt");
  }


  /**
   * Test method for {@link BaseballElimination#numberOfTeams()}.
   */
  @Test
  public void testNumberOfTeams() {
    assertEquals(4, baseballEliminationTeams4.numberOfTeams());
  }

  //TODO: write testNumberOfTeams test cases for teams5 and one other input file

  /**
   * Test method for {@link BaseballElimination#teams()}.
   */
  @Test
  public void testTeams() {
    String[] teams4Expected = {"Atlanta", "Philadelphia", "New_York", "Montreal"};
    ArrayList<String> teams4ExpectedList = new ArrayList<String>();
    for (String team: teams4Expected) {
      teams4ExpectedList.add(team);
    }
    for (String actualTeam: baseballEliminationTeams4.teams()) {
      assertTrue(actualTeam + " is not in the expected list",teams4ExpectedList.contains(actualTeam));
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

  //TODO Write testWins for teams5.txt and one other input file

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

  //TODO Write testLosses for teams5.txt and one other input file

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

  //TODO Write testRemaining for teams5.txt and one other input file


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

  //TODO Write testAgainst for teams5.txt and one other input file


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


  /**
   * Test method for {@link BaseballElimination#certificateOfElimination(java.lang.String)}.
   */
  @Test
  public void testCertificateOfEliminationTriviallyEliminatedTeams4() {
    String[] expectedEliminationCertificate = {"Atlanta"};
    int index = 0;
    for (String actualEliminationTeam: 
      baseballEliminationTeams4.certificateOfElimination("Montreal")) {
      assertEquals(expectedEliminationCertificate[index], actualEliminationTeam);
    }

  }

  //TODO Find two other test files where a team is trivially eliminated and write test cases for those

  /**
   * Test method for {@link BaseballElimination#certificateOfElimination(java.lang.String)}.
   */
  @Test
  public void testCertificateOfEliminationNonTriviallyEliminatedTeams4() {
    List<String> teams4ExpectedList = Arrays.asList("Atlanta", "New York");
    
    for (String actualEliminationTeam: 
      baseballEliminationTeams4.certificateOfElimination("Philadelphia")) {
      assertTrue(actualEliminationTeam + " is not in the expected list", teams4ExpectedList.contains(actualEliminationTeam));

    }

  }

  //TODO Write testCertificateOfElimination for teams5.txt and one other input file

}
