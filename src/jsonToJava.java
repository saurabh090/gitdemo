import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

// added comment from gitstuff
// added first comment to test GIT branch
//added second comment to test GIT branch
public class jsonToJava {

	
	// placed comment as first line inside class  
	//String url = "jdbc:sqlserver://MYPC\\SQLEXPRESS;databaseName=MYDB";
	
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException, JsonGenerationException, JsonMappingException, IOException {
		String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=Business;user=sa;password=pitney@123";
		ArrayList<CustomerDetails> ar= new ArrayList<CustomerDetails>();
		System.out.print("Connecting to SQL Server ... ");
        Connection connection = DriverManager.getConnection(connectionUrl);
                System.out.println("Done.");
                Statement stmt= connection.createStatement();
                //ResultSet rs= stmt.executeQuery("SELECT * FROM CustomerInfo where Location='Asia' and PurchasedDate=CONVERT(date,GETDATE());");
                ResultSet rs= stmt.executeQuery("SELECT * FROM CustomerInfo where Location='Asia' and PurchasedDate='"+java.time.LocalDate.now()+"'");
                //System.out.println("rs::::"+rs);
                //System.out.println(rs.getFetchSize());
                System.out.println("Current Date:::"+java.time.LocalDate.now());
                //rs.next();
                //System.out.println(rs.getInt(1));
                while( rs.next()){
                	CustomerDetails cd= new CustomerDetails();            	
                	cd.setCourseName(rs.getString(1));
                	cd.setPurchasedDate(rs.getString(2));
                	cd.setAmount(rs.getString(3));
                	cd.setLocation(rs.getString(4));
                	//System.out.println("cd.getCourseName():::"+cd.getCourseName());
                	//System.out.println("cd.getPurchasedDate():::"+cd.getPurchasedDate());
                	//System.out.println("cd.getAmount():::"+cd.getAmount());
                	//System.out.println("cd.getLocation():::"+cd.getLocation());
                	ar.add(cd);	
                }
                
                
                
                /*for(CustomerDetails customerDetails:ar){
                	System.out.println(customerDetails.getAmount());
                }*/
               
                System.out.println(System.getProperty("user.dir"));
                for(int i=0;i<ar.size();i++){
                ObjectMapper om = new ObjectMapper();
                om.writeValue(new File(System.getProperty("user.dir")+"//src//generated_json//customerinfo"+i+".json"), ar.get(i));
                }
                
                
               connection.close();
            
        		
		

	}

}
