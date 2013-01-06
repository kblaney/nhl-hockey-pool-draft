package com.kblaney.nhl.draft;

import com.kblaney.commons.lang.ArgAssert;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.Validate;

/**
 * Gets a draft order that is an "S"-curve.  For example, the poolee that drafts
 * last in the first round drafts first in the second round.
 */
public final class SCurveDraftOrderGetter implements DraftOrderGetter
{
  private final IntOrStringValidator numRoundsValidator =
        new NumRoundsValidator();
  private final IntOrStringValidator numPooleesValidator =
        new NumPooleesValidator();

  /** {@inheritDoc} */
  public List<Poolee> getDraftOrder(final List<Poolee> firstRoundDraftOrder,
        final int numRounds)
  {
    ArgAssert.notNull(firstRoundDraftOrder, "firstRoundDraftOrder");
    Validate.isTrue(numPooleesValidator.isValid(firstRoundDraftOrder.size()));
    Validate.isTrue(numRoundsValidator.isValid(numRounds));

    final List<Poolee> draftOrder = new ArrayList<Poolee>();
    for (int roundNum = 1; roundNum <= numRounds; roundNum++)
    {
      // The draft order is normal in odd rounds and reversed in even rounds.
      //
      if (roundNum % 2 != 0)
      {
        for (int i = 0; i < firstRoundDraftOrder.size(); i++)
        {
          draftOrder.add(firstRoundDraftOrder.get(i));
        }
      }
      else
      {
        for (int i = firstRoundDraftOrder.size() - 1; i >= 0; i--)
        {
          draftOrder.add(firstRoundDraftOrder.get(i));
        }
      }
    }

    return draftOrder;
  }
}
