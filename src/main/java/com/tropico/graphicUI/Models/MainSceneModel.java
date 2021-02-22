package com.tropico.graphicUI.Models;


public class MainSceneModel {
	
	public QuestionPanel questionPanel;
	public FactionPanel fractionPanel;
	public PieChartPanel pieChartPanel;
	public int round;
	public int season;
	public int money;
	public int population;
	public double satisfaction;
	
	
	public int getRound() {
		return round;
	}

	public void setRound(int round) {
		this.round = round;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(int season) {
		this.season = season;
	}

	public QuestionPanel getQuestionPanel() {
		return questionPanel;
	}

	public void setQuestionPanel(QuestionPanel questionPanel) {
		this.questionPanel = questionPanel;
	}

	public FactionPanel getFractionPanel() {
		return fractionPanel;
	}

	public void setFractionPanel(FactionPanel fractionPanel) {
		this.fractionPanel = fractionPanel;
	}

	public PieChartPanel getPieChartPanel() {
		return pieChartPanel;
	}

	public void setPieChartPanel(PieChartPanel pieChartPanel) {
		this.pieChartPanel = pieChartPanel;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public double getSatisfaction() {
		return satisfaction;
	}

	public void setSatisfaction(double satisfaction) {
		this.satisfaction = satisfaction;
	}
	
	
}
