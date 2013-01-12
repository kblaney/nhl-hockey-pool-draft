package com.kblaney.nhl.draft.ui;

import com.google.common.collect.Lists;
import com.kblaney.commons.lang.ArgAssert;
import com.kblaney.nhl.Position;
import com.kblaney.nhl.Team;
import com.kblaney.nhl.draft.Draft;
import com.kblaney.nhl.draft.DraftPick;
import com.kblaney.nhl.draft.Poolee;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * A model for a table that displays the chart for a regular season pool.
 */
@SuppressWarnings("serial")
final class RegularSeasonChartTableModel extends AbstractTableModel
{
  private static final int NUM_HEADER_ROWS = 1;
  private static final int POOLEE_ROW_INDEX = 0;
  private static final int NUM_HEADER_COLUMNS = 2;
  private static final int TEAM_COLUMN_INDEX = 0;
  static final int NUM_PICKS_COLUMN_INDEX = 1;
  private static final int NUM_FOOTER_ROWS = 3;
  private final List<Poolee> firstRoundDraftOrder;
  private final List<Team> teams;
  private final Draft draft;
  private final int numForwardsRowIndex;
  private final int numDefensemenRowIndex;
  private final int numGoaliesRowIndex;

  public RegularSeasonChartTableModel(final List<Poolee> firstRoundDraftOrder,
        final List<Team> teams, final Draft draft)
  {
    ArgAssert.notNull(firstRoundDraftOrder, "firstRoundDraftOrder");
    ArgAssert.notNull(teams, "teams");
    ArgAssert.notNull(draft, "draft");

    this.firstRoundDraftOrder = Lists.newArrayList(firstRoundDraftOrder);
    this.teams = Lists.newArrayList(teams);
    this.draft = draft;

    numForwardsRowIndex = NUM_HEADER_ROWS + teams.size();
    numDefensemenRowIndex = numForwardsRowIndex + 1;
    numGoaliesRowIndex = numDefensemenRowIndex + 1;
  }

  /** {@inheritDoc} */
  public int getRowCount()
  {
    return NUM_HEADER_ROWS + teams.size() + NUM_FOOTER_ROWS;
  }

  /** {@inheritDoc} */
  public int getColumnCount()
  {
    return firstRoundDraftOrder.size() + NUM_HEADER_COLUMNS;
  }

  /** {@inheritDoc} */
  public Object getValueAt(final int rowIndex, final int columnIndex)
  {
    if (columnIndex == TEAM_COLUMN_INDEX)
    {
      if (rowIndex == numForwardsRowIndex)
      {
        return "F";
      }
      else if (rowIndex == numDefensemenRowIndex)
      {
        return "D";
      }
      else if (rowIndex == numGoaliesRowIndex)
      {
        return "G";
      }
      else if (rowIndex == POOLEE_ROW_INDEX)
      {
        return null;
      }
      else
      {
        return getTeam(rowIndex);
      }
    }
    else if (columnIndex == NUM_PICKS_COLUMN_INDEX)
    {
      if (rowIndex == POOLEE_ROW_INDEX)
      {
        return "#";
      }
      else if (rowIndex == numForwardsRowIndex)
      {
        return getNumForwardsDrafted();
      }
      else if (rowIndex == numDefensemenRowIndex)
      {
        return getNumDefensemenDrafted();
      }
      else if (rowIndex == numGoaliesRowIndex)
      {
        return getNumGoaliesDrafted();
      }
      else
      {
        final Team team = getTeam(rowIndex);
        return draft.getDraftPicksOnTeam(team).size();
      }
    }
    else
    {
      final Poolee poolee = getPoolee(columnIndex);

      if (rowIndex == numForwardsRowIndex)
      {
        return draft.getDraftPicksOfPooleeAtPosition(
              poolee, Position.FORWARD).size();
      }
      else if (rowIndex == numDefensemenRowIndex)
      {
        return draft.getDraftPicksOfPooleeAtPosition(
              poolee, Position.DEFENSEMAN).size();
      }
      else if (rowIndex == numGoaliesRowIndex)
      {
        return draft.getDraftPicksOfPooleeAtPosition(
              poolee, Position.GOALIE).size();
      }
      else if (rowIndex == POOLEE_ROW_INDEX)
      {
        return poolee;
      }
      else
      {
        final Team team = getTeam(rowIndex);
        final List<DraftPick> draftPicks =
              draft.getDraftPicksOfPooleeFromTeam(poolee, team);

        if (draftPicks.isEmpty())
        {
          return null;
        }
        else
        {
          return draftPicks.get(0);
        }
      }
    }
  }

  private Team getTeam(final int rowIndex)
  {
    return teams.get(rowIndex - NUM_HEADER_ROWS);
  }

  private Poolee getPoolee(final int columnIndex)
  {
    return firstRoundDraftOrder.get(columnIndex - NUM_HEADER_COLUMNS);
  }

  private int getNumForwardsDrafted()
  {
    return getNumPlayersDraftedAtPosition(Position.FORWARD);
  }

  private int getNumPlayersDraftedAtPosition(final Position position)
  {
    int numPlayersDraftedAtPosition = 0;
    for (final DraftPick d : draft.getDraftPicks())
    {
      if (d.getPlayer().getPosition() == position)
      {
        numPlayersDraftedAtPosition++;
      }
    }
    return numPlayersDraftedAtPosition;
  }

  private int getNumDefensemenDrafted()
  {
    return getNumPlayersDraftedAtPosition(Position.DEFENSEMAN);
  }

  private int getNumGoaliesDrafted()
  {
    return getNumPlayersDraftedAtPosition(Position.GOALIE);
  }
}
