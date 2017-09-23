package com.event.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Service;

import com.event.model.Score;

@Service
public class ScoreServices {
	@Autowired
    MongoOperations mongoTemplate;
	public void saveScore(Score score){
		
		mongoTemplate.insert(score);
	}
	public List<Score> getScoreValue(String eventID,String JudgeEmail){
		BasicQuery query=new BasicQuery("{eventID:'"+eventID+"', judgeEmail : '"+JudgeEmail+"'}");
		 List<Score> scores=   mongoTemplate.find(query, Score.class);
		return scores;
	}
	public void removeScore(String eventID,String judgeEmail){
		 BasicQuery query=new BasicQuery("{eventID:'"+eventID+"', judgeEmail: '"+judgeEmail+"' }");
		 mongoTemplate.remove(query,Score.class);
	 }
}
