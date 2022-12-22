package es.sxome.api.portalEmpleo.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import es.sxome.api.portalEmpleo.models.dao.UsuarioDao;
import es.sxome.api.portalEmpleo.models.dto.UsuarioDto;
import es.sxome.api.portalEmpleo.models.entity.Usuario;
import es.sxome.api.portalEmpleo.models.enums.TipoEstatusUsuario;
import es.sxome.api.portalEmpleo.models.service.UsuarioService;

@ExtendWith(MockitoExtension.class)
public class UsuarioControllerTest {

    @InjectMocks
    private UsuarioController usuarioController;
    
	@Mock
	private UsuarioService usuarioService;
	
	@Mock
	private UsuarioDao usuarioDao;

	private ResponseEntity<UsuarioDto> responseOk;
	private ResponseEntity<UsuarioDto> responseNoContent;
	private Usuario usuario;
	private UsuarioDto usuarioDto;
	
	@BeforeEach
	void setUp() throws Exception {
		
		usuario = new Usuario();
		usuario.setId(1L);
		usuario.setUsername("JUANIN");
		usuario.setNombre("JUAN");
		usuario.setPassword("1234");
		usuario.setFechaRegistro(new Date());
		usuario.setEstatus(TipoEstatusUsuario.DESEMPLEADO);
		usuario.setEmail("correo@correo");
		
		usuarioDto = UsuarioDto.create(usuario);
		
		responseOk = new ResponseEntity<UsuarioDto>(usuarioDto,HttpStatus.OK);
		responseNoContent = new ResponseEntity<UsuarioDto>(usuarioDto,HttpStatus.NO_CONTENT);
	}

	@Test
	public void testBuscarUsuarioPorIdSiExiste() {
		
		when(usuarioService.obtenerUsuarioFetchAll(anyLong())).thenReturn(usuario);
		
		assertEquals(usuarioController.mostrar(usuario.getId()).getStatusCode(), responseOk.getStatusCode());
	}

	@Test
	public void testBuscarUsuarioPorIdSiNoExiste() {
		
		when(usuarioService.obtenerUsuarioFetchAll(anyLong())).thenReturn(null);
		
		assertEquals(usuarioController.mostrar(usuario.getId()).getStatusCode(), responseNoContent.getStatusCode());
	}

}
