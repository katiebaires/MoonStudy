/************************************************************************
 * Project 7 - MoonStudy
 *************************************************************************
 * This project will improve martians explorations of the moons
 * Katie Martinez
 * 12/05/2022
 * CMSC 255-001
 *************************************************************************/

/**
 * Input instance variables
 */
public class Moon {
    private String name;
    private double radius;
    private double density;
    private double distance;

    /**
     * Create default constructor and set all instance variables to their default values
     */

    public Moon(){
        this.name = "";
        this.radius = 0;
        this.density = 0;
        this.distance = 0;
    }

    /**
     * Create parameterized constructor that passes the instance variables and sets it to the instance variables
     * @param name
     * @param radius
     * @param density
     * @param distance
     */

    public Moon(String name, double radius, double density, double distance){
        this.name = name;
        this.radius = radius;
        this.density = density;
        this.distance = distance;
    }

    /**
     * Input getters and setters for each instance variable
     * @return
     */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * Input a toString method returning the instance variables and their values
     * @return
     */
    @Override
    public String toString() {
        return name + " " + String.format("%.2f", radius) + " " + String.format("%.2f", density) + " "
                + String.format("%.2f", distance);
    }
}