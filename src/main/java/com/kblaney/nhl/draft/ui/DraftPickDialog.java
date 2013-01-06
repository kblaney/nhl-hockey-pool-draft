package com.kblaney.nhl.draft.ui;

import com.kblaney.commons.lang.ArgAssert;
import com.kblaney.nhl.draft.DraftPick;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.Timer;

/**
 * A dialog that displays a draft pick.
 */
@SuppressWarnings("serial")
final class DraftPickDialog extends JDialog
{
  private final DraftPick draftPick;
  private Timer pollingTimer;

  /**
   * Constructs a new instance of DraftPickDialog with a specified parent
   * frame and draft pick.
   *
   * @param parent the parent frame, which can't be null
   * @param draftPick the draft pick, which can't be null
   */
  public DraftPickDialog(final Frame parent, final DraftPick draftPick)
  {
    super(parent, /*isModal=*/true);
    ArgAssert.notNull(parent, "parent");
    this.draftPick = ArgAssert.notNull(draftPick, "draftPick");

    initComponents();

    setLocationRelativeTo(parent);

    final int minNumMillisecondsToDisplay = 7500;
    final Timer timer = new Timer(minNumMillisecondsToDisplay,
          new ActionListener()
          {
            public void actionPerformed(final ActionEvent actionEvent)
            {
            }
          });
    timer.setRepeats(false);
    timer.start();

    final int numMillisecondsToWaitBetweenPolls = 500;
    pollingTimer = new Timer(numMillisecondsToWaitBetweenPolls,
          new ActionListener()
          {
            public void actionPerformed(final ActionEvent actionEvent)
            {
              if (!timer.isRunning())
              {
                pollingTimer.stop();
                dispose();
                setVisible(false);
              }
            }
          });
    pollingTimer.setRepeats(true);
    pollingTimer.start();
  }

  private String getPooleeLabelText()
  {
    return draftPick.getPoolee().getFullName() + " selects:";
  }

  private String getPickNumLabelText()
  {
    return "Pick #" + draftPick.getPickNum();
  }

  private Icon getPlayerPhotoIcon()
  {
    return new PlayerPhotoIconSupplier().getIcon(draftPick.getPlayer());
  }

  private String getPlayerNameLabelText()
  {
    return draftPick.getPlayer().getFullName();
  }

  private String getPositionLabelText()
  {
    return draftPick.getPlayer().getPosition().toString();
  }

  private String getTeamLabelText()
  {
    return draftPick.getPlayer().getTeam().toString();
  }

  /** This method is called from within the constructor to
   * initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is
   * always regenerated by the Form Editor.
   */
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;

    pooleeLabel = new javax.swing.JLabel();
    pickNumLabel = new javax.swing.JLabel();
    playerPhotoLabel = new javax.swing.JLabel();
    playerNameLabel = new javax.swing.JLabel();
    positionLabel = new javax.swing.JLabel();
    teamLabel = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    setTitle("Last draft pick");
    getContentPane().setLayout(new java.awt.GridBagLayout());

    pooleeLabel.setFont(new java.awt.Font("Tahoma", 1, 36));
    pooleeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    pooleeLabel.setText(getPooleeLabelText());
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    getContentPane().add(pooleeLabel, gridBagConstraints);

    pickNumLabel.setFont(new java.awt.Font("Tahoma", 1, 36));
    pickNumLabel.setText(getPickNumLabelText());
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    getContentPane().add(pickNumLabel, gridBagConstraints);

    playerPhotoLabel.setIcon(getPlayerPhotoIcon());
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.insets = new java.awt.Insets(0, 20, 0, 20);
    getContentPane().add(playerPhotoLabel, gridBagConstraints);

    playerNameLabel.setFont(new java.awt.Font("Tahoma", 1, 36));
    playerNameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    playerNameLabel.setText(getPlayerNameLabelText());
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    getContentPane().add(playerNameLabel, gridBagConstraints);

    positionLabel.setFont(new java.awt.Font("Tahoma", 1, 36));
    positionLabel.setText(getPositionLabelText());
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 4;
    getContentPane().add(positionLabel, gridBagConstraints);

    teamLabel.setFont(new java.awt.Font("Tahoma", 1, 36));
    teamLabel.setText(getTeamLabelText());
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 5;
    getContentPane().add(teamLabel, gridBagConstraints);

    pack();
  }// </editor-fold>//GEN-END:initComponents

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel pickNumLabel;
  private javax.swing.JLabel playerNameLabel;
  private javax.swing.JLabel playerPhotoLabel;
  private javax.swing.JLabel pooleeLabel;
  private javax.swing.JLabel positionLabel;
  private javax.swing.JLabel teamLabel;
  // End of variables declaration//GEN-END:variables
}
