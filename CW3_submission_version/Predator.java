import java.util.Random;
import java.util.List;

/**
 * A simple model of a predator with its characteristics.
 *
 * @author Miriam Czech, K21099181 and Shun Sheng Lee, K21081997
 * @version 2022.02.28
 */ 

abstract public class Predator extends Animal 
{
    /**
     * Create a new predator at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param randomAge Returns true if a random age is to be assigned to a predator
     * @param disease Whether a predator has a disease
     */
    public Predator(Field field, Location location, boolean randomAge, boolean disease)
    {
        super(field, location, randomAge, disease);
    }

    /**
     * Make this predator act - that is: make it do
     * whatever it wants/needs to do.
     * @param newPredators A list to receive newly born predators.
     * @param timeOfTheDay The time in the simulation at that instance 
     * @param weatherStatus The current weather in the simulation
     **/
    public void act(List<Actor> newPredators, String timeOfTheDay, String weatherStatus)
    {
        if (weatherStatus == "fog"){
            // predators can't hunt because of the fog 
            incrementHunger();
            stepsSinceLastEating++;
            super.act(newPredators, timeOfTheDay, weatherStatus);
            if(isAlive()){
                Location newLocation;
                newLocation = justMove();
                // See if it was possible to move.
                if(newLocation != null) {
                    setLocation(newLocation);
                }
                else {
                    // Overcrowding.
                    setDead();
                }
            }
        }
        else {
            super.act(newPredators, timeOfTheDay, weatherStatus);
        }
    }
}
