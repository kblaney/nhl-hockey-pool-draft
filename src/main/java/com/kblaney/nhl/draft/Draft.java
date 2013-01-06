package com.kblaney.nhl.draft;

import com.kblaney.nhl.Player;
import com.kblaney.nhl.Position;
import com.kblaney.nhl.Team;
import java.util.List;
import java.util.Set;

/**
 * A draft.
 *
 * Methods in this interface throw an {@code IllegalStateException} if the state
 * is not valid and {@code IllegalArgumentException} if parameters are illegal.
 */
public interface Draft
{
  /**
   * Gets the season type of the draft.
   *
   * @return the season type of the draft
   */
  SeasonType getSeasonType();

  /**
   * Adds a poolee with a specified draft position in the first round.
   *
   * @param poolee the poolee to add, which can't be null
   * @param draftPositionInFirstRound the poolee's draft position in the first
   * round, which must be a legal draft position for this draft
   */
  void addPoolee(Poolee poolee, int draftPositionInFirstRound);

  /**
   * Starts the draft.
   */
  void startDraft();

  /**
   * Gets the number of rounds in the draft.
   *
   * @return the number of rounds in the draft
   */
  int getNumRounds();

  /**
   * Gets the number of poolees in the draft.
   *
   * @return the number of poolees in the draft
   */
  int getNumPoolees();

  /**
   * Gets whether the draft is over.
   *
   * @return true if the draft is over; false otherwise
   */
  boolean isDraftOver();

  /**
   * Gets the next poolee to draft.
   *
   * @return the next poolee to draft
   */
  Poolee getNextPooleeToDraft();

  /**
   * Gets the number of consecutive picks for the next poolee to draft.
   *
   * @return the number of consecutive picks for the next poolee to draft
   */
  int getNumConsecutivePicksForNextPooleeToDraft();

  /**
   * Gets the next draft pick number.
   *
   * @return the next draft pick number
   */
  int getNextDraftPickNum();

  /**
   * Gets the round number of the next draft pick.
   *
   * @return the round number of the next draft pick
   */
  int getRoundNumOfNextDraftPick();

  /**
   * Adds a draft pick of a specified player by a specified poolee.
   *
   * @param player the drafted player, which can't be null
   * @param poolee the poolee that drafted the player, which can't be null
   *
   * @return the draft pick
   */
  DraftPick addDraftPick(Player player, Poolee poolee);

  /**
   * Gets the first round draft order.  Note that this method always returns
   * a list with a size that is equal to the number of poolees.  The return
   * value has the following properties:
   * <ul>
   * <li> (element X == null) iff no poolee has been defined for position X+1
   * <li> (element X != null) iff a poolee has been defined for position X+1
   * </ul>
   *
   * @return the first round draft order
   */
  List<Poolee> getFirstRoundDraftOrder();

  /**
   * Returns whether a specified player has already been drafted.
   *
   * @param player the player
   *
   * @return true if the player has been drafted; false otherwise
   */
  boolean isPlayerDrafted(Player player);

  /**
   * Returns a list of all draft picks thus far in the draft.  Note that the
   * list returned by this method is sorted by draft pick number.
   *
   * @return the list of all draft picks thus far in the draft
   */
  List<DraftPick> getDraftPicks();

  /**
   * Returns a list of all draft picks of a specified poolee thus far in the
   * draft.  Note that the list returned by this method is sorted by draft
   * pick number.
   *
   * @param poolee the poolee, which can't be null
   *
   * @return the list of draft picks by the specified poolee thus far in the
   * draft
   */
  List<DraftPick> getDraftPicksOfPoolee(Poolee poolee);

  /**
   * Returns a list of all draft picks of a specified poolee from a specified
   * team thus far in the draft.  Note that the list returned by this method is
   * sorted by draft pick number.
   *
   * @param poolee the poolee, which can't be null
   * @param team the team, which can't be null
   *
   * @return the list of draft picks by the specified poolee from the
   * specified team thus far in the draft
   */
  List<DraftPick> getDraftPicksOfPooleeFromTeam(Poolee poolee, Team team);

  /**
   * Returns a list of all draft picks of a specified poolee at a specified
   * position thus far in the draft.  Note that the list returned by this
   * method is sorted by draft pick number.
   *
   * @param poolee the poolee, which can't be null
   * @param position the position, which can't be null
   *
   * @return the list of draft picks by the specified poolee at the
   * specified position thus far in the draft
   */
  List<DraftPick> getDraftPicksOfPooleeAtPosition(Poolee poolee,
        Position position);

  /**
   * Returns a list of all draft picks from a specified team thus far in the
   * draft.  Note that the list returned by this method is sorted by draft
   * pick number.
   *
   * @param team the team, which can't be null
   *
   * @return the list of draft picks from the specified team thus far in the
   * draft
   */
  List<DraftPick> getDraftPicksOnTeam(Team team);

  /**
   * Returns the most recent N draft picks.  If fewer than N draft picks have
   * been made, this method returns a list of all draft picks made thus far.
   *
   * @param numDraftPicksToReturn the number of draft picks to return (N),
   * which can't be negative
   *
   * @return the most recent N draft picks, with the most recent pick at the
   * start of the list
   */
  List<DraftPick> getMostRecentNDraftPicks(int numDraftPicksToReturn);

  /**
   * Gets the set of teams from which a specified poolee is allowed to draft
   * from.
   *
   * @param poolee the poolee, which can't be null
   *
   * @return the set of teams the poolee is allowed to draft from
   */
  Set<Team> getTeamsAvailableToDraftFrom(Poolee poolee);
}
