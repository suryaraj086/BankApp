package db;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import myexception.CustomException;

public class DBLayer implements Storage {
	
	public long idNo=0;
	public long accNo=0;
	Cache cache=new Cache();
	
	public long getAccNo() {
		return accNo;
	}
	public long getIdNo() {
		return idNo;
	}
	
	
	public Cache mapToFile(Map<Long,Map<Long, AccountInfo>> inpAccountMap,Map<Long, CustomerInfo> customerMap) throws CustomException, Exception {
		
        if(!inpAccountMap.isEmpty())
        {
    			try(Connection con=ConnectionUtility.getConnection())  	//here bank is database name, root is username and password  
    			{
    			
    			int i=0;
    			Iterator<Long> itr = customerMap.keySet().iterator();
    			while (itr.hasNext())
    			{
    			    Long key = itr.next();
    			    CustomerInfo value = customerMap.get(key);
    			    String name= value.getName(); 
    			    int age=value.getAge();
    			   char gender= value.getGender();
    			    PreparedStatement stmt=prepareStatement("INSERT INTO customerdata " + "VALUES (?,?,?,?)");
    				stmt.setLong(1,key);
    				stmt.setString(2,name);
    				stmt.setInt(3,age);
    				stmt.setString(4,String.valueOf(gender));
    				stmt.executeUpdate();
    				i++;
    			}
    			
    			Iterator<Long> itreate = inpAccountMap.keySet().iterator();
    			while (itreate.hasNext())
    			{
    			    Long id = itreate.next();
    			    Map<Long, AccountInfo> value = inpAccountMap.get(id);
    			    Iterator<Long> itreate1 = value.keySet().iterator();
    			    while (itreate1.hasNext())
        			{
    			       Long accNo = itreate1.next();
    			       AccountInfo account = value.get(accNo);
    			       String name= account.getName();
        			   Long balance=account.getBalance();
        			   String branch= account.getBranch();
        			   boolean status=account.isStatus();
        			   PreparedStatement stmt=con.prepareStatement("INSERT INTO accountdata " + "VALUES (?,?,?,?,?,?)");
        				stmt.setLong(1,id);
        				stmt.setLong(3,accNo);
        				stmt.setString(4, branch);
        				stmt.setString(2,name);
        				stmt.setLong(5, balance);
        				stmt.setBoolean(6, status);
        				stmt.executeUpdate();
        			}
    				i++;
    			}
    			
    			System.out.println("rows affected for customerdata and accountdata table "+i);
    			cache.storeInCache(inpAccountMap,accNo,idNo,customerMap);
    			inpAccountMap.clear();
    			customerMap.clear();
            	}
        	    catch (Exception e) {
        		e.printStackTrace();
			}
    			return cache;
        }
         throw new CustomException("map is empty");  
}

