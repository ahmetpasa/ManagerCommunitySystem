package com.example.demo;


import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.AppState;
import com.example.demo.dao.AppStateMember;
import com.example.demo.dao.Appointment;
import com.example.demo.dao.FamilyAnswer;
import com.example.demo.dao.FamilyMember;
import com.example.demo.dao.Managers;
import com.example.demo.service.AppStateMemberService;
import com.example.demo.service.AppStateService;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.CustomUserDetails;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.service.FamilyMemberService;
import com.example.demo.validator.AppointmentValidator;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.qrcode.EncodeHintType;
import com.itextpdf.text.pdf.qrcode.ErrorCorrectionLevel;

@Controller
public class Demodemocontroller {	
	
	@Autowired
	private AppointmentService appService;
	
	@Autowired
	private FamilyMemberService fmService;
	
	@Autowired
	private AppStateService appStateService;
	
	@Autowired
	private AppStateMemberService appStateMemberService;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public String login(Model model, @RequestParam(value = "errorMsg", required = false)String error,
    						@RequestParam(value = "logout", required = false) String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "home.jsp";
    }
	
	/*@RequestMapping(value= "/loginControl", method = {RequestMethod.POST})
	public Managers auth()*/
	
	@RequestMapping(value = "/abc", method = RequestMethod.GET)
	public String doLogin(Model model) {
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("name", user.getNameandSurname());
		model.addAttribute("email", user.getUsername());
		model.addAttribute("countapps", appService.countId());
		model.addAttribute("countfamily", fmService.countMembers(user.getWholeManager().getManager_ID()));
		model.addAttribute("countallfamily", fmService.countAllMembers());
		if(appService.findClosestDateOne().getAppType().equals("Individual")) {	
		AppState appState = new AppState();
		if((appStateService.countExists(user.getWholeManager().getManager_ID(), appService.findClosestDateOne().getAppointmentID())) != 0) {
			appState = appStateService.findExists(user.getWholeManager().getManager_ID(), appService.findClosestDateOne().getAppointmentID());
		}
		model.addAttribute("appStateForm", appState);
		}
		model.addAttribute("closestapp", appService.findClosestDateOne());	
		if(appService.findClosestDateOne().getAppType().equals("Family")) {	
			List<FamilyMember> list = new ArrayList<FamilyMember>();
			user.getFamilyMembers().forEach(e -> list.add(e));
			model.addAttribute("comingMembers", list);
			model.addAttribute("familyAnswer", new FamilyAnswer());
		}
		return "login.jsp";
	}
	
	@RequestMapping(value="/updateAnswer", method = RequestMethod.POST)
	public String createAppointment(@ModelAttribute("appStateForm") AppState appState, @ModelAttribute("comingmembers") ArrayList<Long> list, Model model) {
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(appState.isJoiningState() && (appState.getDescription().length() > 0)) {
			appState.setDescription("");
		}
		appState.setAppointment(appService.findClosestDateOne());
		appState.setManager(user.getWholeManager());
		appState.setAttendance(false);
		System.out.println(appState.getAppointment().getDescription_app());
		appStateService.save(appState);
		return "redirect:/abc";
	}
	
	@RequestMapping(value="/updateAnswerFamily", method = RequestMethod.POST)
	public String createFamily(@ModelAttribute("familyAnswer") FamilyAnswer fa, Model model) {
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		for(int i = 0; i < fa.getComingMembers().length; i++) {
			System.out.println(fa.getComingMembers()[i]);
		}
		List<Long> list = Stream.of(fa.getComingMembers()).map(Long::valueOf).collect(Collectors.toList());
		if(fa.isJoiningStateFamily()) {
			AppState appState = new AppState();
			if((appStateService.countExists(user.getWholeManager().getManager_ID(), appService.findClosestDateOne().getAppointmentID())) != 0) {
				appState = appStateService.findExists(user.getWholeManager().getManager_ID(), appService.findClosestDateOne().getAppointmentID());
				appState.setDescription("");
				appState.setJoiningState(fa.isJoiningStateFamily());
				appState.setAttendance(false);
			}
			else {
				appState.setDescription("");
				appState.setJoiningState(fa.isJoiningStateFamily());
				appState.setAttendance(false);
				appState.setAppointment(appService.findClosestDateOne());
				appState.setManager(user.getWholeManager());
			}
			appStateService.save(appState);
			for(long id : list) {
				AppStateMember appStateMember = new AppStateMember();
				if((appStateMemberService.countExists(id, appService.findClosestDateOne().getAppointmentID())) != 0) {
					appStateMember = appStateMemberService.findExists(id, appService.findClosestDateOne().getAppointmentID());
					appStateMember.setAttendance(false);
					appStateMemberService.save(appStateMember);
				}
				else {
					AppStateMember asm = new AppStateMember();
					asm.setMember(fmService.findbyId(id));
					asm.setAttendance(false);
					asm.setAppointment(appService.findClosestDateOne());
					appStateMemberService.save(asm);
				}
			}
		}
		else {
			AppState appState = new AppState();
			if((appStateService.countExists(user.getWholeManager().getManager_ID(), appService.findClosestDateOne().getAppointmentID())) != 0) {
				appState = appStateService.findExists(user.getWholeManager().getManager_ID(), appService.findClosestDateOne().getAppointmentID());
				appState.setDescription(fa.getDescriptionFamily());
				appState.setJoiningState(fa.isJoiningStateFamily());
				appState.setAttendance(false);
			}
			else {
				appState.setDescription(fa.getDescriptionFamily());
				appState.setJoiningState(fa.isJoiningStateFamily());
				appState.setAttendance(false);
				appState.setAppointment(appService.findClosestDateOne());
				appState.setManager(user.getWholeManager());
			}
			appStateService.save(appState);			
		}
		List<AppStateMember> old = appStateMemberService.findByAppID(appService.findClosestDateOne().getAppointmentID());
		for(AppStateMember element : old) {
			if(!list.contains(element.getMember().getMemberID())) {
				appStateMemberService.delete(appStateMemberService.findExists(element.getMember().getMemberID(), appService.findClosestDateOne().getAppointmentID()));
			}
		}
		return "redirect:/abc";
	}
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout";
	}
	
	@RequestMapping(value="/details", method = RequestMethod.GET)
	public String detailPage (Model model) {
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("name", user.getNameandSurname());
		model.addAttribute("email", user.getUsername());
		model.addAttribute("user", customUserDetailsService.findbyManager(user.getWholeManager().getManager_ID()));
	    return "details.jsp";
	}
	
	@RequestMapping(value="/createqr/{id}", method=RequestMethod.GET)
	public void QRPdf(@PathVariable long id, HttpServletResponse response) throws IOException, DocumentException{
		Document document = new Document();
		document.open();
        try{
            response.setContentType("application/pdf");
            PdfWriter.getInstance(document, response.getOutputStream());
            Map<EncodeHintType, Object> hintmap = new HashMap<EncodeHintType, Object>();
            hintmap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
            document.open();
            BarcodeQRCode barcodeQRCode = new BarcodeQRCode("http://192.168.1.198:8083/api/qr/appid/" + id, 1000, 1000, hintmap);
            Image codeQrImage = barcodeQRCode.getImage();
            codeQrImage.scaleAbsolute(500, 500);
            document.add(codeQrImage);
        }catch(Exception e){
            e.printStackTrace();
        }
        document.close();
	}
}
