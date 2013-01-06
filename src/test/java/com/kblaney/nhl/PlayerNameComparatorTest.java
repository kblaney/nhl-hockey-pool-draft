package com.kblaney.nhl;

import java.util.Comparator;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class PlayerNameComparatorTest
{
  private String arbitraryFirstName;
  private String arbitraryLastName;
  private Team arbitraryTeam;
  private Position arbitraryPosition;
  private Player arbitraryPlayer;
  private Comparator<Player> comparator;

  @Before
  public void setUp()
  {
    arbitraryFirstName = "FIRST_NAME";
    arbitraryLastName = "LAST_NAME";
    arbitraryTeam = Team.ANAHEIM_DUCKS;
    arbitraryPosition = Position.DEFENSEMAN;
    arbitraryPlayer = new Player(arbitraryFirstName, arbitraryLastName, arbitraryTeam, arbitraryPosition);
    comparator = new PlayerNameComparator();
  }

  @Test
  public void compare_sameInstance()
  {
    assertEquals(0, comparator.compare(arbitraryPlayer, arbitraryPlayer));
  }

  @Test
  public void compare_playersWithDifferentLastNameSameFirstName()
  {
    final String firstPlayerLastName = "Appleby";
    final String secondPlayerLastName = "Zanzibar";
    final Player firstPlayer = getPlayerWithLastName(firstPlayerLastName);
    final Player secondPlayer = getPlayerWithLastName(secondPlayerLastName);

    assertFirstPlayerLessThanSecond(firstPlayer, secondPlayer);
  }

  private Player getPlayerWithLastName(final String lastName)
  {
    return new Player(arbitraryFirstName, lastName, arbitraryTeam, arbitraryPosition);
  }

  private void assertFirstPlayerLessThanSecond(final Player firstPlayer, final Player secondPlayer)
  {
    assertTrue(comparator.compare(firstPlayer, secondPlayer) < 0);
    assertTrue(comparator.compare(secondPlayer, firstPlayer) > 0);
  }

  @Test
  public void compare_onePlayerWithLowercaseLastName()
  {
    final String firstPlayerLastName = "Appleby";
    final String secondPlayerLastName = "van Riemsdyk";
    final Player firstPlayer = getPlayerWithLastName(firstPlayerLastName);
    final Player secondPlayer = getPlayerWithLastName(secondPlayerLastName);

    assertFirstPlayerLessThanSecond(firstPlayer, secondPlayer);
  }

  @Test
  public void compare_playersWithSameLastName()
  {
    final Player firstPlayer = new Player("Daniel", "Sedin", arbitraryTeam, arbitraryPosition);
    final Player secondPlayer = new Player("Henrik", "Sedin", arbitraryTeam, arbitraryPosition);

    assertFirstPlayerLessThanSecond(firstPlayer, secondPlayer);
  }
}
