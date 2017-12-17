package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static SessionFactory sessionFactory = null;
	private static ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	private HibernateUtil(){};
	static{
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	//获取全新的session
	public static Session openSession(){
		return sessionFactory.openSession();
	}
	public static void closeSession(Session session){
		if(session != null && session.isOpen()){
			session.close();
		}
	}
	//获取和线程关联的session
	public static Session getCurrentSession(){
		Session session = threadLocal.get();
		if(session == null){
			session = sessionFactory.openSession();
			//把session对象设置到threadLocal,相当于这个session与线程绑定
			threadLocal.set(session);
		}
		return session;
	}
}
