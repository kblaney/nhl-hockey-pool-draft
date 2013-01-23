package com.kblaney.nhl.draft;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.kblaney.assertions.ArgAssert;
import com.kblaney.nhl.Player;
import com.kblaney.nhl.PlayersByTeamAndPosition;
import com.kblaney.nhl.Position;
import com.kblaney.nhl.Team;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Implements a draft.
 */
final class DraftImpl implements Draft
{
  private final SeasonType seasonType;
  private final PlayersByTeamAndPosition playersByTeamAndPosition;
  private final DraftOrderGetter draftOrderGetter;
  private final int numRounds;
  private final int numPoolees;
  private final IntOrStringValidator draftPositionInFirstRoundValidator;
  private final List<Poolee> firstRoundDraftOrder;
  private final Set<Player> draftedPlayers;
  private final List<DraftPick> draftPicks;
  private final Map<Poolee, List<DraftPick>> draftPicksByPoolee;
  private final IntOrStringValidator numRoundsValidator = new NumRoundsValidator();
  private final IntOrStringValidator numPooleesValidator = new NumPooleesValidator();
  private List<Poolee> draftOrder;
  private DraftState draftState;

  DraftImpl(final SeasonType seasonType, final PlayersByTeamAndPosition playersByTeamAndPosition, final int numRounds,
        final int numPoolees, final DraftOrderGetter draftOrderGetter)
  {
    ArgAssert.assertNotNull(seasonType, "seasonType");
    ArgAssert.assertNotNull(playersByTeamAndPosition, "playersByTeamAndPosition");
    ArgAssert.assertNotNull(draftOrderGetter, "draftOrderGetter");
    ArgAssert.assertTrue(numRoundsValidator.isValid(numRounds), "numRounds is valid");
    ArgAssert.assertTrue(numPooleesValidator.isValid(numPoolees), "numPoolees is valid");

    this.seasonType = seasonType;
    this.playersByTeamAndPosition = playersByTeamAndPosition;
    this.numRounds = numRounds;
    this.numPoolees = numPoolees;
    this.draftOrderGetter = draftOrderGetter;

    draftState = DraftState.NOT_STARTED;
    draftPositionInFirstRoundValidator = new DraftPositionInFirstRoundValidator(numPoolees);
    draftPicksByPoolee = Maps.newHashMapWithExpectedSize(numPoolees);

    final int totalNumDraftPicks = getTotalNumDraftPicksUponCompletion();
    draftedPlayers = Sets.newHashSetWithExpectedSize(totalNumDraftPicks);
    draftPicks = Lists.newArrayListWithCapacity(totalNumDraftPicks);

    // We add null poolees to the first round draft order. (The null values
    // get overwritten when the addPoolee method is called.)
    //
    firstRoundDraftOrder = Lists.newArrayList(Collections.<Poolee> nCopies(numPoolees, null));
  }

  private int getTotalNumDraftPicksUponCompletion()
  {
    return numPoolees * numRounds;
  }

  /** {@inheritDoc} */
  public SeasonType getSeasonType()
  {
    return seasonType;
  }

  /** {@inheritDoc} */
  public int getNumRounds()
  {
    return numRounds;
  }

  /** {@inheritDoc} */
  public int getNumPoolees()
  {
    return numPoolees;
  }

  /** {@inheritDoc} */
  public int getRoundNumOfNextDraftPick()
  {
    assertDraftStateIsNot(DraftState.COMPLETED);

    return (1 + (getNumPlayersAlreadyDrafted() / numPoolees));
  }

  private void assertDraftStateIsNot(final DraftState illegalDraftState)
  {
    if (draftState == illegalDraftState)
    {
      throw new IllegalStateException("Illegal draft state:" + draftState);
    }
  }

