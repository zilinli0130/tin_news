//**********************************************************************************************************************
// * Documentation
// * Author: zilin.li
// * Date: 12/22
// * Definition: Implementation of NewsResponse class.
// * Node: data class for storing news response information (List<Article>)
//**********************************************************************************************************************
package com.tzllzt.tinnews.model;

//**********************************************************************************************************************
// * Includes
//**********************************************************************************************************************

// System includes
import java.util.List;
import java.util.Objects;

//**********************************************************************************************************************
// * Class definition
//**********************************************************************************************************************
public class NewsResponse {

//**********************************************************************************************************************
// * Public methods
//**********************************************************************************************************************
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewsResponse that = (NewsResponse) o;
        return Objects.equals(totalResults, that.totalResults) && Objects.equals(articles, that.articles) && Objects.equals(code, that.code) && Objects.equals(message, that.message) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalResults, articles, code, message, status);
    }

    @Override
    public String toString() {
        return "NewsResponse{" +
                "totalResults=" + totalResults +
                ", articles=" + articles +
                ", code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

//**********************************************************************************************************************
// * Private attributes
//**********************************************************************************************************************
    public Integer totalResults;
    public List<Article> articles;
    public String code;
    public String message;
    public String status;
}

