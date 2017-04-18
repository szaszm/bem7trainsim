package bem7trainsim;

import javafx.util.Pair;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

/**
 * Created by Csuto on 4/18/2017.
 */
public class TableLoader {

    /**
     * The list of the trains which have already started
     */
    protected List<Train> trains;

    /**
     * The list of UpStations
     */
    public List<UpStation> upstations;

    /**
     * The starting rail of the trains. Needed for train construction.
     */
    public Rail startRail;

    /**
     * The start time and wagons of the trains which have not yet started
     */
    public List<Pair<Integer, List<Wagon>>> trainData;

    private Field[][] fields;
    private char[][] charMap;
    private int rows, columns; // a pálya sorainak és oszlopainak száma
    private int startX, startY; // a kezdő sín

    public Table LoadTable(String mapFileName) throws IOException {
        //TODO: make test use this.

        String pathPrefix = "map/";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pathPrefix + mapFileName + ".txt"), "UTF-8"));
        //pálya méretének beolvasása
        String line;
        LoadSize(br);

        //beolvassuk a kezdő pozíciót
        LoadStart(br);

        //beolvassuk a mezőket
        LoadChars(br);

        //A karakterek függvényében feltöltjük a Fields[][] tömböt
        //Ekkor még csak síneket és váltókat látunk
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                switch (charMap[y][x]) {
                    //Orientation CORRECT
                    case '╗':
                        fields[y][x] = new SimpleRail(SimpleRail.Orientation.BOTTOM_LEFT);
                        break;
                    case '╝':
                        fields[y][x] = new SimpleRail(SimpleRail.Orientation.TOP_LEFT);
                        break;
                    case '╚':
                        fields[y][x] = new SimpleRail(SimpleRail.Orientation.TOP_RIGHT);
                        break;
                    case '╔':
                        fields[y][x] = new SimpleRail(SimpleRail.Orientation.BOTTOM_RIGHT);
                        break;
                    case '═':
                        fields[y][x] = new SimpleRail(SimpleRail.Orientation.HORIZONTAL);
                        break;
                    case '║':
                        fields[y][x] = new SimpleRail(SimpleRail.Orientation.VERTICAL);
                        break;
                    //Orientation WRONG
                    case '┐':
                        fields[y][x] = new Switch(Switch.Orientation.WestRight);
                        break;
                    case '└':
                        fields[y][x] = new Switch(Switch.Orientation.EastRight);
                        break;
                    case '┘':
                        fields[y][x] = new Switch(Switch.Orientation.WestLeft);
                        break;
                    case '┌':
                        fields[y][x] = new Switch(Switch.Orientation.EastLeft);
                        break;

                    case '╬':
                        fields[y][x] = new CrossRail();
                        break;
                    default:
                        fields[y][x] = new Decoration();
                        break;
                }
            }
        }
        br.readLine();

        // ez a lista gyűjti az alagútbejáratokat, hogy a Table konstruktorában könnyen használhassuk őket
        ArrayList<TunnelEntrance> tunnelEntrances = new ArrayList<>();

        //beolvassuk az alagutakat és állomásokat
        //Frissítjük a charMap[][] tartalmát is
        upstations = new ArrayList<>();
        while ((line = br.readLine()).length() > 1) {
            String[] s = line.split(" ");
            int x = Integer.parseInt(s[1]) - 1;
            int y = Integer.parseInt(s[2]) - 1;
            switch (s[0].charAt(0)) {
                //DownStation
                case 'I':
                    fields[y][x] = new DownStation(Color.RED, ((SimpleRail) fields[y][x]).orientation);
                    charMap[y][x] = 'I';
                    break;
                case 'Á':
                    fields[y][x] = new DownStation(Color.YELLOW, ((SimpleRail) fields[y][x]).orientation);
                    charMap[y][x] = 'Á';
                    break;
                case 'Ö':
                    fields[y][x] = new DownStation(Color.GREEN, ((SimpleRail) fields[y][x]).orientation);
                    charMap[y][x] = 'Ö';
                    break;
                case 'É':
                    fields[y][x] = new DownStation(Color.BLUE, ((SimpleRail) fields[y][x]).orientation);
                    charMap[y][x] = 'É';
                    break;
                //UpStation
                case 'i':
                    fields[y][x] = new UpStation(Color.RED, ((SimpleRail) fields[y][x]).orientation);
                    upstations.add((UpStation) fields[y][x]);
                    charMap[y][x] = 'i';
                    break;
                case 'á':
                    fields[y][x] = new UpStation(Color.YELLOW, ((SimpleRail) fields[y][x]).orientation);
                    upstations.add((UpStation) fields[y][x]);
                    charMap[y][x] = 'á';
                    break;
                case 'ö':
                    fields[y][x] = new UpStation(Color.GREEN, ((SimpleRail) fields[y][x]).orientation);
                    upstations.add((UpStation) fields[y][x]);
                    charMap[y][x] = 'ö';
                    break;
                case 'é':
                    fields[y][x] = new UpStation(Color.BLUE, ((SimpleRail) fields[y][x]).orientation);
                    upstations.add((UpStation) fields[y][x]);
                    charMap[y][x] = 'é';
                    break;
                //TunnelEntrance
                case 't':
                    //Tunnel paraméter null, mivel még nincs meg az alagutunk, azt majd csak a Table hozza létre
                    TunnelEntrance tunnelEntrance = new TunnelEntrance(((SimpleRail) fields[y][x]).orientation);
                    tunnelEntrances.add(tunnelEntrance);
                    fields[y][x] = tunnelEntrance;
                    charMap[y][x] = 't';
                    break;
            }
        }

        trains = new ArrayList<>();
        ArrayList<String> trainLines = new ArrayList<>();
        while ((line = br.readLine()) != null && line.length() > 0) {
            trainLines.add(line);
        }
        startRail = (Rail) fields[startY - 1][startX - 1];
        trainData = new ArrayList<>();
        for (String trainLine : trainLines) {
            String[] spl = trainLine.split(" ");
            int start = Integer.parseInt(spl[0]);
            String[] wagonColors = spl[1].split("");
            ArrayList<Wagon> wagons = new ArrayList<>();

            for (String color : wagonColors) {
                Wagon wagon;
                switch (color) {
                    case "P":
                        wagon = new Wagon(Color.RED, false);
                        break;
                    case "S":
                        wagon = new Wagon(Color.YELLOW, false);
                        break;
                    case "Z":
                        wagon = new Wagon(Color.GREEN, false);
                        break;
                    case "K":
                        wagon = new Wagon(Color.BLUE, false);
                        break;
                    case "F":
                        wagon = new Wagon(Color.BLACK);
                        break;
                    case "p":
                        wagon = new Wagon(Color.RED, true);
                        break;
                    case "s":
                        wagon = new Wagon(Color.YELLOW, true);
                        break;
                    case "z":
                        wagon = new Wagon(Color.GREEN, true);
                        break;
                    case "k":
                        wagon = new Wagon(Color.BLUE, true);
                        break;
                    default:
                        throw new IOException("Nem megfelelo vonat leiras: " + trainLine);
                }
                wagons.add(wagon);
            }

            trainData.add(new Pair<>(start, wagons));
        }


        //Összekapcsoljuk a síneket
        //A vonatok csak a fenti vagy a bal oldali pálya szélen indulhatnak el: '║' | '═'
        //Váltó mellett nem állhat váltó és alagútbejárat sem!
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                switch (charMap[y][x]) {
                    //Ezek mind SimpleRail leszármazottak, ezért egyben kezelhetők
                    case '╗':
                    case '╝':
                    case '╚':
                    case '╔':
                    case '═':
                    case '║':
                    case 'I':
                    case 'Á':
                    case 'Ö':
                    case 'É':
                    case 'i':
                    case 'á':
                    case 'ö':
                    case 'é':
                        switch (((SimpleRail) fields[y][x]).orientation) {
                            case BOTTOM_LEFT:
                                ((SimpleRail) fields[y][x]).addLink((Rail) fields[y][x - 1]);
                                ((SimpleRail) fields[y][x]).addLink((Rail) fields[y + 1][x]);
                                break;
                            case TOP_LEFT:
                                ((SimpleRail) fields[y][x]).addLink((Rail) fields[y][x - 1]);
                                ((SimpleRail) fields[y][x]).addLink((Rail) fields[y - 1][x]);
                                break;
                            case TOP_RIGHT:
                                ((SimpleRail) fields[y][x]).addLink((Rail) fields[y][x + 1]);
                                ((SimpleRail) fields[y][x]).addLink((Rail) fields[y - 1][x]);
                                break;
                            case BOTTOM_RIGHT:
                                ((SimpleRail) fields[y][x]).addLink((Rail) fields[y][x + 1]);
                                ((SimpleRail) fields[y][x]).addLink((Rail) fields[y + 1][x]);
                                break;
                            case HORIZONTAL:
                                if (y + 1 == startY && x + 1 == startX) {
                                    ((SimpleRail) fields[y][x]).addLink((Rail) fields[y][x + 1]);
                                } else {
                                    if (x > 0) ((SimpleRail) fields[y][x]).addLink((Rail) fields[y][x - 1]);
                                    if (x < fields[y].length - 1)
                                        ((SimpleRail) fields[y][x]).addLink((Rail) fields[y][x + 1]);
                                }
                                break;
                            case VERTICAL:
                                if (y + 1 == startY && x + 1 == startX) {
                                    ((SimpleRail) fields[y][x]).addLink((Rail) fields[y + 1][x]);
                                } else {
                                    if (y > 0) ((SimpleRail) fields[y][x]).addLink((Rail) fields[y - 1][x]);
                                    if (y < fields.length - 1)
                                        ((SimpleRail) fields[y][x]).addLink((Rail) fields[y + 1][x]);
                                }
                                break;
                        }
                        break;
                    //A váltóknál varázsolni kell, ezért mindegyiknél megvizsgálom a tőle jobbra levő mezőt
                    //Ha ez dekoráció, másfele mutató sín vagy állomás, aminek az orientációja más felé néz, akkor az egyik eset van
                    //különben a másik
                    case '┐':
                        if (charMap[y][x + 1] == ' ' ||
                                "╚╔║".contains(Character.toString(charMap[y][x + 1])) ||
                                ("IÁÖÉiáöé".contains(Character.toString(charMap[y][x + 1])) &&
                                        (((SimpleRail) fields[y][x + 1]).orientation == SimpleRail.Orientation.VERTICAL ||
                                                ((SimpleRail) fields[y][x + 1]).orientation == SimpleRail.Orientation.TOP_RIGHT ||
                                                ((SimpleRail) fields[y][x + 1]).orientation == SimpleRail.Orientation.BOTTOM_RIGHT))) {
                            ((Switch) fields[y][x]).setOrientation(Switch.Orientation.SouthLeft);
                            ((Switch) fields[y][x]).addLink((Rail) fields[y + 1][x]);
                            ((Switch) fields[y][x]).addLink((Rail) fields[y - 1][x]);
                            ((Switch) fields[y][x]).addLinkToChange((Rail) fields[y][x - 1]);
                        } else {
                            ((Switch) fields[y][x]).setOrientation(Switch.Orientation.WestRight);
                            ((Switch) fields[y][x]).addLink((Rail) fields[y][x - 1]);
                            ((Switch) fields[y][x]).addLink((Rail) fields[y][x + 1]);
                            ((Switch) fields[y][x]).addLinkToChange((Rail) fields[y + 1][x]);
                        }
                        break;
                    case '└':
                        if (charMap[y][x - 1] == ' ' ||
                                "╗╝║".contains(Character.toString(charMap[y][x - 1])) ||
                                ("IÁÖÉiáöé".contains(Character.toString(charMap[y][x - 1])) &&
                                        (((SimpleRail) fields[y][x - 1]).orientation == SimpleRail.Orientation.VERTICAL ||
                                                ((SimpleRail) fields[y][x - 1]).orientation == SimpleRail.Orientation.TOP_LEFT ||
                                                ((SimpleRail) fields[y][x - 1]).orientation == SimpleRail.Orientation.BOTTOM_LEFT))) {
                            ((Switch) fields[y][x]).setOrientation(Switch.Orientation.NorthLeft);
                            ((Switch) fields[y][x]).addLink((Rail) fields[y - 1][x]);
                            ((Switch) fields[y][x]).addLink((Rail) fields[y + 1][x]);
                            ((Switch) fields[y][x]).addLinkToChange((Rail) fields[y][x + 1]);
                        } else {
                            ((Switch) fields[y][x]).setOrientation(Switch.Orientation.EastRight);
                            ((Switch) fields[y][x]).addLink((Rail) fields[y][x + 1]);
                            ((Switch) fields[y][x]).addLink((Rail) fields[y][x - 1]);
                            ((Switch) fields[y][x]).addLinkToChange((Rail) fields[y - 1][x]);
                        }
                        break;
                    case '┘':
                        if (charMap[y][x + 1] == ' ' ||
                                "╚╔║".contains(Character.toString(charMap[y][x + 1])) ||
                                ("IÁÖÉiáöé".contains(Character.toString(charMap[y][x + 1])) &&
                                        (((SimpleRail) fields[y][x + 1]).orientation == SimpleRail.Orientation.VERTICAL ||
                                                ((SimpleRail) fields[y][x + 1]).orientation == SimpleRail.Orientation.TOP_RIGHT ||
                                                ((SimpleRail) fields[y][x + 1]).orientation == SimpleRail.Orientation.BOTTOM_RIGHT))) {
                            ((Switch) fields[y][x]).setOrientation(Switch.Orientation.NorthRight);
                            ((Switch) fields[y][x]).addLink((Rail) fields[y - 1][x]);
                            ((Switch) fields[y][x]).addLink((Rail) fields[y + 1][x]);
                            ((Switch) fields[y][x]).addLinkToChange((Rail) fields[y][x - 1]);
                        } else {
                            ((Switch) fields[y][x]).setOrientation(Switch.Orientation.WestLeft);
                            ((Switch) fields[y][x]).addLink((Rail) fields[y][x - 1]);
                            ((Switch) fields[y][x]).addLink((Rail) fields[y][x + 1]);
                            ((Switch) fields[y][x]).addLinkToChange((Rail) fields[y - 1][x]);
                        }
                        break;
                    case '┌':
                        if (charMap[y][x - 1] == ' ' ||
                                "╗╝║".contains(Character.toString(charMap[y][x - 1])) ||
                                ("IÁÖÉiáöé".contains(Character.toString(charMap[y][x - 1])) &&
                                        (((SimpleRail) fields[y][x - 1]).orientation == SimpleRail.Orientation.VERTICAL ||
                                                ((SimpleRail) fields[y][x - 1]).orientation == SimpleRail.Orientation.TOP_LEFT ||
                                                ((SimpleRail) fields[y][x - 1]).orientation == SimpleRail.Orientation.BOTTOM_LEFT))) {
                            ((Switch) fields[y][x]).setOrientation(Switch.Orientation.SouthRight);
                            ((Switch) fields[y][x]).addLink((Rail) fields[y + 11][x]);
                            ((Switch) fields[y][x]).addLink((Rail) fields[y - 1][x]);
                            ((Switch) fields[y][x]).addLinkToChange((Rail) fields[y][x + 1]);
                        } else {
                            ((Switch) fields[y][x]).setOrientation(Switch.Orientation.EastLeft);
                            ((Switch) fields[y][x]).addLink((Rail) fields[y][x + 1]);
                            ((Switch) fields[y][x]).addLink((Rail) fields[y][x - 1]);
                            ((Switch) fields[y][x]).addLinkToChange((Rail) fields[y + 1][x]);
                        }
                        break;
                    //Kereszteződésnél minden kell
                    case '╬':
                        ((CrossRail) fields[y][x]).addLink((Rail) fields[y][x - 1]);
                        ((CrossRail) fields[y][x]).addLink((Rail) fields[y][x + 1]);
                        ((CrossRail) fields[y][x]).addLinkToCross((Rail) fields[y - 1][x]);
                        ((CrossRail) fields[y][x]).addLinkToCross((Rail) fields[y + 1][x]);
                        break;
                    //Alagútbejárat
                    case 't':
                        switch (((TunnelEntrance) fields[y][x]).orientation) {
                            case BOTTOM_LEFT:
                                ((TunnelEntrance) fields[y][x]).addLink((Rail) fields[y][x - 1]);
                                ((TunnelEntrance) fields[y][x]).addLink((Rail) fields[y + 1][x]);
                                break;
                            case TOP_LEFT:
                                ((TunnelEntrance) fields[y][x]).addLink((Rail) fields[y][x - 1]);
                                ((TunnelEntrance) fields[y][x]).addLink((Rail) fields[y - 1][x]);
                                break;
                            case TOP_RIGHT:
                                ((TunnelEntrance) fields[y][x]).addLink((Rail) fields[y][x + 1]);
                                ((TunnelEntrance) fields[y][x]).addLink((Rail) fields[y - 1][x]);
                                break;
                            case BOTTOM_RIGHT:
                                ((TunnelEntrance) fields[y][x]).addLink((Rail) fields[y][x + 1]);
                                ((TunnelEntrance) fields[y][x]).addLink((Rail) fields[y + 1][x]);
                                break;
                            case HORIZONTAL:
                                ((TunnelEntrance) fields[y][x]).addLink((Rail) fields[y][x - 1]);
                                ((TunnelEntrance) fields[y][x]).addLink((Rail) fields[y][x + 1]);
                                break;
                            case VERTICAL:
                                ((TunnelEntrance) fields[y][x]).addLink((Rail) fields[y - 1][x]);
                                ((TunnelEntrance) fields[y][x]).addLink((Rail) fields[y + 1][x]);
                                break;
                        }
                        break;
                    default:
                        break;
                }
            }
        }

        br.close();

        //létrehozzuk a pályát, ő majd létrehozza az alagútbejáratot és összerak mindent
        return new Table(fields, tunnelEntrances);
    }

    private void LoadChars(BufferedReader br) throws IOException {
        String line;
        int lineNum = 0;
        charMap = new char[rows][columns];
        while ((line = br.readLine()) != null && lineNum <= rows) {
            if (lineNum == 0 || lineNum == rows + 1) {
                if (!line.matches("#[1-9]+#")) {
                    throw new IOException("Nem megfelelően struktúrált a pályaleírás eleje és vége.");
                }
            } else {
                if (!line.startsWith(Integer.toString(lineNum)) || !line.endsWith(Integer.toString(lineNum)))
                    throw new IOException("Nem egyezik meg az " + Integer.toString(lineNum) + ". sor számozása.");

                // feltöltjük a char[][] egy sorát a beolvasott karakterekkel
                for (int i = 0; i < columns; i++) {
                    charMap[lineNum - 1][i] = line.charAt(i + 1);
                }
            }
            lineNum++;
        }
    }

    private void LoadStart(BufferedReader br) throws IOException {
        String line;
        if ((line = br.readLine()) != null) {
            String[] nums = line.split(" ");
            startX = Integer.parseInt(nums[0]);
            startY = Integer.parseInt(nums[1]);
        } else throw new IOException("Nem sikerült a kezdő pozíció beolvasása.");
        br.readLine();
    }

    private void LoadSize(BufferedReader br) throws IOException {
        String line;
        if ((line = br.readLine()) != null) {
            String[] nums = line.split(" ");
            columns = Integer.parseInt(nums[0]);
            rows = Integer.parseInt(nums[1]);
            fields = new Field[rows][columns];
        } else throw new IOException("Nem sikerült a pálya méretének beolvasása.");
        br.readLine();
    }
}
