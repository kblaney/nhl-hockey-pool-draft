package com.kblaney.nhl.draft;

import com.kblaney.nhl.Position;
import com.kblaney.nhl.Team;
import java.util.EnumSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class DraftImplCompletedDraftTest
{
  private Draft draft;

  @Before
  public void setUp()
  {
    draft = Drafts.completedDraft();
  }

  @Test
  public void isDraftOver()
  {
    assertTrue(draft.isDraftOver());
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

  @Test(expected = IllegalStateException.class)
  public void getNextDraftPickNum()
  {
    draft.getNextDraftPickNum();
  }

  @Test(expected = IllegalStateException.class)
  public void getRoundNumOfNextDraftPick()
  {
    draft.getRoundNumOfNextDraftPick();
  }

  @Test
  public void getDraftPicks()
  {
    assertEquals(Drafts.NUM_POOLEES * Drafts.NUM_ROUNDS,
          draft.getDraftPicks().size());
  }

  @Test
  public void getTeamsAvailableToDraftFrom()
  {
    final Set<Team> allTeams = EnumSet.allOf(Team.class);
    final int expectedNumTeamsAvailable = allTeams.size() - Drafts.NUM_ROUNDS;

    for (final Poolee poolee : Drafts.ALL_POOLEES)
    {
      assertEquals(expectedNumTeamsAvailable,
            draft.getTeamsAvailableToDraftFrom(poolee).size());
    }
  }

  @Test
  public void getPlayers()
  {
    for (final Poolee poolee : Drafts.ALL_POOLEES)
    {
      assertEquals(Drafts.NUM_ROUNDS,
            draft.getDraftPicksOfPooleeAtPosition(
            poolee, Position.FORWARD).size() +
            draft.getDraftPicksOfPooleeAtPosition(
            poolee, Position.DEFENSEMAN).size() +
            draft.getDraftPicksOfPooleeAtPosition(
            poolee, Position.GOALIE).size());
    }
  }

  @Test
  public void getMostRecentNDraftPicks()
  {
    for (int i = 1; i <= 5; i++)
    {
      assertEquals(i, draft.getMostRecentNDraftPicks(i).size());
    }
  }
}
