package com.kblaney.nhl;

import com.google.common.collect.Sets;
import com.kblaney.assertions.ArgAssert;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * The implementation of a collection of players optimized for retrieval by team and position that uses a map.
 */
public final class PlayersByTeamAndPositionMapImpl implements PlayersByTeamAndPosition
{
  /**
   * Our main data structure: a map indexed by team where each value is itself a map indexed by position. The innermost
   * values are sets of players who play on the team at the position.
   */
  private final Map<Team, Map<Position, Set<Player>>> playersByTeamAndPosition = new EnumMap<Team, Map<Position, Set<Player>>>(
        Team.class);

  /** {@inheritDoc} */
  public void addPlayer(final Player player)
  {
    ArgAssert.assertNotNull(player, "player");

    if (doesTeamAlreadyHavePlayers(player.getTeam()))
    {
      addPlayerToTeamThatAlreadyHasPlayers(player);
    }
    else
    {
      addFirstPlayerToTeam(player);
    }
  }

  private boolean doesTeamAlreadyHavePlayers(final Team team)
  {
    return (playersByTeamAndPosition.get(team) != null);
  }

  private void addFirstPlayerToTeam(final Player player)
  {
    final Map<Position, Set<Player>> playersOnTeam = new EnumMap<Position, Set<Player>>(Position.class);
    playersOnTeam.put(player.getPosition(), Sets.newHashSet(player));

    playersByTeamAndPosition.put(player.getTeam(), playersOnTeam);
  }

  private void addPlayerToTeamThatAlreadyHasPlayers(final Player player)
  {
    final Map<Position, Set<Player>> playersOnTeam = playersByTeamAndPosition.get(player.getTeam());
    final Set<Player> playersOnTeamAtPosition = playersOnTeam.get(player.getPosition());
    if (playersOnTeamAtPosition == null)
    {
      // This player is the first at his position for his team.
      //
      playersOnTeam.put(player.getPosition(), Sets.newHashSet(player));
    }
    else
    {
      playersOnTeamAtPosition.add(player);
      playersOnTeam.put(player.getPosition(), playersOnTeamAtPosition);
    }
  }

  /** {@inheritDoc} */
  public Set<Player> getPlayersOnTeamAtPosition(final Team team, final Position position)
  {
    ArgAssert.assertNotNull(team, "team");
    ArgAssert.assertNotNull(position, "position");

    final Map<Position, Set<Player>> playersOnTeam = getPlayersOnTeam(team);
    if (playersOnTeam.containsKey(position))
    {
      // Return a defensive copy.
      //
      return new HashSet<Player>(playersOnTeam.get(position));
    }
    else
    {
      return Collections.emptySet();
    }
  }

  /** {@inheritDoc} */
  public Map<Position, Set<Player>> getPlayersOnTeam(final Team team)
  {
    ArgAssert.assertNotNull(team, "team");

    final Map<Position, Set<Player>> playersOnTeam = playersByTeamAndPosition.get(team);

    if (playersOnTeam == null)
    {
      return Collections.emptyMap();
    }
    else
    {
      // Return a defensive copy.
      //
      return new EnumMap<Position, Set<Player>>(playersOnTeam);
    }
  }

  /** {@inheritDoc} */
  public Set<Team> getTeams()
  {
    return playersByTeamAndPosition.keySet();
  }

  /** {@inheritDoc} */
  public int getNumPlayers()
  {
    int numPlayers = 0;

    for (final Team team : playersByTeamAndPosition.keySet())
    {
      final Map<Position, Set<Player>> playersOnTeam = playersByTeamAndPosition.get(team);
      for (final Position position : playersOnTeam.keySet())
      {
        numPlayers += playersOnTeam.get(position).size();
      }
    }

    return numPlayers;
  }
}
