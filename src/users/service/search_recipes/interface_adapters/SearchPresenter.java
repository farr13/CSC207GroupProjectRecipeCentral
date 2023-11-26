package users.service.search_recipes.interface_adapters;

import users.entity.Ingredient;
import users.entity.Recipe;
import users.service.search_recipes.application_business_rules.Boundary_Interfaces.SearchOutputBoundary;
import users.service.search_recipes.application_business_rules.DataTypes.SearchOutputData;
public class SearchPresenter implements SearchOutputBoundary {

    @Override
    public void prepareSuccessView(SearchOutputData recipeResults) {
        for (Recipe recipe: recipeResults.getRecipes()){
            System.out.println(recipe.getName() + "\n");
            System.out.println(recipe.getInstructions() + "\n");
            for (Ingredient ingredient: recipe.getIngredients())
                System.out.println(ingredient.getTextDescription() + ",");
        }
    }
}
