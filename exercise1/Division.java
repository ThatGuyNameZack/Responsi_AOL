package exercise1;

import java.util.List;
import java.util.ArrayList;

public class Division {
    private String divCode;
    private String divName;

    //Variable for manager of division
    private Manager divManager;

    //List of employees in this division
    private ArrayList<Employee> employeeList;

    public void insertEmployee(Employee employee){
        //If there's no employee yet, creates the list object.
        if(this.employeeList == null){
            employeeList = new ArrayList<Employee>();
        }
        //If the list exists, add employee to the list.
        employeeList.add(employee);
    }

    public ArrayList<Employee> getEmployees(){
        return employeeList;
    }

    public void setDivisionCode(String code){
        this.divCode = code;
    }

    public String getDivisionCode(){
        return this.divCode;
    }

    public void setDivisionName(String name){
        this.divName = name;
    }

    public String getDivisionName(){
        return this.divName;
    }

    public void setManager(Manager manager){
        //If the manager exists, but not the manager of this division
        if(this.divManager != null && this.divManager.getDivisionCode().equals(this.divCode) == false){
            //Changes the division of the manager to this Division object
            manager.changeDivision(this);
            //Sets the divManager variable to point the Manager object.
            divManager = manager;
        }
        else{
            //If divManager is still empty or the user sets the manager's division to the same division as before, sets this
            //variable to point that Manager object        
            divManager = manager;
        }
        // else{
        //     divManager = manager;
        // }
    }

    public String getManagerName(){
        if(this.divManager != null){
            return this.divManager.getName();
        }
        else return null;
    }

    public String getManagerCode(){
        if(this.divManager != null){
            return this.divManager.getCode();
        }
        else return null;
    }
}