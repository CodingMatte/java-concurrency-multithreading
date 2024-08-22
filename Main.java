
package matteo_incicco;


public class Main {


    public static void main(String[] args) 
    {
        Sack sacco = new Sack();
        SantaClause santaClause = new SantaClause(sacco);
        Elf elf[] = new Elf[24];
        
        santaClause.start();
        
        for(int i = 0;
            i < elf.length;
            i++)
        {
            elf[i] = new Elf(sacco, i);
            elf[i].start();
        }
        
        
        
        try{
        
        for(int i = 0;
            i < elf.length;
            i++)
        {
            elf[i].join();
            
        }
        
        }catch(InterruptedException e){
            System.out.println(e);
        }
        
        
        santaClause.interrupt();
        
        try{
        santaClause.join();
        }catch(InterruptedException e){
            System.out.println(e);
        }
        
        
        System.out.println("Simulation Completed");
        
    }
    
}
