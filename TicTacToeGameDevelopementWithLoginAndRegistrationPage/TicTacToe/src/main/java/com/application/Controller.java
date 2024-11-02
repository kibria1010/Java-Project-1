package com.application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Controller implements Initializable{

    @FXML
    private ImageView log_img_view;

    @FXML
    private TextField log_password;

    @FXML
    private TextField log_username;

    @FXML
    private TextField reg_email;

    @FXML
    private ImageView reg_img_view;

    @FXML
    private TextField reg_name;

    @FXML
    private TextField reg_password;

    @FXML
    private TextField reg_userName;

    
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/testDatabase", "root", "");
    }
    
    
    @FXML
    void registrationBtn(ActionEvent event) throws IOException {
        String name = reg_name.getText();
        String email = reg_email.getText();
        String username = reg_userName.getText();
        String password = reg_password.getText();

        try (Connection connection = getConnection()) {

            String sql = "INSERT INTO `registration`(`name`, `username`, `password`, `email`) VALUES (?,?,?,?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.setString(4, email);

            int affectedRows = statement.executeUpdate();

            if (affectedRows > 0) {
            	
            	alert("Registration success!", "You are successfully registrer!");
            	
            	//scene change...
            	sceneChange(event,"login.fxml");
            	
            } else {
            	alert("Failed", "You are failed to registration!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection failed!");
        }
    }

    
    @FXML
    void login_btn(ActionEvent event) throws IOException, InterruptedException {
        String username = log_username.getText();
        String password = log_password.getText();

        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM registration WHERE username = ? AND password = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                alert("Login Successful", "Welcome, " + resultSet.getString("name") + "!");
                Thread.sleep(1000);
                sceneChange(event,"index.fxml");
                
            } else {
                alert("Login Failed", "Invalid username or password.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            alert("Error", "An error found during login. Please try again.");
        }
    }
    
    
    
    @FXML
    void goToRegistrationPage(ActionEvent event) throws IOException {
    	sceneChange(event,"Registration.fxml");
    }

    
    
    @FXML
    void goToLoginPage(ActionEvent event) throws IOException {
    	sceneChange(event,"login.fxml");
    }
    
    
    
    protected void sceneChange(ActionEvent event, String url) throws IOException {
    	Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    	
    	Parent root = FXMLLoader.load(getClass().getResource(url));
    	Scene scene = new Scene(root);
    	Stage newStage = new Stage();
    	
    	newStage.setScene(scene);
    	currentStage.close();
    	newStage.show();
    }
    
    
    private void alert(String title, String msg) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle(title);
    	alert.setContentText(msg);
    	alert.showAndWait();
    }

    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Image img = new Image(getClass().getResourceAsStream("/images/logo.jpg"));

		
		if (reg_img_view != null) {
		    reg_img_view.setImage(img);
		}

		if (log_img_view != null) {
		    log_img_view.setImage(img);
		}
		
	}

}
