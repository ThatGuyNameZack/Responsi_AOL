package exercise1;

import java.util.ArrayList;

public class Manager extends Person{
    private String mgrCode;

    private Division mgrDiv;

    public Manager(String name, int age, String gender, int salary, String code){
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
        this.setSalary(salary);
        this.mgrCode = code;
    }

    public void setDivision(Division division){
        //makes the division attribute point to the given division object
        this.mgrDiv = division;
        //Sets the manager of this Manager object's division to this Manager object
        this.mgrDiv.setManager(this);
    }

    public String getCode(){
        return this.mgrCode;
    }

    public String getDivisionName(){
        if(this.mgrDiv != null){
            return this.mgrDiv.getDivisionName();
        }
        else return null;
    }

    public String getDivisionCode(){
        if(this.mgrDiv != null){
            return this.mgrDiv.getDivisionCode();
        }
        else return null;
    }

    public void changeDivision(Division newDivision){
        this.mgrDiv = newDivision;
        //Changes the new division's manager to this Manager object
        this.mgrDiv.setManager(this);
    }

    public ArrayList<Employee> getEmployees(){
        //Returns the employees under this manager
        return this.mgrDiv.getEmployees();
    }
}
