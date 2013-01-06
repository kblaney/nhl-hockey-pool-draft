package com.kblaney.nhl;

import java.text.ParseException;

/**
 * Static utility methods related to {@code ParseException}s.
 */
public final class ParseExceptions
{
  private ParseExceptions()
  {
  }

  /**
   * Gets a new instance of ParseException with a specified message.
   *
   * @param msg the message
   *
   * @return the exception
   */
  public static ParseException newInstance(final String msg)
  {
    final int arbitraryOffset = 0;
    return new ParseException(msg, arbitraryOffset);
  }
}
