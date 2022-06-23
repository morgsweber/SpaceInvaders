/* SPACE INVADERS
   PROGRAMAÇÃO ORIENTADA A OBJETOS
   TURMA 128
   GABRIELA PANTA ZORZO: 20280527-1
   MORGANA LUIZA WEBER: 20103601-9
*/

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Red extends BasicElement {
    private Image image;
    private int RELOAD_TIME = 100000000; // Time is in nanoseconds
    private int shot_timer = 0;

    public Red(int px,int py){
        super(px,py);
        try{
            // Carrega a imagem ajustando a altura para 40 pixels
            // mantendo a proporção em ambas dimensões
            image =  new Image( "Red.png",0,25,true,true );
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    @Override
    public void start(){
        setDirH(1);
        setSpeed(4);
    }

    public void fire(){
        Game.getInstance().addChar(new EnemyShot(getX(),getY()));
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
                setSpeed(Params.getInstance().nextInt(4)+4);
                // Se ainda não chegou perto do chão, desce
                /*if (getY() < 450){ */
                    setPosY(getY()+15);
                //}
            }
        }
        if(deltaTime % 5000 == 0){
            fire();
        }
    }

    public void Draw(GraphicsContext graphicsContext){
        graphicsContext.drawImage(image, getX(),getY());
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
