package hp.interceptor;

import hp.model.SysResource;
import hp.model.SysRole;
import hp.model.pageModel.CurrentUser;
import hp.service.SysResourceService;
import hp.util.ConfigUtil;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class SecurityInterceptor extends MethodFilterInterceptor {

	private static final long serialVersionUID = 5470370937254475400L;
	private static final Logger logger = Logger.getLogger(SecurityInterceptor.class);

	@SuppressWarnings("unchecked")
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		// ActionContext actionContext = actionInvocation.getInvocationContext();
		CurrentUser currentUser = (CurrentUser) ServletActionContext.getRequest().getSession().getAttribute(ConfigUtil.getSessionInfoName());
		String servletPath = ServletActionContext.getRequest().getServletPath();

		//servletPath = StringUtils.substringBeforeLast(servletPath, ".");// 去掉后面的后缀 *.action之类的

		//logger.info("进入权限拦截器->访问的资源为：[" + servletPath + "]");
		
		//只有数据库中配置的url才需要权限，没有配置，则放行
		List<String> lists = (List<String>) ServletActionContext.getServletContext().getAttribute("urls");
		if(lists == null) {
			ApplicationContext context = WebApplicationContextUtils.getRequiredWebApplicationContext(ServletActionContext.getServletContext());
			SysResourceService service = (SysResourceService) context.getBean("sysResourceService");
			lists = service.getResources();
			ServletActionContext.getServletContext().setAttribute("urls", service.getResources());
		}
		
		boolean isInvoc = true;//是否放行
		for(String url :lists){
			if(url.equals(servletPath)){
				isInvoc = false;
			}
		}
		if(isInvoc){
			return invocation.invoke();
		}
		

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
