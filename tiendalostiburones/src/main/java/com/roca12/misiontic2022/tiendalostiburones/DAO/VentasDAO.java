package com.roca12.misiontic2022.tiendalostiburones.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.roca12.misiontic2022.tiendalostiburones.DTO.VentasVO;

public class VentasDAO {
		
		public void registrarVenta(VentasVO codigov) {
			//llama y crea una instancia de la clase encargada de hacer la conexión
			Conexion conex = new Conexion();

			try {
				//sentencia que se ejecutara en la base de datos
				Statement estatuto = conex.getConnection().createStatement();
				
				//String que contiene la sentencia insert a ejecutar
				String sentencia = "INSERT INTO ventas VALUES(" 
						+ codigov.getCodigo_venta() + "," 
						+ codigov.getCedula_cliente() + ","
						+ codigov.getCedula_usuario() + ",'" 
						+ codigov.getIvaventa() + "'," 
						+ codigov.getTotal_venta() + "," 
						+ codigov.getValor_venta() + "" 
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
				System.out.println("No se pudo insertar la venta");
				System.out.println(e.getMessage());
				System.out.println(e.getErrorCode());
			} catch (Exception e) {
				//si hay cualquier otro error mostrarlo
				System.out.println("------------------- ERROR --------------");
				System.out.println("No se pudo insertar la venta");
				System.out.println(e.getMessage());
				System.out.println(e.getLocalizedMessage());
			}

		}


		public ArrayList<VentasVO> consultarVenta(Integer codigov) {	
			//lista que contendra el o los productos obtenidos
			ArrayList<VentasVO> listaventas = new ArrayList<VentasVO>();		
			//instancia de la conexión
			Conexion conex = new Conexion();
			try {
				//prepare la sentencia en la base de datos
				PreparedStatement consulta = conex.getConnection()
						.prepareStatement("SELECT * FROM ventas where codigo_venta = ? ");		
				// se cambia el comodin ? por el dato que ha llegado en el parametro de la funcion
				consulta.setInt(1, codigov);			
				//ejecute la sentencia
				ResultSet res = consulta.executeQuery();			
				//cree un objeto basado en la clase entidad con los datos encontrados
				if (res.next()) {
					VentasVO newventa = new VentasVO();
					newventa.setCodigo_venta(Integer.parseInt(res.getString("Codigo_venta")));
					newventa.setCedula_cliente(Integer.parseInt(res.getString("Cedula_cliente")));
					newventa.setCedula_usuario(Integer.parseInt(res.getString("Cedula_usuario")));
					newventa.setIvaventa(Double.parseDouble(res.getString("Ivaventa")));
					newventa.setTotal_venta(Double.parseDouble(res.getString("Total_venta")));
					newventa.setValor_venta(Double.parseDouble(res.getString("Valor_venta")));

					listaventas.add(newventa);
				}
				//cerrar resultado, sentencia y conexión
				res.close();
				consulta.close();
				conex.desconectar();

			} catch (SQLException e) {
				//si hay un error en el sql mostrarlo
				System.out.println("------------------- ERROR --------------");
				System.out.println("No se pudo consultar la venta");
				System.out.println(e.getMessage());
				System.out.println(e.getErrorCode());
			} catch (Exception e) {
				//si hay cualquier otro error mostrarlo
				System.out.println("------------------- ERROR --------------");
				System.out.println("No se pudo consultar la venta");
				System.out.println(e.getMessage());
				System.out.println(e.getLocalizedMessage());
			}
			return listaventas;
		}


