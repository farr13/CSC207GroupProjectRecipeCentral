package backend.service.make_cookbook;

import backend.entity.Cookbook;
import backend.entity.Recipe;

public class MakeCookbookInteractor implements MakeCookbookInputBoundary{

    final MakeCookbookDataAccessInterface makeCookbookDataAccessObject;
    final MakeCookbookOutputBoundary makeCookbookPresenter;
    public MakeCookbookInteractor(MakeCookbookDataAccessInterface makeCookbookDataAccessObject,
                                  MakeCookbookOutputBoundary makeCookbookOutputBoundary) {
        this.makeCookbookDataAccessObject = makeCookbookDataAccessObject;
        this.makeCookbookPresenter = makeCookbookOutputBoundary;
    }

    @Override
    public void execute(MakeCookbookInputData makeCookbookInputData) {
        String title = makeCookbookInputData.getTitle();
        if (!makeCookbookDataAccessObject.existByTitle(title)) {
            makeCookbookPresenter.prepareFailView(title + "Cookbook title already exist.");
        }
        else {
            Recipe[] recipes = {};
            Cookbook cookbook = new Cookbook(makeCookbookInputData.getTitle(), recipes);
            makeCookbookDataAccessObject.addCookbook(cookbook);
            MakeCookbookOutputData makeCookbookOutputData = new MakeCookbookOutputData(
                    cookbook.getName(), false);
            makeCookbookPresenter.prepareSuccessView(makeCookbookOutputData);
        }
    }
}
