//**********************************************************************************************************************
// * Documentation
// * Author: zilin.li
// * Date: 12/22
// * Definition: Implementation of TinNewsDatabase class.
// * Note: abstract class used by Room to build a database
//**********************************************************************************************************************

package com.tzllzt.tinnews.database;
//**********************************************************************************************************************
// * Includes
//**********************************************************************************************************************

// Project includes
import com.tzllzt.tinnews.model.Article;

// Framework includes
import androidx.room.Database;
import androidx.room.RoomDatabase;


//**********************************************************************************************************************
// * Class definition
//**********************************************************************************************************************
@Database(entities = {Article.class}, version = 1, exportSchema = false)
public abstract class TinNewsDatabase extends RoomDatabase {

//**********************************************************************************************************************
// * Public attributes
//**********************************************************************************************************************
public abstract ArticleDao articleDao();

}
