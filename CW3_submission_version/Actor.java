import java.util.List;
import java.util.Random;

/**
 * A class representing shared characteristics of actors.
 * 
 * @author Miriam Czech, K21099181 and Shun Sheng Lee, K21081997
 * @version 2022.02.28
 */
public abstract class Actor
{    
    //instance variables 
    // Whether the actor is alive or not.
    protected boolean alive;
    // The actor's field.
    protected Field field;
    // The actor's position in the field.
    protected Location location;
    // A shared random number generator to control breeding.
    protected static final Random rand = Randomizer.getRandom();
    //Whether this actor has a disease
    protected boolean disease;
    //The simulator
    protected Simulator simulator;

    // Individual characteristics (instance fields).
    // The actor's age.
    protected int age;
    //probability of an actor being born with a disease
    protected static final double PROBABILITY_OF_DISEASE = 0.01;

    /**
     * Create a new actor at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param randomAge Returns true if a random age is to be assigned to an actor
     * @param disease Whether an actor has a disease
     */
    public Actor(Field field, Location location, boolean randomAge, boolean disease)
    {
        alive = true;
        this.field = field;
        setLocation(location);
        this.disease = disease;
        if(randomAge) {
            //generates an integer number that is within the max age limit
            //then assigns to the actor
            int maxAge = getMaxAge();
            age = rand.nextInt(maxAge);
        }
        else {
            age = 0;
        }
    }

    /**
     * Make this actor act - that is: make it do
     * whatever it wants/needs to do.
     * @param newActor A list to receive newly born actors.
     * @param timeOfTheDay The time in the simulation at that instance 
     * @param weatherStatus The current weather in the simulation
     */
    public void act(List<Actor> newActors, String timeOfTheDay, String weatherStatus)
    {
        incrementAge();
        if(isAlive()) {
            giveBirth(newActors);       
        }
    }

    /**
     * Check whether the actor is alive or not.
     * @return true if the actor is still alive.
     */
    protected boolean isAlive()
    {
        return alive;
    }

    /**
     * Indicate that the animal is no longer alive.
     * It is removed from the field.
     */
    protected void setDead()
    {
        alive = false;
        if(location != null) {
            field.clear(location);
            location = null;
            field = null;
        }
    }

    /**
     * Place the animal at the new location in the given field.
     * @param newLocation The animal's new location.
     */
    protected void setLocation(Location newLocation)
    {
        if(location != null) {
            field.clear(location);
        }
        location = newLocation;
        field.place(this, newLocation);
    }

    /**
     * Increase the age. This could result in the actor's death.
     * If actor has a disease, it ages faster
     */
    protected void incrementAge()
    {
        int max_age = getMaxAge();
        if (hasDisease()){
            age = age + 100;
        }
        else {
            age++;
        }

        //If the age of an actor exceeds its maximum age, it dies
        if(age > max_age) {
            setDead();
        }
    }

    /**
     * An actor can breed if it has reached the breeding age.
     */
    protected boolean canBreed()
    {
        int breedingAge = getBreedingAge();
        return age >= breedingAge;
    }

    /**
     * Generate a number representing the number of births,
     * if it can breed.
     * @return The number of births (may be zero).
     */
    protected int breed()
    {
        double breedingProbability = getBreedingProbability();
        int maxLitterSize = getMaxLitterSize();
        int births = 0;
        if(canBreed() && rand.nextDouble() <= breedingProbability) {
            births = rand.nextInt(maxLitterSize) + 1;
        }
        return births;
    }

    /**
     * Check whether or not this actor is to give birth at this step.
     * New births will be made into free adjacent locations.
     * There is a possibility that a diseased young may be produced
     * @param newActors A list to return newly born actors.
     */
    protected void giveBirth(List<Actor> newActors)
    {
        // New actors are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breed();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Actor young;
            if (rand.nextDouble() <= PROBABILITY_OF_DISEASE ){
                young = createNew (field, loc, false, true);
            }
            else {
                young = createNew (field, loc, false, false);
            }
            newActors.add(young);

        }
    }

    /**
     * @return the field currently occupied.
     */
    protected Field getField()
    {
        return field;
    }

    /**
     * @return The actor's location within the field.
     */
    protected Location getLocation()
    {
        return location;
    }

    /**
     * @return whether the actor has a disease
     */
    protected boolean hasDisease ()
    {
        return disease;
    }

    /**
     * Set the boolean disease to true
     * Actor has a disease
     */
    protected void setDisease()
    {
        disease = true;
    }

    /**
     * Get calories provided by the actor.
     */
    abstract public int getCaloriesProvided();

    /**
     * Get the maximum age an actor can live up to
     */
    abstract public int getMaxAge();

    /**
     * Get the age in which an actor can start to breed
     */
    abstract public int getBreedingAge();

    /**
     * Get the probability of an actor breeding 
     */
    abstract public double getBreedingProbability();

    /**
     * Get the maximum number of births of an actor
     */
    abstract public int getMaxLitterSize();

    /**
     * Creating an actor object 
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param randomAge If true, the fox will have random age and hunger level.
     * @param disease Whether an actor has a disease
     */
    abstract public Actor createNew(Field field, Location location, boolean randomAge, boolean disease);
}
