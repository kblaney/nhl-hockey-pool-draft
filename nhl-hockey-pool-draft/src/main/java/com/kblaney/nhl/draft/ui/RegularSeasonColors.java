package com.kblaney.nhl.draft.ui;

import com.kblaney.assertions.ArgAssert;
import com.kblaney.nhl.Position;
import java.awt.Color;

/**
 * Regular season colors.
 */
final class RegularSeasonColors
{
  private static final Color FOREST_GREEN = new Color(0, 100, 100);
  private static final Color ROSE = new Color(165, 50, 150);

  /** {@inheritDoc} */
  public Color getColor(final Position position)
  {
    ArgAssert.assertNotNull(position, "position");

    switch (position)
    {
      case DEFENSEMAN:
      {
        return ROSE;
      }
      case FORWARD:
      {
        return FOREST_GREEN;
      }
      case GOALIE:
      {
        return Color.BLUE;
      }
      default:
      {
        throw new IllegalArgumentException("Invalid position:" + position);
      }
    }
  }
}
