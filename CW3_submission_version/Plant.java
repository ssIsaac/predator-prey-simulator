import java.util.List;

/**
 * A simple model of a plant.
 * Plants age and die.
 * 
 * @author Miriam Czech, K21099181 and Shun Sheng Lee, K21081997
 * @version 2022.02.28
 */
abstract public class Plant extends Actor
{
    /**
     * Create a plant. A plant can be created as a new born (age zero
     * and not hungry) or with a random age and food level.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param randomAge If true, the fox will have random age and hunger level.
     * @param disease Whether an actor has a disease
     */
    public Plant (Field field, Location location, boolean randomAge, boolean disease)
    {
        super(field, location, randomAge, disease);
    }

    /**
     * Make this plant act - that is: make it do
     * whatever it wants/needs to do.
     * @param newPlants A list to receive newly born actors.
     * @param timeOfTheDay The time in the simulation at that instance 
     * * @param weatherStatus The current weather in the simulation
     */
    public void act(List<Actor> newActors, String timeOfTheDay, String weatherStatus)
    {
        if (weatherStatus == "rainfall" || weatherStatus == "thunderstorm"){
            incrementAge();
            if(isAlive()) {
                giveBirthMoreIntensively(newActors);            
            }
        }
        else {
            super.act(newActors, timeOfTheDay, weatherStatus);
        }
    }

    /**
     * Generate a number representing the number of young,
     * if it can breed.
     * @return The maximum number of births 
     */
    protected int breedMoreIntensively()
    {
        double breedingProbability = getBreedingProbability();
        int maxLitterSize = getMaxLitterSize();
        int births = 0;
        if(canBreed() && rand.nextDouble() <= breedingProbability) {
            births = maxLitterSize;
        }
        return births;
    }

    /**
     * Check whether or not this plant is to reproduce at this step.
     * New youngs will be made into free adjacent locations.
     * @param newPlants A list to return newly germinated plants
     */
    protected void giveBirthMoreIntensively(List<Actor> newActors)
    {
        // New plants are born into adjacent locations.
        // Get a list of adjacent free locations.
        Field field = getField();
        List<Location> free = field.getFreeAdjacentLocations(getLocation());
        int births = breedMoreIntensively();
        for(int b = 0; b < births && free.size() > 0; b++) {
            Location loc = free.remove(0);
            Actor young = createNew(field, loc, false, disease);
            newActors.add(young);
        }
    }
}