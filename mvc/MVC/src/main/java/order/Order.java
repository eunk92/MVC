package order;

public class Order {
	String orderNum;
	String orderDate;
	String goodsName;
	String buyerId;
	String address;
	int Total;
	
	public Order(String orderNum,String orderDate, String goodsName, String buyerId, String address, int total) {
		super();
		this.orderNum = orderNum;
		this.orderDate = orderDate;
		this.goodsName = goodsName;
		this.buyerId = buyerId;
		this.address = address;
		this.Total = total;
	}
	
	public Order() {
		// TODO Auto-generated constructor stub
	}

	public String getOrderDate() {
		return orderDate;
	}

	public String getOrderNum() {
		return orderNum;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public String getAddress() {
		return address;
	}

	public int getTotal() {
		return Total;
	}

	@Override
	public String toString() {
		return "주문 [" + orderNum + ", " + orderDate + ", " + goodsName + ", "
				+ buyerId + ", " + address + ", " + Total + "]";
	}


}
