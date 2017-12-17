<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<title>购物车</title>
</head>
<link rel="stylesheet" type="text/css" href="css/shopCar1.css">
<body>
	<table id="cartTable">
		<thead>
			<tr>
				<th>序号</th>		
				<th>商品</th>
				<th>单价</th>
				<th>操作</th>
			</tr>			
		</thead>
		<tbody>
			<s:iterator value="productList"  var="product" status="index">
			<tr>	
					<td class="number"> <s:property value="#index.index+1"/> </td>
					<td class="goods"><img src="<s:property value="#product.pictureUrl"/>" ><span><s:property value="#product.name" /></span></td>
					<td class="price"><s:property value="#product.price" /></td>
					<td class="operation"><a href="AddGoods.action?addId=<s:property value="#product.id"/>">添加到购物车</a></td>
			</tr>
		</s:iterator>
		</tbody>
	</table>
	<div class="foot" id="foot">
		<a href="GoodsList.action">查看购物车</a>
	</div>
	<script type="text/javascript" src="js/shopCar1.js"></script>
</body>
</html>