package com.kblaney.nhl;

import java.io.Reader;
import org.junit.Test;

public final class PlayoffTeamsCsvReaderFactoryTest
{
  @Test
  public void getInstance() throws Exception
  {
    final Reader reader = new PlayoffTeamsCsvReaderFactory().getInstance();
    reader.close();
  }
}
