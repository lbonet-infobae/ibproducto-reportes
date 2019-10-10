package com.infobae.ibproducto.reports.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.infobae.ibproducto.reports.websked.dto.Counts;
import com.infobae.ibproducto.reports.websked.dto.MedianDeadlineMiss;
import com.infobae.ibproducto.reports.websked.dto.SearchResult;
import com.infobae.ibproducto.reports.websked.dto.StoryCount;
import com.infobae.ibproducto.reports.websked.dto.User;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class WebskedService {
	
	Logger logger = LoggerFactory.getLogger(WebskedService.class);
	
	@Value("${websked.config.url}")
	String baseUrl;
	
	@Value("${websked.config.token}")
	String token;
	
	public List<User> getUsers() {
			
		String url = baseUrl + "/users";
		
		try {
			HttpResponse<JsonNode> request = Unirest.get(url)
					.header("Authorization", "Bearer "+token)
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.asJson();
			
			if (request.getStatus() != 200) {
				throw new RuntimeException("Invalid validation response GET " + url + 
						" Status " + request.getStatus());
			}
			
			Gson gson = new GsonBuilder()
					.setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();

			JSONArray resultObject = request.getBody().getArray();
			
			Type type = new TypeToken<Collection<User>>(){}.getType();
			Collection<User> result = gson.fromJson(resultObject.toString(), type);
			
			return new ArrayList<>(result);
			
		} catch (UnirestException e) {
			logger.error("Error getting websked users", e);
			throw new RuntimeException("Error getting websked users", e);
		}
	}

//	public StoryCount getStoryCount(String author, Date from, Date to){
//	
//		String url = baseUrl + "/stats/storyCount";
//		
//		try {
//			
//			Map<String, Object> params = new HashMap<>();
//			params.put("startTime", from.getTime() / 1000);
//			params.put("endTime", to.getTime() / 1000);
//			params.put("offset", "-0h");
//			params.put("interval", "year");
//			
//			if(author != null) {
//				params.put("author", author);
//			}
//			
//			HttpResponse<JsonNode> request = Unirest.get(url)
//					.queryString(params)
//					.header("Authorization", "Bearer "+token)
//					.header("Accept", "application/json")
//					.header("Content-Type", "application/json")
//					.asJson();
//			
//			if (request.getStatus() != 200) {
//				throw new RuntimeException("Invalid validation response GET " + url + 
//						" Status " + request.getStatus());
//			}
//			
//			Gson gson = new GsonBuilder()
//					.setDateFormat("yyyy-MM-dd HH:mm:ss")
//					.create();
//
//			JSONArray resultObject = request.getBody().getArray();
//			
//			Type type = new TypeToken<Collection<StoryCount>>(){}.getType();
//			Collection<StoryCount> result = gson.fromJson(resultObject.toString(), type);
//			
//			if(result.isEmpty()) {
//				return new StoryCount();
//			}
//			
//			return (new ArrayList<>(result)).get(0);
//			
//		} catch (UnirestException e) {
//			logger.error("Error getting websked story count", e);
//			throw new RuntimeException("Error getting websked story count", e);
//		}
//	}
	
	public StoryCount getStoryCount(String author, Date from, Date to){
		
		String url = baseUrl + "/search";
		
		try {
			
			Map<String, Object> params = new HashMap<>();
			params.put("startDate", from.getTime() / 1000);
			params.put("endDate", to.getTime() / 1000);
			params.put("sort", "time");
			params.put("contentType", "article");
			
			if(author != null) {
				params.put("author", author);
			}
			
			HttpResponse<JsonNode> request = Unirest.get(url)
					.queryString(params)
					.header("Authorization", "Bearer "+token)
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.asJson();
			
			if (request.getStatus() != 200) {
				throw new RuntimeException("Invalid validation response GET " + url + 
						" Status " + request.getStatus());
			}
			
			Gson gson = new GsonBuilder()
					.setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();

			JSONObject resultObject = request.getBody().getObject();
			
			SearchResult result = gson.fromJson(resultObject.toString(), SearchResult.class);
			
			StoryCount sc = new StoryCount();
			Counts counts = new Counts();
			counts.setTotal(result.getTotalResults());
			sc.setCounts(counts);
			
			return sc;
			
		} catch (UnirestException e) {
			logger.error("Error getting websked story count", e);
			throw new RuntimeException("Error getting websked story count", e);
		}
	}
	
	public MedianDeadlineMiss getMedianDeadlineMiss(String author, Date from, Date to) {

		String url = baseUrl + "/stats/medianDeadlineMiss";
		
		try {
			HttpResponse<JsonNode> request = Unirest.get(url)
					.queryString("author", author)
					.queryString("startTime", from.getTime() / 1000)
					.queryString("endTime", to.getTime() / 1000)
					.header("Authorization", "Bearer "+token)
					.header("Accept", "application/json")
					.header("Content-Type", "application/json")
					.asJson();
			
			if (request.getStatus() != 200) {
				throw new RuntimeException("Invalid validation response GET " + url + 
						" Status " + request.getStatus());
			}
			
			Gson gson = new GsonBuilder()
					.setDateFormat("yyyy-MM-dd HH:mm:ss")
					.create();

			JSONObject resultObject = request.getBody().getObject();
			
			MedianDeadlineMiss result = gson.fromJson(resultObject.toString(), MedianDeadlineMiss.class);
			
			return result;
			
		} catch (UnirestException e) {
			logger.error("Error getting websked median deadline miss", e);
			throw new RuntimeException("Error getting websked median deadline miss", e);
		}
		
	}
}
