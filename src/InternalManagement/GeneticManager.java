/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InternalManagement;

import Business.Entity;
import Business.EntitySpec;
import Business.EntityType;
import Business.HorseSpec;
import DataManagement.CSVLoader;
import DataManagement.DBPointer;
import DataManagement.DBStructureType;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author pmoro
 */
public class GeneticManager {
    
    DBPointer database;
    
    public GeneticManager(DBPointer d){
        this.database = d;
        this.database.connect(DBStructureType.ENTITY);        
    }
    
    /**
     * Adds an entity to the database
     * @param type
     * @param specs 
     */   
    private void add_entity(EntityType type, EntitySpec specs){  
        this.database.connect(DBStructureType.ENTITY);                
        Entity my_entity = new Entity(type, specs);
        this.database.current.add(my_entity);
    }
    
    /**
     * Adds a single horse to the system.
     * @param register
     * @param name
     * @param birth_date
     * @param color
     * @param sex
     * @param chip
     * @param genotype
     * @param step
     * @param father
     * @param mother 
     */
    public void add_horse(int register, String name, String birth_date, String color, String sex,
            String chip, String genotype, String step, int father, int mother){        
        
        HorseSpec specs = new HorseSpec(register, name, birth_date, color, sex,
            chip, genotype, step, father, mother);        
        this.add_entity(EntityType.HORSE, specs);
    }    
    
    
    /**
     * Loads many horses from a csv file.
     * @param path
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void add_horses(String path) throws FileNotFoundException, IOException{        
        String[] data;
        CSVLoader reader = new CSVLoader(path, 9);                
        reader.has_next();        
        reader.has_next();                               
        data = reader.read_line_only_parse();
        Integer father_code = Integer.valueOf(data[2]);
        reader.has_next();
        reader.has_next();
        
                while(reader.has_next()){ 
                    
                    data = reader.read_line_only_parse();                    
                    Pattern p = Pattern.compile("([0-9])");
                    Matcher m1 = p.matcher(data[0]);
                    Matcher m2 = p.matcher(data[2]);
                    if("".equals(data[0])) continue;
                    if(!m1.find()){ 
                        if("REGISTRO".equals(data[0])) continue;
                        System.out.println("Trouble adding horse: ");
                        System.out.println(data[0] +"  " + data[1]);                        
                        continue;
                    }
                    if(!m2.find()){ 
                        if("REGISTRO".equals(data[0])) continue;
                        System.out.println("Trouble adding horse: ");
                        System.out.println(data[0] +"  " + data[1]);                        
                        continue;
                    }                    
                        Integer registro = Integer.valueOf(data[0]);
                        String name = data[1];
                        String date_nto = data[2];
                        String color = data[3];
                        String sexo = data[4];
                        String chip = data[5];
                        String geno = data[6];
                        String andar = data[7];                                                
                        Integer mother = Integer.valueOf(data[8]);
                    this.add_horse(registro, name, date_nto, color, sexo, chip, geno, andar,
                    father_code, mother);
                }                
    }
    
    
}
