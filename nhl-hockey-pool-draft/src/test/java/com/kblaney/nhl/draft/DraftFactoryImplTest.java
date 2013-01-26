package com.kblaney.nhl.draft;

import static org.junit.Assert.*;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import com.google.common.collect.Lists;
import com.kblaney.nhl.Player;
import com.kblaney.nhl.PlayersByTeamAndPosition;
import com.kblaney.nhl.Position;
import com.kblaney.nhl.Team;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;

public final class DraftFactoryImplTest
{
  private SeasonType arbitrarySeasonType;
  private PlayersByTeamAndPosition arbitraryPlayersByTeamAndPosition;
  private int arbitraryNumRounds;
  private int arbitraryNumPoolees;
  private DraftOrderGetter arbitraryDraftOrderGetter;
  private List<Poolee> arbitraryFirstRoundDraftOrder;
  private List<DraftPick> arbitraryDraftPicks;
  private DraftFactory draftFactory;

  @Before
  @SuppressWarnings("unchecked")
  public void setUp()
  {
    arbitrarySeasonType = SeasonType.PLAYOFF;
    arbitraryPlayersByTeamAndPosition = mock(PlayersByTeamAndPosition.class);
    arbitraryNumRounds = 10;
    arbitraryNumPoolees = 6;
    arbitraryDraftOrderGetter = mock(DraftOrderGetter.class);
    arbitraryFirstRoundDraftOrder = mock(List.class);
    arbitraryDraftPicks = mock(List.class);
    draftFactory = new DraftFactoryImpl();
  }

