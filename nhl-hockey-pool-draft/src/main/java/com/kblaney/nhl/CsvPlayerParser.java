package com.kblaney.nhl;

import com.Ostermiller.util.CSVParse;
import com.Ostermiller.util.CSVParser;
import com.kblaney.assertions.ArgAssert;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;

/**
 * Parses comma-separated values (CSV) from a Reader to get players.
 */
public final class CsvPlayerParser implements PlayerParser
{
  private static final int MIN_NUM_FIELDS = 4;
  private static final int MAX_NUM_FIELDS = 5;
  private static final int FIRST_NAME_INDEX = 0;
  private static final int LAST_NAME_INDEX = 1;
  private static final int TEAM_SHORTFORM_INDEX = 2;
  private static final int POSITION_SHORTFORM_INDEX = 3;
  private static final int ID_INDEX = 4;

  private final CSVParse csvParser;

  /**
   * Constructs a new instance of CsvPlayerParser that gets its input from a specified reader. Each line of the reader
   * must have the following fields:
   * <nl>
   * <li>the player's first name</li>
   * <li>the player's last name</li>
   * <li>the shortform of the player's team</li>
   * <li>the shortform of the player's position</li>
   * </nl>
   * 
   * @param reader the reader, which can't be null
   */
  public CsvPlayerParser(final Reader reader)
  {
    ArgAssert.assertNotNull(reader, "reader");

    csvParser = new CSVParser(reader);
  }

  /** {@inheritDoc} */
  public PlayersByTeamAndPosition getPlayersByTeamAndPosition() throws IOException, ParseException
  {
    final String[][] allLines = csvParser.getAllValues();

    final PlayersByTeamAndPosition playersByTeamAndPosition = new PlayersByTeamAndPositionImpl();
    for (final String[] line : allLines)
    {
      validate(line);
      final Player player = getPlayer(line);
      playersByTeamAndPosition.addPlayer(player);
    }
    return playersByTeamAndPosition;
  }

  private void validate(final String[] line) throws ParseException
  {
    if ((line.length < MIN_NUM_FIELDS) || (line.length > MAX_NUM_FIELDS))
    {
      throw ParseExceptions.newInstance("Invalid # fields:" + Arrays.toString(line));
    }
  }

  private Player getPlayer(final String[] line) throws ParseException
  {
    final String firstName = getFirstName(line);
    final String lastName = getLastName(line);
    final Team team = getTeam(line);
    final Position position = getPosition(line);
    final String id = getId(line);
    return new Player(firstName, lastName, team, position, id);
  }

  private String getFirstName(final String[] line) throws ParseException
  {
    final String firstName = line[FIRST_NAME_INDEX];
    if (StringUtils.isEmpty(firstName))
    {
      throw ParseExceptions.newInstance("Empty first name" + Arrays.toString(line));
    }
    return firstName;
  }

  private String getLastName(final String[] line) throws ParseException
  {
    final String lastName = line[LAST_NAME_INDEX];
    if (StringUtils.isEmpty(lastName))
    {
      throw ParseExceptions.newInstance("Empty last name" + Arrays.toString(line));
    }
    return lastName;
  }

  private Team getTeam(final String[] line) throws ParseException
  {
    final String teamShortform = line[TEAM_SHORTFORM_INDEX];
    try
    {
      return Team.getTeamThatHasShortform(teamShortform);
    }
    catch (final IllegalArgumentException e)
    {
      throw ParseExceptions.newInstance("Invalid team shortform:" + teamShortform);
    }
  }

  private Position getPosition(final String[] line) throws ParseException
  {
    final String positionShortform = line[POSITION_SHORTFORM_INDEX];
    if (Position.isPositionThatHasShortform(positionShortform))
    {
      return Position.getPositionThatHasShortform(positionShortform);
    }
    else
    {
      throw ParseExceptions.newInstance("Invalid position shortform:" + positionShortform);
    }
  }

  private String getId(final String[] line) throws ParseException
  {
    if (line.length > ID_INDEX)
    {
      return line[ID_INDEX];
    }
    else
    {
      return StringUtils.EMPTY;
    }
  }

  /** {@inheritDoc} */
  public void closeQuietly()
  {
    try
    {
      csvParser.close();
    }
    catch (final IOException e)
    {
      // We intentionally ignore this exception. (That's what is meant by
      // the "quietly" in the method name.)
    }
  }
}
