package net.lr.tasklist.blueprint.cdi.persistence;

import java.util.Collection;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.ops4j.pax.cdi.api.Properties;
import org.ops4j.pax.cdi.api.Property;

import net.lr.tasklist.blueprint.cdi.model.Task;
import net.lr.tasklist.blueprint.cdi.model.TaskService;

@OsgiServiceProvider(classes= {TaskService.class})
// The properties below allow to transparently export the service as a web service using Distributed OSGi
@Properties({
	@Property(name="service.exported.interfaces", value="*")
})
@Named
@Transactional
public class TaskServiceImpl implements TaskService {
	@PersistenceContext(unitName="tasklist")
	private EntityManager em;
	
	@Override
	@Transactional(Transactional.TxType.SUPPORTS)
	public Task getTask(Integer id) {
		return em.find(Task.class, id);
	}

	@Override
	public void addTask(Task task) {
		em.persist(task);
		em.flush();
	}

	@Override
	public void updateTask(Task task) {
		em.merge(task);
	}

	@Override
	public void deleteTask(Integer id) {
		em.remove(getTask(id));

	}

	@Override
	@Transactional(Transactional.TxType.SUPPORTS)
	public Collection<Task> getTasks() {
		CriteriaQuery<Task> query = em.getCriteriaBuilder().createQuery(Task.class);
		return em.createQuery(query.select(query.from(Task.class))).getResultList();
	}

}
