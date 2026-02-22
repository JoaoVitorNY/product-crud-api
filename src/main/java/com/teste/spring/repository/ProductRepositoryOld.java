package com.teste.spring.repository;

import com.teste.spring.models.exception.ResourceNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.teste.spring.models.Product;

@Repository
public class ProductRepositoryOld {
    private List<Product> products = new ArrayList<Product>();
    private Integer lastId = 0;

    /** Metodo para retornar uma lista de Products
     * @return Lista de Products
     */
    public List<Product> selectAllProduct() {
        return products;
    }

    /** Metodo para retornar o Product pelo id
     * @param id do Product
     * @return Retorna um Product caso seja encontrado
     */
    public Optional<Product> getProductById(Integer id) {
        return products.stream().filter(Product -> Product.getId().equals(id)).findFirst();
    }

    /** Metodo para adicionar um novo Product
     * @param product Novo Product a ser adicionado
     * @return Retorna o Product adicionado
     */
    public Product addProduct(Product product) {
        lastId++;
        product.setId(lastId);
        products.add(product);

        return product;
    }

    /** Metodo para deletar um Product pelo id
     * @param id do Product a ser deletado
     */
    public void deleteProduct(Integer id) {
        products.removeIf(product -> product.getId().equals(id));
    }

    /** Metodo para atualizar um Product
     * @param product Product com os dados atualizados
     * @return Retorna o Product atualizado
     */
    public Product updateProduct(Product product) {
        // Verifica se o Product existe
        Optional<Product> productFound = getProductById(product.getId());
        if (productFound.isEmpty()) {
            throw new ResourceNotFoundException("Product not found");
        }
        // Remove o Product antigo e adiciona o atualizado
        deleteProduct(product.getId());
        products.add(product);

        return product;
    }
}
