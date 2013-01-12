package com.kblaney.nhl.draft;

import com.kblaney.commons.lang.ArgAssert;
import com.kblaney.nhl.Player;
import com.kblaney.nhl.PlayersByTeamAndPosition;
import java.util.List;
import org.apache.commons.lang3.Validate;

/**
 * Implements methods that create drafts.
 */
public final class DraftFactoryImpl implements DraftFactory
{
  private IntOrStringValidator numRoundsValidator = new NumRoundsValidator();
  private IntOrStringValidator numPooleesValidator = new NumPooleesValidator();

  /** {@inheritDoc} */
  public Draft createNewDraft(final SeasonType seasonType, final PlayersByTeamAndPosition playersByTeamAndPosition,
        final int numRounds, final int numPoolees, final DraftOrderGetter draftOrderGetter)
  {
    ArgAssert.notNull(seasonType, "seasonType");
    ArgAssert.notNull(playersByTeamAndPosition, "playersByTeamAndPosition");
    ArgAssert.notNull(draftOrderGetter, "draftOrderGetter");
    Validate.isTrue(numRoundsValidator.isValid(numRounds));
    Validate.isTrue(numPooleesValidator.isValid(numPoolees));

    return new DraftImpl(seasonType, playersByTeamAndPosition, numRounds, numPoolees, draftOrderGetter);
  }

  /** {@inheritDoc} */
  public Draft resumeDraft(final SeasonType seasonType, final PlayersByTeamAndPosition playersByTeamAndPosition,
        final int numRounds, final int numPoolees, final DraftOrderGetter draftOrderGetter,
        final List<Poolee> firstRoundDraftOrder, final List<DraftPick> draftPicks)
  {
    ArgAssert.notNull(seasonType, "seasonType");
    ArgAssert.notNull(playersByTeamAndPosition, "playersByTeamAndPosition");
    Validate.isTrue(numRoundsValidator.isValid(numRounds));
    Validate.isTrue(numPooleesValidator.isValid(numPoolees));
    ArgAssert.notNull(draftOrderGetter, "draftOrderGetter");
    ArgAssert.notNull(firstRoundDraftOrder, "firstRoundDraftOrder");
    ArgAssert.notNull(draftPicks, "draftPicks");

    final Draft draft = new DraftImpl(seasonType, playersByTeamAndPosition, numRounds, numPoolees, draftOrderGetter);

    int draftPosition = 1;
    for (final Poolee poolee : firstRoundDraftOrder)
    {
      if (poolee != null)
      {
        draft.addPoolee(poolee, draftPosition);
        draftPosition++;
      }
    }

    if (isFirstRoundDraftOrderComplete(firstRoundDraftOrder))
    {
      draft.startDraft();

      for (final DraftPick draftPick : draftPicks)
      {
        final Player player = draftPick.getPlayer();
        final Poolee poolee = draftPick.getPoolee();
        draft.addDraftPick(player, poolee);
      }
    }

    return draft;
  }

  boolean isFirstRoundDraftOrderComplete(final List<Poolee> firstRoundDraftOrder)
  {
    return (!firstRoundDraftOrder.contains(null));
  }
}
