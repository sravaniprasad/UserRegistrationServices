package com.chengannagari.s.dashboard.Controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chengannagari.s.dashboard.Entity.JwtAuthenticationResponse;
import com.chengannagari.s.dashboard.Entity.SigninRequest;
import com.chengannagari.s.dashboard.Entity.User;
import com.chengannagari.s.dashboard.Entity.UserDTO;
import com.chengannagari.s.dashboard.Exceptions.UserNotFoundException;
import com.chengannagari.s.dashboard.Helper.ExcelHelper;
import com.chengannagari.s.dashboard.Helper.PdfExporter;
import com.chengannagari.s.dashboard.Helper.PdfService;
import com.chengannagari.s.dashboard.Reposiyory.UserRepository;
import com.chengannagari.s.dashboard.service.UserService;
import com.chengannagari.s.dashboard.service.UserServiceExcel;
import com.chengannagari.s.dashboard.serviceImplementation.UserServiceImplementation;
import com.lowagie.text.DocumentException;

import jakarta.servlet.http.HttpServletResponse;

	
@RestController
@RequestMapping("api/user")
@CrossOrigin("*")  
public class UserController {

	@Autowired
	private  UserService userService;
	
	@Autowired
	private UserServiceExcel excel;
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	PdfService service;

	private Map<String, Boolean> uploadedFiles = new HashMap<>();

//	@PostMapping("/save")
//	public String saveUser(@RequestBody UserDTO userDTO) {
//		String id= userService.addUser(userDTO);
//		return id;
//		
//	}
	
//	@PostMapping("/data/upload")
//	public ResponseEntity<?> upload(@RequestParam("file") MultipartFile file) throws Exception{
//		
//		Thread.sleep(5000);
//		if(file.isEmpty()) {
//			//return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Data not inserted!!! pease upload the file");
//			throw new UserNotFoundException("file is not uploaded!! upload the file to see the records");
//		}
//		else if(ExcelHelper.checkExcelFormat(file)) {
//			
//			this.excel.save(file);
//			
//			return ResponseEntity.ok(Map.of("message","File is Uploaded. Records are inserted into DataBase"));	
//		}
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Data not inserted due to BADREQUEST");
//		}
	
	 @GetMapping("/allusers")
	    public List<User> getAllUser() {
	        return userRepository.findAll();
	    }
	
	@PostMapping("/data/upload")
	public Map<String, String> upload(@RequestParam("file") MultipartFile file) throws Exception{
		
		Thread.sleep(5000);
		
	
		 Map<String, String> response = new HashMap<>();
	        if (file.isEmpty()) {
	            response.put("message", "Please select a file to upload");
	            return response;
	        }
	        
	        if (uploadedFiles.containsKey(file.getOriginalFilename())) {
	            response.put("message", "File already uploaded......");
	            return response;
	        }
             uploadedFiles.put(file.getOriginalFilename(), true);
	        if(ExcelHelper.checkExcelFormat(file)) {
				
				this.excel.save(file);
				  response.put("message", "File uploaded successfully... data recorded into database");
			}
	      
	        return response;
	    }
	
	//to read all data in db
	 @GetMapping("/all")
	    public List<User> getAllUsers() {
	        return userRepository.findAll();
	    }
	 
	 //search with firstname
	 @GetMapping("/name/{firstName}")
		public ResponseEntity<User> getEmployeeById(@PathVariable("firstName") String firstName){
			
			return new ResponseEntity<User>(userService.getUserByName(firstName), HttpStatus.OK);
		}
	 
	 @GetMapping("/username/{userName}")
		public ResponseEntity<User> getEmployeeByUserName(@PathVariable("userName") String userName){
			
			return new ResponseEntity<User>(userService.getUserByUserName(userName), HttpStatus.OK);
		}
	 
	 @GetMapping("/phonenumber/{phoneNumber}")
		public ResponseEntity<User> getEmployeeByPhoneNumberName(@PathVariable("phoneNumber") long phoneNumber){
			
			return new ResponseEntity<User>(userService.getUserByPhoneNumber(phoneNumber), HttpStatus.OK);
		}
	 
	 @GetMapping("/mail/{email}")
	 public ResponseEntity<User> getEmployeeByEmail(@PathVariable("email") String email) {
	     User user = userService.getUserByEmail(email);
	     if (user != null) {
	         return new ResponseEntity<>(user, HttpStatus.OK);
	     } else {
	         return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Or appropriate HTTP status
	     }
	 }
	 
	 @PostMapping("/signup")
	 public ResponseEntity<?> signup(@RequestBody UserDTO userDTO){
		return ResponseEntity.ok(userService.addUser(userDTO));
		 
	 }
	 
	 @PutMapping("/update/{firstName}")
		public ResponseEntity<?> UpdateEmployee(@PathVariable ("firstName") String firstName,@RequestBody User user){
			
		  userService.updateUser(user,firstName);
	  	 return ResponseEntity.ok(Map.of("message","Details of "+firstName+ " are Updated"));
			
		}
	 @DeleteMapping("/delete/{firstName}")
	    public ResponseEntity<?> deleteUserByName(@PathVariable String firstName) {
	        Optional<User> userOptional = userRepository.findByFirstName(firstName);
	        if (userOptional.isPresent()) {
	            userRepository.delete(userOptional.get());
	            return ResponseEntity.ok(Map.of("message","Details of "+firstName+ " are Deleted"));
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	 @GetMapping("/data/{firstName}")
	    public ResponseEntity<?> getDataByFirstName(@PathVariable String firstName) {
	        Optional<User> data =userRepository.findByFirstName(firstName);
	        return ResponseEntity.ok(data);
	    }
	 @PostMapping("/save")
	    public ResponseEntity<?> registerUser(@RequestBody User user) {
	        
	        if (userRepository.existsByUserName(user.getUsername())) {
	          
	        	   return ResponseEntity.ok(Map.of("message", "Username already exists"));
	        }
	        else if (userRepository.existsByPhoneNumber(user.getPhoneNumber())) {
		          
	        	   return ResponseEntity.ok(Map.of("message", "user with this phonenumber already registered"));
	        }
	        else if (userRepository.existsByEmail(user.getEmail())) {
		          
	        	   return ResponseEntity.ok(Map.of("message", "email already exists... Use another mailId"));
	        }
	       
	        userRepository.save(user);
	       
	        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
	    }

	 @GetMapping("/welcome")
	 public String welcome() {
		 return "welcome to my portal";
	 }
	 
	 @GetMapping("/download_userspdf")
		public void downloadpdf(HttpServletResponse response) throws DocumentException, IOException {
			response.setContentType("application/pdf");
			DateFormat format=new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
			String currentDateTime =format.format(new Date());
			response.setHeader("Content-Disposition", "attachment; filename=Report From UsersList_ "+ currentDateTime+" .pdf");
			List<User> listUsers=service.listAll();
			PdfExporter exporter=new PdfExporter(listUsers);
			exporter.export(response);
		}
	
}
	
