package com.neu.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.neu.model.ProfileRequest;
import com.neu.model.Education;
import com.neu.model.Error;
import com.neu.model.ErrorResponse;
import com.neu.model.Experience;
import com.neu.model.Message;
import com.neu.model.Profile;
import com.neu.model.User;
import com.neu.service.ProfileRequestConvertor;
import com.neu.service.ProfileService;
import com.neu.service.UserExtracter;
import com.neu.service.UserService;

/**
 * @author Keshav
 *
 */
@RestController
@RequestMapping("/")
public class ProfileController {
    @Autowired
    UserExtracter userExtractor;
	@Autowired
	ProfileService profileService;
	@Autowired
	ProfileRequestConvertor profileRequestConvertor;
	@Autowired
	UserService userService;
	   @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
	            produces = MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.POST,
	      value="/profile/me")
	   public ResponseEntity<?> save(@RequestHeader(value="x-auth-token",required = true) String requestTokenHeader,@RequestBody ProfileRequest addprofilerequest) {
	        
	         User user;
			   try {
			   user=userExtractor.getUserFromtoken(requestTokenHeader);
			  }
			  catch(Exception ex) {
				  Error error=new Error();
			   	   //error.setValue(ex.getMessage());
			   	   error.setMsg("Invalid/Expired token");
			   	   List<Error> errorlist=new ArrayList<>();
			   	   errorlist.add(error);
			          ErrorResponse errors = new ErrorResponse(errorlist);
			   	  return new ResponseEntity<ErrorResponse>(errors , HttpStatus.BAD_REQUEST);
			  } 
			 Profile profile=profileRequestConvertor.ConvertRequestToProfile(addprofilerequest);
			Profile profileres=profileService.addProfile(profile, user);
			  // Profile profileres=null;
	      return new ResponseEntity<Profile>(profileres,HttpStatus.OK);
	   }
	   @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
	            produces = MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.PUT,
	      value="/profile/me")
	   public ResponseEntity<?> update(@RequestHeader(value="x-auth-token",required = true) String requestTokenHeader,@RequestBody Profile profile) {
		   User user;
		   try {
		   user=userExtractor.getUserFromtoken(requestTokenHeader);
		  }
		  catch(Exception ex) {
			  Error error=new Error();
		   	   //error.setValue(ex.getMessage());
		   	   error.setMsg("Invalid/Expired token");
		   	   List<Error> errorlist=new ArrayList<>();
		   	   errorlist.add(error);
		          ErrorResponse errors = new ErrorResponse(errorlist);
		   	  return new ResponseEntity<ErrorResponse>(errors , HttpStatus.BAD_REQUEST);
		  } 
		 // Profile profile=profileService.getProfile(user);
		 //  Profile profile2=profileRequestConvertor.ConvertRequestToProfileUpdate(updateprofilerequest,profile);
		   Profile profileres=profileService.updateProfile(profile, user);
	      return new ResponseEntity<Profile>(profileres,HttpStatus.OK);
	   }
	   @RequestMapping(
	            produces = MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.GET,
	      value="/profile/me")
	   public ResponseEntity<?> get(@RequestHeader(value="x-auth-token",required = true) String requestTokenHeader) {
		   User user;
		   try {
		   user=userExtractor.getUserFromtoken(requestTokenHeader);
		  }
		  catch(Exception ex) {
			  Error error=new Error();
		   	   //error.setValue(ex.getMessage());
		   	   error.setMsg("Invalid/Expired token");
		   	   List<Error> errorlist=new ArrayList<>();
		   	   errorlist.add(error);
		          ErrorResponse errors = new ErrorResponse(errorlist);
		   	  return new ResponseEntity<ErrorResponse>(errors , HttpStatus.BAD_REQUEST);
		  } 
			Profile profile=profileService.getProfile(user);
			if(profile==null) {
				Message msg=new Message();
				msg.setMsg("There is no profile for this user");
				return new ResponseEntity<Message>(msg,HttpStatus.BAD_REQUEST);
			}
	      return new ResponseEntity<Profile>(profile,HttpStatus.OK);
	   }
	   @RequestMapping(
	            produces = MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.GET,
	      value="/profiles")
	   public ResponseEntity<?> getProfilefromEmail(@RequestParam(value="email") String email) {
//		   User user;
//		   try {
//		   user=userExtractor.getUserFromtoken(requestTokenHeader);
//		  }
//		  catch(Exception ex) {
//			  Error error=new Error();
//		   	   //error.setValue(ex.getMessage());
//		   	   error.setMsg("Invalid/Expired token");
//		   	   List<Error> errorlist=new ArrayList<>();
//		   	   errorlist.add(error);
//		          ErrorResponse errors = new ErrorResponse(errorlist);
//		   	  return new ResponseEntity<ErrorResponse>(errors , HttpStatus.BAD_REQUEST);
//		  } 
		  // User user=null;
		  User user=userService.getUserfromemail(email);
			Profile profile=profileService.getProfile(user);
			if(profile==null) {
				Message msg=new Message();
				msg.setMsg("There is no profile for this user");
				return new ResponseEntity<Message>(msg,HttpStatus.BAD_REQUEST);
			}
	      return new ResponseEntity<Profile>(profile,HttpStatus.OK);
	   }
	   @RequestMapping(
	            produces = MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.GET,
	      value="/profiles/all")
	   public ResponseEntity<?> getallProfiles() {
//		   User user;
//		   try {
//		   user=userExtractor.getUserFromtoken(requestTokenHeader);
//		  }
//		  catch(Exception ex) {
//			  Error error=new Error();
//		   	   //error.setValue(ex.getMessage());
//		   	   error.setMsg("Invalid/Expired token");
//		   	   List<Error> errorlist=new ArrayList<>();
//		   	   errorlist.add(error);
//		          ErrorResponse errors = new ErrorResponse(errorlist);
//		   	  return new ResponseEntity<ErrorResponse>(errors , HttpStatus.BAD_REQUEST);
//		  } 
		   List<Profile> profiles=profileService.listProfiles();
		   if(profiles==null||profiles.size()==0) {
				Message msg=new Message();
				msg.setMsg("No profiles found");
				return new ResponseEntity<Message>(msg,HttpStatus.BAD_REQUEST);
			}
//		   for(Profile profile:profiles) {
//			   profile.setSkills(null);
//		   }
	      return new ResponseEntity<List<Profile>>(profiles,HttpStatus.OK);
	   }
	   @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
	            produces = MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.PUT,
	      value="/profile/experience")
	   public ResponseEntity<?> addexperience(@RequestHeader(value="x-auth-token",required = true) String requestTokenHeader,@RequestBody Experience experience) {
	        
	         User user;
			   try {
			   user=userExtractor.getUserFromtoken(requestTokenHeader);
			  }
			  catch(Exception ex) {
				  Error error=new Error();
			   	   //error.setValue(ex.getMessage());
			   	   error.setMsg("Invalid/Expired token");
			   	   List<Error> errorlist=new ArrayList<>();
			   	   errorlist.add(error);
			          ErrorResponse errors = new ErrorResponse(errorlist);
			   	  return new ResponseEntity<ErrorResponse>(errors , HttpStatus.BAD_REQUEST);
			  } 
			   
				Profile profile=profileService.getProfile(user);
			profileService.addExperience(experience, profile);
			Profile profileres=profileService.getProfile(user);
			  // Profile profileres=null;
	      return new ResponseEntity<Profile>(profileres,HttpStatus.OK);
	   }
	   @RequestMapping(
	            produces = MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.DELETE,
	      value="/profile/experience/{id}")
	   public ResponseEntity<?> deleteExperience(@PathVariable(value="id")int expid,@RequestHeader(value="x-auth-token",required = true) String requestTokenHeader) {
	        
	         User user;
			   try {
			   user=userExtractor.getUserFromtoken(requestTokenHeader);
			  }
			  catch(Exception ex) {
				  Error error=new Error();
			   	   //error.setValue(ex.getMessage());
			   	   error.setMsg("Invalid/Expired token");
			   	   List<Error> errorlist=new ArrayList<>();
			   	   errorlist.add(error);
			          ErrorResponse errors = new ErrorResponse(errorlist);
			   	  return new ResponseEntity<ErrorResponse>(errors , HttpStatus.BAD_REQUEST);
			  } 
			   
				Profile profile=profileService.getProfile(user);
			profileService.deleteExperience(expid);
			Profile profileres=profileService.getProfile(user);
			  // Profile profileres=null;
	      return new ResponseEntity<Profile>(profileres,HttpStatus.OK);
	   }
	   @RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
	            produces = MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.PUT,
	      value="/profile/education")
	   public ResponseEntity<?> addeducation(@RequestHeader(value="x-auth-token",required = true) String requestTokenHeader,@RequestBody Education education) {
	        
	         User user;
			   try {
			   user=userExtractor.getUserFromtoken(requestTokenHeader);
			  }
			  catch(Exception ex) {
				  Error error=new Error();
			   	   //error.setValue(ex.getMessage());
			   	   error.setMsg("Invalid/Expired token");
			   	   List<Error> errorlist=new ArrayList<>();
			   	   errorlist.add(error);
			          ErrorResponse errors = new ErrorResponse(errorlist);
			   	  return new ResponseEntity<ErrorResponse>(errors , HttpStatus.BAD_REQUEST);
			  } 
			   
				Profile profile=profileService.getProfile(user);
			profileService.addEducation(education, profile);
			Profile profileres=profileService.getProfile(user);
			  // Profile profileres=null;
	      return new ResponseEntity<Profile>(profileres,HttpStatus.OK);
	   }
	   @RequestMapping(
	            produces = MediaType.APPLICATION_JSON_VALUE, method= RequestMethod.DELETE,
	      value="/profile/education/{id}")
	   public ResponseEntity<?> deleteEducation(@PathVariable(value="id")int eduid,@RequestHeader(value="x-auth-token",required = true) String requestTokenHeader) {
	        
	         User user;
			   try {
			   user=userExtractor.getUserFromtoken(requestTokenHeader);
			  }
			  catch(Exception ex) {
				  Error error=new Error();
			   	   //error.setValue(ex.getMessage());
			   	   error.setMsg("Invalid/Expired token");
			   	   List<Error> errorlist=new ArrayList<>();
			   	   errorlist.add(error);
			          ErrorResponse errors = new ErrorResponse(errorlist);
			   	  return new ResponseEntity<ErrorResponse>(errors , HttpStatus.BAD_REQUEST);
			  } 
			   
				Profile profile=profileService.getProfile(user);
			profileService.deleteEducation(eduid);
			Profile profileres=profileService.getProfile(user);
			  // Profile profileres=null;
	      return new ResponseEntity<Profile>(profileres,HttpStatus.OK);
	   }
}

