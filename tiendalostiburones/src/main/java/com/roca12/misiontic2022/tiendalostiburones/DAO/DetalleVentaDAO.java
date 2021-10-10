package com.roca12.misiontic2022.tiendalostiburones.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.roca12.misiontic2022.tiendalostiburones.DTO.DetalleVentaVO;


public class DetalleVentaDAO {
	public void registrarDetalleVenta(DetalleVentaVO dvent) {
		//llama y crea una instancia de la clase encargada de hacer la conexión
		Conexion conex = new Conexion();

		try {
			//sentencia que se ejecutara en la base de datos
			Statement estatuto = conex.getConnection().createStatement();
			
			//String que contiene la sentencia insert a ejecutar
			String sentencia = "INSERT INTO detalleventas VALUES(" 
					+ dvent.getCodigo_detalle_venta() + "," 
					+ dvent.getCantidad_producto() + ","
					+ dvent.getCodigo_producto() + ",'" 
					+ dvent.getCodigo_venta()+ "'," 
					+ dvent.getValor_total() + ","
					+ dvent.getValor_venta() + ","
					+ dvent.getValor_iva() + "" 
					+ ");";
			
			//se ejecuta la sentencia en la base de datos
			estatuto.executeUpdate(sentencia);
			//impresión en consola para verificación 
			System.out.println("Registrado " + sentencia);
			//cerrando la sentencia y la conexión
			estatuto.close();
			conex.desconectar();

		} catch (SQLException e) {
			//si hay un error en el sql mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo insertar el detalle de la venta");
			System.out.println(e.getMessage());
			System.out.println(e.getErrorCode());
		} catch (Exception e) {
			//si hay cualquier otro error mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo insertar el detalle de la venta");
			System.out.println(e.getMessage());
			System.out.println(e.getLocalizedMessage());
		}

	}


	public ArrayList<DetalleVentaVO> consultarDetalleVenta(Integer dvent) {	
		//lista que contendra el detalle de las ventas
		ArrayList<DetalleVentaVO> listaDetalleVenta = new ArrayList<DetalleVentaVO>();		
		//instancia de la conexión
		Conexion conex = new Conexion();
		try {
			//prepare la sentencia en la base de datos
			PreparedStatement consulta = conex.getConnection()
					.prepareStatement("SELECT * FROM detalleventas where codigo_detalle_venta = ? ");		
			// se cambia el comodin ? por el dato que ha llegado en el parametro de la funcion
			consulta.setInt(1, dvent);			
			//ejecute la sentencia
			ResultSet res = consulta.executeQuery();			
			//cree un objeto basado en la clase entidad con los datos encontrados
			if (res.next()) {
				DetalleVentaVO newdetalleventa = new DetalleVentaVO();
				newdetalleventa.setCodigo_detalle_venta(Integer.parseInt(res.getString("codigo_detalle_venta")));
				newdetalleventa.setCantidad_producto(Integer.parseInt(res.getString("cantidad_producto")));
				newdetalleventa.setCodigo_producto(Integer.parseInt(res.getString("codigo_producto")));
				newdetalleventa.setCodigo_venta(Integer.parseInt(res.getString("codigo_venta")));
				newdetalleventa.setValor_total(Double.parseDouble(res.getString("valor_total")));
				newdetalleventa.setValor_venta(Double.parseDouble(res.getString("valor_venta")));
				newdetalleventa.setValor_iva(Double.parseDouble(res.getString("valor_iva")));
				
				listaDetalleVenta.add(newdetalleventa);
			}
			//cerrar resultado, sentencia y conexión
			res.close();
			consulta.close();
			conex.desconectar();

		} catch (SQLException e) {
			//si hay un error en el sql mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo consultar la venta detallada");
			System.out.println(e.getMessage());
			System.out.println(e.getErrorCode());
		} catch (Exception e) {
			//si hay cualquier otro error mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo consultar la venta detallada");
			System.out.println(e.getMessage());
			System.out.println(e.getLocalizedMessage());
		}
		return listaDetalleVenta;
	}


