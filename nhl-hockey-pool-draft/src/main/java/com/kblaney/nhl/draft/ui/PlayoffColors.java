package com.kblaney.nhl.draft.ui;

import com.google.common.collect.Lists;
import com.kblaney.nhl.draft.Draft;
import com.kblaney.nhl.draft.Poolee;
import java.awt.Color;
import java.util.List;

final class PlayoffColors
{
  private static final Color REAL_ORANGE = new Color(255, 100, 0);
  private static final Color FOREST_GREEN = new Color(0, 100, 100);
  private static final Color PURPLE = new Color(100, 0, 100);
  private static final Color LIME_GREEN = new Color(100, 125, 0);
  private static final List<Color> COLORS = Lists.newArrayList(Color.BLUE, Color.RED, Color.DARK_GRAY, Color.MAGENTA,
        REAL_ORANGE, FOREST_GREEN, PURPLE, LIME_GREEN, Color.PINK);

  public Color getColor(final Poolee poolee, final Draft draft)
  {
    return COLORS.get(draft.getFirstRoundDraftOrder().indexOf(poolee));
  }
}
