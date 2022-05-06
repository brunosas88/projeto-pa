package br.com.letscode.projetopa.compra.repository;

import br.com.letscode.projetopa.compra.model.Compra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends MongoRepository<Compra, String> {
    Page<Compra> findByCpfCliente(String cpf, Pageable pageable);
}
