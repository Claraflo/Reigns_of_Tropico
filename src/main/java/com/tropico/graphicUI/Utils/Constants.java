package com.tropico.graphicUI.Utils;

import com.tropico.game.Factions;

public final class Constants {

    private Constants() {
    
    }

    // ID area
    public static final String QUESTION_PANEL_ID = "questionPanel";
    public static final String PIECHART_PANEL_ID = "PieChartPanel";
    
    public static final String INFO_LABEL_ID = "titleLabel";
    
    public static final String MONEY_VALUE_ID = "moneyValueInfo";
    public static final String POPULATION_VALUE_ID = "populationValueInfo";
    public static final String SATISFACTION_VALUE_ID = "satisfactionValueInfo";

    
    public static final String PROGRESSBAR_SUFIX_ID = "_progressBar";
    public static final String PROGRESSBAR_LABEL_SUFIX_ID = "_progressBarLabel";

    
    // Game Constants
    public static final Factions[] FACTIONS_NAMES = {Factions.CAPITALISTES, Factions.COMUNISTES, Factions.LIBERAUX, Factions.RELIGIEUX, Factions.MILITAIRES , Factions.ECOLOGISTES, Factions.NATIONNALISTES, Factions.LOYALISTES };
} 
