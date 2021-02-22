package com.tropico.graphicUI.Models;

import java.util.ArrayList;

public class QuestionPanel {
	public ArrayList<String> responses;
	public String question;
	
	
	public QuestionPanel() {
		this.responses = new ArrayList<String>();
	}
	
	public ArrayList<String> getResponses() {
		return responses;
	}
	public void setResponses(ArrayList<String> responses) {
		this.responses = responses;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	
	
	
}
