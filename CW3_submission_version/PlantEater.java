
/**
 * A simple model of a plant eater with its characteristics.
 *
 * @author Miriam Czech, K21099181 and Shun Sheng Lee, K21081997
 * @version 2022.02.28
 */
abstract public class PlantEater extends Animal 
{
    /**
     * Create a new plant eater at location in field.
     * 
     * @param field The field currently occupied.
     * @param location The location within the field.
     * @param randomAge Returns true if a random age is to be assigned to a plant eater
     * @param disease Whether a plant eater has a disease
     */
    public PlantEater(Field field, Location location, boolean randomAge, boolean disease)
    {
        super(field, location, randomAge, disease);
    }
}
