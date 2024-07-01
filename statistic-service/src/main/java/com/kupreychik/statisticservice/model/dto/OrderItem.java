package com.kupreychik.statisticservice.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderItem {
    private Long productId;
    private Long quantity;
    private Long orderId;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("productId", productId)
                .append("quantity", quantity)
                .toString();
    }
}
