package com.kblaney.nhl.draft;

import static org.junit.Assert.*;
import org.junit.Test;

public final class NumRoundsValidatorTest
{
  @Test
  public void isValid()
  {
    final IntOrStringValidator validator = new NumRoundsValidator();
    assertFalse(validator.isValid("0"));
    assertFalse(validator.isValid("-1"));
    assertFalse(validator.isValid("kyle"));
    assertFalse(validator.isValid(0));
    assertFalse(validator.isValid(-1));
    assertTrue(validator.isValid(1));
    assertTrue(validator.isValid("2"));
    assertTrue(validator.isValid("3"));
    assertTrue(validator.isValid(4));
    assertTrue(validator.isValid(5));
  }
}
