package jia.begins.examples.basics.ventseslav.marinov;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Exercise1 {

public static int age;
public static double salary;

public static void main(String[] args) throws IOException,
FileNotFoundException {

boolean isInputOk = false;

System.out.println("JACME Inc.");
System.out.println("-----------------------------------------------");

Scanner in = new Scanner(System.in);

File file = new File("employees.txt");

if (!file.exists()) {
file.createNewFile();
}

PrintWriter pw = new PrintWriter(new FileWriter(file, true));


System.out.print("Name: ");
String name = in.nextLine();

System.out.print("Last Name: ");
String lastName = in.nextLine();

System.out.print("Position: ");
String position = in.nextLine();

do {

try {

System.out.print("Age: ");
age = Integer.parseInt(in.nextLine());
isInputOk = true;
} catch (NumberFormatException e) {
System.out.println("Error: Must be a number! ");
isInputOk = false;
}
} while (!isInputOk);

do {

try {
System.out.print("Salary: ");
salary = Double.parseDouble(in.nextLine());
isInputOk = true;
} catch (NumberFormatException e) {
System.out.println("Error: Must be a number! ");
isInputOk = false;
}
} while (!isInputOk);

pw.write(String.format("%10s %10s %10s %5d %15f %n", name, lastName, position, age, salary));

//pw.write(String.format("\nName: " + name + "\tLastName: " + lastName + "\tPosition: " + position + "\tAge: " + age + "\tSalary: " + salary));

pw.flush();
pw.close();

}

}