// EcoBin
// Brendan Apple, Marcellus Day, Alex Hinkle

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;

public class App extends Application 
{ 
  private Scene homeScene;
  private Scene infoScene;
  private HashMap<String, Item> dataTable;
  
  @Override
  public void start(Stage primaryStage) {
    // Home Scene Stuff
    HBox initialSearchArea;
    VBox homeSceneFill;
    Label searchLabel; 
    TextField searchbar;
    Button searchButton;

    VBox homeBackgroundBox;
    VBox infoBackgroundBox;

    // Info Scene Stuff
    HBox toolbar;
    VBox infoSceneFill;
    Label name;
    Label recycleLabel;
    Label recycle;
    Label altLabel;
    Label alternatives;
    Label reuseLabel;
    Label reuse;
    Button backButton;

    // Options bar
    Vbox optionsBar;

    // Set Up Scenes
    searchbar = new TextField("");
    searchbar.setMaxWidth(200);

    searchLabel = new Label("Search for Your Item");
    searchButton = new Button(" > "); 

    backButton = new Button(" < ");
    recycleLabel = new Label("Recycle:");
    altLabel = new Label("Alternatives:");
    reuseLabel = new Label("Reuse:");
    name = new Label("");
    recycle = new Label("");
    alternatives = new Label("");
    reuse = new Label("");

    // Button Logic
    searchButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override public void handle(ActionEvent e) {
        String query = searchbar.getText().toLowerCase();
        Item result = dataTable.get(query);
        // label.setText(tf.getText());

        name.setText(result.getName().trim());
        recycle.setText(Item.formatStuff(result.getRecycling().trim()));
        alternatives.setText(Item.formatStuff(result.getAlternatives().trim()));
        reuse.setText(Item.formatStuff(result.getReuse().trim()));
        
        primaryStage.setScene(infoScene);
        searchbar.setText("");
      }
    });

    // Key Logic
    homeScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event)
        {
            System.out.println(event.getCode().toString()));

        }
    });

    backButton.setOnAction(new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
          primaryStage.setScene(homeScene);
        }
    });

    initialSearchArea = new HBox(searchbar, searchButton);
    initialSearchArea.setSpacing(10);
    initialSearchArea.setAlignment(Pos.CENTER);
    homeSceneFill = new VBox(searchLabel, initialSearchArea);
    homeSceneFill.setSpacing(20);
    homeSceneFill.setAlignment(Pos.CENTER);

    toolbar = new HBox(backButton);
    toolbar.setSpacing(10);
    toolbar.setAlignment(Pos.TOP_LEFT);
    infoSceneFill = new VBox(toolbar, name, recycleLabel, recycle, altLabel, alternatives, reuseLabel, reuse);
    infoSceneFill.setSpacing(10);
    infoSceneFill.setAlignment(Pos.TOP_CENTER);

    homeBackgroundBox = new VBox(homeSceneFill);
    homeBackgroundBox.setPadding(new Insets(100));
    infoBackgroundBox = new VBox(infoSceneFill);
    infoBackgroundBox.setPadding(new Insets(100));

    homeScene = new Scene(homeBackgroundBox, 1280, 720);
    infoScene = new Scene(infoBackgroundBox, 1280, 720);

    // Load in Data
    dataTable = new HashMap<String, Item>();
    dataTable = dataFill(dataTable);

    // Load Stages and CSS
    homeScene.getStylesheets().add("fancyBritishBoy.css");  
    infoSceneFill.getStylesheets().add("fancyBritishBoy.css"); 
    
    primaryStage.setTitle("EcoBin");
    primaryStage.setScene(homeScene);
    primaryStage.show();
  } 
    
  public static void main(String[] args) {
    launch(args);
  }

  public static HashMap<String, Item> dataFill(HashMap<String, Item> data) {
    String content = "";
    try {
      content = Files.readString(Paths.get("Data.txt"));
    } catch (IOException e) {
      System.err.println(e);
    }
    String[] datarray = content.split("@");

    for (String s : datarray) {
      Item temp = new Item(s);
      data.put(temp.getName().toLowerCase(), temp);
    }

    return data;
  }
} 
