package com.kblaney.nhl;

import java.io.Reader;
import org.junit.Test;

public final class PlayersCsvReaderFactoryTest
{
  @Test
  public void getInstance() throws Exception
  {
    final Reader reader = new PlayersCsvReaderFactory().getInstance();
    reader.close();
  }
}
