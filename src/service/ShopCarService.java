package service;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import entity.Goods;

public class ShopCarService {
	Session session = null;
	Transaction ts = null;
	/**
	 * 购物车中的商品列表
	 * @return list
	 */
	public List<Goods> selectAll() {
		session = HibernateUtil.openSession();
		Query query = session.createQuery("from Goods");
		List<Goods> list = query.list();
		HibernateUtil.closeSession(session);
		return list;
	}
	/**
	 * 根据id删除商品
	 * @param id
	 * @return
	 */
	public boolean deleltById(int id) {
		try {
			session = HibernateUtil.openSession();
			ts = session.beginTransaction();
			Query query = session.createQuery("delete Goods where id = ?");
			query.setParameter(0, id);
			query.executeUpdate();
			ts.commit();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			if(ts!=null){
				ts.rollback();
				return false;
			}
			throw new RuntimeException(e.getMessage());
		}finally{
			if(session!=null){
				HibernateUtil.closeSession(session);
			}
		}
	}
	/**
	 * 修改商品
	 * @param goods
	 */
	public void modify(Goods goods) {
		try {
			session = HibernateUtil.openSession();
			ts = session.beginTransaction();
			session.update(goods);
			ts.commit();
		}catch (Exception e) {
			// TODO: handle exception
			if(ts!=null){
				ts.rollback();	
			}
			throw new RuntimeException(e.getMessage());
		}finally{
			if(session!=null){
				HibernateUtil.closeSession(session);
			}
		}
	}
	
	/**
	 * 根据id查找商品
	 * @param id
	 * @return
	 */
	public Goods selectById(int id) {
		session = HibernateUtil.openSession();
		Query query = session.createQuery("from Goods where id = ?");
		query.setParameter(0, id);
		Goods goods = (Goods) query.uniqueResult();
		HibernateUtil.closeSession(session);
		return goods;
	}
	/**
	 * 增加购物车中的商品数量
	 * @param goods
	 */
	public void add(Goods goods){
		try {
			session = HibernateUtil.openSession();
			ts = session.beginTransaction();
			session.save(goods);
			ts.commit();	
		} catch (Exception e) {
			// TODO: handle exception
			if(ts!=null){
				ts.rollback();
			}
			throw new RuntimeException(e.getMessage());
		}finally{
			if(session!=null){
				HibernateUtil.closeSession(session);
			}
		}
	}
	
	
}
