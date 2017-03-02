
package term;

import java.util.concurrent.TimeUnit;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/**
 * TERM PROJECT
 * Project description: 
 * This program creates a username and password for an account
 * It leads the user through the process, and ensures that the
 * password is secure. Once the user logs into the account one
 * time, the program ends with a congratulations message.
 * 
 * @author Michael Rod
 * version 1.1.0
 * released 8/6/2016
 * 
 */
    
    public class LoginFlowPane extends Application {
        @Override // Override the start method of the Application class
        public void start(Stage primaryStage) throws InterruptedException {
            
            // Create nodes
            TextField inputUsername = new TextField();
            TextField inputPassword = new PasswordField();
            TextField correctUsername = new TextField();
            PasswordField correctPassword = new PasswordField();
            PasswordField re_enterPassword = new PasswordField();
            TextField firstName = new TextField();
            TextField lastName = new TextField();
            TextField email = new TextField();
        
            // Create a pane for the introduction
            TextArea introPane = new TextArea(); 
           
            // Populate the introduction text area
            intro(introPane);
            
            // Create a scene for the pane
            Scene scene = new Scene(introPane, 250, 250); 
            primaryStage.setResizable(false);
            primaryStage.setScene(scene); // Place the scene on the stage
            primaryStage.show(); // Display the stage

            // Create another pane and set its properties
            FlowPane pane1 = new FlowPane();
            pane1.setPadding(new Insets(10,10,10,10));
            pane1.setHgap(5);
            pane1.setVgap(5);
            
            // Create another pane and set its properties
            FlowPane pane2 = new FlowPane();
            pane2.setPadding(new Insets(10,10,10,10));
            pane2.setHgap(5);
            pane2.setVgap(5);
            
            // Place nodes in the pane
            pane1.getChildren().addAll(new Label("Username:"),
                inputUsername);
            pane1.getChildren().addAll(new Label("Password:"),
                inputPassword);
            Button btNewUser = new Button("New User");
            pane1.getChildren().addAll(btNewUser);
            Button btLogin = new Button("Login");
            pane1.getChildren().addAll(btLogin);        
            
            // Place nodes in the pane
            pane2.getChildren().addAll(new Label("Username:"),
                correctUsername);
            pane2.getChildren().addAll(new Label("Password:"),
                correctPassword);
            pane2.getChildren().addAll(new Label("Re-enter Password:"),
                re_enterPassword);
            pane2.getChildren().addAll(new Label("First Name:"),
                firstName);
            pane2.getChildren().addAll(new Label("Last Name:"),
                lastName);
            pane2.getChildren().addAll(new Label("Email:"),
                email);
            Button btNext2 = new Button("Next"); // Next button on pane2 associated with scene2 
            pane2.getChildren().addAll(btNext2);
            
            // Create another pane and its properties
                Pane pane9 = new Pane();
        
            // Create box for the pane
                Rectangle rect1 = new Rectangle(25,10,200,60);
                rect1.setStroke(Color.RED);
                rect1.setFill(Color.WHITE);
                pane9.getChildren().add(rect1);
            
            // Create text for the pane
                Text text1 = new Text(45,40,"Congratulations! You're all set!");
                pane9.getChildren().add(text1);

            // Create a scene and place it on the stage
            Scene scene1 = new Scene(pane1, 200, 200);
            primaryStage.setTitle("Login Dialog Box"); // Set the stage title
            TimeUnit.SECONDS.sleep(8); // This is a pause while showing the introduction
            primaryStage.setScene(scene1); // Place the scene on the stage
            primaryStage.show(); // Display the stage; 
            
            // Event Handler New User button
            btNewUser.setOnAction((ActionEvent event) -> {
                newUser(pane2, primaryStage, btNext2, correctPassword, re_enterPassword,
                        correctUsername, pane9, rect1);    
            }); // End of button action Scene 1 --> New User
        
            // Event Handler Login button
            btLogin.setOnAction((ActionEvent event) -> {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Username not found. Please click New User");
                alert.showAndWait();    
            }); // End of button action Scene 1 --> Login
        
        } // End of Start Method 
        
        // Method for creating scene 2 - newUser
        public void newUser(FlowPane flowPane, Stage stage, Button bt1, PasswordField correctPassword,
                PasswordField inputPassword, TextField correctUsername, Pane pane9, Rectangle rect1) {
            
            // Create a scene and place it on the stage    
            Scene scene2 = new Scene(flowPane, 200, 400);
            stage.setTitle("New User Dialog Box"); // Set the stage title
            stage.setScene(scene2); // Place the scene on the stage
            stage.show(); // Display the stage;
            
                // Event Handler
                bt1.setOnAction((ActionEvent event) -> {
                    if (isSecure(correctPassword.getText()))
                        System.out.println("Debugging Comment: Secure Password");
                    else { // Not secure password warning
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText(null);
                        alert.setContentText("Secure password requirements include "
                                + "at least eight characters with at least one lower "
                                + "case[a-z], one upper case[A-Z], and one number[0-9].");
                        alert.showAndWait();
                        return;
                    }
                    if (isMatchingPassword(correctPassword.getText(), inputPassword.getText()))
                        System.out.println("Debugging Comment: Match Password");
                    else { // Passwords do not match warning
                        Alert alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText(null);
                        alert.setContentText("The passwords provided DO NOT match.");
                        alert.showAndWait();
                        return;
                    }        
                    setSecurityQuestions(stage, correctPassword, correctUsername, pane9, rect1);
            }); // End of button action Scene 2 --> Next    
        
        } //end newUser method
        
        // Method for populating the introducion text
        public void intro(TextArea textArea) {
        textArea.setEditable(false);
        textArea.setWrapText(true);
        textArea.setText("This program will setup a new user account with a username, "
                + "password, and security question. It demonstrates my abilities "
                + "with event driven programming in JavaFX. Please wait...then press New User");
        }
        
        // Method for getting choice from drop down list
        private String getChoice (ChoiceBox<String> choiceBox){
            String questionChoice = choiceBox.getValue();
            return questionChoice;
         }

        // Method for going back to login screen after security question setup 
        public void backToLogin(Stage stage, PasswordField correctPassword, TextField correctUsername,
                        ChoiceBox<String> choiceBox, TextField securityAnswer, Pane pane9, Rectangle rect1) {
            
            // Declare variables and constants
            TextField inputUsername = new TextField(); 
            PasswordField inputPassword = new PasswordField();
            int[] countAttempts = new int[1]; // variables for use in lambda expressions need
                            // to be effectively final
            
            // Create a pane and set its properties
            FlowPane pane4 = new FlowPane();
            pane4.setPadding(new Insets(10,10,10,10));
            pane4.setHgap(5);
            pane4.setVgap(5);
            
            // Place nodes in the pane
            pane4.getChildren().addAll(new Label("Username:"),
                inputUsername);
                pane4.getChildren().addAll(new Label("Password:"),
                inputPassword);
            Button btLogin = new Button("Login"); // Login button on pane 4
            Button btForgot = new Button("Forgot Password"); // Forgot Password button on pane 4
            pane4.getChildren().addAll(btLogin);
            pane4.getChildren().addAll(btForgot);
  
            // Create a scene and place it on the stage
            Scene scene4 = new Scene(pane4, 200, 200);
            stage.setTitle("Login Dialog Box"); // Set the stage title
            stage.setScene(scene4); // Place the scene on the stage
            stage.show(); // Display the stage;
            
                // Event Handler
                btLogin.setOnAction((ActionEvent event) -> {
                    countAttempts[0]++;
                    if ((isMatchingPassword(correctPassword.getText(), inputPassword.getText())) &&
                            isMatch(correctUsername.getText(), inputUsername.getText())){
                        System.out.println("Debugging Comment: Successful Setup Screen - via Password");
                        successShape(stage, pane9, rect1);
                    }
                    else { // UserName or Password incorrect warning
                        System.out.println("Debugging Comment: CountAttempts = " + countAttempts[0]);
                        if (countAttempts[0] > 2)
                            securityQuestionCheck(stage, choiceBox, securityAnswer);
                        Alert alert;
                        alert = new Alert(AlertType.WARNING);
                        alert.setTitle("Warning");
                        alert.setHeaderText(null);
                        alert.setContentText("Username or Password provided is incorrect.");
                        alert.showAndWait();
                    }
            }); // End of button action Scene 4 --> Login
               
                // Event Handler
                btForgot.setOnAction((ActionEvent event) -> {
                    securityQuestionCheck(stage, choiceBox, securityAnswer);
            }); // End of button action Scene 4 --> Forgot Password                
        
        } // End backToLogin method

        // method for security question setup
        public void setSecurityQuestions(Stage stage, PasswordField correctPassword, 
                TextField correctUsername, Pane pane9, Rectangle rect1){
            String default1 = "Please select a security question...";
            String question1 = "What is the name of your favorite pet?";
            String question2 = "What is the name of the street you grew up on?";
            String question3 = "What is the name of the city where you were married?";
            String question4 = "What is the first name of your childhood best friend?";
            
            // Create a pane and set its properties
            GridPane pane3 = new GridPane();
  
            // Place nodes in the pane
            ChoiceBox<String> choiceBox = new ChoiceBox<>();
            choiceBox.getItems().add(default1);
            choiceBox.getItems().add(question1);
            choiceBox.getItems().add(question2);
            choiceBox.getItems().add(question3);
            choiceBox.getItems().add(question4);
            pane3.getChildren().addAll(choiceBox);
            
            TextField securityAnswer = new TextField();
            pane3.add(securityAnswer, 0, 1);
            
            Button bt3Next = new Button("Next"); // Next button on pane 3
            pane3.add(bt3Next, 0, 2);
            
            // Set default value
            choiceBox.setValue(default1);
            
            // Create a scene and place it on the stage
            Scene scene3 = new Scene(pane3, 400, 200);
            stage.setTitle("Select Security Question Dialog Box"); // Set the stage title
            stage.setScene(scene3); // Place the scene on the stage
            stage.show(); // Display the stage;
 
            // Event Handler Next button
            bt3Next.setOnAction((ActionEvent event) -> {
                System.out.println("Debugging Comment: Security Question Selected");
                Alert alert;
                        alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText(null);
                        alert.setContentText("User information is saved.");
                        alert.showAndWait();
                
                backToLogin(stage, correctPassword, correctUsername, choiceBox, securityAnswer, pane9, rect1);    
            }); // End of button action Scene 3 --> Next
        
        } // end setSecurityQuestions method
        
        // Method for security question check
        public void securityQuestionCheck(Stage stage, ChoiceBox<String> choiceBox, TextField securityAnswer) {
            
            // create a pane
            GridPane pane5 = new GridPane();
            
            // create nodes
            TextField heading = new TextField("Please answer the following security question:");
            heading.setPrefWidth(350);
            TextField questionChoice = new TextField(getChoice(choiceBox));
            questionChoice.setPrefWidth(350);
            TextField answer = new TextField();
            answer.setPrefWidth(350);
            Button bt5Next = new Button("Next");
            
            // place the nodes on a pane
            pane5.add(heading, 0, 1);
            pane5.add(questionChoice, 0, 2);
            pane5.add(answer, 0 , 3);
            pane5.add(bt5Next, 0 , 4);
            
            // Create a scene and place it in the stage
            Scene scene5 = new Scene(pane5, 400, 200);
            stage.setTitle("Security Question Dialog Box"); // Set the stage title
            stage.setScene(scene5); // Place the scene in the stage
            stage.show(); // Display the stage;
            
            // Event Handler
            bt5Next.setOnAction((ActionEvent event) -> {
                if (isMatch(answer.getText(), securityAnswer.getText())){
                    System.out.println("Debugging Comment: Successful Setup Screen - via Security Question");
                    successGif(stage); 
                }    
                else {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Incorrect answer to security question, please "
                        + "call 888-555-5555 for further assistance.");
                alert.showAndWait(); 
                System.exit(1); // Incorrect user input
                }
            });    
        } //end securityQuestionCheck method
        
        // Method for successful login case
        public void successGif(Stage stage){
            Pane pane8 = new HBox(10);
            pane8.setPadding(new Insets(5,5,5,5));
            Image pic = new Image("http://2.bp.blogspot.com/-wh1GDiNwZh4/VOw5DRh6yCI/"
                    + "AAAAAAAAZ-M/HAGF0MWQsEQ/s1600/Congratulations_zps35c2563e.jpg");
            
            pane8.getChildren().add(new ImageView(pic));
            
            // Create a scene and place it on the stage
            Scene scene = new Scene(pane8);
            stage.setTitle("Successful Setup Dialog Box"); // Set the stage title
            stage.setScene(scene); // Place the scene on the stage
            stage.show(); // Display the stage
        }    
                
        // Another alternative method for successful login case
        public void successShape (Stage stage, Pane pane, Rectangle rect) {
             
            //Create a scene
            Scene scene = new Scene(pane, 250,150);
            stage.setTitle("Successful Setup Dialog Box");
            stage.setScene(scene); // Place the scene on the stage
            stage.show();
            
        }

        // Method to check passwords are identical
        public boolean isMatchingPassword (String s1, String s2 ) { // Check passwords match
            return (s1.equals(s2));    
        }
         
        // Method to check the usernames are identical - ignore case
        public boolean isMatch (String s1, String s2 ) { // Check usernames match
            return (s1.equalsIgnoreCase(s2));    
        }
        
        // Method to check the password has necessary security features
        public boolean isSecure (String pw1) { // Check password is secure
            
            // Declare variables
            int countUpper = 0, countLower = 0, countDigit = 0;
            
            // Loop through each character of password string
            for (int i = 0; i < pw1.length(); i++) {
                if (Character.isUpperCase(pw1.charAt(i)))
                    countUpper++;
                if (Character.isLowerCase(pw1.charAt(i)))
                    countLower++;
                if (Character.isDigit(pw1.charAt(i)))
                    countDigit++;
            }
            
            return (countUpper > 0 && countLower > 0 && countDigit > 0 && pw1.length() >= 8);
        } // End isSecure method
   
    // Main method for JavaFX requred by IDE    
    public static void main(String[] args) {
        // TODO code application logic here
        Application.launch(args);
        
    }
} 


    

