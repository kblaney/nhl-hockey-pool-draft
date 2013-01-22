package com.kblaney.nhl.draft.ui;

import com.google.common.collect.Lists;
import com.kblaney.assertions.ArgAssert;
import com.kblaney.nhl.Team;
import java.util.List;
import java.util.Set;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 * A model for a combo box that selects a team.
 */
@SuppressWarnings("serial")
final class TeamComboBoxModel extends AbstractListModel implements ComboBoxModel
{
  private Team selectedTeam;
  private final List<Team> teams;

  public TeamComboBoxModel(final Set<Team> teamsToInclude)
  {
    ArgAssert.assertNotNull(teamsToInclude, "teamsToInclude");

    teams = Lists.newArrayList();
    for (final Team team : Team.values())
    {
      if (teamsToInclude.contains(team))
      {
        teams.add(team);
      }
    }
  }

  /** {@inheritDoc} */
  public Object getSelectedItem()
  {
    return selectedTeam;
  }

  /** {@inheritDoc} */
  public void setSelectedItem(final Object selectedItem)
  {
    selectedTeam = (Team) selectedItem;
  }

  /** {@inheritDoc} */
  public int getSize()
  {
    return teams.size();
  }

  /** {@inheritDoc} */
  public Object getElementAt(final int index)
  {
    return teams.get(index);
  }
}
