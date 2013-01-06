package com.kblaney.nhl.draft;

/**
 * Validates the number of poolees.
 */
public final class NumPooleesValidator implements IntOrStringValidator
{
  /** {@inheritDoc} */
  public boolean isValid(final String numPooleesString)
  {
    try
    {
      final int numPoolees = Integer.parseInt(numPooleesString);
      return isValid(numPoolees);
    }
    catch (final NumberFormatException e)
    {
      return false;
    }
  }

  /** {@inheritDoc} */
  public boolean isValid(final int numPoolees)
  {
    final int minNumPoolees = 2;
    return (numPoolees >= minNumPoolees);
  }
}
