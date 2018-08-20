package search.view;

import search.Advertisement;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.util.List;

/**
 * Represents the implementation of the view. The view displays the model selector gui to the user
 * and displays the search results to the user (providing buttons to interact with the results).
 */
public class ViewSearch extends JFrame implements IViewSearch {
    //the panel to display the model selection gui
    private PanelModelSelector panelModelSelector;
    //the panel to display the search results
    private PanelViewResults panelViewResults;
    //the width of the model selection gui
    private int widthModel;
    //the height of the model selection gui
    private int heightModel;
    //the width of the results gui
    private int widthResults;
    //the height of the results gui
    private int heightResults;

    /**
     * Constructs the interactive view with default sizes for the gui panels.
     */
    public ViewSearch() {
        super("WebSearch");
        this.widthModel = 300;
        this.heightModel = 200;
        this.widthResults = 900;
        this.heightResults = 700;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(this.widthModel, this.heightModel);
        this.setResizable(false);

        this.panelModelSelector = new PanelModelSelector(this.widthModel, this.heightModel - 30);
        this.panelViewResults = new PanelViewResults(this.widthResults - 15, this.heightResults - 30);
    }

    /**
     * Sets the visibility of the model selection gui.
     *
     * @param visible whether or not the model selection gui is visible
     */
    @Override
    public void setModelSelectorVisibility(boolean visible) {
        this.setContentPane(this.panelModelSelector);
        this.setVisible(visible);
    }

    /**
     * Given a list of Advertisements, sets the visibility of the results gui to the given value
     *
     * @param advertisements the list of Advertisements to display
     * @param visible whether or not the results gui is visible
     */
    @Override
    public void updateResultsVisibility(List<Advertisement> advertisements, boolean visible) {
        this.panelViewResults.populateResults(advertisements);
        this.setSize(this.widthResults, this.heightResults);
        this.setContentPane(this.panelViewResults);
        this.setVisible(visible);
    }

    @Override
    public void showAttributeEditor() {
        // TODO
    }

    /**
     * Adds the given listeners to the appropriate Components in the view. Used for handling user
     * input from the gui (e.g. clicking buttons, list selections, key presses)
     *
     * @param actionListener the action listener
     * @param listListener the list selection listener
     * @param keyListener the key listener
     */
    @Override
    public void addListeners(ActionListener actionListener, ListSelectionListener listListener, KeyListener keyListener) {
        if (actionListener == null || listListener == null || keyListener == null) {
            throw new IllegalArgumentException("Given listeners cannot by null");
        } else {
            this.panelModelSelector.addActionListener(actionListener);
            this.panelViewResults.addActionListener(actionListener);
            this.panelViewResults.addListeners(listListener, keyListener);
        }
    }

    /**
     * Opens the currently selected listing in the system's default web browser.
     */
    @Override
    public void openSelected() {
        this.panelViewResults.openSelected();
    }


    /**
     * Opens all listings previously passed to the view in the system's default web browser.
     */
    @Override
    public void openAll() {
        this.panelViewResults.openAll();
    }

    /**
     * Refreshes the gui displaying the listing results (description and images), based on
     * the currently selected listing.
     */
    @Override
    public void updateListingSelection() {
        this.panelViewResults.updateListingSelection();
    }

    /**
     * Refreshes the gui displaying the listing image (which image in the Advertisement's list of images), given
     * what key was pressed.
     *
     * @param key the key the user pressed
     */
    @Override
    public void updateImageSelection(int key) {
        this.panelViewResults.updateImageSelection(key);
    }
}
