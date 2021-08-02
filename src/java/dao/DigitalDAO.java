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
package dao;

import entity.Digital;
import java.util.ArrayList;

/**
 * This class contains interfaces for retrieving data from digital tables in the
 * database.
 * <p>
 * Bugs: None
 *
 * @author Nguyen Tuan Vu
 */
public interface DigitalDAO {

    /**
     * Find the list Digital in the top "number" The result contain a list of
     * <code>entity.Digital</code> objects
     *
     * @param number the number of Digital want get. It is a number which has
     * type int
     * @return a list of <code>Digital</code> objects. It is a
     * <code>java.util.ArrayList</code> object
     * @throws java.lang.Exception
     */
    public ArrayList<Digital> getTopDigital(int number) throws Exception;

    /**
     * Find the Digital order by id. All the Digital has id matches with
     * searched id will be returned The result contain a Digital of
     * <code>entity.Digital</code> objects
     *
     * @param id the id of a digital. It is an int number
     * @return a Digital of <code>Digital</code> objects. It is a
     * <code>entity.Digital</code> object
     * @throws java.lang.Exception
     */
    public Digital getSelectedNews(int id) throws Exception;

    /**
     * Find the list Digital by ID and title. All the Digital matched with id
     * order ascending, title and between pageIndex*(pageSize-2) and
     * pageIndex*pageSize will be returned The result contain a list of
     * <code>entity.Digital</code> objects
     *
     * @param searchContent the text in search box. It is a
     * <code>java.lang.String</code> object
     * @param pageIndex the index of page. It is an int number
     * @param pageSize the max number of news in each page. It is a number which
     * has type int
     * @return a list of <code>Digital</code> objects. It is a
     * <code>java.util.ArrayList</code> object
     * @throws java.lang.Exception
     */
    public ArrayList<Digital> getSearchResult(String searchContent, 
            int pageIndex, int pageSize) throws Exception;

    /**
     * Find the number of news after user search. All news has title matched
     * with searched title will be returned The result contain an int number
     *
     * @param searchContent the text in search box. It is a
     * <code>java.lang.String</code> object
     * @return an int number
     * @throws java.lang.Exception
     */
    public int countNumberOfResult(String searchContent) throws Exception;

}
