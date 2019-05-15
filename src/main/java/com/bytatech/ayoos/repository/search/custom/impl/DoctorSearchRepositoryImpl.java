package com.bytatech.ayoos.repository.search.custom.impl;


/*import java.util.*;
import static org.elasticsearch.index.query.QueryBuilders.*;
import static org.elasticsearch.action.search.SearchType.*;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AbstractAggregationBuilder;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static org.elasticsearch.search.aggregations.AggregationBuilders.terms;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import com.bytatech.ayoos.client.doctor.domain.*;

import com.bytatech.ayoos.repository.search.custom.DoctorCustomSearchRepository;
//import com.bytatech.jest.example.jestDemo.serviceImpl.AggregationsResultsExtractor;
import com.github.vanroy.springdata.jest.JestElasticsearchTemplate;
import com.github.vanroy.springdata.jest.aggregation.AggregatedPage;
import com.github.vanroy.springdata.jest.aggregation.impl.AggregatedPageImpl;


import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.SearchResult;
import io.searchbox.core.search.aggregation.TermsAggregation;
import org.elasticsearch.common.unit.Fuzziness;
import com.github.vanroy.springdata.jest.mapper.JestResultsExtractor;
import com.github.vanroy.springdata.jest.mapper.JestSearchResultMapper;
import com.google.gson.JsonObject;


//@Repository
public class DoctorSearchRepositoryImpl  implements DoctorCustomSearchRepository {
	
	
	@Autowired
	ElasticsearchOperations elasticsearchOperations;



	//simple search using QueryDsl(MATCHALL QUERY)
	public  Page<Doctor> findAllDoctor(Pageable pageable) {
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				  .withQuery(matchAllQuery())
				  .build();

		return  elasticsearchOperations.queryForPage(searchQuery,Doctor.class);

	
	} 
	
	
	
	//using Aggregation WORKED mETOD
	public  void findaggregate(Pageable pageable)
	{        
	SearchQuery searchQuery = new NativeSearchQueryBuilder()
			.withQuery(matchAllQuery())
	       .withIndices("employee").withTypes("employee")
			.addAggregation(terms("all_interests").field("interest"))
			.build();
	// when
	AggregatedPage<Employee> result = elasticsearchTemplate.queryForPage(searchQuery, Employee.class);
	TermsAggregation subjectAgg = result.getAggregation("all_interests", TermsAggregation.class);
	System.out.println(subjectAgg.getBuckets().get(0).getCount());
	//CLOSED
	
	
	
	
Aggregation aggregations = elasticsearchTemplate.query(searchQuery, new AggregationsResultsExtractor());
	StringTerms topTags = (StringTerms) aggregations.getAsMap().get("all_interests");
	 topTags.getBuckets().forEach(bucket -> {
		 System.out.println("$$$$$$$$$$$$$ "+bucket.getKeyAsString()+":"+ bucket.getDocCount());
     });
		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(matchAllQuery()) 
			4	.withIndices("employee")
				.addAggregation(terms("all_interests").field("interest")).build();
		Aggregations aggregations = elasticsearchTemplate.query(searchQuery, new ResultsExtractor<Aggregations>() {
	        @Override
	        public Aggregations extract(SearchResponse response) {
	            return response.getAggregations();
	        }
  
	    });
		System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$"+aggregations.asMap().get("all_interests"));
		//elasticsearchTemplate.queryForList(searchQuery, Employee.class);
	}
	//CLOSED}
	
	 private SearchQuery buildSearchQuery(AbstractAggregationBuilder abstractAggregationBuilder){
	        return new NativeSearchQueryBuilder()
	                .withQuery(matchAllQuery()).withSearchType(COUNT)
	                .withIndices("employee").withTypes("employee")
	                .addAggregation(abstractAggregationBuilder)
	                .build();
	    }
	//WORKED METHOD
	public List<Employee> testAggregate(Pageable pageable)
	{ 
		
		
		
		
	SearchQuery searchQuery = new NativeSearchQueryBuilder()
			.withQuery(matchAllQuery())
	       .withIndices("employee","interest")
			.addAggregation(terms("all_interests").field("interest"))
			.build();
	// when
    List<SearchResult.Hit<Employee, Void>> searchHits =elasticsearchTemplate.query(searchQuery , new JestResultsExtractor<List<SearchResult.Hit<Employee,Void >>>(){
	 @Override
     public List<SearchResult.Hit<Employee, Void>> extract(SearchResult searchResult) {
         return  searchResult.getHits(Employee.class);
     }
 });
 List<Employee> aaas = new ArrayList<>();
     for (SearchResult.Hit<Employee, Void> searchHit : searchHits) {
    	 //Employee aaa = searchHit.source;
	   //  aaas.add(aaa);
     }
	return aaas;
	
	}//CLOSED
	
	
	//WORKED METHOD
	public void testAggregate2(Pageable pageable)
	{ 
		List<SampleEntity> values = new ArrayList<SampleEntity>();;
		
		
		
	SearchQuery searchQuery = new NativeSearchQueryBuilder()
			.withQuery(matchAllQuery()).withIndices("doctor","pharmacy")
			  .build();
	// when
    elasticsearchTemplate.query(searchQuery , new JestResultsExtractor<List<SampleEntity>>(){
	 @Override
     public List<SampleEntity> extract(SearchResult response) {
		
			for (SearchResult.Hit<JsonObject, Void> searchHit : response.getHits(JsonObject.class)) {
				SampleEntity sampleEntity = new SampleEntity();
				//sampleEntity.setId(searchHit.source.get(JestResult.ES_METADATA_ID).getAsString());
				sampleEntity.setName(searchHit.source.get("name").getAsString());
				System.out.println("sampleEntity.getName"+sampleEntity.getName());
				values.add(sampleEntity);
				
				System.out.println("size%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%"+values.size());
		 
		 
		
     }
			return values;
	 }
 });

	
	}//CLOSED
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
*/