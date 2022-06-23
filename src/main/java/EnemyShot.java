/* SPACE INVADERS
   PROGRAMAÇÃO ORIENTADA A OBJETOS
   TURMA 128
   GABRIELA PANTA ZORZO: 20280527-1
   MORGANA LUIZA WEBER: 20103601-9
*/

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class EnemyShot extends BasicElement{
    public EnemyShot(int px,int py){
        super(px,py);
    }

    @Override
    public void start(){
        setDirV(1);
        setSpeed(2);
    }

    @Override
    public void Update(long deltaTime){
        if (jaColidiu()){
          deactivate();
        }else{
            setPosY(getY() + getDirV() * getSpeed());
            // Se chegou na parte superior da tela ...
            if (getY() >= getLMaxV()){
                // Desaparece
                deactivate();
            }
        }
    }

    @Override
    public int getAltura(){
        return 10;
    }

    @Override
    public int getLargura(){
        return 6;
    }

    public void Draw(GraphicsContext graphicsContext){
        graphicsContext.setFill(Paint.valueOf("#FF0000"));
        graphicsContext.fillOval(getX(), getY(), 6, 10);
    }

    @Override
    public void testaColisao(Character outro){
        if (outro instanceof Red || outro instanceof Black || outro instanceof Green || outro instanceof Blue || outro instanceof EnemyShot){
            return;
        }else{
            super.testaColisao(outro);
        }
    }

    
}