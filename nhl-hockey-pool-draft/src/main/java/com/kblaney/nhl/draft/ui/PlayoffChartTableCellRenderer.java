package com.kblaney.nhl.draft.ui;

import com.kblaney.assertions.ArgAssert;
import com.kblaney.nhl.Player;
import com.kblaney.nhl.Team;
import com.kblaney.nhl.draft.Draft;
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
 * The cell renderer for a playoff chart table.
 */
final class PlayoffChartTableCellRenderer implements TableCellRenderer
{
  private static final DefaultTableCellRenderer DEFAULT_RENDERER =
        new DefaultTableCellRenderer();
  private final Draft draft;

  /**
   * Constructs a new instance of PlayoffChartTableCellRenderer.
   *
   * @param draft the draft, which can't be null
   */
  public PlayoffChartTableCellRenderer(final Draft draft)
  {
    ArgAssert.assertNotNull(draft, "draft");

    this.draft = draft;
  }

  /** {@inheritDoc} */
  public Component getTableCellRendererComponent(final JTable table,
        final Object value, final boolean isSelected, final boolean hasFocus,
        final int rowIndex, final int columnIndex)
  {
    final JLabel label = (JLabel) DEFAULT_RENDERER.
          getTableCellRendererComponent(table, value, isSelected, hasFocus,
          rowIndex, columnIndex);

    label.setHorizontalAlignment(JLabel.CENTER);

    final Color backgroundColor;
    final Color foregroundColor;

    if (value instanceof DraftPick)
    {
      final DraftPick draftPick = (DraftPick) value;
      final Player player = draftPick.getPlayer();
      label.setText(draftPick.getPickNum() + "-" +
            player.getFullName() +
            " (" + player.getPosition().getShortform() + ")");

      backgroundColor = new PlayoffColors().getColor(draftPick.getPoolee(), draft);
      foregroundColor = Color.WHITE;
      label.setFont(label.getFont().deriveFont(11.0f));

      final TableColumn column = table.getColumnModel().getColumn(columnIndex);
      // If the player's full name doesn't fit, use their shortened name.
      //
      if (label.getPreferredSize().width >= column.getWidth())
      {
        label.setText(draftPick.getPickNum() + "-" +
              player.getShortenedFullName() +
              " (" + player.getPosition().getShortform() + ")");
      }
    }
    else if (value instanceof Poolee)
    {
      label.setFont(label.getFont().deriveFont(Font.BOLD));

      final Poolee poolee = (Poolee) value;
      backgroundColor = new PlayoffColors().getColor(poolee, draft);
      foregroundColor = Color.WHITE;
    }
    else if (value instanceof Team)
    {
      label.setFont(label.getFont().deriveFont(Font.BOLD));

      final Team team = (Team) value;
      label.setText(team.getShortform());

      backgroundColor = table.getBackground();
      foregroundColor = table.getForeground();
    }
    else if (label.getText().equals("F") ||
          label.getText().equals("D") ||
          label.getText().equals("G"))
    {
      label.setFont(label.getFont().deriveFont(Font.BOLD));

      backgroundColor = table.getBackground();
      foregroundColor = table.getForeground();
    }
    else
    {
      backgroundColor = table.getBackground();
      foregroundColor = table.getForeground();
    }

    label.setBackground(backgroundColor);
    label.setForeground(foregroundColor);

    label.setFont(label.getFont().deriveFont(11.0f));

    return label;
  }
}
