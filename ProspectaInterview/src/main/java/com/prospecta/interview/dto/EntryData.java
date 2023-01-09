package com.prospecta.interview.dto;

import java.util.List;

import com.prospecta.interview.entity.Entry;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EntryData {

	private int count;
	private List<Entry> entries;
	
}
