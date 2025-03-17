package com.biznex.utils;

import com.biznex.common.constant.Status;
import com.biznex.common.request.PageableRequest;
import com.biznex.common.request.SearchCriteria;
import com.biznex.common.request.TypeSearch;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AppUtils {

    public void addSearchCriteriaStatus(PageableRequest pageable, Status status) {
        pageable.getSearch().add(new SearchCriteria("status", "=", status, TypeSearch.STRING));
    }
}
