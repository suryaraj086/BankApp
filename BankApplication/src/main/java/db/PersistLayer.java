package db;

import java.io.*;
import java.sql.SQLException;
import java.util.Map;
import myexception.CustomException;

public class PersistLayer implements Storage  {

    public long accNo=0;
    public long idNo=0;
    
    public long getAccNo() {
		return accNo;
	}
    
    public void setAccNo(long accNo) {
		this.accNo = accNo;
	}
    public long getIdNo() {
		return idNo;
	}
    
  	public void setIdNo(long idNo) {
		this.idNo = idNo;
	}
  	
    public Cache mapToFile(Map<Long,Map<Long, AccountInfo>> inpAccountMap,Map<Long, CustomerInfo> customerMap) throws CustomException, IOException {
    	Cache cache=new Cache();
        if(!inpAccountMap.isEmpty())
        {
            File file=new File("accountmap.txt");
            try( FileOutputStream fos = new FileOutputStream(file,false);
                 ObjectOutputStream oos = new ObjectOutputStream(fos);)
            {
                oos.writeObject(inpAccountMap);
                oos.writeObject(accNo);
                oos.writeObject(idNo);
                oos.writeObject(customerMap);
                cache.storeInCache(inpAccountMap,accNo,idNo,customerMap);
            }
            return cache;
        }
      throw new CustomException("Map is empty");
    }

    @SuppressWarnings("unchecked")
	public Cache readFromFile() throws IOException, ClassNotFoundException, CustomException {
    	Cache cache=new Cache();
    	File file=new File("accountmap.txt");
    	if(file.exists())
    	{
        try( FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis);)
        {
           cache.accountMap = (Map<Long, Map<Long, AccountInfo>>) ois.readObject();
           accNo =  (long) ois.readObject();
           cache.accNo= accNo;
           idNo = (long) ois.readObject();
           cache.idNo=idNo;
           cache.customerMap = (Map<Long, CustomerInfo>) ois.readObject();
        }
        return cache;
    	}
        throw new CustomException("file not exists");
    }

	@Override
	public void updateAmount(long id, long accountNumber, long amount) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCustomer(String name, int age, char gender, long id) throws SQLException, CustomException {
		
	}

	@Override
	public void storeAccount(long id, String branch, String name, long accNo, long balance, boolean status)
			throws SQLException, CustomException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Cache storeCustomer(long id, String name, char gender, int age)
			throws SQLException, ClassNotFoundException, IOException, CustomException {
		// TODO Auto-generated method stub
		return null;
	}

	public void deactivateAccount(long accountNo) {
		// TODO Auto-generated method stub
		
	}
}