package com.kblaney.nhl.draft;

import com.kblaney.nhl.Player;
import com.kblaney.nhl.PlayersByTeamAndPosition;
import com.kblaney.nhl.Position;
import com.kblaney.nhl.Team;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Set;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public final class DraftCsvReaderWriterTest
{
  private PlayersByTeamAndPosition mockPlayersByTeamAndPosition;
  private DraftOrderGetter mockDraftOrderGetter;
  private DraftReaderWriter readerWriter;

  @Before
  public void setUp()
  {
    mockPlayersByTeamAndPosition = mock(PlayersByTeamAndPosition.class);
    mockDraftOrderGetter = mock(DraftOrderGetter.class);
    readerWriter = new DraftCsvReaderWriter(mockPlayersByTeamAndPosition, mockDraftOrderGetter);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructor_nullPlayersByTeamAndPosition()
  {
    new DraftCsvReaderWriter(null, mockDraftOrderGetter);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructor_nullDraftOrderGetter()
  {
    new DraftCsvReaderWriter(mockPlayersByTeamAndPosition, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void writeDraft_nullDraft() throws Exception
  {
    final Writer arbitraryWriter = mock(Writer.class);
    readerWriter.writeDraft(null, arbitraryWriter);
  }

  @Test(expected = IllegalArgumentException.class)
  public void writeDraft_nullWriter() throws Exception
  {
    final Draft arbitraryDraft = mock(Draft.class);
    readerWriter.writeDraft(arbitraryDraft, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void readDraft_nullReader() throws Exception
  {
    readerWriter.readDraft(null);
  }

  @Test
  public void test_manyDrafts() throws Exception
  {
    assertDraftCanBeWrittenAndRead(Drafts.notStartedDraftWithNoPoolees());
    assertDraftCanBeWrittenAndRead(Drafts.notStartedDraftWithOnePoolee());
    assertDraftCanBeWrittenAndRead(Drafts.notStartedDraftWithAllPoolees());
    assertDraftCanBeWrittenAndRead(Drafts.startedDraftNoDraftPicks());
    assertDraftCanBeWrittenAndRead(Drafts.startedDraftOneRoundCompleted());
    assertDraftCanBeWrittenAndRead(Drafts.completedDraft());
  }

  private void assertDraftCanBeWrittenAndRead(final Draft draft) throws Exception
  {
    @SuppressWarnings("unchecked")
    final Set<Player> set = mock(Set.class);
    when(set.contains(any(Player.class))).thenReturn(true);
    when(mockPlayersByTeamAndPosition.getPlayersOnTeamAtPosition(any(Team.class), any(Position.class))).thenReturn(set);
    final DraftOrderGetter arbitraryDraftOrderGetter = new SCurveDraftOrderGetter();
    readerWriter = new DraftCsvReaderWriter(mockPlayersByTeamAndPosition, arbitraryDraftOrderGetter);

    final Writer writer = new StringWriter();
    readerWriter.writeDraft(draft, writer);
    final Reader reader = new StringReader(writer.toString());
    final Draft actual = readerWriter.readDraft(reader);
    assertEquals(draft, actual);
  }
}
