package com.kblaney.nhl.draft.ui;

import com.google.common.collect.Lists;
import com.kblaney.commons.lang.ArgAssert;
import com.kblaney.nhl.Player;
import com.kblaney.nhl.Team;
import com.kblaney.nhl.draft.Draft;
import com.kblaney.nhl.draft.DraftPick;
import com.kblaney.nhl.draft.Poolee;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.List;
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
  private static final Color REAL_ORANGE = new Color(255, 100, 0);
  private static final Color FOREST_GREEN = new Color(0, 100, 100);
  private static final Color PURPLE = new Color(100, 0, 100);
  private static final Color LIME_GREEN = new Color(100, 125, 0);
  private static final List<Color> POOLEE_COLORS = Lists.newArrayList(
        Color.BLUE,
        Color.RED,
        Color.DARK_GRAY,
        Color.MAGENTA,
        REAL_ORANGE,
        FOREST_GREEN,
        PURPLE,
        LIME_GREEN,
        Color.PINK);
  private final Draft draft;

  /**
   * Constructs a new instance of PlayoffChartTableCellRenderer.
   *
   * @param draft the draft, which can't be null
   */
  public PlayoffChartTableCellRenderer(final Draft draft)
  {
    ArgAssert.notNull(draft, "draft");

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

      backgroundColor = POOLEE_COLORS.get(draft.getFirstRoundDraftOrder().
            indexOf(draftPick.getPoolee()));
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
      backgroundColor = POOLEE_COLORS.get(
            draft.getFirstRoundDraftOrder().indexOf(poolee));
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
