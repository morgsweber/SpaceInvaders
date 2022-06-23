/* SPACE INVADERS
   PROGRAMAÇÃO ORIENTADA A OBJETOS
   TURMA 128
   GABRIELA PANTA ZORZO: 20280527-1
   MORGANA LUIZA WEBER: 20103601-9
*/

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.nio.file.*;
import java.io.PrintWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Handles window initialization and primary game setup
 * @author Bernardo Copstein, Rafael Copstein
 */

public class Main extends Application {
    private Score score = new Score();

    @Override
    public void start(Stage stage) throws Exception {
        
        // Initialize Window
        stage.setTitle(Params.WINDOW_TITLE);
        stage.setResizable(false);

        Group root = new Group();
        Scene scene = new Scene( root );
        stage.setScene( scene );

        Canvas canvas = new Canvas(Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT );

        root.getChildren().add( canvas );

        // Setup Game object
        Game.getInstance().Start();

        // Register User Input Handler
        scene.setOnKeyPressed((KeyEvent event) -> {
            Game.getInstance().OnInput(event.getCode(), true);
        });

        scene.setOnKeyReleased((KeyEvent event) -> {
            Game.getInstance().OnInput(event.getCode(), false);
        });

        // Register Game Loop
        final GraphicsContext gc = canvas.getGraphicsContext2D();

        new AnimationTimer()
        {
            long lastNanoTime = System.nanoTime();

            @Override
            public void handle(long currentNanoTime)
            {
                long deltaTime = currentNanoTime - lastNanoTime;

                Game.getInstance().Update(currentNanoTime, deltaTime);
                gc.clearRect(0, 0, Params.WINDOW_WIDTH, Params.WINDOW_HEIGHT);
                gc.fillText("Pontos: "+Game.getInstance().getPontos(), 10, 10);
                gc.fillText("Vidas: "+Game.getInstance().getLife(), 10, 30);
                Game.getInstance().Draw(gc);
                if (Game.getInstance().isGameOver() && (Game.getInstance().getLife() == 0)){  
                    gc.fillText("Game Over! ",10, 50);
                    gc.fillText("Score final: " + Game.getInstance().getPontos(),10, 70);
                    score.update(Game.getInstance().getPontos());
                    gc.fillText(score.imprime(), 10, 90);
                    stop();                
                }
                if(Game.getInstance().getVenceu()){
                    gc.fillText("Você venceu! ",10, 50);
                    gc.fillText("Score final: " + Game.getInstance().getPontos(),10, 70);
                    score.update(Game.getInstance().getPontos());
                    gc.fillText(score.imprime(), 10, 90);
                    Game.getInstance().setGameOver();
                    stop();
                }
                lastNanoTime = currentNanoTime;
            }

        }.start();

        // Show window
        stage.show();
    }

    public static void main(String args[]) {
        launch();
    }
}
