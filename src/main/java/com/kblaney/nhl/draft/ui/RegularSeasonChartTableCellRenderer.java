package com.kblaney.nhl.draft.ui;

import com.kblaney.nhl.Player;
import com.kblaney.nhl.Position;
import com.kblaney.nhl.Team;
import com.kblaney.nhl.draft.DraftPick;
import com.kblaney.nhl.draft.Poolee;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * The cell renderer for a regular season chart table.
 */
final class RegularSeasonChartTableCellRenderer implements TableCellRenderer
{
  private final RegularSeasonColors regularSeasonColors =
        new RegularSeasonColors();
  private static final DefaultTableCellRenderer DEFAULT_RENDERER =
        new DefaultTableCellRenderer();
  private static final int NUM_FOOTER_ROWS = 3;

  /** {@inheritDoc} */
  public Component getTableCellRendererComponent(final JTable table,
        final Object value, final boolean isSelected, final boolean hasFocus,
        final int rowIndex, final int columnIndex)
  {
    final JLabel label = (JLabel) DEFAULT_RENDERER.
          getTableCellRendererComponent(table, value, isSelected, hasFocus,
          rowIndex, columnIndex);

    label.setHorizontalAlignment(JLabel.CENTER);

    final int totalNumRows = table.getModel().getRowCount();
    final int forwardSummaryRowIndex = totalNumRows - NUM_FOOTER_ROWS;
    final int defensemanSummaryRowIndex = forwardSummaryRowIndex + 1;
    final int goalieSummaryRowIndex = defensemanSummaryRowIndex + 1;

    if (value instanceof DraftPick)
    {
      final DraftPick draftPick = (DraftPick) value;
      final Player player = draftPick.getPlayer();
      label.setText(draftPick.getPickNum() + "-" +
            player.getFullName());
      label.setFont(label.getFont().deriveFont(Font.BOLD));
      label.setForeground(regularSeasonColors.getColor(
            draftPick.getPlayer().getPosition()));
      label.setBackground(Color.WHITE);

      final TableColumn column = table.getColumnModel().
            getColumn(columnIndex);
      // If the player's full name doesn't fit, use their shortened name.
      //
      if (label.getPreferredSize().width >= column.getWidth())
      {
        label.setText(draftPick.getPickNum() + "-" +
              player.getShortenedFullName());
      }
    }
    else if (value instanceof Team)
    {
      final Team team = (Team) value;
      label.setText(team.getShortform());
      label.setFont(label.getFont().deriveFont(Font.BOLD));
      label.setBackground(Color.WHITE);
      label.setForeground(Color.BLACK);
    }
    else if (value instanceof Poolee)
    {
      final Poolee poolee = (Poolee) value;
      label.setText(poolee.getFullName());
      label.setFont(label.getFont().deriveFont(Font.BOLD));
      label.setBackground(Color.WHITE);
      label.setForeground(Color.BLACK);
    }
    else if (rowIndex == forwardSummaryRowIndex)
    {
      label.setFont(label.getFont().deriveFont(Font.BOLD));
      label.setForeground(regularSeasonColors.getColor(Position.FORWARD));
      label.setBackground(Color.WHITE);
    }
    else if (rowIndex == defensemanSummaryRowIndex)
    {
      label.setFont(label.getFont().deriveFont(Font.BOLD));
      label.setForeground(regularSeasonColors.getColor(Position.DEFENSEMAN));
      label.setBackground(Color.WHITE);
    }
    else if (rowIndex == goalieSummaryRowIndex)
    {
      label.setFont(label.getFont().deriveFont(Font.BOLD));
      label.setForeground(regularSeasonColors.getColor(Position.GOALIE));
      label.setBackground(Color.WHITE);
    }
    else if (columnIndex ==
          RegularSeasonChartTableModel.NUM_PICKS_COLUMN_INDEX)
    {
      label.setFont(label.getFont().deriveFont(Font.BOLD));
      label.setForeground(Color.BLACK);
      label.setBackground(Color.WHITE);
    }
    else
    {
      label.setBackground(table.getBackground());
      label.setForeground(Color.WHITE);
    }

    return label;
  }
}
