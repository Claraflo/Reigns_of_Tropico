package com.tropico.game;


/**
 * Class Faction
 * <p>
 * constaint informations about factions
 **/

public class Faction {
    private int population;
    private int satisfaction;


    public Faction(int population, int satisfaction) {
        this.population = population;
        this.satisfaction = satisfaction;
    }

    public int getPopulation() {
        return population;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public void setPopulation(int population) {

        if (this.population == 0) {
            this.satisfaction = 0;
        } else {

            if (population < 0) {
                this.population = 0;
                this.satisfaction = 0;
            } else {
                this.population = population;
                if (this.population == 0) {
                    this.satisfaction = 0;
                }
            }
        }
    }

    public void setSatisfaction(int satisfaction) {

        if (this.satisfaction != 0) {

            if (satisfaction < 0) {
                this.satisfaction = 0;
            } else if (satisfaction > 100) {
                this.satisfaction = 100;
            } else {
                this.satisfaction = satisfaction;
            }
        }
    }
}
