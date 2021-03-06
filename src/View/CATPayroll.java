/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import CATPayrollSystem.Employee;
import CATPayrollSystem.EventType;
import CATPayrollSystem.WorkingEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author JoséArmando, Casey and Azry
 */
public class CATPayroll {
    
    public static Map<Integer,Employee> employees = new HashMap<Integer,Employee>();


    private static void initializeData() {
        employees.put(1, new Employee(1, "Casey", null));
        employees.put(2, new Employee(1, "Fadi", null));
    }
    
    private void consoleUI() throws IOException, Exception{                
        
        BufferedReader in = new BufferedReader ( 
                    new InputStreamReader (System.in ) );
        while ( true ) {
            
            System.out.println ( " Select an option: " );
            System.out.println ( "  1) Record an event" );
            System.out.println ( "  2) Print the accounting entries" );
            System.out.println ( "  3) Happiness" );
            System.out.println ( "  E) Exit" );
            
            String option = in.readLine();
                option = option.trim().toUpperCase();
            
            if ( option.equals( "1" ) ) {
                
                System.out.print("Type the employeeID: ");
                Employee employee = null;
                try{
                    int employeeID = Integer.parseInt(in.readLine());
                    employee = findEmployee(employeeID);

                }

                catch(NumberFormatException e){
                    System.out.println("Employee ID invalid");
                    return;
                }


                System.out.print("Type the number of working hours: ");

                try{

                    int workingHours = Integer.parseInt(in.readLine());
                    WorkingEvent event;
                    
                    event = createEvent(workingHours, employee);
                    //event.process();

                    System.out.println(event);

                }
                catch(NumberFormatException e){
                    System.out.println("Hours entered invalid");
                    return;
                }
            } 
            
            else if(option.equals("E")){
               return;
           }
               
           
        }
        
    }
    
    
    
    public static void main (String[] args) throws IOException, Exception{
        initializeData();
        new CATPayroll().consoleUI();
        
    }

    private Employee findEmployee(int employeeID) {
        Employee employee;
        try{
            employee = employees.get(employeeID);                
            System.out.println("Employee: " + employee.getName());
        }
        catch(NullPointerException e){
                throw new NullPointerException("Empoyee ID does not exist");
        }
        return employee;
    }

    private WorkingEvent createEvent(int workingHours, Employee employee) throws Exception {
        WorkingEvent event;
        if(workingHours>8){
            return event = new WorkingEvent(EventType.OvertimeHours, Calendar.getInstance().getTime(), employee, workingHours);
        }else if (workingHours<=8){
            return event = new WorkingEvent(EventType.RegularHours, Calendar.getInstance().getTime(), employee, workingHours);
        }else{
            throw new Exception("Bad working hours");
        }
    }
}
