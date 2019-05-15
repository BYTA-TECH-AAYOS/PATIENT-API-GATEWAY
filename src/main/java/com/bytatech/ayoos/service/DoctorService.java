package com.bytatech.ayoos.service;


import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bytatech.ayoos.client.doctor.domain.*;







public interface DoctorService {
	


	//search using QueryDsl(MATCHALL QUERY)
	public  Page<Doctor> findAllDoctors(Pageable pageable) ;
		
	//public  Page<Qualification> findAllQualification(Pageable pageable) ;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
