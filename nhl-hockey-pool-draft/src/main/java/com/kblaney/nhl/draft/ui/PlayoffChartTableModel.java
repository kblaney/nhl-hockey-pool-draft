package com.kblaney.nhl.draft.ui;

import com.google.common.collect.Lists;
import com.kblaney.assertions.ArgAssert;
import com.kblaney.nhl.Position;
import com.kblaney.nhl.Team;
import com.kblaney.nhl.draft.Draft;
import com.kblaney.nhl.draft.DraftPick;
import com.kblaney.nhl.draft.Poolee;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.apache.commons.lang3.Validate;

/**
 * The table model for a playoff pool chart.
 *
 * This class requires that the number of upper and lower half teams be at least
 * as high as the number of poolees.
 */
@SuppressWarnings("serial")
final class PlayoffChartTableModel extends AbstractTableModel
{
  private static final int UPPER_HALF_TITLE_ROW_INDEX = 0;
  private static final int POOLEE_SUMMARY_POSITION_COLUMN_INDEX = 0;
  private final List<Team> upperHalfTeams;
  private final List<Team> lowerHalfTeams;
  private final Draft draft;
  private final int lowerHalfTitleRowIndex;
  private final int pooleeSummaryTitleRowIndex;
  private final int pooleeSummaryNumForwardsRowIndex;
  private final int pooleeSummaryNumDefensemenRowIndex;
  private final int pooleeSummaryNumGoaliesRowIndex;

  /**
   * Constructs a new instance of PlayoffChartTableModel with a specified list
   * of upper half teams, lower half teams, draft, and maximum number of picks
   * per team.
   *
   * @param upperHalfTeams the upper half teams, which can't be null or empty
   * @param lowerHalfTeams the lower half teams, which can't be null or empty
   * @param draft the draft, which can't be null
   * @param maxNumPicksPerTeam the maximum number of picks per team, which
   * must be positive
   */
  public PlayoffChartTableModel(final List<Team> upperHalfTeams,
        final List<Team> lowerHalfTeams, final Draft draft,
        final int maxNumPicksPerTeam)
  {
    Validate.notEmpty(upperHalfTeams);
    Validate.notEmpty(lowerHalfTeams);
    ArgAssert.assertNotNull(draft, "draft");
    ArgAssert.assertGreaterThanOrEqual(maxNumPicksPerTeam, 1, "maxNumPicksPerTeam ");
    Validate.isTrue(draft.getNumPoolees() <= upperHalfTeams.size());
    Validate.isTrue(draft.getNumPoolees() <= lowerHalfTeams.size());

    // Make defensive copies.
    //
    this.upperHalfTeams = Lists.newArrayList(upperHalfTeams);
    this.lowerHalfTeams = Lists.newArrayList(lowerHalfTeams);

    this.draft = draft;

    lowerHalfTitleRowIndex = UPPER_HALF_TITLE_ROW_INDEX +
          maxNumPicksPerTeam + 1;
    this.pooleeSummaryTitleRowIndex = lowerHalfTitleRowIndex +
          maxNumPicksPerTeam + 1;
    pooleeSummaryNumForwardsRowIndex = pooleeSummaryTitleRowIndex + 1;
    pooleeSummaryNumDefensemenRowIndex = pooleeSummaryNumForwardsRowIndex + 1;
    pooleeSummaryNumGoaliesRowIndex = pooleeSummaryNumDefensemenRowIndex + 1;
  }

  /** {@inheritDoc} */
  public int getRowCount()
  {
    // The last row is the one with the number of goalies in the poolee
    // summary.
    //
    return pooleeSummaryNumGoaliesRowIndex + 1;
  }

  /** {@inheritDoc} */
  public int getColumnCount()
  {
    return Math.max(upperHalfTeams.size(), lowerHalfTeams.size()) + 1;
  }

