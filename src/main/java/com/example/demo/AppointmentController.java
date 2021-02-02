package com.example.demo;

import java.text.SimpleDateFormat;
import java.util.Date;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dao.Appointment;
import com.example.demo.dao.FamilyMember;
import com.example.demo.service.AppStateService;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.CustomUserDetails;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.validator.AppointmentValidator;

@Controller
@SessionAttributes("user")
public class AppointmentController {
	
	@Autowired
	private AppointmentService appService;
	
	@Autowired
	private AppStateService appStateService;
	
	@Autowired
	private CustomUserDetailsService udService;
	
	@Autowired
	AppointmentValidator appointmentValidator;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.setValidator(appointmentValidator);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}
	
	@RequestMapping(value="/seeapps",method = RequestMethod.GET)
	public String seeAppointment(Model model) {
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("apps",appService.findAll());
		return "seeappointments.jsp";
	}
	
	@RequestMapping(value="/appointment", method = RequestMethod.GET)
	public String formAppointment(Model model) {
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("name", user.getNameandSurname());
		model.addAttribute("email", user.getUsername());
		Appointment app = new Appointment();
		model.addAttribute("appForm", app);
		return "createappointment.jsp";
	}
	
	@RequestMapping(value="/createappointment", method = RequestMethod.POST)
	public String createAppointment(@ModelAttribute("appForm") @Validated Appointment app,BindingResult result,Model model) {
		if(result.hasErrors()) {
			return "/createappointment.jsp";
		}
		else {
			CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			app.setManager(user.getWholeManager());
			appService.save(app);
			return "redirect:/abc";
		}		
	}
	@RequestMapping(value= {"/updateapp/{id}"}, method=RequestMethod.GET)
	public String updateMember(@PathVariable("id") long id,Model model) {
		Appointment app = appService.findbyId(id);
		model.addAttribute("appForm",app);
		return "createappointment.jsp";
	}
	@ResponseBody
	@RequestMapping(value= {"/deleteapp/{id}"}, method=RequestMethod.GET)
	public String deleteMember(@PathVariable("id") long id,Model model, RedirectAttributes redirectAttributes) {
		appService.delete(id);
		redirectAttributes.addAttribute("msg", "Member deleted successfully!");
		return "redirect:/abc";
	}
	@RequestMapping(value= {"/appdetails/{id}"}, method=RequestMethod.GET)
	public String detailsAppointment(@PathVariable("id") long id, Model model) {
		model.addAttribute("accepteduser", udService.findAcceptedUsers(id));
		model.addAttribute("rejecteduser", udService.findRejectedUsersAndDescriptions(id));
		model.addAttribute("notruser", udService.findNotAnsweredUsers(id));
		model.addAttribute("came", udService.findUsersThatCame(id));
		model.addAttribute("numberofaccepteduser", udService.countAcceptedUsers(id));
		model.addAttribute("numberofrejecteduser", udService.countRejectedUsers(id));
		model.addAttribute("numberofnotruser", udService.countNotAnsweredUsers(id));
		model.addAttribute("numberofcame", udService.countUsersThatCame(id));
		return "detailapp.jsp";
	}

}
