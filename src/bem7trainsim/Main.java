package bem7trainsim;

public class Main {

    /**
     * Starts the application
     * @param args console args
     */
    public static void main(String[] args) {
        try(Controller ctrl = new Controller())
        {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
