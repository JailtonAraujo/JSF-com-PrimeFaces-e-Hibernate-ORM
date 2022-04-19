import javax.persistence.EntityManager;

import org.junit.Test;

import jpautil.JPAUtil;

public class TestUnit {
	
	@Test
	public void TestJPA () {
		EntityManager entityManager = JPAUtil.getEntityManager();
	}
}
