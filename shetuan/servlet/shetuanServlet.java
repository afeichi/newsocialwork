package zkSocialNetworkProject.shetuan.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import zkSocialNetworkProject.domain.shetuanUser;
import zkSocialNetworkProject.shetuan.service.shetuanService;
import zkSocialNetworkProject.utils.BaseServlet;

@WebServlet("/user.do")
public class shetuanServlet extends BaseServlet {

	/**
	 * 后台用户信息管理
	 */
	private static final long serialVersionUID = 1L;
	//实例化业务层对象
	shetuanService service = new shetuanService();
	
	/**
	 * 用户信息添加功能
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */

	public void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置编码格式utf-8,解决post传递中文乱码，之后可以在过滤器中解决乱码问题
		response.setContentType("text/html;charset=utf-8");
		// 判断form的是否enctype="multipart
		boolean multipartContent = ServletFileUpload.isMultipartContent(request);
		// 是multipart,可支持文件上传
		if (multipartContent == true) {
			// 实例化User对象，对应domain包下的User实体类
			shetuanUser shetuan = new shetuanUser();
			// 创建DiskFileItemFactory工厂对象
			DiskFileItemFactory dfif = new DiskFileItemFactory();
			// 设置上传文件缓冲区大小
			dfif.setSizeThreshold(8192);
			// 创建FileUpload组件对象
			ServletFileUpload sfu = new ServletFileUpload(dfif);
			// 解决上传文件路劲乱码问题，可能用不到
			sfu.setHeaderEncoding("utf-8");
			try {
				// 解析普通request请求对象为文件对象,用于获取请求中上传的文件
				@SuppressWarnings("unchecked")
				List<FileItem> list = sfu.parseRequest(request);
				// 遍历list,判断input组件是普通组件还是文件上传组件
				for (FileItem item : list) {
					// 是文件上传组件
					if (!item.isFormField()) {
						// 获得文件的类型
						String type = item.getContentType();
						// 截取字符串，得到类后缀名字段
						String newtype = type.substring(type.indexOf("/") + 1);
						// 判断是否为图片类型，还可以扩充其他类型
						if (newtype.equals("png") || newtype.equals("jpg") || newtype.equals("jpeg") || newtype.equals("gif")) {
							// 随机生成唯一的一个id
							UUID uuid = UUID.randomUUID();
							// 获得服务器上存放图片的文件夹（这个文件夹位置不是很好，要改善），之后要改为文件分离的形式
							String uploadPath = request.getServletContext().getRealPath("\\upload\\user");
							// 生成图片上传路径
							String evident = uploadPath + "\\" + uuid + "." + newtype;
							// 设置图片上传路径
							shetuan.setEvident(evident);
							// 上传文件到指定路径下
							item.write(new File(evident));
						} else {
							// 不是图片
							response.getWriter().write("上传文件不是图片");
						}
					}
				}

				// 做查询或者删除时，会用到request.getParameter("input中的那么属性");
				// 而不用BeanUtils封装数据

				// 获得社团用户注册信息，传入参数
				Map<String, String[]> properties = request.getParameterMap();
				// 用BeanUtils进行数据封装
				BeanUtils.populate(shetuan, properties);

				//设置id
				shetuan.setUserid(null);

				boolean addInfo = service.addUser(shetuan);
				if (addInfo) {
					response.sendRedirect("");
				} else {
					request.setAttribute("adderror", "添加失败");
					request.getRequestDispatcher("/addmember.jsp").forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("error", "服务器错误");
				request.getRequestDispatcher("").forward(request, response);
			}
		} else {
			request.setAttribute("nofile", "表单不支持文件上传");
			request.getRequestDispatcher("").forward(request, response);
		}
	}
	
