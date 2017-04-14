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
    private enum State{ MAIN_MENU, LEVEL_MENU, PLAY, TEST }
    private State state = State.MAIN_MENU;
    
    private void moveTrains() throws CollisionException {
        for (Train train :
                trains) {
            train.move();
        }
    }

    private void handleCommand(String command) {
        //TODO: Kezel egy parancsot, és futtatja a hozzá tartozó műveleteket
        String[] s = command.split(" ");
        switch (state){
        case MAIN_MENU:
        	switch (s[0]) {
            case "play":
                state = State.LEVEL_MENU;
                System.out.println("LEVEL_MENU");
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
        	if(s[0].startsWith("map_")){
        		try{
        			state = State.PLAY;	
	        		loadMap(s[0]);
        		} catch(IOException e){
        			System.out.println(e.getMessage());
        		}
        		
        	} else if (s[0].startsWith("test_")){
        		try{
        			state = State.TEST;
	        		loadMap(s[0]);
        		} catch(IOException e){
        			System.out.println(e.getMessage());
        		}
        	} else if (s[0].equals("back")){
        		state = State.MAIN_MENU;
        		System.out.println("MAIN_MENU");
        	}
        	break;
        	//TODO play és test bemenetek
        case PLAY: break;
        case TEST: break;
        default: break;
        }
    }

    private void loadMap(String mapFileName) throws IOException{
    	Field[][] fields;
    	int rows, columns; // a pálya sorainak és oszlopainak száma
    	int startX, startY; // a kezdő sín
    	String testString; // a test végén kiírandó string
    	
		BufferedReader br = new BufferedReader( new InputStreamReader (new FileInputStream(mapFileName + ".txt"), "UTF-8"));
    	//pálya méretének beolvasása
    	String line;
    	if((line = br.readLine()) != null){
    		String[] nums = line.split(""); // ez azért így van, mert UTF-8-nál valamiért az első szám elé még beolvasott egy valamit, amit a split(" ") nem szedett ki
    		rows = Integer.parseInt(nums[1]);
    		columns = Integer.parseInt(nums[3]);
			fields = new Field[rows][columns];
    	} else throw new IOException("Nem sikerült a pálya méretének beolvasása.");
    	br.readLine();
    	
    	
    	//ha test-et olvasunk be, akkor be kell olvasni a kiírandó szöveget
    	if(state == State.TEST){
    		if((line = br.readLine()) != null){
    			testString = line;
    		} else throw new IOException("Nem sikerült a teszt szövegnek beolvasása.");
    		br.readLine();
    	}
    	
    	
    	//beolvassuk a kezdő pozíciót
    	if((line = br.readLine()) != null){
    		String[] nums = line.split(" ");
    		startY = Integer.parseInt(nums[0]);
    		startX = Integer.parseInt(nums[1]);
    	} else throw new IOException("Nem sikerült a kezdő pozíció beolvasása.");
    	br.readLine();
    	
    	//beolvassuk a mezőket
    	int lineNum = 0;
    	char[][] charMap = new char[rows][columns];
    	while((line = br.readLine()) != null && lineNum <= rows){
    		if(lineNum == 0 || lineNum == rows + 1){
    			if(!line.matches("#[1-9]+#")){
    				throw new IOException("Nem megfelelően struktúrált a pályaleírás eleje és vége.");
    			}
    		} else {
    			if(!line.startsWith(Integer.toString(lineNum)) || !line.endsWith(Integer.toString(lineNum)))
    				throw new IOException("Nem egyezik meg az " + Integer.toString(lineNum) + ". sor számozása.");
    		
    			// feltöltjük a char[][] egy sorát a beolvasott karakterekkel
    			for(int i = 0; i < columns; i++){
    				charMap[lineNum-1][i] = line.charAt(i+1);
    			}
    		}
    		lineNum++;
    	}
    		
		//A karakterek függvényében feltöltjük a Fields[][] tömböt
    	//Ekkor még csak síneket és váltókat látunk
		for(int y = 0; y < rows; y++){
			for(int x = 0; x < columns; x++){
				switch(charMap[y][x]){
				//Oriantation CORRECT
    				case '╗': fields[y][x] = new SimpleRail(SimpleRail.Orientation.BOTTOM_LEFT); break;
    				case '╝': fields[y][x] = new SimpleRail(SimpleRail.Orientation.TOP_LEFT); break;
    				case '╚': fields[y][x] = new SimpleRail(SimpleRail.Orientation.TOP_RIGHT); break;
    				case '╔': fields[y][x] = new SimpleRail(SimpleRail.Orientation.BOTTOM_RIGHT); break;
    				case '═': fields[y][x] = new SimpleRail(SimpleRail.Orientation.HORIZONTAL); break;
    				case '║': fields[y][x] = new SimpleRail(SimpleRail.Orientation.VERTICAL); break;
        		//Oriantation WRONG
    				case '┐': fields[y][x] = new Switch(Switch.Orientation.WestRight); break;
    				case '└': fields[y][x] = new Switch(Switch.Orientation.EastRight); break;
    				case '┘': fields[y][x] = new Switch(Switch.Orientation.WestLeft); break;
    				case '┌': fields[y][x] = new Switch(Switch.Orientation.EastLeft); break;
    					
    				case '╬': fields[y][x] = new CrossRail(); break;
    				default : fields[y][x] = new Decoration(); break;
				}
			}
		}
		br.readLine();
		
		//beolvassuk az alagutakat és állomásokat
		//Frissítjük a charMap[][] tartalmát is
		while((line = br.readLine()).length() > 1){
			String[] s = line.split(" ");
			int y = Integer.parseInt(s[1]) - 1;
			int x = Integer.parseInt(s[2]) - 1;
			switch(s[0].charAt(0)){
			//DownStation
				case 'I':
					fields[y][x] = new DownStation(Color.RED, ((SimpleRail)fields[y][x]).orientation);
					charMap[y][x] = 'I';
					break;
				case 'Á':
					fields[y][x] = new DownStation(Color.YELLOW, ((SimpleRail)fields[y][x]).orientation);
					charMap[y][x] = 'Á';
					break;
				case 'Ö':
					fields[y][x] = new DownStation(Color.GREEN, ((SimpleRail)fields[y][x]).orientation);
					charMap[y][x] = 'Ö';
					break;
				case 'É':
					fields[y][x] = new DownStation(Color.BLUE, ((SimpleRail)fields[y][x]).orientation);
					charMap[y][x] = 'É';
					break;
			//UpStation
				case 'i':
					fields[y][x] = new UpStation(Color.RED, ((SimpleRail)fields[y][x]).orientation);
					charMap[y][x] = 'i';
					break;
				case 'á':
					fields[y][x] = new UpStation(Color.YELLOW, ((SimpleRail)fields[y][x]).orientation);
					charMap[y][x] = 'á';
					break;
				case 'ö':
					fields[y][x] = new UpStation(Color.GREEN, ((SimpleRail)fields[y][x]).orientation);
					charMap[y][x] = 'ö';
					break;
				case 'é':
					fields[y][x] = new UpStation(Color.BLUE, ((SimpleRail)fields[y][x]).orientation);
					charMap[y][x] = 'é';
					break;
			//TunnelEntrance
				case 't':
					fields[y][x] = new TunnelEntrance(((SimpleRail)fields[y][x]).orientation);
					charMap[y][x] = 't';
					break;
			}
		}
		
		//Összekapcsoljuk a síneket
		//A vonatok csak a fenti vagy a bal oldali pálya szélen indulhatnak el: '║' | '═'
		//Váltó mellett nem állhat váltó és alagútbejárat sem!
		for(int y = 0; y < rows; y++){
			for(int x = 0; x < columns; x++){
				switch(charMap[y][x]){
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
    					switch(((SimpleRail) fields[y][x]).orientation){
	    					case BOTTOM_LEFT:
	    						((SimpleRail) fields[y][x]).addLink((Rail)fields[y][x-1]);
			    				((SimpleRail) fields[y][x]).addLink((Rail)fields[y+1][x]);
			    				break;
	    					case TOP_LEFT:
		    					((SimpleRail) fields[y][x]).addLink((Rail)fields[y][x-1]);
		    					((SimpleRail) fields[y][x]).addLink((Rail)fields[y-1][x]);
		    					break;
	    					case TOP_RIGHT:
		    					((SimpleRail) fields[y][x]).addLink((Rail)fields[y][x+1]);
		    					((SimpleRail) fields[y][x]).addLink((Rail)fields[y-1][x]);
		    					break;
	    					case BOTTOM_RIGHT:
		    					((SimpleRail) fields[y][x]).addLink((Rail)fields[y][x+1]);
		    					((SimpleRail) fields[y][x]).addLink((Rail)fields[y+1][x]);
		    					break;
	    					case HORIZONTAL:
		    					if(y+1 == startY && x+1 == startX){
		    						((SimpleRail) fields[y][x]).addLink((Rail)fields[y][x+1]);
		    					} else {
		    						((SimpleRail) fields[y][x]).addLink((Rail)fields[y][x-1]);
		    						((SimpleRail) fields[y][x]).addLink((Rail)fields[y][x+1]);
		    					}
		    					break;
	    					case VERTICAL:
		    					if(y+1 == startY && x+1 == startX){
		    						((SimpleRail) fields[y][x]).addLink((Rail)fields[y+1][x]);
		    					} else {
		    						((SimpleRail) fields[y][x]).addLink((Rail)fields[y-1][x]);
		    						((SimpleRail) fields[y][x]).addLink((Rail)fields[y+1][x]);
		    					}
		    					break;
    					}
    					break;
    			//A váltóknál varázsolni kell, ezért mindegyiknél megvizsgálom a tőle jobbra levő mezőt
    			//Ha ez dekoráció, másfele mutató sín vagy állomás, aminek az orientációja más felé néz, akkor az egyik eset van
    			//különben a másik
    				case '┐':
    					if(charMap[y][x+1] == ' ' ||
    						"╚╔║".contains(Character.toString(charMap[y][x+1])) ||
    						"IÁÖÉiáöé".contains(Character.toString(charMap[y][x+1])) &&
    							(((SimpleRail) fields[y][x]).orientation == SimpleRail.Orientation.VERTICAL ||
    							 ((SimpleRail) fields[y][x]).orientation == SimpleRail.Orientation.TOP_RIGHT ||
    							 ((SimpleRail) fields[y][x]).orientation == SimpleRail.Orientation.BOTTOM_RIGHT))
    					{
    						((Switch)fields[y][x]).setOrientation(Switch.Orientation.SouthLeft);
    						((Switch)fields[y][x]).addLink((Rail)fields[y-1][x]);
    						((Switch)fields[y][x]).addLink((Rail)fields[y+1][x]);
    						((Switch)fields[y][x]).addLinkToChange((Rail)fields[y][x-1]);
    					} else {
    						((Switch)fields[y][x]).setOrientation(Switch.Orientation.WestRight);
    						((Switch)fields[y][x]).addLink((Rail)fields[y][x-1]);
    						((Switch)fields[y][x]).addLink((Rail)fields[y][x+1]);
    						((Switch)fields[y][x]).addLinkToChange((Rail)fields[y+1][x]);
    					}
    					break;
    				case '└':
    					if(charMap[y][x-1] == ' ' ||
						"╗╝║".contains(Character.toString(charMap[y][x-1])) ||
						"IÁÖÉiáöé".contains(Character.toString(charMap[y][x-1])) &&
							(((SimpleRail) fields[y][x]).orientation == SimpleRail.Orientation.VERTICAL ||
							 ((SimpleRail) fields[y][x]).orientation == SimpleRail.Orientation.TOP_LEFT ||
							 ((SimpleRail) fields[y][x]).orientation == SimpleRail.Orientation.BOTTOM_LEFT))
    					{
    						((Switch)fields[y][x]).setOrientation(Switch.Orientation.NorthLeft);
    						((Switch)fields[y][x]).addLink((Rail)fields[y-1][x]);
    						((Switch)fields[y][x]).addLink((Rail)fields[y+1][x]);
    						((Switch)fields[y][x]).addLinkToChange((Rail)fields[y][x+1]);
    					} else {
    						((Switch)fields[y][x]).setOrientation(Switch.Orientation.EastRight);
    						((Switch)fields[y][x]).addLink((Rail)fields[y][x-1]);
    						((Switch)fields[y][x]).addLink((Rail)fields[y][x+1]);
    						((Switch)fields[y][x]).addLinkToChange((Rail)fields[y-1][x]);
    					}
    					break;
    				case '┘':
    					if(charMap[y][x+1] == ' ' ||
						"╚╔║".contains(Character.toString(charMap[y][x+1])) ||
						"IÁÖÉiáöé".contains(Character.toString(charMap[y][x+1])) &&
							(((SimpleRail) fields[y][x]).orientation == SimpleRail.Orientation.VERTICAL ||
							 ((SimpleRail) fields[y][x]).orientation == SimpleRail.Orientation.TOP_RIGHT ||
							 ((SimpleRail) fields[y][x]).orientation == SimpleRail.Orientation.BOTTOM_RIGHT))
    					{
    						((Switch)fields[y][x]).setOrientation(Switch.Orientation.NorthRight);
    						((Switch)fields[y][x]).addLink((Rail)fields[y-1][x]);
    						((Switch)fields[y][x]).addLink((Rail)fields[y+1][x]);
    						((Switch)fields[y][x]).addLinkToChange((Rail)fields[y][x-1]);
    					} else {
    						((Switch)fields[y][x]).setOrientation(Switch.Orientation.WestLeft);
    						((Switch)fields[y][x]).addLink((Rail)fields[y][x-1]);
    						((Switch)fields[y][x]).addLink((Rail)fields[y][x+1]);
    						((Switch)fields[y][x]).addLinkToChange((Rail)fields[y-1][x]);
    					}
    					break;
    				case '┌':
    					if(charMap[y][x-1] == ' ' ||
						"╗╝║".contains(Character.toString(charMap[y][x-1])) ||
						"IÁÖÉiáöé".contains(Character.toString(charMap[y][x-1])) &&
							(((SimpleRail) fields[y][x]).orientation == SimpleRail.Orientation.VERTICAL ||
							 ((SimpleRail) fields[y][x]).orientation == SimpleRail.Orientation.TOP_LEFT ||
							 ((SimpleRail) fields[y][x]).orientation == SimpleRail.Orientation.BOTTOM_LEFT))
    					{
    						((Switch)fields[y][x]).setOrientation(Switch.Orientation.SouthRight);
    						((Switch)fields[y][x]).addLink((Rail)fields[y-1][x]);
    						((Switch)fields[y][x]).addLink((Rail)fields[y+1][x]);
    						((Switch)fields[y][x]).addLinkToChange((Rail)fields[y][x+1]);
    					} else {
    						((Switch)fields[y][x]).setOrientation(Switch.Orientation.EastLeft);
    						((Switch)fields[y][x]).addLink((Rail)fields[y][x-1]);
    						((Switch)fields[y][x]).addLink((Rail)fields[y][x+1]);
    						((Switch)fields[y][x]).addLinkToChange((Rail)fields[y+1][x]);
    					}
    					break;
    			//Kereszteződésnél minden kell
    				case '╬':
    					((CrossRail)fields[y][x]).addLink((Rail)fields[y][x-1]);
    					((CrossRail)fields[y][x]).addLink((Rail)fields[y][x+1]);
    					((CrossRail)fields[y][x]).addLinkToCross((Rail)fields[y-1][x]);
    					((CrossRail)fields[y][x]).addLinkToCross((Rail)fields[y+1][x]);
    					break;
    			//Alagútbejárat
    				case 't':
    					switch(((TunnelEntrance)fields[y][x]).orientation){
	    					case BOTTOM_LEFT:
	    						((TunnelEntrance) fields[y][x]).addLink((Rail)fields[y][x-1]);
			    				((TunnelEntrance) fields[y][x]).addLink((Rail)fields[y+1][x]);
			    				break;
	    					case TOP_LEFT:
		    					((TunnelEntrance) fields[y][x]).addLink((Rail)fields[y][x-1]);
		    					((TunnelEntrance) fields[y][x]).addLink((Rail)fields[y-1][x]);
		    					break;
	    					case TOP_RIGHT:
		    					((TunnelEntrance) fields[y][x]).addLink((Rail)fields[y][x+1]);
		    					((TunnelEntrance) fields[y][x]).addLink((Rail)fields[y-1][x]);
		    					break;
	    					case BOTTOM_RIGHT:
		    					((TunnelEntrance) fields[y][x]).addLink((Rail)fields[y][x+1]);
		    					((TunnelEntrance) fields[y][x]).addLink((Rail)fields[y+1][x]);
		    					break;
	    					case HORIZONTAL:
	    						((TunnelEntrance) fields[y][x]).addLink((Rail)fields[y][x-1]);
		    					((TunnelEntrance) fields[y][x]).addLink((Rail)fields[y][x+1]);
		    					break;
	    					case VERTICAL:
		    					((TunnelEntrance) fields[y][x]).addLink((Rail)fields[y-1][x]);
		    					((TunnelEntrance) fields[y][x]).addLink((Rail)fields[y+1][x]);
		    					break;
    					}
    					break;
    				default : break;
				}
			}
		}

    	br.close();
    }
    
    public void start() {
        // TODO: ElindĂ­tja az esemĂ©nyek kezelĂ©sĂ©t. Ha teszt van betĂ¶ltve, akkor futtatja a tesztet, majd a vĂ©gĂ©n kiĂ­rja a vĂ©gsĹ‘ Ăˇllapotot.
        run = true;
        System.out.println("MAIN_MENU");
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
