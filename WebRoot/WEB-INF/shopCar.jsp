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
				<%-- <th><s:checkbox id="checkAll" name="checkAll" label="全选" value="true" class="check-all check"></s:checkbox><th> --%>
				<th><label><input class="check-all check" type="checkbox" name="checkAll" value=1 >全选</input></label></th>
				
				<th>商品</th>
				<th>单价</th>
				<th>数量</th>
				<th>小计</th>
				<th>操作</th>
			</tr>			
		</thead>
		<tbody>
		<s:iterator value="goodsList"  var="goods" status="index">
			<tr>
					<%-- <td class="checkbox"><s:checkbox id="checkOne" name="checkOne" value="true" class="check-one check"></s:checkbox></td> --%>
					<td class="checkbox"><input class="check-one check" name="checkOne" type="checkbox" value=1></input></td>
					
					<td class="goods"><img src="<s:property value="#goods.pictureUrl"/>" ><span><s:property value="#goods.name" /></span></td>
					<td class="price"><s:property value="#goods.price" /></td>
					<td class="count">
						<span class="reduce" onclick="location='ReduceCount.action?modifyId=<s:property value="#goods.id"/>'">-</span> 
						<input class="count-input" type="text" value="<s:property value="#goods.count"/>"></input>
						<span class="add" onclick="location='AddCount.action?modifyId=<s:property value="#goods.id"/>'">+</span>
					</td>
					<td class="subtotal tot"><s:property value="#goods.subtotal" /></td>
					<td class="operation"><a href="DelGoods.action?deleteId=<s:property value="#goods.id"/>">删除</a></td>
			</tr>
		</s:iterator>
			
		</tbody>
	</table>
	<div class="foot" id="foot">
		<label class="fl select-all"><input class="check-all check" type="checkbox"></input>全选</label>
		<a href="javascript:;" class="fl delete" id="deleteAll">删除</a>
		<span class="fr closing"><a href="ProductList.action">查看商品详情</a></span><!-- 当向右浮动时最前面的标签在最右边 -->
		<span class="fr total">合计：￥<span id="priceTotal" class="tot">0</span></span>
		<div class="fr selected" id="selected">
			<span>已选商品<span id="selectedTotal" class="tot">0</span>件</span>
			<span class="arrow up">︽</span>
			<span class="arrow down">︾</span>
		</div>
		<div class="selected-view ">
			<div id="selectedViewList" class="clearfix">
				
			</div>
		</div>
	</div>
	<script type="text/javascript" src="js/shopCar1.js"></script>
</body>
</html>