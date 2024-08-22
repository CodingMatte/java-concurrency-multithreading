
package matteo_incicco;


public class SantaClause extends Thread
{
    private Sack sack;
    
    
    public SantaClause(Sack s)
    {
        super("SantaClause");
        this.sack = s;
        
        
    }
    
    
    
    
    @Override
    public void run()
    {
        boolean running = true;
        while(running)
        {
            try{
            
            
            this.sack.checkGifts(this);
            
            
            
            }catch(InterruptedException e){
                System.out.println(e);
                running = false;
            }            
            
        }
        
        
        
    }
    
    
}
