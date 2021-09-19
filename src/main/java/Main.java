import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.student.manage.Student;
import com.student.manage.StudentDao;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.out.println("Welcome to Student Management App");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.println("PRESS 1 to ADD student");
			System.out.println("PRESS 2 to DELETE student");
			System.out.println("PRESS 3 to display student");
			System.out.println("PRESS 4 to update student");
			int c = Integer.parseInt(br.readLine());

			if (c == 1) {
				// add student
				System.out.println("Enter student name :");
				String name = br.readLine();

				System.out.println("Enter student phonenumber :");
				String phone = br.readLine();

				System.out.println("Enter student city :");
				String city = br.readLine();

				// create student object to store student (we are not taking id as it is set to
				// zauto_increment in database)
				Student st = new Student(name, phone, city);
				boolean answer = StudentDao.insertStudentToDB(st);
				if (answer) {
					System.out.println("Student is successfully inserted!");
				} else {
					System.out.println("Something went wrong.");
				}
				// System.out.println(st);

			} else if (c == 2) {
				System.out.println("Enter student Id to delete");
				int studentId = Integer.parseInt(br.readLine());
				boolean answer = StudentDao.deleteStudent(studentId);
				if (answer) {
					System.out.println("Student with Id " + studentId + " is successfully deleted!");
				} else {
					System.out.println("Something went wrong.");
				}
			} else if (c == 3) {
				// display students
				StudentDao.showAllStudents();
			} else if (c == 4) {
				System.out.println("Enter student id to be updated");
				int studentId= Integer.parseInt(br.readLine());
				boolean answer = StudentDao.updateStudent(studentId);
				if (answer) {
					System.out.println("Student with Id " + studentId + " is successfully updated!");
				} else {
					System.out.println("Something went wrong.");
				}
			} else {
				break;
			}
		}
		System.out.println("Thank you for using my application!");
		System.out.println("See you soon!");
	}
}