	/**
	 * 修改社团信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	public void updateUser(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charst=urf-8");
		String oldPath = request.getParameter("oldPath");
		// 判断form是否enctype=multipart;
		boolean multipartContent = ServletFileUpload.isMultipartContent(request);
		if (multipartContent == true) {
			  shetuanUser shetuan = new shetuanUser();
			// DiskFileltemFaxtory工厂对象
			DiskFileItemFactory dfif = new DiskFileItemFactory();
			// 设置上传文件缓存区的大小
			dfif.setSizeThreshold(8192);
			// 创建FileUpload组件对象
			ServletFileUpload sfu = new ServletFileUpload(dfif);
			sfu.setHeaderEncoding("utf-8");
			try {
				// 解析普通request请求对象为文件对象，用于获取请求中上传文件
				@SuppressWarnings("unchecked")
				List<FileItem> list = sfu.parseRequest(request);
				// 遍历list，判断input组件是普通组件还是文件上传组件
				for (FileItem item : list) {
					// 是文件上传组件
					if (!item.isFormField()) {
						// 获得文件的类型
						String type = item.getContentType();
						String fieldName = item.getFieldName();
						if (fieldName == null || fieldName == "") {
							shetuan.setEvident(oldPath);
						} else {
							// 截取字符串，得到类后缀名字段
							String newtype = type.substring(type.indexOf("/") + 1);
							// 判断是否为图片等类型
							if (newtype.equals("png") || newtype.equals("png") || newtype.equals("jpeg")
									|| newtype.equals("gif")) {
								// 随机生成唯一的一个id
								UUID uuid = UUID.randomUUID();
								// 获取服务器上存放图片的文件夹（待修改）
								String uploadPath = request.getServletContext().getRealPath("\\upload\\user");
								// 生成图片上传路径
								String evident = uploadPath + "\\" + uuid + "." + newtype;
								// 设置图片上传路径
								shetuan.setEvident(evident);
								// 上传文件到指定的路径下
								item.write(new File(evident));

							} else {
								// 不是图片
								response.getWriter().write("上传文件不是图片");
							}
						}
					}
				}
				// 获得用户注册信息，需要传入的参数需要（学号、真实姓名、昵称、身份证号、性别、情感状态、爱好、自我介绍）
				Map<String, String[]> properties = request.getParameterMap();
				// 用BeanUtils进行数据封装
				BeanUtils.populate(shetuan, properties);


				// 创建UserService对象，对应service层
				shetuanService service = new shetuanService();

				boolean updateInfo = service.updateUser(shetuan);
				if (updateInfo) {
					response.sendRedirect("/updateStudent.jsp");
				} else {
					request.setAttribute("updateerror", "添加失败");
					request.getRequestDispatcher("/updatestudent.jsp").forward(request, response);
				}
			} catch (Exception e) {
				e.printStackTrace();
				request.setAttribute("error", "服务器错误");
				request.getRequestDispatcher("").forward(request, response);
			}
		} else {
			request.setAttribute("nofile", "表单不支持文件上传");
			request.getRequestDispatcher("").forward(request, response);
		}
	}
	
	/**
	 * 删除社团信息by sid
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void deleteCom(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获得userid
		String userid = request.getParameter("userid");
		boolean deleteInfo = service.deleteCom(userid);
		if(deleteInfo) {
			request.setAttribute("deleteInfo", "删除成功");
			request.getRequestDispatcher("").forward(request, response);
		}else {
			request.setAttribute("deleteInfo", "删除失败");
			request.getRequestDispatcher("").forward(request, response);
		}
	}
	
	/**
	 * 查询所有社团
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public List<shetuanUser> Queryall(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String allInfo=request.getParameter("allInfo");
		List<shetuanUser> QueryallInfo=service.Queryall(allInfo);
		if(QueryallInfo!=null) {
			return QueryallInfo;
		}else{
			request.setAttribute("noCom","没有社团信息");
			request.getRequestDispatcher("").forward(request, response);
			return null;
		}
	}
	
	/**
	 * 查询社团by sid
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	public shetuanUser QueryInfoByid(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String sid=request.getParameter("sid");
		shetuanUser studentInfo=service.QueryInfoByid(sid);
		if(studentInfo!=null)
			return studentInfo;
		else
			request.setAttribute("noStudent","没有社团信息");
			request.getRequestDispatcher("/updaetestudent.jsp").forward(request, response);
			return null;
	}
	
}
