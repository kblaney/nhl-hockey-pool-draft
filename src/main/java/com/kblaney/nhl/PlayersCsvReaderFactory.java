package com.kblaney.nhl;

import com.kblaney.commons.io.FileOrResourceReaderFactory;
import com.kblaney.commons.io.ReaderFactory;
import java.io.IOException;
import java.io.Reader;

/**
 * Provides {@code Reader} instances that get players in comma-separated values (CSV) format.
 * 
 * <p>
 * Each line in a reader provided by this class is CSV with the following fields:
 * <nl>
 * <li>the player's first name
 * <li>the player's last name
 * <li>the shortform of the player's team
 * <li>the shortform of the player's position
 * </nl>
 * </p>
 */
public final class PlayersCsvReaderFactory implements ReaderFactory
{
  private final ReaderFactory factory = new FileOrResourceReaderFactory("players.csv.txt");

  /** {@inheritDoc} */
  public Reader getInstance() throws IOException
  {
    return factory.getInstance();
  }
}
