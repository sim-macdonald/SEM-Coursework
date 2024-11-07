package com.napier.sem.reports;

public class Population {

    private String name;
    private long totalPopulation;
    private long cityPopulation;
    private long nonCityPopulation;
    private double cityPercentage;
    private double nonCityPercentage;

    // Constructor
    public Population(String name, long totalPopulation, long cityPopulation, long nonCityPopulation, double cityPercentage, double nonCityPercentage) {
        this.name = name;
        this.totalPopulation = totalPopulation;
        this.cityPopulation = cityPopulation;
        this.nonCityPopulation = nonCityPopulation;
        this.cityPercentage = cityPercentage;
        this.nonCityPercentage = nonCityPercentage;
    }

    // Getter methods
    public String getName() { return name; }
    public long getTotalPopulation() { return totalPopulation; }
    public long getCityPopulation() { return cityPopulation; }
    public long getNonCityPopulation() { return nonCityPopulation; }
    public double getCityPercentage() { return cityPercentage; }
    public double getNonCityPercentage() { return nonCityPercentage; }
}
