package application;

import javafx.scene.control.TextArea;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Main extends Application {
	Stage primaryStage;
	int x = 50, y = 200;

	@Override
	public void start(Stage primaryStage) {
		try {
			Pane root = new Pane();
			Pane root2 = new Pane();
			HBox root1 = new HBox();

			Image img1 = new Image("mainpageavl.jpg", 1280, 720, false, false);
			BackgroundImage bImg = new BackgroundImage(img1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, // Setting
																														// //
																														// scene
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround = new Background(bImg);
			root.setBackground(bGround);// main screen

			Image img3 = new Image("Deps&Stu.jpg", 1280, 720, false, false);
			BackgroundImage bImg2 = new BackgroundImage(img3, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, // Setting
					// scene
					BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
			Background bGround2 = new Background(bImg2);
			root2.setBackground(bGround2);// main screen
			root1.setBackground(bGround2);

			AVLTree<String> tree = new AVLTree<>();

			Button browse = new Button();

			browse.setTranslateX(990);
			browse.setTranslateY(460);

			browse.setText("Browse File");
			browse.setStyle("-fx-background-color: #212121; -fx-font-size:23; -fx-font-weight:bold");
			browse.setTextFill(Color.WHITE);
			root.getChildren().add(browse);

			
			
			ArrayList<String> idk = new ArrayList<String>();

			browse.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					FileChooser fc = new FileChooser();
					fc.getExtensionFilters().addAll(new ExtensionFilter("Text File", "*txt"));
					File srccc = fc.showOpenDialog(primaryStage);
					fc.setTitle("Choose Path for BZU Departments");
					idk.add(srccc.toString());
				}
			});
			
			
			FileChooser fc = new FileChooser();
			fc.getExtensionFilters().addAll(new ExtensionFilter("Text File", "*txt"));
			File file1 = fc.showOpenDialog(primaryStage);
			fc.setTitle("Choose Path for BZU Departments");

			File myObj = new File(file1 + "");
			Scanner myReader = new Scanner(myObj);
			

			
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine().replaceAll("\\s+", "");
				int name = data.indexOf('/');
				tree.insertElement(data.substring(0, name));

				int last = file1.toString().lastIndexOf('\\');
				File myObj1 = new File(file1.toString().substring(0, last)
						+ data.substring(name, data.length()).replaceAll("\\s+", ""));
				System.out.println(myObj1);
				if (myObj1.exists()) {

					System.out.println(myObj1);

					root1.getChildren().add(createButtonForDepartment(data.substring(0, name), myObj1.toString(), x, y));
				
					if (x >= 400) {
						x = 50;
						y += 100;
					} else {
						x += 50;
					}
				}
				myObj = null;

			}
			myReader.close();

			System.out.println(tree.InorderTraversal());

			Button mngDepartments = new Button();

			mngDepartments.setTranslateX(980);
			mngDepartments.setTranslateY(350);

			mngDepartments.setText("Departments");
			mngDepartments.setStyle("-fx-background-color: #212121; -fx-font-size:23; -fx-font-weight:bold");
			mngDepartments.setTextFill(Color.WHITE);

			// programming buttons for the Departments UI

			Button DisplayDepartments = new Button();
			DisplayDepartments.setTranslateX(50);
			DisplayDepartments.setTranslateY(200);
			DisplayDepartments.setText("Display All Departments");
			DisplayDepartments.setStyle("-fx-background-color: #212121; -fx-font-size:21");
			DisplayDepartments.setTextFill(Color.WHITE);
			root2.getChildren().add(DisplayDepartments);

			DisplayDepartments.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					final Stage dialog = new Stage();
					dialog.initModality(Modality.APPLICATION_MODAL);
					dialog.initOwner(primaryStage);

					GridPane dialogVbox = new GridPane();
					dialogVbox.setAlignment(Pos.CENTER);
					dialogVbox.setHgap(5);
					dialogVbox.setVgap(5);
					dialogVbox.setPadding(new Insets(25, 25, 25, 25));

					Label inord = new Label("In Order Traversal:");

					Label text = new Label();
					text.setText(tree.InorderTraversal());

					Label preord = new Label("Pre Order Traversal:");

					Label text1 = new Label();
					text1.setText(tree.preorderTraversal());

					Label postord = new Label("Post Order Traversal:");

					Label text2 = new Label();
					text2.setText(tree.postorderTraversal());

//					text.setStyle("verdana", 12);
					dialogVbox.add(inord, 0, 0);
					dialogVbox.add(text, 0, 1);
					dialogVbox.add(preord, 0, 2);
					dialogVbox.add(text1, 0, 3);
					dialogVbox.add(postord, 0, 4);
					dialogVbox.add(text2, 0, 5);

					BorderPane borderPane = new BorderPane();
					borderPane.setCenter(dialogVbox);
					Scene dialogScene = new Scene(borderPane, 640, 480);
					dialog.setTitle("Display Flights");
					dialog.setScene(dialogScene);
					dialog.show();
				}
			});

			Button TreeHeight = new Button();
			TreeHeight.setTranslateX(50);
			TreeHeight.setTranslateY(300);
			TreeHeight.setText("Tree Height");
			TreeHeight.setStyle("-fx-background-color: #212121; -fx-font-size:21");
			TreeHeight.setTextFill(Color.WHITE);
			root2.getChildren().add(TreeHeight);

			TreeHeight.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					final Stage dialog = new Stage();
					dialog.initModality(Modality.APPLICATION_MODAL);
					dialog.initOwner(primaryStage);

					GridPane dialogVbox = new GridPane();
					dialogVbox.setAlignment(Pos.CENTER);
					dialogVbox.setHgap(5);
					dialogVbox.setVgap(5);
					dialogVbox.setPadding(new Insets(25, 25, 25, 25));

					Label inord = new Label("Tree Height: ");

					Label text = new Label();
					text.setText(tree.TreeHeight() + "");

