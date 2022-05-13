package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.UnitOfMeasure;
import junit.framework.TestCase;

import java.math.BigDecimal;

public class IngredientToIngredientCommandTest extends TestCase {

    private static final String DESCRIPTION = "description";
    private static final Long INGREDIENT_ID = 1L;
    private static final BigDecimal AMOUNT = new BigDecimal(2);
    private static final Long UOM_ID = 1L;

    IngredientToIngredientCommand ingredientConverter;

    public void setUp() throws Exception {
        ingredientConverter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    public void testNullParameter() {
        assertNull(ingredientConverter.convert(null));
    }

    public void testEmptyParameter() {
        assertNotNull(ingredientConverter.convert(new Ingredient()));
    }

    public void testConvert() {

        //given
        Ingredient ingredientSource = new Ingredient();
        ingredientSource.setId(INGREDIENT_ID);
        ingredientSource.setAmount(AMOUNT);
        ingredientSource.setUom(new UnitOfMeasure().setId(UOM_ID));
        ingredientSource.setDescription(DESCRIPTION);

        //when
        IngredientCommand ingredient = ingredientConverter.convert(ingredientSource);

        //then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUnitOfMeasure());
        assertEquals(INGREDIENT_ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(UOM_ID, ingredient.getUnitOfMeasure().getId());
    }

    public void testConvertWithNullUom() {

        //given
        Ingredient ingredientSource = new Ingredient();
        ingredientSource.setId(INGREDIENT_ID);
        ingredientSource.setAmount(AMOUNT);
        ingredientSource.setUom(null);
        ingredientSource.setDescription(DESCRIPTION);

        //when
        IngredientCommand ingredient = ingredientConverter.convert(ingredientSource);

        //then
        assertNotNull(ingredient);
        assertNull(ingredient.getUnitOfMeasure());
        assertEquals(INGREDIENT_ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
    }
}