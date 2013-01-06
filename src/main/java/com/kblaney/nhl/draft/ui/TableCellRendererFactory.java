package com.kblaney.nhl.draft.ui;

import com.kblaney.nhl.draft.Draft;
import javax.swing.table.TableCellRenderer;

/**
 * Defines methods that get table cell renderers.
 */
interface TableCellRendererFactory
{
  /**
   * Gets a table cell renderer for a specified draft.
   *
   * @param draft the draft, which can't be null
   *
   * @return a table cell renderer for the specified draft
   */
  TableCellRenderer getTableCellRenderer(Draft draft);
}
