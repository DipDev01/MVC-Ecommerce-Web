package service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Student;
import utils.DatabaseConnectivity;
import utils.PasswordHash;

public class StudentDAO {
	
	private Connection conn;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private boolean isSuccess;
	private final String insert_query = "insert into student_register(firstName,lastName,userName,dob,"
			+ "gender,email,phoneNumber,subject,password"
			+ "values(?,?,?,?,?,?,?,?,?)";
	public StudentDAO() {
		conn = DatabaseConnectivity.getDbConnection();
	}
	
	public boolean isSaveStudent(Student student)
	{
		try {
			statement = conn.prepareStatement("select count (*) from student_register");
			resultSet = statement.executeQuery();
			if(resultSet.next())
			{
				if(isCheck(student)) {
					isSuccess=false;
				}
				else {
					int row = setData(student);
					if(row>0)
					{
						isSuccess=true;
					}
					else {
						isSuccess=false;
					}
				}
			}
			else {
				int row =setData(student);
				if(row>0)
				{
					isSuccess=true;
				}
				else {
					isSuccess=false;
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isSuccess;
		
	}
	public int setData(Student student) {
		int row = 0;
		try {
			statement= conn.prepareStatement(insert_query);
			statement.setString(1, student.getFirstName());
			statement.setString(2, student.getLastName());
			statement.setString(3, student.getUsername());
			statement.setDate(4, (Date) student.getDob());
			statement.setString(5, student.getGender());
			statement.setString(6, student.getEmail());
			statement.setLong(7, student.getPhoneNumber());
			statement.setString(8,student.getSubject());
			statement.setString(10,student.getPassword());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return row;
	}
	
	public boolean isCheck(Student student) {
		boolean isFind=false;
		try {
			statement=conn.prepareStatement("select userName,email,phoneNumber from student_register");
			resultSet=statement.executeQuery();
			while(resultSet.next()) {
				if(student.getUsername().equals(resultSet.getString("userName"))) {
					isFind=true;
					break;
				}
				else if(student.getEmail().equals(resultSet.getString(1))) {
					isFind=true;
					break;
				}
				else if(student.getPhoneNumber()==(resultSet.getLong("phoneNumber"))){
					isFind=true;
					break;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return isFind;
	}

	public boolean studentLogin(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		
		statement = conn.prepareStatement("select username,password from stduent_register where username = ?");
		statement.setString(1, username);
		resultSet = statement.executeQuery();
		boolean isSuccess = false;
		if(resultSet.next()) {
			String passwordFromDb= resultSet.getString("password");
			
			if(PasswordHash.verifyPassword(password, passwordFromDb)){
				isSuccess=true;
			}
			else {
				isSuccess=false;
			}
		}
		return isSuccess;
	}
}
