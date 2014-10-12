package com.kblaney.nhl;

import static org.junit.Assert.*;
import org.junit.Test;

public final class TeamTest
{
  @Test
  public void getShortform()
  {
    assertEquals("ANA", Team.ANAHEIM_DUCKS.getShortform());
    assertEquals("ARI", Team.ARIZONA_COYOTES.getShortform());
    assertEquals("BOS", Team.BOSTON_BRUINS.getShortform());
    assertEquals("BUF", Team.BUFFALO_SABRES.getShortform());
    assertEquals("CGY", Team.CALGARY_FLAMES.getShortform());
    assertEquals("CAR", Team.CAROLINA_HURRICANES.getShortform());
    assertEquals("CHI", Team.CHICAGO_BLACKHAWKS.getShortform());
    assertEquals("COL", Team.COLORADO_AVALANCHE.getShortform());
    assertEquals("CLB", Team.COLUMBUS_BLUE_JACKETS.getShortform());
    assertEquals("DAL", Team.DALLAS_STARS.getShortform());
    assertEquals("DET", Team.DETROIT_RED_WINGS.getShortform());
    assertEquals("EDM", Team.EDMONTON_OILERS.getShortform());
    assertEquals("FLA", Team.FLORIDA_PANTHERS.getShortform());
    assertEquals("LA", Team.LOS_ANGELES_KINGS.getShortform());
    assertEquals("MIN", Team.MINNESOTA_WILD.getShortform());
    assertEquals("MON", Team.MONTREAL_CANADIENS.getShortform());
    assertEquals("NAS", Team.NASHVILLE_PREDATORS.getShortform());
    assertEquals("NJ", Team.NEW_JERSEY_DEVILS.getShortform());
    assertEquals("NYI", Team.NEW_YORK_ISLANDERS.getShortform());
    assertEquals("NYR", Team.NEW_YORK_RANGERS.getShortform());
    assertEquals("OTT", Team.OTTAWA_SENATORS.getShortform());
    assertEquals("PHI", Team.PHILADELPHIA_FLYERS.getShortform());
    assertEquals("PIT", Team.PITTSBURGH_PENGUINS.getShortform());
    assertEquals("SJ", Team.SAN_JOSE_SHARKS.getShortform());
    assertEquals("STL", Team.ST_LOUIS_BLUES.getShortform());
    assertEquals("TB", Team.TAMPA_BAY_LIGHTNING.getShortform());
    assertEquals("TOR", Team.TORONTO_MAPLE_LEAFS.getShortform());
    assertEquals("VAN", Team.VANCOUVER_CANUCKS.getShortform());
    assertEquals("WAS", Team.WASHINGTON_CAPITALS.getShortform());
    assertEquals("WIN", Team.WINNIPEG_JETS.getShortform());
  }

  @Test
  public void isTeamThatHasShortform_invalidShortforms()
  {
    assertFalse(Team.isTeamThatHasShortform("ABA"));
    assertFalse(Team.isTeamThatHasShortform("ZAX"));
    assertFalse(Team.isTeamThatHasShortform("anaheim"));
  }

  @Test
  public void isTeamThatHasShortform_validShortforms()
  {
    for (final Team team : Team.values())
    {
      assertTrue(Team.isTeamThatHasShortform(team.getShortform()));
      assertTrue(Team.isTeamThatHasShortform(team.getShortform().toLowerCase()));
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void getTeamThatHasShortform_nullShortform()
  {
    Team.getTeamThatHasShortform(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getTeamThatHasShortform_invalidShortform()
  {
    final String arbitraryInvalidShortform = "BUB";
    Team.getTeamThatHasShortform(arbitraryInvalidShortform);
  }

  @Test
  public void getTeamThatHasShortform_validShortforms()
  {
    for (final Team team : Team.values())
    {
      assertEquals(team, Team.getTeamThatHasShortform(team.getShortform()));
      assertEquals(team, Team.getTeamThatHasShortform(team.getShortform().toLowerCase()));
    }
  }
}
