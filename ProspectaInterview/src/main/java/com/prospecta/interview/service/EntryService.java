package com.prospecta.interview.service;

import java.util.List;

import com.prospecta.interview.entity.Entry;
import com.prospecta.interview.exception.EntryException;

public interface EntryService {

	public List<Entry> saveEntries(List<Entry> entry)throws EntryException;	
}
