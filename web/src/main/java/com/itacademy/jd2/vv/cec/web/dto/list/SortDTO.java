package com.itacademy.jd2.vv.cec.web.dto.list;

public class SortDTO {

    private String column;

    private boolean ascending;

    public SortDTO(final String column) {
        this(column, false);
    }

    public SortDTO(final String column, final boolean ascending) {
        super();
        this.column = column;
        this.ascending = ascending;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(final String column) {
        this.column = column;
    }

    public boolean getAscending() {
        return ascending;
    }

    public void setAscending(final boolean ascending) {
        this.ascending = ascending;
    }

}
