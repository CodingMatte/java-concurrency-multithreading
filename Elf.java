
package matteo_incicco;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Elf extends Thread
{
    private Sack sack;
    private Random rnd;
    private Semaphore personal;
    
    
    public Elf(Sack s, int i)
    {
        super("Elfo " + i);
        this.sack = s;
        this.rnd = new Random();
        this.personal = new Semaphore(0);
        
        
    }
    
    
    
    @Override
    public void run()
    {
        for(int i = 0;
            i < 5;
            i++)
        {
            
            System.out.println(getName() + " prepare gift");
            
            try {
                Thread.sleep(this.rnd.nextInt(200) + 100);
            
            
            this.sack.giftDeposit(this);
            
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
            
        }
        
        
    }
    
    
    
    public void suspend() throws InterruptedException
    {
        this.personal.acquire();
    }
    
    
    public void wakeup()
    {
        this.personal.release();
    }
    
}
