package com.kblaney.nhl;

import com.kblaney.assertions.ArgAssert;

/**
 * A National Hockey League team.
 */
public enum Team
{
  ANAHEIM_DUCKS("Anaheim Ducks", "ANA"),
  BOSTON_BRUINS("Boston Bruins", "BOS"),
  BUFFALO_SABRES("Buffalo Sabres", "BUF"),
  CALGARY_FLAMES("Calgary Flames", "CGY"),
  CAROLINA_HURRICANES("Carolina Hurricanes", "CAR"),
  CHICAGO_BLACKHAWKS("Chicago Blackhawks", "CHI"),
  COLORADO_AVALANCHE("Colorado Avalanche", "COL"),
  COLUMBUS_BLUE_JACKETS("Columbus Blue Jackets", "CLB"),
  DALLAS_STARS("Dallas Stars", "DAL"),
  DETROIT_RED_WINGS("Detroit Red Wings", "DET"),
  EDMONTON_OILERS("Edmonton Oilers", "EDM"),
  FLORIDA_PANTHERS("Florida Panthers", "FLA"),
  LOS_ANGELES_KINGS("Los Angeles Kings", "LA"),
  MINNESOTA_WILD("Minnesota Wild", "MIN"),
  MONTREAL_CANADIENS("Montreal Canadiens", "MON"),
  NASHVILLE_PREDATORS("Nashville Predators", "NAS"),
  NEW_JERSEY_DEVILS("New Jersey Devils", "NJ"),
  NEW_YORK_ISLANDERS("New York Islanders", "NYI"),
  NEW_YORK_RANGERS("New York Rangers", "NYR"),
  OTTAWA_SENATORS("Ottawa Senators", "OTT"),
  PHILADELPHIA_FLYERS("Philadelphia Flyers", "PHI"),
  PHOENIX_COYOTES("Phoenix Coyotes", "PHO"),
  PITTSBURGH_PENGUINS("Pittsburgh Penguins", "PIT"),
  SAN_JOSE_SHARKS("San Jose Sharks", "SJ"),
  ST_LOUIS_BLUES("St. Louis Blues", "STL"),
  TAMPA_BAY_LIGHTNING("Tampa Bay Lightning", "TB"),
  TORONTO_MAPLE_LEAFS("Toronto Maple Leafs", "TOR"),
  VANCOUVER_CANUCKS("Vancouver Canucks", "VAN"),
  WASHINGTON_CAPITALS("Washington Capitals", "WAS"),
  WINNIPEG_JETS("Winnipeg Jets", "WIN");

  private final String longform;
  private final String shortform;

  private Team(final String longform, final String shortform)
  {
    this.longform = longform;
    this.shortform = shortform;
  }

  public String getShortform()
  {
    return shortform;
  }

  /**
   * Determines if there is a team that has a specified shortform. Note that {@code shortform} is not case-significant.
   * 
   * @param shortform the shortform
   * 
   * @return {@code true} if there is a team that has the specified shortform; {@code false} otherwise
   * 
   * @see #getTeamThatHasShortform(String)
   */
  public static boolean isTeamThatHasShortform(final String shortform)
  {
    for (final Team team : Team.values())
    {
      if (team.getShortform().equalsIgnoreCase(shortform))
      {
        return true;
      }
    }
    return false;
  }

  /**
   * Gets the team that has a specified shortform.
   * 
   * @param shortform the team's shortform, which can't be null
   * 
   * @return the team with the specified shortform
   */
  public static Team getTeamThatHasShortform(final String shortform)
  {
    ArgAssert.assertNotNull(shortform, "shortform");

    for (final Team team : Team.values())
    {
      if (team.getShortform().equalsIgnoreCase(shortform))
      {
        return team;
      }
    }

    throw new IllegalArgumentException(shortform + " is not a legal NHL team shortform");
  }

  /** {@inheritDoc} */
  @Override
  public String toString()
  {
    return longform;
  }
}
