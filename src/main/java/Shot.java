/* SPACE INVADERS
   PROGRAMAÇÃO ORIENTADA A OBJETOS
   TURMA 128
   GABRIELA PANTA ZORZO: 20280527-1
   MORGANA LUIZA WEBER: 20103601-9
*/

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

/**
 * Represents a shot that crosses the screen from bottom to up and then dismiss
 * @author Bernardo Copstein and Rafael Copstein
 */
public abstract class Shot extends BasicElement{
    public Shot(int px,int py){
        super(px,py);
    }

    @Override
    public void testaColisao(Character outro){
        if (colidiu){
            return;
        }
        // Monta pontos
        int p1x = this.getX();
        int p1y = this.getY();
        int p2x = p1x+this.getLargura();
        int p2y = p1y+this.getAltura();

        int op1x = outro.getX();
        int op1y = outro.getY();
        int op2x = op1x+outro.getLargura();
        int op2y = op1y+outro.getAltura();

        // Verifica colisão
        if (p1x < op2x && p2x > op1x && p1y < op2y && p2y > op1y){
            colidiu = true;
        }

    }

    @Override
    public abstract void Draw(GraphicsContext graphicsContext);
}
