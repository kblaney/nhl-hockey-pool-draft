package com.kblaney.nhl.draft;

/**
 * Determines if integers or strings are valid.
 */
public interface IntOrStringValidator
{
  /**
   * Determines if a specified integer is valid.
   *
   * @param i the integer to validate
   *
   * @return {@code true} if {@code i} is valid; {@code false} if {@code i} is
   * invalid
   */
  boolean isValid(int i);

  /**
   * Determines if a specified string is valid.
   *
   * @param s the string to validate
   *
   * @return {@code true} if {@code s} is valid; {@code false} if {@code s} is
   * invalid
   */
  boolean isValid(String s);
}
