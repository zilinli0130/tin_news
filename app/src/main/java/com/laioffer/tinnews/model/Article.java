//**********************************************************************************************************************
// * Documentation
// * Author: zilin.li
// * Date: 12/22
// * Definition: Implementation of Article class.
// * Node: data class for storing article information
//**********************************************************************************************************************
package com.laioffer.tinnews.model;

//**********************************************************************************************************************
// * Includes
//**********************************************************************************************************************

// Framework includes
import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// System includes
import java.util.Objects;

//**********************************************************************************************************************
// * Class definition
//**********************************************************************************************************************
@Entity
public class Article {

//**********************************************************************************************************************
// * Public methods
//**********************************************************************************************************************
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(author, article.author) && Objects.equals(content, article.content) && Objects.equals(description, article.description) && Objects.equals(publishedAt, article.publishedAt) && Objects.equals(title, article.title) && Objects.equals(url, article.url) && Objects.equals(urlToImage, article.urlToImage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, content, description, publishedAt, title, url, urlToImage);
    }

    @Override
    public String toString() {
        return "Article{" +
                "author='" + author + '\'' +
                ", content='" + content + '\'' +
                ", description='" + description + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", urlToImage='" + urlToImage + '\'' +
                '}';
    }

//**********************************************************************************************************************
// * Private attributes
//**********************************************************************************************************************

    @NonNull
    @PrimaryKey
    public String url;
    public String author;
    public String content;
    public String description;
    public String publishedAt;
    public String title;
    public String urlToImage;
}

