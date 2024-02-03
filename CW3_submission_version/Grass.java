import java.util.Random;
import java.util.List;

/**
 * A model of a patch of grass with its characteristics
 *
 * @author Miriam Czech, K21099181 and Shun Sheng Lee, K21081997
 * @version 2022.02.28
 */

public class Grass extends Plant
implements EatenByMice, EatenByFlies
{
    // Characteristics shared by all grasses (class variables).
    // The age at which a grass can start to breed.
    private static final int BREEDING_AGE = 3;
    // The age to which a grass can live.
    private static final int MAX_AGE = 200;
    // The likelihood of a grass breeding.
    private static final double BREEDING_PROBABILITY = 0.01;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 1;
    // The food value that a single grass provides when eaten. 
    private static final int CALORIES_PROVIDED = 240;
    // A shared random number generator to control breeding.
    private static final Random rand = Randomizer.getRandom();

    /**
     * Create a new grass at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param randomAge Returns true if a random age is to be assigned to a grass
     * @param disease Whether a grass has a disease
     */
    public Grass(Field field, Location location, boolean randomAge, boolean disease)
    {
        super(field, location, randomAge, disease);
    }

    /**
     * @return The maximum age a grass can live up to. This could result in the grass's death.
     */
    public int getMaxAge()
    {
        return MAX_AGE;
    }

    /**
     * @return the age in which a grass can start to breed
     */
    public int getBreedingAge()
    {
        return BREEDING_AGE;
    }

    /**
     * @return breeding probability of the grass.
     */
    public double getBreedingProbability()
    {
        return BREEDING_PROBABILITY;
    }

    /**
     * @return maximum number of young produced by a grass.
     */
    public int getMaxLitterSize()
    {
        return MAX_LITTER_SIZE;
    }

    /**
     * @return calories provided by the grass. 
     */
    public int getCaloriesProvided()
    {
        return CALORIES_PROVIDED;
    }

    /**
     * Create a new grass at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param randomAge Returns true if a random age is to be assigned to a grass.
     * @param disease Whether a grass has a disease
     */
    public Grass createNew(Field field, Location location, boolean randomAge, boolean disease)
    {
        Grass young = new Grass(field, location, false, disease);
        return young;
    }
}
