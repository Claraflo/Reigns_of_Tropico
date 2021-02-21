package fr.esgi.fonctionnel.game;

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
        this.population = population;
    }

    public void setSatisfaction(int satisfaction) {
        this.satisfaction = satisfaction;
    }
}
