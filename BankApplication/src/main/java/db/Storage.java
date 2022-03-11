package db;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import myexception.CustomException;

public interface Storage {
	
	public Cache mapToFile(Map<Long,Map<Long, AccountInfo>> accMap,Map<Long, CustomerInfo> cusMap) throws CustomException, IOException, Exception;
	public Cache readFromFile() throws IOException, ClassNotFoundException, CustomException;
	public long getAccNo();
	public long getIdNo();
	public void setAccNo(long accNumber);
	public void setIdNo(long id);
	public void updateAmount(long id,long accountNumber,long amount) throws SQLException, ClassNotFoundException, CustomException;
	public void updateCustomer(String name,int age,char gender,long id) throws SQLException, CustomException;
	public void storeAccount(long id,String branch,String name,long accNo,long balance,boolean status) throws SQLException, CustomException;
	public Cache storeCustomer(long id,String name,char gender,int age) throws SQLException, ClassNotFoundException, IOException, CustomException;
	public void deactivateAccount(long accountNo) throws SQLException, CustomException;
	public void activateAccount(long accountNo) throws SQLException, CustomException;
	public boolean login(long id, String password) throws SQLException, CustomException;
	public long getId(long accNo) throws SQLException, CustomException;
	public Map<Long, Map<Long, AccountInfo>> readInactive() throws IOException, ClassNotFoundException, CustomException, SQLException;
	public void newLogin(long userId,String password) throws SQLException, CustomException;
	public void updateAccount(String name,long accNo,String branch,long id) throws SQLException, CustomException;
}
