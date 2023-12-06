package upn.proyect;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;


import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;

import upn.proyect.entidades.Categoria;

import upn.proyect.repositorio.ICategoriaRepo;
import upn.proyect.repositorio.IProductoRepo;
import upn.proyect.servicio.CategoriaServicio;


@SpringBootTest
class TestServicio1 {
	
	@Autowired
	private ICategoriaRepo categoriaRepo;
	@Autowired
	private IProductoRepo productoRepo;
	@Autowired
	private CategoriaServicio categoriaServicio;
   
	@Test
	void test() {
		
		List<Integer> ids = Arrays.asList(9, 10);
		
		// Crear categorías de ejemplo
        Categoria categoria1 = new Categoria(9, "Categoría 1", "imagen1.jpg", '1');
        Categoria categoria2 = new Categoria(10, "Categoría 2", "imagen2.jpg", '1');
        List<Categoria> categoriasEsperadas = Arrays.asList(categoria1, categoria2);

        // Guardar las categorías de ejemplo en la base de datos
        int idCategoria1 = categoriaServicio.guardarCategoria(categoria1);
        int idCategoria2 = categoriaServicio.guardarCategoria(categoria2);

        // Verificar que se hayan guardado correctamente
        assertTrue(idCategoria1 > 0);
        assertTrue(idCategoria2 > 0);

        // Ejecutar el método que deseas probar
        List<Categoria> resultado = categoriaServicio.buscarPorIDs(ids);
 
        // Verificar el resultado
        assertEquals(categoriasEsperadas.size(), resultado.size()); // Verificar que las listas tengan la misma cantidad de elementos

        for (int i = 0; i < categoriasEsperadas.size(); i++) {
            Categoria categoriaEsperada = categoriasEsperadas.get(i);
            Categoria categoriaObtenida = resultado.get(i);

            assertEquals(categoriaEsperada.getId(), categoriaObtenida.getId());
            assertEquals(categoriaEsperada.getDescripcion(), categoriaObtenida.getDescripcion());
            assertEquals(categoriaEsperada.getImagen(), categoriaObtenida.getImagen());
            assertEquals(categoriaEsperada.getEstado(), categoriaObtenida.getEstado());
        }
        
        System.out.println("Prueba exitosa");
        System.out.println("Categorías obtenidas:");

        for (Categoria categoria : resultado) {
            System.out.println(categoria);
        }
       
	}

}
