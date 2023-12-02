package view.usecase_views;

import backend.service.add_recipe.AddRecipeController;
import view.view_models.AddRecipeViewModel;
import view.view_models.SearchResultViewModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SearchResultsView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "results";
    private final SearchResultViewModel searchResultViewModel;
    private final AddRecipeViewModel addRecipeViewModel;
    private final AddRecipeController addRecipeController;
    private final  JButton AddtoCookbook;
    private final  JButton mainMenu;

    public SearchResultsView(SearchResultViewModel searchResultViewModel, AddRecipeViewModel addRecipeViewModel,
                             AddRecipeController addRecipeController) {
        this.searchResultViewModel = searchResultViewModel;
        this.addRecipeViewModel = addRecipeViewModel;
        this.addRecipeController = addRecipeController;
        searchResultViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel(SearchResultViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);


        JList<String> recipeLst = new JList<>(SearchResultViewModel.RECIPES);

        JScrollPane scrollPane = new JScrollPane(recipeLst);

        AddtoCookbook = new JButton(SearchResultViewModel.ADD_COOKBOOK_BUTTON_LABEL);
        mainMenu = new JButton(SearchResultViewModel.MAIN_BUTTON_LABEL);

        JPanel flowLayoutPanel = new JPanel(new FlowLayout());
        flowLayoutPanel.add(mainMenu);
        flowLayoutPanel.add(AddtoCookbook);

        mainMenu.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(mainMenu)) {
                            System.out.println("Not Implemented Yet");
                        }
                    }
                }
        );

        AddtoCookbook.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(AddtoCookbook)) {
                            System.out.println("Not Implemented Yet");
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(scrollPane);
        this.add(flowLayoutPanel, BorderLayout.NORTH);
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showConfirmDialog(this, "Cancel " +
                "not implemented yet.");
    }
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }
}