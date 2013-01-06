package com.kblaney.nhl;

import java.util.List;

/**
 * Gets teams in the playoffs.
 */
public interface PlayoffTeamsGetter
{
  /**
   * Gets the upper half teams.
   * 
   * @return the teams
   */
  List<Team> getUpperHalfTeams();

  /**
   * Gets the lower-half teams.
   * 
   * @return the teams
   */
  List<Team> getLowerHalfTeams();
}
