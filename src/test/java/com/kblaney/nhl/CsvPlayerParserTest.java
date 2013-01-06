package com.kblaney.nhl;

import static org.junit.Assert.*;
import com.google.common.collect.Sets;
import java.io.StringReader;
import java.text.ParseException;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

public final class CsvPlayerParserTest
{
  @Test(expected = IllegalArgumentException.class)
  public void constructor_nullReader()
  {
    new CsvPlayerParser(null);
  }

  @Test(expected = ParseException.class)
  public void getPlayersByTeamAndPosition_tooManyFields() throws Exception
  {
    getPlayersByTeamAndPosition("Dave,Woods,ANA,G,1,TOO_MANY");
  }

  private PlayersByTeamAndPosition getPlayersByTeamAndPosition(
        final String input) throws Exception
  {
    return new CsvPlayerParser(new StringReader(input)).
          getPlayersByTeamAndPosition();
  }

  @Test(expected = ParseException.class)
  public void getPlayersByTeamAndPosition_tooFewFields() throws Exception
  {
    getPlayersByTeamAndPosition("Dave,Woods,ANA");
  }

  @Test(expected = ParseException.class)
  public void getPlayersByTeamAndPosition_emptyFirstName() throws Exception
  {
    getPlayersByTeamAndPosition(",Woods,ANA,G");
  }

  @Test(expected = ParseException.class)
  public void getPlayersByTeamAndPosition_emptyLastName() throws Exception
  {
    getPlayersByTeamAndPosition("Dave,,ANA,G");
  }

  @Test(expected = ParseException.class)
  public void getPlayersByTeamAndPosition_invalidTeamShortform()
        throws Exception
  {
    getPlayersByTeamAndPosition("Dave,Woods,ANT,G");
  }

  @Test(expected = ParseException.class)
  public void getPlayersByTeamAndPosition_invalidPositionShortform()
        throws Exception
  {
    getPlayersByTeamAndPosition("Dave,Woods,ANA,E");
  }

  @Test
  public void getPlayersByTeamAndPosition_leadingAndTrailingWhiteSpace()
        throws Exception
  {
    final PlayersByTeamAndPosition players =
          getPlayersByTeamAndPosition("    Dave   , Woods   ,  ANA  ,  F");
    final Set<Player> anaheimForwards = players.
          getPlayersOnTeamAtPosition(Team.ANAHEIM_DUCKS, Position.FORWARD);

    final Player expected = new Player("Dave", "Woods", Team.ANAHEIM_DUCKS,
          Position.FORWARD);
    assertEquals(expected, anaheimForwards.iterator().next());
  }

  @Test
  public void getPlayersByTeamAndPosition_nineLines() throws Exception
  {
    final PlayersByTeamAndPosition playersByTeamAndPosition =
          getPlayersByTeamAndPosition(StringUtils.join(new String[]
          {
            "Dave,Woods,ANA,F",
            "Brad,Hamilton,WIN,D",
            "Rob,Hodgins,BOS,G",
            "Daniel,Briere,BUF,F",
            "Miika,Kiprusoff,CGY,G",
            "Eric,Staal,CAR,G",
            "Bryan,Berard,CHI,D",
            "Marc,Denis,CLB,G",
            "Mike,Ribeiro,MON,F"
          }, "\n"));
    assertEquals(9, playersByTeamAndPosition.getNumPlayers());
  }

  @Test
  public void getPlayersByTeamAndPosition_noId() throws Exception
  {
    final PlayersByTeamAndPosition players =
          getPlayersByTeamAndPosition("Teemu,Selanne,ANA,F");
    final Set<Player> expected = Sets.newHashSet(
          new Player("Teemu", "Selanne", Team.ANAHEIM_DUCKS, Position.FORWARD));
    final Set<Player> actual = players.getPlayersOnTeamAtPosition(
          Team.ANAHEIM_DUCKS, Position.FORWARD);
    assertEquals(expected, actual);
  }

  @Test
  public void getPlayersByTeamAndPosition_emptyId() throws Exception
  {
    final PlayersByTeamAndPosition players =
          getPlayersByTeamAndPosition("Teemu,Selanne,ANA,F,");
    final Set<Player> expected = Sets.newHashSet(
          new Player("Teemu", "Selanne", Team.ANAHEIM_DUCKS, Position.FORWARD));
    final Set<Player> actual = players.getPlayersOnTeamAtPosition(
          Team.ANAHEIM_DUCKS, Position.FORWARD);
    assertEquals(expected, actual);
  }

  @Test
  public void getPlayersByTeamAndPosition_nonEmptyId() throws Exception
  {
    final PlayersByTeamAndPosition players =
          getPlayersByTeamAndPosition("Teemu,Selanne,ANA,F,3");
    final Set<Player> expected = Sets.newHashSet(
          new Player("Teemu", "Selanne", Team.ANAHEIM_DUCKS, Position.FORWARD,
          "3"));
    final Set<Player> actual = players.getPlayersOnTeamAtPosition(
          Team.ANAHEIM_DUCKS, Position.FORWARD);
    assertEquals(expected, actual);
  }

  @Test
  public void closeQuietly()
  {
    new CsvPlayerParser(new StringReader("ARBITRARY_STRING")).closeQuietly();
    assertTrue(true);
  }
}
