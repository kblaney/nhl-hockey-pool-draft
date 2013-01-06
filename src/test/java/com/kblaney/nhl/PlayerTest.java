package com.kblaney.nhl;

import static org.junit.Assert.*;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

public final class PlayerTest
{
  private String firstName;
  private String lastName;
  private Team team;
  private Position position;
  private Player playerWithEmptyId;

  @Before
  public void setUp()
  {
    firstName = "FIRST_NAME";
    lastName = "LAST_NAME";
    team = Team.ANAHEIM_DUCKS;
    position = Position.DEFENSEMAN;
    playerWithEmptyId = new Player(firstName, lastName, team, position);
  }

  @Test(expected = NullPointerException.class)
  public void constructor_nullFirstName()
  {
    new Player(null, lastName, team, position);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructor_emptyFirstName()
  {
    new Player(StringUtils.EMPTY, lastName, team, position);
  }

  @Test(expected = NullPointerException.class)
  public void constructor_nullLastName()
  {
    new Player(firstName, null, team, position);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructor_emptyLastName()
  {
    new Player(firstName, StringUtils.EMPTY, team, position);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructor_nullTeam()
  {
    new Player(firstName, lastName, null, position);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructor_nullPosition()
  {
    new Player(firstName, lastName, team, null);
  }

  @Test
  public void getFirstName()
  {
    assertEquals(firstName, playerWithEmptyId.getFirstName());
  }

  @Test
  public void getLastName()
  {
    assertEquals(lastName, playerWithEmptyId.getLastName());
  }

  @Test
  public void getTeam()
  {
    assertEquals(team, playerWithEmptyId.getTeam());
  }

  @Test
  public void getPosition()
  {
    assertEquals(position, playerWithEmptyId.getPosition());
  }

  @Test
  public void getId_emptyId()
  {
    assertEquals(StringUtils.EMPTY, playerWithEmptyId.getId());
  }

  @Test
  public void getId_nonEmptyId()
  {
    final String id = "2";
    final Player player = new Player(firstName, lastName, team, position, id);
    assertEquals(id, player.getId());
  }

  @Test
  public void getFullName()
  {
    assertEquals("Kyle Blaney", new Player("Kyle", "Blaney", team, position).getFullName());
  }

  @Test
  public void getShortenedFullName_shortFirstNameShortLastName()
  {
    assertEquals("K. Blaney", new Player("Kyle", "Blaney", team, position).getShortenedFullName());
  }

  @Test
  public void getShortenedFullName_shortFirstNameLongLastName()
  {
    assertEquals("K. Blaneywilsonsmith", new Player("Kyle", "Blaneywilsonsmith", team, position).getShortenedFullName());
  }

  @Test
  public void getShortenedFullName_firstNameContainsDash()
  {
    assertEquals("J.P. Blaneywilsonsmith",
          new Player("Jean-Pierre", "Blaneywilsonsmith", team, position).getShortenedFullName());
  }

  @Test
  public void equals_null()
  {
    assertFalse(playerWithEmptyId.equals(null));
  }

  @Test
  public void equals_wrongClass()
  {
    final String arbitraryInstanceOfWrongClass = "A";
    assertFalse(playerWithEmptyId.equals(arbitraryInstanceOfWrongClass));
  }

  @Test
  public void equals_differentFirstName()
  {
    final String differentFirstName = firstName + "A";
    final Player playerWithDifferentFirstName = new Player(differentFirstName, lastName, team, position);
    assertNotEquals(playerWithEmptyId, playerWithDifferentFirstName);
  }

  private void assertNotEquals(final Player p1, final Player p2)
  {
    assertFalse(p1.equals(p2));
    assertFalse(p2.equals(p1));
  }

  @Test
  public void equals_differentLastName()
  {
    final String differentLastName = lastName + "A";
    final Player playerWithDifferentLastName = new Player(firstName, differentLastName, team, position);
    assertNotEquals(playerWithEmptyId, playerWithDifferentLastName);
  }

  @Test
  public void equals_differentTeam()
  {
    final Team differentTeam = Team.WINNIPEG_JETS;
    assertFalse(team == differentTeam);
    final Player playerWithDifferentTeam = new Player(firstName, lastName, differentTeam, position);
    assertNotEquals(playerWithEmptyId, playerWithDifferentTeam);
  }

  @Test
  public void equals_differentPosition()
  {
    final Position differentPosition = Position.FORWARD;
    assertFalse(position == differentPosition);
    final Player playerWithDifferentPosition = new Player(firstName, lastName, team, differentPosition);
    assertNotEquals(playerWithEmptyId, playerWithDifferentPosition);
  }

  @Test
  public void equals_differentId()
  {
    final String id = "1";
    final Player playerWithNonEmptyId = new Player(firstName, lastName, team, position, id);
    assertNotEquals(playerWithEmptyId, playerWithNonEmptyId);
  }

  @Test
  public void equals_equalNamesTeamsAndPositionsAndEmptyId()
  {
    final Player equalPlayer = new Player(firstName, lastName, team, position);
    assertPlayersAreEqual(playerWithEmptyId, equalPlayer);
  }

  private void assertPlayersAreEqual(final Player p1, final Player p2)
  {
    assertTrue(p1.equals(p2));
    assertTrue(p2.equals(p1));
  }

  @Test
  public void equals_equalNamesTeamsPositionsAndIds()
  {
    final String id = "3";
    final Player p1 = new Player(firstName, lastName, team, position, id);
    final Player p2 = new Player(firstName, lastName, team, position, id);
    assertPlayersAreEqual(p1, p2);
  }

  @Test
  public void hashCode_equalPlayer()
  {
    final Player equalPlayer = new Player(firstName, lastName, team, position);
    assertEquals(playerWithEmptyId.hashCode(), equalPlayer.hashCode());
  }

  @Test
  public void hashCode_repeatedCallsOnSameInstance()
  {
    final int hashCode = playerWithEmptyId.hashCode();
    assertEquals(hashCode, playerWithEmptyId.hashCode());
    assertEquals(hashCode, playerWithEmptyId.hashCode());
    assertEquals(hashCode, playerWithEmptyId.hashCode());
    assertEquals(hashCode, playerWithEmptyId.hashCode());
  }

  @Test
  public void testToString()
  {
    final String expected = lastName + ", " + firstName;
    assertEquals(expected, playerWithEmptyId.toString());
  }
}
