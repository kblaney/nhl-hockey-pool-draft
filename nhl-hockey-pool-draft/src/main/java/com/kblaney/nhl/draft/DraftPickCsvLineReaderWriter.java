package com.kblaney.nhl.draft;

import com.kblaney.nhl.ParseExceptions;
import com.kblaney.nhl.Player;
import com.kblaney.nhl.Position;
import com.kblaney.nhl.Team;
import java.text.ParseException;
import java.util.Arrays;

/**
 * Reads and writes a draft pick line in comma-separated values (CSV) format.
 */
final class DraftPickCsvLineReaderWriter
{
  private static final int DRAFT_PICK_ROUND_NUM_INDEX = 0;
  private static final int DRAFT_PICK_NUM_INDEX = 1;
  private static final int POOLEE_FIRST_NAME_INDEX = 2;
  private static final int POOLEE_LAST_NAME_INDEX = 3;
  private static final int PLAYER_FIRST_NAME_INDEX = 4;
  private static final int PLAYER_LAST_NAME_INDEX = 5;
  private static final int PLAYER_TEAM_SHORTFORM_INDEX = 6;
  private static final int PLAYER_POSITION_SHORTFORM_INDEX = 7;
  private static final int NUM_FIELDS = 8;

  public String[] getFields(final DraftPick draftPick)
  {
    final String[] fields = new String[NUM_FIELDS];
    fields[DRAFT_PICK_ROUND_NUM_INDEX] = Integer.toString(draftPick.getRoundNum());
    fields[DRAFT_PICK_NUM_INDEX] = Integer.toString(draftPick.getPickNum());

    final Poolee poolee = draftPick.getPoolee();
    fields[POOLEE_FIRST_NAME_INDEX] = poolee.getFirstName();
    fields[POOLEE_LAST_NAME_INDEX] = poolee.getLastName();

    final Player player = draftPick.getPlayer();
    fields[PLAYER_FIRST_NAME_INDEX] = player.getFirstName();
    fields[PLAYER_LAST_NAME_INDEX] = player.getLastName();
    fields[PLAYER_TEAM_SHORTFORM_INDEX] = player.getTeam().getShortform();
    fields[PLAYER_POSITION_SHORTFORM_INDEX] = player.getPosition().getShortform();
    return fields;
  }

  public DraftPick getDraftPick(final String[] fields) throws ParseException
  {
    if (fields.length == NUM_FIELDS)
    {
      final Poolee poolee = getPoolee(fields);
      final Player player = getPlayer(fields);
      final int draftPickRoundNum = getDraftPickRoundNum(fields);
      final int draftPickNum = getDraftPickNum(fields);
      return new DraftPick(player, poolee, draftPickRoundNum, draftPickNum);
    }
    else
    {
      throw ParseExceptions.newInstance("Invalid number of fields on draft pick line:" + Arrays.toString(fields));
    }
  }

  private Poolee getPoolee(final String[] fields) throws ParseException
  {
    final String pooleeFirstName = fields[POOLEE_FIRST_NAME_INDEX];
    final String pooleeLastName = fields[POOLEE_LAST_NAME_INDEX];
    return new Poolee(pooleeFirstName, pooleeLastName);
  }

  private Player getPlayer(final String[] fields) throws ParseException
  {
    final String firstName = getPlayerFirstName(fields);
    final String lastName = getPlayerLastName(fields);
    final Team team = getPlayerTeam(fields);
    final Position position = getPlayerPosition(fields);
    return new Player(firstName, lastName, team, position);
  }

  private String getPlayerFirstName(final String[] fields)
  {
    return fields[PLAYER_FIRST_NAME_INDEX];
  }

  private String getPlayerLastName(final String[] fields)
  {
    return fields[PLAYER_LAST_NAME_INDEX];
  }

  private Team getPlayerTeam(final String[] fields) throws ParseException
  {
    final String teamShortform = fields[PLAYER_TEAM_SHORTFORM_INDEX];
    if (Team.isTeamThatHasShortform(teamShortform))
    {
      return Team.getTeamThatHasShortform(teamShortform);
    }
    else
    {
      throw ParseExceptions.newInstance("Invalid team shortform:" + teamShortform);
    }
  }

  private Position getPlayerPosition(final String[] fields) throws ParseException
  {
    final String positionShortform = fields[PLAYER_POSITION_SHORTFORM_INDEX];
    if (Position.isPositionThatHasShortform(positionShortform))
    {
      return Position.getPositionThatHasShortform(positionShortform);
    }
    else
    {
      throw ParseExceptions.newInstance("Invalid position shortform:" + positionShortform);
    }
  }

  private int getDraftPickRoundNum(final String[] fields) throws ParseException
  {
    final String draftPickRoundNumString = fields[DRAFT_PICK_ROUND_NUM_INDEX];
    try
    {
      return Integer.parseInt(draftPickRoundNumString);
    }
    catch (final IllegalArgumentException e)
    {
      throw ParseExceptions.newInstance("Invalid draft pick round num:" + draftPickRoundNumString);
    }
  }

  private int getDraftPickNum(final String[] fields) throws ParseException
  {
    final String draftPickNumString = fields[DRAFT_PICK_NUM_INDEX];
    try
    {
      return Integer.parseInt(draftPickNumString);
    }
    catch (final IllegalArgumentException e)
    {
      throw ParseExceptions.newInstance("Invalid draft pick num:" + draftPickNumString);
    }
  }
}
