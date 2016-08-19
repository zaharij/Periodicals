/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.service;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import model.dao.impl.ArticlesDao;
import model.entities.Article;

/**
 *
 * @author Zakhar
 */
public class ArticlesService {
    private ArticlesDao articlesDao = new ArticlesDao();
    
    /**
      * getArticleNamesByPeriodical
      * get article's names in periodical
      * get payed periodicals names 
      * @return array list of names
      */
     public List<String> getArticleNamesByPeriodical (String periodical){
         try{
            List<String> resultListNames = articlesDao.getAllArticleNamesByPeriodical(periodical);
            Collections.reverse(resultListNames);
            return resultListNames;
         }catch (RuntimeException ex){
             return null;
         }
     }
          
     /**
      * getAboutArticleText
       get text about current article
     * @param title of the article
     * @return text about article
      */
     public String getAboutArticleText (String title){
         try{
            return articlesDao.getAboutArticleText(title);
         }catch (RuntimeException ex){
             return null;
         }
     }
     
     /**
      * getArticleText
       get current article text
     * @param title of the article
     * @return text
      */
     public String getArticleText (String title){
         try{
            return articlesDao.getArticleText(title);
         }catch (RuntimeException ex){
             return null;
         }
     }
     
     /**
      * getArticleDate
      * get article Date
      * @return 
      */
     public String getArticleDate (String title){
         try{
            return articlesDao.getArticleDate(title).toString();
         }catch (RuntimeException ex){
             return null;
         }
     }
         
     /**
      * setArticleToDB
      * @param article 
      */
     public void setArticleToDB (Article article){
         try{
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            article.setTimestamp(timestamp);
            articlesDao.insertNewArticle(article);
         }catch (RuntimeException ex){
         }
     }
     
     /**
     * deleteArticle
     * delete article
     * @param title
     * @param periodical 
     */
    public void deleteArticle (String title){
        try{
            articlesDao.deleteArticle(title);
        }catch (RuntimeException ex){
         }
    }
    
    /**
     * getNotPermittedArticles
     * @return 
     */
    public List<String> getNotPermittedArticles (){
        try{
            List<String> resultListNames = articlesDao.getNotPermittedArticles();
            Collections.reverse(resultListNames);
            return resultListNames;
        }catch (RuntimeException ex){
            return null;
        }
    }
    
    /**
     * setBlockUser
     * @param email 
     */
    public void setArticlePermissionTrue (String title){
        try{
            articlesDao.setArticlePermission(title);
        }catch (RuntimeException ex){
         }
    }
}
