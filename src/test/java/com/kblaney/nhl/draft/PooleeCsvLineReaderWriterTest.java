package com.kblaney.nhl.draft;

import java.text.ParseException;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class PooleeCsvLineReaderWriterTest
{
  private String arbitraryFirstName;
  private String arbitraryLastName;
  private String[] fieldsWithArbitraryValues;
  private PooleeCsvLineReaderWriter readerWriter;

  @Before
  public void setUp()
  {
    arbitraryFirstName = "Kyle";
    arbitraryLastName = "Blaney";
    fieldsWithArbitraryValues = new String[]
    {
      arbitraryFirstName,
      arbitraryLastName,
    };
    readerWriter = new PooleeCsvLineReaderWriter();
  }

  @Test
  public void getFields()
  {
    assertTrue(Arrays.equals(fieldsWithArbitraryValues, readerWriter.getFields(
          new Poolee(arbitraryFirstName, arbitraryLastName))));
  }

  @Test(expected = ParseException.class)
  public void getPoolee_invalidNumFields() throws Exception
  {
    final String[] arbitraryFieldsWithInvalidNumFields = new String[0];
    readerWriter.getPoolee(arbitraryFieldsWithInvalidNumFields);
  }

  @Test
  public void getPoolee() throws Exception
  {
    assertEquals(new Poolee(arbitraryFirstName, arbitraryLastName),
          readerWriter.getPoolee(fieldsWithArbitraryValues));
  }
}
