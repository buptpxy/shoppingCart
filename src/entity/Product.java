package entity;

/**
 * 商品类
 */

public class Product implements java.io.Serializable {

	// Fields

	/**
	 * 主键id
	 */
	private Integer id;
	
	/**
	 * 商品名称
	 */
	private String name;
	
	/**
	 * 图片地址
	 */
	private String pictureUrl;
	
	/**
	 * 价格
	 */
	private Float price;

	// Constructors

	/** default constructor */
	public Product() {
	}

	/** full constructor */
	public Product(String name, String pictureUrl, Float price) {
		this.name = name;
		this.pictureUrl = pictureUrl;
		this.price = price;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPictureUrl() {
		return this.pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public Float getPrice() {
		return this.price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

}