package Login;

public class LoginService {

	
     LoginDAO  DAO = new LoginDAO();

     
	public LoginService() {
        this.DAO = new LoginDAO();
    }

    public boolean loginUser(String inputId, String inputPw) {
        // DAO를 통해 로그인 처리를 수행하고 결과를 반환합니다.
        return DAO.login(inputId, inputPw);
    }
    
    
    
    
    public boolean loginUser2(String inputId, String inputPw) {
        // DAO를 통해 로그인 처리를 수행하고 결과를 반환합니다.
        return DAO.login1(inputId, inputPw);
    }
    
    
    
    
    
    
    
    public boolean buyerSignUp(String buyerId, String buyerPw, String buyerName, String buyerAddress) {
    	System.out.println("adfad");
    	return DAO.signupUser(buyerId, buyerPw, buyerName, buyerAddress);
    }
    
    public boolean buyeridCheck(String buyerId) {
    	 return DAO.idCheck(buyerId);
    }
   
    
    public static void main(String[] args) {
    	LoginService s = new LoginService();
    	boolean result  = s.loginUser("dy", "4482");
    	System.out.println( result);
    	
	}
}