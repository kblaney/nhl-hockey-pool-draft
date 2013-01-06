package com.kblaney.nhl.draft;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public final class DraftImplNotStartedDraftWithOnePooleeTest
{
  @Test
  public void getFirstRoundDraftOrder()
  {
    final Draft draft = Drafts.notStartedDraftWithOnePoolee();
    final List<Poolee> firstRoundDraftOrder = draft.getFirstRoundDraftOrder();
    final List<Poolee> expectedFirstRoundDraftOrder = new ArrayList<Poolee>();
    expectedFirstRoundDraftOrder.add(Drafts.FIRST_POOLEE);
    for (int i = 1; i < Drafts.NUM_POOLEES; i++)
    {
      expectedFirstRoundDraftOrder.add(null);
    }

    assertTrue(firstRoundDraftOrder.equals(expectedFirstRoundDraftOrder));
  }
}
