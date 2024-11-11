package exercise1;

public class Employee extends Person{
    public String employeeCode;
    public String position;

    private Division division;

    public Employee(String name, int age, String gender, int salary, String employeeCode, String position){
        this.setName(name);
        this.setAge(age);
        this.setGender(gender);
        this.setSalary(salary);
        this.employeeCode = employeeCode;
        this.position = position;
        // this.division.setDivisionName(divName);
        // this.division.setDivisionCode(divCode);
    }

    public void setDivision(Division employeeDiv){
        //Sets this employee's division to the received Division object
        this.division = employeeDiv;
        //Inserts Employee object to the employee list in Division object
        this.division.insertEmployee(this);
    }

    public String getDivisionName(){
        return this.division.getDivisionName();
    }

    public String getDivisionCode(){
        return this.division.getDivisionCode();
    }

    public void changeDivision(Division newDivision){
        //Changes this employee's division to another Division object
        this.division = newDivision;
    }

    public void changePosition(String position){
        this.position = position;
    }

    public String getPosition(){
        return this.position;
    }

    public String getCode(){
        return this.employeeCode;
    }

    public String getManagerCode(){
        if(this.division.getManagerCode() != null){
            return this.division.getManagerCode();
        }
        else return null;
    }

    public String getManagerName(){
        if(this.division.getManagerName() != null){
            return this.division.getManagerName();
        }
        else return null;
    }
}
