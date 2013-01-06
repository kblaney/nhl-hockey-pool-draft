package com.kblaney.nhl;

import com.kblaney.commons.lang.ArgAssert;

/**
 * Enumerates a player's position.
 */
public enum Position
{
  FORWARD("Forward", "F"), DEFENSEMAN("Defenseman", "D"), GOALIE("Goalie", "G");

  private final String longform;
  private final String shortform;

  private Position(final String longform, final String shortform)
  {
    this.longform = longform;
    this.shortform = shortform;
  }

  public String getShortform()
  {
    return shortform;
  }

  /**
   * Determines if there is a position that has a specified shortform. Note that {@code shortform} is not
   * case-significant.
   * 
   * @param shortform the shortform
   * 
   * @return {@code true} if there is a position that has the specified shortform; {@code false} otherwise
   * 
   * @see #getPositionThatHasShortform(String)
   */
  public static boolean isPositionThatHasShortform(final String shortform)
  {
    for (final Position position : Position.values())
    {
      if (position.getShortform().equalsIgnoreCase(shortform))
      {
        return true;
      }
    }
    return false;
  }

  /**
   * Gets the position that has a specified shortform. Note that {@code shortform} is not case-significant.
   * 
   * @param shortform the shortform, which can't be null
   * 
   * @return the position with the specified shortform
   * 
   * @see #isPositionThatHasShortform(String)
   */
  public static Position getPositionThatHasShortform(final String shortform)
  {
    ArgAssert.notNull(shortform, "shortform");

    for (final Position position : Position.values())
    {
      if (position.getShortform().equalsIgnoreCase(shortform))
      {
        return position;
      }
    }

    throw new IllegalArgumentException("Invalid position shortform:" + shortform);
  }

  /** {@inheritDoc} */
  @Override
  public String toString()
  {
    return longform;
  }
}
