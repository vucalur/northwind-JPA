package pl.edu.agh.db2.northwind.dao.workaround;

import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

/**
 * <h1>Motivation</h1>
 * ManyToOne relations are represented in XML similarly to the way they are represented in relational database:<br/>
 * Node representing entity A contains Foreign Key (FK) stored as element with integer value
 * (or type of referenced entity's B Primary Key in general) <br/>
 * <p/>
 * We want to be able to read only this Foreign Key value, store it in entity A and persist entity A,
 * without having to worry about fetching B and updating reference to it in A.<br/>
 * Such a way of storing entities with ManyToOne relations is desirable when working with Object/XML Mapping (OXM).
 * <p/>
 * <h1>Solution</h1>
 * Introduced solution keeps both FK value (eg. Product.supplierId) and referenced entity B (eg. Product.supplier)
 * and stores them in single column in underlying database. <br/>
 * See sample class with ManyToOne relation: {@link pl.edu.agh.db2.northwind.model.Product} implementation for details
 * <p/>
 * Methods {@link #saveOne(Object)} and {@link #saveAll(Iterable)} assume that only FK has been set
 * and prior to saving entity they update reference to B entity.
 * <p/>
 * Whenever there is a need of persisting entities with relationships, say entities of type SampleClass, provide:
 * <ol>
 * <li>
 * First, interface extending {@link GenericRelationAwareRepository}: SampleClassRepositoryCustom  with empty body:<br/>
 * <pre>public interface SampleClassRepositoryCustom extends GenericRelationAwareRepository&lt;SampleClass&gt; {
 * }</pre><br/>
 * (this is merely a workaround for inability of Spring-Data-JPA to deduce from common, generic interface)
 * </li>
 * <li>
 * Implement {@link #saveOne(Object)} and {@link #saveAll(Iterable)} SampleClassRepositoryCustom in SampleClassRepositoryImpl<br/>
 * <b>Note:</b> SampleClassRepositoryImpl shall implement SampleClassRepositoryCustom, no {@link GenericRelationAwareRepository}
 * <h2>WARNING:</h2> Do not try to put those interfaces in separate packages as you'll encounter issues with Spring-Data-JPA<br/>
 * <b>Note:</b>In fact only {@link GenericRelationAwareRepository} can be placed in a separate package.
 * </li>
 * <li>
 * Add extend clause to the spring-data-jpa interface exposed to client, i.e:<br/>
 * <pre>public interface SampleClassRepository extends JpaRepository&lt;SampleClass, Integer&gt;, SampleClassRepositoryCustom {
 * }</pre>
 * </li>
 * <li>
 * Client pre should use saveOne() and saveAll() -
 * do not use 2 overloaded version of save(), as they have problems with setting both referenced object and FK when only FK has been set.
 * </li>
 * </ol>
 * <p/>
 * <h1>To be fixed</h1>
 * <b>↓↓↓ TODO</b><br/>
 * Methods that are inappropriate for storing (2 save() overloads from Crud/JpaRepository) shouldn't be exposed to client
 * in EndUser interfaces (eg. {@link pl.edu.agh.db2.northwind.dao.ProductRepository}).<br/>
 * User should be forced to use saveOne() and saveAll().
 * <h1>To be fixed 2</h1>
 * <b>↓↓↓ TODO</b><br/>
 * Way of dividing and fine-graining {@link pl.edu.agh.db2.northwind.dao} bulk package to smaller must be found.
 *
 * @param <T> Entity's A type
 */
@NoRepositoryBean
public interface GenericRelationAwareRepository<T> {
	// TODO: provide update (in crUd) operations if needed

	<S extends T> S saveOne(S s);

	<S extends T> List<S> saveAll(Iterable<S> entities);
}

