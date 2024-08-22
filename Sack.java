
package matteo_incicco;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;


public class Sack
{
   private ReentrantLock lock;
   private Semaphore sem_elfs;
   private int santaclaussack;
   private Semaphore sem_santa;
    private ArrayList<Elf> queue;
   private Elf elftowakeup;
   
    public Sack()
    {
      this.lock = new ReentrantLock();  
      this.sem_elfs = new Semaphore(3, true);
      this.santaclaussack = 0;
      this.sem_santa = new Semaphore(0);
      this.queue = new ArrayList();
    }
    
    
    public void giftDeposit(Elf e) throws InterruptedException
    {
       
        this.sem_elfs.acquire();
       
        
        System.out.println(e.getName() + " deposit the gift");
        
        this.lock.lock();
        try{
        
        this.santaclaussack++;
        
        this.queue.add(e);
        
        if(this.santaclaussack == 3)
        {
          // wake up Santa Clouse
          System.out.println("There are 3 presents in the sack! Santa Clause wake up!");
          this.sem_santa.release(3);
        }
        
  
        }finally{
            this.lock.unlock();
        }
        e.suspend();
        
    }
    
    
    
    public void checkGifts(SantaClause b) throws InterruptedException
    {
        this.sem_santa.acquire();
        
        System.out.println("Santa Claus check the gifts");
        
        
        this.elftowakeup = this.queue.get(this.queue.size() - 1);
        System.out.println(this.queue.size());
        Thread.sleep(100);
        
        this.queue.remove(this.elftowakeup);
        
        this.santaclaussack--;
        
        this.elftowakeup.wakeup();
        
        if(this.santaclaussack == 0)
        {
            // wake up all the other elfs
            this.sem_elfs.release(3);
            
        }
        
        
    }
    
    
    
    
    
}
