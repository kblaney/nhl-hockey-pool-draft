package com.kblaney.nhl.draft;

import com.kblaney.nhl.ParseExceptions;
import java.text.ParseException;
import java.util.Arrays;

/**
 * Reads and writes a poolee line in comma-separated values (CSV) format.
 */
final class PooleeCsvLineReaderWriter
{
  private static final int POOLEE_FIRST_NAME_INDEX = 0;
  private static final int POOLEE_LAST_NAME_INDEX = 1;
  private static final int NUM_FIELDS = 2;

  public String[] getFields(final Poolee poolee)
  {
    final String[] fields = new String[NUM_FIELDS];
    fields[POOLEE_FIRST_NAME_INDEX] = poolee.getFirstName();
    fields[POOLEE_LAST_NAME_INDEX] = poolee.getLastName();
    return fields;
  }

  public Poolee getPoolee(final String[] fields) throws ParseException
  {
    if (fields.length == NUM_FIELDS)
    {
      final String firstName = fields[POOLEE_FIRST_NAME_INDEX];
      final String lastName = fields[POOLEE_LAST_NAME_INDEX];
      return new Poolee(firstName, lastName);
    }
    else
    {
      throw ParseExceptions.newInstance("Invalid number of items on poolee line:" + Arrays.toString(fields));
    }
  }
}
