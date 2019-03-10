import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
	
	ImageView imv;
	Label label1;
	String file="";

	GridPane gp;
  Scene scene;
  Task<Void> task1;
  
  @Override
  public void start(Stage primarystage) {
	  gp=new GridPane();
	  gp.setPadding(new Insets(0, 10, 10, 10));
	  gp.setAlignment(Pos.CENTER);
	  gp.setVgap(0);
	  gp.setHgap(5);
	  
	  monitor();
	  
	  label1 = new Label();
	  label1.setGraphic(imv);	 
	
	  gp.add(label1, 5, 0, 1, 1);	  
	 
	  scene=new Scene(gp, 450, 300, Color.TRANSPARENT);

	  primarystage.setScene(scene);
	  primarystage.initStyle(StageStyle.TRANSPARENT);
	  primarystage.setTitle("WINDOW");
	  primarystage.show();
	  new Thread(task1).start();
  }
  
  public void monitor() {	  
	  task1=new Task<Void>() {
		  @Override
		  protected Void call() {
			  System.out.println("run called");
			  int i=1;
			  while(true) {
				  System.out.println("inside while");
				  try {
					  Thread.sleep(1000);
					  task1.messageProperty().addListener((observable, oldValue, newValue) -> {
						  Image image = new Image(getClass().getResourceAsStream("images/"+newValue+".png"));
						  imv.setImage(image);
						  label1.setGraphic(imv);
						});
				  }
				  catch(Exception e) {
					  
				  }				  
			  }
		 }
	  };
  } 
 
  
  public static void main(String[] args) {
	    launch(args);
  }
}