		public ArrayList<VentasVO> listaDeVentas() {
			//lista que contendra el o los productos obtenidos
			ArrayList<VentasVO> listaventas = new ArrayList<VentasVO>();
			
			//instancia de la conexión
			Conexion conex = new Conexion();

			try {
				//prepare la sentencia en la base de datos
				PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM ventas");
				
				//ejecute la sentencia
				ResultSet res = consulta.executeQuery();
				
				//cree un objeto para cada encontrado en la base de datos basado en la clase entidad con los datos encontrados
				while (res.next()) {
					VentasVO newventa = new VentasVO();
					newventa.setCodigo_venta(Integer.parseInt(res.getString("Codigo_venta")));
					newventa.setCedula_cliente(Integer.parseInt(res.getString("Cedula_cliente")));
					newventa.setCedula_usuario(Integer.parseInt(res.getString("Cedula_usuario")));
					newventa.setIvaventa(Double.parseDouble(res.getString("Ivaventa")));
					newventa.setTotal_venta(Double.parseDouble(res.getString("Total_venta")));
					newventa.setValor_venta(Double.parseDouble(res.getString("Valor_venta")));

					listaventas.add(newventa);
				}
				
				//cerrar resultado, sentencia y conexión
				res.close();
				consulta.close();
				conex.desconectar();

			} catch (SQLException e) {
				//si hay un error en el sql mostrarlo
				System.out.println("------------------- ERROR --------------");
				System.out.println("No se pudo consultar todas las ventas");
				System.out.println(e.getMessage());
				System.out.println(e.getErrorCode());
			} catch (Exception e) {
				//si hay cualquier otro error mostrarlo
				System.out.println("------------------- ERROR --------------");
				System.out.println("No se pudo consultar todas las ventas");
				System.out.println(e.getMessage());
				System.out.println(e.getLocalizedMessage());
			}

			return listaventas;
		}

		public void eliminarVenta(Integer codigo_venta) {
			
			//instancia de la conexion
			Conexion conex = new Conexion();

			try {
				//sentencia inicializada
				Statement consulta = conex.getConnection().createStatement();
				
				//preparando sentencia a realizar
				String sentencia = "delete from ventas where codigo_venta=" + codigo_venta + ";";
				
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
				System.out.println("No se pudo eliminar la venta");
				System.out.println(e.getMessage());
				System.out.println(e.getErrorCode());
			} catch (Exception e) {
				//si hay cualquier otro error mostrarlo
				System.out.println("------------------- ERROR --------------");
				System.out.println("No se pudo eliminar la venta");
				System.out.println(e.getMessage());
				System.out.println(e.getLocalizedMessage());
			}

		}

		public void actualizarVenta(VentasVO codigov) {
			
			//instancia de conexion
			Conexion conex = new Conexion();

			try {
				//inicializando sentencia
				Statement estatuto = conex.getConnection().createStatement();
				
				//String con la sentencia a ejecutar
				String sentencia = "UPDATE productos "
						+ "SET Codigo_venta = "+codigov.getCodigo_venta()+","
						+ "Cedula_cliente = '"+codigov.getCedula_cliente()+"',"
						+ "Cedula_usuario = "+codigov.getCedula_usuario()+","
						+ "Ivaventa = "+codigov.getIvaventa()+","
						+ "Total_venta = "+codigov.getTotal_venta()+""
						+ "WHERE Valor_venta = "+codigov.getValor_venta()+";";
				
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
				System.out.println("No se pudo actualizar  la venta");
				System.out.println(e.getMessage());
				System.out.println(e.getErrorCode());
			} catch (Exception e) {
				//si hay cualquier otro error mostrarlo
				System.out.println("------------------- ERROR --------------");
				System.out.println("No se pudo eliminar la venta");
				System.out.println(e.getMessage());
				System.out.println(e.getLocalizedMessage());
			}

		}
		
		
		/**public void eliminarTodoProducto() {
			
			//instancia de la conexion
			Conexion conex = new Conexion();

			try {
				//sentencia inicializada
				Statement consulta = conex.getConnection().createStatement();
				
				//preparando sentencia a realizar
				String sentencia = "delete from productos;";
				
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
				System.out.println("No se pudo eliminar todos los productos");
				System.out.println(e.getMessage());
				System.out.println(e.getErrorCode());
			} catch (Exception e) {
				//si hay cualquier otro error mostrarlo
				System.out.println("------------------- ERROR --------------");
				System.out.println("No se pudo eliminar todos los productos");
				System.out.println(e.getMessage());
				System.out.println(e.getLocalizedMessage());
			}

		}*/

}
	


