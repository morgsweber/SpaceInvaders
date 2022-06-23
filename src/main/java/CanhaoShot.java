/* SPACE INVADERS
   PROGRAMAÇÃO ORIENTADA A OBJETOS
   TURMA 128
   GABRIELA PANTA ZORZO: 20280527-1
   MORGANA LUIZA WEBER: 20103601-9
*/

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class CanhaoShot extends BasicElement{
    public CanhaoShot(int px,int py){
        super(px,py);
    }

    @Override
    public void start(){
        setDirV(-1);
        setSpeed(3);
    }

    @Override
    public void Update(long deltaTime){
        if (jaColidiu()){
            deactivate();
        }else{
            setPosY(getY() + getDirV() * getSpeed());
            // Se chegou na parte superior da tela ...
            if (getY() <= getLMinV()){
                // Desaparece
                deactivate();
            }
        }
    }

    @Override
    public void testaColisao(Character outro){
        // Não verifica colisão de um tiro com outro tiro
        if (outro instanceof CanhaoShot){
            return;
        }else{
            super.testaColisao(outro);
        }
    }

    @Override
    public int getAltura(){
        return 16;
    }

    @Override
    public int getLargura(){
        return 8;
    }

    public void Draw(GraphicsContext graphicsContext){
        graphicsContext.setFill(Paint.valueOf("#00FF00"));
        graphicsContext.fillOval(getX(), getY(), 8, 16);
    }
}