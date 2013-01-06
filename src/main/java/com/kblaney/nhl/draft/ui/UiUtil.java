package com.kblaney.nhl.draft.ui;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 * User interface (UI) utilities.
 */
final class UiUtil
{
  /**
   * Constructor made private to restrict instantiation.
   */
  private UiUtil()
  {
  }

  /**
   * Shows a yes/no question with a default of no.
   *
   * @param component the parent component
   * @param question the question
   * @param title the title
   *
   * @return JOptionPane.YES_OPTION if the user answers yes to the question
   */
  public static int showYesNoQuestionDefaultNo(final Component component,
        final Object question, final String title)
  {
    return JOptionPane.showOptionDialog(component, question, title,
          JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
          new Object[]
          {
            "Yes",
            "No",
          }, "No");
  }

  public static void showErrorMessageDialog(final Component component,
        final Object errorMessage)
  {
    final String title = "Error";
    JOptionPane.showMessageDialog(component, errorMessage, title,
          JOptionPane.ERROR_MESSAGE);
  }
}
