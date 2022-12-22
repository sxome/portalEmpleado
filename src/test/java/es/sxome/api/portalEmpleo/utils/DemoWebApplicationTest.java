package es.sxome.api.portalEmpleo.utils;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import es.sxome.api.portalEmpleo.models.dao.UsuarioDao;
import es.sxome.api.portalEmpleo.models.entity.Usuario;

@SpringBootTest
public class DemoWebApplicationTest {

    @Autowired
    private UsuarioDao usuarioDao;
	
    @Autowired
    private BCryptPasswordEncoder encoder;
    
    @Test
	public void crearUsuarioTest() {
		Usuario usuario = new Usuario();
		usuario.setId(10L);
		usuario.setUsername("KIKO");
		usuario.setPassword(encoder.encode("1234"));
		
		Usuario retorno = usuarioDao.save(usuario);
		
		assertTrue(retorno.getPassword().equals(usuario.getPassword()));
	}

}
