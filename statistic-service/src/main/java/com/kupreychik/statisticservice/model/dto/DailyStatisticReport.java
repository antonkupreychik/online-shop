package com.kupreychik.statisticservice.model.dto;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Data
public class DailyStatisticReport {
    private ProductDto product;
    private Integer totalCount;

    public DailyStatisticReport(ProductDto product, Integer totalCount) {
        this.product = product;
        this.totalCount = totalCount;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("product", product)
                .append("totalCount", totalCount)
                .toString();
    }
}
