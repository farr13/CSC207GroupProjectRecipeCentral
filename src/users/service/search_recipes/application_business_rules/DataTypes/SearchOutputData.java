package users.service.search_recipes.application_business_rules.DataTypes;

import users.entity.Recipe;

public class SearchOutputData {
    private Recipe[] recipes;

    public SearchOutputData(Recipe[] recipes){
        this.recipes = recipes;
    }

    public Recipe[] getRecipes() {
        return recipes;
    }
}
