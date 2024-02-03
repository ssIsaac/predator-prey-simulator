import java.util.Random;
import java.util.List;
import java.util.Iterator;

/**
 * A simple model of a bird with its characteristics.
 *
 * @author Miriam Czech, K21099181 and Shun Sheng Lee, K21081997
 * @version 2022.02.28
 */

public class Bird extends Predator
implements EatenByWolves, EatenByCats
{
    // Characteristics shared by all birds (class variables).

    // The age at which a bird can start to breed.
    private static final int BREEDING_AGE = 8;
    // The age to which a bird can live.
    private static final int MAX_AGE = 960;
    // The likelihood of a bird breeding.
    private static final double BREEDING_PROBABILITY = 0.32;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 6;
    // The food value avaliable at birth. In effect, this is the
    // number of steps a bird can go before it has to eat again.
    private static final int AT_BIRTH_FOOD_LEVEL = 240;
    // The food value that a single bird provides when eaten. 
    private static final int CALORIES_PROVIDED = 240;

    /**
     * Create a new bird at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param randomAge Returns true if a random age is to be assigned to a bird
     * @param timeOfTheDay The time in the simulation at that instance
     * @param disease Whether a bird has a disease
     */
    public Bird(Field field, Location location, boolean randomAge, boolean disease)
    {
        super(field, location, randomAge, disease);
    }

    /**
     * Make this bird act - that is: make it do
     * whatever it wants/needs to do.
     * @param newBirds A list to receive newly born birds.
     * @param weatherStatus The current weather in the simulation
     */
    public void act(List<Actor> newBirds, String timeOfTheDay, String weatherStatus)
    {
        if (timeOfTheDay == "night"){
            //birds sleep - they do not move, eat or breed
            incrementHunger();
            incrementAge();
        }
        else {
            super.act(newBirds, timeOfTheDay, weatherStatus);
        }
    }

    /**
     * @return The maximum age a bird can live up to. This could result in the bird's death.
     */
    public int getMaxAge()
    {
        return MAX_AGE;
    }

    /**
     * @return the age in which a bird can start to breed
     */
    public int getBreedingAge()
    {
        return BREEDING_AGE;
    }

    /**
     * @return breeding probability of the bird.   
     */
    public double getBreedingProbability()
    {
        return BREEDING_PROBABILITY;
    }

    /**
     * @return maximum number of young produced by a bird.
     */
    public int getMaxLitterSize()
    {
        return MAX_LITTER_SIZE;
    }

    /**
     * @return Food level of bird at birth
     */
    public int getAtBirthFoodLevel()
    {
        return AT_BIRTH_FOOD_LEVEL;
    }

    /**
     * @return calories provided by the bird. 
     */
    public int getCaloriesProvided()
    {
        return CALORIES_PROVIDED;
    }

    /**
     * Check whether or not this bird is to give birth at this step.
     * New births will be made into free adjacent locations.
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param randomAge Returns true if a random age is to be assigned to a bird
     * @param disease Whether a bird has a disease
     */
    public Bird createNew(Field field, Location location, boolean randomAge, boolean disease)
    {
        Bird young = new Bird(field, location, false, disease);
        return young;
    }

    /**
     * Check if object is to be eaten by this bird
     * @param the object near the bird 
     * @return true if the actor can be eaten by a bird  
     */
    public boolean isInstanceOfEatenByThisAnimal(Object object)
    {
        return object instanceof EatenByBirds;
    }

    /**
     * Check if object is a bird. 
     * @param the object near the bird
     * @return true if the actor is of type bird
     */
    public boolean isInstanceOf(Object object)
    {
        return object instanceof Bird;
    }
}

