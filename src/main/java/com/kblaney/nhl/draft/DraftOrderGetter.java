package com.kblaney.nhl.draft;

import java.util.List;

/**
 * Defines methods that get a draft order.
 */
public interface DraftOrderGetter
{
  /**
   * Gets the draft order for an entire draft from a specified first round
   * draft order and number of rounds.
   *
   * @param firstRoundDraftOrder the first round draft order, which can't be
   * null
   * @param numRounds the number of rounds, which must be valid
   *
   * @return the draft order for the entire draft
   */
  List<Poolee> getDraftOrder(List<Poolee> firstRoundDraftOrder, int numRounds);
}
