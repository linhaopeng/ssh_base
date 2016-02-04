package hp.util;

import java.util.ResourceBundle;

/**
 * 项目参数工具类
 * 
 * 
 */
public class ConfigUtil {

	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("config");

	/**
	 * 获得currentUser名字
	 * 
	 * @return
	 */
	public static final String getSessionInfoName() {
		return bundle.getString("currentUser");
	}

	/**
	 * 通过键获取值
	 * 
	 * @param key
	 * @return
	 */
	public static final String get(String key) {
		return bundle.getString(key);
	}

}
