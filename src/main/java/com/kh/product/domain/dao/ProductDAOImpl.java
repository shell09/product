package com.kh.product.domain.dao;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ProductDAOImpl implements ProductDAO {

  private final JdbcTemplate jdbcTemplate;

  // 상품등록
  @Override
  public Long write(Product product) {

    StringBuffer sql = new StringBuffer();
    sql.append("insert into product (product_id,product_name,product_price,product_fig) ");
    sql.append("values(product_product_id_seq.nextval, ?, ? ,?) ");

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(
        sql.toString(),
        new String[]{"product_id"}
        );
        pstmt.setString(1, product.getProductName());
        pstmt.setLong(2, product.getProductPrice());
        pstmt.setLong(3, product.getProductFig());

        return pstmt;
      }
    },keyHolder);

    return Long.valueOf(keyHolder.getKeys().get("product_id").toString());
  }

  // 전체조회
  @Override
  public List<Product> findAll() {
    StringBuffer sql = new StringBuffer();
    sql.append("select product_id, product_name, product_price, product_fig ");
    sql.append("  from product ");
    sql.append("order by product_id desc ");

    List<Product> products = jdbcTemplate.query(
            sql.toString(), new BeanPropertyRowMapper<>(Product.class));

    return products;
  }

  // 상세조회
  @Override
  public Product selectOne(Long productId) {
    StringBuffer sql = new StringBuffer();
    sql.append("select product_id, product_name, product_price, product_fig ");
    sql.append("from product ");
    sql.append("where product_id = ? ");

    List<Product> query = jdbcTemplate.query(
            sql.toString(), new BeanPropertyRowMapper<>(Product.class), productId);

    return (query.size() == 1) ? query.get(0) : null;
  }

  // 수정
  @Override
  public int modify(Long productId, Product product) {

    StringBuffer sql = new StringBuffer();
    sql.append("update product ");
    sql.append("set product_name = ?, ");
    sql.append("    product_price = ?, ");
    sql.append("    product_fig = ? ");
    sql.append("where product_id = ? ");

    int affectedRow = jdbcTemplate.update(
            sql.toString(),
            product.getProductName(),
            product.getProductPrice(),
            product.getProductFig(),
            productId);
    return affectedRow;
  }

  // 삭제
  @Override
  public int delete(Long productId) {

    StringBuffer sql = new StringBuffer();
    sql.append("delete from product ");
    sql.append(" where product_id = ? ");

    int cnt = jdbcTemplate.update(sql.toString(), productId);

    return cnt;
  }
}
