package com.kblaney.nhl.draft;

import com.kblaney.assertions.ArgAssert;
import com.kblaney.nhl.Player;
import com.kblaney.nhl.PlayersByTeamAndPosition;
import java.util.List;

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
    ArgAssert.assertNotNull(seasonType, "seasonType");
    ArgAssert.assertNotNull(playersByTeamAndPosition, "playersByTeamAndPosition");
    ArgAssert.assertNotNull(draftOrderGetter, "draftOrderGetter");
    ArgAssert.assertTrue(numRoundsValidator.isValid(numRounds), "numRounds is valid");
    ArgAssert.assertTrue(numPooleesValidator.isValid(numPoolees), "numPoolees is valid");

    return new DraftImpl(seasonType, playersByTeamAndPosition, numRounds, numPoolees, draftOrderGetter);
  }

  /** {@inheritDoc} */
  public Draft resumeDraft(final SeasonType seasonType, final PlayersByTeamAndPosition playersByTeamAndPosition,
        final int numRounds, final int numPoolees, final DraftOrderGetter draftOrderGetter,
        final List<Poolee> firstRoundDraftOrder, final List<DraftPick> draftPicks)
  {
    ArgAssert.assertNotNull(seasonType, "seasonType");
    ArgAssert.assertNotNull(playersByTeamAndPosition, "playersByTeamAndPosition");
    ArgAssert.assertTrue(numRoundsValidator.isValid(numRounds), "numRounds is valid");
    ArgAssert.assertTrue(numPooleesValidator.isValid(numPoolees), "numPoolees is valid");
    ArgAssert.assertNotNull(draftOrderGetter, "draftOrderGetter");
    ArgAssert.assertNotNull(firstRoundDraftOrder, "firstRoundDraftOrder");
    ArgAssert.assertNotNull(draftPicks, "draftPicks");

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
