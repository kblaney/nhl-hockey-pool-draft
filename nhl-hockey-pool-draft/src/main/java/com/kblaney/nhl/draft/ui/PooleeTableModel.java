package com.kblaney.nhl.draft.ui;

import com.google.common.collect.Lists;
import com.kblaney.assertions.ArgAssert;
import com.kblaney.nhl.draft.Poolee;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.apache.commons.lang3.StringUtils;

/**
 * A model for the poolee table.
 */
@SuppressWarnings("serial")
final class PooleeTableModel extends AbstractTableModel
{
  private List<Poolee> firstRoundDraftOrder;
  private static final String[] COLUMN_NAMES =
  {
    "Draft position in first round",
    "Poolee",
  };
  private static final int DRAFT_POSITION_COLUMN_INDEX = 0;

  public PooleeTableModel(final List<Poolee> firstRoundDraftOrder)
  {
    ArgAssert.assertNotNull(firstRoundDraftOrder, "firstRoundDraftOrder");

    this.firstRoundDraftOrder = Lists.newArrayList(firstRoundDraftOrder);
  }

  /** {@inheritDoc} */
  @Override
  public String getColumnName(final int columnIndex)
  {
    return COLUMN_NAMES[columnIndex];
  }

  /** {@inheritDoc} */
  public int getRowCount()
  {
    return firstRoundDraftOrder.size();
  }

  /** {@inheritDoc} */
  public int getColumnCount()
  {
    return COLUMN_NAMES.length;
  }

  /** {@inheritDoc} */
  public Object getValueAt(final int rowIndex, final int columnIndex)
  {
    if (columnIndex == DRAFT_POSITION_COLUMN_INDEX)
    {
      return rowIndex + 1;
    }
    else
    {
      final Poolee poolee = firstRoundDraftOrder.get(rowIndex);
      if (poolee == null)
      {
        return StringUtils.EMPTY;
      }
      else
      {
        return poolee.getFullName();
      }
    }
  }
}
