package com.prospecta.interview.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.prospecta.interview.dto.EntryData;
import com.prospecta.interview.dto.EntryDto;
import com.prospecta.interview.dto.EntryDtoData;
import com.prospecta.interview.entity.Entry;
import com.prospecta.interview.exception.EntryException;
import com.prospecta.interview.serviceimpl.EntryServiceImpl;


@RestController
public class EntryController {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private EntryServiceImpl entryServiceImpl;
	
	@GetMapping("/entries")
	public ResponseEntity<EntryDtoData> getRequiredEntriesHandler(@RequestParam String category) throws EntryException{
		
		EntryData data = restTemplate.getForObject("https://api.publicapis.org/entries", EntryData.class);
		
		List<Entry> entries = data.getEntries();
		
		List<EntryDto> dtos = new ArrayList<>();
		
		for(int i=0; i<entries.size(); i++) {
			String[] cat = entries.get(i).getCategory().split(" ");
			
			if(cat[0].equalsIgnoreCase(category)) {
				
				for(Entry entry : entries) {
					
					if(entry.getCategory().equalsIgnoreCase(entries.get(i).getCategory())) {
						
						dtos.add(new EntryDto(entry.getApi(), entry.getDescription()));
					}
				}
				
				break;
			}
		}
		
		EntryDtoData entryDtoData = new EntryDtoData();
		entryDtoData.setCount(dtos.size());
		entryDtoData.setFilteredList(dtos);
	
		return new ResponseEntity<EntryDtoData>(entryDtoData, HttpStatus.OK);
		
	}
	
	
	@PostMapping("/entries")
	public ResponseEntity<List<Entry>> saveEntriesHandler(@RequestBody List<Entry> entry)throws Exception{
		
		EntryData data = restTemplate.getForObject("https://api.publicapis.org/entries", EntryData.class);
		
		List<Entry> entries = data.getEntries();
		
		entries.addAll(entry);
		
		List<Entry> list = entryServiceImpl.saveEntries(entries);
		
		return new ResponseEntity<List<Entry>>(list, HttpStatus.CREATED);
		
	}
	
}
