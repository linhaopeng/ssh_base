package hp.model.pageModel;

import hp.model.SysUser;

public class CurrentUser {
	private SysUser sysUser;

	public SysUser getSysUser() {
		return sysUser;
	}

	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}

	@Override
	public String toString() {
		return this.sysUser.getLoginname();
	}

}
