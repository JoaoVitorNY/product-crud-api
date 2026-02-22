package com.teste.spring.service;

import com.teste.spring.models.Product;
import com.teste.spring.models.exception.ResourceNotFoundException;
import com.teste.spring.repository.ProductRepository;
import com.teste.spring.shared.ProductDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    /** Metodo para retornar uma lista de Products
     * @return Lista de Products
     */
    public List<ProductDTO> selectAllProducts(){
        // retorna uma lista de produtos model
        List<Product> products = productRepository.findAll();

        // converte a lista de produtos model para uma lista de produtos DTO usando o ModelMapper
        return products.stream()
                .map(product -> new ModelMapper()
                        .map(product, ProductDTO.class)).collect(Collectors.toList());
    }

    /** Metodo para retornar o Product pelo id
     * @param id do Product
     * @return Retorna um Product caso seja encontrado
     */
    public Optional<ProductDTO> getProductById(Integer id){
        // obtendo optional de produto do banco de dados
        Optional<Product> product = productRepository.findById(id);

        // se não encontrar o produto, lança uma exceção de recurso não encontrado
        if(product.isEmpty()){
            throw new ResourceNotFoundException("Product with id "+id+" not found");
        }

        // converte o produto model para um produto DTO usando o ModelMapper
        ProductDTO dto = new ModelMapper().map(product.get(), ProductDTO.class);

        // cria um optional de produto DTO e retorna
        return Optional.of(dto);
    }

     /** Metodo para adicionar um novo Product
     * @param productDto Novo Product a ser adicionado
     * @return Retorna o Product adicionado
     */
    public ProductDTO addProduct(ProductDTO productDto){
        // remove o id do produto DTO para garantir que um novo produto seja criado
        productDto.setId(null);

        // criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();

        // converter o produtoDTO em um produto
        Product product = mapper.map(productDto, Product.class);

        // salvar no banco de dados
        product = productRepository.save(product);

        productDto.setId(product.getId());

        // retornar o produtoDTO atualizado
        return productDto;
    }

    /** Metodo para deletar um Product pelo id
     * @param id do Product a ser deletado
     */
    public void deleteProduct(Integer id){
        Optional<Product> product = productRepository.findById(id);

        if(product.isEmpty()){
            throw new ResourceNotFoundException("Not possible to delete the product with id "+id+" - Product not exists");
        }

        productRepository.deleteById(id);
    }

    /** Metodo para atualizar um Product
     * @param productDto Product com os dados atualizados
     * @param id do Product a ser atualizado
     * @return Retorna o Product atualizado
     */
    public ProductDTO updateProduct(Integer id, ProductDTO productDto){
        // passar o id para productDTO
        productDto.setId(id);

        // criar um objeto de mapeamento
        ModelMapper mapper = new ModelMapper();

        // converter o produtoDTO em um produto
        Product product = mapper.map(productDto, Product.class);

        // atualizar o produto no banco de dados
        productRepository.save(product);

        // retornar o produtoDTO atualizado
        return productDto;
    }
}
