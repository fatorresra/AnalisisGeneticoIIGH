/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InternalManagement;

import Business.AutorizationLevel;
import Business.User;
import DataManagement.DBPointer;
import DataManagement.DBStructureType;

/**
 *
 * @author pmoro
 */
public class UserManager {
    DBPointer database;
    
    public UserManager(DBPointer d){
        this.database = d;
        this.database.connect(DBStructureType.USER);        
    }
     
    
    /**
     * Adds and stores an user
     * @param autolevel autorization level
     * @param user_name
     * @param password
     * @param first_name
     * @param last_name
     * @param email
     */    
    public void sign_up(AutorizationLevel autolevel, String user_name,
                        String password, String first_name,String last_name, String email){                       
       
       this.database.connect(DBStructureType.USER);         
        
       User user = new User(autolevel, user_name, password, first_name, last_name, email);
       this.database.current.add(user);        
    }
    
    /**
     * Allows users to login into the system, loads a profile
     * @param auto_level The autorization level of the user
     * @param user_name
     * @param password
     * @throws ClassNotFoundException 
     */
    public User login(AutorizationLevel auto_level, String user_name, String password) throws ClassNotFoundException{           
        
        this.database.connect(DBStructureType.USER);        
        
        User usuario = new User(auto_level, user_name, password);
                    User aux = (User) this.database.current.find(usuario);
                    if ( aux != null){
                        return aux;
                    }
                    else{
                        throw  new Class­Not­Found­Exception("Not founded");
                    }                       
    }    
    
    
    
    public void delete_account(User user){    
        this.database.connect(DBStructureType.USER);                
        
        this.database.current.remove(user);        
    }               
    
}
