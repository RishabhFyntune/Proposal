package com.example.proposal.RequestHandler;

public class ProposerPage
{

    private Integer page;
    private Integer size;
    private String sortBy;
    private String sortOrder;

    public ProposerPage(Integer page, Integer size, String sortBy, String sortOrder)
    {
        this.page = page;
        this.size = size;
        this.sortBy = sortBy;
        this.sortOrder = sortOrder;
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
