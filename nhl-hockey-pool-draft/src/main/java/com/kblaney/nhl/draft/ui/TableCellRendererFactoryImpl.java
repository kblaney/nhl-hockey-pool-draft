package com.kblaney.nhl.draft.ui;

import com.kblaney.commons.lang.ArgAssert;
import com.kblaney.nhl.draft.Draft;
import javax.swing.table.TableCellRenderer;

/**
 * Implements methods that get table cell renderers.
 */
final class TableCellRendererFactoryImpl implements TableCellRendererFactory
{
  /** {@inheritDoc} */
  public TableCellRenderer getTableCellRenderer(final Draft draft)
  {
    ArgAssert.notNull(draft, "draft");

    switch (draft.getSeasonType())
    {
      case REGULAR_SEASON:
      {
        return new RegularSeasonChartTableCellRenderer();
      }
      case PLAYOFF:
      {
        return new PlayoffChartTableCellRenderer(draft);
      }
      default:
      {
        throw new IllegalArgumentException("Illegal season type: " +
              draft.getSeasonType());
      }
    }
  }
}
