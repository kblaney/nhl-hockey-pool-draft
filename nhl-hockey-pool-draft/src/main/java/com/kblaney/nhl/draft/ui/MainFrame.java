package com.kblaney.nhl.draft.ui;

import com.kblaney.assertions.ArgAssert;
import com.kblaney.nhl.Player;
import com.kblaney.nhl.PlayersByTeamAndPosition;
import com.kblaney.nhl.Position;
import com.kblaney.nhl.Team;
import com.kblaney.nhl.draft.Draft;
import com.kblaney.nhl.draft.DraftFactory;
import com.kblaney.nhl.draft.DraftOrderGetter;
import com.kblaney.nhl.draft.DraftPick;
import com.kblaney.nhl.draft.DraftReaderWriter;
import com.kblaney.nhl.draft.IntOrStringValidator;
import com.kblaney.nhl.draft.NumPooleesValidator;
import com.kblaney.nhl.draft.NumRoundsValidator;
import com.kblaney.nhl.draft.Poolee;
import com.kblaney.nhl.draft.SeasonType;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import org.apache.commons.io.IOUtils;

/**
 * The main frame of the application.
 */
@SuppressWarnings("serial")
final class MainFrame extends JFrame
{
  private final PlayersByTeamAndPosition playersByTeamAndPosition;
  private final DraftOrderGetter draftOrderGetter;
  private final DraftFactory draftFactory;
  private final DraftReaderWriter draftReaderWriter;
  private final TableModelFactory tableModelFactory;
  private final IntOrStringValidator numRoundsValidator = new NumRoundsValidator();
  private final IntOrStringValidator numPooleesValidator = new NumPooleesValidator();
  private final List<JLabel> tickerLabels;
  private final int numPicksInTicker;
  private final Map<JLabel, DraftPick> tickerLabelToDraftPickMap = new HashMap<JLabel, DraftPick>();
  private Draft draft;
  private JFileChooser fileChooser;
  private File draftOutputFile;
  private final JMenuItem addPooleeMenuItem = new JMenuItem();
  private final JLabel anaheimLabel = new JLabel();
  private final JLabel atlanticDivisionLabel = new JLabel();
  private final JPanel atlanticDivisionPanel = new JPanel();
  private final JPanel atlanticDivisionTeamPanel = new JPanel();
  private final JLabel bostonLabel = new JLabel();
  private final JPanel bottomPanel = new JPanel();
  private final JLabel buffaloLabel = new JLabel();
  private final JPanel buttonPanel = new JPanel();
  private final JLabel calgaryLabel = new JLabel();
  private final JLabel carolinaLabel = new JLabel();
  private final JLabel centralDivisionLabel = new JLabel();
  private final JPanel centralDivisionPanel = new JPanel();
  private final JPanel centralDivisionTeamsPanel = new JPanel();
  private final JTable chartTable = new JTable();
  private final JLabel chicagoLabel = new JLabel();
  private final JLabel coloradoLabel = new JLabel();
  private final JLabel columbusLabel = new JLabel();
  private final JLabel dallasLabel = new JLabel();
  private final JLabel detroitLabel = new JLabel();
  private final JMenu draftMenu = new JMenu();
  private final JPanel draftPanel = new JPanel();
  private final JPanel draftPickInfoPanel = new JPanel();
  private final JLabel draftPickLabel = new JLabel();
  private final JLabel draftPickNumLabel = new JLabel();
  private final JLabel draftPickOfLabel = new JLabel();
  private final JLabel easternConferenceLabel = new JLabel();
  private final JPanel easternConferencePanel = new JPanel();
  private final JLabel edmontonLabel = new JLabel();
  private final JMenuItem exitMenuItem = new JMenuItem();
  private final JLabel fifthMostRecentPickLabel = new JLabel();
  private final JMenu fileMenu = new JMenu();
  private final JLabel floridaLabel = new JLabel();
  private final JLabel fourthMostRecentPickLabel = new JLabel();
  private final JPanel infoPanel = new JPanel();
  private final JLabel losAngelesLabel = new JLabel();
  private final JScrollPane mainChartScrollPane = new JScrollPane();
  private final JButton makeDraftPickButton = new JButton();
  private final JMenuBar menuBar = new JMenuBar();
  private final JLabel minnesotaLabel = new JLabel();
  private final JLabel montrealLabel = new JLabel();
  private final JLabel mostRecentPickLabel = new JLabel();
  private final JLabel nashvilleLabel = new JLabel();
  private final JLabel newJerseyLabel = new JLabel();
  private final JMenuItem newPlayoffDraftMenuItem = new JMenuItem();
  private final JMenuItem newRegularSeasonDraftMenuItem = new JMenuItem();
  private final JLabel newYorkIslandersLabel = new JLabel();
  private final JLabel newYorkRangersLabel = new JLabel();
  private final JLabel northeastDivisionLabel = new JLabel();
  private final JPanel northeastDivisionPanel = new JPanel();
  private final JPanel northeastDivisionTeamsPanel = new JPanel();
  private final JLabel northwestDivisionLabel = new JLabel();
  private final JPanel northwestDivisionPanel = new JPanel();
  private final JPanel northwestDivisionTeamsPanel = new JPanel();
  private final JLabel nowDraftingLabel = new JLabel();
  private final JMenuItem openDraftMenuItem = new JMenuItem();
  private final JLabel ottawaLabel = new JLabel();
  private final JLabel pacificDivisionLabel = new JLabel();
  private final JPanel pacificDivisionPanel = new JPanel();
  private final JPanel pacificDivisionTeamsPanel = new JPanel();
  private final JLabel philadelphiaLabel = new JLabel();
  private final JLabel phoenixLabel = new JLabel();
  private final JLabel pittsburghLabel = new JLabel();
  private final JComboBox playerComboBox = new JComboBox();
  private final JLabel playerLabel = new JLabel();
  private final JComboBox positionComboBox = new JComboBox();
  private final JLabel positionLabel = new JLabel();
  private final JPanel preDraftPanel = new JPanel();
  private final JPanel roundInfoPanel = new JPanel();
  private final JLabel roundLabel = new JLabel();
  private final JLabel roundNumLabel = new JLabel();
  private final JLabel roundOfLabel = new JLabel();
  private final JLabel sanJoseLabel = new JLabel();
  private final JMenuItem saveDraftMenuItem = new JMenuItem();
  private final JLabel secondMostRecentPickLabel = new JLabel();
  private final JPanel selectionPanel = new JPanel();
  private final JMenuItem showPooleesMenuItem = new JMenuItem();
  private final JLabel southeastDivisionLabel = new JLabel();
  private final JPanel southeastDivisionPanel = new JPanel();
  private final JPanel southeastDivisionTeamsPanel = new JPanel();
  private final JLabel stLouisLabel = new JLabel();
  private final JMenuItem startDraftMenuItem = new JMenuItem();
  private final JLabel tampaBayLabel = new JLabel();
  private final JComboBox teamComboBox = new JComboBox();
  private final JLabel teamLabel = new JLabel();
  private final JPanel teamPositionPlayerPanel = new JPanel();
  private final JLabel thirdMostRecentPickLabel = new JLabel();
  private final JPanel tickerAndBottomPanel = new JPanel();
  private final JPanel tickerPanel = new JPanel();
  private final JLabel titleLabel = new JLabel();
  private final JLabel torontoLabel = new JLabel();
  private final JLabel totalNumDraftPicksLabel = new JLabel();
  private final JLabel totalNumRoundsLabel = new JLabel();
  private final JMenuItem undoLastDraftPickMenuItem = new JMenuItem("Undo last draft pick");
  private final JLabel vancouverLabel = new JLabel();
  private final JLabel washingtonLabel = new JLabel();
  private final JLabel westernConferenceLabel = new JLabel();
  private final JPanel westernConferencePanel = new JPanel();
  private final JLabel winnipegLabel = new JLabel();

