package co.icesi.automoviles.dto;

import lombok.Data;
import org.springframework.data.domain.Page;

@Data
public class PageResponse<T> {
    private T[] items;
    private int page;
    private int totalPages;
    private int count;

    public PageResponse(Page<T> page, T[] a){
        this.items = page.getContent().toArray(a);
        this.page = page.getNumber()+1;
        this.totalPages = page.getTotalPages();
        this.count = (int) page.getTotalElements();
    }
}
