package action;

import java.util.List;

import javax.swing.JOptionPane;

import service.ShopCarService;

import com.opensymphony.xwork2.ActionSupport;

import entity.Goods;

public class ShopCarAction extends ActionSupport {
	private Goods goods;
	private List<Goods> goodsList;
	private int deleteId;
	private int modifyId;

	public int getModifyId() {
		return modifyId;
	}
	public void setModifyId(int modifyId) {
		this.modifyId = modifyId;
	}
	public Goods getGoods() {
		return goods;
	}
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public List<Goods> getGoodsList() {
		return goodsList;
	}
	public void setGoodsList(List<Goods> goodsList) {
		this.goodsList = goodsList;
	}
	public int getDeleteId() {
		return deleteId;
	}
	public void setDeleteId(int deleteId) {
		this.deleteId = deleteId;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		ShopCarService sc = new ShopCarService();
		goodsList = sc.selectAll();
		return SUCCESS;
	}
	
	public String delete() throws Exception {
		ShopCarService sc = new ShopCarService();
		Object[] options = { "是", "取消" }; 
		int res = JOptionPane.showOptionDialog(null, "确定删除此商品？", "警告", 
		JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, 
		null, options, options[0]);
		if(res==0){
			sc.deleltById(deleteId);
		}
//		System.out.println(res);
		goodsList = sc.selectAll();
		return SUCCESS;
	}
	
	public String addCount() throws Exception {
		// TODO Auto-generated method stub
		ShopCarService sc = new ShopCarService();
		goods = sc.selectById(modifyId);
		String name = goods.getName();
		String pictureUrl = goods.getPictureUrl();
		int count = goods.getCount()+1;
		float price = goods.getPrice();
		float subtotal = price*count;
		Goods goods1 = new Goods();
		goods1.setId(modifyId);
		goods1.setCount(count);
		goods1.setSubtotal(subtotal);
		goods1.setName(name);
		goods1.setPictureUrl(pictureUrl);
		goods1.setPrice(price);
		sc.modify(goods1);
		goodsList = sc.selectAll();
		return SUCCESS;
	}
	
	public String reduceCount() throws Exception {
		// TODO Auto-generated method stub
		ShopCarService sc = new ShopCarService();
		goods = sc.selectById(modifyId);
		String name = goods.getName();
		String pictureUrl = goods.getPictureUrl();
		int count = goods.getCount();
		if(count>0){
			count = count - 1;
		}
		float price = goods.getPrice();
		float subtotal = price*count;
		Goods goods1 = new Goods();
		goods1.setId(modifyId);
		goods1.setCount(count);
		goods1.setSubtotal(subtotal);
		goods1.setName(name);
		goods1.setPictureUrl(pictureUrl);
		goods1.setPrice(price);
		sc.modify(goods1);
		goodsList = sc.selectAll();
		return SUCCESS;
	}
	
	
}
