package bem7trainsim;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.awt.*;

/**
 * Created by Csuto on 4/9/2017.
 */
public class Controller {
    private Table table;
    private List<Train> trains;
    private boolean run;
    private boolean playstate = false, isGameRun = false, isCreditState = false;
    private enum State{ MAIN_MENU, LEVEL_MENU, PLAY, TEST }
    private State state;
    
    private void moveTrains() throws CollisionException {
        for (Train train :
                trains) {
            train.move();
        }
    }

    private void handleCommand(String command) {
        //TODO: Kezel egy parancsot, Ă©s futtatja a hozzĂˇ tartozĂł mĹ±veleteket.
        String[] s = command.split(" ");
        switch (state){
        case MAIN_MENU:
        	switch (s[0]) {
            case "play":
                state = State.LEVEL_MENU;
                break;
            case "credits":
                System.out.println("Csutorás Robin\nGnandt Balázs\nSzász Márton\nTamás Csongor\nZabó Kristóf");
                break;
            case "exit":
                run = false;
                break;
        	}
        	break;
        case LEVEL_MENU:
        	if(s[0].matches("^map_[0-9]+")){
        		try{
	        		loadMap(s[0]);
	        		state = State.PLAY;	
        		} catch(IOException e){
        			System.out.println(e.getMessage());
        		}
        		
        	} else if (s[0].matches("^test_")){
        		try{
	        		loadMap(s[0]);
	        		state = State.TEST;
        		} catch(IOException e){
        			System.out.println(e.getMessage());
        		}
        	} else if (s[0].equals("back")){
        		state = State.MAIN_MENU;
        	}
        	break;
        	//TODO play és test bemenetek
        case PLAY: break;
        case TEST: break;
        default: break;
        }
    }

