// package com.example.demo.dao.impl;
// import java.util.ArrayList;
// import java.util.List;
// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import javax.sql.DataSource;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Repository;

// import com.example.demo.dao.CustomerDAO;
// import com.example.demo.entity.Customer;
// import com.example.demo.entity.AddressCount;

// @Repository
// public class CustomerDAOImpl implements CustomerDAO {
//   @Autowired
//   private DataSource dataSource;
  
//   private Customer getCustomer(ResultSet rs) throws SQLException, Exception{
//     return new Customer(
//       rs.getLong("id"),
//       rs.getString("name"),
//       rs.getString("address"),
//       rs.getInt("weight"));
//   }

//   public Customer get(Long id) throws Exception{
//     Customer customer = new Customer(-1l,"","",1);
//     String sql = "select id, name, address, weight from customer where id = ?";
//     //String sql = "select id, name, address, weight from customer where id = ?";
//     //當 Connection與PreparedStatement無法取得時，會closeConnection與PreparedStatement
//     try (
//       Connection conn = dataSource.getConnection();
//       PreparedStatement stmt = conn.prepareStatement(sql);
//       ){
//       stmt.setLong(1, id);
//       try (ResultSet rs = stmt.executeQuery();){
//         if (rs.next()){
//           customer=getCustomer(rs);
//         } 
//       }
//       catch (Exception e){
//         throw e;
//       }
//     }
//     catch (Exception e){
//       throw e;
//     }
//     return customer;

//   }

//   public List<Customer> getNameList(String name) throws Exception{
//     List<Customer> customers = new ArrayList<Customer>();
//     String sql = "select id, name, address, weight from customer where name like ?";
//     //當 Connection與PreparedStatement無法取得時，會closeConnection與PreparedStatement
//     try (
//       Connection conn = dataSource.getConnection();
//       PreparedStatement stmt = conn.prepareStatement(sql);){
//       stmt.setString(1, "%"+name+"%");
//       try (ResultSet rs = stmt.executeQuery();){
//         while (rs.next()){
//           customers.add(getCustomer(rs));
//         } 
//       }
//       catch (Exception e){
//         throw e;
//       }
//     }
//     catch (Exception e){
//       throw e;
//     }
//     return customers;

//   }


//   public List<Customer> getList() throws Exception{
//     List<Customer> customers = new ArrayList<Customer>();
//     String sql = "select id, name, address, weight from customer";
//     try(
//       Connection conn = dataSource.getConnection();
//       PreparedStatement stmt = conn.prepareStatement(sql);
//       ResultSet rs = stmt.executeQuery();) {
//       while (rs.next()){
//         customers.add(getCustomer(rs));
//       }  
      
//     } catch(Exception e) {
//       throw e;
//     }
//     return customers;
//   }

//   public List<Customer> getWeightOrderedList() throws Exception{
//     List<Customer> customers = new ArrayList<Customer>();
//     String sql = "select id, name, address, weight from customer order by weight";
//     try(
//       Connection conn = dataSource.getConnection();
//       PreparedStatement stmt = conn.prepareStatement(sql);
//       ResultSet rs = stmt.executeQuery();) {
//       while (rs.next()){
//         customers.add(getCustomer(rs));
//       }
//     } catch(Exception e) {
//       throw e;
//     }
//     return customers;
//   }

//   public List<Customer> getWeightBetweenList(int lowBound, int upperBound) throws Exception{
//     List<Customer> customers = new ArrayList<Customer>();
//     String sql = "select id, name, address, weight from customer where weight between ? and ?";
//     try(
//       Connection conn = dataSource.getConnection();
//       PreparedStatement stmt = conn.prepareStatement(sql);) {
//       stmt.setInt(1,lowBound);
//       stmt.setInt(2,upperBound);
//       try(ResultSet rs = stmt.executeQuery();){
//         while (rs.next()){
//           customers.add(getCustomer(rs));
//         }  
//       }
//       catch(Exception e) {throw e;}  
//     } catch(Exception e) {
//       throw e;
//     }
//     return customers;
//   }

//   public List<String> getDistinctAddressList() throws Exception{
//     List<String> customers = new ArrayList<String>();
//     String sql = "select distinct address from customer";
//     try(
//       Connection conn = dataSource.getConnection();
//       PreparedStatement stmt = conn.prepareStatement(sql);
//       ResultSet rs = stmt.executeQuery();) {
//       while (rs.next()){
//         customers.add(rs.getString("address"));
//       }  
//     } catch(Exception e) {
//       throw e;
//     }
//     return customers;
//   }

//   public List<AddressCount> getGroupByAddressList() throws Exception{
//     List<AddressCount> customers = new ArrayList<AddressCount>();
//     String sql = "select address, COUNT(address) As count from customer group by address";
//     try(
//       Connection conn = dataSource.getConnection();
//       PreparedStatement stmt = conn.prepareStatement(sql);
//       ResultSet rs = stmt.executeQuery();) {     
//       while (rs.next()){
//         //System.out.println(rs.getString("COUNT(address)"));
//         //customers.add(new AddressCount(rs.getString("address"), rs.getInt("COUNT(address)")));
//         customers.add(new AddressCount(rs.getString("address"), rs.getInt("count")));
//       }  
//     } catch(Exception e) {
//       throw e;
//     }
//     return customers;
//   }

//   public List<String> getGroupByAddressHavingList() throws Exception{
//     List<String> customers = new ArrayList<String>();
//     String sql = "select address from customer group by address having COUNT(address)>2";
//     try(
//       Connection conn = dataSource.getConnection();
//       PreparedStatement stmt = conn.prepareStatement(sql);) {
//       try(ResultSet rs = stmt.executeQuery();){
//         while (rs.next()){
//           customers.add(rs.getString("address"));
//         }  
//       }
//       catch(Exception e) {throw e;}  
//     } catch(Exception e) {
//       throw e;
//     }
//     return customers;
//   }

//   public int insert(Customer customer) throws Exception{
//     int result = 0;
//     String sql = "insert into customer (name, address, weight) values(?, ?, ?)";
//     try( 
//       Connection conn = dataSource.getConnection();
//       PreparedStatement stmt = conn.prepareStatement(sql);) {    
//       stmt.setString(1, customer.getName());
//       stmt.setString(2, customer.getAddress());
//       stmt.setInt(3, customer.getWeight());
//       result = stmt.executeUpdate();
//     } catch(Exception e) {
//       //something wrong
//       throw e;
//     }
//     return result;
//   }
//   public int update(Customer customer) throws Exception{
//     int result = 0;
//     String sql = "update customer set name=?, address=?, weight=? where id =?";
//     try(
//       Connection conn = dataSource.getConnection();
//       PreparedStatement stmt = conn.prepareStatement(sql);) {
//       stmt.setString(1, customer.getName());
//       stmt.setString(2, customer.getAddress());
//       stmt.setInt(3, customer.getWeight());
//       stmt.setLong(4, customer.getId());
//       result = stmt.executeUpdate();
//     } catch(Exception e) {
//       throw e;
//     }
//     return result;
//   }
// }
