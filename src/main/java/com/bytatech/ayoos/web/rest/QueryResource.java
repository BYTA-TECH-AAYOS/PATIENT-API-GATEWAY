package com.bytatech.ayoos.web.rest;

import static org.elasticsearch.index.query.QueryBuilders.*;

import java.util.*;

import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bytatech.ayoos.client.doctor.domain.*;

import com.bytatech.ayoos.service.DoctorService;
//import com.bytatech.jest.example.jestDemo.search.custom.ArtistSearchRepositoryCustomImpl;
import com.github.vanroy.springdata.jest.aggregation.AggregatedPage;

@RestController
public class QueryResource {
	@Autowired
	DoctorService doctorService;
	
	
	@GetMapping("/findAllDoctors")
	public Page<Doctor> findAllDoctors(Pageable pageable) {
		return doctorService.findAllDoctors(pageable);
	}
    @GetMapping("/findAllQualification")
	public String agg() {
		
	
		return "Success";
	}

	
	

}