    private void loadMap(String mapFileName) throws IOException{
        //TODO: BetĂ¶lt egy pĂˇlyĂˇt a megadott fĂˇjlbĂłl, Ă©s beĂˇllĂ­tja azt a jelenlegi pĂˇlyĂˇnak.
    	Field[][] fields;
    	int rows, columns; // a pálya sorainak és oszlopainak száma
    	int startX, startY; // a kezdő sín
    	String testString; // a test végén kiírandó string
    	
    	BufferedReader br = new BufferedReader( new InputStreamReader (new FileInputStream(mapFileName)));
    	//pálya méretének beolvasása
    	String line;
    	if((line = br.readLine()) != null){
    		String[] nums = line.split(" ");
    		rows = Integer.parseInt(nums[0]);
    		columns = Integer.parseInt(nums[1]);
			fields = new Field[rows][columns];
    	} else throw new IOException("Nem sikerült a pálya méretének beolvasása.");
    	br.readLine();
    	
    	
    	//ha test-et olvasunk be, akkor be kell olvasni a kiírandó szöveget
    	if(state == State.TEST){
    		if((line = br.readLine()) != null){
    			testString = line;
    		}
    		br.readLine();
    	} else throw new IOException("Nem sikerült a teszt szövegnek beolvasása.");
    	
    	
    	//beolvassuk a kezdő pozíciót
    	if((line = br.readLine()) != null){
    		String[] nums = line.split(" ");
    		startX = Integer.parseInt(nums[0]);
    		startY = Integer.parseInt(nums[1]);
    	} else throw new IOException("Nem sikerült a kezdő pozíció beolvasása.");
    	br.readLine();
    	
    	//beolvassuk a mezőket
    	int lineNum = 0;
    	char[][] charMap = new char[rows][columns];
    	while((line = br.readLine()) != null && lineNum <= rows){
    		if(lineNum == 0 || lineNum == rows + 1){
    			if(!line.matches("#[1-9]+#"))
    				throw new IOException("Nem megfelelően struktúrált a pályaleírás eleje és vége.");
    		}
    		if(!line.startsWith(Integer.toString(lineNum)) || !line.endsWith(Integer.toString(lineNum)))
    			throw new IOException("Nem egyezik meg az " + Integer.toString(lineNum) + ". sor számozása.");
    		
    		// feltöltjük a char[][] egy sorát a beolvasott karakterekkel
    		for(int i = 0; i < columns; i++){
    			charMap[lineNum][i] = line.charAt(i+1);
    		}
    		
    		//A karakterek függvényében feltöltjük a Fields[][] tömböt, még nem állítjuk be a sínek kapcsolódását és az irányokat sem
    		for(int y = 0; y < rows; y++){
    			for(int x = 0; x < columns; x++){
    				switch(charMap[y][x]){
	    				case '╗': fields[y][x] = new SimpleRail(SimpleRail.Orientation.BOTTOM_LEFT); break;
	    				case '╝': fields[y][x] = new SimpleRail(SimpleRail.Orientation.TOP_LEFT); break;
	    				case '╚': fields[y][x] = new SimpleRail(SimpleRail.Orientation.TOP_RIGHT); break;
	    				case '╔': fields[y][x] = new SimpleRail(SimpleRail.Orientation.BOTTOM_RIGHT); break;
	    				case '═': fields[y][x] = new SimpleRail(SimpleRail.Orientation.HORIZONTAL); break;
	    				case '║': fields[y][x] = new SimpleRail(SimpleRail.Orientation.VERTICAL); break;

	    				case 'I': fields[y][x] = new DownStation(Color.RED, SimpleRail.Orientation.HORIZONTAL); break;
	    				case 'Á': fields[y][x] = new DownStation(Color.YELLOW, SimpleRail.Orientation.HORIZONTAL); break;
	    				case 'Ö': fields[y][x] = new DownStation(Color.GREEN, SimpleRail.Orientation.HORIZONTAL); break;
	    				case 'É': fields[y][x] = new DownStation(Color.BLUE, SimpleRail.Orientation.HORIZONTAL); break;
	    				
	    				case 'i': fields[y][x] = new UpStation(Color.RED, SimpleRail.Orientation.HORIZONTAL); break;
	    				case 'á': fields[y][x] = new UpStation(Color.YELLOW, SimpleRail.Orientation.HORIZONTAL); break; 
	    				case 'ö': fields[y][x] = new UpStation(Color.GREEN, SimpleRail.Orientation.HORIZONTAL); break;
	    				case 'é': fields[y][x] = new UpStation(Color.BLUE, SimpleRail.Orientation.HORIZONTAL); break;
	    					
	    				case '┐': fields[y][x] = new Switch(Switch.Orientation.WestRight); break;
	    				case '└': fields[y][x] = new Switch(Switch.Orientation.EastRight); break;
	    				case '┘': fields[y][x] = new Switch(Switch.Orientation.WestLeft); break;
	    				case '┌': fields[y][x] = new Switch(Switch.Orientation.EastLeft); break;
	    					
	    				case '╬': fields[y][x] = new CrossRail(); break;
	    					
	    				case 't': fields[y][x] = new TunnelEntrance(Switch.Orientation.WestRight); break;
	    				default : fields[y][x] = new Decoration(); break;
    				}
    			}
    		}
    		
    		//TODO a charMap alapján beállítgatni fields mezőinek irányát és összekapcsolni a síneket 
    		
    		lineNum++;
    	}
    }
    
    public void start() {
        // TODO: ElindĂ­tja az esemĂ©nyek kezelĂ©sĂ©t. Ha teszt van betĂ¶ltve, akkor futtatja a tesztet, majd a vĂ©gĂ©n kiĂ­rja a vĂ©gsĹ‘ Ăˇllapotot.
        run = true;
        while (run) {
            BufferedReader buffer=new BufferedReader(new InputStreamReader(System.in));
            try {
                handleCommand(buffer.readLine());
            } catch (IOException e) {
                // TODO: do something
                e.printStackTrace();
            }
        }
    }
}
