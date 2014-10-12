package com.kblaney.nhl;

import java.io.Reader;
import java.io.StringReader;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of the PlayersByTeamAndPosition interface that comes with many players already added.
 */
public final class MockPlayersByTeamAndPosition implements PlayersByTeamAndPosition
{
  private PlayersByTeamAndPosition playersByTeamAndPosition;

  public MockPlayersByTeamAndPosition()
  {
    final StringBuilder s = new StringBuilder();
    s.append("Sergei,Fedorov,ANA,F\n");
    s.append("Ryan,Getzlaf,ANA,F\n");
    s.append("Zenon,Konopka,ANA,F\n");
    s.append("Joffrey,Lupul,ANA,F\n");
    s.append("Rob,Niedermayer,ANA,F\n");
    s.append("Samuel,Pahlsson,ANA,F\n");
    s.append("Kip,Brennan,ANA,F\n");
    s.append("Todd,Fedoruk,ANA,F\n");
    s.append("Andy,McDonald,ANA,F\n");
    s.append("Travis,Moen,ANA,F\n");
    s.append("Jonathan,Hedstrom,ANA,F\n");
    s.append("Corey,Perry,ANA,F\n");
    s.append("Teemu,Selanne,ANA,F\n");
    s.append("Petr,Sykora,ANA,F\n");
    s.append("Keith,Carney,ANA,D\n");
    s.append("Joey,DiPenta,ANA,D\n");
    s.append("Jason,Marshall,ANA,D\n");
    s.append("Scott,Niedermayer,ANA,D\n");
    s.append("Sandis,Ozolinsh,ANA,D\n");
    s.append("Ruslan,Salei,ANA,D\n");
    s.append("Ladislav,Smid,ANA,D\n");
    s.append("Jordan,Smith,ANA,D\n");
    s.append("Vitaly,Vishnevski,ANA,D\n");
    s.append("Bruno,St. Jacques,ANA,D\n");
    s.append("Ilya,Bryzgalov,ANA,G\n");
    s.append("Jean-Sebastien,Giguere,ANA,G\n");
    s.append("Steve,Shields,ANA,G\n");
    s.append("Bobby,Holik,WIN,F\n");
    s.append("Marc,Savard,WIN,F\n");
    s.append("Patrik,Stefan,WIN,F\n");
    s.append("Serge,Aubin,WIN,F\n");
    s.append("Eric,Boulton,WIN,F\n");
    s.append("Ilya,Kovalchuk,WIN,F\n");
    s.append("Slava,Kozlov,WIN,F\n");
    s.append("Ramzi,Abid,WIN,F\n");
    s.append("Brad,Larsen,WIN,F\n");
    s.append("Peter,Bondra,WIN,F\n");
    s.append("Marian,Hossa,WIN,F\n");
    s.append("Francis,Lessard,WIN,F\n");
    s.append("Scott,Mellanby,WIN,F\n");
    s.append("Ronald,Petrovicky,WIN,F\n");
    s.append("J.P.,Vigier,WIN,F\n");
    s.append("Greg,de Vries,WIN,D\n");
    s.append("Garnet,Exelby,WIN,D\n");
    s.append("Niclas,Havelid,WIN,D\n");
    s.append("Shane,Hnidy,WIN,D\n");
    s.append("Tomas,Kloucek,WIN,D\n");
    s.append("Jaroslav,Modry,WIN,D\n");
    s.append("Andy,Sutton,WIN,D\n");
    s.append("Mike,Dunham,WIN,G\n");
    s.append("Jani,Hurme,WIN,G\n");
    s.append("Kari,Lehtonen,WIN,G\n");
    s.append("Patrice,Bergeron,BOS,F\n");
    s.append("Ben,Walter,BOS,F\n");
    s.append("Brad,Boyes,BOS,F\n");
    s.append("Travis,Green,BOS,F\n");
    s.append("Dave,Scatchard,BOS,F\n");
    s.append("Joe,Thornton,BOS,F\n");
    s.append("Alexei,Zhamnov,BOS,F\n");
    s.append("P.J.,Axelsson,BOS,F\n");
    s.append("Brad,Isbister,BOS,F\n");
    s.append("Sergei,Samsonov,BOS,F\n");
    s.append("Tom,Fitzgerald,BOS,F\n");
    s.append("Shawn,McEachern,BOS,F\n");
    s.append("Glen,Murray,BOS,F\n");
    s.append("Colton,Orr,BOS,F\n");
    s.append("Kevin,Dallman,BOS,D\n");
    s.append("Andrew,Alberts,BOS,D\n");
    s.append("Milan,Jurcina,BOS,D\n");
    s.append("Matt,Lashoff,BOS,D\n");
    s.append("Hal,Gill,BOS,D\n");
    s.append("Jonathan,Girard,BOS,D\n");
    s.append("Brian,Leetch,BOS,D\n");
    s.append("Ian,Moran,BOS,D\n");
    s.append("Jiri,Slegr,BOS,D\n");
    s.append("Mark,Stuart,BOS,D\n");
    s.append("Nick,Boynton,BOS,D\n");
    s.append("Andrew,Raycroft,BOS,G\n");
    s.append("Hannu,Toivonen,BOS,G\n");
    s.append("Tim,Thomas,BOS,G\n");
    s.append("Daniel,Briere,BUF,F\n");
    s.append("Chris,Drury,BUF,F\n");
    s.append("Derek,Roy,BUF,F\n");
    s.append("Adam,Mair,BUF,F\n");
    s.append("Chris,Taylor,BUF,F\n");
    s.append("Tim,Connolly,BUF,F\n");
    s.append("Jochen,Hecht,BUF,F\n");
    s.append("Thomas,Vanek,BUF,F\n");
    s.append("Andrew,Peters,BUF,F\n");
    s.append("J.P.,Dumont,BUF,F\n");
    s.append("Ales,Kotalik,BUF,F\n");
    s.append("Taylor,Pyatt,BUF,F\n");
    s.append("Mike,Grier,BUF,F\n");
    s.append("Maxim,Afinogenov,BUF,F\n");
    s.append("Jason,Pominville,BUF,F\n");
    s.append("Sean,McMorrow,BUF,F\n");
    s.append("Paul,Gaustad,BUF,F\n");
    s.append("Rory,Fitzpatrick,BUF,D\n");
    s.append("Doug,Janik,BUF,D\n");
    s.append("Dmitri,Kalinin,BUF,D\n");
    s.append("Teppo,Numminen,BUF,D\n");
    s.append("Toni,Lydman,BUF,D\n");
    s.append("Henrik,Tallinder,BUF,D\n");
    s.append("Jeff,Jillson,BUF,D\n");
    s.append("Jay,McKee,BUF,D\n");
    s.append("Brian,Campbell,BUF,D\n");
    s.append("Michael,Leighton,BUF,G\n");
    s.append("Martin,Biron,BUF,G\n");
    s.append("Ryan,Miller,BUF,G\n");
    s.append("Mika,Noronen,BUF,G\n");
    s.append("Lynn,Loyns,CGY,F\n");
    s.append("Daymond,Langkow,CGY,F\n");
    s.append("Marcus,Nilson,CGY,F\n");
    s.append("Stephane,Yelle,CGY,F\n");
    s.append("Matthew,Lombardi,CGY,F\n");
    s.append("Craig,MacDonald,CGY,F\n");
    s.append("Byron,Ritchie,CGY,F\n");
    s.append("Steve,Reinprecht,CGY,F\n");
    s.append("Eric,Nystrom,CGY,F\n");
    s.append("Tony,Amonte,CGY,F\n");
    s.append("Chris,Simon,CGY,F\n");
    s.append("Jason,Wiemer,CGY,F\n");
    s.append("Jarome,Iginla,CGY,F\n");
    s.append("Shean,Donovan,CGY,F\n");
    s.append("Darren,McCarty,CGY,F\n");
    s.append("Chuck,Kobasew,CGY,F\n");
    s.append("Brantt,Myhres,CGY,F\n");
    s.append("Roman,Hamrlik,CGY,D\n");
    s.append("Jordan,Leopold,CGY,D\n");
    s.append("Robyn,Regehr,CGY,D\n");
    s.append("Dion,Phaneuf,CGY,D\n");
    s.append("Andrew,Ference,CGY,D\n");
    s.append("Rhett,Warrener,CGY,D\n");
    s.append("Steve,Montador,CGY,D\n");
    s.append("Miikka,Kiprusoff,CGY,G\n");
    s.append("Philippe,Sauve,CGY,G\n");
    s.append("Kevyn,Adams,CAR,F\n");
    s.append("Rod,Brind'Amour,CAR,F\n");
    s.append("Matt,Cullen,CAR,F\n");
    s.append("Colin,Forbes,CAR,F\n");
    s.append("Eric,Staal,CAR,F\n");
    s.append("Josef,Vasicek,CAR,F\n");
    s.append("Erik,Cole,CAR,F\n");
    s.append("Niklas,Nordgren,CAR,F\n");
    s.append("Gordie,Dwyer,CAR,F\n");
    s.append("Cory,Stillman,CAR,F\n");
    s.append("Ray,Whitney,CAR,F\n");
    s.append("Jesse,Boulerice,CAR,F\n");
    s.append("Radim,Vrbata,CAR,F\n");
    s.append("Craig,Adams,CAR,F\n");
    s.append("Justin,Williams,CAR,F\n");
    s.append("Andrew,Ladd,CAR,F\n");
    s.append("Michael,Zigomanis,CAR,F\n");
    s.append("Mike,Commodore,CAR,D\n");
    s.append("Bret,Hedican,CAR,D\n");
    s.append("Andrew,Hutchinson,CAR,D\n");
    s.append("Frantisek,Kaberle,CAR,D\n");
    s.append("Oleg,Tverdovsky,CAR,D\n");
    s.append("Niclas,Wallin,CAR,D\n");
    s.append("Aaron,Ward,CAR,D\n");
    s.append("Glen,Wesley,CAR,D\n");
    s.append("Martin,Gerber,CAR,G\n");
    s.append("Cam,Ward,CAR,G\n");
    s.append("Kevin,Nastiuk,CAR,G\n");
    s.append("Tyler,Arnason,CHI,F\n");
    s.append("Curtis,Brown,CHI,F\n");
    s.append("Jim,Dowd,CHI,F\n");
    s.append("Tuomo,Ruutu,CHI,F\n");
    s.append("Mark,Bell,CHI,F\n");
    s.append("Rene,Bourque,CHI,F\n");
    s.append("Kyle,Calder,CHI,F\n");
    s.append("Eric,Daze,CHI,F\n");
    s.append("Shawn,Thornton,CHI,F\n");
    s.append("Matthew,Barnaby,CHI,F\n");
    s.append("Matt,Ellison,CHI,F\n");
    s.append("Jason,Morgan,CHI,F\n");
    s.append("Martin,Lapointe,CHI,F\n");
    s.append("Mikael,Holmqvist,CHI,F\n");
    s.append("Pavel,Vorobiev,CHI,F\n");
    s.append("Milan,Bartovic,CHI,F\n");
    s.append("Adrian,Aucoin,CHI,D\n");
    s.append("Duncan,Keith,CHI,D\n");
    s.append("Cam,Barker,CHI,D\n");
    s.append("Jassen,Cullimore,CHI,D\n");
    s.append("Brent,Seabrook,CHI,D\n");
    s.append("Todd,Simpson,CHI,D\n");
    s.append("Jaroslav,Spacek,CHI,D\n");
    s.append("Jim,Vandermeer,CHI,D\n");
    s.append("Nikolai,Khabibulin,CHI,G\n");
    s.append("Craig,Anderson,CHI,G\n");
    s.append("Dan,Fritsche,CLB,F\n");
    s.append("Jan,Hrdina,CLB,F\n");
    s.append("Manny,Malhotra,CLB,F\n");
    s.append("Todd,Marchant,CLB,F\n");
    s.append("Ben,Simon,CLB,F\n");
    s.append("Rick,Nash,CLB,F\n");
    s.append("Alexandre,Picard,CLB,F\n");
    s.append("Jody,Shelley,CLB,F\n");
    s.append("Trevor,Letowski,CLB,F\n");
    s.append("Joe,Motzko,CLB,F\n");
    s.append("David,Vyborny,CLB,F\n");
    s.append("Tyler,Wright,CLB,F\n");
    s.append("Nikolai,Zherdev,CLB,F\n");
    s.append("Jaroslav,Balastik,CLB,F\n");
    s.append("Gilbert,Brule,CLB,F\n");
    s.append("Michael,Rupp,CLB,F\n");
    s.append("Jason,Chimera,CLB,F\n");
    s.append("Cale,Hulse,CLB,D\n");
    s.append("Bryan,Berard,CLB,D\n");
    s.append("Adam,Foote,CLB,D\n");
    s.append("Francois,Beauchemin,CLB,D\n");
    s.append("Andy,Delmore,CLB,D\n");
    s.append("Aaron,Johnson,CLB,D\n");
    s.append("Rostislav,Klesla,CLB,D\n");
    s.append("Luke,Richardson,CLB,D\n");
    s.append("Radoslav,Suchy,CLB,D\n");
    s.append("Duvie,Westcott,CLB,D\n");
    s.append("Marc,Denis,CLB,G\n");
    s.append("Pascal,Leclaire,CLB,G\n");
    s.append("Martin,Prusek,CLB,G\n");
    s.append("Riku,Hahl,COL,F\n");
    s.append("Brett,McLean,COL,F\n");
    s.append("Joe,Sakic,COL,F\n");
    s.append("Pierre,Turgeon,COL,F\n");
    s.append("Andrew,Brunette,COL,F\n");
    s.append("Steve,Konowalchuk,COL,F\n");
    s.append("Antti,Laaksonen,COL,F\n");
    s.append("Brad,May,COL,F\n");
    s.append("Alex,Tanguay,COL,F\n");
    s.append("Paul,Healey,COL,F\n");
    s.append("Milan,Hejduk,COL,F\n");
    s.append("Dan,Hinote,COL,F\n");
    s.append("Ian,Laperriere,COL,F\n");
    s.append("Marek,Svatos,COL,F\n");
    s.append("Rob,Blake,COL,D\n");
    s.append("Bob,Boughner,COL,D\n");
    s.append("Patrice,Brisebois,COL,D\n");
    s.append("Curtis,Leschyshyn,COL,D\n");
    s.append("John-Michael,Liles,COL,D\n");
    s.append("Kurt,Sauer,COL,D\n");
    s.append("Karlis,Skrastins,COL,D\n");
    s.append("Ossi,Vaananen,COL,D\n");
    s.append("David,Aebischer,COL,G\n");
    s.append("Peter,Budaj,COL,G\n");
    s.append("Jason,Arnott,DAL,F\n");
    s.append("Stu,Barnes,DAL,F\n");
    s.append("Yared,Hagos,DAL,F\n");
    s.append("Niko,Kapanen,DAL,F\n");
    s.append("Mike,Modano,DAL,F\n");
    s.append("Steve,Ott,DAL,F\n");
    s.append("Jussi,Jokinen,DAL,F\n");
    s.append("Brenden,Morrow,DAL,F\n");
    s.append("Mathias,Tjarnqvist,DAL,F\n");
    s.append("Bill,Guerin,DAL,F\n");
    s.append("Jere,Lehtinen,DAL,F\n");
    s.append("Junior,Lessard,DAL,F\n");
    s.append("Jaroslav,Svoboda,DAL,F\n");
    s.append("Garrett,Burnett,DAL,F\n");
    s.append("Antti,Miettinen,DAL,F\n");
    s.append("David,Oliver,DAL,F\n");
    s.append("Philippe,Boucher,DAL,D\n");
    s.append("Trevor,Daley,DAL,D\n");
    s.append("Jon,Klemm,DAL,D\n");
    s.append("Stephane,Robidas,DAL,D\n");
    s.append("Martin,Skoula,DAL,D\n");
    s.append("Sergei,Zubov,DAL,D\n");
    s.append("John,Erskine,DAL,D\n");
    s.append("Patrick,Traverse,DAL,D\n");
    s.append("Johan,Hedberg,DAL,G\n");
    s.append("Marty,Turco,DAL,G\n");
    s.append("Pavel,Datsyuk,DET,F\n");
    s.append("Kris,Draper,DET,F\n");
    s.append("Johan,Franzen,DET,F\n");
    s.append("Jiri,Hudler,DET,F\n");
    s.append("Robert,Lang,DET,F\n");
    s.append("Jason,Williams,DET,F\n");
    s.append("Steve,Yzerman,DET,F\n");
    s.append("Tomas,Holmstrom,DET,F\n");
    s.append("Kirk,Maltby,DET,F\n");
    s.append("Brendan,Shanahan,DET,F\n");
    s.append("Henrik,Zetterberg,DET,F\n");
    s.append("Daniel,Cleary,DET,F\n");
    s.append("Kent,McDonell,DET,F\n");
    s.append("Mark,Mowers,DET,F\n");
    s.append("Rem,Murray,DET,F\n");
    s.append("Mikael,Samuelsson,DET,F\n");
    s.append("Jason,Woolley,DET,D\n");
    s.append("Brett,Lebda,DET,D\n");
    s.append("Chris,Chelios,DET,D\n");
    s.append("Jiri,Fischer,DET,D\n");
    s.append("Niklas,Kronwall,DET,D\n");
    s.append("Nicklas,Lidstrom,DET,D\n");
    s.append("Andreas,Lilja,DET,D\n");
    s.append("Mathieu,Schneider,DET,D\n");
    s.append("Jamie,Rivers,DET,D\n");
    s.append("Manny,Legace,DET,G\n");
    s.append("Chris,Osgood,DET,G\n");
    s.append("Jim,Howard,DET,G\n");
    s.append("Shawn,Horcoff,EDM,F\n");
    s.append("Michael,Peca,EDM,F\n");
    s.append("Marty,Reasoner,EDM,F\n");
    s.append("Jarret,Stoll,EDM,F\n");
    s.append("Ethan,Moreau,EDM,F\n");
    s.append("Jani,Rita,EDM,F\n");
    s.append("Ryan,Smyth,EDM,F\n");
    s.append("Raffi,Torres,EDM,F\n");
    s.append("Radek,Dvorak,EDM,F\n");
    s.append("Ales,Hemsky,EDM,F\n");
    s.append("Georges,Laraque,EDM,F\n");
    s.append("Fernando,Pisani,EDM,F\n");
    s.append("Todd,Harvey,EDM,F\n");
    s.append("J.J.,Hunter,EDM,F\n");
    s.append("Brad,Winchester,EDM,F\n");
    s.append("Marc-Andre,Bergeron,EDM,D\n");
    s.append("Cory,Cross,EDM,D\n");
    s.append("Chris,Pronger,EDM,D\n");
    s.append("Dan,Smith,EDM,D\n");
    s.append("Alexei,Semenov,EDM,D\n");
    s.append("Jason,Smith,EDM,D\n");
    s.append("Steve,Staios,EDM,D\n");
    s.append("Igor,Ulanov,EDM,D\n");
    s.append("Ty,Conklin,EDM,G\n");
    s.append("Jussi,Markkanen,EDM,G\n");
    s.append("Greg,Campbell,FLA,F\n");
    s.append("Chris,Gratton,FLA,F\n");
    s.append("Nathan,Horton,FLA,F\n");
    s.append("Olli,Jokinen,FLA,F\n");
    s.append("Kamil,Kreps,FLA,F\n");
    s.append("Joe,Nieuwendyk,FLA,F\n");
    s.append("Rostislav,Olesz,FLA,F\n");
    s.append("Anthony,Stewart,FLA,F\n");
    s.append("Jozef,Stumpel,FLA,F\n");
    s.append("Stephen,Weiss,FLA,F\n");
    s.append("Martin,Gelinas,FLA,F\n");
    s.append("Niklas,Hagman,FLA,F\n");
    s.append("Kristian,Huselius,FLA,F\n");
    s.append("Gary,Roberts,FLA,F\n");
    s.append("Juraj,Kolnik,FLA,F\n");
    s.append("Jay,Bouwmeester,FLA,D\n");
    s.append("Eric,Cairns,FLA,D\n");
    s.append("Dan,Focht,FLA,D\n");
    s.append("Sean,Hill,FLA,D\n");
    s.append("Alexander,Karpovtsev,FLA,D\n");
    s.append("Joel,Kwiatkowski,FLA,D\n");
    s.append("Branislav,Mezei,FLA,D\n");
    s.append("Mike,Van Ryn,FLA,D\n");
    s.append("Roberto,Luongo,FLA,G\n");
    s.append("Jamie,McLennan,FLA,G\n");
    s.append("Derek,Armstrong,LA,F\n");
    s.append("Sean,Avery,LA,F\n");
    s.append("Eric,Belanger,LA,F\n");
    s.append("Craig,Conroy,LA,F\n");
    s.append("Pavol,Demitra,LA,F\n");
    s.append("Jeremy,Roenick,LA,F\n");
    s.append("Jeff,Cowan,LA,F\n");
    s.append("Alexander,Frolov,LA,F\n");
    s.append("Luc,Robitaille,LA,F\n");
    s.append("Dustin,Brown,LA,F\n");
    s.append("Valeri,Bure,LA,F\n");
    s.append("Tom,Kostopoulos,LA,F\n");
    s.append("Michael,Cammalleri,LA,F\n");
    s.append("George,Parros,LA,F\n");
    s.append("Ken,Belanger,LA,F\n");
    s.append("Joe,Corvo,LA,D\n");
    s.append("Nathan,Dempsey,LA,D\n");
    s.append("Tim,Gleason,LA,D\n");
    s.append("Aaron,Miller,LA,D\n");
    s.append("Mattias,Norstrom,LA,D\n");
    s.append("Lubomir,Visnovsky,LA,D\n");
    s.append("Mike,Weaver,LA,D\n");
    s.append("Denis,Grebeshkov,LA,D\n");
    s.append("Mathieu,Garon,LA,G\n");
    s.append("Jason,Labarbera,LA,G\n");
    s.append("Derek,Boogaard,MIN,F\n");
    s.append("Marc,Chouinard,MIN,F\n");
    s.append("Brian,Rolston,MIN,F\n");
    s.append("Wes,Walz,MIN,F\n");
    s.append("Todd,White,MIN,F\n");
    s.append("Pierre-Marc,Bouchard,MIN,F\n");
    s.append("Pascal,Dupuis,MIN,F\n");
    s.append("Andrei,Nazarov,MIN,F\n");
    s.append("Stephane,Veilleux,MIN,F\n");
    s.append("Alexandre,Daigle,MIN,F\n");
    s.append("Matt,Foy,MIN,F\n");
    s.append("Marian, Gaborik,MIN,F\n");
    s.append("Mikko,Koivu,MIN,F\n");
    s.append("Randy,Robitaille,MIN,F\n");
    s.append("Kyle,Wanvig,MIN,F\n");
    s.append("Benoit,Pouliot,MIN,F\n");
    s.append("Brent,Burns,MIN,D\n");
    s.append("Scott,Ferguson,MIN,D\n");
    s.append("Alex,Henry,MIN,D\n");
    s.append("Filip,Kuba,MIN,D\n");
    s.append("Willie,Mitchell,MIN,D\n");
    s.append("Nick,Schultz,MIN,D\n");
    s.append("Daniel,Tjarnqvist,MIN,D\n");
    s.append("Andrei,Zyuzin,MIN,D\n");
    s.append("Manny,Fernandez,MIN,G\n");
    s.append("Dwayne,Roloson,MIN,G\n");
    s.append("Josh,Harding,MIN,G\n");
    s.append("Steve,Begin,MON,F\n");
    s.append("Radek,Bonk,MON,F\n");
    s.append("Jan,Bulis,MON,F\n");
    s.append("Saku,Koivu,MON,F\n");
    s.append("Alexander,Perezhogin,MON,F\n");
    s.append("Pierre,Dagenais,MON,F\n");
    s.append("Andrei,Kastsitsyn,MON,F\n");
    s.append("Mike,Ribeiro,MON,F\n");
    s.append("Pete,Vandermeer,MON,F\n");
    s.append("Alexei,Kovalev,MON,F\n");
    s.append("Michael,Ryder,MON,F\n");
    s.append("Garth,Murray,MON,F\n");
    s.append("Niklas,Sundstrom,MON,F\n");
    s.append("Richard,Zednik,MON,F\n");
    s.append("Francis,Bouillon,MON,D\n");
    s.append("Mathieu,Dandenault,MON,D\n");
    s.append("Mike,Komisarek,MON,D\n");
    s.append("Andrei,Markov,MON,D\n");
    s.append("Craig,Rivet,MON,D\n");
    s.append("Sheldon,Souray,MON,D\n");
    s.append("Mark,Streit,MON,D\n");
    s.append("Jose,Theodore,MON,G\n");
    s.append("Cristobal,Huet,MON,G\n");
    s.append("Yann,Danis,MON,G\n");
    s.append("Kris,Beech,NAS,F\n");
    s.append("Greg,Johnson,NAS,F\n");
    s.append("David,Legwand,NAS,F\n");
    s.append("Scott,Nichol,NAS,F\n");
    s.append("Martin,Erat,NAS,F\n");
    s.append("Scott,Hartnell,NAS,F\n");
    s.append("Darcy,Hordichuk,NAS,F\n");
    s.append("Paul,Kariya,NAS,F\n");
    s.append("Jeremy,Stevenson,NAS,F\n");
    s.append("Adam,Hall,NAS,F\n");
    s.append("Steve,Sullivan,NAS,F\n");
    s.append("Scott,Walker,NAS,F\n");
    s.append("Vernon,Fiddler,NAS,F\n");
    s.append("Simon,Gamache,NAS,F\n");
    s.append("Yanic,Perreault,NAS,F\n");
    s.append("Jordin,Tootoo,NAS,F\n");
    s.append("Jamie,Allison,NAS,D\n");
    s.append("Mark,Eaton,NAS,D\n");
    s.append("Ryan,Suter,NAS,D\n");
    s.append("Dan,Hamhuis,NAS,D\n");
    s.append("Danny,Markov,NAS,D\n");
    s.append("Kimmo,Timonen,NAS,D\n");
    s.append("Marek,Zidlicky,NAS,D\n");
    s.append("Chris,Mason,NAS,G\n");
    s.append("Tomas,Vokoun,NAS,G\n");
    s.append("Sergei,Brylin,NJ,F\n");
    s.append("Scott,Gomez,NJ,F\n");
    s.append("John,Madden,NJ,F\n");
    s.append("Zach,Parise,NJ,F\n");
    s.append("Erik,Rasmussen,NJ,F\n");
    s.append("Patrik,Elias,NJ,F\n");
    s.append("Darren,Langdon,NJ,F\n");
    s.append("Krzysztof,Oliwa,NJ,F\n");
    s.append("Jay,Pandolfo,NJ,F\n");
    s.append("Brian,Gionta,NJ,F\n");
    s.append("Viktor,Kozlov,NJ,F\n");
    s.append("Jamie,Langenbrunner,NJ,F\n");
    s.append("Grant,Marshall,NJ,F\n");
    s.append("Pascal,Rheaume,NJ,F\n");
    s.append("Alexander,Mogilny,NJ,F\n");
    s.append("Sean,Brown,NJ,D\n");
    s.append("Vladimir,Malakhov,NJ,D\n");
    s.append("Paul,Martin,NJ,D\n");
    s.append("Richard,Matvichuk,NJ,D\n");
    s.append("Dan,McGillis,NJ,D\n");
    s.append("Brian,Rafalski,NJ,D\n");
    s.append("Colin,White,NJ,D\n");
    s.append("Martin,Brodeur,NJ,G\n");
    s.append("Scott,Clemmensen,NJ,G\n");
    s.append("Robert,Nilsson,NYI,F\n");
    s.append("Petteri,Nokelainen,NYI,F\n");
    s.append("Justin,Papineau,NYI,F\n");
    s.append("Alexei,Yashin,NYI,F\n");
    s.append("Mike,York,NYI,F\n");
    s.append("Shawn,Bates,NYI,F\n");
    s.append("Sean,Bergenheim,NYI,F\n");
    s.append("Jason,Blake,NYI,F\n");
    s.append("Oleg,Kvasha,NYI,F\n");
    s.append("Arron,Asham,NYI,F\n");
    s.append("Eric,Godard,NYI,F\n");
    s.append("Trent,Hunter,NYI,F\n");
    s.append("Mark,Parrish,NYI,F\n");
    s.append("Miroslav,Satan,NYI,F\n");
    s.append("Mattias,Weinhandl,NYI,F\n");
    s.append("Joel,Bouchard,NYI,D\n");
    s.append("Chris,Campoli,NYI,D\n");
    s.append("Bruno,Gervais,NYI,D\n");
    s.append("Brad,Lukowich,NYI,D\n");
    s.append("Radek,Martinek,NYI,D\n");
    s.append("Janne,Niinimaa,NYI,D\n");
    s.append("Tomi,Pettinen,NYI,D\n");
    s.append("Brent,Sopel,NYI,D\n");
    s.append("Alexei,Zhitnik,NYI,D\n");
    s.append("Rick,DiPietro,NYI,G\n");
    s.append("Garth,Snow,NYI,G\n");
    s.append("Fedor,Fedorov,NYR,F\n");
    s.append("Marcel,Hossa,NYR,F\n");
    s.append("Jamie,Lundmark,NYR,F\n");
    s.append("Michael,Nylander,NYR,F\n");
    s.append("Steve,Rucchin,NYR,F\n");
    s.append("Martin,Straka,NYR,F\n");
    s.append("Ville,Nieminen,NYR,F\n");
    s.append("Martin,Rucinsky,NYR,F\n");
    s.append("Jaromir,Jagr,NYR,F\n");
    s.append("Jason,Ward,NYR,F\n");
    s.append("Dominic,Moore,NYR,F\n");
    s.append("Blair,Betts,NYR,F\n");
    s.append("Jason,Strudwick,NYR,F\n");
    s.append("Ryan,Hollweg,NYR,F\n");
    s.append("Jarkko,Immonen,NYR,F\n");
    s.append("Petr,Prucha,NYR,F\n");
    s.append("Jed,Ortmeyer,NYR,F\n");
    s.append("Darius,Kasparaitis,NYR,D\n");
    s.append("Bryce,Lampman,NYR,D\n");
    s.append("Marek,Malik,NYR,D\n");
    s.append("Tom,Poti,NYR,D\n");
    s.append("Dale,Purinton,NYR,D\n");
    s.append("Jason,Strudwick,NYR,D\n");
    s.append("Fedor,Tyutin,NYR,D\n");
    s.append("Henrik,Lundqvist,NYR,G\n");
    s.append("Kevin,Weekes,NYR,G\n");
    s.append("Mike,Fisher,OTT,F\n");
    s.append("Bryan,Smolinski,OTT,F\n");
    s.append("Jason,Spezza,OTT,F\n");
    s.append("Peter,Schaefer,OTT,F\n");
    s.append("Vaclav,Varada,OTT,F\n");
    s.append("Antoine,Vermette,OTT,F\n");
    s.append("Daniel,Alfredsson,OTT,F\n");
    s.append("Martin,Havlat,OTT,F\n");
    s.append("Dany,Heatley,OTT,F\n");
    s.append("Chris,Neil,OTT,F\n");
    s.append("Steve,Martins,OTT,F\n");
    s.append("Denis,Hamel,OTT,F\n");
    s.append("Brian,McGrattan,OTT,F\n");
    s.append("Brandon,Bochenski,OTT,F\n");
    s.append("Chris,Kelly,OTT,F\n");
    s.append("Zdeno,Chara,OTT,D\n");
    s.append("Christoph,Schubert,OTT,D\n");
    s.append("Andrej,Meszaros,OTT,D\n");
    s.append("Chris,Phillips,OTT,D\n");
    s.append("Brian,Pothier,OTT,D\n");
    s.append("Wade,Redden,OTT,D\n");
    s.append("Anton,Volchenkov,OTT,D\n");
    s.append("Lance,Ward,OTT,D\n");
    s.append("Ray,Emery,OTT,G\n");
    s.append("Dominik,Hasek,OTT,G\n");
    s.append("Jeff,Carter,PHI,F\n");
    s.append("R.J.,Umberger,PHI,F\n");
    s.append("Peter,Forsberg,PHI,F\n");
    s.append("Michal,Handzus,PHI,F\n");
    s.append("Keith,Primeau,PHI,F\n");
    s.append("Mike,Richards,PHI,F\n");
    s.append("Patrick,Sharp,PHI,F\n");
    s.append("Donald,Brashear,PHI,F\n");
    s.append("Simon,Gagne,PHI,F\n");
    s.append("Brian,Savage,PHI,F\n");
    s.append("Jon,Sim,PHI,F\n");
    s.append("Sami,Kapanen,PHI,F\n");
    s.append("Mike,Knuble,PHI,F\n");
    s.append("Branko,Radivojevic,PHI,F\n");
    s.append("Turner,Stevenson,PHI,F\n");
    s.append("Eric,Desjardins,PHI,D\n");
    s.append("Derian,Hatcher,PHI,D\n");
    s.append("Kim,Johnsson,PHI,D\n");
    s.append("Joni,Pitkanen,PHI,D\n");
    s.append("Mike,Rathje,PHI,D\n");
    s.append("Dennis,Seidenberg,PHI,D\n");
    s.append("Chris,Therien,PHI,D\n");
    s.append("Robert,Esche,PHI,G\n");
    s.append("Antero,Niittymaki,PHI,G\n");
    s.append("Jamie,Storr,PHI,G\n");
    s.append("Mike,Comrie,ARI,F\n");
    s.append("Boyd,Devereaux,ARI,F\n");
    s.append("Krystofer,Kolanos,ARI,F\n");
    s.append("Petr,Nedved,ARI,F\n");
    s.append("Mike,Ricci,ARI,F\n");
    s.append("Jeff,Taffe,ARI,F\n");
    s.append("Shane,Doan,ARI,F\n");
    s.append("Mike,Leclerc,ARI,F\n");
    s.append("Ladislav,Nagy,ARI,F\n");
    s.append("Tyson,Nash,ARI,F\n");
    s.append("Oleg,Saprykin,ARI,F\n");
    s.append("Brett,Hull,ARI,F\n");
    s.append("Mike,Johnson,ARI,F\n");
    s.append("Fredrik,Sjostrom,ARI,F\n");
    s.append("Geoff,Sanderson,ARI,F\n");
    s.append("Tim,Jackman,ARI,F\n");
    s.append("Brad,Ference,ARI,D\n");
    s.append("Denis,Gauthier,ARI,D\n");
    s.append("Paul,Mara,ARI,D\n");
    s.append("Chris,McAllister,ARI,D\n");
    s.append("Derek,Morris,ARI,D\n");
    s.append("Sean,O'Donnell,ARI,D\n");
    s.append("Matthew,Spiller,ARI,D\n");
    s.append("David,Tanabe,ARI,D\n");
    s.append("Keith,Ballard,ARI,D\n");
    s.append("Zbynek,Michalek,ARI,D\n");
    s.append("Brian,Boucher,ARI,G\n");
    s.append("Curtis,Joseph,ARI,G\n");
    s.append("David,Leneveu,ARI,G\n");
    s.append("Matt,Murley,PIT,F\n");
    s.append("Sidney,Crosby,PIT,F\n");
    s.append("Shane,Endicott,PIT,F\n");
    s.append("Mario,Lemieux,PIT,F\n");
    s.append("Lasse,Pirjeta,PIT,F\n");
    s.append("Konstantin,Koltsov,PIT,F\n");
    s.append("John,LeClair,PIT,F\n");
    s.append("Ryan,Malone,PIT,F\n");
    s.append("Mark,Recchi,PIT,F\n");
    s.append("Andre,Roy,PIT,F\n");
    s.append("Rico,Fata,PIT,F\n");
    s.append("Zigmund,Palffy,PIT,F\n");
    s.append("Ryan,VandenBussche,PIT,F\n");
    s.append("Maxime,Talbot,PIT,F\n");
    s.append("Sergei,Gonchar,PIT,D\n");
    s.append("Ric,Jackman,PIT,D\n");
    s.append("Josef,Melichar,PIT,D\n");
    s.append("Lyle,Odelein,PIT,D\n");
    s.append("Brooks,Orpik,PIT,D\n");
    s.append("Steve,Poapst,PIT,D\n");
    s.append("Dick,Tarnstrom,PIT,D\n");
    s.append("Sebastien,Caron,PIT,G\n");
    s.append("Marc-Andre,Fleury,PIT,G\n");
    s.append("Jocelyn,Thibault,PIT,G\n");
    s.append("Andy,Chiodo,PIT,G\n");
    s.append("Marcel,Goc,SJ,F\n");
    s.append("Patrick,Marleau,SJ,F\n");
    s.append("Alyn,McCauley,SJ,F\n");
    s.append("Wayne,Primeau,SJ,F\n");
    s.append("Mark,Smith,SJ,F\n");
    s.append("Nils,Ekman,SJ,F\n");
    s.append("Marco,Sturm,SJ,F\n");
    s.append("Scott,Thornton,SJ,F\n");
    s.append("Jonathan,Cheechoo,SJ,F\n");
    s.append("Ryan,Clowe,SJ,F\n");
    s.append("Niko,Dimitrakos,SJ,F\n");
    s.append("Josh,Langfeld,SJ,F\n");
    s.append("Milan,Michalek,SJ,F\n");
    s.append("Scott,Parker,SJ,F\n");
    s.append("Rob,Davison,SJ,D\n");
    s.append("Christian,Ehrhoff,SJ,D\n");
    s.append("Josh,Gorges,SJ,D\n");
    s.append("Jim,Fahey,SJ,D\n");
    s.append("Scott,Hannan,SJ,D\n");
    s.append("Kyle,McLaren,SJ,D\n");
    s.append("Tom,Preissing,SJ,D\n");
    s.append("Brad,Stuart,SJ,D\n");
    s.append("Evgeni,Nabokov,SJ,G\n");
    s.append("Vesa,Toskala,SJ,G\n");
    s.append("Petr,Cajanek,STL,F\n");
    s.append("Ryan,Johnson,STL,F\n");
    s.append("Dean,McAmmond,STL,F\n");
    s.append("Jay,McClement,STL,F\n");
    s.append("Lee,Stempniak,STL,F\n");
    s.append("Mike,Sillinger,STL,F\n");
    s.append("Doug,Weight,STL,F\n");
    s.append("Trent,Whitfield,STL,F\n");
    s.append("Peter,Sejna,STL,F\n");
    s.append("Keith,Tkachuk,STL,F\n");
    s.append("Eric,Boguniecki,STL,F\n");
    s.append("Aaron,Downey,STL,F\n");
    s.append("Dallas,Drake,STL,F\n");
    s.append("Jeff,Hoggan,STL,F\n");
    s.append("Reed,Low,STL,F\n");
    s.append("Jamal,Mayers,STL,F\n");
    s.append("Mark,Rycroft,STL,F\n");
    s.append("Scott,Young,STL,F\n");
    s.append("Christian,Backman,STL,D\n");
    s.append("Andy,Roach,STL,D\n");
    s.append("Eric,Brewer,STL,D\n");
    s.append("Barret,Jackman,STL,D\n");
    s.append("Doug,Lynch,STL,D\n");
    s.append("Bryce,Salvador,STL,D\n");
    s.append("Matt,Walker,STL,D\n");
    s.append("Eric,Weinrich,STL,D\n");
    s.append("Jeff,Woywitka,STL,D\n");
    s.append("Dennis,Wideman,STL,D\n");
    s.append("Reinhard,Divis,STL,G\n");
    s.append("Patrick,Lalime,STL,G\n");
    s.append("Martin,Cibak,TB,F\n");
    s.append("Vincent,Lecavalier,TB,F\n");
    s.append("Brad,Richards,TB,F\n");
    s.append("Tim,Taylor,TB,F\n");
    s.append("Dmitry,Afanasenkov,TB,F\n");
    s.append("Dave,Andreychuk,TB,F\n");
    s.append("Chris,Dingman,TB,F\n");
    s.append("Fredrik,Modin,TB,F\n");
    s.append("Vaclav,Prospal,TB,F\n");
    s.append("Rob,DiMaio,TB,F\n");
    s.append("Ruslan,Fedotenko,TB,F\n");
    s.append("Martin,St. Louis,TB,F\n");
    s.append("Dan,Boyle,TB,D\n");
    s.append("Timo,Helbling,TB,D\n");
    s.append("Pavel,Kubina,TB,D\n");
    s.append("Nolan,Pratt,TB,D\n");
    s.append("Todd,Rohloff,TB,D\n");
    s.append("Cory,Sarich,TB,D\n");
    s.append("Darryl,Sydor,TB,D\n");
    s.append("Sean,Burke,TB,G\n");
    s.append("John,Grahame,TB,G\n");
    s.append("Matthew,Stajan,TOR,F\n");
    s.append("Jason,Allison,TOR,F\n");
    s.append("Nik,Antropov,TOR,F\n");
    s.append("Eric,Lindros,TOR,F\n");
    s.append("Mats,Sundin,TOR,F\n");
    s.append("Clarke,Wilm,TOR,F\n");
    s.append("Chad,Kilger,TOR,F\n");
    s.append("Alexei,Ponikarovsky,TOR,F\n");
    s.append("Wade,Belak,TOR,F\n");
    s.append("Mariusz,Czerkawski,TOR,F\n");
    s.append("Tie,Domi,TOR,F\n");
    s.append("Jeff,O'Neill,TOR,F\n");
    s.append("Nathan,Perrott,TOR,F\n");
    s.append("Darcy,Tucker,TOR,F\n");
    s.append("Alexander,Steen,TOR,F\n");
    s.append("Kyle,Wellwood,TOR,F\n");
    s.append("Aki,Berg,TOR,D\n");
    s.append("Brad,Brown,TOR,D\n");
    s.append("Carlo,Colaiacovo,TOR,D\n");
    s.append("Tomas,Kaberle,TOR,D\n");
    s.append("Alexander,Khavanov,TOR,D\n");
    s.append("Ken,Klee,TOR,D\n");
    s.append("Bryan,McCabe,TOR,D\n");
    s.append("Andy,Wozniewski,TOR,D\n");
    s.append("Ed,Belfour,TOR,G\n");
    s.append("Mikael,Tellqvist,TOR,G\n");
    s.append("Jean-Sebastien,Aubin,TOR,G\n");
    s.append("Matt,Cooke,VAN,F\n");
    s.append("Trevor,Linden,VAN,F\n");
    s.append("Brendan,Morrison,VAN,F\n");
    s.append("Henrik,Sedin,VAN,F\n");
    s.append("Josh,Green,VAN,F\n");
    s.append("Markus,Naslund,VAN,F\n");
    s.append("Jozef,Balej,VAN,F\n");
    s.append("Jarkko,Ruutu,VAN,F\n");
    s.append("Daniel,Sedin,VAN,F\n");
    s.append("Todd,Bertuzzi,VAN,F\n");
    s.append("Tyler,Bouck,VAN,F\n");
    s.append("Anson,Carter,VAN,F\n");
    s.append("Richard,Park,VAN,F\n");
    s.append("Lee,Goren,VAN,F\n");
    s.append("Ryan,Kesler,VAN,F\n");
    s.append("Jason,King,VAN,F\n");
    s.append("Bryan,Allen,VAN,D\n");
    s.append("Sven,Butenschon,VAN,D\n");
    s.append("Wade,Brookbank,VAN,D\n");
    s.append("Nolan,Baumgartner,VAN,D\n");
    s.append("Ed,Jovanovski,VAN,D\n");
    s.append("Steve,McCarthy,VAN,D\n");
    s.append("Mattias,Ohlund,VAN,D\n");
    s.append("Sami,Salo,VAN,D\n");
    s.append("Dan,Cloutier,VAN,G\n");
    s.append("Alex,Auld,VAN,G\n");
    s.append("Jared,Aulin,WAS,F\n");
    s.append("Andrew,Cassels,WAS,F\n");
    s.append("Jeff,Halpern,WAS,F\n");
    s.append("Alexander,Ovechkin,WAS,F\n");
    s.append("Matt,Pettinger,WAS,F\n");
    s.append("Alexander,Semin,WAS,F\n");
    s.append("Matt,Bradley,WAS,F\n");
    s.append("Chris,Clark,WAS,F\n");
    s.append("Ben,Clymer,WAS,F\n");
    s.append("Stephen,Peat,WAS,F\n");
    s.append("Brian,Willsie,WAS,F\n");
    s.append("Miroslav,Zalesak,WAS,F\n");
    s.append("Dainius,Zubrus,WAS,F\n");
    s.append("Brian,Sutherby,WAS,F\n");
    s.append("Chris,Bourque,WAS,F\n");
    s.append("Jeff,Friesen,WAS,F\n");
    s.append("Boyd,Gordon,WAS,F\n");
    s.append("Petr,Sykora,WAS,F\n");
    s.append("Mathieu,Biron,WAS,D\n");
    s.append("Steve,Eminger,WAS,D\n");
    s.append("Ivan,Majesky,WAS,D\n");
    s.append("Bryan,Muir,WAS,D\n");
    s.append("Brendan,Witt,WAS,D\n");
    s.append("Jakub,Cutta,WAS,D\n");
    s.append("Mike,Green,WAS,D\n");
    s.append("Jamie,Heward,WAS,D\n");
    s.append("Shaone,Morrisonn,WAS,D\n");
    s.append("Lawrence,Nycholat,WAS,D\n");
    s.append("Nolan,Yonkman,WAS,D\n");
    s.append("Olaf,Kolzig,WAS,G\n");
    s.append("Maxime,Ouellet,WAS,G\n");
    s.append("Brent,Johnson,WAS,G\n");
    final Reader reader = new StringReader(s.toString());
    final PlayerParser playerParser = new CsvPlayerParser(reader);
    try
    {
      playersByTeamAndPosition = playerParser.getPlayersByTeamAndPosition();
    }
    catch (final Exception e)
    {
      throw new RuntimeException(e);
    }
  }

  public void addPlayer(final Player player)
  {
    playersByTeamAndPosition.addPlayer(player);
  }

  public Map<Position, Set<Player>> getPlayersOnTeam(final Team team)
  {
    return playersByTeamAndPosition.getPlayersOnTeam(team);
  }

  public Set<Player> getPlayersOnTeamAtPosition(final Team team, final Position position)
  {
    return playersByTeamAndPosition.getPlayersOnTeamAtPosition(team, position);
  }

  public Set<Team> getTeams()
  {
    return playersByTeamAndPosition.getTeams();
  }

  public int getNumPlayers()
  {
    return playersByTeamAndPosition.getNumPlayers();
  }
}
