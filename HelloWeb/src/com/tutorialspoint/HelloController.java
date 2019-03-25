package com.tutorialspoint;


import java.util.ArrayList;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.cj.Session;

import Dao.UserDao;
import model.Candidate;
import model.Employee;
import model.Result;
import model.Users;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

@Controller

public class HelloController extends HttpServlet
	{
	HttpServletRequest request;
	@Autowired
	UserDao dao;
   	
	@RequestMapping(value="/hello", method = RequestMethod.GET)
   public String printHello(ModelMap model) 
   	{
      model.addAttribute("message", "Hello Spring MVC Framework!");
      return "hello";
   }
   	@RequestMapping(value="/login_chk",method=RequestMethod.GET)
   	public String login(@ModelAttribute("login")Employee lm)
   
   	{	/*System.out.println("In login");
   		return "redirect:/Action_file.jsp";
	*/System.out.println(lm.getId());
   		String password=dao.loginChk(lm);
   		if(lm.getPassword().equals(password))
   		{
   		return "redirect:/home.jsp";}
   		else
   		{
   			System.out.println("error");
   			return "redirect:/error.jsp";
   		}
   	}
   	
   /*	public String getvalue(ModelMap model)
   	{
   		model.addAttribute("id")
   	}*/
   	@RequestMapping(value="/register",method=RequestMethod.GET)
   	public String register(@ModelAttribute("reg")Employee emp)
   	{
   		System.out.println("id:"+emp.getId());
   		
   		int i=dao.save(emp);
   		System.out.println("saved successfully"+i);
   		return "Action_file.jsp";
   	}
   	
   	@RequestMapping(value="/candidateDetails",method=RequestMethod.GET)
   	public String saveCandidate(@ModelAttribute("candidate")Candidate candidate)
   	{
   		//int i=dao.saveCandidate(candidate);
   		int id=candidate.getId();
   		String fname=candidate.getFirstname();
   		String lname=candidate.getLastname();
   		String category=candidate.getCategory();
   		
   		System.out.println("saved successfully"+id+""+fname+""+lname+""+category);
   		return "";
   	}
   	@RequestMapping(value="/result",method=RequestMethod.POST)
   	public String showResultPage(Model model,@ModelAttribute("cand")Candidate cand)
   	{
   		String category=cand.getCategory();
   		
   		java.util.List<Candidate> list=dao.getCandidates(category);
   		
   		System.out.println("id is:"+list.size());
   		for(int i=0;i<list.size();i++)
   		{
   			System.out.println("values:"+list.get(i).getId());
   			System.out.println("values:"+list.get(i).getFirstname());
   		}
   
   				model.addAttribute("msg","hello");
   
   		model.addAttribute("list",list);
   		return "result";
   	}
   	@RequestMapping(value="/result_details/{user}",method=RequestMethod.GET)
   	@ResponseBody
   	public String saveResultDetails(@ModelAttribute("user")ArrayList<Result> user)
   
   	{

   		//ArrayList<Result> result=request.getParameter("user");
   		//System.out.println("in controller"+list);
   		/*int j=dao.updateResult(list);
   		for(int i=0;i<list.size();i++)
   		{
   			System.out.println("Record list:"+list.get(i).getL0()+""+list.get(i).getId()+""+list.get(i).getHr());
   		}*/
   		System.out.println("user value:"+user);
   		//System.out.println(user.getId()+""+user.getL0()+""+user.getL1()+""+user.getName());
   		//Result result=user.getResult().get(0);
   		//System.out.println(result);
   		return "redirect:/error.jsp";
   		/*ModelAndView model = new ModelAndView();
   		Users wrapper = new Users();
   	    ArrayList<Result> res=wrapper.getResult();
   	    System.out.println(res.get(0).getId());
   	    model.addObject("userListWrapper",wrapper);

   	    model.setViewName("error");
   	    return model;
   		return "";*/
   	}
}