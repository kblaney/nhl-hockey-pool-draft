package com.kblaney.nhl;

import com.Ostermiller.util.CSVParser;
import com.google.common.collect.Lists;
import com.kblaney.commons.lang.ArgAssert;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.List;

/**
 * Gets teams in the playoffs.
 * 
 * <p>
 * This class requires that the reader passed to its constructor has two lines, each with comma-separated team
 * shortforms. The first line is shortforms of the teams in the upper half. The second line is shortforms of the teams
 * in the lower half. Typically, the two lists correspond to the NHL's Easter and Western Conferences.
 * </p>
 */
public final class CsvPlayoffTeamsGetter implements PlayoffTeamsGetter
{
  private static final int MIN_NUM_LINES = 2;
  private static final int UPPER_HALF_TEAMS_LINE_INDEX = 0;
  private static final int LOWER_HALF_TEAMS_LINE_INDEX = 1;
  private static final int MIN_NUM_TEAMS_IN_EACH_HALF = 8;
  private final List<Team> upperHalfTeams;
  private final List<Team> lowerHalfTeams;

  /**
   * Constructs a new instance of CsvPlayoffTeamsGetter.
   * 
   * @param reader the reader, which can't be null
   * 
   * @throws IOException if an error occurs reading from {@code reader}
   * @throws ParseException if an error occurs parsing the contents of {@code reader}
   */
  public CsvPlayoffTeamsGetter(final Reader reader) throws IOException, ParseException
  {
    ArgAssert.notNull(reader, "reader");

    final String[][] lines = getLines(reader);
    validate(lines);
    upperHalfTeams = getUpperHalfTeams(lines);
    lowerHalfTeams = getLowerHalfTeams(lines);
  }

  private String[][] getLines(final Reader reader) throws IOException
  {
    return new CSVParser(reader).getAllValues();
  }

  private void validate(final String[][] lines) throws ParseException
  {
    if (lines == null)
    {
      throw ParseExceptions.newInstance("No lines in playoff teams reader");
    }
    else
    {
      if (lines.length < MIN_NUM_LINES)
      {
        throw ParseExceptions.newInstance("Too few lines in playoff teams reader");
      }
    }
  }

  private List<Team> getUpperHalfTeams(final String[][] lines) throws ParseException
  {
    final String[] line = lines[UPPER_HALF_TEAMS_LINE_INDEX];
    final List<Team> teams = getTeamsOnLine(line);
    if (teams.size() < MIN_NUM_TEAMS_IN_EACH_HALF)
    {
      throw ParseExceptions.newInstance("Not enough upper-half teams");
    }
    return teams;
  }

  private List<Team> getLowerHalfTeams(final String[][] lines) throws ParseException
  {
    final String[] line = lines[LOWER_HALF_TEAMS_LINE_INDEX];
    final List<Team> teams = getTeamsOnLine(line);
    if (teams.size() < MIN_NUM_TEAMS_IN_EACH_HALF)
    {
      throw ParseExceptions.newInstance("Not enough lower-half teams");
    }
    return teams;
  }

  private List<Team> getTeamsOnLine(final String[] line) throws ParseException
  {
    final List<Team> teams = Lists.newArrayList();
    for (final String teamShortform : line)
    {
      if (Team.isTeamThatHasShortform(teamShortform))
      {
        teams.add(Team.getTeamThatHasShortform(teamShortform));
      }
      else
      {
        throw ParseExceptions.newInstance("Invalid team shortform:" + teamShortform);
      }
    }
    return teams;
  }

  /** {@inheritDoc} */
  public List<Team> getUpperHalfTeams()
  {
    return Lists.newArrayList(upperHalfTeams);
  }

  /** {@inheritDoc} */
  public List<Team> getLowerHalfTeams()
  {
    return Lists.newArrayList(lowerHalfTeams);
  }
}
