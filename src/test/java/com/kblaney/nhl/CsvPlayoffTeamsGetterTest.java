package com.kblaney.nhl;

import com.google.common.collect.Lists;
import java.io.StringReader;
import java.text.ParseException;
import org.junit.Test;
import static org.junit.Assert.*;

public final class CsvPlayoffTeamsGetterTest
{
  @Test
  public void constructor_readerHasEmptyInput() throws Exception
  {
    try
    {
      new CsvPlayoffTeamsGetter(new StringReader(""));
      fail();
    }
    catch (final ParseException e)
    {
      assertEquals("No lines in playoff teams reader", e.getMessage());
    }
  }

  @Test
  public void constructor_readerHasTooFewLines() throws Exception
  {
    try
    {
      new CsvPlayoffTeamsGetter(new StringReader("TOR,ATL"));
      fail();
    }
    catch (final ParseException e)
    {
      assertEquals("Too few lines in playoff teams reader", e.getMessage());
    }
  }

  @Test
  public void constructor_readerHasInvalidTeamOnFirstLine() throws Exception
  {
    try
    {
      new CsvPlayoffTeamsGetter(new StringReader("ANA,WIN,BOS,BUF,CGY,CAR,CHI,COLX\n"
            + "CLB,DAL,DET,EDM,FLA,LA,MIN,MON\n"));
      fail();
    }
    catch (final ParseException e)
    {
      assertEquals("Invalid team shortform:COLX", e.getMessage());
    }
  }

  @Test
  public void constructor_readerHasInvalidTeamOnSecondLine() throws Exception
  {
    try
    {
      new CsvPlayoffTeamsGetter(new StringReader("ANA,WIN,BOS,BUF,CGY,CAR,CHI,COL\n"
            + "CLB,DAL,DET,EDMX,FLA,LA,MIN,MON\n"));
      fail();
    }
    catch (final ParseException e)
    {
      assertEquals("Invalid team shortform:EDMX", e.getMessage());
    }
  }

  @Test
  public void constructor_readerHasTooFewTeamsOnFirstLine() throws Exception
  {
    try
    {
      new CsvPlayoffTeamsGetter(new StringReader("ANA,WIN,BOS,BUF,CGY,CAR,CHI\n" + "CLB,DAL,DET,EDM,FLA,LA,MIN,MON\n"));
      fail();
    }
    catch (final ParseException e)
    {
      assertEquals("Not enough upper-half teams", e.getMessage());
    }
  }

  @Test
  public void constructor_readerHasTooFewTeamsOnSecondLine() throws Exception
  {
    try
    {
      new CsvPlayoffTeamsGetter(new StringReader("ANA,WIN,BOS,BUF,CGY,CAR,CHI,COL\n" + "CLB,DAL,DET,EDM,FLA,LA,MIN\n"));
      fail();
    }
    catch (final ParseException e)
    {
      assertEquals("Not enough lower-half teams", e.getMessage());
    }
  }

  @Test
  public void validInput() throws Exception
  {
    final PlayoffTeamsGetter playoffTeamsGetter = new CsvPlayoffTeamsGetter(new StringReader(
          "ANA,WIN,BOS,BUF,CGY,CAR,CHI,COL,PHO\n" + "CLB,DAL,DET,EDM,FLA,LA,MIN,MON,PHI,SJ,TB\n"));
    assertEquals(Lists.newArrayList(Team.ANAHEIM_DUCKS, Team.WINNIPEG_JETS, Team.BOSTON_BRUINS, Team.BUFFALO_SABRES,
          Team.CALGARY_FLAMES, Team.CAROLINA_HURRICANES, Team.CHICAGO_BLACKHAWKS, Team.COLORADO_AVALANCHE,
          Team.PHOENIX_COYOTES), playoffTeamsGetter.getUpperHalfTeams());
    assertEquals(Lists.newArrayList(Team.COLUMBUS_BLUE_JACKETS, Team.DALLAS_STARS, Team.DETROIT_RED_WINGS,
          Team.EDMONTON_OILERS, Team.FLORIDA_PANTHERS, Team.LOS_ANGELES_KINGS, Team.MINNESOTA_WILD,
          Team.MONTREAL_CANADIENS, Team.PHILADELPHIA_FLYERS, Team.SAN_JOSE_SHARKS, Team.TAMPA_BAY_LIGHTNING),
          playoffTeamsGetter.getLowerHalfTeams());
  }
}
