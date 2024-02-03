import java.util.List;
import java.util.Random;
import java.util.Iterator;

/**
 * A simple model of a fly with its characteristics
 *
 * @author Miriam Czech, K21099181 and Shun Sheng Lee, K21081997
 * @version 2022.02.28
 */
public class Fly extends PlantEater
implements EatenByBirds
{
    // Characteristics shared by all flies (class variables).

    // The age at which a fly can start to breed.
    private static final int BREEDING_AGE = 3;
    // The age to which a fly can live.
    private static final int MAX_AGE = 960;
    // The likelihood of a fly breeding.
    private static final double BREEDING_PROBABILITY = 0.17;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 3;
    // number of steps a fly can go before it has to eat again since birth
    private static final int AT_BIRTH_FOOD_LEVEL = 240;
    // The food value that a single fly provides when eaten. 
    private static final int CALORIES_PROVIDED = 240;

    /**
     * Create a new fly at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param randomAge Returns true if a random age is to be assigned to a fly
     * @param disease Whether a fly has a disease
     */
    public Fly(Field field, Location location, boolean randomAge, boolean disease)
    {
        super(field, location, randomAge, disease);
    }

    /**
     * Make this fly act - that is: make it do
     * whatever it wants/needs to do.
     * @param newFlies A list to receive newly born flies.
     * @param timeOfTheDay The time in the simulation at that instance
     * @param weatherStatus The current weather in the simulation
     */
    public void act(List<Actor> newFlies, String timeOfTheDay, String weatherStatus)
    {
        if (timeOfTheDay == "night"){
            //flies sleep - they do not move, eat or breed
            incrementHunger();
            incrementAge();
        }
        else {
            super.act(newFlies, timeOfTheDay, weatherStatus);
        }
    }

    /**
     * @return The maximum age a fly can live up to. This could result in the fly's death.
     */
    public int getMaxAge()
    {
        return MAX_AGE;
    }

    /**
     * @return the age in which a fly can start to breed
     */
    public int getBreedingAge()
    {
        return BREEDING_AGE;
    }

    /**
     * @return breeding probability of the fly.
     */
    public double getBreedingProbability()
    {
        return BREEDING_PROBABILITY;
    }

    /**
     * @return maximum number of young produced by a fly.
     */
    public int getMaxLitterSize()
    {
        return MAX_LITTER_SIZE;
    }

    /**
     * @return Food level of fly at birth
     */
    public int getAtBirthFoodLevel()
    {
        return AT_BIRTH_FOOD_LEVEL;
    }

    /**
     * @return calories provided by the fly. 
     */
    public int getCaloriesProvided()
    {
        return CALORIES_PROVIDED;
    }

    /**
     * Create a new fly at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param randomAge Returns true if a random age is to be assigned to a fly
     * @param disease Whether a fly has a disease
     */
    public Fly createNew(Field field, Location location, boolean randomAge, boolean disease)
    {
        Fly young = new Fly(field, location, false, disease);
        return young;
    }

    /**
     * Check if object is to be eaten by this fly
     * @param the object near the fly
     * @return true if the actor can be eaten by a fly
     */
    public boolean isInstanceOfEatenByThisAnimal(Object object)
    {
        return object instanceof EatenByFlies;
    }

    /**
     * Check if object is a fly. 
     * @param the object near the fly
     * @return true if the actor is of type fly
     */
    public boolean isInstanceOf(Object object)
    {
        return object instanceof Fly;
    }
}
