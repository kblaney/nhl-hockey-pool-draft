package com.kblaney.nhl;

import java.util.Comparator;
import org.apache.commons.lang3.builder.CompareToBuilder;

/**
 * Compares players by their last and first name.  Players with an earlier
 * last name in alphabetical order compare as lesser.  For two players with the
 * same last name, the one with an earlier first name in alphabetical order
 * compares as lesser.  Note that names are compared in a case-insensitive
 * manner.
 */
public final class PlayerNameComparator implements Comparator<Player>
{
  @Override
  public int compare(final Player p1, final Player p2)
  {
    return new CompareToBuilder().
          append(p1.getLastName(), p2.getLastName(),
          String.CASE_INSENSITIVE_ORDER).
          append(p1.getFirstName(), p2.getFirstName(),
          String.CASE_INSENSITIVE_ORDER).toComparison();
  }
}
