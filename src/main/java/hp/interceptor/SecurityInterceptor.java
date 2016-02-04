package hp.interceptor;

import hp.model.SysResource;
import hp.model.SysRole;
import hp.model.pageModel.CurrentUser;
import hp.util.ConfigUtil;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class SecurityInterceptor extends MethodFilterInterceptor {

	private static final Logger logger = Logger.getLogger(SecurityInterceptor.class);

	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		logger.info("进入了权限拦截器");
		// ActionContext actionContext = actionInvocation.getInvocationContext();
		CurrentUser currentUser = (CurrentUser) ServletActionContext.getRequest().getSession().getAttribute(ConfigUtil.getSessionInfoName());
		String servletPath = ServletActionContext.getRequest().getServletPath();

		//servletPath = StringUtils.substringBeforeLast(servletPath, ".");// 去掉后面的后缀 *.action之类的

		logger.info("进入权限拦截器->访问的资源为：[" + servletPath + "]");

		Set<SysRole> roles = currentUser.getSysUser().getRoles();
		for (SysRole role : roles) {
			for (SysResource resource : role.getResources()) {
				if (resource != null && StringUtils.equals(resource.getUrl(), servletPath)) {
					return invocation.invoke();
				}
			}
		}

		String errMsg = "您没有访问此功能的权限！功能路径为[" + servletPath + "]请联系管理员给你赋予相应权限。";
		logger.info(errMsg);
		ServletActionContext.getRequest().setAttribute("msg", errMsg);
		return "noSecurity";
	}

}
