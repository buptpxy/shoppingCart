package service;

import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import util.HibernateUtil;
import entity.Goods;
import entity.Product;

public class ProductService {
	Session session = null;
	Transaction ts = null;
	/**
	 *���е���Ʒ�б�
	 * @return list
	 */
	public List<Product> selectAll() {
		session = HibernateUtil.openSession();
		Query query = session.createQuery("from Product");
		List<Product> list = query.list();
		HibernateUtil.closeSession(session);
		return list;
	}
	
	/**
	 * ����id��ѯ
	 * @param id
	 * @return product
	 */
	public Product selectById(int id) {
		session = HibernateUtil.openSession();
		Query query = session.createQuery("from Product where id = ?");
		query.setParameter(0, id);
		Product product = (Product) query.uniqueResult();
		HibernateUtil.closeSession(session);
		return product;
	}
	
	/**
	 * �жϴ���Ʒ�Ƿ��Ѿ����빺�ﳵ
	 * @param product
	 * @return
	 */
	public boolean hasSameGoods(Product product) {
		session = HibernateUtil.openSession();
		Query query = session.createQuery("from Goods where name = ?");
		query.setString(0, product.getName());
		List<Goods> list = query.list();
		HibernateUtil.closeSession(session);
		if(list.size()>0){
			Object[] options = { "�������", "ȡ��" }; 
			int res = JOptionPane.showOptionDialog(null, "����Ʒ�Ѵ��ڹ��ﳵ��", "����", 
			JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
			null, options, options[0]);
			if(res==0){
				Goods goods1 = (Goods) list.get(0);
				int count = goods1.getCount()+1;
				float price = goods1.getPrice();
				float subtotal = count * price;
				goods1.setCount(count);
				goods1.setSubtotal(subtotal);
				try {
					session = HibernateUtil.openSession();
					ts = session.beginTransaction();
					session.update(goods1);
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
			return true;
		}else {
			return false;
		}
	}
}
