import java.util.Random;
import java.util.List;

/**
 * A simple model of a fruit with its characteristics
 *
 * @author Miriam Czech, K21099181 and Shun Sheng Lee, K21081997
 * @version 2022.02.28
 */
public class Fruit extends Plant
implements EatenByMice
{
    // Characteristics shared by all fruits (class variables).

    // The age at which a fruit can start to breed.
    private static final int BREEDING_AGE = 15;
    // The age to which a fruit can live.
    private static final int MAX_AGE = 200;
    // The likelihood of a fruit breeding.
    private static final double BREEDING_PROBABILITY = 0.01;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 1;
    // The food value that a single fruit provides when eaten. 
    private static final int CALORIES_PROVIDED = 240;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();

    /**
     * Create a new fruit at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param randomAge Returns true if a random age is to be assigned to a fruit
     * @param disease Whether a fruit has a disease
     */
    public Fruit(Field field, Location location, boolean randomAge, boolean disease)
    {
        super(field, location, randomAge, disease);
    }

    /**
     * @return The maximum age a fruit can live up to. This could result in the fruit's death.
     */
    public int getMaxAge()
    {
        return MAX_AGE;
    }

    /**
     * @return the age in which a fruit can start to breed
     */
    public int getBreedingAge()
    {
        return BREEDING_AGE;
    }

    /**
     * @return breeding probability of the fruit.  
     */
    public double getBreedingProbability()
    {
        return BREEDING_PROBABILITY;
    }

    /**
     * @return maximum number of young produced by a fruit.  
     */
    public int getMaxLitterSize()
    {
        return MAX_LITTER_SIZE;
    }

    /**
     * @return calories provided by the animal. 
     */
    public int getCaloriesProvided()
    {
        return CALORIES_PROVIDED;
    }

    /**
     * Check whether or not this fruit is to reproduce at this step.
     * New fruits will be made into free adjacent locations.
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param randomAge Returns true if a random age is to be assigned to a fruit
     * @param disease Whether a fruit has a disease
     */
    public Fruit createNew(Field field, Location location, boolean randomAge, boolean disease)
    {
        Fruit young = new Fruit(field, location, false, disease);
        return young;
    }
}

