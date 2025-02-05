package application;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.application.Platform;
import databasePart1.*;

/**
 * The WelcomeLoginPage class displays a welcome screen for authenticated users.
 * It allows users to navigate to their respective pages based on their role or
 * quit the application.
 */
public class WelcomeLoginPage {

	private final DatabaseHelper databaseHelper;

	public WelcomeLoginPage(DatabaseHelper databaseHelper) {
		this.databaseHelper = databaseHelper;
	}

	public void show(Stage primaryStage, User user) {

		VBox layout = new VBox(5);
		layout.setStyle("-fx-alignment: center; -fx-padding: 20;");

		Label welcomeLabel = new Label("Welcome!!");
		welcomeLabel.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

		// Button to navigate to the user's respective page based on their role
		Button continueButton = new Button("Continue to your Page");
		continueButton.setOnAction(a -> {
			String role = user.getRole();
			System.out.println(role);

			if (role.equals("admin")) {
				new AdminHomePage().show(primaryStage);
			} else if (role.equals("user")) {
				new UserHomePage().show(primaryStage);
			}
		});
		
		Button resetPasswordButton = new Button("Reset User Password");
		Button deleteUserButton = new Button("Delete User");
		Button listUsersButton = new Button("List Users");
		Button editUserRolesButton = new Button("Edit User Roles");
		Button adminRoleButton = new Button("Admin Role");
		Button studentRoleButton = new Button("Student Role");
		Button instructorRoleButton = new Button("Instructor Role");
		Button staffRoleButton = new Button("Staff Role");
		Button reviewerRoleButton = new Button("Reviewer Role");

		// HBox to align role buttons horizontally
		HBox roleButtonsBox = new HBox(10); // Space between buttons
		// Add margin to the HBox to create spacing between it and the button
		VBox.setMargin(roleButtonsBox, new Insets(5, 0, 0, 0)); // Top margin of 20px
		roleButtonsBox.setStyle("-fx-alignment: center;");
		roleButtonsBox.getChildren().addAll(adminRoleButton, studentRoleButton, instructorRoleButton, staffRoleButton,
				reviewerRoleButton);

		// Button to quit the application
		Button quitButton = new Button("Quit");
		quitButton.setOnAction(a -> {
			databaseHelper.closeConnection();
			Platform.exit(); // Exit the JavaFX application
		});

		// "Invite" button for admin to generate invitation codes
		if ("admin".equals(user.getRole())) {
			Button inviteButton = new Button("Invite");
			inviteButton.setOnAction(a -> {
				new InvitationPage().show(databaseHelper, primaryStage);
			});
			layout.getChildren().addAll(inviteButton, resetPasswordButton, deleteUserButton, listUsersButton, editUserRolesButton);
			layout.getChildren().addAll(welcomeLabel, continueButton, quitButton);
		}
		else
		{
			layout.getChildren().addAll(welcomeLabel, continueButton, roleButtonsBox, quitButton);
		}

		Scene welcomeScene = new Scene(layout, 800, 400);

		// Set the scene to primary stage
		primaryStage.setScene(welcomeScene);
		primaryStage.setTitle("Welcome Page");
	}
}