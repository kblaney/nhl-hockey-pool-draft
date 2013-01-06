package com.kblaney.nhl.draft.ui;

import com.kblaney.nhl.CsvPlayerParser;
import com.kblaney.nhl.CsvPlayoffTeamsGetter;
import com.kblaney.nhl.PlayersByTeamAndPosition;
import com.kblaney.nhl.PlayersCsvReaderFactory;
import com.kblaney.nhl.PlayoffTeamsCsvReaderFactory;
import com.kblaney.nhl.PlayoffTeamsGetter;
import com.kblaney.nhl.draft.DraftCsvReaderWriter;
import com.kblaney.nhl.draft.DraftFactory;
import com.kblaney.nhl.draft.DraftFactoryImpl;
import com.kblaney.nhl.draft.DraftOrderGetter;
import com.kblaney.nhl.draft.DraftReaderWriter;
import com.kblaney.nhl.draft.SCurveDraftOrderGetter;
import java.awt.EventQueue;
import java.io.Reader;
import org.apache.commons.io.IOUtils;

/**
 * The class that contains the entry point of the application.
 */
public final class EntryPoint
{
  /**
   * Constructor made private to restrict instantiation.
   */
  private EntryPoint()
  {
  }

  /**
   * The entry point of the application.
   *
   * @param args the command line arguments
   */
  public static void main(final String[] args)
  {
    EventQueue.invokeLater(new Runnable()
    {
      /** {@inheritDoc} */
      public void run()
      {
        Reader playersCsvReader = null;
        Reader playoffTeamsReader = null;
        try
        {
          playersCsvReader = new PlayersCsvReaderFactory().getInstance();
          final PlayersByTeamAndPosition playersByTeamAndPosition =
                new CsvPlayerParser(playersCsvReader).
                getPlayersByTeamAndPosition();
          IOUtils.closeQuietly(playersCsvReader);

          playoffTeamsReader = new PlayoffTeamsCsvReaderFactory().getInstance();
          final PlayoffTeamsGetter playoffTeamsGetter =
                new CsvPlayoffTeamsGetter(playoffTeamsReader);
          final TableModelFactory tableModelFactory =
                new TableModelFactoryImpl(playoffTeamsGetter);
          IOUtils.closeQuietly(playoffTeamsReader);

          final DraftOrderGetter draftOrderGetter =
                new SCurveDraftOrderGetter();

          final DraftFactory draftFactory = new DraftFactoryImpl();

          final DraftReaderWriter draftReaderWriter =
                new DraftCsvReaderWriter(playersByTeamAndPosition,
                draftOrderGetter);

          final MainFrame mainFrame = new MainFrame(
                playersByTeamAndPosition, tableModelFactory,
                draftOrderGetter, draftFactory, draftReaderWriter);
          mainFrame.setVisible(true);
        }
        catch (final Exception e)
        {
          e.printStackTrace();
        }
        finally
        {
          IOUtils.closeQuietly(playersCsvReader);
          IOUtils.closeQuietly(playoffTeamsReader);
        }
      }
    });
  }
}