	public ArrayList<DetalleVentaVO> listaDeDetalleVenta() {
		//lista que contendra el o los productos obtenidos
		ArrayList<DetalleVentaVO> listaDetalleVenta = new ArrayList<DetalleVentaVO>();
		
		//instancia de la conexión
		Conexion conex = new Conexion();

		try {
			//prepare la sentencia en la base de datos
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM detalleventas");
			
			//ejecute la sentencia
			ResultSet res = consulta.executeQuery();
			
			//cree un objeto para cada encontrado en la base de datos basado en la clase entidad con los datos encontrados
			while (res.next()) {
				DetalleVentaVO newdetalleventa = new DetalleVentaVO();
				newdetalleventa.setCodigo_detalle_venta(Integer.parseInt(res.getString("codigo_detalle_venta")));
				newdetalleventa.setCantidad_producto(Integer.parseInt(res.getString("cantidad_producto")));
				newdetalleventa.setCodigo_producto(Integer.parseInt(res.getString("codigo_producto")));
				newdetalleventa.setCodigo_venta(Integer.parseInt(res.getString("codigo_venta")));
				newdetalleventa.setValor_total(Double.parseDouble(res.getString("valor_total")));
				newdetalleventa.setValor_venta(Double.parseDouble(res.getString("valor_venta")));
				newdetalleventa.setValor_iva(Double.parseDouble(res.getString("valor_iva")));

				listaDetalleVenta.add(newdetalleventa);;
			}
			
			//cerrar resultado, sentencia y conexión
			res.close();
			consulta.close();
			conex.desconectar();

		} catch (SQLException e) {
			//si hay un error en el sql mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo consultar todas las ventas detalladas");
			System.out.println(e.getMessage());
			System.out.println(e.getErrorCode());
		} catch (Exception e) {
			//si hay cualquier otro error mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo consultar todas las ventas detalladas");
			System.out.println(e.getMessage());
			System.out.println(e.getLocalizedMessage());
		}

		return listaDetalleVenta;
	}

	public void eliminarDetalleVenta(Integer codigo_detalle_venta) {
		
		//instancia de la conexion
		Conexion conex = new Conexion();

		try {
			//sentencia inicializada
			Statement consulta = conex.getConnection().createStatement();
			
			//preparando sentencia a realizar
			String sentencia = "delete from detalleventas where codigo_detalle_venta=" + codigo_detalle_venta + ";";
			
			//impresion de verificación
			System.out.println("Registrado " + sentencia);
			
			//ejecutando la sentencia en la base de datos
			consulta.execute(sentencia);
			
			//cerrando sentencia y conexión
			consulta.close();
			conex.desconectar();

		} catch (SQLException e) {
			//si hay un error en el sql mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo eliminar la venta detallada");
			System.out.println(e.getMessage());
			System.out.println(e.getErrorCode());
		} catch (Exception e) {
			//si hay cualquier otro error mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo eliminar la venta detallada");
			System.out.println(e.getMessage());
			System.out.println(e.getLocalizedMessage());
		}

	}

	public void actualizarDetalleVenta(DetalleVentaVO dvent) {
		
		//instancia de conexion
		Conexion conex = new Conexion();

		try {
			//inicializando sentencia
			Statement estatuto = conex.getConnection().createStatement();
			
			//String con la sentencia a ejecutar
			String sentencia = "UPDATE detalleventas "
					+ "SET codigo_venta = "+dvent.getCodigo_venta()+","
					+ "cantidad_producto = '"+dvent.getCantidad_producto()+"',"
					+ "valor_venta = "+dvent.getValor_venta()+","
					+ "WHERE codigo_detalle_venta = "+dvent.getCodigo_detalle_venta()+";";
			
			//ejecuta la sentencia 
			estatuto.executeUpdate(sentencia);
			
			//verificación por consola de la sentencia
			System.out.println("Registrado " + sentencia);
			
			//cerrando sentencia y conexión
			estatuto.close();
			conex.desconectar();

		} catch (SQLException e) {
			//si hay un error en el sql mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo actualizar la venta detallada");
			System.out.println(e.getMessage());
			System.out.println(e.getErrorCode());
		} catch (Exception e) {
			//si hay cualquier otro error mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo eliminar la venta detallada");
			System.out.println(e.getMessage());
			System.out.println(e.getLocalizedMessage());
		}

	}
	
	
	public void eliminarTodoDetalleVenta() {
		
		//instancia de la conexion
		Conexion conex = new Conexion();

		try {
			//sentencia inicializada
			Statement consulta = conex.getConnection().createStatement();
			
			//preparando sentencia a realizar
			String sentencia = "delete from detalleventas;";
			
			//impresion de verificación
			System.out.println("Registrado " + sentencia);
			
			//ejecutando la sentencia en la base de datos
			consulta.execute(sentencia);
			
			//cerrando sentencia y conexión
			consulta.close();
			conex.desconectar();

		} catch (SQLException e) {
			//si hay un error en el sql mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo eliminar todas las ventas detalladas");
			System.out.println(e.getMessage());
			System.out.println(e.getErrorCode());
		} catch (Exception e) {
			//si hay cualquier otro error mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo eliminar todas las ventas detalladas");
			System.out.println(e.getMessage());
			System.out.println(e.getLocalizedMessage());
		}

	}
	
	
	

}