  /** {@inheritDoc} */
  public void addPoolee(final Poolee poolee, final int draftPositionInFirstRound)
  {
    ArgAssert.assertNotNull(poolee, "poolee");
    assertDraftStateIs(DraftState.NOT_STARTED);
    ArgAssert.assertTrue(draftPositionInFirstRoundValidator.isValid(draftPositionInFirstRound),
          "draftPositionInFirstRound is valid");

    // We have to shift the draft position by 1 to get a list index because
    // draft positions start at 1 and indices start at 0.
    //
    firstRoundDraftOrder.set(draftPositionInFirstRound - 1, poolee);
  }

  private void assertDraftStateIs(final DraftState expectedDraftState)
  {
    if (draftState != expectedDraftState)
    {
      throw new IllegalStateException("Illegal draft state:" + draftState + " (expected " + expectedDraftState + ")");
    }
  }

  /** {@inheritDoc} */
  public void startDraft()
  {
    assertDraftStateIs(DraftState.NOT_STARTED);
    assertDraftOrderIsValid();

    draftOrder = draftOrderGetter.getDraftOrder(firstRoundDraftOrder, numRounds);
    draftState = DraftState.UNDERWAY;
  }

  private void assertDraftOrderIsValid()
  {
    assertAllDraftPositionsAssigned();
    assertNoPooleeHasDuplicateDraftPositions();
  }

  private void assertAllDraftPositionsAssigned()
  {
    if (firstRoundDraftOrder.contains(null))
    {
      throw new IllegalStateException("Not all draft positions assigned");
    }
  }

  private void assertNoPooleeHasDuplicateDraftPositions()
  {
    if ((Sets.newHashSet(firstRoundDraftOrder)).size() != numPoolees)
    {
      throw new IllegalStateException("Poolee has >1 draft positions");
    }
  }

  /** {@inheritDoc} */
  public List<Poolee> getFirstRoundDraftOrder()
  {
    // Return a defensive copy.
    //
    return Lists.newArrayList(firstRoundDraftOrder);
  }

  /** {@inheritDoc} */
  public DraftPick addDraftPick(final Player player, final Poolee poolee)
  {
    ArgAssert.assertNotNull(player, "player");
    ArgAssert.assertNotNull(poolee, "poolee");
    assertDraftStateIs(DraftState.UNDERWAY);
    validateDraftPick(player, poolee);

    final int roundNum = getRoundNumOfNextDraftPick();

    draftedPlayers.add(player);

    // Add this draft pick to the list of all draft picks.
    //
    final int pickNum = draftedPlayers.size();
    final DraftPick draftPick = new DraftPick(player, poolee, roundNum, pickNum);
    draftPicks.add(draftPick);

    // Add this draft pick to the list of draft picks of this poolee.
    //
    final List<DraftPick> draftPicksByThisPoolee;
    if (draftPicksByPoolee.containsKey(poolee))
    {
      draftPicksByThisPoolee = draftPicksByPoolee.get(poolee);
    }
    else
    {
      draftPicksByThisPoolee = Lists.newArrayListWithCapacity(numRounds);
      draftPicksByPoolee.put(poolee, draftPicksByThisPoolee);
    }
    draftPicksByThisPoolee.add(draftPick);

    if (isDraftOver())
    {
      draftState = DraftState.COMPLETED;
    }

    return draftPick;
  }

  private void validateDraftPick(final Player draftedPlayer, final Poolee poolee)
  {
    assertPlayerIsKnown(draftedPlayer);
    assertPooleeIsNextToDraft(poolee);
    assertPooleeAllowedToDraftPlayer(poolee, draftedPlayer);
    assertPlayerNotAlreadyDrafted(draftedPlayer);
  }

  private void assertPlayerIsKnown(final Player player)
  {
    final Set<Player> playersOnTeamAtPosition = playersByTeamAndPosition.getPlayersOnTeamAtPosition(player.getTeam(),
          player.getPosition());
    if (!playersOnTeamAtPosition.contains(player))
    {
      throw new IllegalArgumentException("Unknown player:" + player.getFullName());
    }
  }

