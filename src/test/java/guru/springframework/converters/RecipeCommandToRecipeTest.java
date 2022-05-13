package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.NotesCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Difficulty;
import guru.springframework.domain.Recipe;
import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Set;

public class RecipeCommandToRecipeTest extends TestCase {

    public static final Long RECIPE_ID = 1L;
    public static final Integer COOK_TIME = Integer.valueOf("5");
    public static final Integer PREP_TIME = Integer.valueOf("7");
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS = Integer.valueOf("3");
    public static final String SOURCE = "Source";
    public static final String URL = "Some URL";
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;

    RecipeCommandToRecipe recipeConverter;


    public void setUp() throws Exception {
        recipeConverter = new RecipeCommandToRecipe(new IngredientCommandToIngredient(
                new UnitOfMeasureCommandToUnitOfMeasure()), new CategoryCommandToCategory(), new NotesCommandToNotes());
    }


    public void testNullParameter() {
        assertNull(recipeConverter.convert(null));
    }

    public void testEmptyParameter() {
        assertNotNull(recipeConverter.convert(new RecipeCommand()));
    }

    public void testConvert() {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_ID);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setServings(SERVINGS);
        recipeCommand.setSource(SOURCE);
        recipeCommand.setUrl(URL);
        recipeCommand.setNotes(new NotesCommand().setId(NOTES_ID));

        Set<CategoryCommand> categories = new HashSet<>();
        categories.add(new CategoryCommand().setId(CAT_ID_1));
        categories.add(new CategoryCommand().setId(CAT_ID2));

        recipeCommand.setCategories(categories);

        Set<IngredientCommand> ingredients = new HashSet<>();
        ingredients.add(new IngredientCommand().setId(INGRED_ID_1));
        ingredients.add(new IngredientCommand().setId(INGRED_ID_2));

        recipeCommand.setIngredients(ingredients);

        //when
        Recipe recipe = recipeConverter.convert(recipeCommand);

        //then
        assertNotNull(recipe);
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(URL, recipe.getUrl());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
    }
}