  /**
   * Consructs a new instance of MainFrame for a draft that has specified players, a specified object that gets the
   * draft order, a specified draft factory, and a specified object that reads and writes the draft.
   * 
   * @param playersByTeamAndPosition the players, which can't be null
   * @param draftOrderGetter the object that gets the draft order, which can't be null
   * @param draftFactor the object that creates drafts, which can't be null
   * @param draftReaderWriter the object that reads and writes drafts, which can't be null
   */
  public MainFrame(final PlayersByTeamAndPosition playersByTeamAndPosition, final TableModelFactory tableModelFactory,
        final DraftOrderGetter draftOrderGetter, final DraftFactory draftFactory,
        final DraftReaderWriter draftReaderWriter)
  {
    ArgAssert.assertNotNull(playersByTeamAndPosition, "playersByTeamAndPosition");
    ArgAssert.assertNotNull(tableModelFactory, "tableModelFactory");
    ArgAssert.assertNotNull(draftOrderGetter, "draftOrderGetter");
    ArgAssert.assertNotNull(draftFactory, "draftFactory");
    ArgAssert.assertNotNull(draftReaderWriter, "draftReaderWriter");

    this.playersByTeamAndPosition = playersByTeamAndPosition;
    this.tableModelFactory = tableModelFactory;
    this.draftOrderGetter = draftOrderGetter;
    this.draftFactory = draftFactory;
    this.draftReaderWriter = draftReaderWriter;

    initComponents();

    // We must initialize the list of ticker labels after initComponents
    // is called so that the labels aren't null.
    //
    tickerLabels = new ArrayList<JLabel>();
    tickerLabels.add(mostRecentPickLabel);
    tickerLabels.add(secondMostRecentPickLabel);
    tickerLabels.add(thirdMostRecentPickLabel);
    tickerLabels.add(fourthMostRecentPickLabel);
    tickerLabels.add(fifthMostRecentPickLabel);
    numPicksInTicker = tickerLabels.size();

    // Center on the screen.
    //
    setLocationRelativeTo(null);
  }

