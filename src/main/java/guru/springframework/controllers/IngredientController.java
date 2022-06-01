package guru.springframework.controllers;

import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
public class IngredientController {
    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping("/recipe/{recipeId}/ingredients")
    public String listIngredients(@PathVariable Long recipeId, Model model) {
        log.debug("Getting ingredient list for recipe id: " + recipeId);

        model.addAttribute("recipe", recipeService.findCommandById(recipeId));

        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/show")
    public String listIngredients(@PathVariable Long recipeId,
                                  @PathVariable Long id, Model model) {
        log.debug("Getting ingredient by id: " + id);

        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId, id));

        return "recipe/ingredient/show";
    }
}
