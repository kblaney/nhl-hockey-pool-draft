package com.kblaney.nhl.draft.ui;

import com.kblaney.nhl.draft.Draft;
import javax.swing.table.TableModel;

/**
 * Defines methods that get table models.
 */
interface TableModelFactory
{
  /**
   * Gets a table model for a specified draft.
   *
   * @param draft the draft, which can't be null
   *
   * @return a table model for the specified draft
   */
  TableModel getTableModel(Draft draft);
}
