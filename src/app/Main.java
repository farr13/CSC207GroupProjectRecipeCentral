package app;

import backend.entity.Cookbook;
import backend.service.delete_cookbook.DeleteCookbookDAI;
import backend.service.delete_recipe.ViewRecipeDAI;
import backend.service.see_list_cookbooks.SeeListCookbooksDAI;
import backend.service.view_cookbook.ViewCookbookDAI;
import data_access.*;
import view.recipe_objects.*;
import view.states.CookbookListState;
import view.usecase_views.CookbookListView;
import view.usecase_views.MainMenuView;
import view.usecase_views.SearchResultView;
import view.usecase_views.OpenCookbookView;
import view.view_managers.ViewManager;
import view.view_managers.ViewManagerModel;
import view.view_models.AddRecipeViewModel;
import view.view_models.CookbookListViewModel;
import view.view_models.MainMenuViewModel;
import view.view_models.OpenCookbookViewModel;
import view.view_models.SearchResultViewModel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // The main application window.
        JFrame frame = new JFrame("Recipe Central");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();
        JPanel view = new JPanel(cardLayout);
        view.setLayout(cardLayout);
        //frame.add(view);
        view.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        frame.add(view, BorderLayout.CENTER);

        //This keeps track of and manages which view is currently showing.
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(view, cardLayout, viewManagerModel);

        //Creating data access objects
        AddCookbookDAO addCookbookDAO = new AddCookbookDAO("saved_data.json");
        AddRecipeDAO addRecipeDAO = new AddRecipeDAO("saved_data.json");
        DeleteCookbookDAO deleteCookbookDAO = new DeleteCookbookDAO("saved_data.json");
        DeleteRecipeDAO deleteRecipeDAO = new DeleteRecipeDAO("saved_data.json");
        ViewCookbookDAO viewCookbookDAO = new ViewCookbookDAO("saved_data.json");
        ViewRecipeDAO viewRecipeDAO = new ViewRecipeDAO("saved_data.json");

        //Making View Models
        MainMenuViewModel mainMenuViewModel = new MainMenuViewModel();
        CookbookListViewModel cookbookListViewModel = new CookbookListViewModel();
        OpenCookbookViewModel openCookbookViewModel = new OpenCookbookViewModel();
        SearchResultViewModel searchResultViewModel = new SearchResultViewModel();
        AddRecipeViewModel addRecipeViewModel = new AddRecipeViewModel();

        //Making Views
        MainMenuView mainMenuView = MainMenuUseCaseFactory.create(
                viewManagerModel, mainMenuViewModel,
                searchResultViewModel, cookbookListViewModel,
                viewCookbookDAO);
        view.add(mainMenuView, mainMenuView.viewName);

        CookbookListView cookbookListView = CookbookListUseCaseFactory.create(
                viewManagerModel, mainMenuViewModel,
                cookbookListViewModel, openCookbookViewModel,
                viewCookbookDAO, deleteCookbookDAO);
        view.add(cookbookListView, cookbookListView.viewName);

        OpenCookbookView openCookbookView = OpenCookbookViewUseCaseFactory.create(
                viewManagerModel, openCookbookViewModel,
                cookbookListViewModel, mainMenuViewModel,
                viewCookbookDAO, deleteRecipeDAO, viewRecipeDAO);
        view.add(openCookbookView, openCookbookView.viewName);

        //Final Steps
        viewManagerModel.setActiveView(mainMenuView.viewName);
        viewManagerModel.firePropertyChanged();

        frame.pack();
        frame.setVisible(true);
    }
}
