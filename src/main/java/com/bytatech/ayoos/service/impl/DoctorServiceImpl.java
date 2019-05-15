package com.bytatech.ayoos.service.impl;

import java.util.*;
import static org.elasticsearch.index.query.QueryBuilders.*;


import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bytatech.ayoos.client.doctor.domain.*;
import com.bytatech.ayoos.service.DoctorService;


/**
 * Service Implementation for managing Doctor.
 */
@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

    private final Logger log = LoggerFactory.getLogger(DoctorServiceImpl.class);

  

    @Autowired
	ElasticsearchOperations elasticsearchOperations;

	@Override
	public  Page<Doctor> findAllDoctors(Pageable pageable) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				  .withQuery(matchAllQuery())
				  .build();

		return  elasticsearchOperations.queryForPage(searchQuery,Doctor.class);

	
	} 
	
	
}
