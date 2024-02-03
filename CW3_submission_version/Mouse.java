import java.util.Random;
import java.util.List;
import java.util.Iterator;

/**
 * A simple model of a mouse and its characteristics
 *
 * @author Miriam Czech, K21099181 and Shun Sheng Lee, K21081997
 * @version 2022.02.28
 */
public class Mouse extends PlantEater
implements EatenByWolves, EatenByCats
{
    // Characteristics shared by all mice (class variables).

    // The age at which a mouse can start to breed.
    private static final int BREEDING_AGE = 8;
    // The age to which a mouse can live.
    private static final int MAX_AGE = 960;
    // The likelihood of a mouse breeding.
    private static final double BREEDING_PROBABILITY = 0.3;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 6;
    // number of steps a mouse can go before it has to eat again since birth
    private static final int AT_BIRTH_FOOD_LEVEL = 240;
    // The food value that a single mouse provides when eaten. 
    private static final int CALORIES_PROVIDED = 240;


    /**
     * Create a new mouse at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param randomAge Returns true if a random age is to be assigned to a mouse
     * @param disease Whether a mouse has a disease
     */
    public Mouse(Field field, Location location, boolean randomAge, boolean disease)
    {
        super(field, location, randomAge, disease);
    }

    /**
     * Make this mouse act - that is: make it do
     * whatever it wants/needs to do.
     * @param newMice A list to receive newly born mice.
     * @param timeOfTheDay The time in the simulation at that instance
     * @param weatherStatus The current weather in the simulation
     */
    public void act(List<Actor> newMice, String timeOfTheDay, String weatherStatus)
    {
        if (timeOfTheDay == "night"){
            //mice sleep - they do not move, eat or breed
            incrementHunger();
            incrementAge();
        }
        else {
            super.act(newMice, timeOfTheDay, weatherStatus);
        }
    }

    /**
     * @return The maximum age a mouse can live up to. This could result in the mouse's death.
     */
    public int getMaxAge()
    {
        return MAX_AGE;
    }

    /**
     * @return the age in which a mouse can start to breed
     */
    public int getBreedingAge()
    {
         return BREEDING_AGE;
    }

    /**
     * @return breeding probability of the mouse.
     */
    public double getBreedingProbability()
    {
        return BREEDING_PROBABILITY;
    }

    /**
     * @return maximum number of young produced by a mouse. 
     */
    public int getMaxLitterSize()
    {
        return MAX_LITTER_SIZE;
    }

    /**
     * @return Food level of mouse at birth 
     */
    public int getAtBirthFoodLevel()
    {
        return AT_BIRTH_FOOD_LEVEL;
    }

    /**
     * @return calories provided by the mouse. 
     */
    public int getCaloriesProvided()
    {
        return CALORIES_PROVIDED;
    }

    /**
     * Create a new mouse at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param randomAge Returns true if a random age is to be assigned to a mouse
     * @param disease Whether a mouse has a disease
     */
    public Mouse createNew(Field field, Location location, boolean randomAge, boolean disease)
    {
        Mouse young = new Mouse(field, location, false, disease);
        return young;
    }

    /**
     * Check if object is to be eaten by this mouse
     * @param the object near the mouse
     * @return true if the actor can be eaten by a mouse
     */
    public boolean isInstanceOfEatenByThisAnimal(Object object)
    {
        return object instanceof EatenByMice;
    }

    /**
     * Check if object is a mouse. 
     * @param the object near the mouse
     * @return true if the actor is of type mouse
     */
    public boolean isInstanceOf(Object object)
    {
        return object instanceof Mouse;
    }
}
