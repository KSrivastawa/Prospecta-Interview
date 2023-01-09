package com.prospecta.interview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.prospecta.interview.entity.Entry;

public interface EntryRepository extends JpaRepository<Entry, String>{

	
}
