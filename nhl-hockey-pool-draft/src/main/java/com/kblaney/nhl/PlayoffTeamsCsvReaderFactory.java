package com.kblaney.nhl;

import com.kblaney.io.FileOrResourceReaderFactory;
import com.kblaney.io.ReaderFactory;
import java.io.IOException;
import java.io.Reader;

/**
 * Provides {@code Reader} instances that get playoff teams in comma-separated values (CSV) format.
 * 
 * <p>
 * Readers provided by this class have two comma-separated lines. The first line contains the abbreviations of the team
 * names to display in the upper half of the chart. The second line contains the abbreviations of the team names to
 * display in the lower half of the chart.
 * </p>
 */
public final class PlayoffTeamsCsvReaderFactory implements ReaderFactory
{
  private final ReaderFactory factory = new FileOrResourceReaderFactory("playoffTeams.csv.txt");

  /** {@inheritDoc} */
  public Reader getInstance() throws IOException
  {
    return factory.getInstance();
  }
}
