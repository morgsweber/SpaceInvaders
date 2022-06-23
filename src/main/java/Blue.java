/* SPACE INVADERS
   PROGRAMAÇÃO ORIENTADA A OBJETOS
   TURMA 128
   GABRIELA PANTA ZORZO: 20280527-1
   MORGANA LUIZA WEBER: 20103601-9
*/

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Blue extends BasicElement {
    private Image image;
    private Image image2;

    public Blue(int px,int py){
        super(px,py);
        try{
            // Carrega a imagem ajustando a altura para 40 pixels
            // mantendo a proporção em ambas dimensões
            image =  new Image( "Blue.png",0,25,true,true );
            image2 =  new Image( "Blue.png",0,25,true,true );
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void start(){
        setDirH(1);
        setSpeed(3);
    }

    @Override
    public void Update(long deltaTime){
        if (jaColidiu()){
            Game.getInstance().incPontos();
            deactivate();
        }else{
            setPosX(getX() + getDirH() * getSpeed());
            // Se chegou no lado direito da tela ...
            if (getX() >= getLMaxH() || getX() < getLMinH()){
                // Inverte a direção
                setDirH(getDirH()*-1);
                // Sorteia o passo de avanço [1,5]
                setSpeed(Params.getInstance().nextInt(3)+3);
                // Se ainda não chegou perto do chão, desce
                /*if (getY() < 450){ */
                    setPosY(getY()+25);
                //}
            }
        }
    }

    public void Draw(GraphicsContext graphicsContext){
        graphicsContext.drawImage(image, getX(),getY());
        graphicsContext.drawImage(image2, getX()+15,getY()+15);
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
