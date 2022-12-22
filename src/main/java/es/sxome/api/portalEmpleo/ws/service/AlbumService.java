package es.sxome.api.portalEmpleo.ws.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import es.sxome.api.portalEmpleo.ws.entity.Album;

@Service
public class AlbumService {

    @Autowired
    RestTemplate restTemplate;
    
    public List<ResponseEntity<Album[]>> mostrarAlbumns(){
        ResponseEntity<Album[]> albums =
                  restTemplate.getForEntity(
                		  "https://jsonplaceholder.typicode.com/users/1/albums/",
                  Album[].class);
    List<ResponseEntity<Album[]>> m = Arrays.asList(albums);
        return  m;
    }
  
    public List<Album> getAlbums(){
        ResponseEntity<Album[]> response =
                  restTemplate.getForEntity(
                  "https://jsonplaceholder.typicode.com/users/1/albums/",
                  Album[].class);
                Album[] albums = response.getBody();
                List<Album> m = Arrays.asList(albums);
        return  m;
    }
    
}
