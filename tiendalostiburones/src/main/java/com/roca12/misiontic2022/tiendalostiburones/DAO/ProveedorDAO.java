package com.roca12.misiontic2022.tiendalostiburones.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.roca12.misiontic2022.tiendalostiburones.DTO.ClienteVO;
import com.roca12.misiontic2022.tiendalostiburones.DTO.ProveedorVO;

public class ProveedorDAO {

	public void registrarProveedor(ProveedorVO prov) {
		//llama y crea una instancia de la clase encargada de hacer la conexión
		Conexion conex = new Conexion();

		try {
			//sentencia que se ejecutara en la base de datos
			Statement estatuto = conex.getConnection().createStatement();
			
			//String que contiene la sentencia insert a ejecutar
			String sentencia = "INSERT INTO proveedores VALUES(" 
					+ prov.getNit_proveedor() + "," + "'"
					+ prov.getCiudad_proveedor() + "'," + "'" 
					+ prov.getDireccion_proveedor() + "'," + "'" 
					+ prov.getNombre_proveedor()+ "'," + "'" 
					+ prov.getTelefono_proveedor() + "'" 
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
			System.out.println("No se pudo insertar el Proveedor");
			System.out.println(e.getMessage());
			System.out.println(e.getErrorCode());
		} catch (Exception e) {
			//si hay cualquier otro error mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo insertar el Proveedor");
			System.out.println(e.getMessage());
			System.out.println(e.getLocalizedMessage());
		}

	}


	public ArrayList<ProveedorVO> consultarProveedor(Integer prov) {	
		//lista que contendra el o los Clientes obtenidos
		ArrayList<ProveedorVO> listaProveedores = new ArrayList<ProveedorVO>();		
		//instancia de la conexión
		Conexion conex = new Conexion();
		try {
			//prepare la sentencia en la base de datos
			PreparedStatement consulta = conex.getConnection()
					.prepareStatement("SELECT * FROM proveedores where nit_proveedor = ? ");		
			// se cambia el comodin ? por el dato que ha llegado en el parametro de la funcion
			consulta.setInt(1, prov);			
			//ejecute la sentencia
			ResultSet res = consulta.executeQuery();			
			//cree un objeto basado en la clase entidad con los datos encontrados
			if (res.next()) {
				ProveedorVO newproveedor = new ProveedorVO();
				newproveedor.setNit_proveedor(Integer.parseInt(res.getString("nit_proveedor")));
				newproveedor.setCiudad_proveedor(res.getString("ciudad_proveedor"));
				newproveedor.setDireccion_proveedor(res.getString("direccion_proveedor"));
				newproveedor.setNombre_proveedor(res.getString("nombre_proveedor"));
				newproveedor.setTelefono_proveedor(res.getString("telefono_proveedor"));

				listaProveedores.add(newproveedor);
			}
			//cerrar resultado, sentencia y conexión
			res.close();
			consulta.close();
			conex.desconectar();

		} catch (SQLException e) {
			//si hay un error en el sql mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo consultar el Proveedor");
			System.out.println(e.getMessage());
			System.out.println(e.getErrorCode());
		} catch (Exception e) {
			//si hay cualquier otro error mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo consultar el Proveedor");
			System.out.println(e.getMessage());
			System.out.println(e.getLocalizedMessage());
		}
		return listaProveedores;
	}


	public ArrayList<ProveedorVO> listaDeProveedores() {
		//lista que contendra el o los Clientes obtenidos
		ArrayList<ProveedorVO> listaProveedores = new ArrayList<ProveedorVO>();
		
		//instancia de la conexión
		Conexion conex = new Conexion();

		try {
			//prepare la sentencia en la base de datos
			PreparedStatement consulta = conex.getConnection().prepareStatement("SELECT * FROM proveedores");
			
			//ejecute la sentencia
			ResultSet res = consulta.executeQuery();
			
			//cree un objeto para cada encontrado en la base de datos basado en la clase entidad con los datos encontrados
			while (res.next()) {
				ProveedorVO newproveedor = new ProveedorVO();
				newproveedor.setNit_proveedor(Integer.parseInt(res.getString("nit_proveedor")));
				newproveedor.setCiudad_proveedor(res.getString("ciudad_proveedor"));
				newproveedor.setDireccion_proveedor(res.getString("direccion_proveedor"));
				newproveedor.setNombre_proveedor(res.getString("nombre_proveedor"));
				newproveedor.setTelefono_proveedor(res.getString("telefono_proveedor"));

				listaProveedores.add(newproveedor);
			}
			
			//cerrar resultado, sentencia y conexión
			res.close();
			consulta.close();
			conex.desconectar();

		} catch (SQLException e) {
			//si hay un error en el sql mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo consultar todos los Proveedores");
			System.out.println(e.getMessage());
			System.out.println(e.getErrorCode());
		} catch (Exception e) {
			//si hay cualquier otro error mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo consultar todos los Proveedores");
			System.out.println(e.getMessage());
			System.out.println(e.getLocalizedMessage());
		}

		return listaProveedores;
	}

	public void eliminarProveedor(Integer nit_proveedor) {
		
		//instancia de la conexion
		Conexion conex = new Conexion();

		try {
			//sentencia inicializada
			Statement consulta = conex.getConnection().createStatement();
			
			//preparando sentencia a realizar
			String sentencia = "delete from proveedores where nit_proveedor=" + nit_proveedor + ";";
			
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
			System.out.println("No se pudo eliminar el Proveedor");
			System.out.println(e.getMessage());
			System.out.println(e.getErrorCode());
		} catch (Exception e) {
			//si hay cualquier otro error mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo eliminar el Proveedor");
			System.out.println(e.getMessage());
			System.out.println(e.getLocalizedMessage());
		}

	}

	public void actualizarProveedor(ProveedorVO prov) {
		
		//instancia de conexion
		Conexion conex = new Conexion();

		try {
			//inicializando sentencia
			Statement estatuto = conex.getConnection().createStatement();
			
			//String con la sentencia a ejecutar
			String sentencia = "UPDATE proveedores "
					+ "SET direccion_proveedor = '"+prov.getDireccion_proveedor()+"',"
					+ "ciudad_proveedor = '"+prov.getCiudad_proveedor()+"',"
					+ "nombre_proveedor = '"+prov.getNombre_proveedor()+"',"
					+ "telefono_proveedor = '"+prov.getTelefono_proveedor()+"' "
					+ "WHERE nit_proveedor = "+prov.getNit_proveedor()+";";
			
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
			System.out.println("No se pudo actualizar  el Proveedor");
			System.out.println(e.getMessage());
			System.out.println(e.getErrorCode());
		} catch (Exception e) {
			//si hay cualquier otro error mostrarlo
			System.out.println("------------------- ERROR --------------");
			System.out.println("No se pudo eliminar el Proveedor");
			System.out.println(e.getMessage());
			System.out.println(e.getLocalizedMessage());
		}

	}

}
