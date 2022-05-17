package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import junit.framework.TestCase;

import java.math.BigDecimal;

public class IngredientCommandToIngredientTest extends TestCase {

    private static final String DESCRIPTION = "description";
    private static final Long INGREDIENT_ID = 1L;
    private static final BigDecimal AMOUNT = new BigDecimal(2);
    private static final Long UOM_ID = 1L;

    IngredientCommandToIngredient ingredientConverter;

    public void setUp() throws Exception {
        ingredientConverter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }


    public void testNullParameter() {
        assertNull(ingredientConverter.convert(null));
    }

    public void testEmptyParameter() {
        assertNotNull(ingredientConverter.convert(new IngredientCommand()));
    }

    public void testConvert() {

        //given
        UnitOfMeasureCommand uomSource = new UnitOfMeasureCommand().setId(UOM_ID);
        IngredientCommand ingredientSource = new IngredientCommand();
        ingredientSource.setId(INGREDIENT_ID);
        ingredientSource.setAmount(AMOUNT);
        ingredientSource.setUom(uomSource);
        ingredientSource.setDescription(DESCRIPTION);

        //when
        Ingredient ingredient = ingredientConverter.convert(ingredientSource);

        //then
        assertNotNull(ingredient);
        assertNotNull(ingredient.getUom());
        assertEquals(INGREDIENT_ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(UOM_ID, ingredient.getUom().getId());
    }

    public void testConvertWithNullUom() {

        //given
        IngredientCommand ingredientSource = new IngredientCommand();
        ingredientSource.setId(INGREDIENT_ID);
        ingredientSource.setAmount(AMOUNT);
        ingredientSource.setUom(null);
        ingredientSource.setDescription(DESCRIPTION);

        //when
        Ingredient ingredient = ingredientConverter.convert(ingredientSource);

        //then
        assertNotNull(ingredient);
        assertNull(ingredient.getUom());
        assertEquals(INGREDIENT_ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
    }
}