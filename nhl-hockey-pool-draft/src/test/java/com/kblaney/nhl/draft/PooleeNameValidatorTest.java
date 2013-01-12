package com.kblaney.nhl.draft;

import static org.junit.Assert.*;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

public final class PooleeNameValidatorTest
{
  private PooleeNameValidator validator;

  @Before
  public void setUp()
  {
    validator = new PooleeNameValidator();
  }

  @Test
  public void isValid_nullName()
  {
    assertFalse(validator.isValid(null));
  }

  @Test
  public void isValid_emptyName()
  {
    assertFalse(validator.isValid(StringUtils.EMPTY));
  }

  @Test
  public void isValid_nameWithOneCharacter()
  {
    final String arbitraryNameWithOneCharacter = "A";
    assertFalse(validator.isValid(arbitraryNameWithOneCharacter));
  }

  @Test
  public void isValid_nameWithTwoCharacter()
  {
    final String arbitraryNameWithTwoCharacters = "Bo";
    assertTrue(validator.isValid(arbitraryNameWithTwoCharacters));
  }

  @Test
  public void isValid_longName()
  {
    final String arbitraryLongName = "Williamhelmina";
    assertTrue(validator.isValid(arbitraryLongName));
  }
}