  private void assertPooleeIsNextToDraft(final Poolee poolee)
  {
    if (!poolee.equals(getNextPooleeToDraft()))
    {
      throw new IllegalArgumentException(poolee.getFullName() + " is drafting out of position");
    }
  }

  private void assertPooleeAllowedToDraftPlayer(final Poolee poolee, final Player player)
  {
    if (seasonType == SeasonType.REGULAR_SEASON)
    {
      final List<DraftPick> draftPicksByPooleeOnTeam = getDraftPicksOfPooleeFromTeam(poolee, player.getTeam());
      if (!draftPicksByPooleeOnTeam.isEmpty())
      {
        final Player alreadyDraftedPlayer = draftPicksByPooleeOnTeam.get(0).getPlayer();
        throw new IllegalArgumentException(poolee.getFullName() + " has already picked " +
              alreadyDraftedPlayer.getFullName() + " from " + player.getTeam());
      }
    }
  }

  private void assertPlayerNotAlreadyDrafted(final Player player)
  {
    if (draftedPlayers.contains(player))
    {
      throw new IllegalArgumentException(player.getFullName() + " has already been drafted");
    }
  }

  /** {@inheritDoc} */
  public boolean isDraftOver()
  {
    if (draftState == DraftState.NOT_STARTED)
    {
      return false;
    }
    return (getNumPlayersAlreadyDrafted() >= getTotalNumDraftPicksUponCompletion());
  }

  /** {@inheritDoc} */
  public int getNextDraftPickNum()
  {
    assertDraftStateIsNot(DraftState.COMPLETED);

    return getNumPlayersAlreadyDrafted() + 1;
  }

  /** {@inheritDoc} */
  public Poolee getNextPooleeToDraft()
  {
    assertDraftStateIs(DraftState.UNDERWAY);

    return draftOrder.get(getNumPlayersAlreadyDrafted());
  }

  private int getNumPlayersAlreadyDrafted()
  {
    return draftedPlayers.size();
  }

  /** {@inheritDoc} */
  public int getNumConsecutivePicksForNextPooleeToDraft()
  {
    assertDraftStateIs(DraftState.UNDERWAY);

    final Poolee nextPooleeToDraft = getNextPooleeToDraft();

    int numConsecutivePicksForNextPooleeToDraft = 0;
    int draftOrderIndex = draftedPlayers.size();
    while ((draftOrderIndex < draftOrder.size()) && (draftOrder.get(draftOrderIndex).equals(nextPooleeToDraft)))
    {
      draftOrderIndex++;
      numConsecutivePicksForNextPooleeToDraft++;
    }

    return numConsecutivePicksForNextPooleeToDraft;
  }

  /** {@inheritDoc} */
  public Set<Team> getTeamsAvailableToDraftFrom(final Poolee poolee)
  {
    ArgAssert.assertNotNull(poolee, "poolee");

    // We assume that every team is available to draft from, then remove
    // teams as necessary.
    //
    final Set<Team> teamsAvailableToDraftFrom = EnumSet.copyOf(playersByTeamAndPosition.getTeams());

    if (seasonType == SeasonType.REGULAR_SEASON)
    {
      // We remove the teams this poolee has already drafted from.
      //
      for (final DraftPick draftPick : getDraftPicksOfPoolee(poolee))
      {
        teamsAvailableToDraftFrom.remove(draftPick.getPlayer().getTeam());
      }
    }
    else
    {
      // For a playoff pool, we do not need to remove any teams because
      // there are no restrictions on how many players a poolee can take
      // from any one team.
    }

    return teamsAvailableToDraftFrom;
  }

  /** {@inheritDoc} */
  public boolean isPlayerDrafted(final Player player)
  {
    ArgAssert.assertNotNull(player, "player");

    return draftedPlayers.contains(player);
  }

  /** {@inheritDoc} */
  public List<DraftPick> getDraftPicks()
  {
    // Return a defensive copy.
    //
    return Lists.newArrayList(draftPicks);
  }

