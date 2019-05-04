package SistemaGestorActivos.Presentation.Login;

import SistemaGestorActivos.Logic.Usuario;

public class LoginModel {
    Usuario current;

    public LoginModel() {
        current = new Usuario();
    }
    
    public Usuario getCurrent() {
        return current;
    }

    public void setCurrent(Usuario current) {
        this.current = current;
    }
    
}
