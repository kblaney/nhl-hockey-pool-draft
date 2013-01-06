package com.kblaney.nhl.draft;

import com.kblaney.nhl.Position;
import com.kblaney.nhl.Player;
import com.kblaney.nhl.Team;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class DraftPickTest
{
  private String arbitraryPlayerFirstName;
  private String arbitraryPlayerLastName;
  private Team arbitraryTeam;
  private Position arbitraryPosition;
  private Player arbitraryPlayer;
  private String arbitraryPooleeFirstName;
  private String arbitraryPooleeLastName;
  private Poolee arbitraryPoolee;
  private int arbitraryRoundNum;
  private int arbitraryPickNum;
  private DraftPick arbitraryDraftPick;

  @Before
  public void setUp()
  {
    arbitraryPlayerFirstName = "Jarome";
    arbitraryPlayerLastName = "Iginla";
    arbitraryTeam = Team.CALGARY_FLAMES;
    arbitraryPosition = Position.FORWARD;
    arbitraryPlayer = new Player(arbitraryPlayerFirstName,
          arbitraryPlayerLastName, arbitraryTeam, arbitraryPosition);
    arbitraryPooleeFirstName = "Kyle";
    arbitraryPooleeLastName = "Blaney";
    arbitraryPoolee = new Poolee(arbitraryPooleeFirstName,
          arbitraryPooleeLastName);
    arbitraryRoundNum = 2;
    arbitraryPickNum = 17;
    arbitraryDraftPick = new DraftPick(arbitraryPlayer, arbitraryPoolee,
          arbitraryRoundNum, arbitraryPickNum);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructor_nullPlayer()
  {
    new DraftPick(null, arbitraryPoolee, arbitraryRoundNum, arbitraryPickNum);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructor_nullPoolee()
  {
    new DraftPick(arbitraryPlayer, null, arbitraryRoundNum, arbitraryPickNum);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructor_invalidRoundNum()
  {
    final int arbitraryInvalidRoundNum = 0;
    new DraftPick(arbitraryPlayer, arbitraryPoolee, arbitraryInvalidRoundNum,
          arbitraryPickNum);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructor_invalidPickNum()
  {
    final int arbitraryInvalidPickNum = -6;
    new DraftPick(arbitraryPlayer, arbitraryPoolee, arbitraryRoundNum,
          arbitraryInvalidPickNum);
  }

  @Test
  public void getPlayer()
  {
    assertEquals(arbitraryPlayer, arbitraryDraftPick.getPlayer());
  }

  @Test
  public void getPoolee()
  {
    assertEquals(arbitraryPoolee, arbitraryDraftPick.getPoolee());
  }

  @Test
  public void getRoundNum()
  {
    assertEquals(arbitraryRoundNum, arbitraryDraftPick.getRoundNum());
  }

  @Test
  public void getPickNum()
  {
    assertEquals(arbitraryPickNum, arbitraryDraftPick.getPickNum());
  }

  @Test
  public void equals_sameDraftPickInstance()
  {
    assertEquals(arbitraryDraftPick, arbitraryDraftPick);
  }

  @Test
  public void equals_instanceNotDraftPick()
  {
    final Object arbitraryObjectThatIsNotDraftPick = "A";
    assertFalse(arbitraryDraftPick.equals(arbitraryObjectThatIsNotDraftPick));
  }

  @Test
  public void equals_differentPlayer()
  {
    final Player arbitraryDifferentPlayer = new Player(arbitraryPlayerFirstName,
          arbitraryPlayerLastName + "B", arbitraryTeam, arbitraryPosition);
    final DraftPick draftPickWithDifferentPlayer = new DraftPick(
          arbitraryDifferentPlayer, arbitraryPoolee, arbitraryRoundNum,
          arbitraryPickNum);

    assertFalse(arbitraryDraftPick.equals(draftPickWithDifferentPlayer));
    assertFalse(draftPickWithDifferentPlayer.equals(arbitraryDraftPick));
  }

  @Test
  public void equals_differentPoolee()
  {
    final Poolee arbitraryDifferentPoolee = new Poolee(
          arbitraryPooleeFirstName + "A", arbitraryPooleeLastName);
    final DraftPick draftPickWithDifferentPoolee = new DraftPick(arbitraryPlayer,
          arbitraryDifferentPoolee, arbitraryRoundNum, arbitraryPickNum);

    assertFalse(arbitraryDraftPick.equals(draftPickWithDifferentPoolee));
    assertFalse(draftPickWithDifferentPoolee.equals(arbitraryDraftPick));
  }

  @Test
  public void equals_differentRoundNum()
  {
    final int arbitraryDifferentRoundNum = arbitraryRoundNum + 1;
    final DraftPick draftPickWithDifferentRoundNum = new DraftPick(
          arbitraryPlayer, arbitraryPoolee, arbitraryDifferentRoundNum,
          arbitraryPickNum);

    assertFalse(arbitraryDraftPick.equals(draftPickWithDifferentRoundNum));
    assertFalse(draftPickWithDifferentRoundNum.equals(arbitraryDraftPick));
  }

  @Test
  public void equals_differentPickNum()
  {
    final int arbitraryDifferentPickNum = arbitraryPickNum + 1;
    final DraftPick draftPickWithDifferentPickNum = new DraftPick(
          arbitraryPlayer, arbitraryPoolee, arbitraryRoundNum,
          arbitraryDifferentPickNum);

    assertFalse(arbitraryDraftPick.equals(draftPickWithDifferentPickNum));
    assertFalse(draftPickWithDifferentPickNum.equals(arbitraryDraftPick));
  }

  @Test
  public void equals_equalDraftPick()
  {
    final DraftPick equalDraftPick = new DraftPick(arbitraryPlayer,
          arbitraryPoolee, arbitraryRoundNum, arbitraryPickNum);

    assertTrue(arbitraryDraftPick.equals(equalDraftPick));
    assertTrue(equalDraftPick.equals(arbitraryDraftPick));
    assertEquals(arbitraryDraftPick.hashCode(), equalDraftPick.hashCode());
  }

  @Test
  public void hashCode_equalDraftPicks()
  {
    final DraftPick equalDraftPick = new DraftPick(arbitraryPlayer,
          arbitraryPoolee, arbitraryRoundNum, arbitraryPickNum);

    assertEquals(arbitraryDraftPick.hashCode(), equalDraftPick.hashCode());
  }

  @Test
  public void hashCode_repeatedInvocationsOnSameInstance()
  {
    final int hashCode = arbitraryDraftPick.hashCode();

    final int arbitraryNumRepetitions = 4;
    for (int i = 0; i < arbitraryNumRepetitions; i++)
    {
      assertEquals(hashCode, arbitraryDraftPick.hashCode());
    }
  }

  @Test
  public void testToString()
  {
    assertNotNull(arbitraryDraftPick.toString());
  }
}
