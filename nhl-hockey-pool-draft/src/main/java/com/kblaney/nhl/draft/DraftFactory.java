package com.kblaney.nhl.draft;

import com.kblaney.nhl.PlayersByTeamAndPosition;
import java.util.List;

/**
 * Defines methods that create drafts.
 */
public interface DraftFactory
{
  /**
   * Creates a new draft of a specified season type with specified players, and a specified number of rounds, poolees,
   * and draft order.
   * 
   * @param seasonType the season type of the draft, which can't be null
   * @param playersByTeamAndPosition the players that can be drafted, which can't be null
   * @param numRounds the number of rounds in the draft, which must be valid
   * @param numPoolees the number of poolees in the draft, which must be valid
   * @param draftOrderGetter the object that gets the draft order, which can't be null
   * 
   * @return the draft
   */
  Draft createNewDraft(SeasonType seasonType, PlayersByTeamAndPosition playersByTeamAndPosition, int numRounds,
        int numPoolees, DraftOrderGetter draftOrderGetter);

  /**
   * Resumes a draft of a specified season type with specified players, and a specified number of rounds, poolees, and
   * draft order getter.
   * 
   * @param seasonType the season type of the draft, which can't be null
   * @param playersByTeamAndPosition the players that can be drafted, which can't be null
   * @param numRounds the number of rounds in the draft, which must be valid
   * @param numPoolees the number of poolees in the draft, which must be valid
   * @param draftOrderGetter the object that gets the draft order, which can't be null
   * @param firstRoundDraftOrder the first round draft order, which can't be null
   * @param draftPicks the draft picks, which can't be null
   * 
   * @return the draft
   */
  Draft resumeDraft(SeasonType seasonType, PlayersByTeamAndPosition playersByTeamAndPosition, int numRounds,
        int numPoolees, DraftOrderGetter draftOrderGetter, List<Poolee> firstRoundDraftOrder, List<DraftPick> draftPicks);
}
