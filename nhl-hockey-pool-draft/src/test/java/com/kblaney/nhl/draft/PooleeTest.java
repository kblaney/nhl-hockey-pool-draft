package com.kblaney.nhl.draft;

import static org.junit.Assert.*;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

public final class PooleeTest
{
  private String firstName;
  private String lastName;
  private Poolee poolee;

  @Before
  public void setUp()
  {
    firstName = "FIRST_NAME";
    lastName = "LAST_NAME";
    poolee = new Poolee(firstName, lastName);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorNullFirstName()
  {
    new Poolee(null, lastName);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorEmptyFirstName()
  {
    new Poolee(StringUtils.EMPTY, lastName);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorNullLastName()
  {
    new Poolee(firstName, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorEmptyLastName()
  {
    new Poolee(firstName, StringUtils.EMPTY);
  }

  @Test
  public void getFirstName()
  {
    assertEquals(firstName, poolee.getFirstName());
  }

  @Test
  public void getLastName()
  {
    assertEquals(lastName, poolee.getLastName());
  }

  @Test
  public void getFullName()
  {
    assertEquals("Kyle Blaney", new Poolee("Kyle", "Blaney").getFullName());
  }

  @Test
  public void equals_differentFirstName()
  {
    final Poolee differentPoolee = new Poolee("DIFFERENT_FIRST_NAME", lastName);

    assertFalse(poolee.equals(differentPoolee));
    assertFalse(differentPoolee.equals(poolee));
  }

  @Test
  public void equals_differentLastName()
  {
    final Poolee differentPoolee = new Poolee(firstName, "DIFFERENT_LAST_NAME");

    assertFalse(poolee.equals(differentPoolee));
    assertFalse(differentPoolee.equals(poolee));
  }

  @Test
  public void testSamePoolee()
  {
    final Poolee samePoolee = new Poolee(firstName, lastName);

    assertTrue(poolee.equals(samePoolee));
    assertTrue(samePoolee.equals(poolee));
    assertTrue("Equal poolees should have equal hash codes", poolee.hashCode() == samePoolee.hashCode());
  }

  @Test
  public void hashCode_repeatedCalls()
  {
    final int hashCode = poolee.hashCode();

    assertEquals(hashCode, poolee.hashCode());
    assertEquals(hashCode, poolee.hashCode());
    assertEquals(hashCode, poolee.hashCode());
    assertEquals(hashCode, poolee.hashCode());
  }
}
