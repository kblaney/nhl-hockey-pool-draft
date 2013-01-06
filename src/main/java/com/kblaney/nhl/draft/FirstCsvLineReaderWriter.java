package com.kblaney.nhl.draft;

import com.kblaney.nhl.ParseExceptions;
import java.text.ParseException;
import java.util.Arrays;

/**
 * Reads and writes the first line in comma-separated values (CSV) format.
 */
final class FirstCsvLineReaderWriter
{
  private static final int SEASON_TYPE_INDEX = 0;
  private static final int NUM_ROUNDS_INDEX = 1;
  private static final int NUM_POOLEES_INDEX = 2;
  private static final int NUM_FIELDS = 3;

  public String[] getFields(final SeasonType seasonType, final int numRounds,
        final int numPoolees)
  {
    final String[] fields = new String[NUM_FIELDS];
    fields[SEASON_TYPE_INDEX] = seasonType.toString();
    fields[NUM_ROUNDS_INDEX] = Integer.toString(numRounds);
    fields[NUM_POOLEES_INDEX] = Integer.toString(numPoolees);
    return fields;
  }

  public SeasonType getSeasonType(final String[] fields) throws ParseException
  {
    validateNumFields(fields);

    final String seasonTypeString = fields[SEASON_TYPE_INDEX];
    try
    {
      return SeasonType.valueOf(seasonTypeString);
    }
    catch (final IllegalArgumentException e)
    {
      throw ParseExceptions.newInstance("Invalid season type:" +
            seasonTypeString);
    }
  }

  private void validateNumFields(final String[] fields) throws ParseException
  {
    if (fields.length != NUM_FIELDS)
    {
      throw ParseExceptions.newInstance("Invalid num fields on first line:" +
            Arrays.toString(fields));
    }
  }

  public int getNumRounds(final String[] fields) throws ParseException
  {
    validateNumFields(fields);

    final String numRoundsString = fields[NUM_ROUNDS_INDEX];
    final IntOrStringValidator numRoundsValidator = new NumRoundsValidator();
    if (!numRoundsValidator.isValid(numRoundsString))
    {
      throw ParseExceptions.newInstance("Invalid numRounds:" + numRoundsString);
    }
    return Integer.parseInt(numRoundsString);
  }

  public int getNumPoolees(final String[] fields) throws ParseException
  {
    validateNumFields(fields);

    final String numPooleesString = fields[NUM_POOLEES_INDEX];
    final IntOrStringValidator numPooleesValidator = new NumPooleesValidator();
    if (!numPooleesValidator.isValid(numPooleesString))
    {
      throw ParseExceptions.newInstance("Invalid numPoolees:" +
            numPooleesString);
    }
    return Integer.parseInt(numPooleesString);
  }
}