	private PreparedStatement prepareStatement(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Cache readFromFile() throws IOException, ClassNotFoundException, CustomException {
		
	    Map<Long,Map<Long, AccountInfo>> inpAccountMap=new HashMap<Long, Map<Long,AccountInfo>>();
		
		Map<Long, CustomerInfo> customerMap=new HashMap<Long, CustomerInfo>();
		
		try(Statement st=ConnectionUtility.getConnection().createStatement())  	//here bank is database name, root is username and 1234 is password  
		{
			  String query = "SELECT * FROM accountdata where status=true";
		      //java.sql.Statement st = con.createStatement();
		      ResultSet rs = st.executeQuery(query);
		      while (rs.next())
		      {
		        Long id = (long) rs.getLong("id");
		        String name = rs.getString("name");
		        long accountNumber = rs.getLong("accountnumber");
		        String branch = rs.getString("branch");
		        boolean status = rs.getBoolean("status");
		        Long balance = rs.getLong("balance");
		        AccountInfo accObj=new AccountInfo();
		        accNo=accountNumber;
		        accObj.setAccountNumber(accountNumber);
		        accObj.setBalance(balance);
		        accObj.setBranch(branch);
		        accObj.setId(id);
		        accObj.setName(name);
		        accObj.setStatus(status);
		        Map<Long, AccountInfo> tempMap=inpAccountMap.get(id);
		        if(tempMap==null)
		        {
		        	tempMap=new HashMap<Long,AccountInfo>();
		        	inpAccountMap.put(id, tempMap);
		        }
		        tempMap.put(accountNumber,accObj);
		      }
		      
		      query = "SELECT * FROM customerdata";
		    
		      ResultSet rs1 = st.executeQuery(query);
		      while (rs1.next())
		      {
		        Long id = rs1.getLong("id");
		        String name = rs1.getString("name");
		        String gender = rs1.getString("gender");
		        int age = rs1.getInt("age");
		        idNo=id;
		        CustomerInfo cusObj=new CustomerInfo();
		        cusObj.setAge(age);
		        cusObj.setCustomerId(id);
		        char gen=gender.charAt(0);
		        cusObj.setGender(gen);
		        cusObj.setName(name);
		        customerMap.put(id, cusObj);
		        
		      }
		      
		      cache.storeInCache(inpAccountMap,accNo,idNo,customerMap);
		      st.close();
		   
		}
			
		 catch (SQLException e) {
			 
			e.printStackTrace();
		}
		return cache;
	}
	
	@Override
	public void setAccNo(long accNumber) {
		this.accNo=accNumber;
		
	}
	
	@Override
	public void setIdNo(long id) {
	this.idNo=id;
		
	}

	public Cache storeCustomer(long id,String name,char gender,int age) throws SQLException, ClassNotFoundException, IOException, CustomException {
	
		String input="INSERT INTO customerdata " + "VALUES (?,?,?,?)";
	try(PreparedStatement stmt=ConnectionUtility.getConnection().prepareStatement(input)  )
	{
	  
	  	stmt.setLong(1,id);
	  	stmt.setString(2,name);
	  	stmt.setInt(3,age);
		stmt.setString(4,String.valueOf(gender));
	    stmt.executeUpdate();
    	Cache cache=readFromFile();	
		return cache;
	}

	
	}
	
	
	public void storeAccount(long id,String branch,String name,long accNo,long balance,boolean status) throws SQLException, CustomException {
	
			String input="INSERT INTO accountdata " + "VALUES (?,?,?,?,?,?)";
			try(PreparedStatement stmt=ConnectionUtility.getConnection().prepareStatement(input)  )
			{
			  
			stmt.setLong(1,id);
			stmt.setLong(3,accNo);
			stmt.setString(4, branch);
			stmt.setString(2,name);
			stmt.setLong(5, balance);
			stmt.setBoolean(6, status);
			stmt.executeUpdate();
			}
	}
	
	
	public void updateAmount(long id,long accountNumber,long amount) throws SQLException, ClassNotFoundException, CustomException
	{
		String input="Update accountdata " + "SET balance=? where id=? and accountnumber=?";
	try(PreparedStatement stmt=ConnectionUtility.getConnection().prepareStatement(input))  
	{ 	
	  	stmt.setLong(1,amount);
	  	stmt.setLong(2,id);
	  	stmt.setLong(3,accountNumber);
	    stmt.executeUpdate();
	}
	}
	
	public Boolean login(long userId,String password) throws SQLException, CustomException 
	{
		String input="select * from login " + " where userId=? and password=?";
		boolean role = false;
		try(PreparedStatement stmt=ConnectionUtility.getConnection().prepareStatement(input))  
		{ 		
			stmt.setLong(1,userId);
		  	stmt.setString(2,password);
		  	ResultSet rs = stmt.executeQuery();
		  	 while (rs.next())
		      {
		  		  role=rs.getBoolean("role");
		      }
		      }
	
		return role;
		}
	
	public void updateCustomer(long name,int age,char gender,long id)
	{
		String input="Update customerdata " + "SET name=? , age=? , gender=? where id=?";
	}
}
