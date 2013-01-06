package com.kblaney.nhl.draft;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Stores information about a poolee.
 * <p>
 * This class is immutable.
 * </p>
 */
public final class Poolee
{
  private String firstName;
  private String lastName;

  /**
   * Constructs a new instance of Poolee with a specified first and last name.
   *
   * @param firstName the poolee's first name, which can't be null or empty
   * @param lastName the poolee's last name, which can't be null or empty
   */
  public Poolee(final String firstName, final String lastName)
  {
    Validate.notEmpty(firstName, "firstName can't be null or empty");
    Validate.notEmpty(lastName, "lastName can't be null or empty");

    this.firstName = firstName;
    this.lastName = lastName;
  }

  /**
   * Gets this poolee's first name.
   *
   * @return this poolee's first name
   */
  public String getFirstName()
  {
    return firstName;
  }

  /**
   * Gets this poolee's last name.
   *
   * @return this poolee's last name
   */
  public String getLastName()
  {
    return lastName;
  }

  /**
   * Gets this poolee's full name.
   *
   * @return this poolee's full name
   */
  public String getFullName()
  {
    return firstName + " " + getLastName();
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(final Object thatObject)
  {
    if (this == thatObject)
    {
      return true;
    }
    else if (!(thatObject instanceof Poolee))
    {
      return false;
    }
    else
    {
      final Poolee that = (Poolee) thatObject;
      return new EqualsBuilder().
            append(firstName, that.firstName).
            append(lastName, that.lastName).isEquals();
    }
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode()
  {
    return new HashCodeBuilder().
          append(firstName).
          append(lastName).toHashCode();
  }

  /** {@inheritDoc} */
  @Override
  public String toString()
  {
    return getFullName();
  }
}
