package com.kblaney.nhl.draft.ui;

import com.kblaney.assertions.ArgAssert;
import com.kblaney.nhl.draft.DraftPick;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.WindowConstants;

/**
 * A dialog that displays a draft pick.
 */
@SuppressWarnings("serial")
final class DraftPickDialog extends JDialog
{
  private final DraftPick draftPick;
  private Timer pollingTimer;
  private final JLabel pickNumLabel = new JLabel();
  private final JLabel playerNameLabel = new JLabel();
  private final JLabel playerPhotoLabel = new JLabel();
  private final JLabel pooleeLabel = new JLabel();
  private final JLabel positionLabel = new JLabel();
  private final JLabel teamLabel = new JLabel();

  /**
   * Constructs a new instance of DraftPickDialog with a specified parent frame and draft pick.
   * 
   * @param parent the parent frame, which can't be null
   * @param draftPick the draft pick, which can't be null
   */
  public DraftPickDialog(final Frame parent, final DraftPick draftPick)
  {
    super(parent, /* isModal= */true);
    ArgAssert.assertNotNull(parent, "parent");
    this.draftPick = ArgAssert.assertNotNull(draftPick, "draftPick");

    initComponents();

    setLocationRelativeTo(parent);

    final int minNumMillisecondsToDisplay = 7500;
    final Timer timer = new Timer(minNumMillisecondsToDisplay, new ActionListener()
    {
      public void actionPerformed(final ActionEvent actionEvent)
      {
      }
    });
    timer.setRepeats(false);
    timer.start();

    final int numMillisecondsToWaitBetweenPolls = 500;
    pollingTimer = new Timer(numMillisecondsToWaitBetweenPolls, new ActionListener()
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

  private void initComponents()
  {
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    setTitle("Last draft pick");
    getContentPane().setLayout(new GridBagLayout());

    pooleeLabel.setFont(new Font("Tahoma", 1, 36));
    pooleeLabel.setHorizontalAlignment(SwingConstants.CENTER);
    pooleeLabel.setText(getPooleeLabelText());
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    getContentPane().add(pooleeLabel, gridBagConstraints);

    pickNumLabel.setFont(new Font("Tahoma", 1, 36));
    pickNumLabel.setText(getPickNumLabelText());
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    getContentPane().add(pickNumLabel, gridBagConstraints);

    playerPhotoLabel.setIcon(getPlayerPhotoIcon());
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.insets = new Insets(0, 20, 0, 20);
    getContentPane().add(playerPhotoLabel, gridBagConstraints);

    playerNameLabel.setFont(new Font("Tahoma", 1, 36));
    playerNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
    playerNameLabel.setText(getPlayerNameLabelText());
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = GridBagConstraints.WEST;
    getContentPane().add(playerNameLabel, gridBagConstraints);

    positionLabel.setFont(new Font("Tahoma", 1, 36));
    positionLabel.setText(getPositionLabelText());
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 4;
    getContentPane().add(positionLabel, gridBagConstraints);

    teamLabel.setFont(new Font("Tahoma", 1, 36));
    teamLabel.setText(getTeamLabelText());
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 5;
    getContentPane().add(teamLabel, gridBagConstraints);

    pack();
  }
}
