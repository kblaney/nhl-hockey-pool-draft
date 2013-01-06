package com.kblaney.nhl.draft;

import com.kblaney.nhl.Player;
import com.kblaney.nhl.Position;
import com.kblaney.nhl.Team;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class DraftImplStartedDraftWithDoublePickUpNextTest
{
  private Draft draft;

  @Before
  public void setUp()
  {
    draft = Drafts.startedDraftDoublePickUpNext();
  }

  @Test
  public void getNextPooleeToDraft()
  {
    assertEquals(Drafts.THIRD_POOLEE, draft.getNextPooleeToDraft());
  }

  @Test
  public void getNumConsecutivePicksForNextPooleeToDraft()
  {
    assertEquals(2, draft.getNumConsecutivePicksForNextPooleeToDraft());
  }

  @Test
  public void getNextDraftPickNum()
  {
    assertEquals(Drafts.NUM_POOLEES, draft.getNextDraftPickNum());
  }

  @Test
  public void getRoundNumOfNextDraftPick()
  {
    assertEquals(1, draft.getRoundNumOfNextDraftPick());
  }

  @Test(expected = IllegalArgumentException.class)
  public void addDraftPick_pooleeDraftingTwiceFromSameTeamInRegularSeason()
  {
    final Player firstPlayer = new Player("Pavel", "Datsyuk", Team.DETROIT_RED_WINGS, Position.FORWARD);
    final Player secondPlayer = new Player("Henrik", "Zetterberg", Team.DETROIT_RED_WINGS, Position.FORWARD);
    draft.addDraftPick(firstPlayer, Drafts.THIRD_POOLEE);
    draft.addDraftPick(secondPlayer, Drafts.THIRD_POOLEE);
  }
}
