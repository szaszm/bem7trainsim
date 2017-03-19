package bem7trainsim.TestCases;

/**
 * Created by marci on 2017.03.19..
 */
public abstract class BaseTestCase {
    protected abstract void execute() throws Exception;

    public final void call() {
        System.out.println("START: "+getName());
        try {
            execute();
        } catch (Exception e) {
            System.out.print("EXCEPTION "+e.getClass().getName()+": "+e.getMessage()+"     @ ");
            StackTraceElement[] trace = e.getStackTrace();
            for(int i = 0; i < 3 && i < trace.length; ++i) {
                System.out.print(trace[i] + ", ");
            }
            System.out.println();
        }
        System.out.println("STOP:  "+getName());
    }

    public String getName() {
        return this.getClass().getName();
    }
}
