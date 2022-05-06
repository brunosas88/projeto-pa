package br.com.letscode.projetopa.produto.repository;



import br.com.letscode.projetopa.produto.model.Produto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String> {

    Produto findProdutoByCodigo(String codigo);

}
