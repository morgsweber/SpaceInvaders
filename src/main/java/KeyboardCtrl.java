/* SPACE INVADERS
   PROGRAMAÇÃO ORIENTADA A OBJETOS
   TURMA 128
   GABRIELA PANTA ZORZO: 20280527-1
   MORGANA LUIZA WEBER: 20103601-9
*/

import javafx.scene.input.KeyCode;

/**
 * Represents the basic game character
 * @author Bernardo Copstein and Rafael Copstein
 */
public interface KeyboardCtrl {
    void OnInput(KeyCode keyCode, boolean isPressed);
}
