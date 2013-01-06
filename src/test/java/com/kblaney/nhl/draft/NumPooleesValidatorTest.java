package com.kblaney.nhl.draft;

import org.junit.Test;
import static org.junit.Assert.*;

public final class NumPooleesValidatorTest
{
  @Test
  public void isValid()
  {
    final IntOrStringValidator validator = new NumPooleesValidator();
    assertFalse(validator.isValid("0"));
    assertFalse(validator.isValid("1"));
    assertFalse(validator.isValid("bubba"));
    assertFalse(validator.isValid(0));
    assertFalse(validator.isValid(-1));
    assertFalse(validator.isValid(1));
    assertTrue(validator.isValid("2"));
    assertTrue(validator.isValid("3"));
    assertTrue(validator.isValid(4));
    assertTrue(validator.isValid(5));
  }
}
