package analisisgeneticoiigh;
import Business.Entity;
import Business.EntityType;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Main {
  
    
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {        
        //Aplication.reset();
        Aplication my_app =  new Aplication();                
        Test test = new Test(my_app);                              
        test.full_user_management(1);                       
        //my_app.save_changes();        
    }
    
}
