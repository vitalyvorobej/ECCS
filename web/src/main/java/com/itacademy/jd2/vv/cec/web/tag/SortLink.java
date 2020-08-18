package com.itacademy.jd2.vv.cec.web.tag;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.itacademy.jd2.vv.cec.web.dto.list.ListDTO;
import com.itacademy.jd2.vv.cec.web.dto.list.SortDTO;

public class SortLink extends SimpleTagSupport {

    private String column;

    private String pageUrl;

    @Override
    public void doTag() throws JspException, IOException {

        final JspContext jspContext = getJspContext();

        final ListDTO<?> listModel = (ListDTO<?>) getJspContext().findAttribute(ListDTO.SESSION_ATTR_NAME);
        if (listModel == null) {
            throw new IllegalArgumentException("context should have required attribute in session:" + ListDTO.SESSION_ATTR_NAME);
        }

        final SortDTO sort = listModel.getSort();
        String sortOrder = (sort != null && sort.getAscending()) ? "desc" : "asc";
        String sortColumn = null;
        if (sort != null) {
            sortColumn = sort.getColumn();
        }

        final String href = String.format("%s?sort=%s:%s", pageUrl, column, sortOrder);

        final StringWriter tagBodyWriter = new StringWriter();
        getJspBody().invoke(tagBodyWriter); // copy tag body defined in jsp

        String sortIcon;

        if (column.equals(sortColumn)) {
            sortIcon = sort.getAscending() ? "<i class=\"material-icons\">keyboard_arrow_down</i>"
                    : "<i class=\"material-icons\">keyboard_arrow_up</i>";
        } else {
            sortIcon = "";
        }

        jspContext.getOut().println(String.format("<a href=\"%s\">%s%s</a>", href, tagBodyWriter.toString(), sortIcon));
    }

    public void setColumn(final String column) {
        this.column = column;
    }

    public void setPageUrl(final String pageUrl) {
        this.pageUrl = pageUrl;
    }

}
