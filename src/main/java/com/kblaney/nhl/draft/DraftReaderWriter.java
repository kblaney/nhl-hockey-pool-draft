package com.kblaney.nhl.draft;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.text.ParseException;

/**
 * Defines methods that read and write drafts.
 */
public interface DraftReaderWriter
{
  /**
   * Writes a specified draft to a specified writer.
   *
   * @param draft the draft to write, which can't be null
   * @param writer where to write the draft, which can't be null
   *
   * @throws IOException if an I/O problem occurs while writing the draft
   */
  void writeDraft(Draft draft, Writer writer) throws IOException;

  /**
   * Reads a draft from a specified reader.
   *
   * @param reader where to read the draft, which can't be null
   *
   * @return the draft
   *
   * @throws IOException if an I/O problem occurs while reading the draft
   * @throws ParseException if a problem occurs while parsing the draft
   */
  Draft readDraft(Reader reader) throws IOException, ParseException;
}
