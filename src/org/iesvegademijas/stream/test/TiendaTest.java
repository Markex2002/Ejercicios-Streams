package org.iesvegademijas.stream.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;
import static java.util.Comparator.*;

import org.iesvegademijas.hibernate.Fabricante;
import org.iesvegademijas.hibernate.FabricanteHome;
import org.iesvegademijas.hibernate.Producto;
import org.iesvegademijas.hibernate.ProductoHome;
import org.junit.jupiter.api.Test;


class 	TiendaTest {
	
	@Test
	void testSkeletonFrabricante() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
			List<Fabricante> listFab = fabHome.findAll();

			//TODO STREAMS
			
		
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	

	@Test
	void testSkeletonProducto() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS
		
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	@Test
	void testAllFabricante() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
	
			List<Fabricante> listFab = fabHome.findAll();
			assertEquals(9,listFab.size());
		
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	@Test
	void testAllProducto() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
			assertEquals(11,listProd.size());
		
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		

	
	}
	
	/**
	 * 1. Lista los nombres y los precios de todos los productos de la tabla producto
	 */
	@Test
	void test1() {
	
		ProductoHome prodHome = new ProductoHome();
		
		try {
			prodHome.beginTransaction();
			
			List<Producto> listProd = prodHome.findAll();
			
			//TODO STREAMS

			List<String> listaNombrePrecio = listProd.stream().map(p -> "Nombre: " + p.getNombre() + ", Precio: " + p.getPrecio() + "€").collect(Collectors.toList());
			System.out.println(listaNombrePrecio);

			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	
	}
	
	
	/**
	 * 2. Devuelve una lista de Producto completa con el precio de euros convertido a dólares .
	 */
	@Test
	void test2() {
		
		ProductoHome prodHome = new ProductoHome();
		
		try {
			prodHome.beginTransaction();			
			List<Producto> listProd = prodHome.findAll();
			
			//TODO STREAMS

			List<String> listaPrecioDolares = listProd.stream().map
					(p -> "Nombre: " + p.getNombre() + ", precio: " + (p.getPrecio()*0.95)).collect(Collectors.toList());
			//Usando For each para mostrar los Strings
			listaPrecioDolares.forEach(System.out::println);

			
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 3. Lista los nombres y los precios de todos los productos, convirtiendo los nombres a mayúscula.
	 */
	@Test
	void test3() {
		
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS
			List<String> listaNombresPreciosMayus = listProd.stream().map(p -> "Nombre: " + p.getNombre().toUpperCase() + ", precio: " + p.getPrecio()).collect(Collectors.toList());

			listaNombresPreciosMayus.forEach(System.out::println);
			//listaPrecioDolares.forEach(s -> System.out.println(s));

		
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 4. Lista el nombre de todos los fabricantes y a continuación en
	 * mayúsculas los dos primeros caracteres del nombre del fabricante.
	 */
	@Test
	void test4() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
	
			List<Fabricante> listFab = fabHome.findAll();
					
			//TODO STREAMS

			List<String> listaFabricantes = listFab.stream().map(f -> f.getNombre() + " " + f.getNombre().substring(0,2).toUpperCase()).collect(Collectors.toList());

			listaFabricantes.forEach(System.out::println);
			//listaFabricantes.forEach(System.out::println);


					
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 5. Lista el código de los fabricantes que tienen productos.
	 */
	@Test
	void test5() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
	
			List<Fabricante> listFab = fabHome.findAll();
					
			//TODO STREAMS

			List<Fabricante> listFabricanteFiltrada = listFab.stream().filter(f -> !f.getProductos().isEmpty()).collect(Collectors.toList());
			listFabricanteFiltrada.forEach(System.out::println);


			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 6. Lista los nombres de los fabricantes ordenados de forma descendente.
	 */
	@Test
	void test6() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
			List<Fabricante> listFab = fabHome.findAll();
					
			//TODO STREAMS

			listFab.stream().sorted(comparing(Fabricante::getNombre).reversed())
					.map(Fabricante::getNombre).forEach(System.out::println);


			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 7. Lista los nombres de los productos ordenados en primer
	 * lugar por el nombre de forma ascendente y en segundo lugar por el precio de forma descendente.
	 */
	@Test
	void test7() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS

			listProd.stream().sorted(comparing(Producto::getNombre).
					thenComparing(comparing(Producto::getPrecio).reversed())).
					map(p -> p.getNombre() + " | " + p.getPrecio() + "€").forEach(System.out::println);

			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 8. Devuelve una lista con los 5 primeros fabricantes.
	 */
	@Test
	void test8() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
	
			List<Fabricante> listFab = fabHome.findAll();
					
			//TODO STREAMS

			List<Fabricante> listaFabricante = listFab.stream().limit(5).collect(Collectors.toList());
			listaFabricante.forEach(System.out::println);

		
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 9.Devuelve una lista con 2 fabricantes a partir del cuarto fabricante. El cuarto fabricante también se debe incluir en la respuesta.
	 */
	@Test
	void test9() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
	
			List<Fabricante> listFab = fabHome.findAll();
					
			//TODO STREAMS

			//List<Fabricante> listaFabricantes = listFab.stream().skip(3).limit(2).collect(Collectors.toList());
			List<Fabricante> listaFabricantes = listFab.stream().skip(3).limit(2).collect(toList());

			listaFabricantes.forEach(System.out::println);

			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 10. Lista el nombre y el precio del producto más barato
	 */
	@Test
	void test10() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS

			Producto productoMasBarato = new Producto();
			Optional<Producto> prodOptional = listProd.stream().sorted(comparing(Producto::getPrecio)).limit(1).findFirst();
			if (prodOptional.isPresent()){
				productoMasBarato = prodOptional.get();
			}

			System.out.println(productoMasBarato.getNombre() + ", Precio: " + productoMasBarato.getPrecio() + "€");
				
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 11. Lista el nombre y el precio del producto más caro
	 */
	@Test
	void test11() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS
			Producto productoMascaro = new Producto();
			Optional<Producto> prodOptional = listProd.stream().sorted(comparing(Producto::getPrecio).reversed()).limit(1).findFirst();
			if (prodOptional.isPresent()){
				productoMascaro = prodOptional.get();
			}

			System.out.println(productoMascaro.getNombre() + ", Precio: " + productoMascaro.getPrecio() + "€");


			//Otra Forma de hacerlo mas compacta
			listProd.stream().max(comparing(Producto::getPrecio))
					.map(Producto::getNombre)
					.ifPresent(System.out::println);

			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 12. Lista el nombre de todos los productos del fabricante cuyo código de fabricante es igual a 2.
	 * 
	 */
	@Test
	void test12() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS

			List<Producto> productoFiltrado = listProd.stream().filter
					(p -> p.getFabricante().getCodigo().equals(2)).collect(toList());

			productoFiltrado.forEach(System.out::println);

				
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 13. Lista el nombre de los productos que tienen un precio menor o igual a 120€.
	 */
	@Test
	void test13() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS
			//Filtramos los productos por el precio
			List<Producto> productosFiltrados = listProd.stream().filter(p -> p.getPrecio() <= 120).collect(toList());

			//Enseñamos la lista filtrada
			productosFiltrados.forEach(System.out::println);

			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 14. Lista los productos que tienen un precio mayor o igual a 400€.
	 */
	@Test
	void test14() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS
			//Filtramos los productos por el precio
			List<Producto> productosFiltrados = listProd.stream().filter(p -> p.getPrecio() >= 400).collect(toList());

			//Enseñamos la lista filtrada
			productosFiltrados.forEach(System.out::println);
				
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 15. Lista todos los productos que tengan un precio entre 80€ y 300€. 
	 */
	@Test
	void test15() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();	
			
			//TODO STREAMS
			//Filtramos los productos por el precio
			List<Producto> productosFiltrados = listProd.stream().filter
					(p -> (p.getPrecio() >= 80 && p.getPrecio() <= 300)).collect(toList());

			//Enseñamos la lista filtrada
			productosFiltrados.forEach(System.out::println);
				
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 16. Lista todos los productos que tengan un precio mayor que 200€ y que el código de fabricante sea igual a 6.
	 */
	@Test
	void test16() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS
			//Filtramos los productos por el precio
			List<Producto> productosFiltrados = listProd.stream().filter
					(p -> (p.getPrecio() >= 200 && p.getFabricante().getCodigo() == 6)).collect(toList());

			//Enseñamos la lista filtrada
			productosFiltrados.forEach(System.out::println);
				
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 17. Lista todos los productos donde el código de fabricante
	 * sea 1, 3 o 5 utilizando un Set de codigos de fabricantes para filtrar.
	 */
	@Test
	void test17() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
			
			//TODO STREAMS

			final Set <Integer> codigosValidos = new HashSet<>();
			codigosValidos.add(1);
			codigosValidos.add(3);
			codigosValidos.add(5);

			List<Producto> productosFiltrados = listProd.stream().
					filter(p -> codigosValidos.contains(p.getFabricante().getCodigo())).collect(toList());

			productosFiltrados.forEach(System.out::println);





				
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 18. Lista el nombre y el precio de los productos en céntimos.
	 */
	@Test
	void test18() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
			
			//TODO STREAMS
			//Guardar los Strings en un List
			List<String> listaNombrePrecio = listProd.stream()
					.map(p -> p.getNombre() + ", Precio: " + (int)(p.getPrecio() * 100) + "Cents.").collect(toList());
			listaNombrePrecio.forEach(System.out::println);

			//Declarar los Strings directamente usando Lambdas
			//listProd.forEach(p -> System.out.println(p.getNombre() + ", Precio: " + (int)(p.getPrecio() * 100) + "Cents."));
				
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	
	/**
	 * 19. Lista los nombres de los fabricantes cuyo nombre empiece por la letra S
	 */
	@Test
	void test19() {
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
	
			List<Fabricante> listFab = fabHome.findAll();
					
			//TODO STREAMS
			List<Fabricante> listaNombres = listFab.stream()
					.filter(f -> f.getNombre().charAt(0) == 'S').collect(toList());

			listaNombres.forEach(f -> System.out.println(f.getNombre()));


			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 20. Devuelve una lista con los productos que contienen la cadena Portátil en el nombre.
	 */
	@Test
	void test20() {

		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
			
			//TODO STREAMS
			List<Producto> listaPortatiles = listProd.stream()
					.filter(p -> p.getNombre().toUpperCase().contains("PORTÁTIL")).collect(toList());
			listaPortatiles.forEach(System.out::println);


			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 21. Devuelve una lista con el nombre de todos los productos que
	 * contienen la cadena Monitor en el nombre y tienen un precio inferior a 215 €.
	 */
	@Test
	void test21() {
	
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
			List<Producto> listProd = prodHome.findAll();

			//TODO STREAMS
			List<Producto> productosFiltrados = listProd.stream()
					.filter(p -> (p.getNombre().contains("Monitor") && p.getPrecio() <= 215)).collect(toList());

			productosFiltrados.forEach(p -> System.out.println(p.getNombre()));
				
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 22. Lista el nombre y el precio de todos los productos que tengan un precio mayor o igual a 180€. 
	 * Ordene el resultado en primer lugar por el precio (en orden descendente) y en segundo lugar por el nombre (en orden ascendente).
	 */
	@Test
	void test22() {
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
			
			//TODO STREAMS
			//Filtramos y ordenamos los Productos
			List<Producto> productosOrdenadosFiltrados = listProd.stream()
					.filter(p -> p.getPrecio() <= 180)
					.sorted(comparing(Producto::getPrecio).reversed()
							.thenComparing(Producto::getNombre))
					.collect(toList());

			//Los mostramos
			productosOrdenadosFiltrados.forEach(p -> System.out.println(p.getNombre() + ", Precio: " + p.getPrecio()));


			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 23. Devuelve una lista con el nombre del producto, precio y nombre de fabricante de todos los productos de la base de datos. 
	 * Ordene el resultado por el nombre del fabricante, por orden alfabético.
	 */
	@Test
	void test23() {
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
			
			//TODO STREAMS

			List<String> lista = listProd.stream()
					.sorted(comparing(producto -> producto.getFabricante().getNombre()))
					.map(p -> "Nombre: " + p.getNombre() + ", Precio: " + p.getPrecio() + ", Fabricante: " + p.getFabricante().getNombre()).collect(toList());

			lista.forEach(System.out::println);


			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 24. Devuelve el nombre del producto, su precio y el nombre de su fabricante, del producto más caro.
	 */
	@Test
	void test24() {
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
			
			//TODO STREAMS

			listProd.stream().max(comparing(Producto::getPrecio))
					.map(p -> p.getNombre() + ", " + p.getPrecio() + "€, Fabricante: " + p.getFabricante().getNombre())
					.ifPresent(System.out::println);

			
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 25. Devuelve una lista de todos los productos del fabricante Crucial que tengan un precio mayor que 200€.
	 */
	@Test
	void test25() {
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
			
			//TODO STREAMS

			List<Producto> listaCrucial = listProd.stream()
					.filter(p -> (p.getFabricante().getNombre().equalsIgnoreCase("Crucial")) && (p.getPrecio() >= 200))
					.collect(toList());

			listaCrucial.forEach(System.out::println);

			
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 26. Devuelve un listado con todos los productos de los fabricantes Asus, Hewlett-Packard y Seagate
	 */
	@Test
	void test26() {
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();

			//TODO STREAMS

			List<Producto> lista = listProd.stream()
					.filter(p -> p.getFabricante().getNombre().equalsIgnoreCase("Asus") ||
							p.getFabricante().getNombre().equalsIgnoreCase("Hewlett-Packard") ||
							p.getFabricante().getNombre().equalsIgnoreCase("Seagate")).collect(toList());

			lista.forEach(System.out::println);

			
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 27. Devuelve un listado con el nombre de producto, precio y nombre de fabricante, de todos los productos que tengan un precio mayor o igual a 180€. 
	 * Ordene el resultado en primer lugar por el precio (en orden descendente) y en segundo lugar por el nombre.
	 * El listado debe mostrarse en formato tabla. Para ello, procesa las longitudes máximas de los diferentes campos a presentar y compensa mediante la inclusión de espacios en blanco.
	 * La salida debe quedar tabulada como sigue:

Producto                Precio             Fabricante
-----------------------------------------------------
GeForce GTX 1080 Xtreme|611.5500000000001 |Crucial
Portátil Yoga 520      |452.79            |Lenovo
Portátil Ideapd 320    |359.64000000000004|Lenovo
Monitor 27 LED Full HD |199.25190000000003|Asus

	 */		
	@Test
	void test27() {
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
			
			//TODO STREAMS

			List<String> listaProductos = listProd.stream().sorted(comparing(Producto::getPrecio).reversed()
					.thenComparing(Producto::getNombre)).
					filter(p -> p.getPrecio() >= 180)
					.map(p -> p.getNombre() + "\t|" + p.getPrecio() + "\t|" + p.getFabricante().getNombre())
					.collect(toList());

			System.out.println("Producto \t\t Precio \t\t Fabricante");
			System.out.println("------------------------------------------");
			listaProductos.forEach(System.out::println);





			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 28. Devuelve un listado de los nombres fabricantes que existen en la base de datos, junto con los nombres productos que tiene cada uno de ellos. 
	 * El listado deberá mostrar también aquellos fabricantes que no tienen productos asociados. 
	 * SÓLO SE PUEDEN UTILIZAR STREAM, NO PUEDE HABER BUCLES
	 * La salida debe queda como sigue:
Fabricante: Asus

            	Productos:
            	Monitor 27 LED Full HD
            	Monitor 24 LED Full HD

Fabricante: Lenovo

            	Productos:
            	Portátil Ideapd 320
            	Portátil Yoga 520

Fabricante: Hewlett-Packard

            	Productos:
            	Impresora HP Deskjet 3720
            	Impresora HP Laserjet Pro M26nw

Fabricante: Samsung

            	Productos:
            	Disco SSD 1 TB

Fabricante: Seagate

            	Productos:
            	Disco duro SATA3 1TB

Fabricante: Crucial

            	Productos:
            	GeForce GTX 1080 Xtreme
            	Memoria RAM DDR4 8GB

Fabricante: Gigabyte

            	Productos:
            	GeForce GTX 1050Ti

Fabricante: Huawei

            	Productos:


Fabricante: Xiaomi

            	Productos:

	 */
	@Test
	void test28() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
	
			List<Fabricante> listFab = fabHome.findAll();
					
			//TODO STREAMS

			List<String> fabricantes_Productos = listFab.stream()
					.map(f -> "\nFabricante: " + f.getNombre() + "\n\n\tProductos: \n\t" +
							f.getProductos().stream().map(Producto::getNombre).collect(joining("\n\t")))
					.collect(toList());

					fabricantes_Productos.forEach(System.out::println);
								
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}

	//Usamos filter y luego la funcion is Empty despues de GetProductos, para ver que fabricantes están vacios
	/**
	 * 29. Devuelve un listado donde sólo aparezcan aquellos fabricantes que no tienen ningún producto asociado.
	 */
	@Test
	void test29() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
	
			List<Fabricante> listFab = fabHome.findAll();
					
			//TODO STREAMS

			List<Fabricante> listadoFabricante = listFab.stream()
					.filter(f -> f.getProductos().isEmpty())
					.collect(toList());

			listadoFabricante.forEach(System.out::println);

			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}


	//Usamos la función Count para contar los elementos de listProd
	/**
	 * 30. Calcula el número total de productos que hay en la tabla productos. Utiliza la api de stream.
	 */
	@Test
	void test30() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
						
			//TODO STREAMS

			long total = listProd.stream().count();

			System.out.println(total);
			
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}

	//IMPORTANTE cojemos los productos y contamos los fabricantes, pero usamos Distinct para que no se repitan
	/**
	 * 31. Calcula el número de fabricantes con productos, utilizando un stream de Productos.
	 */
	@Test
	void test31() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS

			long total = listProd.stream().map(p -> p.getFabricante()).distinct().count();

			System.out.println("Fabricantes que tienen productos: " + total);
			
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}



	//AQUI LA CLAVE ESTA EN USAR MAPTODOUBLE Y AVERAGE
	/**
	 * 32. Calcula la media del precio de todos los productos
	 */
	@Test
	void test32() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS

			double media = listProd.stream()
					.mapToDouble(Producto::getPrecio)
					.average()
					.orElse(0);

			System.out.println("La media de los productos es de: " + Math.round(media) + "€");

			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}


	//AQUI SOLO HAY QUE USAR MIN
	/**
	 * 33. Calcula el precio más barato de todos los productos. No se puede utilizar ordenación de stream.
	 */
	@Test
	void test33() {
	
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS

			double precioMasBajo = listProd.stream().mapToDouble(Producto::getPrecio).min().orElse(0);

			System.out.println(precioMasBajo);

			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 34. Calcula la suma de los precios de todos los productos.
	 */
	@Test
	void test34() {
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS

			double total = listProd.stream().mapToDouble(Producto::getPrecio).sum();
			System.out.println("Total: " + total + "€");
			
			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 35. Calcula el número de productos que tiene el fabricante Asus.
	 */
	@Test
	void test35() {
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS

			long cantidadProductosAsus = listProd.stream()
					.filter(p -> p.getFabricante().getNombre().equalsIgnoreCase("Asus"))
					.count();

			System.out.println("Cantidad de Productos Asus: " + cantidadProductosAsus);

			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}



	/**
	 * 36. Calcula la media del precio de todos los productos del fabricante Asus.
	 */
	@Test
	void test36() {
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();		
						
			//TODO STREAMS

			double mediaProductosAsus = listProd.stream()
					.filter(p -> p.getFabricante().getNombre().equalsIgnoreCase("Asus"))
					.mapToDouble(Producto::getPrecio)
					.average()
					.orElse(0);

			System.out.println("Media de Productos Asus: " + mediaProductosAsus + "€");

			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	
	/**
	 * 37. Muestra el precio máximo, precio mínimo, precio medio y el número total de productos que tiene el fabricante Crucial.
	 *  Realízalo en 1 solo stream principal. Utiliza reduce con Double[] como "acumulador".
	 */
	@Test
	void test37() {
		
		ProductoHome prodHome = new ProductoHome();	
		try {
			prodHome.beginTransaction();
		
			List<Producto> listProd = prodHome.findAll();
						
			//TODO STREAMS

			//EJEMPLOS
			double lmao = listProd.stream()
					.filter(p -> p.getFabricante().getNombre().equalsIgnoreCase("Crucial"))
					.map(Producto::getPrecio)
					.reduce(0.00, Double::sum);
			double xD = listProd.stream()
					.filter(p -> p.getFabricante().getNombre().equalsIgnoreCase("Crucial"))
					.map(Producto::getPrecio)
					.reduce(Double::min).orElse(0.00);


			//0 es el contador
			//1 es las suma total
			//2 es la media
			//3 es el maximo
			//4 es el minimo

			double[] dolorDeCabeza = listProd.stream()
					.filter(p -> p.getFabricante().getNombre().equalsIgnoreCase("Crucial"))
					.map(Producto::getPrecio).map( p -> new double[]{p, 0, 0, 0, 0})
					.reduce(new double[]{0, 0, 0, 0, 999999999}, (realimentado, p) -> {

						realimentado[0]++;
						realimentado[1]+=p[0];
						realimentado[2] = realimentado[1]/realimentado[0];

						//Maximo
						if (p[0] > realimentado[3]){realimentado[3] = p[0];}
						//Minimo
						if (p[0] < realimentado[4]){realimentado[4] = p[0];}

						return realimentado;
					});


			System.out.println("Maximo: " + dolorDeCabeza[3]);
			System.out.println("Minimo: " + dolorDeCabeza[4]);
			System.out.println("Media: " + dolorDeCabeza[2]);
			System.out.println("Total de elementos: " + dolorDeCabeza[0]);

			prodHome.commitTransaction();
		}
		catch (RuntimeException e) {
			prodHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 38. Muestra el número total de productos que tiene cada uno de los fabricantes. 
	 * El listado también debe incluir los fabricantes que no tienen ningún producto. 
	 * El resultado mostrará dos columnas, una con el nombre del fabricante y otra con el número de productos que tiene. 
	 * Ordene el resultado descendentemente por el número de productos. Utiliza String.format para la alineación de los nombres y las cantidades.
	 * La salida debe queda como sigue:
	 
     Fabricante     #Productos
-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*
           Asus              2
         Lenovo              2
Hewlett-Packard              2
        Samsung              1
        Seagate              1
        Crucial              2
       Gigabyte              1
         Huawei              0
         Xiaomi              0

	 */
	@Test
	void test38() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
				
			List<Fabricante> listFab = fabHome.findAll();
				
			//TODO STREAMS

			List<String> fabricantesProductos = listFab.stream()
					.map(f -> "\t" + f.getNombre() + "\t\t\t\t\t" + (long) f.getProductos().size()).collect(toList());


			System.out.println("\tFabricante \t\t\t\t #Productos");
			System.out.println("-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*--*-*-*-*-*-*-*-*-*-*-*-*-*");
			fabricantesProductos.forEach(System.out::println);


		
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 39. Muestra el precio máximo, precio mínimo y precio medio de los productos de cada uno de los fabricantes. 
	 * El resultado mostrará el nombre del fabricante junto con los datos que se solicitan.
	 * Realízalo en 1 solo stream principal. Utiliza reduce con Double[] como "acumulador".
	 * Deben aparecer los fabricantes que no tienen productos.
	 */
	@Test
	void test39() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
				
			List<Fabricante> listFab = fabHome.findAll();
				
			//TODO STREAMS
		
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 40. Muestra el precio máximo, precio mínimo, precio medio y el número total de
	 * productos de los fabricantes que tienen un precio medio superior a 200€.
	 * No es necesario mostrar el nombre del fabricante, con el código del fabricante es suficiente.
	 */
	@Test
	void test40() {
	
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
				
			List<Fabricante> listFab = fabHome.findAll();
				
			//TODO STREAMS

			List<Fabricante> listFabricantes = listFab.stream()
					.filter(f -> 200.0d < f.getProductos().stream()
							.mapToDouble(Producto::getPrecio)
							.average()
							.orElse(0.0)).collect(toList());

			listFabricantes.forEach(System.out::println);

		
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 41. Devuelve un listado con los nombres de los fabricantes que tienen 2 o más productos.
	 */
	@Test
	void test41() {
		
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
				
			List<Fabricante> listFab = fabHome.findAll();
				
			//TODO STREAMS

			List<Fabricante> lista = listFab.stream().filter(p -> p.getProductos().size() >= 2).collect(toList());

			lista.forEach(System.out::println);

		
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
	}
	
	/**
	 * 42. Devuelve un listado con los nombres de los fabricantes y
	 * el número de productos que tiene cada uno con un precio superior o igual a 220 €.
	 * Ordenado de mayor a menor número de productos.
	 */
	@Test
	void test42() {
		
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
				
			List<Fabricante> listFab = fabHome.findAll();
				
			//TODO STREAMS
		
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 43.Devuelve un listado con los nombres de los fabricantes donde la suma del precio de todos sus productos es superior a 1000 €
	 */
	@Test
	void test43() {
		
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
				
			List<Fabricante> listFab = fabHome.findAll();
				
			//TODO STREAMS
		
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 44. Devuelve un listado con los nombres de los fabricantes donde la suma del precio de todos sus productos es superior a 1000 €
	 * Ordenado de menor a mayor por cuantía de precio de los productos.
	 */
	@Test
	void test44() {
		
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
				
			List<Fabricante> listFab = fabHome.findAll();
				
			//TODO STREAMS
		
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
		
	}
	
	/**
	 * 45. Devuelve un listado con el nombre del producto más caro que tiene cada fabricante. 
	 * El resultado debe tener tres columnas: nombre del producto, precio y nombre del fabricante. 
	 * El resultado tiene que estar ordenado alfabéticamente de menor a mayor por el nombre del fabricante.
	 */
	@Test
	void test45() {
		
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
				
			List<Fabricante> listFab = fabHome.findAll();
				
			//TODO STREAMS
		
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
			
	}
	
	/**
	 * 46. Devuelve un listado de todos los productos que tienen un precio mayor o igual a la media de todos los productos de su mismo fabricante.
	 * Se ordenará por fabricante en orden alfabético ascendente y los productos de cada fabricante tendrán que estar ordenados por precio descendente.
	 */
	@Test
	void test46() {
		
		FabricanteHome fabHome = new FabricanteHome();
		
		try {
			fabHome.beginTransaction();
				
			List<Fabricante> listFab = fabHome.findAll();
				
			//TODO STREAMS															
		
			fabHome.commitTransaction();
		}
		catch (RuntimeException e) {
			fabHome.rollbackTransaction();
		    throw e; // or display error message
		}
			
	}
	
}

