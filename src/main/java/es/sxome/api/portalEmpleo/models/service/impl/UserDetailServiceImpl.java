package es.sxome.api.portalEmpleo.models.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import es.sxome.api.portalEmpleo.models.dao.UsuarioDao;
import es.sxome.api.portalEmpleo.models.entity.Perfil;
import es.sxome.api.portalEmpleo.models.entity.Usuario;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioDao usuarioDao;
    
    @Override
    public UserDetails loadUserByUsername(String username){
    	Assert.notNull(username, "El username no puede ser null");
    	
        Usuario usuario = usuarioDao.findByUsernameFetchAll(username);
        
        List<GrantedAuthority> roles = new ArrayList<>();
        for (Perfil perfil : usuario.getPerfiles()) {
        	roles.add(new SimpleGrantedAuthority(perfil.getPerfil()));
		}
        
        UserDetails userDet = new User(usuario.getUsername(),usuario.getPassword(),roles);
        
        return userDet;
    }

}
