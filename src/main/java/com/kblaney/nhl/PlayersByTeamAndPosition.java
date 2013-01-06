package com.kblaney.nhl;

import java.util.Map;
import java.util.Set;

/**
 * A collection of players optimized for retrieval by team and position.
 */
public interface PlayersByTeamAndPosition
{
  /**
   * Adds a player.
   *
   * @param player the player, which can't be null
   */
  void addPlayer(Player player);

  /**
   * Gets the players on a specified team, sorted by position.
   *
   * @param team the team from which to get the players, which can't be null
   *
   * @return a map whose keys are positions and whose values are the set of
   * players on the specified team at the position
   */
  Map<Position, Set<Player>> getPlayersOnTeam(Team team);

  /**
   * Gets the players on a specified team at a specified position.
   *
   * @param team the team, which can't be null
   * @param position the position, which can't be null
   *
   * @return set of players on the specified team at the position
   */
  Set<Player> getPlayersOnTeamAtPosition(Team team, Position position);

  /**
   * Gets the teams that have players.
   *
   * @return the teams that have players
   */
  Set<Team> getTeams();

  /**
   * Gets the total number of players.
   *
   * @return the total number of players
   */
  int getNumPlayers();
}
