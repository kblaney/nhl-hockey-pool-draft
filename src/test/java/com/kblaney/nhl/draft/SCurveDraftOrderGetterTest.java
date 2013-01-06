package com.kblaney.nhl.draft;

import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class SCurveDraftOrderGetterTest
{
  private DraftOrderGetter draftOrderGetter;
  private List<Poolee> firstRoundDraftOrder;
  private List<Poolee> expectedDraftOrder;
  private int numRounds;

  @Before
  public void setUp()
  {
    draftOrderGetter = new SCurveDraftOrderGetter();
    firstRoundDraftOrder = new ArrayList<Poolee>();

    final Poolee firstPoolee = new Poolee("Kyle", "Blaney");
    final Poolee secondPoolee = new Poolee("Rob", "Hodgins");
    final Poolee thirdPoolee = new Poolee("Dave", "Woods");
    final Poolee fourthPoolee = new Poolee("Brad", "Hamilton");

    firstRoundDraftOrder.add(firstPoolee);
    firstRoundDraftOrder.add(secondPoolee);
    firstRoundDraftOrder.add(thirdPoolee);
    firstRoundDraftOrder.add(fourthPoolee);
    numRounds = 4;

    expectedDraftOrder = new ArrayList<Poolee>();
    expectedDraftOrder.add(firstPoolee);
    expectedDraftOrder.add(secondPoolee);
    expectedDraftOrder.add(thirdPoolee);
    expectedDraftOrder.add(fourthPoolee);
    expectedDraftOrder.add(fourthPoolee);
    expectedDraftOrder.add(thirdPoolee);
    expectedDraftOrder.add(secondPoolee);
    expectedDraftOrder.add(firstPoolee);
    expectedDraftOrder.add(firstPoolee);
    expectedDraftOrder.add(secondPoolee);
    expectedDraftOrder.add(thirdPoolee);
    expectedDraftOrder.add(fourthPoolee);
    expectedDraftOrder.add(fourthPoolee);
    expectedDraftOrder.add(thirdPoolee);
    expectedDraftOrder.add(secondPoolee);
    expectedDraftOrder.add(firstPoolee);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getDraftOrderNullFirstRoundDraftOrder()
  {
    draftOrderGetter.getDraftOrder(null, numRounds);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getDraftOrderNumRoundsTooSmall()
  {
    draftOrderGetter.getDraftOrder(firstRoundDraftOrder, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void getDraftOrderNumPooleesTooSmall()
  {
    firstRoundDraftOrder = new ArrayList<Poolee>();
    firstRoundDraftOrder.add(new Poolee("A", "B"));
    draftOrderGetter.getDraftOrder(firstRoundDraftOrder, numRounds);
  }

  @Test
  public void getDraftOrder()
  {
    assertEquals(expectedDraftOrder, draftOrderGetter.getDraftOrder(firstRoundDraftOrder, numRounds));
  }
}
