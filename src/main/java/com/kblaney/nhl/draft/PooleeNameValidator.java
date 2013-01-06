package com.kblaney.nhl.draft;

/**
 * Validates poolee names.
 */
public final class PooleeNameValidator
{
  /**
   * Determines if a specified name is valid as a poolee's first or last name.
   *
   * @param name the name
   *
   * @return {@code true} if the name is valid; {@code false} if the name is
   * invalid
   */
  public boolean isValid(final String name)
  {
    final int minNameLength = 2;
    return ((name != null) && (name.length() >= minNameLength));
  }
}
