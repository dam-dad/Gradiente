package dad.javafx.gradiente;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class App extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		Rectangle rectangle = new Rectangle(200, 120);
		Label label = new Label();
		Slider slider = new Slider(0, 1, 0);
		
		label.textProperty().bind(slider.valueProperty().asString("%.2f"));
		
		VBox root = new VBox(5);
		root.setPadding(new Insets(5));
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(rectangle, label, slider);
		
		primaryStage.setTitle("Gradiente");
		primaryStage.setScene(new Scene(root, 320, 200));
		primaryStage.show();
		
		slider.valueProperty().addListener((o, ov, nv) -> {
			Color color = interpolate(Color.RED, Color.YELLOW, Color.GREEN, nv.doubleValue());
			String web = color.toString().replace("0x", "#");
			rectangle.setStyle("-fx-fill:" + web + ";");
		});
		slider.setValue(1);
	
//		Region r = progressBar.lookup("bar");
//		r.setStyle(value);
		
	}
	
	public Color interpolate(Color color1, Color color2, double percent) {
		double red = color1.getRed() + percent * (color2.getRed() - color1.getRed());
		double green = color1.getGreen() + percent * (color2.getGreen() - color1.getGreen());
		double blue = color1.getBlue() + percent * (color2.getBlue() - color1.getBlue());
		return Color.color(Math.abs(red), Math.abs(green), Math.abs(blue));
	}

	public Color interpolate(Color color1, Color color2, Color color3, double percent) {
		if (percent <= 0.5) {
			return interpolate(color1, color2, percent * 2);
		} else {
			return interpolate(color2, color3, (percent - 0.5) * 2);
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
