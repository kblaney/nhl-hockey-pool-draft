package com.kblaney.nhl.draft;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import com.kblaney.nhl.PlayersByTeamAndPosition;
import org.junit.Before;
import org.junit.Test;

public final class DraftImplConstructorTest
{
  private PlayersByTeamAndPosition playersByTeamAndPosition;
  private SeasonType arbitrarySeasonType;
  private int arbitraryNumRounds;
  private int arbitraryNumPoolees;
  private DraftOrderGetter arbitraryDraftOrderGetter;

  @Before
  public void setUp() throws Exception
  {
    arbitrarySeasonType = SeasonType.REGULAR_SEASON;
    playersByTeamAndPosition = mock(PlayersByTeamAndPosition.class);
    arbitraryNumRounds = 3;
    arbitraryNumPoolees = 4;
    arbitraryDraftOrderGetter = mock(DraftOrderGetter.class);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructor_nullSeasonType()
  {
    new DraftImpl(null, playersByTeamAndPosition, arbitraryNumRounds, arbitraryNumPoolees, arbitraryDraftOrderGetter);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructor_nullPlayersByTeamAndPosition()
  {
    new DraftImpl(arbitrarySeasonType, null, arbitraryNumRounds, arbitraryNumPoolees, arbitraryDraftOrderGetter);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructor_nullDraftOrderGetter()
  {
    new DraftImpl(arbitrarySeasonType, playersByTeamAndPosition, arbitraryNumRounds, arbitraryNumPoolees, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructor_invalidNumRounds()
  {
    final int arbitraryInvalidNumRounds = 0;
    new DraftImpl(arbitrarySeasonType, playersByTeamAndPosition, arbitraryInvalidNumRounds, arbitraryNumPoolees,
          arbitraryDraftOrderGetter);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructor_invalidNumPoolees()
  {
    final int arbitraryInvalidNumPooleess = 0;
    new DraftImpl(arbitrarySeasonType, playersByTeamAndPosition, arbitraryNumRounds, arbitraryInvalidNumPooleess,
          arbitraryDraftOrderGetter);
  }

  @Test
  public void constructor_allParametersValid()
  {
    final Draft draft = new DraftImpl(arbitrarySeasonType, playersByTeamAndPosition, arbitraryNumRounds,
          arbitraryNumPoolees, arbitraryDraftOrderGetter);
    assertEquals(arbitrarySeasonType, draft.getSeasonType());
    assertEquals(arbitraryNumRounds, draft.getNumRounds());
    assertEquals(arbitraryNumPoolees, draft.getNumPoolees());
    assertEquals(1, draft.getRoundNumOfNextDraftPick());
  }

  @Test
  public void equals_sameInstance()
  {
    final Draft arbitraryDraft = Drafts.completedDraft();
    assertEquals(arbitraryDraft, arbitraryDraft);
  }

  @Test
  public void equals_null()
  {
    final Draft arbitraryDraft = Drafts.completedDraft();
    assertFalse(arbitraryDraft.equals(null));
  }

  @Test
  public void equals_wrongType()
  {
    final Draft arbitraryDraft = Drafts.completedDraft();
    final Object arbitraryObjectOfWrongType = "A";
    assertFalse(arbitraryDraft.equals(arbitraryObjectOfWrongType));
  }

  @Test
  public void hashCode_equalInstances()
  {
    final Draft arbitraryDraft = Drafts.completedDraft();
    final Draft equalInstance = Drafts.completedDraft();
    assertEquals(arbitraryDraft.hashCode(), equalInstance.hashCode());
  }
}