//					text.setStyle("verdana", 12);
					dialogVbox.add(inord, 0, 0);
					dialogVbox.add(text, 0, 1);

					BorderPane borderPane = new BorderPane();
					borderPane.setCenter(dialogVbox);
					Scene dialogScene = new Scene(borderPane, 640, 480);
					dialog.setTitle("Tree Height");
					dialog.setScene(dialogScene);
					dialog.show();
				}
			});

			Button SearchDepartment = new Button();
			SearchDepartment.setTranslateX(50);
			SearchDepartment.setTranslateY(400);
			SearchDepartment.setText("Search Department");
			SearchDepartment.setStyle("-fx-background-color: #212121; -fx-font-size:21");
			SearchDepartment.setTextFill(Color.WHITE);
			root2.getChildren().add(SearchDepartment);

			SearchDepartment.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					final Stage dialog = new Stage();
					dialog.initModality(Modality.APPLICATION_MODAL);
					dialog.initOwner(primaryStage);

					GridPane dialogVbox = new GridPane();
					dialogVbox.setAlignment(Pos.CENTER);
					dialogVbox.setHgap(5);
					dialogVbox.setVgap(5);
					dialogVbox.setPadding(new Insets(25, 25, 25, 25));
					Label depName = new Label("Enter Department Name to Search: ");

					TextField DEPinput = new TextField();

					DEPinput.setPrefColumnCount(14);

					dialogVbox.add(depName, 0, 1);
					dialogVbox.add(DEPinput, 1, 1);
					Button showFlight = new Button("Search");
					Text show = new Text();
					showFlight.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							if (tree.searchElement(DEPinput.getText()))
								show.setText("Department Found!");
							else {
								HBox radios = new HBox();
								radios.setPrefWidth(300);
								radios.setSpacing(20);
								radios.setPadding(new Insets(0, 0, 0, 0));

								Label question = new Label(
										"Would you like to create a new Department named " + DEPinput.getText());
								ToggleGroup answer = new ToggleGroup();
								RadioButton yes = new RadioButton("Yes");
								yes.setToggleGroup(answer);
								RadioButton no = new RadioButton("No");
								no.setToggleGroup(answer);

								radios.getChildren().addAll(yes, no);

								dialogVbox.add(question, 0, 3);
								dialogVbox.add(radios, 1, 3);

								yes.setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent arg0) {

										Button ADDDepartment = new Button("Submit!");

										dialogVbox.add(ADDDepartment, 1, 4);

										ADDDepartment.setOnAction(new EventHandler<ActionEvent>() {
											@Override
											public void handle(ActionEvent arg0) {
												tree.insertElement(DEPinput.getText());
												int last = file1.toString().lastIndexOf('\\');

												try {
													FileWriter fw = new FileWriter(file1, true);
													fw.write("\n" + DEPinput.getText() + "/" + DEPinput.getText()
															+ ".txt");
													fw.close();
													File myObjey = null;
													try {
														myObjey = new File(file1.toString().substring(0, last) + "/"
																+ DEPinput.getText() + ".txt");
														myObjey.createNewFile();
													} catch (IOException e) {
														System.out.println("An error occurred.");
														e.printStackTrace();
													} finally {
														root1.getChildren().add(createButtonForDepartment(
																DEPinput.getText(), myObjey.toString(), x, y));
													}

												} catch (IOException e) {
												}
												System.out.println(tree.InorderTraversal());
											}
										});
									}
								});

								no.setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent arg0) {

										Button ADDDepartment = new Button("Submit!");

										dialogVbox.add(ADDDepartment, 1, 4);

										ADDDepartment.setOnAction(new EventHandler<ActionEvent>() {
											@Override
											public void handle(ActionEvent arg0) {
												dialog.hide();
											}
										});
									}
								});

							}

						}
					});
					dialogVbox.add(showFlight, 0, 2);
					dialogVbox.add(show, 1, 2);

					Scene dialogScene = new Scene(dialogVbox, 500, 500);
					dialog.setTitle("Search Department");
					dialog.setScene(dialogScene);
					dialog.show();
				}
			});

			Button DeleteDepartment = new Button();
			DeleteDepartment.setTranslateX(50);
			DeleteDepartment.setTranslateY(500);
			DeleteDepartment.setText("Delete Department");
			DeleteDepartment.setStyle("-fx-background-color: #212121; -fx-font-size:21");
			DeleteDepartment.setTextFill(Color.WHITE);
			root2.getChildren().add(DeleteDepartment);

			DeleteDepartment.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					final Stage dialog = new Stage();
					dialog.initModality(Modality.APPLICATION_MODAL);
					dialog.initOwner(primaryStage);

					GridPane dialogVbox = new GridPane();
					dialogVbox.setAlignment(Pos.CENTER);
					dialogVbox.setHgap(5);
					dialogVbox.setVgap(5);
					dialogVbox.setPadding(new Insets(25, 25, 25, 25));
					Label depName = new Label("Enter Department Name to Search: ");
					
					TextField DEPinput = new TextField();

					DEPinput.setPrefColumnCount(14);

					dialogVbox.add(depName, 0, 1);
					dialogVbox.add(DEPinput, 1, 1);
					Button showFlight = new Button("Search");
					Text show = new Text();
					showFlight.setOnAction(new EventHandler<ActionEvent>() {
						@Override
						public void handle(ActionEvent arg0) {
							if (tree.searchElement(DEPinput.getText())) {
								HBox radios = new HBox();
								radios.setPrefWidth(300);
								radios.setSpacing(20);
								radios.setPadding(new Insets(0, 0, 0, 0));

								Label question = new Label("Department Found! Would you like to Delete the Department "
										+ DEPinput.getText() + " and all of its components?");
								ToggleGroup answer = new ToggleGroup();
								RadioButton yes = new RadioButton("Yes");
								yes.setToggleGroup(answer);
								RadioButton no = new RadioButton("No");
								no.setToggleGroup(answer);

								radios.getChildren().addAll(yes, no);

								dialogVbox.add(question, 0, 3);
								dialogVbox.add(radios, 1, 3);

								yes.setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent arg0) {

										Button ADDDepartment = new Button("Submit!");

										dialogVbox.add(ADDDepartment, 1, 4);

										ADDDepartment.setOnAction(new EventHandler<ActionEvent>() {
											@Override
											public void handle(ActionEvent arg0) {
												tree.delete(DEPinput.getText());

												try {
													for (Node child : root1.getChildren()) {
														int start = child.toString().indexOf('\'');

														if (child.toString()
																.substring(start + 1, child.toString().length() - 1)
																.equals(DEPinput.getText())) {
															root1.getChildren().remove(child);
														}

													}
												} catch (ConcurrentModificationException ex) {

												}

												System.out.println(tree.InorderTraversal());
											}
										});
									}
								});
								no.setOnAction(new EventHandler<ActionEvent>() {
									@Override
									public void handle(ActionEvent arg0) {

										Button ADDDepartment = new Button("Submit!");

										dialogVbox.add(ADDDepartment, 1, 4);

										ADDDepartment.setOnAction(new EventHandler<ActionEvent>() {
											@Override
											public void handle(ActionEvent arg0) {
												dialog.hide();
											}
										});
									}
								});
							}

							else {
								show.setText("Sorry, couldn't find a department by the name " + DEPinput.getText());
							}

						}
					});
					dialogVbox.add(showFlight, 0, 2);
					dialogVbox.add(show, 1, 2);

					Scene dialogScene = new Scene(dialogVbox, 500, 500);
					dialog.setTitle("Search Department");
					dialog.setScene(dialogScene);
					dialog.show();
				}
			});

			Button back_2 = new Button();
			back_2.setTranslateX(50);
			back_2.setTranslateY(600);
			back_2.setText("Back");
			back_2.setStyle("-fx-background-color: #212121; -fx-font-size:23; -fx-font-weight:bold");
			back_2.setTextFill(Color.WHITE);
			root2.getChildren().add(back_2);// back button for the flights page

			Button mngCourses = new Button();

			mngCourses.setTranslateX(1000);
			mngCourses.setTranslateY(250);
			mngCourses.setText("Students");
			mngCourses.setStyle("-fx-background-color: #212121; -fx-font-size:23; -fx-font-weight:bold");
			mngCourses.setTextFill(Color.WHITE);

			root.getChildren().add(mngDepartments);
			root.getChildren().add(mngCourses);

			Button back_1 = new Button();
			back_1.setTranslateX(50);
			back_1.setTranslateY(600);
			back_1.setText("Back");
			back_1.setStyle("-fx-background-color: #212121; -fx-font-size:23; -fx-font-weight:bold");
			back_1.setTextFill(Color.WHITE);
			root1.getChildren().add(back_1);

			Scene scene = new Scene(root, 1280, 720);

			Scene DepartmentsScene = new Scene(root2, 1280, 720);

			Scene CoursesScene = new Scene(root1, 1280, 720);

			mngCourses.setOnAction(e -> primaryStage.setScene(CoursesScene));

			mngDepartments.setOnAction(e -> primaryStage.setScene(DepartmentsScene));

			back_1.setOnAction(e -> primaryStage.setScene(scene));

			back_2.setOnAction(e -> primaryStage.setScene(scene));

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (

		Exception e) {
			e.printStackTrace();
		}

	}

	private Button createButtonForDepartment(String name, String source, int x, int y) {
		Button button = new Button(name);
		System.out.println(button.toString());
		
		button.setTranslateX(x);
		button.setTranslateY(y);
		button.setPadding(new Insets(15, 15, 15, 15));
		button.setStyle("-fx-font-size:15; -fx-font-weight:bold");
		button.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				final Stage dialog = new Stage();
				dialog.initModality(Modality.APPLICATION_MODAL);
				dialog.initOwner(primaryStage);

				ScrollPane sp = new ScrollPane();

				GridPane dialogVbox = new GridPane();
				dialogVbox.setAlignment(Pos.CENTER);
				dialogVbox.setHgap(5);
				dialogVbox.setVgap(5);
				dialogVbox.setPadding(new Insets(25, 25, 25, 25));

				File src = new File(source);

				HashMap hash = new HashMap(5);
				Scanner myReader = null;
				
				try {	
					myReader = new Scanner(src);
					while (myReader.hasNextLine()) {
						String line = myReader.nextLine();
						// process the line
						String[] split = line.split("/");
						Student s = new Student(split[0].trim(), Integer.parseInt(split[1].replaceAll("\\s+", "")),
								Double.parseDouble(split[2].replaceAll("\\s+", "")),
								split[3].replaceAll("\\s+", "").charAt(0));
						hash.insert(s.hashCode(), s);
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				} finally {
					myReader.close();
				}

				System.out.println(hash.toStringQuadratic());

				Text print = new Text("Printed HashTable: ");
				Text size = new Text("HashTable size is: ");
				Text funky = new Text("Hashfunction for every Entry:");

				TextArea printyy = new TextArea();
				TextArea funkyy = new TextArea();

				printyy.setText(hash.toStringQuadratic());
				funkyy.setText(hash.toStringHashCode());
				funkyy.setEditable(false);
				printyy.setEditable(false);
				size.setStyle("-fx-font-size:15; -fx-font-weight:bold");
				print.setStyle("-fx-font-size:15; -fx-font-weight:bold");
				funky.setStyle("-fx-font-size:15; -fx-font-weight:bold");

				Text sizeans = new Text(hash.getTableSize() + "");
				sizeans.setStyle("-fx-font-size:15; -fx-font-weight:bold");

				Button inserting = new Button("Insert");
				Button searching = new Button("Search");
				Button Dell = new Button("Delete");

				inserting.setStyle("-fx-font-size:15; -fx-font-weight:bold");
				searching.setStyle("-fx-font-size:15; -fx-font-weight:bold");
				Dell.setStyle("-fx-font-size:15; -fx-font-weight:bold");

				Button backywacky = new Button("Back");

				backywacky.setStyle("-fx-font-size:15; -fx-font-weight:bold");

				Button backywacky1 = new Button("Back");

				backywacky1.setStyle("-fx-font-size:15; -fx-font-weight:bold");

				Button backywacky2 = new Button("Back");

				backywacky2.setStyle("-fx-font-size:15; -fx-font-weight:bold");

				dialogVbox.add(size, 0, 0);
				dialogVbox.add(sizeans, 0, 1);
				dialogVbox.add(print, 1, 0);
				dialogVbox.add(printyy, 1, 1);
				dialogVbox.add(funky, 2, 0);
				dialogVbox.add(funkyy, 2, 1);
				dialogVbox.add(inserting, 0, 2);
//				dialogVbox.add(sizeans, 0, 1);
				dialogVbox.add(searching, 1, 2);
//				dialogVbox.add(printyy, 1, 1);
				dialogVbox.add(Dell, 2, 2);
//				dialogVbox.add(funkyy, 2, 1);

				GridPane root4 = new GridPane();// Insert
				root4.setAlignment(Pos.CENTER);
				root4.setHgap(5);
				root4.setVgap(5);
				root4.setPadding(new Insets(25, 25, 25, 25));

				Label enterID_1 = new Label("Student Name:");
				Label enterName = new Label("Student ID:");
				Label enterAddress = new Label("Average:");
				Label enterMobile = new Label("Gender:");

				TextField NameInput = new TextField();
				TextField IDInput_1 = new TextField();
				TextField AddressInput = new TextField();
				TextField MobileInput = new TextField();

				NameInput.setPrefColumnCount(14);
				IDInput_1.setPrefColumnCount(14);
				AddressInput.setPrefColumnCount(14);
				MobileInput.setPrefColumnCount(14);

				Button Add = new Button("Add");
				Add.setStyle("-fx-font-size:15; -fx-font-weight:bold");

				root4.add(enterID_1, 0, 0);
				root4.add(IDInput_1, 1, 0);
				root4.add(enterName, 0, 1);
				root4.add(NameInput, 1, 1);
				root4.add(enterAddress, 0, 2);
				root4.add(AddressInput, 1, 2);
				root4.add(enterMobile, 0, 3);
				root4.add(MobileInput, 1, 3);
				root4.add(backywacky1, 0, 5);
				root4.add(Add, 0, 4);

				Text fill = new Text("Fill All Values!");
				fill.setStyle("-fx-font-size:15; -fx-font-weight:bold");

				Add.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						if (NameInput.getText().equals("") || IDInput_1.getText().equals("")
								|| AddressInput.getText().equals("") || MobileInput.getText().equals(""))
							root4.add(fill, 1, 5);
						else {
							root4.getChildren().remove(fill);
							Student s = new Student(IDInput_1.getText(), Integer.parseInt(NameInput.getText()),
									Double.parseDouble(AddressInput.getText()), MobileInput.getText().charAt(0));
							hash.insert(s.hashCode(), s);
							printyy.setText(hash.toStringQuadratic());
							funkyy.setText(hash.toStringHashCode());
							System.out.println(hash.toStringFileSave());

							try {
								FileWriter writer = new FileWriter(source, false);
								writer.write(hash.toStringFileSave());
								writer.close();
							} catch (IOException e) {
								e.printStackTrace();
							}

							NameInput.setText("");
							IDInput_1.setText("");
							AddressInput.setText("");
							MobileInput.setText("");

						}
					}
				});

				GridPane root5 = new GridPane();// Search
				root5.setAlignment(Pos.CENTER);
				root5.setHgap(5);
				root5.setVgap(5);
				root5.setPadding(new Insets(25, 25, 25, 25));

				Label enternamee = new Label("Student Name:");

				TextField NameInput1 = new TextField();

				NameInput1.setPrefColumnCount(14);

				Button Adderral = new Button("Search");
				Adderral.setStyle("-fx-font-size:15; -fx-font-weight:bold");

				root5.add(enternamee, 0, 0);
				root5.add(NameInput1, 1, 0);

				root5.add(backywacky2, 0, 2);
				root5.add(Adderral, 0, 1);

				Text fill1 = new Text("Fill Value");
				fill1.setStyle("-fx-font-size:15; -fx-font-weight:bold");

				Text founded = new Text("Student Found!");
				founded.setStyle("-fx-font-size:15; -fx-font-weight:bold");

				Text NOtfounded = new Text("Student Not Found!");
				NOtfounded.setStyle("-fx-font-size:15; -fx-font-weight:bold");

				Adderral.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						if (NameInput1.getText().equals("")) {
							root5.getChildren().remove(NOtfounded);
							root5.getChildren().remove(founded);
							root5.add(fill1, 2, 0);
						} else {
							root5.getChildren().remove(fill1);

							if (hash.search(NameInput1.getText())) {
								root5.getChildren().remove(NOtfounded);
								root5.add(founded, 2, 0);
							} else {
								root5.getChildren().remove(founded);
								root5.add(NOtfounded, 2, 0);
							}
						}
					}
				});

				GridPane root6 = new GridPane();// Delete
				root6.setAlignment(Pos.CENTER);
				root6.setHgap(5);
				root6.setVgap(5);
				root6.setPadding(new Insets(25, 25, 25, 25));

				Label enternamee1 = new Label("Student Name:");

				TextField NameInput2 = new TextField();

				NameInput2.setPrefColumnCount(14);

				Button Xanax = new Button("Delete");
				Xanax.setStyle("-fx-font-size:15; -fx-font-weight:bold");

				root6.add(enternamee1, 0, 0);
				root6.add(NameInput2, 1, 0);

				root6.add(backywacky, 0, 2);
				root6.add(Xanax, 0, 1);

				Text fill2 = new Text("Fill Value");
				fill1.setStyle("-fx-font-size:15; -fx-font-weight:bold");

				Text founded1 = new Text("Successfully Deleted");
				founded.setStyle("-fx-font-size:15; -fx-font-weight:bold");

				Text NOtfounded1 = new Text("Student Not Found");
				NOtfounded.setStyle("-fx-font-size:15; -fx-font-weight:bold");

				Xanax.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent arg0) {
						if (NameInput2.getText().equals("")) {
							root6.getChildren().remove(fill2);
							root6.getChildren().remove(founded1);
							root6.getChildren().remove(NOtfounded1);
							root6.add(fill2, 2, 0);
						} else {
							root6.getChildren().remove(fill2);
							if (hash.search(NameInput2.getText())) {
								hash.remove(NameInput2.getText());

								root6.getChildren().remove(NOtfounded1);
								root6.getChildren().remove(founded1);
								root6.add(founded1, 2, 0);
								printyy.setText(hash.toStringQuadratic());
								funkyy.setText(hash.toStringHashCode());
								try {
									FileWriter writer = new FileWriter(source, false);
									writer.write(hash.toStringFileSave());
									writer.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							} else {
								root6.getChildren().remove(NOtfounded1);
								root6.getChildren().remove(founded1);
								root6.add(NOtfounded1, 2, 0);
							}
						}
					}
				});

				BorderPane borderPane4 = new BorderPane();

				borderPane4.setCenter(root4);

				BorderPane borderPane6 = new BorderPane();

				borderPane6.setCenter(root6);

				BorderPane borderPane5 = new BorderPane();

				borderPane5.setCenter(root5);

				Scene Insert = new Scene(borderPane4, 640, 480);

				Scene Search = new Scene(borderPane5, 640, 480);

				Scene Delete = new Scene(borderPane6, 640, 480);

				BorderPane borderPane = new BorderPane();

				borderPane.setCenter(dialogVbox);
				sp.setContent(borderPane);
				sp.fitToWidthProperty().set(true);
				Scene dialogScene = new Scene(borderPane, 640, 480);

				inserting.setOnAction(e -> dialog.setScene(Insert));

				searching.setOnAction(e -> dialog.setScene(Search));

				Dell.setOnAction(e -> dialog.setScene(Delete));

				backywacky.setOnAction(e -> dialog.setScene(dialogScene));

				backywacky1.setOnAction(e -> dialog.setScene(dialogScene));

				backywacky2.setOnAction(e -> dialog.setScene(dialogScene));

				dialog.setTitle(name);
				dialog.setScene(dialogScene);
				dialog.show();
			}
		});

		return button;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
