import java.util.List;
import java.util.Random;
import java.util.Iterator;

/**
 * A simple model of an animal.
 * Animals age, move, breed, and die.
 * 
 * @author Miriam Czech, K21099181 and Shun Sheng Lee, K21081997
 * @version 2022.02.28
 */

abstract public class Animal extends Actor
{
    //instance variables
    protected int foodLevel;
    protected boolean female;
    protected int stepsSinceLastEating;

    /**
     * Create a new animal at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param randomAge Returns true if a random age is to be assigned to an animal
     * @param disease Whether an animal has a disease
     */
    public Animal(Field field, Location location, boolean randomAge, boolean disease)
    {
        super(field, location, randomAge, disease);
        int atBirthFoodLevel = getAtBirthFoodLevel();
        if(randomAge) {
            foodLevel = rand.nextInt(atBirthFoodLevel) + 1;
        }
        else {
            foodLevel = atBirthFoodLevel;
        }
        female = (rand.nextInt(2) == 1);
        stepsSinceLastEating = 0;
    }

    /**
     * Make this animal act - that is: make it do
     * whatever it wants/needs to do.
     * @param newAnimal A list to receive newly born animals.
     * @param timeOfTheDay The time in the simulation at that instance 
     * @param weatherStatus The current weather in the simulation
     */
    public void act(List<Actor> newActors, String timeOfTheDay, String weatherStatus)
    {
        //everytime an animal acts (a step simulated),
        //its food level reduces by one
        incrementHunger();
        //steps since last eating increases by one
        stepsSinceLastEating++;
        //act method inherited from actor class
        super.act(newActors, timeOfTheDay, weatherStatus);

        if(isAlive()){
            Location newLocation;
            //96 is the amount of steps in 24 hours
            //If an animal has not eaten in 24 hours,
            //it will attempt to hunt for food
            if (stepsSinceLastEating > 96){
                newLocation = findFood();
                if(newLocation == null) { 
                    // No food found - try to move to a free location.
                    newLocation = getField().freeAdjacentLocation(getLocation());
                }
            }
            else {
                newLocation = justMove();
            }
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

    /**
     * Attempt to hunt for food. 
     */
    protected Location findFood()
    {
        //get the field grid of where the animal is at
        Field field = getField();
        //get the adjacent grids to the animal's location in the field
        //storing it in a list
        List<Location> adjacent = field.adjacentLocations(getLocation());
        Iterator<Location> it = adjacent.iterator();
        //Iterates over the list of locations
        while(it.hasNext()) {
            Location where = it.next();
            //get the object at a specific grid
            Object object = field.getObjectAt(where);
            if(isInstanceOfEatenByThisAnimal(object)) {
                Actor eatenByThisAnimal = (Actor) object;
                if(eatenByThisAnimal.isAlive() && hasDisease()) { 
                    eatenByThisAnimal.setDead();
                    setDisease();
                    foodLevel = foodLevel + eatenByThisAnimal.getCaloriesProvided();
                    return where;
                }
            }
        }
        return null;
    }

    /**
     * @return Free adjacent locations
     */
    protected Location justMove()
    {
        Field field = getField();
        Location newLocation;
        newLocation = field.freeAdjacentLocation(location);
        return newLocation;
    }

    /**
     * Make this wolf more hungry. This could result in the wolf's death.
     */
    public void incrementHunger()
    {
        foodLevel--;
        if(foodLevel <= 0) {
            setDead();
        }
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
        if(female && canBreed() && rand.nextDouble() <= breedingProbability) {
            Field field = getField();
            List<Location> adjacent = field.adjacentLocations(getLocation());
            Iterator<Location> it = adjacent.iterator();
            while(it.hasNext()) {
                Location where = it.next();
                Object object = field.getObjectAt(where);
                if(object instanceof Animal && isInstanceOf(object)) {
                    Animal potentialBreeder = (Animal) object;
                    if(potentialBreeder.isAlive() && potentialBreeder.female == false) { 
                        births = rand.nextInt(maxLitterSize) + 1;
                        break;
                    }
                }
            }
        }
        return births;
    }

    /**
     * @return The food level at birth 
     */
    abstract public int getAtBirthFoodLevel();

    /**
     * @return true if the animal can be eaten by this animal 
     */
    abstract public boolean isInstanceOfEatenByThisAnimal(Object object);

    /**
     * @return true if the animal is of the same type as this animal 
     */
    abstract public boolean isInstanceOf(Object object);
}
