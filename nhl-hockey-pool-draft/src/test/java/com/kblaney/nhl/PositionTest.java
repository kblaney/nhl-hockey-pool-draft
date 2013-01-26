package com.kblaney.nhl;

import static org.junit.Assert.*;
import org.junit.Test;

public final class PositionTest
{
  @Test
  public void getShortform()
  {
    assertEquals("F", Position.FORWARD.getShortform());
    assertEquals("D", Position.DEFENSEMAN.getShortform());
    assertEquals("G", Position.GOALIE.getShortform());
  }

  @Test
  public void isPositionWithShortform_validShortforms()
  {
    assertIsPositionWithShortform("F", "f", "D", "d", "G", "g");
  }

  private void assertIsPositionWithShortform(final String... shortforms)
  {
    for (final String s : shortforms)
    {
      assertTrue(Position.isPositionThatHasShortform(s));
    }
  }

  @Test
  public void isPositionWithShortform_invalidShortforms()
  {
    assertIsNotPositionWithShortform("FD", "da", "Z");
  }

  private void assertIsNotPositionWithShortform(final String... shortforms)
  {
    for (final String s : shortforms)
    {
      assertFalse(Position.isPositionThatHasShortform(s));
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void getPositionWithShortform_nullShortform()
  {
    Position.getPositionThatHasShortform(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getPositionWithShortform_invalidShortform()
  {
    final String arbitraryInvalidShortform = "A";
    Position.getPositionThatHasShortform(arbitraryInvalidShortform);
  }

  @Test
  public void getPositionThatHasShortform()
  {
    assertPositionHasShortforms(Position.FORWARD, "F", "f");
    assertPositionHasShortforms(Position.DEFENSEMAN, "D", "d");
    assertPositionHasShortforms(Position.GOALIE, "G", "g");
  }

  private void assertPositionHasShortforms(final Position position, final String... shortforms)
  {
    for (final String shortform : shortforms)
    {
      assertEquals(position, Position.getPositionThatHasShortform(shortform));
    }
  }

  @Test
  public void testToString()
  {
    assertEquals("Forward", Position.FORWARD.toString());
    assertEquals("Defenseman", Position.DEFENSEMAN.toString());
    assertEquals("Goalie", Position.GOALIE.toString());
  }
}