  /** {@inheritDoc} */
  public Object getValueAt(final int rowIndex, final int columnIndex)
  {
    final Object value;

    if (rowIndex == UPPER_HALF_TITLE_ROW_INDEX)
    {
      value = getTeamAt(columnIndex, upperHalfTeams);
    }
    else if (rowIndex == lowerHalfTitleRowIndex)
    {
      value = getTeamAt(columnIndex, lowerHalfTeams);
    }
    else if (rowIndex == pooleeSummaryTitleRowIndex)
    {
      value = getPooleeAt(columnIndex);
    }
    else if (rowIndex == pooleeSummaryNumForwardsRowIndex)
    {
      if (columnIndex == POOLEE_SUMMARY_POSITION_COLUMN_INDEX)
      {
        value = "F";
      }
      else
      {
        value = getNumPlayersAt(columnIndex, Position.FORWARD);
      }
    }
    else if (rowIndex == pooleeSummaryNumDefensemenRowIndex)
    {
      if (columnIndex == POOLEE_SUMMARY_POSITION_COLUMN_INDEX)
      {
        value = "D";
      }
      else
      {
        value = getNumPlayersAt(columnIndex, Position.DEFENSEMAN);
      }
    }
    else if (rowIndex == pooleeSummaryNumGoaliesRowIndex)
    {
      if (columnIndex == POOLEE_SUMMARY_POSITION_COLUMN_INDEX)
      {
        value = "G";
      }
      else
      {
        value = getNumPlayersAt(columnIndex, Position.GOALIE);
      }
    }
    else if (rowIndex < lowerHalfTitleRowIndex)
    {
      value = getDraftPickAt(columnIndex, rowIndex, upperHalfTeams);
    }
    else if (rowIndex < pooleeSummaryTitleRowIndex)
    {
      value = getDraftPickAt(columnIndex,
            rowIndex - lowerHalfTitleRowIndex, lowerHalfTeams);
    }
    else
    {
      // This should never happen!
      //
      throw new IllegalArgumentException(
            "Invalid row index:  " + rowIndex);
    }

    return value;
  }

  /**
   * Gets the team at a specified column index from a specified list of teams
   * (either the upper or lower teams).
   *
   * @param columnIndex the column index, which can't be negative
   * @param teams the teams, which can't be null
   *
   * @return the team at the column index in the specified list, or null
   */
  private Team getTeamAt(final int columnIndex, final List<Team> teams)
  {
    ArgAssert.assertGreaterThanOrEqual(columnIndex, 0, "columnIndex");
    ArgAssert.assertNotNull(teams, "teams");

    if (columnIndex != POOLEE_SUMMARY_POSITION_COLUMN_INDEX)
    {
      if (columnIndex <= teams.size())
      {
        return teams.get(columnIndex - 1);
      }
      else
      {
        return null;
      }
    }
    else
    {
      return null;
    }
  }

  /**
   * Gets the poolee at a specified column index.
   *
   * @param columnIndex the column index, which can't be negative
   *
   * @return the poolee at the column index, or null
   */
  private Poolee getPooleeAt(final int columnIndex)
  {
    ArgAssert.assertGreaterThanOrEqual(columnIndex, 0, "columnIndex");

    if (columnIndex != POOLEE_SUMMARY_POSITION_COLUMN_INDEX)
    {
      if (columnIndex <= draft.getFirstRoundDraftOrder().size())
      {
        return draft.getFirstRoundDraftOrder().get(columnIndex - 1);
      }
      else
      {
        return null;
      }
    }
    else
    {
      return null;
    }
  }

  /**
   * Gets the number of players a poolee at a specified column has at a
   * specified position.
   *
   * @param columnIndex the column index, which can't be negative
   * @param position the position, which can't be null
   *
   * @return the number of players the poolee at the specified column has at
   * the specified position
   */
  private Integer getNumPlayersAt(final int columnIndex,
        final Position position)
  {
    ArgAssert.assertGreaterThanOrEqual(columnIndex, 0, "columnIndex");
    ArgAssert.assertNotNull(position, "position");

    final Poolee poolee = getPooleeAt(columnIndex);
    if (poolee == null)
    {
      return null;
    }
    else
    {
      return draft.getDraftPicksOfPooleeAtPosition(poolee, position).size();
    }
  }

  /**
   * Gets the draft pick at a specified column and row.
   *
   * @param columnIndex the column index, which can't be negative
   * @param rowIndex the row index, which can't be negative
   * @param teams the teams, which can't be null
   *
   * @return the draft pick at the specified column and row, or null
   */
  private DraftPick getDraftPickAt(final int columnIndex, final int rowIndex,
        final List<Team> teams)
  {
    ArgAssert.assertGreaterThanOrEqual(columnIndex, 0, "columnIndex");
    ArgAssert.assertGreaterThanOrEqual(rowIndex, 0, "rowIndex");
    ArgAssert.assertNotNull(teams, "teams");

    final Team team = getTeamAt(columnIndex, teams);
    if (team == null)
    {
      return null;
    }
    else
    {
      final List<DraftPick> draftPicksOnTeam = draft.getDraftPicksOnTeam(team);
      if (rowIndex <= draftPicksOnTeam.size())
      {
        return draftPicksOnTeam.get(rowIndex - 1);
      }
      else
      {
        return null;
      }
    }
  }
}
