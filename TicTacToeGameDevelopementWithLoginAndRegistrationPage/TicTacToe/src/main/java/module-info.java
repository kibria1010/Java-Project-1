module com.example.tictactoe {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    
	requires java.sql;
	requires javafx.graphics;
	
	opens com.application to javafx.graphics, javafx.fxml;

    opens com.example.tictactoe to javafx.fxml;
    exports com.example.tictactoe;
}