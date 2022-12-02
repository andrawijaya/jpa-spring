package com.enigmacamp.model.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;
@ToString
public class PaggingResponse<T> extends CommonResponse {

    @Getter @Setter
    private List<T> data;
    @Getter @Setter
    private long count;
    @Getter @Setter
    private int totalPage;
    @Getter @Setter
    private int page;

    @Getter @Setter
    private int size;

    public PaggingResponse(String message, Page<T> page){
        super.setCode("00");
        super.setMessage(message);
        super.setStatus(HttpStatus.OK.name());
        this.data = page.getContent();
        this.count = page.getTotalElements();
        this.totalPage = page.getTotalPages();
        this.page = page.getNumber() + 1;
        this.size = page.getSize();
    }
}