  private void initComponents()
  {
    setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
    setTitle("Hockey Pool!");
    addWindowListener(new java.awt.event.WindowAdapter()
    {
      public void windowClosing(java.awt.event.WindowEvent evt)
      {
        formWindowClosing(evt);
      }
    });
    getContentPane().setLayout(new java.awt.CardLayout(5, 2));

    preDraftPanel.setLayout(new GridBagLayout());

    titleLabel.setFont(new Font("Tahoma", 1, 48));
    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    titleLabel.setText("Hockey Pool!");
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridwidth = 2;
    preDraftPanel.add(titleLabel, gridBagConstraints);

    easternConferencePanel.setLayout(new GridLayout(4, 0, 0, 20));

    easternConferenceLabel.setFont(new Font("Tahoma", 1, 24));
    easternConferenceLabel.setHorizontalAlignment(SwingConstants.CENTER);
    easternConferenceLabel.setIcon(new ImageIcon(getClass().getResource("/nhl-logos/easternConference.gif")));
    easternConferencePanel.add(easternConferenceLabel);

    atlanticDivisionPanel.setLayout(new BorderLayout());

    newJerseyLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/newJersey.png")));
    atlanticDivisionTeamPanel.add(newJerseyLabel);

    newYorkIslandersLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/newYorkIslanders.png")));
    atlanticDivisionTeamPanel.add(newYorkIslandersLabel);

    newYorkRangersLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/newYorkRangers.png")));
    atlanticDivisionTeamPanel.add(newYorkRangersLabel);

    philadelphiaLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/philadelphia.png")));
    atlanticDivisionTeamPanel.add(philadelphiaLabel);

    pittsburghLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/pittsburgh.png")));
    atlanticDivisionTeamPanel.add(pittsburghLabel);

    atlanticDivisionPanel.add(atlanticDivisionTeamPanel, BorderLayout.CENTER);

    atlanticDivisionLabel.setFont(new Font("Tahoma", 1, 24));
    atlanticDivisionLabel.setHorizontalAlignment(SwingConstants.CENTER);
    atlanticDivisionLabel.setText("Atlantic");
    atlanticDivisionPanel.add(atlanticDivisionLabel, BorderLayout.NORTH);

    easternConferencePanel.add(atlanticDivisionPanel);

    southeastDivisionPanel.setLayout(new BorderLayout());

    carolinaLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/carolina.png")));
    southeastDivisionTeamsPanel.add(carolinaLabel);

    floridaLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/florida.png")));
    southeastDivisionTeamsPanel.add(floridaLabel);

    tampaBayLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/tampaBay.png")));
    southeastDivisionTeamsPanel.add(tampaBayLabel);

    washingtonLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/washington.png")));
    southeastDivisionTeamsPanel.add(washingtonLabel);

    winnipegLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/winnipeg.png")));
    southeastDivisionTeamsPanel.add(winnipegLabel);

    southeastDivisionPanel.add(southeastDivisionTeamsPanel, BorderLayout.CENTER);

    southeastDivisionLabel.setFont(new Font("Tahoma", 1, 24));
    southeastDivisionLabel.setHorizontalAlignment(SwingConstants.CENTER);
    southeastDivisionLabel.setText("Southeast");
    southeastDivisionPanel.add(southeastDivisionLabel, BorderLayout.NORTH);

    easternConferencePanel.add(southeastDivisionPanel);

    northeastDivisionPanel.setLayout(new BorderLayout());

    bostonLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/boston.png")));
    northeastDivisionTeamsPanel.add(bostonLabel);

    buffaloLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/buffalo.png")));
    northeastDivisionTeamsPanel.add(buffaloLabel);

    montrealLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/montreal.png")));
    northeastDivisionTeamsPanel.add(montrealLabel);

    ottawaLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/ottawa.png")));
    northeastDivisionTeamsPanel.add(ottawaLabel);

    torontoLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/toronto.png")));
    northeastDivisionTeamsPanel.add(torontoLabel);

    northeastDivisionPanel.add(northeastDivisionTeamsPanel, BorderLayout.CENTER);

    northeastDivisionLabel.setFont(new Font("Tahoma", 1, 24));
    northeastDivisionLabel.setHorizontalAlignment(SwingConstants.CENTER);
    northeastDivisionLabel.setText("Northeast");
    northeastDivisionPanel.add(northeastDivisionLabel, BorderLayout.NORTH);

    easternConferencePanel.add(northeastDivisionPanel);

    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.ipady = 20;
    gridBagConstraints.insets = new Insets(0, 35, 0, 0);
    preDraftPanel.add(easternConferencePanel, gridBagConstraints);

    westernConferencePanel.setLayout(new GridLayout(4, 0, 0, 20));

    westernConferenceLabel.setFont(new Font("Tahoma", 1, 24));
    westernConferenceLabel.setHorizontalAlignment(SwingConstants.CENTER);
    westernConferenceLabel.setIcon(new ImageIcon(getClass().getResource("/nhl-logos/westernConference.gif")));
    westernConferencePanel.add(westernConferenceLabel);

    pacificDivisionPanel.setLayout(new BorderLayout());

    pacificDivisionLabel.setFont(new Font("Tahoma", 1, 24));
    pacificDivisionLabel.setHorizontalAlignment(SwingConstants.CENTER);
    pacificDivisionLabel.setText("Pacific");
    pacificDivisionPanel.add(pacificDivisionLabel, BorderLayout.NORTH);

    anaheimLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/anaheim.png")));
    pacificDivisionTeamsPanel.add(anaheimLabel);

    dallasLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/dallas.png")));
    pacificDivisionTeamsPanel.add(dallasLabel);

    losAngelesLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/losAngeles.png")));
    pacificDivisionTeamsPanel.add(losAngelesLabel);

    phoenixLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/phoenix.png")));
    pacificDivisionTeamsPanel.add(phoenixLabel);

    sanJoseLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/sanJose.png")));
    pacificDivisionTeamsPanel.add(sanJoseLabel);

    pacificDivisionPanel.add(pacificDivisionTeamsPanel, BorderLayout.CENTER);

    westernConferencePanel.add(pacificDivisionPanel);

    northwestDivisionPanel.setLayout(new BorderLayout());

    calgaryLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/calgary.png")));
    northwestDivisionTeamsPanel.add(calgaryLabel);

    coloradoLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/colorado.png")));
    northwestDivisionTeamsPanel.add(coloradoLabel);

    edmontonLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/edmonton.png")));
    northwestDivisionTeamsPanel.add(edmontonLabel);

    minnesotaLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/minnesota.png")));
    northwestDivisionTeamsPanel.add(minnesotaLabel);

    vancouverLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/vancouver.png")));
    northwestDivisionTeamsPanel.add(vancouverLabel);

    northwestDivisionPanel.add(northwestDivisionTeamsPanel, BorderLayout.CENTER);

    northwestDivisionLabel.setFont(new Font("Tahoma", 1, 24));
    northwestDivisionLabel.setHorizontalAlignment(SwingConstants.CENTER);
    northwestDivisionLabel.setText("Northwest");
    northwestDivisionPanel.add(northwestDivisionLabel, BorderLayout.NORTH);

    westernConferencePanel.add(northwestDivisionPanel);

    centralDivisionPanel.setLayout(new BorderLayout());

    chicagoLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/chicago.png")));
    centralDivisionTeamsPanel.add(chicagoLabel);

    columbusLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/columbus.png")));
    centralDivisionTeamsPanel.add(columbusLabel);

    detroitLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/detroit.png")));
    centralDivisionTeamsPanel.add(detroitLabel);

    nashvilleLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/nashville.png")));
    centralDivisionTeamsPanel.add(nashvilleLabel);

    stLouisLabel.setIcon(new ImageIcon(getClass().getResource("/team-logos/stLouis.png")));
    centralDivisionTeamsPanel.add(stLouisLabel);

    centralDivisionPanel.add(centralDivisionTeamsPanel, BorderLayout.CENTER);

    centralDivisionLabel.setFont(new Font("Tahoma", 1, 24));
    centralDivisionLabel.setHorizontalAlignment(SwingConstants.CENTER);
    centralDivisionLabel.setText("Central");
    centralDivisionPanel.add(centralDivisionLabel, BorderLayout.NORTH);

    westernConferencePanel.add(centralDivisionPanel);

    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.ipady = 20;
    gridBagConstraints.insets = new Insets(0, 0, 0, 35);
    preDraftPanel.add(westernConferencePanel, gridBagConstraints);

    getContentPane().add(preDraftPanel, "preDraftPanel");

    draftPanel.setLayout(new GridBagLayout());

    chartTable.setGridColor(new Color(153, 153, 153));
    chartTable.setTableHeader(null);
    mainChartScrollPane.setViewportView(chartTable);

    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    draftPanel.add(mainChartScrollPane, gridBagConstraints);

    tickerAndBottomPanel.setLayout(new BorderLayout(0, 3));

    bottomPanel.setLayout(new BorderLayout());

    infoPanel.setLayout(new GridBagLayout());

    roundLabel.setFont(new Font("Tahoma", 1, 24));
    roundLabel.setText("Round:  ");
    roundInfoPanel.add(roundLabel);

    roundNumLabel.setFont(new Font("Tahoma", 1, 24));
    roundNumLabel.setText("21");
    roundInfoPanel.add(roundNumLabel);

    roundOfLabel.setFont(new Font("Tahoma", 1, 24));
    roundOfLabel.setText("of");
    roundInfoPanel.add(roundOfLabel);

    totalNumRoundsLabel.setFont(new Font("Tahoma", 1, 24));
    totalNumRoundsLabel.setText("23");
    roundInfoPanel.add(totalNumRoundsLabel);

    infoPanel.add(roundInfoPanel, new GridBagConstraints());

    draftPickLabel.setFont(new Font("Tahoma", 1, 24));
    draftPickLabel.setText("Pick:  ");
    draftPickInfoPanel.add(draftPickLabel);

    draftPickNumLabel.setFont(new Font("Tahoma", 1, 24));
    draftPickNumLabel.setText("1");
    draftPickInfoPanel.add(draftPickNumLabel);

    draftPickOfLabel.setFont(new Font("Tahoma", 1, 24));
    draftPickOfLabel.setText("of");
    draftPickInfoPanel.add(draftPickOfLabel);

    totalNumDraftPicksLabel.setFont(new Font("Tahoma", 1, 24));
    totalNumDraftPicksLabel.setText("230");
    draftPickInfoPanel.add(totalNumDraftPicksLabel);

    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    infoPanel.add(draftPickInfoPanel, gridBagConstraints);

    bottomPanel.add(infoPanel, BorderLayout.WEST);

    selectionPanel.setLayout(new BorderLayout());

    nowDraftingLabel.setFont(new Font("Tahoma", 1, 24));
    nowDraftingLabel.setHorizontalAlignment(SwingConstants.CENTER);
    nowDraftingLabel.setText("Now drafting:  ");
    selectionPanel.add(nowDraftingLabel, BorderLayout.NORTH);

    teamPositionPlayerPanel.setLayout(new GridBagLayout());

    teamLabel.setFont(new Font("Tahoma", 1, 14));
    teamLabel.setText("Team:  ");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = GridBagConstraints.EAST;
    gridBagConstraints.insets = new Insets(0, 0, 2, 0);
    teamPositionPlayerPanel.add(teamLabel, gridBagConstraints);

    positionLabel.setFont(new Font("Tahoma", 1, 14));
    positionLabel.setText("Position:  ");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.anchor = GridBagConstraints.EAST;
    gridBagConstraints.insets = new Insets(0, 0, 2, 0);
    teamPositionPlayerPanel.add(positionLabel, gridBagConstraints);

    playerLabel.setFont(new Font("Tahoma", 1, 14));
    playerLabel.setText("Player:  ");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.anchor = GridBagConstraints.EAST;
    gridBagConstraints.insets = new Insets(0, 0, 2, 0);
    teamPositionPlayerPanel.add(playerLabel, gridBagConstraints);

    teamComboBox.setMaximumRowCount(30);
    teamComboBox.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent evt)
      {
        teamComboBoxActionPerformed(evt);
      }
    });
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new Insets(0, 0, 2, 0);
    teamPositionPlayerPanel.add(teamComboBox, gridBagConstraints);

    positionComboBox.setMaximumRowCount(3);
    positionComboBox.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent evt)
      {
        positionComboBoxActionPerformed(evt);
      }
    });
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new Insets(0, 0, 2, 0);
    teamPositionPlayerPanel.add(positionComboBox, gridBagConstraints);

    playerComboBox.setMaximumRowCount(20);
    playerComboBox.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent evt)
      {
        playerComboBoxActionPerformed(evt);
      }
    });
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 2;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.insets = new Insets(0, 0, 2, 0);
    teamPositionPlayerPanel.add(playerComboBox, gridBagConstraints);

    selectionPanel.add(teamPositionPlayerPanel, BorderLayout.CENTER);

    bottomPanel.add(selectionPanel, BorderLayout.CENTER);

    buttonPanel.setLayout(new BorderLayout());

    makeDraftPickButton.setText("Make draft pick");
    makeDraftPickButton.setEnabled(false);
    makeDraftPickButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent evt)
      {
        makeDraftPickActionPerformed(evt);
      }
    });
    buttonPanel.add(makeDraftPickButton, BorderLayout.CENTER);

    bottomPanel.add(buttonPanel, BorderLayout.EAST);

    tickerAndBottomPanel.add(bottomPanel, BorderLayout.CENTER);

    tickerPanel.setLayout(new GridLayout(1, 0, 5, 0));

    fifthMostRecentPickLabel.setBorder(BorderFactory.createEtchedBorder());
    tickerPanel.add(fifthMostRecentPickLabel);

    fourthMostRecentPickLabel.setBorder(BorderFactory.createEtchedBorder());
    tickerPanel.add(fourthMostRecentPickLabel);

    thirdMostRecentPickLabel.setBorder(BorderFactory.createEtchedBorder());
    tickerPanel.add(thirdMostRecentPickLabel);

    secondMostRecentPickLabel.setBorder(BorderFactory.createEtchedBorder());
    tickerPanel.add(secondMostRecentPickLabel);

    mostRecentPickLabel.setBorder(BorderFactory.createEtchedBorder());
    tickerPanel.add(mostRecentPickLabel);

    tickerAndBottomPanel.add(tickerPanel, BorderLayout.NORTH);

    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    draftPanel.add(tickerAndBottomPanel, gridBagConstraints);

    getContentPane().add(draftPanel, "draftPanel");

    fileMenu.setText("File");

    newPlayoffDraftMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N,
          InputEvent.CTRL_MASK));
    newPlayoffDraftMenuItem.setText("New playoff draft...");
    newPlayoffDraftMenuItem.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent evt)
      {
        newPlayoffDraftActionPerformed(evt);
      }
    });
    fileMenu.add(newPlayoffDraftMenuItem);

    newRegularSeasonDraftMenuItem.setText("New regular season draft...");
    newRegularSeasonDraftMenuItem.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent evt)
      {
        newRegularSeasonDraftActionPerformed(evt);
      }
    });
    fileMenu.add(newRegularSeasonDraftMenuItem);

    openDraftMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O,
          InputEvent.CTRL_MASK));
    openDraftMenuItem.setText("Open draft...");
    openDraftMenuItem.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent evt)
      {
        openDraftActionPerformed(evt);
      }
    });
    fileMenu.add(openDraftMenuItem);

    saveDraftMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S,
          InputEvent.CTRL_MASK));
    saveDraftMenuItem.setText("Save draft...");
    saveDraftMenuItem.setEnabled(false);
    saveDraftMenuItem.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent evt)
      {
        saveDraftActionPerformed(evt);
      }
    });
    fileMenu.add(saveDraftMenuItem);

    exitMenuItem.setText("Exit");
    exitMenuItem.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent evt)
      {
        exitActionPerformed(evt);
      }
    });
    fileMenu.add(exitMenuItem);

    menuBar.add(fileMenu);

    draftMenu.setText("Draft");

    addPooleeMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P,
          InputEvent.CTRL_MASK));
    addPooleeMenuItem.setText("Add poolee...");
    addPooleeMenuItem.setEnabled(false);
    addPooleeMenuItem.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent evt)
      {
        addPooleeActionPerformed(evt);
      }
    });
    draftMenu.add(addPooleeMenuItem);

    showPooleesMenuItem.setText("Show poolees...");
    showPooleesMenuItem.setEnabled(false);
    showPooleesMenuItem.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent evt)
      {
        showPooleesActionPerformed(evt);
      }
    });
    draftMenu.add(showPooleesMenuItem);

    startDraftMenuItem.setText("Start draft...");
    startDraftMenuItem.setEnabled(false);
    startDraftMenuItem.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent evt)
      {
        startDraftActionPerformed(evt);
      }
    });
    draftMenu.add(startDraftMenuItem);

    menuBar.add(draftMenu);

    undoLastDraftPickMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
    undoLastDraftPickMenuItem.setEnabled(false);
    undoLastDraftPickMenuItem.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent unused)
      {
        undoLastDraftPickActionPerformed();
      }
    });
    draftMenu.add(undoLastDraftPickMenuItem);

    setJMenuBar(menuBar);

    pack();
  }

  private void undoLastDraftPickActionPerformed()
  {
    draft.undoLastDraftPick();
    updateUiForUnderwayDraft();
    fireChartTableDataChanged();
  }

  private void newRegularSeasonDraftActionPerformed(final ActionEvent evt)
  {
    handleNewDraft(SeasonType.REGULAR_SEASON);
  }

  private void showPooleesActionPerformed(final ActionEvent event)
  {
    try
    {
      ShowPooleesDialog showPooleesDialog = new ShowPooleesDialog(this, draft.getFirstRoundDraftOrder());
      showPooleesDialog.setVisible(true);
    }
    catch (final Exception e)
    {
      UiUtil.showErrorMessageDialog(this, e.getMessage());
    }
  }

  private void formWindowClosing(java.awt.event.WindowEvent event)
  {
    exit();
  }

  private void exitActionPerformed(final ActionEvent event)
  {
    exit();
  }

  /**
   * Asks the user whether to exit the application.
   */
  private void exit()
  {
    if (UiUtil.showYesNoQuestionDefaultNo(this, "Do you want to exit?", "Exit") == JOptionPane.YES_OPTION)
    {
      setVisible(false);
      dispose();
    }
  }

  private void openDraftActionPerformed(final ActionEvent event)
  {
    try
    {
      if (fileChooser == null)
      {
        fileChooser = new JFileChooser();
      }

      if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
      {
        Reader reader = null;
        try
        {
          reader = new BufferedReader(new FileReader(fileChooser.getSelectedFile()));

          draft = draftReaderWriter.readDraft(reader);

          // TODO: Don't assume the draft is underway. (It might not even
          // have all its poolees defined.)

          updateUiForUnderwayDraft();
          fireChartTableDataChanged();
        }
        finally
        {
          IOUtils.closeQuietly(reader);
        }
      }
    }
    catch (final Exception e)
    {
      UiUtil.showErrorMessageDialog(this, e.getMessage());
    }
  }

  private void saveDraftActionPerformed(final ActionEvent event)
  {
    try
    {
      if (fileChooser == null)
      {
        fileChooser = new JFileChooser();
      }

      if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
      {
        draftOutputFile = fileChooser.getSelectedFile();
        saveDraftIfOutputFileSelected();
        JOptionPane.showMessageDialog(this, "Draft successfully saved");
      }
    }
    catch (final Exception e)
    {
      UiUtil.showErrorMessageDialog(this, e.getMessage());
    }
  }

  private void saveDraftIfOutputFileSelected() throws IOException
  {
    if (draftOutputFile != null)
    {
      final Writer writer = new BufferedWriter(new FileWriter(draftOutputFile));
      try
      {
        draftReaderWriter.writeDraft(draft, writer);
      }
      finally
      {
        writer.close();
      }
    }
  }

  private void makeDraftPickActionPerformed(final ActionEvent event)
  {
    try
    {
      final Player player = getSelectedPlayer();
      final Poolee poolee = draft.getNextPooleeToDraft();

      final StringBuilder confirmationMessage = new StringBuilder();
      confirmationMessage.append("Do you confirm your selection of ");
      confirmationMessage.append(player.getPosition().toString().toLowerCase());
      confirmationMessage.append(" ");
      confirmationMessage.append(player.getFullName());
      confirmationMessage.append(" of the ");
      confirmationMessage.append(player.getTeam().toString());
      confirmationMessage.append("?");

      final String[] message = new String[] { poolee.getFullName() + ":", confirmationMessage.toString() };
      final String title = "Confirm draft pick";

      if (UiUtil.showYesNoQuestionDefaultNo(this, message, title) == JOptionPane.YES_OPTION)
      {
        makeDraftPickButton.setEnabled(false);
        final DraftPick draftPick = draft.addDraftPick(player, poolee);

        final DraftPickDialog draftPickDialog = new DraftPickDialog(this, draftPick);
        draftPickDialog.setVisible(true);

        updateUiAfterDraftPick();
        fireChartTableDataChanged();
        saveDraftIfOutputFileSelected();
      }
    }
    catch (final Exception e)
    {
      UiUtil.showErrorMessageDialog(this, e.getMessage());
    }
  }

  /**
   * Updates the user interface after a draft pick is made.
   */
  private void updateUiAfterDraftPick()
  {
    if (draft.isDraftOver())
    {
      setTeamEmptyModel();
      setPositionEmptyModel();
      setPlayerEmptyModel();
      teamComboBox.setEnabled(false);
      positionComboBox.setEnabled(false);
      playerComboBox.setEnabled(false);
      nowDraftingLabel.setText("Draft completed");
    }
    else
    {
      setRoundNum();
      setDraftPickNum();
      setNowDrafting();
      setTeamModel();
      setPositionModel();
      setPlayerEmptyModel();
    }

    setEnabledStatusForUndoLastDraftPickMenuItem();
    updateTicker();
  }

  private void updateTicker()
  {
    tickerLabelToDraftPickMap.clear();

    final List<DraftPick> mostRecentDraftPicks = draft.getMostRecentNDraftPicks(numPicksInTicker);
    for (int i = 0; i < mostRecentDraftPicks.size(); i++)
    {
      final JLabel tickerLabel = tickerLabels.get(i);
      final DraftPick draftPick = mostRecentDraftPicks.get(i);
      tickerLabelToDraftPickMap.put(tickerLabel, draftPick);

      final StringBuilder labelText = new StringBuilder();
      labelText.append(draftPick.getPoolee().getFirstName());
      labelText.append(": ");
      labelText.append(draftPick.getPickNum());
      labelText.append(" - ");
      labelText.append(draftPick.getPlayer().getShortenedFullName());
      labelText.append(" (");
      labelText.append(draftPick.getPlayer().getTeam().getShortform());
      labelText.append(")");

      tickerLabel.setForeground(new TickerDraftPickColorSupplier().getColor(draftPick, draft));
      tickerLabel.setText(labelText.toString());
      tickerLabel.getWidth();
    }
  }

  /**
   * Sets the round number.
   */
  private void setRoundNum()
  {
    roundNumLabel.setText(Integer.toString(draft.getRoundNumOfNextDraftPick()));
    totalNumRoundsLabel.setText(Integer.toString(draft.getNumRounds()));
  }

  private void setDraftPickNum()
  {
    draftPickNumLabel.setText(Integer.toString(draft.getNextDraftPickNum()));
    totalNumDraftPicksLabel.setText(Integer.toString(draft.getNumRounds() * draft.getNumPoolees()));
  }

  private void fireChartTableDataChanged()
  {
    final AbstractTableModel tableModel = (AbstractTableModel) chartTable.getModel();
    tableModel.fireTableDataChanged();
  }

  private void playerComboBoxActionPerformed(final ActionEvent event)
  {
    try
    {
      setMakeDraftPickButtonEnabled();
    }
    catch (final Exception e)
    {
      UiUtil.showErrorMessageDialog(this, e.getMessage());
    }
  }

  private void setMakeDraftPickButtonEnabled()
  {
    makeDraftPickButton.setEnabled(isPlayerSelected());
  }

  private boolean isPlayerSelected()
  {
    return (getSelectedPlayer() != null);
  }

  private void positionComboBoxActionPerformed(final ActionEvent event)
  {
    try
    {
      updatePlayerList();
      setMakeDraftPickButtonEnabled();
    }
    catch (final Exception e)
    {
      UiUtil.showErrorMessageDialog(this, e.getMessage());
    }
  }

  private void teamComboBoxActionPerformed(final ActionEvent event)
  {
    try
    {
      updatePlayerList();
      setMakeDraftPickButtonEnabled();
    }
    catch (final Exception e)
    {
      UiUtil.showErrorMessageDialog(this, e.getMessage());
    }
  }

  private Team getSelectedTeam()
  {
    return (Team) teamComboBox.getSelectedItem();
  }

  private Position getSelectedPosition()
  {
    return (Position) positionComboBox.getSelectedItem();
  }

  private Player getSelectedPlayer()
  {
    return (Player) playerComboBox.getSelectedItem();
  }

  private void updatePlayerList()
  {
    final Team selectedTeam = getSelectedTeam();
    final Position selectedPosition = getSelectedPosition();

    if ((selectedTeam != null) && (selectedPosition != null))
    {
      final Set<Player> players = playersByTeamAndPosition.getPlayersOnTeamAtPosition(selectedTeam, selectedPosition);
      final Set<Player> playersAvailable = new HashSet<Player>();
      for (final Player player : players)
      {
        if (!draft.isPlayerDrafted(player))
        {
          playersAvailable.add(player);
        }
      }
      playerComboBox.setModel(new PlayerComboBoxModel(playersAvailable));

      if (playersAvailable.isEmpty())
      {
        final String noPlayersLeftString = getNoPlayersLeftString(selectedTeam, selectedPosition);
        JOptionPane.showMessageDialog(this, noPlayersLeftString);
      }
    }
  }

  private String getNoPlayersLeftString(final Team team, final Position position)
  {
    final StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("There are no ");
    switch (position)
    {
      case FORWARD:
      {
        stringBuilder.append("forwards");
        break;
      }
      case DEFENSEMAN:
      {
        stringBuilder.append("defensemen");
        break;
      }
      case GOALIE:
      {
        stringBuilder.append("goalies");
        break;
      }
      default:
      {
        throw new IllegalStateException("Invalid position: " + position);
      }
    }
    stringBuilder.append(" left on the ");
    stringBuilder.append(team.toString());
    stringBuilder.append(".");

    return stringBuilder.toString();
  }

  private void startDraftActionPerformed(final ActionEvent event)
  {
    try
    {
      if (JOptionPane.showConfirmDialog(this, "Are you ready to start the draft?", "Start draft",
            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION)
      {
        draft.startDraft();
        updateUiForUnderwayDraft();
      }
    }
    catch (final Exception e)
    {
      UiUtil.showErrorMessageDialog(this, e.getMessage());
    }
  }

  private void updateUiForUnderwayDraft()
  {
    setExtendedState(getExtendedState() | MAXIMIZED_BOTH);

    ((CardLayout) getContentPane().getLayout()).last(getContentPane());

    // Set the table's model.
    //
    final TableModel tableModel = tableModelFactory.getTableModel(draft);
    chartTable.setModel(tableModel);

    // Set the table's default renderer.
    //
    final TableCellRendererFactory tableCellRendererFactory = new TableCellRendererFactoryImpl();
    final TableCellRenderer tableCellRenderer = tableCellRendererFactory.getTableCellRenderer(draft);
    chartTable.setDefaultRenderer(Object.class, tableCellRenderer);

    final TableColumn firstColumn = chartTable.getColumnModel().getColumn(0);
    final int firstColumnWidth = 36;
    firstColumn.setMaxWidth(firstColumnWidth);
    firstColumn.setMinWidth(firstColumnWidth);
    firstColumn.setPreferredWidth(firstColumnWidth);

    if (draft.getSeasonType() == SeasonType.REGULAR_SEASON)
    {
      final TableColumn numPicksPerTeamColumn = chartTable.getColumnModel().getColumn(
            RegularSeasonChartTableModel.NUM_PICKS_COLUMN_INDEX);
      final int columnWidth = 25;
      numPicksPerTeamColumn.setMaxWidth(columnWidth);
      numPicksPerTeamColumn.setMinWidth(columnWidth);
      numPicksPerTeamColumn.setPreferredWidth(columnWidth);
    }

    updateUiAfterDraftPick();

    newPlayoffDraftMenuItem.setEnabled(false);
    newRegularSeasonDraftMenuItem.setEnabled(false);
    openDraftMenuItem.setEnabled(false);
    saveDraftMenuItem.setEnabled(true);
    addPooleeMenuItem.setEnabled(false);
    showPooleesMenuItem.setEnabled(true);
    startDraftMenuItem.setEnabled(false);
    setEnabledStatusForUndoLastDraftPickMenuItem();
  }

  private void setEnabledStatusForUndoLastDraftPickMenuItem()
  {
    undoLastDraftPickMenuItem.setEnabled(!draft.getDraftPicks().isEmpty());
  }

  private void addPooleeActionPerformed(final ActionEvent event)
  {
    try
    {
      AddPooleeDialog addPooleeDialog = new AddPooleeDialog(this, draft.getNumPoolees());
      addPooleeDialog.setVisible(true);
      if (addPooleeDialog.wasOkPressed())
      {
        final Poolee poolee = addPooleeDialog.getPoolee();
        final int draftPositionInFirstRound = addPooleeDialog.getDraftPositionInFirstRound();
        draft.addPoolee(poolee, draftPositionInFirstRound);
      }
    }
    catch (final Exception e)
    {
      UiUtil.showErrorMessageDialog(this, e.getMessage());
    }
  }

  private void newPlayoffDraftActionPerformed(final ActionEvent event)
  {
    handleNewDraft(SeasonType.PLAYOFF);
  }

  private void handleNewDraft(final SeasonType seasonType)
  {
    try
    {
      final String numRoundsString = JOptionPane.showInputDialog(this, "How many rounds are in this draft?",
            "New draft: Number of rounds", JOptionPane.QUESTION_MESSAGE);

      if (numRoundsString != null)
      {
        if (numRoundsValidator.isValid(numRoundsString))
        {
          int numRounds = Integer.parseInt(numRoundsString);

          final String numPooleesString = JOptionPane.showInputDialog(this, "How many poolees are in this draft?",
                "New draft: Number of poolees", JOptionPane.QUESTION_MESSAGE);
          if (numPooleesValidator.isValid(numPooleesString))
          {
            final int numPoolees = Integer.parseInt(numPooleesString);

            draft = draftFactory.createNewDraft(seasonType, playersByTeamAndPosition, numRounds, numPoolees,
                  draftOrderGetter);
            addPooleeMenuItem.setEnabled(true);
            showPooleesMenuItem.setEnabled(true);
            startDraftMenuItem.setEnabled(true);
          }
          else
          {
            JOptionPane.showMessageDialog(this, numPooleesString + " is not a valid number of poolees");
          }
        }
        else
        {
          JOptionPane.showMessageDialog(this, numRoundsString + " is not a valid number of rounds");
        }
      }
    }
    catch (final Exception e)
    {
      UiUtil.showErrorMessageDialog(this, e.getMessage());
    }
  }

  public void setNowDrafting()
  {
    final StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Now drafting:  ");
    stringBuilder.append(draft.getNextPooleeToDraft().getFullName());
    stringBuilder.append(" (");

    final int numConsecutivePicks = draft.getNumConsecutivePicksForNextPooleeToDraft();
    stringBuilder.append(numConsecutivePicks);
    if (numConsecutivePicks > 1)
    {
      stringBuilder.append(" picks");
    }
    else
    {
      stringBuilder.append(" pick");
    }
    stringBuilder.append(")");

    nowDraftingLabel.setText(stringBuilder.toString());
  }

  public void setTeamModel()
  {
    final Poolee nextPooleeToDraft = draft.getNextPooleeToDraft();
    teamComboBox.setModel(new TeamComboBoxModel(draft.getTeamsAvailableToDraftFrom(nextPooleeToDraft)));
  }

  public void setPositionModel()
  {
    positionComboBox.setModel(new PositionComboBoxModel());
  }

  public void setTeamEmptyModel()
  {
    teamComboBox.setModel(new DefaultComboBoxModel(new Object[] {}));
  }

  public void setPositionEmptyModel()
  {
    positionComboBox.setModel(new DefaultComboBoxModel(new Object[] {}));
  }

  public void setPlayerEmptyModel()
  {
    playerComboBox.setModel(new DefaultComboBoxModel(new Object[] {}));
  }
}
