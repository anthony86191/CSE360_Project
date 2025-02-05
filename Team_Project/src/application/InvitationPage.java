package application;


import databasePart1.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.stage.Stage;

/**
 * InvitePage class represents the page where an admin can generate an invitation code.
 * The invitation code is displayed upon clicking a button.
 */

public class InvitationPage {

	/**
     * Displays the Invite Page in the provided primary stage.
     * 
     * @param databaseHelper An instance of DatabaseHelper to handle database operations.
     * @param primaryStage   The primary stage where the scene will be displayed.
     */
    public void show(DatabaseHelper databaseHelper,Stage primaryStage) {
    	VBox layout = new VBox();
	    layout.setStyle("-fx-alignment: center; -fx-padding: 20;");
	    
	    // Label to display the title of the page
	    Label userLabel = new Label("Invite ");
	    userLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
	    
	    // Button to generate the invitation code
	    Button showCodeButton = new Button("Generate Invitation Code");
	    Button adminRoleButton = new Button("Admin Role");
	    Button studentRoleButton = new Button("Student Role");
	    Button instructorRoleButton = new Button("Instructor Role");
	    Button staffRoleButton = new Button("Staff Role");
	    Button reviewerRoleButton = new Button("Reviewer Role");
	    
	    // Label to display the generated invitation code
	    Label inviteCodeLabel = new Label(""); ;
        inviteCodeLabel.setStyle("-fx-font-size: 14px; -fx-font-style: italic;");
        
        showCodeButton.setOnAction(a -> {
        	// Generate the invitation code using the databaseHelper and set it to the label
            String invitationCode = databaseHelper.generateInvitationCode();
            inviteCodeLabel.setText(invitationCode);
        });
	    
        // HBox to align role buttons horizontally
        HBox roleButtonsBox = new HBox(10); // Space between buttons
        // Add margin to the HBox to create spacing between it and the button
        VBox.setMargin(roleButtonsBox, new Insets(20, 0, 0, 0)); // Top margin of 20px
        roleButtonsBox.setStyle("-fx-alignment: center;");
        roleButtonsBox.getChildren().addAll(adminRoleButton, studentRoleButton, instructorRoleButton, staffRoleButton, reviewerRoleButton);
        
        layout.getChildren().addAll(userLabel, showCodeButton, roleButtonsBox, inviteCodeLabel);
	    Scene inviteScene = new Scene(layout, 800, 400);

	    // Set the scene to primary stage
	    primaryStage.setScene(inviteScene);
	    primaryStage.setTitle("Invite Page");
    	
    }
}