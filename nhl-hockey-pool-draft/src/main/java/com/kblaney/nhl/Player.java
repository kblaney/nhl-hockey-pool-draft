package com.kblaney.nhl;

import com.kblaney.assertions.ArgAssert;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * A National Hockey League player.
 * 
 * Distinct players that have the same name, team, and position are distinguished by an optional ID.
 */
public final class Player
{
  private final String firstName;
  private final String lastName;
  private final Team team;
  private final Position position;
  private final String id;

  /**
   * Constructs a player with a specified first and last name, team, and position. The player has an empty ID.
   * 
   * @param firstName the player's first name, which can't be null or empty
   * @param lastName the player's last name, which can't be null or empty
   * @param team the player's team, which can't be null
   * @param position the player's position, which can't be null
   */
  public Player(final String firstName, final String lastName, final Team team, final Position position)
  {
    this(firstName, lastName, team, position, /* id= */StringUtils.EMPTY);
  }

  /**
   * Constructs a player with a specified first and last name, team, position, and ID.
   * 
   * @param firstName the player's first name, which can't be null or empty
   * @param lastName the player's last name, which can't be null or empty
   * @param team the player's team, which can't be null
   * @param position the player's position, which can't be null
   * @param id the player's ID, which can't be null
   */
  public Player(final String firstName, final String lastName, final Team team, final Position position, final String id)
  {
    Validate.notEmpty(firstName, "firstName can't be null or empty");
    Validate.notEmpty(lastName, "lastName can't be null or empty");
    this.team = ArgAssert.assertNotNull(team, "team");
    this.position = ArgAssert.assertNotNull(position, "position");
    this.id = ArgAssert.assertNotNull(id, "id");

    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getFirstName()
  {
    return firstName;
  }

  public String getLastName()
  {
    return lastName;
  }

  /**
   * Gets the player's full name, equivalent to a concatenation of the player's first and last name, separated by a
   * space.
   * 
   * @return the player's full name
   */
  public String getFullName()
  {
    return firstName + " " + lastName;
  }

  /**
   * Gets a shortened version of the player's full name.
   * 
   * @return a shortened version of the player's full name
   */
  public String getShortenedFullName()
  {
    if (firstName.contains("-"))
    {
      return firstName.charAt(0) + "." + firstName.charAt(firstName.indexOf("-") + 1) + ". " + lastName;
    }
    else
    {
      return firstName.charAt(0) + ". " + lastName;
    }
  }

  public Team getTeam()
  {
    return team;
  }

  public Position getPosition()
  {
    return position;
  }

  public String getId()
  {
    return id;
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(final Object thatObject)
  {
    if (this == thatObject)
    {
      return true;
    }
    else if (thatObject == null)
    {
      return false;
    }
    else if (thatObject.getClass() != getClass())
    {
      return false;
    }
    else
    {
      final Player that = (Player) thatObject;
      return new EqualsBuilder().append(firstName, that.firstName).append(lastName, that.lastName)
            .append(team, that.team).append(position, that.position).append(id, that.id).isEquals();
    }
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode()
  {
    return new HashCodeBuilder().append(firstName).append(lastName).append(team).append(position).append(id)
          .toHashCode();
  }

  /** {@inheritDoc} */
  @Override
  public String toString()
  {
    return getLastName() + ", " + getFirstName();
  }
}
