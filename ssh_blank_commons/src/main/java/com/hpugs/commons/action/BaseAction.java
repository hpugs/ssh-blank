package com.hpugs.commons.action;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.web.context.ServletContextAware;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hpugs.commons.email.tencent.SendEmailUtil;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @Description action基类，所有的 action都应该继承它
 *
 * @author 高尚 
 * @version 1.0
 * @date 创建时间：2017年12月6日 下午2:40:02
 */
public class BaseAction extends ActionSupport
		implements ServletRequestAware, ServletResponseAware, ServletContextAware {
	
	private static final long serialVersionUID = 1L;
	
	protected Log log = LogFactory.getLog(this.getClass());// 打印log日志

	protected HttpServletRequest request;
	protected HttpSession session;
	protected HttpServletResponse response;
	protected Integer pageCount = 1;// 分页总数
	protected Integer pageSize = 20;// 页容量
	protected Integer currentPage = 1;// 当前请求页
	protected Integer page = 1;// 页码--easyUI自带分页功能使用
	protected Integer rows = 20;// 页容量--easyUI自带分页功能使用
	protected static Map<Object, Object> fileUrlConfig = new HashMap<Object, Object>();
	protected static Map<Object, Object> shopConfig = new HashMap<Object, Object>();
	protected ServletContext servletContext;// servlet 上下文
	static {
		String filePath = "systemConfig.properties";
		String shopConfigFilePath = "shopConfig.properties";
		Properties ps = new Properties();
		Properties shopConfigPS = new Properties();
		try {
			InputStream in = BaseAction.class.getClassLoader().getResourceAsStream(filePath);
			InputStream shopConfigIS = BaseAction.class.getClassLoader().getResourceAsStream(shopConfigFilePath);
			ps.load(in);
			shopConfigPS.load(shopConfigIS);
		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		fileUrlConfig = ps;
		shopConfig = shopConfigPS;
	}

	/**
	 * @Description 用于AJAX结果返回
	 * @param jsonMap
	 *            返回参数Map对象
	 * @throws IOException
	 * 
	 * DisableCheckSpecialChar：一个对象的字符串属性中如果有特殊字符如双引号，将会在转成json时带有反斜杠转移符。如果不需要转义，可以使用这个属性。默认为false
	 * QuoteFieldNames———-输出key时是否使用双引号,默认为true
	 * WriteMapNullValue——–是否输出值为null的字段,默认为false
	 * WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null
	 * WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null
	 * WriteNullStringAsEmpty—字符类型字段如果为null,输出为”“,而非null
	 * WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
	 *
	 * @author 高尚
	 * @version 1.0
	 * @date 创建时间：2017年6月10日 下午1:27:31
	 */
	protected void writeJsonFromMap(Map<String, Object> jsonMap) {
		response.setContentType("text/html;charset=UTF-8");// 解决中文乱码
		response.setCharacterEncoding("UTF-8");
		String jo = JSON.toJSONString(jsonMap, SerializerFeature.WriteMapNullValue);
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(jo);
		} catch (IOException e) {
			SendEmailUtil.send("接口返回异常", "1、jsonMap格式数据返回异常；2、返回参数：" + jo + "；3、异常信息：" + e.toString());
		}finally {
			if(null != out){
				out.flush();
				out.close();
			}
		}
	}
	
	/**
	 * @Description 用于AJAX结果返回
	 * @param result
	 *            返回参数String对象
	 * @throws IOException
	 *
	 * @author 高尚
	 * @version 1.0
	 * @date 创建时间：2017年6月13日 上午10:23:24
	 */
	protected void writeJsonFromString(String result) {
		response.setContentType("text/html;charset=UTF-8");// 解决中文乱码
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			SendEmailUtil.send("接口返回异常", "1、String格式数据返回异常；2、返回参数：" + result + "；3、异常信息：" + e.toString());
		}finally {
			if(null != out){
				out.flush();
				out.close();
			}
		}
	}
	
	// request session 的 getter setter方法
	public void setServletRequest(HttpServletRequest req) {
		servletContext.setAttribute("fileUrlConfig", fileUrlConfig);
		this.request = req;
		this.session = this.request.getSession();
		// this.session.setAttribute("fileUrlConfig", fileUrlConfig);
	}

	public void setServletResponse(HttpServletResponse res) {
		this.response = res;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public static Map<Object, Object> getFileUrlConfig() {
		return fileUrlConfig;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}
	
	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	/**** session cache ****/
	/**
	 * 
	 * @Description 通过 key 来获取 session 中的值
	 * @param key
	 * @return session 中的值
	 *
	 * @author 付雄
	 * @version 1.0
	 * @date 创建时间：2017年6月17日 下午1:43:58
	 */
	public <T> Object getSessionCacheByKey(T key) {
		return session.getAttribute(String.valueOf(key));
	}

	/**
	 * 
	 * @Description 向指定的key中添加或者更新值
	 * @param key
	 * @param value
	 * @return 成功返回 true,失败返回false
	 *
	 * @author 付雄
	 * @version 1.0
	 * @date 创建时间：2017年6月17日 下午2:10:47
	 */
	public <K, V> boolean setSessionCacheByKey(K key, V value) {
		try {
			session.setAttribute(String.valueOf(key), value);
			return true;
		} catch (Exception e) {
			// TODO: handle exception - push to log file
			return false;
		}
	}
	
}
