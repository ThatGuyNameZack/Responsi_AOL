package exercise1;

import java.util.ArrayList;
import java.util.Scanner;

import java.util.InputMismatchException;

public class MainClass{

    //Displays menu
    static void menuDisplay(){
        System.out.println("1. Add division.");
        System.out.println("2. Add employee.");
        System.out.println("3. Add manager.");
        System.out.println("4. Show employee data.");
        System.out.println("5. Show manager data.");
        System.out.println("6. Show employees in a division.");
        System.out.println("7. Exit");
    }

    //Finds division based on name within the list of divisions
    static Division findDivision(ArrayList<Division> divisionList, String name){
        //For each division in the list
        for(Division division : divisionList){
            //If the name of the division in the list matches the searched one
            if(division.getDivisionName().equals(name)){
                return division;
            }
        }
        return null;
    }

    //Create a new division if needed
    static Division createDivision(ArrayList<Division> divisionList, String divName, String divCode){
        Division newDiv = new Division();
        newDiv.setDivisionName(divName);
        newDiv.setDivisionCode(divCode);
        //Adds division to the list
        divisionList.add(newDiv);
        return newDiv;
    }

    public static void main(String[] args){
        // Division IT = new Division();
        // IT.setDivisionName("IT");
        // IT.setDivisionCode("D006");
        
        // Employee employee1 = new Employee("Budianto", 27, "Male", 5000000, "E0010",
        // "Junior Dev");
        // employee1.setDivision(IT);

        // Manager managerMarketing = new Manager("Arif Setiawan", 35, "Male", 30000000, "M003");

        // if(employee1.getManagerName() == null){
        //     System.out.println("This employee doesn't have a manager yet.");
        // }

        // Manager managerIT = new Manager("Juliana", 32, "Female", 30000000, "M002");
        // managerIT.setDivision(IT);
    
        // if(employee1.getManagerName() == null){
        //     System.out.println("This employee doesn't have a manager yet.");
        // }
        // else{
        //     System.out.println("This employee's manager is " + employee1.getManagerName());
        // }

        // Employee employee2 = new Employee("Dimas", 32, "Male", 10000000, "E0003", 
        // "Senior Dev");
        // employee2.setDivision(IT);

        // ArrayList<Employee> ITEmployees = IT.getEmployees();

        // for(Employee e : ITEmployees){
        //     System.out.println(e.getName() + "; " + e.getCode());
        // }

        ArrayList<Division> divisionList = new ArrayList<Division>();
        ArrayList<Employee> employeeList = new ArrayList<Employee>();
        ArrayList<Manager> managerList = new ArrayList<Manager>();

        Scanner input = new Scanner(System.in);

        int choice = 0;

        try{
            while(choice != 7){
                menuDisplay();
                choice = Integer.valueOf(input.nextLine());

                // if((choice != 1 && choice != 7) && divisionList.isEmpty() == false){
                //     System.out.println("Please create a division first.");
                // }

                switch(choice){
                    case 1:
                        System.out.print("Division Name: ");
                        String divName = input.nextLine();
                        System.out.print("Division Code: ");
                        String divCode = input.nextLine();

                        //If the division has already existed, no new one is created.
                        if(divisionList != null && findDivision(divisionList, divName) != null){
                            break;
                        }
                        //If the division list is still null/doesn't exist
                        else if(divisionList == null){
                            divisionList = new ArrayList<Division>();
                        }
                        //If the division hasn't existed, create an object, then sets the name and code of the division
                        Division newDiv = new Division();
                        newDiv.setDivisionName(divName);
                        newDiv.setDivisionCode(divCode);
                        divisionList.add(newDiv);
                        break;
                    case 2:
                        //Takes input data
                        System.out.print("Employee name: ");
                        String employeeName = input.nextLine();
                        System.out.print("Employee age: ");
                        int employeeAge = Integer.valueOf(input.nextLine());
                        System.out.print("Employee gender: ");
                        String employeeGender = input.nextLine();
                        System.out.print("Employee code: ");
                        String employeeCode = input.nextLine();
                        System.out.print("Employee position: ");
                        String employeePosition = input.nextLine();
                        System.out.print("Employee salary: ");
                        int employeeSalary = Integer.valueOf(input.nextLine());
                        System.out.print("Employee division: ");
                        String employeeDiv = input.nextLine();

                        //Creates new Employee object
                        Employee newEmployee = new Employee(employeeName, employeeAge, employeeGender, employeeSalary,
                        employeeCode, employeePosition);

                        //A variable for employee division
                        Division employeeDivision;
                        
                        if(findDivision(divisionList, employeeDiv) != null){
                            //If the division already exists, employeeDivision points to that division
                            employeeDivision = findDivision(divisionList, employeeDiv);
                        }
                        else{
                            //If the division doesn't exist, create new one
                            System.out.println("Division not found. Add new division.");
                            System.out.print("Division name: ");
                            String newDivName = input.nextLine();
                            String newDivCode = input.nextLine();
                            employeeDivision = createDivision(divisionList, newDivName, newDivCode);                           
                        }

                        //Sets the division in the employee object to employeeDivision
                        newEmployee.setDivision(employeeDivision);
                        employeeList.add(newEmployee);
                        break;
                    case 3:
                        System.out.print("Manager name: ");
                        String mgrName = input.nextLine();
                        System.out.print("Manager age: ");
                        int mgrAge = Integer.valueOf(input.nextLine());
                        System.out.print("Manager gender: ");
                        String mgrGender = input.nextLine();
                        System.out.print("Manager code: ");
                        String mgrCode = input.nextLine();
                        System.out.print("Manager salary: ");
                        int mgrSalary = Integer.valueOf(input.nextLine());
                        System.out.print("Manager division: ");
                        String mgrDiv = input.nextLine();

                        //Creates new Manager object
                        Manager newManager = new Manager(mgrName, mgrAge, mgrGender, mgrSalary, mgrCode);

                        //A variable to hold the manager's division
                        Division managerDivision;

                        if(findDivision(divisionList, mgrDiv) != null){
                            managerDivision = findDivision(divisionList, mgrDiv);
                        }
                        else{
                            System.out.println("Division not found. Add new division.");
                            System.out.print("Division name: ");
                            String newDivName = input.nextLine();
                            System.out.print("Division code: ");
                            String newDivCode = input.nextLine();
                            managerDivision = createDivision(divisionList, newDivName, newDivCode);                           
                        }

                        //Sets the division in Manager object
                        newManager.setDivision(managerDivision);
                        managerList.add(newManager);
                        break;
                    case 4:
                        boolean employeeFound = false;
                        System.out.print("Employee name: ");
                        String findEmployee = input.nextLine();
                        //For each employee in the list
                        for(Employee employee : employeeList){
                            if(employee.getName().equals(findEmployee)){
                                //If the name of the employee matches, display information
                                System.out.println("Employee name: " + employee.getName());
                                System.out.println("Employee age: " + employee.getAge());
                                System.out.println("Employee gender: " + employee.getGender());
                                System.out.println("Employee code: " + employee.getCode());
                                System.out.println("Employee position: " + employee.getPosition());
                                System.out.println("Employee salary: " + employee.getSalary());
                                System.out.println("Employee's division name: " + employee.getDivisionName());
                                System.out.println("Employee's division code: " + employee.getDivisionCode());
                                if(employee.getManagerName() != null){
                                    System.out.println("Employee's manager: " + employee.getManagerName());
                                }
                                else{
                                    System.out.println("Employee's manager is not found.");
                                }

                                employeeFound = true;
                                break;
                            }
                        }
                        if(employeeFound == false){
                            System.out.println("Employee not found.");
                        }
                        break;
                    case 5:
                    boolean mgrFound = false;
                    System.out.print("Manager name: ");
                    String findMgr = input.nextLine();
                    for(Manager manager : managerList){
                        if(manager.getName().equals(findMgr)){
                            System.out.println("Manager name: " + manager.getName());
                            System.out.println("Manager age: " + manager.getAge());
                            System.out.println("Manager gender: " + manager.getGender());
                            System.out.println("Manager code: " + manager.getCode());
                            System.out.println("Manager salary: " + manager.getSalary());
                            System.out.println("Manager's division name: " + manager.getDivisionName());
                            System.out.println("Manager's division code: " + manager.getDivisionCode());
                            mgrFound = true;
                            break;
                        }
                    }
                    if(mgrFound == false){
                        System.out.println("Manager is not found.");
                    }
                    break;
                case 6:
                    System.out.print("Division name: ");
                    String findDiv = input.nextLine();
                    //If the division exists
                    if(findDivision(divisionList, findDiv) != null){
                        //Stores the division in a variable
                        Division foundDiv = findDivision(divisionList, findDiv);
                        //Take the list of employees from the division
                        ArrayList<Employee> divisionEmployeeList = foundDiv.getEmployees();
                        //If the list isn't empty, display all employees
                        if(divisionEmployeeList != null){
                            for(Employee employee : divisionEmployeeList){
                                System.out.println("Employee name: " + employee.getName() + ", code: " + employee.getCode());
                            }
                        }
                        else{
                            System.out.println("This division doesn't have any employee.");
                        }
                    }
                    else{
                        //If the division isn't found
                        System.out.println("The division is not found.");
                    }
                    break;
                case 7:
                    System.exit(0);
                }
            }
        }
        catch(InputMismatchException e){
            System.out.println("Invalid input.");
        }
        catch(NumberFormatException f){
            System.out.println("Invalid input.");
        }
        catch(ArrayIndexOutOfBoundsException g){
            System.out.println("A problem occured with the list.");
        }
        finally{
            input.close();
        }
        
    }
}