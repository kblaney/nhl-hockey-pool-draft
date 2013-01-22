package com.kblaney.nhl.draft;

import com.kblaney.assertions.ArgAssert;

/**
 * Implements methods related to the validation of a draft position in the first round of a draft.
 */
public final class DraftPositionInFirstRoundValidator implements IntOrStringValidator
{
  private final int numPoolees;

  /**
   * Constructs a new instance of DraftPositionInFirstRoundValidator for a draft that has a specified number of poolees.
   * 
   * @param numPoolees the number of poolees in the draft, which must be positive
   */
  public DraftPositionInFirstRoundValidator(final int numPoolees)
  {
    this.numPoolees = ArgAssert.assertGreaterThanOrEqual(numPoolees, 1, "numPoolees");
  }

  /** {@inheritDoc} */
  public boolean isValid(final String stringToValidate)
  {
    try
    {
      final int draftPositionInFirstRound = Integer.parseInt(stringToValidate);
      return isValid(draftPositionInFirstRound);
    }
    catch (final NumberFormatException e)
    {
      return false;
    }
  }

  /** {@inheritDoc} */
  public boolean isValid(final int intToValidate)
  {
    final int minDraftPosition = 1;
    final int maxDraftPosition = numPoolees;
    return (minDraftPosition <= intToValidate) && (intToValidate <= maxDraftPosition);
  }
}
