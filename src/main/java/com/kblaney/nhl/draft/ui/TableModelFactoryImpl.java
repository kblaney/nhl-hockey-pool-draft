package com.kblaney.nhl.draft.ui;

import com.google.common.collect.Lists;
import com.kblaney.commons.lang.ArgAssert;
import com.kblaney.nhl.PlayoffTeamsGetter;
import com.kblaney.nhl.Team;
import com.kblaney.nhl.draft.Draft;
import javax.swing.table.TableModel;

/**
 * Implements methods that get table models.
 */
final class TableModelFactoryImpl implements TableModelFactory
{
  private final PlayoffTeamsGetter playoffTeamsGetter;

  /**
   * Constructs a new instance of TableModelFactoryImpl.
   *
   * @param playoffTeamsGetter the playoff teams getter, which can't be null
   */
  public TableModelFactoryImpl(final PlayoffTeamsGetter playoffTeamsGetter)
  {
    ArgAssert.notNull(playoffTeamsGetter, "playoffTeamsGetter");

    this.playoffTeamsGetter = playoffTeamsGetter;
  }

  /** {@inheritDoc} */
  public TableModel getTableModel(final Draft draft)
  {
    ArgAssert.notNull(draft, "draft");

    switch (draft.getSeasonType())
    {
      case REGULAR_SEASON:
      {
        return new RegularSeasonChartTableModel(
              draft.getFirstRoundDraftOrder(),
              Lists.newArrayList(Team.values()), draft);
      }
      case PLAYOFF:
      {
        final int maxNumPicksPerTeam = 20;

        return new PlayoffChartTableModel(
              playoffTeamsGetter.getUpperHalfTeams(),
              playoffTeamsGetter.getLowerHalfTeams(), draft,
              maxNumPicksPerTeam);
      }
      default:
      {
        throw new IllegalArgumentException("Unknown season type:  " +
              draft.getSeasonType());
      }
    }
  }
}
