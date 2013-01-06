package com.kblaney.nhl.draft.ui;

import com.google.common.collect.Lists;
import com.kblaney.nhl.Position;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

/**
 * A model for a combo box that selects a position.
 */
@SuppressWarnings("serial")
final class PositionComboBoxModel extends AbstractListModel
      implements ComboBoxModel
{
  private Position selectedPosition;
  private final List<Position> positions;

  public PositionComboBoxModel()
  {
    positions = Lists.newArrayList(Position.FORWARD, Position.DEFENSEMAN,
          Position.GOALIE);
  }

  /** {@inheritDoc} */
  public Object getSelectedItem()
  {
    return selectedPosition;
  }

  /** {@inheritDoc} */
  public void setSelectedItem(final Object selectedItem)
  {
    selectedPosition = (Position) selectedItem;
  }

  /** {@inheritDoc} */
  public int getSize()
  {
    return positions.size();
  }

  /** {@inheritDoc} */
  public Object getElementAt(final int index)
  {
    return positions.get(index);
  }
}
