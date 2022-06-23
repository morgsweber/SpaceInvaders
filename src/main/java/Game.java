/* SPACE INVADERS
   PROGRAMAÇÃO ORIENTADA A OBJETOS
   TURMA 128
   GABRIELA PANTA ZORZO: 20280527-1
   MORGANA LUIZA WEBER: 20103601-9
*/

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import java.util.List;
import java.util.LinkedList;

/**
 * Handles the game lifecycle and behavior
 * @author Bernardo Copstein and Rafael Copstein
 */
public class Game {
    private static Game game = null;
    private Canhao canhao;
    private List<Character> activeChars;
    private boolean gameOver;
    private int pontos = 0;
    private int level = 1;
    private int vida = 3;
    private boolean venceu = false;

    private Game(){
        gameOver = false;
        pontos = 0;
    }

    public void setGameOver(){
        gameOver = true;
    }

    public int getLevel(){
        return level;
    }

    public boolean getVenceu(){
        return venceu;
    }

    public boolean isGameOver(){
        return gameOver;
    }

    public int getLife(){
        return vida;
    }

    public int getPontos(){
        return pontos;
    }

    public void incPontos(){
        pontos++;
    }

    public static Game getInstance(){
        if (game == null){
            game = new Game();
        }
        return(game);
    }

    public void addChar(Character c){
        activeChars.add(c);
        c.start();
    }

    public void eliminate(Character c){
        activeChars.remove(c);
        testLevel();
    }

    public void testLevel(){
        int active = 0;
        switch(level){
        case 1:
                for(Character c:activeChars){
                    if(c instanceof Green){
                        active++;
                    }
                }
                break;
        case 2:
                for(Character c:activeChars){
                    if(c instanceof Black){
                        active++;
                    }
                }
                break;
        case 3:
                for(Character c:activeChars){
                    if(c instanceof Blue){
                        active++;
                    }
                }
                break;
        case 4:
                for(Character c:activeChars){
                    if(c instanceof Red){
                        active++;
                    }
                }
                break;
        }
        if (active == 0){
            level++;
            if(level == 5){
                venceu = true;
            }
            activateEnemy(level);
        }
    }

    public void activateEnemy(int level){
        switch (level){
            case 1:
                    // Adiciona Green
                    for(int i=0; i<5; i++){
                        Green g = new Green(100+(i*60),60+i*40);
                        activeChars.add(g);
                        g.start();
                    }
                    break;
            case 2:
                    // Adiciona Black
                    for(int i=0; i<6; i++){
                        Black b = new Black(100+(i*60),60+i*40);
                        activeChars.add(b);
                        b.start();
                    }
                    break;
            case 3:
                    // Adiciona Blue
                    for(int i=0; i<7; i++){
                        Blue bl = new Blue(100+(i*60),60+i*40);
                        activeChars.add(bl);
                        bl.start();
                    }
                    break;
            case 4:
                    // Adiciona Red
                    for(int i=0; i<5; i++){
                        Red r = new Red(100+(i*60),60+i*40);
                        activeChars.add(r);
                        r.start();
                    }
                    break;
        } 
    }

    public void Start() {
        // Repositório de personagens
        activeChars = new LinkedList<>();

        // Adiciona o canhao
        canhao = new Canhao(400,550);
        activeChars.add(canhao);

        activateEnemy(level);

        canhao.start();
    }

    public void Update(long currentTime, long deltaTime) {
        if (gameOver){
            vida--;
            if(vida == 0){
                return;
            }
            else{
                canhao.resetColidiu();
                gameOver = false;
            }
        }

        for(int i=0;i<activeChars.size();i++){
            Character este = activeChars.get(i);
            este.Update(deltaTime);
            for(int j =0; j<activeChars.size();j++){
                Character outro = activeChars.get(j);
                if (este != outro){
                    este.testaColisao(outro);
                }
            }
            if(este.getY() >= Params.WINDOW_HEIGHT && !(este instanceof EnemyShot)){
                vida--;
                eliminate(este);
                if(vida == 0)
                    gameOver = true;
            }
        }
    }

    public void OnInput(KeyCode keyCode, boolean isPressed) {
        canhao.OnInput(keyCode, isPressed);
    }

    public void Draw(GraphicsContext graphicsContext) {
        for(Character c:activeChars){
            c.Draw(graphicsContext);
        }
    }
}
