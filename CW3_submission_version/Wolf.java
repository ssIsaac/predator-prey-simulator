import java.util.List;
import java.util.Random;
import java.util.Iterator;

/**
 * A simple model of a wolf with its characteristics.
 *
 * @author Miriam Czech, K21099181 and Shun Sheng Lee, K21081997
 * @version 2022.02.28
 */
public class Wolf extends Predator
{
    // Characteristics shared by all wolves (class variables).
    // The age at which a wolf can start to breed.
    private static final int BREEDING_AGE = 15;
    // The age to which a wolf can live.
    private static final int MAX_AGE = 960;
    // The likelihood of a wolf breeding.
    private static final double BREEDING_PROBABILITY = 0.32;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 3;
    // number of steps a wolf can go before it has to eat again since birth
    private static final int AT_BIRTH_FOOD_LEVEL = 240;
    // food value provided when the wolf is eaten
    private static final int CALORIES_PROVIDED = 240;

    /**
     * Create a new wolf at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param randomAge Returns true if a random age is to be assigned to a wolf
     * @param disease Whether a wolf has a disease
     */
    public Wolf(Field field, Location location, boolean randomAge, boolean disease)
    {
        super(field, location, randomAge, disease);
    }

    /**
     * Make this wolf act - that is: make it do
     * whatever it wants/needs to do.
     * @param newWolves A list to receive newly born wolves.
     * @param timeOfTheDay The time in the simulation at that instance
     * @param weatherStatus The current weather in the simulation
     */
    public void act(List<Actor> newWolves, String timeOfTheDay, String weatherStatus)
    {
        if (timeOfTheDay == "morning"){
            // foxes sleep - they do not move, eat or breed
            incrementHunger();
            incrementAge();
        }
        else {
            super.act(newWolves, timeOfTheDay, weatherStatus);
        }
    }

    /**
     * @return The maximum age a wolf can live up to. This could result in the wolf's death.
     */
    public int getMaxAge()
    {
        return MAX_AGE;
    }

    /**
     * @return the age in which a wolf can start to breed
     */
    public int getBreedingAge()
    {
        return BREEDING_AGE;
    }

    /**
     * @return breeding probability of the wolf. 
     */
    public double getBreedingProbability()
    {
        return BREEDING_PROBABILITY;
    }

    /**
     * @return maximum number of young produced by a wolf.
     */
    public int getMaxLitterSize()
    {
        return MAX_LITTER_SIZE;
    }

    /**
     * @return Food level of wolf at birth
     */
    public int getAtBirthFoodLevel()
    {
        return AT_BIRTH_FOOD_LEVEL;
    }

    /**
     * @return calories provided by the wolf.  
     */
    public int getCaloriesProvided()
    {
        return CALORIES_PROVIDED;
    }

    /**
     * Create a new wolf at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param randomAge Returns true if a random age is to be assigned to a wolf
     * @param disease Whether a wolf has a disease
     */
    public Wolf createNew(Field field, Location location, boolean randomAge, boolean disease)
    {
        Wolf young = new Wolf(field, location, false, disease);
        return young;
    }

    /**
     * Check if object is to be eaten by this wolf
     * @param the object near the wolf 
     * @return true if the actor can be eaten by a wolf 
     */
    public boolean isInstanceOfEatenByThisAnimal(Object object)
    {
        return object instanceof EatenByWolves;
    }

    /**
     * Check if object is a wolf. 
     * @param the object near the wolf
     * @return true if the actor is of type wolf
     */
    public boolean isInstanceOf(Object object)
    {
        return object instanceof Wolf;
    }
}
