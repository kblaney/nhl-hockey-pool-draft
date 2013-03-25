package com.kblaney.nhl.draft.ui;

import com.google.common.collect.Lists;
import com.kblaney.assertions.ArgAssert;
import com.kblaney.nhl.draft.Poolee;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
final class ShowPooleesDialog extends JDialog
{
  private final List<Poolee> firstRoundDraftOrder;
  private final JPanel buttonPanel = new JPanel();
  private final JButton okButton = new JButton();
  private final JScrollPane pooleeScrollPane = new JScrollPane();
  private final JTable pooleeTable = new JTable();
  private final JLabel titleLabel = new JLabel();

  public ShowPooleesDialog(final Frame parent, final List<Poolee> firstRoundDraftOrder)
  {
    super(parent, /*isModel=*/true);
    ArgAssert.assertNotNull(parent, "parent");
    ArgAssert.assertNotNull(firstRoundDraftOrder, "firstRoundDraftOrder");

    // Store a defensive copy.
    //
    this.firstRoundDraftOrder = Lists.newArrayList(firstRoundDraftOrder);

    initComponents();

    getRootPane().setDefaultButton(okButton);
    setLocationRelativeTo(parent);
  }

  private void initComponents()
  {
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Poolees");
    titleLabel.setFont(new Font("Tahoma", 1, 18));
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    titleLabel.setText("Poolees");
    getContentPane().add(titleLabel, BorderLayout.NORTH);

    pooleeTable.setModel(new PooleeTableModel(firstRoundDraftOrder));
    pooleeScrollPane.setViewportView(pooleeTable);

    getContentPane().add(pooleeScrollPane, BorderLayout.CENTER);

    buttonPanel.setLayout(new BorderLayout());

    okButton.setText("OK");
    okButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent event)
      {
        exitActionPerformed(event);
      }
    });

    buttonPanel.add(okButton, BorderLayout.EAST);

    getContentPane().add(buttonPanel, BorderLayout.SOUTH);

    pack();
  }

  private void exitActionPerformed(final ActionEvent event)
  {
    dispose();
    setVisible(false);
  }
}