  /** {@inheritDoc} */
  public List<DraftPick> getDraftPicksOfPoolee(final Poolee poolee)
  {
    ArgAssert.assertNotNull(poolee, "poolee");

    if (draftPicksByPoolee.containsKey(poolee))
    {
      // Return a defensive copy.
      //
      return Lists.newArrayList(draftPicksByPoolee.get(poolee));
    }
    else
    {
      return Collections.emptyList();
    }
  }

  /** {@inheritDoc} */
  public List<DraftPick> getDraftPicksOfPooleeFromTeam(final Poolee poolee, final Team team)
  {
    ArgAssert.assertNotNull(poolee, "poolee");
    ArgAssert.assertNotNull(team, "team");

    final List<DraftPick> draftPicksOfPooleeFromTeam = Lists.newArrayList();

    for (final DraftPick draftPick : getDraftPicksOfPoolee(poolee))
    {
      if (draftPick.getPlayer().getTeam() == team)
      {
        draftPicksOfPooleeFromTeam.add(draftPick);
      }
    }

    return draftPicksOfPooleeFromTeam;
  }

  /** {@inheritDoc} */
  public List<DraftPick> getDraftPicksOfPooleeAtPosition(final Poolee poolee, final Position position)
  {
    ArgAssert.assertNotNull(poolee, "poolee");
    ArgAssert.assertNotNull(position, "position");

    final List<DraftPick> draftPicksOfPooleeAtPosition = Lists.newArrayList();

    for (final DraftPick draftPick : getDraftPicksOfPoolee(poolee))
    {
      if (draftPick.getPlayer().getPosition() == position)
      {
        draftPicksOfPooleeAtPosition.add(draftPick);
      }
    }

    return draftPicksOfPooleeAtPosition;
  }

  /** {@inheritDoc} */
  public List<DraftPick> getDraftPicksOnTeam(final Team team)
  {
    ArgAssert.assertNotNull(team, "team");

    final List<DraftPick> draftPicksOnTeam = Lists.newArrayList();

    for (final DraftPick draftPick : draftPicks)
    {
      if (draftPick.getPlayer().getTeam() == team)
      {
        draftPicksOnTeam.add(draftPick);
      }
    }

    return draftPicksOnTeam;
  }

  /** {@inheritDoc} */
  public List<DraftPick> getMostRecentNDraftPicks(final int numDraftPicksToReturn)
  {
    ArgAssert.assertNotNegative(numDraftPicksToReturn, "numDraftPicksToReturn");
    assertDraftStateIsNot(DraftState.NOT_STARTED);

    final int fromIndex;
    if (numDraftPicksToReturn >= draftPicks.size())
    {
      // The caller is requesting more draft picks than we have thus far.
      // Therefore, we need to return the entire list.
      //
      fromIndex = 0;
    }
    else
    {
      fromIndex = draftPicks.size() - numDraftPicksToReturn;
    }

    final int toIndex = draftPicks.size();

    // We create a new list so that reversing it doesn't reverse the elements
    // in our private member.
    //
    final List<DraftPick> mostRecentNDraftPicks = Lists.newArrayList(draftPicks.subList(fromIndex, toIndex));
    Collections.reverse(mostRecentNDraftPicks);

    return mostRecentNDraftPicks;
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(final Object thatObject)
  {
    if (this == thatObject)
    {
      return true;
    }
    else if (thatObject.getClass() != getClass())
    {
      return false;
    }
    else
    {
      final DraftImpl that = (DraftImpl) thatObject;
      return new EqualsBuilder().append(seasonType, that.seasonType).append(numRounds, that.numRounds)
            .append(firstRoundDraftOrder, that.firstRoundDraftOrder).append(draftPicks, that.draftPicks).isEquals();
    }
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode()
  {
    return new HashCodeBuilder().append(seasonType).append(numRounds).append(firstRoundDraftOrder).append(draftPicks)
          .toHashCode();
  }
}
