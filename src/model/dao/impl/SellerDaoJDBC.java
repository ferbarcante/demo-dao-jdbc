package model.dao.impl;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private Connection conn;

    public SellerDaoJDBC(Connection conn){
        this.conn = conn;
    }
    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deletById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try{
           st = conn.prepareStatement(
                   "SELECT seller. *,department.Name as DepName "
                   + "FROM seller INNER JOIN department "
                   + "ON seller.DepartmentId = department.Id "
                   + "WHERE seller.Id = ?"
           );

           st.setInt(1, id);
           rs = st.executeQuery();
           if(rs.next()){
               Department dep = new Department();
               dep.setId(rs.getInt("DepartmentId"));
               dep.setName(rs.getString("DepName"));

               Seller sl = new Seller();
               sl.setId(rs.getInt("Id"));
               sl.setName(rs.getString("Name"));
               sl.setEmail(rs.getString("Email"));
               sl.setBaseSalary(rs.getDouble("BaseSalary"));
               sl.setBirthDate(rs.getDate("BirthDate"));
               sl.setDepartment(dep);
               return sl;
           }
           return null;
        }
        catch (SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }
}
