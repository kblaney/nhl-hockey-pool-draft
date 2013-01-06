package com.kblaney.nhl.draft;

/**
 * Validates the number of rounds.
 */
public final class NumRoundsValidator implements IntOrStringValidator
{
  /** {@inheritDoc} */
  public boolean isValid(final String numRoundsString)
  {
    try
    {
      final int numRounds = Integer.parseInt(numRoundsString);
      return isValid(numRounds);
    }
    catch (final NumberFormatException e)
    {
      return false;
    }
  }

  /** {@inheritDoc} */
  public boolean isValid(final int numRounds)
  {
    final int minNumRounds = 1;
    return (numRounds >= minNumRounds);
  }
}
