package com.example.demo.restcontrollers;

import java.io.Console;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.dao.*;
import com.example.demo.service.AppStateMemberService;
import com.example.demo.service.AppStateService;
import com.example.demo.service.AppointmentService;
import com.example.demo.service.CustomUserDetails;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.service.FamilyMemberService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api")
public class DemodemoRestController{
	
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
	
	@InitBinder
    public void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null,  new CustomDateEditor(dateFormat, true));
    }
	
	@RequestMapping(value="/details", method = RequestMethod.GET)
	@ResponseBody
	public Managers detailPage (Model model) {
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Managers userDetail = user.getWholeManager();
	    return userDetail;
	}
	
	@RequestMapping(value="/abc", method = RequestMethod.GET)
	@ResponseBody
	public List<Long> getNumericValues(){
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Long> list = new ArrayList<Long>();
		list.add(appService.countId());
		list.add(fmService.countMembers(user.getWholeManager().getManager_ID()));
		list.add(fmService.countAllMembers());
		return list;
	}
	
	@RequestMapping(value="/getClosest", method = RequestMethod.GET)
	@ResponseBody
	public Appointment getClosestOne() {
		return appService.findClosestDateOne();
	}
	
	@RequestMapping(value="/seeapps",method = RequestMethod.GET)
	@ResponseBody
	public List<Appointment> seeAppointments() {
		return appService.findAll();
	}
	
	@RequestMapping(value="/createappointment",method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> addAppointment(@ModelAttribute Appointment app, @RequestParam(required=false, name="dateof_app") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, @RequestParam(required=false, name="time") String time, @RequestParam(required=false, name="description_app") String desc, @RequestParam(required=false, name="appType") String type) throws MethodArgumentNotValidException{
		app.setDateof_app(date);
		app.setTime(time);
		app.setDescription_app(desc);
		app.setAppType(type);
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		app.setManager(user.getWholeManager());
		appService.save(app);
		Map<String, Object> success = new HashMap<>();
		success.put("success", true);
		success.put("message", "Appointment added successfully.");
		return new ResponseEntity<>(success, HttpStatus.OK);
	}
	
	@RequestMapping(value="/appStateSubmit", method= RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> addappState(@ModelAttribute AppState appState, @RequestParam(required=false, name="joiningstate") boolean joiningState, @RequestParam(required=false, name="description") String desc, @RequestParam(required=false, name="attendance") boolean attendance){
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if((appStateService.countExists(user.getWholeManager().getManager_ID(), appService.findClosestDateOne().getAppointmentID())) != 0) {
			appState = appStateService.findExists(user.getWholeManager().getManager_ID(), appService.findClosestDateOne().getAppointmentID());
			appState.setDescription(desc);
			appState.setJoiningState(joiningState);
			appState.setAttendance(attendance);
		}
		else {
			appState.setDescription(desc);
			appState.setJoiningState(joiningState);
			appState.setAttendance(attendance);
			appState.setAppointment(appService.findClosestDateOne());
			appState.setManager(user.getWholeManager());
		}
		appStateService.save(appState);
		Map<String, Object> success = new HashMap<>();
		success.put("success", true);
		success.put("message", "Appointment added successfully.");
		return new ResponseEntity<>(success, HttpStatus.OK);		
	}	
	
	@RequestMapping(value="/seefamilymembers",method = RequestMethod.GET)
	@ResponseBody
	public List<FamilyMember> seeFamilyMembers(){
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return fmService.findbyManager(user.getWholeManager().getManager_ID());
	}
	
	@RequestMapping(value="/createmember",method= RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> addMember(@ModelAttribute FamilyMember newMember, @RequestParam(required=false, name="name") String name, @RequestParam(required=false, name="surname") String surname, @RequestParam(required=false, name="email") String email, @RequestParam(required=false, name="telephone") String telephone, @RequestParam(required=false, name="address") String address, @RequestParam(required=false, name="birthday") Date birthday, @RequestParam(required=false, name="degree") int degree){
		newMember.setName(name);
		newMember.setSurname(surname);
		newMember.setEmail(email);
		newMember.setTelephone(telephone);
		newMember.setAddress(address);
		newMember.setBirthday(birthday);
		newMember.setDegree(degree);
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		newMember.setManager(user.getWholeManager());
		fmService.save(newMember);
		Map<String, Object> success = new HashMap<>();
		success.put("success", true);
		success.put("message", "Member added successfully.");
		return new ResponseEntity<>(success, HttpStatus.OK);		
	}
	
	@RequestMapping(value="/deletemember/{id}", method= RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> deleteMember(@PathVariable("id") long id){
		fmService.delete(id);
		Map<String, Object> success = new HashMap<>();
		success.put("success", true);
		success.put("message", "Member deleted successfully.");
		return new ResponseEntity<>(success, HttpStatus.OK);		
	}
	
	@RequestMapping(value="/deleteapp/{id}", method= RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> deleteApp(@PathVariable("id") long id){
		appService.delete(id);
		Map<String, Object> success = new HashMap<>();
		success.put("success", true);
		success.put("message", "Appointment deleted successfully.");
		return new ResponseEntity<>(success, HttpStatus.OK);		
	}
	
	@RequestMapping(value="/updateapp/{id}",method= RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Object> updateApp(@PathVariable("id") long id, @RequestParam(required=false, name="dateof_app") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date date, @RequestParam(required=false, name="time") String time, @RequestParam(required=false, name="description_app") String desc, @RequestParam(required=false, name="appType") String type){
		Appointment updating = appService.findbyId(id);
		updating.setDateof_app(date);
		updating.setTime(time);
		updating.setDescription_app(desc);
		updating.setAppType(type);
		appService.save(updating);
		if(type.equals("Individual")) {
			if(appStateMemberService.findByAppID(id).size() != 0) {
				for(AppStateMember element: appStateMemberService.findByAppID(id)) {
					appStateMemberService.delete(element);
				}				
			}
		}
		Map<String, Object> success = new HashMap<>();
		success.put("success", true);
		success.put("message", "Appointment updated successfully.");
		return new ResponseEntity<>(success, HttpStatus.OK);		
	}
	
	@RequestMapping(value="/updatemember/{id}",method= RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity<Object> updateMember(@PathVariable("id") long id, @RequestParam(required=false, name="name") String name, @RequestParam(required=false, name="surname") String surname, @RequestParam(required=false, name="email") String email, @RequestParam(required=false, name="telephone") String telephone, @RequestParam(required=false, name="address") String address, @RequestParam(required=false, name="birthday") Date birthday, @RequestParam(required=false, name="degree") int degree){
		FamilyMember updating = fmService.findbyId(id);
		updating.setName(name);
		updating.setSurname(surname);
		updating.setEmail(email);
		updating.setTelephone(telephone);
		updating.setAddress(address);
		updating.setBirthday(birthday);
		updating.setDegree(degree);
		fmService.save(updating);
		Map<String, Object> success = new HashMap<>();
		success.put("success", true);
		success.put("message", "Member updated successfully.");
		return new ResponseEntity<>(success, HttpStatus.OK);		
	}
	
	@RequestMapping(value="/appdetails/{id}",method= RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> detailsAppointment(@PathVariable("id") long id){
		Map<String, Object> success = new HashMap<>();
		String numberofaccept = Long.toString(customUserDetailsService.countAcceptedUsers(id));
		String numberofreject = Long.toString(customUserDetailsService.countRejectedUsers(id));
		String numberofnotr = Long.toString(customUserDetailsService.countNotAnsweredUsers(id));
		String numberofcame = Long.toString(customUserDetailsService.countUsersThatCame(id));
		success.put("listofaccept", customUserDetailsService.findAcceptedUsers(id));
		success.put("listofreject", customUserDetailsService.findRejectedUsersAndDescriptions(id));
		success.put("listofnotr", customUserDetailsService.findNotAnsweredUsers(id));
		success.put("listofcame", customUserDetailsService.findUsersThatCame(id));
		success.put("numberofaccept", numberofaccept);
		success.put("numberofreject", numberofreject);
		success.put("numberofnotr", numberofnotr);
		success.put("numberofcame", numberofcame);
		success.put("success", true);
		success.put("message", "Member updated successfully.");
		return new ResponseEntity<>(success, HttpStatus.OK);
	}
	
	@RequestMapping(value="/qr/appid/{id}", method= RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> qrAttendance(@ModelAttribute AppStateMember appStateMember, @ModelAttribute AppState appState, @PathVariable("id") long id, @RequestParam(required=false, name="cameids") String ids, @RequestParam(required=false, name="joiningstate") boolean joiningState, @RequestParam(required=false, name="description") String desc, @RequestParam(required=false, name="attendance") boolean attendance) throws JsonMappingException, JsonProcessingException{
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if((appStateService.countExists(user.getWholeManager().getManager_ID(), appService.findClosestDateOne().getAppointmentID())) != 0) {
			appState = appStateService.findExists(user.getWholeManager().getManager_ID(), appService.findClosestDateOne().getAppointmentID());
			appState.setDescription(desc);
			appState.setJoiningState(joiningState);
			appState.setAttendance(attendance);
		}
		/*else {
			appState.setDescription(desc);
			appState.setJoiningState(joiningState);
			appState.setAttendance(attendance);
			appState.setAppointment(appService.findClosestDateOne());
			appState.setManager(user.getWholeManager());
		}*/
		appStateService.save(appState);
		if(appService.findClosestDateOne().getAppType().equals("Family")) {
			ObjectMapper mapper = new ObjectMapper();
			List<Long> list = mapper.readValue(ids, new TypeReference<List<Long>>(){});
			for(Long memberid : list) {
				if((appStateMemberService.countExists(memberid, appService.findClosestDateOne().getAppointmentID())) != 0) {
					appStateMember = appStateMemberService.findExists(memberid, appService.findClosestDateOne().getAppointmentID());
					appStateMember.setAttendance(attendance);
					appStateMemberService.save(appStateMember);
				}
				else {
					AppStateMember asm = new AppStateMember();
					asm.setMember(fmService.findbyId(id));
					asm.setAttendance(attendance);
					asm.setAppointment(appService.findClosestDateOne());
					appStateMemberService.save(asm);
				}
			}
		}		
		Map<String, Object> success = new HashMap<>();
		success.put("success", true);
		success.put("message", "Welcome to appointment!");
		return new ResponseEntity<>(success, HttpStatus.OK);		
	}
	
	@RequestMapping(value="/appStateMember", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> familySubmit(@ModelAttribute AppState appState, @ModelAttribute AppStateMember appStateMember, @RequestParam(required=false, name="ids") String ids, @RequestParam(required=false, name="joiningstate") boolean joiningState, @RequestParam(required=false, name="description") String desc, @RequestParam(required=false, name="attendance") boolean attendance) throws JsonMappingException, JsonProcessingException{
		ObjectMapper mapper = new ObjectMapper();
		CustomUserDetails user = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if((appStateService.countExists(user.getWholeManager().getManager_ID(), appService.findClosestDateOne().getAppointmentID())) != 0) {
			appState = appStateService.findExists(user.getWholeManager().getManager_ID(), appService.findClosestDateOne().getAppointmentID());
			appState.setDescription(desc);
			appState.setJoiningState(joiningState);
			appState.setAttendance(attendance);
		}
		else {
			appState.setDescription(desc);
			appState.setJoiningState(joiningState);
			appState.setAttendance(attendance);
			appState.setAppointment(appService.findClosestDateOne());
			appState.setManager(user.getWholeManager());
		}
		appStateService.save(appState);
		List<Long> list = mapper.readValue(ids, new TypeReference<List<Long>>(){});
		for(Long id : list) {
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
		List<AppStateMember> old = appStateMemberService.findByAppID(appService.findClosestDateOne().getAppointmentID());
		for(AppStateMember element : old) {
			if(!list.contains(element.getMember().getMemberID())) {
				appStateMemberService.delete(appStateMemberService.findExists(element.getMember().getMemberID(), appService.findClosestDateOne().getAppointmentID()));
			}
		}
		Map<String, Object> success = new HashMap<>();
		success.put("success", true);
		success.put("message", "Answer and members saved!");
		return new ResponseEntity<>(success, HttpStatus.OK);
	}
}
