package com.bh.mall.dao;

import java.util.List;

import com.bh.mall.dao.base.IBaseDAO;
import com.bh.mall.domain.Product;
import com.bh.mall.domain.ProductSpec;

public interface IProductDao extends IBaseDAO<Product>{

	String NAMESPACE = IProductDao.class.getName().concat(".");
	
	/**
	 * 添加产品
	 * @param data
	 */
	public int add(Product data);
	
	/**
	 * 修改产品
	 * @param data
	 * @return
	 */
	public int update(Product data); 
	
	/**
	 * 删除产品
	 * @param code
	 * @return
	 */
	public int delete(String code);
	
	/**
	 * 产品上架/下架
	 * @param code
	 * @param update
	 * @return
	 */
	public int shelfOrShelves(String code, String update);
	
	/**
	 * 添加库存
	 * @param productSpecsCode
	 * @param realNumber
	 * @param virNumber
	 * @return
	 */
	public int addStock(String productSpecsCode, Long realNumber, Long virNumber);
	
	/**
	 * 分页查询产品规格
	 * @param producCode
	 * @param limit
	 * @param orderCloumn
	 * @param start
	 * @return
	 */
	public List<ProductSpec> selectProductSpecsByPage(String producCode, Integer limit,  Integer start, String orderCloumn, String orderDir);
	
	/**
	 * 列表查询产品规格
	 * @param producCode
	 * @return
	 */
	public List<ProductSpec> selectProductSpecsList(String productCode);
	
	/**
	 * 详情查询产品规格
	 * @param code
	 * @return
	 */
	public ProductSpec selectProductSpecs(String code);
	
	/**
	 * 分页查询产品
	 * @param code
	 * @param name
	 * @param status
	 * @param orderColumn
	 * @param orderDir
	 * @param limit
	 * @param start
	 * @return
	 */
	public List<Product> selectProductByPage(String code, String name, String status, String orderColumn, String orderDir, 
			Integer limit, Integer start);
	
	
}
