package action;

import java.util.List;

import service.ProductService;
import service.ShopCarService;

import com.opensymphony.xwork2.ActionSupport;

import entity.Goods;
import entity.Product;

public class ProductAction extends ActionSupport {
	private Product product;
	private List<Product> productList;
	private int addId;
	
	
	public int getAddId() {
		return addId;
	}
	public void setAddId(int addId) {
		this.addId = addId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		ProductService ps = new ProductService();
		productList = ps.selectAll();
		return SUCCESS;
	}
	
	public String addGoods() throws Exception {
		// TODO Auto-generated method stub
		ProductService ps = new ProductService();
		product = ps.selectById(addId);
		Goods goods = new Goods();
		String name = product.getName();
		String pictureUrl = product.getPictureUrl();
		float price = product.getPrice();
		goods.setCount(1);
		goods.setName(name);
		goods.setPictureUrl(pictureUrl);
		goods.setPrice(price);
		goods.setSubtotal(price);	
		ShopCarService sc = new ShopCarService();
		if(ps.hasSameGoods(product)==true){
			productList = ps.selectAll();
			return SUCCESS;
		}else {
			sc.add(goods);
			productList = ps.selectAll();
			return SUCCESS;
		}
	}
	
}
