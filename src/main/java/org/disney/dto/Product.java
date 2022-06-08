package org.disney.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {
    private Integer id;
    private String content;
    private String experienceDetails;
    private Integer inventoryId;

    @JsonCreator
    public Product(@JsonProperty("id")Integer id,
                   @JsonProperty("content") String content,
                   @JsonProperty("experienceDetails") String experienceDetails,
                   @JsonProperty("inventoryId") Integer inventoryId) {
        this.id = id;
        this.content = content;
        this.experienceDetails = experienceDetails;
        this.inventoryId = inventoryId;
    }
}
