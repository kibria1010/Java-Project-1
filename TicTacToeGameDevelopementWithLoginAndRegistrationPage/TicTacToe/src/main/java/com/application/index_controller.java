package com.application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class index_controller implements Initializable{

    @FXML
    private ImageView index_logo_img;
    
    ActionEvent event;

    @FXML
    void exit_game(ActionEvent event) {
    	System.exit(0);    	
    }

    @FXML
    void start_game(ActionEvent event) throws IOException {
    	new Controller().sceneChange(event, "/com/example/tictactoe/hello-view.fxml");;
    }

    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Image img = new Image(getClass().getResourceAsStream("/images/game_logo.png"));

		
		if (index_logo_img != null) {
			index_logo_img.setImage(img);
		}


		
	}

}
