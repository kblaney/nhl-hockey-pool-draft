package com.kblaney.nhl.draft;

import com.google.common.collect.Sets;
import com.kblaney.nhl.MockPlayersByTeamAndPosition;
import com.kblaney.nhl.Player;
import com.kblaney.nhl.PlayersByTeamAndPosition;
import com.kblaney.nhl.Position;
import com.kblaney.nhl.Team;
import java.util.Set;

public final class Drafts
{
  private Drafts()
  {
  }

  private static final SeasonType SEASON_TYPE = SeasonType.REGULAR_SEASON;
  private static final PlayersByTeamAndPosition PLAYERS_BY_TEAM_AND_POSITION = new MockPlayersByTeamAndPosition();
  public static final int NUM_ROUNDS = 4;
  public static final int NUM_POOLEES = 3;
  public static final Poolee FIRST_POOLEE = new Poolee("Kyle", "Blaney");
  public static final Poolee SECOND_POOLEE = new Poolee("Dave", "Woods");
  public static final Poolee THIRD_POOLEE = new Poolee("Rob", "Hodgins");
  public static final Set<Poolee> ALL_POOLEES = Sets.newHashSet(FIRST_POOLEE, SECOND_POOLEE, THIRD_POOLEE);
  private static final DraftOrderGetter DRAFT_ORDER_GETTER = new SCurveDraftOrderGetter();

  public static Draft notStartedDraftWithNoPoolees()
  {
    return new DraftImpl(SEASON_TYPE, PLAYERS_BY_TEAM_AND_POSITION, NUM_ROUNDS, NUM_POOLEES, DRAFT_ORDER_GETTER);
  }

  public static Draft notStartedDraftWithOnePoolee()
  {
    final Draft draft = notStartedDraftWithNoPoolees();
    draft.addPoolee(FIRST_POOLEE, 1);

    return draft;
  }

  public static Draft notStartedDraftWithAllPoolees()
  {
    final Draft draft = notStartedDraftWithOnePoolee();
    draft.addPoolee(SECOND_POOLEE, 2);
    draft.addPoolee(THIRD_POOLEE, 3);

    return draft;
  }

  public static Draft startedDraftNoDraftPicks()
  {
    final Draft draft = notStartedDraftWithAllPoolees();
    draft.startDraft();

    return draft;
  }

  public static Draft startedDraftDoublePickUpNext()
  {
    final Draft draft = startedDraftNoDraftPicks();
    draft.addDraftPick(new Player("Roberto", "Luongo", Team.FLORIDA_PANTHERS, Position.GOALIE), FIRST_POOLEE);
    draft.addDraftPick(new Player("Jose", "Theodore", Team.MONTREAL_CANADIENS, Position.GOALIE), SECOND_POOLEE);
    return draft;
  }

  public static Draft startedDraftOneRoundCompleted()
  {
    final Draft draft = startedDraftDoublePickUpNext();
    draft.addDraftPick(new Player("Dominik", "Hasek", Team.OTTAWA_SENATORS, Position.GOALIE), THIRD_POOLEE);
    return draft;
  }

  public static Draft completedDraft()
  {
    final Draft draft = startedDraftOneRoundCompleted();

    // Second round.
    //
    draft.addDraftPick(new Player("Martin", "Brodeur", Team.NEW_JERSEY_DEVILS, Position.GOALIE), THIRD_POOLEE);
    draft.addDraftPick(new Player("Jarome", "Iginla", Team.CALGARY_FLAMES, Position.FORWARD), SECOND_POOLEE);
    draft.addDraftPick(new Player("Joe", "Thornton", Team.BOSTON_BRUINS, Position.FORWARD), FIRST_POOLEE);

    // Third round.
    //
    draft.addDraftPick(new Player("Brad", "Richards", Team.TAMPA_BAY_LIGHTNING, Position.FORWARD), FIRST_POOLEE);
    draft.addDraftPick(new Player("Martin", "St. Louis", Team.TAMPA_BAY_LIGHTNING, Position.FORWARD), SECOND_POOLEE);
    draft.addDraftPick(new Player("Vincent", "Lecavalier", Team.TAMPA_BAY_LIGHTNING, Position.FORWARD), THIRD_POOLEE);

    // Fourth round.
    //
    draft.addDraftPick(new Player("Scott", "Niedermayer", Team.ANAHEIM_DUCKS, Position.DEFENSEMAN), THIRD_POOLEE);
    draft.addDraftPick(new Player("Marek", "Zidlicky", Team.NASHVILLE_PREDATORS, Position.DEFENSEMAN), SECOND_POOLEE);
    draft.addDraftPick(new Player("Sergei", "Gonchar", Team.PITTSBURGH_PENGUINS, Position.DEFENSEMAN), FIRST_POOLEE);

    return draft;
  }
}
