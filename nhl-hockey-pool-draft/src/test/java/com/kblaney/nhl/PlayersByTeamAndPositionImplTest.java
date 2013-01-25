package com.kblaney.nhl;

import java.util.Collections;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class PlayersByTeamAndPositionImplTest
{
  private Team arbitraryTeam;
  private Position arbitraryPosition;
  private PlayersByTeamAndPosition playersByTeamAndPosition;

  @Before
  public void setUp()
  {
    arbitraryTeam = Team.CALGARY_FLAMES;
    arbitraryPosition = Position.DEFENSEMAN;
    playersByTeamAndPosition = new PlayersByTeamAndPositionImpl();
  }

  @Test(expected = IllegalArgumentException.class)
  public void addPlayer_nullPlayer()
  {
    playersByTeamAndPosition.addPlayer(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getPlayersOnTeamAtPosition_nullTeam()
  {
    playersByTeamAndPosition.getPlayersOnTeamAtPosition(null, arbitraryPosition);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getPlayersOnTeamAtPosition_nullPosition()
  {
    playersByTeamAndPosition.getPlayersOnTeamAtPosition(arbitraryTeam, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getPlayersOnTeam_nullTeam()
  {
    playersByTeamAndPosition.getPlayersOnTeam(null);
  }

  @Test
  public void zeroPlayers()
  {
    assertEquals(0, playersByTeamAndPosition.getTeams().size());
    assertEquals(0, playersByTeamAndPosition.getNumPlayers());
    assertEquals(Collections.emptyMap(), playersByTeamAndPosition.getPlayersOnTeam(arbitraryTeam));
    assertEquals(Collections.emptySet(),
          playersByTeamAndPosition.getPlayersOnTeamAtPosition(arbitraryTeam, arbitraryPosition));
  }

  @Test
  public void onePlayer()
  {
    final Player player = new Player("Kyle", "Blaney", arbitraryTeam, arbitraryPosition);
    playersByTeamAndPosition.addPlayer(player);

    assertEquals(1, playersByTeamAndPosition.getTeams().size());
    assertEquals(1, playersByTeamAndPosition.getNumPlayers());
    assertEquals(1, playersByTeamAndPosition.getPlayersOnTeam(arbitraryTeam).size());

    final Team arbitraryOtherTeam = Team.BOSTON_BRUINS;
    assertEquals(Collections.emptyMap(), playersByTeamAndPosition.getPlayersOnTeam(arbitraryOtherTeam));
    assertEquals(Collections.emptySet(),
          playersByTeamAndPosition.getPlayersOnTeamAtPosition(arbitraryOtherTeam, arbitraryPosition));

    final Position arbitraryOtherPosition = Position.GOALIE;
    assertEquals(Collections.emptySet(),
          playersByTeamAndPosition.getPlayersOnTeamAtPosition(arbitraryTeam, arbitraryOtherPosition));

    final Set<Player> playersOnTeamAtPosition = playersByTeamAndPosition.getPlayersOnTeamAtPosition(arbitraryTeam,
          arbitraryPosition);
    assertEquals(1, playersOnTeamAtPosition.size());

    assertEquals(player, playersOnTeamAtPosition.iterator().next());
  }

  @Test
  public void manyPlayersOnOneTeam()
  {
    playersByTeamAndPosition.addPlayer(new Player("Jarome", "Iginla", arbitraryTeam, Position.FORWARD));
    playersByTeamAndPosition.addPlayer(new Player("Daymond", "Langkow", arbitraryTeam, Position.FORWARD));
    playersByTeamAndPosition.addPlayer(new Player("Stephane", "Yelle", arbitraryTeam, Position.FORWARD));
    playersByTeamAndPosition.addPlayer(new Player("Darren", "McCarty", arbitraryTeam, Position.FORWARD));
    playersByTeamAndPosition.addPlayer(new Player("Roman", "Hamrlik", arbitraryTeam, Position.DEFENSEMAN));
    playersByTeamAndPosition.addPlayer(new Player("Dion", "Phaneuf", arbitraryTeam, Position.DEFENSEMAN));
    playersByTeamAndPosition.addPlayer(new Player("Rhett", "Warrener", arbitraryTeam, Position.DEFENSEMAN));
    playersByTeamAndPosition.addPlayer(new Player("Miika", "Kiprusoff", arbitraryTeam, Position.GOALIE));
    playersByTeamAndPosition.addPlayer(new Player("Phillipe", "Sauve", arbitraryTeam, Position.GOALIE));

    assertEquals(1, playersByTeamAndPosition.getTeams().size());
    assertEquals(9, playersByTeamAndPosition.getNumPlayers());
    assertEquals(Collections.emptySet(),
          playersByTeamAndPosition.getPlayersOnTeamAtPosition(Team.SAN_JOSE_SHARKS, Position.FORWARD));
    assertEquals(Collections.emptySet(),
          playersByTeamAndPosition.getPlayersOnTeamAtPosition(Team.SAN_JOSE_SHARKS, Position.DEFENSEMAN));
    assertEquals(Collections.emptySet(),
          playersByTeamAndPosition.getPlayersOnTeamAtPosition(Team.SAN_JOSE_SHARKS, Position.GOALIE));

    final Set<Player> forwards = playersByTeamAndPosition.getPlayersOnTeamAtPosition(arbitraryTeam, Position.FORWARD);
    assertEquals(4, forwards.size());

    final Set<Player> defensemen = playersByTeamAndPosition.getPlayersOnTeamAtPosition(arbitraryTeam,
          Position.DEFENSEMAN);
    assertEquals(3, defensemen.size());

    final Set<Player> goalies = playersByTeamAndPosition.getPlayersOnTeamAtPosition(arbitraryTeam, Position.GOALIE);
    assertEquals(2, goalies.size());
  }

  @Test
  public void manyPlayersManyTeams()
  {
    playersByTeamAndPosition.addPlayer(new Player("Jarome", "Iginla", Team.CALGARY_FLAMES, Position.FORWARD));
    playersByTeamAndPosition.addPlayer(new Player("Martin", "St. Louis", Team.TAMPA_BAY_LIGHTNING, Position.FORWARD));
    playersByTeamAndPosition.addPlayer(new Player("Bryan", "Berard", Team.CHICAGO_BLACKHAWKS, Position.DEFENSEMAN));
    playersByTeamAndPosition.addPlayer(new Player("Nikolai", "Khabibulin", Team.CHICAGO_BLACKHAWKS, Position.GOALIE));
    playersByTeamAndPosition.addPlayer(new Player("Roman", "Hamrlik", Team.CALGARY_FLAMES, Position.DEFENSEMAN));
    playersByTeamAndPosition.addPlayer(new Player("Dion", "Phaneuf", Team.CALGARY_FLAMES, Position.DEFENSEMAN));
    playersByTeamAndPosition.addPlayer(new Player("Jose", "Theodore", Team.MONTREAL_CANADIENS, Position.GOALIE));
    playersByTeamAndPosition.addPlayer(new Player("Cristobal", "Huet", Team.MONTREAL_CANADIENS, Position.GOALIE));
    playersByTeamAndPosition.addPlayer(new Player("Mike", "Ribeiro", Team.MONTREAL_CANADIENS, Position.FORWARD));
    playersByTeamAndPosition.addPlayer(new Player("Jan", "Bulis", Team.MONTREAL_CANADIENS, Position.FORWARD));

    assertEquals(4, playersByTeamAndPosition.getTeams().size());
    assertEquals(10, playersByTeamAndPosition.getNumPlayers());

    // Test getting forwards from teams with 0, 1 and 2 forwards.
    //
    final Set<Player> sanJoseForwards = playersByTeamAndPosition.getPlayersOnTeamAtPosition(Team.SAN_JOSE_SHARKS,
          Position.FORWARD);
    assertEquals(0, sanJoseForwards.size());
    final Set<Player> tampaBayForwards = playersByTeamAndPosition.getPlayersOnTeamAtPosition(Team.TAMPA_BAY_LIGHTNING,
          Position.FORWARD);
    assertEquals(1, tampaBayForwards.size());
    final Set<Player> montrealForwards = playersByTeamAndPosition.getPlayersOnTeamAtPosition(Team.MONTREAL_CANADIENS,
          Position.FORWARD);
    assertEquals(2, montrealForwards.size());

    // Test getting defensemen from teams with 0, 1 and 2 defensemen.
    //
    final Set<Player> buffaloDefensemen = playersByTeamAndPosition.getPlayersOnTeamAtPosition(Team.BUFFALO_SABRES,
          Position.DEFENSEMAN);
    assertEquals(0, buffaloDefensemen.size());
    final Set<Player> chicagoDefensemen = playersByTeamAndPosition.getPlayersOnTeamAtPosition(Team.CHICAGO_BLACKHAWKS,
          Position.DEFENSEMAN);
    assertEquals(1, chicagoDefensemen.size());
    final Set<Player> calgaryDefensemen = playersByTeamAndPosition.getPlayersOnTeamAtPosition(Team.CALGARY_FLAMES,
          Position.DEFENSEMAN);
    assertEquals(2, calgaryDefensemen.size());

    // Test getting goalies from teams with 0, 1 and 2 goalies.
    //
    final Set<Player> buffaloGoalies = playersByTeamAndPosition.getPlayersOnTeamAtPosition(Team.BUFFALO_SABRES,
          Position.GOALIE);
    assertEquals(0, buffaloGoalies.size());
    final Set<Player> chicagoGoalies = playersByTeamAndPosition.getPlayersOnTeamAtPosition(Team.CHICAGO_BLACKHAWKS,
          Position.GOALIE);
    assertEquals(1, chicagoGoalies.size());
    final Set<Player> montrealGalies = playersByTeamAndPosition.getPlayersOnTeamAtPosition(Team.MONTREAL_CANADIENS,
          Position.GOALIE);
    assertEquals(2, montrealGalies.size());
  }

  @Test
  public void getPlayersOnTeamAtPosition_ensureDefensiveCopy()
  {
    playersByTeamAndPosition.addPlayer(new Player("Jarome", "Iginla", Team.CALGARY_FLAMES, Position.FORWARD));
    final Set<Player> calgaryForwards = playersByTeamAndPosition.getPlayersOnTeamAtPosition(Team.CALGARY_FLAMES,
          Position.FORWARD);
    final int numCalgaryForwards = calgaryForwards.size();

    // Try to maliciously alter the object's internal state.
    //
    calgaryForwards.clear();

    assertEquals(numCalgaryForwards,
          playersByTeamAndPosition.getPlayersOnTeamAtPosition(Team.CALGARY_FLAMES, Position.FORWARD).size());
  }
}
