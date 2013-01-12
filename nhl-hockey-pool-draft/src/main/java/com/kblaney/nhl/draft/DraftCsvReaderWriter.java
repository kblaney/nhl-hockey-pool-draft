package com.kblaney.nhl.draft;

import com.Ostermiller.util.CSVParse;
import com.Ostermiller.util.CSVParser;
import com.Ostermiller.util.CSVPrint;
import com.Ostermiller.util.CSVPrinter;
import com.google.common.collect.Lists;
import com.kblaney.commons.lang.ArgAssert;
import com.kblaney.nhl.PlayersByTeamAndPosition;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

/**
 * Reads and writes drafts from comma-separated value (CSV) fields.
 */
public final class DraftCsvReaderWriter implements DraftReaderWriter
{
  private final FirstCsvLineReaderWriter firstCsvLineReaderWriter = new FirstCsvLineReaderWriter();
  private final PooleeCsvLineReaderWriter pooleeCsvLineReaderWriter = new PooleeCsvLineReaderWriter();
  private final DraftPickCsvLineReaderWriter draftPickCsvLineReaderWriter = new DraftPickCsvLineReaderWriter();
  private final PlayersByTeamAndPosition playersByTeamAndPosition;
  private final DraftOrderGetter draftOrderGetter;

  /**
   * Constructs a new instance of DraftCsvReaderWriter that uses specified players and a specified object to get the
   * draft order.
   * 
   * @param playersByTeamAndPosition the players (that determine player validity when reading), which can't be null
   * @param draftOrderGetter the object that gets the draft order, which can't be null
   */
  public DraftCsvReaderWriter(final PlayersByTeamAndPosition playersByTeamAndPosition,
        final DraftOrderGetter draftOrderGetter)
  {
    ArgAssert.notNull(playersByTeamAndPosition, "playersByTeamAndPosition");
    ArgAssert.notNull(draftOrderGetter, "draftOrderGetter");

    this.playersByTeamAndPosition = playersByTeamAndPosition;
    this.draftOrderGetter = draftOrderGetter;
  }

  /** {@inheritDoc} */
  public void writeDraft(final Draft draft, final Writer writer) throws IOException
  {
    ArgAssert.notNull(draft, "draft");
    ArgAssert.notNull(writer, "writer");

    final CSVPrint csvPrinter = new CSVPrinter(writer);
    try
    {
      writeFirstLine(draft, csvPrinter);
      writePooleeLines(draft, csvPrinter);
      writeDraftPickLines(draft, csvPrinter);
    }
    finally
    {
      closeCsvPrinterQuietly(csvPrinter);
    }
  }

  private void writeFirstLine(final Draft draft, final CSVPrint csvPrinter) throws IOException
  {
    final String[] fields = firstCsvLineReaderWriter.getFields(draft.getSeasonType(), draft.getNumRounds(),
          draft.getNumPoolees());
    csvPrinter.writeln(fields);
  }

  private void writePooleeLines(final Draft draft, final CSVPrint csvPrinter) throws IOException
  {
    for (final Poolee poolee : draft.getFirstRoundDraftOrder())
    {
      if (poolee != null)
      {
        final String[] fields = pooleeCsvLineReaderWriter.getFields(poolee);
        csvPrinter.writeln(fields);
      }
    }
  }

  private void writeDraftPickLines(final Draft draft, final CSVPrint csvPrinter) throws IOException
  {
    for (final DraftPick draftPick : draft.getDraftPicks())
    {
      final String[] fields = draftPickCsvLineReaderWriter.getFields(draftPick);
      csvPrinter.writeln(fields);
    }
  }

  private void closeCsvPrinterQuietly(final CSVPrint csvPrinter)
  {
    try
    {
      csvPrinter.close();
    }
    catch (final IOException e)
    {
      // We intentionally ignore this exception.
    }
  }

  /** {@inheritDoc} */
  public Draft readDraft(final Reader reader) throws IOException, ParseException
  {
    ArgAssert.notNull(reader, "reader");

    final CSVParse csvParser = new CSVParser(reader);

    final String[] firstLineFields = csvParser.getLine();
    final SeasonType seasonType = firstCsvLineReaderWriter.getSeasonType(firstLineFields);
    final int numRounds = firstCsvLineReaderWriter.getNumRounds(firstLineFields);
    final int numPoolees = firstCsvLineReaderWriter.getNumPoolees(firstLineFields);
    final List<Poolee> firstRoundDraftOrder = getFirstRoundDraftOrder(csvParser, numPoolees);
    final List<DraftPick> draftPicks = getDraftPicks(csvParser);

    return new DraftFactoryImpl().resumeDraft(seasonType, playersByTeamAndPosition, numRounds, numPoolees,
          draftOrderGetter, firstRoundDraftOrder, draftPicks);
  }

  private List<Poolee> getFirstRoundDraftOrder(final CSVParse csvParser, final int numPoolees) throws IOException,
        ParseException
  {
    final List<Poolee> firstRoundDraftOrder = Lists.newArrayList();
    final String[][] pooleeLines = new String[numPoolees][];
    for (int i = 0; i < numPoolees; i++)
    {
      pooleeLines[i] = csvParser.getLine();
    }
    for (final String[] line : pooleeLines)
    {
      if (line == null)
      {
        firstRoundDraftOrder.add(null);
      }
      else
      {
        firstRoundDraftOrder.add(getPoolee(line));
      }
    }
    return firstRoundDraftOrder;
  }

  private Poolee getPoolee(final String[] pooleeLineFields) throws ParseException
  {
    return pooleeCsvLineReaderWriter.getPoolee(pooleeLineFields);
  }

  private List<DraftPick> getDraftPicks(final CSVParse csvParser) throws IOException, ParseException
  {
    final String[][] draftPickLines = csvParser.getAllValues();
    if (draftPickLines == null)
    {
      return Collections.emptyList();
    }
    else
    {
      final List<DraftPick> draftPicks = Lists.newArrayList();
      for (final String[] draftPickLine : draftPickLines)
      {
        draftPicks.add(getDraftPick(draftPickLine));
      }
      return draftPicks;
    }
  }

  private DraftPick getDraftPick(final String[] draftPickLineFields) throws ParseException
  {
    return draftPickCsvLineReaderWriter.getDraftPick(draftPickLineFields);
  }
}
