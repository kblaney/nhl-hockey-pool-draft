package com.kblaney.nhl.draft;

import org.junit.Test;
import static org.junit.Assert.*;

public final class DraftPositionInFirstRoundValidatorTest
{
  @Test(expected = IllegalArgumentException.class)
  public void constructor_invalidNumPoolees()
  {
    final int arbitraryInvalidNumPoolees = 0;
    new DraftPositionInFirstRoundValidator(arbitraryInvalidNumPoolees);
  }

  @Test
  public void isValid_threePoolees()
  {
    final int numPoolees = 3;
    final IntOrStringValidator validator =
          new DraftPositionInFirstRoundValidator(numPoolees);
    assertFalse(validator.isValid("0"));
    assertFalse(validator.isValid("4"));
    assertFalse(validator.isValid("bubba"));
    assertFalse(validator.isValid(0));
    assertFalse(validator.isValid(-1));
    assertFalse(validator.isValid(4));
    assertFalse(validator.isValid(Integer.MAX_VALUE));
    assertTrue(validator.isValid("1"));
    assertTrue(validator.isValid("2"));
    assertTrue(validator.isValid("3"));
    assertTrue(validator.isValid(1));
    assertTrue(validator.isValid(2));
    assertTrue(validator.isValid(3));
  }
}
