package backend.service.delete_recipe;

import backend.entity.Ingredient;
import backend.entity.Recipe;
import view.recipe_objects.Triplet;

import java.util.ArrayList;

public class DeleteRecipeInteractor implements DeleteRecipeInputBoundary {
    private final DeleteRecipeDAI deleteRecipeDAO;
    private final ViewRecipeDAI viewRecipeDAO;
    private final DeleteRecipeOutputBoundary deleteRecipePresenter;

    public DeleteRecipeInteractor(DeleteRecipeDAI deleteRecipeDAO, ViewRecipeDAI viewRecipeDAO,
                                  DeleteRecipeOutputBoundary deleteRecipeOutputBoundary){
        this.deleteRecipeDAO = deleteRecipeDAO;
        this.viewRecipeDAO = viewRecipeDAO;
        this.deleteRecipePresenter = deleteRecipeOutputBoundary;
    }
    private Recipe[] convertTripletToRecipes(Triplet[] triplets){
        ArrayList<Recipe> storedRecipes = new ArrayList<>();
        for (Triplet triplet: triplets){
            ArrayList<Ingredient> storedIngredients= new ArrayList<Ingredient>();
            for (String description: triplet.getList())
                storedIngredients.add(new Ingredient(description));
            Recipe recipeTemp = new Recipe(triplet.getName(), triplet.getLink(), storedIngredients.toArray(new Ingredient[0]));
            storedRecipes.add(recipeTemp);
        }

        return storedRecipes.toArray(new Recipe[0]);
    }
    @Override
    public void execute(DeleteRecipeInputData deleteRecipeInputData){
        try {
            deleteRecipeDAO.deleteRecipe(deleteRecipeInputData.getCookbookName(),
                    convertTripletToRecipes(deleteRecipeInputData.getRecipes()));

            DeleteRecipeOutputData deleteRecipeOutputData =
                    new DeleteRecipeOutputData(viewRecipeDAO.viewRecipes(deleteRecipeInputData.getCookbookName()));

            deleteRecipePresenter.prepareSuccessView(deleteRecipeOutputData);
        } catch (Exception e) {
            deleteRecipePresenter.prepareFailView("Could not delete cookbook.");
        }
    }
}
