package pl.edu.agh.db2.northwind.dao;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.db2.northwind.model.Product;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepositoryCustom {
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	private ProductRepository productRepository;

	@Override
	public <S extends Product> S saveOne(S s) {
		updateRelations(s);
		return productRepository.save(s);
	}

	@Override
	public <S extends Product> List<S> saveAll(Iterable<S> entities) {
		for (S s : entities) {
			updateRelations(s);
		}
		return productRepository.save(entities);
	}

	private <S extends Product> void updateRelations(S s) {
		s.setSupplier(supplierRepository.findOne(s.getSupplierId()));
		s.setCategory(categoryRepository.findOne(s.getCategoryId()));
	}
}
