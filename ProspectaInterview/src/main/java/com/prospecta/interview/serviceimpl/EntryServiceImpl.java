package com.prospecta.interview.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospecta.interview.entity.Entry;
import com.prospecta.interview.exception.EntryException;
import com.prospecta.interview.repository.EntryRepository;
import com.prospecta.interview.service.EntryService;


@Service
public class EntryServiceImpl implements EntryService {

	@Autowired
	private EntryRepository entryRepository;
	
	@Override
	public List<Entry> saveEntries(List<Entry> entry) throws EntryException {
		
		List<Entry> entries = entryRepository.saveAll(entry);
		
		if(entries.isEmpty()) throw new EntryException("List is empty...");
		
		return entries;
	}

	
	
}
