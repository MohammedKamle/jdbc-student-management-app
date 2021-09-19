package com.student.manage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentDao {

	public static boolean insertStudentToDB(Student st) {
		boolean flag = false;
		try {
			Connection con = ConnectionProvider.createConnection();
			/*
			 * "?" in query will be replaced by actual student details with
			 * PreparedStatement class
			 */
			String query = "insert into students(sname, sphone, scity) values(?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(query);
			// set the value of parameter
			pstmt.setString(1, st.getStudentName());
			pstmt.setString(2, st.getStudentPhone());
			pstmt.setNString(3, st.getStudentCity());
			// execute
			pstmt.executeUpdate();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static boolean deleteStudent(int studentId) {
		boolean flag = false;
		try {
			Connection con = ConnectionProvider.createConnection();
			/*
			 * "?" in query will be replaced by actual student details with
			 * PreparedStatement class
			 */
			String query = "delete from students where sid=?";
			PreparedStatement pstmt = con.prepareStatement(query);
			// set the value of parameter
			pstmt.setInt(1, studentId);
			// execute
			pstmt.executeUpdate();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;

	}

	public static void showAllStudents() {
		try {
			Connection con = ConnectionProvider.createConnection();
			String query = "select * from students;";
			Statement stmt = con.createStatement();
			ResultSet set = stmt.executeQuery(query);
			while (set.next()) {
				/*
				 * 1,2,3,4 are column numbers corresponding to our database, it can also be
				 * coloumn names viz.,sid,sname,sphone and scity
				 */
				int id = set.getInt(1);
				String name = set.getNString(2);
				String phone = set.getString(3);
				String city = set.getNString(4);

				System.out.println("ID : " + id);
				System.out.println("Name : " + name);
				System.out.println("Phone : " + phone);
				System.out.println("City : " + city);
				System.out.println("++++++++++++++++++++++++++++++++++++++");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static boolean updateStudent(int studentId) {
		boolean flag = false;
		try {
			Connection con = ConnectionProvider.createConnection();
			/*
			 * "?" in query will be replaced by actual student details with
			 * PreparedStatement class
			 */
			String query = "update students set sname=?,sphone=?, scity=? where sid=?";
			PreparedStatement pstmt = con.prepareStatement(query);
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Enter nameto be updated :");
			String updatedName = br.readLine();
			System.out.println("Enter phone-number to be updated :");
			String updatedPhoneNumber = br.readLine();
			System.out.println("Enter city to be updated :");
			String updatedCity = br.readLine();
			// set the value of parameter
			pstmt.setString(1, updatedName);
			pstmt.setString(2, updatedPhoneNumber);
			pstmt.setNString(3,updatedCity);
			pstmt.setInt(4, studentId);
			// execute
			pstmt.executeUpdate();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
		
	}
}
