package com.kblaney.nhl.draft;

import java.text.ParseException;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class FirstCsvLineReaderWriterTest
{
  private String[] arbitraryFieldsWithInvalidNumFields;
  private FirstCsvLineReaderWriter readerWriter;

  @Before
  public void setUp()
  {
    arbitraryFieldsWithInvalidNumFields = new String[0];
    readerWriter = new FirstCsvLineReaderWriter();
  }

  @Test
  public void getFields()
  {
    final SeasonType arbitrarySeasonType = SeasonType.PLAYOFF;
    final int arbitraryNumRounds = 3;
    final int arbitraryNumPoolees = 4;
    final String[] expectedFields = new String[]
    {
      arbitrarySeasonType.toString(),
      Integer.toString(arbitraryNumRounds),
      Integer.toString(arbitraryNumPoolees),
    };
    assertTrue(Arrays.equals(expectedFields, readerWriter.getFields(
          arbitrarySeasonType, arbitraryNumRounds, arbitraryNumPoolees)));
  }

  @Test(expected = ParseException.class)
  public void getSeasonType_invalidNumFields() throws Exception
  {
    readerWriter.getSeasonType(arbitraryFieldsWithInvalidNumFields);
  }

  @Test(expected = ParseException.class)
  public void getSeasonType_invalidSeasonTypeString() throws Exception
  {
    final String arbitraryInvalidSeasonTypeString = "BUBBA";
    readerWriter.getSeasonType(getFieldsWithSeasonTypeString(
          arbitraryInvalidSeasonTypeString));
  }

  private String[] getFieldsWithSeasonTypeString(final String seasonTypeString)
  {
    final int arbitraryNumRounds = 3;
    final int arbitraryNumPoolees = 4;
    return new String[]
    {
      seasonTypeString,
      Integer.toString(arbitraryNumRounds),
      Integer.toString(arbitraryNumPoolees),
    };
  }

  @Test
  public void getSeasonType_playoff() throws Exception
  {
    assertEquals(SeasonType.PLAYOFF, readerWriter.getSeasonType(
          getFieldsWithSeasonTypeString("PLAYOFF")));
  }

  @Test
  public void getSeasonType_regularSeason() throws Exception
  {
    assertEquals(SeasonType.REGULAR_SEASON, readerWriter.getSeasonType(
          getFieldsWithSeasonTypeString("REGULAR_SEASON")));
  }

  @Test(expected = ParseException.class)
  public void getNumRounds_invalidNumFields() throws Exception
  {
    readerWriter.getNumRounds(arbitraryFieldsWithInvalidNumFields);
  }

  @Test(expected = ParseException.class)
  public void getNumRounds_invalidNumRounds() throws Exception
  {
    final int arbitraryInvalidNumRounds = 0;
    readerWriter.getNumRounds(getFieldsWithNumRounds(
          arbitraryInvalidNumRounds));
  }

  private String[] getFieldsWithNumRounds(final int numRounds)
  {
    final SeasonType arbitrarySeasonType = SeasonType.REGULAR_SEASON;
    final int arbitraryNumPoolees = 6;
    return new String[]
    {
      arbitrarySeasonType.toString(),
      Integer.toString(numRounds),
      Integer.toString(arbitraryNumPoolees),
    };
  }

  @Test
  public void getNumRounds_validNumRounds() throws Exception
  {
    final int arbitraryNumRounds = 12;
    assertEquals(arbitraryNumRounds, readerWriter.getNumRounds(
          getFieldsWithNumRounds(arbitraryNumRounds)));
  }

  @Test(expected = ParseException.class)
  public void getNumPoolees_invalidNumFields() throws Exception
  {
    readerWriter.getNumPoolees(arbitraryFieldsWithInvalidNumFields);
  }

  @Test(expected = ParseException.class)
  public void getNumPoolees_invalidNumPoolees() throws Exception
  {
    final int arbitraryInvalidNumPoolees = -2;
    readerWriter.getNumPoolees(getFieldsWithNumPoolees(
          arbitraryInvalidNumPoolees));
  }

  private String[] getFieldsWithNumPoolees(final int numPoolees)
  {
    final SeasonType arbitrarySeasonType = SeasonType.PLAYOFF;
    final int arbitraryNumRounds = 14;
    return new String[]
    {
      arbitrarySeasonType.toString(),
      Integer.toString(arbitraryNumRounds),
      Integer.toString(numPoolees),
    };
  }

  @Test
  public void getNumPoolees_validNumRounds() throws Exception
  {
    final int arbitraryNumPoolees = 12;
    assertEquals(arbitraryNumPoolees, readerWriter.getNumPoolees(
          getFieldsWithNumPoolees(arbitraryNumPoolees)));
  }
}
