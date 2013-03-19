package com.kblaney.nhl.draft;

import static org.junit.Assert.*;
import com.google.common.collect.Lists;
import com.kblaney.nhl.Player;
import com.kblaney.nhl.Position;
import com.kblaney.nhl.Team;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

public final class DraftImplStartedDraftWithNoDraftPicksTest
{
  private Draft draft;
  private Player player;
  private Poolee poolee;

  @Before
  public void setUp()
  {
    draft = Drafts.startedDraftNoDraftPicks();
    player = new Player("Steve", "Yzerman", Team.DETROIT_RED_WINGS, Position.FORWARD);
    poolee = Drafts.FIRST_POOLEE;
  }

  @Test(expected = IllegalStateException.class)
  public void addPoolee()
  {
    draft.addPoolee(new Poolee("Joe", "Blow"), 1);
  }

  @Test(expected = IllegalStateException.class)
  public void startDraft()
  {
    draft.startDraft();
  }

  @Test
  public void getNumRounds()
  {
    assertEquals(Drafts.NUM_ROUNDS, draft.getNumRounds());
  }

  @Test
  public void getNumPoolees()
  {
    assertEquals(Drafts.NUM_POOLEES, draft.getNumPoolees());
  }

  @Test
  public void isDraftOver()
  {
    assertFalse(draft.isDraftOver());
  }

  @Test
  public void getNextPooleeToDraft()
  {
    assertEquals(poolee, draft.getNextPooleeToDraft());
  }

  @Test
  public void getNumConsecutivePicksForNextPooleeToDraft()
  {
    assertEquals(1, draft.getNumConsecutivePicksForNextPooleeToDraft());
  }

  @Test
  public void getNextDraftPickNum()
  {
    assertEquals(1, draft.getNextDraftPickNum());
  }

  @Test
  public void getRoundNumOfNextDraftPick()
  {
    assertEquals(1, draft.getRoundNumOfNextDraftPick());
  }

  @Test(expected = IllegalArgumentException.class)
  public void addDraftPick_unknownPlayer()
  {
    final Team arbitraryTeam = Team.CHICAGO_BLACKHAWKS;
    final Position arbitraryPosition = Position.DEFENSEMAN;
    final Player unknownPlayer = new Player("UNKNOWN_FIRST_NAME", "UNKNOWN_LAST_NAME", arbitraryTeam, arbitraryPosition);
    draft.addDraftPick(unknownPlayer, poolee);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addDraftPick_pooleeDraftingOutOfPosition()
  {
    final Poolee arbitraryWrongPoolee = Drafts.SECOND_POOLEE;
    draft.addDraftPick(player, arbitraryWrongPoolee);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addDraftPick_samePlayerDraftedTwice()
  {
    draft.addDraftPick(player, Drafts.FIRST_POOLEE);
    draft.addDraftPick(player, Drafts.SECOND_POOLEE);
  }

  @Test
  public void addDraftPick()
  {
    draft.addDraftPick(player, poolee);

    final int roundNum = 1;
    final int pickNum = 1;
    final List<DraftPick> singleton = Collections.singletonList(new DraftPick(player, poolee, roundNum, pickNum));
    assertEquals(singleton, draft.getDraftPicks());
    assertEquals(singleton, draft.getDraftPicksOfPoolee(poolee));
    assertEquals(singleton, draft.getDraftPicksOfPooleeAtPosition(poolee, player.getPosition()));
    assertEquals(singleton, draft.getDraftPicksOfPooleeFromTeam(poolee, player.getTeam()));
    assertEquals(singleton, draft.getDraftPicksOnTeam(player.getTeam()));
    assertEquals(singleton, draft.getMostRecentNDraftPicks(1));
    assertEquals(singleton, draft.getMostRecentNDraftPicks(2));

    final Team arbitraryOtherTeam = Team.ANAHEIM_DUCKS;
    assertTrue(draft.getDraftPicksOnTeam(arbitraryOtherTeam).isEmpty());
  }

  @Test
  public void getFirstRoundDraftOrder()
  {
    final List<Poolee> expected = Lists.newArrayList(Drafts.FIRST_POOLEE, Drafts.SECOND_POOLEE, Drafts.THIRD_POOLEE);
    assertEquals(expected, draft.getFirstRoundDraftOrder());
  }

  @Test
  public void isPlayerDrafted()
  {
    assertFalse(draft.isPlayerDrafted(player));
  }

  @Test
  public void getDraftPicks()
  {
    assertTrue(draft.getDraftPicks().isEmpty());
  }

  @Test
  public void getTeamsAvailableToDraftFrom()
  {
    assertTrue(draft.getTeamsAvailableToDraftFrom(poolee).equals(EnumSet.allOf(Team.class)));
  }

  @Test
  public void getDraftPicksOfPooleeFromTeam()
  {
    for (final Team team : Team.values())
    {
      assertTrue(draft.getDraftPicksOfPooleeFromTeam(poolee, team).isEmpty());
    }
  }

  @Test
  public void getForwardsDraftedByPoolee()
  {
    assertTrue(draft.getDraftPicksOfPooleeAtPosition(poolee, Position.FORWARD).isEmpty());
  }

  @Test
  public void getDefensemenDraftedByPoolee()
  {
    assertTrue(draft.getDraftPicksOfPooleeAtPosition(poolee, Position.DEFENSEMAN).isEmpty());
  }

  @Test
  public void getGoaliesDraftedByPoolee()
  {
    assertTrue(draft.getDraftPicksOfPooleeAtPosition(poolee, Position.GOALIE).isEmpty());
  }

  @Test(expected = IllegalArgumentException.class)
  public void getMostRecentNDraftPicks_negativeNumDraftPicks()
  {
    final int arbitraryNegativeNumDraftPicks = -1;
    draft.getMostRecentNDraftPicks(arbitraryNegativeNumDraftPicks);
  }

  @Test
  public void getMostRecentNDraftPicks()
  {
    final int arbitraryNumDraftPicks = 2;
    assertTrue(draft.getMostRecentNDraftPicks(arbitraryNumDraftPicks).isEmpty());
  }

  @Test(expected = IllegalStateException.class)
  public void undoLastDraftPick()
  {
    draft.undoLastDraftPick();
  }
}
