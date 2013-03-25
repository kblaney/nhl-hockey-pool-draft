package com.kblaney.nhl.draft.ui;

import com.kblaney.assertions.ArgAssert;
import com.kblaney.nhl.draft.DraftPositionInFirstRoundValidator;
import com.kblaney.nhl.draft.IntOrStringValidator;
import com.kblaney.nhl.draft.NumPooleesValidator;
import com.kblaney.nhl.draft.Poolee;
import com.kblaney.nhl.draft.PooleeNameValidator;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 * A dialog that adds a poolee to a draft.
 */
@SuppressWarnings("serial")
final class AddPooleeDialog extends JDialog
{
  private static final boolean IS_MODAL = true;
  private final PooleeNameValidator nameValidator = new PooleeNameValidator();
  private final IntOrStringValidator draftPositionInFirstRoundValidator;
  private boolean wasOkPressed;
  private Poolee poolee;
  private int draftPositionInFirstRound;
  private JPanel buttonPanel;
  private JLabel draftPositionInFirstRoundLabel;
  private JTextField draftPositionInFirstRoundTextField;
  private JLabel firstNameLabel;
  private JTextField firstNameTextField;
  private JLabel lastNameLabel;
  private JTextField lastNameTextField;
  private JButton okButton;

  /**
   * Constructs a new instance of AddPooleeDialog that has a specified parent frame for a draft that has a specified
   * number of poolees.
   * 
   * @param parent the parent frame, which can't be null
   * @param numPoolees the number of poolees in the draft, which must be valid
   */
  public AddPooleeDialog(final Frame parent, final int numPoolees)
  {
    super(parent, AddPooleeDialog.IS_MODAL);
    ArgAssert.assertNotNull(parent, "parent");
    ArgAssert.assertTrue(new NumPooleesValidator().isValid(numPoolees), "numPoolees is valid");

    draftPositionInFirstRoundValidator = new DraftPositionInFirstRoundValidator(numPoolees);

    initComponents();

    getRootPane().setDefaultButton(okButton);
    setLocationRelativeTo(parent);
  }

  /**
   * Determines whether OK was pressed to close this dialog.
   * 
   * @return true if OK was pressed to close this dialog; false otherwise
   */
  public boolean wasOkPressed()
  {
    return wasOkPressed;
  }

  /**
   * Gets the poolee entered on this dialog, or null if the user canceled this dialog.
   * 
   * @return the poolee entered on this dialog, or null if the user canceled this dialog
   */
  public Poolee getPoolee()
  {
    return poolee;
  }

  /**
   * Gets the draft position in the first round entered on this dialog, or 0 if the user canceled this dialog.
   * 
   * @return the draft position in the first round entered on this dialog, or 0 if the user canceled this dialog
   */
  public int getDraftPositionInFirstRound()
  {
    return draftPositionInFirstRound;
  }

  private void initComponents()
  {
    firstNameLabel = new JLabel();
    lastNameLabel = new JLabel();
    draftPositionInFirstRoundLabel = new JLabel();
    firstNameTextField = new JTextField();
    lastNameTextField = new JTextField();
    draftPositionInFirstRoundTextField = new JTextField();
    buttonPanel = new JPanel();
    okButton = new JButton();

    getContentPane().setLayout(new GridBagLayout());

    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    setTitle("Add new poolee");
    firstNameLabel.setText("First name:  ");
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.anchor = GridBagConstraints.EAST;
    gridBagConstraints.insets = new Insets(5, 0, 5, 0);
    getContentPane().add(firstNameLabel, gridBagConstraints);

    lastNameLabel.setText("Last name:  ");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = GridBagConstraints.EAST;
    gridBagConstraints.insets = new Insets(5, 0, 5, 0);
    getContentPane().add(lastNameLabel, gridBagConstraints);

    draftPositionInFirstRoundLabel.setText("Draft position in first round:  ");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = GridBagConstraints.EAST;
    gridBagConstraints.insets = new Insets(5, 0, 5, 0);
    getContentPane().add(draftPositionInFirstRoundLabel, gridBagConstraints);

    firstNameTextField.setColumns(25);
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    getContentPane().add(firstNameTextField, gridBagConstraints);

    lastNameTextField.setColumns(25);
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    getContentPane().add(lastNameTextField, gridBagConstraints);

    draftPositionInFirstRoundTextField.setColumns(25);
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    getContentPane().add(draftPositionInFirstRoundTextField, gridBagConstraints);

    okButton.setText("OK");
    okButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent event)
      {
        okActionPerformed(event);
      }
    });

    buttonPanel.add(okButton);

    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.anchor = GridBagConstraints.EAST;
    getContentPane().add(buttonPanel, gridBagConstraints);

    pack();
  }

  private void okActionPerformed(final ActionEvent event)
  {
    final String firstName = getFirstName();
    final String lastName = getLastName();
    final String draftPositionInFirstRoundString = getDraftPositionInFirstRoundString();

    if (isValid(firstName))
    {
      if (isValid(lastName))
      {
        if (draftPositionInFirstRoundValidator.isValid(draftPositionInFirstRoundString))
        {
          draftPositionInFirstRound = Integer.parseInt(draftPositionInFirstRoundString);
          poolee = new Poolee(firstName, lastName);
          wasOkPressed = true;
          dispose();
          setVisible(false);
        }
        else
        {
          JOptionPane.showMessageDialog(this, draftPositionInFirstRoundString + " is not a valid " + "draft position");
        }
      }
      else
      {
        JOptionPane.showMessageDialog(this, lastName + " is not a valid last name");
      }
    }
    else
    {
      JOptionPane.showMessageDialog(this, firstName + " is not a valid first name");
    }
  }

  private boolean isValid(final String name)
  {
    return nameValidator.isValid(name);
  }

  private String getFirstName()
  {
    return trimmedTextIn(firstNameTextField);
  }

  private String trimmedTextIn(final JTextField textField)
  {
    return textField.getText().trim();
  }

  private String getLastName()
  {
    return trimmedTextIn(lastNameTextField);
  }

  private String getDraftPositionInFirstRoundString()
  {
    return trimmedTextIn(draftPositionInFirstRoundTextField);
  }
}
