package com.kblaney.nhl.draft.ui;

import com.google.common.collect.Lists;
import com.kblaney.assertions.ArgAssert;
import com.kblaney.nhl.Player;
import com.kblaney.nhl.PlayerNameComparator;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 * A model for a combo box that selects players.
 */
@SuppressWarnings("serial")
final class PlayerComboBoxModel extends AbstractListModel
      implements ComboBoxModel
{
  private Player selectedPlayer;
  private final List<Player> players;

  /**
   * Constructs a new instance.
   *
   * @param players the players, which can't be null
   */
  public PlayerComboBoxModel(final Set<Player> players)
  {
    ArgAssert.assertNotNull(players, "players");

    this.players = Lists.newArrayList(players);
    Collections.sort(this.players, new PlayerNameComparator());
  }

  /** {@inheritDoc} */
  public Object getSelectedItem()
  {
    return selectedPlayer;
  }

  /** {@inheritDoc} */
  public void setSelectedItem(final Object selectedItem)
  {
    selectedPlayer = (Player) selectedItem;
  }

  /** {@inheritDoc} */
  public int getSize()
  {
    return players.size();
  }

  /** {@inheritDoc} */
  public Object getElementAt(final int index)
  {
    return players.get(index);
  }
}
