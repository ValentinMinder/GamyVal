package ch.heigvd.amt.gamyval.service.daos;

import ch.heigvd.amt.gamyval.models.entities.AbstractDomainModelEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Olivier Liechti (olivier.liechti@heig-vd.ch)
 */
@Local
public interface IGenericDAO<T extends AbstractDomainModelEntity, PK> {

  public PK create(T t);

  public T createAndReturnManagedEntity(T t);

  public void update(T t) throws BusinessDomainEntityNotFoundException ;

  public void delete(T t) throws BusinessDomainEntityNotFoundException ;

  public long count();
  
  public T findById(PK id) throws BusinessDomainEntityNotFoundException ;

  public List<T> findAll();
  
  public List<T> findAllByPage(int pageSize, int pageIndex);

}
