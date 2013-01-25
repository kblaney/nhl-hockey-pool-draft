package com.kblaney.nhl;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import com.kblaney.assertions.ArgAssert;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * The implementation of a collection of players optimized for retrieval by team and position.
 */
public final class PlayersByTeamAndPositionImpl implements PlayersByTeamAndPosition
{
  private final Table<Team, Position, Set<Player>> players = HashBasedTable.create();

  /** {@inheritDoc} */
  public void addPlayer(final Player player)
  {
    ArgAssert.assertNotNull(player, "player");

    final Team team = player.getTeam();
    final Position position = player.getPosition();
    if (players.contains(team, position))
    {
      players.get(team, position).add(player);
    }
    else
    {
      players.put(team, position, Sets.newHashSet(player));
    }
  }

  /** {@inheritDoc} */
  public Set<Player> getPlayersOnTeamAtPosition(final Team team, final Position position)
  {
    ArgAssert.assertNotNull(team, "team");
    ArgAssert.assertNotNull(position, "position");

    if (players.contains(team, position))
    {
      return Sets.newHashSet(players.get(team, position));
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

    return Maps.newHashMap(players.row(team));
  }

  /** {@inheritDoc} */
  public Set<Team> getTeams()
  {
    return Sets.newHashSet(players.rowKeySet());
  }

  /** {@inheritDoc} */
  public int getNumPlayers()
  {
    int numPlayers = 0;
    for (final Table.Cell<Team, Position, Set<Player>> cell : players.cellSet())
    {
      numPlayers += cell.getValue().size();
    }
    return numPlayers;
  }
}
