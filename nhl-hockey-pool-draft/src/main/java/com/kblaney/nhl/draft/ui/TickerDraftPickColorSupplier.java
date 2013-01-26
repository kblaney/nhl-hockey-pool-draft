package com.kblaney.nhl.draft.ui;

import com.kblaney.nhl.draft.Draft;
import com.kblaney.nhl.draft.DraftPick;
import com.kblaney.nhl.draft.SeasonType;
import java.awt.Color;

final class TickerDraftPickColorSupplier
{
  public Color getColor(final DraftPick draftPick, final Draft draft)
  {
    if (draft.getSeasonType() == SeasonType.REGULAR_SEASON)
    {
      return new RegularSeasonColors().getColor(draftPick.getPlayer().getPosition()); 
    }
    else if (draft.getSeasonType() == SeasonType.PLAYOFF)
    {
      return new PlayoffColors().getColor(draftPick.getPoolee(), draft);
    }
    throw new IllegalArgumentException("Invalid season type: " + draft.getSeasonType());
  }
}
