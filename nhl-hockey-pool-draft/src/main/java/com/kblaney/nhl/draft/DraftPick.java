package com.kblaney.nhl.draft;

import com.kblaney.assertions.ArgAssert;
import com.kblaney.nhl.Player;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * A draft pick.
 * <p>
 * This class is immutable.
 * </p>
 */
public final class DraftPick
{
  private final Player player;
  private final Poolee poolee;
  private final int roundNum;
  private final int pickNum;

  /**
   * Constructs a new instance of DraftPick.
   * 
   * @param player the player that is drafted, which can't be null
   * @param poolee the poolee that drafted the player, which can't be null
   * @param roundNum the round number in which the player was drafted, which must be positive
   * @param pickNum the pick number in which the player was drafted, which must be positive
   */
  public DraftPick(final Player player, final Poolee poolee, final int roundNum, final int pickNum)
  {
    ArgAssert.assertNotNull(player, "player");
    ArgAssert.assertNotNull(poolee, "poolee");
    ArgAssert.assertGreaterThanOrEqual(roundNum, 1, "roundNum");
    ArgAssert.assertGreaterThanOrEqual(pickNum, 1, "pickNum");

    this.player = player;
    this.poolee = poolee;
    this.roundNum = roundNum;
    this.pickNum = pickNum;
  }

  /**
   * Gets the player of this draft pick.
   * 
   * @return the player of this draft pick
   */
  public Player getPlayer()
  {
    // We do not need to return a defensive copy because Player is immutable.
    //
    return player;
  }

  /**
   * Gets the poolee of this draft pick.
   * 
   * @return the poolee of this draft pick
   */
  public Poolee getPoolee()
  {
    // We do not need to return a defensive copy because Poolee is immutable.
    //
    return poolee;
  }

  /**
   * Gets the round number of this draft pick.
   * 
   * @return the round number of this draft pick
   */
  public int getRoundNum()
  {
    return roundNum;
  }

  /**
   * Gets the pick number of this draft pick.
   * 
   * @return the pick number of this draft pick
   */
  public int getPickNum()
  {
    return pickNum;
  }

  /** {@inheritDoc} */
  @Override
  public boolean equals(final Object thatObject)
  {
    if (this == thatObject)
    {
      return true;
    }
    else if (!(thatObject instanceof DraftPick))
    {
      return false;
    }
    else
    {
      final DraftPick that = (DraftPick) thatObject;
      return new EqualsBuilder().append(roundNum, that.roundNum).append(pickNum, that.pickNum)
            .append(player, that.player).append(poolee, that.poolee).isEquals();
    }
  }

  /** {@inheritDoc} */
  @Override
  public int hashCode()
  {
    return new HashCodeBuilder().append(roundNum).append(pickNum).append(player).append(poolee).toHashCode();
  }

  /** {@inheritDoc} */
  @Override
  public String toString()
  {
    return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append(roundNum).append(pickNum).append(player)
          .append(poolee).toString();
  }
}
