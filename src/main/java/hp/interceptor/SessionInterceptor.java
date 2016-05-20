package hp.interceptor;

import hp.model.pageModel.CurrentUser;
import hp.util.ConfigUtil;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class SessionInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 843981519562752524L;
	private static final Logger logger = Logger.getLogger(SessionInterceptor.class);

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		CurrentUser currentUser = (CurrentUser) ServletActionContext.getRequest().getSession().getAttribute(ConfigUtil.getSessionInfoName());
		//logger.info("进入session拦截器->访问路径为[" + ServletActionContext.getRequest().getServletPath() + "]");
		if (currentUser == null) {
			String errMsg = "您还没有登录或登录已超时，请重新登录.";
			logger.info(errMsg);
			ServletActionContext.getRequest().setAttribute("msg", errMsg);
			return "noSession";
		}
		return invocation.invoke();
	}

}
