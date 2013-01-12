package com.kblaney.nhl.draft;

import com.kblaney.nhl.Position;
import com.kblaney.nhl.Player;
import com.kblaney.nhl.Team;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class DraftImplNotStartedDraftWithNoPooleesTest
{
  private Draft draft;
  private Player player;
  private Poolee poolee;
  private DraftPick draftPick;

  @Before
  public void setUp()
  {
    draft = Drafts.notStartedDraftWithNoPoolees();
    player = new Player("Steve", "Yzerman", Team.DETROIT_RED_WINGS, Position.FORWARD);
    poolee = Drafts.FIRST_POOLEE;
  }

  @Test(expected = IllegalArgumentException.class)
  public void addPoolee_nullPoolee()
  {
    draft.addPoolee(null, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addPoolee_draftPositionZero()
  {
    draft.addPoolee(Drafts.FIRST_POOLEE, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addPoolee_negativeDraftPosition()
  {
    draft.addPoolee(Drafts.FIRST_POOLEE, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void addPoolee_draftPositionTooLarge()
  {
    draft.addPoolee(Drafts.FIRST_POOLEE, Drafts.NUM_POOLEES + 1);
  }

  @Test
  public void addPoolee_samePooleeTwice()
  {
    draft.addPoolee(Drafts.FIRST_POOLEE, 1);
    draft.addPoolee(Drafts.FIRST_POOLEE, 2);
    assertTrue(true);
  }

  @Test
  public void addPoolee_sameDraftPosition()
  {
    draft.addPoolee(new Poolee("Joe", "Blow"), 1);
    assertTrue(true);
  }

  @Test(expected = IllegalStateException.class)
  public void startDraft_duplicateDraftPositions()
  {
    for (int i = 1; i <= Drafts.NUM_POOLEES; i++)
    {
      draft.addPoolee(Drafts.FIRST_POOLEE, i);
    }
    draft.startDraft();
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

  @Test(expected = IllegalStateException.class)
  public void getNextPooleeToDraft()
  {
    draft.getNextPooleeToDraft();
  }

  @Test(expected = IllegalStateException.class)
  public void getNumConsecutivePicksForNextPooleeToDraft()
  {
    draft.getNumConsecutivePicksForNextPooleeToDraft();
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

  @Test(expected = IllegalStateException.class)
  public void addDraftPick()
  {
    draft.addDraftPick(player, poolee);
  }

  @Test
  public void getFirstRoundDraftOrder()
  {
    final List<Poolee> firstRoundDraftOrder = draft.getFirstRoundDraftOrder();
    final List<Poolee> expectedFirstRoundDraftOrder = new ArrayList<Poolee>();
    for (int i = 0; i < Drafts.NUM_POOLEES; i++)
    {
      expectedFirstRoundDraftOrder.add(null);
    }

    assertTrue(firstRoundDraftOrder.equals(expectedFirstRoundDraftOrder));
  }

  @Test
  public void getFirstRoundDraftOrder_defensiveCopy()
  {
    final List<Poolee> firstRoundDraftOrder = draft.getFirstRoundDraftOrder();
    firstRoundDraftOrder.set(0, Drafts.FIRST_POOLEE);
    assertFalse(draft.getFirstRoundDraftOrder().contains(Drafts.FIRST_POOLEE));
  }

  @Test(expected = IllegalArgumentException.class)
  public void isPlayerDrafted_nullPlayer()
  {
    draft.isPlayerDrafted(null);
  }

  @Test
  public void isPlayerDrafted()
  {
    assertFalse(draft.isPlayerDrafted(new Player("Steve", "Yzerman", Team.DETROIT_RED_WINGS, Position.FORWARD)));
  }

  @Test
  public void getDraftPicks()
  {
    assertTrue(draft.getDraftPicks().isEmpty());
  }

  @Test
  public void getDraftPicks_defensiveCopy()
  {
    final List<DraftPick> draftPicks = draft.getDraftPicks();
    draftPicks.add(draftPick);
    assertFalse(draft.getDraftPicks().contains(draftPick));
  }

  @Test
  public void getTeamsAvailableToDraftFrom()
  {
    assertEquals(EnumSet.allOf(Team.class), draft.getTeamsAvailableToDraftFrom(Drafts.FIRST_POOLEE));
  }

  @Test
  public void getTeamsAvailableToDraftFrom_defensiveCopy()
  {
    final Set<Team> teams = draft.getTeamsAvailableToDraftFrom(Drafts.FIRST_POOLEE);
    teams.clear();
    getTeamsAvailableToDraftFrom();
  }

  @Test(expected = IllegalArgumentException.class)
  public void getDraftPicksOfPooleeFromTeam_nullPoolee()
  {
    draft.getDraftPicksOfPooleeFromTeam(null, Team.ANAHEIM_DUCKS);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getDraftPicksOfPooleeFromTeam_nullTeam()
  {
    draft.getDraftPicksOfPooleeFromTeam(poolee, null);
  }

  @Test
  public void getPlayerDraftedByPooleeOnTeam()
  {
    for (final Team team : EnumSet.allOf(Team.class))
    {
      assertTrue(draft.getDraftPicksOfPooleeFromTeam(poolee, team).isEmpty());
    }
  }

  @Test(expected = IllegalArgumentException.class)
  public void getDraftPicksOfPooleeAtPosition_nullPoolee()
  {
    draft.getDraftPicksOfPooleeAtPosition(null, Position.FORWARD);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getDraftPicksOfPooleeAtPosition_nullPosition()
  {
    draft.getDraftPicksOfPooleeAtPosition(poolee, null);
  }

  @Test
  public void getForwardsDraftedByPoolee()
  {
    assertTrue(draft.getDraftPicksOfPooleeAtPosition(poolee, Position.FORWARD).isEmpty());
  }

  @Test
  public void getForwardsDraftedByPoolee_defensiveCopy()
  {
    final List<DraftPick> forwardDraftPicks = draft.getDraftPicksOfPooleeAtPosition(poolee, Position.FORWARD);
    forwardDraftPicks.add(new DraftPick(new Player("Mike", "Ribeiro", Team.MONTREAL_CANADIENS, Position.FORWARD),
          poolee, 2, 17));
    getForwardsDraftedByPoolee();
  }

  @Test
  public void getDefensemenDraftedByPoolee()
  {
    assertTrue(draft.getDraftPicksOfPooleeAtPosition(poolee, Position.DEFENSEMAN).isEmpty());
  }

  @Test
  public void getDefensemenDraftedByPoolee_defensiveCopy()
  {
    final List<DraftPick> defensemenDraftPicks = draft.getDraftPicksOfPooleeAtPosition(poolee, Position.DEFENSEMAN);
    defensemenDraftPicks.add(new DraftPick(
          new Player("Sheldon", "Souray", Team.MONTREAL_CANADIENS, Position.DEFENSEMAN), poolee, 2, 17));
    getDefensemenDraftedByPoolee();
  }

  @Test
  public void getGoaliesDraftedByPoolee()
  {
    assertTrue(draft.getDraftPicksOfPooleeAtPosition(poolee, Position.GOALIE).isEmpty());
  }

  @Test
  public void getGoaliesDraftedByPoolee_defensiveCopy()
  {
    final List<DraftPick> goalieDraftPicks = draft.getDraftPicksOfPooleeAtPosition(poolee, Position.GOALIE);
    goalieDraftPicks.add(new DraftPick(new Player("Jose", "Theodore", Team.MONTREAL_CANADIENS, Position.GOALIE),
          poolee, 2, 17));
    getGoaliesDraftedByPoolee();
  }
}
