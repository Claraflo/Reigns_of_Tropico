package com.tropico.game;

import com.tropico.game.Constraint;
import com.tropico.game.Factions;
import com.tropico.game.Level;
import com.tropico.game.Faction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class Island
 *
 * Constaint informations about Island
 *
 * **/

public class Island {
    private int treasure;
    private int industry;
    private int agriculture;
    private double globalSatisfaction;
    private Constraint constraint;
    private HashMap<Factions, Faction> factions;

    public Island(Level level, ArrayList<Integer> startStatisticsByLevel) {

        this.treasure = startStatisticsByLevel.get(0);
        this.industry = startStatisticsByLevel.get(1);
        this.agriculture = startStatisticsByLevel.get(2);
        this.constraint = new Constraint(level, startStatisticsByLevel.get(3));
        this.factions = new HashMap<>();
        this.globalSatisfaction = 52.6;
        initFactionsMap();
    }

    private void initFactionsMap() {
        for (Factions name : Factions.values()) {

            if (name.equals(Factions.LOYALISTES)) {
                factions.put(name, new Faction(15, 100));
            } else {
                factions.put(name, new Faction(15, 50));
            }
        }
    }

    public int getTreasure() {
        return treasure;
    }

    public void setTreasure(int treasure) {
        if (treasure < 0) {
            this.treasure = 0;
        } else {
            this.treasure = treasure;
        }
    }

    public int getIndustry() {
        return industry;
    }

    public void setIndustry(int industry) {
        if (industry < 0) {
            this.industry = 0;
        } else {
            this.industry = industry;
        }
    }

    public int getAgriculture() {
        return agriculture;
    }

    public void setAgriculture(int agriculture) {
        if (agriculture < 0) {
            this.agriculture = 0;
        } else {
            this.agriculture = agriculture;
        }
    }

    public double getGlobalSatisfaction() {
        return globalSatisfaction;
    }

    public HashMap<Factions, Faction> getFactions() {
        return new HashMap<>(factions);
    }

    public void setPopulationFactions(Factions factionName, int variation) {

        if (variation > 0) {
            this.factions.get(factionName).setPopulation(this.factions.get(factionName).getPopulation() + (variation * constraint.getGainWeight()));
        } else {
            this.factions.get(factionName).setPopulation(this.factions.get(factionName).getPopulation() + (variation * constraint.getLossWeight()));
        }
    }


    public void setSatisfactionFactions(Factions factionName, int variation) {

        if (variation > 0) {
            this.factions.get(factionName).setSatisfaction(this.factions.get(factionName).getSatisfaction() + (variation * constraint.getGainWeight()));
        } else {
            this.factions.get(factionName).setSatisfaction(this.factions.get(factionName).getSatisfaction() + (variation * constraint.getLossWeight()));
        }
    }

    public int checkSumIndustryAgriculture(int newValueFeatureOne, int oldValueFeatureTwo) {

        if (newValueFeatureOne + oldValueFeatureTwo > 100) {
            return 100 - oldValueFeatureTwo;
        }

        return newValueFeatureOne;
    }

    public void setFeaturesIsland(ArrayList<Integer> variations) {

        if (variations.get(0) > 0) {
            setTreasure(treasure + variations.get(0) * constraint.getGainWeight());
        } else {
            setTreasure(treasure + variations.get(0) * constraint.getLossWeight());
        }

        if (variations.get(0) > 0) {
            setIndustry(checkSumIndustryAgriculture(industry + variations.get(1) * constraint.getGainWeight(), this.agriculture));
        } else {
            setIndustry(industry + variations.get(1) * constraint.getLossWeight());
        }

        if (variations.get(0) > 0) {
            setAgriculture(checkSumIndustryAgriculture(agriculture + variations.get(2) * constraint.getGainWeight(), this.industry));
        } else {
            setAgriculture(agriculture + variations.get(2) * constraint.getLossWeight());
        }
    }

    public void setFactions(Factions factionName, ArrayList<Integer> variations) {
        setSatisfactionFactions(factionName, variations.get(0));
        setPopulationFactions(factionName, variations.get(1));
    }


    public Constraint getConstraint() {
        return new Constraint(constraint.getLevel(), constraint.getThreshold());
    }

    public void updateGlobalSatisfaction() {

        int effectifTotal = 0;

        for (Map.Entry<Factions, Faction> entry : factions.entrySet()) {
            this.globalSatisfaction += entry.getValue().getPopulation() * entry.getValue().getSatisfaction();
            effectifTotal += entry.getValue().getPopulation();
        }

        if (effectifTotal != 0) {
            this.globalSatisfaction /= effectifTotal;
            this.globalSatisfaction = Math.round(this.globalSatisfaction * 100.0) / 100.0;
        } else {
            this.globalSatisfaction = 0;
        }
    }

    @Override
    public String toString() {

        StringBuilder infoFaction = new StringBuilder();

        for (Map.Entry<Factions, Faction> entry : factions.entrySet()) {
            infoFaction.append(entry.getKey());
            infoFaction.append(" : Pop ").append(entry.getValue().getPopulation()).append(", Sat ").append(entry.getValue().getSatisfaction());
            infoFaction.append("\n");
        }

        return "Island{" +
                "treasure=" + treasure +
                ", industry=" + industry +
                ", agriculture=" + agriculture +
                ", factions=\n" + infoFaction +
                '}';
    }

    public int getTotalPopulation() {

        int totalPop = 0;
        for (Map.Entry<Factions, Faction> entry : factions.entrySet()) {
            totalPop += entry.getValue().getPopulation();
        }
        return totalPop;
    }
}
