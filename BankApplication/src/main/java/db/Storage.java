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

}
