package com.roca12.misiontic2022.tiendalostiburones.BO;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.roca12.misiontic2022.tiendalostiburones.DAO.DetalleVentaDAO;
import com.roca12.misiontic2022.tiendalostiburones.DTO.DetalleVentaVO;

@RestController
public class DetalleVentaController {

	/*
	 * @GetMapping obtener o buscar
	 * @PostMapping insertar o agregar
	 * @PutMapping modificar o actualizar
	 * @DeleteMapping eliminar o borrar
	 * */
	
	@PostMapping("/registrardetalleventa")
	public void registrarDetalleVenta(DetalleVentaVO dvent) {
		DetalleVentaDAO Dao = new DetalleVentaDAO();
		Dao.registrarDetalleVenta(dvent);
	}

	@GetMapping("/consultardetalleventas")
	public ArrayList<DetalleVentaVO> consultarDetalleVentas(Integer dvent) {
		DetalleVentaDAO Dao = new DetalleVentaDAO();
		return Dao.consultarDetalleVenta(dvent);
	}

	@GetMapping("/listardetalleventas")
	public ArrayList<DetalleVentaVO> listaDeDetalleVenta() {
		DetalleVentaDAO Dao = new DetalleVentaDAO();
		return Dao.listaDeDetalleVenta();
	}
	
	@DeleteMapping("/eliminardetalleventas")
	public void eliminarDetalleVenta(Integer dvent) {
		DetalleVentaDAO Dao = new DetalleVentaDAO();
		Dao.eliminarDetalleVenta(dvent);
	}
	
	@PutMapping("/actualizardetalleventas")
	public void actualizarDetalleVenta(DetalleVentaVO dvent) {
		DetalleVentaDAO Dao = new DetalleVentaDAO();
		Dao.actualizarDetalleVenta(dvent);
	}
	
	
}