  @Test(expected = IllegalArgumentException.class)
  public void createNewDraft_nullSeasonType()
  {
    draftFactory.createNewDraft(null, arbitraryPlayersByTeamAndPosition, arbitraryNumRounds, arbitraryNumPoolees,
          arbitraryDraftOrderGetter);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createNewDraft_nullPlayersByTeamAndPosition()
  {
    draftFactory.createNewDraft(arbitrarySeasonType, null, arbitraryNumRounds, arbitraryNumPoolees,
          arbitraryDraftOrderGetter);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createNewDraft_invalidNumRounds()
  {
    final int arbitraryInvalidNumRounds = -14;
    draftFactory.createNewDraft(arbitrarySeasonType, arbitraryPlayersByTeamAndPosition, arbitraryInvalidNumRounds,
          arbitraryNumPoolees, arbitraryDraftOrderGetter);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createNewDraft_invalidNumPoolees()
  {
    final int arbitraryInvalidNumPoolees = 0;
    draftFactory.createNewDraft(arbitrarySeasonType, arbitraryPlayersByTeamAndPosition, arbitraryNumRounds,
          arbitraryInvalidNumPoolees, arbitraryDraftOrderGetter);
  }

  @Test(expected = IllegalArgumentException.class)
  public void createNewDraft_nullDraftOrderGetter()
  {
    draftFactory.createNewDraft(arbitrarySeasonType, arbitraryPlayersByTeamAndPosition, arbitraryNumRounds,
          arbitraryNumPoolees, null);
  }

  @Test
  public void createNewDraft()
  {
    final Draft draft = draftFactory.createNewDraft(arbitrarySeasonType, arbitraryPlayersByTeamAndPosition,
          arbitraryNumRounds, arbitraryNumPoolees, arbitraryDraftOrderGetter);
    assertTrue(draft.getDraftPicks().isEmpty());
    assertEquals(1, draft.getNextDraftPickNum());
  }

  @Test(expected = IllegalArgumentException.class)
  public void resumeDraft_nullSeasonType()
  {
    draftFactory.resumeDraft(null, arbitraryPlayersByTeamAndPosition, arbitraryNumRounds, arbitraryNumPoolees,
          arbitraryDraftOrderGetter, arbitraryFirstRoundDraftOrder, arbitraryDraftPicks);
  }

  @Test(expected = IllegalArgumentException.class)
  public void resumeDraft_nullPlayersByTeamAndPosition()
  {
    draftFactory.resumeDraft(arbitrarySeasonType, null, arbitraryNumRounds, arbitraryNumPoolees,
          arbitraryDraftOrderGetter, arbitraryFirstRoundDraftOrder, arbitraryDraftPicks);
  }

  @Test(expected = IllegalArgumentException.class)
  public void resumeDraft_invalidNumRounds()
  {
    final int arbitraryInvalidNumRounds = 0;
    draftFactory.resumeDraft(arbitrarySeasonType, arbitraryPlayersByTeamAndPosition, arbitraryInvalidNumRounds,
          arbitraryNumPoolees, arbitraryDraftOrderGetter, arbitraryFirstRoundDraftOrder, arbitraryDraftPicks);
  }

  @Test(expected = IllegalArgumentException.class)
  public void resumeDraft_invalidNumPoolees()
  {
    final int arbitraryInvalidNumPoolees = -9;
    draftFactory.resumeDraft(arbitrarySeasonType, arbitraryPlayersByTeamAndPosition, arbitraryNumRounds,
          arbitraryInvalidNumPoolees, arbitraryDraftOrderGetter, arbitraryFirstRoundDraftOrder, arbitraryDraftPicks);
  }

  @Test(expected = IllegalArgumentException.class)
  public void resumeDraft_nullDraftOrderGetter()
  {
    draftFactory.resumeDraft(arbitrarySeasonType, arbitraryPlayersByTeamAndPosition, arbitraryNumRounds,
          arbitraryNumPoolees, null, arbitraryFirstRoundDraftOrder, arbitraryDraftPicks);
  }

  @Test(expected = IllegalArgumentException.class)
  public void resumeDraft_nullFirstRoundDraftOrder()
  {
    draftFactory.resumeDraft(arbitrarySeasonType, arbitraryPlayersByTeamAndPosition, arbitraryNumRounds,
          arbitraryNumPoolees, arbitraryDraftOrderGetter, null, arbitraryDraftPicks);
  }

  @Test(expected = IllegalArgumentException.class)
  public void resumeDraft_nullDraftPicks()
  {
    draftFactory.resumeDraft(arbitrarySeasonType, arbitraryPlayersByTeamAndPosition, arbitraryNumRounds,
          arbitraryNumPoolees, arbitraryDraftOrderGetter, arbitraryFirstRoundDraftOrder, null);
  }

  @Test
  public void resumeDraft_noPooleesAndNoDraftPicks()
  {
    final List<Poolee> firstRoundDraftOrder = Collections.nCopies(arbitraryNumPoolees, null);
    final List<DraftPick> draftPicks = Collections.emptyList();
    final Draft draft = draftFactory.resumeDraft(arbitrarySeasonType, arbitraryPlayersByTeamAndPosition,
          arbitraryNumRounds, arbitraryNumPoolees, arbitraryDraftOrderGetter, firstRoundDraftOrder, draftPicks);

    assertEquals(arbitrarySeasonType, draft.getSeasonType());
    assertEquals(arbitraryNumRounds, draft.getNumRounds());
    assertEquals(arbitraryNumPoolees, draft.getNumPoolees());
    assertEquals(firstRoundDraftOrder, draft.getFirstRoundDraftOrder());
    assertEquals(draftPicks, draft.getDraftPicks());
    assertFalse(draft.isDraftOver());
  }

  @Test
  public void resumeDraft_allPooleesAndNoDraftPicks()
  {
    final int numPoolees = 3;
    final List<Poolee> firstRoundDraftOrder = Lists.newArrayList(new Poolee("FIRST", "POOLEE"), new Poolee("SECOND",
          "POOLEE"), new Poolee("THIRD", "POOLEE"));
    final List<DraftPick> draftPicks = Collections.emptyList();
    final Draft draft = draftFactory.resumeDraft(arbitrarySeasonType, arbitraryPlayersByTeamAndPosition,
          arbitraryNumRounds, numPoolees, arbitraryDraftOrderGetter, firstRoundDraftOrder, draftPicks);

    assertEquals(arbitrarySeasonType, draft.getSeasonType());
    assertEquals(arbitraryNumRounds, draft.getNumRounds());
    assertEquals(numPoolees, draft.getNumPoolees());
    assertEquals(firstRoundDraftOrder, draft.getFirstRoundDraftOrder());
    assertEquals(draftPicks, draft.getDraftPicks());
    assertFalse(draft.isDraftOver());
  }

  @Test
  public void resumeDraft_allPooleesAndOneDraftPick()
  {
    final int numPoolees = 2;
    final Poolee firstPoolee = new Poolee("FIRST", "POOLEE");
    final List<Poolee> firstRoundDraftOrder = Lists.newArrayList(firstPoolee, new Poolee("SECOND", "POOLEE"));
    final List<DraftPick> draftPicks = getDraftPicksForOnlyOnePick(firstPoolee);
    final Draft draft = draftFactory.resumeDraft(arbitrarySeasonType, getPlayerSetThatContainsEveryPlayer(),
          arbitraryNumRounds, numPoolees, new SCurveDraftOrderGetter(), firstRoundDraftOrder, draftPicks);

    assertEquals(arbitrarySeasonType, draft.getSeasonType());
    assertEquals(arbitraryNumRounds, draft.getNumRounds());
    assertEquals(numPoolees, draft.getNumPoolees());
    assertEquals(firstRoundDraftOrder, draft.getFirstRoundDraftOrder());
    assertEquals(draftPicks, draft.getDraftPicks());
    assertFalse(draft.isDraftOver());
  }

  private PlayersByTeamAndPosition getPlayerSetThatContainsEveryPlayer()
  {
    @SuppressWarnings("unchecked")
    final Set<Player> setThatIndicatesThatItContainsEverything = mock(Set.class);
    when(setThatIndicatesThatItContainsEverything.contains(any(Player.class))).thenReturn(true);

    final PlayersByTeamAndPosition mockPlayersByTeamAndPosition = mock(PlayersByTeamAndPosition.class);
    when(mockPlayersByTeamAndPosition.getPlayersOnTeamAtPosition((Team) anyObject(), (Position) anyObject()))
          .thenReturn(setThatIndicatesThatItContainsEverything);

    return mockPlayersByTeamAndPosition;
  }

  private List<DraftPick> getDraftPicksForOnlyOnePick(final Poolee poolee)
  {
    final Player arbitraryDraftedPlayer = new Player("FIRST_NAME", "LAST_NAME", Team.WINNIPEG_JETS, Position.DEFENSEMAN);
    final int roundNum = 1;
    final int pickNum = 1;
    return Lists.newArrayList(new DraftPick(arbitraryDraftedPlayer, poolee, roundNum, pickNum));
  }
}
