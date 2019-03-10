package com.kefelan.messaging.kefelanmicroservicesnote.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.kefelan.messaging.kefelanmicroservicesnote.model.Note;
import com.kefelan.messaging.kefelanmicroservicesnote.model.User;
import com.kefelan.messaging.kefelanmicroservicesnote.repository.NoteRepository;

@RestController
@RequestMapping("/note")
public class NoteController {
	
	private static String SERVICE_USER = "user";
	
	@Autowired
	RestTemplate restTemplate;
	
	private NoteRepository noteRepository;
	
	public NoteController(NoteRepository noteRepository) {
		this.noteRepository = noteRepository;
	}

	@GetMapping()
	public List<Note> findAllNotes(){
		return  noteRepository.findAll();
	}
	
	@GetMapping(value = "/{noteId}")
	public Note findNoteById(@PathVariable("noteId") Integer noteId){
		return noteRepository.findById(noteId).orElse(null);
	}
	
	@PostMapping()
	public Note createNote(@RequestBody Note note){
		return noteRepository.save(note);
	}
	
	@PutMapping(value = "/{noteId}")
	public Note updateNote(@RequestBody Note note,@PathVariable("noteId") Integer noteId){
		if(noteRepository.findById(noteId).orElse(null)!=null) {
			note.setId(noteId);
			return noteRepository.save(note);
		}
		return null;
	}
	
	@DeleteMapping(value = "/{noteId}")
	public Note deleteNote(@PathVariable("noteId") Integer noteId){
		noteRepository.deleteById(noteId);
		return noteRepository.findById(noteId).orElse(null);
	}
	
	
	/**
	 * **Invoke user service**
	 * @param userName
	 * @return
	 */
	@GetMapping("/user/username/{username}")
	public User[] findAllUsers(@PathVariable("username") final String userName){
		User[] userList = restTemplate.getForObject("http://"+SERVICE_USER+"/user/username/{userName}", User[].class, userName);
		return userList;
	}
	
	/**
	 * **Invoke user service**
	 * @param userName
	 * @return
	 */
	@GetMapping(value = "/username/{userName}")
	public List<Note> findAllNotes(@PathVariable("userName") String userName){
//		ResponseEntity<List<Integer>> quoteResponse = restTemplate.exchange("http://localhost:8301/user/username/" + userName,
//				HttpMethod.GET, null, new ParameterizedTypeReference<List<Integer>>() {
//				});
//		List<Integer> idList = quoteResponse.getBody();
		
		User[] userList = restTemplate.getForObject("http://"+SERVICE_USER+"/user/username/{nameName}", User[].class, userName);
		
		List<Note> resList = new ArrayList<Note>();
		for(User user : userList) {
			List<Note> noteList = noteRepository.findByUserId(user.getId());
			resList.addAll(noteList);
		}
		
		return resList;
	}
}
