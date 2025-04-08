package com.example.proposal.Pagenation;

public class ProposerPage
{

    private Integer page;
    private Integer size;
    private String sortBy;
    private String sortOrder;
    private SearchFilter[] searchFilters;

    public SearchFilter[] getSearchFilters() {

        return searchFilters;
    }

    public void setSearchFilters(SearchFilter[] searchFilters) {
        this.searchFilters = searchFilters;
    }

    public ProposerPage()
    {

    }

    public Integer getPage()
    {
        return page;
    }

    public void setPage(Integer page)
    {
        this.page = page;
    }

    public Integer getSize()
    {
        return size;
    }

    public void setSize(Integer size)
    {
        this.size = size;
    }

    public String getSortBy()
    {
        return sortBy;
    }

    public void setSortBy(String sortBy)
    {
        this.sortBy = sortBy;
    }

    public String getSortOrder()
    {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder)
    {
        this.sortOrder = sortOrder;
    }


    //    sortby;
//sortorder
}

//grttrt
