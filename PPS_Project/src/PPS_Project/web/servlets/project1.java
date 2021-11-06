package PPS_Project.web.servlets;

import java.sql.SQLException;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class project1 {
	
	
	
	

	/**
	 * 
	 * @throws SQLException
	 */
	
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Connection connect1 = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	

    
	
	
    public void part1() throws SQLException {
    	

    	try {
    		
    		if (connect == null || connect.isClosed()) {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    throw new SQLException(e);
                }
                connect = (Connection) DriverManager
        			      .getConnection("jdbc:mysql://127.0.0.1:3306/PPS_DB?serverTimezone=UTC"
        			          + "&useSSL=false&user=john&password=john1234");
                System.out.println(connect);
            }
        
    
    	

    	statement = connect.createStatement();
    	
    	// Create all the tables statements
    	String createUsersTable_SQL =" CREATE TABLE IF NOT EXISTS `users` (\r\n"
		  		+ "  `email` varchar(50) NOT NULL,\r\n"
		  		+ "  `pass` varchar(50) DEFAULT NULL,\r\n"
		  		+ "  `fname` varchar(30) DEFAULT NULL,\r\n"
		  		+ "  `lname` varchar(50) DEFAULT NULL,\r\n"
		  		+ "  `address` varchar(100) DEFAULT NULL,\r\n"
		  		+ "  `dob` date DEFAULT NULL,\r\n"
		  		+ "  `PPS_balance` double DEFAULT NULL,\r\n"
		  		+ "  `dollar_balance` double DEFAULT NULL,\r\n"
		  		+ "  PRIMARY KEY (`email`))";
    	
    	
    	String createFollowTable_SQL = "CREATE TABLE IF NOT EXISTS follows " +
                "(follower_email  VARCHAR(50), " + 
                " following_email VARCHAR(50), " + 
                " PRIMARY KEY (follower_email,following_email))";
    	
    	String createTransactionsTable_SQL ="CREATE TABLE IF NOT EXISTS transactions " +
                "(transaction_ID integer AUTO_INCREMENT, " +
                " transaction_name VARCHAR(20), " + 
                " transaction_date date, " + 
                " transaction_time time, " + 
                " dollar_amount double, " + 
                " PPS_amount integer, " + 
                " transaction_from_email varchar (20) not null, " + 
                " transaction_to_email varchar (20) not null, " + 
                "  PRIMARY KEY ( transaction_ID ))" ;
    	
    	String createPPS_priceTable_SQL ="CREATE TABLE IF NOT EXISTS PPS_price " +
                "(price double)" ;
    	
    	
    	// Drop all tables
    	String dropUserTable_SQL="delete from users where email <> 'root'";
    	String dropTransactionsTable_SQL = "drop table if exists transactions";
    	String dropFollowTable_SQL = "drop table if exists follows";
    	String dropPPS_priceTable_SQL = "drop table if exists PPS_price";
    	
    	// Intialize all tables
    	String intializeUserTable = "insert into users (email,pass,fname,lname,address,dob,PPS_balance,dollar_balance) values"
    			+ "('jbjb@yahoo.com','1234','try','effat','ctg','2012-11-12',12345,1345.66),"
    			+ "('effat@gmail.com','1234','try','effat','ctg','2012-11-12',12345,1345.66),"
    			+ "('jbxsjb@gmail.com','1234','try','effat','ctg','2012-11-12',12345,1345.66),"
    			+ "('gdfgd@gmail.com','1234','try','effat','ctg','2012-11-12',12345,1345.66),"
    			+ "('rofdgfgot@gmail.com','1234','try','effat','ctg','2012-11-12',12345,1345.66),"
    			+ "('bit@gmail.com','1234','try','effat','ctg','2012-11-12',12345,1345.66),"
    			+ "('tre@gmail.com','1234','try','effat','ctg','2012-11-12',12345,1345.66),"
    			+ "('rgt@gmail.com','1234','try','effat','ctg','2012-11-12',12345,1345.66),"
    			+ "('roofwert@gmail.com','1234','try','effat','ctg','2012-11-12',12345,1345.66),"
    			+ "('asfegtt@gmail.com','1234','dd','effat','ctg','2012-01-12',12345,1345.66);";
    	
    	String intializeFollowTable = "insert into follows(follower_email,following_email) values"
    			+ "('ef@gmail.com','ret@gmail.com'), "
    			+ "('ef1@gmail.com','ret2@gmail.com'),"
    			+ "('yuu@gmail.com','hhgj@gmail.com'),"
    			+ "('gjk@gmail.com','ret@gmail.com'),"
    			+ "('efkn@gmail.com','poet@gmail.com'),"
    			+ "('efat@gmail.com','ropet@gmail.com'),("
    			+ "'ekhkf@gmail.com','rejkt@gmail.com'),"
    			+ "('eoijhf@gmail.com','roiuhet@gmail.com'),"
    			+ "('ekkjhf@gmail.com','repoiht@gmail.com'),"
    			+ "('pooef@gmail.com','pomnret@gmail.com') ;";
    	
    	String intializeTransactionsTable = "INSERT INTO transactions (transaction_name,transaction_date,transaction_time,dollar_amount,PPS_amount,transaction_from_email,transaction_to_email) VALUES "
    			+ "('effat',  '2018-11-12','13:30',1200,1234,'ef@gmail.com','ret@gmail.com')"
    			+ ",('dnn',  '2018-11-12','13:30',1200,1234,'ef@gmail.com','ret@gmail.com')"
    			+ ",('uiopeffat',  '2018-11-12','13:30',1200,1234,'ef@gmail.com','ret@gmail.com')"
    			+ ",('eerteffat',  '2018-11-12','13:30',1200,1234,'ef@gmail.com','ret@gmail.com')"
    			+ ",('wqereffat',  '2018-11-12','13:30',1200,1234,'ef@gmail.com','ret@gmail.com')"
    			+ ",('iuyeffat',  '2018-11-12','13:30',1200,1234,'ef@gmail.com','ret@gmail.com')"
    			+ ",('treeffat',  '2018-11-12','13:30',1200,1234,'ef@gmail.com','ret@gmail.com')"
    			+ ",('fwegeffat',  '2018-11-12','13:30',1200,1234,'ef@gmail.com','ret@gmail.com')"
    			+ ",('effat',  '2018-11-12','13:30',1200,1234,'ef@gmail.com','ret@gmail.com')"
    			+ ",('ijijeffat',  '2018-11-12','13:30',1200,1234,'ef@gmail.com','ret@gmail.com')";
    	
    	String intializePPS_priceTable = "insert into PPS_price (price) values (1000000);";
    	
    
    	// Execute all drop statements
      	statement.executeUpdate(dropPPS_priceTable_SQL);
   	    statement.executeUpdate(dropTransactionsTable_SQL);
   	    statement.executeUpdate(dropFollowTable_SQL);
   	    statement.executeUpdate(dropUserTable_SQL);
       	
   	    // Execute all create statements
       	statement.executeUpdate(createUsersTable_SQL);
       	statement.executeUpdate(createTransactionsTable_SQL);
        statement.executeUpdate(createFollowTable_SQL);
        statement.executeUpdate(createPPS_priceTable_SQL);
        
        // Execute all intialization statements
        statement.executeUpdate(intializeUserTable);
      	statement.executeUpdate(intializeFollowTable);
      	statement.executeUpdate(intializeTransactionsTable);
      	statement.executeUpdate(intializePPS_priceTable);
    

    	
    	
    }
    	catch (Exception e) {
            System.out.println(e);
       } 
    }
    

}
