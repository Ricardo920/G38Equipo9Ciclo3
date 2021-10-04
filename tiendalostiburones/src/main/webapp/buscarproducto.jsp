<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>


<!-- paquete de caracteres -->
<meta charset="utf-8">
<!-- Tamaño de la pantalla -->
<meta name="viewport" content="width=device-width">
<!-- titulo de la pestaña -->
<title>Buscar producto</title>
<!-- bootstrap-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">

<!-- font awesome -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css"
	integrity="sha384-DyZ88mC6Up2uqS4h/KRgHuoeGwBcD4Ng9SiP4dIRy0EXTlnuz47vAwmeGwVChigm"
	crossorigin="anonymous">

<!-- Cargando mi hoja de estilo -->
<link href="style.css" rel="stylesheet" type="text/css" />



</head>


<body>

<body background="https://www.ecestaticos.com/imagestatic/clipping/c08/a24/c08a2495ebeaecb195044abdd9f0304b/seis-alimentos-que-te-dijeron-que-eran-malos-pero-son-saludables.jpg?mtime=1579565836">

  		<br>
  <thead>
    <div class="d-grid gap-2 col-29 mx-auto">


	<!-- Navbar-->
	<left>
 <h1><i class="fas fa-fish"></i>Tu alacena a la mano!</h1>
      </left>

	<!-- Navbar modulos-->
	<nav class="navbar navbar-dark bg-primary">
		<div class="container">
			<a class="navbar-brand links" href="listausuarios.jsp"> <i
				class="fas fa-users"></i> Usuarios
			</a> <a class="navbar-brand links" href="listaclientes.jsp"> <i
				class="fas fa-address-book"></i> Clientes
			</a> <a class="navbar-brand links" href="listaproveedores.jsp"> <i
				class="fas fa-truck"></i> Proveedores
			</a> <a class="navbar-brand links" href="insertarproducto.jsp"> <i
				class="fas fa-apple-alt"></i> Productos
			</a> <a class="navbar-brand links" href="listausuarios.jsp"> <i
				class="fas fa-money-check-alt"></i> Ventas
			</a> <a class="navbar-brand links" href="listausuarios.jsp"> <i
				class="fas fa-clipboard-list"></i> Reportes
			</a>
		</div>
	</nav>

	<div style="padding-left: 5px">
		<h1>
			<i class="fas fa-search"></i> Buscando un producto
		</h1>
		<div class="container">


			<div id="error" class="alert alert-danger visually-hidden"
				role="alert">Error al buscar el producto, el producto no existe</div>

			<div id="correcto" class="alert alert-success visually-hidden"
				role="alert">Producto encontrado con exito</div>

			<form id="form1">
			
				<div class="input-group mb-3">
					<span class="input-group-text" id="basic-addon4">producto a buscar</span> <input
						type="text" class="form-control"
						placeholder="Inserte codigo de producto aqui..."
						aria-describedby="basic-addon4" required id="prodsearch" >
				</div>
				<br>
				<br>
				<br>
				<div class="input-group mb-3">
					<span class="input-group-text" id="basic-addon1">Codigo</span> <input
						type="text" class="form-control"
						aria-describedby="basic-addon1" required id="codigo_producto" disabled="disabled">
				</div>

				<div class="input-group mb-3">
					<span class="input-group-text" id="basic-addon2">Iva</span> <input
						type="text" class="form-control"
						aria-describedby="basic-addon2" required id="iva_compra" disabled="disabled">
				</div>

				<div class="input-group mb-3">
					<span class="input-group-text" id="basic-addon3">Nit proveedor</span>
					<input type="text" class="form-control"
						aria-describedby="basic-addon3" required id="nit_proveedor"  disabled="disabled">
				</div>

				<div class="input-group mb-3">
					<span class="input-group-text" id="basic-addon4">Nombre producto</span> <input
						type="text" class="form-control"
						aria-describedby="basic-addon4" required id="nombre_producto"  disabled="disabled">
				</div>

				<div class="input-group mb-3">
					<span class="input-group-text" id="basic-addon5">Precio compra</span> <input
						type="text" class="form-control"
						aria-describedby="basic-addon5" required id="precio_compra"  disabled="disabled">
				</div>
				
				<div class="input-group mb-3">
					<span class="input-group-text" id="basic-addon5">Precio venta</span> <input
						type="text" class="form-control"
						aria-describedby="basic-addon5" required id="precio_venta"  disabled="disabled">
				</div>
			</form>

			<button type="button" class="btn btn-primary" onclick="busqueda()">
				<i class="fas fa-search"></i> Buscar producto
			</button>
			
			<br>
			<br>
			<br>
			<br>
			<br>

			<h1>
				<i class="fas fa-cogs"></i> Operaciones
			</h1>
			<div class="container">
				<div class="row">
					<button type="button" class="btn btn-success"
						onclick="window.location.href='/buscarproducto.jsp'">
						<i class="fas fa-plus-circle"></i> buscar producto
					</button>
					<button type="button" class="btn btn-primary"
						onclick="window.location.href='/listaproductos.jsp'">
						<i class="fas fa-search"></i> lista productos
					</button>
				</div>
			</div>
		</div>

	</div>
	<nav class="navbar fixed-bottom navbar-dark bg-dark">
		<div class="row justify-content-between">
			<div class="col-4">
				<a class="navbar-brand links" href="#"><i class="fas fa-code"></i>
					Diseñado y programado por Equipo 9, Grupo 38 <i
					class="fas fa-code-branch"></i></a>
			</div>
		</div>
	</nav>
	<script>
		function busqueda() {

				
				var req = new XMLHttpRequest();
				var coincidencia = false;
				var prod=   document.getElementById("prodsearch").value;
				req.open('GET', 'http://localhost:8080/consultarproducto?product='+codigo, false);
				req.send(null);
				var producto = null;
				if (req.status == 200)
					producto = JSON.parse(req.responseText);
				console.log(JSON.parse(req.responseText));
				
			

				var element = document.getElementById("error");
				element.classList.add("visually-hidden");
				var element2 = document.getElementById("correcto");
				element2.classList.remove("visually-hidden");
				
				console.log(producto.toString());
				
			if (producto.toString()!=""){

				document.getElementById("codigo_producto").value = producto[0].codigo_producto;
				document.getElementById("iva_compra").value = producto[0].iva_compra;
				document.getElementById("nit_proveedor").value = producto[0].nit_proveedor;
				document.getElementById("nombre_producto").value = producto[0].nombre_producto;
				document.getElementById("precio_compra").value = producto[0].precio_compra;
				document.getElementById("precio_venta").value = producto[0].precio_venta;
				
				document.getElementById("prodsearch").value = "";
			

			} else {
				var element = document.getElementById("error");
				element.classList.remove("visually-hidden");
				var element2 = document.getElementById("correcto");
				element2.classList.add("visually-hidden");
				document.getElementById("codigo_producto").value = "";
				document.getElementById("iva_compra").value = "";
				document.getElementById("nit_proveedor").value = "";
				document.getElementById("nombre_producto").value = "";
				document.getElementById("precio_compra").value = "";
				document.getElementById("precio_venta").value = "";
			}
		}
	</script>
  </body>
</html>