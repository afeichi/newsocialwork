package com.utils;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class NavigationTag extends TagSupport{
	static final long serialVersionUID=2372405317744358833L;
	
	
	/*
	 * request中用于保护Page<E>对象的变量名，默认为“page”
	 */
	
	private String bean="page";
	 /*
	  * 分页跳转的url地址，
	  */
	private String url=null;
	
	//显示页码的数量
	 
	private int number=5;
	
	public int doStartTag() {
		JspWriter writer=pageContext.getOut();
		HttpServletRequest request=(HttpServletRequest)pageContext.getRequest();
		Page page=(Page) request.getAttribute(bean);
		if(page==null)
			return SKIP_BODY;
		url=resolveUrl(url,pageContext);
			//计算总页数
			int pageCount=page.getTotal()/page.getSize();
			if(page.getTotal()%page.getSize()>0) {
				pageCount++;
		}
			try {
				writer.print("<nav><ul class=\"pagination\">");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//显示“上一页按钮”
			if(page.getPage()>1) {
				String preUrl =append(url,"page",page.getPage()-1);
				preUrl=append(preUrl,"rows",page.getSize());
				try {
					writer.print("<li><a href=\"" + preUrl + "\">上一页</a></li>");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
            	try {
					writer.print("<li class=\"disabled\"><a href=\"#\">上一页</a></li>");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			//显示当前页码的前2页码和后两页码
			int indexPage =(page.getPage() -2>0?page.getPage()-2:1);
			for(int i=1; i<=number&&indexPage<=pageCount;indexPage++,i++) {
				if(indexPage==page.getPage()) {
                    try {
						writer.print( "<li class=\"active\"><a href=\"#\">"+indexPage+"<span class=\"sr-only\">(current)</span></a></li>");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                    continue;
				}
				String pageUrl=append(url,"page",indexPage);
				pageUrl =append(pageUrl,"rows",page.getSize());
                try {
					writer.print("<li><a href=\"" + pageUrl + "\">"+ indexPage +"</a></li>");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		//显示“下一页按钮”
                if(page.getPage()<pageCount) {
                	String nextUrl= append(url,"page",page.getPage()+1);
                	nextUrl=append(nextUrl,"rows",page.getSize());
                    try {
						writer.print("<li><a href=\"" + nextUrl + "\">下一页</a></li>");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                } else {
                	try {
						writer.print("<li class=\"disabled\"><a href=\"#\">下一页</a></li>");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
                try {
					writer.print("</nav>");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                }
			return SKIP_BODY;
			}
	
	private String append(String url,String key,int value) {
		return append(url,key,String.valueOf(value));
	}
	
	
	
	
	private String append(String url,String key,String value) {
		if(url==null||url.trim().length()==0) {
			return " ";
		}
		
		if(url.indexOf("?") == -1) {
			url=url+"?"+key+"="+value;
		}else {
			if(url.endsWith("?")) {
				url=url+key+"="+value;
			}else {
				url=url+"&amp"+key+"="+value;
			}
		}
		return url;
	}
	
	/*
	 * 为url添加翻页请求参数
	 * 
	 */
	private String resolveUrl(String url,javax.servlet.jsp.PageContext pageContext) {
		
		Map params=pageContext.getRequest().getParameterMap();
		for(Object key:params.keySet()) {
			if("page".equals(key)||"row".equals(key))continue;
			Object value =params.get(key);
			if(value==null)
				continue;
			if(value.getClass().isArray()) {
				url=append(url,key.toString(),((String[])value)[0]);
			}else if(value instanceof String) {
				url=append(url,key.toString(),value.toString());
			}
		}
		
		return url;		
	}
	public String getBean() {
		return bean;
	}
	
	public void setBean(String bean) {
		this.bean=bean;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url=url;
	}
	
	public void setNumber(int number) {
		this.number=number;
	}
	}
	
	


