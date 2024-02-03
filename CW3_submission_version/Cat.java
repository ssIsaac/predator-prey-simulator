import java.util.Random;
import java.util.List;
import java.util.Iterator;

/**
 * A simple model of a cat with its characteristics
 *
 * @author Miriam Czech, K21099181 and Shun Sheng Lee, K21081997
 * @version 2022.02.28
 */
public class Cat extends Predator
implements EatenByWolves
{
    // Characteristics shared by all cats (class variables).

    // The age at which a cat can start to breed.
    private static final int BREEDING_AGE = 8;
    // The age to which a cat can live.
    private static final int MAX_AGE = 960;
    // The likelihood of a cat breeding.
    private static final double BREEDING_PROBABILITY = 0.20;
    // The maximum number of births.
    private static final int MAX_LITTER_SIZE = 4;
    // number of steps a cat can go before it has to eat again since birth
    private static final int AT_BIRTH_FOOD_LEVEL = 240;
    // The food value that a single cat provides when eaten. 
    private static final int CALORIES_PROVIDED = 240;

    /**
     * Create a new cat at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param randomAge Returns true if a random age is to be assigned to a cat
     * @param disease Whether a cat has a disease
     */
    public Cat(Field field, Location location, boolean randomAge, boolean disease)
    {
        super(field, location, randomAge, disease);
    }

    /**
     * Make this cat act - that is: make it do
     * whatever it wants/needs to do.
     * @param newCats A list to receive newly born cats.
     * @param timeOfTheDay The time in the simulation at that instance
     * @param weatherStatus The current weather in the simulation
     */
    public void act(List<Actor> newCats, String timeOfTheDay, String weatherStatus)
    {
        if (timeOfTheDay == "morning"){
            //cats sleep - they do not move, eat or breed
            incrementHunger();
            incrementAge();
        }
        else {
            super.act(newCats, timeOfTheDay, weatherStatus);
        }
    }

    /**
     * @return The maximum age a cat can live up to. This could result in the cat's death.
     */
    public int getMaxAge()
    {
        return MAX_AGE;
    }

    /**
     * @return the age in which a cat can start to breed
     */
    public int getBreedingAge()
    {
        return BREEDING_AGE;
    }

    /**
     * @return breeding probability of the cat.
     */
    public double getBreedingProbability()
    {
        return BREEDING_PROBABILITY;
    }

    /**
     * @return maximum number of young produced by a cat.
     */
    public int getMaxLitterSize()
    {
        return MAX_LITTER_SIZE;
    }

    /**
     * @return Food level of cat at birth  
     */
    public int getAtBirthFoodLevel()
    {
        return AT_BIRTH_FOOD_LEVEL;
    }

    /**
     * @return calories provided by the cat. 
     */
    public int getCaloriesProvided()
    {
        return CALORIES_PROVIDED;
    }

    /**
     * Create a new cat at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param randomAge Returns true if a random age is to be assigned to a cat
     * @param disease Whether a cat has a disease
     */
    public Cat createNew(Field field, Location location, boolean randomAge, boolean disease)
    {
        Cat young = new Cat(field, location, false, disease);
        return young;
    }

    /**
     * Check if object is to be eaten by this cat.
     * @param the object near the cat.
     * @return true if the actor can be eaten by a cat.
     */
    public boolean isInstanceOfEatenByThisAnimal(Object object)
    {
        return object instanceof EatenByCats;
    }

    /**
     * Check if object is a cat. 
     * @param the object near the cat.
     * @return true if the actor is of type cat.
     */
    public boolean isInstanceOf(Object object)
    {
        return object instanceof Cat;
    }
}

