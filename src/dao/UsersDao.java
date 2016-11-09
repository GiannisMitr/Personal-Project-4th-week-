package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sun.crypto.provider.RSACipher;

import domain.Comment;
import domain.User;

public class UsersDao {

public User find(String email)throws Exception{
		
		try{	
			User user;
			
			String sqlquery;
			sqlquery = "SELECT * FROM giannism.users"
					+ " where email=?;";
			
			PreparedStatement stmt=Database.open().prepareStatement(sqlquery);
		     stmt.setString(1, email);	
		  
		
		ResultSet rs = stmt.executeQuery();
		
		
		if(rs.next()){
			user= new User(rs.getString("username"), rs.getString("email"), rs.getString("password"));
			rs.close();
			stmt.close();
			return user;
		}
		else{rs.close();stmt.close();
		return null;}
		
	}

		
		catch(Exception e){
			throw new Exception("An error occured while searching for user in database: " + e.getMessage());
			}
		
		}
	
	
}
