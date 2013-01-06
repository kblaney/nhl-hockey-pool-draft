package com.kblaney.nhl;

import java.io.IOException;
import java.text.ParseException;

/**
 * Parses to get players.
 */
public interface PlayerParser
{
  /**
   * Gets players by team and position.
   *
   * @return players by team and position
   *
   * @throws IOException if an I/O problem occurs during parsing
   * @throws ParseException if a parse problem occurs
   */
  PlayersByTeamAndPosition getPlayersByTeamAndPosition() throws IOException,
        ParseException;

  /**
   * Close the parser quietly.
   */
  void closeQuietly();
}
