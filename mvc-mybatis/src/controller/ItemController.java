package controller;

import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import pojo.Items;
import pojo.QueryVo;
import service.ItemService;

@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	//入门程序 第一 包类+类包+方法名字
	@RequestMapping(value = "/item/itemlist.action")
	public ModelAndView itemlist() {
		List<Items> list =itemService.selectItemsList();
		
		ModelAndView mav=new ModelAndView();
		//数据
		mav.addObject("itemList",list);
		mav.setViewName("itemList");
		return mav;
	}
	
	//去修改页面 入参id
	@RequestMapping(value="/itemEdit.action")
	public ModelAndView toEdit(Integer id,
			HttpServletRequest request,
			HttpServletResponse response,HttpSession session,Model model) {
		Items items =itemService.selectItemsById(id);
		ModelAndView mav =new ModelAndView();
		//数据
		mav.addObject("item", items);
		mav.setViewName("editItem");
		return mav;
	}
	//提交修改页面 入参为Items对象
	@RequestMapping(value="/updateitem.action")
	public ModelAndView updateitem(QueryVo vo,MultipartFile pictureFile) throws Exception, Exception {
		String name=UUID.randomUUID().toString().replace("-", "");
		String ext=FilenameUtils.getExtension(pictureFile.getOriginalFilename());
		pictureFile.transferTo(new File("D:\\\\load\\\\"+name+"."+ext));
		vo.getItems().setPic(name+"."+ext);
		//修改
		itemService.updateItemsById(vo.getItems());
		ModelAndView mav= new ModelAndView();
		mav.setViewName("success");
	return mav;
	}
	//delete多个
	@RequestMapping(value="//deletes.action")
	public ModelAndView deletes(Integer[] ids) {
		
		 
		
		ModelAndView mav= new ModelAndView();
		mav.setViewName("success");
		return mav;
	}
	//修改
	@RequestMapping(value="/update.action",method= {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView updates(QueryVo vo) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("success");
		return mav;
	}
	//json数据交互
	@RequestMapping(value="/json.action")
	public @ResponseBody
	Items json(@RequestBody Items items){
		return 	items;
	}
	//RestFul风格的开发
	@RequestMapping(value="/itemEdit/{id}.action")
	public ModelAndView toEdit1(@PathVariable Integer id,HttpServletRequest request,
			HttpServletResponse response,HttpSession session,Model model) {
//		Items items=itemService.selectItemsById(Integer.parseInt(id));
		Items items=itemService.selectItemsById(id);
		ModelAndView mav=new ModelAndView();
		mav.addObject("item", items);
		mav.setViewName("editItem");
		return mav;
	}
	//去登录的页面
	@RequestMapping(value="login.action",method=RequestMethod.GET)
	public String login() {
		return "login";
	}
	@RequestMapping(value="login.action",method=RequestMethod.POST)
	public String login(String username,HttpSession httpSession) {
		httpSession.setAttribute("USER_SESSION", username);
	return "redirect:/item/itemlist.action";
	}
}
