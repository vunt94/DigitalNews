/*
* Copyright(C) 2021,  vunthe141507.
* J3.L.P0004
* Java Web
*
* Record of change:
* DATE                Version           AUTHOR          DESCRIPTION
* 17/05/2021           1.0               VuNT       fix comment, fix bug
* 19/05/2021           2.0               VuNT       fix comment, fix bug
 */
package dao.impl;

import context.DBContext;
import dao.DigitalDAO;
import entity.Digital;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * This class has methods for retrieving data from digital tables in database.
 * In the searching, the data is normalized (removing spaces from both ends)
 * before searching This class extends class DBContext and implement interface
 * Methods that use Connection, PrepareStatement, ResultSet are closed in the
 * order in finally{} The method will throw an object of
 * <code>java.lang.Exception</code> class if there is any error occurring when
 * finding, inserting, or updating data
 * <p>
 * Bugs: None
 *
 * @author Nguyen Tuan Vu
 */
public class DigitalDAOImpl extends DBContext implements DigitalDAO {

    /**
     * Find the list Digital order by time post descending. Digital has time
     * post most recent will be returned The result contain a list of
     * <code>entity.Digital</code> objects with id, title, description, image,
     * author, timePost, shortDes
     *
     * @param number the number of Digital want get. It is an int number
     * @return a list of <code>Digital</code> objects. It is a
     * <code>java.util.ArrayList</code> 
     */
    @Override
    public ArrayList<Digital> getTopDigital(int number) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Digital> list = new ArrayList<>();
        try {
            String sql = "select top (?) * from digital \n"
                    + "order by timePost desc";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, number);
            rs = ps.executeQuery();
            while (rs.next()) {
                Digital digital = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                list.add(digital);
            }
            return list;
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(conn);
        }
    }

    
    /**
     * Find the Digital order by id. All the Digital has id matches with
     * searched id will be returned The result contain a Digital of
     * <code>entity.Digital</code> objects with id, title, description, image,
     * author, timePost, shortDes
     *
     * @param id the id of a digital. It is an int number
     * @return a Digital of <code>Digital</code> objects. It is an
     * <code>entity.Digital</code> object
     */
    @Override
    public Digital getSelectedNews(int id) throws Exception {

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select * from digital where id = ?";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                Digital digital = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                return digital;
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(conn);
        }
        return null;
    }

    /**
     * Find the list Digital by ID and title. All the Digital matched with id
     * order ascending, title and between pageIndex*(pageSize-2) and
     * pageIndex*pageSize will be returned The result contain a list of
     * <code>entity.Digital</code> objects with id, title, description, image,
     * author, timePost, shortDes
     *
     * @param searchContent the text in search box. It is a
     * <code>java.lang.String</code> object
     * @param pageIndex the index of page. It is an int number
     * @param pageSize the max number of news in each page. It is an int number
     * @return a list of <code>Digital</code> objects. It is a
     * <code>java.util.ArrayList</code> object
     */
    @Override
    public ArrayList<Digital> getSearchResult(String searchContent, 
            int pageIndex, int pageSize) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ArrayList<Digital> list = new ArrayList<>();
            String query = "select *from("
                    + "select ROW_NUMBER() over (order by ID ASC) as rn, *\n"
                    + "from digital where title \n"
                    + "like ?"
                    + ")as x\n"
                    + "where rn between ?*?-2"
                    + "and ?*?";
            conn = getConnection();
            ps = conn.prepareStatement(query);
            ps.setString(1, "%" + searchContent + "%");
            ps.setInt(2, pageIndex);
            ps.setInt(3, pageSize);
            ps.setInt(4, pageIndex);
            ps.setInt(5, pageSize);
            rs = ps.executeQuery();
            while (rs.next()) {
                Digital digital = new Digital(rs.getInt("ID"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("image"),
                        rs.getString("author"),
                        rs.getDate("timePost"),
                        rs.getString("shortDes"));
                list.add(digital);
            }
            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(conn);
        }
    }

    /**
     * Find the number of news after user search. All news has title matched
     * with searched title will be returned The result contain an int number
     *
     * @param searchContent the text in search box. It is a
     * <code>java.lang.String</code> object
     * @return an int number
     */  
    @Override
    public int countNumberOfResult(String searchContent) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select count(*) from digital where title like ?";
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + searchContent + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            closeResultSet(rs);
            closePreparedStatement(ps);
            closeConnection(conn);
        }
        return 0;
    }

}
