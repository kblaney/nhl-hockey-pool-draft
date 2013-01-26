package com.kblaney.nhl.draft;

import static org.junit.Assert.*;
import com.kblaney.nhl.Position;
import com.kblaney.nhl.Team;
import java.util.EnumSet;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public final class DraftImplStartedDraftWithOneRoundCompletedTest
{
  private Draft draft;

  @Before
  public void setUp()
  {
    draft = Drafts.startedDraftOneRoundCompleted();
  }

  @Test
  public void isDraftOver()
  {
    assertFalse(draft.isDraftOver());
  }

  @Test
  public void getNextPooleeToDraft()
  {
    assertEquals(Drafts.THIRD_POOLEE, draft.getNextPooleeToDraft());
  }

  @Test
  public void getNumConsecutivePicksForNextPooleeToDraft()
  {
    assertEquals(1, draft.getNumConsecutivePicksForNextPooleeToDraft());
  }

  @Test
  public void getNextDraftPickNum()
  {
    assertEquals(Drafts.NUM_POOLEES + 1, draft.getNextDraftPickNum());
  }

  @Test
  public void getRoundNumOfNextDraftPick()
  {
    assertEquals(2, draft.getRoundNumOfNextDraftPick());
  }

  @Test
  public void getDraftPicks()
  {
    assertEquals(Drafts.NUM_POOLEES, draft.getDraftPicks().size());
  }

  @Test
  public void getTeamsAvailableToDraftFrom()
  {
    final Set<Team> allTeams = EnumSet.allOf(Team.class);
    final int expectedNumTeamsAvailable = allTeams.size() - 1;

    for (final Poolee poolee : Drafts.ALL_POOLEES)
    {
      assertEquals(expectedNumTeamsAvailable, draft.getTeamsAvailableToDraftFrom(poolee).size());
    }
  }

  @Test
  public void getDraftPicksOfPooleeAtPosition()
  {
    for (final Poolee poolee : Drafts.ALL_POOLEES)
    {
      assertEquals(1, draft.getDraftPicksOfPooleeAtPosition(poolee, Position.FORWARD).size() +
            draft.getDraftPicksOfPooleeAtPosition(poolee, Position.DEFENSEMAN).size() +
            draft.getDraftPicksOfPooleeAtPosition(poolee, Position.GOALIE).size());
    }
  }
}
