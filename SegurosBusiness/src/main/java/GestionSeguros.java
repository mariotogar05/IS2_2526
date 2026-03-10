
public class GestionSeguros implements IGestionSeguros, IGestionClientes, IInfoSeguros {

	IClientesDAO daoClientes;
	ISegurosDAO daoSeguros;
	
	public GestionSeguros(IClientesDAO daoClientes, ISegurosDAO daoSeguros) {
		this.daoClientes = daoClientes;
		this.daoSeguros = daoSeguros;
	}
	
	@Override
	public Cliente cliente(String dni) throws DataAccessException {
		Cliente cliente = daoClientes.cliente(dni);
		if (cliente == null) throw new DataAccessException();
		return cliente;
	}

	@Override
	public Seguro seguro(String matricula) throws DataAccessException {
		Seguro seguro = daoSeguros.seguroPorMatricula(matricula);
		if (seguro == null) throw new DataAccessException();
		return seguro;
	}

	@Override
	public Cliente nuevoCliente(Cliente c) throws DataAccessException {
		Cliente cliente = daoClientes.creaCliente(c);
		if (cliente == null) return null; 
		return cliente;
	}

	@Override
	public Cliente bajaCliente(String dni) throws OperacionNoValida, DataAccessException {
		Cliente cliente = daoClientes.cliente(dni);
		if (cliente == null) return null;
		
		if (cliente.getSeguros() != null && !cliente.getSeguros().isEmpty()) {
			throw new OperacionNoValida("El cliente tiene seguros a su nombre");
		}
		return daoClientes.eliminaCliente(dni);
	}

	@Override
	public Seguro nuevoSeguro(Seguro s, String dni) throws OperacionNoValida, DataAccessException {
		Cliente cliente = daoClientes.cliente(dni);
		if (cliente == null) return null;
		
		if (daoSeguros.seguroPorMatricula(s.getMatricula()) != null) {
			throw new OperacionNoValida("El seguro ya existe");
		}
		Seguro nuevo = daoSeguros.creaSeguro(s);
		cliente.getSeguros().add(nuevo);
		daoClientes.actualizaCliente(cliente); 
		
		return nuevo;
	}

	@Override
	public Seguro bajaSeguro(String matricula, String dni) throws OperacionNoValida, DataAccessException {
		Cliente cliente = daoClientes.cliente(dni);
		Seguro seguro = daoSeguros.seguroPorMatricula(matricula);

		if (cliente == null || seguro == null) return null;

		boolean pertenece = false;
		for (Seguro s : cliente.getSeguros()) {
		    if (s.getMatricula().equals(matricula)) {
		        pertenece = true;
		        break;
		    }
		}
		
		if (!pertenece) {
			throw new OperacionNoValida("El seguro no pertenece al dni indicado");
		}
		return daoSeguros.eliminaSeguro(seguro.getId());
	}

	@Override
	public Seguro anhadeConductorAdicional(String matricula, String conductor) throws DataAccessException {
		Seguro seguro = daoSeguros.seguroPorMatricula(matricula);
		if (seguro == null) return null;

		seguro.setConductorAdicional(conductor);
		return daoSeguros.actualizaSeguro(seguro);
	}
}
