package action;

import java.util.Map;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LogoutAction extends ActionSupport implements SessionAware{
	private SessionMap<String, Object> userSession;
	
public String execute(){
	return logout();
}

@Override
public void setSession(Map<String, Object> session) {
	userSession = (SessionMap<String, Object>) session;
}

public String logout() {
	if (userSession != null) {
		System.out.println("userSession Invalidated........:)");
		userSession.invalidate();
	}
	return "success";
}
}
