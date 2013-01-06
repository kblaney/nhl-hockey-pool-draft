package com.kblaney.nhl.draft;

import com.kblaney.nhl.Player;
import com.kblaney.nhl.Position;
import com.kblaney.nhl.Team;
import java.text.ParseException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public final class DraftPickCsvLineReaderWriterTest
{
  private int arbitraryRoundNum;
  private int arbitraryPickNum;
  private String arbitraryPooleeFirstName;
  private String arbitraryPooleeLastName;
  private String arbitraryPlayerFirstName;
  private String arbitraryPlayerLastName;
  private Team arbitraryTeam;
  private Position arbitraryPosition;
  private DraftPickCsvLineReaderWriter readerWriter;

  @Before
  public void setUp()
  {
    arbitraryRoundNum = 3;
    arbitraryPickNum = 19;
    arbitraryPooleeFirstName = "Kyle";
    arbitraryPooleeLastName = "Blaney";
    arbitraryPlayerFirstName = "Steve";
    arbitraryPlayerLastName = "Yzerman";
    arbitraryTeam = Team.DETROIT_RED_WINGS;
    arbitraryPosition = Position.FORWARD;
    readerWriter = new DraftPickCsvLineReaderWriter();
  }

  @Test
  public void testRoundTrip() throws Exception
  {
    final Poolee arbitraryPoolee = new Poolee(arbitraryPooleeFirstName,
          arbitraryPooleeLastName);
    final Player arbitraryPlayer = new Player(arbitraryPlayerFirstName,
          arbitraryPlayerLastName, arbitraryTeam, arbitraryPosition);
    final DraftPick draftPick = new DraftPick(arbitraryPlayer, arbitraryPoolee,
          arbitraryRoundNum, arbitraryPickNum);
    final String[] fields = readerWriter.getFields(draftPick);

    assertEquals(draftPick, readerWriter.getDraftPick(fields));
  }

  @Test(expected = ParseException.class)
  public void getDraftPick_invalidNumFields() throws Exception
  {
    final String[] arbitraryFieldsWithInvalidNumFields = new String[0];
    readerWriter.getDraftPick(arbitraryFieldsWithInvalidNumFields);
  }

  @Test(expected = ParseException.class)
  public void getDraftPick_invalidDraftPickRoundNum() throws Exception
  {
    final String arbitraryInvalidDraftPickRoundNum = "Yo";
    final String[] fieldsWithInvalidTeamShortform = new String[]
    {
      arbitraryInvalidDraftPickRoundNum,
      Integer.toString(arbitraryPickNum),
      arbitraryPooleeFirstName,
      arbitraryPooleeLastName,
      arbitraryPlayerFirstName,
      arbitraryPlayerLastName,
      arbitraryTeam.getShortform(),
      arbitraryPosition.getShortform(),
    };
    readerWriter.getDraftPick(fieldsWithInvalidTeamShortform);
  }

  @Test(expected = ParseException.class)
  public void getDraftPick_invalidDraftPickNum() throws Exception
  {
    final String arbitraryInvalidDraftPickNum = "A";
    final String[] fieldsWithInvalidTeamShortform = new String[]
    {
      Integer.toString(arbitraryRoundNum),
      arbitraryInvalidDraftPickNum,
      arbitraryPooleeFirstName,
      arbitraryPooleeLastName,
      arbitraryPlayerFirstName,
      arbitraryPlayerLastName,
      arbitraryTeam.getShortform(),
      arbitraryPosition.getShortform(),
    };
    readerWriter.getDraftPick(fieldsWithInvalidTeamShortform);
  }

  @Test(expected = ParseException.class)
  public void getDraftPick_invalidTeamShortform() throws Exception
  {
    final String arbitraryInvalidTeamShortform = "BUBBA";
    final String[] fieldsWithInvalidTeamShortform = new String[]
    {
      Integer.toString(arbitraryRoundNum),
      Integer.toString(arbitraryPickNum),
      arbitraryPooleeFirstName,
      arbitraryPooleeLastName,
      arbitraryPlayerFirstName,
      arbitraryPlayerLastName,
      arbitraryInvalidTeamShortform,
      arbitraryPosition.getShortform(),
    };
    readerWriter.getDraftPick(fieldsWithInvalidTeamShortform);
  }

  @Test(expected = ParseException.class)
  public void getDraftPick_invalidPositionShortform() throws Exception
  {
    final String arbitraryInvalidPositionShortform = "K";
    final String[] fieldsWithInvalidTeamShortform = new String[]
    {
      Integer.toString(arbitraryRoundNum),
      Integer.toString(arbitraryPickNum),
      arbitraryPooleeFirstName,
      arbitraryPooleeLastName,
      arbitraryPlayerFirstName,
      arbitraryPlayerLastName,
      arbitraryTeam.getShortform(),
      arbitraryInvalidPositionShortform,
    };
    readerWriter.getDraftPick(fieldsWithInvalidTeamShortform);
  }
}
