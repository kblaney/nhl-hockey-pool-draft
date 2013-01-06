package com.kblaney.nhl.draft;

import com.google.common.collect.Lists;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class DraftImplNotStartedDraftWithAllPooleesTest
{
  private Draft draft;

  @Before
  public void setUp()
  {
    draft = Drafts.notStartedDraftWithAllPoolees();
  }

  @Test
  public void getFirstRoundDraftOrder()
  {
    final List<Poolee> expected = Lists.newArrayList(
          Drafts.FIRST_POOLEE, Drafts.SECOND_POOLEE, Drafts.THIRD_POOLEE);
    assertEquals(expected, draft.getFirstRoundDraftOrder());
  }

  @Test(expected = IllegalStateException.class)
  public void getMostRecentNDraftPicks()
  {
    final int arbitraryNumDraftPicks = 2;
    draft.getMostRecentNDraftPicks(arbitraryNumDraftPicks);
  }
}
