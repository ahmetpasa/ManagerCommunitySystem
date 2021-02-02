package com.example.demo;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dao.FamilyMember;
import com.example.demo.service.CustomUserDetails;
import com.example.demo.service.FamilyMemberService;
import com.example.demo.validator.MemberValidator;

@Controller
@SessionAttributes("user")
public class MemberController {
	
	@Autowired
	private FamilyMemberService fmService;
	
	@Autowired
	MemberValidator memberValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.setValidator(memberValidator);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping(value= {"/seemember"}, method=RequestMethod.GET)
	public String seeMember(Model model) {
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("members",fmService.findbyManager(user.getWholeManager().getManager_ID()));
		model.addAttribute("allMembers", fmService.findMembersWithManagers());
		return "seemembers.jsp";
	}
	@RequestMapping(value= {"/addmember"}, method=RequestMethod.GET)
	public String addMemberPage(Model model) {
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("name", user.getNameandSurname());
		model.addAttribute("email", user.getUsername());
		FamilyMember fm = new FamilyMember();
		model.addAttribute("memberForm",fm);
		return "addmember.jsp";
	}
	@RequestMapping(value= {"/addmember"}, method=RequestMethod.POST)
	public String addMember(@ModelAttribute("memberForm") @Validated FamilyMember fm,BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "/addmember.jsp";
		}
		else {
			CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			fm.setManager(user.getWholeManager());
			fmService.save(fm);
			return "redirect:/abc";
		}
		
	}
	@RequestMapping(value= {"/updatemember/{id}"}, method=RequestMethod.GET)
	public String updateMember(@PathVariable("id") long id,Model model) {
		FamilyMember fm = fmService.findbyId(id);
		model.addAttribute("memberForm",fm);
		return "addmember.jsp";
	}
	@RequestMapping(value= {"/deletemember/{id}"}, method=RequestMethod.GET)
	public String deleteMember(@PathVariable("id") long id,Model model, RedirectAttributes redirectAttributes) {
		fmService.delete(id);
		redirectAttributes.addAttribute("msg", "Member deleted successfully!");
		return "redirect:/abc";
	}
	

}
